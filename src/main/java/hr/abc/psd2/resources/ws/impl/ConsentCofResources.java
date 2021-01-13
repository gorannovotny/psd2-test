package hr.abc.psd2.resources.ws.impl;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.PSU_ID;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.TPP_ID;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.TPP_NAME;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.TPP_REDIRECT_URI;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.X_REQUEST_ID;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import hr.abc.psd2.bean.IAuthorizationBean;
import hr.abc.psd2.bean.IConsentCofBean;
import hr.abc.psd2.model.dto.ConsentCofRequestResponseDto;
import hr.abc.psd2.model.dto.authorization.AuthorizationListResponseDto;
import hr.abc.psd2.model.dto.authorization.CreateAuthorizationRequestDto;
import hr.abc.psd2.model.dto.authorization.ScaStatusResponseDto;
import hr.abc.psd2.model.dto.authorization.StartAuthorizationResponseDto;
import hr.abc.psd2.model.dto.error.ErrorCode;
import hr.abc.psd2.resources.ws.IConsentCofResources;
import hr.abc.psd2.util.GenericBassxConstants;
import hr.abc.psd2.util.InternationalizationUtil;
import hr.abc.psd2.util.Psd2Constants;
import hr.abc.psd2.util.Psd2Util;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConsentCofResources implements IConsentCofResources {

    @Inject
    private IConsentCofBean consentCofBean;

    @Inject
    private IAuthorizationBean authorizationBean;


    @Override
    public Response createConsentConfirmationOfFunds(ConsentCofRequestResponseDto consentCofRequestResponseDto, HttpHeaders headers) {
        Response response = null;
        ConsentCofRequestResponseDto consentCofRequestResponseDtoResult = new ConsentCofRequestResponseDto();
        consentCofRequestResponseDtoResult.setValid(true);
        try {
            MultivaluedMap<String, String> requestHeaders = headers.getRequestHeaders();
            //X-Request-ID
            consentCofRequestResponseDto.setxRequestID(requestHeaders.getFirst(X_REQUEST_ID.getValue()));
            //TPP-ID
            consentCofRequestResponseDto.setTppID(requestHeaders.getFirst(TPP_ID.getValue()));
            //TPP-Name
            consentCofRequestResponseDto.setTppName(requestHeaders.getFirst(TPP_NAME.getValue()));
            //TPP-Redirect URI
            consentCofRequestResponseDto.setTppRedirectUri(requestHeaders.getFirst(TPP_REDIRECT_URI.getValue()));
            //PSU-ID
            if (StringUtils.isNotBlank(requestHeaders.getFirst(PSU_ID.getValue()))) {
                consentCofRequestResponseDto.setPsuID(requestHeaders.getFirst(PSU_ID.getValue()));
            }

            //validate Request Body
            consentCofRequestResponseDto = Psd2Util.validateCofConsentRequestBody(consentCofRequestResponseDto);
            if (consentCofRequestResponseDto.getValid()) {
                // consentCofRequestResponseDtoResult = consentCofBean.createConsentCof(consentCofRequestResponseDto); // stari
                consentCofRequestResponseDtoResult = consentCofBean.createConsentCof(consentCofRequestResponseDto); // prazni
                if (consentCofRequestResponseDtoResult.getValid()) {
                    consentCofRequestResponseDtoResult.setValid(null);
                    response = Response.created(new URI(consentCofRequestResponseDtoResult.get_links().getSelf().getHref())).header("ASPSP-SCA-Approach", "REDIRECT").entity(consentCofRequestResponseDtoResult).build();
                } else {
                    if (consentCofRequestResponseDtoResult.getErrorInformationDto().getTppMessages() != null && !consentCofRequestResponseDtoResult.getErrorInformationDto().getTppMessages().isEmpty() &&
                            consentCofRequestResponseDtoResult.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.FORMAT_ERROR.getValue())) {
                        response = Response.status(Response.Status.BAD_REQUEST).entity(consentCofRequestResponseDtoResult.getErrorInformationDto()).build();
                    }

                }
            } else {
                response = Response.status(Response.Status.BAD_REQUEST).entity(consentCofRequestResponseDto.getErrorInformationDto()).build();
            }

        } catch (Exception e) {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();
            e.printStackTrace();
            //TODO dodati loger
        }
        return response;
    }

    @Override
    public Response getConsentConfirmationOfFundsStatus(String consentId, HttpHeaders headers) {
        Response response = null;
        //response object for body
        ConsentCofRequestResponseDto consentCofRequestResponseDtoTemp = new ConsentCofRequestResponseDto();

        consentCofRequestResponseDtoTemp.setValid(true);
        try {
            MultivaluedMap<String, String> requestHeaders = headers.getRequestHeaders();
            String tppId = requestHeaders.getFirst(TPP_ID.getValue());
            if (StringUtils.isNotBlank(consentId)) {
                // consentCofRequestResponseDtoTemp = consentCofBean.getConsentInformation(consentId, tppId); // stari
                consentCofRequestResponseDtoTemp = consentCofBean.getConsentInformation(consentId, tppId); // prazni
                if (consentCofRequestResponseDtoTemp.getValid()) {
                    if (consentCofRequestResponseDtoTemp != null) {
                        ConsentCofRequestResponseDto consentCofRequestResponseDtoResoult = new ConsentCofRequestResponseDto();
                        consentCofRequestResponseDtoResoult.setValid(null);
                        consentCofRequestResponseDtoResoult.setConsentStatus(consentCofRequestResponseDtoTemp.getConsentStatus());
                        response = Response.status(Response.Status.OK).entity(consentCofRequestResponseDtoResoult).build();
                    } else {
                        response = Response.status(Response.Status.OK).build();
                    }
                } else {
                    if (consentCofRequestResponseDtoTemp.getErrorInformationDto().getTppMessages() != null && !consentCofRequestResponseDtoTemp.getErrorInformationDto().getTppMessages().isEmpty() &&
                            consentCofRequestResponseDtoTemp.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.FORMAT_ERROR.getValue())) {
                        response = Response.status(Response.Status.BAD_REQUEST).entity(consentCofRequestResponseDtoTemp.getErrorInformationDto()).build();
                    } else if (consentCofRequestResponseDtoTemp.getErrorInformationDto().getTppMessages() != null && !consentCofRequestResponseDtoTemp.getErrorInformationDto().getTppMessages().isEmpty() &&
                            consentCofRequestResponseDtoTemp.getErrorInformationDto().getTppMessages().get(0).getCode().equals("RESOURCE_UNKNOWN")) {
                        response = Response.status(Response.Status.NOT_FOUND).entity(consentCofRequestResponseDtoTemp.getErrorInformationDto()).build();
                    } else if (consentCofRequestResponseDtoTemp.getErrorInformationDto().getTppMessages() != null && !consentCofRequestResponseDtoTemp.getErrorInformationDto().getTppMessages().isEmpty() &&
                            consentCofRequestResponseDtoTemp.getErrorInformationDto().getTppMessages().get(0).getCode().equals("CONSENT_EXPIRED")) {
                        response = Response.status(Response.Status.UNAUTHORIZED).entity(consentCofRequestResponseDtoTemp.getErrorInformationDto()).build();
                    } else {
                        response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(consentCofRequestResponseDtoTemp.getErrorInformationDto()).build();
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

    @Override
    public Response getConsentConfirmationOfFunds(String consentId,HttpHeaders headers) {
        Response response = null;
        //response object for body
        ConsentCofRequestResponseDto consentCofRequestResponseDto = new ConsentCofRequestResponseDto();
        consentCofRequestResponseDto.setValid(true);
        try {
            MultivaluedMap<String, String> requestHeaders = headers.getRequestHeaders();
            String tppId = requestHeaders.getFirst(TPP_ID.getValue());
            if (StringUtils.isNotBlank(consentId)) {
                // consentCofRequestResponseDto = consentCofBean.getConsentInformation(consentId, tppId); // stari
                consentCofRequestResponseDto = consentCofBean.getConsentInformation(consentId, tppId); // prazni
                if (consentCofRequestResponseDto.getValid()) {
                    consentCofRequestResponseDto.setValid(null);
                    response = Response.status(Response.Status.OK).entity(consentCofRequestResponseDto).build();
                } else {
                    if (consentCofRequestResponseDto.getErrorInformationDto().getTppMessages() != null && !consentCofRequestResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            consentCofRequestResponseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.FORMAT_ERROR.getValue())) {
                        response = Response.status(Response.Status.BAD_REQUEST).entity(consentCofRequestResponseDto.getErrorInformationDto()).build();
                    } else if (consentCofRequestResponseDto.getErrorInformationDto().getTppMessages() != null && !consentCofRequestResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            consentCofRequestResponseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.RESOURCE_UNKNOWN.getValue())) {
                        response = Response.status(Response.Status.NOT_FOUND).entity(consentCofRequestResponseDto.getErrorInformationDto()).build();
                    } else {
                        response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(consentCofRequestResponseDto.getErrorInformationDto()).build();
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

    @Override
    public Response deleteConsentConfirmationOfFunds(String consentId, HttpHeaders headers) {
        Response response = null;
        ConsentCofRequestResponseDto consentCofRequestResponseDto = new ConsentCofRequestResponseDto();
        consentCofRequestResponseDto.setValid(true);
        try {
            MultivaluedMap<String, String> requestHeaders = headers.getRequestHeaders();
            String tppId = requestHeaders.getFirst(TPP_ID.getValue());
            if (StringUtils.isNotBlank(consentId)) {
                // consentCofRequestResponseDto = consentCofBean.deleteConsent(consentId, tppId); // stari
                consentCofRequestResponseDto = consentCofBean.deleteConsent(consentId, tppId); // prazni
                if (consentCofRequestResponseDto.getValid()) {
                    response = Response.status(Response.Status.NO_CONTENT).build();
                } else {
                    if (consentCofRequestResponseDto.getErrorInformationDto().getTppMessages() != null && !consentCofRequestResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            consentCofRequestResponseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.FORMAT_ERROR.getValue())) {
                        response = Response.status(Response.Status.BAD_REQUEST).entity(consentCofRequestResponseDto.getErrorInformationDto()).build();
                    } else if (consentCofRequestResponseDto.getErrorInformationDto().getTppMessages() != null && !consentCofRequestResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            consentCofRequestResponseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.RESOURCE_UNKNOWN.getValue())) {
                        response = Response.status(Response.Status.NOT_FOUND).entity(consentCofRequestResponseDto.getErrorInformationDto()).build();
                    } else if (consentCofRequestResponseDto.getErrorInformationDto().getTppMessages() != null && !consentCofRequestResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            consentCofRequestResponseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.RESOURCE_EXPIRED.getValue())) {
                        response = Response.status(Response.Status.FORBIDDEN).entity(consentCofRequestResponseDto.getErrorInformationDto()).build();
                    } else {
                        response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(consentCofRequestResponseDto.getErrorInformationDto()).build();
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

    @Override
    public Response startConsentCofAuthorisation(String consentId, HttpHeaders headers) {
        Response response = null;
        String xRequestId = null;
        try {
            MultivaluedMap<String, String> requestHeaders = headers.getRequestHeaders();
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
                // StartAuthorizationResponseDto startAuthorizationResponseDto = authorizationBean.startAuthorisation(new CreateAuthorizationRequestDto(null, null, Psd2Constants.AuthorizationObjectType.CONSENT_COF_AUTHORUZATION.getValue(), consentIdInt, Psd2Constants.AuthorizationType.CREATED.getValue(), xRequestId, tppId, null)); // stari
                StartAuthorizationResponseDto startAuthorizationResponseDto = authorizationBean.startAuthorisation(new CreateAuthorizationRequestDto(null, null, Psd2Constants.AuthorizationObjectType.CONSENT_COF_AUTHORUZATION.getValue(), consentIdInt.toString(), Psd2Constants.AuthorizationType.CREATED.getValue(), xRequestId, tppId, null)); // prazni
                if (startAuthorizationResponseDto.getValid()) {
                    startAuthorizationResponseDto.setValid(null);
                    String location = startAuthorizationResponseDto.get_links().getSelf().getHref();
                    startAuthorizationResponseDto.get_links().setSelf(null);
                    response = Response.created(new URI(location)).entity(startAuthorizationResponseDto).build();
                    return response;
                } else {
                    if (startAuthorizationResponseDto.getErrorInformationDto().getTppMessages() != null && !startAuthorizationResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            startAuthorizationResponseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.STATUS_INVALID.getValue())) {
                        response = Response.status(Response.Status.CONFLICT).entity(startAuthorizationResponseDto.getErrorInformationDto()).build();
                        return response;
                    } else if (startAuthorizationResponseDto.getErrorInformationDto().getTppMessages() != null && !startAuthorizationResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            startAuthorizationResponseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.RESOURCE_UNKNOWN.getValue())) {
                        response = Response.status(Response.Status.FORBIDDEN).entity(startAuthorizationResponseDto.getErrorInformationDto()).build();
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

    @Override
    public Response getConsentCofAuthorisation(String consentId, HttpHeaders headers) {
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
                // AuthorizationListResponseDto authorizationListResponseBackWsDto = authorizationBean.getAuthorizationSubResources(consentIdInt, Psd2Constants.AuthorizationObjectType.CONSENT_COF_AUTHORUZATION.getValue(), null, null, tppId, Psd2Constants.AuthorizationType.CREATED.getValue()); // stari
                AuthorizationListResponseDto authorizationListResponseBackWsDto = authorizationBean.getAuthorizationSubResources(consentIdInt.toString(), Psd2Constants.AuthorizationObjectType.CONSENT_COF_AUTHORUZATION.getValue(), null, null, tppId, Psd2Constants.AuthorizationType.CREATED.getValue()); // prazni
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

    @Override
    public Response getConsentCofScaStatus(String consentId, String authorisationId, HttpHeaders headers) {
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
                // ScaStatusResponseDto scaStatusResponseBackWsDto = authorizationBean.getScaStatus(consentIdInt, Psd2Constants.AuthorizationObjectType.CONSENT_COF_AUTHORUZATION.getValue(), authIdInt, null, null, tppId, Psd2Constants.AuthorizationType.CREATED.getValue()); // stari
                ScaStatusResponseDto scaStatusResponseBackWsDto = authorizationBean.getScaStatus(consentIdInt.toString(), Psd2Constants.AuthorizationObjectType.CONSENT_COF_AUTHORUZATION.getValue(), authIdInt.toString(), null, null, tppId, Psd2Constants.AuthorizationType.CREATED.getValue()); // prazni
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
}
