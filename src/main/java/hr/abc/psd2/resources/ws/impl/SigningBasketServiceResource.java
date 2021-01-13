package hr.abc.psd2.resources.ws.impl;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.PSU_IP_ADDRESS;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.TPP_ID;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.TPP_REDIRECT_URI;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.X_REQUEST_ID;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import hr.abc.psd2.bean.IAuthorizationBean;
import hr.abc.psd2.bean.ISigningBasketBean;
import hr.abc.psd2.model.dto.authorization.CreateAuthorizationRequestDto;
import hr.abc.psd2.model.dto.authorization.ScaStatusResponseDto;
import hr.abc.psd2.model.dto.authorization.StartAuthorizationResponseDto;
import hr.abc.psd2.model.dto.basket.BasketDto;
import hr.abc.psd2.model.dto.basket.BasketRequestDto;
import hr.abc.psd2.model.dto.basket.BasketResponseDto;
import hr.abc.psd2.model.dto.error.ErrorCode;
import hr.abc.psd2.resources.ws.ISigningBasketServiceResource;
import hr.abc.psd2.util.GenericBassxConstants;
import hr.abc.psd2.util.InternationalizationUtil;
import hr.abc.psd2.util.Psd2Constants;
import hr.abc.psd2.util.Psd2Util;
import lombok.extern.slf4j.Slf4j;


/**
 * Web service interface resource for Signing Basket .
 * <p>
 * Created by tkorpar on 07.05.2019.
 */
@Slf4j
public class SigningBasketServiceResource implements ISigningBasketServiceResource {

    
    @Inject
    private ISigningBasketBean signingBasketBean;
    
    @Inject
    private IAuthorizationBean authorizationBean;

    /**
     * summary: Create a signing basket resource
     * description:
     * Create a signing basket resource for authorising several transactions with one SCA method.
     * The resource identifications of these transactions are contained in the  payload of this access method
     */
    public Response createSigningBasket(BasketRequestDto basketRequestDto, HttpHeaders httpHeaders) {
        Response response = null;
        try {
            MultivaluedMap<String, String> requestHeaders = httpHeaders.getRequestHeaders();
            basketRequestDto.setxRequestId(requestHeaders.getFirst(X_REQUEST_ID.getValue()));
            basketRequestDto.setPsuIpAddress(requestHeaders.getFirst(PSU_IP_ADDRESS.getValue()));
            basketRequestDto.setTppRedirectUri(requestHeaders.getFirst(TPP_REDIRECT_URI.getValue()));
            basketRequestDto.setTppId(requestHeaders.getFirst(TPP_ID.getValue()));

            // BasketResponseDto basketResponseDto = signingBasketBean.create(basketRequestDto); // stari
            BasketResponseDto basketResponseDto = signingBasketBean.create(basketRequestDto); // prazni
            if (basketResponseDto.getIsValid()) {
                basketResponseDto.setIsValid(null);
                response = Response.created(new URI(basketResponseDto.get_links().getSelf().getHref())).header("ASPSP-SCA-Approach", "REDIRECT").entity(basketResponseDto).build();
                return response;
            } else {
                if (basketResponseDto.getErrorInformationDto().getTppMessages() != null && !basketResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                        basketResponseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.FORMAT_ERROR.getValue())) {
                    response = Response.status(Response.Status.BAD_REQUEST).entity(basketResponseDto.getErrorInformationDto()).build();

                } else if (basketResponseDto.getErrorInformationDto().getTppMessages() != null && !basketResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                        basketResponseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.RESOURCE_UNKNOWN.getValue())) {
                    response = Response.status(Response.Status.NOT_FOUND).entity(basketResponseDto.getErrorInformationDto()).build();
                } else if (basketResponseDto.getErrorInformationDto().getTppMessages() != null && !basketResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                        basketResponseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.RESOURCE_BLOCKED.getValue())) {
                    response = Response.status(Response.Status.BAD_REQUEST).entity(basketResponseDto.getErrorInformationDto()).build();
                } else if (basketResponseDto.getErrorInformationDto().getTppMessages() != null && !basketResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                        basketResponseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.PARAMETER_NOT_SUPPORTED.getValue())) {
                    response = Response.status(Response.Status.BAD_REQUEST).entity(basketResponseDto.getErrorInformationDto()).build();
                } else {
                    response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(basketResponseDto.getErrorInformationDto()).build();
                }
            }
        } catch (Exception e) {
            log.error("Exception is:", e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto(ErrorCode.INTERNAL_SERVER_ERROR.getValue(), new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();
            return response;
        }
        return response;
    }

    public Response getSigningBasket(String basketId, HttpHeaders httpHeaders) {
        Response response = null;
        BasketRequestDto basketRequestDto = new BasketRequestDto();
        try {
            MultivaluedMap<String, String> requestHeaders = httpHeaders.getRequestHeaders();
            basketRequestDto.setxRequestId(requestHeaders.getFirst(X_REQUEST_ID.getValue()));
            basketRequestDto.setTppId(requestHeaders.getFirst(TPP_ID.getValue()));
            // BasketDto basketDto = signingBasketBean.getAndValidateBasket(basketId, basketRequestDto); // stari
            BasketDto basketDto = signingBasketBean.getAndValidateBasket(basketId, basketRequestDto);
            if (basketDto.getValid()) {
                // basketRequestDto = signingBasketBean.getSigningBasket(basketDto); // stari
                basketRequestDto = signingBasketBean.getSigningBasket(basketDto); // prazni
                if (basketRequestDto.getValid()) {
                    basketRequestDto.setValid(null);
                    response = Response.status(Response.Status.OK).entity(basketRequestDto).build();
                } else {
                    if (basketRequestDto.getErrorInformationDto().getTppMessages() != null && !basketRequestDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            basketRequestDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.RESOURCE_UNKNOWN.getValue())) {
                        response = Response.status(Response.Status.BAD_REQUEST).entity(basketRequestDto.getErrorInformationDto()).build();
                    }
                }
            } else {
                if (basketDto.getErrorInformationDto().getTppMessages() != null && !basketDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                        basketDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.RESOURCE_UNKNOWN.getValue())) {
                    response = Response.status(Response.Status.BAD_REQUEST).entity(basketDto.getErrorInformationDto()).build();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();

        }
        return response;
    }

    public Response getSigningBasketStatusRequest(String basketId, HttpHeaders httpHeaders) {
        Response response = null;
        BasketRequestDto basketRequestDto = new BasketRequestDto();
        try {
            MultivaluedMap<String, String> requestHeaders = httpHeaders.getRequestHeaders();
            basketRequestDto.setxRequestId(requestHeaders.getFirst(X_REQUEST_ID.getValue()));
            basketRequestDto.setTppId(requestHeaders.getFirst(TPP_ID.getValue()));
            // BasketDto basketDto = signingBasketBean.getAndValidateBasket(basketId, basketRequestDto); // stari
            BasketDto basketDto = signingBasketBean.getAndValidateBasket(basketId, basketRequestDto); // prazni
            if (basketDto.getValid()) {
                // basketRequestDto = signingBasketBean.getSigningBasket(basketDto); // stari
                basketRequestDto = signingBasketBean.getSigningBasket(basketDto); // prazni
                if (basketRequestDto.getValid()) {
                    basketRequestDto.setValid(null);
                    basketRequestDto.setPaymentIds(null);
                    response = Response.status(Response.Status.OK).entity(basketRequestDto).build();
                } else {
                    if (basketRequestDto.getErrorInformationDto().getTppMessages() != null && !basketRequestDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            basketRequestDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.RESOURCE_UNKNOWN.getValue())) {
                        response = Response.status(Response.Status.BAD_REQUEST).entity(basketRequestDto.getErrorInformationDto()).build();
                    }
                }
            } else {
                if (basketDto.getErrorInformationDto().getTppMessages() != null && !basketDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                        basketDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.RESOURCE_UNKNOWN.getValue())) {
                    response = Response.status(Response.Status.BAD_REQUEST).entity(basketDto.getErrorInformationDto()).build();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();

        }
        return response;
    }

    public Response deleteSigningBasket(String basketID, HttpHeaders httpHeaders) {
        Response response = null;
        BasketRequestDto basketRequestDto = new BasketRequestDto();
        BasketResponseDto responseDto = new BasketResponseDto();
        try {
            MultivaluedMap<String, String> requestHeaders = httpHeaders.getRequestHeaders();
            basketRequestDto.setxRequestId(requestHeaders.getFirst(X_REQUEST_ID.getValue()));
            basketRequestDto.setTppId(requestHeaders.getFirst(TPP_ID.getValue()));
            // BasketDto basketDto = signingBasketBean.getAndValidateBasket(basketID, basketRequestDto); // stari
            BasketDto basketDto = signingBasketBean.getAndValidateBasket(basketID, basketRequestDto); // prazni
            if (basketDto.getValid()) {
                // responseDto = signingBasketBean.cancellSingningBasket(basketDto); // stari
                responseDto = signingBasketBean.cancellSingningBasket(basketDto); // prazni
                if (responseDto.getIsValid()) {
                    response = Response.status(Response.Status.NO_CONTENT).build();
                } else {
                    if (responseDto.getErrorInformationDto().getTppMessages() != null && !responseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            responseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.STATUS_INVALID.getValue())) {
                        response = Response.status(Response.Status.CONFLICT).entity(responseDto.getErrorInformationDto()).build();
                    }
                    if (responseDto.getErrorInformationDto().getTppMessages() != null && !responseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            responseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.INTERNAL_SERVER_ERROR.getValue())) {
                        response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto(ErrorCode.INTERNAL_SERVER_ERROR.getValue(), new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();

                    }
                }
            } else {
                if (basketDto.getErrorInformationDto().getTppMessages() != null && !basketDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                        basketDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.RESOURCE_UNKNOWN.getValue())) {
                    response = Response.status(Response.Status.BAD_REQUEST).entity(basketDto.getErrorInformationDto()).build();
                }
            }
        } catch (Exception e) {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto(ErrorCode.INTERNAL_SERVER_ERROR.getValue(), new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();

        }
        return response;
    }

    public Response startSigningBasketAuthorisation(String basketID, HttpHeaders httpHeaders) {
        Response response = null;
        BasketRequestDto basketRequestDto = new BasketRequestDto();
        try {
            MultivaluedMap<String, String> requestHeaders = httpHeaders.getRequestHeaders();
            basketRequestDto.setxRequestId(requestHeaders.getFirst(X_REQUEST_ID.getValue()));
            basketRequestDto.setTppId(requestHeaders.getFirst(TPP_ID.getValue()));
            // BasketDto basketDto = signingBasketBean.getAndValidateBasket(basketID, basketRequestDto); // stari
            BasketDto basketDto = signingBasketBean.getAndValidateBasket(basketID, basketRequestDto); // stari
            if (basketDto.getValid()) {
                // StartAuthorizationResponseDto startAuthorizationResponseDto = authorizationBean.startAuthorisation(new CreateAuthorizationRequestDto(null, null, Psd2Constants.AuthorizationObjectType.BASKET_AUTHORIZATION.getValue(), Integer.valueOf(basketID), Psd2Constants.AuthorizationType.CREATED.getValue(), basketRequestDto.getxRequestId(), basketRequestDto.getTppId(), null)); // stari
                StartAuthorizationResponseDto startAuthorizationResponseDto = authorizationBean.startAuthorisation(new CreateAuthorizationRequestDto(null, null, Psd2Constants.AuthorizationObjectType.BASKET_AUTHORIZATION.getValue(), basketID, Psd2Constants.AuthorizationType.CREATED.getValue(), basketRequestDto.getxRequestId(), basketRequestDto.getTppId(), null)); // prazni
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
            } else {
                if (basketDto.getErrorInformationDto().getTppMessages() != null && !basketDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                        basketDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.RESOURCE_UNKNOWN.getValue())) {
                    response = Response.status(Response.Status.BAD_REQUEST).entity(basketDto.getErrorInformationDto()).build();
                }
            }
        } catch (Exception e) {
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto(ErrorCode.INTERNAL_SERVER_ERROR.getValue(), new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();
        }
        return response;
    }

    public Response getSigningBasketScaStatus(String basketId, String authorisationId, HttpHeaders httpHeaders) {
        Response response = null;
        ScaStatusResponseDto scaStatusResponseDtoResult = new ScaStatusResponseDto();
        scaStatusResponseDtoResult.setValid(true);
        try {
            MultivaluedMap<String, String> requestHeaders = httpHeaders.getRequestHeaders();
            String tppId = requestHeaders.getFirst(TPP_ID.getValue());
            //validate tpp role
            //validate path param
            if (StringUtils.isNotBlank(basketId) && StringUtils.isNotBlank(authorisationId)) {
                // ScaStatusResponseDto scaStatusResponseBackWsDto = authorizationBean.getScaStatus(consentIdInt, Psd2Constants.AuthorizationObjectType.BASKET_AUTHORIZATION.getValue(), authIdInt, null, null, tppId, Psd2Constants.AuthorizationType.CREATED.getValue()); // stari
                ScaStatusResponseDto scaStatusResponseBackWsDto = authorizationBean.getScaStatus(basketId, Psd2Constants.AuthorizationObjectType.BASKET_AUTHORIZATION.getValue(), authorisationId, null, null, tppId, Psd2Constants.AuthorizationType.CREATED.getValue()); // prazni
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
                        response = Response.status(Response.Status.NOT_FOUND).entity(scaStatusResponseBackWsDto).build();
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