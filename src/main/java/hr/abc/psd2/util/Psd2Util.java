package hr.abc.psd2.util;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.IdentifiableType;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.WriterInterceptorContext;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import hr.abc.psd2.config.AppConfig;
import io.vertx.core.MultiMap;
import io.vertx.core.http.HttpServerRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.model.ConsentTypeAccess;
import hr.abc.psd2.model.dto.ConsentCofRequestResponseDto;
import hr.abc.psd2.model.dto.ConsentRequestResponseDto;
import hr.abc.psd2.model.dto.RequestHeaderDto;
import hr.abc.psd2.model.dto.ais.AccountReferenceDto;
import hr.abc.psd2.model.dto.ais.Amount;
import hr.abc.psd2.model.dto.ais.ConsentAisDto;
import hr.abc.psd2.model.dto.ais.Href;
import hr.abc.psd2.model.dto.ais.Links;
import hr.abc.psd2.model.dto.authorization.AuthorizationDto;
import hr.abc.psd2.model.dto.basket.BasketDto;
import hr.abc.psd2.model.dto.cof.ConsentCofDto;
import hr.abc.psd2.model.dto.core.NalogNefinDto;
import hr.abc.psd2.model.dto.core.NalogSepaDto;
import hr.abc.psd2.model.dto.core.PrometNefinDto;
import hr.abc.psd2.model.dto.core.SifraNamjeneDto;
import hr.abc.psd2.model.dto.error.ErrorCode;
import hr.abc.psd2.model.dto.error.ErrorInformationDto;
import hr.abc.psd2.model.dto.error.ErrorInformationXmlDto;
import hr.abc.psd2.model.dto.error.ErrorMessageDto;
import hr.abc.psd2.model.dto.pis.AddressDto;
import hr.abc.psd2.model.dto.pis.PaymentProduct;
import hr.abc.psd2.model.dto.pis.PaymentServiceType;
import hr.abc.psd2.model.dto.pis.Psd2DatotekaDto;
import hr.abc.psd2.model.dto.pis.RemittanceDto;
import hr.abc.psd2.model.dto.pis.SinglePaymentDto;
import hr.abc.psd2.model.dto.pis.SinglePaymentResponseDto;
import hr.abc.psd2.model.dto.pis.TppNalogPlatniDto;
import hr.abc.psd2.model.dto.pis.TransactionStatus;
import hr.abc.psd2.model.dto.pis.pain002.BranchAndFinancialInstitutionIdentification4;
import hr.abc.psd2.model.dto.pis.pain002.CustomerPaymentStatusReportV03;
import hr.abc.psd2.model.dto.pis.pain002.Document;
import hr.abc.psd2.model.dto.pis.pain002.FinancialInstitutionIdentification7;
import hr.abc.psd2.model.dto.pis.pain002.GroupHeader36;
import hr.abc.psd2.model.dto.pis.pain002.OriginalGroupInformation20;
import hr.abc.psd2.model.dto.pis.pain002.OriginalPaymentInformation1;
import hr.abc.psd2.model.dto.pis.pain002.TransactionGroupStatus3Code;
import hr.abc.psd2.security.Globals;
import hr.abc.psd2.service2.ICoreSrvc;
import hr.abc.psd2.service2.IPlatniSrvc;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Tomica on 04-Dec-18.
 */
@Slf4j
@Dependent
public class Psd2Util {

    //static final Logger LOGGER = LoggerFactory.getLogger(Psd2Util.class);


    @Inject
    private static EntityManager entityManager;

    @Inject
    private static ICoreSrvc coreSrvc; // prazni
    @Inject
    private static IPlatniSrvc platniSrvc; // prazni

    @Inject
    private static AppConfig appConfig;

    public boolean isIbanBbanForConsentValid(String account) {
        boolean result = true;
        if (StringUtils.isBlank(account)) {
            result = false;
            //duljina 21 (IBAN) ili 17 (BBAN)
        } else if (account.length() != 21 && account.length() != 17) {
            result = false;
            //IBAN - start with HR i od 5-11 znaka treba biti jednako Bassx2MinConstants.PlatniPromet.NKSBANKA_VBDINBAN_BANKA
        } else if (account.length() == 21) {
            //TODO VBDI
            if (!StringUtils.startsWith(account, "HR") || !StringUtils.equals(StringUtils.substring(account, 4, 11), appConfig.vbdi())) {
                result = false;
            }
            //BBAN prvih 7 znakova treba biti jedno Bassx2MinConstants.PlatniPromet.NKSBANKA_VBDINBAN_BANKA
        } else if (account.length() == 17) {
            if (!StringUtils.equals(StringUtils.substring(account, 0, 7), Bassx2MinConstants.PlatniPromet.NKSBANKA_VBDINBAN_BANKA)) {
                result = false;
            }
        }
        return result;
    }

    public static NalogSepaDto formNalogSepaDtoFromPsd2Nalog(SinglePaymentDto psd2Nalog, Globals globals) {
        NalogSepaDto nalogSepaDto = null;
        if (psd2Nalog != null) {
            SimpleDateFormat dateFormater = new SimpleDateFormat(Psd2Constants.DATE_PSD2_FORMAT);
            dateFormater.setLenient(false);
            nalogSepaDto = new NalogSepaDto();
            // defaults
            defaultsForNalogSepaDtoFromPsd2Nalog(nalogSepaDto, globals);
            //model i poziv na broj zaduzenja
            if (StringUtils.isNotBlank(psd2Nalog.getEndToEndIdentification())) {
                if (psd2Nalog.getEndToEndIdentification().length() >= 4) {
                    nalogSepaDto.setModelZaduzenja(StringUtils.substring(psd2Nalog.getEndToEndIdentification(), 0, 4));
                    nalogSepaDto.setPozivNaBrojZaduzenja(StringUtils.substring(psd2Nalog.getEndToEndIdentification(), 4));
                } else {
                    nalogSepaDto.setModelZaduzenja(psd2Nalog.getEndToEndIdentification());
                }
            }
            //iban zaduzenja
            if (StringUtils.isNotBlank(psd2Nalog.getDebtorAccount().getIban())) {
                nalogSepaDto.setIbanZaduzenja(psd2Nalog.getDebtorAccount().getIban());
            }
            //id zaduženja
            if (StringUtils.isNotBlank(psd2Nalog.getDebtorId())) {
                nalogSepaDto.setNazivDebtor(psd2Nalog.getDebtorId());
            }
            //stvarni dužnik
            if (StringUtils.isNotBlank(psd2Nalog.getUltimateDebtor())) {
                nalogSepaDto.setUltimateDebtorNaziv(psd2Nalog.getUltimateDebtor());
            }
            //iznos i valuta
            if (psd2Nalog.getInstructedAmount() != null) {
                //iznos
                if (StringUtils.isNotBlank(psd2Nalog.getInstructedAmount().getAmount())) {
                    try {
                        nalogSepaDto.setIznos(new BigDecimal(psd2Nalog.getInstructedAmount().getAmount()));
                    } catch (NumberFormatException nre) {
                        nalogSepaDto.setGreska(InternationalizationUtil.getLocalMessage("psd2_amountIllegalFormat", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
                        nalogSepaDto.addExceptions(new AbitRuleException(nalogSepaDto.getGreska()));
                    }
                }
                //valuta
                if (StringUtils.isNotBlank(psd2Nalog.getInstructedAmount().getCurrency())) {
                    nalogSepaDto.setSifraValute(psd2Nalog.getInstructedAmount().getCurrency()); //kasnije se kroz validaciju mijenja iz oznake u šifru
                    nalogSepaDto.setOznakaValute(psd2Nalog.getInstructedAmount().getCurrency());
                }
            }
            // iban odobrenja
            if (StringUtils.isNotBlank(psd2Nalog.getCreditorAccount().getIban())) {
                nalogSepaDto.setIbanOdobrenja(psd2Nalog.getCreditorAccount().getIban());
            }
            // SWIFT BIC nalogodavatelja/korisnika
            if (psd2Nalog.getPaymentProduct().toLowerCase().equals(PaymentProduct.CROSS_BORDER_CREDIT_TRANSFER.getCode())){
                if (StringUtils.isNotBlank(psd2Nalog.getCreditorAgent())) {
                    if (StringUtils.length(psd2Nalog.getCreditorAgent()) > 5) {
                        nalogSepaDto.setSwiftBicNalogodavateljaKorisnika(psd2Nalog.getCreditorAgent());
                    }
                    if (StringUtils.isNotBlank(psd2Nalog.getCreditorAgentName())) {
                        nalogSepaDto.setSwiftBicNalogodavateljaKorisnikaNaziv(psd2Nalog.getCreditorAgentName());
                    }
                } else {
                    if (StringUtils.isNotBlank(psd2Nalog.getCreditorAgentName())) {
                        nalogSepaDto.setSwiftBicNalogodavateljaKorisnikaNaziv(psd2Nalog.getCreditorAgentName());
                    }
                }
            }
            //naziv primatelja
            if (StringUtils.isNotBlank(psd2Nalog.getCreditorName())) {
                nalogSepaDto.setNazivCreditor(psd2Nalog.getCreditorName());
            }
            //id primatelja
            if (StringUtils.isNotBlank(psd2Nalog.getCreditorId())) {
                nalogSepaDto.setIdCreditor(psd2Nalog.getCreditorId());
            }
            //adresa primatelja
            if (psd2Nalog.getCreditorAddress() != null) {
                //drzava
                if (StringUtils.isNotBlank(psd2Nalog.getCreditorAddress().getCountry())) {
                    nalogSepaDto.setDrzavaCreditor(psd2Nalog.getCreditorAddress().getCountry());
                }
                //grad
                if (StringUtils.isNotBlank(psd2Nalog.getCreditorAddress().getTownName())) {
                    nalogSepaDto.setGradCreditor(psd2Nalog.getCreditorAddress().getTownName());
                }
                //adresa
                if (StringUtils.isNotBlank(psd2Nalog.getCreditorAddress().getStreetName())) {
                    String adresa = psd2Nalog.getCreditorAddress().getStreetName();
                    if (StringUtils.isNotBlank(psd2Nalog.getCreditorAddress().getBuildingNumber())) {
                        if (StringUtils.isNotBlank(adresa)) {
                            adresa = adresa + (StringUtils.isNotBlank(psd2Nalog.getCreditorAddress().getBuildingNumber()) ? " " + psd2Nalog.getCreditorAddress().getBuildingNumber() : "");
                        }
                    }
                    nalogSepaDto.setAdresaCreditor(adresa);
                }
            }
            //Krajnji primatelj
            if (StringUtils.isNotBlank(psd2Nalog.getUltimateCreditor())) {
                nalogSepaDto.setUltimateCreditorNaziv(psd2Nalog.getUltimateCreditor());
            }
            //Šifra namjene
            if (StringUtils.isNotBlank(psd2Nalog.getPurposeCode())) {
                try {
                    //SifraNamjeneDto sifraNamjene = LookupHelper.getIPlatniDaoProxyRemote().getSifraNamjeneDtoBySifraNamjene(psd2Nalog.getPurposeCode()); // stari
                    SifraNamjeneDto sifraNamjene = platniSrvc.getSifraNamjeneDtoBySifraNamjene(psd2Nalog.getPurposeCode()); // prazni
                    if (sifraNamjene == null) {
                        nalogSepaDto.setGreska(InternationalizationUtil.getLocalMessage("psd2NeispravnaSifraNamjene", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
                        nalogSepaDto.addExceptions(new AbitRuleException(nalogSepaDto.getGreska()));
                    } else {
                        nalogSepaDto.setSifraNamjene(psd2Nalog.getPurposeCode());
                    }
                //} catch (AbitRuleException e) {
                } catch (Exception e) {
                    nalogSepaDto.setGreska(InternationalizationUtil.getLocalMessage("psd2GreskaDohvatSifraNamjene", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
                    nalogSepaDto.addExceptions(new AbitRuleException(nalogSepaDto.getGreska()));
                }
            }
            //troškovna opcija
            if (StringUtils.isNotBlank(psd2Nalog.getChargeBearer())) {
                switch (psd2Nalog.getChargeBearer()) {
                    case "DEBT":
                        nalogSepaDto.setTrosakInoBanke(Bassx2Constants.PlatniPromet.DEVIZNI_NALOG_TROSAK_INO_BANKE_OUR);
                        break;
                    case "CRED":
                        nalogSepaDto.setTrosakInoBanke(Bassx2Constants.PlatniPromet.DEVIZNI_NALOG_TROSAK_INO_BANKE_BEN);
                        break;
                    case "SLEV":
                    case "SHAR":
                        nalogSepaDto.setTrosakInoBanke(Bassx2Constants.PlatniPromet.DEVIZNI_NALOG_TROSAK_INO_BANKE_SHA);
                        break;
                    default:
                        nalogSepaDto.setTrosakInoBanke(psd2Nalog.getChargeBearer());
                }
            } else {
                nalogSepaDto.setTrosakInoBanke(Bassx2Constants.PlatniPromet.DEVIZNI_NALOG_TROSAK_INO_BANKE_SHA);
            }
            //svrha naloga
            if (StringUtils.isNotBlank(psd2Nalog.getRemittanceInformationUnstructured())) {
                nalogSepaDto.getKnjizniNalog().setSvrha(psd2Nalog.getRemittanceInformationUnstructured());
            }
            //model i poziv na broj odobrenja
            if (psd2Nalog.getRemittanceInformationStructured() != null && StringUtils.isNotBlank(psd2Nalog.getRemittanceInformationStructured().getReference())) {
                if (psd2Nalog.getRemittanceInformationStructured().getReference().length() >= 4) {
                    nalogSepaDto.setModelOdobrenja(StringUtils.substring(psd2Nalog.getRemittanceInformationStructured().getReference(), 0, 4));
                    nalogSepaDto.setPozivNaBrojOdobrenja(StringUtils.substring(psd2Nalog.getRemittanceInformationStructured().getReference(), 4));
                } else {
                    nalogSepaDto.setModelOdobrenja(psd2Nalog.getRemittanceInformationStructured().getReference());
                }
            }
            //datum valute
            if (StringUtils.isNotBlank(psd2Nalog.getRequestedExecutionDate())) {
                try {
                    nalogSepaDto.getKnjizniNalog().setDatumValute(dateFormater.parse(psd2Nalog.getRequestedExecutionDate()));
                } catch (ParseException e) {
                    nalogSepaDto.setGreska(InternationalizationUtil.getLocalMessage("psd2_requestedExecutionDateParseError", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
                    nalogSepaDto.addExceptions(new AbitRuleException(nalogSepaDto.getGreska()));
                }
            } else {
                nalogSepaDto.getKnjizniNalog().setDatumValute(Calendar.getInstance().getTime());
            }
            // hitnost
            nalogSepaDto.setHitnost(resolveInstancyFromPsd2Nalog(psd2Nalog.getPaymentProduct()));
            validateNalogSepaDtoFromPsd2Nalog(nalogSepaDto);
        }
        return nalogSepaDto;
    }

    public static void defaultsForNalogSepaDtoFromPsd2Nalog(NalogSepaDto nalogSepaDto, Globals globals) {
        nalogSepaDto.setMedij(Bassx2Constants.PlatniPromet.PLATNI_NALOG_MEDIJ_PISP);
        nalogSepaDto.getKnjizniNalog().setSifraLikvidatora(globals.getSifraLikvidatora());
        nalogSepaDto.getKnjizniNalog().setSifraPoslovnice(globals.getSifraPoslovnice());
        nalogSepaDto.getKnjizniNalog().setSifraModula(globals.getSifraModula());
    }

    public static Integer resolveInstancyFromPsd2Nalog(String paymentProduct) {
        Integer hitnost = 0;
        if (StringUtils.equals(paymentProduct, PaymentProduct.TARGET2_PAYMENTS.getCode()) || StringUtils.equals(paymentProduct, PaymentProduct.PAIN_TARGET2_PAYMENTS.getCode()) ||
                StringUtils.equals(paymentProduct, PaymentProduct.HR_RTGS_PAYMENTS.getCode()) || StringUtils.equals(paymentProduct, PaymentProduct.PAIN_HR_RTGS_PAYMENTS.getCode())) {
            hitnost = 1;
        }
        if (StringUtils.equals(paymentProduct, PaymentProduct.HR_INSTANT_DOMESTIC_PAYMENTS.getCode()) || StringUtils.equals(paymentProduct, PaymentProduct.PAIN_HR_INSTANT_DOMESTIC_PAYMENTS.getCode())) {
             hitnost = GenericBassxConstants.PlatniPromet.PLATNI_NALOG_HITNOST_INSTANT;
        }
        return hitnost;
    }

    public static void validateNalogSepaDtoFromPsd2Nalog(NalogSepaDto nalogSepaDto) {
        //iban zaduzenja
        if (StringUtils.isNotBlank(nalogSepaDto.getIbanZaduzenja())) {
            if (!IbanFunctions.isIban(nalogSepaDto.getIbanZaduzenja())) {
                nalogSepaDto.setGreska(InternationalizationUtil.getLocalMessage("psd2_debtorIbanValidationError", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
                nalogSepaDto.addExceptions(new AbitRuleException(nalogSepaDto.getGreska()));
            }
        } else {
            nalogSepaDto.setGreska(InternationalizationUtil.getLocalMessage("psd2_debtorIbanEmpty", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
            nalogSepaDto.addExceptions(new AbitRuleException(nalogSepaDto.getGreska()));
        }
        //iznos
        if (nalogSepaDto.getIznos() != null) {
            if (getNumberOfDecimalPlaces(nalogSepaDto.getIznos()) > 2) {
                nalogSepaDto.setGreska(InternationalizationUtil.getLocalMessage("psd2_amountIllegalNumberOfDecimalPlaces", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
                nalogSepaDto.addExceptions(new AbitRuleException(nalogSepaDto.getGreska()));
            }
        } else {
            nalogSepaDto.setGreska(InternationalizationUtil.getLocalMessage("psd2_amountEmpty", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
            nalogSepaDto.addExceptions(new AbitRuleException(nalogSepaDto.getGreska()));
        }
        //iban odobrenja
        if (StringUtils.isNotBlank(nalogSepaDto.getIbanOdobrenja())) {
            if (!IbanFunctions.isIban(nalogSepaDto.getIbanOdobrenja())) {
                nalogSepaDto.setGreska(InternationalizationUtil.getLocalMessage("psd2_creditorIbanValidationError", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
                nalogSepaDto.addExceptions(new AbitRuleException(nalogSepaDto.getGreska()));
            }
        } else {
            nalogSepaDto.setGreska(InternationalizationUtil.getLocalMessage("psd2_creditorIbanEmpty", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
            nalogSepaDto.addExceptions(new AbitRuleException(nalogSepaDto.getGreska()));
        }
        //svrha naloga
        if (StringUtils.isNotBlank(nalogSepaDto.getKnjizniNalog().getSvrha())) {
            if (nalogSepaDto.getKnjizniNalog().getSvrha().length() > Psd2Constants.REMITTANCE_INFO_MAX_LENGTH_IP) {
                nalogSepaDto.setGreska(InternationalizationUtil.getLocalMessage("psd2_purposeLengthError", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
                nalogSepaDto.addExceptions(new AbitRuleException(nalogSepaDto.getGreska()));
            }
            //if (!AuthenticationUtils.isValidAlfaNumeric(nalogSepaDto.getKnjizniNalog().getSvrha())) {
            if (true) { // TODO Ivana - dodati pravi uvjet
                nalogSepaDto.setGreska(InternationalizationUtil.getLocalMessage("psd2_illegal_characters", new String[]{"RemittanceInformationUnstructured"}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
                nalogSepaDto.addExceptions(new AbitRuleException(nalogSepaDto.getGreska()));
            }
        } else {
            nalogSepaDto.setGreska(InternationalizationUtil.getLocalMessage("psd2_purposeEmpty", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
            nalogSepaDto.addExceptions(new AbitRuleException(nalogSepaDto.getGreska()));
        }
        // SWIFT BIC nalogodavatelja/korisnika
        if (StringUtils.isNotBlank(nalogSepaDto.getSwiftBicNalogodavateljaKorisnika())) {
            if (StringUtils.length(nalogSepaDto.getSwiftBicNalogodavateljaKorisnika()) < 6) {
                nalogSepaDto.setGreska(InternationalizationUtil.getLocalMessage("psd2_swiftBicLength", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
                nalogSepaDto.addExceptions(new AbitRuleException(nalogSepaDto.getGreska()));
            }
        }
        if (StringUtils.isNotBlank(nalogSepaDto.getSwiftBicNalogodavateljaKorisnikaNaziv())) {
            if (StringUtils.length(nalogSepaDto.getSwiftBicNalogodavateljaKorisnikaNaziv()) > Psd2Constants.REMITTANCE_INFO_MAX_LENGTH_IP) {
                nalogSepaDto.setGreska(InternationalizationUtil.getLocalMessage("psd2_swiftBicNameLength", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
                nalogSepaDto.addExceptions(new AbitRuleException(nalogSepaDto.getGreska()));
            }
        }
        //datum valute
        if (nalogSepaDto.getKnjizniNalog().getDatumValute() != null) {
            LocalDate datumValute = nalogSepaDto.getKnjizniNalog().getDatumValute().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (datumValute.isBefore(LocalDate.now())) {
                nalogSepaDto.setGreska(InternationalizationUtil.getLocalMessage("psd2_requestedExecutionDateError", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
                nalogSepaDto.addExceptions(new AbitRuleException(nalogSepaDto.getGreska()));
            }
        } else {
            nalogSepaDto.setGreska(InternationalizationUtil.getLocalMessage("psd2_requestedExecutionDateEmpty", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
            nalogSepaDto.addExceptions(new AbitRuleException(nalogSepaDto.getGreska()));
        }
        //troškovna opcija
        if (StringUtils.isNotBlank(nalogSepaDto.getTrosakInoBanke())) {
            if (!Psd2Constants.getTroskoviInoBanke().contains(nalogSepaDto.getTrosakInoBanke())) {
                nalogSepaDto.setGreska(InternationalizationUtil.getLocalMessage("psd2_detailsOfChargesError", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
                nalogSepaDto.addExceptions(new AbitRuleException(nalogSepaDto.getGreska()));
            }
        } else {
            nalogSepaDto.setGreska(InternationalizationUtil.getLocalMessage("psd2_detailsOfChargesEmpty", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
            nalogSepaDto.addExceptions(new AbitRuleException(nalogSepaDto.getGreska()));
        }
        // medij
        if (StringUtils.isBlank(nalogSepaDto.getMedij())) {
            nalogSepaDto.setGreska(InternationalizationUtil.getLocalMessage("psd2_medijEmpty", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
            nalogSepaDto.addExceptions(new AbitRuleException(nalogSepaDto.getGreska()));
        }
    }

    public static NalogSepaDto formNalogSepaDtoFromTppNalog(TppNalogPlatniDto tppNalog, Globals globals) {
        NalogSepaDto nalogSepaDto = null;
        if (tppNalog != null) {
            SimpleDateFormat dateFormater = new SimpleDateFormat(Psd2Constants.DATE_PSD2_FORMAT);
            dateFormater.setLenient(false);
            nalogSepaDto = new NalogSepaDto();
            //model i poziv na broj zaduzenja
            nalogSepaDto.setModelZaduzenja(tppNalog.getModelZaduzenja());
            nalogSepaDto.setPozivNaBrojZaduzenja(tppNalog.getPozivNaBrojZaduzenja());

            nalogSepaDto.setIbanZaduzenja(tppNalog.getIbanZaduzenja());
            nalogSepaDto.setNazivDebtor(tppNalog.getNazivDebtor());
            nalogSepaDto.setAdresaDebtor(tppNalog.getAdresaDebtor());
            nalogSepaDto.setGradDebtor(tppNalog.getGradDebtor());
            nalogSepaDto.setDrzavaDebtor(tppNalog.getDrzavaDebtor());
            //stvarni dužnik
            nalogSepaDto.setUltimateDebtorNaziv(tppNalog.getUltimateDebtorNaziv());
            nalogSepaDto.setUltimateDebtorId(tppNalog.getUltimateDebtorId());
            //iznos
            nalogSepaDto.setIznos(tppNalog.getIznos());
            //valuta
            nalogSepaDto.setSifraValute(tppNalog.getSifraValute()); //kasnije se kroz validaciju mijenja iz oznake u šifru
            nalogSepaDto.setOznakaValute(tppNalog.getOznakaValute());
            //iban odobrenja
            nalogSepaDto.setIbanOdobrenja(tppNalog.getIbanOdobrenja());
            //SWIFT BIC nalogodavatelja/korisnika
            nalogSepaDto.setSwiftBicNalogodavateljaKorisnika(tppNalog.getSwiftNalogodavateljaKorisnika());
            //SWIFT BIC nalogodavatelja/korisnika
            nalogSepaDto.setSwiftBicNalogodavateljaKorisnikaNaziv(tppNalog.getSwiftNalogodavateljaKorisnikaNaziv());
            //naziv primatelja
            nalogSepaDto.setNazivCreditor(tppNalog.getNazivCreditor());
            //id primatelja
            nalogSepaDto.setIdCreditor(tppNalog.getIdCreditor());
            //drzava
            nalogSepaDto.setDrzavaCreditor(tppNalog.getDrzavaCreditor());
            //grad
            nalogSepaDto.setGradCreditor(tppNalog.getGradCreditor());
            //adresa
            nalogSepaDto.setAdresaCreditor(tppNalog.getAdresaCreditor());
            //Krajnji primatelj
            nalogSepaDto.setUltimateCreditorNaziv(tppNalog.getUltimateCreditorNaziv());
            nalogSepaDto.setUltimateCreditorId(tppNalog.getUltimateCreditorId());
            //Šifra namjene
            nalogSepaDto.setSifraNamjene(tppNalog.getSifraNamjene());
            //troškovna opcija
            nalogSepaDto.setTrosakInoBanke(tppNalog.getTrosakInoBanke());
            //svha naloga
            nalogSepaDto.getKnjizniNalog().setSvrha(tppNalog.getSvrha());
            //model i poziv na broj odobrenja
            nalogSepaDto.setModelOdobrenja(tppNalog.getModelOdobrenja());
            nalogSepaDto.setPozivNaBrojOdobrenja(tppNalog.getPozivNaBrojOdobrenja());
            //datum valute
            nalogSepaDto.getKnjizniNalog().setDatumValute(tppNalog.getDatumValute());
            //medij Q
            nalogSepaDto.setMedij(Bassx2Constants.PlatniPromet.PLATNI_NALOG_MEDIJ_PISP);
            nalogSepaDto.getKnjizniNalog().setSifraLikvidatora(globals.getSifraLikvidatora());
            nalogSepaDto.getKnjizniNalog().setSifraPoslovnice(globals.getSifraPoslovnice());
            nalogSepaDto.getKnjizniNalog().setSifraModula(globals.getSifraModula());
            nalogSepaDto.setHitnost(tppNalog.getHitnost());

        }
        return nalogSepaDto;
    }

//    public static List<NalogSepaDto> formNalogSepaDtoFromPsd2NalogList(List<SinglePaymentDto> psd2NalogList, Globals globals) {
//        List<NalogSepaDto> resultList = null;
//        if (psd2NalogList != null && !psd2NalogList.isEmpty()) {
//            resultList = new ArrayList<>();
//            for (SinglePaymentDto psd2Nalog : psd2NalogList) {
//                NalogSepaDto nalogSepaDto = formNalogSepaDtoFromPsd2Nalog(psd2Nalog, globals);
//                resultList.add(nalogSepaDto);
//            }
//        }
//        return resultList;
//    }


    public static List<TppNalogPlatniDto> formTppNalogFromSepaNalog(List<NalogSepaDto> listaSepaNaloga, SinglePaymentDto psd2Nalog) {
        List<TppNalogPlatniDto> resultList = null;
        if (listaSepaNaloga != null && !listaSepaNaloga.isEmpty()) {
            resultList = new ArrayList<>();
            for (NalogSepaDto nalogSepaDto : listaSepaNaloga) {
                TppNalogPlatniDto tppNalogPlatniDto = TppNalogPlatniDto.builder().build();
                tppNalogPlatniDto = Psd2Util.formTppNalogFromSepa(tppNalogPlatniDto, nalogSepaDto, psd2Nalog);
                //tppNalogPlatniDto.setTypeOfTransaction("TODO");
                resultList.add(tppNalogPlatniDto);
            }
        }
        return resultList;
    }

    public static List<TppNalogPlatniDto> formTppNalogFromSepaNalog(List<NalogSepaDto> listaSepaNaloga, TppNalogPlatniDto tppOrigin) {
        List<TppNalogPlatniDto> resultList = null;
        if (listaSepaNaloga != null && !listaSepaNaloga.isEmpty()) {
            resultList = new ArrayList<>();
            for (NalogSepaDto nalogSepaDto : listaSepaNaloga) {
                TppNalogPlatniDto tppNalogPlatniDto = TppNalogPlatniDto.builder().build();
                tppNalogPlatniDto = Psd2Util.formTppNalogFromSepa(tppNalogPlatniDto, nalogSepaDto, tppOrigin);
                //tppNalogPlatniDto.setTypeOfTransaction("TODO");
                resultList.add(tppNalogPlatniDto);
            }
        }
        return resultList;
    }

    public static TppNalogPlatniDto formTppNalogFromSepa(TppNalogPlatniDto tppNalogPlatniDto, NalogSepaDto nalogSepaDto, Object addData) {
        TppNalogPlatniDto originalDto = null;
        if (addData != null) {
            if (addData instanceof TppNalogPlatniDto) {
                originalDto =null;// (TppNalogPlatniDto) ((TppNalogPlatniDto) addData).klone();
            }
        }

        if (tppNalogPlatniDto == null) tppNalogPlatniDto = TppNalogPlatniDto.builder().build();
        tppNalogPlatniDto.setStatusTpp(TransactionStatus.ACTC.toString()); //TODO funkcija koja će mapirati naše statuse u PSD2 statuse
        tppNalogPlatniDto.setIznos(nalogSepaDto.getIznos());
        tppNalogPlatniDto.setSifraValute(nalogSepaDto.getSifraValute());
        tppNalogPlatniDto.setOznakaValute(nalogSepaDto.getOznakaValute());
        tppNalogPlatniDto.setSifraValutePokrica(nalogSepaDto.getSifraValutePokrica());
        tppNalogPlatniDto.setIbanZaduzenja(nalogSepaDto.getIbanZaduzenja());
        tppNalogPlatniDto.setNazivDebtor(nalogSepaDto.getNazivDebtor());
        tppNalogPlatniDto.setAdresaDebtor(nalogSepaDto.getAdresaDebtor());
        tppNalogPlatniDto.setGradDebtor(nalogSepaDto.getGradDebtor());
        tppNalogPlatniDto.setDrzavaDebtor(nalogSepaDto.getDrzavaDebtor());
        tppNalogPlatniDto.setIdDebtor(nalogSepaDto.getNazivDebtor());
        tppNalogPlatniDto.setUltimateDebtorNaziv(nalogSepaDto.getUltimateDebtorNaziv());
        tppNalogPlatniDto.setUltimateDebtorId(nalogSepaDto.getUltimateDebtorId());
        tppNalogPlatniDto.setIbanOdobrenja(nalogSepaDto.getIbanOdobrenja());
        tppNalogPlatniDto.setNazivCreditor(nalogSepaDto.getNazivCreditor());
        tppNalogPlatniDto.setAdresaCreditor(nalogSepaDto.getAdresaCreditor());
        tppNalogPlatniDto.setGradCreditor(nalogSepaDto.getGradCreditor());
        tppNalogPlatniDto.setDrzavaCreditor(nalogSepaDto.getDrzavaCreditor());
        tppNalogPlatniDto.setIdCreditor(nalogSepaDto.getIdCreditor());
        tppNalogPlatniDto.setUltimateCreditorNaziv(nalogSepaDto.getUltimateCreditorNaziv());
        tppNalogPlatniDto.setUltimateCreditorId(nalogSepaDto.getUltimateCreditorId());
        tppNalogPlatniDto.setModelZaduzenja(nalogSepaDto.getModelZaduzenja());
        tppNalogPlatniDto.setPozivNaBrojZaduzenja(nalogSepaDto.getPozivNaBrojZaduzenja());
        tppNalogPlatniDto.setModelOdobrenja(nalogSepaDto.getModelOdobrenja());
        tppNalogPlatniDto.setPozivNaBrojOdobrenja(nalogSepaDto.getPozivNaBrojOdobrenja());
        tppNalogPlatniDto.setSmjer(nalogSepaDto.getSmjer());
        tppNalogPlatniDto.setReferenca(nalogSepaDto.getReferenca());
        tppNalogPlatniDto.setGreska(nalogSepaDto.getGreska());
        tppNalogPlatniDto.setSifraKnjiznogNaloga(nalogSepaDto.getKnjizniNalog().getSifra());
        tppNalogPlatniDto.setSwiftNalogodavateljaKorisnika(nalogSepaDto.getSwiftBicNalogodavateljaKorisnika());
        tppNalogPlatniDto.setSwiftNalogodavateljaKorisnikaNaziv(nalogSepaDto.getSwiftBicNalogodavateljaKorisnikaNaziv());
        tppNalogPlatniDto.setTrosakInoBanke(nalogSepaDto.getTrosakInoBanke());
        tppNalogPlatniDto.setSifraNamjene(nalogSepaDto.getSifraNamjene());
        tppNalogPlatniDto.setKategorijaNamjene(nalogSepaDto.getKategorijaNamjene());
        tppNalogPlatniDto.setSifraKategorijaNamjene(nalogSepaDto.getSifraKategorijaNamjene());
        tppNalogPlatniDto.setSifraOpisaPlacanja(nalogSepaDto.getSifraOpisaPlacanja());
        tppNalogPlatniDto.setDatumValute(nalogSepaDto.getKnjizniNalog().getDatumValute());
        tppNalogPlatniDto.setDatumKnjizenja(nalogSepaDto.getKnjizniNalog().getDatumKnjizenja());
        tppNalogPlatniDto.setSvrha(nalogSepaDto.getSvrha());
        tppNalogPlatniDto.setNacinIzvrsenja(nalogSepaDto.getNacinIzvrsenja());
        tppNalogPlatniDto.setSifraTipaNaloga(nalogSepaDto.getSifraTipaNaloga());
        tppNalogPlatniDto.setSifraPrethodnogNaloga(nalogSepaDto.getSifraPrethodnogNaloga());
        tppNalogPlatniDto.setSifraPlatnogNaloga(nalogSepaDto.getSifra());
        tppNalogPlatniDto.setSifraNalogaStorna(nalogSepaDto.getSifraNalogaStorna());
        tppNalogPlatniDto.setIznosNaknade(nalogSepaDto.getIznosNaknade());
        tppNalogPlatniDto.setHitnost(nalogSepaDto.getHitnost());
        tppNalogPlatniDto.setSifraTipaNaloga(nalogSepaDto.getKnjizniNalog().getSifraTipaNaloga());
        tppNalogPlatniDto.setSifraKlaseNaloga(nalogSepaDto.getKnjizniNalog().getSifraKlaseNaloga());
        tppNalogPlatniDto.setStatus(nalogSepaDto.getStatus());
        //devizni odljev
        if (StringUtils.isNotBlank(nalogSepaDto.getNacinIzvrsenja()) && StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_SWIFT_NALOG) &&
                StringUtils.isNotBlank(nalogSepaDto.getSmjer()) && StringUtils.equals(nalogSepaDto.getSmjer(), Bassx2Constants.Core.DUGOVNA_STRANA)) {
            //pretvaranje partije u IBAN
            if (StringUtils.isNotBlank(tppNalogPlatniDto.getIbanZaduzenja()))
                //tppNalogPlatniDto.setIbanZaduzenja(DeviznoUtil.resolveHrIbanForPartija(tppNalogPlatniDto.getIbanZaduzenja()));
                tppNalogPlatniDto.setIbanZaduzenja(null); // TODO Ivana
            if (StringUtils.isNotBlank(tppNalogPlatniDto.getIbanOdobrenja()))
                //tppNalogPlatniDto.setIbanOdobrenja(DeviznoUtil.resolveHrIbanForPartija(tppNalogPlatniDto.getIbanOdobrenja()));
                tppNalogPlatniDto.setIbanOdobrenja(null); // TODO Ivana
            //model, poziv na broj
            if (addData != null) {
                if (addData instanceof TppNalogPlatniDto) {
                    if (StringUtils.isNotBlank(originalDto.getModelZaduzenja()) && (StringUtils.isBlank(tppNalogPlatniDto.getModelZaduzenja()))) {
                        tppNalogPlatniDto.setModelZaduzenja(originalDto.getModelZaduzenja());
                    }
                    if (StringUtils.isNotBlank(originalDto.getPozivNaBrojZaduzenja()) && (StringUtils.isBlank(tppNalogPlatniDto.getPozivNaBrojZaduzenja()))) {
                        tppNalogPlatniDto.setPozivNaBrojZaduzenja(originalDto.getPozivNaBrojZaduzenja());
                    }
                    if (StringUtils.isNotBlank(originalDto.getModelOdobrenja()) && (StringUtils.isBlank(tppNalogPlatniDto.getModelOdobrenja()))) {
                        tppNalogPlatniDto.setModelOdobrenja(originalDto.getModelOdobrenja());
                    }
                    if (StringUtils.isNotBlank(originalDto.getPozivNaBrojOdobrenja()) && (StringUtils.isBlank(tppNalogPlatniDto.getPozivNaBrojOdobrenja()))) {
                        tppNalogPlatniDto.setPozivNaBrojOdobrenja(originalDto.getPozivNaBrojOdobrenja());
                    }
                }
            }

        }
        if (addData != null) {
            if (addData instanceof TppNalogPlatniDto) {
                TppNalogPlatniDto tNalog = (TppNalogPlatniDto) addData;
                tppNalogPlatniDto.setTppId(tNalog.getTppId());
                tppNalogPlatniDto.setxRequestId(tNalog.getxRequestId());
                tppNalogPlatniDto.setContentType(tNalog.getContentType());
                tppNalogPlatniDto.setPaymentProduct(tNalog.getPaymentProduct());
                tppNalogPlatniDto.setPaymentService(tNalog.getPaymentService());
                tppNalogPlatniDto.setPsuIpAddress(tNalog.getPsuIpAddress());
                tppNalogPlatniDto.setTppRedirectUri(tNalog.getTppRedirectUri());
                tppNalogPlatniDto.setVrijemeUpisaNaloga(tNalog.getVrijemeUpisaNaloga());
                tppNalogPlatniDto.setBatchBooking(tNalog.getBatchBooking());
                tppNalogPlatniDto.setSifraZbirnogBatchBookingNaloga(tNalog.getSifraZbirnogBatchBookingNaloga());
            }
            if (addData instanceof SinglePaymentDto) {
                SinglePaymentDto sPayment = (SinglePaymentDto) addData;
                tppNalogPlatniDto.setTppId(sPayment.getTppId());
                tppNalogPlatniDto.setxRequestId(sPayment.getxRequestId());
                tppNalogPlatniDto.setContentType(sPayment.getContentType());
                tppNalogPlatniDto.setPaymentProduct(sPayment.getPaymentProduct());
                tppNalogPlatniDto.setPaymentService(sPayment.getPaymentService());
                tppNalogPlatniDto.setPsuIpAddress(sPayment.getPsuIpAddress());
                tppNalogPlatniDto.setTppRedirectUri(sPayment.getTppRedirectUri());
                //datum valute - requestedExecutionDate
                if (StringUtils.isNotBlank(sPayment.getRequestedExecutionDate())) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate datumValuteInput = LocalDate.parse(sPayment.getRequestedExecutionDate(), formatter);
                    LocalDate datumValute = nalogSepaDto.getKnjizniNalog().getDatumValute().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    if (datumValuteInput.isBefore(datumValute)) {
                        tppNalogPlatniDto.setStatusTpp(TransactionStatus.ACWC.toString());
                    }
                }
            }
        }


        return tppNalogPlatniDto;
    }


    public static Links formLinksPaymentInitiation(AuthorizationDto authorizationDto, TppNalogPlatniDto tppNalogPlatniDto) throws AbitRuleException {
        Links links = new Links();
        Href href = new Href();
        //SELF
        href.setHref("/v1/" + tppNalogPlatniDto.getPaymentService() + "/" + tppNalogPlatniDto.getPaymentProduct() + "/" + tppNalogPlatniDto.getPaymentRequestId());
        links.setSelf(href);
        //STATUS
        href = new Href();
        href.setHref("/v1/" + tppNalogPlatniDto.getPaymentService() + "/" + tppNalogPlatniDto.getPaymentProduct() + "/" + tppNalogPlatniDto.getPaymentRequestId() + "/status");
        links.setStatus(href);
        if (authorizationDto != null) {
            //SCA REDIRECT
            href = new Href();
            href.setHref(Psd2Util.formScaRedirectLink(authorizationDto.getLinkAuth(), Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), Psd2Constants.AuthorizationType.CREATED.getValue()));
            links.setScaRedirect(href);
            //SCA STATUS
            href = new Href();
            href.setHref("/v1/" + tppNalogPlatniDto.getPaymentService() + "/" + tppNalogPlatniDto.getPaymentProduct() + "/" + tppNalogPlatniDto.getPaymentRequestId() + "/authorisations/" + authorizationDto.getIdAuth());
            links.setScaStatus(href);
        } else {
            //START AUTHORIZATION
            href = new Href();
            href.setHref("/v1/" + tppNalogPlatniDto.getPaymentService() + "/" + tppNalogPlatniDto.getPaymentProduct() + "/" + tppNalogPlatniDto.getPaymentRequestId() + "/authorisations");
            links.setStartAuthorisation(href);
        }
        return links;
    }

    public static Links formLinksExchangePaymentInitiation(AuthorizationDto authorizationDto, TppNalogPlatniDto tppNalogPlatniDto) throws AbitRuleException {
        Links links = new Links();
        Href href = new Href();
        //SELF
        href.setHref("/v1/" + tppNalogPlatniDto.getPaymentService() + "/" + tppNalogPlatniDto.getPaymentProduct() + "/" + tppNalogPlatniDto.getSifraTppNaloga());
        links.setSelf(href);
        //STATUS
        href = new Href();
        href.setHref("/v1/" + tppNalogPlatniDto.getPaymentService() + "/" + tppNalogPlatniDto.getPaymentProduct() + "/" + tppNalogPlatniDto.getSifraTppNaloga() + "/status");
        links.setStatus(href);
        if (authorizationDto != null) {
            //SCA REDIRECT
            href = new Href();
            href.setHref(Psd2Util.formScaRedirectLink(authorizationDto.getLinkAuth(), Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), Psd2Constants.AuthorizationType.CREATED.getValue(), Boolean.FALSE, Boolean.TRUE));
            links.setScaRedirect(href);
            //SCA STATUS
            href = new Href();
            href.setHref("/v1/" + tppNalogPlatniDto.getPaymentService() + "/" + tppNalogPlatniDto.getPaymentProduct() + "/" + tppNalogPlatniDto.getSifraTppNaloga() + "/authorisations/" + authorizationDto.getIdAuth());
            links.setScaStatus(href);
        } else {
            //START AUTHORIZATION
            href = new Href();
            href.setHref("/v1/" + tppNalogPlatniDto.getPaymentService() + "/" + tppNalogPlatniDto.getPaymentProduct() + "/" + tppNalogPlatniDto.getSifraTppNaloga() + "/authorisations");
            links.setStartAuthorisation(href);
        }
        return links;
    }

    public static Links formLinksPaymentInitiation(String authLink, SinglePaymentDto header, Integer sifraDatoteke) throws AbitRuleException {
        Links links = new Links();
        Href href = new Href();
        //SELF
        href.setHref("/v1/" + header.getPaymentService() + "/" + header.getPaymentProduct() + "/" + sifraDatoteke.intValue());
        links.setSelf(href);
        //STATUS
        href = new Href();
        href.setHref("/v1/" + header.getPaymentService() + "/" + header.getPaymentProduct() + "/" + sifraDatoteke.intValue() + "/status");
        links.setStatus(href);
        if (authLink != null) {
            //SCA REDIRECT
            href = new Href();
            href.setHref(Psd2Util.formScaRedirectLink(authLink, Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), Psd2Constants.AuthorizationType.CREATED.getValue(), true, false));
            links.setScaRedirect(href);
            //SCA STATUS
            href = new Href();
            //href.setHref("/v1/" + header.getPaymentService() + "/" + header.getPaymentProduct() + "/" + sifraDatoteke + "/authorisations/" + authorizationDto.getIdAuth());
            href.setHref("/v1/" + header.getPaymentService() + "/" + header.getPaymentProduct() + "/" + sifraDatoteke.intValue() + "/authorisations/" + sifraDatoteke.intValue());
            links.setScaStatus(href);
        } else {
            //START AUTHORIZATION
            href = new Href();
            href.setHref("/v1/" + header.getPaymentService() + "/" + header.getPaymentProduct() + "/" + sifraDatoteke.intValue() + "/authorisations");
            links.setStartAuthorisation(href);
        }
        return links;
    }

    public static Links formLinksConsent(AuthorizationDto authorizationDto, ConsentAisDto consentAisDto) throws AbitRuleException {
        Links links = new Links();
        Href href = new Href();
        //SELF
        href.setHref("/v1/consents/" + consentAisDto.getSifra());
        links.setSelf(href);
        //STATUS
        href = new Href();
        href.setHref("/v1/consents/" + consentAisDto.getSifra() + "/status");
        links.setStatus(href);
        if (authorizationDto != null) {
            //SCA REDIRECT
            href = new Href();
            href.setHref(Psd2Util.formScaRedirectLink(authorizationDto.getLinkAuth(), Psd2Constants.AuthorizationObjectType.CONSENT_AUTHORIZATION.getValue(), Psd2Constants.AuthorizationType.CREATED.getValue()));
            links.setScaRedirect(href);
            //SCA STATUS
            href = new Href();
            href.setHref("/v1/consents/" + consentAisDto.getSifra() + "/authorisations/" + authorizationDto.getIdAuth());
            links.setScaStatus(href);
        } else {
            //START AUTHORIZATION
            href = new Href();
            href.setHref("/v1/consents/" + consentAisDto.getSifra() + "/authorisations");
            links.setStartAuthorisation(href);
        }
        return links;
    }

    public static Links formLinksConsentCof(AuthorizationDto authorizationDto, ConsentCofDto consentCofDto) throws AbitRuleException {
        Links links = new Links();
        Href href = new Href();
        //SELF
        href.setHref("v2/consents/confirmation-of-funds/" + consentCofDto.getSifra());
        links.setSelf(href);
        //STATUS
        href = new Href();
        href.setHref("v2/consents/confirmation-of-funds/" + consentCofDto.getSifra() + "/status");
        links.setStatus(href);
        if (authorizationDto != null) {
            //SCA REDIRECT
            href = new Href();
            href.setHref(Psd2Util.formScaRedirectLink(authorizationDto.getLinkAuth(), Psd2Constants.AuthorizationObjectType.CONSENT_COF_AUTHORUZATION.getValue(), Psd2Constants.AuthorizationType.CREATED.getValue()));
            links.setScaRedirect(href);
            //SCA STATUS
            href = new Href();
            href.setHref("v2/consents/confirmation-of-funds/" + consentCofDto.getSifra() + "/authorisations/" + authorizationDto.getIdAuth());
            links.setScaStatus(href);
        } else {
            //START AUTHORIZATION
            href = new Href();
            href.setHref("v2/consents/confirmation-of-funds/" + consentCofDto.getSifra() + "/authorisations");
            links.setStartAuthorisation(href);
        }
        return links;
    }

    public static Links formLinksBasket(AuthorizationDto authorizationDto, BasketDto basketDto) throws AbitRuleException {
        Links links = new Links();
        Href href = new Href();
        //SELF
        href.setHref("/v1/signing-baskets/" + basketDto.getSifra());
        links.setSelf(href);
        if (authorizationDto != null) {
            //SCA REDIRECT
            href = new Href();
            href.setHref(Psd2Util.formScaRedirectLink(authorizationDto.getLinkAuth(), Psd2Constants.AuthorizationObjectType.BASKET_AUTHORIZATION.getValue(), Psd2Constants.AuthorizationType.CREATED.getValue()));
            links.setScaRedirect(href);
            //SCA STATUS
            href = new Href();
            href.setHref("/v1/singing-baskets/" + basketDto.getSifra() + "/authorisations/" + authorizationDto.getIdAuth());
            links.setScaStatus(href);
        } else {
            //START AUTHORIZATION
            href = new Href();
            href.setHref("/v1/singing-baskets/" + basketDto.getSifra() + "/authorisations");
            links.setStartAuthorisation(href);
        }
        return links;
    }

    public static Links formLinksPaymentCancellation(TppNalogPlatniDto tppNalogPlatniDto) {
        Links links = new Links();
        Href href = new Href();
        //SELF
        href.setHref("/v1/" + tppNalogPlatniDto.getPaymentService() + "/" + tppNalogPlatniDto.getPaymentProduct() + "/" + tppNalogPlatniDto.getSifraTppNaloga());
        links.setSelf(href);
        //STATUS
        href = new Href();
        href.setHref("/v1/" + tppNalogPlatniDto.getPaymentService() + "/" + tppNalogPlatniDto.getPaymentProduct() + "/" + tppNalogPlatniDto.getSifraTppNaloga() + "/status");
        links.setStatus(href);
        //START AUTHORIZATION
        href = new Href();
        href.setHref("/v1/" + tppNalogPlatniDto.getPaymentService() + "/" + tppNalogPlatniDto.getPaymentProduct() + "/" + tppNalogPlatniDto.getSifraTppNaloga() + "/cancellation-authorisations");
        links.setStartAuthorisation(href);

        return links;
    }

    public static Links formLinksPaymentBulkCancellation(TppNalogPlatniDto tppNalogPlatniDto, Integer datotekaId) {
        Links links = new Links();
        Href href = new Href();
        //SELF
        href.setHref("/v1/" + tppNalogPlatniDto.getPaymentService() + "/" + tppNalogPlatniDto.getPaymentProduct() + "/" + datotekaId);
        links.setSelf(href);
        //STATUS
        href = new Href();
        href.setHref("/v1/" + tppNalogPlatniDto.getPaymentService() + "/" + tppNalogPlatniDto.getPaymentProduct() + "/" + datotekaId + "/status");
        links.setStatus(href);
        //START AUTHORIZATION
        href = new Href();
        href.setHref("/v1/" + tppNalogPlatniDto.getPaymentService() + "/" + tppNalogPlatniDto.getPaymentProduct() + "/" + datotekaId + "/cancellation-authorisations");
        links.setStartAuthorisation(href);

        return links;
    }

    public static Links formLinksPaymentAuthorization(AuthorizationDto authorizationDto, TppNalogPlatniDto paymentDto) throws AbitRuleException {
        Links links = new Links();
        Href href = new Href();
        //SELF
        href.setHref("/v1/" + paymentDto.getPaymentService() + "/" + paymentDto.getPaymentProduct() + "/" + paymentDto.getPaymentRequestId() + "/" + authorizationDto.getIdAuth());
        links.setSelf(href);
        //SCA REDIRECT
        href = new Href();
        if (authorizationDto.getAutTypeAuth().equals(Psd2Constants.AuthorizationType.CREATED.getValue())) {
            href.setHref(Psd2Util.formScaRedirectLink(authorizationDto.getLinkAuth(), Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), Psd2Constants.AuthorizationType.CREATED.getValue()));
        }
        if (authorizationDto.getAutTypeAuth().equals(Psd2Constants.AuthorizationType.CANCELLED.getValue())) {
            href.setHref(Psd2Util.formScaRedirectLink(authorizationDto.getLinkAuth(), Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), Psd2Constants.AuthorizationType.CANCELLED.getValue()));
        }
        links.setScaRedirect(href);
        //SCA STATUS
        href = new Href();
        if (authorizationDto.getAutTypeAuth().equals(Psd2Constants.AuthorizationType.CREATED.getValue())) {
            href.setHref("/v1/" + paymentDto.getPaymentService() + "/" + paymentDto.getPaymentProduct() + "/" + paymentDto.getPaymentRequestId() + "/authorisations/" + authorizationDto.getIdAuth());
        }
        if (authorizationDto.getAutTypeAuth().equals(Psd2Constants.AuthorizationType.CANCELLED.getValue())) {
            href.setHref("/v1/" + paymentDto.getPaymentService() + "/" + paymentDto.getPaymentProduct() + "/" + paymentDto.getPaymentRequestId() + "/cancellation-authorisations/" + authorizationDto.getIdAuth());
        }
        links.setScaStatus(href);

        return links;
    }

    public static Links formLinksBulkPaymentAuthorization(AuthorizationDto authorizationDto, Integer sifraDatoteke, String paymentProduct) throws AbitRuleException {
        Links links = new Links();
        Href href = new Href();
        //SELF
        href.setHref("/v1/" + PaymentServiceType.BULK.getValue() + "/" + paymentProduct + "/" + sifraDatoteke + "/" + sifraDatoteke);
        links.setSelf(href);
        //SCA REDIRECT
        href = new Href();
        if (authorizationDto.getAutTypeAuth().equals(Psd2Constants.AuthorizationType.CREATED.getValue())) {
            href.setHref(Psd2Util.formScaRedirectLink(authorizationDto.getLinkAuth(), Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), Psd2Constants.AuthorizationType.CREATED.getValue(), true, false));
        }
        if (authorizationDto.getAutTypeAuth().equals(Psd2Constants.AuthorizationType.CANCELLED.getValue())) {
            href.setHref(Psd2Util.formScaRedirectLink(authorizationDto.getLinkAuth(), Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), Psd2Constants.AuthorizationType.CANCELLED.getValue(), true, false));
        }
        links.setScaRedirect(href);
        //SCA STATUS
        href = new Href();
        if (authorizationDto.getAutTypeAuth().equals(Psd2Constants.AuthorizationType.CREATED.getValue())) {
            href.setHref("/v1/" + PaymentServiceType.BULK.getValue() + "/" + paymentProduct + "/" + sifraDatoteke + "/authorisations/" + sifraDatoteke);
        }
        if (authorizationDto.getAutTypeAuth().equals(Psd2Constants.AuthorizationType.CANCELLED.getValue())) {
            href.setHref("/v1/" + PaymentServiceType.BULK.getValue() + "/" + paymentProduct + "/" + sifraDatoteke + "/cancellation-authorisations/" + sifraDatoteke);
        }
        links.setScaStatus(href);

        return links;
    }

//    public static Links formAisReadAccountListLinks(Boolean withBalance, ConsentAccountAisDto consentAccountAisDto, AccountDto accountDto) {
//        Links links = null;
//        Href balanceHref = new Href();
//        Href transactionHref = new Href();
//        //form balance links
//        if (withBalance.equals(true)) {
//            // basically compare given account list with consent and form link if account has a consent
//            if (consentAccountAisDto.getSifraPar().equals(Integer.parseInt(accountDto.getResourceId()))
//                    && consentAccountAisDto.getTypeAcc().equals(ConsentTypeAccess.BALANCE.getValue())) {
//                links = new Links();
//                balanceHref.setHref("/v1/accounts/" + accountDto.getResourceId() + "/balances");
//                links.setBalances(balanceHref);
//            } else if (consentAccountAisDto.getSifraPar().equals(Integer.parseInt(accountDto.getResourceId()))
//                    && consentAccountAisDto.getTypeAcc().equals(ConsentTypeAccess.BALANCE.getValue())
//                    && consentAccountAisDto.getTypeAcc().equals(ConsentTypeAccess.TRANSACTION.getValue())) {
//                links = new Links();
//                balanceHref.setHref("/v1/accounts/" + accountDto.getResourceId() + "/balances");
//                links.setBalances(balanceHref);
//                transactionHref.setHref("/v1/accounts/" + accountDto.getResourceId() + "/transactions");
//                links.setTransactions(transactionHref);
//            }
//        } else {
//            if (consentAccountAisDto.getSifraPar().equals(Integer.parseInt(accountDto.getResourceId())) &&
//                    consentAccountAisDto.getTypeAcc().equals(ConsentTypeAccess.TRANSACTION.getValue())) {
//                links = new Links();
//                transactionHref.setHref("/v1/accounts/" + accountDto.getResourceId() + "/transactions");
//                links.setTransactions(transactionHref);
//            }
//        }
//        return links;
//    }

    public static Links formAccountInfServiceLinks(String accountId, String accountAccessType) {
        Links link = null;
        Href balanceHref = new Href();
        Href transactionHref = new Href();
        if (accountAccessType.equals(ConsentTypeAccess.BALANCE.getValue())) {
            link = new Links();
            balanceHref.setHref("/v1/accounts/" + accountId + "/balances");
            link.setBalances(balanceHref);

        } else if (accountAccessType.equals(ConsentTypeAccess.TRANSACTION.getValue())) {
            link = new Links();
            transactionHref.setHref("/v1/accounts/" + accountId + "/transactions");
            link.setTransactions(transactionHref);
        }
        return link;
    }

    public static Links formAisGetTransactionListLinks(String accountId) {
        Links link = new Links();
        Href accountDetails = new Href();
        accountDetails.setHref("/v1/accounts/" + accountId);
        link.setAccount(accountDetails);
        return link;
    }


    public static String formScaRedirectLink(String authorizationLink, Integer objectType, Integer authorizationType) throws AbitRuleException {
        return formScaRedirectLink(authorizationLink, objectType, authorizationType, false, false);
    }

    public static String formScaRedirectLink(String authorizationLink, Integer objectType, Integer authorizationType, boolean isBulkPayment, boolean isNalogKupoprodaje) throws AbitRuleException {
        String scaRedirectLink = null;
        try {
            if (StringUtils.isBlank(authorizationLink)) {
                return null;
            } else {
                //dohvat endpointa
                //String endpoint = LookupHelper.getICoreDaoProxyRemote().getVrijednostParametraBySifra(Psd2Constants.PARAMETAR_PSD2_REDIRECT_LINK); // stari
                String endpoint = appConfig.psd2RedirectLink(); //coreSrvc.getVrijednostParametraBySifra(Psd2Constants.PARAMETAR_PSD2_REDIRECT_LINK); // prazni

                if (objectType.equals(Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue())) {
                    if (authorizationType.equals(Psd2Constants.AuthorizationType.CREATED.getValue())) {
                        if (isBulkPayment) {
                            scaRedirectLink = endpoint + Psd2Constants.LINK_SCA_REDIRECT_SIGN_MULTI_CREATE_NALOG + authorizationLink;
                        } else if (isNalogKupoprodaje) {
                            scaRedirectLink = endpoint + Psd2Constants.LINK_SCA_REDIRECT_SIGN_CREATE_EXCHANGE_NALOG + authorizationLink;
                        } else {
                            scaRedirectLink = endpoint + Psd2Constants.LINK_SCA_REDIRECT_SIGN_CREATE_NALOG + authorizationLink;
                        }
                    } else if (authorizationType.equals(Psd2Constants.AuthorizationType.CANCELLED.getValue())) {
                        scaRedirectLink = endpoint + (isBulkPayment ? Psd2Constants.LINK_SCA_REDIRECT_SIGN_MULTI_CANCEL_NALOG : Psd2Constants.LINK_SCA_REDIRECT_SIGN_CANCEL_NALOG) + authorizationLink;
                    }
                } else if (objectType.equals(Psd2Constants.AuthorizationObjectType.CONSENT_AUTHORIZATION.getValue())) {
                    if (authorizationType.equals(Psd2Constants.AuthorizationType.CREATED.getValue())) {
                        scaRedirectLink = endpoint + Psd2Constants.LINK_SCA_REDIRECT_SIGN_CREATE_COSENT + authorizationLink;
                    } else if (authorizationType.equals(Psd2Constants.AuthorizationType.CANCELLED.getValue())) {
                        scaRedirectLink = endpoint + Psd2Constants.LINK_SCA_REDIRECT_SIGN_CANCEL_COSENT + authorizationLink;
                    }
                } else if (objectType.equals(Psd2Constants.AuthorizationObjectType.BASKET_AUTHORIZATION.getValue())) {
                    if (authorizationType.equals(Psd2Constants.AuthorizationType.CREATED.getValue())) {
                        scaRedirectLink = endpoint + Psd2Constants.LINK_SCA_REDIRECT_SIGN_CREATE_BASKET + authorizationLink;
                    }
                } else if (objectType.equals(Psd2Constants.AuthorizationObjectType.CONSENT_COF_AUTHORUZATION.getValue())) {
                    if (authorizationType.equals(Psd2Constants.AuthorizationType.CREATED.getValue())) {
                        scaRedirectLink = endpoint + Psd2Constants.LINK_SCA_REDIRECT_SIGN_CREATE_CONSENT_COF + authorizationLink;
                    } else if (authorizationType.equals(Psd2Constants.AuthorizationType.CANCELLED.getValue())) {
                        scaRedirectLink = endpoint + Psd2Constants.LINK_SCA_REDIRECT_SIGN_CANCEL_CONSENT_COF + authorizationLink;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new AbitRuleException("Greška kod formiranja SCA REDIRECT linka.");
        }
        return scaRedirectLink;
    }

    public static String formAutorizationScaRedirectLink(String sifraResursa) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String result = timestamp.getTime() + String.valueOf(sifraResursa) + RandomStringUtils.randomAlphanumeric(10);
        return result;
    }

    /**
     * @param nalogSepaDto
     * @return
     */
    public static boolean isSepaTransactionChanged(NalogSepaDto nalogSepaDto, SinglePaymentDto psd2Nalog) {
        boolean result = false;
        //EUR_NKS
        if (StringUtils.equals(psd2Nalog.getPaymentProduct(), PaymentProduct.SEPA_CREDIT_TANSFER.getCode()) || StringUtils.equals(psd2Nalog.getPaymentProduct(), PaymentProduct.PAIN_SEPA_CREDIT_TANSFER.getCode())) {
            if (!StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_EUR_NKS) && !StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_INTERNI_NALOG)
                    && !StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_DEVIZNI_INTERNI_NALOG)) {
                result = true;
            }
            if (StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_EUR_NKS) && !StringUtils.equals(nalogSepaDto.getSifraValute(), Bassx2Constants.Core.VALUTA_SIFRAVAL_EUR)) {
                result = true;
            }
            //TARGET2
        } else if (StringUtils.equals(psd2Nalog.getPaymentProduct(), PaymentProduct.TARGET2_PAYMENTS.getCode()) || StringUtils.equals(psd2Nalog.getPaymentProduct(), PaymentProduct.PAIN_TARGET2_PAYMENTS.getCode())) {
            if (!StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_TARGET2) && !StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_INTERNI_NALOG)
                    && !StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_DEVIZNI_INTERNI_NALOG)) {
                result = true;
            }
            if (StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_TARGET2) && !StringUtils.equals(nalogSepaDto.getSifraValute(), Bassx2Constants.Core.VALUTA_SIFRAVAL_EUR)) {
                result = true;
            }
            //NKS
        } else if (StringUtils.equals(psd2Nalog.getPaymentProduct(), PaymentProduct.HR_DOMESTIC_PAYMENTS.getCode()) || StringUtils.equals(psd2Nalog.getPaymentProduct(), PaymentProduct.PAIN_HR_DOMESTIC_PAYMENTS.getCode())) {
            if (!StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_HRK_NKS) && !StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_INTERNI_NALOG)
                    && !StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_DEVIZNI_INTERNI_NALOG)) {
                result = true;
            }
            if (StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_HRK_NKS) && !StringUtils.equals(nalogSepaDto.getSifraValute(), Bassx2Constants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY)) {
                result = true;
            }
            if (StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_HRK_NKS) && !StringUtils.startsWith(nalogSepaDto.getIbanOdobrenja(), "HR")) {
                result = true;
            }
            //HSVP
        } else if (StringUtils.equals(psd2Nalog.getPaymentProduct(), PaymentProduct.HR_RTGS_PAYMENTS.getCode()) || StringUtils.equals(psd2Nalog.getPaymentProduct(), PaymentProduct.PAIN_HR_RTGS_PAYMENTS.getCode())) {
            if (!StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_HSVP) && !StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_INTERNI_NALOG)
                    && !StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_DEVIZNI_INTERNI_NALOG)) {
                result = true;
            }
            if (StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_HSVP) && !StringUtils.equals(nalogSepaDto.getSifraValute(), Bassx2Constants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY)) {
                result = true;
            }
            if (StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_HSVP) && !StringUtils.startsWith(nalogSepaDto.getIbanOdobrenja(), "HR")) {
                result = true;
            }
            //SWIFT
        } else if (StringUtils.equals(psd2Nalog.getPaymentProduct(), PaymentProduct.CROSS_BORDER_CREDIT_TRANSFER.getCode()) || StringUtils.equals(psd2Nalog.getPaymentProduct(), PaymentProduct.PAIN_CROSS_BORDER_CREDIT_TRANSFER.getCode())) {
            if (!StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_SWIFT_NALOG) && !StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_INTERNI_NALOG)
                    && !StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_DEVIZNI_INTERNI_NALOG)) {
                result = true;
            }
        }
        return result;
    }

    public static boolean isSepaTransactionChanged(NalogSepaDto nalogSepaDto, TppNalogPlatniDto psd2Nalog) {
        boolean result = false;
        //EUR_NKS
        if (StringUtils.equals(psd2Nalog.getPaymentProduct(), PaymentProduct.SEPA_CREDIT_TANSFER.getCode()) || StringUtils.equals(psd2Nalog.getPaymentProduct(), PaymentProduct.PAIN_SEPA_CREDIT_TANSFER.getCode())) {
            if (!StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_EUR_NKS) && !StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_INTERNI_NALOG)
                    && !StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_DEVIZNI_INTERNI_NALOG)) {
                result = true;
            }
            if (StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_EUR_NKS) && !StringUtils.equals(nalogSepaDto.getSifraValute(), Bassx2Constants.Core.VALUTA_SIFRAVAL_EUR)) {
                result = true;
            }
            //TARGET2
        } else if (StringUtils.equals(psd2Nalog.getPaymentProduct(), PaymentProduct.TARGET2_PAYMENTS.getCode()) || StringUtils.equals(psd2Nalog.getPaymentProduct(), PaymentProduct.PAIN_TARGET2_PAYMENTS.getCode())) {
            if (!StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_TARGET2) && !StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_INTERNI_NALOG)
                    && !StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_DEVIZNI_INTERNI_NALOG)) {
                result = true;
            }
            if (StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_TARGET2) && !StringUtils.equals(nalogSepaDto.getSifraValute(), Bassx2Constants.Core.VALUTA_SIFRAVAL_EUR)) {
                result = true;
            }
            //NKS
        } else if (StringUtils.equals(psd2Nalog.getPaymentProduct(), PaymentProduct.HR_DOMESTIC_PAYMENTS.getCode()) || StringUtils.equals(psd2Nalog.getPaymentProduct(), PaymentProduct.PAIN_HR_DOMESTIC_PAYMENTS.getCode())) {
            if (!StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_HRK_NKS) && !StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_INTERNI_NALOG)
                    && !StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_DEVIZNI_INTERNI_NALOG)) {
                result = true;
            }
            if (StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_HRK_NKS) && !StringUtils.equals(nalogSepaDto.getSifraValute(), Bassx2Constants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY)) {
                result = true;
            }
            if (StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_HRK_NKS) && !StringUtils.startsWith(nalogSepaDto.getIbanOdobrenja(), "HR")) {
                result = true;
            }
            //HSVP
        } else if (StringUtils.equals(psd2Nalog.getPaymentProduct(), PaymentProduct.HR_RTGS_PAYMENTS.getCode()) || StringUtils.equals(psd2Nalog.getPaymentProduct(), PaymentProduct.PAIN_HR_RTGS_PAYMENTS.getCode())) {
            if (!StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_HSVP) && !StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_INTERNI_NALOG)
                    && !StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_DEVIZNI_INTERNI_NALOG)) {
                result = true;
            }
            if (StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_HSVP) && !StringUtils.equals(nalogSepaDto.getSifraValute(), Bassx2Constants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY)) {
                result = true;
            }
            if (StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_HSVP) && !StringUtils.startsWith(nalogSepaDto.getIbanOdobrenja(), "HR")) {
                result = true;
            }
            //SWIFT
        } else if (StringUtils.equals(psd2Nalog.getPaymentProduct(), PaymentProduct.CROSS_BORDER_CREDIT_TRANSFER.getCode()) || StringUtils.equals(psd2Nalog.getPaymentProduct(), PaymentProduct.PAIN_CROSS_BORDER_CREDIT_TRANSFER.getCode())) {
            if (!StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_SWIFT_NALOG) && !StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_INTERNI_NALOG)
                    && !StringUtils.equals(nalogSepaDto.getNacinIzvrsenja(), Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_DEVIZNI_INTERNI_NALOG)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Metoda koja generira return path na temelju requesta
     *
     * @param request
     * @return path /psd2/dir/facess/desination.xhtml?id=id_naloga
     */
    public static String getReturnPath(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String context = request.getContextPath();
        String queryString = request.getQueryString();
        if (!StringUtils.isEmpty(context) && !StringUtils.isEmpty(uri)) {
            uri = uri.replace(context, "");
            uri = uri + "?faces-redirect=true";
        }
        if (!StringUtils.isEmpty(queryString)) {
            uri += "&" + queryString;
        }
        return uri;
    }

    public static boolean isRequestHeaderExist(String headerName) {
        if (headerName != null && headerName.length() > 0) {
            if (StringUtils.isNotBlank(headerName)) {
                return true;
            }
        }

        return false;
    }

    public static ConsentRequestResponseDto validateCreateConsentRequestBody(ConsentRequestResponseDto
                                                                                     consentRequestResponseDto) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(Psd2Constants.DATE_PSD2_FORMAT);
        dateFormat.setLenient(false);
        List<String> errorDescriptionList = new ArrayList<String>();
        consentRequestResponseDto.setIsValid(true);
        //recurring indicator
        if (consentRequestResponseDto.getRecurringIndicator() == null) {
            consentRequestResponseDto.setIsValid(false);
            errorDescriptionList.add("Nedostaje recurringIndicator parametar. RecurringIndicator parametar je obavezan");
            consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), errorDescriptionList));

        }
        //valid until
        if (StringUtils.isBlank(consentRequestResponseDto.getValidUntil())) {
            consentRequestResponseDto.setIsValid(false);
            errorDescriptionList.add("Nedostaje validUntil parametar. ValidUntil parametar je obavezan");
            consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), errorDescriptionList));
        } else {

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate dateValidUntil = LocalDate.parse(consentRequestResponseDto.getValidUntil(), formatter);
                if (dateValidUntil.isBefore(LocalDate.now())) {
                    consentRequestResponseDto.setIsValid(false);
                    errorDescriptionList.add("ValidUntil datum ne smije biti u prošlosti");
                    consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), errorDescriptionList));
                }
            } catch (DateTimeException e) {
                e.printStackTrace();
                consentRequestResponseDto.setIsValid(false);
                errorDescriptionList.add("ValidUntil parametar nije u ispravnom formatu, ispravan format je 'yyyy-MM-dd'");
                consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), errorDescriptionList));
            }
        }
        //frequency per day
        if (consentRequestResponseDto.getFrequencyPerDay() == null) {
            consentRequestResponseDto.setIsValid(false);
            errorDescriptionList.add("Nedostaje frequencyPerDay parametar. FrequencyPerDay parametar je obavezan");
            consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), errorDescriptionList));
        }
        //frequency per day cant be 0 or less
        if (consentRequestResponseDto.getFrequencyPerDay() <= 0) {
            consentRequestResponseDto.setIsValid(false);
            errorDescriptionList.add("Parametar frequencyPerDay mora biti veći od 0");
            consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), errorDescriptionList));
        }
        //frequency per day can't be more then frequency parametar set by ASPSP
        if (consentRequestResponseDto.getFrequencyPerDay() != null) {
            //get frequency param from DB..
            try {
                //Integer frequencyParam = Integer.valueOf(LookupHelper.getICoreDaoProxyRemote().getVrijednostParametraBySifra("TPP_FREQ")); // stari
                Integer frequencyParam = appConfig.tppFrequencyPerday(); // prazni
                if (consentRequestResponseDto.getFrequencyPerDay() > frequencyParam) {
                    consentRequestResponseDto.setIsValid(false);
                    errorDescriptionList.add("Parametar frequencyPerDay ne smije biti veći od " + frequencyParam);
                    consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), errorDescriptionList));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        }


        //frequency per day fro recurring indicator
        if (consentRequestResponseDto.getRecurringIndicator().equals(false) && consentRequestResponseDto.getFrequencyPerDay() > 1) {
            consentRequestResponseDto.setIsValid(false);
            errorDescriptionList.add("Neispravo zadani request --> ako je recurringIndicator = false, frequencyPerDay ne smije biti veći od 1");
            consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), errorDescriptionList));
        }

        //combined service indicator
        if (consentRequestResponseDto.getCombinedServiceIndicator() == null) {
            consentRequestResponseDto.setIsValid(false);
            errorDescriptionList.add("Nedostaje combinedServiceIndicatorAttribute parametar. CombinedServiceIndicatorAttribute parametar je obavezan");
            consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), errorDescriptionList));
        }
        //access
        if (consentRequestResponseDto.getAccess() == null) {
            consentRequestResponseDto.setIsValid(false);
            errorDescriptionList.add("Nedostaje access parametar. Access parametar je obavezan");
            consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), errorDescriptionList));
        } else {
            //accounts
            if (consentRequestResponseDto.getAccess().getAccounts() != null && !consentRequestResponseDto.getAccess().getAccounts().isEmpty()) {
                for (AccountReferenceDto accountReferenceDto : consentRequestResponseDto.getAccess().getAccounts()) {
                    if (StringUtils.isBlank(accountReferenceDto.getIban()) && StringUtils.isBlank(accountReferenceDto.getBban())) {
                        consentRequestResponseDto.setIsValid(false);
                        errorDescriptionList.add("Nedostaje iban u access parametru");
                        consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), errorDescriptionList));

                    }
                    if (StringUtils.isNotBlank(accountReferenceDto.getPan()) || StringUtils.isNotBlank(accountReferenceDto.getMaskedPan())) {
                        consentRequestResponseDto.setIsValid(false);
                        errorDescriptionList.add("Pan i maskedPan - nisu podržani");
                        consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.PARAMETER_NOT_SUPPORTED.getValue(), errorDescriptionList));
                    }
                }
            }
            //balances
            if (consentRequestResponseDto.getAccess().getBalances() != null && !consentRequestResponseDto.getAccess().getBalances().isEmpty()) {
                for (AccountReferenceDto accountReferenceDto : consentRequestResponseDto.getAccess().getBalances()) {
                    if (StringUtils.isBlank(accountReferenceDto.getIban()) && StringUtils.isBlank(accountReferenceDto.getBban())) {
                        consentRequestResponseDto.setIsValid(false);
                        errorDescriptionList.add("Nedostaje iban u access parametru");
                        consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), errorDescriptionList));
                    }
                    if (StringUtils.isNotBlank(accountReferenceDto.getPan()) || StringUtils.isNotBlank(accountReferenceDto.getMaskedPan())) {
                        consentRequestResponseDto.setIsValid(false);
                        errorDescriptionList.add("Pan i maskedPan - nisu podržani");
                        consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.PARAMETER_NOT_SUPPORTED.getValue(), errorDescriptionList));
                    }
                }
            }
            //transactions
            if (consentRequestResponseDto.getAccess().getTransactions() != null && !consentRequestResponseDto.getAccess().getTransactions().isEmpty()) {
                for (AccountReferenceDto accountReferenceDto : consentRequestResponseDto.getAccess().getTransactions()) {
                    if (StringUtils.isBlank(accountReferenceDto.getIban()) && StringUtils.isBlank(accountReferenceDto.getBban())) {
                        consentRequestResponseDto.setIsValid(false);
                        errorDescriptionList.add("Nedostaje iban u access parametru");
                        consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), errorDescriptionList));
                    }
                    if (StringUtils.isNotBlank(accountReferenceDto.getPan()) || StringUtils.isNotBlank(accountReferenceDto.getMaskedPan())) {
                        consentRequestResponseDto.setIsValid(false);
                        errorDescriptionList.add("Pan i maskedPan - nisu podržani");
                        consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.PARAMETER_NOT_SUPPORTED.getValue(), errorDescriptionList));
                    }
                }
            }
            //availableAccounts
            if (StringUtils.isNotBlank(consentRequestResponseDto.getAccess().getAvailableAccounts())) {
                consentRequestResponseDto.setIsValid(false);
                errorDescriptionList.add("AvailableAccounts - nije podržano"); //TODO - čeka se odgovor HUB-a
                consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.PARAMETER_NOT_SUPPORTED.getValue(), errorDescriptionList));

//                if (StringUtils.compare(consentRequestResponseDto.getAccess().getAvailableAccounts(), "allAccounts") != 0) {
//                    consentRequestResponseDto.setIsValid(false);
//                    errorDescriptionList.add("AvailableAccounts - not supported!");
//
//                }
            }
            //allPsd2
            if (StringUtils.isNotBlank(consentRequestResponseDto.getAccess().getAllPsd2())) {
                consentRequestResponseDto.setIsValid(false);
                errorDescriptionList.add("AllPsd2 - nije podržano"); ////TODO - čeka se odgovor HUB-a
                consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.PARAMETER_NOT_SUPPORTED.getValue(), errorDescriptionList));
//                if (StringUtils.compare(consentRequestResponseDto.getAccess().getAllPsd2(), "allAccounts") != 0) {
//                    consentRequestResponseDto.setIsValid(false);
//                    errorDescriptionList.add("AllPsd2 - not supported!");
//                    //TODO
//                }
            }
            //all access null
            if (consentRequestResponseDto.getAccess().getAccounts() == null && consentRequestResponseDto.getAccess().getBalances() == null && consentRequestResponseDto.getAccess().getTransactions() == null
                    && consentRequestResponseDto.getAccess().getAvailableAccounts() == null && consentRequestResponseDto.getAccess().getAllPsd2() == null) {
                consentRequestResponseDto.setIsValid(false);
                errorDescriptionList.add("Nedostaje account access (accounts, balances, transactions, availableAccounts, allPsd2");
                consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), errorDescriptionList));
            }
        }
//        netreba jer več u uvjetu punim ErrorInformationDto...

//        if (!consentRequestResponseDto.getIsValid()) {
//            consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.BAD_REQUEST.getValue(), errorDescriptionList));
//        }
        return consentRequestResponseDto;
    }

    public static boolean isValidDateFormat(String value, String format) {
        DateFormat ft = new SimpleDateFormat(format);
        ft.setLenient(false);
        try {
            ft.parse(value.trim());

        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static ConsentCofRequestResponseDto validateCofConsentRequestBody(ConsentCofRequestResponseDto consentCofRequestResponseDto) {
        consentCofRequestResponseDto.setValid(true);

        //validacija ibana
        if (StringUtils.isBlank(consentCofRequestResponseDto.getAccount().getIban())) {
            consentCofRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_psp_ic_iban_error", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
            consentCofRequestResponseDto.setValid(false);
        } else {
            if (consentCofRequestResponseDto.getAccount().getIban().length() == 21) {
//                if (!StringUtils.startsWith(consentCofRequestResponseDto.getAccount().getIban(), "HR")
//                        || !StringUtils.equals(StringUtils.substring(consentCofRequestResponseDto.getAccount().getIban(), 4, 11), Bassx2MinConstants.PlatniPromet.NKSBANKA_VBDINBAN_BANKA)) {
//                    consentCofRequestResponseDto.setValid(false);
//                    consentCofRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_psp_ic_iban_error", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
//                }
            } else {
                consentCofRequestResponseDto.setValid(false);
                consentCofRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_psp_ic_iban_error", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
            }

        }
//        //card number validacija
//        if (StringUtils.isBlank(consentCofRequestResponseDto.getCardNumber())) {
//            consentCofRequestResponseDto.setValid(false);
//            consentCofRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto("BAD_REQUEST", new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_psp_ic_credit_card_error", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
//        }
//        //card experation date validacija
//        if (consentCofRequestResponseDto.getCardExpiryDate() == null){
//            consentCofRequestResponseDto.setValid(false);
//            consentCofRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto("BAD_REQUEST", new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_psp_ic_credit_card_attribute_error", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
//        } else {
//            if (LocalDate.now().isAfter(consentCofRequestResponseDto.getCardExpiryDate())){
//                consentCofRequestResponseDto.setValid(false);
//                consentCofRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto("BAD_REQUEST", new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_psp_ic_card_expired", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
//            }
//        }
        return consentCofRequestResponseDto;
    }


    /**
     * Verify if mandatory attributtes exists in header and get all attributes from
     *
     * @param header
     * @param mandatoryAttributes
     * @return
     */
    public static RequestHeaderDto validateMandatoryAndGetAllHeaderAttributes(HttpHeaders
                                                                                      header, List<String> mandatoryAttributes) {
        RequestHeaderDto requestHeaderDto = new RequestHeaderDto();
        requestHeaderDto.setIsValid(true);
        if (header != null && header.getRequestHeaders() != null && !header.getRequestHeaders().isEmpty()) {
            //set all request header attributes
            for (String headerAttribute : header.getRequestHeaders().keySet()) {
                requestHeaderDto.getHeaderAttributesMap().put(headerAttribute, header.getRequestHeaders().getFirst(headerAttribute));
            }
            if (mandatoryAttributes != null && !mandatoryAttributes.isEmpty()) {
                for (String attribute : mandatoryAttributes) {
                    if (StringUtils.isBlank(requestHeaderDto.getHeaderAttributesMap().get(attribute.toLowerCase()))) {
                        requestHeaderDto.setIsValid(false);
                        requestHeaderDto.getErrorList().add("Nedostaje " + attribute + " ---> parametar " + attribute + " je obavezan request header parametar!");
                    }
                }
            }
        } else {
            requestHeaderDto.setIsValid(false);
            requestHeaderDto.getErrorList().add("Neispravan http request header!");
        }
        return requestHeaderDto;
    }

    /**
     * Verify if mandatory attributtes exists in header and get all attributes from
     *
     * @param request
     * @param mandatoryAttributes
     * @return
     */
    public static RequestHeaderDto validateMandatoryAndGetAllHeaderAttributes(HttpServerRequest request, List<String> mandatoryAttributes) {
        RequestHeaderDto requestHeaderDto = new RequestHeaderDto();
        requestHeaderDto.setIsValid(true);
        requestHeaderDto.setHeaderAttributesMap(getHeadersInfo(request));
        if (mandatoryAttributes != null && mandatoryAttributes.size() > 0) {
            for (String attribute : mandatoryAttributes) {
                if (StringUtils.isBlank(requestHeaderDto.getHeaderAttributesMap().get(attribute.toLowerCase()))) {
                    requestHeaderDto.setIsValid(false);
                    requestHeaderDto.getErrorList().add("Nedostaje " + attribute + " ---> parametar " + attribute + " je obavezan request header parametar!");
                }
            }
        }
        return requestHeaderDto;
    }

    /**
     * Validate UUID (format, version)
     *
     * @param uuidString
     * @return isValid
     */
    public static Boolean validateUUID(String uuidString) {
        Boolean isUUIDValid = Boolean.FALSE;
        try {
            UUID uuid = UUID.fromString(uuidString);
            if (StringUtils.equals(uuidString, uuid.toString())) {
                if (uuid.version() == 4) {
                    isUUIDValid = Boolean.TRUE;
                }
            }
        } catch (IllegalArgumentException iae) {
            isUUIDValid = Boolean.FALSE;
        }
        return isUUIDValid;
    }


    public static ErrorInformationDto setErrorInformationDto(String code, List<String> errorDescriptionList) {
        ErrorInformationDto errorInformationDto = null;
        if (StringUtils.isNotBlank(code) && errorDescriptionList != null && !errorDescriptionList.isEmpty()) {
            errorInformationDto = new ErrorInformationDto();
            List<ErrorMessageDto> errorMessageDtoList = new ArrayList<>();
            for (String error : errorDescriptionList) {
                errorMessageDtoList.add(new ErrorMessageDto(code, error));
            }
            errorInformationDto.setTppMessages(errorMessageDtoList);
        }
        return errorInformationDto;
    }

    public static ErrorInformationXmlDto setErrorInformationXmlDto(String
                                                                           code, List<String> errorDescriptionList) {
        ErrorInformationXmlDto errorInformationDto = null;
        if (StringUtils.isNotBlank(code) && errorDescriptionList != null && !errorDescriptionList.isEmpty()) {
            errorInformationDto = new ErrorInformationXmlDto();
            List<ErrorMessageDto> errorMessageDtoList = new ArrayList<>();
            for (String error : errorDescriptionList) {
                ErrorMessageDto errorMessageDto = new ErrorMessageDto(code, error);
                errorMessageDtoList.add(errorMessageDto);
            }
            errorInformationDto.setTppMessages(errorMessageDtoList);
        }
        return errorInformationDto;
    }


    public static Map<String, String> getHeadersInfo(HttpServerRequest request) {
        Map<String, String> map = new HashMap<String, String>();
        MultiMap headerNames = request.headers();
        headerNames.forEach(headerEntry -> {map.put(headerEntry.getKey(),headerEntry.getValue());} );
        return map;
    }

    public static LocalDate getPsd2DateFromString(String stringDate) {
        LocalDate date = null;
        if (StringUtils.isNotBlank(stringDate)) {
            try {
                DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                date = LocalDate.parse(stringDate, dateFormat);
            } catch (DateTimeException dte) {
                throw new DateTimeException("Wrong date format!!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return date;
    }

    public static String getStringFromLocalDatetime(LocalDateTime localDateTime, String pattern) {
        String formatDateTime = null;
        if (localDateTime != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            formatDateTime = localDateTime.format(formatter);
        }
        return formatDateTime;
    }

    public static Date getDateFromString(String stringDate) throws ParseException {
        Date date = null;
        if (StringUtils.isNotBlank(stringDate)) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            formatter.setLenient(false);
            date = formatter.parse(stringDate);
        }
        return date;
    }

    public static SinglePaymentDto formPsd2NalogFromTppNalog(TppNalogPlatniDto tppNalogPlatniDto) {
        SinglePaymentDto singlePaymentDto = null;
        if (tppNalogPlatniDto != null) {
            singlePaymentDto = SinglePaymentDto.builder().build();
            if (StringUtils.isNotBlank(tppNalogPlatniDto.getModelZaduzenja())) {
                singlePaymentDto.setEndToEndIdentification(tppNalogPlatniDto.getModelZaduzenja());
                if (StringUtils.isNotBlank(tppNalogPlatniDto.getPozivNaBrojZaduzenja())) {
                    singlePaymentDto.setEndToEndIdentification(singlePaymentDto.getEndToEndIdentification() + tppNalogPlatniDto.getPozivNaBrojZaduzenja());
                }
            }
            if (StringUtils.isNotBlank(tppNalogPlatniDto.getIbanZaduzenja())) {
                singlePaymentDto.setDebtorAccount(new AccountReferenceDto());
                singlePaymentDto.getDebtorAccount().setIban(tppNalogPlatniDto.getIbanZaduzenja());
            }
            if (StringUtils.isNotBlank(tppNalogPlatniDto.getNazivDebtor())) {
                singlePaymentDto.setDebtorId(tppNalogPlatniDto.getNazivDebtor());
            }
            if (StringUtils.isNotBlank(tppNalogPlatniDto.getUltimateDebtorNaziv())) {
                singlePaymentDto.setUltimateDebtor(tppNalogPlatniDto.getUltimateDebtorNaziv());
            }
            if (tppNalogPlatniDto.getIznos() != null) {
                singlePaymentDto.setInstructedAmount(new Amount());
                singlePaymentDto.getInstructedAmount().setAmount(String.valueOf(tppNalogPlatniDto.getIznos()));
                if (StringUtils.isNotBlank(tppNalogPlatniDto.getOznakaValute())) {
                    singlePaymentDto.getInstructedAmount().setCurrency(tppNalogPlatniDto.getOznakaValute());
                } else {
                    singlePaymentDto.getInstructedAmount().setCurrency(tppNalogPlatniDto.getSifraValute());
                }
            }
            if (StringUtils.isNotBlank(tppNalogPlatniDto.getIbanOdobrenja())) {
                singlePaymentDto.setCreditorAccount(new AccountReferenceDto());
                singlePaymentDto.getCreditorAccount().setIban(tppNalogPlatniDto.getIbanOdobrenja());
            }
            if (StringUtils.isNotBlank(tppNalogPlatniDto.getNazivCreditor())) {
                singlePaymentDto.setCreditorName(tppNalogPlatniDto.getNazivCreditor());
            }
            if (StringUtils.isNotBlank(tppNalogPlatniDto.getIdCreditor())) {
                singlePaymentDto.setCreditorId(tppNalogPlatniDto.getIdCreditor());
            }
            if (StringUtils.isNotBlank(tppNalogPlatniDto.getAdresaCreditor()) || StringUtils.isNotBlank(tppNalogPlatniDto.getDrzavaCreditor()) ||
                    StringUtils.isNotBlank(tppNalogPlatniDto.getGradCreditor())) {
                singlePaymentDto.setCreditorAddress(new AddressDto());
                if (StringUtils.isNotBlank(tppNalogPlatniDto.getAdresaCreditor())) {
                    singlePaymentDto.getCreditorAddress().setStreetName(tppNalogPlatniDto.getAdresaCreditor());
                }
                if (StringUtils.isNotBlank(tppNalogPlatniDto.getGradCreditor())) {
                    singlePaymentDto.getCreditorAddress().setTownName(tppNalogPlatniDto.getGradCreditor());
                }
                if (StringUtils.isNotBlank(tppNalogPlatniDto.getDrzavaCreditor())) {
                    singlePaymentDto.getCreditorAddress().setCountry(tppNalogPlatniDto.getDrzavaCreditor());
                }
            }
            if (StringUtils.isNotBlank(tppNalogPlatniDto.getUltimateCreditorNaziv())) {
                singlePaymentDto.setUltimateCreditor(tppNalogPlatniDto.getUltimateCreditorNaziv());
            }
            if (StringUtils.isNotBlank(tppNalogPlatniDto.getSifraNamjene())) {
                singlePaymentDto.setPurposeCode(tppNalogPlatniDto.getSifraNamjene());
            }
            if (StringUtils.isNotBlank(tppNalogPlatniDto.getSifraNamjene())) {
                singlePaymentDto.setPurposeCode(tppNalogPlatniDto.getSifraNamjene());
            }
            if (StringUtils.isNotBlank(tppNalogPlatniDto.getSvrha())) {
                singlePaymentDto.setRemittanceInformationUnstructured(tppNalogPlatniDto.getSvrha());
            }
            if (StringUtils.isNotBlank(tppNalogPlatniDto.getModelOdobrenja())) {
                singlePaymentDto.setRemittanceInformationStructured(new RemittanceDto());
                singlePaymentDto.getRemittanceInformationStructured().setReference(tppNalogPlatniDto.getModelOdobrenja());
                if (StringUtils.isNotBlank(tppNalogPlatniDto.getPozivNaBrojOdobrenja())) {
                    singlePaymentDto.getRemittanceInformationStructured().setReference(singlePaymentDto.getRemittanceInformationStructured().getReference() + tppNalogPlatniDto.getPozivNaBrojOdobrenja());
                }
            }
            if (StringUtils.isNotBlank(tppNalogPlatniDto.getSwiftNalogodavateljaKorisnika())) {
                singlePaymentDto.setCreditorAgent(tppNalogPlatniDto.getSwiftNalogodavateljaKorisnika());
            }
            if (StringUtils.isNotBlank(tppNalogPlatniDto.getSwiftNalogodavateljaKorisnikaNaziv())) {
                singlePaymentDto.setCreditorAgentName(tppNalogPlatniDto.getSwiftNalogodavateljaKorisnikaNaziv());
            }
            if (StringUtils.isNotBlank(tppNalogPlatniDto.getTrosakInoBanke())) {
                singlePaymentDto.setChargeBearer(tppNalogPlatniDto.getTrosakInoBanke());
            }
            if (tppNalogPlatniDto.getDatumValute() != null) {
                DateFormat df = new SimpleDateFormat(Psd2Constants.DATE_PSD2_FORMAT);
                singlePaymentDto.setRequestedExecutionDate(df.format(tppNalogPlatniDto.getDatumValute()));
            }
        }
        return singlePaymentDto;
    }

    public static SinglePaymentResponseDto resolveResposneForBulkPayments
            (List<TppNalogPlatniDto> tppNalozi, SinglePaymentDto header, String authLink, Integer sifraDatoteke) throws
            AbitRuleException {
        SinglePaymentResponseDto response = null;
        List<String> errorsList = new ArrayList<>();
        BigDecimal sumaNaknada = BigDecimal.ZERO;
        for (TppNalogPlatniDto psd2Nalog : tppNalozi) {
            if (StringUtils.isNotBlank(psd2Nalog.getGreska())) { //IMA GREŠKE
                errorsList.add(psd2Nalog.getGreska() + " Iban odobrenja: " + psd2Nalog.getIbanOdobrenja());
            } else { //NEMA GREŠKE
                if (psd2Nalog.isChanged()) {
                    errorsList.add("Neispravan routing.");
                } else {
                    if (psd2Nalog.getBatchBooking() && psd2Nalog.getBatchBookingNalozi() != null) {
                        for (TppNalogPlatniDto bbNalog : psd2Nalog.getBatchBookingNalozi()) {
                            if (bbNalog.getIznosNaknade() != null && bbNalog.getIznosNaknade().compareTo(BigDecimal.ZERO) > 0) {
                                sumaNaknada = sumaNaknada.add(bbNalog.getIznosNaknade());
                            }
                        }
                    } else {
                        if (psd2Nalog.getIznosNaknade() != null && psd2Nalog.getIznosNaknade().compareTo(BigDecimal.ZERO) > 0) {
                            sumaNaknada = sumaNaknada.add(psd2Nalog.getIznosNaknade());
                        }
                    }
                }
            }
        }
        if (errorsList != null && errorsList.size() > 0) {
            response = new SinglePaymentResponseDto(false, Psd2Util.setErrorInformationDto("BAD_REQUEST", errorsList));
        } else {
            response = new SinglePaymentResponseDto(true, TransactionStatus.RCVD, sifraDatoteke.toString(), null, null, Psd2Util.formLinksPaymentInitiation(authLink, header, sifraDatoteke));
            Amount naknada = new Amount();
            naknada.setCurrency(Bassx2Constants.Core.VALUTA_OZNKAVAL_HOME_CURRENCY);
            if (sumaNaknada.compareTo(BigDecimal.ZERO) > 0) {
                naknada.setAmount(String.valueOf(sumaNaknada));
                response.setTransactionFeeIndicator(true);
            } else {
                naknada.setAmount("0.00");
                response.setTransactionFeeIndicator(false);
            }
            response.setTransactionFees(naknada);
        }
        return response;
    }

    public static TppNalogPlatniDto resolvePodaciDebtor(TppNalogPlatniDto nalogTpp, EntityManager em) throws
            AbitRuleException {
        //String[] listaPlatitelja = CommonUtil.resolveClientDescription(IbanFunctions.resolveVbdiPartijaFromText(nalogTpp.getIbanZaduzenja()).get(IbanFunctions.PARTIJA_ID), true, em);
        String[] listaPlatitelja = null; // TODO Ivana
        if (listaPlatitelja == null || listaPlatitelja.length == 0) {
            throw new AbitRuleException(InternationalizationUtil.getLocalMessage("painError_debtorNepoznat", new String[]{nalogTpp.getIbanZaduzenja()}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
        }
        nalogTpp.setNazivDebtor(listaPlatitelja[0]);
        nalogTpp.setAdresaDebtor(listaPlatitelja[1]);
        nalogTpp.setGradDebtor(listaPlatitelja[2]);
        nalogTpp.setDrzavaDebtor(listaPlatitelja[3]);
        return nalogTpp;
    }

    public static Document formPain002Xml(List<TppNalogPlatniDto> tppNalogPlatniDtoList, Psd2DatotekaDto
            psd2DatotekaDto) throws AbitRuleException {
        Document pain002 = null;
        try {
            if (tppNalogPlatniDtoList != null && !tppNalogPlatniDtoList.isEmpty() && psd2DatotekaDto != null) {
                pain002 = new Document();
                CustomerPaymentStatusReportV03 customerPaymentStatusReportV03 = new CustomerPaymentStatusReportV03();
                //Group Header
                GroupHeader36 groupHeader36 = new GroupHeader36();
                groupHeader36.setMsgId(tppNalogPlatniDtoList.get(0).getSifraDatoteke().toString() + (new Timestamp(System.currentTimeMillis()).toString()));
                groupHeader36.setCreDtTm(resolveXMLGregorianCalendar(new Date()));
                //Debtor Agent
                BranchAndFinancialInstitutionIdentification4 branchAndFinancialInstitutionIdentification4 = new BranchAndFinancialInstitutionIdentification4();
                FinancialInstitutionIdentification7 financialInstitutionIdentification7 = new FinancialInstitutionIdentification7();
                financialInstitutionIdentification7.setBIC(Bassx2Constants.CoreHub.BANK_SWIFT_BIC8);
                branchAndFinancialInstitutionIdentification4.setFinInstnId(financialInstitutionIdentification7);
                groupHeader36.setDbtrAgt(branchAndFinancialInstitutionIdentification4);
                customerPaymentStatusReportV03.setGrpHdr(groupHeader36);
                //-----------Group Header End -------------------

                //Original Group Information and Status
                OriginalGroupInformation20 originalGroupInformation20 = new OriginalGroupInformation20();
                originalGroupInformation20.setOrgnlMsgId(psd2DatotekaDto.getNazivDatoteke());
                originalGroupInformation20.setOrgnlMsgNmId(psd2DatotekaDto.getNazivDatoteke());
                //originalGroupInformation20.setOrgnlNbOfTxs("TODO");
                customerPaymentStatusReportV03.setOrgnlGrpInfAndSts(originalGroupInformation20);
                //----------Original Group Information and Status End --------

                //Original Payment Information And Status
                for (TppNalogPlatniDto tppNalogPlatniDto : tppNalogPlatniDtoList) {
                    OriginalPaymentInformation1 originalPaymentInformation1 = new OriginalPaymentInformation1();
                    originalPaymentInformation1.setOrgnlPmtInfId(tppNalogPlatniDto.getSifraTppNaloga().toString());
                    for (TransactionGroupStatus3Code transactionGroupStatus3Code : TransactionGroupStatus3Code.values()) {
                        if (StringUtils.equals(transactionGroupStatus3Code.toString(), tppNalogPlatniDto.getStatusPsd2())) {
                            originalPaymentInformation1.setPmtInfSts(transactionGroupStatus3Code);
                        }
                    }
                    customerPaymentStatusReportV03.getOrgnlPmtInfAndSts().add(originalPaymentInformation1);
                }
                pain002.setCstmrPmtStsRpt(customerPaymentStatusReportV03);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new AbitRuleException("Greška kod formiranja pain.002 datoteke!");
        }
        return pain002;
    }

    private static XMLGregorianCalendar resolveXMLGregorianCalendar(Date datum) throws AbitRuleException {
        GregorianCalendar gcal = new GregorianCalendar();
        gcal.setTime(datum);
        XMLGregorianCalendar xmlDate = null;
        try {
            xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
        } catch (DatatypeConfigurationException ex) {
            throw new AbitRuleException("Greška kod pretvorbe datuma u XMLGregorianCalendar");
        }
        return xmlDate;
    }

    public static int getNumberOfDecimalPlaces(BigDecimal bigDecimal) {
        String string = bigDecimal.stripTrailingZeros().toPlainString();
        int index = string.indexOf(".");
        return index < 0 ? 0 : string.length() - index - 1;
    }


//    public static List<KarticaDto> getKarticeFromB2(String partija, EntityManager em) {
//        partija = IbanFunctions.isIban(partija) ? IbanFunctions.resolveVbdiPartijaFromText(partija).get(IbanFunctions.PARTIJA_ID) : partija;
//        if (partija == null || partija.trim().equals("")) return null;
//        List<KarticaDto> kartice = new ArrayList<>();
//        String sql = "select panka_kar, rbrka_kar, statu_kar, komit_kar, parti_kar, ispos_kte " +
//                "from bassx2\\:mbu_kartice " +
//                "inner join bassx2\\:partija on parti_kar = sifra_par " +
//                "inner join bassx2\\:mbu_kartemplate on tipka_kar = sifra_kte " +
//                "where parti_par = :partija";
//        try {
//            List<Object[]> tmpList = em.createNativeQuery(sql).setParameter("partija", partija).getResultList();
//            if (tmpList != null) {
//                KarticaDto kar = null;
//                for (Object[] obj : tmpList) {
//                    kar = new KarticaDto();
//                    kar.setPan((String) obj[0]);
//                    kar.setRedniBroj((Integer) obj[1]);
//                    kar.setStatus((String) obj[2]);
//                    kar.setSifraVlasnika((Integer) obj[3]);
//                    kar.setSifraPartije((Integer) obj[4]);
//                    kar.setIsPoslovnaString((String) obj[5]);
//                    kartice.add(kar);
//                }
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            kartice = null;
//        }
//
//        return kartice;
//    }

   public enum MandatoryRequestHeadersAttributes {
        X_REQUEST_ID("X-Request-ID");


        private String value;

        MandatoryRequestHeadersAttributes(String status) {
            this.value = status;
        }

        public String getValue() {
            return value;
        }
    }

    public static ErrorInformationXmlDto formErrorInformationXml(ErrorInformationDto errorInformationDto) {
        ErrorInformationXmlDto errorInformationXmlDto = new ErrorInformationXmlDto();
        errorInformationXmlDto.setTppMessages(errorInformationDto.getTppMessages());
        return errorInformationXmlDto;
    }


    /**
     * Logiranje Update u bazi.
     *
     * @param after Entity koji se insertira
     * @param log   nefinancijski nalog u koji se sprema promjena
     * @author Goran
     */
    public static NalogNefinDto logEdit(Object after, NalogNefinDto nlog) {
        try {
            // nađi stari entitet
            Object key = entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(after);
            Object before = entityManager.find(after.getClass(), key);
            // učitaj shemu entiteta
            EntityType<?> shema = entityManager.getMetamodel().entity(after.getClass());
            // rekurzivno hodaj po poljima u cijeloj hijerarhiji i dodaji u log
            nlog = walkAttributes(shema.getJavaType().getSimpleName(), resolveFieldValueToString(key), shema, before, after, PrometNefinDto.OPERATION_UPDATE, nlog);
        } catch (Exception e) {
            log.error("LOGEDIT", e);
        }
        return nlog;
    }

    /**
     * "Serijalizacija" sadržaja polja u String
     *
     * @author Goran
     */
    private static String resolveFieldValueToString(Object o) {
        // ovo bi možda trebalo u neku util klasu
        Object ret = o;
// TODO Hibernate dependent
//		if (o instanceof HibernateProxy) {
//			HibernateProxy proxy = (HibernateProxy) o;
//			ret = proxy.getHibernateLazyInitializer().getIdentifier();
//		}
        if (o != null && o.getClass().isAnnotationPresent(Entity.class)) {
            ret = entityManager.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(o);
        }
        if (o != null && o instanceof Date) {
            ret = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(o);
        }
        return ret == null ? null : ret.toString();
    }

    /**
     * rekurzivna funkcija za češljanje svih polja entita u svrhu logiranja
     *
     * @author Goran
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static NalogNefinDto walkAttributes(String tablica, String kljuc, IdentifiableType mt, Object before, Object after, String operation, NalogNefinDto log) throws Exception {
        if (mt.getSupertype() != null) {
            log = walkAttributes(tablica, kljuc, mt.getSupertype(), before, after, operation, log);
        }
        Set<Attribute> polja = mt.getDeclaredAttributes();
        for (Attribute a : polja) {
            if (mt.hasVersionAttribute() && mt.getVersion(Integer.class).equals(a)) {
                continue;
            }
            Field newField = mt.getJavaType().getDeclaredField(a.getName());
            Field oldField = mt.getJavaType().getDeclaredField(a.getName());
            newField.setAccessible(true);
            oldField.setAccessible(true);
            Object oldFieldValue = oldField.get(before);
            String stara = resolveFieldValueToString(oldFieldValue);
            if (after != null) {
                Object newFieldValue = newField.get(after);
                String nova = resolveFieldValueToString(newFieldValue);
                if (StringUtils.isEmpty(stara) && StringUtils.isNotEmpty(nova)) {
                    log.addPrometNefin(new PrometNefinDto(tablica, mt.getJavaType().getSimpleName(), kljuc, a.getName(), stara, nova, operation));
                } else if (StringUtils.isNotEmpty(stara) && StringUtils.isEmpty(nova)) {
                    log.addPrometNefin(new PrometNefinDto(tablica, mt.getJavaType().getSimpleName(), kljuc, a.getName(), stara, nova, operation));
                } else if (StringUtils.isNotEmpty(stara) && StringUtils.isNotEmpty(nova) && !stara.equals(nova)) {
                    log.addPrometNefin(new PrometNefinDto(tablica, mt.getJavaType().getSimpleName(), kljuc, a.getName(), stara, nova, operation));
                }
            } else {
                log.addPrometNefin(new PrometNefinDto(tablica, mt.getJavaType().getSimpleName(), kljuc, a.getName(), stara, null, operation));
            }
        }
        return log;
    }

    public static List<TppNalogPlatniDto> resolveNepotpisani(List<TppNalogPlatniDto> nepotpisani) {
        List<TppNalogPlatniDto> res = null;
        Map<Integer, TppNalogPlatniDto> mapTppNalog = new HashMap<>();
        //Prvo u mapu stavimo sve naloge koji nisu detaljni BB
        for (TppNalogPlatniDto dto : nepotpisani) {
            if (dto.getSifraZbirnogBatchBookingNaloga() == null) {
                mapTppNalog.put(dto.getSifraTppNaloga(), dto);
            }
        }
        //Sad u listi provjerimo da li ima detaljnih BB naloga
        for (TppNalogPlatniDto dto : nepotpisani) {
            if (dto.getBatchBooking() && dto.getSifraZbirnogBatchBookingNaloga() != null) {
                TppNalogPlatniDto zbirniBB = mapTppNalog.get(dto.getSifraZbirnogBatchBookingNaloga());
                if (zbirniBB != null) {
                    List<TppNalogPlatniDto> bbDetaljni = zbirniBB.getBatchBookingNalozi();
                    bbDetaljni.add(dto);
                    zbirniBB.setBatchBookingNalozi(bbDetaljni);
                    zbirniBB.addNaknada(dto.getIznosNaknade());
                }
                mapTppNalog.put(zbirniBB.getSifraTppNaloga(), zbirniBB);
            }
        }
        res = new ArrayList<>();
        for (Map.Entry<Integer, TppNalogPlatniDto> tmpEntry : mapTppNalog.entrySet()) {
            res.add(tmpEntry.getValue());
        }
        return res;
    }

    public static List<String> getValuteTecajneListe(EntityManager em) {
        List<String> res = new ArrayList<>();
        String sql = " select distinct sifra_val, oznka_val from valuta join tecaj on sifva_tec = sifra_val where tecli_tec = (select max(sifra_tli) from tecajna_lista) ";
        List<Object[]> tmpList = (List<Object[]>) em.createNativeQuery(sql).getResultList();
        if (tmpList != null) {
            for (Object[] obj : tmpList) {
                res.add((String) obj[0]);
                res.add((String) obj[1]);
            }
        }
        res.add(Bassx2Constants.Core.VALUTA_OZNKAVAL_HOME_CURRENCY); //Kuna nema na tecajnoj
        res.add(Bassx2Constants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY); //Kuna nema na tecajnoj
        return res;
    }

    public static TppNalogPlatniDto isTppNalogChanged(SinglePaymentDto originalNalog, TppNalogPlatniDto tppNalogPlatniDto) {
        TppNalogPlatniDto tppNalogPlatniDtoValidated = null;
        List<String> changedFieldsList = null;
        String changed = null;
        if (originalNalog != null && tppNalogPlatniDto != null) {
            tppNalogPlatniDtoValidated = tppNalogPlatniDto;
            tppNalogPlatniDtoValidated.setChanged(false);

            //endToEndIdentification
            String modelZaduzenja = null;
            String pozivNaBrojZaduzenja = null;
            if (StringUtils.isNotBlank(originalNalog.getEndToEndIdentification())) {
                modelZaduzenja = originalNalog.getEndToEndIdentification().substring(0, 4);
                pozivNaBrojZaduzenja = originalNalog.getEndToEndIdentification().substring(4);
            }

            if (!StringUtils.equals(tppNalogPlatniDto.getModelZaduzenja(), modelZaduzenja)) {
                tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "modzd_tpp,");

            }

            if (!StringUtils.equals(tppNalogPlatniDto.getPozivNaBrojZaduzenja(), pozivNaBrojZaduzenja)) {
                tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "pbrzd_tpp,");

            }

            //iban zaduzenja
            if (!StringUtils.equals(originalNalog.getDebtorAccount().getIban(), tppNalogPlatniDto.getIbanZaduzenja())) {
                tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "ibanz_tpp,");

            }

            //id zaduzenja
            if (!StringUtils.equals(originalNalog.getDebtorId(), tppNalogPlatniDto.getIdDebtor())) {
                tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "debid_tpp,");

            }

            //stvarni dužnik
            if (!StringUtils.equals(originalNalog.getUltimateDebtor(), tppNalogPlatniDto.getUltimateDebtorNaziv())) {
                tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "dulnz_tpp,");

            }

            //iznos
            if (tppNalogPlatniDto.getIznos() != null) {
                String iznos = String.valueOf(tppNalogPlatniDto.getIznos());
                if (!StringUtils.equals(iznos, originalNalog.getInstructedAmount().getAmount())) {
                    tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "iznos_tpp,");

                }
            } else if (tppNalogPlatniDto.getIznos() == null && originalNalog.getInstructedAmount().getAmount() != null) {
                tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "iznos_tpp,");
            }

            //valuta
            if (!StringUtils.equals(originalNalog.getInstructedAmount().getCurrency(), tppNalogPlatniDto.getOznakaValute())) {
                tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "valut_tpp,");

            }

            //iban odobrenja
            if (!StringUtils.equals(originalNalog.getCreditorAccount().getIban(), tppNalogPlatniDto.getIbanOdobrenja())) {
                tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "ibano_tpp,");

            }

            //SWIFT BIC nalogoadavatelja
            if (!StringUtils.equals(originalNalog.getCreditorAgent(), tppNalogPlatniDto.getSwiftNalogodavateljaKorisnika())) {
                tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "swbic_tpp,");

            }

            //NISAM SIGURAN DAL TO TREBA NEMA TOGA U TABLICI psd2_nalog_tpp!!!!
            //SWIFT BIC nalogoadavatelja naziv
            if (!StringUtils.equals(originalNalog.getCreditorAgentName(), tppNalogPlatniDto.getSwiftNalogodavateljaKorisnikaNaziv())) {
                tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "swiftBicNaziv,");

            }

            //id primatelja
            if (!StringUtils.equals(originalNalog.getCreditorId(), tppNalogPlatniDto.getIdCreditor())) {
                tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "crdid_tpp,");
            }

            //naziv primatelja
            if (!StringUtils.equals(originalNalog.getCreditorName(), tppNalogPlatniDto.getNazivCreditor())) {
                tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "cnaziv_tpp,");
            }

            //adresa primatelja (država, grad, ulica)
            if (originalNalog.getCreditorAddress() != null) {
                //drzava
                if (!StringUtils.equals(originalNalog.getCreditorAddress().getCountry(), tppNalogPlatniDto.getDrzavaCreditor())) {
                    tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "cdrzv_tpp,");

                }
                //grad
                if (!StringUtils.equals(originalNalog.getCreditorAddress().getTownName(), tppNalogPlatniDto.getGradCreditor())) {
                    tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "cgrad_tpp,");

                }
                //adresa
                String ulica = null;
                String adresa = null;
                if (StringUtils.isNotBlank(originalNalog.getCreditorAddress().getStreetName())) {
                    ulica = originalNalog.getCreditorAddress().getStreetName();
                    if (StringUtils.isNotBlank(originalNalog.getCreditorAddress().getBuildingNumber())) {
                        if (StringUtils.isNotBlank(ulica)) {
                            adresa = ulica + (StringUtils.isNotBlank(originalNalog.getCreditorAddress().getBuildingNumber()) ? " " + originalNalog.getCreditorAddress().getBuildingNumber() : "");
                        }
                    }
                }
                if (!StringUtils.equals(adresa, tppNalogPlatniDto.getAdresaCreditor())) {
                    tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "cadrs_tpp,");
                }
            } else if (originalNalog.getCreditorAddress() == null && (tppNalogPlatniDto.getDrzavaCreditor() != null || tppNalogPlatniDto.getGradCreditor() != null || tppNalogPlatniDto.getAdresaCreditor() != null)) {
                tppNalogPlatniDtoValidated.setChanged(true);
                if (tppNalogPlatniDto.getDrzavaCreditor() != null) {
                    tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "cdrzv_tpp,");

                }
                if (tppNalogPlatniDto.getGradCreditor() != null) {
                    tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "cgrad_tpp,");

                }
                if (tppNalogPlatniDto.getAdresaCreditor() != null) {
                    tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "cadrs_tpp,");
                }
            }

            //krajnji primatelj
            if (!StringUtils.equals(originalNalog.getUltimateCreditor(), tppNalogPlatniDto.getUltimateCreditorNaziv())) {
                tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "culnz_tpp,");

            }

            //šifraNamjene
            if (!StringUtils.equals(originalNalog.getPurposeCode(), tppNalogPlatniDto.getSifraNamjene())) {
                tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "snamj_tpp,");
            }

            //svrha
            if (!StringUtils.equals(originalNalog.getRemittanceInformationUnstructured(), tppNalogPlatniDto.getSvrha())) {
                tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "svrha_tpp,");
            }

            //model i poziv na broj odobrenja
            if (originalNalog.getRemittanceInformationStructured() != null) {
                String modelOdobrenja = null;
                String pozivNaBrojOdobrenja = null;
                if (StringUtils.isNotBlank(originalNalog.getRemittanceInformationStructured().getReference())) {
                    modelOdobrenja = originalNalog.getRemittanceInformationStructured().getReference().substring(0, 4);
                    pozivNaBrojOdobrenja = originalNalog.getRemittanceInformationStructured().getReference().substring(4);
                }

                if (!StringUtils.equals(tppNalogPlatniDto.getModelOdobrenja(), modelOdobrenja)) {
                    tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "modod_tpp,");
                }

                if (!StringUtils.equals(tppNalogPlatniDto.getPozivNaBrojOdobrenja(), pozivNaBrojOdobrenja)) {
                    tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "pbrod_tpp,");

                }
            } else if (tppNalogPlatniDto.getModelOdobrenja() != null) {
                tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "modod_tpp,");
            } else if (tppNalogPlatniDto.getPozivNaBrojOdobrenja() != null) {
                tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "pbrod_tpp,");
            }

            //datum valute
            if (StringUtils.isNotBlank(originalNalog.getRequestedExecutionDate())) {
                Date datumValute = null;
                try {
                    datumValute = Psd2Util.getDateFromString(originalNalog.getRequestedExecutionDate());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (!datumValute.equals(tppNalogPlatniDto.getDatumValute())) {
                    tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "datva_tpp,");
                }
            } else if (originalNalog.getRequestedExecutionDate() == null && tppNalogPlatniDto.getDatumValute() != null) {
                tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "datva_tpp,");
            }

            // troškovna opcija
            if (StringUtils.isNotBlank(originalNalog.getChargeBearer())) {
                if ((originalNalog.getChargeBearer().trim().equals("DEBT"))) {
                    if (!StringUtils.equals(tppNalogPlatniDto.getTrosakInoBanke(), "OUR")) {
                        tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "trino_tpp,");
                    }
                } else if ((originalNalog.getChargeBearer().trim().equals("CRED"))) {
                    if (!StringUtils.equals(tppNalogPlatniDto.getTrosakInoBanke(), "BEN")) {
                        tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "trino_tpp,");
                    }
                } else if ((originalNalog.getChargeBearer().trim().equals("SLEV"))) {
                    if (!StringUtils.equals(tppNalogPlatniDto.getTrosakInoBanke(), "SHA")) {
                        tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "trino_tpp,");
                    }
                } else if ((originalNalog.getChargeBearer().trim().equals("SHAR"))) {
                    if (!StringUtils.equals(tppNalogPlatniDto.getTrosakInoBanke(), "SHA")) {
                        tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "trino_tpp,");
                    }
                }
            } else if (StringUtils.isNotBlank((tppNalogPlatniDto.getTrosakInoBanke()))) {
                tppNalogPlatniDtoValidated = updateChangedNalog(tppNalogPlatniDtoValidated, "trino_tpp,");
            }
        }
        return tppNalogPlatniDtoValidated;
    }

    public static TppNalogPlatniDto updateChangedNalog(TppNalogPlatniDto tppNalogDto, String updatedField) {
        tppNalogDto.setChanged(true);
        if (StringUtils.isNotBlank(updatedField)) {
            if (StringUtils.isNotBlank(tppNalogDto.getChangedFields())) {
                tppNalogDto.setChangedFields(tppNalogDto.getChangedFields() + updatedField);
            } else {
                tppNalogDto.setChangedFields(updatedField);
            }

        }
        return tppNalogDto;
    }

    public static String getObjectAsString(WriterInterceptorContext context) {
        String objectStr = null;
        OutputStream originalStream = context.getOutputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        context.setOutputStream(baos);
        try {
            context.proceed();
            objectStr = baos.toString();
            baos.writeTo(originalStream);
            baos.close();
            context.setOutputStream(originalStream);
        } catch (Exception e) {

        }
        return objectStr;
    }

    /**
     * Provjera da li je psd2 nalog (tpp nalog) nalog kupoprodaje
     *
     * @param psd2Nalog
     * @return true/false
     */
    public static Boolean isNalogKupoprodaje(SinglePaymentDto psd2Nalog) {
        Boolean isNalogKupoprodaje = Boolean.FALSE;
        if (psd2Nalog != null) {
            if (psd2Nalog.getInstructedAmount() != null && StringUtils.isNotBlank(psd2Nalog.getInstructedAmount().getCurrency()) && StringUtils.isNotBlank(psd2Nalog.getInstructedAmount().getAmount())) { //instructed amount, currency
                if (psd2Nalog.getDebtorAccount() != null && psd2Nalog.getCreditorAccount() != null) { //debtor i creditor
                    if (StringUtils.isNotBlank(psd2Nalog.getDebtorAccount().getIban()) && StringUtils.isNotBlank(psd2Nalog.getCreditorAccount().getIban())) { //iban
                        if (StringUtils.equals(psd2Nalog.getDebtorAccount().getIban().trim(), psd2Nalog.getCreditorAccount().getIban().trim())) { //ibano = ibanz
                            if (StringUtils.isNotBlank(psd2Nalog.getDebtorAccount().getCurrency()) && StringUtils.isNotBlank(psd2Nalog.getCreditorAccount().getCurrency())) { //currency
                                isNalogKupoprodaje = Boolean.TRUE;
                            }
                        }
                    }
                }
            }
        }
        return isNalogKupoprodaje;
    }

}


