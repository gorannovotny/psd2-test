//package hr.abc.psd2.web;
//
//import hr.abc.psd2.exception.AbitRuleException;
//import hr.abc.psd2.util.*;
//import hr.abc.psd2.dto.authorization.AuthorizationDto;
//import hr.abc.psd2.dto.pis.TppNalogPlatniDto;
//import hr.abc.psd2.util.Psd2Constants;
//import hr.abit.b3.biz.riznica.PovlasteniTecajUtil;
//import hr.abit.b3.entity.ib.IBNalog;
//import org.apache.commons.lang3.StringUtils;
//
//import javax.annotation.PostConstruct;
//import javax.faces.view.ViewScoped;
//import javax.inject.Named;
//import java.math.BigDecimal;
//import java.math.RoundingMode;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//@Named
//@ViewScoped
//public class PotpisNalogKupoprodajeController extends PotpisNalogController {
//
//    protected static final long serialVersionUID = 1L;
//
//    private String iznosZaduzenjaStr, iznosOdobrenjaStr;
//    private BigDecimal tecaj;
//
//    public PotpisNalogKupoprodajeController() {
//        super();
//        setIsPotpis(Boolean.TRUE);
//    }
//
//    @PostConstruct
//    public void init() {
//        super.init();
//
//        //Tu dohvatimo nalog id=broj u autorizaciji
//        if (refTppNaloga != null && !StringUtils.isEmpty(refTppNaloga)) {
//            AuthorizationDto authorizationDto = authDao.getAuthorizationByLink(refTppNaloga, Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue());
//            if (authorizationDto != null) {
//                if (Arrays.asList(AuthorizationDto.SCA_STATUSI_ZA_POTPIS).contains(authorizationDto.getScastAuth())) {
//                    nepotpisaniNalozi = new ArrayList<>();
//                    TppNalogPlatniDto nalogTpp = nalogDao.getTppNalogBySifra(authorizationDto.getObjectAuth(), true);
//                    if (nalogTpp != null) {
//                        nalogTpp.setAuthorization(authorizationDto);
//                        createDto = nalogTpp;
//                        initNalog();
//                        initNalogKupoprodaja();
//                        nepotpisaniNalozi.add(createDto);
//                        redirectLink = redirectLink == null ? nalogTpp.getTppRedirectUri() : redirectLink;
//                    } else {
//                        showError(InternationalizationUtil.getLocalMessage("potpisNalogaError_nePostoji", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//                    }
//                } else {
//                    showError(InternationalizationUtil.getLocalMessage("potpisNalogaError_nePostojiAutorizacija", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//                }
//            }
//        } else {
//            showError(InternationalizationUtil.getLocalMessage("potpisNalogaError_idNePostoji", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//        }
//    }
//
//    public void initNalogKupoprodaja() {
//        createDto.setValutaZaduzenja(createDto.getSifraValute());
//        createDto.setValutaOdobrenja(createDto.getSifraValutePokrica());
//        createDto.setTipTecajaOdobrenja(GenericBassxConstants.Core.TIP_TECAJA_PRODAJNI);
//        createDto.setTipTecajaZaduzenja(GenericBassxConstants.Core.TIP_TECAJA_KUPOVNI);
//
//        if (StringUtils.isNotBlank(createDto.getSmjer())) {
//            if (StringUtils.equals(createDto.getSmjer(), IBNalog.SMJER_ZADANO_DUGOVNO)) {
//                createDto.setIznosZaduzenja(createDto.getIznos());
//                postaviUneseneTecajeve();
//                createDto = nalogDao.zadanIznosZaduzenje(createDto);
//                postaviIznoseTecajDugovno();
//            } else if (StringUtils.equals(createDto.getSmjer(), IBNalog.SMJER_ZADANO_POTRAZNO)) {
//                createDto.setIznosOdobrenja(createDto.getIznos());
//                postaviUneseneTecajeve();
//                createDto = nalogDao.zadanIznosOdobrenje(createDto);
//                postaviIznoseTecajPotrazno();
//            }
//        }
//    }
//
//    public void postaviUneseneTecajeve() {
//        if (getCreateDto().getSmjer() != null) {
//            if (getCreateDto().getSmjer().equals(GenericBassxConstants.Core.DUGOVNA_STRANA)) {
//                postaviUneseniDugovniTecaj(true);
//                // postavljanje kontra iznosa
//                if (getCreateDto().getIznosZaduzenja() != null && getCreateDto().getTecajZaduzenja() != null && getCreateDto().getTecajOdobrenja() != null) {
//                    getCreateDto().setIznosOdobrenja(getCreateDto().getIznosZaduzenja().multiply(getCreateDto().getTecajZaduzenja()).divide(getCreateDto().getTecajOdobrenja(), 2, RoundingMode.HALF_UP));
//                }
//            } else {
//                postaviUneseniPotrazniTecaj(true);
//                // postavljanje kontra iznosa
//                if (getCreateDto().getIznosOdobrenja() != null && getCreateDto().getTecajOdobrenja() != null && getCreateDto().getTecajZaduzenja() != null) {
//                    getCreateDto().setIznosZaduzenja(getCreateDto().getIznosOdobrenja().multiply(getCreateDto().getTecajOdobrenja()).divide(getCreateDto().getTecajZaduzenja(), 2, RoundingMode.HALF_UP));
//                }
//            }
//        }
//    }
//
//    private void postaviUneseniDugovniTecaj(boolean setTecajPotrazno) {
//        if (getCreateDto().getSifra() != null) {
//            if (getCreateDto().getValutaZaduzenja().equals(GenericBassxConstants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY)) {
//                getCreateDto().setRegularniTecajZaduzenja(BigDecimal.ONE);
//            } else {
//                try {
//                    getCreateDto().setPovlasteniTecajZaduzenja(PovlasteniTecajUtil.getStoredPovlasteniTecajIb(getCreateDto().getSifra(), GenericBassxConstants.Core.DUGOVNA_STRANA, nalogDao.getEntityManager()));
//                    getCreateDto().setRegularniTecajZaduzenja(LookupMinHelper.getICoreDaoMinProxyRemote().getTecajNaDan(getCreateDto().getDatumValute() != null ? getCreateDto().getDatumValute() : GenericDateUtil.today(), LookupMinHelper.getICoreDaoMinProxyRemote().findValutaBySifra(getCreateDto().getValutaZaduzenja()), getCreateDto().getTipTecajaZaduzenja()));
//                } catch (AbitRuleException e) {
//                    log.getLogger(getClass().getName()).log(Level.FINE, e.getMessage(), e);
//                    showError(InternationalizationUtil.getLocalMessage("controller_kupoprodajaDevizaController_errorIzracunTecaja", new Object[]{e.getMessage()}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//                }
//            }
//        } else {
//            getCreateDto().setRegularniTecajZaduzenja(null);
//        }
//        if (setTecajPotrazno) {
//            postaviUneseniPotrazniTecaj(false);
//        }
//    }
//
//    private void postaviUneseniPotrazniTecaj(boolean setTecajDugovno) {
//        if (getCreateDto().getSifra() != null) {
//            if (getCreateDto().getValutaOdobrenja().equals(GenericBassxConstants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY)) {
//                getCreateDto().setRegularniTecajOdobrenja(BigDecimal.ONE);
//            } else {
//                try {
//                    getCreateDto().setPovlasteniTecajOdobrenja(PovlasteniTecajUtil.getStoredPovlasteniTecajIb(getCreateDto().getSifra(), GenericBassxConstants.Core.POTRAZNA_STRANA, nalogDao.getEntityManager()));
//                    getCreateDto().setRegularniTecajOdobrenja(LookupMinHelper.getICoreDaoMinProxyRemote().getTecajNaDan(getCreateDto().getDatumValute() != null ? getCreateDto().getDatumValute() : GenericDateUtil.today(), LookupMinHelper.getICoreDaoMinProxyRemote().findValutaBySifra(getCreateDto().getValutaOdobrenja()), getCreateDto().getTipTecajaOdobrenja()));
//                } catch (AbitRuleException e) {
//                    log.getLogger(getClass().getName()).log(Level.FINE, e.getMessage(), e);
//                    showError(InternationalizationUtil.getLocalMessage("controller_kupoprodajaDevizaController_errorIzracunTecaja", new Object[]{e.getMessage()}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//                }
//            }
//        } else {
//            getCreateDto().setRegularniTecajOdobrenja(null);
//        }
//        if (setTecajDugovno) {
//            postaviUneseniDugovniTecaj(false);
//        }
//    }
//
//    public void postaviIznoseTecajDugovno() {
//        createDto = nalogDao.postaviDugovniTecaj(createDto);
//        checkChangeValuta();
//        // postavljanje kontra iznosa
//        if (getCreateDto().getIznosZaduzenja() != null && getCreateDto().getTecajZaduzenja() != null && getCreateDto().getTecajOdobrenja() != null) {
//            getCreateDto().setIznosOdobrenja(getCreateDto().getIznosZaduzenja().multiply(getCreateDto().getTecajZaduzenja()).divide(getCreateDto().getTecajOdobrenja(), 2, RoundingMode.HALF_UP));
//        } else if (getCreateDto().getIznosOdobrenja() != null && getCreateDto().getTecajZaduzenja() != null && getCreateDto().getTecajOdobrenja() != null) {
//            getCreateDto().setIznosZaduzenja(getCreateDto().getIznosOdobrenja().multiply(getCreateDto().getTecajOdobrenja()).divide(getCreateDto().getTecajZaduzenja(), 2, RoundingMode.HALF_UP));
//        }
//        tecaj = getCreateDto().getTecajOdobrenja() != null && getCreateDto().getIbanZaduzenja() != null ? getCreateDto().getTecajOdobrenja().compareTo(BigDecimal.ONE) == 0 ? getCreateDto().getTecajZaduzenja() : getCreateDto().getTecajOdobrenja() : null;
//    }
//
//    public void postaviIznoseTecajPotrazno() {
//        createDto = nalogDao.postaviPotrazniTecaj(createDto);
//        checkChangeValuta();
//        // postavljanje kontra iznosa
//        if (getCreateDto().getIznosOdobrenja() != null) {
//            getCreateDto().setIznosZaduzenja(getCreateDto().getIznosOdobrenja().multiply(getCreateDto().getTecajOdobrenja()).divide(getCreateDto().getTecajZaduzenja(), 2, RoundingMode.HALF_UP));
//        } else if (getCreateDto().getIznosZaduzenja() != null && getCreateDto().getTecajZaduzenja() != null && getCreateDto().getTecajOdobrenja() != null) {
//            getCreateDto().setIznosOdobrenja(getCreateDto().getIznosZaduzenja().multiply(getCreateDto().getTecajZaduzenja()).divide(getCreateDto().getTecajOdobrenja(), 2, RoundingMode.HALF_UP));
//        }
//        tecaj = getCreateDto().getTecajOdobrenja() != null && getCreateDto().getIbanZaduzenja() != null ? getCreateDto().getTecajOdobrenja().compareTo(BigDecimal.ONE) == 0 ? getCreateDto().getTecajZaduzenja() : getCreateDto().getTecajOdobrenja() : null;
//    }
//
//    private void checkChangeValuta() {
//        if (getCreateDto().getIznosOdobrenja() != null && getCreateDto().getIznosZaduzenja() != null) {
//            if (getCreateDto().getSmjer() != null && getCreateDto().getSmjer().equals(GenericBassxConstants.Core.POTRAZNA_STRANA))
//                getCreateDto().setIznosZaduzenja(null);
//            if (getCreateDto().getSmjer() != null && getCreateDto().getSmjer().equals(GenericBassxConstants.Core.DUGOVNA_STRANA))
//                getCreateDto().setIznosOdobrenja(null);
//        }
//    }
//
//    //getters & setters
//    public String getIznosZaduzenjaStr() {
//        if (createDto.getIznosZaduzenja() == null) createDto.setIznosZaduzenja(BigDecimal.ZERO);
//        iznosZaduzenjaStr = StringFunctions.bigDecimalToStringCurrency(createDto.getIznosZaduzenja());
//        try {
//            iznosZaduzenjaStr = iznosZaduzenjaStr + " " + LookupHelper.getICoreDaoProxyRemote().findValutaBySifra(createDto.getValutaZaduzenja()).getOznakaValute();
//        } catch (AbitRuleException e) {
//        }
//        return iznosZaduzenjaStr;
//    }
//
//    public void setIznosZaduzenjaStr(String iznosZaduzenjaStr) {
//        this.iznosZaduzenjaStr = iznosZaduzenjaStr;
//    }
//
//    public String getIznosOdobrenjaStr() {
//        if (createDto.getIznosOdobrenja() == null) createDto.setIznosOdobrenja(BigDecimal.ZERO);
//        iznosOdobrenjaStr = StringFunctions.bigDecimalToStringCurrency(createDto.getIznosOdobrenja());
//        try {
//            iznosOdobrenjaStr = iznosOdobrenjaStr + " " + LookupHelper.getICoreDaoProxyRemote().findValutaBySifra(createDto.getValutaOdobrenja()).getOznakaValute();
//        } catch (AbitRuleException e) {
//        }
//        return iznosOdobrenjaStr;
//    }
//
//    public void setIznosOdobrenjaStr(String iznosOdobrenjaStr) {
//        this.iznosOdobrenjaStr = iznosOdobrenjaStr;
//    }
//
//    public BigDecimal getTecaj() {
//        return tecaj;
//    }
//
//    public void setTecaj(BigDecimal tecaj) {
//        this.tecaj = tecaj;
//    }
//
//}
