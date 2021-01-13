package hr.abc.psd2.dao;


import static hr.abc.psd2.model.ConsentStatus.RECEIVED;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;

import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.model.dto.ais.ConsentAisDto;
import hr.abc.psd2.model.dto.authorization.AuthorizationDto;
import hr.abc.psd2.model.dto.authorization.AuthorizationListResponseDto;
import hr.abc.psd2.model.dto.authorization.CreateAuthorizationRequestDto;
import hr.abc.psd2.model.dto.authorization.ScaStatus;
import hr.abc.psd2.model.dto.authorization.StartAuthorizationResponseDto;
import hr.abc.psd2.model.dto.cof.ConsentCofDto;
import hr.abc.psd2.model.dto.error.ErrorCode;
import hr.abc.psd2.model.dto.pis.TppNalogPlatniDto;
import hr.abc.psd2.model.entity.psd2.Authorization;
import hr.abc.psd2.service2.ICoreSrvc;
import hr.abc.psd2.util.Psd2Constants;
import hr.abc.psd2.util.Psd2Util;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Dependent
@Transactional
public class AuthorizationDao implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final int IDX_ID_AUT = 0;
    private static final int IDX_SCAST_AUT = 1;
    private static final int IDX_SCAMT_AUT = 2;
    private static final int IDX_CDATE_AUT = 3;
    private static final int IDX_OBJCT_AUT = 4;
    private static final int IDX_OTYPE_AUT = 5;
    private static final int IDX_ATYPE_AUT = 6;
    private static final int IDX_TPPID_AUT = 7;
    private static final int IDX_PSUID_AUT = 8;
    private static final int IDX_REQID_AUT = 9;
    private static final int IDX_LINK_AUT = 10;
    private static final int IDX_TODAT_AUT = 11;
    private static final int IDX_MDATE_AUT = 12;
    private static final int IDX_OHASH_AUT = 13;


    private
    @Inject
    Psd2UtilDao psd2UtilDao;
    //resources
    @Inject
    private EntityManager entityManager;

    @Inject
    private ICoreSrvc coreSrvc;

    private String getBasicSql() {
        return "SELECT id_aut, scast_aut, scamt_aut, cdate_aut, objct_aut, otype_aut, atype_aut, tppid_aut, psuid_aut, reqid_aut, link_aut, todat_aut, m_date, ohash_aut FROM psd2_authorization";
    }

    public AuthorizationDto getAuthorizationByID(Integer authId) {
        AuthorizationDto authorizationDto = null;
        try {
            if (authId != null) {
                String sql = getBasicSql() + " WHERE id_aut = :id_aut";
                Query query = entityManager.createNativeQuery(sql);
                query.setParameter("id_aut", authId);
                List<Object[]> authorizationList = query.getResultList();
                if (authorizationList != null && !authorizationList.isEmpty()) {
                    authorizationDto = formAuthorizationDto(authorizationList).get(0);
                }
            }
        } catch (Exception e) {
        	log.error("Exception is:", e);
        }
        return authorizationDto;
    }

    public AuthorizationDto getAuthorizationByLink(String link, Integer objectType) {
        AuthorizationDto authorizationDtos = null;
        try {
            if (link != null) {
                String sql = getBasicSql() + " WHERE link_aut = :link_aut ";
                sql += objectType != null ? " and otype_aut = :otype " : "";
                Query query = entityManager.createNativeQuery(sql);
                query.setParameter("link_aut", link);
                if (objectType != null) query.setParameter("otype", objectType);
                Object[] authorizationList = (Object[]) query.getSingleResult();
                if (authorizationList != null) {
                    authorizationDtos = formAuthorizationDto(authorizationList);
                }
            }
        } catch (Exception e) {
        	log.error("Exception is:", e);
        }
        return authorizationDtos;
    }

    public List<AuthorizationDto> getAuthorizationsByLink(String link, Integer objectType) {
        List<AuthorizationDto> authorizationDtos = null;
        try {
            if (link != null) {
                String sql = getBasicSql() + " WHERE link_aut = :link_aut ";
                sql += objectType != null ? " and otype_aut = :otype " : "";
                Query query = entityManager.createNativeQuery(sql);
                query.setParameter("link_aut", link);
                if (objectType != null) query.setParameter("otype", objectType);
                List<Object[]> authorizationList = (List<Object[]>) query.getResultList();
                if (authorizationList != null) {
                    authorizationDtos = new ArrayList<>();
                    for (Object[] tmpObj : authorizationList) {
                        authorizationDtos.add(formAuthorizationDto(tmpObj));
                    }
                }
            }
        } catch (Exception e) {
        	log.error("Exception is:", e);
        }
        return authorizationDtos;
    }

    public List<AuthorizationDto> getAuthorizationsByDatoteka(Integer datotekaId, Integer authorizationType, Integer authorizationId) {
        List<AuthorizationDto> authorizationDtos = null;
        try {
            if (datotekaId != null && authorizationType != null) {
                String sql = getBasicSql() +
                        " join psd2_nalog_tpp nalog on nalog.sifra_tpp = psd2_authorization.objct_aut" +
                        " join psd2_datoteka dat on dat.sifra_dat = nalog.datot_tpp" +
                        " where psd2_authorization.otype_aut = :objectType " +
                        " and psd2_authorization.atype_aut = :authorizationType " +
                        " and dat.sifra_dat = :datotekaId ";
                if (authorizationId != null) {
                    sql = sql + " and id_aut = :authorizationId ";
                }

                Query query = entityManager.createNativeQuery(sql);
                query.setParameter("authorizationType", authorizationType);
                query.setParameter("datotekaId", datotekaId);
                query.setParameter("objectType", Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue());
                if (authorizationId != null) {
                    query.setParameter("authorizationId", authorizationId);
                }
                List<Object[]> authorizationList = (List<Object[]>) query.getResultList();
                if (authorizationList != null) {
                    authorizationDtos = new ArrayList<>();
                    for (Object[] tmpObj : authorizationList) {
                        authorizationDtos.add(formAuthorizationDto(tmpObj));
                    }
                }
            }
        } catch (Exception e) {
        	log.error("Exception is:", e);
        }
        return authorizationDtos;
    }

    public List<AuthorizationDto> getAuthorizationByObjectId(String objectIdAuth,
                                                             Integer objTypeAuth,
                                                             String paymentSrv,
                                                             String paymentProduct,
                                                             Integer authorizationType) {
        List<AuthorizationDto> resulAuthtList = null;
        try {
            String sql = getBasicSql();
            if (objectIdAuth != null && (objTypeAuth.equals(Psd2Constants.AuthorizationObjectType.CONSENT_AUTHORIZATION.getValue())) || objTypeAuth.equals(Psd2Constants.AuthorizationObjectType.CONSENT_COF_AUTHORUZATION.getValue())) {
                sql = sql + " WHERE objct_aut = :objct_aut AND otype_aut = :otype_aut ";
            } /*else if (objectIdAuth != null && objTypeAuth.equals(Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue())
                    && StringUtils.isNotBlank(paymentSrv) && StringUtils.isNotBlank(paymentProduct)) {
                sql = sql + " join psd2_nalog_tpp tpp_nalog on tpp_nalog.sifra_tpp = objct_aut" +
                        " WHERE tpp_nalog.pyser_tpp = :pyser_tpp AND tpp_nalog.pyprd_tpp = :pyprd_tpp" +
                        " AND objct_aut = :objct_aut AND otype_aut = :otype_aut ";
            }*/ else {
                sql = sql + " WHERE objct_aut = :objct_aut AND otype_aut = :otype_aut ";
            }
            if (authorizationType != null) {
                sql = sql + " and atype_aut = :authorizationType ";
            }
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("objct_aut", objectIdAuth);
            query.setParameter("otype_aut", objTypeAuth);
            /*if (objectIdAuth != null && objTypeAuth.equals(Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue())
                    && StringUtils.isNotBlank(paymentSrv) && StringUtils.isNotBlank(paymentProduct)) {
                query.setParameter("pyser_tpp", paymentSrv);
                query.setParameter("pyprd_tpp", paymentProduct);
            }*/
            if (authorizationType != null) {
                query.setParameter("authorizationType", authorizationType);
            }
            List<Object[]> authDtoResultList = query.getResultList();
            resulAuthtList = formAuthorizationDto(authDtoResultList);
        } catch (Exception e) {
        	log.error("Exception is:", e);
        }
        return resulAuthtList;
    }

    public AuthorizationDto getSecondAuthorizationByObjectId(Integer objectIdAuth,
                                                             Integer objTypeAuth,
                                                             Integer authorizationType,
                                                             Integer firstAuthorization) {
        AuthorizationDto resulAutht = null;
        try {
            String sql = getBasicSql() + " WHERE objct_aut = :objct_aut AND otype_aut = :otype_aut AND id_aut != :firstAuth ";
            sql += authorizationType != null ? " AND atype_aut = :authorizationType " : "";

            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("objct_aut", objectIdAuth);
            query.setParameter("otype_aut", objTypeAuth);
            query.setParameter("firstAuth", firstAuthorization);
            if (authorizationType != null) query.setParameter("authorizationType", authorizationType);

            Object[] authDtoResult = (Object[]) query.getSingleResult();
            resulAutht = formAuthorizationDto(authDtoResult);
        } catch (Exception e) {
        	log.error("Exception is:", e);
        }
        return resulAutht;
    }

    public AuthorizationDto getConsentAuthorization(Integer objecIdAuth, Integer objTypeAuth, Integer idAuth) {
        AuthorizationDto authorizationDto = null;
        try {
            String sql = getBasicSql();
            if (objecIdAuth != null && objTypeAuth != null && idAuth != null) {
                sql = getBasicSql() + " WHERE objct_aut = :objct_aut AND otype_aut = :otype_aut AND id_aut = :id_aut";
                Query query = entityManager.createNativeQuery(sql);
                query.setParameter("objct_aut", objecIdAuth);
                query.setParameter("otype_aut", objTypeAuth);
                query.setParameter("id_aut", idAuth);
                List<Object[]> authDtoResultList = query.getResultList();
                if (authDtoResultList != null && !authDtoResultList.isEmpty()) {
                    authorizationDto = formAuthorizationDto(authDtoResultList.get(0));
                }
            }
        } catch (Exception e) {
        	log.error("Exception is:", e);
        }
        return authorizationDto;
    }

    public AuthorizationDto getPaymentAuthorization(Integer objTypeAuth, String idAuth, String objIdAuth, String paymentSerTpp, String paymentPrdTpp) {
        AuthorizationDto authorizationDto = null;
        try {
            String sql = getBasicSql();
            if (objIdAuth != null && idAuth != null && objTypeAuth != null && paymentSerTpp != null && paymentPrdTpp != null) {
                sql = getBasicSql() + " join psd2_nalog_tpp tpp_nalog on tpp_nalog.pymtd_tpp = objct_aut" +
                        " where psd2_authorization.otype_aut = :otype_aut and psd2_authorization.id_aut = :id_aut" +
                        " and psd2_authorization.objct_aut = :objct_aut and tpp_nalog.pyser_tpp = :pyser_tpp" +
                        " and tpp_nalog.pyprd_tpp = :pyprd_tpp";
                Query query = entityManager.createNativeQuery(sql);
                query.setParameter("otype_aut", objTypeAuth);
                query.setParameter("id_aut", idAuth);
                query.setParameter("objct_aut", objIdAuth);
                query.setParameter("pyser_tpp", paymentSerTpp);
                query.setParameter("pyprd_tpp", paymentPrdTpp);
                List<Object[]> authDtoResultList = query.getResultList();
                if (authDtoResultList != null && !authDtoResultList.isEmpty()) {
                    authorizationDto = formAuthorizationDto(authDtoResultList.get(0));
                }
            }
        } catch (Exception e) {
        	log.error("Exception is:", e);
        }
        return authorizationDto;
    }

    public AuthorizationDto getBasketAuthorization(Integer objecIdAuth, Integer objTypeAuth, Integer idAuth) {
        AuthorizationDto authorizationDto = null;
        try {
            String sql = getBasicSql();
            if (objecIdAuth != null && objTypeAuth != null && idAuth != null) {
                sql = getBasicSql() + " WHERE objct_aut = :objct_aut AND otype_aut = :otype_aut AND id_aut = :id_aut";
                Query query = entityManager.createNativeQuery(sql);
                query.setParameter("objct_aut", objecIdAuth);
                query.setParameter("otype_aut", objTypeAuth);
                query.setParameter("id_aut", idAuth);
                List<Object[]> authDtoResultList = query.getResultList();
                if (authDtoResultList != null && !authDtoResultList.isEmpty()) {
                    authorizationDto = formAuthorizationDto(authDtoResultList.get(0));
                }
            }
        } catch (Exception e) {
        	log.error("Exception is:", e);
        }
        return authorizationDto;
    }

    public List<AuthorizationDto> getBasketAuthorizationById(Integer objecIdAuth, Integer objTypeAuth) {
        AuthorizationDto authorizationDto = null;
        List<AuthorizationDto> resulList=new ArrayList<>();
        try {
            String sql = getBasicSql();
            if (objecIdAuth != null && objTypeAuth != null ) {
                sql = getBasicSql() + " WHERE objct_aut = :objct_aut AND otype_aut = :otype_aut";
                Query query = entityManager.createNativeQuery(sql);
                query.setParameter("objct_aut", objecIdAuth);
                query.setParameter("otype_aut", objTypeAuth);
                List<Object[]> authDtoResultList = query.getResultList();
                if (authDtoResultList != null && !authDtoResultList.isEmpty()) {
                    for (Object[] o : authDtoResultList) {
                        authorizationDto = formAuthorizationDto(o);
                        resulList.add(authorizationDto);
                    }
                }
            }
        } catch (Exception e) {
        	log.error("Exception is:", e);
        }
        return resulList;
    }



    public AuthorizationDto createAuthorization(AuthorizationDto authorizationDto) throws AbitRuleException {
        String sqlInsertAuthorization = "INSERT  INTO psd2_authorization (scast_aut, scamt_aut, cdate_aut, objct_aut, otype_aut, atype_aut, tppid_aut, psuid_aut, reqid_aut, link_aut, todat_aut )" +
                " VALUES (:scast_aut, :scamt_aut, :cdate_aut, :objct_aut, :otype_aut, :atype_aut, :tppid_aut, :psuid_aut, :reqid_aut, :link_aut, :todat_aut)";
        try {
            Query insertQuery = entityManager.createNativeQuery(sqlInsertAuthorization);
            insertQuery.setParameter("scast_aut", authorizationDto.getScastAuth());
            insertQuery.setParameter("scamt_aut", authorizationDto.getScamtAuth());

            insertQuery.setParameter("objct_aut", authorizationDto.getObjectAuth());
            insertQuery.setParameter("otype_aut", authorizationDto.getObjTypeAuth());
            insertQuery.setParameter("atype_aut", authorizationDto.getAutTypeAuth());
            insertQuery.setParameter("tppid_aut", authorizationDto.getTppIdAuth());
            insertQuery.setParameter("psuid_aut", authorizationDto.getPsuIdAuth());
            insertQuery.setParameter("reqid_aut", authorizationDto.getReqIdAuth());
            insertQuery.setParameter("link_aut", authorizationDto.getLinkAuth());
            //getting current time
            LocalDateTime currentTime = LocalDateTime.now();
            insertQuery.setParameter("cdate_aut", Timestamp.valueOf(currentTime));
            if ((!authorizationDto.getObjTypeAuth().equals(Psd2Constants.AuthorizationObjectType.CONSENT_AUTHORIZATION.getValue()) && !authorizationDto.getObjTypeAuth().equals(Psd2Constants.AuthorizationObjectType.CONSENT_COF_AUTHORUZATION.getValue())) && authorizationDto.getAutTypeAuth().equals(Psd2Constants.AuthorizationType.CREATED.getValue())) {
                //insertQuery.setParameter("todat_aut", Timestamp.valueOf(authorizationDto.getTodateAuth()));
                insertQuery.setParameter("todat_aut", Timestamp.valueOf(getAuthValidToTime(currentTime)));
            } else {
                insertQuery.setParameter("todat_aut", Timestamp.valueOf(getAuthValidToTime(currentTime)));
            }
            //execute query
            insertQuery.executeUpdate();

            //fetch inserted autoritzation
            String sqlPk = "SELECT @@IDENTITY";

            Object primaryKey = entityManager.createNativeQuery(sqlPk).getSingleResult();
            authorizationDto.setIdAuth(((BigDecimal) primaryKey).intValue());
        } catch (Exception e) {
        	log.error("Exception is:", e);
            throw new AbitRuleException("Greška kod spremanja autorizacije (createAuthorization): " + e);
        }
        return authorizationDto;
    }

    public void edit(AuthorizationDto dto) throws AbitRuleException {
        try {
            validateBeforeEdit(dto);
            Authorization entity = formEntity(dto);
            entityManager.merge(entity);
            entityManager.flush();
        } catch (Exception ex) {
        	log.error("Exception :", ex);
            throw new AbitRuleException("authorizationEditError" + ex.getMessage());
        }
    }

    private void validateBeforeEdit(AuthorizationDto dto) {
        //TODO
    }

    private List<AuthorizationDto> formAuthorizationDto(List<Object[]> authorizationList) {
        List<AuthorizationDto> resultList = null;
        if (authorizationList != null && !authorizationList.isEmpty()) {

            resultList = new ArrayList<>();
            for (Object[] authorizationObject : authorizationList) {
                resultList.add(formAuthorizationDto(authorizationObject));
            }
        }
        return resultList;
    }

    private AuthorizationDto formAuthorizationDto(Object[] authorizationObject) {
        AuthorizationDto authorizationDto = null;
        if (authorizationObject != null) {
            authorizationDto = new AuthorizationDto();
            authorizationDto.setIdAuth((Integer) authorizationObject[IDX_ID_AUT]);
            authorizationDto.setScastAuth((String) authorizationObject[IDX_SCAST_AUT]);
            authorizationDto.setScamtAuth((String) authorizationObject[IDX_SCAMT_AUT]);
            authorizationDto.setCdateAuth(((Timestamp) authorizationObject[IDX_CDATE_AUT]).toLocalDateTime());
            authorizationDto.setObjectAuth((String) authorizationObject[IDX_OBJCT_AUT]);
            authorizationDto.setObjTypeAuth((Integer) authorizationObject[IDX_OTYPE_AUT]);
            authorizationDto.setAutTypeAuth((Integer) authorizationObject[IDX_ATYPE_AUT]);
            authorizationDto.setTppIdAuth((String) authorizationObject[IDX_TPPID_AUT]);
            authorizationDto.setPsuIdAuth((String) authorizationObject[IDX_PSUID_AUT]);
            authorizationDto.setReqIdAuth((String) authorizationObject[IDX_REQID_AUT]);
            authorizationDto.setLinkAuth((String) authorizationObject[IDX_LINK_AUT]);
            Object tmpObj = authorizationObject[IDX_TODAT_AUT];
            authorizationDto.setTodateAuth(tmpObj != null ? ((Timestamp) authorizationObject[IDX_TODAT_AUT]).toLocalDateTime() : null);
            authorizationDto.setmDate(authorizationObject[IDX_MDATE_AUT] != null ? ((Timestamp) authorizationObject[IDX_MDATE_AUT]).toLocalDateTime() : null);
            authorizationDto.setObjectHashAut(authorizationObject[IDX_OHASH_AUT] != null ? (String) authorizationObject[IDX_OHASH_AUT] : null);
        }
        return authorizationDto;
    }

    private Authorization formEntity(AuthorizationDto dto) {
        Authorization entity = null;
        if (dto != null) {
            entity = new Authorization();
            entity.setIdAut(dto.getIdAuth());
            entity.setScastAut(dto.getScastAuth());
            entity.setScamtAut(dto.getScamtAuth());
            entity.setCdateAut(Timestamp.valueOf(dto.getCdateAuth()));
            entity.setObjctAut(dto.getObjectAuth());
            entity.setOtypeAut(dto.getObjTypeAuth());
            entity.setAtypeAut(dto.getAutTypeAuth());
            entity.setTppIdAut(dto.getTppIdAuth());
            entity.setPsuIdAut(dto.getPsuIdAuth());
            entity.setReqIdAut(dto.getReqIdAuth());
            entity.setLinkAut(dto.getLinkAuth());
            entity.setToDateAuth(dto.getTodateAuth() != null ? Timestamp.valueOf(dto.getTodateAuth()) : null);
            entity.setmDate(dto.getmDate() != null?Timestamp.valueOf(dto.getmDate()) : null);
            entity.setoHashAut(dto.getObjectHashAut());
        }
        return entity;
    }

    public AuthorizationListResponseDto getListOfAuthIds(String objectIdAuth, Integer objTypeAuth, String paymentSrv, String paymentProduct, String tppId, Integer authotizationType) {
        AuthorizationListResponseDto authorizationListResponseDto = new AuthorizationListResponseDto();
        List<AuthorizationDto> resultAuthDtoList;
        List<Integer> resultList = null;
        resultAuthDtoList = getAuthorizationByObjectId(objectIdAuth, objTypeAuth, paymentSrv, paymentProduct, null);
        if (resultAuthDtoList != null && !resultAuthDtoList.isEmpty()) {
            resultList = new ArrayList<>();
            for (AuthorizationDto item : resultAuthDtoList) {
                if (tppId.equals(item.getTppIdAuth()) && authotizationType.equals(item.getAutTypeAuth())) {
                    resultList.add(item.getIdAuth());
                }
            }
        }
        if (resultList != null && !resultList.isEmpty()) {
            authorizationListResponseDto.setAuthorisationIds(resultList);
            authorizationListResponseDto.setValid(true);
        } else {
            authorizationListResponseDto.setValid(false);
            authorizationListResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList("Ne postoji autorizacija"))));
        }
        return authorizationListResponseDto;
    }

    public AuthorizationListResponseDto getBulkListOfAuthIds(Integer datotekaId, String paymentProduct, String tppId, Integer authotizationType) {
        AuthorizationListResponseDto authorizationListResponseDto = new AuthorizationListResponseDto();
        List<AuthorizationDto> resultAuthDtoList;
        List<Integer> resultList = null;
        resultAuthDtoList = getAuthorizationsByDatoteka(datotekaId, authotizationType, null);
        if (resultAuthDtoList != null && !resultAuthDtoList.isEmpty()) {
            resultList = new ArrayList<>();
            Boolean mozeUListu = Boolean.FALSE;
            for (AuthorizationDto item : resultAuthDtoList) {
                if (tppId.equals(item.getTppIdAuth()) && authotizationType.equals(item.getAutTypeAuth())) {
                    //resultList.add(item.getIdAuth()); //zakomentirano jer zasad dodajemo datotekaId
                    mozeUListu = Boolean.TRUE;
                }
            }
            if (mozeUListu) {
                resultList.add(datotekaId);
            }
        }
        if (resultList != null && !resultList.isEmpty()) {
            authorizationListResponseDto.setAuthorisationIds(resultList);
            authorizationListResponseDto.setValid(true);
        } else {
            authorizationListResponseDto.setValid(false);
            authorizationListResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList("Ne postoji autorizacija!"))));
        }
        return authorizationListResponseDto;
    }

    public StartAuthorizationResponseDto validateBeforeCreateAutorizationNalogCreate(CreateAuthorizationRequestDto authorizationRequestDto, TppNalogPlatniDto paymentDto) {
        StartAuthorizationResponseDto authorizationResponseDto = new StartAuthorizationResponseDto();
        //provjera dal se podudaraju service, product, tpp
        if (StringUtils.equals(authorizationRequestDto.getPaymentService(), paymentDto.getPaymentService()) &&
                StringUtils.equals(authorizationRequestDto.getPaymentProduct(), paymentDto.getPaymentProduct()) &&
                StringUtils.equals(authorizationRequestDto.getTppId(), paymentDto.getTppId())) {
            //provjera status naloga //TODO
            if (paymentDto.getTransactionStatus() != null) {
                //dohvat autorizacija
                List<AuthorizationDto> authorizationDtoList = getAuthorizationByObjectId(paymentDto.getPaymentRequestId(), Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), paymentDto.getPaymentService(),
                        paymentDto.getPaymentProduct(), Psd2Constants.AuthorizationType.CREATED.getValue());
                if (authorizationDtoList != null && !authorizationDtoList.isEmpty()) {
                    Integer brojValidnihAutorizacija = 0;
                    for (AuthorizationDto authorizationDto : authorizationDtoList) {
                        if (!authorizationDto.getScastAuth().equals(ScaStatus.FAILED.getValue())) {
                            brojValidnihAutorizacija = brojValidnihAutorizacija + 1;
                        }
                    }
                    //ZAKOMENTIRANO JER TREBA SAMO 1 POTPIS ZA CONSENT
                    //getBrojPotpisnika
//                    Map<Integer, Integer> mapaPartija = new HashMap<>();
//                    for (ConsentAccountAisDto aisDto : consentAisDto.getConsentAccountAisDtoList()) {
//                        mapaPartija.put(aisDto.getSifraPar(), aisDto.getSifraPar());
//                    }
//                    List<Integer> listaPartija = new ArrayList(mapaPartija.values());
//                    Integer brojPotpisnika = psd2UtilDao.getMaxBrojIbPotpisnika(listaPartija, null);
                    if (brojValidnihAutorizacija > 0) {
                        authorizationResponseDto.setValid(false);
                        authorizationResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.STATUS_INVALID.getValue(), new ArrayList<String>(Arrays.asList("Autorizacija za ovaj consent već postoji."))));
                    } else {
                        authorizationResponseDto.setValid(true);
                    }
                } else {
                    authorizationResponseDto.setValid(true);
                }
            } else {
                authorizationResponseDto.setValid(false);
                authorizationResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.STATUS_INVALID.getValue(), new ArrayList<String>(Arrays.asList("Ne možemo započeti process autorizacije - neispravan status naloga "))));
            }
        } else {
            authorizationResponseDto.setValid(false);
            authorizationResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList("Ne možemo započeti process autorizacije - nalog se ne odnosi se na TPP koji šalje zahtjev."))));
        }

        return authorizationResponseDto;
    }

    public StartAuthorizationResponseDto validateBeforeCreateAutorizationNalogCancel(CreateAuthorizationRequestDto authorizationRequestDto, TppNalogPlatniDto paymentDto) {
        StartAuthorizationResponseDto authorizationResponseDto = new StartAuthorizationResponseDto();
        //provjera dal se podudaraju service, product, tpp
        if (StringUtils.equals(authorizationRequestDto.getPaymentService(), paymentDto.getPaymentService()) &&
                StringUtils.equals(authorizationRequestDto.getPaymentProduct(), paymentDto.getPaymentProduct()) &&
                StringUtils.equals(authorizationRequestDto.getTppId(), paymentDto.getTppId())) {
            //provjera status naloga
            if (paymentDto.getTransactionStatus() != null) {
                //dohvat autorizacija
                List<AuthorizationDto> authorizationDtoList = getAuthorizationByObjectId(paymentDto.getPaymentRequestId(), Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), paymentDto.getPaymentService(),
                        paymentDto.getPaymentProduct(), Psd2Constants.AuthorizationType.CANCELLED.getValue());
                if (authorizationDtoList != null && !authorizationDtoList.isEmpty()) {
                    Integer brojValidnihAutorizacija = new Integer(0);
                    for (AuthorizationDto authorizationDto : authorizationDtoList) {
                        if (!authorizationDto.getScastAuth().equals(ScaStatus.FAILED.getValue())) {
                            brojValidnihAutorizacija = brojValidnihAutorizacija + new Integer(1);
                        }
                    }
                    /*Integer brojPotpisnika = psd2UtilDao.getMaxBrojIbPotpisnika(null, Arrays.asList(tppNalogPlatniDto.getIbanZaduzenja().substring(tppNalogPlatniDto.getIbanZaduzenja().length() - 10)));
                    if (brojPotpisnika.compareTo(brojValidnihAutorizacija) <= 0) {
                        authorizationResponseDto.setValid(false);
                        authorizationResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.STATUS_INVALID.getValue(), new ArrayList<String>(Arrays.asList("Autorizacija/e za ovaj nalog već postoji."))));
                    } else {
                        authorizationResponseDto.setValid(true);
                    }*/
                    if (brojValidnihAutorizacija > 0) {
                        authorizationResponseDto.setValid(false);
                        authorizationResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.STATUS_INVALID.getValue(), new ArrayList<String>(Arrays.asList("Autorizacija za ovaj consent već postoji."))));
                    } else {
                        authorizationResponseDto.setValid(true);
                    }
                } else {
                    authorizationResponseDto.setValid(true);
                }
            } else {
                authorizationResponseDto.setValid(false);
                authorizationResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.CANCELLATION_INVALID.getValue(), new ArrayList<String>(Arrays.asList("Ne možete povući nalog koji nije potpisan i koji nije najava."))));
            }
        } else {
            authorizationResponseDto.setValid(false);
            authorizationResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList("Ne možemo započeti process autorizacije - nalog se ne odnosi se na TPP koji šalje zahtjev."))));
        }

        return authorizationResponseDto;
    }

    public StartAuthorizationResponseDto validateBeforeCreateAutorizationConsent(CreateAuthorizationRequestDto authorizationRequestDto, ConsentAisDto consentAisDto) {
        StartAuthorizationResponseDto authorizationResponseDto = new StartAuthorizationResponseDto();
        //provjera dal se tpp ovi podudaraju
        if (StringUtils.equals(authorizationRequestDto.getTppId(), consentAisDto.getTppIdCon())) {
            //provjera status naloga
            if (consentAisDto.getStateCon().equals(RECEIVED.getValue())) {
                //dohvat autorizacija
                List<AuthorizationDto> authorizationDtoList = getAuthorizationByObjectId(consentAisDto.getSifra().toString(), Psd2Constants.AuthorizationObjectType.CONSENT_AUTHORIZATION.getValue(), null, null, Psd2Constants.AuthorizationType.CREATED.getValue());
                if (authorizationDtoList != null && !authorizationDtoList.isEmpty()) {
                    Integer brojValidnihAutorizacija = new Integer(0);
                    for (AuthorizationDto authorizationDto : authorizationDtoList) {
                        if (!authorizationDto.getScastAuth().equals(ScaStatus.FAILED.getValue())) {
                            brojValidnihAutorizacija = brojValidnihAutorizacija + new Integer(1);
                        }
                    }
                    //ZAKOMENTIRANO JER TREBA SAMO 1 POTPIS ZA CONSENT
                    //getBrojPotpisnika
//                    Map<Integer, Integer> mapaPartija = new HashMap<>();
//                    for (ConsentAccountAisDto aisDto : consentAisDto.getConsentAccountAisDtoList()) {
//                        mapaPartija.put(aisDto.getSifraPar(), aisDto.getSifraPar());
//                    }
//                    List<Integer> listaPartija = new ArrayList(mapaPartija.values());
//                    Integer brojPotpisnika = psd2UtilDao.getMaxBrojIbPotpisnika(listaPartija, null);
                    if (brojValidnihAutorizacija > 0) {
                        authorizationResponseDto.setValid(false);
                        authorizationResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.STATUS_INVALID.getValue(), new ArrayList<String>(Arrays.asList("Autorizacija za ovaj consent već postoji."))));
                    } else {
                        authorizationResponseDto.setValid(true);
                    }
                } else {
                    authorizationResponseDto.setValid(true);
                }
            } else {
                authorizationResponseDto.setValid(false);
                authorizationResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.STATUS_INVALID.getValue(), new ArrayList<String>(Arrays.asList("Ne možemo započeti process autorizacije - neispravan status consenta (" + consentAisDto.getStateCon() + ")."))));
            }
        } else {
            authorizationResponseDto.setValid(false);
            authorizationResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList("Ne možemo započeti process autorizacije - consent se ne odnosi se na TPP koji šalje zahtjev."))));
        }

        return authorizationResponseDto;
    }

    LocalDateTime getAuthValidToTime(LocalDateTime authCreationTime) throws AbitRuleException {
        LocalDateTime validateUntil;
        // Long validateUntilParam = Long.valueOf(LookupHelper.getICoreDaoProxyRemote().getVrijednostParametraBySifra(Psd2Constants.PARAMETAR_PSD2_AUTH_TODATE)); // stari
        Long validateUntilParam = 500L;//Long.valueOf(coreSrvc.getVrijednostParametraBySifra(Psd2Constants.PARAMETAR_PSD2_AUTH_TODATE));
        validateUntil = authCreationTime.plusSeconds(validateUntilParam);
        return validateUntil;
    }


    public AuthorizationDto editForSign(AuthorizationDto authDto, Integer sifraIbKorisnika) throws AbitRuleException {
        authDto.setPsuIdAuth(sifraIbKorisnika.toString());
        authDto.setScastAuth(ScaStatus.FINALISED.getValue());
        authDto.setmDate(LocalDateTime.now());
        edit(authDto);
        return authDto;
    }

    public AuthorizationDto editForSignBeforeObjectUpdate(AuthorizationDto authDto, Integer sifraIbKorisnika) throws AbitRuleException {
        authDto.setPsuIdAuth(sifraIbKorisnika.toString());
        authDto.setScastAuth(ScaStatus.FINALISED.getValue());
        authDto.setmDate(LocalDateTime.now());
        //edit(authDto);
        return authDto;
    }

    public StartAuthorizationResponseDto validateBeforeCreateAutorizationConsentCof(CreateAuthorizationRequestDto authorizationRequestDto, ConsentCofDto consentCofDto) {
        StartAuthorizationResponseDto authorizationResponseDto = new StartAuthorizationResponseDto();
        //provjera dal se tpp ovi podudaraju
        if (StringUtils.equals(authorizationRequestDto.getTppId(), consentCofDto.getTppIdCon())) {
            if (consentCofDto.getStateCon().equals(RECEIVED.getValue())) {
                //dohvat autorizacija
                List<AuthorizationDto> authorizationDtoList = getAuthorizationByObjectId(consentCofDto.getSifra().toString(), Psd2Constants.AuthorizationObjectType.CONSENT_AUTHORIZATION.getValue(), null, null, Psd2Constants.AuthorizationType.CREATED.getValue());
                if (authorizationDtoList != null && !authorizationDtoList.isEmpty()) {
                    Integer brojValidnihAutorizacija = new Integer(0);
                    for (AuthorizationDto authorizationDto : authorizationDtoList) {
                        if (!authorizationDto.getScastAuth().equals(ScaStatus.FAILED.getValue())) {
                            brojValidnihAutorizacija = brojValidnihAutorizacija + new Integer(1);
                        }
                    }
                    if (brojValidnihAutorizacija > 0) {
                        authorizationResponseDto.setValid(false);
                        authorizationResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.STATUS_INVALID.getValue(), new ArrayList<String>(Arrays.asList("Autorizacija za ovaj consent već postoji."))));
                    } else {
                        authorizationResponseDto.setValid(true);
                    }
                } else {
                    authorizationResponseDto.setValid(true);
                }

            } else {
                authorizationResponseDto.setValid(false);
                authorizationResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.STATUS_INVALID.getValue(), new ArrayList<String>(Arrays.asList("Ne možemo započeti process autorizacije - neispravan status consenta (" + consentCofDto.getStateCon() + ")."))));
            }

        } else {
            authorizationResponseDto.setValid(false);
            authorizationResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList("Ne možemo započeti process autorizacije - consent se ne odnosi se na TPP koji šalje zahtjev."))));
        }
        return authorizationResponseDto;
    }
}
