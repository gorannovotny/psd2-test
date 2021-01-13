package hr.abc.psd2.bean.impl.paba;

import static hr.abc.psd2.bean.impl.ServiceConstants.SERVICE_PABA;
import static hr.abc.psd2.bean.impl.ServiceConstants.TRUE;
import static hr.abc.psd2.model.ConsentStatus.REVOKED_BY_PSU;
import static hr.abc.psd2.model.ConsentStatus.TERMINATED_BY_TPP;
import static hr.abc.psd2.model.ConsentStatus.VALID;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;

//import hr.abit.b3.biz.security.WebLoginBean;
//import hr.abit.b3.entity.ib.IBSmartPotpis;
//import hr.abit.b3.entity.ib.IBUsluga;
//import hr.abit.b3.entity.ib.WebSmartPotpisDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import hr.abc.psd2.model.dto.pis.SinglePaymentDto;
import hr.abc.psd2.model.dto.pis.TransactionStatusResponseDto;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

import hr.abc.psd2.bean.INalogTppBean;
import hr.abc.psd2.bean.impl.AbstractNalogTppBean;
import hr.abc.psd2.dao.AuthorizationDao;
import hr.abc.psd2.dao.BasketDao;
import hr.abc.psd2.dao.ConsentAisDao;
import hr.abc.psd2.dao.ConsentCofDao;
import hr.abc.psd2.dao.NalogTppDao;
import hr.abc.psd2.dao.Psd2DatotekaDao;
import hr.abc.psd2.dao.Psd2UtilDao;
import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.model.ConsentStatus;
import hr.abc.psd2.model.dto.ais.ConsentAisDto;
import hr.abc.psd2.model.dto.authorization.AuthorizationDto;
import hr.abc.psd2.model.dto.authorization.ScaStatus;
import hr.abc.psd2.model.dto.basket.BasketDto;
import hr.abc.psd2.model.dto.cof.ConsentCofDto;
import hr.abc.psd2.model.dto.core.NalogDevizniDto;
import hr.abc.psd2.model.dto.core.NalogDto;
import hr.abc.psd2.model.dto.core.NalogKnjizniDto;
import hr.abc.psd2.model.dto.core.NalogNefinDto;
import hr.abc.psd2.model.dto.core.NalogPlatniDto;
import hr.abc.psd2.model.dto.core.NalogSepaDto;
import hr.abc.psd2.model.dto.core.WebKorisnikDto;
import hr.abc.psd2.model.dto.core.WebKorisnikRacunDto;
import hr.abc.psd2.model.dto.pis.TppNalogPlatniDto;
//import hr.abit.b3.biz.retail.util.RetailSaldoUtil;
import hr.abc.psd2.security.Globals;
import hr.abc.psd2.service2.IAvpoSrvc;
import hr.abc.psd2.service2.ICoreSrvc;
import hr.abc.psd2.service2.IPlatniSrvc;
import hr.abc.psd2.service2.IVerifikacijaSrvc;
//import hr.abit.b3.biz.core.remote.IAvpoDaoProxyRemote;
//import hr.abit.b3.biz.core.remote.IKomitentPartijaProxyRemote;
import hr.abc.psd2.util.Bassx2Constants;
import hr.abc.psd2.util.FormPsd2Pain001XmlFile;
import hr.abc.psd2.util.GenericBassxConstants;
import hr.abc.psd2.util.GenericDateUtil;
import hr.abc.psd2.util.InternationalizationUtil;
import hr.abc.psd2.util.Psd2Constants;
import hr.abc.psd2.util.Psd2Util;
import io.quarkus.arc.properties.IfBuildProperty;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Tomica on 09-Jan-19.
 */
@IfBuildProperty(name = SERVICE_PABA, stringValue = TRUE)
@Slf4j
@Dependent
@Transactional
public class NalogTppBeanPaba extends AbstractNalogTppBean implements INalogTppBean, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	ConsentAisDao consentDao;
	@Inject
	ConsentCofDao consentCofDao;
	private @EJB AuthorizationBeanPaba authorizationBean;
	private @EJB NalogTppBeanPaba thisBean;
	private @EJB Psd2UtilBeanPaba psd2UtilBean;
	private @Inject NalogTppDao nalogTppDao;
	private @Inject Psd2UtilDao psd2UtilDao;
	private @Inject AuthorizationDao authorizationDao;
	//    @Inject
	//    private WebLoginBean loginBean;
	//    @Inject
	//    private WebSmartPotpisDao smartPotpisDao;
	//    @Inject
	//    private IBSecurityConstantsBean constBean;
	@Inject
	private Psd2DatotekaDao datotekaDao;
	@Inject
	private FormPsd2Pain001XmlFile formPsd2Pain001XmlFile;
	@Inject
	private BasketDao basketDao;

	@Inject
	private IPlatniSrvc platniSrvc;

	@Inject
	private ICoreSrvc coreSrvc;

	@Inject
	private IVerifikacijaSrvc verifikacijaSrvc;

	@Inject
	private IAvpoSrvc avpoSrvc;

	public String addSmartCheck(List<?> nalozi, String sessionId, String hash) throws AbitRuleException {
		return null;

		//        //Uzmemo zadnjih 100 znakova ako je hash duži od 100
		//        if (hash != null && hash.length() > 100) hash = StringUtils.right(hash, 100);
		//        if (nalozi != null) {
		//            WebSmartPotpisDto smartPotpis = null;
		//            List<Integer> listNalozi = new ArrayList<>();
		//            Object obj = null;
		//            for (Object nal : nalozi) {
		//                obj = nal;
		//                smartPotpis = new WebSmartPotpisDto();
		//                smartPotpis.setSessionId(sessionId);
		//                smartPotpis.setHash(hash);
		//                smartPotpis.setSifraIbKorisnik(loginBean.getIbKorisnik().getSifra());
		//                if (nal instanceof TppNalogPlatniDto) smartPotpis.setSifraNaloga(((TppNalogPlatniDto) nal).getSifra());
		//                if (nal instanceof ConsentAisDto) smartPotpis.setSifraNaloga(((ConsentAisDto) nal).getSifra());
		//                if (nal instanceof ConsentCofDto) smartPotpis.setSifraNaloga(((ConsentCofDto) nal).getSifra());
		//                smartPotpis.setSifraVlasnikaRacuna(null);
		//                smartPotpis.setStatus(IBSmartPotpis.STATUS_AKTIVAN);
		//                smartPotpis.setTipPotpisa(nal instanceof TppNalogPlatniDto ? IBSmartPotpis.TIP_TPP_NALOG
		//                        : (nal instanceof ConsentAisDto ? IBSmartPotpis.TIP_CONSENT_AIS : IBSmartPotpis.TIP_CONSENT_COF));
		//                smartPotpisDao.create(smartPotpis);
		//                listNalozi.add(smartPotpis.getSifraNaloga());
		//            }
		//            //Postavljanje vremena do kad je nalog zaključan za daljnje obrade
		//            if (obj instanceof TppNalogPlatniDto)
		//                nalogTppDao.updateSmartLock(listNalozi, constBean.getSmartExpiriyTime());
		//            if (obj instanceof ConsentAisDto) consentDao.updateSmartLock(listNalozi, constBean.getSmartExpiriyTime());
		//            if (obj instanceof ConsentCofDto)
		//                consentCofDao.updateSmartLock(listNalozi, constBean.getSmartExpiriyTime());
		//
		//        }
		//        return hash;
	}
	//
	//    public void invalidateSmartPotpis(WebSmartPotpisDto filter) throws AbitRuleException {
	//        smartPotpisDao.invalidateSmartPotpis(filter);
	//    }

	public List<TppNalogPlatniDto> signNalozi(List<TppNalogPlatniDto> nalozi, Globals remoteGlobals, WebKorisnikDto potpisnik, String signId) throws AbitRuleException {
		LoggerFactory.getLogger(this.getClass()).info("signTppNalozi - IB korisnik " + ""); // TODO Ivana
		Globals backgroundGlobals = Globals.createBackgroundGlobals(Bassx2Constants.Core.SIFRALIK_PSD2_TPP, Bassx2Constants.PlatniPromet.POSLOVNICA_PSD2, Bassx2Constants.Core.MODUL_SIFRAMOD_PSD2, true);
		Boolean isIbGradjani = remoteGlobals.getSifraLikvidatora().compareTo(GenericBassxConstants.Core.SIFRALIK_IBG_LIKVIDATOR) == 0;

		//Ako se radi o FO, onda prvo dohvatimo raspolozivo partija zaduzenja
		Map<Integer, Map<String, BigDecimal>> raspolozivoRetail = new HashMap<>();
		if (isIbGradjani) {
			Set<Integer> partijeZaduzenjaSet = new HashSet<>();
			for (TppNalogPlatniDto nalog : nalozi) {
				partijeZaduzenjaSet.add(nalog.getSifraPartijeZaduzenja());
			}
			//raspolozivoRetail = RetailSaldoUtil.getRaspolozivoPoAVFOPartijamaIValutamaMap(new ArrayList<>(partijeZaduzenjaSet), nalogTppDao.getEntityManager());
			raspolozivoRetail = null; // TODO Ivana
		} else {
			//Raspolozivo za PO - ne treba jer to radi sepa validacija
		}

		BigDecimal tmpRaspolozivo = null;
		List<Integer> potpisaniNalozi = new ArrayList<>();
		boolean postojiGreska = false;
		Integer basketSifra = null;
		for (TppNalogPlatniDto nalog : nalozi) {
			try {
				nalog.setGreska(null);
				basketSifra = nalog.getSifraBasketa();
				nalogTppDao.validateAndSetSepaNalog(nalog, backgroundGlobals);
				//List<WebKorisnikRacunDto> ovlastenja = loginBean.getOvlastenja();
				List<WebKorisnikRacunDto> ovlastenja = null; // TODO Ivana
				for (WebKorisnikRacunDto racun : ovlastenja) {
					if (racun.getPartijaDto().getSifra().compareTo(nalog.getSifraPartijeZaduzenja()) == 0 && !potpisaniNalozi.contains(nalog.getSifraTppNaloga())) {
						//Provjerimo da li je link istekao
						if (nalog.getAuthorization() != null && nalog.getAuthorization().isExpired(LocalDateTime.now())) {
							throw new AbitRuleException(InternationalizationUtil.getLocalMessage("potpisNalogaError_authExpired", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
						}
						//Provjerimo za svaki slučaj da li je korisnik već potpisao nalog
						if (nalog.getKorisnikPotpisaoNalog()) {
							throw new AbitRuleException("Korisnik već potpisao nalog.");
						}
						//if (!AuthenticationUtils.mozeUslugaByIban(nalog.getIbanZaduzenja(), loginBean.getOvlastenja(), IBUsluga.USLUGA_POTPIS_NALOGA)) {
						if (true) { // TODO Ivana - dodati pravi uvjet
							nalog.setGreska(InternationalizationUtil.getLocalMessage("potpisNalogaError_nedostatnaPrava", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
						}
						if (nalog.getGreska() == null) {
							//Kontrola raspolozivog za FO
							if (isIbGradjani) {
								//                                AuthenticationUtils.checkRaspolozivoFO(nalog.getIznos(),
								//                                        nalog.getIznosNaknade(),
								//                                        nalog.getIbanZaduzenja(),
								//                                        nalog.getSifraValute(),
								//                                        raspolozivoRetail.get(nalog.getSifraPartijeZaduzenja()),
								//                                        nalogTppDao.getEntityManager()); // TODO Ivana
							}

							//Ako je sve u redu, obradimo nalog
							if (nalog.getBatchBooking()) {
								boolean bbProlazi = true;
								Set<Date> datumiValuteSet = new HashSet<>();
								List<TppNalogPlatniDto> bbNalozi = nalog.getBatchBookingNalozi();
								nalog.setBrojPotrebnihPotpisa(racun.getBrojPotrebnihPotpisa());
								nalog = thisBean.obradiBBNalog(nalog, signId, potpisnik, remoteGlobals, racun);
								datumiValuteSet.add(nalog.getDatumValute());
								List<TppNalogPlatniDto> updateNalozi = new ArrayList<>();
								if (nalog.getBatchBookingNalozi() != null) {
									Integer sifraNalogaBB = null;
									try {
										for (TppNalogPlatniDto nalBb : nalog.getBatchBookingNalozi()) {
											sifraNalogaBB = nalBb.getSifraTppNaloga();
											nalBb.setBrojPotrebnihPotpisa(racun.getBrojPotrebnihPotpisa());
											try {
												updateNalozi.add(thisBean.obradiBBNalog(nalBb, signId, potpisnik, remoteGlobals, racun));
											} catch (AbitRuleException exBd) {
												nalBb.setGreska(exBd.getMessage());
												bbProlazi = false;
											}
											datumiValuteSet.add(nalBb.getDatumValute());
										}
										nalog.setBatchBookingNalozi(updateNalozi);
										thisBean.updateBBNalog(nalog);
									} catch (AbitRuleException exB) {
										nalog.setGreska(exB.getMessage());
									}
								}
								if (datumiValuteSet.size() > 1) {
									thisBean.stornoBBNaloga(nalog, remoteGlobals);
									//                                    nalog.setAuthorizations(authorizationDao.getAuthorizationByObjectId(nalog.getSifra(), Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), null, null, null));
									nalog.setGreska(InternationalizationUtil.getLocalMessage("bbNalozi_razlicitDatumValuta", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
								}
								if (!bbProlazi) {
									thisBean.stornoBBNaloga(nalog, remoteGlobals);
									//                                    nalog.setAuthorizations(authorizationDao.getAuthorizationByObjectId(nalog.getSifra(), Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), null, null, null));
									nalog.setGreska(InternationalizationUtil.getLocalMessage("bbNalozi_greskaNaNalogu", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
								}
							} else {
								nalog.setBrojPotrebnihPotpisa(racun.getBrojPotrebnihPotpisa());
								thisBean.obradiPojedinacniNalog(nalog, signId, potpisnik, remoteGlobals, racun);
							}
							potpisaniNalozi.add(nalog.getSifraTppNaloga()); //dodatna kontrola na potpisane naloge
							break;
						}
					}
				}
			} catch (AbitRuleException ex) {
				nalog.setGreska(ex.getMessage() == null || ex.getMessage().equals("") ? "Greška kod potpisa naloga. Kontaktirajte administratora." : ex.getMessage());
			} finally {
				nalog.setAuthorizations(authorizationDao.getAuthorizationByObjectId(
						nalog.isInBasket() ? nalog.getSifraBasketa().toString() : nalog.getSifraTppNaloga().toString(),
						nalog.isInBasket() ? Psd2Constants.AuthorizationObjectType.BASKET_AUTHORIZATION.getValue() : Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(),
						null,
						null,
						null));
			}

			if (nalog.getGreska() != null)
				postojiGreska = true;
		}
		//Provjera basketa
		if (basketSifra != null && postojiGreska) {
			thisBean.stornoBasketNaloga(nalozi, remoteGlobals, basketSifra);
			nalozi = nalogTppDao.getNalogTppListByBasketId(basketSifra);
		}

		return nalozi;
	}

	public List<ConsentAisDto> signConsenti(List<ConsentAisDto> consenti, Globals remoteGlobals, WebKorisnikDto potpisnik, String signId) throws AbitRuleException {
		//LoggerFactory.getLogger(this.getClass()).info("signTppNalozi - IB korisnik " + loginBean.getIbKorisnik().getSifra());
		LoggerFactory.getLogger(this.getClass()).info("signTppNalozi - IB korisnik " + ""); // TODO Ivana
		Globals backgroundGlobals = Globals.createBackgroundGlobals(Bassx2Constants.Core.SIFRALIK_PSD2_TPP, Bassx2Constants.PlatniPromet.POSLOVNICA_PSD2, Bassx2Constants.Core.MODUL_SIFRAMOD_PSD2, true);
		Boolean isIbGradjani = remoteGlobals.getSifraLikvidatora().compareTo(GenericBassxConstants.Core.SIFRALIK_IBG_LIKVIDATOR) == 0;

		List<Integer> potpisaniConsenti = new ArrayList<>();
		for (ConsentAisDto consent : consenti) {
			try {
				consent.setGreska(null);
				//List<WebKorisnikRacunDto> ovlastenja = loginBean.getOvlastenja();
				List<WebKorisnikRacunDto> ovlastenja = null; // TODO Ivana
				for (WebKorisnikRacunDto racun : ovlastenja) {
					//                    if (racun.getPartijaDto().getSifra().compareTo(nalog.getSifraPartijeZaduzenja()) == 0 && !potpisaniNalozi.contains(nalog.getSifra())) {
					//Provjerimo za svaki slučaj da li je korisnik već potpisao nalog
					if (consent.getKorisnikPotpisaoNalog()) {
						throw new AbitRuleException("Korisnik već potpisao consent.");
					} else if (consent.getGreska() == null
					//                                && AuthenticationUtils.mozeUslugaByIban(nalog.getIbanZaduzenja(), loginBean.getOvlastenja(), IBUsluga.USLUGA_POTPIS_NALOGA)
					) {

						//Ako je sve u redu, obradimo nalog
						obradiConsent(consent, signId, potpisnik, remoteGlobals, racun);
						potpisaniConsenti.add(consent.getSifra()); //dodatna kontrola na potpisane naloge
						break; //Ako smo obradili nalog, nalog ne moze biti u drugom ovlastenju
					}
					//                    }
				}
			} catch (AbitRuleException ex) {
				consent.setGreska(ex.getMessage() == null || ex.getMessage().equals("") ? "Greška kod potpisa naloga. Kontaktirajte administratora." : ex.getMessage());
			}
		}
		return consenti;
	}

	public List<ConsentCofDto> signConsentiCof(List<ConsentCofDto> consenti, Globals remoteGlobals, WebKorisnikDto potpisnik, String signId) throws AbitRuleException {
		//LoggerFactory.getLogger(this.getClass()).info("signTppNalozi - IB korisnik " + loginBean.getIbKorisnik().getSifra());
		LoggerFactory.getLogger(this.getClass()).info("signTppNalozi - IB korisnik " + ""); // TODO Ivana
		Globals backgroundGlobals = Globals.createBackgroundGlobals(Bassx2Constants.Core.SIFRALIK_PSD2_TPP, Bassx2Constants.PlatniPromet.POSLOVNICA_PSD2, Bassx2Constants.Core.MODUL_SIFRAMOD_PSD2, true);
		Boolean isIbGradjani = remoteGlobals.getSifraLikvidatora().compareTo(GenericBassxConstants.Core.SIFRALIK_IBG_LIKVIDATOR) == 0;

		List<Integer> potpisaniConsenti = new ArrayList<>();
		for (ConsentCofDto consent : consenti) {
			try {
				consent.setGreska(null);
				//List<WebKorisnikRacunDto> ovlastenja = loginBean.getOvlastenja();
				List<WebKorisnikRacunDto> ovlastenja = null; // TODO Ivana
				for (WebKorisnikRacunDto racun : ovlastenja) {
					//                    if (racun.getPartijaDto().getSifra().compareTo(nalog.getSifraPartijeZaduzenja()) == 0 && !potpisaniNalozi.contains(nalog.getSifra())) {
					//Provjerimo za svaki slučaj da li je korisnik već potpisao nalog
					if (consent.getKorisnikPotpisaoNalog()) {
						throw new AbitRuleException("Korisnik već potpisao consent.");
					} else if (consent.getGreska() == null
					//                                && AuthenticationUtils.mozeUslugaByIban(nalog.getIbanZaduzenja(), loginBean.getOvlastenja(), IBUsluga.USLUGA_POTPIS_NALOGA)
					) {

						//Ako je sve u redu, obradimo nalog
						obradiConsentCof(consent, signId, potpisnik, remoteGlobals, racun);
						potpisaniConsenti.add(consent.getSifra()); //dodatna kontrola na potpisane naloge
						break; //Ako smo obradili nalog, nalog ne moze biti u drugom ovlastenju
					}
					//                    }
				}
			} catch (AbitRuleException ex) {
				consent.setGreska(ex.getMessage() == null || ex.getMessage().equals("") ? "Greška kod potpisa naloga. Kontaktirajte administratora." : ex.getMessage());
			}
		}
		return consenti;
	}

	public List<TppNalogPlatniDto> povuciNaloge(List<TppNalogPlatniDto> nalozi, Globals remoteGlobals, WebKorisnikDto potpisnik, String signId) throws AbitRuleException {
		//LoggerFactory.getLogger(this.getClass()).info("cancelTppNalozi - IB korisnik " + loginBean.getIbKorisnik().getSifra());
		LoggerFactory.getLogger(this.getClass()).info("cancelTppNalozi - IB korisnik " + ""); // TODO Ivana
		Globals backgroundGlobals = Globals.createBackgroundGlobals(Bassx2Constants.Core.SIFRALIK_PSD2_TPP, Bassx2Constants.PlatniPromet.POSLOVNICA_PSD2, Bassx2Constants.Core.MODUL_SIFRAMOD_PSD2, true);

		List<Integer> potpisaniNalozi = new ArrayList<>();
		for (TppNalogPlatniDto nalog : nalozi) {
			try {
				nalog.setGreska(null);
				nalogTppDao.validateAndSetSepaNalog(nalog, backgroundGlobals);
				//List<WebKorisnikRacunDto> ovlastenja = loginBean.getOvlastenja();
				List<WebKorisnikRacunDto> ovlastenja = null; // TODO Ivana
				for (WebKorisnikRacunDto racun : ovlastenja) {
					if (racun.getPartijaDto().getSifra().compareTo(nalog.getSifraPartijeZaduzenja()) == 0 && !potpisaniNalozi.contains(nalog.getSifraTppNaloga())) {
						//Provjerimo za svaki slučaj da li je korisnik već potpisao nalog
						if (nalog.getKorisnikPotpisaoNalog()) {
							throw new AbitRuleException("Korisnik već potpisao nalog.");
							//} else if (nalog.getGreska() == null &&
							//AuthenticationUtils.mozeUslugaByIban(nalog.getIbanZaduzenja(), loginBean.getOvlastenja(), IBUsluga.USLUGA_BRISANJE_POVLACENJE_NALOGA)) {
						} else if (true) { // TODO Ivana - dodati pravi uvjet
							thisBean.povuciNalog(nalog, signId, potpisnik, backgroundGlobals, racun);
							potpisaniNalozi.add(nalog.getSifraTppNaloga()); //dodatna kontrola na potpisane naloge
							break; //Ako smo obradili nalog, nalog ne moze biti u drugom ovlastenju
						}
					}
				}
			} catch (AbitRuleException ex) {
				nalog.setGreska(ex.getMessage() == null || ex.getMessage().equals("") ? "Greška kod potpisa naloga. Kontaktirajte administratora." : ex.getMessage());
				if (nalog.getBatchBooking() || nalog.getSifraDatoteke() != null || nalog.isInBasket()) {
					throw ex;
				}
			}
		}
		return nalozi;
	}

	public List<ConsentAisDto> povuciConsente(List<ConsentAisDto> nalozi, Globals remoteGlobals, WebKorisnikDto potpisnik, String signId) throws AbitRuleException {
		//LoggerFactory.getLogger(this.getClass()).info("cancelTppNalozi - IB korisnik " + loginBean.getIbKorisnik().getSifra());
		LoggerFactory.getLogger(this.getClass()).info("cancelTppNalozi - IB korisnik " + ""); // TODO Ivana
		Globals backgroundGlobals = Globals.createBackgroundGlobals(Bassx2Constants.Core.SIFRALIK_PSD2_TPP, Bassx2Constants.PlatniPromet.POSLOVNICA_PSD2, Bassx2Constants.Core.MODUL_SIFRAMOD_PSD2, true);

		List<Integer> potpisaniNalozi = new ArrayList<>();
		for (ConsentAisDto nalog : nalozi) {
			try {
				nalog.setGreska(null);
				//                nalogTppDao.validateAndSetSepaNalog(nalog, backgroundGlobals);
				//List<WebKorisnikRacunDto> ovlastenja = loginBean.getOvlastenja();
				List<WebKorisnikRacunDto> ovlastenja = null; // TODO Ivana
				//                for (WebKorisnikRacunDto racun : ovlastenja) {
				//                    if (racun.getPartijaDto().getSifra().compareTo(nalog.getSifraPartijeZaduzenja()) == 0 && !potpisaniNalozi.contains(nalog.getSifra())) {
				//Provjerimo za svaki slučaj da li je korisnik već potpisao nalog
				if (nalog.getKorisnikPotpisaoNalog()) {
					throw new AbitRuleException("Korisnik već potpisao privolu.");
				} else if (nalog.getGreska() == null
				//                                && AuthenticationUtils.mozeUslugaByIban(nalog.get, loginBean.getOvlastenja(), IBUsluga.USLUGA_BRISANJE_POVLACENJE_NALOGA)
				) {
					thisBean.povuciConsent(nalog, signId, potpisnik, backgroundGlobals);
					potpisaniNalozi.add(nalog.getSifra()); //dodatna kontrola na potpisane naloge
					break; //Ako smo obradili nalog, nalog ne moze biti u drugom ovlastenju
				}
				//                    }
				//                }
			} catch (AbitRuleException ex) {
				nalog.setGreska(ex.getMessage() == null || ex.getMessage().equals("") ? "Greška kod potpisa consenta. Kontaktirajte administratora." : ex.getMessage());
			}
		}
		return nalozi;
	}

	public List<ConsentCofDto> povuciConsenteCof(List<ConsentCofDto> nalozi, Globals remoteGlobals, WebKorisnikDto potpisnik, String signId) throws AbitRuleException {
		//LoggerFactory.getLogger(this.getClass()).info("cancelTppNalozi - IB korisnik " + loginBean.getIbKorisnik().getSifra());
		LoggerFactory.getLogger(this.getClass()).info("cancelTppNalozi - IB korisnik " + ""); // TODO Ivana
		Globals backgroundGlobals = Globals.createBackgroundGlobals(Bassx2Constants.Core.SIFRALIK_PSD2_TPP, Bassx2Constants.PlatniPromet.POSLOVNICA_PSD2, Bassx2Constants.Core.MODUL_SIFRAMOD_PSD2, true);

		List<Integer> potpisaniNalozi = new ArrayList<>();
		for (ConsentCofDto nalog : nalozi) {
			try {
				nalog.setGreska(null);
				//                nalogTppDao.validateAndSetSepaNalog(nalog, backgroundGlobals);
				//List<WebKorisnikRacunDto> ovlastenja = loginBean.getOvlastenja();
				List<WebKorisnikRacunDto> ovlastenja = null; // TODO Ivana
				//                for (WebKorisnikRacunDto racun : ovlastenja) {
				//                    if (racun.getPartijaDto().getSifra().compareTo(nalog.getSifraPartijeZaduzenja()) == 0 && !potpisaniNalozi.contains(nalog.getSifra())) {
				//Provjerimo za svaki slučaj da li je korisnik već potpisao nalog
				if (nalog.getKorisnikPotpisaoNalog()) {
					throw new AbitRuleException("Korisnik već potpisao privolu.");
				} else if (nalog.getGreska() == null
				//                                && AuthenticationUtils.mozeUslugaByIban(nalog.get, loginBean.getOvlastenja(), IBUsluga.USLUGA_BRISANJE_POVLACENJE_NALOGA)
				) {
					thisBean.povuciConsentCof(nalog, signId, potpisnik, backgroundGlobals);
					potpisaniNalozi.add(nalog.getSifra()); //dodatna kontrola na potpisane naloge
					break; //Ako smo obradili nalog, nalog ne moze biti u drugom ovlastenju
				}
				//                    }
				//                }
			} catch (AbitRuleException ex) {
				nalog.setGreska(ex.getMessage() == null || ex.getMessage().equals("") ? "Greška kod potpisa consenta. Kontaktirajte administratora." : ex.getMessage());
			}
		}
		return nalozi;
	}

	//    public List<ConsentCofDto> povuciConsenteCofFromIb(List<ConsentCofDto> nalozi, Globals remoteGlobals, WebKorisnikDto potpisnik, String signId) throws AbitRuleException {
	//        LoggerFactory.getLogger(this.getClass()).info("cancelTppNalozi - IB korisnik " + loginBean.getIbKorisnik().getSifra());
	//        Globals backgroundGlobals = Globals.createBackgroundGlobals(Bassx2Constants.Core.SIFRALIK_PSD2_TPP, Bassx2Constants.PlatniPromet.POSLOVNICA_PSD2, Bassx2Constants.Core.MODUL_SIFRAMOD_PSD2, true);
	//
	//        List<Integer> potpisaniNalozi = new ArrayList<>();
	//        for (ConsentCofDto nalog : nalozi) {
	//            try {
	//                nalog.setGreska(null);
	////                nalogTppDao.validateAndSetSepaNalog(nalog, backgroundGlobals);
	//                List<WebKorisnikRacunDto> ovlastenja = loginBean.getOvlastenja();
	////                for (WebKorisnikRacunDto racun : ovlastenja) {
	////                    if (racun.getPartijaDto().getSifra().compareTo(nalog.getSifraPartijeZaduzenja()) == 0 && !potpisaniNalozi.contains(nalog.getSifra())) {
	//                //Provjerimo za svaki slučaj da li je korisnik već potpisao nalog
	//                if (nalog.getKorisnikPotpisaoNalog()) {
	//                    throw new AbitRuleException("Korisnik već potpisao privolu.");
	//                } else if (nalog.getGreska() == null
	////                                && AuthenticationUtils.mozeUslugaByIban(nalog.get, loginBean.getOvlastenja(), IBUsluga.USLUGA_BRISANJE_POVLACENJE_NALOGA)
	//                ) {
	//                    thisBean.povuciConsentCofFomIb(nalog, signId, potpisnik, backgroundGlobals);
	//                    potpisaniNalozi.add(nalog.getSifra()); //dodatna kontrola na potpisane naloge
	//                    break; //Ako smo obradili nalog, nalog ne moze biti u drugom ovlastenju
	//                }
	////                    }
	////                }
	//            } catch (AbitRuleException ex) {
	//                nalog.setGreska(ex.getMessage() == null || ex.getMessage().equals("") ? "Greška kod potpisa consenta. Kontaktirajte administratora." : ex.getMessage());
	//            }
	//        }
	//        return nalozi;
	//    }

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public TppNalogPlatniDto obradiPojedinacniNalog(TppNalogPlatniDto nalog, String signId, WebKorisnikDto potpisnik, Globals remoteGlobals, WebKorisnikRacunDto racun) throws AbitRuleException {
		return obradiNalog(nalog, signId, potpisnik, remoteGlobals, racun);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public TppNalogPlatniDto obradiBBNalog(TppNalogPlatniDto nalog, String signId, WebKorisnikDto potpisnik, Globals remoteGlobals, WebKorisnikRacunDto racun) throws AbitRuleException {
		return obradiNalog(nalog, signId, potpisnik, remoteGlobals, racun);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void updateBBNalog(TppNalogPlatniDto nalog) throws AbitRuleException {
		nalogTppDao.azuritajBatchBookingPlatni(nalog);
	}

	private TppNalogPlatniDto obradiNalog(TppNalogPlatniDto nalog, String signId, WebKorisnikDto potpisnik, Globals remoteGlobals, WebKorisnikRacunDto racun) throws AbitRuleException {
		//LoggerFactory.getLogger(this.getClass()).info("obradiNalog - korisnik " + loginBean.getIbKorisnik().getSifra() + " nalog " + nalog.getSifra());
		LoggerFactory.getLogger(this.getClass()).info("obradiNalog - korisnik " + "" + " nalog " + nalog.getSifraTppNaloga()); // TODO Ivana
		List<NalogSepaDto> listPotencijalnoIp = new ArrayList<>();
		Globals backgroundGlobals = Globals.createBackgroundGlobals(Bassx2Constants.Core.SIFRALIK_PSD2_TPP, Bassx2Constants.PlatniPromet.POSLOVNICA_PSD2, Bassx2Constants.Core.MODUL_SIFRAMOD_PSD2, true);

		nalog.setGreska(null);
		//nalog.setTrenutniAutenticiraniKorisnik(loginBean.getIbKorisnik().getSifra());
		nalog.setTrenutniAutenticiraniKorisnik(null); // TODO Ivana

		if (nalog.getKorisnikPotpisaoNalog()) {
			throw new AbitRuleException(InternationalizationUtil.getLocalMessage("potpisKorisnikVecPotpisao", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
		}

		try {
			//IAvpoDaoProxyRemote avpoProxy = null; // TODO Ivana - sto je ovo
			List<AuthorizationDto> authorizations = new ArrayList<>();
			AuthorizationDto authTemp = null;
			//Dodamo prvo postojeću autorizaciju u listu i provjerimo
			//Ako je basket u pitanju prvo dohvatimo postojeću autorizaciju, možda ju je već ažurirao prethodni nalog
			if (nalog.isInBasket()) {
				authTemp = authorizationDao.getAuthorizationByID(nalog.getAuthorization().getIdAuth());
				if (!authTemp.getScastAuth().equals(ScaStatus.FINALISED.getValue())) {
					//authTemp = authorizationDao.editForSign(nalog.getAuthorization(), loginBean.getIbKorisnik().getSifra());
					authTemp = null; // TODO Ivana
				}
			} else {
				//authTemp = authorizationDao.editForSign(nalog.getAuthorization(), loginBean.getIbKorisnik().getSifra());
				authTemp = null; // TODO Ivana
			}
			authorizations.add(authTemp);
			//Ako za potpis treba više potpisnika, dohvatimo i drugu autorizaciju
			if (nalog.getBrojPotrebnihPotpisa() != null && nalog.getBrojPotrebnihPotpisa().compareTo(1) > 0) {
				AuthorizationDto authSecond = authorizationDao.getSecondAuthorizationByObjectId(
						nalog.isInBasket() ? nalog.getSifraBasketa() : nalog.getSifraTppNaloga(),
						nalog.isInBasket() ? Psd2Constants.AuthorizationObjectType.BASKET_AUTHORIZATION.getValue() : Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(),
						Psd2Constants.AuthorizationType.CREATED.getValue(),
						nalog.getAuthorization().getIdAuth());
				if (authSecond != null)
					authorizations.add(authSecond);
			}
			nalog.setAuthorizations(authorizations);
			if (!nalog.getDovoljanBrojPotpisa(racun.getBrojPotrebnihPotpisa(), Psd2Constants.AuthorizationType.CREATED.getValue())) {
				//ibPotpisWaitForDrugiPotpis
				nalog.addInfoObrade(InternationalizationUtil.getLocalMessage("potpisWaitForDrugiPotpis", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
			} else {
				if (nalog.getVrstaNaloga() == null) {
					List<NalogSepaDto> sepaDtos = new ArrayList<>();
					sepaDtos.add(Psd2Util.formNalogSepaDtoFromTppNalog(nalog, backgroundGlobals));
					//if (avpoProxy == null) avpoProxy = LookupHelper.getIAvpoDaoProxyRemote();
					//sepaDtos = avpoProxy.createAvpoFromSepa(sepaDtos, backgroundGlobals);
					sepaDtos = null; // TODO Ivana
					// izdvajanje potencijalnih naloga za IP
					if (sepaDtos != null && !sepaDtos.isEmpty()) {
						for (NalogSepaDto np : sepaDtos) {
							if (np != null && StringUtils.isNotBlank(np.getNacinIzvrsenja()) && np.getNacinIzvrsenja().equals(Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_INSTANT)) {
								listPotencijalnoIp.add(np);
							}
						}
					}
					// izdvajanje potencijalnih naloga za IP

					//SepaDto mora biti točno 1
					if (sepaDtos == null || sepaDtos.size() != 1) {
						nalog.addInfoObrade(InternationalizationUtil.getLocalMessage("potpisWaitForDrugiPotpis", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
					} else {
						NalogSepaDto n = sepaDtos.get(0);
						//Dodamo naknadu za devizni odljev
						if (n != null && StringUtils.isNotBlank(n.getNacinIzvrsenja()) && n.getNacinIzvrsenja().equals(Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_SWIFT_NALOG)) {
							try {
								NalogDevizniDto devizniOdljev = new NalogDevizniDto(n);
								//n = avpoProxy.deviznoNaknada(n, devizniOdljev, backgroundGlobals);
								n = null; // TODO Ivana
								//} catch (AbitRuleException are) {
							} catch (Exception are) {
								n.setGreska(are.getMessage());
								n.addExceptions(are);
							}
						}
						nalog = Psd2Util.formTppNalogFromSepa(nalog, n, nalog);
					}
					nalogTppDao.edit(nalog);
					//                    WebDigitalSignatureFootprint footprint = new WebDigitalSignatureFootprint(nalog);
					//                    String hash = WebDigitalSignatureFootprint.getSHAHash(footprint.getFootprint(), SignatureContants.DIGEST_ALGORITHM_SHA_256);
					//                    nalog.getAuthorization().setObjectHashAut(hash);
					//edit authorization // TODO Ivana - ovo se nece koristiti?
					authorizationDao.edit(nalog.getAuthorization());
					//kupoprodaja
				} else if (nalog.getVrstaNaloga().compareTo(GenericBassxConstants.Ib.NALOG_VRSTA_KUPNJA_DEVIZA) == 0) {
					//inicijalizacija tppNaloga
					nalog = nalogTppDao.initializeKupnjaDeviza(nalog);

					NalogDevizniDto nalogDevizni = nalogTppDao.formNalogDevizniFromTppNalog(nalog);
					//nalogDevizni = LookupMinHelper.getIPlatniDaoMinProxyRemote().validateAndInitializeKupoprodaja(nalogDevizni, remoteGlobals); // stari
					nalogDevizni = platniSrvc.validateAndInitializeKupoprodaja(nalogDevizni, remoteGlobals); // prazni

					//nalogDevizni = LookupMinHelper.getIPlatniDaoMinProxyRemote().createKupoprodaja(nalogDevizni, backgroundGlobals); // stari
					nalogDevizni = platniSrvc.createKupoprodaja(nalogDevizni, backgroundGlobals); // prazni
					if (nalogDevizni != null) {
						if (nalogDevizni.getKnjizniNalog() != null) {
							nalog.setSifraKnjiznogNaloga(nalogDevizni.getKnjizniNalog().getSifra());
							nalog.setDatumValute(nalogDevizni.getKnjizniNalog().getDatumValute());
							nalog.setSvrha(nalogDevizni.getKnjizniNalog().getSvrha());
						}
						//nalog.setNacinZadavanja(nalogDevizni.getNacinIzvrsenja());
					}
					// inkrement statusa, za interne odmah knjizi, za eksterne se verificira i čeka likvidnost - SWIFT nalozi ostaju u statusu 0
					//NalogKnjizniDto nalogBasic = LookupMinHelper.getICoreDaoMinProxyRemote().getNalogKnjizniDto(nalog.getSifraKnjiznogNaloga()); // stari
					NalogKnjizniDto nalogBasic = coreSrvc.getNalogKnjizniDto(nalog.getSifraKnjiznogNaloga()); // prazni
					if (nalogBasic.getStatus().compareTo(GenericBassxConstants.Core.NALOG_STATUNAL_PROKNJIZEN) != 0 && GenericDateUtil.compareDates(nalogBasic.getDatumValute(), GenericDateUtil.today()) <= 0)
						//LookupMinHelper.getIVerifikacijaBeanMinRemote().inkrementStatusa(nalogBasic, remoteGlobals); // stari
						verifikacijaSrvc.inkrementStatusa(nalogBasic, remoteGlobals); // prazni
					//LookupMinHelper.getICoreDaoMinProxyRemote().adaptAndInsertListPlatniNalog(Arrays.asList(nalogDevizni), false); // stari
					coreSrvc.adaptAndInsertListPlatniNalog(Arrays.asList(nalogDevizni), false); // prazni
					nalogTppDao.edit(nalog);
					//                    WebDigitalSignatureFootprint footprint = new WebDigitalSignatureFootprint(nalog);
					//                    String hash = WebDigitalSignatureFootprint.getSHAHash(footprint.getFootprint(), SignatureContants.DIGEST_ALGORITHM_SHA_256);
					//                    nalog.getAuthorization().setObjectHashAut(hash); // TODO Ivana - ovo se nece koristiti?
					//edit authorization
					authorizationDao.edit(nalog.getAuthorization());
				}
			}
			//Ponovno dodamo autorizacije jer su se kod edita izgubile
			nalog.setAuthorizations(authorizations);
			BasketDto basketDto = basketDao.getBasketDetails(nalog.getSifraBasketa());
			if (nalog.isInBasket() && !basketDto.getStatusBas().equals(Psd2Constants.BasketStatus.ACCEPTED_TECHNICAL_VALIDATION.getValue())) {

				if (nalog.getBrojPotrebnihPotpisa().compareTo(1) > 0) {
					Integer brPotpisanih = 0;
					for (AuthorizationDto authDtoBasket : nalog.getAuthorizations()) {
						if (authDtoBasket.getScastAuth().equals(ScaStatus.FINALISED.getValue())) {
							brPotpisanih++;
						}
					}
					if (!brPotpisanih.equals(nalog.getBrojPotrebnihPotpisa())) {
						if (!basketDto.getStatusBas().equals(Psd2Constants.BasketStatus.PARTIALLY_ACCEPTED_TECHNICAL_CORRECT.getValue())) {
							// update basket status PATC
							basketDao.changeBasketStatus(basketDto, Psd2Constants.BasketStatus.PARTIALLY_ACCEPTED_TECHNICAL_CORRECT.getValue());
						}
					} else {
						//update to ACTC
						basketDao.changeBasketStatus(basketDto, Psd2Constants.BasketStatus.ACCEPTED_TECHNICAL_VALIDATION.getValue());
					}
				} else {
					for (AuthorizationDto authDtoBasket : nalog.getAuthorizations()) {
						if (authDtoBasket.getScastAuth().equals(ScaStatus.FINALISED.getValue())) {
							//UPDATE TO ACTC
							basketDao.changeBasketStatus(basketDto, Psd2Constants.BasketStatus.ACCEPTED_TECHNICAL_VALIDATION.getValue());

						}
					}

				}
			}
			// poziv slanja u IP na kraju metode
			if (listPotencijalnoIp != null && !listPotencijalnoIp.isEmpty()) {
				for (NalogSepaDto np : listPotencijalnoIp) {
					if (np != null) {
						//LookupHelper.getIPlatniDaoProxyRemote().forwardToInstaPay(np, remoteGlobals); // stari
						platniSrvc.forwardToInstaPay(np, remoteGlobals); // prazni
					}
				}
			}
		} catch (AbitRuleException are) {
			String porukaGreske = are.getMessage();
			if (porukaGreske == null || porukaGreske.equals("")) {
				porukaGreske = are.getMessages() != null && are.getMessages().size() > 0 ? are.getMessages().get(0) : "Nepoznata greška. Kontaktirajte administratora.";
				are = new AbitRuleException(porukaGreske);
			}
			LoggerFactory.getLogger(this.getClass()).error(porukaGreske, are);
			throw are;
		} catch (Exception e) {
			LoggerFactory.getLogger(this.getClass()).error(e.getMessage(), e);
			throw new AbitRuleException(e.getMessage() != null ? e.getMessage() : e.toString());
		}

		return nalog;
	}

	public ConsentAisDto obradiConsent(ConsentAisDto consent, String signId, WebKorisnikDto potpisnik, Globals remoteGlobals, WebKorisnikRacunDto racun) throws AbitRuleException {
		//LoggerFactory.getLogger(this.getClass()).info("obradiConsent - korisnik " + loginBean.getIbKorisnik().getSifra() + " consent " + consent.getSifra());
		LoggerFactory.getLogger(this.getClass()).info("obradiConsent - korisnik " + "" + " consent " + consent.getSifra()); // TODO Ivana - loginbean
		Globals backgroundGlobals = Globals.createBackgroundGlobals(Bassx2Constants.Core.SIFRALIK_PSD2_TPP, Bassx2Constants.PlatniPromet.POSLOVNICA_PSD2, Bassx2Constants.Core.MODUL_SIFRAMOD_PSD2, true);

		consent.setGreska(null);
		//consent.setTrenutniAutenticiraniKorisnik(loginBean.getIbKorisnik().getSifra()); // TODO Ivana
		consent.setTrenutniAutenticiraniKorisnik(null);

		if (consent.getKorisnikPotpisaoNalog()) {
			throw new AbitRuleException(InternationalizationUtil.getLocalMessage("potpisKorisnikVecPotpisao", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
		}

		try {
			//Dodamo prvo postojeću autorizaciju u listu i provjerimo
			//consent.setAuthorization(authorizationDao.editForSign(consent.getAuthorization(), loginBean.getIbKorisnik().getSifra()));
			consent.setAuthorization(null); // TODO
			consent.setStateCon(VALID.getValue());
			//Provjerimo recurring consent
			if (consent.getRecurringIndicatorCon()) {
				consentDao.updateStatusOnRecurringConsents(consent);
			}
			consentDao.edit(consent);
			//            WebDigitalSignatureFootprint footprint = new WebDigitalSignatureFootprint(consent);
			//            String hash = WebDigitalSignatureFootprint.getSHAHash(footprint.getFootprint(), SignatureContants.DIGEST_ALGORITHM_SHA_256);
			//            consent.getAuthorization().setObjectHashAut(hash); // TODO Ivana - ovo se nece koristiti?
			authorizationDao.edit(consent.getAuthorization());
		} catch (AbitRuleException are) {
			String porukaGreske = are.getMessage();
			if (porukaGreske == null || porukaGreske.equals("")) {
				porukaGreske = are.getMessages() != null && are.getMessages().size() > 0 ? are.getMessages().get(0) : "Nepoznata greška. Kontaktirajte administratora.";
				are = new AbitRuleException(porukaGreske);
			}
			LoggerFactory.getLogger(this.getClass()).error(porukaGreske, are);
			throw are;
		} catch (Exception e) {
			LoggerFactory.getLogger(this.getClass()).error(e.getMessage(), e);
			throw new AbitRuleException(e.getMessage() != null ? e.getMessage() : e.toString());
		}

		return consent;
	}

	public ConsentCofDto obradiConsentCof(ConsentCofDto consent, String signId, WebKorisnikDto potpisnik, Globals remoteGlobals, WebKorisnikRacunDto racun) throws AbitRuleException {
		//LoggerFactory.getLogger(this.getClass()).info("obradiConsent - korisnik " + loginBean.getIbKorisnik().getSifra() + " consent " + consent.getSifra());
		LoggerFactory.getLogger(this.getClass()).info("obradiConsent - korisnik " + "" + " consent " + consent.getSifra()); // TODO Ivana - loginbean
		Globals backgroundGlobals = Globals.createBackgroundGlobals(Bassx2Constants.Core.SIFRALIK_PSD2_TPP, Bassx2Constants.PlatniPromet.POSLOVNICA_PSD2, Bassx2Constants.Core.MODUL_SIFRAMOD_PSD2, true);

		consent.setGreska(null);
		//consent.setTrenutniAutenticiraniKorisnik(loginBean.getIbKorisnik().getSifra());
		consent.setTrenutniAutenticiraniKorisnik(null); // TODO Ivana

		if (consent.getKorisnikPotpisaoNalog()) {
			throw new AbitRuleException(InternationalizationUtil.getLocalMessage("potpisKorisnikVecPotpisao", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
		}

		try {
			//Dodamo prvo postojeću autorizaciju u listu i provjerimo
			//consent.setAuthorization(authorizationDao.editForSign(consent.getAuthorization(), loginBean.getIbKorisnik().getSifra()));
			consent.setAuthorization(null); // TODO Ivana
			consent.setStateCon(VALID.getValue());
			//Svim validnim consentima mijenjamio status tako da je samo trenutni validan
			consentCofDao.updateStatusOnAllValidCofConsents(consent);

			consentCofDao.edit(consent);
			//            WebDigitalSignatureFootprint footprint = new WebDigitalSignatureFootprint(consent);
			//            String hash = WebDigitalSignatureFootprint.getSHAHash(footprint.getFootprint(), SignatureContants.DIGEST_ALGORITHM_SHA_256);
			//            consent.getAuthorization().setObjectHashAut(hash); // TODO Ivana - ovo se nece koristiti?
			authorizationDao.edit(consent.getAuthorization());
		} catch (AbitRuleException are) {
			String porukaGreske = are.getMessage();
			if (porukaGreske == null || porukaGreske.equals("")) {
				porukaGreske = are.getMessages() != null && are.getMessages().size() > 0 ? are.getMessages().get(0) : "Nepoznata greška. Kontaktirajte administratora.";
				are = new AbitRuleException(porukaGreske);
			}
			LoggerFactory.getLogger(this.getClass()).error(porukaGreske, are);
			throw are;
		} catch (Exception e) {
			LoggerFactory.getLogger(this.getClass()).error(e.getMessage(), e);
			throw new AbitRuleException(e.getMessage() != null ? e.getMessage() : e.toString());
		}

		return consent;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void povuciNalog(TppNalogPlatniDto nalog, String signId, WebKorisnikDto potpisnik, Globals remoteGlobals, WebKorisnikRacunDto racun) throws AbitRuleException {
		try {
			if (nalog.getSifraKnjiznogNaloga() == null) {
				throw new AbitRuleException(InternationalizationUtil.getLocalMessage("povlacenjeError_kriviStatus", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
			} else if (nalog.isNajava()) {
				//Dodamo prvo postojeću autorizaciju u listu i provjerimo
				//nalog.addAuthorization(authorizationDao.editForSign(nalog.getAuthorization(), loginBean.getIbKorisnik().getSifra()));
				nalog.addAuthorization(null); // TODO Ivana
				//Ako za potpis treba više potpisnika, dohvatimo i drugu autorizaciju
				if (racun.getBrojPotrebnihPotpisa() != null && racun.getBrojPotrebnihPotpisa().compareTo(1) > 1) {
					nalog.addAuthorization(authorizationDao.getSecondAuthorizationByObjectId(
							nalog.getSifraTppNaloga(),
							Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(),
							Psd2Constants.AuthorizationType.CANCELLED.getValue(),
							nalog.getAuthorization().getIdAuth()));
				}
				if (!nalog.getDovoljanBrojPotpisa(racun.getBrojPotrebnihPotpisa(), Psd2Constants.AuthorizationType.CANCELLED.getValue())) {
					//ibPotpisWaitForDrugiPotpis
					nalog.addInfoObrade(InternationalizationUtil.getLocalMessage("potpisWaitForDrugiPotpis", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
				} else {
					//NalogKnjizniDto nalogKnjizniDto = LookupHelper.getICoreDaoProxyRemote().getNalogKnjizniDto(nalog.getSifraKnjiznogNaloga()); // stari
					NalogKnjizniDto nalogKnjizniDto = coreSrvc.getNalogKnjizniDto(nalog.getSifraKnjiznogNaloga()); // prazni
					nalogKnjizniDto.setOstaviPromete(true);
					//LookupHelper.getIVerifikacijaBeanRemote().povuciNalog(nalogKnjizniDto, loginBean.getIbGlobals().getSifraLikvidatora()); // stari
					//verifikacijaSrvc.povuciNalog(nalogKnjizniDto, loginBean.getIbGlobals().getSifraLikvidatora()); // prazni
					verifikacijaSrvc.povuciNalog(nalogKnjizniDto, null); // prazni // TODO Ivana - loginbean
					// povlačenje naloga obračuna naknade (samo devizni nalozi USD...)
					List<NalogDto> listNalogKnjizni = new ArrayList<>();
					listNalogKnjizni.add(nalogKnjizniDto);
					//List<NalogDto> listNalogaNakande = new ArrayList<NalogDto>(LookupHelper.getICoreDaoProxyRemote().fetchListKnjizniNalogNaknadeByPrethodnik(listNalogKnjizni, Arrays.asList(new String[]{Bassx2Constants.Core.KLASA_NALOGA_GRUPA_OBRACUN_NAKNADE, Bassx2Constants.Core.KLASA_NALOGA_GRUPA_NAPLATA_NAKNADE, Bassx2Constants.PlatniPromet.KLASA_NALOGA_GRUPA_POKRICE_DEVIZNE_NAKNADE}), true)); // stari
					List<NalogDto> listNalogaNakande = new ArrayList<NalogDto>(coreSrvc.fetchListKnjizniNalogNaknadeByPrethodnik(listNalogKnjizni, Arrays.asList(new String[] { Bassx2Constants.Core.KLASA_NALOGA_GRUPA_OBRACUN_NAKNADE, Bassx2Constants.Core.KLASA_NALOGA_GRUPA_NAPLATA_NAKNADE, Bassx2Constants.PlatniPromet.KLASA_NALOGA_GRUPA_POKRICE_DEVIZNE_NAKNADE }), true)); // prazni
					if (listNalogaNakande != null && !listNalogaNakande.isEmpty()) {
						//LookupHelper.getIVerifikacijaBeanRemote().povuciNalogList(listNalogaNakande, loginBean.getIbGlobals().getSifraLikvidatora()); // stari
						//verifikacijaSrvc.povuciNalogList(listNalogaNakande, loginBean.getIbGlobals().getSifraLikvidatora()); // prazni
						verifikacijaSrvc.povuciNalogList(listNalogaNakande, null); // prazni // TODO Ivana - loginbean
					}
					nalogTppDao.edit(nalog);
				}
			} else {
				throw new AbitRuleException(InternationalizationUtil.getLocalMessage("povlacenjeError_NijeNajava", GenericBassxConstants.Ib.MESSAGE_BUNDLE));
			}
		} catch (AbitRuleException e) {
			throw e;
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void povuciConsent(ConsentAisDto consent, String signId, WebKorisnikDto potpisnik, Globals remoteGlobals) throws AbitRuleException {
		try {
			//Dodamo prvo postojeću autorizaciju u listu i provjerimo
			consent.setAuthorization(authorizationDao.editForSign(consent.getAuthorization(), potpisnik.getSifra()));
			consent.setStateCon(TERMINATED_BY_TPP.getValue());
			consentDao.edit(consent);
		} catch (AbitRuleException e) {
			throw e;
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void povuciConsentCof(ConsentCofDto consent, String signId, WebKorisnikDto potpisnik, Globals remoteGlobals) throws AbitRuleException {
		try {
			//Dodamo prvo postojeću autorizaciju u listu i provjerimo
			consent.setAuthorization(authorizationDao.editForSign(consent.getAuthorization(), potpisnik.getSifra()));
			consent.setStateCon(TERMINATED_BY_TPP.getValue());
			consentCofDao.edit(consent);
		} catch (AbitRuleException e) {
			throw e;
		}
	}

	//    public List<ConsentCofDto> povuciConsenteCofFromIb(List<ConsentCofDto> nalozi, Globals remoteGlobals, Integer sifraKorisnika, String signId) throws AbitRuleException {
	////        LoggerFactory.getLogger(this.getClass()).info("cancelTppNalozi - IB korisnik " + loginBean.getIbKorisnik().getSifra());
	////        Globals backgroundGlobals = Globals.createBackgroundGlobals(Bassx2Constants.Core.SIFRALIK_PSD2_TPP, Bassx2Constants.PlatniPromet.POSLOVNICA_PSD2, Bassx2Constants.Core.MODUL_SIFRAMOD_PSD2, true);
	//
	//        List<Integer> potpisaniNalozi = new ArrayList<>();
	//        for (ConsentCofDto nalog : nalozi) {
	//            try {
	//                nalog.setGreska(null);
	////                nalogTppDao.validateAndSetSepaNalog(nalog, backgroundGlobals);
	////                List<WebKorisnikRacunDto> ovlastenja = loginBean.getOvlastenja();
	////                for (WebKorisnikRacunDto racun : ovlastenja) {
	////                    if (racun.getPartijaDto().getSifra().compareTo(nalog.getSifraPartijeZaduzenja()) == 0 && !potpisaniNalozi.contains(nalog.getSifra())) {
	//                //Provjerimo za svaki slučaj da li je korisnik već potpisao nalog
	//                if (nalog.getKorisnikPotpisaoNalog()) {
	//                    throw new AbitRuleException("Korisnik već potpisao privolu.");
	//                } else if (nalog.getGreska() == null
	////                                && AuthenticationUtils.mozeUslugaByIban(nalog.get, loginBean.getOvlastenja(), IBUsluga.USLUGA_BRISANJE_POVLACENJE_NALOGA)
	//                ) {
	//                    povuciConsentCofFomIb(nalog, signId, sifraKorisnika);
	//                    potpisaniNalozi.add(nalog.getSifra()); //dodatna kontrola na potpisane naloge
	//                    break; //Ako smo obradili nalog, nalog ne moze biti u drugom ovlastenju
	//                }
	////                    }
	////                }
	//            } catch (AbitRuleException ex) {
	//                nalog.setGreska(ex.getMessage() == null || ex.getMessage().equals("") ? "Greška kod potpisa consenta. Kontaktirajte administratora." : ex.getMessage());
	//            }
	//        }
	//        return nalozi;
	//    }

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ConsentAisDto povuciConsentCofFomIb(ConsentAisDto consent, String signId, Integer sifraKorisnika) throws AbitRuleException {
		ConsentCofDto consentCofDto = null;
		try {
			consentCofDto = consentCofDao.getConsentCofBySifra(consent.getSifra());
			//validacija
			if (StringUtils.equals(consentCofDto.getStateCon(), VALID.getValue())) {
				consentCofDao.updateConsentCofStatus(consentCofDto.getSifra(), REVOKED_BY_PSU.getValue(), consentCofDto.getTppIdCon());
			} else {
				throw new AbitRuleException("Greška kod povlačenja naloga");
			}
		} catch (AbitRuleException e) {
			throw e;
		}

		if (consentCofDto != null) {
			return consent;
		} else {
			consent = null;
			return consent;
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public ConsentAisDto povuciConsentAisFomIb(ConsentAisDto consent, String signId, Integer sifraKorisnika) throws AbitRuleException {
		ConsentAisDto consentAisDto = null;

		try {
			consentAisDto = consentDao.getConsentAisBySifra(consent.getSifra());
			if (StringUtils.equals(consentAisDto.getStateCon(), VALID.getValue())) {
				consentDao.updateConsentStatus(consentAisDto.getSifra(), REVOKED_BY_PSU.getValue(), consentAisDto.getTppIdCon());
			} else {
				throw new AbitRuleException("Greška kod povlačenja naloga");
			}

		} catch (AbitRuleException e) {
			throw e;
		} catch (Exception ex) {
			throw new AbitRuleException("Greška kod povlačenja naloga");
		}
		return consentAisDto;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public TppNalogPlatniDto stornoBBNaloga(TppNalogPlatniDto nalog, Globals remoteGlobals) throws AbitRuleException {
		nalogTppDao.stornoBatchBookingPlatni(nalog);
		//brisanje potpisa
		nalogTppDao.stornoBBAuthorizations(nalog);
		//IKomitentPartijaProxyRemote komitentPartijaRemote = LookupHelper.getIKomitentPartijaProxyRemote();
		//update IB naloga
		//NalogNefinDto logDto = komitentPartijaRemote.beginLog(Bassx2Constants.Ib.TIP_NALOGA_SIFRA_POD_AUTOMATSKI_STORNO_BB_PSD2_LOG, "Storno BB naloga " + nalog.getSifra(), remoteGlobals);
		NalogNefinDto logDto = null; // TODO Ivana
		//nalog.setNalogNefin(logDto);
		nalog.setSifraKnjiznogNaloga(null);
		nalogTppDao.edit(nalog, logDto);
		/*if (nalog.getBatchBookingNalozi() != null) {
			for (TppNalogPlatniDto bbNal : nalog.getBatchBookingNalozi()) {
				bbNal.setNalogNefin(logDto);
				bbNal.setSifraKnjiznogNaloga(null);
				nalogTppDao.edit(bbNal, logDto);
			}
		}*/
		//komitentPartijaRemote.endLog(logDto); //TODO Ivana
		nalog = nalogTppDao.getTppNalogBySifra(nalog.getSifraTppNaloga(), true);
		return nalog;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void stornoBasketNaloga(List<TppNalogPlatniDto> naloziBasket, Globals remoteGlobals, Integer sifraBasket) throws AbitRuleException {
		//storno naloga
		nalogTppDao.stornoBasketPlatni(sifraBasket);
		//brisanje potpisa
		nalogTppDao.stornoBasketAuthorizations(sifraBasket);
		//IKomitentPartijaProxyRemote komitentPartijaRemote = LookupHelper.getIKomitentPartijaProxyRemote();
		//update IB naloga
		//NalogNefinDto logDto = komitentPartijaRemote.beginLog(Bassx2Constants.Ib.TIP_NALOGA_SIFRA_POD_AUTOMATSKI_STORNO_BASKET_PSD2_LOG, "Storno basket " + naloziBasket.get(0).getSifraBasketa(), remoteGlobals);
		NalogNefinDto logDto = null; //TODO Ivana
		for (TppNalogPlatniDto bbNal : naloziBasket) {
			if (bbNal.getGreska() == null && bbNal.getSifraKnjiznogNaloga() == null)
				continue;
			//bbNal.setNalogNefin(logDto);
			bbNal.setSifraKnjiznogNaloga(null);
			bbNal.setGreska(bbNal.getGreska());
			nalogTppDao.edit(bbNal, logDto);
		}
		//komitentPartijaRemote.endLog(logDto); // TODO Ivana
	}

	@Override
	public TppNalogPlatniDto getTppNalogBySifra(Integer paymentId) {
		return nalogTppDao.getTppNalogBySifra(paymentId);
	}

	@Override
	protected void createNalogFromChangedTppNalog(TppNalogPlatniDto tppNalogPlatniDto) {
		coreSrvc.createNalogFromChangedTppNalog(tppNalogPlatniDto);
	}

	@Override
	protected NalogDevizniDto validateAndInitializeKupoprodaja(NalogDevizniDto devizniNalogDto, Globals globals) {
		return platniSrvc.validateAndInitializeKupoprodaja(devizniNalogDto, globals);
	}

	@Override
	protected List<NalogPlatniDto> resolvePainDatoteka(byte[] podaciDatoteke) {
		return avpoSrvc.resolvePainDatoteka(podaciDatoteke);
	}

	@Override
	protected List<NalogSepaDto> pripremaNaloga(List<NalogSepaDto> nalogSepaDtos, Globals globals) {
		return avpoSrvc.pripremaNaloga(nalogSepaDtos, globals);
	}


}
