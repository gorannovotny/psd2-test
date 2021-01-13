package hr.abc.psd2.bean.impl;

import static hr.abc.psd2.model.ConsentStatus.VALID;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import hr.abc.psd2.bean.IPsd2UtilBean;
import hr.abc.psd2.dao.ConsentCofDao;
import hr.abc.psd2.model.dto.cof.ConsentCofDto;
import hr.abc.psd2.model.dto.core.NalogSepaDto;
import hr.abc.psd2.model.dto.error.ErrorCode;
import hr.abc.psd2.model.dto.pis.TppNalogPlatniDto;
import hr.abc.psd2.model.dto.pspic.ConfirmationOfFoundsRequestDto;
import hr.abc.psd2.util.Bassx2Constants;
import hr.abc.psd2.util.Bassx2MinConstants;
import hr.abc.psd2.util.GenericBassxConstants;
import hr.abc.psd2.util.InternationalizationUtil;
import hr.abc.psd2.util.Psd2Util;

public abstract class AbstractPsd2UtilBean implements IPsd2UtilBean , Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    protected ConsentCofDao consentCofDao;

    public LocalDateTime getVrijemeTrajanjaAutorizacijeSepaNaloga(List<NalogSepaDto> listaNaloga) {
        LocalDateTime endDateTimeAuthorization = null;
        if (CollectionUtils.isNotEmpty(listaNaloga)) {
            //dohvat dozvoljenih vremena
            //Map<String, LocalDateTime> defaultMapa = obracunskiDatum.napuniMapuDozvoljenaVremena(null, entityManager);
            Map<String, LocalDateTime> defaultMapa = null; // TODO Ivana
            LocalDateTime timeNacinIzvrsenja = null;
            LocalDateTime nalogDatVaTime = null;
            for (NalogSepaDto nalogSepaDto : listaNaloga) {
                timeNacinIzvrsenja = defaultMapa.get(nalogSepaDto.getNacinIzvrsenja());
                nalogDatVaTime = LocalDateTime.ofInstant(nalogSepaDto.getKnjizniNalog().getDatumValute().toInstant(), ZoneId.systemDefault());
                nalogDatVaTime = LocalDateTime.of(nalogDatVaTime.getYear(), nalogDatVaTime.getMonth(), nalogDatVaTime.getDayOfMonth(), timeNacinIzvrsenja.getHour(), timeNacinIzvrsenja.getMinute(), timeNacinIzvrsenja.getSecond());
                if (endDateTimeAuthorization == null || nalogDatVaTime.isBefore(endDateTimeAuthorization)) {
                    endDateTimeAuthorization = nalogDatVaTime;
                }
            }
        }
        return endDateTimeAuthorization;
    }

    public LocalDateTime getVrijemeTrajanjaAutorizacijeTppNaloga(List<TppNalogPlatniDto> listaNaloga) {
        LocalDateTime endDateTimeAuthorization = null;
        if (CollectionUtils.isNotEmpty(listaNaloga)) {
            //dohvat dozvoljenih vremena
            //Map<String, LocalDateTime> defaultMapa = obracunskiDatum.napuniMapuDozvoljenaVremena(null, entityManager);
            Map<String, LocalDateTime> defaultMapa = null; // TODO Ivana
            LocalDateTime timeNacinIzvrsenja = null;
            LocalDateTime nalogDatVaTime = null;
            for (TppNalogPlatniDto tppNalogPlatniDto : listaNaloga) {
                //nalog kupoprodaje
                if (StringUtils.equals(tppNalogPlatniDto.getVrstaNaloga(), GenericBassxConstants.Ib.NALOG_VRSTA_KUPNJA_DEVIZA)) {
                    timeNacinIzvrsenja = defaultMapa.get(Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_INTERNI_NALOG);
                } else {
                    timeNacinIzvrsenja = defaultMapa.get(tppNalogPlatniDto.getNacinIzvrsenja());
                }
                nalogDatVaTime = LocalDateTime.ofInstant(tppNalogPlatniDto.getDatumValute().toInstant(), ZoneId.systemDefault());
                nalogDatVaTime = LocalDateTime.of(nalogDatVaTime.getYear(), nalogDatVaTime.getMonth(), nalogDatVaTime.getDayOfMonth(), timeNacinIzvrsenja.getHour(), timeNacinIzvrsenja.getMinute(), timeNacinIzvrsenja.getSecond());
                if (endDateTimeAuthorization == null || nalogDatVaTime.isBefore(endDateTimeAuthorization)) {
                    endDateTimeAuthorization = nalogDatVaTime;
                }
            }
        }
        return endDateTimeAuthorization;
    }


    @Override
    public ConfirmationOfFoundsRequestDto validateConfirmationOfFoundsRequestBody(ConfirmationOfFoundsRequestDto confirmationOfFoundsRequestDto) {
        confirmationOfFoundsRequestDto.setValid(true);

        //validate request object
        if (confirmationOfFoundsRequestDto != null) {
            //validacija cardNumber
            if (StringUtils.isNotBlank(confirmationOfFoundsRequestDto.getCardNumber())) {
                confirmationOfFoundsRequestDto.setValid(false);
                confirmationOfFoundsRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.PARAMETER_NOT_SUPPORTED.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_psp_ic_credit_card_error", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
            }

            //validacija iban-a!!
            if (confirmationOfFoundsRequestDto.getAccount() != null && StringUtils.isNotBlank(confirmationOfFoundsRequestDto.getAccount().getIban())) {
                if (confirmationOfFoundsRequestDto.getAccount().getIban().length() == 21) {
                    if (!StringUtils.startsWith(confirmationOfFoundsRequestDto.getAccount().getIban(), "HR")
                            || !StringUtils.equals(StringUtils.substring(confirmationOfFoundsRequestDto.getAccount().getIban(), 4, 11), Bassx2MinConstants.PlatniPromet.NKSBANKA_VBDINBAN_BANKA)) {
                        confirmationOfFoundsRequestDto.setValid(false);
                        confirmationOfFoundsRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_psp_ic_iban_error", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                    }
                } else {
                    confirmationOfFoundsRequestDto.setValid(false);
                    confirmationOfFoundsRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_psp_ic_iban_error", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                }
            } else {
                confirmationOfFoundsRequestDto.setValid(false);
                confirmationOfFoundsRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_psp_ic_iban_error", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
            }

            //validacija ibana u bazi..
            try {
                if (!consentCofDao.validateAisConsentRequestAccount(confirmationOfFoundsRequestDto.getAccount().getIban())) {
                    confirmationOfFoundsRequestDto.setValid(false);
                    confirmationOfFoundsRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_psp_ic_iban_error", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                }
            } catch (Exception e) {
                confirmationOfFoundsRequestDto.setValid(false);
                confirmationOfFoundsRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_psp_ic_iban_error", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
            }

            //validacija valute
            if (confirmationOfFoundsRequestDto.getInstructedAmount() != null && StringUtils.isNotBlank(confirmationOfFoundsRequestDto.getInstructedAmount().getCurrency())) {
                try {
                    //String sifraValute = (String) PlatniUtil.getFieldValueFromTableRecord("valuta", "sifra_val", "oznka_val", confirmationOfFoundsRequestDto.getInstructedAmount().getCurrency(), entityManager);
                    String sifraValute = null; // TODO Ivana
                    if (StringUtils.isNotBlank(sifraValute)) {
                        confirmationOfFoundsRequestDto.getAccount().setSifraVal(sifraValute);
                    } else {
                        confirmationOfFoundsRequestDto.setValid(false);
                        confirmationOfFoundsRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_psp_ic_valute_error", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                    }
                } catch (Exception e) {
                    confirmationOfFoundsRequestDto.setValid(false);
                    confirmationOfFoundsRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_psp_ic_valute_error", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                }
            } else {
                confirmationOfFoundsRequestDto.setValid(false);
                confirmationOfFoundsRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_psp_ic_valute_error", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
            }

            //validacija traženog iznosa
            if (confirmationOfFoundsRequestDto.getInstructedAmount() != null && StringUtils.isNotBlank(confirmationOfFoundsRequestDto.getInstructedAmount().getAmount())) {
                try {
                    BigDecimal iznos = new BigDecimal(confirmationOfFoundsRequestDto.getInstructedAmount().getAmount());
                    if (getNumberOfDecimalPlaces(iznos) > 2) {
                        confirmationOfFoundsRequestDto.setValid(false);
                        confirmationOfFoundsRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_psp_ic_ammount_error", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                    }
                } catch (NumberFormatException nfe) {
                    confirmationOfFoundsRequestDto.setValid(false);
                    confirmationOfFoundsRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_psp_ic_ammount_error", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                }
            } else {
                confirmationOfFoundsRequestDto.setValid(false);
                confirmationOfFoundsRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_psp_ic_ammount_error", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
            }

        } else {
            confirmationOfFoundsRequestDto = new ConfirmationOfFoundsRequestDto();
            confirmationOfFoundsRequestDto.setValid(false);
            confirmationOfFoundsRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_psp_ic_req_error", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
        }
        return confirmationOfFoundsRequestDto;
    }

    private static int getNumberOfDecimalPlaces(BigDecimal bigDecimal) {
        String string = bigDecimal.stripTrailingZeros().toPlainString();
        int index = string.indexOf(".");
        return index < 0 ? 0 : string.length() - index - 1;
    }

    @Override
    public ConsentCofDto validateConsentCof(String consentId, String tppId) {
        ConsentCofDto consentCof = null;
        //to nije najbolje rješenje trbalo bi dohvatiti konsent prema id-u!!! ali do nove specifikacije je ok...
        try {
            if (consentId != null && !StringUtils.isBlank(tppId)) {
                Integer consentIdInt = Integer.parseInt(consentId);
                consentCof = consentCofDao.getConsentCofBySifra(consentIdInt);
                if (consentCof != null) {
                    consentCof.setIsValid(true);

                    //validacija TPP id-a
                    if (StringUtils.equals(consentCof.getTppIdCon(), consentId)) {
                        consentCof.setIsValid(false);
                        consentCof.setErrorInformationDto(Psd2Util.setErrorInformationDto("CONSENT_UNKNOWN", new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unknown_consent", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                    }

                    //validacija statusa
                    if (!consentCof.getStateCon().equals(VALID.getValue())) {
                        consentCof.setIsValid(false);
                        consentCof.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.STATUS_INVALID.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_consent_invalid_status", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                    }
                } else {
                    consentCof.setIsValid(false);
                    consentCof.setErrorInformationDto(Psd2Util.setErrorInformationDto("CONSENT_UNKNOWN", new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unknown_consent", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                }
            } else {
                consentCof = new ConsentCofDto();
                consentCof.setIsValid(false);
                consentCof.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_consent_tpp_mandatory", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
            }
        } catch (NumberFormatException nfe) {
            consentCof = new ConsentCofDto();
            consentCof.setIsValid(false);
            consentCof.setErrorInformationDto(Psd2Util.setErrorInformationDto("CONSENT_UNKNOWN", new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unknown_consent", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));

        } catch (Exception e) {
            consentCof = new ConsentCofDto();
            consentCof.setIsValid(false);
            consentCof.setErrorInformationDto(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase()))));
        }
        return consentCof;
    }
}
