package hr.abc.psd2.bean.impl;

import hr.abc.psd2.bean.ISigningBasketBean;
import hr.abc.psd2.dao.AuthorizationDao;
import hr.abc.psd2.dao.BasketDao;
import hr.abc.psd2.dao.NalogTppDao;
import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.model.dto.authorization.AuthorizationDto;
import hr.abc.psd2.model.dto.authorization.ScaStatus;
import hr.abc.psd2.model.dto.basket.BasketDto;
import hr.abc.psd2.model.dto.basket.BasketRequestDto;
import hr.abc.psd2.model.dto.basket.BasketResponseDto;
import hr.abc.psd2.model.dto.core.PartijaDto;
import hr.abc.psd2.model.dto.error.ErrorCode;
import hr.abc.psd2.model.dto.pis.TppNalogPlatniDto;
import hr.abc.psd2.model.dto.pis.TransactionStatus;
import hr.abc.psd2.util.GenericBassxConstants;
import hr.abc.psd2.util.InternationalizationUtil;
import hr.abc.psd2.util.Psd2Constants;
import hr.abc.psd2.util.Psd2Util;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
public abstract class AbstractSigningBasketBean implements ISigningBasketBean , Serializable {

    private static final long serialVersionUID = 1L;

    @Inject protected
    NalogTppDao nalogTppDao;

    @Inject protected
    BasketDao basketDao;

    @Inject protected
    AuthorizationDao authorizationDao;

    protected abstract PartijaDto getPartijaDtoByBrojPartije(String brojPartije);

    public List<Integer> getNalogSifraParListfromBasket(BasketDto basketDto) {
        List<TppNalogPlatniDto> basketNalogList = nalogTppDao.getNalogTppListByBasketId(basketDto.getSifra());
        List<Integer> listaSifraParNaloga = new ArrayList<>();
        for (TppNalogPlatniDto dto : basketNalogList) {
            //String partiPar = PlatniUtil.getPartiParFromIban(dto.getIbanZaduzenja());
            String partiPar = null; // TODO Ivana
            //listaSifraParNaloga.add(AvpoUtilMin.getSifraPartijeByPartiPar(partiPar, entityManager));
            listaSifraParNaloga.add(null); // TODO Ivana
        }
        return listaSifraParNaloga;
    }


    @Override
    public BasketResponseDto create(BasketRequestDto basketRequestDto) throws AbitRuleException {
        BasketResponseDto basketResponseResultDto = null;
        try {
            //validate
            basketResponseResultDto = validateBasketBeforeCreate(basketRequestDto);
            if (!basketResponseResultDto.getIsValid()) {
                return basketResponseResultDto;
            }
            //create basket
            BasketDto basketDto = new BasketDto();
            basketDto.setCreationDateBas(LocalDateTime.now());
            basketDto.setRequestIdBas(basketRequestDto.getxRequestId());
            basketDto.setTppIdBas(basketRequestDto.getTppId());
            basketDto.setTppRedirectUriBas(basketRequestDto.getTppRedirectUri());
            basketDto.setPsuIpBas(basketRequestDto.getPsuIpAddress());
            basketDto.setBasketType(Psd2Constants.BasketType.NALOG.getValue());
            basketDto.setStatusBas(TransactionStatus.RCVD.getTransactionStatus());
            basketDto = basketDao.create(basketDto);

            //update tpp nalog
            for (TppNalogPlatniDto tppNalogPlatni : basketResponseResultDto.getListaTppNaloga()) {
                tppNalogPlatni.setSifraBasketa(basketDto.getSifra());
                nalogTppDao.edit(tppNalogPlatni);
            }
            basketResponseResultDto.setListaTppNaloga(null);
            basketResponseResultDto.setBasketId(basketDto.getSifra().toString());
            basketResponseResultDto.setTransactionStatus(basketDto.getStatusBas());
            basketResponseResultDto.set_links(Psd2Util.formLinksBasket(null, basketDto));
        } catch (Exception e) {
        	log.error("Exception is:", e);
            BasketResponseDto basketResponseDto = new BasketResponseDto();
            basketResponseDto.setIsValid(false);
            basketResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<String>(Arrays.asList("Internal server error"))));
        }
        return basketResponseResultDto;
    }

    @Override
    public BasketDto getAndValidateBasket(String basketId, BasketRequestDto basketRequestDto) {
        BasketDto validatedBasket = new BasketDto();
        if (basketId != null) {
            try {
                Integer basketIdInt = Integer.valueOf(basketId);
                validatedBasket = basketDao.getBasketDetails(basketIdInt);
                validatedBasket.setValid(true);
                if (validatedBasket == null) {
                    validatedBasket = new BasketDto();
                    validatedBasket.setValid(false);
                    validatedBasket.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unknown_basket", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                }
                if (!StringUtils.equals(validatedBasket.getTppIdBas(), basketRequestDto.getTppId())) {
                    validatedBasket.setValid(false);
                    validatedBasket.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unknown_basket", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));

                }
            } catch (NumberFormatException nfe) {
                validatedBasket.setValid(false);
                validatedBasket.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unknown_basket", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
            }
        } else {
            validatedBasket.setValid(false);
            validatedBasket.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unknown_basket", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
        }
        return validatedBasket;
    }

    @Override
    public BasketRequestDto getSigningBasket(BasketDto basketDto) {
        BasketRequestDto basketResponse = new BasketRequestDto();
        List<TppNalogPlatniDto> tppNalogList = null;
        tppNalogList = nalogTppDao.getNalogTppListByBasketId(basketDto.getSifra());
        if (tppNalogList != null && !tppNalogList.isEmpty()) {
            ArrayList<String> paymentIdArray = new ArrayList<>();
            for (TppNalogPlatniDto tppNalog : tppNalogList) {
                paymentIdArray.add(String.valueOf(tppNalog.getPaymentRequestId()));
            }
            basketResponse.setPaymentIds(paymentIdArray);
            basketResponse.setTransactionStatus(basketDto.getStatusBas());
            basketResponse.setValid(true);
        } else {
            basketResponse.setValid(false);
            basketResponse.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unknown_basket", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
        }
        return basketResponse;
    }

    @Override
    public BasketResponseDto cancellSingningBasket(BasketDto basketDto) {
        BasketResponseDto basketResponseDto = new BasketResponseDto();
        try {
            if ((StringUtils.equals(basketDto.getStatusBas(), Psd2Constants.BasketStatus.RECEIVED.getValue()) || (StringUtils.equals(basketDto.getStatusBas(), Psd2Constants.BasketStatus.PARTIALLY_ACCEPTED_TECHNICAL_CORRECT.getValue())))) {
                basketDao.changeBasketStatus(basketDto, Psd2Constants.BasketStatus.CANCELLED.getValue());
                List<AuthorizationDto> authList=authorizationDao.getBasketAuthorizationById(basketDto.getSifra(),Psd2Constants.AuthorizationObjectType.BASKET_AUTHORIZATION.getValue());
                if (CollectionUtils.isNotEmpty(authList)) {
                    for (AuthorizationDto auth : authList) {
                        auth.setScastAuth(ScaStatus.FAILED.getValue());
                        authorizationDao.edit(auth);
                    }
                }
                basketResponseDto.setIsValid(true);
            }
            else {
                basketResponseDto.setIsValid(false);
                basketResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.STATUS_INVALID.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_basket_invalid_status", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
            }
        } catch (AbitRuleException abe) {
            basketResponseDto.setIsValid(false);
            basketResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.INTERNAL_SERVER_ERROR.getValue(), new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase()))));
        }
        return basketResponseDto;
    }

    private BasketResponseDto validateBasketBeforeCreate(BasketRequestDto basketRequestDto) {
        BasketResponseDto basketResponseDto = new BasketResponseDto();
        try {
            if (basketRequestDto == null || ((basketRequestDto.getPaymentIds() == null || basketRequestDto.getPaymentIds().size() == 0) &&
                    (basketRequestDto.getConsentIds() == null || basketRequestDto.getConsentIds().size() == 0))) {
                basketResponseDto.setIsValid(false);
                basketResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<String>(Arrays.asList("Request body nije ispravan"))));
                return basketResponseDto;
            }
            //nalozi
            if (basketRequestDto.getPaymentIds() != null && basketRequestDto.getPaymentIds().size() > 0) {
                List<String> listaNaloga = new ArrayList<>(basketRequestDto.getPaymentIds());

                List<TppNalogPlatniDto> tppNalogPlatniDtoList = nalogTppDao.getTppNaloziByListaPaymentsRequestId(listaNaloga);
                if (tppNalogPlatniDtoList == null || tppNalogPlatniDtoList.size() != listaNaloga.size()) {
                    basketResponseDto.setIsValid(false);
                    basketResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_payment", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                    return basketResponseDto;
                }
                //mapa ibana zaduzenja
                Map<String, String> mapIbanZaduzenja = new HashMap<>();
                for (TppNalogPlatniDto tppNalogPlatniDto : tppNalogPlatniDtoList) {

                    //validate TPP-ID
                    if (!StringUtils.equals(tppNalogPlatniDto.getTppId(),basketRequestDto.getTppId())){
                        basketResponseDto.setIsValid(false);
                        basketResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_payment", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                        return basketResponseDto;
                    }
                    //nije potpisan // TODO status naloga je
                    /*if (tppNalogPlatniDto.getSifraKnjiznogNaloga() != null) {
                        basketResponseDto.setIsValid(false);
                        basketResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_BLOCKED.getValue(), new ArrayList<>(Arrays.asList("Jedan ili više naloga već su potpisani"))));
                        return basketResponseDto;
                    }*/
                    //nije dio basketa
                    if (tppNalogPlatniDto.getSifraBasketa() != null) {
                        BasketDto basketDto = basketDao.getBasketDetails(tppNalogPlatniDto.getSifraBasketa());
                        if (!StringUtils.equals(basketDto.getStatusBas(), TransactionStatus.CANC.getTransactionStatus())) {
                            basketResponseDto.setIsValid(false);
                            basketResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_BLOCKED.getValue(), new ArrayList<>(Arrays.asList("Jedan ili više naloga već su dio basketa"))));
                            return basketResponseDto;
                        }
                    }
                    //bulk payment - došli datotekom
                    /*if (tppNalogPlatniDto.getSifraDatoteke() != null) {
                        basketResponseDto.setIsValid(false);
                        basketResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_BLOCKED.getValue(), new ArrayList<>(Arrays.asList("Jedan ili više naloga imaju bulk-payments service"))));
                        return basketResponseDto;
                    }*/
                    //mapIbanZaduzenja.put(tppNalogPlatniDto.getIbanZaduzenja(), tppNalogPlatniDto.getIbanZaduzenja());
                }
                /*//procjera FO/PO
                if (mapIbanZaduzenja.size() > 1) {
                    List<PartijaDto> listaPartijaDto = new ArrayList<>();
                    for (String iban : mapIbanZaduzenja.keySet()) {
                        //PartijaDto partijaDto = LookupHelper.getICoreDaoProxyRemote().getPartijaDtoByBrojPartije(iban.substring(iban.length() - 10)); // stari
                        PartijaDto partijaDto = getPartijaDtoByBrojPartije(iban.substring(iban.length() - 10)); // prazni
                        //Boolean isPravna = PartijaKomitentUtil.isPravnaOsobaBySifraPartije(partijaDto.getSifra(), entityManager);
                        Boolean isPravna = true; // TODO Ivana - staviti pravi uvjet
                        if (isPravna) {
                            basketResponseDto.setIsValid(false);
                            basketResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_BLOCKED.getValue(), new ArrayList<>(Arrays.asList("Za pravne osobe mogu ući samo nalozi jednog računa"))));
                            return basketResponseDto;
                        }
                        listaPartijaDto.add(partijaDto);
                    }
                    Map<Integer, Integer> mapSifraKomitenta = new HashMap<>();
                    for (PartijaDto partijaDto : listaPartijaDto) {
                        mapSifraKomitenta.put(partijaDto.getSifraVlasnika(), partijaDto.getSifraVlasnika());
                    }
                    if (mapSifraKomitenta.size() > 1) {
                        basketResponseDto.setIsValid(false);
                        basketResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_BLOCKED.getValue(), new ArrayList<>(Arrays.asList("Za fizičke osobe mogu ući samo nalozi računa istog vlasnika"))));
                        return basketResponseDto;
                    }
                }*/
                basketResponseDto.setIsValid(true);
                basketResponseDto.setListaTppNaloga(tppNalogPlatniDtoList);

                //consent TODO kasnije - nije prioritet
            } else if (basketRequestDto.getConsentIds() != null && basketRequestDto.getConsentIds().size() > 0) {
                basketResponseDto.setIsValid(false);
                basketResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.PARAMETER_NOT_SUPPORTED.getValue(), new ArrayList<>(Arrays.asList("Signing basket consent još nije podržan"))));
                return basketResponseDto;
            }
        } catch (Exception e) {
        	log.error("Exception is:", e);
            basketResponseDto.setIsValid(false);
            basketResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<String>(Arrays.asList("Internal server error"))));
        }
        return basketResponseDto;
    }
}
