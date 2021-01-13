package hr.abc.psd2.ws;


import java.util.ArrayList;
import java.util.Arrays;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.abc.psd2.bean.IAuthorizationBean;
import hr.abc.psd2.bean.IConsentAisBean;
import hr.abc.psd2.bean.INalogTppBean;
import hr.abc.psd2.bean.impl.paba.AuthorizationBeanPaba;
import hr.abc.psd2.dao.AuthorizationDao;
import hr.abc.psd2.dao.ConsentAisDao;
import hr.abc.psd2.dao.NalogTppDao;
import hr.abc.psd2.model.dto.ais.ConsentAisDto;
import hr.abc.psd2.model.dto.authorization.AuthorizationDto;
import hr.abc.psd2.model.dto.authorization.AuthorizationListResponseDto;
import hr.abc.psd2.model.dto.authorization.CreateAuthorizationRequestDto;
import hr.abc.psd2.model.dto.authorization.ScaStatus;
import hr.abc.psd2.model.dto.authorization.ScaStatusResponseDto;
import hr.abc.psd2.model.dto.authorization.StartAuthorizationResponseDto;
import hr.abc.psd2.model.dto.error.ErrorCode;
import hr.abc.psd2.model.dto.pis.TppNalogPlatniDto;
import hr.abc.psd2.util.GenericBassxConstants;
import hr.abc.psd2.util.InternationalizationUtil;
import hr.abc.psd2.util.Psd2Constants;
import hr.abc.psd2.util.Psd2Util;

@Path("/authorization")
public class AuthorizationBackResources {

    private static final Logger logger = LoggerFactory.getLogger(ConsentAisBackResources.class);

    @EJB
    private IAuthorizationBean authorizationBean;
 
    @EJB
    private INalogTppBean nalogTppBean;
    
    @EJB
    private IConsentAisBean consentAisBean;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{objectIdAuth}/{objectTypeAuth}/{authorizationId}/{paymentSrvTpp}/{paymentProduct}/{tppId}/{authorizationType}/status")
    public ScaStatusResponseDto getScaStatus(@PathParam("objectIdAuth") Integer objIdAuth, @PathParam("objectTypeAuth") Integer objTypeAuth, @PathParam("authorizationId") Integer authId, @PathParam("paymentSrvTpp") String paymentSrvTpp,
                                             @PathParam("paymentProduct") String paymentProduct, @PathParam("tppId") String tppId, @PathParam("authorizationType") Integer authorizationType) {
        ScaStatusResponseDto scaStatusResponseDto = new ScaStatusResponseDto();
        AuthorizationDto authorizationDto;
        try {
            if (objTypeAuth.equals(Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue())) {
                authorizationDto = authorizationBean.getPaymentAuthorization(objTypeAuth, authId, objIdAuth, paymentSrvTpp, paymentProduct);
                if (authorizationDto != null && StringUtils.isNotBlank(authorizationDto.getScastAuth())) {
                    if (authorizationDto.getTppIdAuth().equals(tppId) && authorizationDto.getAutTypeAuth().equals(authorizationType)) {
                        scaStatusResponseDto.setValid(true);
                        scaStatusResponseDto.setScaStatus(authorizationDto.getScastAuth());
                    } else {
                        scaStatusResponseDto.setValid(false);
                        scaStatusResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.NOT_FOUND.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_auth", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                    }
                } else {
                    scaStatusResponseDto.setValid(false);
                    scaStatusResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.NOT_FOUND.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_auth", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                }
            } else if (objTypeAuth.equals(Psd2Constants.AuthorizationObjectType.CONSENT_AUTHORIZATION.getValue())) {
                authorizationDto = authorizationBean.getConsentAuthorization(objIdAuth, objTypeAuth, authId);
                if (authorizationDto != null && StringUtils.isNotBlank(authorizationDto.getScastAuth())) {
                    if (authorizationDto.getTppIdAuth().equals(tppId) && authorizationDto.getAutTypeAuth().equals(authorizationType)) {
                        scaStatusResponseDto.setValid(true);
                        scaStatusResponseDto.setScaStatus(authorizationDto.getScastAuth());
                    } else {
                        scaStatusResponseDto.setValid(false);
                        scaStatusResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.NOT_FOUND.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_auth", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                    }
                } else {
                    scaStatusResponseDto.setValid(false);
                    scaStatusResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.NOT_FOUND.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_auth", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                }
            }
        } catch (Exception e) {
            logger.error("Exception is:", e);
            scaStatusResponseDto.setValid(false);
            scaStatusResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.INTERNAL_SERVER_ERROR.getValue(), new ArrayList<>(Arrays.asList("Internal server error"))));
        }
        return scaStatusResponseDto;
    }


    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{objectIdAuth}/{objectTypeAuth}/{paymentSrvTpp}/{paymentProduct}/{tppId}/{authorizationType}/authorisations")
    public AuthorizationListResponseDto getAuthorizationSubResources(@PathParam("objectIdAuth") Integer objIdAuth, @PathParam("objectTypeAuth") Integer objTypeAuth,
                                                                     @PathParam("paymentSrvTpp") String paymentSrvTpp, @PathParam("paymentProduct") String paymentProd,
                                                                     @PathParam("tppId") String tppId, @PathParam("authorizationType") Integer authorizationType) {
        AuthorizationListResponseDto authorizationListResponseDto = null;

        try {
            authorizationListResponseDto = authorizationBean.getListOfAuthIds(objIdAuth.toString(), objTypeAuth, paymentSrvTpp, paymentProd, tppId, authorizationType);
        } catch (Exception e) {
            logger.error("Exception is:", e);
            authorizationListResponseDto.setValid(false);
            authorizationListResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.INTERNAL_SERVER_ERROR.getValue(), new ArrayList<>(Arrays.asList("Internal server error"))));
        }
        return authorizationListResponseDto;
    }


    /**
     * summary: Start the authorisation process for a payment initiation - create authorization resource
     */
    @SuppressWarnings("unchecked")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/authorisations")
    public StartAuthorizationResponseDto startPaymentAuthorisation(CreateAuthorizationRequestDto authorizationRequestDto) {
        StartAuthorizationResponseDto authorizationResponseDto = new StartAuthorizationResponseDto();
        try {
            //nalog create autorization
            if (authorizationRequestDto.getObjectType().equals(Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue()) &&
                    authorizationRequestDto.getAuthorizationType().equals(Psd2Constants.AuthorizationType.CREATED.getValue())) {
                AuthorizationDto authorizationDto = null;
                TppNalogPlatniDto tppNalogPlatniDto =null; //nalogTppBean.getTppNalogBySifra(authorizationRequestDto.getObjectId());
                if (tppNalogPlatniDto != null) {
                    authorizationResponseDto = authorizationBean.validateBeforeCreateAutorizationNalogCreate(authorizationRequestDto, null); // umjesto preko dao-a ici ce preko servisa
                    if (authorizationResponseDto.getValid()) {
                        // authorizationDto = authorizationBean.createAuthorization(new AuthorizationDto(ScaStatus.RECEIVED.getValue(), Psd2Constants.ScaMethod.REDIRECT_EXPLICIT.toString(),
                                // authorizationRequestDto.getObjectId(), authorizationRequestDto.getObjectType(), authorizationRequestDto.getAuthorizationType(), authorizationRequestDto.getTppId(),
                                // authorizationRequestDto.getPsuId(), authorizationRequestDto.getxRequestId(), Psd2Util.formAutorizationScaRedirectLink(tppNalogPlatniDto.getSifra()))); // stari
                        authorizationDto = authorizationBean.createAuthorization(new AuthorizationDto(ScaStatus.RECEIVED.getValue(), Psd2Constants.ScaMethod.REDIRECT_EXPLICIT.toString(),
                                authorizationRequestDto.getObjectId(), authorizationRequestDto.getObjectType(), authorizationRequestDto.getAuthorizationType(), authorizationRequestDto.getTppId(),
                                authorizationRequestDto.getPsuId(), authorizationRequestDto.getxRequestId(), Psd2Util.formAutorizationScaRedirectLink(tppNalogPlatniDto.getSifraTppNaloga().toString()))); // prazni
                        authorizationResponseDto.setAuthorisationId(String.valueOf(authorizationDto.getIdAuth()));
                        authorizationResponseDto.setScaStatus(authorizationDto.getScastAuth());
                        authorizationResponseDto.set_links(Psd2Util.formLinksPaymentAuthorization(authorizationDto, null));
                    }
                } else {
                    authorizationResponseDto.setValid(false);
                    authorizationResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.BAD_REQUEST.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_auth_payment_err", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                }
            //nalog cancellation autorization
            } else if (authorizationRequestDto.getObjectType().equals(Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue()) &&
                    authorizationRequestDto.getAuthorizationType().equals(Psd2Constants.AuthorizationType.CANCELLED.getValue())) {
                AuthorizationDto authorizationDto = null;
                // TppNalogPlatniDto tppNalogPlatniDto = nalogTppDao.getTppNalogBySifra(authorizationRequestDto.getObjectId()); // stari
                TppNalogPlatniDto tppNalogPlatniDto = null;//nalogTppBean.getTppNalogBySifra(authorizationRequestDto.getObjectId()); // umjesto preko dao-a ici ce preko servisa
                if (tppNalogPlatniDto != null) {
                    // authorizationResponseDto = authorizationDao.validateBeforeCreateAutorizationNalogCancel(authorizationRequestDto, tppNalogPlatniDto); // stari
                    authorizationResponseDto = authorizationBean.validateBeforeCreateAutorizationNalogCancel(authorizationRequestDto, null); // umjesto preko dao-a ici ce preko servisa
                    if (authorizationResponseDto.getValid()) {
                        // authorizationDto = authorizationBean.createAuthorization(new AuthorizationDto(ScaStatus.RECEIVED.getValue(), Psd2Constants.ScaMethod.REDIRECT_EXPLICIT.toString(),
                                // authorizationRequestDto.getObjectId(), authorizationRequestDto.getObjectType(), authorizationRequestDto.getAuthorizationType(), authorizationRequestDto.getTppId(),
                                // authorizationRequestDto.getPsuId(), authorizationRequestDto.getxRequestId(), Psd2Util.formAutorizationScaRedirectLink(tppNalogPlatniDto.getSifra()))); // stari
                        authorizationDto = authorizationBean.createAuthorization(new AuthorizationDto(ScaStatus.RECEIVED.getValue(), Psd2Constants.ScaMethod.REDIRECT_EXPLICIT.toString(),
                                authorizationRequestDto.getObjectId(), authorizationRequestDto.getObjectType(), authorizationRequestDto.getAuthorizationType(), authorizationRequestDto.getTppId(),
                                authorizationRequestDto.getPsuId(), authorizationRequestDto.getxRequestId(), Psd2Util.formAutorizationScaRedirectLink(tppNalogPlatniDto.getSifraTppNaloga().toString()))); // prazni
                        authorizationResponseDto.setAuthorisationId(String.valueOf(authorizationDto.getIdAuth()));
                        authorizationResponseDto.setScaStatus(authorizationDto.getScastAuth());
                        authorizationResponseDto.set_links(Psd2Util.formLinksPaymentAuthorization(authorizationDto, null));
                    }
                } else {
                    authorizationResponseDto.setValid(false);
                    authorizationResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.BAD_REQUEST.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_auth_payment_err", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                }
            //consent create authorization
            } else if (authorizationRequestDto.getObjectType().equals(Psd2Constants.AuthorizationObjectType.CONSENT_AUTHORIZATION.getValue()) &&
                    authorizationRequestDto.getAuthorizationType().equals(Psd2Constants.AuthorizationType.CREATED.getValue())) {
                AuthorizationDto authorizationDto = null;
                ConsentAisDto consentAisDto = null;//consentAisBean.getConsentAisBySifra(authorizationRequestDto.getObjectId());
                if (consentAisDto != null) {
                    // authorizationResponseDto = authorizationDao.validateBeforeCreateAutorizationConsent(authorizationRequestDto, consentAisDto); // stari
                    authorizationResponseDto = authorizationBean.validateBeforeCreateAutorizationConsent(authorizationRequestDto, consentAisDto); // umjesto preko dao-a ici ce preko servisa
                    if (authorizationResponseDto.getValid()) {
                        // authorizationDto = authorizationBean.createAuthorization(new AuthorizationDto(ScaStatus.RECEIVED.getValue(), Psd2Constants.ScaMethod.REDIRECT_EXPLICIT.toString(),
                                // authorizationRequestDto.getObjectId(), authorizationRequestDto.getObjectType(), authorizationRequestDto.getAuthorizationType(), authorizationRequestDto.getTppId(),
                                // authorizationRequestDto.getPsuId(), authorizationRequestDto.getxRequestId(), Psd2Util.formAutorizationScaRedirectLink(consentAisDto.getSifra()))); // stari
                        authorizationDto = authorizationBean.createAuthorization(new AuthorizationDto(ScaStatus.RECEIVED.getValue(), Psd2Constants.ScaMethod.REDIRECT_EXPLICIT.toString(),
                                authorizationRequestDto.getObjectId(), authorizationRequestDto.getObjectType(), authorizationRequestDto.getAuthorizationType(), authorizationRequestDto.getTppId(),
                                authorizationRequestDto.getPsuId(), authorizationRequestDto.getxRequestId(), Psd2Util.formAutorizationScaRedirectLink(consentAisDto.getSifra().toString()))); // prazni
                        authorizationResponseDto.setAuthorisationId(String.valueOf(authorizationDto.getIdAuth()));
                        authorizationResponseDto.setScaStatus(authorizationDto.getScastAuth());
                        authorizationResponseDto.set_links(Psd2Util.formLinksConsent(authorizationDto, consentAisDto));
                    }
                } else {
                    authorizationResponseDto.setValid(false);
                    authorizationResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.BAD_REQUEST.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_auth_consent_err", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                }
            }
        } catch (Exception e) {
            logger.error("Exception is:", e);
            authorizationResponseDto.setValid(false);
            authorizationResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.INTERNAL_SERVER_ERROR.getValue(), new ArrayList<>(Arrays.asList("Internal server error"))));
        }
        return authorizationResponseDto;
    }
}

