package hr.abc.psd2.bean.impl;

import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import hr.abc.psd2.bean.IAuthorizationBean;
import hr.abc.psd2.bean.INalogTppBean;
import hr.abc.psd2.bean.IPsd2UtilBean;
import hr.abc.psd2.dao.NalogTppDao;
import hr.abc.psd2.dao.Psd2DatotekaDao;
import hr.abc.psd2.dao.Psd2UtilDao;
import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.model.dto.authorization.AuthorizationDto;
import hr.abc.psd2.model.dto.authorization.ScaStatus;
import hr.abc.psd2.model.dto.core.*;
import hr.abc.psd2.model.dto.error.ErrorCode;
import hr.abc.psd2.model.dto.pis.*;
import hr.abc.psd2.model.dto.pis.pain.Document;
import hr.abc.psd2.security.Globals;
import hr.abc.psd2.service2.ICoreSrvc;
import hr.abc.psd2.util.*;
import hr.sberbank.model.payment.PaymentOrderResponseJson;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
public abstract class AbstractNalogTppBean implements INalogTppBean , Serializable {

    private static final long serialVersionUID = 1L;

    @Inject protected
    NalogTppDao nalogTppDao;

    @Inject protected
    Psd2UtilDao psd2UtilDao;

    @Inject protected
     IAuthorizationBean authorizationBean;

    @Inject protected
    IPsd2UtilBean psd2UtilBean;

    @Inject protected
    Psd2DatotekaDao datotekaDao;

    @Inject protected
    FormPsd2Pain001XmlFile formPsd2Pain001XmlFile;

    @Inject protected ICoreSrvc coreSrvc;

    protected  abstract  void createNalogFromChangedTppNalog (TppNalogPlatniDto tppNalogPlatniDto);

    protected abstract NalogDevizniDto validateAndInitializeKupoprodaja(NalogDevizniDto devizniNalogDto, Globals globals);

    protected abstract List<NalogPlatniDto> resolvePainDatoteka(byte[] podaciDatoteke);

    protected abstract List<NalogSepaDto> pripremaNaloga(List<NalogSepaDto> nalogSepaDtos, Globals globals);

    @Override
    public SinglePaymentResponseDto resolveDatoteka(String file, SinglePaymentDto header) throws AbitRuleException {
        SinglePaymentResponseDto response = null;
        List<TppNalogPlatniDto> tppNalozi = new ArrayList<>();
        List<NalogPlatniDto> platniList = null;
        try {
            //Prije učitavanja provjerimo uopće da li je datoeka već učitana
            String cheksum = datotekaDao.getCheksum(file);
            Psd2DatotekaDto ibDatoteka = new Psd2DatotekaDto();
            ibDatoteka.setCheksum(cheksum);
            List<Psd2DatotekaDto> istiCheksum = datotekaDao.filterList(ibDatoteka);
            if (istiCheksum != null && istiCheksum.size() > 0) {
                response = new SinglePaymentResponseDto(false, Psd2Util.setErrorInformationDto(ErrorCode.PAYMENT_FAILED.getValue(), new ArrayList<String>
                        (Arrays.asList(InternationalizationUtil.getLocalMessage("datotekaCheksumViolated", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                return response;
            }
            //Map<String, String> mapValute = AuthenticationUtils.mapValute();
            Map<String, String> mapValute = null; //TODO Ivana

            //platniList = LookupMinHelper.getIAvpoDaoMinProxyRemote().resolvePainDatoteka(file.getBytes()); // stari
            platniList = resolvePainDatoteka(file.getBytes()); // prazni

            //Provjera da li je valuta na tecajnoj listi
            List<String> tecajna = Psd2Util.getValuteTecajneListe(nalogTppDao.getEntityManager());
            for (NalogPlatniDto nalPlatni : platniList) {
                if (nalPlatni.getSifraValute() == null || StringUtils.isBlank(nalPlatni.getSifraValute())) {
                    throw new AbitRuleException(InternationalizationUtil.getLocalMessage("createNalogValutaNull", new String[] { nalPlatni.getSifraValute() }, GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
                }
                if (!tecajna.contains(nalPlatni.getSifraValute())) {
                    throw new AbitRuleException(InternationalizationUtil.getLocalMessage("createNalogValutaNePostoji", new String[]{nalPlatni.getSifraValute()}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
                }
            }

            //Odredimo naknadu
            List<NalogAvpoDto> listAvpo = new ArrayList<>();
            TppNalogPlatniDto tmpIbNalog = null;
            for (NalogPlatniDto np : platniList) {
                if (np.getKnjizniNalog() != null && np.getKnjizniNalog().getDatumValute() != null &&
                        DateUtil.compareDatesYMD(np.getKnjizniNalog().getDatumValute(), new Date()) < 0) {
                    throw new AbitRuleException(InternationalizationUtil.getLocalMessage("datotekaDatumValuteNotValid", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
                }
                tmpIbNalog = nalogTppDao.formTppNalogPlatniDtoFromNalogPlatni(np);
                tmpIbNalog = nalogTppDao.initializeTppNalogBeforeCreate(tmpIbNalog);
                listAvpo.add(nalogTppDao.formNalogAvpoFromIBNalog(tmpIbNalog));
            }
            List<NalogSepaDto> sepaTmp = resolveNaknadaForAvpo(listAvpo);

            if (sepaTmp != null) {
                tppNalozi = new ArrayList<>();
                TppNalogPlatniDto tppNalog = null;
                NalogPlatniDto nal = null;
                WebKorisnikRacunDto korRac = null;
                //Batch booking dio
                Boolean postojiBatchBooking = Boolean.FALSE;
                Map<String, BigDecimal> batchBoookingSumaNaloga = new HashMap<>();
                Map<String, BigDecimal> batchBookingSumaNaknada = new HashMap<>();
                Map<String, Set<String>> batchBookingValute = new HashMap<>();
                Map<String, List<TppNalogPlatniDto>> batchBookingMap = new HashMap<>();
                List<TppNalogPlatniDto> batchBookingList = null;
                BigDecimal iznosSum = BigDecimal.ZERO;
                BigDecimal naknadaSum = BigDecimal.ZERO;
                Set<String> setValute = new HashSet<>();

                //Odredimo batch booking naloge (koje ćemo kasnije spremiti i normalne naloge koje odmah spremamo
                HashSet<Date> batchBookingDatva = new HashSet<>();
                boolean datotekaProlazi = true;
                for (NalogSepaDto nalSepa : sepaTmp) {
                    nal = nalSepa.getNalogPlatniDto();
                    tppNalog = nalogTppDao.formTppNalogPlatniDtoFromNalogPlatni(nal);
                    if (nalSepa.getGreska() != null) {
                        tppNalog.setGreska(nalSepa.getGreska());
                        datotekaProlazi = false;
                    } else {
                        tppNalog.setIznosNaknade(nalSepa.getIznosNaknade());
                    }

                    //napunimo BB mape ako se radi o BB nalozima
                    if (nalSepa.getBtchBookg() != null && nalSepa.getBtchBookg()) {
                        batchBookingDatva.add(nalSepa.getKnjizniNalog().getDatumValute() != null ? DateUtil.anuliranjeDatuma(nalSepa.getKnjizniNalog().getDatumValute()) : null);
                        //ne smije biti greške u nijednom nalogu, inače se cijela datoteka odbija
//						if(ibNal.getGreska() != null){
//							throw new AbitRuleException(InternationalizationUtil.getLocalMessage("painBatchBookingGreska", GenericBassxConstants.Ib.MESSAGE_BUNDLE));
//						}
                        //Dohvatimo postojeće stanje
                        batchBookingList = batchBookingMap.get(nalSepa.getPainPaymentInfoId()) != null ? batchBookingMap.get(nalSepa.getPainPaymentInfoId()) : new ArrayList<>();
                        iznosSum = batchBoookingSumaNaloga.get(nalSepa.getPainPaymentInfoId()) != null ? batchBoookingSumaNaloga.get(nalSepa.getPainPaymentInfoId()) : BigDecimal.ZERO;
                        naknadaSum = batchBookingSumaNaknada.get(nalSepa.getPainPaymentInfoId()) != null ? batchBookingSumaNaknada.get(nalSepa.getPainPaymentInfoId()) : BigDecimal.ZERO;
                        setValute = batchBookingValute.get(nalSepa.getPainPaymentInfoId()) != null ? batchBookingValute.get(nalSepa.getPainPaymentInfoId()) : new HashSet<>();
                        //Ažuriramo s novim nalogom
                        batchBookingList.add(tppNalog);
                        iznosSum = iznosSum.add(tppNalog.getIznos());
                        naknadaSum = naknadaSum.add(tppNalog.getIznosNaknade() != null ? tppNalog.getIznosNaknade() : BigDecimal.ZERO);
                        setValute.add(tppNalog.getSifraValute());
                        //Zasad dozvoljavamo samo kune
                        if (!tppNalog.getSifraValute().equals(Bassx2Constants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY)) {
                            throw new AbitRuleException(InternationalizationUtil.getLocalMessage("painBatchBookingNijeHrkValuta", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
                        }
                        if (setValute != null && setValute.size() > 1) {
                            throw new AbitRuleException(InternationalizationUtil.getLocalMessage("painBatchBookingViseValutaGreska", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
                        }
                        //Ažuriranje mapa
                        batchBookingValute.put(nalSepa.getPainPaymentInfoId(), setValute);
                        batchBookingSumaNaknada.put(nalSepa.getPainPaymentInfoId(), naknadaSum);
                        batchBoookingSumaNaloga.put(nalSepa.getPainPaymentInfoId(), iznosSum);
                        batchBookingMap.put(nalSepa.getPainPaymentInfoId(), batchBookingList);
                    } else {
                        tppNalozi.add(tppNalog);
                    }
                }

                //Spremanje naloga
                List<TppNalogPlatniDto> tppNaloziFinal = new ArrayList<>();
                String authLink = null;
                Integer brojPotpisnika = null;

                if (datotekaProlazi) { //Ako je datoteka OK, zapišemo naloge
                    //Sad kreiramo datoteku jer imamo ime - messageId iz taga <GrpHdr>
                    if (platniList != null && platniList.size() > 0) {
                        ibDatoteka.setNazivDatoteke(platniList.get(0).getPainMessageId());
                        ibDatoteka.setVrijemeNastankaDatoteke(new Date());
                        ibDatoteka.setSifraIbKorisnika(null);
                        ibDatoteka.setInicijatorPlacanjaNaziv(platniList.get(0).getPainInitiatingPartyName());
                        ibDatoteka.setInicijatorPlacanjaId(platniList.get(0).getPainInitiatingPartyOrgId());
                        ibDatoteka = datotekaDao.create(ibDatoteka);
                    }

                    Map<String, Integer> mapRacunBrojPotpisa = new HashMap<>();

                    if (batchBookingMap != null && batchBookingMap.size() > 0) {
                        //Izvadimo max datva iz batchbooking datuma valute
                        Date datumValuteMax = null;
                        if (batchBookingDatva != null) {
                            for (Date datum : batchBookingDatva) {
                                if (datumValuteMax == null || DateUtil.compareDatesOnly(datum, datumValuteMax) > 0) {
                                    datumValuteMax = datum;
                                }
                            }
                        }
                        for (Map.Entry<String, List<TppNalogPlatniDto>> tmpEntry : batchBookingMap.entrySet()) {
                            List<TppNalogPlatniDto> batchBookingNalozi = tmpEntry.getValue();
                            TppNalogPlatniDto zbirniBatchBooking = null;
                            if (batchBookingNalozi != null && batchBookingNalozi.size() > 0) {
                                zbirniBatchBooking = nalogTppDao.initializeBatchBookingNalogBeforeCreate(batchBookingNalozi.get(0), datumValuteMax, null, Bassx2Constants.Core.SIFRALIK_PSD2_TPP, batchBoookingSumaNaloga.get(tmpEntry.getKey()));
                                //Sad odredimo naknadu zbirnog naloga
                                List<TppNalogPlatniDto> tmpList = new ArrayList<>();
                                tmpList.add(zbirniBatchBooking);
                                List<NalogSepaDto> naknadaZbirni = resolveNaknadaForPsd2(tmpList);
                                zbirniBatchBooking.setIznosNaknade((naknadaZbirni != null && naknadaZbirni.get(0) != null) ? naknadaZbirni.get(0).getIznosNaknade() : BigDecimal.ZERO);
                                zbirniBatchBooking = createNalogFromDatoteka(zbirniBatchBooking, ibDatoteka.getSifra(), header);
                                //Odredimo broj potpisa za ibanz
                                if (!mapRacunBrojPotpisa.containsKey(zbirniBatchBooking.getIbanZaduzenja()))
                                    mapRacunBrojPotpisa.put(zbirniBatchBooking.getIbanZaduzenja(), psd2UtilDao.getMaxBrojIbPotpisnika(null, Arrays.asList(zbirniBatchBooking.getIbanZaduzenja().substring(zbirniBatchBooking.getIbanZaduzenja().length() - 10))));
                            }
                            for (TppNalogPlatniDto bbNalog : tmpEntry.getValue()) {
                                bbNalog.setSifraZbirnogBatchBookingNaloga(zbirniBatchBooking.getSifraTppNaloga());
                                bbNalog.setDatumValute(datumValuteMax);
                                if (bbNalog.isNajavaPrijePotpisa()) {
                                    bbNalog.setDatumKnjizenja(datumValuteMax);

                                }
                                bbNalog = createNalogFromDatoteka(bbNalog, ibDatoteka.getSifra(), header);
                                //Odredimo broj potpisa za ibanz
                                if (!mapRacunBrojPotpisa.containsKey(bbNalog.getIbanZaduzenja()))
                                    mapRacunBrojPotpisa.put(bbNalog.getIbanZaduzenja(), psd2UtilDao.getMaxBrojIbPotpisnika(null, Arrays.asList(bbNalog.getIbanZaduzenja().substring(bbNalog.getIbanZaduzenja().length() - 10))));
                                List<TppNalogPlatniDto> bbNalozi = zbirniBatchBooking.getBatchBookingNalozi();
                                bbNalozi.add(bbNalog);
                                zbirniBatchBooking.setBatchBookingNalozi(bbNalozi);
                            }
                            tppNaloziFinal.add(zbirniBatchBooking);
                        }
                    } else {
                        for (TppNalogPlatniDto tppNal : tppNalozi) {
                            tppNaloziFinal.add(createNalogFromDatoteka(tppNal, ibDatoteka.getSifra(), header));
                            //Odredimo broj potpisa za ibanz
                            if (!mapRacunBrojPotpisa.containsKey(tppNal.getIbanZaduzenja()))
                                mapRacunBrojPotpisa.put(tppNal.getIbanZaduzenja(), psd2UtilDao.getMaxBrojIbPotpisnika(null, Arrays.asList(tppNal.getIbanZaduzenja().substring(tppNal.getIbanZaduzenja().length() - 10))));
                        }
                    }

                    //Sad kreiramo autorizacije za sve naloge koji imaju jednog potpisnika
                    //vrijene trajanja autorizacije
                    LocalDateTime toDateAut = psd2UtilBean.getVrijemeTrajanjaAutorizacijeTppNaloga(tppNaloziFinal);
                    for (TppNalogPlatniDto tppNal : tppNaloziFinal) {
                        if (mapRacunBrojPotpisa.containsKey(tppNal.getIbanZaduzenja()) && mapRacunBrojPotpisa.get(tppNal.getIbanZaduzenja()).compareTo(1) == 0) {
                            if (authLink == null)
                                authLink = Psd2Util.formAutorizationScaRedirectLink(ibDatoteka.getSifra().toString());
                            createAuthorizationForBulkNalog(tppNal, authLink, toDateAut);
                        }
                        if (tppNal.getBatchBooking() && tppNal.getBatchBookingNalozi() != null) {
                            for (TppNalogPlatniDto tppNalBB : tppNal.getBatchBookingNalozi()) {
                                if (mapRacunBrojPotpisa.containsKey(tppNalBB.getIbanZaduzenja()) && mapRacunBrojPotpisa.get(tppNalBB.getIbanZaduzenja()).compareTo(1) == 0) {
                                    if (authLink == null)
                                        authLink = Psd2Util.formAutorizationScaRedirectLink(ibDatoteka.getSifra().toString());
                                    createAuthorizationForBulkNalog(tppNalBB, authLink, toDateAut);
                                }
                            }
                        }
                    }
                } else { //Ako datoteka nije ok, također stavimo naloge u listu zbog response-a
                    if (tppNalozi != null && tppNalozi.size() > 0) {
                        for (TppNalogPlatniDto tppNal : tppNalozi) {
                            tppNaloziFinal.add(tppNal);
                        }
                    }
                    //Provjerimo i BB naloge
                    if (batchBookingList != null && batchBookingList.size() > 0) {
                        for (TppNalogPlatniDto tppNal : batchBookingList) {
                            tppNaloziFinal.add(tppNal);
                        }
                    }
                }
                //Sad odredimo response
                response = Psd2Util.resolveResposneForBulkPayments(tppNaloziFinal, header, authLink, ibDatoteka.getSifra());
            }
        } catch (AbitRuleException ex) {
            throw ex;
        } catch (Exception e) {
            e.printStackTrace();
            LoggerFactory.getLogger(this.getClass()).error(e.getMessage(), e);
            throw new AbitRuleException(e.getMessage());
        }
        return response;
    }

    public TppNalogPlatniDto createAuthorizationForBulkNalog(TppNalogPlatniDto tppNalog, String authLink, LocalDateTime toDateAut) throws AbitRuleException {
        AuthorizationDto auth = authorizationBean.createAuthorization(
                new AuthorizationDto(ScaStatus.SCAMETHODSELECTED.getValue(),
                        Psd2Constants.ScaMethod.REDIRECT_IMPLICT.toString(),
                        tppNalog.getSifraTppNaloga().toString(),
                        Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(),
                        Psd2Constants.AuthorizationType.CREATED.getValue(),
                        tppNalog.getTppId(),
                        null,
                        tppNalog.getxRequestId(),
                        authLink,
                        toDateAut));
        tppNalog.setAuthorization(auth);
        return tppNalog;
    }

    public TppNalogPlatniDto createNalogFromDatoteka(TppNalogPlatniDto tppNalog, Integer sifraDatoteke, SinglePaymentDto header) throws AbitRuleException {
        tppNalog.setSifraDatoteke(sifraDatoteke);
        tppNalog.setVrijemeUpisaNaloga(LocalDateTime.now());

        //napunimo podatke naloga iz headera
        tppNalog.setPaymentService(header.getPaymentService());
        tppNalog.setPaymentProduct(header.getPaymentProduct());
        tppNalog.setxRequestId(header.getxRequestId());
        tppNalog.setTppId(header.getTppId());
        tppNalog.setContentType(header.getContentType());
        tppNalog.setPsuIpAddress(header.getPsuIpAddress());
        tppNalog.setTppRedirectUri(header.getTppRedirectUri());

        //unos u bazu podataka - moramo sacuvati sve transient podatke pa uvodimo klonirani dto
        TppNalogPlatniDto responseNalog = null;//(TppNalogPlatniDto) tppNalog.klone();
        //Punimo podatke platitelja
        tppNalog = Psd2Util.resolvePodaciDebtor(tppNalog, nalogTppDao.getEntityManager());
        tppNalog = nalogTppDao.createTppNalog(tppNalog);
        responseNalog.setSifraTppNaloga(tppNalog.getSifraTppNaloga());

        return responseNalog;
    }

    public List<NalogSepaDto> resolveNaknadaForPsd2(List<TppNalogPlatniDto> nalozi) throws AbitRuleException {
        List<NalogAvpoDto> listAvpo = new ArrayList<>();
        for (TppNalogPlatniDto np : nalozi) {
            listAvpo.add(nalogTppDao.formNalogAvpoFromIBNalog(np));
        }
        return resolveNaknadaForAvpo(listAvpo);
    }

    public List<NalogSepaDto> resolveNaknadaForAvpo(List<NalogAvpoDto> nalozi) throws AbitRuleException {
        //List<NalogSepaDto> sepaTmp = AvpoUtilMin.formSepaFromAvpo(nalozi);
        List<NalogSepaDto> sepaTmp = null; // TODO
        //sepaTmp = LookupMinHelper.getIAvpoDaoMinProxyRemote().pripremaNaloga(sepaTmp, Globals.createBackgroundGlobals(Bassx2Constants.Core.SIFRALIK_PSD2_TPP, Bassx2Constants.PlatniPromet.POSLOVNICA_PSD2, Bassx2Constants.Core.MODUL_SIFRAMOD_PSD2, true)); // stari
        sepaTmp = pripremaNaloga(sepaTmp, Globals.createBackgroundGlobals(Bassx2Constants.Core.SIFRALIK_PSD2_TPP, Bassx2Constants.PlatniPromet.POSLOVNICA_PSD2, Bassx2Constants.Core.MODUL_SIFRAMOD_PSD2, true)); // prazni
        return sepaTmp;
    }

    @Override
    public PaymentCancellationResponseDto cancelBulkPayment(String paymentService, String paymentProduct, String tppId, Integer paymentId) {
        PaymentCancellationResponseDto responseDto = new PaymentCancellationResponseDto();
        try {
            Psd2DatotekaDto filterDatoteka = new Psd2DatotekaDto();
            filterDatoteka.setSifra(paymentId);
            List<Psd2DatotekaDto> psd2DatotekaDtoList = datotekaDao.filterList(filterDatoteka);
            if (psd2DatotekaDtoList != null && psd2DatotekaDtoList.size() == 1) {
                List<TppNalogPlatniDto> listTppNalogPlatniDtoList = nalogTppDao.getTppNaloziByDatotekaBasket(paymentId, TppNalogPlatniDto.TIP_GRUPIRANJA_DATOTEKA);
                if (listTppNalogPlatniDtoList != null && listTppNalogPlatniDtoList.size() > 0) {
                    if (StringUtils.equals(listTppNalogPlatniDtoList.get(0).getTppId(), tppId)) {
                        Boolean mozePovlacenjeDatoteke = true;
                        for (TppNalogPlatniDto tppNalogPlatniDto : listTppNalogPlatniDtoList) {
                            if (!tppNalogPlatniDto.mozeStartAutorizacijePovlacenje()) {
                                mozePovlacenjeDatoteke = false;
                                break;
                            }
                        }
                        if (mozePovlacenjeDatoteke) {
                            responseDto.setTransactionStatus(listTppNalogPlatniDtoList.get(0).getStatusPsd2());
                            responseDto.set_links(Psd2Util.formLinksPaymentBulkCancellation(listTppNalogPlatniDtoList.get(0), paymentId));
                            responseDto.setIsValid(true);
                        } else {
                            responseDto.setIsValid(false);
                            responseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.CANCELLATION_INVALID.getValue(), new ArrayList<String>(Arrays.asList("Ne možete povući datoteku - nisu zadovoljeni uvjeti."))));
                        }
                    } else {
                        responseDto.setIsValid(false);
                        responseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_payment_file", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                    }
                } else {
                    responseDto.setIsValid(false);
                    responseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_payment_file", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                }
            } else {
                responseDto.setIsValid(false);
                responseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_payment_file", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
            }
        } catch (Exception e) {
        	log.error("Exception is:", e);
            responseDto.setIsValid(false);
            responseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<String>(Arrays.asList("Internal server error"))));
        }
        return responseDto;
    }

    @Override
    public SinglePaymentDto getBulkPaymentInformation(String paymentProduct, String tppId, Integer paymentId) {
        SinglePaymentDto response = SinglePaymentDto.builder().build();
        try {
            Psd2DatotekaDto filterDatoteka = new Psd2DatotekaDto();
            filterDatoteka.setSifra(paymentId);
            List<Psd2DatotekaDto> psd2DatotekaDtoList = datotekaDao.filterList(filterDatoteka);
            if (psd2DatotekaDtoList != null && psd2DatotekaDtoList.size() == 1) {
                List<TppNalogPlatniDto> listTppNalogPlatniDtoList = nalogTppDao.getTppNaloziByDatotekaBasket(paymentId, TppNalogPlatniDto.TIP_GRUPIRANJA_DATOTEKA);
                if (listTppNalogPlatniDtoList != null && listTppNalogPlatniDtoList.size() > 0) {
                    if (StringUtils.equals(listTppNalogPlatniDtoList.get(0).getTppId(), tppId)) {
                        Document document = formPsd2Pain001XmlFile.formXmlFromDtos(listTppNalogPlatniDtoList, psd2DatotekaDtoList.get(0), nalogTppDao.getEntityManager());
                        //String contents = XmlUtil.marshalToString(document, true, true, "UTF-8", false);
                        String contents = null; // TODO Ivana
                        response.setIsValid(true);
                        response.setPainXml(contents);
                    } else {
                        response.setIsValid(false);
                        response.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_payment_file", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                    }
                } else {
                    response.setIsValid(false);
                    response.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_payment_file", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                }
            } else {
                response.setIsValid(false);
                response.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_payment_file", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
            }
        } catch (Exception e) {
        	log.error("Exception is:", e);
            response.setIsValid(false);
            response.setErrorInformationDto(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<String>(Arrays.asList("Internal server error"))));
        }
        return response;
    }

    @Override
    public TransactionStatusResponseDto getBulkPaymentInitiationStatus(String paymentProduct, String tppId, Integer paymentId) {
        TransactionStatusResponseDto response = null;
        try {
            response = nalogTppDao.getBulkTppNalogStatus(paymentId, tppId, paymentProduct);
        } catch (Exception e) {
        	log.error("Exception is:", e);
            response = new TransactionStatusResponseDto();
            response.setIsValid(false);
            response.setErrorInformationDto(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<String>(Arrays.asList("Internal server error"))));
        }
        return response;
    }

    @Override
    public SinglePaymentResponseDto create(SinglePaymentDto tppNalog, String originalPaymentOrder) throws AbitRuleException {
        SinglePaymentResponseDto responseDto = null;
        List<String> errorsList = null;
        try {
            // inicijalizacija testnih globala
            Globals backgroundGlobals = Globals.createBackgroundGlobals(Bassx2Constants.Core.SIFRALIK_PSD2_TPP, Bassx2Constants.PlatniPromet.POSLOVNICA_PLATNOG_PROMETA, Bassx2Constants.Core.MODUL_SIFRAMOD_PSD2, true);
            if (!Psd2Util.isNalogKupoprodaje(tppNalog)) {

                Response initiatePayment= coreSrvc.initiatePayment(originalPaymentOrder, tppNalog.getPaymentProduct(), tppNalog.getxRequestId());
                if (initiatePayment.getStatusInfo().getStatusCode() == 201) {
                    PaymentOrderResponseJson responseJson=initiatePayment.readEntity(PaymentOrderResponseJson.class);
                    TppNalogPlatniDto tppNalogPlatniDto= TppNalogPlatniDto.builder().tppId(tppNalog.getTppId()).xRequestId(tppNalog.getxRequestId()).contentType(tppNalog.getContentType())
                            .paymentProduct(tppNalog.getPaymentProduct()).paymentService(tppNalog.getPaymentService()).psuIpAddress(tppNalog.getPsuIpAddress()).paymentRequestId(responseJson.getPaymentRequestId()).transactionStatus(TransactionStatus.RCVD).build();
                    responseDto = new SinglePaymentResponseDto(true, TransactionStatus.RCVD, responseJson.getPaymentRequestId() , null, null, Psd2Util.formLinksPaymentInitiation(null, tppNalogPlatniDto));
                    nalogTppDao.createTppNalog(tppNalogPlatniDto);
                }
                //validate
                //ne treba
               /* SinglePaymentDto psd2Nalog = nalogTppDao.validateAndSetSepaNalogList(tppNalog, backgroundGlobals);
                //Validate sifra valute
                if (psd2Nalog.getNalogSepa().getSifraValute() == null || StringUtils.isBlank(psd2Nalog.getNalogSepa().getSifraValute())) {
                    throw new AbitRuleException(InternationalizationUtil.getLocalMessage("createNalogValutaNull", new String[] { psd2Nalog.getNalogSepa().getSifraValute() }, GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
                }
                List<String> tecajna = Psd2Util.getValuteTecajneListe(nalogTppDao.getEntityManager());
                if (!tecajna.contains(psd2Nalog.getNalogSepa().getSifraValute())) {
                    throw new AbitRuleException(InternationalizationUtil.getLocalMessage("createNalogValutaNePostoji", new String[]{psd2Nalog.getNalogSepa().getSifraValute()}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
                }
                //provjera da li ima grešaka
                //IMA GREŠKE
                if (StringUtils.isNotBlank(psd2Nalog.getNalogSepa().getGreska())) {
                    List<Exception> listExceptions = (List<Exception>) psd2Nalog.getNalogSepa().getUtilMap().get(Bassx2Constants.PlatniPromet.UTILMAP_KEY_EXCEPTION);
                    AbitRuleException are;
                    errorsList = new ArrayList<>();
                    for (Exception e : listExceptions) {
                        if (e instanceof AbitRuleException) {
                            are = (AbitRuleException) e;
                            if (are.getMessages() != null && !are.getMessages().isEmpty()) errorsList.addAll(are.getMessages());
                            if (StringUtils.isNotBlank(are.getMessage())) errorsList.add(are.getMessage());
                        }
                    }
                    responseDto = new SinglePaymentResponseDto(false, Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), errorsList));
                    //NEMA GREŠKE
                } else {
                    if (psd2Nalog.isChanged()) {
                        responseDto = new SinglePaymentResponseDto(false, Psd2Util.setErrorInformationDto(ErrorCode.PAYMENT_FAILED.getValue(), new ArrayList<String>(Arrays.asList("Neispravan routing."))));
                    } else {
                        //form tpp nalog from sepa nalog
                        List<TppNalogPlatniDto> listaTppNaloga = Psd2Util.formTppNalogFromSepaNalog(Arrays.asList(psd2Nalog.getNalogSepa()), tppNalog);
                        //Punimo podatke platitelja
                        TppNalogPlatniDto nalogTpp = Psd2Util.resolvePodaciDebtor(listaTppNaloga.get(0), nalogTppDao.getEntityManager());
                        //unos u bazu podataka
                        TppNalogPlatniDto tppNalogPlatniDto = nalogTppDao.createTppNalog(nalogTpp);
                        //provjera da li je nalog promijenjen - logiranje akcija
                        tppNalogPlatniDto = Psd2Util.isTppNalogChanged(tppNalog, tppNalogPlatniDto);
                        if (tppNalogPlatniDto.isChanged()) {
                            //LookupHelper.getICoreDaoProxyRemote().createNalogFromChangedTppNalog(tppNalogPlatniDto); // stari
                            createNalogFromChangedTppNalog(tppNalogPlatniDto); // prazni
                        }
                        //getBrojPotpisnika
                        Integer brojPotpisnika = psd2UtilDao.getMaxBrojIbPotpisnika(null, Arrays.asList(tppNalogPlatniDto.getIbanZaduzenja().substring(tppNalogPlatniDto.getIbanZaduzenja().length() - 10)));
                        AuthorizationDto authorizationDto = null;
                        if (brojPotpisnika == null || brojPotpisnika < 2) {
                            //create authorization --> implicit sca
                            LocalDateTime toDateAut = psd2UtilBean.getVrijemeTrajanjaAutorizacijeSepaNaloga(Arrays.asList(psd2Nalog.getNalogSepa()));
                            authorizationDto = authorizationBean.createAuthorization(new AuthorizationDto(ScaStatus.SCAMETHODSELECTED.getValue(), Psd2Constants.ScaMethod.REDIRECT_IMPLICT.toString(), tppNalogPlatniDto.getSifra(), Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), Psd2Constants.AuthorizationType.CREATED.getValue(), tppNalog.getTppId(), null, tppNalog.getxRequestId(), Psd2Util.formAutorizationScaRedirectLink(tppNalogPlatniDto.getSifra()), toDateAut));
                        }
                        //form responseDto
                        responseDto = new SinglePaymentResponseDto(true, StringUtils.isNotBlank(tppNalogPlatniDto.getStatusTpp()) ? TransactionStatus.getByName(tppNalogPlatniDto.getStatusTpp()) : TransactionStatus.ACTC, tppNalogPlatniDto.getSifra().toString(), null, null, Psd2Util.formLinksPaymentInitiation(authorizationDto, tppNalogPlatniDto));
                        //naknada
                        Amount naknada = new Amount();
                        naknada.setCurrency(Bassx2Constants.Core.VALUTA_OZNKAVAL_HOME_CURRENCY);
                        if (tppNalogPlatniDto.getIznosNaknade() != null && tppNalogPlatniDto.getIznosNaknade().compareTo(BigDecimal.ZERO) > 0) {
                            naknada.setAmount(String.valueOf(tppNalogPlatniDto.getIznosNaknade()));
                            responseDto.setTransactionFeeIndicator(true);
                        } else {
                            naknada.setAmount("0.00");
                            responseDto.setTransactionFeeIndicator(false);
                        }
                        responseDto.setTransactionFees(naknada);
                    }
                }*/
                //nalog kupoprodaje
            } else {
                TppNalogPlatniDto tppNalogPlatniDto = createKupnjaDeviza(tppNalog, backgroundGlobals);
                if (tppNalogPlatniDto.getIsValid()) {
                    Integer brojPotpisnika = psd2UtilDao.getMaxBrojIbPotpisnika(null, Arrays.asList(tppNalogPlatniDto.getIbanZaduzenja().substring(tppNalogPlatniDto.getIbanZaduzenja().length() - 10)));
                    AuthorizationDto authorizationDto = null;
                    if (brojPotpisnika == null || brojPotpisnika < 2) {
                        LocalDateTime toDateAut =psd2UtilBean.getVrijemeTrajanjaAutorizacijeTppNaloga(Arrays.asList(tppNalogPlatniDto));
                        //create authorization --> implicit sca
                        authorizationDto = authorizationBean.createAuthorization(new AuthorizationDto(ScaStatus.SCAMETHODSELECTED.getValue(), Psd2Constants.ScaMethod.REDIRECT_IMPLICT.toString(), tppNalogPlatniDto.getSifraTppNaloga().toString(), Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), Psd2Constants.AuthorizationType.CREATED.getValue(), tppNalog.getTppId(), null, tppNalog.getxRequestId(), Psd2Util.formAutorizationScaRedirectLink(tppNalogPlatniDto.getSifraTppNaloga().toString()), toDateAut));
                    }
                    responseDto = new SinglePaymentResponseDto(true, StringUtils.isNotBlank(tppNalogPlatniDto.getStatusTpp()) ? TransactionStatus.getByName(tppNalogPlatniDto.getStatusTpp()) : TransactionStatus.ACTC, tppNalogPlatniDto.getSifraTppNaloga().toString(), null, null, Psd2Util.formLinksExchangePaymentInitiation(authorizationDto, tppNalogPlatniDto));
                } else {
                    responseDto = new SinglePaymentResponseDto(false, tppNalogPlatniDto.getErrorInformationDto());
                }
            }
        } catch (AbitRuleException ex) {
            throw ex;
        } catch (Exception e) {
        	log.error("Exception is:", e);
        	if(e instanceof WebApplicationException){
                return new SinglePaymentResponseDto(false, Psd2Util.setErrorInformationDto(((WebApplicationException) e).getResponse() .getStatusInfo().toString(), new ArrayList<String>(Arrays.asList(((WebApplicationException) e).getResponse().readEntity(String.class)))));
            }
            else return new SinglePaymentResponseDto(false, Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<String>(Arrays.asList("Internal server error"))));
        }
        return responseDto;
    }

    public TppNalogPlatniDto createKupnjaDeviza(SinglePaymentDto psd2Nalog, Globals remoteGlobals) {
        TppNalogPlatniDto tppNalogPlatniDto = null;
        try {
            //validate and form tpp nalog from psd2 nalog
            tppNalogPlatniDto = nalogTppDao.validateAndSetTppNalogKupoprodaja(psd2Nalog, remoteGlobals);
            if (!tppNalogPlatniDto.getIsValid()) {
                return tppNalogPlatniDto;
            }
            // validacija povlaštenog tečaja ako postoji
            Integer sifraPovlastenogTecaja = tppNalogPlatniDto != null ? tppNalogPlatniDto.getSifraPovlastenogTecaja() : null;
            if (sifraPovlastenogTecaja != null) {
                if (tppNalogPlatniDto.getMaxIznosZaduzenja() != null && tppNalogPlatniDto.getIznosZaduzenja() != null) {
                    if (tppNalogPlatniDto.getIznosZaduzenja().compareTo(tppNalogPlatniDto.getMaxIznosZaduzenja()) > 0) {
                        //warningMessages.add(InternationalizationUtil.getLocalMessage("kupoprodajaBean_error_iskoristeniPovlasteni", new String[]{StringFunctions.bigDecimalToString(nalog.getIznosZaduzenja(), StringFunctions.DEFAULT_NUMBER_PATTERN), StringFunctions.bigDecimalToString(nalog.getMaxIznosZaduzenja(), StringFunctions.DEFAULT_NUMBER_PATTERN)}, Bassx2Constants.Ib.MESSAGE_BUNDLE));
                        sifraPovlastenogTecaja = null; // nema povlaštenog
                    }
                }
                if (tppNalogPlatniDto.getMaxIznosOdobrenja() != null && tppNalogPlatniDto.getIznosOdobrenja() != null) {
                    if (tppNalogPlatniDto.getIznosOdobrenja().compareTo(tppNalogPlatniDto.getMaxIznosOdobrenja()) > 0) {
                        //warningMessages.add(InternationalizationUtil.getLocalMessage("kupoprodajaBean_error_iskoristeniPovlasteni", new String[]{StringFunctions.bigDecimalToString(nalog.getIznosOdobrenja(), StringFunctions.DEFAULT_NUMBER_PATTERN), StringFunctions.bigDecimalToString(nalog.getMaxIznosOdobrenja(), StringFunctions.DEFAULT_NUMBER_PATTERN)}, Bassx2Constants.Ib.MESSAGE_BUNDLE));
                        sifraPovlastenogTecaja = null; // nema povlaštenog
                    }
                }
            }
            //kreiranje i validacija NalogDevizni
            NalogDevizniDto nalogDevizni = nalogTppDao.formNalogDevizniFromTppNalog(tppNalogPlatniDto);
            try {
                //nalogDevizni = LookupMinHelper.getIPlatniDaoMinProxyRemote().validateAndInitializeKupoprodaja(nalogDevizni, remoteGlobals); // stari
                nalogDevizni = validateAndInitializeKupoprodaja(nalogDevizni, remoteGlobals); // prazni
                //} catch (AbitRuleException are) {
            } catch (Exception are) {
                tppNalogPlatniDto.setIsValid(Boolean.FALSE);
                tppNalogPlatniDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.BAD_REQUEST.getValue(), new ArrayList<String>(Arrays.asList(are.getMessage()))));
                return tppNalogPlatniDto;
            }
            tppNalogPlatniDto.setSmjer(nalogDevizni.getSmjer());
            tppNalogPlatniDto.setIznos(nalogDevizni.getIznos());
            tppNalogPlatniDto.setSvrha(nalogDevizni.getKnjizniNalog().getSvrha());
            tppNalogPlatniDto.setSifraValute(nalogDevizni.getSifraValute());
            tppNalogPlatniDto.setSifraValutePokrica(nalogDevizni.getSifraValutePokrica());
            tppNalogPlatniDto.setTipTecaja(nalogDevizni.getTipTecaja());
            //ako ne postoji povlašteni tečaj, kontrolira se dnevni limit
            if (sifraPovlastenogTecaja == null) {
                //Exception ako je premašen limit kuppro - PRIJE SPREMANJA - TODO (PROVJERA LIMITA)
//                try {
//                    if (!nalogTppDao.kupoprodajaUnutarDnevnogLimita(new Date(), constBean.getMaxKupproIbg(), tppNalogPlatniDto)) {
//                        tppNalogPlatniDto.setIsValid(Boolean.FALSE);
//                        tppNalogPlatniDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.BAD_REQUEST.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_kupoprodaja_err_spremiKupproLimitError", new String[]{StringFunctions.bigDecimalToStringCurrency(constBean.getMaxKupproIbg())}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
//                        return tppNalogPlatniDto;
//                    }
//                } catch (AbitRuleException are) {
//                    tppNalogPlatniDto.setIsValid(Boolean.FALSE);
//                    tppNalogPlatniDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.BAD_REQUEST.getValue(), new ArrayList<String>(Arrays.asList(are.getMessage()))));
//                    return tppNalogPlatniDto;
//                }
            }
            tppNalogPlatniDto = nalogTppDao.createTppNalog(tppNalogPlatniDto);
        } catch (Exception e) {
        	log.error("Exception is:", e);
            tppNalogPlatniDto.setIsValid(Boolean.FALSE);
            tppNalogPlatniDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.INTERNAL_SERVER_ERROR.getValue(), new ArrayList<String>(Arrays.asList(e.getMessage()))));
            return tppNalogPlatniDto;
        }
        return tppNalogPlatniDto;
    }

    @Override
    public PaymentCancellationResponseDto cancelPayment(String paymentService, String paymentProduct, String tppId, String paymentId, String tppRedirectUriCancellation) {
        PaymentCancellationResponseDto responseDto = new PaymentCancellationResponseDto();
        try {

            Response responseCancelPayment=coreSrvc.cancelPayment(paymentService,paymentProduct,tppId,paymentId);
            if(responseCancelPayment.getStatusInfo() == Response.Status.NO_CONTENT) {
                responseDto.setIsValid(true);
            }
           /* TppNalogPlatniDto tppNalogPlatniDto = getTppNalogBySifra(paymentId);
            if (tppNalogPlatniDto != null) {
                if (tppNalogPlatniDto.getPaymentService().equals(paymentService) && tppNalogPlatniDto.getPaymentProduct().equals(paymentProduct) && tppNalogPlatniDto.getTppId().equals(tppId)) {
                    if (tppNalogPlatniDto.mozeStartAutorizacijePovlacenje()) {
                        //update tpp redirect cancelation url
                        if (StringUtils.isNotBlank(tppRedirectUriCancellation)) {
                            tppNalogPlatniDto.setTppRedirectUriCancelation(tppRedirectUriCancellation);
                            tppNalogPlatniDto = nalogTppDao.edit(tppNalogPlatniDto);
                        }
                        responseDto.setTransactionStatus(tppNalogPlatniDto.getStatusPsd2());
                        responseDto.set_links(Psd2Util.formLinksPaymentCancellation(tppNalogPlatniDto));
                        responseDto.setIsValid(true);
                    } else {
                        responseDto.setIsValid(false);
                        responseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.CANCELLATION_INVALID.getValue(), new ArrayList<String>(Arrays.asList("Ne možete povući nalog koji nije potpisan i koji nije najava."))));
                    }
                } else {
                    responseDto.setIsValid(false);
                    responseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList("Nalog (payment id) se ne odnosi na TPP koji šalje zahtjev."))));
                }
            } else {
                responseDto.setIsValid(false);
                responseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList("Nalog (payment id) se ne odnosi na TPP koji šalje zahtjev."))));
            }*/
        } catch (Exception e) {
        	log.error("Exception is:", e);
            responseDto.setIsValid(false);
            responseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<String>(Arrays.asList("Internal server error"))));
        }
        return responseDto;
    }

    @Override
    public SinglePaymentDto getPaymentInformation(String paymentService, String paymentProduct, String tppId, String paymentId) {
        SinglePaymentDto response = null;
        try {
           // TppNalogPlatniDto tppNalogPlatniDto = nalogTppDao.getTppNalogBySifraProductServiceTpp(paymentId, paymentService, paymentProduct, tppId);
            response = coreSrvc.getPaymentRequest(paymentService, paymentProduct, tppId, paymentId);
            response.setIsValid(true);

        } catch (Exception e) {
        	log.error("Exception is:", e);
            response = SinglePaymentDto.builder().build();
            response.setIsValid(false);
            response.setErrorInformationDto(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<String>(Arrays.asList("Internal server error"))));
        }
        return response;
    }

    @Override
    public TransactionStatusResponseDto getPaymentInitiationStatus(String paymentService, String paymentProduct, String tppId, String paymentId) {
        TransactionStatusResponseDto response = null;
        try {
            response = coreSrvc.getPaymentRequestStatus(paymentService,paymentProduct,tppId,paymentId); //nalogTppDao.getTppNalogStatusBySifraServiceTpp(paymentId, paymentService, tppId, paymentProduct);
        } catch (Exception e) {
        	log.error("Exception is:", e);
            response = new TransactionStatusResponseDto();
            response.setIsValid(false);
            response.setErrorInformationDto(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<String>(Arrays.asList("Internal server error"))));
        }
        return response;
    }
}
