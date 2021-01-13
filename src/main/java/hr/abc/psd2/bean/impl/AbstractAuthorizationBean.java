package hr.abc.psd2.bean.impl;

import hr.abc.psd2.bean.IAuthorizationBean;
import hr.abc.psd2.dao.*;
import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.model.dto.ais.ConsentAisDto;
import hr.abc.psd2.model.dto.authorization.*;
import hr.abc.psd2.model.dto.basket.BasketDto;
import hr.abc.psd2.model.dto.cof.ConsentCofDto;
import hr.abc.psd2.model.dto.error.ErrorCode;
import hr.abc.psd2.model.dto.pis.PaymentServiceType;
import hr.abc.psd2.model.dto.pis.SinglePaymentDto;
import hr.abc.psd2.model.dto.pis.TppNalogPlatniDto;
import hr.abc.psd2.service2.ICoreSrvc;
import hr.abc.psd2.util.GenericBassxConstants;
import hr.abc.psd2.util.InternationalizationUtil;
import hr.abc.psd2.util.Psd2Constants;
import hr.abc.psd2.util.Psd2Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.ejb.EJB;
import javax.inject.Inject;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
public abstract class AbstractAuthorizationBean implements IAuthorizationBean , Serializable {

    private static final long serialVersionUID = 1L;

    
    @Inject
    NalogTppDao nalogTppDao;

    @EJB
    AbstractPsd2UtilBean psd2UtilBean;

    @Inject
    AuthorizationDao authorizationDao;

    @Inject
    Psd2UtilDao psd2UtilDao;

    @Inject
    BasketDao basketDao;

    @Inject
    ConsentAisDao consentAisDao;

    @Inject
    private ConsentCofDao consentCofDao;

    @EJB
    AbstractSigningBasketBean basketBean;

    @Inject
    ICoreSrvc coreSrvc;

    protected abstract StartAuthorizationResponseDto validatePaymentAuthBeforeCreate();

    /**
     * summary: Start the authorisation process for a payment initiation - create authorization resource
     */
    @Override
    public StartAuthorizationResponseDto startAuthorisation(CreateAuthorizationRequestDto authorizationRequestDto) {
        StartAuthorizationResponseDto authorizationResponseDto = new StartAuthorizationResponseDto();
        try {
            //nalog create autorization
            if (authorizationRequestDto.getObjectType().equals(Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue()) &&
                    authorizationRequestDto.getPaymentService().equals(PaymentServiceType.SINGLE.getValue()) &&
                    authorizationRequestDto.getAuthorizationType().equals(Psd2Constants.AuthorizationType.CREATED.getValue())) {
                AuthorizationDto authorizationDto = null;
                //TppNalogPlatniDto tppNalogPlatniDto = nalogTppDao.getTppNalogBySifra(authorizationRequestDto.getObjectId());
                //dohvati payment

                TppNalogPlatniDto paymentAuth= nalogTppDao.getTppNalogByPaymentRequestId(authorizationRequestDto.getObjectId());//coreSrvc.getPaymentRequest(authorizationRequestDto.getPaymentService(),authorizationRequestDto.getPaymentProduct(),authorizationRequestDto.getTppId(),authorizationRequestDto.getObjectId());

                if (paymentAuth != null) {
                    paymentAuth.setPaymentRequestId(authorizationRequestDto.getObjectId());
                    authorizationResponseDto = validateBeforeCreateAutorizationNalogCreate(authorizationRequestDto, paymentAuth);
                    if (authorizationResponseDto.getValid()) {
                        //LocalDateTime toDateAut = psd2UtilBean.getVrijemeTrajanjaAutorizacijeTppNaloga(Arrays.asList(tppNalogPlatniDto));
                        authorizationDto = createAuthorization(new AuthorizationDto(ScaStatus.RECEIVED.getValue(), Psd2Constants.ScaMethod.REDIRECT_EXPLICIT.toString(),
                                authorizationRequestDto.getObjectId(), authorizationRequestDto.getObjectType(), authorizationRequestDto.getAuthorizationType(), authorizationRequestDto.getTppId(),
                                authorizationRequestDto.getPsuId(), authorizationRequestDto.getxRequestId(), Psd2Util.formAutorizationScaRedirectLink(authorizationRequestDto.getObjectId()), null));
                        authorizationResponseDto.setAuthorisationId(String.valueOf(authorizationDto.getIdAuth()));
                        authorizationResponseDto.setScaStatus(authorizationDto.getScastAuth());
                        authorizationResponseDto.set_links(Psd2Util.formLinksPaymentAuthorization(authorizationDto, paymentAuth));
                    }
                    else
                    {
                        authorizationResponseDto.setValid(false);
                    }
                } else {
                    authorizationResponseDto.setValid(false);
                    authorizationResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.BAD_REQUEST.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_auth_payment_err", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                }
                //nalog cancellation autorization
            } else if (authorizationRequestDto.getObjectType().equals(Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue()) &&
                    authorizationRequestDto.getPaymentService().equals(PaymentServiceType.SINGLE.getValue()) &&
                    authorizationRequestDto.getAuthorizationType().equals(Psd2Constants.AuthorizationType.CANCELLED.getValue())) {
                AuthorizationDto authorizationDto = null;
                TppNalogPlatniDto paymentAuthCancellation=nalogTppDao.getTppNalogByPaymentRequestId(authorizationRequestDto.getObjectId());//coreSrvc.getPaymentRequest(authorizationRequestDto.getPaymentService(),authorizationRequestDto.getPaymentProduct(),authorizationRequestDto.getTppId(),authorizationRequestDto.getObjectId());
                //TppNalogPlatniDto tppNalogPlatniDto =  null;//nalogTppDao.getTppNalogBySifra(authorizationRequestDto.getObjectId());
                if (paymentAuthCancellation != null) {
                    authorizationResponseDto = validateBeforeCreateAutorizationNalogCancel(authorizationRequestDto, paymentAuthCancellation);
                    if (authorizationResponseDto.getValid()) {
                        authorizationDto = createAuthorization(new AuthorizationDto(ScaStatus.RECEIVED.getValue(), Psd2Constants.ScaMethod.REDIRECT_EXPLICIT.toString(),
                                authorizationRequestDto.getObjectId(), authorizationRequestDto.getObjectType(), authorizationRequestDto.getAuthorizationType(), authorizationRequestDto.getTppId(),
                                authorizationRequestDto.getPsuId(), authorizationRequestDto.getxRequestId(), Psd2Util.formAutorizationScaRedirectLink(paymentAuthCancellation.getPaymentRequestId())));
                        authorizationResponseDto.setAuthorisationId(String.valueOf(authorizationDto.getIdAuth()));
                        authorizationResponseDto.setScaStatus(authorizationDto.getScastAuth());
                        authorizationResponseDto.set_links(Psd2Util.formLinksPaymentAuthorization(authorizationDto, paymentAuthCancellation));
                    }
                } else {
                    authorizationResponseDto.setValid(false);
                    authorizationResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.BAD_REQUEST.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_auth_payment_err", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                }
                //bulk create autorization
            } else if (authorizationRequestDto.getObjectType().equals(Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue()) &&
                    authorizationRequestDto.getPaymentService().equals(PaymentServiceType.BULK.getValue()) &&
                    authorizationRequestDto.getAuthorizationType().equals(Psd2Constants.AuthorizationType.CREATED.getValue())) {
                //dohvat tpp naloga
                List<TppNalogPlatniDto> listaTppNaloga = null;//nalogTppDao.getTppNaloziByDatotekaBasket(authorizationRequestDto.getObjectId(), TppNalogPlatniDto.TIP_GRUPIRANJA_DATOTEKA);
                if (listaTppNaloga != null && listaTppNaloga.size() > 0) {
                    if (StringUtils.equals(authorizationRequestDto.getPaymentService(), listaTppNaloga.get(0).getPaymentService()) &&
                            StringUtils.equals(authorizationRequestDto.getPaymentProduct(), listaTppNaloga.get(0).getPaymentProduct()) &&
                            StringUtils.equals(authorizationRequestDto.getTppId(), listaTppNaloga.get(0).getTppId())) {
                        String authLink = Psd2Util.formAutorizationScaRedirectLink(authorizationRequestDto.getObjectId());
                        List<AuthorizationDto> authorizationDtoList = new ArrayList<>();
                        LocalDateTime toDateAut = psd2UtilBean.getVrijemeTrajanjaAutorizacijeTppNaloga(listaTppNaloga);
                        for (TppNalogPlatniDto tppNalogPlatniDto : listaTppNaloga) {
                            AuthorizationDto authorizationDto = validateAndCreateAutorizationNalog(authorizationRequestDto, tppNalogPlatniDto, authLink, toDateAut);
                            if (authorizationDto != null) {
                                authorizationDtoList.add(authorizationDto);
                            }
                        }
                        if (authorizationDtoList != null && authorizationDtoList.size() > 0) {
                            authorizationResponseDto.setAuthorisationId(String.valueOf(authorizationRequestDto.getObjectId()));
                            authorizationResponseDto.setScaStatus(authorizationDtoList.get(0).getScastAuth());
                            authorizationResponseDto.set_links(Psd2Util.formLinksBulkPaymentAuthorization(authorizationDtoList.get(0), /*authorizationRequestDto.getObjectId()*/null, authorizationRequestDto.getPaymentProduct()));
                            authorizationResponseDto.setValid(true);
                        } else {
                            authorizationResponseDto.setValid(false);
                            authorizationResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.STATUS_INVALID.getValue(), new ArrayList<String>(Arrays.asList("Ne možemo započeti process autorizacije - već postoje autorizacije za datoteku."))));
                        }
                    } else {
                        authorizationResponseDto.setValid(false);
                        authorizationResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList("Ne možemo započeti process autorizacije - datoteka se ne odnosi se na TPP koji šalje zahtjev."))));
                    }
                } else {
                    authorizationResponseDto.setValid(false);
                    authorizationResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList("Ne možemo započeti process autorizacije - datoteka se ne odnosi na TPP koji šalje zahtjev!"))));
                }
                //bulk cancel authorization
            } else if (authorizationRequestDto.getObjectType().equals(Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue()) &&
                    authorizationRequestDto.getPaymentService().equals(PaymentServiceType.BULK.getValue()) &&
                    authorizationRequestDto.getAuthorizationType().equals(Psd2Constants.AuthorizationType.CANCELLED.getValue())) {
                //dohvat tpp naloga
                List<TppNalogPlatniDto> listaTppNaloga = null;//nalogTppDao.getTppNaloziByDatotekaBasket(authorizationRequestDto.getObjectId(), TppNalogPlatniDto.TIP_GRUPIRANJA_DATOTEKA);
                if (listaTppNaloga != null && listaTppNaloga.size() > 0) {
                    if (StringUtils.equals(authorizationRequestDto.getPaymentService(), listaTppNaloga.get(0).getPaymentService()) &&
                            StringUtils.equals(authorizationRequestDto.getPaymentProduct(), listaTppNaloga.get(0).getPaymentProduct()) &&
                            StringUtils.equals(authorizationRequestDto.getTppId(), listaTppNaloga.get(0).getTppId())) {
                        String authLink = Psd2Util.formAutorizationScaRedirectLink(authorizationRequestDto.getObjectId());
                        List<AuthorizationDto> authorizationDtoList = new ArrayList<>();
                        for (TppNalogPlatniDto tppNalogPlatniDto : listaTppNaloga) {
                            AuthorizationDto authorizationDto = validateAndCreateAutorizationNalogCancel(authorizationRequestDto, tppNalogPlatniDto, authLink);
                            if (authorizationDto != null) {
                                authorizationDtoList.add(authorizationDto);
                            }
                        }
                        if (authorizationDtoList != null && authorizationDtoList.size() > 0) {
                            authorizationResponseDto.setAuthorisationId(String.valueOf(authorizationRequestDto.getObjectId()));
                            authorizationResponseDto.setScaStatus(authorizationDtoList.get(0).getScastAuth());
                            authorizationResponseDto.set_links(Psd2Util.formLinksBulkPaymentAuthorization(authorizationDtoList.get(0), /*authorizationRequestDto.getObjectId()*/null, authorizationRequestDto.getPaymentProduct()));
                            authorizationResponseDto.setValid(true);
                        } else {
                            authorizationResponseDto.setValid(false);
                            authorizationResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.STATUS_INVALID.getValue(), new ArrayList<String>(Arrays.asList("Ne možemo započeti process autorizacije - već postoje autorizacije za datoteku ili nalozi datoteke nisu potpisani iil nisu najavacd.." +
                                    "."))));
                        }
                    } else {
                        authorizationResponseDto.setValid(false);
                        authorizationResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList("Ne možemo započeti process autorizacije - datoteka se ne odnosi se na TPP koji šalje zahtjev."))));
                    }
                } else {
                    authorizationResponseDto.setValid(false);
                    authorizationResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList("Ne možemo započeti process autorizacije - datoteka se ne odnosi na TPP koji šalje zahtjev."))));
                }
                //consent create authorization
            } else if (authorizationRequestDto.getObjectType().equals(Psd2Constants.AuthorizationObjectType.CONSENT_AUTHORIZATION.getValue()) &&
                    authorizationRequestDto.getAuthorizationType().equals(Psd2Constants.AuthorizationType.CREATED.getValue())) {
                AuthorizationDto authorizationDto = null;
                ConsentAisDto consentAisDto = consentAisDao.getConsentAisBySifra(Integer.parseInt(authorizationRequestDto.getObjectId()));
                if (consentAisDto != null) {
                    authorizationResponseDto = validateBeforeCreateAutorizationConsent(authorizationRequestDto, consentAisDto);
                    if (authorizationResponseDto.getValid()) {
                        authorizationDto = createAuthorization(new AuthorizationDto(ScaStatus.RECEIVED.getValue(), Psd2Constants.ScaMethod.REDIRECT_EXPLICIT.toString(),
                                authorizationRequestDto.getObjectId(), authorizationRequestDto.getObjectType(), authorizationRequestDto.getAuthorizationType(), authorizationRequestDto.getTppId(),
                                authorizationRequestDto.getPsuId(), authorizationRequestDto.getxRequestId(), Psd2Util.formAutorizationScaRedirectLink(consentAisDto.getSifra().toString())));
                        authorizationResponseDto.setAuthorisationId(String.valueOf(authorizationDto.getIdAuth()));
                        authorizationResponseDto.setScaStatus(authorizationDto.getScastAuth());
                        authorizationResponseDto.set_links(Psd2Util.formLinksConsent(authorizationDto, consentAisDto));
                    }
                    else {
                        authorizationResponseDto.setValid(false);
                    }
                } else {
                    authorizationResponseDto.setValid(false);
                    authorizationResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.BAD_REQUEST.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_auth_consent_err", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                }

                //consent COF authorisation
            } else if (authorizationRequestDto.getObjectType().equals(Psd2Constants.AuthorizationObjectType.CONSENT_COF_AUTHORUZATION.getValue()) &&
                    authorizationRequestDto.getAuthorizationType().equals(Psd2Constants.AuthorizationType.CREATED.getValue())) {
                AuthorizationDto authorizationDto = null;
                ConsentCofDto consentCofDto = consentCofDao.getConsentCofBySifra(Integer.parseInt(authorizationRequestDto.getObjectId()));
                if (consentCofDto != null) {
                    authorizationResponseDto = authorizationDao.validateBeforeCreateAutorizationConsentCof(authorizationRequestDto, consentCofDto);
                    if (authorizationResponseDto.getValid()) {
                        authorizationDto = createAuthorization(new AuthorizationDto(ScaStatus.RECEIVED.getValue(), Psd2Constants.ScaMethod.REDIRECT_EXPLICIT.toString(),
                                authorizationRequestDto.getObjectId(), authorizationRequestDto.getObjectType(), authorizationRequestDto.getAuthorizationType(), authorizationRequestDto.getTppId(),
                                authorizationRequestDto.getPsuId(), authorizationRequestDto.getxRequestId(), Psd2Util.formAutorizationScaRedirectLink(consentCofDto.getSifra().toString())));
                        authorizationResponseDto.setAuthorisationId(String.valueOf(authorizationDto.getIdAuth()));
                        authorizationResponseDto.setScaStatus(authorizationDto.getScastAuth());
                        authorizationResponseDto.set_links(Psd2Util.formLinksConsentCof(authorizationDto, consentCofDto));
                    }
                } else {
                    authorizationResponseDto.setValid(false);
                    authorizationResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.BAD_REQUEST.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_auth_consent_err", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                }
                //basket authorization
            } else if (authorizationRequestDto.getObjectType().equals(Psd2Constants.AuthorizationObjectType.BASKET_AUTHORIZATION.getValue()) &&
                    authorizationRequestDto.getAuthorizationType().equals(Psd2Constants.AuthorizationType.CREATED.getValue())) {
                AuthorizationDto authorizationDto = null;
                BasketDto basketDto = basketDao.getBasketDetails(Integer.parseInt(authorizationRequestDto.getObjectId()));
                if (basketDto != null) {
                    authorizationResponseDto = validateBeforeCreateBasketAuthorisation(authorizationRequestDto, basketDto);
                    if (authorizationResponseDto.getValid()) {
                        //List<TppNalogPlatniDto> tppNalogPlatniDtoList = nalogTppDao.getTppNaloziByDatotekaBasket(Integer.parseInt(authorizationRequestDto.getObjectId()), TppNalogPlatniDto.TIP_GRUPIRANJA_BASKET);
                        LocalDateTime toDateAut = null;//psd2UtilBean.getVrijemeTrajanjaAutorizacijeTppNaloga(tppNalogPlatniDtoList);
                        authorizationDto = createAuthorization(new AuthorizationDto(ScaStatus.RECEIVED.getValue(), Psd2Constants.ScaMethod.REDIRECT_EXPLICIT.toString(),
                                authorizationRequestDto.getObjectId(), authorizationRequestDto.getObjectType(), authorizationRequestDto.getAuthorizationType(), authorizationRequestDto.getTppId(),
                                authorizationRequestDto.getPsuId(), authorizationRequestDto.getxRequestId(), Psd2Util.formAutorizationScaRedirectLink(basketDto.getSifra().toString()), toDateAut));
                        authorizationResponseDto.setAuthorisationId(String.valueOf(authorizationDto.getIdAuth()));
                        authorizationResponseDto.setScaStatus(authorizationDto.getScastAuth());
                        authorizationResponseDto.set_links(Psd2Util.formLinksBasket(authorizationDto, basketDto));
                    }else {
                        authorizationResponseDto.setValid(false);
                        authorizationResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_auth_basket_err", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                    }
                }
            }
        } catch (Exception e) {
        	log.error("Exception is:", e);
            authorizationResponseDto.setValid(false);
            authorizationResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.INTERNAL_SERVER_ERROR.getValue(), new ArrayList<>(Arrays.asList("Internal server error"))));
        }
        return authorizationResponseDto;
    }

    public StartAuthorizationResponseDto validateBeforeCreateBasketAuthorisation(CreateAuthorizationRequestDto authorizationRequestDto, BasketDto basketDto) {
        StartAuthorizationResponseDto authorizationResponseDto = new StartAuthorizationResponseDto();
        //provjera dal se tpp ovi podudaraju
        if (StringUtils.equals(authorizationRequestDto.getTppId(), basketDto.getTppIdBas())) {
            //provjera status naloga
            if (basketDto.getStatusBas().equals(Psd2Constants.BasketStatus.RECEIVED.getValue()) || basketDto.getStatusBas().equals(Psd2Constants.BasketStatus.PARTIALLY_ACCEPTED_TECHNICAL_CORRECT.getValue())) {
                //dohvat autorizacija
                List<AuthorizationDto> authorizationDtoList = authorizationDao.getAuthorizationByObjectId(basketDto.getSifra().toString(), Psd2Constants.AuthorizationObjectType.BASKET_AUTHORIZATION.getValue(), null, null, Psd2Constants.AuthorizationType.CREATED.getValue());
                if (authorizationDtoList != null && !authorizationDtoList.isEmpty()) {
                    Integer brojValidnihAutorizacija = 0;
                    for (AuthorizationDto authorizationDto : authorizationDtoList) {
                        if (!authorizationDto.getScastAuth().equals(ScaStatus.FAILED.getValue())) {
                            brojValidnihAutorizacija++;
                        }
                    }
                    //getBrojPotpisnika
                    //Integer brojPotpisnika = psd2UtilDao.getMaxBrojIbPotpisnika(basketBean.getNalogSifraParListfromBasket(basketDto), null);
                    if (brojValidnihAutorizacija > 0) {
                        authorizationResponseDto.setValid(false);
                        authorizationResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.STATUS_INVALID.getValue(), new ArrayList<String>(Arrays.asList("Autorizacija/e za ovaj basket već postoji."))));
                    } else {
                        authorizationResponseDto.setValid(true);
                    }
                } else {
                    authorizationResponseDto.setValid(true);
                }
            } else {
                authorizationResponseDto.setValid(false);
                authorizationResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.STATUS_INVALID.getValue(), new ArrayList<String>(Arrays.asList("Ne možemo započeti process autorizacije - neispravan status basketa (" + basketDto.getStatusBas() + ")."))));
            }
        } else {
            authorizationResponseDto.setValid(false);
            authorizationResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList("Ne možemo započeti process autorizacije - basket se ne odnosi se na TPP koji šalje zahtjev."))));
        }

        return authorizationResponseDto;
    }

    private AuthorizationDto validateAndCreateAutorizationNalog(CreateAuthorizationRequestDto authorizationRequestDto, TppNalogPlatniDto tppNalogPlatniDto, String authLink, LocalDateTime toDateAut) throws AbitRuleException {
        AuthorizationDto authorizationResultDto = null;
        //provjera dal se podudaraju service, product, tpp
        if (StringUtils.equals(authorizationRequestDto.getPaymentService(), tppNalogPlatniDto.getPaymentService()) &&
                StringUtils.equals(authorizationRequestDto.getPaymentProduct(), tppNalogPlatniDto.getPaymentProduct()) &&
                StringUtils.equals(authorizationRequestDto.getTppId(), tppNalogPlatniDto.getTppId())) {
            //provjera status naloga
            if (tppNalogPlatniDto.getSifraKnjiznogNaloga() == null) {
                Boolean mozeCreate;
                //dohvat autorizacija
                List<AuthorizationDto> authorizationDtoList = authorizationDao.getAuthorizationByObjectId(tppNalogPlatniDto.getSifraTppNaloga().toString(), Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), tppNalogPlatniDto.getPaymentService(),
                        tppNalogPlatniDto.getPaymentProduct(), Psd2Constants.AuthorizationType.CREATED.getValue());
                if (authorizationDtoList != null && !authorizationDtoList.isEmpty()) {
                    Integer brojValidnihAutorizacija = 0;
                    for (AuthorizationDto authorizationDto : authorizationDtoList) {
                        if (!authorizationDto.getScastAuth().equals(ScaStatus.FAILED.getValue())) {
                            brojValidnihAutorizacija = brojValidnihAutorizacija + 1;
                        }
                    }
                    Integer brojPotpisnika = psd2UtilDao.getMaxBrojIbPotpisnika(null, Arrays.asList(tppNalogPlatniDto.getIbanZaduzenja().substring(tppNalogPlatniDto.getIbanZaduzenja().length() - 10)));
                    if (brojPotpisnika.compareTo(brojValidnihAutorizacija) <= 0) {
                        mozeCreate = Boolean.FALSE;
                    } else {
                        mozeCreate = Boolean.TRUE;
                    }
                } else {
                    mozeCreate = Boolean.TRUE;
                }
                if (mozeCreate) {
                    authorizationResultDto = createAuthorization(new AuthorizationDto(ScaStatus.RECEIVED.getValue(), Psd2Constants.ScaMethod.REDIRECT_EXPLICIT.toString(),
                            tppNalogPlatniDto.getSifraTppNaloga().toString(), authorizationRequestDto.getObjectType(), authorizationRequestDto.getAuthorizationType(), authorizationRequestDto.getTppId(),
                            authorizationRequestDto.getPsuId(), authorizationRequestDto.getxRequestId(), authLink, toDateAut));
                }
            }
        }
        return authorizationResultDto;
    }

    private AuthorizationDto validateAndCreateAutorizationNalogCancel(CreateAuthorizationRequestDto authorizationRequestDto, TppNalogPlatniDto tppNalogPlatniDto, String authLink) throws AbitRuleException {
        AuthorizationDto authorizationResultDto = null;
        //provjera dal se podudaraju service, product, tpp
        if (StringUtils.equals(authorizationRequestDto.getPaymentService(), tppNalogPlatniDto.getPaymentService()) &&
                StringUtils.equals(authorizationRequestDto.getPaymentProduct(), tppNalogPlatniDto.getPaymentProduct()) &&
                StringUtils.equals(authorizationRequestDto.getTppId(), tppNalogPlatniDto.getTppId())) {
            //provjera status naloga
            if (tppNalogPlatniDto.mozeStartAutorizacijePovlacenje()) {
                Boolean mozeCreate;
                //dohvat autorizacija
                List<AuthorizationDto> authorizationDtoList = authorizationDao.getAuthorizationByObjectId(tppNalogPlatniDto.getSifraTppNaloga().toString(), Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), tppNalogPlatniDto.getPaymentService(),
                        tppNalogPlatniDto.getPaymentProduct(), Psd2Constants.AuthorizationType.CANCELLED.getValue());
                if (authorizationDtoList != null && !authorizationDtoList.isEmpty()) {
                    Integer brojValidnihAutorizacija = 0;
                    for (AuthorizationDto authorizationDto : authorizationDtoList) {
                        if (!authorizationDto.getScastAuth().equals(ScaStatus.FAILED.getValue())) {
                            brojValidnihAutorizacija = brojValidnihAutorizacija + 1;
                        }
                    }
                    Integer brojPotpisnika = psd2UtilDao.getMaxBrojIbPotpisnika(null, Arrays.asList(tppNalogPlatniDto.getIbanZaduzenja().substring(tppNalogPlatniDto.getIbanZaduzenja().length() - 10)));
                    if (brojPotpisnika.compareTo(brojValidnihAutorizacija) <= 0) {
                        mozeCreate = Boolean.FALSE;
                    } else {
                        mozeCreate = Boolean.TRUE;
                    }
                } else {
                    mozeCreate = Boolean.TRUE;
                }
                if (mozeCreate) {
                    authorizationResultDto = createAuthorization(new AuthorizationDto(ScaStatus.RECEIVED.getValue(), Psd2Constants.ScaMethod.REDIRECT_EXPLICIT.toString(),
                            tppNalogPlatniDto.getSifraTppNaloga().toString(), authorizationRequestDto.getObjectType(), authorizationRequestDto.getAuthorizationType(), authorizationRequestDto.getTppId(),
                            authorizationRequestDto.getPsuId(), authorizationRequestDto.getxRequestId(), authLink));
                }
            }
        }
        return authorizationResultDto;
    }


    @Override
    public AuthorizationListResponseDto getBulkAuthorizationSubResources(Integer objIdAuth, String paymentProd, String tppId, Integer authorizationType) {
        AuthorizationListResponseDto authorizationListResponseDto = null;
        try {
            authorizationListResponseDto = authorizationDao.getBulkListOfAuthIds(objIdAuth, paymentProd, tppId, authorizationType);
        } catch (Exception e) {
        	log.error("Exception is:", e);
            authorizationListResponseDto.setValid(false);
            authorizationListResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.INTERNAL_SERVER_ERROR.getValue(), new ArrayList<>(Arrays.asList("Internal server error"))));
        }
        return authorizationListResponseDto;
    }

    @Override
    public ScaStatusResponseDto getScaStatus(String objIdAuth, Integer objTypeAuth, String authId, String paymentSrvTpp, String paymentProduct, String tppId, Integer authorizationType) {
        ScaStatusResponseDto scaStatusResponseDto = new ScaStatusResponseDto();
        AuthorizationDto authorizationDto;
        try {
            //NALOG - SINGLE
            if (objTypeAuth.equals(Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue()) && paymentSrvTpp.equals(PaymentServiceType.SINGLE.getValue())) {
                authorizationDto = authorizationDao.getPaymentAuthorization(objTypeAuth,authId,objIdAuth,paymentSrvTpp,paymentProduct);
                if (authorizationDto != null && StringUtils.isNotBlank(authorizationDto.getScastAuth())) {
                    if (authorizationDto.getTppIdAuth().equals(tppId) && authorizationDto.getAutTypeAuth().equals(authorizationType)) {
                        scaStatusResponseDto.setValid(true);
                        scaStatusResponseDto.setScaStatus(authorizationDto.getScastAuth());
                    } else {
                        scaStatusResponseDto.setValid(false);
                        scaStatusResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_auth", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                    }
                } else {
                    scaStatusResponseDto.setValid(false);
                    scaStatusResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_auth", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                }
//                //NALOG - BULK
//            } else if (objTypeAuth.equals(Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue()) && paymentSrvTpp.equals(PaymentServiceType.BULK.getValue())) {
//                List<AuthorizationDto> resultAuthDtoList = authorizationDao.getAuthorizationsByDatoteka(objIdAuth, authorizationType, authId);
//                if (resultAuthDtoList != null && resultAuthDtoList.size() == 1) {
//                    if (resultAuthDtoList.get(0).getTppIdAuth().equals(tppId)) {
//                        scaStatusResponseDto.setValid(true);
//                        scaStatusResponseDto.setScaStatus(resultAuthDtoList.get(0).getScastAuth());
//                    } else {
//                        scaStatusResponseDto.setValid(false);
//                        scaStatusResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.NOT_FOUND.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_auth", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
//                    }
//                } else {
//                    scaStatusResponseDto.setValid(false);
//                    scaStatusResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.NOT_FOUND.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_auth", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
//                }
                //NALOG - BULK CREATE
            } else if (objTypeAuth.equals(Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue()) && paymentSrvTpp.equals(PaymentServiceType.BULK.getValue()) && authorizationType.equals(Psd2Constants.AuthorizationType.CREATED.getValue())) {
                List<TppNalogPlatniDto> tppNalogPlatniDtoList = nalogTppDao.getTppNaloziByDatotekaBasket(Integer.parseInt(objIdAuth), TppNalogPlatniDto.TIP_GRUPIRANJA_DATOTEKA);
                if (tppNalogPlatniDtoList != null && tppNalogPlatniDtoList.size() > 0) {
                    Boolean sviPotpisani = Boolean.TRUE;
                    if (tppNalogPlatniDtoList.get(0).getTppId().equals(tppId)) {
                        for (TppNalogPlatniDto tppNalogPlatniDto : tppNalogPlatniDtoList) {
                            if (tppNalogPlatniDto.getSifraKnjiznogNaloga() == null) {
                                sviPotpisani = Boolean.FALSE;
                            }
                        }
                        if (sviPotpisani) {
                            scaStatusResponseDto.setValid(true);
                            scaStatusResponseDto.setScaStatus(ScaStatus.FINALISED.getValue());
                        } else {
                            List<AuthorizationDto> resultAuthDtoList = authorizationDao.getAuthorizationsByDatoteka(Integer.parseInt(objIdAuth), authorizationType, null);
                            if (resultAuthDtoList != null && resultAuthDtoList.size() > 0) {
                                scaStatusResponseDto.setValid(true);
                                scaStatusResponseDto.setScaStatus(ScaStatus.RECEIVED.getValue());
                            } else {
                                scaStatusResponseDto.setValid(false);
                                scaStatusResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_auth", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                            }
                        }
                    } else {
                        scaStatusResponseDto.setValid(false);
                        scaStatusResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_auth", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                    }
                }

                //NALOG - BULK CANCEL
            } else if (objTypeAuth.equals(Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue()) && paymentSrvTpp.equals(PaymentServiceType.BULK.getValue()) && authorizationType.equals(Psd2Constants.AuthorizationType.CANCELLED.getValue())) {
                List<TppNalogPlatniDto> tppNalogPlatniDtoList = nalogTppDao.getTppNaloziByDatotekaBasket(Integer.parseInt(objIdAuth), TppNalogPlatniDto.TIP_GRUPIRANJA_DATOTEKA);
                if (tppNalogPlatniDtoList != null && tppNalogPlatniDtoList.size() > 0) {
                    Boolean sviPovuceni = Boolean.TRUE;
                    if (tppNalogPlatniDtoList.get(0).getTppId().equals(tppId)) {
                        for (TppNalogPlatniDto tppNalogPlatniDto : tppNalogPlatniDtoList) {
                            if (StringUtils.isBlank(tppNalogPlatniDto.getStatus()) || !StringUtils.equals(tppNalogPlatniDto.getStatus(), GenericBassxConstants.Core.NALOG_STATUNAL_POVUCEN)) {
                                sviPovuceni = Boolean.FALSE;
                            }
                        }
                        if (sviPovuceni) {
                            scaStatusResponseDto.setValid(true);
                            scaStatusResponseDto.setScaStatus(ScaStatus.FINALISED.getValue());
                        } else {
                            List<AuthorizationDto> resultAuthDtoList = authorizationDao.getAuthorizationsByDatoteka(Integer.parseInt(objIdAuth), authorizationType, null);
                            if (resultAuthDtoList != null && resultAuthDtoList.size() > 0) {
                                scaStatusResponseDto.setValid(true);
                                scaStatusResponseDto.setScaStatus(ScaStatus.RECEIVED.getValue());
                            } else {
                                scaStatusResponseDto.setValid(false);
                                scaStatusResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_auth", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                            }
                        }
                    } else {
                        scaStatusResponseDto.setValid(false);
                        scaStatusResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_auth", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                    }
                }
                //CONSENT
            } else if (objTypeAuth.equals(Psd2Constants.AuthorizationObjectType.CONSENT_AUTHORIZATION.getValue()) || objTypeAuth.equals(Psd2Constants.AuthorizationObjectType.CONSENT_COF_AUTHORUZATION.getValue())) {
                authorizationDto = getConsentAuthorization(Integer.parseInt(objIdAuth), objTypeAuth, Integer.parseInt(authId));
                if (authorizationDto != null && StringUtils.isNotBlank(authorizationDto.getScastAuth())) {
                    if (authorizationDto.getTppIdAuth().equals(tppId) && authorizationDto.getAutTypeAuth().equals(authorizationType)) {
                        scaStatusResponseDto.setValid(true);
                        scaStatusResponseDto.setScaStatus(authorizationDto.getScastAuth());
                    } else {
                        scaStatusResponseDto.setValid(false);
                        scaStatusResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_auth", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                    }
                } else {
                    scaStatusResponseDto.setValid(false);
                    scaStatusResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_auth", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                }
                //BASKET
            } else if (objTypeAuth.equals(Psd2Constants.AuthorizationObjectType.BASKET_AUTHORIZATION.getValue())) {
                authorizationDto = authorizationDao.getBasketAuthorization(Integer.parseInt(objIdAuth), objTypeAuth, Integer.parseInt(authId));
                if (authorizationDto != null && StringUtils.isNotBlank(authorizationDto.getScastAuth())) {
                    if (authorizationDto.getTppIdAuth().equals(tppId) && authorizationDto.getAutTypeAuth().equals(authorizationType)) {
                        scaStatusResponseDto.setValid(true);
                        scaStatusResponseDto.setScaStatus(authorizationDto.getScastAuth());
                    } else {
                        scaStatusResponseDto.setValid(false);
                        scaStatusResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_auth", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                    }
                } else {
                    scaStatusResponseDto.setValid(false);
                    scaStatusResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_auth", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                }
            }

        } catch (Exception e) {
        	log.error("Exception is:", e);
            scaStatusResponseDto.setValid(false);
            scaStatusResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.INTERNAL_SERVER_ERROR.getValue(), new ArrayList<>(Arrays.asList("Internal server error"))));
        }

        return scaStatusResponseDto;
    }

    @Override
    public AuthorizationListResponseDto getAuthorizationSubResources(String objIdAuth, Integer objTypeAuth, String paymentSrvTpp, String paymentProd, String tppId, Integer authorizationType) {
        AuthorizationListResponseDto authorizationListResponseDto = null;
        try {
            authorizationListResponseDto = getListOfAuthIds(objIdAuth, objTypeAuth, paymentSrvTpp, paymentProd, tppId, authorizationType);
        } catch (Exception e) {
        	log.error("Exception is:", e);
            authorizationListResponseDto.setValid(false);
            authorizationListResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.INTERNAL_SERVER_ERROR.getValue(), new ArrayList<>(Arrays.asList("Internal server error"))));
        }
        return authorizationListResponseDto;
    }

    @Override
    public AuthorizationDto createAuthorization(AuthorizationDto authorizationDto) throws AbitRuleException {
        AuthorizationDto result = null;
        try {
            if (authorizationDto != null) {
                result = authorizationDao.createAuthorization(authorizationDto);
            }
        } catch (Exception e) {
        	log.error("Exception is:", e);
            throw new AbitRuleException("Greška kod spremanja autorizacije (createAuthorization): " + e);
        }
        return result;
    }

    @Override
    public AuthorizationDto getPaymentAuthorization(Integer objTypeAuth, Integer idAuth, Integer objIdAuth, String paymentSerTpp, String paymentPrdTpp) {
        return authorizationDao.getPaymentAuthorization(objTypeAuth, idAuth.toString(), objIdAuth.toString(), paymentSerTpp, paymentPrdTpp);
    }

    @Override
    public AuthorizationDto getConsentAuthorization(Integer objecIdAuth, Integer objTypeAuth, Integer idAuth) {
        return authorizationDao.getConsentAuthorization(objecIdAuth, objTypeAuth, idAuth);
    }

    @Override
    public AuthorizationListResponseDto getListOfAuthIds(String objectIdAuth, Integer objTypeAuth, String paymentSrv, String paymentProduct, String tppId, Integer authotizationType) {
        return authorizationDao.getListOfAuthIds(objectIdAuth, objTypeAuth, paymentSrv, paymentProduct,tppId, authotizationType);
    }

    @Override
    public StartAuthorizationResponseDto validateBeforeCreateAutorizationConsent(CreateAuthorizationRequestDto authorizationRequestDto, ConsentAisDto consentAisDto) {
        return  authorizationDao.validateBeforeCreateAutorizationConsent(authorizationRequestDto,consentAisDto);
    }

    @Override
    public StartAuthorizationResponseDto validateBeforeCreateAutorizationNalogCreate(CreateAuthorizationRequestDto authorizationRequestDto, TppNalogPlatniDto tppNalogPlatniDto) {
        return authorizationDao.validateBeforeCreateAutorizationNalogCreate(authorizationRequestDto, tppNalogPlatniDto);
    }

    @Override
    public StartAuthorizationResponseDto validateBeforeCreateAutorizationNalogCancel(CreateAuthorizationRequestDto authorizationRequestDto, TppNalogPlatniDto tppNalogPlatniDto) {
        return authorizationDao.validateBeforeCreateAutorizationNalogCancel(authorizationRequestDto,tppNalogPlatniDto);
    }
}
