//package hr.abc.psd2.web;
//
//import hr.abit.b3.biz.core.GenericMinDao;
//import hr.abc.psd2.exception.AbitRuleException;
//import hr.abit.b3.biz.core.util.Bassx2Message;
//import hr.abc.psd2.util.GenericBassxConstants;
//import hr.abc.psd2.util.InternationalizationUtil;
//import hr.abit.b3.biz.ib.bean.AuthenticationUtilBean;
//import hr.abit.b3.biz.ib.bean.IBSecurityConstantsBean;
//import hr.abit.b3.biz.ib.dataObject.WebSmartPotpisDto;
//import hr.abit.b3.biz.ib.util.AuthenticationUtils;
//import hr.abit.b3.biz.ib.util.WebDigitalSignatureFootprint;
//import hr.abc.psd2.dto.authorization.AuthorizationDto;
//import hr.abc.psd2.dto.cof.ConsentCofDto;
//import hr.abc.psd2.util.Psd2Constants;
//import hr.abit.b3.biz.security.*;
//import hr.abit.b3.entity.ib.IBPrijavaOdjava;
//import hr.abit.b3.entity.ib.IBSmartPotpis;
//import hr.abit.b3.entity.ib.IBUsluga;
//import hr.abit.b3.entity.ib.WebSmartPotpisDao;
//import hr.abit.b3.web.core.GenericController;
//import hr.abit.b3.web.core.WebCommons;
//import org.apache.commons.lang.RandomStringUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.primefaces.PrimeFaces;
//import org.slf4j.LoggerFactory;
//import hr.abc.psd2.bean.NalogTppBean;
//import hr.abc.psd2.dao.AuthorizationDao;
//import hr.abc.psd2.dao.ConsentCofDao;
//
//import javax.annotation.PostConstruct;
//import javax.ejb.EJB;
//import javax.faces.context.FacesContext;
//import javax.faces.view.ViewScoped;
//import javax.inject.Inject;
//import javax.inject.Named;
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.*;
//import java.util.logging.Level;
//
//@ViewScoped
//@Named
//public class PotpisConsentCofController extends WebCommons<ConsentCofDto, Integer> {
//    protected static final long serialVersionUID = 1L;
//
//    @EJB
//    protected NalogTppBean nalogBean;
//    @Inject
//    protected ConsentCofDao consentDao;
//    @Inject protected WebLoginBean loginBean;
//    @EJB protected AuthenticationUtilBean utilBean;
//    @Inject private AuthenticationUtilBean authUtilBean;
//    @Inject protected AuthorizationDao authDao;
//    protected Boolean isPotpis = Boolean.TRUE;
//    protected String base24SignValue = null;
//    protected List<ConsentCofDto> odabraniConsenti = new ArrayList<ConsentCofDto>();
//    protected List<ConsentCofDto> nepotpisaniConsenti = new ArrayList<ConsentCofDto>();
//    protected List<ConsentCofDto> zaPovlacenje = new ArrayList<ConsentCofDto>();
//    protected List<ConsentCofDto> potpisaniConsenti = new ArrayList<ConsentCofDto>();
//    protected String signFormVisible = "false";
//    protected String redirectVisible = "false";
//    protected Map<String, BigDecimal> infoOznacenihNaloga;
//    protected Integer brojOznacenihNaloga;
//    protected Boolean oznaciSve;
//    protected String appli2;
//    protected String response;
//    protected String pkiPotpis;
//    //Varijable za potpis
//    protected int brojacSmart = 0;
//    protected boolean stopSmartCheck;
//    protected boolean naloziObradjeni;
//    protected boolean showPotpisaniNalozi;
//    protected String packedBlock;
//    protected long endSmartCheck;
//    protected String hashPotpis;
//    protected Boolean potpisSmart = Boolean.FALSE;
//    protected Boolean potpisPki = Boolean.FALSE;
//    protected Boolean potpisToken = Boolean.FALSE;
//    protected Boolean potpisUnsecured = Boolean.FALSE;
//    protected WebSmartPotpisDto filterPotpisCheck;
//    protected ConsentCofDto nalog;
//    protected Boolean potpisi = Boolean.FALSE;
//    protected Boolean povuci = Boolean.FALSE;
//    protected String onemoguciAkcija = "true";
//    protected String refTppNaloga = null;
//    protected String redirectLink = null;
//    private String signStatement;
//    private String odjava;
//    @Inject
//    IBSecurityConstantsBean constBean;
//    @Inject private WebSmartPotpisDao smartDao;
//
//
//
//
//    public PotpisConsentCofController() {
//        super(ConsentCofDto.class);
//        String[] params = AuthenticationUtils.getRequestParameter(FacesContext.getCurrentInstance(), "id");
//        if(params != null){
//            refTppNaloga = params[0];
//        }
//    }
//
//    @PostConstruct
//    public void init() {
//        if(loginBean.getPrijava() != null && loginBean.getPrijava() instanceof WebPrijavaSmartToken){
//            try {
//                utilBean.updateExpiredSmartPotpis(IBSmartPotpis.TIP_CONSENT_COF);
//            } catch (AbitRuleException e) {
//                LoggerFactory.getLogger(PotpisNalogMultiController.class.getName()).error("Update Expired SmartPotpis: "+e.getMessage());
//            }
//        }
//
//        //Odredimo akciju za potpis
//        if(loginBean.getPrijava() instanceof WebPrijavaSmartToken){
//            potpisSmart = Boolean.TRUE;
//        }else if(loginBean.getPrijava() instanceof WebPrijavaPKIKartica){
//            potpisPki = Boolean.TRUE;
//        }else if(loginBean.getPrijava() instanceof WebPrijavaUnsecured){
//            potpisUnsecured = Boolean.TRUE;
//        }else{
//            potpisToken = Boolean.TRUE;
//        }
//        try {
//            //Dohvatimo ovlastenja za prijavljenog korisnika
//            if(isPotpis) {
//                initConsent();
//            }else {
//                initPovlacenje();
//            }
//        }catch(AbitRuleException ex){
//            showError(ex);
//        }
//        if(isPotpis) potpisi = Boolean.TRUE; else povuci = Boolean.TRUE;
//    }
//
//    public void initConsent() throws AbitRuleException {
//        if(refTppNaloga != null && !StringUtils.isEmpty(refTppNaloga)){
//            List<AuthorizationDto> authorizationDto = authDao.getAuthorizationsByLink(refTppNaloga, Psd2Constants.AuthorizationObjectType.CONSENT_COF_AUTHORUZATION.getValue());
//            if(authorizationDto != null) {
//                List<Integer> tppSifraList = new ArrayList<>();
//                Map<Integer, AuthorizationDto> mapAuth = new HashMap<>();
//                for(AuthorizationDto authDto : authorizationDto){
//                    if(!Arrays.asList(AuthorizationDto.SCA_STATUSI_ZA_POTPIS).contains(authDto.getScastAuth()))continue;
//                    tppSifraList.add(authDto.getObjectAuth());
//                    mapAuth.put(authDto.getObjectAuth(), authDto);
//                }
//                if(mapAuth != null && mapAuth.size() > 0) {
//                    nepotpisaniConsenti = new ArrayList<>();
//                    List<ConsentCofDto> consentList = consentDao.getConsentCofBySifraList(tppSifraList);
//                    //Provjera prava - za gradjane treba rijesiti da se automatski kreiraju prava ili ne provjeravati
//                    if(utilBean.mozePotpisConsentCof(IBUsluga.USLUGA_POTPIS_CONSENTA, loginBean.getIbKorisnik().getSifra(), consentList)) {
//                        if (consentList != null) {
//                            for (ConsentCofDto consent : consentList) {
//                                consent.setAuthorization(mapAuth.get(consent.getSifra()));
//                                consent.setSelected(true);
//                                redirectLink = redirectLink == null ? consent.getTppRedirectUri() : redirectLink;
//                                if(consent.getAuthorization().isExpired(LocalDateTime.now())){
//                                    showError(InternationalizationUtil.getLocalMessage("potpisPrivoleError_authExpired", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//                                }else {
//                                    nepotpisaniConsenti.add(consent);
//                                    signStatement = formSignStatement(consent.getConsentAccountCofDto().getIbanAcc(),consent.getTppName(), consent.getTppIdCon());
//                                }
//                            }
//                        } else {
//                            showError(InternationalizationUtil.getLocalMessage("potpisPrivoleError_nePostoji", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//                        }
//                    }else{
//                        showError(InternationalizationUtil.getLocalMessage("potpisPrivoleError_nedostatnaPrava", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//                    }
//                }else{
//                    showError(InternationalizationUtil.getLocalMessage("potpisPrivoleError_nePostojiAutorizacija", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//                }
//            }
//        }else{
//            showError(InternationalizationUtil.getLocalMessage("potpisPrivoleError_idNePostoji",GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//        }
//        oznaciSve = false;
//        calculateSHA1();
//        potpisaniConsenti = new ArrayList<>();
//    }
//
//    private String formSignStatement (String iban, String tppName, String tppId) {
//
//        //budući da je tppName obavezan samo kroz certifikat... ako nema tppName koristimo tppId
//        String tppNameorIdVar;
//        if (!StringUtils.isBlank(tppName)){
//            tppNameorIdVar = tppName;
//        }else {
//            tppNameorIdVar = tppId;
//        }
//        String statement = "<b>Suglasnost za davanje informacije o raspoloživosti sredstava na Računu</b> <br />" +
//                "<br />" +
//                "Ovim putem dajem izričitu suglasnost Partner banci d.d. Zagreb (u nastavku: Banka) kao pružatelju platne usluge vođenja Računa " +"<b>"+iban+"</b>"+" (u nastavku: Račun) "+
//                "da odgovori(a) na upit(e) "+ tppNameorIdVar+" kao pružatelju platnih usluga koji izdaje <br /> platni instrument na temelju kartice o raspoloživosti sredstava na Računu.<br />" +
//                "Razumijem i suglasan/suglasna sam da Banka odgovara na upit o raspoloživosti sredstava na Računu sve do opoziva ili prestanka ove suglasnosti.<br />" +
//                "<br />" +
//                "Upoznat/upoznata sam i razumijem:<br />" +
//                "• da suglasnost mogu opozvati putem P@RTNERneta ili putem "+ tppNameorIdVar +";<br />" +
//                "• uvjete prestanka važenja ove suglasnosti, kada Račun prestaje biti dostupan online, kao i druge razloga propisane Općim uvjetima Banke;<br />" +
//                "• uvjete pod kojima Banka neće omogućiti pristup/ne odgovara na upit o raspoloživosti sredstava na Računu "+ tppNameorIdVar +" koji su propisani Općim uvjetima;<br />" +
//                "• odredbe Općih uvjeta transakcijskog i depozitnog poslovanja fizičkih osoba odnosno Općih uvjeta transakcijskog poslovanja poslovnih subjekata Partner banke d.d. Zagreb u kojima su detaljno dane informacije o ovoj usluzi.<br /> <br />";
//        return statement;
//    }
//
//    public void initPovlacenje() {
//        //Tu dohvatimo consent id = broj u autorizaciji
//        if(refTppNaloga != null && !StringUtils.isEmpty(refTppNaloga)){
//            AuthorizationDto authorizationDto = authDao.getAuthorizationByLink(refTppNaloga, Psd2Constants.AuthorizationObjectType.CONSENT_COF_AUTHORUZATION.getValue());
//            if(authorizationDto != null)  {
//                if(authorizationDto.getAutTypeAuth().compareTo(Psd2Constants.AuthorizationType.CANCELLED.getValue()) == 0) {
//                    zaPovlacenje = new ArrayList<>();
//                    ConsentCofDto consent = null;
//                    try{
//                        consent = consentDao.getConsentCofBySifra(authorizationDto.getObjectAuth());
//                        if (consent != null) {
//                            consent.setAuthorization(authorizationDto);
//                            createDto = consent;
//                            zaPovlacenje.add(createDto);
//                        } else {
//                            showError(InternationalizationUtil.getLocalMessage("potpisPrivoleError_nePostoji", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//                        }
//                    }catch(AbitRuleException ex){
//                        consent = null;
//                    }
//                }else{
//                    showError(InternationalizationUtil.getLocalMessage("potpisPrivoleError_nePostojiAutorizacija", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//                }
//            }
//        }else{
//            showError(InternationalizationUtil.getLocalMessage("potpisPrivoleError_idNePostoji",GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//        }
//    }
//
//
//
//    public void ukljuciAkcija() {
//        //potpis
//        if (!potpisi) potpisi = true;
//        else potpisi = false;
//        //povlacenje
//        if (!povuci) povuci = true;
//        else povuci = false;
//    }
//
//    public void updateNalog() {
//        createDto = findDtoByPrimaryKey(Integer.toString(createDto.getSifra()));
//        ukljuciAkcija();
//        provjeriAkcija();
//        dohvatiAkcijeNaloga();
////        createDto.setNalogAkcije(listaAkcijaNaloga);
////		createDto.resolveStatusAndInfo(false);
////		RequestContext requestContext = RequestContext.getCurrentInstance();
////		requestContext.execute("javascript:redirect();");
//    }
//
//    protected void provjeriAkcija(){
////		try {
////			if (createDto != null && createDto.getSifra() != null){
////				if (createDto.getSifraKnjiznogNaloga() != null) onemoguciAkcija = "true";
////				else  onemoguciAkcija= "false";
////			}
////		} catch (Exception e) {
////			onemoguciAkcija = "true";
////		}
//
////		try {
////			if (createDto != null && createDto.getSifra() != null){
////				if (createDto.getSifraKnjiznogNaloga() != null) potpisi = false;
////				else  potpisi = true;
////			}
////		} catch (Exception e) {
////			potpisi = false;
////		}
//    }
//
////	protected void napuniListe() {
////		setListaIban(utilBean.getListaIbanFromOvlastenja(loginBean.getOvlastenja(), null, true, true));
////		for (SelectItem iban : getListaIban()){
////			if (iban.getValue() == null || iban.getValue().toString().length() < 10)
////				continue;
////			String naziv;
////            Integer sifraProizvoda = null;
////			String partija = iban.getValue().toString().substring(iban.getValue().toString().length()-10, iban.getValue().toString().length());
////            try {
////                PartijaDto partijaDto = LookupHelper.getICoreDaoProxyRemote().getPartijaDtoByBrojPartije(partija);
////                if (partijaDto != null) sifraProizvoda = partijaDto.getSifraProizvoda();
////            } catch (AbitRuleException e) {
////                e.printStackTrace();
////            }
////            naziv = AuthenticationUtils.resolveNazivRacuna(partija, null, sifraProizvoda, null);
////			if(StringUtils.isNotBlank(naziv)) {
////				if (iban.getLabel().contains("(")) {
////					iban.setLabel(iban.getLabel().substring(0, iban.getLabel().indexOf("(")) + " - " + naziv + " - " + iban.getLabel().substring(iban.getLabel().indexOf("(")));
////				} else {
////					iban.setLabel(iban.getLabel() + " - " + naziv);
////				}
////			}
////		}
////		listaPlatiteljPodaci = nalogDao.getListaPlatiteljPodaciFromOvlastenja(loginBean.getOvlastenja(), IBKorisnikRacunUsluga.UNOS_NALOGA);
////        if (listaDrzavaPrimatelj == null || listaDrzavaPrimatelj.isEmpty()) {
////			setListaDrzavaPrimatelj(utilBean.getSelectItemDrzavaLabelDrzava());
////		}
////        if (listaValuta == null || listaValuta.isEmpty()) {
////			setListaValuta(utilBean.getSelectItemValuta(true, false, true));
////			//sort HRK, EUR .......
////			listaValuta = AuthenticationUtils.sortSelectListValutaPlatniNalog(listaValuta);
////		}
////		if(listaSifraNamjene == null || listaSifraNamjene.isEmpty()){
////			setListaSifraNamjene(utilBean.getSelectItemSifraNamjene());
////		}
////		if(listaKategorijaNamjene == null || listaKategorijaNamjene.isEmpty()){
////			setListaKategorijaNamjene(utilBean.getSelectItemKategorijaNamjene());
////		}
////        if (listSifraOznakePosla == null || listSifraOznakePosla.isEmpty()) {
////            setListSifraOznakePosla(utilBean.getSelectItemSifraOznakePosla());
////        }
////		if (listaTroskovnaOpcija == null || listaTroskovnaOpcija.isEmpty()) {
////            listaTroskovnaOpcija = new ArrayList<SelectItem>();
////            listaTroskovnaOpcija.add(new SelectItem("", InternationalizationUtil.getLocalMessage("odaberite", GenericBassxConstants.Ib.MESSAGE_BUNDLE)));
////            listaTroskovnaOpcija.add(new SelectItem(GenericBassxConstants.PlatniPromet.DEVIZNI_NALOG_TROSAK_INO_BANKE_OUR, GenericBassxConstants.PlatniPromet.DEVIZNI_NALOG_TROSAK_INO_BANKE_OUR));
////            listaTroskovnaOpcija.add(new SelectItem(GenericBassxConstants.PlatniPromet.DEVIZNI_NALOG_TROSAK_INO_BANKE_SHA, GenericBassxConstants.PlatniPromet.DEVIZNI_NALOG_TROSAK_INO_BANKE_SHA));
////        }
////    }
//
////    public void raspolozivo(){
////		createDto.setSifraPartijeZaduzenja(AuthenticationUtils.resolvePartijaDtoFromIban(createDto.getIbanZaduzenja(), loginBean.getOvlastenja()).getSifra());
////		if(createDto.getSifraPartijeZaduzenja() != null){
////			trenutnoRaspolozivo = utilBean.resolveRaspolozivo(createDto.getSifraPartijeZaduzenja(), createDto.getSifraValute() != null ? createDto.getSifraValute() : Bassx2Constants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY);
////		}
////	}
//
//    public void getIcon (){
////		if((potpisi && createDto.getMozePotpis()) || (povuci)) signFormVisible = "true";
////		else signFormVisible = "false";
//        signFormVisible = "true";
//    }
//
//
//    protected void napuniDatasource() {
//        //hvatanje naloga za pregled iz prethodne forme i popunjavanje createDto
//        if(nalog != null){
//            createDto = nalog;
////            postaviPodatkePrimateljaIzNalogaPredloska();
//            dohvatiAkcijeNaloga();
//        }
//        //upozorenje ako je datum valute subota, nedjelja, praznik ovisno o kanalu
//    }
//
//
//    public String pregledajNalog(String key) {
//        try{
//            // otvaranje forme za unos deviznog priljeva
//            putValueInFlash("inputFormVisible", GenericController.divFormVisible);
//            // punjenje deviznog naloga DTO
//
//            putValueInFlash("nalog", findDtoByPrimaryKey(key));
//            // redirect na devizni priljev
//            return "/app/platniNalog.xhtml?faces-redirect=true";
//        } catch (Exception e) {
//            showError(InternationalizationUtil.getLocalMessage("controller_ibPredlozakIzmijeniPredlozakError", new String[]{e.getMessage()}, GenericBassxConstants.Ib.MESSAGE_BUNDLE));
//            return "";
//        }
//    }
//
//    public String redirectNalog() {
////        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
////        session.invalidate();
//        return "";
////		return getCreateDto().getTppRedirectUri() != null ? getCreateDto().getTppRedirectUri() : "";
//    }
//
//    public String obrisiNalog() {
//        /*
//        ConsentAisDto webNalog = nalogDao.findByPrimaryKey(createDto.getSifra());
//        try {
//            nalogBean.obrisiNalog(webNalog);
//        } catch (AbitRuleException are) {
//            showError(are);
//            //showError(InternationalizationUtil.getLocalMessage("ibPotpisNalogaBrisiNalogError", new String[]{e.getMessage()}, GenericBassxConstants.Ib.MESSAGE_BUNDLE));
//            return "";
//        }
//        */
//        putValueInFlash("nalog", null);
//        return "/app/platniNalog.xhtml?faces-redirect=true";
//    }
//
//    protected void dohvatiAkcijeNaloga() {
////		WebNalogAkcijaDto akcija = new WebNalogAkcijaDto();
////		if(createDto.getSifra() != null){
////			akcija.setSifraNaloga(createDto.getSifra());
////			listaAkcijaNaloga = ibNalogAkcijaDao.filterList(akcija);
////		}
//    }
//
//    public String ocistiFormuPotpisa() {
//
//        setAppli2(null);
//        setResponse(null);
//        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
//        try {
//            utilBean.logirajPrijavuOdjavu(loginBean.getPrijava(), IBPrijavaOdjava.VRSTA_ODJAVA, true);
//        }catch(AbitRuleException ex){
//            java.util.logging.log.getLogger(Psd2MenuController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return "/login.xhtml?odjava=true&faces-redirect=true";
//    }
//
//
//
//    @Override
//    public GenericMinDao<?, ConsentCofDto, Integer> getGenericDao() {
//        return null;
//    }
//
//    @Override
//    public ConsentCofDto findDtoByPrimaryKey(String id) {
//        ConsentCofDto dto = null;
//        try{
//            dto = consentDao.getConsentCofBySifra(Integer.parseInt(id));
//        }catch(AbitRuleException ex){
//            dto = null;
//        }
//        if(dto != null) dto.setAuthorization(authDao.getAuthorizationByObjectId(dto.getSifra(), Psd2Constants.AuthorizationObjectType.CONSENT_AUTHORIZATION.getValue(), null, null, null).get(0));
//        return dto;
//    }
//
//    @Override
//    public String getCustomReportName() {
//        return "NalogPlatniReport_2";
//    }
//
//    @Override
//    public String getReportOutputType() {
//        return "pdf";
//    }
//
//
//    public ConsentCofDto getNalog() {
//        return nalog;
//    }
//
//    public void setNalog(ConsentCofDto nalog) {
//        this.nalog = nalog;
//    }
//
//    public String getBase24SignValue() {
//        return base24SignValue;
//    }
//
//    public void setBase24SignValue(String base24SignValue) {
//        this.base24SignValue = base24SignValue;
//    }
//    public List<ConsentCofDto> getnepotpisaniConsenti() {
//        return nepotpisaniConsenti;
//    }
//
//    public void setnepotpisaniConsenti(List<ConsentCofDto> nepotpisaniConsenti) {
//        this.nepotpisaniConsenti = nepotpisaniConsenti;
//    }
//
//    public String getSignFormVisible() {
//        return signFormVisible;
//    }
//
//    public void setSignFormVisible(String signFormVisible) {
//        this.signFormVisible = signFormVisible;
//    }
//
//    public Boolean getShowChallengeResponse(){
//        Boolean res = Boolean.FALSE;
//        res = loginBean.getPrijava() instanceof WebPrijavaSxsToken || loginBean.getPrijava() instanceof WebPrijavaSoftToken ? Boolean.TRUE : Boolean.FALSE;
//        return res;
//    }
//
//
//    public Boolean getPovuci() {
//        return povuci;
//    }
//
//    public void setPovuci(Boolean povuci) {
//        this.povuci = povuci;
//    }
//
//    public Boolean getPotpisi() {
//        return potpisi;
//    }
//
//    public void setPotpisi(Boolean potpisi) {
//        this.potpisi = potpisi;
//    }
//
//    public String getOnemoguciAkcija() {
//        return onemoguciAkcija;
//    }
//
//    public void setOnemoguciAkcija(String onemoguciAkcija) {
//        this.onemoguciAkcija = onemoguciAkcija;
//    }
//
//
//    public Integer getBrojOznacenihNaloga() {
//        return brojOznacenihNaloga;
//    }
//
//    public void setBrojOznacenihNaloga(Integer brojOznacenihNaloga) {
//        this.brojOznacenihNaloga = brojOznacenihNaloga;
//    }
//
//    public Boolean getOznaciSve() {
//        return oznaciSve;
//    }
//
//    public void setOznaciSve(Boolean oznaciSve) {
//        this.oznaciSve = oznaciSve;
//    }
//
//    public String getAppli2() {
//        return appli2;
//    }
//
//    public void setAppli2(String appli2) {
//        this.appli2 = appli2;
//    }
//
//    public String getResponse() {
//        return response;
//    }
//
//    public void setResponse(String response) {
//        this.response = response;
//    }
//    public Boolean getPotpisPki() {
//        return potpisPki;
//    }
//
//    public void setPotpisPki(Boolean potpisPki) {
//        this.potpisPki = potpisPki;
//    }
//
//    public Boolean getPotpisSmart() {
//        return potpisSmart;
//    }
//
//    public void setPotpisSmart(Boolean potpisSmart) {
//        this.potpisSmart = potpisSmart;
//    }
//
//    public Boolean getPotpisToken() {
//        return potpisToken;
//    }
//
//    public void setPotpisToken(Boolean potpisToken) {
//        this.potpisToken = potpisToken;
//    }
//
//    public boolean getShowPotpisaniNalozi() {
//        return potpisaniConsenti != null && potpisaniConsenti.size() > 0;
//    }
//
//    public void setShowPotpisaniNalozi(boolean showPotpisaniNalozi) {
//        this.showPotpisaniNalozi = showPotpisaniNalozi;
//    }
//
//    public Boolean getPotpisUnsecured() {
//        return potpisUnsecured;
//    }
//
//    public void setPotpisUnsecured(Boolean potpisUnsecured) {
//        this.potpisUnsecured = potpisUnsecured;
//    }
//
//    public Boolean getIsPotpis() {
//        return isPotpis;
//    }
//
//    public void setIsPotpis(Boolean isPotpis) {
//        this.isPotpis = isPotpis;
//    }
//
//    public List<ConsentCofDto> getZaPovlacenje() {
//        return zaPovlacenje;
//    }
//
//    public void setZaPovlacenje(List<ConsentCofDto> zaPovlacenje) {
//        this.zaPovlacenje = zaPovlacenje;
//    }
//
//    public Map<WebPrijava.UTILITY_OBJECT_ID, Object> resolveMapUtilData(){
//        Map<WebPrijava.UTILITY_OBJECT_ID, Object> res = new HashMap<>();
//        res.put(WebPrijava.UTILITY_OBJECT_ID.MAC_INPUT, new WebDigitalSignatureFootprint(odabraniConsenti).getFootprint());
//        res.put(WebPrijava.UTILITY_OBJECT_ID.CHALLENGE, appli2);
//        res.put(WebPrijava.UTILITY_OBJECT_ID.RESPONSE, response);
//        res.put(WebPrijava.UTILITY_OBJECT_ID.SIGN_ID, RandomStringUtils.randomAlphanumeric(16));
//        res.put(WebPrijava.UTILITY_OBJECT_ID.LOGIN_ID, loginBean.getIbKorisnik().getSifra().toString());
//        res.put(WebPrijava.UTILITY_OBJECT_ID.IB_KORISNIK_SIFRA, loginBean.getIbKorisnik().getSifra());
//        res.put(WebPrijava.UTILITY_OBJECT_ID.UTIL_BEAN, utilBean);
//        res.put(WebPrijava.UTILITY_OBJECT_ID.AUTH_UTIL_BEAN, authUtilBean);
//        res.put(WebPrijava.UTILITY_OBJECT_ID.IB_PRIJAVA, loginBean.getPrijava());
//        return res;
//    }
//
//    public void resolveodabraniConsenti() throws AbitRuleException{
//        odabraniConsenti = new ArrayList<ConsentCofDto>();
//        potpisaniConsenti = new ArrayList<ConsentCofDto>();
//        if(nepotpisaniConsenti != null) {
//            for (ConsentCofDto nalog : nepotpisaniConsenti) {
//                if (nalog != null && nalog.isSelected()) {
//                    odabraniConsenti.add(nalog);
//                }
//            }
//        }
//        if(zaPovlacenje != null) {
//            for (ConsentCofDto nalog : zaPovlacenje) {
//                if (nalog != null && nalog.isSelected()) {
//                    odabraniConsenti.add(nalog);
//                }
//            }
//        }
//        if (odabraniConsenti == null || odabraniConsenti.size() == 0) {
//            throw new AbitRuleException("Nije odabrana nijedna privola za potpis.");
//        }
//    }
//
//    public void obradiConsente(String signId){
//        try {
//            //Ovdje cemo ugraditi provjeru datuma valute BB naloga
//            String poruka = null; //nalogBean.checkDatumValuteBatchBooking(odabraniConsenti, loginBean.getIbGlobals());
//            if(poruka != null){
//                Bassx2Message.addMessage(Bassx2Message.MessageType.INFO, poruka);
//            }else {
//                odabraniConsenti = nalogBean.signConsentiCof(odabraniConsenti, loginBean.getIbGlobals(), loginBean.getIbKorisnik(), signId);
//
//                //Provjerimo greske i provjerimo broj knjizenih autorizacija
//                if (odabraniConsenti != null) {
//                    int brojOk = 0;
//                    for (ConsentCofDto nal : odabraniConsenti) {
//                        if (nal.getGreska() != null && !nal.getGreska().equals("")) {
//                            showError(InternationalizationUtil.getLocalMessage("potpisConsentGreska", GenericBassxConstants.Psd2.MESSAGE_BUNDLE) + " " + nal.getSifra().intValue() + ": " + nal.getGreska());
//                            nal.setSelected(true);
//                        } else {
//                            brojOk++;
//                            nal.setSelected(false);
//                        }
//                    }
//
//                    setAppli2(null);
//                    setResponse(null);
//                    setBrojOznacenihNaloga(0);
//                    setOznaciSve(false);
//                    calculateSHA1();
//                    if (odabraniConsenti.size() > 0) {
//                        String porukaTmp = odabraniConsenti.size() == 1 ? InternationalizationUtil.getLocalMessage("potpisConsentOkInfo", GenericBassxConstants.Psd2.MESSAGE_BUNDLE) : InternationalizationUtil.getLocalMessage("potpisViseOkInfo", GenericBassxConstants.Psd2.MESSAGE_BUNDLE);
//                        Bassx2Message.addMessage(Bassx2Message.MessageType.INFO, porukaTmp);
//                        for (ConsentCofDto wn : odabraniConsenti) {
////							if (wn != null && wn.getWarningMessages() != null && !wn.getWarningMessages().isEmpty()) {
////								for (String m : wn.getWarningMessages()) {
////									showWarnning(m);
////								}
////							}
//                        }
//                        if(nepotpisaniConsenti != null && brojOk == nepotpisaniConsenti.size()) nepotpisaniConsenti = new ArrayList<>();
//                    }
//                }
//            }
//        } catch (AbitRuleException e) {
//            showError(InternationalizationUtil.getLocalMessage("potpisPlatnog_error", new String[]{e.getMessage()}, GenericBassxConstants.Ib.MESSAGE_BUNDLE));
//        }
//        redirectVisible = "true";
//    }
//
//    public void povuciConsente(String signId){
//        try {
//            //Ovdje cemo ugraditi provjeru datuma valute BB naloga
//            String poruka = null; //nalogBean.checkDatumValuteBatchBooking(odabraniConsenti, loginBean.getIbGlobals());
//            if(poruka != null){
//                Bassx2Message.addMessage(Bassx2Message.MessageType.INFO, poruka);
//            }else {
//                odabraniConsenti = nalogBean.povuciConsenteCof(odabraniConsenti, loginBean.getIbGlobals(), loginBean.getIbKorisnik(), signId);
//
//                //Provjerimo greske i provjerimo broj knjizenih autorizacija
//                if (odabraniConsenti != null) {
//                    int brojOk = 0;
//                    for (ConsentCofDto nal : odabraniConsenti) {
//                        if (nal.getGreska() != null && !nal.getGreska().equals("")) {
//                            showError(InternationalizationUtil.getLocalMessage("potpisGreska", GenericBassxConstants.Psd2.MESSAGE_BUNDLE) + " " + nal.getSifra().intValue() + ": " + nal.getGreska());
//                            nal.setSelected(true);
//                        } else {
//                            brojOk++;
//                            nal.setSelected(false);
//                        }
//                    }
//
//                    setAppli2(null);
//                    setResponse(null);
//                    setBrojOznacenihNaloga(0);
//                    setOznaciSve(false);
//                    calculateSHA1();
//                    if (odabraniConsenti.size() > 0) {
//                        Bassx2Message.addMessage(Bassx2Message.MessageType.INFO, InternationalizationUtil.getLocalMessage("povlacenjeOkInfo", new Object[]{brojOk, odabraniConsenti.size()}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//                    }
//                    if(zaPovlacenje != null && brojOk == zaPovlacenje.size()) zaPovlacenje = new ArrayList<>();
//                }
//            }
//        } catch (AbitRuleException e) {
//        }
//        redirectVisible = "true";
//    }
//
//    public void calculateSHA1(){
//        if(nepotpisaniConsenti != null || zaPovlacenje != null) {
//            infoOznacenihNaloga = new HashMap<>();
//            List<ConsentCofDto> oznaceni = new ArrayList<>();
//            brojOznacenihNaloga = 0;
//            pkiPotpis = "";
//            response = "";
//            for (ConsentCofDto nal : nepotpisaniConsenti) {
//                if (nal.isSelected()) {
//                    brojOznacenihNaloga++;
//                    oznaceni.add(nal);
//                    if(loginBean.getPrijava() instanceof WebPrijavaPKIKartica) pkiPotpis += new WebDigitalSignatureFootprint(nal).getFootprint();
//                }
//            }
//
//            for (ConsentCofDto nal : zaPovlacenje) {
//                if (nal.isSelected()) {
//                    brojOznacenihNaloga++;
//                    oznaceni.add(nal);
//                    if(loginBean.getPrijava() instanceof WebPrijavaPKIKartica) pkiPotpis += new WebDigitalSignatureFootprint(nal).getFootprint();
//                }
//            }
//
//            //Idemo kreirati info oznacenih naloga
//            infoOznacenihNaloga = new HashMap<>();
//            if(infoOznacenihNaloga != null && infoOznacenihNaloga.size() == 0){
//                infoOznacenihNaloga.put("",BigDecimal.ZERO);
//            }
//            WebDigitalSignatureFootprint footprint = new WebDigitalSignatureFootprint(oznaceni);
//            try {
//                appli2 = footprint.getFootprint() != null && !footprint.getFootprint().equals("") ? footprint.calculateHash(new String[]{footprint.getFootprint()}) : "";
//            }catch(AbitRuleException ex){
//                showError(ex);
//            }
//        }
//
//    }
//
//    public void potpisPki(){
//        try {
//            resolveodabraniConsenti();
//            Map<WebPrijava.UTILITY_OBJECT_ID, Object> mapObjects = resolveMapUtilData();
////TODO			odabraniConsenti = loginBean.getPrijava().potpisiNaloge(odabraniConsenti, mapObjects);
//            obradiConsente((String) mapObjects.get(WebPrijava.UTILITY_OBJECT_ID.SIGN_ID));
//        }catch(AbitRuleException ex){
//            showError(InternationalizationUtil.getLocalMessage("potpisPlatnog_error", new String[]{ex.getMessage()}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//        }
//    }
//
//    public void potpisPkiNalog(ConsentCofDto webNalogDto){
////        try {
//        odabraniConsenti = new ArrayList<ConsentCofDto>();
//        odabraniConsenti.add(webNalogDto);
//        Map<WebPrijava.UTILITY_OBJECT_ID, Object> mapObjects = resolveMapUtilData();
////TODO            odabraniConsenti = loginBean.getPrijava().potpisiNaloge(odabraniConsenti, mapObjects);
//        obradiConsente((String) mapObjects.get(WebPrijava.UTILITY_OBJECT_ID.SIGN_ID));
////        RequestContext requestContext = RequestContext.getCurrentInstance();
////        if (odabraniConsenti != null && odabraniConsenti.size() == 1 && odabraniConsenti.get(0).getGreska() == null && requestContext != null) requestContext.execute("javascript:update();");
//        if (odabraniConsenti != null && odabraniConsenti.size() == 1 && odabraniConsenti.get(0).getGreska() == null && PrimeFaces.current() != null) PrimeFaces.current().executeScript("javascript:update();");
////        }catch(AbitRuleException ex){
////            showError(InternationalizationUtil.getLocalMessage("potpisPlatnog_error", new String[]{ex.getMessage()}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
////        }
//    }
//
//    public void potpisToken(){
//        try {
//            resolveodabraniConsenti();
//            Map<WebPrijava.UTILITY_OBJECT_ID, Object> mapObjects = resolveMapUtilData();
////TODO			odabraniConsenti = loginBean.getPrijava().potpisiNaloge(odabraniConsenti, mapObjects);
//            obradiConsente((String) mapObjects.get(WebPrijava.UTILITY_OBJECT_ID.SIGN_ID));
//        }catch(AbitRuleException ex){
//            showError(InternationalizationUtil.getLocalMessage("potpisPlatnog_error", new String[]{ex.getMessage()}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//        }
//    }
//    //Dio za potpis
//    public void potpisSmart(){
//        try {
//            naloziObradjeni = false;
////            RequestContext requestContext = RequestContext.getCurrentInstance();
//            resolveodabraniConsenti();
//            String sesionId = RandomStringUtils.randomAlphanumeric(16);
//            hashPotpis = WebDigitalSignatureFootprint.getBase24Sign(new WebDigitalSignatureFootprint(odabraniConsenti, new Date(), true).getFootprint());
//            endSmartCheck = System.currentTimeMillis() + ((constBean.getSmartExpiriyTime().intValue() + 15) * 1000); //15 zbog otvaranja smartis aplikacije
//            nalogBean.addSmartCheck(odabraniConsenti, sesionId, hashPotpis);
//            setBase24SignValue("pabasign://sign/" + new WebDigitalSignatureFootprint(odabraniConsenti, sesionId, loginBean.getIbKorisnik().getSifra().toString(), WebDigitalSignatureFootprint.TIP_CONSENT, hashPotpis).getBase24Sign());
//            stopSmartCheck = Boolean.FALSE;
//            //filter za check
//            filterPotpisCheck = new WebSmartPotpisDto();
//            filterPotpisCheck.setSifraIbKorisnik(loginBean.getIbKorisnik().getSifra());
//            //filterPotpisCheck.setHash(hashPotpis);
//            filterPotpisCheck.setSessionId(sesionId);
//            filterPotpisCheck.setStatus(IBSmartPotpis.STATUS_POTPISAN);
//            //end filter
////            requestContext.execute("javascript:pabasign('"+getBase24SignValue()+"');");
//            PrimeFaces.current().executeScript("javascript:pabasign('"+getBase24SignValue()+"');");
//        }catch(AbitRuleException ex){
//            showError(InternationalizationUtil.getLocalMessage("potpisPlatnog_error", new String[]{ex.getMessage()}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//        }
//    }
//
//    public void potpisSmartNalog(ConsentCofDto webNalogDto){
//        try {
//            naloziObradjeni = false;
//            odabraniConsenti = new ArrayList<ConsentCofDto>();
//            odabraniConsenti.add(webNalogDto);
////            RequestContext requestContext = RequestContext.getCurrentInstance();
//            String sesionId = RandomStringUtils.randomAlphanumeric(16);
//            hashPotpis = WebDigitalSignatureFootprint.getBase24Sign(new WebDigitalSignatureFootprint(odabraniConsenti, new Date(), true).getFootprint());
//            endSmartCheck = System.currentTimeMillis() + ((constBean.getSmartExpiriyTime().intValue() + 15) * 1000); //15 zbog otvaranja smartis aplikacije
//            nalogBean.addSmartCheck(odabraniConsenti, sesionId, hashPotpis);
//            setBase24SignValue("pabasign://sign/" + new WebDigitalSignatureFootprint(odabraniConsenti, sesionId, loginBean.getIbKorisnik().getSifra().toString(), WebDigitalSignatureFootprint.TIP_CONSENT, hashPotpis).getBase24Sign());
//            stopSmartCheck = Boolean.FALSE;
//            //filter za check
//            filterPotpisCheck = new WebSmartPotpisDto();
//            filterPotpisCheck.setSifraIbKorisnik(loginBean.getIbKorisnik().getSifra());
//            //filterPotpisCheck.setHash(hashPotpis != null && hashPotpis.length() > 100 ? hashPotpis.substring(0, 100) : hashPotpis);
//            filterPotpisCheck.setSessionId(sesionId);
//            filterPotpisCheck.setStatus(IBSmartPotpis.STATUS_POTPISAN);
//            //end filter
////            requestContext.execute("javascript:pabasign('"+getBase24SignValue()+"');");
//            PrimeFaces.current().executeScript("javascript:pabasign('"+getBase24SignValue()+"');");
//        }catch(AbitRuleException ex){
//            showError(InternationalizationUtil.getLocalMessage("potpisPlatnog_error", new String[]{ex.getMessage()}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//        }
//    }
//
//
//
//    public void potpisTokenNalog(ConsentCofDto webNalogDto, String appli2){
////        try {
//        odabraniConsenti = new ArrayList<ConsentCofDto>();
//        odabraniConsenti.add(webNalogDto);
//        setAppli2(appli2);
//        Map<WebPrijava.UTILITY_OBJECT_ID, Object> mapObjects = resolveMapUtilData();
////TODO            odabraniConsenti = loginBean.getPrijava().potpisiNaloge(odabraniConsenti, mapObjects);
//        obradiConsente((String) mapObjects.get(WebPrijava.UTILITY_OBJECT_ID.SIGN_ID));
////        RequestContext requestContext = RequestContext.getCurrentInstance();
////        if (odabraniConsenti != null && odabraniConsenti.size() == 1 && odabraniConsenti.get(0).getGreska() == null && requestContext != null) requestContext.execute("javascript:update();");
//        if (odabraniConsenti != null && odabraniConsenti.size() == 1 && odabraniConsenti.get(0).getGreska() == null && PrimeFaces.current() != null) PrimeFaces.current().executeScript("javascript:update();");
////        }catch(AbitRuleException ex){
////            showError(InternationalizationUtil.getLocalMessage("potpisPlatnog_error", new String[]{ex.getMessage()}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
////        }
//    }
//
//    public void potpisUnsecured(){
//        try {
//            resolveodabraniConsenti();
//            Map<WebPrijava.UTILITY_OBJECT_ID, Object> mapObjects = resolveMapUtilData();
//            odabraniConsenti = loginBean.getPrijava().potpisiConsenteCof(odabraniConsenti, mapObjects);
//            if(isPotpis) {
//                obradiConsente((String) mapObjects.get(WebPrijava.UTILITY_OBJECT_ID.SIGN_ID));
//            }else{
//                povuciConsente((String) mapObjects.get(WebPrijava.UTILITY_OBJECT_ID.SIGN_ID));
//            }
//        }catch(AbitRuleException ex){
//            showError(InternationalizationUtil.getLocalMessage("potpisPlatnog_error", new String[]{ex.getMessage()}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//        }
//    }
//
//    public void potpisUnsecuredNalog(){
//        try {
//            odabraniConsenti = new ArrayList<>();
//            odabraniConsenti.add(getCreateDto());
//            Map<WebPrijava.UTILITY_OBJECT_ID, Object> mapObjects = resolveMapUtilData();
//            odabraniConsenti = ((WebPrijavaUnsecured)loginBean.getPrijava()).potpisiConsenteCof(odabraniConsenti, mapObjects);
//            if(isPotpis) {
//                obradiConsente((String) mapObjects.get(WebPrijava.UTILITY_OBJECT_ID.SIGN_ID));
//            }else{
//                povuciConsente((String) mapObjects.get(WebPrijava.UTILITY_OBJECT_ID.SIGN_ID));
//            }
//            createDto = odabraniConsenti != null && odabraniConsenti.size() == 1 && odabraniConsenti.get(0).getGreska() != null ? odabraniConsenti.get(0) : createDto;
//            updateNalog();
//        }catch(AbitRuleException ex){
//            showError(InternationalizationUtil.getLocalMessage("potpisPlatnog_error", new String[]{ex.getMessage()}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//        }
//    }
//
//    public void potpisPKI() {
//        try {
//            Map<WebPrijava.UTILITY_OBJECT_ID, Object> mapObjects = resolveMapUtilData();
//            resolveodabraniConsenti();
////TODO			odabraniConsenti = loginBean.getPrijava().potpisiNaloge(odabraniConsenti, mapObjects);
//            obradiConsente((String) mapObjects.get(WebPrijava.UTILITY_OBJECT_ID.SIGN_ID));
//        } catch (AbitRuleException e) {
//            showError(InternationalizationUtil.getLocalMessage("potpisPlatnog_error", new String[]{e.getMessage()}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//        }
//    }
//
//    public void checkSmartPotpisResult(boolean isNalog){
//        LoggerFactory.getLogger(PotpisNalogMultiController.class.getName()).info("Count");
//        //Ovdje provjeravamo da li je je web servis provjerio naloge i postavio ispravan status na smartCheck
//        List<WebSmartPotpisDto> naloziSmart = smartDao.filterList(filterPotpisCheck);
//        if(System.currentTimeMillis() >= endSmartCheck || (naloziSmart != null && naloziSmart.size() == odabraniConsenti.size())){
//            stopSmartCheck = Boolean.TRUE;
//            LoggerFactory.getLogger(this.getClass()).info("stopSmartCheck = TRUE");
//        }
//        if(stopSmartCheck && !naloziObradjeni){
////            RequestContext requestContext = RequestContext.getCurrentInstance();
////            requestContext.execute("javascript:pabasignEnd();");
//            PrimeFaces.current().executeScript("javascript:pabasignEnd();");
//            List<ConsentCofDto> potpisaniNalozi = new ArrayList<>();
//            for(ConsentCofDto nalTmp : odabraniConsenti){
//                nalTmp.setGreska(InternationalizationUtil.getLocalMessage("potpisPlatniNijePotpisan", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//                for(WebSmartPotpisDto smartPot : naloziSmart){
//                    if(smartPot.getSifraNaloga().compareTo(nalTmp.getSifra()) == 0){
//                        nalTmp.setSifraSmartPotpisa(smartPot.getSifra());
//                        nalTmp.setGreska(null);
//                        potpisaniNalozi.add(nalTmp);
//                        break;
//                    }
//                }
//            }
//            if(odabraniConsenti != null && potpisaniNalozi != null && odabraniConsenti.size() != potpisaniNalozi.size()) {
//                showError(InternationalizationUtil.getLocalMessage("potpisUsbTimeexceeded", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//            }else{
//                LoggerFactory.getLogger(this.getClass()).info("checkSmartPotpisResult - obrada naloga");
//                odabraniConsenti = potpisaniNalozi;
//                obradiConsente(filterPotpisCheck.getSessionId());
//                naloziObradjeni = true;
//            }
//        }
//    }
//
//    public void selectAllNone() {
//        if(nepotpisaniConsenti != null && nepotpisaniConsenti.size() > 0){
//            for(ConsentCofDto nalog : nepotpisaniConsenti) {
//                if (nalog.getMozePotpis())
//                    nalog.setSelected(oznaciSve);
//                else
//                    nalog.setSelected(Boolean.FALSE);
//            }
//        }
//        if(zaPovlacenje != null && zaPovlacenje.size() > 0){
//            for(ConsentCofDto nalog : zaPovlacenje) {
//                if (nalog.getMozePotpis())
//                    nalog.setSelected(oznaciSve);
//                else
//                    nalog.setSelected(Boolean.FALSE);
//            }
//        }
//        calculateSHA1();
//        response = "";
//    }
//
//    public Boolean getRenderPotpisPanel() {
//        Boolean res = Boolean.TRUE;
//        res = res && ((nepotpisaniConsenti != null && nepotpisaniConsenti.size() > 0) || (zaPovlacenje != null && zaPovlacenje.size() > 0));
//        return res;
//    }
//
//    public List<ConsentCofDto> getOdabraniConsenti() {
//        return odabraniConsenti;
//    }
//
//    public void setOdabraniConsenti(List<ConsentCofDto> odabraniConsenti) {
//        this.odabraniConsenti = odabraniConsenti;
//    }
//
//    public List<ConsentCofDto> getNepotpisaniConsenti() {
//        return nepotpisaniConsenti;
//    }
//
//    public void setNepotpisaniConsenti(List<ConsentCofDto> nepotpisaniConsenti) {
//        this.nepotpisaniConsenti = nepotpisaniConsenti;
//    }
//
//    public String getPkiPotpis() {
//        return pkiPotpis;
//    }
//
//    public void setPkiPotpis(String pkiPotpis) {
//        this.pkiPotpis = pkiPotpis;
//    }
//
//    public Map<String, BigDecimal> getInfoOznacenihNaloga() {
//        return infoOznacenihNaloga;
//    }
//
//    public void setInfoOznacenihNaloga(Map<String, BigDecimal> infoOznacenihNaloga) {
//        this.infoOznacenihNaloga = infoOznacenihNaloga;
//    }
//
//    public int getBrojacSmart() {
//        return brojacSmart;
//    }
//
//    public void setBrojacSmart(int brojacSmart) {
//        this.brojacSmart = brojacSmart;
//    }
//
//    public boolean isStopSmartCheck() {
//        return stopSmartCheck;
//    }
//
//    public void setStopSmartCheck(boolean stopSmartCheck) {
//        this.stopSmartCheck = stopSmartCheck;
//    }
//
//    public boolean isNaloziObradjeni() {
//        return naloziObradjeni;
//    }
//
//    public void setNaloziObradjeni(boolean naloziObradjeni) {
//        this.naloziObradjeni = naloziObradjeni;
//    }
//
//    public String getPackedBlock() {
//        return packedBlock;
//    }
//
//    public void setPackedBlock(String packedBlock) {
//        this.packedBlock = packedBlock;
//    }
//
//    public String getHashPotpis() {
//        return hashPotpis;
//    }
//
//
//    public void setHashPotpis(String hashPotpis) {
//        this.hashPotpis = hashPotpis;
//    }
//
//    public List<Map.Entry<String, String>> getInfoZaPotpis() {
////		Set<Map.Entry<String, String>> infoSet = new HashSet<>();
//        Map<String, String> infoMap = new HashMap<>();
//        if(infoOznacenihNaloga != null) {
//            infoMap.put(InternationalizationUtil.getLocalMessage("brojOznacenihPrivola", GenericBassxConstants.Psd2.MESSAGE_BUNDLE), brojOznacenihNaloga != null ? brojOznacenihNaloga.toString() : "");
//        }
//        Set<Map.Entry<String,String>> infoSet = infoMap.entrySet();
//        return new ArrayList<Map.Entry<String,String>>(infoSet);
//    }
//
//    public String getRedirectVisible() {
//        return redirectVisible;
//    }
//
//    public void setRedirectVisible(String redirectVisible) {
//        this.redirectVisible = redirectVisible;
//    }
//
//    public String getRedirectLink() {
//        return redirectLink != null ? redirectLink.startsWith("http") ? redirectLink : "http://"+redirectLink : null;
//    }
//
//    public String getSignStatement() {
//        return signStatement;
//    }
//
//    public void setSignStatement(String signStatement) {
//        this.signStatement = signStatement;
//    }
//
//    public void setRedirectLink(String redirectLink) {
//        this.redirectLink = redirectLink;
//    }
//
//    public Boolean getLinkExpired(){
//        Boolean res = Boolean.FALSE;
//        if(isPotpis){
//            if(nepotpisaniConsenti != null){
//                for(ConsentCofDto nalog : nepotpisaniConsenti){
//                    if(nalog.getAuthorization().isExpired(LocalDateTime.now())) {
//                        res = Boolean.TRUE;
//                        break;
//                    }
//                }
//            }
//        }else{
//            if(zaPovlacenje != null){
//                for(ConsentCofDto nalog : zaPovlacenje){
//                    if(nalog.getAuthorization().isExpired(LocalDateTime.now())) {
//                        res = Boolean.TRUE;
//                        break;
//                    }
//                }
//            }
//        }
//        return res;
//    }
//}
