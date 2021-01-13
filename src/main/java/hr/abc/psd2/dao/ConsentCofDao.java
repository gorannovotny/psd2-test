package hr.abc.psd2.dao;

import static hr.abc.psd2.model.ConsentStatus.TERMINATED_BY_TPP;
import static hr.abc.psd2.model.ConsentStatus.VALID;
import static hr.abc.psd2.model.ConsentTypeAccess.FUNDS;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.model.ConsentStatus;
import hr.abc.psd2.model.ConsentTypeAccess;
import hr.abc.psd2.model.dto.ConsentCofRequestResponseDto;
import hr.abc.psd2.model.dto.cof.ConsentAccountCofDto;
import hr.abc.psd2.model.dto.cof.ConsentCofDto;
import hr.abc.psd2.model.dto.core.PartijaDto;
import hr.abc.psd2.model.dto.error.ErrorCode;
import hr.abc.psd2.model.entity.psd2.ConsentCof;
import hr.abc.psd2.resources.ws.impl.ConsentCofResources;
import hr.abc.psd2.service2.ICoreSrvc;
import hr.abc.psd2.util.Bassx2Constants;
import hr.abc.psd2.util.GenericBassxConstants;
import hr.abc.psd2.util.InternationalizationUtil;
import hr.abc.psd2.util.Psd2Util;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Dependent
@Transactional
public class ConsentCofDao implements Serializable {

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
    private static final int IDX_TPP_NAME = 20;

    @Inject
    private AuthorizationDao authorizationDao;

    @Inject
    private EntityManager entityManager;

    @Inject
    private ICoreSrvc coreSrvc;

    @Inject Psd2Util psd2Util;

       private String getBasicSql() {
        return "select sifra_con, recur_con, todat_con, frequ_con, state_con, comsr_con, sifra_acc, iban_acc, curr_acc, type_acc, " +
                "komit_con, parti_acc, tppid_con, cdate_con, reqid_con, psuid_con, lacda_con, uctpp_con, tppfr_con, tppre_con, tppna_con " +
                "from psd2_consent_cof consent " +
                "join psd2_consent_access_cof accesss on accesss.sicon_acc = consent.sifra_con ";
    }

    public ConsentCofDto createConnsent(ConsentCofDto consentCofDto) throws AbitRuleException {
        String sqlInsertConsentCof = "insert into psd2_consent_cof(state_con, reqid_con, psuid_con, tppid_con, komit_con, lacda_con, tppre_con, tppna_con, cdate_con) " +
                " values (:state_con, :reqid_con, :psuid_con, :tppid_con, :komit_con, :lacda_con, :tppre_con, :tppna_con, :cdate_con)";
        try {
            Query queryInsertConsentAis = entityManager.createNativeQuery(sqlInsertConsentCof);
            queryInsertConsentAis.setParameter("state_con", consentCofDto.getStateCon());
            queryInsertConsentAis.setParameter("reqid_con", consentCofDto.getxRequestIdCon());
            queryInsertConsentAis.setParameter("psuid_con", consentCofDto.getPsuIdCon());
            queryInsertConsentAis.setParameter("tppid_con", consentCofDto.getTppIdCon());
            queryInsertConsentAis.setParameter("komit_con", consentCofDto.getSifraKom());
            queryInsertConsentAis.setParameter("lacda_con", Date.valueOf(LocalDate.now()));
            queryInsertConsentAis.setParameter("tppre_con", consentCofDto.getTppRedirectUri());
            queryInsertConsentAis.setParameter("tppna_con", consentCofDto.getTppName());
            queryInsertConsentAis.setParameter("cdate_con", Timestamp.valueOf(LocalDateTime.now()));

            //execute
            queryInsertConsentAis.executeUpdate();
            //fetch execuded
            String sqlPk = "SELECT @@IDENTITY";
            Object primaryKey = entityManager.createNativeQuery(sqlPk).getSingleResult();
            consentCofDto.setSifra((((BigDecimal) primaryKey).intValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return consentCofDto;
    }

    public void insertConsentAccountCof(ConsentCofDto consentCofDto) {
        String sqlInsertConsentAccountCof = "insert into psd2_consent_access_cof (iban_acc, type_acc, sicon_acc, parti_acc)" +
                " values (:iban_acc, :type_acc, :sicon_acc, :parti_acc)";
        try {
            Query queryInsertConsentAccessCof = entityManager.createNativeQuery(sqlInsertConsentAccountCof);
            queryInsertConsentAccessCof.setParameter("iban_acc", consentCofDto.getConsentAccountCofDto().getIbanAcc());
            queryInsertConsentAccessCof.setParameter("type_acc", FUNDS.getValue());
            queryInsertConsentAccessCof.setParameter("sicon_acc", consentCofDto.getSifra());
            queryInsertConsentAccessCof.setParameter("parti_acc", consentCofDto.getConsentAccountCofDto().getSifraPar());
            queryInsertConsentAccessCof.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ConsentCofRequestResponseDto updateConsentCofStatus(Integer sifraConsenta, String statusConsenta, String tppId) throws AbitRuleException {
        ConsentCofRequestResponseDto consentCofRequestResponseDtoResult = new ConsentCofRequestResponseDto();
        try {
            if (sifraConsenta != null && StringUtils.isNotBlank(statusConsenta) && StringUtils.isNotBlank(tppId)) {
                if (ConsentStatus.contains(statusConsenta)) {
                    ConsentCofDto consentCofDto = getConsentCofBySifra(sifraConsenta);
                    if (consentCofDto != null && StringUtils.equals(consentCofDto.getTppIdCon(), tppId)) {
                        if (!StringUtils.equals(consentCofDto.getStateCon(), statusConsenta)) {
                            String sql = "update psd2_consent_cof set state_con = :statusConsenta where sifra_con = :sifraConsenta ";
                            Query query = entityManager.createNativeQuery(sql);
                            query.setParameter("sifraConsenta", sifraConsenta);
                            query.setParameter("statusConsenta", statusConsenta);
                            query.executeUpdate();
                            consentCofRequestResponseDtoResult.setValid(true);
                        } else {
                            consentCofRequestResponseDtoResult.setValid(false);
                            consentCofRequestResponseDtoResult.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_EXPIRED.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_consent_already_terminated", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                        }
                    } else {
                        consentCofRequestResponseDtoResult.setValid(false);
                        consentCofRequestResponseDtoResult.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unknown_consent", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                    }
                } else {
                    consentCofRequestResponseDtoResult.setValid(false);
                    consentCofRequestResponseDtoResult.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_consent_invalid_status", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));

                }

            } else {
                consentCofRequestResponseDtoResult.setValid(false);
                consentCofRequestResponseDtoResult.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_consent_tpp_state_mandatory", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return consentCofRequestResponseDtoResult;
    }

    public ConsentCofDto getConsentCofBySifra(Integer sifraConsenta) throws AbitRuleException {
        ConsentCofDto consentCofDto = null;
        try {
            if (sifraConsenta != null) {
                String sql = getBasicSql() + " where sifra_con = :sifraConsenta ";
                Query query = entityManager.createNativeQuery(sql);
                query.setParameter("sifraConsenta", sifraConsenta);
                List<Object[]> consentAccessList = query.getResultList();
                if (consentAccessList != null && !consentAccessList.isEmpty()) {
                    consentCofDto = formConsentCofDto(consentAccessList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return consentCofDto;
    }

    public List<ConsentCofDto> getConsentCofBySifraList(List<Integer> paymentId) {
        List<ConsentCofDto> consentCofDtoList = null;
        try {
            if (paymentId != null) {
                String sql = getBasicSql() + " where sifra_con in :sifra_con ";
                Query query = entityManager.createNativeQuery(sql);
                query.setParameter("sifra_con", paymentId);
                List<Object[]> consentObjectList = (List<Object[]>) query.getResultList();
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
                    for (Map.Entry<Integer, List<Object[]>> tmpEntry : mapConsent.entrySet()) {
                        if (consentCofDtoList == null) consentCofDtoList = new ArrayList<>();
                        consentCofDtoList.add(formConsentCofDto(tmpEntry.getValue()));
                    }
                }
            }

        } catch (NoResultException nre) {
            consentCofDtoList = null;
        } catch (Exception e) {
            log.error("Exception is:", e);
        }
        return consentCofDtoList;
    }

    public List<ConsentCofDto> getConsentCofDtoListByKom(Integer sifraKom) throws AbitRuleException {
        List<ConsentCofDto> consentCofDtoList = new ArrayList<>();
        try {
            if (sifraKom != null) {
                String sql = getBasicSql() + "where komit_con = :sifraKomitenta";
                Query query = entityManager.createNativeQuery(sql);
                query.setParameter("sifraKomitenta", sifraKom);
                List<Object[]> consentAccessList = query.getResultList();
                if (consentAccessList != null) {
                    consentCofDtoList = formListOfConsentsCof(consentAccessList);
                }
            }
        } catch (Exception e) {
            log.error("Exception is:", e);
        }
        return consentCofDtoList;
    }

    public List<ConsentCofDto> getConsentCofDtoListByIban(String Iban) throws AbitRuleException {
        List<ConsentCofDto> consentCofDtoList = new ArrayList<>();
        try {
            if (Iban != null) {
                String sql = getBasicSql() + " where iban_acc = :iban_acc ";
                Query query = entityManager.createNativeQuery(sql);
                query.setParameter("iban_acc", Iban);
                List<Object[]> consentAccessList = query.getResultList();
                if (consentAccessList != null) {
                    consentCofDtoList = formListOfConsentsCof(consentAccessList);
                }
            }
        } catch (Exception e) {
            log.error("Exception is:", e);
        }
        return consentCofDtoList;
    }

    private List<ConsentCofDto> formListOfConsentsCof(List<Object[]> consentAccessList) {

        List<ConsentCofDto> consentCofDtoList = null;
        ConsentCofDto consentCofDto;
        ConsentAccountCofDto consentAccountCofDto;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        if (consentAccessList != null && !consentAccessList.isEmpty()) {
            consentCofDtoList = new ArrayList<>();

            //Sort  consentAccessList by IDX_SIFRA_CON
            Set consentAccesSet = new HashSet<>();
            List<Object[]> filteredAccessList = new ArrayList<>();
            for (int i = 0; i < consentAccessList.size(); i++) {
                if (consentAccesSet.add(consentAccessList.get(i)[IDX_SIFRA_CON])) {
                    filteredAccessList.add(consentAccessList.get(i));
                }
            }
            for (int i = 0; i < filteredAccessList.size(); i++) {
                consentCofDto = new ConsentCofDto();
                List<ConsentAccountCofDto> consentAccountCofDtoList = new ArrayList<>();

                consentCofDto.setSifra((Integer) filteredAccessList.get(i)[IDX_SIFRA_CON]);
                //consentAisDto.setRecurringIndicatorCon(((Integer) filteredAccessList.get(i)[LIST_SQL_IDX_RECURING_INDICATOR_CON]).equals(new Integer(1)) ? true : false);

                //set creation date
                if (filteredAccessList.get(i)[IDX_CDATE_CON] != null) {
                    long timestamp = ((Timestamp) filteredAccessList.get(i)[IDX_CDATE_CON]).getTime();
                    consentCofDto.setCreationDateCon(LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), TimeZone.getDefault().toZoneId()));
                }

                if (filteredAccessList.get(i)[IDX_TO_DATE_CON] != null) {
                    consentCofDto.setToDateCon(((Date) filteredAccessList.get(i)[IDX_TO_DATE_CON]).toLocalDate());
                }
                consentCofDto.setFrequencyPerDayCon((Integer) filteredAccessList.get(i)[IDX_FREQUENCY_PER_DAY_CON]);
                consentCofDto.setStateCon((String) filteredAccessList.get(i)[IDX_STATE_CON]);

                if (filteredAccessList.get(i)[IDX_COMBINED_SERVICE_INDICATOR_CON] != null) {
                    consentCofDto.setCombinedServiceIndicator(((Integer) filteredAccessList.get(i)[IDX_COMBINED_SERVICE_INDICATOR_CON]).equals(new Integer(1)) ? true : false);
                }

                if (filteredAccessList.get(i)[IDX_TPP_NAME] != null) {
                    consentCofDto.setTppName((String) filteredAccessList.get(i)[IDX_TPP_NAME]);
                }

                consentCofDto.setSifraKom((Integer) filteredAccessList.get(i)[IDX_KOMITENT_CON]);
                consentCofDto.setTppIdCon((String) filteredAccessList.get(i)[IDX_TPP_ID_CON]);
                if (filteredAccessList.get(i)[IDX_RECURING_INDICATOR_CON] != null) {
                    consentCofDto.setRecurringIndicatorCon(((Integer) filteredAccessList.get(i)[IDX_RECURING_INDICATOR_CON]).equals(new Integer(1)) ? true : false);
                }
                consentCofDto.setxRequestIdCon((String) filteredAccessList.get(i)[IDX_XREQID_CON]);
                consentCofDto.setPsuIdCon((String) filteredAccessList.get(i)[IDX_PSUID_CON]);
                consentCofDto.setLastActionDate(((Date) filteredAccessList.get(i)[IDX_LAST_ACT_DAT_COM]).toLocalDate());
                consentCofDto.setTppRedirectUri((String) filteredAccessList.get(i)[IDX_TPP_REDIRECT_URI]);

                //setup conset aist accaunt list
                for (int j = 0; j < consentAccessList.size(); j++) {
                    if (filteredAccessList.get(i)[IDX_SIFRA_CON].equals(consentAccessList.get(j)[IDX_SIFRA_CON])) {
                        consentAccountCofDto = new ConsentAccountCofDto();
                        consentAccountCofDto.setIbanAcc((String) consentAccessList.get(j)[IDX_IBAN_ACC]);
                        consentAccountCofDto.setTypeAcc((String) consentAccessList.get(j)[IDX_TYPE_ACC]);
                        consentAccountCofDto.setSifraPar((Integer) consentAccessList.get(j)[IDX_PARTIJA_ACC]);
                        consentAccountCofDtoList.add(consentAccountCofDto);
                    }
                }
                consentCofDto.setConsentAccountCofDtoList(consentAccountCofDtoList);
                consentCofDtoList.add(consentCofDto);
            }
        }
        return consentCofDtoList;
    }

    private ConsentCofDto formConsentCofDto(List<Object[]> consentAccessList) {
        ConsentCofDto consentCofDto = null;
        if (consentAccessList != null && !consentAccessList.isEmpty()) {
            consentCofDto = new ConsentCofDto();
            consentCofDto.setSifra((Integer) consentAccessList.get(0)[IDX_SIFRA_CON]);
            consentCofDto.setStateCon((String) consentAccessList.get(0)[IDX_STATE_CON]);
            consentCofDto.setTppIdCon((String) consentAccessList.get(0)[IDX_TPP_ID_CON]);
            consentCofDto.setxRequestIdCon((String) consentAccessList.get(0)[IDX_XREQID_CON]);
            consentCofDto.setLastActionDate(((Date) consentAccessList.get(0)[IDX_LAST_ACT_DAT_COM]).toLocalDate());
            consentCofDto.setSifraKom((Integer) consentAccessList.get(0)[IDX_KOMITENT_CON]);
            consentCofDto.setPsuIdCon((String) consentAccessList.get(0)[IDX_PSUID_CON]);
            consentCofDto.setPsuIdCon((String) consentAccessList.get(0)[IDX_TPP_REDIRECT_URI]);
            consentCofDto.setCreationDateCon(((Timestamp) consentAccessList.get(0)[IDX_CDATE_CON]).toLocalDateTime());
            consentCofDto.setTppName((String) consentAccessList.get(0)[IDX_TPP_NAME] != null ? (String) consentAccessList.get(0)[IDX_TPP_NAME] : null);

            //account
            ConsentAccountCofDto accountCofDto = new ConsentAccountCofDto();
            accountCofDto.setSifraAcc((Integer) consentAccessList.get(0)[IDX_SIFRA_ACC]);
            accountCofDto.setIbanAcc((String) consentAccessList.get(0)[IDX_IBAN_ACC]);
            accountCofDto.setTypeAcc((String) consentAccessList.get(0)[IDX_TYPE_ACC]);
            accountCofDto.setSifraPar((Integer) consentAccessList.get(0)[IDX_PARTIJA_ACC]);
            accountCofDto.setSifraConAcc((Integer) consentAccessList.get(0)[IDX_SIFRA_CON]);

            consentCofDto.setConsentAccountCofDto(accountCofDto);
            if (accountCofDto != null) {
                List<ConsentAccountCofDto> consentAccessCofs = new ArrayList<>();
                consentAccessCofs.add(accountCofDto);
                consentCofDto.setConsentAccountCofDtoList(consentAccessCofs);
            }
        }
        return consentCofDto;
    }

    public void edit(ConsentCofDto dto) throws AbitRuleException {
        validateBeforeEdit(dto);
        ConsentCof entity = formEntity(dto);
        entityManager.merge(entity);
        entityManager.flush();
    }

    public ConsentCofDto getConsentCofByIban(String Iban) {
        ConsentCofDto consentCofDto = null;
        try {
            if (StringUtils.isNotBlank(Iban)) {
                String sql = getBasicSql() + " where iban_acc = :iban_acc ";
                Query query = entityManager.createNativeQuery(sql);
                query.setParameter("iban_acc", Iban);
                List<Object[]> consentAccessList = query.getResultList();
                if (consentAccessList != null && !consentAccessList.isEmpty()) {
                    consentCofDto = formConsentCofDto(consentAccessList);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return consentCofDto;

    }

    private void validateBeforeEdit(ConsentCofDto dto) {
    }

    protected ConsentCof formEntity(ConsentCofDto dto) {
        ConsentCof entity = null;
        if (dto != null) {
            entity = new ConsentCof();
            entity.setSifraCon(dto.getSifra());
            entity.setStateCon(dto.getStateCon());
            entity.setFrequCon(dto.getFrequencyPerDayCon());
            entity.setCdateCon(dto.getCreationDateCon() != null ? Timestamp.valueOf(dto.getCreationDateCon()) : null);
            entity.setTodatCon(dto.getToDateCon() != null ? Date.valueOf(dto.getToDateCon()) : null);
            entity.setRecurCon(dto.getRecurringIndicatorCon() != null ? dto.getRecurringIndicatorCon() ? new Integer(1) : new Integer(0) : null);
            entity.setReqidCon(dto.getxRequestIdCon());
            entity.setPsuidCon(dto.getPsuIdCon());
            entity.setTppidCon(dto.getTppIdCon());
            entity.setTppfrCon(dto.getTppFrequencyPerDayCon());
            entity.setLacda_con(Date.valueOf(dto.getLastActionDate()));
            entity.setComsr_Con(dto.getCombinedServiceIndicator() != null ? dto.getCombinedServiceIndicator() ? new Integer(1) : new Integer(0) : null);
            entity.setUctppCon(dto.getTppUsageCounterCon());
            entity.setKomitCon(dto.getSifraKom());
            entity.setTppRedirectUri(dto.getTppRedirectUri());
        }
        return entity;
    }

    /**
     * Validate cof Consent Request Account Number
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
                    PartijaDto partijaDto = coreSrvc.getPartijaDtoByBrojPartije(accountNumber.substring(accountNumber.length() - 10)); // prazni
//                    if (partijaDto != null) {
//                        if (StringUtils.isBlank(partijaDto.getStatus()) ||
//                                (StringUtils.isNotBlank(partijaDto.getStatus()) && StringUtils.compare(partijaDto.getStatus(), Bassx2Constants.Core.PARTIJA_STATUPAR_OTVORENA) != 0)) {
//                            isAccountValid = false;
//                        }
//                    } else {
//                        isAccountValid = false;
//                    }
                }
                //da li postoji IB korisnik (ukoliko ne postoji neće moći autorizirati Consent) TODO

            } else {
                isAccountValid = false;
            }
        } catch (Exception e) {
            // log.error("Exception is:", e);
        }
        return isAccountValid;
    }

    public void updateStatusOnAllValidCofConsents(ConsentCofDto dto) {
        try {
            //get all consents from db with same comit_con && tppid
            String sql = "SELECT sifra_con from psd2_consent_cof where komit_con = :komit_con and tppid_con = :tppid_con and state_con = :status";
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("komit_con", dto.getSifraKom());
            query.setParameter("tppid_con", dto.getTppIdCon());
            query.setParameter("status", VALID.getValue());
            List<Integer> resultList = query.getResultList();
            if (resultList != null && !resultList.isEmpty()) {
                String updateSql = "update psd2_consent_cof set state_con = :statusConsenta where sifra_con in (:sifraConsenta)";
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
            String sql = "update psd2_consent_cof set smloc_con = :timer where sifra_con in :nalozi";
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
