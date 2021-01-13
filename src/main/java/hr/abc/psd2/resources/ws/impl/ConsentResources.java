package hr.abc.psd2.resources.ws.impl;

import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.PSU_ID;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.TPP_ID;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.TPP_NAME;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.TPP_REDIRECT_URI;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.X_REQUEST_ID;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import hr.abc.psd2.bean.IAuthorizationBean;
import hr.abc.psd2.bean.IConsentAisBean;
import hr.abc.psd2.config.AppConfig;
import hr.abc.psd2.model.dto.ConsentRequestResponseDto;
import hr.abc.psd2.model.dto.ais.ConsentResponseDto;
import hr.abc.psd2.model.dto.authorization.AuthorizationListResponseDto;
import hr.abc.psd2.model.dto.authorization.CreateAuthorizationRequestDto;
import hr.abc.psd2.model.dto.authorization.ScaStatusResponseDto;
import hr.abc.psd2.model.dto.authorization.StartAuthorizationResponseDto;
import hr.abc.psd2.model.dto.error.ErrorCode;
import hr.abc.psd2.resources.ws.IConsentResources;
import hr.abc.psd2.util.GenericBassxConstants;
import hr.abc.psd2.util.InternationalizationUtil;
import hr.abc.psd2.util.Psd2Constants;
import hr.abc.psd2.util.Psd2Util;
import io.vertx.axle.core.http.HttpServerRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * Web service resource for Account Information Consent .
 * <p>
 * Created by tkorpar on 19.09.2018..
 */

@Slf4j
public class ConsentResources implements IConsentResources {

  
  
    @Inject
    private IConsentAisBean consentAisBean;
    
    @Inject
    private IAuthorizationBean authorizationBean;

    @Inject
    AppConfig appConfig;

    /**
     * summary: Create consent
     * description:
     * This method create a consent resource, defining access rights to dedicated accounts of
     * a given PSU-ID. These accounts are addressed explicitly in the method as
     * parameters as a core function.
     */
    public Response createConsent(ConsentRequestResponseDto consentRequestResponseDto, ContainerRequestContext ctx, HttpServerRequest request, HttpHeaders headers) {
        Response response = null;
        //response object for body
        ConsentRequestResponseDto consentRequestResponseDtoResult = new ConsentRequestResponseDto();
        consentRequestResponseDtoResult.setIsValid(true);
        try {
            MultivaluedMap<String, String> requestHeaders = headers.getRequestHeaders();
            //X-Request-ID
            consentRequestResponseDto.setxRequestID(requestHeaders.getFirst(X_REQUEST_ID.getValue()));
            //TPP-ID
            consentRequestResponseDto.setTppID(requestHeaders.getFirst(TPP_ID.getValue()));
            //TPP-Name
            consentRequestResponseDto.setTppName(requestHeaders.getFirst(TPP_NAME.getValue()));
            //TPP-Redirect URI
            consentRequestResponseDto.setTppRedirectUri(requestHeaders.getFirst(TPP_REDIRECT_URI.getValue()));
            //PSU-ID
            if (StringUtils.isNotBlank(requestHeaders.getFirst(PSU_ID.getValue()))) {
                consentRequestResponseDto.setPsuID(requestHeaders.getFirst(PSU_ID.getValue()));
            }
            //validate request body
            consentRequestResponseDto = Psd2Util.validateCreateConsentRequestBody(consentRequestResponseDto);
            //OK
            if (consentRequestResponseDto.getIsValid()) {
                //ConsentRequestResponseDto consentRequestResponseDtoWSResult = consentAisBean.createConsentAis(consentRequestResponseDto); // stari
                ConsentRequestResponseDto consentRequestResponseDtoWSResult = consentAisBean.createConsentAis(consentRequestResponseDto); // prazni
                if (consentRequestResponseDtoWSResult.getIsValid()) {
                    consentRequestResponseDtoResult.setConsentStatus(consentRequestResponseDtoWSResult.getConsentStatus());
                    consentRequestResponseDtoResult.setConsentId(consentRequestResponseDtoWSResult.getConsentId());
                    consentRequestResponseDtoResult.set_links(consentRequestResponseDtoWSResult.get_links());
                    consentRequestResponseDtoResult.setIsValid(null);
                    response = Response.created(new URI(consentRequestResponseDtoResult.get_links().getSelf().getHref())).header("ASPSP-SCA-Approach", "REDIRECT").entity(consentRequestResponseDtoResult).build();
                } else {
                    if (consentRequestResponseDtoWSResult.getErrorInformationDto().getTppMessages() != null && !consentRequestResponseDtoWSResult.getErrorInformationDto().getTppMessages().isEmpty() &&
                            consentRequestResponseDtoWSResult.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.FORMAT_ERROR.getValue())) {
                        response = Response.status(Response.Status.BAD_REQUEST).entity(consentRequestResponseDtoWSResult.getErrorInformationDto()).build();
                    } else if (consentRequestResponseDtoWSResult.getErrorInformationDto().getTppMessages() != null && !consentRequestResponseDtoWSResult.getErrorInformationDto().getTppMessages().isEmpty() &&
                            consentRequestResponseDtoWSResult.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.PARAMETER_NOT_SUPPORTED.getValue())) {
                        response = Response.status(Response.Status.BAD_REQUEST).entity(consentRequestResponseDtoWSResult.getErrorInformationDto()).build();
                    } else if (consentRequestResponseDtoWSResult.getErrorInformationDto().getTppMessages() != null && !consentRequestResponseDtoWSResult.getErrorInformationDto().getTppMessages().isEmpty() &&
                            consentRequestResponseDtoWSResult.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.SERVICE_INVALID.getValue())) {
                        response = Response.status(Response.Status.BAD_REQUEST).entity(consentRequestResponseDtoWSResult.getErrorInformationDto()).build();
                    } else {
                        response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(consentRequestResponseDtoWSResult.getErrorInformationDto()).build();
                    }
                }
            } else {
                if (consentRequestResponseDto.getErrorInformationDto().getTppMessages() != null && !consentRequestResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                        consentRequestResponseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.FORMAT_ERROR.getValue())) {
                    response = Response.status(Response.Status.BAD_REQUEST).entity(consentRequestResponseDto.getErrorInformationDto()).build();
                } else if (consentRequestResponseDto.getErrorInformationDto().getTppMessages() != null && !consentRequestResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                        consentRequestResponseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.PARAMETER_NOT_SUPPORTED.getValue())) {
                    response = Response.status(Response.Status.BAD_REQUEST).entity(consentRequestResponseDto.getErrorInformationDto()).build();
                }

            }
        } catch (Exception e) {
            log.error("Exception is:", e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();
        }
        return response;
    }

    /**
     * summary: Consent status request
     * description:
     * Read the status of an account information consent resource.
     */
    public Response getConsentStatus(@PathParam("consentId") String consentId, ContainerRequestContext ctx,  HttpHeaders headers) {
        Response response = null;
        //response object for body
        ConsentRequestResponseDto consentRequestResponseDtoResult = new ConsentRequestResponseDto();
        consentRequestResponseDtoResult.setIsValid(true);
        List<String> errorList = new ArrayList<>();
        try {
            MultivaluedMap<String, String> requestHeaders = headers.getRequestHeaders();
            String tppId = requestHeaders.getFirst(TPP_ID.getValue());
            //path parameter
            if (StringUtils.isNotBlank(consentId)) {
                // ConsentRequestResponseDto consentRequestResponseDtoWSResult = consentAisBean.getConsentStatus(consentId, tppId); // stari
                ConsentRequestResponseDto consentRequestResponseDtoWSResult = consentAisBean.getConsentStatus(consentId, tppId); // prazni
                if (consentRequestResponseDtoWSResult.getIsValid()) {
                    if (consentRequestResponseDtoWSResult.getConsentStatus() != null) {
                        consentRequestResponseDtoResult.setIsValid(null);
                        consentRequestResponseDtoResult.setConsentStatus(consentRequestResponseDtoWSResult.getConsentStatus());
                        response = Response.status(Response.Status.OK).entity(consentRequestResponseDtoResult).build();
                    } else {
                        response = Response.status(Response.Status.OK).build();
                    }
                } else {
                    if (consentRequestResponseDtoWSResult.getErrorInformationDto().getTppMessages() != null && !consentRequestResponseDtoWSResult.getErrorInformationDto().getTppMessages().isEmpty() &&
                            consentRequestResponseDtoWSResult.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.FORMAT_ERROR.getValue())) {
                        response = Response.status(Response.Status.BAD_REQUEST).entity(consentRequestResponseDtoWSResult.getErrorInformationDto()).build();
                    } else if (consentRequestResponseDtoWSResult.getErrorInformationDto().getTppMessages() != null && !consentRequestResponseDtoWSResult.getErrorInformationDto().getTppMessages().isEmpty() &&
                            consentRequestResponseDtoWSResult.getErrorInformationDto().getTppMessages().get(0).getCode().equals("RESOURCE_UNKNOWN")) {
                        response = Response.status(Response.Status.NOT_FOUND).entity(consentRequestResponseDtoWSResult.getErrorInformationDto()).build();
                    } else if (consentRequestResponseDtoWSResult.getErrorInformationDto().getTppMessages() != null && !consentRequestResponseDtoWSResult.getErrorInformationDto().getTppMessages().isEmpty() &&
                            consentRequestResponseDtoWSResult.getErrorInformationDto().getTppMessages().get(0).getCode().equals("CONSENT_EXPIRED")) {
                        response = Response.status(Response.Status.UNAUTHORIZED).entity(consentRequestResponseDtoWSResult.getErrorInformationDto()).build();
                    } else {
                        response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(consentRequestResponseDtoWSResult.getErrorInformationDto()).build();
                    }
                }
            } else {
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_consent_id", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
            }
        } catch (Exception e) {
            log.error("Exception is:", e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();
        }
        return response;
    }

    /**
     * summary: Get Consent Request
     * description:
     * Returns the content of an account information consent object.
     * This is returning the data for the TPP especially in cases,
     * where the consent was directly managed between ASPSP and PSU e.g. in a re-direct SCA Approach.
     */
    public Response getConsentInformation(@PathParam("consentId") String consentId, ContainerRequestContext ctx, HttpHeaders headers) {
        Response response = null;
        //response object for body
        ConsentResponseDto consentRequestResponseDtoResult = new ConsentResponseDto();
        consentRequestResponseDtoResult.setIsValid(true);
        List<String> errorList = new ArrayList<>();
        try {
            MultivaluedMap<String, String> requestHeaders = headers.getRequestHeaders();
            String tppId = requestHeaders.getFirst(TPP_ID.getValue());
            //path parameter
            if (StringUtils.isNotBlank(consentId)) {
                // ConsentRequestResponseDto consentRequestResponseDtoWSResult = consentAisBean.getConsentInformation(consentId, tppId); // stari
                ConsentRequestResponseDto consentRequestResponseDtoWSResult = consentAisBean.getConsentInformation(consentId, tppId); // prazni
                if (consentRequestResponseDtoWSResult.getIsValid()) {
                    consentRequestResponseDtoResult.setIsValid(null);
                    if (consentRequestResponseDtoWSResult.getAccess() != null) {
                        consentRequestResponseDtoResult.setConsentStatus(consentRequestResponseDtoWSResult.getConsentStatus());
                        consentRequestResponseDtoResult.setRecurringIndicator(consentRequestResponseDtoWSResult.getRecurringIndicator());
                        consentRequestResponseDtoResult.setFrequencyPerDay(consentRequestResponseDtoWSResult.getFrequencyPerDay().toString());
                        consentRequestResponseDtoResult.setCombinedServiceIndicator(consentRequestResponseDtoWSResult.getCombinedServiceIndicator());
                        consentRequestResponseDtoResult.setValidUntil(consentRequestResponseDtoWSResult.getValidUntil());
                        consentRequestResponseDtoResult.setAccess(consentRequestResponseDtoWSResult.getAccess());
                        consentRequestResponseDtoResult.setLastActionDate(consentRequestResponseDtoWSResult.getLastActionDate());
                        response = Response.status(Response.Status.OK).entity(consentRequestResponseDtoResult).build();
                    } else {
                        response = Response.status(Response.Status.OK).build();
                    }
                } else {
                    if (consentRequestResponseDtoWSResult.getErrorInformationDto().getTppMessages() != null && !consentRequestResponseDtoWSResult.getErrorInformationDto().getTppMessages().isEmpty() &&
                            consentRequestResponseDtoWSResult.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.FORMAT_ERROR.getValue())) {
                        response = Response.status(Response.Status.BAD_REQUEST).entity(consentRequestResponseDtoWSResult.getErrorInformationDto()).build();
                    } else if (consentRequestResponseDtoWSResult.getErrorInformationDto().getTppMessages() != null && !consentRequestResponseDtoWSResult.getErrorInformationDto().getTppMessages().isEmpty() &&
                            consentRequestResponseDtoWSResult.getErrorInformationDto().getTppMessages().get(0).getCode().equals("RESOURCE_UNKNOWN")) {
                        response = Response.status(Response.Status.NOT_FOUND).entity(consentRequestResponseDtoWSResult.getErrorInformationDto()).build();
                    } else {
                        response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(consentRequestResponseDtoWSResult.getErrorInformationDto()).build();
                    }
                }
            } else {
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_consent_id", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
            }
        } catch (Exception e) {
            log.error("Exception is:", e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();
        }
        return response;
    }


    /**
     * summary: Delete Consent
     * description:
     * The TPP can delete an account information consent object if needed.
     */
    public Response deleteConsent(@PathParam("consentId") String consentId, ContainerRequestContext ctx, HttpHeaders headers) {
        Response response = null;
        //response object for body
        ConsentResponseDto consentRequestResponseDtoResult = new ConsentResponseDto();
        consentRequestResponseDtoResult.setIsValid(true);
        List<String> errorList = new ArrayList<>();
        try {
            MultivaluedMap<String, String> requestHeaders = headers.getRequestHeaders();
            String tppId = requestHeaders.getFirst(TPP_ID.getValue());
            //path parameter
            if (StringUtils.isNotBlank(consentId)) {
                // ConsentRequestResponseDto consentRequestResponseDtoWSResult = consentAisBean.deleteConsent(consentId, tppId); // stari
                ConsentRequestResponseDto consentRequestResponseDtoWSResult = consentAisBean.deleteConsent(consentId, tppId); // prazni
                if (consentRequestResponseDtoWSResult.getIsValid()) {
                    response = Response.status(Response.Status.NO_CONTENT).build();
                } else {
                    if (consentRequestResponseDtoWSResult.getErrorInformationDto().getTppMessages() != null && !consentRequestResponseDtoWSResult.getErrorInformationDto().getTppMessages().isEmpty() &&
                            consentRequestResponseDtoWSResult.getErrorInformationDto().getTppMessages().get(0).getCode().equals("RESOURCE_UNKNOWN")) {
                        response = Response.status(Response.Status.NOT_FOUND).entity(consentRequestResponseDtoWSResult.getErrorInformationDto()).build();
                    } else {
                        response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(consentRequestResponseDtoWSResult.getErrorInformationDto()).build();
                    }
                }
            } else {
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_consent_id", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
            }
        } catch (Exception e) {
            log.error("Exception is:", e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();
        }
        return response;
    }


    /**
     * summary: Start the authorisation process for a consent
     * description:
     * Create an authorisation sub-resource and start the authorisation process of a consent.
     * The message might in addition transmit authentication and authorisation related data.
     */
    public Response startConsentAuthorisation(@PathParam("consentId") String consentId, ContainerRequestContext ctx) {
        Response response = null;
        String xRequestId = null;
        try {
            MultivaluedMap<String, String> requestHeaders = ctx.getHeaders();
            xRequestId = requestHeaders.getFirst(X_REQUEST_ID.getValue());
            //validate payment id
            if (StringUtils.isBlank(consentId)) {
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_consent_id", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            } else {
                String tppId = requestHeaders.getFirst(TPP_ID.getValue());
                Integer consentIdInt = null;
                try {
                    consentIdInt = Integer.valueOf(consentId);
                } catch (NumberFormatException nfe) {
                    response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto("RESOURCE_UNKNOWN", new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_consent", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                    return response;
                }
                // StartAuthorizationResponseDto startAuthorizationResponseDto = authorizationBean.startAuthorisation(new CreateAuthorizationRequestDto(null, null, Psd2Constants.AuthorizationObjectType.CONSENT_AUTHORIZATION.getValue(), consentIdInt, Psd2Constants.AuthorizationType.CREATED.getValue(), xRequestId, tppId, null)); // stari
                StartAuthorizationResponseDto startAuthorizationResponseDto = authorizationBean.startAuthorisation(new CreateAuthorizationRequestDto(null, null, Psd2Constants.AuthorizationObjectType.CONSENT_AUTHORIZATION.getValue(), consentIdInt.toString(), Psd2Constants.AuthorizationType.CREATED.getValue(), xRequestId, tppId, null));
                if (startAuthorizationResponseDto.getValid()) {
                    startAuthorizationResponseDto.setValid(null);
                    String location = startAuthorizationResponseDto.get_links().getSelf().getHref();
                    startAuthorizationResponseDto.get_links().setSelf(null);
                    response = Response.created(new URI(location)).entity(startAuthorizationResponseDto).build();
                    return response;
                } else {
                    if (startAuthorizationResponseDto.getErrorInformationDto().getTppMessages() != null && !startAuthorizationResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            startAuthorizationResponseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals("BAD_REQUEST")) {
                        response = Response.status(Response.Status.BAD_REQUEST).entity(startAuthorizationResponseDto.getErrorInformationDto()).build();
                        return response;
                    } else if (startAuthorizationResponseDto.getErrorInformationDto().getTppMessages() != null && !startAuthorizationResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            startAuthorizationResponseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals("RESOURCE_UNKNOWN")) {
                        response = Response.status(Response.Status.NOT_FOUND).entity(startAuthorizationResponseDto.getErrorInformationDto()).build();
                        return response;
                    } else if (startAuthorizationResponseDto.getErrorInformationDto().getTppMessages() != null && !startAuthorizationResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            startAuthorizationResponseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.STATUS_INVALID.getValue())) {
                        response = Response.status(Response.Status.CONFLICT).entity(startAuthorizationResponseDto.getErrorInformationDto()).build();
                        return response;
                    } else {
                        response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(startAuthorizationResponseDto.getErrorInformationDto()).build();
                        return response;
                    }
                }
            }
        } catch (Exception e) {
            log.error("Exception is:", e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();
            return response;
        }
    }


    /**
     * summary: Get Consent Authorisation Sub-Resources Request
     * description:
     * Return a list of all authorisation subresources IDs which have been created.
     **/
    public Response getConsentAuthorisation(@PathParam("consentId") String consentId, ContainerRequestContext ctx, HttpHeaders headers) {
        Response response = null;
        //response object for body
        AuthorizationListResponseDto authorizationListResponseDtoResult = new AuthorizationListResponseDto();
        authorizationListResponseDtoResult.setValid(true);
        try {
            MultivaluedMap<String, String> requestHeaders = headers.getRequestHeaders();
            String tppId = requestHeaders.getFirst(TPP_ID.getValue());
            //validate tpp role
            //validate path parameter
            if (StringUtils.isNotBlank(consentId)) {
                String jsonResponse = null;
                Integer consentIdInt = Integer.parseInt(consentId);
                // AuthorizationListResponseDto authorizationListResponseBackWsDto = authorizationBean.getAuthorizationSubResources(consentIdInt, Psd2Constants.AuthorizationObjectType.CONSENT_AUTHORIZATION.getValue(), null, null, tppId, Psd2Constants.AuthorizationType.CREATED.getValue()); // stari
                AuthorizationListResponseDto authorizationListResponseBackWsDto = authorizationBean.getAuthorizationSubResources(consentIdInt.toString(), Psd2Constants.AuthorizationObjectType.CONSENT_AUTHORIZATION.getValue(), null, null, tppId, Psd2Constants.AuthorizationType.CREATED.getValue()); // prazni
                //validate response from back end ws
                if (authorizationListResponseBackWsDto.getValid()) {
                    if (authorizationListResponseBackWsDto.getAuthorisationIds() != null && !authorizationListResponseBackWsDto.getAuthorisationIds().isEmpty()) {
                        authorizationListResponseDtoResult.setValid(null);
                        authorizationListResponseDtoResult.setAuthorisationIds(authorizationListResponseBackWsDto.getAuthorisationIds());
                        response = Response.status(Response.Status.OK).entity(authorizationListResponseDtoResult).build();
                    } else {
                        response = Response.status(Response.Status.NOT_FOUND).entity(authorizationListResponseDtoResult.getErrorInformationDto()).build();
                    }
                } else {
                    if (authorizationListResponseBackWsDto.getErrorInformationDto().getTppMessages() != null && !authorizationListResponseBackWsDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            authorizationListResponseBackWsDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.RESOURCE_UNKNOWN.getValue())) {
                        response = Response.status(Response.Status.NOT_FOUND).entity(authorizationListResponseBackWsDto.getErrorInformationDto()).build();
                    } else {
                        response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(authorizationListResponseBackWsDto.getErrorInformationDto()).build();
                    }
                }
            } else {
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_consent_id", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
            }
        } catch (Exception e) {
            log.error("Exception is:", e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();
        }
        return response;
    }

    /**
     * summary: Read the SCA status of the consent authorisation.
     * description:
     * This method returns the SCA status of a consent initiation's authorisation sub-resource.
     */
    public Response getConsentScaStatus(@PathParam("consentId") String
                                                consentId, @PathParam("authorisationId") String authorisationId, ContainerRequestContext ctx, HttpHeaders headers) {
        Response response = null;
        //response object for body
        ScaStatusResponseDto scaStatusResponseDtoResult = new ScaStatusResponseDto();
        scaStatusResponseDtoResult.setValid(true);
        try {
            MultivaluedMap<String, String> requestHeaders = headers.getRequestHeaders();
            String tppId = requestHeaders.getFirst(TPP_ID.getValue());
            //validate tpp role
            //validate path param
            if (StringUtils.isNotBlank(consentId) && StringUtils.isNotBlank(authorisationId)) {
                String jsonResponse = null;
                Integer consentIdInt = Integer.parseInt(consentId);
                Integer authIdInt = Integer.parseInt(authorisationId);
                // ScaStatusResponseDto scaStatusResponseBackWsDto = authorizationBean.getScaStatus(consentIdInt, Psd2Constants.AuthorizationObjectType.CONSENT_AUTHORIZATION.getValue(), authIdInt,null, null, tppId, Psd2Constants.AuthorizationType.CREATED.getValue()); // stari
                ScaStatusResponseDto scaStatusResponseBackWsDto = authorizationBean.getScaStatus(consentIdInt.toString(), Psd2Constants.AuthorizationObjectType.CONSENT_AUTHORIZATION.getValue(), authIdInt.toString(),null, null, tppId, Psd2Constants.AuthorizationType.CREATED.getValue()); // prazni
                //validate response from backend ws
                if (scaStatusResponseBackWsDto.getValid()) {
                    if (scaStatusResponseBackWsDto.getScaStatus() != null) {
                        scaStatusResponseDtoResult.setValid(null);
                        scaStatusResponseDtoResult.setScaStatus(scaStatusResponseBackWsDto.getScaStatus());
                        response = Response.status(Response.Status.OK).entity(scaStatusResponseDtoResult).build();
                    } else {
                        response = Response.status(Response.Status.NOT_FOUND).entity(scaStatusResponseBackWsDto.getErrorInformationDto()).build();
                    }
                } else {
                    if (scaStatusResponseBackWsDto.getErrorInformationDto().getTppMessages() != null && !scaStatusResponseBackWsDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            scaStatusResponseBackWsDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals("BAD_REQUEST")) {
                        response = Response.status(Response.Status.BAD_REQUEST).entity(scaStatusResponseBackWsDto).build();
                    } else if (scaStatusResponseBackWsDto.getErrorInformationDto().getTppMessages() != null && !scaStatusResponseBackWsDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            scaStatusResponseBackWsDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.RESOURCE_UNKNOWN.getValue())) {
                        response = Response.status(Response.Status.NOT_FOUND).entity(scaStatusResponseBackWsDto.getErrorInformationDto()).build();
                    } else {
                        response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(scaStatusResponseBackWsDto.getErrorInformationDto()).build();
                    }
                }
            } else {
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_consent_id_no_authid", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
            }
        } catch (Exception e) {
            log.error("Exceprion is:", e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();
        }
        return response;
    }

    /*
    *
    summary: Update PSU Data for consents
    description:
         This method update PSU data on the consents  resource if needed.
         It may authorise a consent within the Embedded SCA Approach where needed.
    *
    */
    public void updateConsentsPsuData(@PathParam("consentId") String
                                              consentId, @PathParam("authorisationId") String authorisationId) {
//        try {
//
//        } catch ( ) {
//            e.printStackTrace();
//        }
//        return null;
    }

}
