package hr.abc.psd2.bean.impl;

import hr.abc.psd2.bean.IPsd2FilterBean;
import hr.abc.psd2.config.AppConfig;
import hr.abc.psd2.dao.Psd2UtilDao;
import hr.abc.psd2.model.MessageType;
import hr.abc.psd2.model.dto.*;
import hr.abc.psd2.model.dto.error.ErrorCode;
import hr.abc.psd2.model.dto.error.ErrorInformationDto;
import hr.abc.psd2.resources.ws.filter.ChainOfTrust;
import hr.abc.psd2.util.*;
import io.quarkus.arc.properties.IfBuildProperty;

import io.vertx.core.http.HttpServerRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.ConstraintViolationException;

import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.WriterInterceptorContext;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static hr.abc.psd2.bean.impl.ServiceConstants.SERVICE_SBER;
import static hr.abc.psd2.bean.impl.ServiceConstants.TRUE;
import static hr.abc.psd2.model.MessageType.REQUEST;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.*;

@Slf4j
@TransactionAttribute
@ApplicationScoped
@IfBuildProperty(name = SERVICE_SBER, stringValue = TRUE)
public class Psd2FilterBean implements IPsd2FilterBean , Serializable {

    private static final long serialVersionUID = 1L;

    /*@Inject protected
    Psd2MessageLogDao psd2MessageLogDao;*/

    @Inject protected
    Psd2UtilDao psd2UtilDao;

    @Inject
    AppConfig appConfig;

    @Inject
    protected Psd2CertificateSignatureBean psd2CertificateSignatureBean;

    public static final String DEFAULT_DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm:ss";
    public static final String LOG_DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm:ss_SSS";


    @Override
    public ProcessRequestDto processRequest(ResourceInfo resourceInfo, ContainerRequestContext ctx, HttpServerRequest httpServletRequest) {
        ProcessRequestDto processRequestDto = new ProcessRequestDto();
        processRequestDto.setIsValid(true);
        ErrorInformationDto errorInformationDto = new ErrorInformationDto();
        String requestQWacRoles = null;
        String requestQSealRoles = null;
        InputStream keyStore = null;
        String logMessageString = null;
        String xRequestId = null;
        try {
            xRequestId = ctx.getHeaderString(X_REQUEST_ID.getValue());
            //QWAC certifikat
            // String useQWacCert = LookupHelper.getICoreDaoProxyRemote().getVrijednostParametraBySifra(Psd2Constants.PARAMETAR_PSD2_QWAC_USE); // stari
            String useQWacCert = appConfig.qwacUse(); //getVrijednostParametraBySifra(Psd2Constants.PARAMETAR_PSD2_QWAC_USE);
            CertificateInfoDto qWacCertificateInfoDto = null;

            if (StringUtils.equals(useQWacCert, Psd2Constants.PSD2_QWAC_USE)) {
                keyStore = new FileInputStream(SignatureContants.PSD2_KEYSTORE_FILE);
                qWacCertificateInfoDto = ChainOfTrust.getCertificateInfo(httpServletRequest.getHeader("ssl_client_cert"), keyStore, SignatureContants.PSD2_KEYSTORE_PASS);
                qWacCertificateInfoDto = psd2CertificateSignatureBean.checkQWacCertificate(httpServletRequest);
                keyStore.close();
                keyStore = null;
                if (!qWacCertificateInfoDto.getIsValid()) {
                    //log request
                    logMessageString = logRequest(resourceInfo, ctx, httpServletRequest, qWacCertificateInfoDto);
                    if (qWacCertificateInfoDto != null) {
                       // psd2MessageLogDao.create(new Psd2MessageLogDto(xRequestId, REQUEST.toString(), Timestamp.valueOf(LocalDateTime.now()), logMessageString, StringUtils.isNotBlank(qWacCertificateInfoDto.getOrganizationID()) ? qWacCertificateInfoDto.getOrganizationID() : null,
                               // httpServletRequest.getHeader(X_FORWARDED_FOR.getValue()), httpServletRequest.getRemoteHost(), httpServletRequest.getHeader(PSU_IP_ADDRESS.getValue())));
                        log.info("REQUEST INFO: "+new Psd2MessageLogDto(xRequestId, REQUEST.toString(), Timestamp.valueOf(LocalDateTime.now()), logMessageString, StringUtils.isNotBlank(qWacCertificateInfoDto.getOrganizationID()) ? qWacCertificateInfoDto.getOrganizationID() : null,
                                httpServletRequest.getHeader(X_FORWARDED_FOR.getValue()), httpServletRequest.remoteAddress().host(), httpServletRequest.getHeader(PSU_IP_ADDRESS.getValue())).toString());
                    } else {
                        log.info("REQUEST INFO: "+new Psd2MessageLogDto(xRequestId, REQUEST.toString(), Timestamp.valueOf(LocalDateTime.now()), logMessageString, null,
                                 httpServletRequest.getHeader(X_FORWARDED_FOR.getValue()), httpServletRequest.remoteAddress().host(), httpServletRequest.getHeader(PSU_IP_ADDRESS.getValue())));
                        //psd2MessageLogDao.create(new Psd2MessageLogDto(xRequestId, REQUEST.toString(), Timestamp.valueOf(LocalDateTime.now()), logMessageString, null,
                               // httpServletRequest.getHeader(X_FORWARDED_FOR.getValue()), httpServletRequest.getRemoteHost(), httpServletRequest.getHeader(PSU_IP_ADDRESS.getValue())));
                    }
                    processRequestDto.setIsValid(false);
                    processRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.CERTIFICATE_INVALID.getValue(), qWacCertificateInfoDto.getErrorList()));
                    return processRequestDto;
                } else {
                    requestQWacRoles = StringFunctions.getDelimitedTextFromList(qWacCertificateInfoDto.getTppRoles(), ",");
                    processRequestDto.setTppId(qWacCertificateInfoDto.getOrganizationID());
                    processRequestDto.setTppName(qWacCertificateInfoDto.getOrganizationName());
                }
            } else {
                requestQWacRoles = httpServletRequest.getHeader(TPP_ROLE.getValue());
                processRequestDto.setTppId(httpServletRequest.getHeader(TPP_ID.getValue()));
                processRequestDto.setTppName(httpServletRequest.getHeader(TPP_NAME.getValue()));
            }
            //log request
            logMessageString = logRequest(resourceInfo, ctx, httpServletRequest, qWacCertificateInfoDto);

            if (qWacCertificateInfoDto != null) {
                //DB log
                log.info("REQUEST INFO: "+new Psd2MessageLogDto(xRequestId, REQUEST.toString(), Timestamp.valueOf(LocalDateTime.now()), logMessageString, StringUtils.isNotBlank(qWacCertificateInfoDto.getOrganizationID()) ? qWacCertificateInfoDto.getOrganizationID() : null,
                         httpServletRequest.getHeader(X_FORWARDED_FOR.getValue()), httpServletRequest.remoteAddress().host(), httpServletRequest.getHeader(PSU_IP_ADDRESS.getValue())));
                //psd2MessageLogDao.create(new Psd2MessageLogDto(xRequestId, REQUEST.toString(), Timestamp.valueOf(LocalDateTime.now()), logMessageString, StringUtils.isNotBlank(qWacCertificateInfoDto.getOrganizationID()) ? qWacCertificateInfoDto.getOrganizationID() : null,
                       // httpServletRequest.getHeader(X_FORWARDED_FOR.getValue()), httpServletRequest.getRemoteHost(), httpServletRequest.getHeader(PSU_IP_ADDRESS.getValue())));
                //add attribute for response log
               // httpServletRequest.setAttribute(TPP_ID.getValue(), StringUtils.isNotBlank(qWacCertificateInfoDto.getOrganizationID()) ? qWacCertificateInfoDto.getOrganizationID() : null);
                httpServletRequest.headers().add(TPP_ID.getValue(), StringUtils.isNotBlank(qWacCertificateInfoDto.getOrganizationID()) ? qWacCertificateInfoDto.getOrganizationID() : null);
            } else {
                log.info("REQUEST INFO: "+new Psd2MessageLogDto(xRequestId, REQUEST.toString(), Timestamp.valueOf(LocalDateTime.now()), logMessageString, null,
                              httpServletRequest.getHeader(X_FORWARDED_FOR.getValue()), httpServletRequest.remoteAddress().host(), httpServletRequest.getHeader(PSU_IP_ADDRESS.getValue())));
                //psd2MessageLogDao.create(new Psd2MessageLogDto(xRequestId, REQUEST.toString(), Timestamp.valueOf(LocalDateTime.now()), logMessageString, null,
                //      httpServletRequest.getHeader(X_FORWARDED_FOR.getValue()), httpServletRequest.getRemoteHost(), httpServletRequest.getHeader(PSU_IP_ADDRESS.getValue())));
            }

            //validate tpp_id
            if (StringUtils.isBlank(processRequestDto.getTppId())) {
                processRequestDto.setIsValid(false);
                if (StringUtils.equals(useQWacCert, Psd2Constants.PSD2_QWAC_USE)) {
                    processRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.CERTIFICATE_INVALID.getValue(), new ArrayList<>(Arrays.asList("Nedostaje TPP-ID u QWAC certifikatu"))));
                } else {
                    processRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.CERTIFICATE_INVALID.getValue(), new ArrayList<>(Arrays.asList("Nedostaje TPP-ID ---> parametar TPP-ID je obavezan request header parametar"))));
                }
                return processRequestDto;
            }
            //validate role
            if (StringUtils.isBlank(requestQWacRoles)) {
                processRequestDto.setIsValid(false);
                if (StringUtils.equals(useQWacCert, Psd2Constants.PSD2_QWAC_USE)) {
                    processRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.CERTIFICATE_INVALID.getValue(), new ArrayList<>(Arrays.asList("Nedostaje TPP-Role u QWAC certifikatu"))));
                } else {
                    processRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.CERTIFICATE_INVALID.getValue(), new ArrayList<>(Arrays.asList("Nedostaje TPP-Role ---> parametar TPP-Role je obavezan request header parametar"))));
                }
                return processRequestDto;
            }
            if (!isRoleValid(requestQWacRoles, resourceInfo.getResourceMethod().getName())) {
                processRequestDto.setIsValid(false);
                processRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.INVALID_TPP_ROLE.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_role_invalid", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                return processRequestDto;
            }
            //QSeal certifikat
            //String useQSealCert = LookupHelper.getICoreDaoProxyRemote().getVrijednostParametraBySifra(Psd2Constants.PARAMETAR_PSD2_QSEAL_USE); // stari
            String useQSealCert = appConfig.qwacSeal();//getVrijednostParametraBySifra(Psd2Constants.PARAMETAR_PSD2_QSEAL_USE);
            String signatureRequestHeader = httpServletRequest.getHeader(SIGNATURE.getValue());
            String digestRequestHeader = httpServletRequest.getHeader(DIGEST.getValue());
            String qSealCertRequestHeader = httpServletRequest.getHeader(TPP_SIGNATURE_CERTIFICATE.getValue());
            if (StringUtils.equals(useQSealCert, Psd2Constants.PSD2_QSEAL_USE) && (StringUtils.isBlank(signatureRequestHeader) ||
                    StringUtils.isBlank(digestRequestHeader) || StringUtils.isBlank(qSealCertRequestHeader))) {
                processRequestDto.setIsValid(false);
                processRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.SIGNATURE_MISSING.getValue(), new ArrayList<>(Arrays.asList("Nedostaje Signature i/ili Digest i/ili TPP-Signature-Certificate ---> parametari Signature, Digest i TPP-Signature-Certificate su obavezani request header parametri"))));
                return processRequestDto;
            }
            if (StringUtils.isNotBlank(signatureRequestHeader)) {
                digestRequestHeader = httpServletRequest.getHeader(DIGEST.getValue());
                qSealCertRequestHeader = httpServletRequest.getHeader(TPP_SIGNATURE_CERTIFICATE.getValue());
                if (StringUtils.isBlank(digestRequestHeader) || StringUtils.isBlank(qSealCertRequestHeader)) {
                    processRequestDto.setIsValid(false);
                    processRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.SIGNATURE_MISSING.getValue(), new ArrayList<>(Arrays.asList("Nedostaje Digest i/ili TPP-Signature-Certificate ---> parametari Digest i TPP-Signature-Certificate su obavezani request header parametri"))));
                    return processRequestDto;
                }
                CertificateInfoDto qSealCertificateInfoDto = null;
                //get and validate QSeal certificate
                keyStore = new FileInputStream(SignatureContants.PSD2_KEYSTORE_FILE);
                qSealCertificateInfoDto = ChainOfTrust.getCertificateInfo(httpServletRequest.getHeader(TPP_SIGNATURE_CERTIFICATE.getValue()), keyStore, SignatureContants.PSD2_KEYSTORE_PASS);
                qSealCertificateInfoDto = psd2CertificateSignatureBean.checkQSealCertificate(httpServletRequest);
                if (!qSealCertificateInfoDto.getIsValid()) {
                    processRequestDto.setIsValid(false);
                    processRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.CERTIFICATE_INVALID.getValue(), qSealCertificateInfoDto.getErrorList()));
                    return processRequestDto;
                }
                requestQSealRoles = StringFunctions.getDelimitedTextFromList(qSealCertificateInfoDto.getTppRoles(), ",");
                //validate role QSEAL
                if (StringUtils.isBlank(requestQSealRoles)) {
                    processRequestDto.setIsValid(false);
                    processRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.CERTIFICATE_INVALID.getValue(), new ArrayList<>(Arrays.asList("Nedostaje TPP-Role u QSeal certifikatu"))));
                    return processRequestDto;
                }
                if (!isRoleValid(requestQSealRoles, resourceInfo.getResourceMethod().getName())) {
                    processRequestDto.setIsValid(false);
                    processRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.INVALID_TPP_ROLE.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_role_invalid_qseal", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                    return processRequestDto;
                }
                //provjera potpisa
                ProcessRequestDto processRequestDto1 = psd2CertificateSignatureBean.validateRequestSignature(qSealCertificateInfoDto.getCertificate(), httpServletRequest, ctx);
                if (!processRequestDto1.getIsValid()) {
                    return processRequestDto1;
                }
            }
            //validate mandatory request header param
            RequestHeaderDto requestHeaderDto = validateMandatoryHeaderAttributes(resourceInfo, httpServletRequest);
            if (!requestHeaderDto.getIsValid()) {
                processRequestDto.setIsValid(false);
                processRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), requestHeaderDto.getErrorList()));
                return processRequestDto;
            }
            //validateXRequestId
            RequestHeaderDto requestHeaderDtoX = validateXRequestId(httpServletRequest);
            if (!requestHeaderDtoX.getIsValid()) {
                processRequestDto.setIsValid(false);
                for (Map.Entry<String, String> entry : requestHeaderDtoX.getErrorMap().entrySet()){
                    if (StringUtils.equals(entry.getKey(),ErrorCode.FORMAT_ERROR.getValue())){
                        processRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(entry.getValue()))));
                    }else if (StringUtils.equals(entry.getKey(),ErrorCode.INTERNAL_SERVER_ERROR.getValue())) {
                        processRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.INTERNAL_SERVER_ERROR.getValue(), new ArrayList<>(Arrays.asList(entry.getValue()))));
                    }
                }

                return processRequestDto;
            }
        } catch (Exception e) {
        	log.error("Greška kod obrade zahtjeva:", e);
            processRequestDto.setIsValid(false);
            processRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.INTERNAL_SERVER_ERROR.getValue(), new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase()))));
            return processRequestDto;
        }

        return processRequestDto;
    }

    /**
     * Log request
     */
    public String logRequest(ResourceInfo resourceInfo, ContainerRequestContext ctx, HttpServerRequest httpServletRequest, CertificateInfoDto certificateInfoDto) {
        String logMessageString = null;
        try {
            String messageName = Psd2Constants.PSD2_METHODS_REQUEST_LOG.get(resourceInfo.getResourceMethod().getName());
            if (StringUtils.isNotBlank(messageName)) {
                //ima li entity
                String tppMessage = null;
                if (ctx.hasEntity()) {
                    tppMessage = IOUtils.toString(ctx.getEntityStream());
                    String tppEntity = tppMessage;
                    InputStream in = IOUtils.toInputStream(tppEntity);
                    ctx.setEntityStream(in);
                    //ako nema onda uzmemo parametre
                } else {
                    MultivaluedMap<String, String> parametarMap = ctx.getUriInfo().getPathParameters();
                    if (parametarMap != null) {
                        for (String key : parametarMap.keySet()) {
                            tppMessage = key + ":" + parametarMap.getFirst(key) + ";";
                        }
                    }
                }
                logMessageString = logMessage(REQUEST,messageName,httpServletRequest, null, StringUtils.isNotBlank(tppMessage) ? tppMessage : null,null,certificateInfoDto,null);
            }
        } catch (Exception e) {
        	log.error("Greška kod loggiranja PSD2 requesta:", e);
        }
        return logMessageString;
    }

    public static String logMessage(MessageType messageType, String messageName, HttpServerRequest request, EntityManager entityManager, String tppMessage, WriterInterceptorContext responseContext, CertificateInfoDto certificateInfoDto, HttpServletResponse httpServletResponse) {
        String logMessageString = null;
        Psd2Xs2aLogDto logDto = null;
        if (messageType.equals(REQUEST)) {
            logDto = formRequestLogDto(messageName, request, null, tppMessage, certificateInfoDto);
            //logMessageString = addRequestMessageInfoToFileLog(logDto, request);
        } else {
            //logDto = formResponseFilterLogDto2(messageName, request, null, httpServletResponse, responseContext);
            //logMessageString = addResponseMessageInfoToFileLog2(logDto, responseContext);
        }

        return  logDto.toString();
    }

    private static Psd2Xs2aLogDto formRequestLogDto(String messageName, HttpServerRequest request, String interfaceEnvironment, String tppMessage, CertificateInfoDto certificateInfoDto) {
        Psd2Xs2aLogDto logDto = new Psd2Xs2aLogDto();
        Calendar time = GregorianCalendar.getInstance();
        logDto.setDateInString(getDateAsString(time.getTime(), LOG_DATE_TIME_FORMAT));
        logDto.setTime(time.getTime());
        logDto.setMessageType(REQUEST.toString());
        logDto.setMessageName(messageName);
        logDto.setInterfaceName(interfaceEnvironment);
        //logDto.setSessionId(request.sslSession().get);
        logDto.setApiCall( messageName+ " - " + request.path());
        logDto.setxRequestId(request.getHeader(X_REQUEST_ID.getValue()));
        if (certificateInfoDto != null) {
            if (certificateInfoDto.getValidFrom() != null) {
                logDto.setCertIssueDate(Psd2Util.getStringFromLocalDatetime(certificateInfoDto.getValidFrom(), DEFAULT_DATE_TIME_FORMAT));
            }
            if (certificateInfoDto.getValidTo() != null) {
                logDto.setCertValidToDate(Psd2Util.getStringFromLocalDatetime(certificateInfoDto.getValidTo(), DEFAULT_DATE_TIME_FORMAT));
            }
            if (StringUtils.isNotBlank(certificateInfoDto.getOrganizationName())) {
                logDto.setTppName(certificateInfoDto.getOrganizationName());
            }
            if (StringUtils.isNotBlank(certificateInfoDto.getOrganizationID())) {
                logDto.setTppId(certificateInfoDto.getOrganizationID());
            }
            if (certificateInfoDto.getTppRoles() != null && certificateInfoDto.getTppRoles().size() > 0) {
                logDto.setTppRole(StringFunctions.getDelimitedTextFromList(certificateInfoDto.getTppRoles(), ","));
            }
            if (StringUtils.isNotBlank(certificateInfoDto.getCompetentAuthorityName())) {
                logDto.setCompetentAuthorityName(certificateInfoDto.getCompetentAuthorityName());
            }
            if (StringUtils.isNotBlank(certificateInfoDto.getCompetentAuthorityID())) {
                logDto.setCompetentAuthorityId(certificateInfoDto.getCompetentAuthorityID());
            }
        } else {
            logDto.setTppName(request.getHeader(TPP_NAME.getValue()));
            logDto.setTppRole(request.getHeader(TPP_ROLE.getValue()));
        }
        logDto.setRemoteHost(request.remoteAddress().host());
        logDto.setTppAddress(request.getHeader(X_FORWARDED_FOR.getValue()));
        logDto.setPsuId(request.getHeader(PSU_ID.getValue()));
        logDto.setPsuAddress(request.getHeader(PSU_IP_ADDRESS.getValue()));
        logDto.setTppRedirectUri(request.getHeader(TPP_REDIRECT_URI.getValue()));
        logDto.setTppMessage(tppMessage);
        return logDto;
    }



    public static String getDateAsString(Date date, String format) {
        if (date != null) {
            SimpleDateFormat f = new SimpleDateFormat(format);
            return f.format(date);
        }
        return null;
    }

    private Boolean isRoleValid(String requestRoles, String psd2Method) {
        Boolean isValid = false;
        if (StringUtils.isNotBlank(psd2Method) && StringUtils.isNotBlank(requestRoles)) {
            String methodRole = Psd2Constants.PSD2_METHODS_ROLE_MAP.get(psd2Method);
            List<String> listaRola = StringFunctions.getListFromDelimitedText(requestRoles, ",");
            if (listaRola.contains(methodRole)) {
                isValid = true;
            }
        }
        return isValid;
    }

    /**
     * Validate x-request-id-a.
     *
     * @param
     * @return
     * @author tkorpar
     */
    public RequestHeaderDto validateXRequestId(HttpServerRequest httpServletRequest) {
        RequestHeaderDto requestHeaderDto = new RequestHeaderDto();
        requestHeaderDto.setIsValid(Boolean.TRUE);
        Boolean validateXRequestId = Boolean.FALSE;
        try {
            //dohvat parametra
            //String validateXRequestIdParam = LookupHelper.getICoreDaoProxyRemote().getVrijednostParametraBySifra(Psd2Constants.PARAMETAR_PSD2_VALIDATE_X_REQUEST_ID); // stari
            String validateXRequestIdParam = appConfig.xReqIdUse();//getVrijednostParametraBySifra(Psd2Constants.PARAMETAR_PSD2_VALIDATE_X_REQUEST_ID); // prazni
            if (StringUtils.equals(validateXRequestIdParam, Psd2Constants.PSD2_X_REQUEST_ID_VALIDATE)) {
                validateXRequestId = Boolean.TRUE;
            }
            if (validateXRequestId) {
                String xRequestId = httpServletRequest.getHeader(X_REQUEST_ID.getValue());
                //validate format/version
                if (!Psd2Util.validateUUID(xRequestId)) {
                    requestHeaderDto.setIsValid(Boolean.FALSE);
                    //requestHeaderDto.getErrorList().add("Krivi format ili UUID verzija X-Request-ID-a");
                    requestHeaderDto.getErrorMap().put(ErrorCode.FORMAT_ERROR.getValue(),"Krivi format ili UUID verzija X-Request-ID-a");
                    return requestHeaderDto;
                }
                //insert xRequestId into DB
                try {
                    psd2UtilDao.insertXRequestId(xRequestId);
                } catch (PersistenceException jpe) {
                    if (((PersistenceException) jpe).getCause() instanceof ConstraintViolationException) {
                        requestHeaderDto.setIsValid(Boolean.FALSE);
                        //requestHeaderDto.getErrorList().add("X-Request-ID nije unique. Već je poslan request s tim X-Request-ID-om");
                        requestHeaderDto.getErrorMap().put(ErrorCode.FORMAT_ERROR.getValue(),"X-Request-ID nije unique. Već je poslan request s tim X-Request-ID-om");
                    } else {
                        requestHeaderDto.setIsValid(Boolean.FALSE);
                        //requestHeaderDto.getErrorList().add("Greška kod upisa X-Request-ID-a u bazu");
                        requestHeaderDto.getErrorMap().put(ErrorCode.INTERNAL_SERVER_ERROR.getValue(),"Greška kod upisa X-Request-ID-a u bazu");
                    }
                }
            }
        } catch (Exception e) {
            requestHeaderDto.setIsValid(Boolean.FALSE);
            // requestHeaderDto.getErrorList().add("Greška kod provjere X-Request-ID-a");
            requestHeaderDto.getErrorMap().put(ErrorCode.INTERNAL_SERVER_ERROR.getValue(),"Greška kod provjere X-Request-ID-a");
        }
        return requestHeaderDto;
    }

    private RequestHeaderDto validateMandatoryHeaderAttributes(ResourceInfo resourceInfo, HttpServerRequest httpServletRequest) {
        RequestHeaderDto requestHeaderDto = null;
        requestHeaderDto = Psd2Util.validateMandatoryAndGetAllHeaderAttributes(httpServletRequest, Psd2Constants.PSD2_METHODS_MANDATORY_PARAM_MAP.get(resourceInfo.getResourceMethod().getName()));
        return requestHeaderDto;
    }

    public void setResponseHeaders(ResourceInfo resourceInfo, ContainerResponseContext responseContext, HttpServerRequest request) {

        List<String> responseAttributeList = Psd2Constants.PSD2_METHODS_MANDATORY_RESPONE_PARAM_MAP.get(resourceInfo.getResourceMethod().getName());
        for (String attribute : responseAttributeList) {
            if (StringUtils.equals(attribute, Psd2Constants.ResponsetHeaderAttributes.X_REQUEST_ID.getValue())) {
                responseContext.getHeaders().add(attribute, request.getHeader(Psd2Constants.ResponsetHeaderAttributes.X_REQUEST_ID.getValue()));
            }
            if (200 == responseContext.getStatus()) {
                if (StringUtils.equals(attribute, Psd2Constants.ResponsetHeaderAttributes.LOCATION.getValue())) {
                    responseContext.getHeaders().add(attribute, request.uri());
                }
            }
        }
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, dd MMM yyyy HH:mm:ss z");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss O", Locale.ENGLISH);
        //responseContext.getHeaders().add(Psd2Constants.ResponsetHeaderAttributes.DATE.getValue(), ZonedDateTime.now().format(formatter));
        responseContext.getHeaders().add(Psd2Constants.ResponsetHeaderAttributes.DATE.getValue(), formatter.format(ZonedDateTime.now(ZoneOffset.UTC)));
    }

    public void signResposne(ContainerResponseContext responseContext) {
        InputStream keyStore = null;
        CertificateSignInfoDto certificateSignInfoDto = null;
        //key store file
        if (StringUtils.equals(appConfig.signResponse(), Psd2Constants.PSD2_SIGN_RESPONSE)) {
            try {
                keyStore = new FileInputStream(SignatureContants.PSD2_QSEAL_KEYSTORE_FILE);
            } catch (FileNotFoundException fnfe) {
                log.error("Error: " + fnfe);
                responseContext.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
                ErrorInformationDto errorInformationDto = Psd2Util.setErrorInformationDto(ErrorCode.INTERNAL_SERVER_ERROR.getValue(), new ArrayList<>(Arrays.asList("Greška kod potpisa response-a.")));
                responseContext.setEntity(errorInformationDto);
                fnfe.printStackTrace();
                return;
            }
            //get cefrtificate / private key
            try {
                certificateSignInfoDto = CertificateUtil.getCertificateSignInfoFromKeyStore(keyStore, SignatureContants.PSD2_QSEAL_KEYSTORE_PASS, SignatureContants.PSD2_QSEAL_CERT_ALIAS);
            } catch (Exception ex) {
                log.error("Error: " + ex);
                responseContext.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
                ErrorInformationDto errorInformationDto = Psd2Util.setErrorInformationDto(ErrorCode.INTERNAL_SERVER_ERROR.getValue(), new ArrayList<>(Arrays.asList("Greška kod potpisa response-a.")));
                responseContext.setEntity(errorInformationDto);
                return;
            }
            //sign
            try {
                psd2CertificateSignatureBean.signResponse(responseContext, certificateSignInfoDto);
            } catch (Exception e) {
                log.error("Error: " + e);
                responseContext.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
                ErrorInformationDto errorInformationDto = Psd2Util.setErrorInformationDto(ErrorCode.INTERNAL_SERVER_ERROR.getValue(), new ArrayList<>(Arrays.asList("Greška kod potpisa response-a.")));
                responseContext.setEntity(errorInformationDto);
            }
        }
    }
}
