package hr.abc.psd2.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.ContextNotActiveException;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import hr.abc.psd2.dto.specificni.ValutaDto;
import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.model.dto.authorization.ScaStatus;
import hr.abc.psd2.model.dto.core.NalogAvpoDto;
import hr.abc.psd2.model.dto.core.NalogDevizniDto;
import hr.abc.psd2.model.dto.core.NalogNefinDto;
import hr.abc.psd2.model.dto.core.NalogPlatniDto;
import hr.abc.psd2.model.dto.core.NalogSepaDto;
import hr.abc.psd2.model.dto.core.PartijaDto;
import hr.abc.psd2.model.dto.core.PovlasteniTecajDto;
import hr.abc.psd2.model.dto.core.TppNalogFilterDto;
import hr.abc.psd2.model.dto.error.ErrorCode;
import hr.abc.psd2.model.dto.pis.PaymentServiceType;
import hr.abc.psd2.model.dto.pis.Psd2DatotekaDto;
import hr.abc.psd2.model.dto.pis.SinglePaymentDto;
import hr.abc.psd2.model.dto.pis.TppNalogPlatniDto;
import hr.abc.psd2.model.dto.pis.TransactionStatus;
import hr.abc.psd2.model.dto.pis.TransactionStatusResponseDto;
import hr.abc.psd2.model.dto.pis.pain002.Document;
import hr.abc.psd2.model.entity.core.IBNalog;
import hr.abc.psd2.model.entity.psd2.TppNalog;
import hr.abc.psd2.resources.ws.impl.ConsentCofResources;
import hr.abc.psd2.security.Globals;
import hr.abc.psd2.service2.IAvpoSrvc;
import hr.abc.psd2.service2.ICoreSrvc;
import hr.abc.psd2.util.Bassx2Constants;
import hr.abc.psd2.util.DateUtil;
import hr.abc.psd2.util.GenericBassxConstants;
import hr.abc.psd2.util.IbanFunctions;
import hr.abc.psd2.util.InternationalizationUtil;
import hr.abc.psd2.util.MathFunctions;
import hr.abc.psd2.util.Psd2Constants;
import hr.abc.psd2.util.Psd2Util;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Tomica on 08-Jan-19.
 */
@Slf4j
@Dependent
@Transactional
public class NalogTppDao implements Serializable {

    private static final long serialVersionUID = 1L;
    
    // index

    private final int IDX_TPP_SIFRA_NALOGA = 0;
    private final int IDX_TPP_VRIJEME_KREIRANJA = 1;
    private final int IDX_TPP_TPP_ID = 2;
    private final int IDX_TPP_REQUEST_ID = 3;
    private final int IDX_TPP_CONTENT_TYPE = 4;
    private final int IDX_TPP_PAYMENT_PRODUCT = 5;
    private final int IDX_TPP_PAYMENT_SERVICE = 6;
    private final int IDX_TPP_PSU_IP = 7;
    private final int IDX_TPP_PID_IP = 8;
    private final int IDX_TPP_SIFRA_BSKT = 9;
    private final int IDX_TPP_TRS_IP = 10;

//    @Inject
//    private PlatniValidator platniValidator;
//    @Inject
//    private WebLoginBean loginBean;
    @Inject
    private Psd2DatotekaDao datotekaDao;
    
    @Inject
    private EntityManager entityManager;

    @Inject
    private IAvpoSrvc avpoSrvc;

    @Inject
    private ICoreSrvc coreSrvc;

    private String getBasicSql() {
        return "SELECT sifra_tpp, vrije_tpp, tppid_tpp, reqid_tpp, conty_tpp, pyprd_tpp, pyser_tpp, psuip_tpp, pymid_tpp, baskt_tpp, trsts_tpp " +
                "FROM psd2_nalog_tpp ";
    }

    private SimpleDateFormat sdfSql = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Unos tpp naloga.
     *
     * @param tppNalogPlatniDto
     * @return
     * @author tkorpar
     */
    public TppNalogPlatniDto createTppNalog(TppNalogPlatniDto tppNalogPlatniDto) {
        // SQL upit za insert u bazu podataka
        String sqlInsertMobileNalog = "INSERT INTO psd2_nalog_tpp " +
                "(vrije_tpp, tppid_tpp, reqid_tpp, conty_tpp, pyprd_tpp, pyser_tpp, psuip_tpp, pymid_tpp, trsts_tpp) " +
                "VALUES(:vrije_tpp, :tppid_tpp, :reqid_tpp, :conty_tpp, :pyprd_tpp, :pyser_tpp, :psuip_tpp, :pymid_tpp, :trsts_tpp) ";
        // priprema upita
        Query queryInsertMobileNalog = entityManager.createNativeQuery(sqlInsertMobileNalog);

        queryInsertMobileNalog.setParameter("vrije_tpp", Timestamp.valueOf(LocalDateTime.now()));
        queryInsertMobileNalog.setParameter("tppid_tpp", tppNalogPlatniDto.getTppId());
        queryInsertMobileNalog.setParameter("reqid_tpp", tppNalogPlatniDto.getxRequestId());
        queryInsertMobileNalog.setParameter("conty_tpp", tppNalogPlatniDto.getContentType());
        queryInsertMobileNalog.setParameter("pyprd_tpp", tppNalogPlatniDto.getPaymentProduct());
        queryInsertMobileNalog.setParameter("pyser_tpp", tppNalogPlatniDto.getPaymentService());
        queryInsertMobileNalog.setParameter("psuip_tpp", tppNalogPlatniDto.getPsuIpAddress());
        queryInsertMobileNalog.setParameter("pymid_tpp", tppNalogPlatniDto.getPaymentRequestId());
        queryInsertMobileNalog.setParameter("trsts_tpp", tppNalogPlatniDto.getTransactionStatus().getTransactionStatus());

        // izvršavanje na bazi podataka
        queryInsertMobileNalog.executeUpdate();
        // dohvat unesenog naloga
        String sqlPk = "SELECT @@IDENTITY";
        try {
            Object primaryKey = entityManager.createNativeQuery(sqlPk).getSingleResult();
            tppNalogPlatniDto.setSifraTppNaloga(((BigDecimal) primaryKey).intValue());
        } catch (Exception e) {
            LoggerFactory.getLogger(getClass()).error(e.getMessage(), e);
        }
        return tppNalogPlatniDto;
    }

    public TppNalogPlatniDto edit(TppNalogPlatniDto dto) {
        validateBeforeEdit(dto);
        TppNalog entity = formEntity(dto);
        entityManager.merge(entity);
        entityManager.flush();
        return getTppNalogBySifra(dto.getSifraTppNaloga());
    }

    public void edit(TppNalogPlatniDto dto, NalogNefinDto log) {
        validateBeforeEdit(dto);
        TppNalog entity = formEntity(dto);

       /* // Ako unutar dto postoji nalogNefin - on je jači
        boolean logInside = false;
        if (dto.getNalogNefin() != null) {
            logInside = true;
            log = dto.getNalogNefin();
        }
        log = Psd2Util.logEdit(entity, log);
        dto = edit(dto);
        if (logInside) {
            dto.setNalogNefin(log);
        }*/


        entityManager.merge(entity);
        entityManager.flush();
    }

    private void validateBeforeEdit(TppNalogPlatniDto dto) {
        //TODO
    }


//    public List<NalogSepaDto> validateTppSepaNalogList(List<NalogSepaDto> nalogSepaDtos){
//        nalogSepaDtos = PlatniUtil.zamijeniOznakuValute(nalogSepaDtos, entityManager);
//        nalogSepaDtos = platniValidator.validateBasic(nalogSepaDtos);  //dodati datum valute, knji today
//        nalogSepaDtos = platniValidator.validateAll(nalogSepaDtos, entityManager);
//
//        return nalogSepaDtos;
//    }


    public SinglePaymentDto validateAndSetSepaNalog(SinglePaymentDto psd2Nalog, Globals globals) throws AbitRuleException {
        SinglePaymentDto result = SinglePaymentDto.builder().build();
        //form sepa nalog
        NalogSepaDto nalogSepaDto = Psd2Util.formNalogSepaDtoFromPsd2Nalog(psd2Nalog, globals);
        if (StringUtils.isBlank(nalogSepaDto.getGreska())) {
            nalogSepaDto.getUtilMap().put(Bassx2Constants.PlatniPromet.UTILMAP_KEY_KLON, nalogSepaDto.klone());
            nalogSepaDto.setNacinIzvrsenja(null);
            //zamijeni oznaku valute
            List<NalogSepaDto> nalogSepaDtos = Arrays.asList(nalogSepaDto);
            //validate
            // nalogSepaDtos = LookupHelper.getIAvpoDaoProxyRemote().pripremaNaloga(nalogSepaDtos, globals); // stari
            nalogSepaDtos = avpoSrvc.pripremaNaloga(nalogSepaDtos, globals); // prazni
            nalogSepaDto = nalogSepaDtos.get(0);
            result.setNalogSepa(nalogSepaDto);
            result.setChanged(Psd2Util.isSepaTransactionChanged(nalogSepaDto, psd2Nalog));
        } else {
            result.setNalogSepa(nalogSepaDto);
        }
        return result;
    }

    public TppNalogPlatniDto validateAndSetSepaNalog(TppNalogPlatniDto psd2Nalog, Globals globals) throws AbitRuleException {
        TppNalogPlatniDto result = TppNalogPlatniDto.builder().build();
        //form sepa nalog
        NalogSepaDto nalogSepaDto = Psd2Util.formNalogSepaDtoFromTppNalog(psd2Nalog, globals);
        if (StringUtils.isBlank(nalogSepaDto.getGreska())) {
            nalogSepaDto.getUtilMap().put(Bassx2Constants.PlatniPromet.UTILMAP_KEY_KLON, nalogSepaDto.klone());
            nalogSepaDto.setNacinIzvrsenja(null);
            //zamijeni oznaku valute
            List<NalogSepaDto> nalogSepaDtos = Arrays.asList(nalogSepaDto);
            //validate
            //nalogSepaDtos = LookupHelper.getIAvpoDaoProxyRemote().pripremaNaloga(nalogSepaDtos, globals); //stari
            nalogSepaDtos = avpoSrvc.pripremaNaloga(nalogSepaDtos, globals); // prazni
            nalogSepaDto = nalogSepaDtos.get(0);
            result.setNalogSepa(nalogSepaDto);
            result.setChanged(Psd2Util.isSepaTransactionChanged(nalogSepaDto, psd2Nalog));
        } else {
            result.setNalogSepa(nalogSepaDto);
        }

        return result;
    }

    /**
     * Validacija i postavljanje SepaNaloga (JSON i XML)
     *
     * @param psd2Nalog
     * @param globals
     * @return
     * @throws AbitRuleException
     */
    public SinglePaymentDto validateAndSetSepaNalogList(SinglePaymentDto psd2Nalog, Globals globals) throws AbitRuleException {
        SinglePaymentDto result = SinglePaymentDto.builder().build();
        //SINGLE PAYMENTS
        if (StringUtils.equals(psd2Nalog.getPaymentService(), PaymentServiceType.SINGLE.getValue())) {
            if (StringUtils.contains(psd2Nalog.getContentType(), MediaType.APPLICATION_JSON)) {
                //form sepa nalog
                NalogSepaDto nalogSepaDto = Psd2Util.formNalogSepaDtoFromPsd2Nalog(psd2Nalog, globals);
                if (StringUtils.isBlank(nalogSepaDto.getGreska())) {
                    List<NalogSepaDto> nalogSepaDtos = Arrays.asList(nalogSepaDto);
                    //validate
                    //nalogSepaDtos = LookupHelper.getIAvpoDaoProxyRemote().pripremaNaloga(nalogSepaDtos, globals); //stari
                    nalogSepaDtos = avpoSrvc.pripremaNaloga(nalogSepaDtos, globals); // prazni
                    nalogSepaDto = nalogSepaDtos.get(0);
                    result.setNalogSepa(nalogSepaDto);
                    result.setChanged(Psd2Util.isSepaTransactionChanged(nalogSepaDto, psd2Nalog));
                } else {
                    result.setNalogSepa(nalogSepaDto);
                }
            } else if (StringUtils.equals(psd2Nalog.getContentType(), MediaType.APPLICATION_XML)) {
                try {
                    //List<NalogPlatniDto> platniList = LookupMinHelper.getIAvpoDaoMinProxyRemote().resolvePainDatoteka(psd2Nalog.getPainXml().getBytes()); // stari
                    List<NalogPlatniDto> platniList = avpoSrvc.resolvePainDatoteka(psd2Nalog.getPainXml().getBytes()); // prazni
                    if (platniList != null && platniList.size() == 1) {
                        NalogSepaDto nalogSepaDto = new NalogSepaDto(platniList.get(0));
                        Psd2Util.defaultsForNalogSepaDtoFromPsd2Nalog(nalogSepaDto, globals);
                        nalogSepaDto.setHitnost(Psd2Util.resolveInstancyFromPsd2Nalog(psd2Nalog.getPaymentProduct()));
                        Psd2Util.validateNalogSepaDtoFromPsd2Nalog(nalogSepaDto);
                        List<NalogSepaDto> nalogSepaDtos = Arrays.asList(nalogSepaDto);
                        //validate
                        //nalogSepaDtos = LookupHelper.getIAvpoDaoProxyRemote().pripremaNaloga(nalogSepaDtos, globals); // stari
                        nalogSepaDtos = avpoSrvc.pripremaNaloga(nalogSepaDtos, globals); // prazni
                        nalogSepaDto = nalogSepaDtos.get(0);
                        result.setNalogSepa(nalogSepaDto);
                        result.setChanged(Psd2Util.isSepaTransactionChanged(nalogSepaDto, psd2Nalog));
                    } else {
                        result.setNalogSepa(new NalogSepaDto());
                        result.getNalogSepa().setGreska("Za Payment Service PAYMENTS (single payment initiation request) ne smije biti više od jednog naloga");
                        result.getNalogSepa().addExceptions(new AbitRuleException(result.getNalogSepa().getGreska()));
                    }
                //} catch (AbitRuleException are) {
                } catch (Exception are) {
                    result.setNalogSepa(new NalogSepaDto());
//                    result.getNalogSepa().setGreska(are.getMessages().get(0));
//                    result.getNalogSepa().addExceptions(new AbitRuleException(are.getMessages().get(0)));
                }
            }
            //BULK
        } else if (StringUtils.equals(psd2Nalog.getPaymentService(), PaymentServiceType.BULK.getValue())) {
            //TODO
        }
        return result;
    }


    private TppNalogPlatniDto formTppNalogPlatniDto(Object[] nalogObject) {
        TppNalogPlatniDto tppNalogPlatniDto = null;
        if (nalogObject != null) {
            tppNalogPlatniDto = TppNalogPlatniDto.builder()
                    .sifraTppNaloga((Integer)nalogObject[IDX_TPP_SIFRA_NALOGA])
                    .vrijemeUpisaNaloga(((Timestamp) nalogObject[IDX_TPP_VRIJEME_KREIRANJA]).toLocalDateTime())
                    .tppId(((String) nalogObject[IDX_TPP_TPP_ID]))
                    .xRequestId(((String) nalogObject[IDX_TPP_REQUEST_ID]))
                    .contentType(((String) nalogObject[IDX_TPP_CONTENT_TYPE]))
                    .paymentProduct(((String) nalogObject[IDX_TPP_PAYMENT_PRODUCT]))
                    .paymentService(((String) nalogObject[IDX_TPP_PAYMENT_SERVICE]))
                    .psuIpAddress(((String) nalogObject[IDX_TPP_PSU_IP]))
                    .paymentRequestId(((String) nalogObject[IDX_TPP_PID_IP]))
                    .sifraBasketa((Integer)nalogObject[IDX_TPP_SIFRA_BSKT])
                    .transactionStatus(TransactionStatus.getByName((String) nalogObject[IDX_TPP_TRS_IP]) )
                    .build();


        }
        return tppNalogPlatniDto;
    }

    /**
     * Get TppNalogPlatniDto by sifra
     *
     * @param paymentId
     * @return TppNalogPlatniDto
     */
    public TppNalogPlatniDto getTppNalogBySifra(Integer paymentId) {
        return getTppNalogBySifra(paymentId, false);
    }

    public TppNalogPlatniDto getTppNalogBySifra(Integer paymentId, boolean forPotpis) {
        TppNalogPlatniDto tppNalogPlatniDto = null;
        try {
            if (paymentId != null) {
                String sql = getBasicSql() + " where sifra_tpp = :sifra_tpp ";
                if (forPotpis) sql += " and nalkn_tpp is null ";
                Query query = entityManager.createNativeQuery(sql);
                query.setParameter("sifra_tpp", paymentId);
                Object[] nalogObject = (Object[]) query.getSingleResult();
                if (nalogObject != null) {
                    tppNalogPlatniDto = formTppNalogPlatniDto(nalogObject);
                }
            }
        } catch (NoResultException nre) {
            tppNalogPlatniDto = null;
        } catch (Exception e) {
            log.error("Exception is:", e);
        }
        return tppNalogPlatniDto;
    }

    public TppNalogPlatniDto getTppNalogByPaymentRequestId(String paymentRequestId) {
        TppNalogPlatniDto tppNalogPlatniDto = null;
        try {
            if (paymentRequestId != null) {
                String sql = getBasicSql() + " where pymid_tpp = :pymid_tpp ";
                Query query = entityManager.createNativeQuery(sql);
                query.setParameter("pymid_tpp", paymentRequestId);
                Object[] nalogObject = (Object[]) query.getSingleResult();
                if (nalogObject != null) {
                    tppNalogPlatniDto = formTppNalogPlatniDto(nalogObject);
                }
            }
        } catch (NoResultException nre) {
            tppNalogPlatniDto = null;
        } catch (Exception e) {
            log.error("Exception is:", e);
        }
        return tppNalogPlatniDto;
    }

    public List<TppNalogPlatniDto> getTppNaloziBySifraList(List<Integer> paymentId, boolean forPotpis) {
        List<TppNalogPlatniDto> tppNalogPlatniDtoList = null;
        try {
            if (paymentId != null) {
                String sql = getBasicSql() + " where sifra_tpp in :sifra_tpp ";
                if (forPotpis) sql += " and nalkn_tpp is null ";
                Query query = entityManager.createNativeQuery(sql);
                query.setParameter("sifra_tpp", paymentId);
                List<Object[]> nalogObjectList = (List<Object[]>) query.getResultList();
                if (nalogObjectList != null) {
                    tppNalogPlatniDtoList = new ArrayList<>();
                    for (Object[] tmpObj : nalogObjectList) {
                        tppNalogPlatniDtoList.add(formTppNalogPlatniDto(tmpObj));
                    }
                }
            }
        } catch (NoResultException nre) {
            tppNalogPlatniDtoList = null;
        } catch (Exception e) {
            log.error("Exception is:", e);
        }
        return tppNalogPlatniDtoList;
    }

    public List<TppNalogPlatniDto> getTppNaloziByDatotekaBasket(Integer sifra, String tipGrupiranja) {
        return getTppNaloziByDatotekaBasket(sifra, tipGrupiranja, false);
    }

    public List<TppNalogPlatniDto> getTppNaloziByDatotekaBasket(Integer sifra, String tipGrupiranja, boolean forPotpis) {
        List<TppNalogPlatniDto> tppNalogPlatniDtoList = null;
        try {
            if (sifra != null) {
                String sql = getBasicSql() + " where 1 = 1 ";
                if (forPotpis) sql += " and nalkn_tpp is null ";
                if (tipGrupiranja != null && tipGrupiranja.equals(TppNalogPlatniDto.TIP_GRUPIRANJA_DATOTEKA))
                    sql += " and datot_tpp = :grupa ";
                if (tipGrupiranja != null && tipGrupiranja.equals(TppNalogPlatniDto.TIP_GRUPIRANJA_BASKET))
                    sql += " and baskt_tpp = :grupa ";
                Query query = entityManager.createNativeQuery(sql);
                query.setParameter("grupa", sifra);
                List<Object[]> nalogObjectList = (List<Object[]>) query.getResultList();
                if (nalogObjectList != null) {
                    tppNalogPlatniDtoList = new ArrayList<>();
                    for (Object[] tmpObj : nalogObjectList) {
                        tppNalogPlatniDtoList.add(formTppNalogPlatniDto(tmpObj));
                    }
                }
            }
        } catch (NoResultException nre) {
            tppNalogPlatniDtoList = null;
        } catch (Exception e) {
            log.error("Exception is:", e);
        }
        return tppNalogPlatniDtoList;
    }

    public List<TppNalogPlatniDto> getTppNaloziByListaPaymentsRequestId(List<String> listaSifreNaloga) {
        List<TppNalogPlatniDto> tppNalogPlatniDtoList = null;
        try {
            if (listaSifreNaloga != null && listaSifreNaloga.size() > 0) {
                String sql = getBasicSql() + " where pymid_tpp in :listaNaloga ";
                Query query = entityManager.createNativeQuery(sql);
                query.setParameter("listaNaloga", listaSifreNaloga);
                List<Object[]> nalogObjectList = (List<Object[]>) query.getResultList();
                if (nalogObjectList != null) {
                    tppNalogPlatniDtoList = new ArrayList<>();
                    for (Object[] tmpObj : nalogObjectList) {
                        tppNalogPlatniDtoList.add(formTppNalogPlatniDto(tmpObj));
                    }
                }
            }
        } catch (NoResultException nre) {
            tppNalogPlatniDtoList = null;
        } catch (Exception e) {
            log.error("Exception is:", e);
        }
        return tppNalogPlatniDtoList;
    }

    /**
     * Get TppNalogPlatniDto by sifra, payment service, payment product
     *
     * @param paymentId
     * @param paymentProduct
     * @param paymentService
     * @param tppId
     * @return TppNalogPlatniDto
     */
    public TppNalogPlatniDto getTppNalogBySifraProductServiceTpp(Integer paymentId, String paymentService, String paymentProduct, String tppId) {
        TppNalogPlatniDto result = TppNalogPlatniDto.builder().build();
        if (paymentId != null && StringUtils.isNotBlank(paymentService) && StringUtils.isNotBlank(tppId) && StringUtils.isNotBlank(paymentProduct)) {
            //dohvat naloga
            TppNalogPlatniDto tppNalogPlatniDto = getTppNalogBySifra(paymentId);
            if (tppNalogPlatniDto != null) {
                if (StringUtils.equals(paymentService, tppNalogPlatniDto.getPaymentService()) && StringUtils.equals(paymentProduct, tppNalogPlatniDto.getPaymentProduct()) && StringUtils.equals(tppId, tppNalogPlatniDto.getTppId())) {
                    result = tppNalogPlatniDto;
                    result.setIsValid(true);
                } else {
                    result.setIsValid(false);
                    result.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_payment", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                }
            } else {
                result.setIsValid(false);
                result.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_payment", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
            }
        } else {
            result.setIsValid(false);
            result.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<String>(Arrays.asList("psd2_req_id_ser_pro_tpp", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))));
        }
        return result;
    }

    /**
     * Get transaction status by sifra, payment service
     *
     * @param paymentId
     * @param paymentService
     * @param tppId
     * @return TppNalogPlatniDto
     */
    public TransactionStatusResponseDto getTppNalogStatusBySifraServiceTpp(Integer paymentId, String paymentService, String tppId, String paymentProduct) throws AbitRuleException {
        TransactionStatusResponseDto result = new TransactionStatusResponseDto();
        if (paymentId != null && StringUtils.isNotBlank(paymentService) && StringUtils.isNotBlank(tppId) && StringUtils.isNotBlank(paymentProduct)) {
            //dohvat naloga
            TppNalogPlatniDto tppNalogPlatniDto = getTppNalogBySifra(paymentId);
            if (tppNalogPlatniDto != null) {
                if (StringUtils.equals(paymentService, tppNalogPlatniDto.getPaymentService()) && StringUtils.equals(tppId, tppNalogPlatniDto.getTppId()) && StringUtils.equals(paymentProduct, tppNalogPlatniDto.getPaymentProduct())) {
                    result.setTransactionStatus(resolvePsd2StatusNaloga(tppNalogPlatniDto));
                    result.setIsValid(true);
                } else {
                    result.setIsValid(false);
                    result.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_payment", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                }
            } else {
                result.setIsValid(false);
                result.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_payment", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
            }
        } else {
            result.setIsValid(false);
            result.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_req_id_ser_pro_tpp", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
        }
        return result;
    }

    /**
     * Get transaction status by sifra, payment service
     *
     * @param datotekaId
     * @param paymentProduct
     * @param tppId
     * @return TppNalogPlatniDto
     */
    public TransactionStatusResponseDto getBulkTppNalogStatus(Integer datotekaId, String tppId, String paymentProduct) throws AbitRuleException {
        TransactionStatusResponseDto result = new TransactionStatusResponseDto();
        if (datotekaId != null && StringUtils.isNotBlank(paymentProduct) && StringUtils.isNotBlank(tppId)) {
            //dohvat naloga
            List<TppNalogPlatniDto> listTppNaloga = getTppNaloziByDatotekaBasket(datotekaId, TppNalogPlatniDto.TIP_GRUPIRANJA_DATOTEKA);
            if (listTppNaloga != null && !listTppNaloga.isEmpty()) {
                if (StringUtils.equals(PaymentServiceType.BULK.getValue(), listTppNaloga.get(0).getPaymentService()) && StringUtils.equals(tppId, listTppNaloga.get(0).getTppId())) {
                    //TODO && StringUtils.equals(paymentProduct, listTppNaloga.get(0).getPaymentProduct())) {
                    //dohvat podataka o datoteci
                    Psd2DatotekaDto filterDatoteka = new Psd2DatotekaDto();
                    filterDatoteka.setSifra(datotekaId);
                    List<Psd2DatotekaDto> psd2DatotekaDtoList = datotekaDao.filterList(filterDatoteka);
                    for (TppNalogPlatniDto tppNalogPlatniDto : listTppNaloga) {
                        tppNalogPlatniDto.setStatusPsd2(resolvePsd2StatusNaloga(tppNalogPlatniDto));
                    }
                    Document pain002 = Psd2Util.formPain002Xml(listTppNaloga, psd2DatotekaDtoList.get(0));
                    result.setPain002(pain002);
                    result.setIsValid(true);
                } else {
                    result.setIsValid(false);
                    result.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_payment_file", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                }
            } else {
                result.setIsValid(false);
                result.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_payment_file", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
            }
        } else {
            result.setIsValid(false);
            result.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_req_id_pro_tpp", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
        }
        return result;
    }

    private String resolvePsd2StatusNaloga(TppNalogPlatniDto tppNalogPlatniDto) throws AbitRuleException {
        String result = null;
        if (StringUtils.isBlank(tppNalogPlatniDto.getStatus())) {
            if (StringUtils.isBlank(tppNalogPlatniDto.getStatusTpp())) {
                result = TransactionStatus.ACTC.getTransactionStatus();
            } else {
                result = tppNalogPlatniDto.getStatusTpp();
            }
        } else {
            if (StringUtils.equals(tppNalogPlatniDto.getStatus(), Bassx2Constants.Core.NALOG_STATUNAL_LIKVIDNOST) ||
                    StringUtils.equals(tppNalogPlatniDto.getStatus(), Bassx2Constants.Core.NALOG_STATUNAL_POSLAN)) {
                result = TransactionStatus.ACSP.getTransactionStatus();
            } else if (StringUtils.equals(tppNalogPlatniDto.getStatus(), Bassx2Constants.Core.NALOG_STATUNAL_PROKNJIZEN)) {
                result = TransactionStatus.ACSC.getTransactionStatus();
            } else if (StringUtils.equals(tppNalogPlatniDto.getStatus(), Bassx2Constants.Core.NALOG_STATUNAL_VERIFICIRAN) &&
                    tppNalogPlatniDto.getDatumKnjizenja() == null) {
                result = TransactionStatus.PDNG.getTransactionStatus();
            } else if (StringUtils.equals(tppNalogPlatniDto.getStatus(), Bassx2Constants.Core.NALOG_STATUNAL_VERIFICIRAN)) {
                //check raspoloživo RETAIL
                //if (!PartijaUtil.isPartijaPlatni(StringUtils.right(tppNalogPlatniDto.getIbanZaduzenja(), 10))) {
                if (true) { // TODO Ivana - pravi uvjet staviti
                    //check platni raspoloživo
                    Map<Integer, Map<String, BigDecimal>> raspolozivoRetail = new HashMap<>();
                    Set<Integer> partijeZaduzenjaSet = new HashSet<>();
                    //PartijaDto partijaDto = LookupHelper.getICoreDaoProxyRemote().getPartijaDtoByBrojPartije(StringUtils.right(tppNalogPlatniDto.getIbanZaduzenja(), 10)); // stari
                    PartijaDto partijaDto = coreSrvc.getPartijaDtoByBrojPartije(StringUtils.right(tppNalogPlatniDto.getIbanZaduzenja(), 10)); // prazni
                    partijeZaduzenjaSet.add(partijaDto.getSifra());
                    //raspolozivoRetail = RetailSaldoUtil.getRaspolozivoPoAVFOPartijamaIValutamaMap(new ArrayList<>(partijeZaduzenjaSet), entityManager);
                    raspolozivoRetail = null; // TODO Ivana
                    try {
//                        AuthenticationUtils.checkRaspolozivoFO(tppNalogPlatniDto.getIznos(), tppNalogPlatniDto.getIznosNaknade(), tppNalogPlatniDto.getIbanZaduzenja(),
//                                tppNalogPlatniDto.getSifraValute(), raspolozivoRetail.get(partijaDto.getSifra()), entityManager);
                        // TODO Ivana
                        result = TransactionStatus.ACFC.getTransactionStatus();
                    //} catch (AbitRuleException are) {
                    } catch (Exception are) {
                        result = TransactionStatus.ACCP.getTransactionStatus();
                    }
                } else {
                    //check raspoloživo AVPO
                    result = TransactionStatus.ACCP.getTransactionStatus();

                }
                //TODO Check raspolozivo  ACFC Status 1 i danas i ako  ima raspoloživo (odrediti račun pravne ili fizičke osobe i prema tome zvati medotu za raspoloživo)  - radimo kasnije kod  getStatus statusa
            } else if (StringUtils.equals(tppNalogPlatniDto.getStatus(), Bassx2Constants.Core.NALOG_STATUNAL_POVUCEN) ||
                    StringUtils.equals(tppNalogPlatniDto.getStatus(), Bassx2Constants.Core.NALOG_STATUNAL_POVUCEN_B2)) {
                if (tppNalogPlatniDto.getIsOdbijen()) {
                    result = TransactionStatus.RJCT.getTransactionStatus();
                } else {
                    result = TransactionStatus.CANC.getTransactionStatus();
                }
            } else if (StringUtils.equals(tppNalogPlatniDto.getStatus(), Bassx2Constants.Core.NALOG_STATUNAL_UPISAN)) {
                result = TransactionStatus.PATC.getTransactionStatus();
            }
        }
        if (StringUtils.isBlank(result)) {
            result = tppNalogPlatniDto.getStatusTpp();
        }
        return result;
    }

    public void updateSmartLock(List<Integer> nalozi, Integer vremenskiOkvir) throws AbitRuleException {
        try {
            String sql = "update psd2_nalog_tpp set smloc_tpp = :timer where sifra_tpp in :nalozi";
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

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    private TppNalog formEntity(TppNalogPlatniDto nalogDto) {
        TppNalog tppNalog = null;
        if (nalogDto != null) {
            tppNalog = new TppNalog();
            tppNalog.setSifraTpp(nalogDto.getSifraTppNaloga());
            tppNalog.setVrijeTpp(nalogDto.getVrijemeUpisaNaloga() != null ? Date.from(nalogDto.getVrijemeUpisaNaloga().atZone(ZoneId.systemDefault()).toInstant()) : null);
            tppNalog.setTppidTpp(nalogDto.getTppId());
            tppNalog.setReqidTpp(nalogDto.getxRequestId());
            tppNalog.setContyTpp(nalogDto.getContentType());
            tppNalog.setPyprdTpp(nalogDto.getPaymentProduct());
            tppNalog.setPyserTpp(nalogDto.getPaymentService());
            tppNalog.setPsuIpTpp(nalogDto.getPsuIpAddress());
            tppNalog.setPaymentRequestId(nalogDto.getPaymentRequestId());
            tppNalog.setTransactoinStatus(nalogDto.getTransactionStatus().getTransactionStatus());
            tppNalog.setBasketId(nalogDto.getSifraBasketa());


        }
        return tppNalog;
    }

    public TppNalogPlatniDto formTppNalogPlatniDtoFromNalogPlatni(NalogPlatniDto nalog) {
        TppNalogPlatniDto tppNalog = TppNalogPlatniDto.builder().build();
        tppNalog.setIbanZaduzenja(nalog.getIbanZaduzenja());
        tppNalog.setNazivDebtor(nalog.getNazivDebtor());
        tppNalog.setAdresaDebtor(nalog.getAdresaDebtor());
        tppNalog.setGradDebtor(nalog.getGradCreditor());
        tppNalog.setDrzavaDebtor(nalog.getDrzavaDebtor());
        tppNalog.setUltimateDebtorId(nalog.getUltimateDebtorId());
        tppNalog.setUltimateDebtorNaziv(nalog.getUltimateDebtorNaziv());
        tppNalog.setModelZaduzenja(nalog.getModelZaduzenja());
        tppNalog.setPozivNaBrojZaduzenja(nalog.getPozivNaBrojZaduzenja());
        tppNalog.setIbanOdobrenja(nalog.getIbanOdobrenja());
        tppNalog.setNazivCreditor(nalog.getNazivCreditor());
        tppNalog.setAdresaCreditor(nalog.getAdresaCreditor());
        tppNalog.setGradCreditor(nalog.getGradCreditor());
        tppNalog.setDrzavaCreditor(nalog.getDrzavaCreditor());
        tppNalog.setUltimateCreditorId(nalog.getUltimateCreditorId());
        tppNalog.setUltimateCreditorNaziv(nalog.getUltimateCreditorNaziv());
        tppNalog.setModelOdobrenja(nalog.getModelOdobrenja());
        tppNalog.setPozivNaBrojOdobrenja(nalog.getPozivNaBrojOdobrenja());
        tppNalog.setIznos(nalog.getIznos());
        tppNalog.setSifraValute(nalog.getSifraValute());
        tppNalog.setSifraValutePokrica(nalog.getSifraValute());
        tppNalog.setDatumValute(nalog.getKnjizniNalog() != null ? nalog.getKnjizniNalog().getDatumValute() : nalog.getDatumValute());
        tppNalog.setHitnost(nalog.getHitnost());
        tppNalog.setSvrha(nalog.getKnjizniNalog() != null ? nalog.getKnjizniNalog().getSvrha() : nalog.getSvrha());
        tppNalog.setSifraNamjene(nalog.getSifraNamjene());
        tppNalog.setKategorijaNamjene(nalog.getKategorijaNamjene());
        tppNalog.setTrosakInoBanke(nalog.getTrosakInoBanke());
        //tppNalog.setNalogodavateljSpp(nalog.getNalogodavateljSpp()); //TODO ? da li je to swiftNalogodavateljaKorisnika
        if (nalog.getSwiftNalogodavateljaKorisnika() == null) {
            String vbdi = null;
            if (IbanFunctions.isIban(tppNalog.getIbanOdobrenja()) && tppNalog.getIbanOdobrenja() != null && tppNalog.getIbanOdobrenja().startsWith("HR")) {
                vbdi = IbanFunctions.resolveVbdiFromIban(tppNalog.getIbanOdobrenja(), getEntityManager());
            }
            //tppNalog.setSwiftNalogodavateljaKorisnika(DeviznoUtilMin.fillXOnBic(DeviznoUtilMin.fetchSwiftBicByVbdi(vbdi, getEntityManager())));
            tppNalog.setSwiftNalogodavateljaKorisnika(null); // TODO Ivana
        } else {
            //tppNalog.setSwiftNalogodavateljaKorisnika(DeviznoUtilMin.fillXOnBic(nalog.getSwiftNalogodavateljaKorisnika()));
            tppNalog.setSwiftNalogodavateljaKorisnika(null); // TODO  Ivana
        }
        //tppNalog.setMedij(nalog.getMedij()); //TODO da li mi treba
        tppNalog.setDatumKnjizenja(nalog.getKnjizniNalog() != null ? nalog.getKnjizniNalog().getDatumKnjizenja() : nalog.getDatumKnjizenja());
        tppNalog.setStatus(nalog.getStatus());
        tppNalog.setBatchBooking(nalog.getBatchBooking());
        tppNalog.setPainPaymentInfoId(nalog.getPainPaymentInfoId());
        tppNalog.setSifraOznakePosla(nalog.getSifraOznakePosla());
        tppNalog.setNacinIzvrsenja(nalog.getNacinIzvrsenja());
        tppNalog.setSifraTipaNaloga(nalog.getKnjizniNalog() != null ? nalog.getKnjizniNalog().getSifraTipaNaloga() : nalog.getSifraTipaNaloga());
        tppNalog.setSifraKlaseNaloga(nalog.getKnjizniNalog() != null ? nalog.getKnjizniNalog().getSifraKlaseNaloga() : nalog.getSifraKlaseNaloga());
        tppNalog.setPainPaymentInformationId(nalog.getPainPaymentInformationId());
        tppNalog.setPainPaymentMethod(nalog.getPainPaymentMethod());
        return tppNalog;
    }

    public TppNalogPlatniDto initializeTppNalogBeforeCreate(TppNalogPlatniDto nalog) throws AbitRuleException {

        nalog.setVrijemeUpisaNaloga(LocalDateTime.now());
        //nalog.setMedij(Bassx2Constants.PlatniPromet.PLATNI_NALOG_MEDIJ_ELEKTRONSKO_BANKARSTVO);

        if (nalog.getModelZaduzenja().isEmpty()) {
            nalog.setModelZaduzenja("HR99");
        }
        //nalog.setVrstaNaloga(Bassx2Constants.Ib.NALOG_VRSTA_KUNSKI);
        //nalog.setIbKorisnik(sifraIbKorisnika);  //loginBean.getIbKorisnik().getSifra());
        nalog.setSmjer(Bassx2Constants.Core.DUGOVNA_STRANA);
        if (StringUtils.isBlank(nalog.getTrosakInoBanke()))
            nalog.setTrosakInoBanke(Bassx2Constants.PlatniPromet.DEVIZNI_NALOG_TROSAK_INO_BANKE_SHA);

        String brojPartijeZaduzenja = IbanFunctions.resolvePartijaFromIban(nalog.getIbanZaduzenja());
        //nalog.setPartijaZaduzenja(LookupMinHelper.getICoreDaoMinProxyRemote().getPartijaDtoByBrojPartije(brojPartijeZaduzenja));
        //nalog.setSifraLikvidatora(sifraLikvidatora);
        //nalog.setIdent("-001");
        nalog.setIbanOdobrenja(nalog.getIbanOdobrenja().toUpperCase());
        nalog.setDrzavaDebtor(Bassx2Constants.PlatniPromet.DRZAVA_HOME_OZNKADVA);
        // kompenzacija - postavljanje valute pokrica = valuta odobrenja
        if (nalog != null && StringUtils.isNotBlank(nalog.getIbanZaduzenja()) && StringUtils.isNotBlank(nalog.getIbanOdobrenja()) && nalog.getIbanZaduzenja().compareTo(nalog.getIbanOdobrenja()) == 0) {
            nalog.setSifraValutePokrica(nalog.getSifraValute());
        }

        return nalog;
    }

    public TppNalogPlatniDto initializeBatchBookingNalogBeforeCreate(TppNalogPlatniDto nalog,
                                                                     Date datumValute,
                                                                     Integer sifraIbKorisnika,
                                                                     Integer sifraLikvidatora,
                                                                     BigDecimal iznosSum) throws AbitRuleException {

        TppNalogPlatniDto zbirni = null; //(TppNalogPlatniDto) nalog.klone();
        zbirni.setDatumValute(datumValute);
        zbirni.setVrijemeUpisaNaloga(LocalDateTime.now());
        zbirni.setSwiftNalogodavateljaKorisnika(null);

        if (zbirni.getModelZaduzenja().isEmpty()) {
            zbirni.setModelZaduzenja("HR99");
        }
        zbirni.setSmjer(Bassx2Constants.Core.DUGOVNA_STRANA);
        zbirni.setTrosakInoBanke(Bassx2Constants.PlatniPromet.DEVIZNI_NALOG_TROSAK_INO_BANKE_SHA);

        String brojPartijeZaduzenja = IbanFunctions.resolvePartijaFromIban(nalog.getIbanZaduzenja());
//        zbirni.setPartijaZaduzenja(LookupMinHelper.getICoreDaoMinProxyRemote().getPartijaDtoByBrojPartije(brojPartijeZaduzenja));
        //nalog.setIdent("-001");
        zbirni.setIbanOdobrenja(zbirni.getIbanZaduzenja());
        zbirni.setDrzavaDebtor(Bassx2Constants.PlatniPromet.DRZAVA_HOME_OZNKADVA);
        zbirni.setSifraTipaNaloga(Bassx2Constants.Avpo.TIP_NALOGA_SIFRAPOD_FINA_BEZGOTOVINSKI_BATCHBOOKING);
        if (zbirni.isNajavaPrijePotpisa()) {
            zbirni.setSifraTipaNaloga(Bassx2Constants.Avpo.TIP_NALOGA_SIFRAPOD_FINA_BEZGOTOVINSKI_BATCHBOOKING_NAJAVA);
        }
        zbirni.setIznos(iznosSum);
//		zbirni.setIznosNaknade(naknadaSum);
        zbirni.setSifraValute(nalog.getSifraValute());
        zbirni.setNazivCreditor("Batch booking");
        zbirni.setSvrha((zbirni.isNajavaPrijePotpisa() ? "Batch Booking najava " : "Batch Booking ") + nalog.getPainPaymentInfoId());
        zbirni.setAdresaCreditor(null);
        zbirni.setGradCreditor(null);

        return zbirni;
    }

    public NalogAvpoDto formNalogAvpoFromIBNalog(TppNalogPlatniDto nalog) {
        NalogAvpoDto nalogAvpo = new NalogAvpoDto();
        nalogAvpo.getNalogSepa().setIbanZaduzenja(nalog.getIbanZaduzenja());
        nalogAvpo.getNalogSepa().setNazivDebtor(nalog.getNazivDebtor());
        nalogAvpo.getNalogSepa().setAdresaDebtor(nalog.getAdresaDebtor());
        nalogAvpo.getNalogSepa().setGradDebtor(nalog.getGradDebtor());
        nalogAvpo.getNalogSepa().setDrzavaDebtor(nalog.getDrzavaDebtor());
        nalogAvpo.getNalogSepa().setUltimateDebtorId(nalog.getUltimateDebtorId());
        nalogAvpo.getNalogSepa().setUltimateDebtorNaziv(nalog.getUltimateDebtorNaziv());
        nalogAvpo.getNalogSepa().setModelZaduzenja(nalog.getModelZaduzenja());
        nalogAvpo.getNalogSepa().setPozivNaBrojZaduzenja(nalog.getPozivNaBrojZaduzenja());
        nalogAvpo.getNalogSepa().setIbanOdobrenja(nalog.getIbanOdobrenja());
        nalogAvpo.getNalogSepa().setNazivCreditor(nalog.getNazivCreditor());
        nalogAvpo.getNalogSepa().setAdresaCreditor(nalog.getAdresaCreditor());
        nalogAvpo.getNalogSepa().setGradCreditor(nalog.getGradCreditor());
        nalogAvpo.getNalogSepa().setDrzavaCreditor(nalog.getDrzavaCreditor());
        nalogAvpo.getNalogSepa().setUltimateCreditorId(nalog.getUltimateCreditorId());
        nalogAvpo.getNalogSepa().setUltimateCreditorNaziv(nalog.getUltimateCreditorNaziv());
        nalogAvpo.getNalogSepa().setModelOdobrenja(nalog.getModelOdobrenja());
        nalogAvpo.getNalogSepa().setPozivNaBrojOdobrenja(nalog.getPozivNaBrojOdobrenja());
        nalogAvpo.getNalogSepa().setIznos(nalog.getIznos());
        nalogAvpo.getNalogSepa().setSifraValute(nalog.getSifraValute());
        nalogAvpo.getNalogSepa().getKnjizniNalog().setDatumValute(nalog.getDatumValute());
        nalogAvpo.getNalogSepa().setHitnost(nalog.getHitnost());
        nalogAvpo.getNalogSepa().setSwiftBicNalogodavateljaKorisnika(nalog.getSwiftNalogodavateljaKorisnika());
        nalogAvpo.getNalogSepa().getKnjizniNalog().setSvrha(nalog.getSvrha());
        nalogAvpo.getNalogSepa().getKnjizniNalog().setSifraTipaNaloga(nalog.getSifraTipaNaloga());
        nalogAvpo.getNalogSepa().setSifraNamjene(nalog.getSifraNamjene());
        nalogAvpo.getNalogSepa().setKategorijaNamjene(nalog.getKategorijaNamjene());
        nalogAvpo.getNalogSepa().setTrosakInoBanke(nalog.getTrosakInoBanke());
        //nalogAvpo.getNalogSepa().setNalogodavateljSpp(nalog.getNalogodavateljSpp());
        nalogAvpo.getNalogSepa().setMedij(Bassx2Constants.PlatniPromet.PLATNI_NALOG_MEDIJ_PISP);
        nalogAvpo.getNalogSepa().getKnjizniNalog().setSifraLikvidatora(Bassx2Constants.Core.SIFRALIK_PSD2_TPP);
        //nalogAvpo.getNalogSepa().setPartijaZaduzenja(nalog.getPartijaZaduzenja());
        nalogAvpo.getNalogSepa().setSifraValutePokrica(nalog.getSifraValute());
        nalogAvpo.getNalogSepa().setBtchBookg(nalog.getBatchBooking());
        nalogAvpo.getNalogSepa().setPainPaymentInfoId(nalog.getPainPaymentInfoId());
        //nalogAvpo.getNalogSepa().setIdent(nalog.getIdent());
        if (Arrays.asList(IBNalog.DRZAVE_IBAN_BEZ_KONTROLE).contains(nalog.getDrzavaCreditor())) {
            nalogAvpo.getNalogSepa().getUtilMap().put(Bassx2Constants.PlatniPromet.UTILMAP_KEY_ISKLJUCI_PROVJERA_IBAN, Boolean.TRUE);
        }
        nalogAvpo.getNalogSepa().setPainPaymentInformationId(nalog.getPainPaymentInformationId());
        nalogAvpo.getNalogSepa().setPainPaymentMethod(nalog.getPainPaymentMethod());
        //Maknuta kontrola raspolozivog za pojedinacne batch booking naloge
//        if((nalog.getBatchBooking() != null && nalog.getBatchBooking() && nalog.getSifraBatchBookingNaloga() != null) || nalog.isNajavaPrijePotpisa()){
//            nalogAvpo.getNalogSepa().getUtilMap().put(Bassx2Constants.PlatniPromet.UTILMAP_KEY_RSAPOLOZIVO, Boolean.TRUE);
//        }

        return nalogAvpo;
    }


    public void stornoBatchBookingPlatni(TppNalogPlatniDto batchBookingNalog) {
        //update status knjiznih naloga
        String sql = "update nalog set statu_nal = ?1 where sifra_nal in (select nalkn_tpp from psd2_nalog_tp where (sifra_tpp = ?2 or batzb_tpp = ?3) and batch_tpp = ?4)";
        Query q = getEntityManager().createNativeQuery(sql);
        q.setParameter(1, Bassx2Constants.PlatniPromet.NALOG_STATUS_POVUCEN_B2);
        q.setParameter(2, batchBookingNalog.getSifraTppNaloga());
        q.setParameter(3, batchBookingNalog.getSifraTppNaloga());
        q.setParameter(4, "Y");
        q.executeUpdate();
    }

    public void stornoBBAuthorizations(TppNalogPlatniDto batchBookingNalog) {
        //update status knjiznih naloga
        String sql = "update psd2_authorization set scast_aut = ?1 where objct_aut in (select sifra_tpp from psd2_nalog_tp where (sifra_tpp = ?2 or batzb_tpp = ?3) and batch_tpp = ?4) and otype_aut = ?5 ";
        Query q = getEntityManager().createNativeQuery(sql);
        q.setParameter(1, ScaStatus.FAILED.getValue());
        q.setParameter(2, batchBookingNalog.getSifraTppNaloga());
        q.setParameter(3, batchBookingNalog.getSifraTppNaloga());
        q.setParameter(4, "Y");
        q.setParameter(5, Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue());
        q.executeUpdate();
    }

    public List<TppNalogPlatniDto> getNalogTppListByBasketId(Integer basketId) {
        List<TppNalogPlatniDto> listaNaloga = new ArrayList<>();
        String sql = getBasicSql() + " where baskt_tpp = :baskt_tpp";
        try {
            Query query = entityManager.createNativeQuery(sql);
            query.setParameter("baskt_tpp", basketId);
            List<Object[]> resultQueryList = query.getResultList();
            for (Object[] objArray : resultQueryList) {
                TppNalogPlatniDto tppNalogDto = formTppNalogPlatniDto(objArray);
                listaNaloga.add(tppNalogDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaNaloga;
    }

    public void azuritajBatchBookingPlatni(TppNalogPlatniDto batchBookingNalog) {
        //update prena_nal
        String sql = "update nalog set prena_nal = (select nalkn_tpp from psd2_nalog_tpp where sifra_tpp = ?1 and batch_tpp = ?2) where sifra_nal in (select nalkn_tpp from psd2_nalog_tpp where batzb_tpp = ?1 and batch_tpp = ?2)";
        Query q = getEntityManager().createNativeQuery(sql);
        q.setParameter(1, batchBookingNalog.getSifraTppNaloga());
        q.setParameter(2, "Y");
        q.executeUpdate();
        q = null;
        //update bb zbirni nalog
        sql = "update nalog_platni set sifop_trn = 'BBX' where nalkn_trn = (select nalkn_tpp from psd2_nalog_tpp where sifra_tpp = ?1 and batch_tpp = ?2)";
        q = getEntityManager().createNativeQuery(sql);
        q.setParameter(1, batchBookingNalog.getSifraTppNaloga());
        q.setParameter(2, "Y");
        q.executeUpdate();
        q = null;
        //update bb zbirni nalog
        sql = "update nalog_platni set sifop_trn = 'BBN' where nalkn_trn in (select nalkn_tpp from psd2_nalog_tpp where batzb_tpp = ?1 and batch_tpp = ?2)";
        q = getEntityManager().createNativeQuery(sql);
        q.setParameter(1, batchBookingNalog.getSifraTppNaloga());
        q.setParameter(2, "Y");
        q.executeUpdate();
    }

    public List<TppNalogPlatniDto> getTppNalogList(TppNalogFilterDto filterDto) {
        List<TppNalogPlatniDto> resultNalogList = null;
        String sql = getFilteredSql(filterDto);
        try {
            Query query = getEntityManager().createNativeQuery(sql);
            List<Object[]> resultQueryList = query.getResultList();
            if (resultQueryList != null) {
                resultNalogList = new ArrayList<>();
                for (Object[] objArray : resultQueryList) {
                    TppNalogPlatniDto tppNalogDto = formTppNalogPlatniDto(objArray);
                    resultNalogList.add(tppNalogDto);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultNalogList;

    }

    private String getFilteredSql(TppNalogFilterDto filterDto) {
        String sql = getBasicSql();
        if (filterDto != null) {
            if (filterDto.getDatumOd() != null) {
                sql += "where date(datva_tpp) >= TO_DATE('" + sdfSql.format(filterDto.getDatumOd()) + "','%Y-%m-%d') ";
            }
            if (filterDto.getDatumDo() != null) {
                sql += " and date(datva_tpp) <= TO_DATE('" + sdfSql.format(filterDto.getDatumDo()) + "','%Y-%m-%d') ";
            }
            if (filterDto.getIznosOd() != null) {
                sql += " and iznos_tpp >= " + filterDto.getIznosOd().doubleValue() + "";
            }
            if (filterDto.getIznosDo() != null) {
                sql += " and iznos_tpp <= " + filterDto.getIznosDo().doubleValue() + "";
            }
            if (filterDto.getStatus() != null) {
                if (filterDto.getStatus().equals(IBNalog.STATUS_IB_IZVRSENI)) {
                    sql += " and n.statu_nal = '" + Bassx2Constants.Core.NALOG_STATUNAL_PROKNJIZEN + "'";
                } else if (filterDto.getStatus().equals(IBNalog.STATUS_IB_NEIZVRSENI)) {
                    sql += " and nalkn_tpp is null and smloc_tpp is null ";
                } else if (filterDto.getStatus().equals(IBNalog.STATUS_IB_OBRADA) || filterDto.getStatus().compareTo(Bassx2Constants.Core.NALOG_STATUNAL_UPISAN) == 0) {
                    sql += " and n.statu_nal in ('" + Bassx2Constants.Core.NALOG_STATUNAL_UPISAN + "','"
                            + Bassx2Constants.Core.NALOG_STATUNAL_VERIFICIRAN + "','"
                            + Bassx2Constants.Core.NALOG_STATUNAL_LIKVIDNOST + "','"
                            + Bassx2Constants.Core.NALOG_STATUNAL_POSLAN + "')";
                } else if (filterDto.getStatus().equals(IBNalog.STATUS_IB_OBRISAN)) {
                    sql += " and n.statu_nal in ('" + Bassx2Constants.Core.NALOG_STATUNAL_POVUCEN + "', '" + Bassx2Constants.Core.NALOG_STATUNAL_POVUCEN_B2 + "') ";
                } else if (filterDto.getStatus().equals(IBNalog.STATUS_IB_DEFAULT)) {
                    //default status kod početnog pregleda (bez proknjiženih i bez povučenih)
                    sql += " and ((n.statu_nal is null) or (n.statu_nal not in ('" + Bassx2Constants.Core.NALOG_STATUNAL_PROKNJIZEN + "', '" + Bassx2Constants.Core.NALOG_STATUNAL_POVUCEN + "', '" + Bassx2Constants.Core.NALOG_STATUNAL_POVUCEN_B2 + "'))) ";
                } else if (filterDto.getStatus().equals(IBNalog.STATUS_IB_OBRADA_I_NAJAVA)) {
                    //default status kod početnog pregleda (bez proknjiženih i bez povučenih)
                    sql += " and n.statu_nal not in ('" + Bassx2Constants.Core.NALOG_STATUNAL_PROKNJIZEN + "', '" + Bassx2Constants.Core.NALOG_STATUNAL_POVUCEN + "', '" + Bassx2Constants.Core.NALOG_STATUNAL_POVUCEN_B2 + "') ";
                }
                filterDto.setStatus(null);
            }
            if (StringUtils.isNotBlank(filterDto.getIbanOdobrenja())) {
                sql += " and (upper(to_char(ibano_tpp)) LIKE'" + filterDto.getIbanOdobrenja() + "')";
                filterDto.setIbanOdobrenja(null);
            }
            if (StringUtils.isNotBlank(filterDto.getNazivCreditor())) {
                sql += " and (upper(to_char(cnazv_tpp)) LIKE'" + filterDto.getNazivCreditor() + "')";
                filterDto.setNazivCreditor(null);
            }
            if (StringUtils.isNotBlank(filterDto.getSvrha())) {
                sql += " and (upper(to_char(svrha_tpp)) LIKE'" + filterDto.getSvrha() + "')";
                filterDto.setSvrha(null);
            }

            if (filterDto != null && filterDto.getIbanZaduzenja() != null) {
                sql += " and (ibanz_tpp = '" + filterDto.getIbanZaduzenja() + "' or ibano_tpp = '" + filterDto.getIbanZaduzenja() + "') ";
                filterDto.setIbanZaduzenja(null);
            }
            if (filterDto != null && filterDto.getValutaZaduzenja() != null) {
                sql += "and (valut_tpp = '" + filterDto.getValutaZaduzenja() + "' or valpk_tpp = '" + filterDto.getValutaZaduzenja() + "') ";
                filterDto.setValutaZaduzenja(null);
            }


        }
        return sql;
    }

    public void stornoBasketAuthorizations(Integer sifraBasket) {
        //update status knjiznih naloga
        String sql = " update psd2_authorization set scast_aut = ?1 where objct_aut = ?2 and otype_aut = ?3 ";
        Query q = getEntityManager().createNativeQuery(sql);
        q.setParameter(1, ScaStatus.FAILED.getValue());
        q.setParameter(2, sifraBasket);
        q.setParameter(3, Psd2Constants.AuthorizationObjectType.BASKET_AUTHORIZATION.getValue());
        q.executeUpdate();
    }

    public void stornoBasketPlatni(Integer sifraBasket) {
        //update status knjiznih naloga
        String sql = "update nalog set statu_nal = ?1 where sifra_nal in (select nalkn_tpp from psd2_nalog_tpp where baskt_tpp = ?2) ";
        Query q = getEntityManager().createNativeQuery(sql);
        q.setParameter(1, Bassx2Constants.PlatniPromet.NALOG_STATUS_POVUCEN_B2);
        q.setParameter(2, sifraBasket);
        q.executeUpdate();
    }


    /**
     * Validacija podataka i formiranje Tpp naloga iz psd2 (json) naloga
     *
     * @param psd2Nalog
     * @param globals
     * @return tppNalogPlatniDto
     */
    public TppNalogPlatniDto validateAndSetTppNalogKupoprodaja(SinglePaymentDto psd2Nalog, Globals globals) throws AbitRuleException {
        TppNalogPlatniDto result = TppNalogPlatniDto.builder().build();
        result.setIsValid(Boolean.TRUE);
        try {
            //validate is nalog kupoprodaje
            if (!Psd2Util.isNalogKupoprodaje(psd2Nalog)) {
                result.setIsValid(Boolean.FALSE);
                result.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.BAD_REQUEST.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_kupoprodaja_err_empty_nalog", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                return result;
            }
            //iban
            //if (!PlatniUtil.isRacunMojeBanke(psd2Nalog.getCreditorAccount().getIban().trim())) {
            if (true) { // TODO Ivana - dodati pravi uvjet
                result.setIsValid(Boolean.FALSE);
                result.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.BAD_REQUEST.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_kupoprodaja_err_iban", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                return result;
            }
            result.setIbanOdobrenja(psd2Nalog.getCreditorAccount().getIban().trim());
            result.setIbanZaduzenja(psd2Nalog.getCreditorAccount().getIban().trim());

            //datum valute
            if (StringUtils.isNotBlank(psd2Nalog.getRequestedExecutionDate())) {
                SimpleDateFormat dateFormater = new SimpleDateFormat(Psd2Constants.DATE_PSD2_FORMAT);
                dateFormater.setLenient(false);
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Psd2Constants.DATE_PSD2_FORMAT);
                    LocalDate datumValute = LocalDate.parse(psd2Nalog.getRequestedExecutionDate(), formatter);
                    if (datumValute.isBefore(LocalDate.now())) {
                        result.setIsValid(Boolean.FALSE);
                        result.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.BAD_REQUEST.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_requestedExecutionDateError", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                        return result;
                    }
                    Date datumVal = dateFormater.parse(psd2Nalog.getRequestedExecutionDate());
                    result.setDatumValute(datumVal);

                } catch (ParseException e) {
                    result.setIsValid(Boolean.FALSE);
                    result.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.BAD_REQUEST.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_kupoprodaja_err_datum_parse", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                    return result;
                }
            } else {
                result.setDatumValute(Calendar.getInstance().getTime());
            }
            //instructed amount
            if (psd2Nalog.getInstructedAmount() == null || StringUtils.isBlank(psd2Nalog.getInstructedAmount().getAmount()) || StringUtils.isBlank(psd2Nalog.getInstructedAmount().getCurrency())) {
                result.setIsValid(Boolean.FALSE);
                result.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.BAD_REQUEST.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_kupoprodaja_err_instructedAmount", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                return result;
            }
            //amount
            BigDecimal iznos = BigDecimal.ZERO;
            try {
                iznos = new BigDecimal(psd2Nalog.getInstructedAmount().getAmount());
                if (Psd2Util.getNumberOfDecimalPlaces(iznos) > 2) {
                    result.setIsValid(Boolean.FALSE);
                    result.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.BAD_REQUEST.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_amountIllegalNumberOfDecimalPlaces", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                    return result;
                }
            } catch (NumberFormatException nre) {
                result.setIsValid(Boolean.FALSE);
                result.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.BAD_REQUEST.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_amountIllegalFormat", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                return result;
            }
            //valuta - instructed ammount currency ne smije biti HRK
            if (StringUtils.equals(psd2Nalog.getInstructedAmount().getCurrency(), Bassx2Constants.Core.VALUTA_OZNKAVAL_HOME_CURRENCY)) {
                result.setIsValid(Boolean.FALSE);
                result.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.BAD_REQUEST.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_kupoprodaja_err_instructed_ammount_curreny", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                return result;
            }
            //valuta ispravna valuta
            List<String> tecajna = Psd2Util.getValuteTecajneListe(getEntityManager());
            if (!tecajna.contains(psd2Nalog.getInstructedAmount().getCurrency()) || !tecajna.contains(psd2Nalog.getCreditorAccount().getCurrency()) || !tecajna.contains(psd2Nalog.getDebtorAccount().getCurrency())) {
                result.setIsValid(Boolean.FALSE);
                result.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.BAD_REQUEST.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_kupoprodaja_err_curreny_not_exist", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                return result;
            }
            if (!StringUtils.equals(psd2Nalog.getDebtorAccount().getCurrency(), Bassx2Constants.Core.VALUTA_OZNKAVAL_HOME_CURRENCY) && !StringUtils.equals(psd2Nalog.getCreditorAccount().getCurrency(), Bassx2Constants.Core.VALUTA_OZNKAVAL_HOME_CURRENCY)) {
                result.setIsValid(Boolean.FALSE);
                result.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.BAD_REQUEST.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_kupoprodaja_err_curreny_cr_de1", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                return result;
            }
            if (StringUtils.equals(psd2Nalog.getDebtorAccount().getCurrency(), Bassx2Constants.Core.VALUTA_OZNKAVAL_HOME_CURRENCY)) {
                if (!StringUtils.equals(psd2Nalog.getCreditorAccount().getCurrency(), psd2Nalog.getInstructedAmount().getCurrency())) {
                    result.setIsValid(Boolean.FALSE);
                    result.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.BAD_REQUEST.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_kupoprodaja_err_curreny_cr_de", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                    return result;
                }
            }
            if (StringUtils.equals(psd2Nalog.getCreditorAccount().getCurrency(), Bassx2Constants.Core.VALUTA_OZNKAVAL_HOME_CURRENCY)) {
                if (!StringUtils.equals(psd2Nalog.getDebtorAccount().getCurrency(), psd2Nalog.getInstructedAmount().getCurrency())) {
                    result.setIsValid(Boolean.FALSE);
                    result.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.BAD_REQUEST.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_kupoprodaja_err_curreny_cr_de", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                    return result;
                }
            }
            //partija/komitent
            //PartijaDto partijaDto = PlatniUtil.getPartijaDtoByBrojPartije(PlatniUtil.getPartiParFromIban(result.getIbanZaduzenja()), null, entityManager, false);
            PartijaDto partijaDto = null; // TODO Ivana
            result.setSifraKomitenta(partijaDto.getSifraVlasnika());
            result.setSifraPartijeOdobrenja(partijaDto.getSifra());
            result.setSifraPartijeZaduzenja(partijaDto.getSifra());
            //tečaj
            result.setTipTecajaOdobrenja(GenericBassxConstants.Core.TIP_TECAJA_PRODAJNI);
            result.setTipTecajaZaduzenja(GenericBassxConstants.Core.TIP_TECAJA_KUPOVNI);

            //kupnja valute - valuta, smjer (npr. kupujem 10 EURA)
            if (StringUtils.equals(psd2Nalog.getDebtorAccount().getCurrency(), Bassx2Constants.Core.VALUTA_OZNKAVAL_HOME_CURRENCY)) {
                result.setSmjer(GenericBassxConstants.Core.POTRAZNA_STRANA);
                result.setValutaZaduzenja(Bassx2Constants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY);
                //result.setValutaOdobrenja(PlatniUtil.zamijeniOznakuValute2(psd2Nalog.getCreditorAccount().getCurrency(), getEntityManager()));
                result.setValutaOdobrenja(null); // TODO Ivana
                result.setIznosOdobrenja(iznos);
                result = zadanIznosOdobrenje(result);
                if (!result.getIsValid()) {
                    return result;
                }
            }
            //prodaja valute - valuta, smjer
            if (StringUtils.equals(psd2Nalog.getCreditorAccount().getCurrency(), Bassx2Constants.Core.VALUTA_OZNKAVAL_HOME_CURRENCY)) {
                result.setSmjer(GenericBassxConstants.Core.DUGOVNA_STRANA);
                // result.setValutaZaduzenja(PlatniUtil.zamijeniOznakuValute2(psd2Nalog.getDebtorAccount().getCurrency(), getEntityManager()));
                result.setValutaZaduzenja(null); // TODO Ivana
                result.setValutaOdobrenja(Bassx2Constants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY);
                result.setIznosZaduzenja(iznos);
                result = zadanIznosZaduzenje(result);
                if (!result.getIsValid()) {
                    return result;
                }
            }
            //initialize
            result.setVrijemeUpisaNaloga(LocalDateTime.now());
            //result.setIsPravnaOsoba(PartijaKomitentUtil.isPravnaOsobaByBrojPartije(partijaDto.getBroj(), entityManager));
            result.setIsPravnaOsoba(null); // TODO Ivana
            if (result.isPravnaOsoba()) {
                result.setSifraKlaseNaloga(GenericBassxConstants.PlatniPromet.KLASA_NALOGA_SIFRAKNA_DEVIZNO_TRZISTE_PRAVNE_OSOBE);
            } else {
                result.setSifraKlaseNaloga(GenericBassxConstants.PlatniPromet.KLASA_NALOGA_SIFRAKNA_DEVIZNO_TRZISTE_FIZICKE_OSOBE);
            }
            //postavljanje naziva Creditora za bolji prikaz na popisu naloga za potpis - postavljen i Debtor (u ib-u se to dohvaća preko ovlaštenja)
            //Object[] primatelj = PlatniUtil.primateljPlatitelj(result.getIbanOdobrenja(), getEntityManager());
            Object[] primatelj = null; // TODO Ivana
            if (primatelj != null) {
                result.setNazivCreditor((String) primatelj[0]);
                result.setNazivDebtor(result.getNazivCreditor());
            }
            result.setVrstaNaloga(GenericBassxConstants.Ib.NALOG_VRSTA_KUPNJA_DEVIZA);
            result.setTppId(psd2Nalog.getTppId());
            result.setxRequestId(psd2Nalog.getxRequestId());
            result.setContentType(psd2Nalog.getContentType());
            result.setPaymentProduct(psd2Nalog.getPaymentProduct());
            result.setPaymentService(psd2Nalog.getPaymentService());
            result.setPsuIpAddress(psd2Nalog.getPsuIpAddress());
            result.setTppRedirectUri(psd2Nalog.getTppRedirectUri());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result.setIsValid(Boolean.FALSE);
            result.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.BAD_REQUEST.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_kupoprodaja_err_setNalogKupoprodaje", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
            return result;
        }
        return result;
    }

    public TppNalogPlatniDto initializeKupnjaDeviza(TppNalogPlatniDto nalog) throws AbitRuleException {
        //partija/komitent
        //PartijaDto partijaDto = PlatniUtil.getPartijaDtoByBrojPartije(PlatniUtil.getPartiParFromIban(nalog.getIbanZaduzenja()), null, entityManager, false);
        PartijaDto partijaDto = null; // TODO IVana
        nalog.setSifraKomitenta(partijaDto.getSifraVlasnika());
        nalog.setSifraPartijeOdobrenja(partijaDto.getSifra());
        nalog.setSifraPartijeZaduzenja(partijaDto.getSifra());

        //tečaj
        nalog.setTipTecajaOdobrenja(GenericBassxConstants.Core.TIP_TECAJA_PRODAJNI);
        nalog.setTipTecajaZaduzenja(GenericBassxConstants.Core.TIP_TECAJA_KUPOVNI);

        nalog.setVrijemeUpisaNaloga(LocalDateTime.now());
        //nalog.setIsPravnaOsoba(PartijaKomitentUtil.isPravnaOsobaByBrojPartije(partijaDto.getBroj(), entityManager));
        nalog.setIsPravnaOsoba(null); // TODO Ivana
        if (nalog.isPravnaOsoba()) {
            nalog.setSifraKlaseNaloga(GenericBassxConstants.PlatniPromet.KLASA_NALOGA_SIFRAKNA_DEVIZNO_TRZISTE_PRAVNE_OSOBE);
        } else {
            nalog.setSifraKlaseNaloga(GenericBassxConstants.PlatniPromet.KLASA_NALOGA_SIFRAKNA_DEVIZNO_TRZISTE_FIZICKE_OSOBE);
        }
        return nalog;
    }


    public TppNalogPlatniDto zadanIznosOdobrenje(TppNalogPlatniDto tppNalogPlatniDto) {
        tppNalogPlatniDto = postaviPotrazniTecaj(tppNalogPlatniDto);
        //postavljanje kontra iznosa
        if (tppNalogPlatniDto.getIznosOdobrenja() != null && tppNalogPlatniDto.getTecajZaduzenja() != null && tppNalogPlatniDto.getTecajOdobrenja() != null) {
            tppNalogPlatniDto.setIznosZaduzenja(tppNalogPlatniDto.getIznosOdobrenja().multiply(tppNalogPlatniDto.getTecajOdobrenja()).divide(tppNalogPlatniDto.getTecajZaduzenja(), 2, RoundingMode.HALF_UP));
        }
        return tppNalogPlatniDto;
    }

    public TppNalogPlatniDto zadanIznosZaduzenje(TppNalogPlatniDto tppNalogPlatniDto) {
        tppNalogPlatniDto = postaviDugovniTecaj(tppNalogPlatniDto);
        // postavljanje kontra iznosa
        if (tppNalogPlatniDto.getIznosZaduzenja() != null && tppNalogPlatniDto.getTecajZaduzenja() != null && tppNalogPlatniDto.getTecajOdobrenja() != null) {
            tppNalogPlatniDto.setIznosOdobrenja(tppNalogPlatniDto.getIznosZaduzenja().multiply(tppNalogPlatniDto.getTecajZaduzenja()).divide(tppNalogPlatniDto.getTecajOdobrenja(), 2, RoundingMode.HALF_UP));
        }
        return tppNalogPlatniDto;
    }

    public TppNalogPlatniDto postaviPotrazniTecaj(TppNalogPlatniDto tppNalogPlatniDto) {
        tppNalogPlatniDto = postaviPotrazniTecaj(true, tppNalogPlatniDto);
        return tppNalogPlatniDto;
    }

    public TppNalogPlatniDto postaviDugovniTecaj(TppNalogPlatniDto tppNalogPlatniDto) {
        tppNalogPlatniDto = postaviDugovniTecaj(true, tppNalogPlatniDto);
        return tppNalogPlatniDto;
    }

    private TppNalogPlatniDto postaviPotrazniTecaj(boolean setTecajDugovno, TppNalogPlatniDto tppNalogPlatniDto) {
        if (tppNalogPlatniDto.getDatumValute() != null && tppNalogPlatniDto.getValutaOdobrenja() != null && StringUtils.isNotBlank(tppNalogPlatniDto.getTipTecajaOdobrenja())) {
            if (tppNalogPlatniDto.getValutaOdobrenja().equals(GenericBassxConstants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY)) {
                tppNalogPlatniDto.setRegularniTecajOdobrenja(BigDecimal.ONE);
            } else {
                try {
                    //PovlasteniTecajDto povlasteniTecaj = PovlasteniTecajUtil.getPovlasteniTecajIb(tppNalogPlatniDto.getSifraKomitenta(), tppNalogPlatniDto.getValutaOdobrenja(), Calendar.getInstance().getTime(), tppNalogPlatniDto.getTipTecajaOdobrenja(), tppNalogPlatniDto.getSifra(), getEntityManager());
                    PovlasteniTecajDto povlasteniTecaj = null; // TODO Ivana
                    if (povlasteniTecaj != null) {
                        tppNalogPlatniDto.setPovlasteniTecajOdobrenja(povlasteniTecaj.getTecaj());
                        tppNalogPlatniDto.setMaxIznosOdobrenja(povlasteniTecaj.getIznosTransakcije());
                        tppNalogPlatniDto.setSifraPovlastenogTecaja(povlasteniTecaj.getSifra());
                    }
                    //tppNalogPlatniDto.setRegularniTecajOdobrenja(LookupMinHelper.getICoreDaoMinProxyRemote().getTecajNaDan(tppNalogPlatniDto.getDatumValute() != null ? tppNalogPlatniDto.getDatumValute() : GenericDateUtil.today(), LookupMinHelper.getICoreDaoMinProxyRemote().findValutaBySifra(tppNalogPlatniDto.getValutaOdobrenja()), tppNalogPlatniDto.getTipTecajaOdobrenja()));
                    tppNalogPlatniDto.setRegularniTecajOdobrenja(null); // TODO Ivana
                //} catch (AbitRuleException e) {
                } catch (Exception e) {
                    log.debug(e.getMessage(), e);
                    tppNalogPlatniDto.setIsValid(Boolean.FALSE);
                    tppNalogPlatniDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.BAD_REQUEST.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_kupoprodaja_err_izracunTecaja", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                    return tppNalogPlatniDto;
                }
            }
        } else {
            tppNalogPlatniDto.setRegularniTecajOdobrenja(null);
        }
        if (setTecajDugovno) {
            tppNalogPlatniDto = postaviDugovniTecaj(false, tppNalogPlatniDto);
        }
        return tppNalogPlatniDto;
    }

    private TppNalogPlatniDto postaviDugovniTecaj(boolean setTecajPotrazno, TppNalogPlatniDto tppNalogPlatniDto) {
        if (tppNalogPlatniDto.getDatumValute() != null && tppNalogPlatniDto.getValutaZaduzenja() != null && StringUtils.isNotBlank(tppNalogPlatniDto.getTipTecajaZaduzenja())) {
            if (tppNalogPlatniDto.getValutaZaduzenja().equals(GenericBassxConstants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY)) {
                tppNalogPlatniDto.setRegularniTecajZaduzenja(BigDecimal.ONE);
            } else {
                try {
                    //PovlasteniTecajDto povlasteniTecaj = PovlasteniTecajUtil.getPovlasteniTecajIb(tppNalogPlatniDto.getSifraKomitenta(), tppNalogPlatniDto.getValutaZaduzenja(), Calendar.getInstance().getTime(), tppNalogPlatniDto.getTipTecajaZaduzenja(), tppNalogPlatniDto.getSifra(), getEntityManager());
                    PovlasteniTecajDto povlasteniTecaj = null; // TODO Ivana
                    if (povlasteniTecaj != null) {
                        tppNalogPlatniDto.setPovlasteniTecajZaduzenja(povlasteniTecaj.getTecaj());
                        tppNalogPlatniDto.setMaxIznosZaduzenja(povlasteniTecaj.getIznosTransakcije());
                        tppNalogPlatniDto.setSifraPovlastenogTecaja(povlasteniTecaj.getSifra());
                    }
                    //tppNalogPlatniDto.setRegularniTecajZaduzenja(LookupMinHelper.getICoreDaoMinProxyRemote().getTecajNaDan(tppNalogPlatniDto.getDatumValute() != null ? tppNalogPlatniDto.getDatumValute() : GenericDateUtil.today(), LookupMinHelper.getICoreDaoMinProxyRemote().findValutaBySifra(tppNalogPlatniDto.getValutaZaduzenja()), tppNalogPlatniDto.getTipTecajaZaduzenja()));
                    tppNalogPlatniDto.setRegularniTecajZaduzenja(null); // TODO Ivana
                //} catch (AbitRuleException e) {
                } catch (Exception e) {
                    log.debug(e.getMessage(), e);
                    tppNalogPlatniDto.setIsValid(Boolean.FALSE);
                    tppNalogPlatniDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.BAD_REQUEST.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_kupoprodaja_err_izracunTecaja", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                    return tppNalogPlatniDto;
                }
            }
        } else {
            tppNalogPlatniDto.setRegularniTecajZaduzenja(null);
        }
        if (setTecajPotrazno) {
            tppNalogPlatniDto = postaviPotrazniTecaj(false, tppNalogPlatniDto);
        }
        return tppNalogPlatniDto;
    }

    public NalogDevizniDto formNalogDevizniFromTppNalog(TppNalogPlatniDto nalog) {
        NalogDevizniDto nalogDevizni = new NalogDevizniDto();
        nalogDevizni.getKnjizniNalog().setDatumValute(nalog.getDatumValute());
        nalogDevizni.getKnjizniNalog().setSvrha(InternationalizationUtil.getLocalMessage("kupnjaDeviza", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
        nalogDevizni.setIbanZaduzenja(IbanFunctions.resolvePartijaFromIban(nalog.getIbanZaduzenja()));
        nalogDevizni.setIbanOdobrenja(IbanFunctions.resolvePartijaFromIban(nalog.getIbanOdobrenja()));
        nalogDevizni.setSifraPartijeZaduzenja(nalog.getSifraPartijeZaduzenja());
        nalogDevizni.setSifraPartijeOdobrenja(nalog.getSifraPartijeOdobrenja());
        nalogDevizni.setSifraValuteZaduzenja(nalog.getValutaZaduzenja());
        nalogDevizni.setSifraValuteOdobrenja(nalog.getValutaOdobrenja());
        if (StringUtils.isNotBlank(nalog.getValutaZaduzenja())) {
            //nalogDevizni.setOznakaValuteZaduzenja((String) PlatniUtil.getFieldValueFromTableRecord("valuta", "oznka_val", "sifra_val", nalog.getValutaZaduzenja(), entityManager));
            nalogDevizni.setOznakaValuteZaduzenja(null); // TODO Ivana
        }
        if (StringUtils.isNotBlank(nalog.getValutaOdobrenja())) {
            //nalogDevizni.setOznakaValuteOdobrenja((String) PlatniUtil.getFieldValueFromTableRecord("valuta", "oznka_val", "sifra_val", nalog.getValutaOdobrenja(), entityManager));
            nalogDevizni.setOznakaValuteOdobrenja(null); // TODO Ivana
        }
        nalogDevizni.setTipTecajaZaduzenja(nalog.getTipTecajaZaduzenja());
        nalogDevizni.setTipTecajaOdobrenja(nalog.getTipTecajaOdobrenja());
        nalogDevizni.setIznosZaduzenja(nalog.getIznosZaduzenja());
        nalogDevizni.setIznosOdobrenja(nalog.getIznosOdobrenja());
        nalogDevizni.setSmjer(nalog.getSmjer());
        nalogDevizni.setSvrha(null); //Svrha se formira u KupoprodajaDevizaDao
        nalogDevizni.setSifraKomitenta(nalog.getSifraKomitenta());
        nalogDevizni.setTecajZaduzenja(nalog.getTecajZaduzenja());
        nalogDevizni.setTecajOdobrenja(nalog.getTecajOdobrenja());
        nalogDevizni.setNazivKomitenta(nalog.getNazivDebtor());
        nalogDevizni.getKnjizniNalog().setSifraKlaseNaloga(nalog.getSifraKlaseNaloga());
        nalogDevizni.setSifraKnjiznogNaloga(nalog.getSifraKnjiznogNaloga());
        nalogDevizni.setSifraStraneOsobe(nalog.isPravnaOsoba() ? Bassx2Constants.PlatniPromet.DEVIZNI_NALOG_SIFRA_STRANCA_PRAVNI : Bassx2Constants.PlatniPromet.DEVIZNI_NALOG_SIFRA_STRANCA_FIZICKI);
        nalogDevizni.setTrosakInoBanke(StringUtils.isNotBlank(nalog.getTrosakInoBanke()) ? nalog.getTrosakInoBanke() : GenericBassxConstants.PlatniPromet.DEVIZNI_NALOG_TROSAK_INO_BANKE_SHA);
        nalogDevizni.setMedij(Bassx2Constants.PlatniPromet.PLATNI_NALOG_MEDIJ_PISP);
        return nalogDevizni;
    }

    public boolean kupoprodajaUnutarDnevnogLimita(Date datumKojiSeProvjerava, BigDecimal limitKupoprodaje, TppNalogPlatniDto kuppro) throws AbitRuleException {
        if (limitKupoprodaje == null)
            throw new AbitRuleException(InternationalizationUtil.getLocalMessage("psd2_kupoprodaja_err_LimitNePostoji", Bassx2Constants.Psd2.MESSAGE_BUNDLE));
        Set<String> ibanSet = new HashSet<>();
        ibanSet.add(kuppro.getIbanZaduzenja());
        ibanSet.add(kuppro.getIbanOdobrenja());
        String ibanSql = "(";
        Iterator it = ibanSet.iterator();
        while (it.hasNext()) {
            ibanSql += "'" + it.next() + "',";
        }
        ibanSql = StringUtils.removeEnd(ibanSql, ",") + ")";

        String sql = "select valut_nal, valpk_nal, iznos_nal, smjer_nal, tipte_nal, sifra_nal " +
                "from ib_nalog " +
                "where date(vrije_nal) = '" + DateUtil.getDateAsString(datumKojiSeProvjerava, DateUtil.SQL_DATE_ONLY_FORMAT) + "' " +
                "and (ibanz_nal in " + ibanSql + " or ibano_nal in " + ibanSql + ") " +
                "and vrsta_nal = '" + IBNalog.VRSTA_KUPOPRODAJA + "' " +
                "and (statu_nal is null or statu_nal <> '" + IBNalog.STATUS_IB_OBRISAN + "') " +
                "and sifra_nal not in (select ibnal_vpt from veza_povlasteni_tecaj) ";
        Query query = getEntityManager().createNativeQuery(sql);
        //query.setParameter("datum", datumKojiSeProvjerava);
//		query.setParameter("ibanList", ibanSet);


        String valutaTerecenja, valutaOdobrenja, smjer, valuta, tipTecaja;
        BigDecimal iznos;
        BigDecimal ukupniIznos = BigDecimal.ZERO;

        //Provjera današnjih transakcija
        List<Object[]> tmpRes = (List<Object[]>) query.getResultList();
        if (tmpRes != null && tmpRes.size() > 0) {
            for (Object[] obj : tmpRes) {
                if (kuppro.getSifraTppNaloga() != null && kuppro.getSifraTppNaloga().compareTo((Integer) obj[5]) == 0) {
                    continue;
                }
                valutaTerecenja = (String) obj[0];
                valutaOdobrenja = (String) obj[1];
                iznos = (BigDecimal) obj[2];
                smjer = (String) obj[3];
                tipTecaja = (String) obj[4];
                if (smjer.equals(IBNalog.SMJER_ZADANO_DUGOVNO)) {
                    valuta = valutaTerecenja;
                } else {
                    valuta = valutaOdobrenja;
                    if (valutaTerecenja.equals(Bassx2Constants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY) && !valutaOdobrenja.equals(Bassx2Constants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY)) {
                        tipTecaja = GenericBassxConstants.Core.TIP_TECAJA_PRODAJNI;
                    }
                }
                if (valuta.equals(Bassx2Constants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY))
                    ukupniIznos = ukupniIznos.add(iznos);
                else {
                    //ukupniIznos = ukupniIznos.add(iznos.multiply(LookupMinHelper.getICoreDaoMinProxyRemote().getTecajNaDan(datumKojiSeProvjerava, new ValutaDto(valuta, null), tipTecaja)).setScale(2, RoundingMode.HALF_UP));
                    ukupniIznos = null; // TODO Ivana
                }
            }
        }
        //Dodamo i trenutnu transakciju
        if (kuppro.getSmjer().equals(IBNalog.SMJER_ZADANO_DUGOVNO)) {
            valuta = kuppro.getSifraValute();
            tipTecaja = kuppro.getTipTecaja();
        } else {
            valuta = kuppro.getSifraValutePokrica();
            if (kuppro.getSifraValute().equals(Bassx2Constants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY) && !kuppro.getSifraValutePokrica().equals(Bassx2Constants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY)) {
                tipTecaja = GenericBassxConstants.Core.TIP_TECAJA_PRODAJNI;
            } else {
                tipTecaja = kuppro.getTipTecaja();
            }
        }
        if (valuta.equals(Bassx2Constants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY))
            ukupniIznos = ukupniIznos.add(kuppro.getIznos());
        else {
            //ukupniIznos = ukupniIznos.add(kuppro.getIznos().multiply(LookupMinHelper.getICoreDaoMinProxyRemote().getTecajNaDan(datumKojiSeProvjerava, new ValutaDto(valuta, null), tipTecaja)).setScale(2, BigDecimal.ROUND_HALF_UP));
            ukupniIznos = null; // TODO Ivana
        }

        //Provjera
        return ukupniIznos.compareTo(limitKupoprodaje) <= 0;
    }
}
