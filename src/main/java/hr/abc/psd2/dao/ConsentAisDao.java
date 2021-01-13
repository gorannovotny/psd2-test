package hr.abc.psd2.dao;

import static hr.abc.psd2.model.ConsentStatus.EXPIRED;
import static hr.abc.psd2.model.ConsentStatus.RECEIVED;
import static hr.abc.psd2.model.ConsentStatus.REVOKED_BY_PSU;
import static hr.abc.psd2.model.ConsentStatus.TERMINATED_BY_TPP;
import static hr.abc.psd2.model.ConsentStatus.VALID;
import static hr.abc.psd2.model.ConsentTypeAccess.ACCOUNT;
import static hr.abc.psd2.model.ConsentTypeAccess.BALANCE;
import static hr.abc.psd2.model.ConsentTypeAccess.TRANSACTION;
import static hr.abc.psd2.model.dto.error.ErrorCode.ACCESS_EXCEEDED;
import static hr.abc.psd2.model.dto.error.ErrorCode.CONSENT_UNKNOWN;
import static hr.abc.psd2.model.dto.error.ErrorCode.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;

//import hr.abit.b3.biz.security.WebLoginBean;
import hr.abc.psd2.config.AppConfig;
import io.agroal.api.AgroalDataSource;
import org.apache.commons.lang3.StringUtils;

import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.exception.ConsentAISException;
import hr.abc.psd2.model.ConsentStatus;
import hr.abc.psd2.model.ConsentTypeAccess;
import hr.abc.psd2.model.RequestType;
import hr.abc.psd2.model.dto.ConsentRequestResponseDto;
import hr.abc.psd2.model.dto.ais.AccountAccessDto;
import hr.abc.psd2.model.dto.ais.AccountReferenceDto;
import hr.abc.psd2.model.dto.ais.ConsentAccountAisDto;
import hr.abc.psd2.model.dto.ais.ConsentActionAisDto;
import hr.abc.psd2.model.dto.ais.ConsentAisDto;
import hr.abc.psd2.model.dto.authorization.AuthorizationDto;
import hr.abc.psd2.model.dto.authorization.ScaStatus;
import hr.abc.psd2.model.dto.core.PartijaDto;
import hr.abc.psd2.model.dto.error.ErrorCode;
import hr.abc.psd2.model.entity.psd2.ConsentAis;
import hr.abc.psd2.service2.ICoreSrvc;
import hr.abc.psd2.util.Bassx2Constants;
import hr.abc.psd2.util.GenericBassxConstants;
import hr.abc.psd2.util.InternationalizationUtil;
import hr.abc.psd2.util.Psd2Constants;
import hr.abc.psd2.util.Psd2Util;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Tomica on 09-Oct-18.
 */
@Slf4j
@Dependent
@Transactional
public class ConsentAisDao implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final int IDX_SIFRA_CON = 0;
    private static final int IDX_RECURING_INDICATOR_CON = 1;
    private static final int IDX_TO_DATE_CON = 2;
    private static final int IDX_FREQUENCY_PER_DAY_CON = 3;
    private static final int IDX_STATE_CON = 4;
    private static final int IDX_COMBINED_SERVICE_INDICATOR_CON = 5;
    private static final int IDX_SIFRA_ACC = 6;
    private static final int IDX_IBAN_ACC = 7;
    private static final int IDX_CURRENCY_ACC = 8;
    private static final int IDX_TYPE_ACC = 9;
    private static final int IDX_KOMITENT_CON = 10;
    private static final int IDX_PARTIJA_ACC = 11;
    private static final int IDX_TPP_ID_CON = 12;
    private static final int IDX_CDATE_CON = 13;
    private static final int IDX_XREQID_CON = 14;
    private static final int IDX_PSUID_CON = 15;
    private static final int IDX_LAST_ACT_DAT_COM = 16;
    private static final int IDX_TPP_USAGE_COUNT_CON = 17;
    private static final int IDX_TPP_FREQUENCY_PER_DAY_CON = 18;
    private static final int IDX_TPP_REDIRECT_URI = 19;
    private static final int IDX_KOMITENT_CON_NAZIV = 20;

//    @Inject
//    private WebLoginBean loginBean;

    @Inject
    private AuthorizationDao authorizationDao;

    @Inject
    private ConsentActionAisDao actionAisDao;
    
    @Inject
    private EntityManager entityManager;

    @Inject
    private ICoreSrvc coreSrvc;

    @Inject
    AppConfig appConfig;

    @Inject Psd2Util psd2Util;

    // resources
    @Inject
    AgroalDataSource dataSource;

     private String getBasicSql() {
        return "select sifra_con, recur_con, todat_con, frequ_con, state_con, comsr_con, sifra_acc, iban_acc, curr_acc, type_acc, " +
                "komit_con, parti_acc, tppid_con, cdate_con, reqid_con, psuid_con, lacda_con, uctpp_con, tppfr_con, tppre_con " +
                "from psd2_consent_ais consent " +
                "join psd2_consent_access_ais accesss on accesss.sicon_acc = consent.sifra_con ";
    }

    /**
     * Get ais Consent by sifra
     *
     * @param sifraConsenta
     * @return ConsentAisDto
     */
    public ConsentAisDto getConsentAisBySifra(Integer sifraConsenta) throws AbitRuleException {
        ConsentAisDto consentAisDto = null;
        try {
            if (sifraConsenta != null) {
                String sql = getBasicSql() + " where sifra_con = :sifraConsenta ";
                Query query = entityManager.createNativeQuery(sql);
                query.setParameter("sifraConsenta", sifraConsenta);
                List<Object[]> consentAccessList = query.getResultList();
                if (consentAccessList != null && !consentAccessList.isEmpty()) {
                    consentAisDto = formConsentAisDto(consentAccessList);
                }
            }
        } catch (Exception e) {
            log.error("Exception is:", e);
        }
        return consentAisDto;
    }

    public List<ConsentAisDto> getConsentAisBySifraList(List<Integer> paymentId) {
        List<ConsentAisDto> consentDtoList = null;
        try {
            if (paymentId != null) {
                String sql = getBasicSql() + " where sifra_con in :sifra_con ";
                Query query = entityManager.createNativeQuery(sql);
                query.setParameter("sifra_con", paymentId);
                List<Object[]> consentObjectList = (List<Object[]>) query.getResultList();
                //Sad kreiramo mapu, da dobijemo listu consenta
                Map<Integer, List<Object[]>> mapConsent = new HashMap<>();
                if (consentObjectList != null) {
                    for (Object[] tmpObj : consentObjectList) {
                        List<Object[]> consentTmpList = new ArrayList<>();
                        if (mapConsent.containsKey(tmpObj[IDX_SIFRA_CON])) {
                            consentTmpList = mapConsent.get(tmpObj[IDX_SIFRA_CON]);
                        }
                        consentTmpList.add(tmpObj);
                        mapConsent.put((Integer) tmpObj[IDX_SIFRA_CON], consentTmpList);
                    }

                    //Sad za svaki zapis u mapi generiramo consentDto
                    for (Map.Entry<Integer, List<Object[]>> tmpEntry : mapConsent.entrySet()) {
                        if (consentDtoList == null) consentDtoList = new ArrayList<>();
                        consentDtoList.add(formConsentAisDto(tmpEntry.getValue()));
                    }
                }
            }
        } catch (NoResultException nre) {
            consentDtoList = null;
        } catch (Exception e) {
            log.error("Exception is:", e);
        }
        return consentDtoList;
    }

    private ConsentAisDto formConsentAisDto(List<Object[]> consentAccessList) {
        ConsentAisDto consentAisDto = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (consentAccessList != null && !consentAccessList.isEmpty()) {
            consentAisDto = new ConsentAisDto();
            List<ConsentAccountAisDto> consentAccountAisDtoList = new ArrayList<>();
            for (Object[] access : consentAccessList) {
                ConsentAccountAisDto consentAccountAisDto = new ConsentAccountAisDto();
                consentAccountAisDto.setIbanAcc((String) access[IDX_IBAN_ACC]);
                consentAccountAisDto.setCurrencyAcc((String) access[IDX_CURRENCY_ACC]);
                consentAccountAisDto.setTypeAcc((String) access[IDX_TYPE_ACC]);
                consentAccountAisDto.setSifraPar((Integer) access[IDX_PARTIJA_ACC]);
                consentAccountAisDtoList.add(consentAccountAisDto);
            }

            consentAisDto.setConsentAccountAisDtoList(consentAccountAisDtoList);
            consentAisDto.setSifra((Integer) consentAccessList.get(0)[IDX_SIFRA_CON]);
            consentAisDto.setStateCon((String) consentAccessList.get(0)[IDX_STATE_CON]);
            consentAisDto.setFrequencyPerDayCon((Integer) consentAccessList.get(0)[IDX_FREQUENCY_PER_DAY_CON]);
            consentAisDto.setCreationDateCon(((Timestamp) consentAccessList.get(0)[IDX_CDATE_CON]).toLocalDateTime());
            consentAisDto.setToDateCon(((Date) consentAccessList.get(0)[IDX_TO_DATE_CON]).toLocalDate());
            consentAisDto.setRecurringIndicatorCon(((Integer) consentAccessList.get(0)[IDX_RECURING_INDICATOR_CON]).equals(new Integer(1)) ? true : false);
            consentAisDto.setxRequestIdCon((String) consentAccessList.get(0)[IDX_XREQID_CON]);
            consentAisDto.setPsuIdCon((String) consentAccessList.get(0)[IDX_PSUID_CON]);
            consentAisDto.setTppIdCon((String) consentAccessList.get(0)[IDX_TPP_ID_CON]);
            consentAisDto.setTppFrequencyPerDayCon((Integer) consentAccessList.get(0)[IDX_TPP_FREQUENCY_PER_DAY_CON]);
            consentAisDto.setLastActionDate(((Date) consentAccessList.get(0)[IDX_LAST_ACT_DAT_COM]).toLocalDate());
            consentAisDto.setCombinedServiceIndicator(((Integer) consentAccessList.get(0)[IDX_COMBINED_SERVICE_INDICATOR_CON]).equals(new Integer(1)) ? true : false);
            consentAisDto.setSifraKom((Integer) consentAccessList.get(0)[IDX_KOMITENT_CON]);
            consentAisDto.setTppUsageCounterCon((Integer) consentAccessList.get(0)[IDX_TPP_USAGE_COUNT_CON]);
            consentAisDto.setTppRedirectUri((String) consentAccessList.get(0)[IDX_TPP_REDIRECT_URI]);
            consentAisDto.setTppName(null);
            try {
                //consentAisDto.setTrenutniAutenticiraniKorisnik(loginBean.getIbKorisnik().getSifra());
                consentAisDto.setTrenutniAutenticiraniKorisnik(null); // TODO Ivana
            } catch (NullPointerException npe) {
                consentAisDto.setTrenutniAutenticiraniKorisnik(null);
            } catch (ContextNotActiveException cne){
                consentAisDto.setTrenutniAutenticiraniKorisnik(null);
            }

        }
        return consentAisDto;
    }

    /**
     * Insert ais Conesnt.
     *
     * @param consentAisDto
     * @return ConsentAisDto
     */
    public ConsentAisDto createConsent(ConsentAisDto consentAisDto) throws AbitRuleException {
        // insert SQL
        String sqlInsertConsentAis = "insert into psd2_consent_ais( state_con, frequ_con, cdate_con, todat_con, recur_con, reqid_con, psuid_con, tppid_con, comsr_con, komit_con, uctpp_con, tppfr_con, lacda_con, tppre_con, tppna_con) " +
                " values (:state_con, :frequ_con, :cdate_con, :todat_con, :recur_con, :reqid_con, :psuid_con, :tppid_con, :comsr_con, :komit_con, :uctpp_con, :tppfr_con, :lacda_con, :tppre_con, :tppna_con)";
        try {
            // priprema upita
            Query queryInsertConsentAis = entityManager.createNativeQuery(sqlInsertConsentAis);
            queryInsertConsentAis.setParameter("state_con", consentAisDto.getStateCon());
            queryInsertConsentAis.setParameter("frequ_con", consentAisDto.getFrequencyPerDayCon());
            queryInsertConsentAis.setParameter("cdate_con", Timestamp.valueOf(LocalDateTime.now()));
            queryInsertConsentAis.setParameter("todat_con", Date.valueOf(consentAisDto.getToDateCon()));
            queryInsertConsentAis.setParameter("recur_con", consentAisDto.getRecurringIndicatorCon().equals(true) ? new Integer(1) : new Integer(0));
            queryInsertConsentAis.setParameter("reqid_con", consentAisDto.getxRequestIdCon());
            queryInsertConsentAis.setParameter("psuid_con", consentAisDto.getPsuIdCon());
            queryInsertConsentAis.setParameter("tppid_con", consentAisDto.getTppIdCon());
            queryInsertConsentAis.setParameter("comsr_con", consentAisDto.getCombinedServiceIndicator().equals(true) ? new Integer(1) : new Integer(0));
            queryInsertConsentAis.setParameter("komit_con", consentAisDto.getSifraKom());
            queryInsertConsentAis.setParameter("uctpp_con", consentAisDto.getTppUsageCounterCon());
            queryInsertConsentAis.setParameter("tppfr_con", consentAisDto.getTppFrequencyPerDayCon());
            queryInsertConsentAis.setParameter("lacda_con", Date.valueOf(LocalDate.now()));
            queryInsertConsentAis.setParameter("tppre_con", consentAisDto.getTppRedirectUri());
            queryInsertConsentAis.setParameter("tppna_con", consentAisDto.getTppName());

            // izvršavanje na bazi podataka
            queryInsertConsentAis.executeUpdate();
            // dohvat unesenog naloga
            String sqlPk = "SELECT @@IDENTITY";

            Object primaryKey = entityManager.createNativeQuery(sqlPk).getSingleResult();
            consentAisDto.setSifra(((BigDecimal) primaryKey).intValue());
        } catch (Exception e) {
            log.error("Exception is:", e);
        }
        return consentAisDto;
    }

    /**
     * Insert ais Conesnt Account List.
     *
     * @param consentAisDto
     */
    public void insertConsentAccountList(ConsentAisDto consentAisDto) throws AbitRuleException {
        // inicijalizacija
        Connection connection = null;
        PreparedStatement psInsert = null;
        String sqlInsertConsentAccount = "insert into psd2_consent_access_ais (iban_acc, curr_acc, type_acc, sicon_acc, parti_acc) values (?,?,?,?,?)";
        try {
            // obrada zapisa
            if (consentAisDto.getConsentAccountAisDtoList() != null && !consentAisDto.getConsentAccountAisDtoList().isEmpty()) {
                // dohvat konekcije preko data source-a
                connection = dataSource.getConnection();
                // formiranje prepared statementa
                psInsert = connection.prepareStatement(sqlInsertConsentAccount);
                // iteracija po listi i popunjavanje liste batcha
                int i;
                for (ConsentAccountAisDto consentAccountDto : consentAisDto.getConsentAccountAisDtoList()) {
                    i = 1;
                    psInsert.setString(i++, consentAccountDto.getIbanAcc());
                    psInsert.setString(i++, consentAccountDto.getCurrencyAcc());
                    psInsert.setString(i++, consentAccountDto.getTypeAcc());
                    psInsert.setInt(i++, consentAisDto.getSifra());
                    psInsert.setInt(i++, consentAccountDto.getSifraPar());

                    psInsert.addBatch();
                }
            }
            // izvršavanje batcha
            psInsert.executeBatch();
            psInsert.clearBatch();
        } catch (SQLException e) {
            log.error("Exception is:", e);
        } finally { // zatvaranje resursa
            try {
                if (psInsert != null) psInsert.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                log.error("Exception is:", e);
            }
        }
    }

    /**
     * Edit Consent Ais
     *
     * @param dto
     */
    public void edit(ConsentAisDto dto) throws AbitRuleException {
        validateBeforeEdit(dto);
        ConsentAis entity = formEntity(dto);
        entityManager.merge(entity);
        entityManager.flush();
    }

    protected ConsentAis formEntity(ConsentAisDto dto) {
        ConsentAis entity = null;
        if (dto != null) {
            entity = new ConsentAis();
            entity.setSifraCon(dto.getSifra());
            entity.setStateCon(dto.getStateCon());
            entity.setFrequCon(dto.getFrequencyPerDayCon());
            entity.setCdateCon(Timestamp.valueOf(dto.getCreationDateCon()));
            entity.setTodatCon(Date.valueOf(dto.getToDateCon()));
            entity.setRecurCon(dto.getRecurringIndicatorCon() ? new Integer(1) : new Integer(0));
            entity.setReqidCon(dto.getxRequestIdCon());
            entity.setPsuidCon(dto.getPsuIdCon());
            entity.setTppidCon(dto.getTppIdCon());
            entity.setTppfrCon(dto.getTppFrequencyPerDayCon());
            entity.setLacda_con(Date.valueOf(dto.getLastActionDate()));
            entity.setComsr_Con(dto.getCombinedServiceIndicator() ? new Integer(1) : new Integer(0));
            entity.setUctppCon(dto.getTppUsageCounterCon());
            entity.setKomitCon(dto.getSifraKom());
            entity.setTppRedirectUri(dto.getTppRedirectUri());
        }
        return entity;
    }

    /**
     * Get ais Consent status
     *
     * @param sifraConsenta
     * @return status
     */
    public ConsentRequestResponseDto getConsentStatusBySifra(Integer sifraConsenta, String tppId) throws AbitRuleException {
        ConsentRequestResponseDto consentRequestResponseDtoResult = new ConsentRequestResponseDto();
        try {
            if (sifraConsenta != null && StringUtils.isNotBlank(tppId)) {
                String sql = "select sifra_con, state_con, tppid_con, todat_con from psd2_consent_ais where sifra_con = :sifraConsenta ";
                Query query = entityManager.createNativeQuery(sql);
                query.setParameter("sifraConsenta", sifraConsenta);
                Object[] result = (Object[]) query.getSingleResult();
                if (StringUtils.equals((String) result[2], tppId)) {
                    consentRequestResponseDtoResult.setIsValid(true);
                    consentRequestResponseDtoResult.setConsentStatus((String) result[1]);
                } else {
                    consentRequestResponseDtoResult.setIsValid(false);
                    consentRequestResponseDtoResult.setErrorInformationDto(Psd2Util.setErrorInformationDto(RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unknown_consent", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                }
                if (!isExpiredByDate(((Date) result[3]).toLocalDate())) {
                    consentRequestResponseDtoResult.setIsValid(true);
                    consentRequestResponseDtoResult.setConsentStatus((String) result[1]);

                } else {
                    consentRequestResponseDtoResult.setIsValid(false);
                    consentRequestResponseDtoResult.setErrorInformationDto(Psd2Util.setErrorInformationDto("CONSENT_EXPIRED", new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_consent_expired", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                    updateConsentStatus(sifraConsenta, "expired", tppId);
                }

            } else {
                consentRequestResponseDtoResult.setIsValid(false);
                consentRequestResponseDtoResult.setErrorInformationDto(Psd2Util.setErrorInformationDto(FORMAT_ERROR.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_consent_tpp_mandatory", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
            }
        } catch (NoResultException nre) {
            consentRequestResponseDtoResult.setIsValid(false);
            consentRequestResponseDtoResult.setErrorInformationDto(Psd2Util.setErrorInformationDto(RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unknown_consent", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
        } catch (Exception e) {
            log.error("Exception is:", e);
        }
        return consentRequestResponseDtoResult;
    }

    /**
     * Validate ais Consent Request Account Number
     *
     * @param accountNumber
     * @return
     */
    public Boolean validateAisConsentRequestAccount(String accountNumber) throws AbitRuleException {
        Boolean isAccountValid = true;
        try {
            if (StringUtils.isNotBlank(accountNumber)) {
                //da li je račun u banci
                isAccountValid = psd2Util.isIbanBbanForConsentValid(accountNumber);
                if (isAccountValid) {
                    //da li je taj račun postoji u banci i da li je aktivan
                    //PartijaDto partijaDto = LookupHelper.getICoreDaoProxyRemote().getPartijaDtoByBrojPartije(accountNumber.substring(accountNumber.length() - 10)); // stari
                    PartijaDto partijaDto = coreSrvc.getPartijaDtoByBrojPartije(accountNumber); // prazni
                    if (partijaDto != null) {
                        if (StringUtils.isBlank(partijaDto.getStatus()) ||
                                (StringUtils.isNotBlank(partijaDto.getStatus()) && StringUtils.compare(partijaDto.getStatus(), Bassx2Constants.Core.PARTIJA_STATUPAR_OTVORENA) != 0)) {
                            isAccountValid = false;
                        }
                    } else {
                        isAccountValid = false;
                    }
                }
                //da li postoji IB korisnik (ukoliko ne postoji neće moći autorizirati Consent) TODO

            } else {
                isAccountValid = false;
            }
        } catch (Exception e) {
            log.error("Exception is:", e);
        }
        return isAccountValid;
    }

    /**
     * Validate ais Consent Request
     *
     * @param consentRequestResponseDto
     * @return
     */
    public ConsentRequestResponseDto validateCreateConsentRequestBody(ConsentRequestResponseDto consentRequestResponseDto) throws AbitRuleException {
        List<String> errorDescriptionList = new ArrayList<String>();
        //dohvat svih valuta
        //List<ValutaDto> valutaDtoList = LookupHelper.getICoreDaoProxyRemote().getValutaDtoList(); // stari
        //List<ValutaDto> valutaDtoList = null;
        Map<String, String> valutaMap = appConfig.currencyMap();

        //access
        if (consentRequestResponseDto.getAccess() == null) {
            consentRequestResponseDto.setIsValid(false);
            errorDescriptionList.add("Ne postoji access attribute. Access attribute je obavezan");
            consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(FORMAT_ERROR.getValue(), errorDescriptionList));
        } else {
            //accounts
            if (consentRequestResponseDto.getAccess().getAccounts() != null && !consentRequestResponseDto.getAccess().getAccounts().isEmpty()) {
                for (AccountReferenceDto accountReferenceDto : consentRequestResponseDto.getAccess().getAccounts()) {
                    if (StringUtils.isBlank(accountReferenceDto.getIban()) && StringUtils.isBlank(accountReferenceDto.getBban())) {
                        consentRequestResponseDto.setIsValid(false);
                        errorDescriptionList.add("Ne postoji iban ili bban u access parametru");
                        consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(FORMAT_ERROR.getValue(), errorDescriptionList));
                    } else {
                        //provjera u bazi
                        if (StringUtils.isNotBlank(accountReferenceDto.getIban())) {
                            if (!validateAisConsentRequestAccount(accountReferenceDto.getIban())) {
                                consentRequestResponseDto.setIsValid(false);
                                errorDescriptionList.add("Iban " + accountReferenceDto.getIban() + " je neispravan");
                                consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(FORMAT_ERROR.getValue(), errorDescriptionList));
                            }
                        }
                        if (StringUtils.isNotBlank(accountReferenceDto.getBban())) {
                            if (!validateAisConsentRequestAccount(accountReferenceDto.getBban())) {
                                consentRequestResponseDto.setIsValid(false);
                                errorDescriptionList.add("Bban " + accountReferenceDto.getBban() + " je neispravan");
                                consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(FORMAT_ERROR.getValue(), errorDescriptionList));
                            }
                        }
                        if (StringUtils.isNotBlank(accountReferenceDto.getCurrency())) {
                            if (valutaMap.get(accountReferenceDto.getCurrency()) == null) {
                                consentRequestResponseDto.setIsValid(false);
                                errorDescriptionList.add("Currency " + accountReferenceDto.getCurrency() + " je neispravan");
                                consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(FORMAT_ERROR.getValue(), errorDescriptionList));
                            }
                        }
                    }
                    if (StringUtils.isNotBlank(accountReferenceDto.getPan()) || StringUtils.isNotBlank(accountReferenceDto.getMaskedPan())) {
                        consentRequestResponseDto.setIsValid(false);
                        errorDescriptionList.add("Pan i maskedPan - nisu podaržani");
                        consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(PARAMETER_NOT_SUPPORTED.getValue(), errorDescriptionList));
                    }
                }
            }
            //balances
            if (consentRequestResponseDto.getAccess().getBalances() != null && !consentRequestResponseDto.getAccess().getBalances().isEmpty()) {
                for (AccountReferenceDto accountReferenceDto : consentRequestResponseDto.getAccess().getBalances()) {
                    if (StringUtils.isBlank(accountReferenceDto.getIban()) && StringUtils.isBlank(accountReferenceDto.getBban())) {
                        consentRequestResponseDto.setIsValid(false);
                        errorDescriptionList.add("Ne postoji iban ili bban u access parametru");
                        consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(FORMAT_ERROR.getValue(), errorDescriptionList));
                    } else {
                        //provjera u bazi
                        if (StringUtils.isNotBlank(accountReferenceDto.getIban())) {
                            if (!validateAisConsentRequestAccount(accountReferenceDto.getIban())) {
                                consentRequestResponseDto.setIsValid(false);
                                errorDescriptionList.add("Iban " + accountReferenceDto.getIban() + " je neispravan");
                                consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(FORMAT_ERROR.getValue(), errorDescriptionList));
                            }
                        }
                        if (StringUtils.isNotBlank(accountReferenceDto.getBban())) {
                            if (!validateAisConsentRequestAccount(accountReferenceDto.getBban())) {
                                consentRequestResponseDto.setIsValid(false);
                                errorDescriptionList.add("Bban " + accountReferenceDto.getBban() + " je neispravan");
                                consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(FORMAT_ERROR.getValue(), errorDescriptionList));
                            }
                        }
                        if (StringUtils.isNotBlank(accountReferenceDto.getCurrency())) {
                            if (valutaMap.get(accountReferenceDto.getCurrency()) == null) {
                                consentRequestResponseDto.setIsValid(false);
                                errorDescriptionList.add("Currency " + accountReferenceDto.getCurrency() + " je neispravan");
                                consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(FORMAT_ERROR.getValue(), errorDescriptionList));
                            }
                        }
                    }
                    if (StringUtils.isNotBlank(accountReferenceDto.getPan()) || StringUtils.isNotBlank(accountReferenceDto.getMaskedPan())) {
                        consentRequestResponseDto.setIsValid(false);
                        errorDescriptionList.add("Pan i maskedPan - nisu podaržani");
                        consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(PARAMETER_NOT_SUPPORTED.getValue(), errorDescriptionList));
                    }
                }
            }
            //transactions
            if (consentRequestResponseDto.getAccess().getTransactions() != null && !consentRequestResponseDto.getAccess().getTransactions().isEmpty()) {
                for (AccountReferenceDto accountReferenceDto : consentRequestResponseDto.getAccess().getTransactions()) {
                    if (StringUtils.isBlank(accountReferenceDto.getIban()) && StringUtils.isBlank(accountReferenceDto.getBban())) {
                        consentRequestResponseDto.setIsValid(false);
                        errorDescriptionList.add("Ne postoji iban ili bban u access parametru");
                        consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(FORMAT_ERROR.getValue(), errorDescriptionList));
                    } else {
                        //provjera u bazi
                        if (StringUtils.isNotBlank(accountReferenceDto.getIban())) {
                            if (!validateAisConsentRequestAccount(accountReferenceDto.getIban())) {
                                consentRequestResponseDto.setIsValid(false);
                                errorDescriptionList.add("Iban " + accountReferenceDto.getIban() + " je neispravan");
                                consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(FORMAT_ERROR.getValue(), errorDescriptionList));
                            }
                        }
                        if (StringUtils.isNotBlank(accountReferenceDto.getBban())) {
                            if (!validateAisConsentRequestAccount(accountReferenceDto.getBban())) {
                                consentRequestResponseDto.setIsValid(false);
                                errorDescriptionList.add("Bban " + accountReferenceDto.getBban() + " je neispravan");
                                consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(FORMAT_ERROR.getValue(), errorDescriptionList));
                            }
                        }
                        if (StringUtils.isNotBlank(accountReferenceDto.getCurrency()) && StringUtils.compare(accountReferenceDto.getCurrency(), "XXX") != 0) {
                            if (valutaMap.get(accountReferenceDto.getCurrency()) == null) {
                                consentRequestResponseDto.setIsValid(false);
                                errorDescriptionList.add("Valuta " + accountReferenceDto.getCurrency() + " je neispravna");
                                consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(FORMAT_ERROR.getValue(), errorDescriptionList));
                            }
                        }
                    }
                    if (StringUtils.isNotBlank(accountReferenceDto.getPan()) || StringUtils.isNotBlank(accountReferenceDto.getMaskedPan())) {
                        consentRequestResponseDto.setIsValid(false);
                        errorDescriptionList.add("Pan i maskedPan - nisu podaržani");
                        consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(PARAMETER_NOT_SUPPORTED.getValue(), errorDescriptionList));
                    }
                }
            }
            //availableAccounts
            if (StringUtils.isNotBlank(consentRequestResponseDto.getAccess().getAvailableAccounts())) {
                consentRequestResponseDto.setIsValid(false);
                errorDescriptionList.add("AvailableAccounts - nije podržano"); //TODO - čeka se odgovor HUB-a
                consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(PARAMETER_NOT_SUPPORTED.getValue(), errorDescriptionList));

//                if (StringUtils.compare(consentRequestResponseDto.getAccess().getAvailableAccounts(), "allAccounts") != 0) {
//                    consentRequestResponseDto.setIsValid(false);
//                    errorDescriptionList.add("AvailableAccounts - not supported!");
//
//                }
            }
            //allPsd2
            if (StringUtils.isNotBlank(consentRequestResponseDto.getAccess().getAllPsd2())) {
                consentRequestResponseDto.setIsValid(false);
                errorDescriptionList.add("AllPsd2 - nije podaržano"); ////TODO - čeka se odgovor HUB-a
                consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(PARAMETER_NOT_SUPPORTED.getValue(), errorDescriptionList));
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
                errorDescriptionList.add("Ne postoji account access (accounts, balances, transactions, availableAccounts, allPsd2");
                consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(FORMAT_ERROR.getValue(), errorDescriptionList));
            }
        }
//        netrebam jer ErrorInformationDto punim u uvjetima!!
//        if (!consentRequestResponseDto.getIsValid()) {
//            consentRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(BAD_REQUEST.getValue(), errorDescriptionList));
//        }
        return consentRequestResponseDto;
    }

    public ConsentAisDto formConsentAisDtoFromRequest(ConsentRequestResponseDto consentRequestResponseDto) throws AbitRuleException {
        ConsentAisDto consentAisDto = new ConsentAisDto();
        //consent
        consentAisDto.setFrequencyPerDayCon(consentRequestResponseDto.getFrequencyPerDay());
        consentAisDto.setPsuIdCon(StringUtils.isNoneBlank(consentRequestResponseDto.getPsuID()) ? consentRequestResponseDto.getPsuID() : null);
        consentAisDto.setRecurringIndicatorCon(consentRequestResponseDto.getRecurringIndicator());
        consentAisDto.setStateCon(RECEIVED.getValue());
        consentAisDto.setTppIdCon(consentRequestResponseDto.getTppID());
        consentAisDto.setToDateCon(Psd2Util.getPsd2DateFromString(consentRequestResponseDto.getValidUntil()));
        consentAisDto.setxRequestIdCon(consentRequestResponseDto.getxRequestID());
        consentAisDto.setCombinedServiceIndicator(consentRequestResponseDto.getCombinedServiceIndicator());
        consentAisDto.setTppFrequencyPerDayCon(getMinFrequencyPerDay(consentAisDto.getFrequencyPerDayCon()));
        consentAisDto.setTppUsageCounterCon(consentAisDto.getTppFrequencyPerDayCon());
        consentAisDto.setTppRedirectUri(consentRequestResponseDto.getTppRedirectUri());
        consentAisDto.setTppName(consentRequestResponseDto.getTppName());

        //consent accesses
        List<ConsentAccountAisDto> consentAccountAisDtoList = new ArrayList<>();
        String currentIban=null;
        String currentCustomerId=null;
        Map<Integer, Integer> mapSifraKomitenta = new HashMap<>();
        //accounts
        if (consentRequestResponseDto.getAccess().getAccounts() != null && !consentRequestResponseDto.getAccess().getAccounts().isEmpty()) {
            for (AccountReferenceDto accountReferenceDto : consentRequestResponseDto.getAccess().getAccounts()) {
                if (StringUtils.isNotBlank(accountReferenceDto.getIban()) || StringUtils.isNotBlank(accountReferenceDto.getBban())) {
                    ConsentAccountAisDto consentAccountAisDto = new ConsentAccountAisDto();
                    //iban
                    consentAccountAisDto.setIbanAcc(StringUtils.isNotBlank(accountReferenceDto.getIban()) ? accountReferenceDto.getIban() : accountReferenceDto.getBban());
                    //sifra partije
                    // PartijaDto partijaDto = LookupHelper.getICoreDaoProxyRemote().getPartijaDtoByBrojPartije(consentAccountAisDto.getIbanAcc().substring(consentAccountAisDto.getIbanAcc().length() - 10)); //stari
                    //PartijaDto partijaDto = coreSrvc.getPartijaDtoByBrojPartije(consentAccountAisDto.getIbanAcc().substring(consentAccountAisDto.getIbanAcc().length() - 10)); // prazni NE TREBA
                    consentAccountAisDto.setSifraPar(1);
                    if(currentIban == null || !currentIban.equals(accountReferenceDto.getIban()) ) {
                        currentCustomerId=coreSrvc.getCustomerIdByIban(accountReferenceDto.getIban());
                    }
                    currentIban=accountReferenceDto.getIban();
                    mapSifraKomitenta.put(Integer.parseInt(currentCustomerId), Integer.parseInt(currentCustomerId));
                    //valuta
                    if (StringUtils.isNotBlank(accountReferenceDto.getCurrency())) {
                        consentAccountAisDto.setCurrencyAcc(accountReferenceDto.getCurrency());
                    } else {
                        consentAccountAisDto.setCurrencyAcc("MVR");
                    }
                    //type
                    consentAccountAisDto.setTypeAcc(ACCOUNT.getValue());
                    consentAccountAisDtoList.add(consentAccountAisDto);
                }
            }
        }
        //balances
        if (consentRequestResponseDto.getAccess().getBalances() != null && !consentRequestResponseDto.getAccess().getBalances().isEmpty()) {
            for (AccountReferenceDto accountReferenceDto : consentRequestResponseDto.getAccess().getBalances()) {
                if (StringUtils.isNotBlank(accountReferenceDto.getIban()) || StringUtils.isNotBlank(accountReferenceDto.getBban())) {
                    ConsentAccountAisDto consentAccountAisDto = new ConsentAccountAisDto();
                    //iban
                    consentAccountAisDto.setIbanAcc(StringUtils.isNotBlank(accountReferenceDto.getIban()) ? accountReferenceDto.getIban() : accountReferenceDto.getBban());
                    //sifra partije
                    // PartijaDto partijaDto = LookupHelper.getICoreDaoProxyRemote().getPartijaDtoByBrojPartije(consentAccountAisDto.getIbanAcc().substring(consentAccountAisDto.getIbanAcc().length() - 10)); // stari
                    //PartijaDto partijaDto = coreSrvc.getPartijaDtoByBrojPartije(consentAccountAisDto.getIbanAcc().substring(consentAccountAisDto.getIbanAcc().length() - 10)); // prazni // ne treba
                    consentAccountAisDto.setSifraPar(1);
                    //mapSifraKomitenta.put(partijaDto.getSifraVlasnika(), partijaDto.getSifraVlasnika());
                    consentAccountAisDto.setSifraPar(1);
                    if(currentIban == null || !currentIban.equals(accountReferenceDto.getIban()) ) {
                        currentCustomerId=coreSrvc.getCustomerIdByIban(accountReferenceDto.getIban());
                    }
                    currentIban=accountReferenceDto.getIban();
                    mapSifraKomitenta.put(Integer.parseInt(currentCustomerId), Integer.parseInt(currentCustomerId));
                    //valuta
                    //valuta
                    if (StringUtils.isNotBlank(accountReferenceDto.getCurrency())) {
                        consentAccountAisDto.setCurrencyAcc(accountReferenceDto.getCurrency());
                    } else {
                        consentAccountAisDto.setCurrencyAcc("MVR");
                    }
                    //type
                    consentAccountAisDto.setTypeAcc(BALANCE.getValue());
                    consentAccountAisDtoList.add(consentAccountAisDto);
                }
            }
        }
        //transactions
        if (consentRequestResponseDto.getAccess().getTransactions() != null && !consentRequestResponseDto.getAccess().getTransactions().isEmpty()) {
            for (AccountReferenceDto accountReferenceDto : consentRequestResponseDto.getAccess().getTransactions()) {
                if (StringUtils.isNotBlank(accountReferenceDto.getIban()) || StringUtils.isNotBlank(accountReferenceDto.getBban())) {
                    ConsentAccountAisDto consentAccountAisDto = new ConsentAccountAisDto();
                    //iban
                    consentAccountAisDto.setIbanAcc(StringUtils.isNotBlank(accountReferenceDto.getIban()) ? accountReferenceDto.getIban() : accountReferenceDto.getBban());
                    //sifra partije
                    //PartijaDto partijaDto = LookupHelper.getICoreDaoProxyRemote().getPartijaDtoByBrojPartije(consentAccountAisDto.getIbanAcc().substring(consentAccountAisDto.getIbanAcc().length() - 10)); //stari
                    //PartijaDto partijaDto = coreSrvc.getPartijaDtoByBrojPartije(consentAccountAisDto.getIbanAcc().substring(consentAccountAisDto.getIbanAcc().length() - 10)); // prazni // ne treba
                    consentAccountAisDto.setSifraPar(1);
                    consentAccountAisDto.setSifraPar(1);
                    if(currentIban == null || !currentIban.equals(accountReferenceDto.getIban()) ) {
                        currentCustomerId=coreSrvc.getCustomerIdByIban(accountReferenceDto.getIban());
                    }
                    currentIban=accountReferenceDto.getIban();
                    mapSifraKomitenta.put(Integer.parseInt(currentCustomerId), Integer.parseInt(currentCustomerId));
                    //valuta
                    //valuta
                    if (StringUtils.isNotBlank(accountReferenceDto.getCurrency())) {
                        consentAccountAisDto.setCurrencyAcc(accountReferenceDto.getCurrency());
                    } else {
                        consentAccountAisDto.setCurrencyAcc("MVR");
                    }
                    //type
                    consentAccountAisDto.setTypeAcc(TRANSACTION.getValue());
                    consentAccountAisDtoList.add(consentAccountAisDto);
                }
            }
        }
        if (mapSifraKomitenta.size() != 1) {
            consentAisDto.setIsValid(false);
            consentAisDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(SERVICE_INVALID.getValue(), new ArrayList<String>(Arrays.asList("Različiti vlasnici računa"))));
        } else {
            consentAisDto.setIsValid(true);
            consentAisDto.setSifraKom((Integer) mapSifraKomitenta.keySet().toArray()[0]);
        }
        consentAisDto.setConsentAccountAisDtoList(consentAccountAisDtoList);

        return consentAisDto;
    }

    /**
     * Get ais Consent Request
     *
     * @param sifraConsenta
     * @return status
     */
    public ConsentRequestResponseDto getConsentReqestBySifra(Integer sifraConsenta, String tppId) throws AbitRuleException {
        ConsentRequestResponseDto consentRequestResponseDtoResult = new ConsentRequestResponseDto();
        try {
            if (sifraConsenta != null && StringUtils.isNotBlank(tppId)) {
                String sql = getBasicSql() + " where sifra_con = :sifraConsenta ";
                Query query = entityManager.createNativeQuery(sql);
                query.setParameter("sifraConsenta", sifraConsenta);
                List<Object[]> consentAccessList = query.getResultList();
                if (consentAccessList != null && !consentAccessList.isEmpty()) {
                    if (StringUtils.equals((String) consentAccessList.get(0)[12], tppId)) {
                        consentRequestResponseDtoResult = formConsentRequest(consentAccessList);
                    } else {
                        consentRequestResponseDtoResult.setIsValid(false);
                        consentRequestResponseDtoResult.setErrorInformationDto(Psd2Util.setErrorInformationDto(RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unknown_consent", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                    }
                } else {
                    consentRequestResponseDtoResult.setIsValid(false);
                    consentRequestResponseDtoResult.setErrorInformationDto(Psd2Util.setErrorInformationDto(RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unknown_consent", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                }
            } else {
                consentRequestResponseDtoResult.setIsValid(false);
                consentRequestResponseDtoResult.setErrorInformationDto(Psd2Util.setErrorInformationDto(FORMAT_ERROR.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_consent_tpp_mandatory", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
            }
        } catch (Exception e) {
            log.error("Exception is:", e);
        }
        return consentRequestResponseDtoResult;
    }

    private ConsentRequestResponseDto formConsentRequest(List<Object[]> consentAccessList) {
        ConsentRequestResponseDto consentRequestResponseDtoResult = null;
        DateFormat dateFormat = new SimpleDateFormat(Psd2Constants.DATE_PSD2_FORMAT);
        if (consentAccessList != null && !consentAccessList.isEmpty()) {
            consentRequestResponseDtoResult = new ConsentRequestResponseDto();
            AccountAccessDto accountAccessDto = new AccountAccessDto();
            List<AccountReferenceDto> accountsList = new ArrayList<>();
            List<AccountReferenceDto> balancesList = new ArrayList<>();
            List<AccountReferenceDto> transactionsList = new ArrayList<>();
            for (Object[] access : consentAccessList) {
                AccountReferenceDto accountReferenceDto = new AccountReferenceDto();
                if (((String) access[IDX_IBAN_ACC]).startsWith("HR")) {
                    accountReferenceDto.setIban((String) access[IDX_IBAN_ACC]);
                } else {
                    accountReferenceDto.setBban((String) access[IDX_IBAN_ACC]);
                }
                if (!((String) access[IDX_CURRENCY_ACC]).equals("MVR")) {
                    accountReferenceDto.setCurrency((String) access[IDX_CURRENCY_ACC]);
                }
                if (((String) access[IDX_TYPE_ACC]).equals(ACCOUNT.getValue())) {
                    accountsList.add(accountReferenceDto);
                } else if (((String) access[IDX_TYPE_ACC]).equals(BALANCE.getValue())) {
                    balancesList.add(accountReferenceDto);
                } else if (((String) access[IDX_TYPE_ACC]).equals(TRANSACTION.getValue())) {
                    transactionsList.add(accountReferenceDto);
                }
            }
            if (!accountsList.isEmpty()) {
                accountAccessDto.setAccounts(accountsList);
            }
            if (!balancesList.isEmpty()) {
                accountAccessDto.setBalances(balancesList);
            }
            if (!transactionsList.isEmpty()) {
                accountAccessDto.setTransactions(transactionsList);
            }
            consentRequestResponseDtoResult.setAccess(accountAccessDto);
            consentRequestResponseDtoResult.setRecurringIndicator(((Integer) consentAccessList.get(0)[IDX_RECURING_INDICATOR_CON]).equals(new Integer(1)) ? true : false);
            consentRequestResponseDtoResult.setValidUntil(dateFormat.format((Date) consentAccessList.get(0)[IDX_TO_DATE_CON]));
            consentRequestResponseDtoResult.setFrequencyPerDay((Integer) consentAccessList.get(0)[IDX_FREQUENCY_PER_DAY_CON]);
            consentRequestResponseDtoResult.setConsentStatus((String) consentAccessList.get(0)[IDX_STATE_CON]);
            consentRequestResponseDtoResult.setCombinedServiceIndicator(((Integer) consentAccessList.get(0)[IDX_COMBINED_SERVICE_INDICATOR_CON]).equals(new Integer(1)) ? true : false);
            consentRequestResponseDtoResult.setLastActionDate(dateFormat.format((Date) consentAccessList.get(0)[IDX_LAST_ACT_DAT_COM]));
            consentRequestResponseDtoResult.setIsValid(true);
        }
        return consentRequestResponseDtoResult;
    }

    /**
     * Get ConsentsAis list by specific komitent
     *
     * @param sifraKom
     * @return List of ConsentAisDto's
     * created by dKosutar
     */

    public List<ConsentAisDto> getConsentAisDtoListByKom(Integer sifraKom) throws AbitRuleException {
        List<ConsentAisDto> consentAisDtoList = new ArrayList<>();
        try {
            if (sifraKom != null) {
                String sql = getBasicSql() + "where komit_con = :sifraKomitenta";
                Query query = entityManager.createNativeQuery(sql);
                query.setParameter("sifraKomitenta", sifraKom);
                List<Object[]> consentAccessList = query.getResultList();
                if (consentAccessList != null) {
                    consentAisDtoList = formListOfConsents(consentAccessList);
                }
            }
        } catch (Exception e) {
            log.error("Exception is:", e);
        }
        return consentAisDtoList;
    }

    private List<ConsentAisDto> formListOfConsents(List<Object[]> consentAccessList) {

        List<ConsentAisDto> consentAisDtoList = null;
        ConsentAisDto consentAisDto;
        ConsentAccountAisDto consentAccountAisDto;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (consentAccessList != null && !consentAccessList.isEmpty()) {
            consentAisDtoList = new ArrayList<>();

            //Sort  consentAccessList by IDX_SIFRA_CON
            Set consentAccesSet = new HashSet<>();
            List<Object[]> filteredAccessList = new ArrayList<>();
            for (int i = 0; i < consentAccessList.size(); i++) {
                if (consentAccesSet.add(consentAccessList.get(i)[IDX_SIFRA_CON])) {
                    filteredAccessList.add(consentAccessList.get(i));
                }
            }
            for (int i = 0; i < filteredAccessList.size(); i++) {
                consentAisDto = new ConsentAisDto();
                List<ConsentAccountAisDto> consentAccountAisDtoList = new ArrayList<>();

                consentAisDto.setSifra((Integer) filteredAccessList.get(i)[IDX_SIFRA_CON]);
                //consentAisDto.setRecurringIndicatorCon(((Integer) filteredAccessList.get(i)[LIST_SQL_IDX_RECURING_INDICATOR_CON]).equals(new Integer(1)) ? true : false);

                //set creation date
                long timestamp = ((Timestamp) filteredAccessList.get(i)[IDX_CDATE_CON]).getTime();
                consentAisDto.setCreationDateCon(LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), TimeZone.getDefault().toZoneId()));

                consentAisDto.setToDateCon(((Date) filteredAccessList.get(i)[IDX_TO_DATE_CON]).toLocalDate());
                consentAisDto.setFrequencyPerDayCon((Integer) filteredAccessList.get(i)[IDX_FREQUENCY_PER_DAY_CON]);
                consentAisDto.setStateCon((String) filteredAccessList.get(i)[IDX_STATE_CON]);
                consentAisDto.setCombinedServiceIndicator(((Integer) filteredAccessList.get(i)[IDX_COMBINED_SERVICE_INDICATOR_CON]).equals(new Integer(1)) ? true : false);
                consentAisDto.setSifraKom((Integer) filteredAccessList.get(i)[IDX_KOMITENT_CON]);
                consentAisDto.setTppIdCon((String) filteredAccessList.get(i)[IDX_TPP_ID_CON]);
                consentAisDto.setRecurringIndicatorCon(((Integer) filteredAccessList.get(i)[IDX_RECURING_INDICATOR_CON]).equals(new Integer(1)) ? true : false);
                consentAisDto.setxRequestIdCon((String) filteredAccessList.get(i)[IDX_XREQID_CON]);
                consentAisDto.setPsuIdCon((String) filteredAccessList.get(i)[IDX_PSUID_CON]);
                consentAisDto.setLastActionDate(((Date) filteredAccessList.get(i)[IDX_LAST_ACT_DAT_COM]).toLocalDate());
                consentAisDto.setTppRedirectUri((String) filteredAccessList.get(i)[IDX_TPP_REDIRECT_URI]);

                //setup conset aist accaunt list
                for (int j = 0; j < consentAccessList.size(); j++) {
                    if (filteredAccessList.get(i)[IDX_SIFRA_CON].equals(consentAccessList.get(j)[IDX_SIFRA_CON])) {
                        consentAccountAisDto = new ConsentAccountAisDto();
                        consentAccountAisDto.setIbanAcc((String) consentAccessList.get(j)[IDX_IBAN_ACC]);
                        consentAccountAisDto.setCurrencyAcc((String) consentAccessList.get(j)[IDX_CURRENCY_ACC]);
                        consentAccountAisDto.setTypeAcc((String) consentAccessList.get(j)[IDX_TYPE_ACC]);
                        consentAccountAisDto.setSifraPar((Integer) consentAccessList.get(j)[IDX_PARTIJA_ACC]);
                        consentAccountAisDtoList.add(consentAccountAisDto);
                    }
                }
                consentAisDto.setConsentAccountAisDtoList(consentAccountAisDtoList);
                consentAisDtoList.add(consentAisDto);
            }
        }
        return consentAisDtoList;
        //TODO refactor the code using foreach insted of for loop!
    }

    /**
     * Update Consent Status
     *
     * @param sifraConsenta
     * @param statusConsenta
     */
    public ConsentRequestResponseDto updateConsentStatus(Integer sifraConsenta, String statusConsenta, String tppId) throws AbitRuleException {
        ConsentRequestResponseDto consentRequestResponseDtoResult = new ConsentRequestResponseDto();
        try {
            if (sifraConsenta != null && StringUtils.isNotBlank(statusConsenta) && StringUtils.isNotBlank(tppId)) {
                if (ConsentStatus.contains(statusConsenta)) {
                    ConsentAisDto consentAisDto = getConsentAisBySifra(sifraConsenta);
                    if (consentAisDto != null && StringUtils.equals(consentAisDto.getTppIdCon(), tppId)) {
                        if (!StringUtils.equals(consentAisDto.getStateCon(), statusConsenta)) {
                            String sql = "update psd2_consent_ais set state_con = :statusConsenta where sifra_con = :sifraConsenta ";
                            Query query = entityManager.createNativeQuery(sql);
                            query.setParameter("sifraConsenta", sifraConsenta);
                            query.setParameter("statusConsenta", statusConsenta);
                            query.executeUpdate();
                            consentRequestResponseDtoResult.setIsValid(true);
                        } else {
                            consentRequestResponseDtoResult.setIsValid(false);
                            consentRequestResponseDtoResult.setErrorInformationDto(Psd2Util.setErrorInformationDto(RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_consent_already_terminated", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                        }
                    } else {
                        consentRequestResponseDtoResult.setIsValid(false);
                        consentRequestResponseDtoResult.setErrorInformationDto(Psd2Util.setErrorInformationDto(RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unknown_consent", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                    }
                } else {
                    consentRequestResponseDtoResult.setIsValid(false);
                    consentRequestResponseDtoResult.setErrorInformationDto(Psd2Util.setErrorInformationDto(RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_consent_invalid_status", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                }
            } else {
                consentRequestResponseDtoResult.setIsValid(false);
                consentRequestResponseDtoResult.setErrorInformationDto(Psd2Util.setErrorInformationDto(RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_consent_tpp_state_mandatory", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
            }
        } catch (Exception e) {
            log.error("Exception is:", e);
        }
        return consentRequestResponseDtoResult;
    }
    public void updateConsentStatusToRevokedByPsu(Integer sifraConsenta) {
        String statusCon = REVOKED_BY_PSU.getValue();
        try {
            if (sifraConsenta != null) {
                String sql = "update psd2_consent_ais set state_con = :statusConsenta where sifra_con = :sifraConsenta ";
                Query query = entityManager.createNativeQuery(sql);
                query.setParameter("sifraConsenta", sifraConsenta);
                query.setParameter("statusConsenta", statusCon);
                query.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ConsentAisDto validateConsent(Integer consentID, String requestTpp, String ipAdressPsu, RequestType reqType) throws AbitRuleException, ConsentAISException {
        ConsentAisDto consentAisDto = null;
        if (consentID != null && !StringUtils.isBlank(requestTpp)) {
            //get consent from db
            consentAisDto = getConsentAisBySifra(consentID);
            //da li consent postoji
            if (consentAisDto != null) {
                consentAisDto.setIsValid(true);
                List<String> errorList = new ArrayList<>();
                //validate TPP (tpp koji je poslao request treba biti jednak tpp consenta) TODO
                if (!requestTpp.equals(consentAisDto.getTppIdCon())) {
                	throw new ConsentAISException.Builder().withCode(CONSENT_UNKNOWN)
            			.withErrors("psd2_unknown_consent")
            			.withReqType(reqType)
            			.build();                	
                }
                //validate date until
                if (isExpiredByDate(consentAisDto.getToDateCon())) {
                	throw new ConsentAISException.Builder().withCode(CONSENT_EXPIRED)
            			.withErrors("psd2_consent_expired")
            			.withReqType(reqType)
            			.build();                    
                }
                //validate status
                if (!consentAisDto.getStateCon().equals(VALID.getValue())) {
                	throw new ConsentAISException.Builder().withCode(RESOURCE_EXPIRED)
        			.withErrors("psd2_consent_invalid_status")
        			.withReqType(reqType)
        			.build();                    
                }
                if (consentAisDto.getIsValid()) {
                    if (StringUtils.isNotBlank(ipAdressPsu)) {
                        AuthorizationDto authorizationDto = null;
                        List<AuthorizationDto> authDtoList;
                        if (!consentAisDto.getRecurringIndicatorCon()) {
                            //ako je jednokratan implementiram TIMEOUT
                            List<ConsentActionAisDto> actionList = actionAisDao.getActionByConsentID(String.valueOf(consentAisDto.getSifra()));
                            if (actionList == null || actionList.isEmpty()) {
                                //znači da je consent okinut prvi put
                                authDtoList = authorizationDao.getAuthorizationByObjectId(consentAisDto.getSifra().toString(), Psd2Constants.AuthorizationObjectType.CONSENT_AUTHORIZATION.getValue(), null, null, null);
                                for (AuthorizationDto dto : authDtoList) {
                                    if (StringUtils.equals(dto.getScastAuth(), ScaStatus.FINALISED.getValue())) {
                                        authorizationDto = dto;
                                    }
                                }
                                //provjeravamo  jel od potpisa prošlo više od 5 min!! ==> graška!!!
                                if (LocalDateTime.now().isAfter(authorizationDto.getmDate().plusMinutes(5))) {
                                    updateConsentStatus(consentID, EXPIRED.getValue(), requestTpp);                                    
                                    throw new ConsentAISException.Builder().withCode(CONSENT_EXPIRED)
                    					.withErrors("psd2_non_recurring_con_timeout")
                    					.withReqType(reqType)
                    					.build();
                                }
                            } else {
                                //znači da se akcija za koju je dan consent NE izvršava prvi put
                                List<Integer> sifraActionTempList = new ArrayList<>();
                                for (ConsentActionAisDto actionCon : actionList) {
                                    sifraActionTempList.add(actionCon.getSifraAct());
                                }
                                Integer sifraLatestAction = Collections.max(sifraActionTempList);
                                ConsentActionAisDto lastAction = null;
                                for (ConsentActionAisDto actionCon : actionList) {
                                    if (actionCon.getSifraAct().equals(sifraLatestAction)) {
                                        lastAction = actionCon;
                                    }
                                }
                                //provjeravamo  jel od zadnje akcije prošlo više od 5 min!! ==> graška!!!
                                if (LocalDateTime.now().isAfter(lastAction.getDateAct().plusMinutes(5))) {
                                    updateConsentStatus(consentID, EXPIRED.getValue(), requestTpp);
                                    throw new ConsentAISException.Builder().withCode(CONSENT_EXPIRED)
                        				.withErrors("psd2_non_recurring_con_timeout")
                        				.withReqType(reqType)
                        				.build();
                                }
                            }

                        }
                    } else {
                        //ako je ponavlajuči a  prošlo je više od 90 dana od kreiranja consenta
                        if (consentAisDto.getIsValid() && LocalDate.now().isAfter(consentAisDto.getCreationDateCon().toLocalDate().plusDays(90))) {
                            updateConsentStatus(consentID, RECEIVED.getValue(), requestTpp);
                            throw new ConsentAISException.Builder().withCode(RESOURCE_EXPIRED)
                				.withErrors("psd2_recuring_con_expired")
                				.withReqType(reqType)
                				.build();
                        }
                    }
                    //validate frequency per day > 0
                    if (StringUtils.isBlank(ipAdressPsu) && consentAisDto.getLastActionDate().compareTo(LocalDate.now()) == 0) {
                        if (consentAisDto.getTppUsageCounterCon() < 1) {
                            //To je novo ali samo implementirano u AIS resursiama!!
                        	throw new ConsentAISException.Builder().withCode(ACCESS_EXCEEDED)
                				.withErrors("psd2_exceed_cons_acces")
                				.withReqType(reqType)
                				.build();                        	
                        }
                    }
                }
            } else {
            	throw new ConsentAISException.Builder().withCode(CONSENT_UNKNOWN)
    				.withErrors("psd2_unknown_consent")
    				.withReqType(reqType)
    				.build();
            }
        } else {
        	throw new ConsentAISException.Builder().withCode(FORMAT_ERROR)
				.withErrors("psd2_consent_tpp_mandatory")
				.withReqType(reqType)
				.build();            
        }
        return consentAisDto;
    }

    private void validateBeforeEdit(ConsentAisDto dto) {
        //TODO
    }

    public Integer getMinFrequencyPerDay(Integer frequncyPerDay) throws AbitRuleException {
        // Integer frequencyParam = Integer.valueOf(LookupHelper.getICoreDaoProxyRemote().getVrijednostParametraBySifra("TPP_FREQ")); // stari
        // TODO PARAMETRI
        Integer frequencyParam = appConfig.tppFrequencyPerday();//Integer.valueOf(coreSrvc.getVrijednostParametraBySifra("TPP_FREQ")); // prazni
        return Integer.min(frequncyPerDay, frequencyParam);
    }

    public Boolean checkOnExperation(ConsentAisDto consentAisDto, String tppId) throws AbitRuleException {
        Boolean isForUpdate = false;
        if (consentAisDto != null && isExpiredByDate(consentAisDto.getToDateCon()) && !consentAisDto.getStateCon().equals(EXPIRED.toString()) && consentAisDto.getTppIdCon().equals(tppId)) {
            isForUpdate = true;
        }
        return isForUpdate;
    }

    public boolean isExpiredByDate(LocalDate toDate) {
        return LocalDate.now().compareTo(toDate) > 0;
    }

    public void updateStatusOnRecurringConsents(ConsentAisDto dto) {
        try {
            //get all recurring consents from db with same comit_con && tppid
            String sql = "SELECT sifra_con from psd2_consent_ais where komit_con = :komit_con and tppid_con = :tppid_con and recur_con = 1 and state_con = :status";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("komit_con", dto.getSifraKom());
            query.setParameter("tppid_con", dto.getTppIdCon());
            query.setParameter("status", VALID.getValue());
            List<Integer> resultList = query.getResultList();
            if (resultList != null && !resultList.isEmpty()) {
                String updateSql = "update psd2_consent_ais set state_con = :statusConsenta where sifra_con in (:sifraConsenta)";
                Query upadeQuery = entityManager.createNativeQuery(updateSql);
                upadeQuery.setParameter("statusConsenta", TERMINATED_BY_TPP.getValue());
                upadeQuery.setParameter("sifraConsenta", resultList);
                upadeQuery.executeUpdate();
            }
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    public void updateSmartLock(List<Integer> nalozi, Integer vremenskiOkvir) throws AbitRuleException {
        try {
            String sql = "update psd2_consent_ais set smloc_con = :timer where sifra_con in :nalozi";
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.SECOND, vremenskiOkvir);
            entityManager.createNativeQuery(sql)
                    .setParameter("timer", cal.getTime())
                    .setParameter("nalozi", nalozi)
                    .executeUpdate();
        } catch (Exception ex) {
            throw new AbitRuleException(ex.getMessage());
        }
    }
}
