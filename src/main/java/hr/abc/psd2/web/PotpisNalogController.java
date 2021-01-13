//package hr.abc.psd2.web;
//
//import hr.abit.b3.biz.core.GenericMinDao;
//import hr.abit.b3.biz.core.dataObject.IzvjestajZaNalogPlatniDto;
//import hr.abc.psd2.exception.AbitRuleException;
//import hr.abc.psd2.util.*;
//import hr.abit.b3.biz.ib.bean.AuthenticationUtilBean;
//import hr.abit.b3.biz.ib.bean.IBSecurityConstantsBean;
//import hr.abit.b3.biz.ib.dataObject.WebKorisnikRacunDto;
//import hr.abit.b3.biz.ib.dataObject.WebNalogAkcijaDto;
//import hr.abit.b3.biz.ib.dataObject.WebSmartPotpisDto;
//import hr.abit.b3.biz.ib.util.AuthenticationUtils;
//import hr.abit.b3.biz.ib.util.WebDigitalSignatureFootprint;
//import hr.abc.psd2.dto.authorization.AuthorizationDto;
//import hr.abc.psd2.dto.pis.TppNalogPlatniDto;
//import hr.abc.psd2.util.Psd2Constants;
//import hr.abit.b3.biz.security.*;
//import hr.abit.b3.entity.ib.IBSmartPotpis;
//import hr.abit.b3.entity.ib.WebSmartPotpisDao;
//import hr.abit.b3.web.core.GenericController;
//import hr.abit.b3.web.core.WebCommons;
//import org.apache.commons.collections.CollectionUtils;
//import org.apache.commons.lang.RandomStringUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.primefaces.PrimeFaces;
//import org.slf4j.LoggerFactory;
//import hr.abc.psd2.bean.NalogTppBean;
//import hr.abc.psd2.dao.AuthorizationDao;
//import hr.abc.psd2.dao.NalogTppDao;
//
//import javax.annotation.PostConstruct;
//import javax.ejb.EJB;
//import javax.faces.context.FacesContext;
//import javax.faces.model.SelectItem;
//import javax.inject.Inject;
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.*;
//
///**
// *
// * @author kristijan
// */
//public class  PotpisNalogController extends WebCommons<TppNalogPlatniDto, Integer> {
//
//	protected static final long serialVersionUID = 1L;
//
//	@EJB protected NalogTppBean nalogBean;
//	@Inject protected NalogTppDao nalogDao;
//	@Inject protected WebLoginBean loginBean;
//	@EJB protected AuthenticationUtilBean utilBean;
//	@Inject private AuthenticationUtilBean authUtilBean;
//	@Inject protected AuthorizationDao authDao;
//	protected Boolean isPotpis = Boolean.TRUE;
//	protected List<SelectItem> listaHitnost, listaValuta, listaTroskovnaOpcija, listaKategorijaNamjene, listaSifraNamjene, listSifraOznakePosla, listaNalogodavateljSpp, listaDrzavaPrimatelj;
////    @Inject protected WebNalogAkcijaDao ibNalogAkcijaDao;
////	@Inject protected WebPotpisPlatnogNalogaDao ibPotpisNalogaDao;
//	protected String base24SignValue = null;
//	protected List<TppNalogPlatniDto> odabraniNalozi = new ArrayList<TppNalogPlatniDto>();
//	protected List<TppNalogPlatniDto> nepotpisaniNalozi = new ArrayList<TppNalogPlatniDto>();
//	protected List<TppNalogPlatniDto> zaPovlacenje = new ArrayList<TppNalogPlatniDto>();
//	protected List<TppNalogPlatniDto> potpisaniNalozi = new ArrayList<TppNalogPlatniDto>();
//	protected String signFormVisible = "false";
//	protected String predlosciVisible = "false";
//	protected String redirectVisible = "false";
//	protected String odabraniIban;
//	protected String selectedPredlozak = "false";
//	protected String raspolozivoStr;
//	protected BigDecimal trenutnoRaspolozivo = BigDecimal.ZERO;
//	protected String readonlyNazivCreditor;
//	protected String readonlyAdresaCreditor;
//	protected String readonlyGradCreditor;
//	protected String readonlyDrzavaCreditor;
//	protected String readonlySwiftNalogodavateljaKorisnika;
//	protected String readonlyBankaNalogodavateljaKorisnika;
//	protected Map<String, BigDecimal> infoOznacenihNaloga;
//	protected List<SelectItem> listaDatoteka;
//	protected Integer odabranaDatoteka = null;
//	protected Integer brojOznacenihNaloga;
//	protected BigDecimal sumaOznacenihNaloga;
//	protected BigDecimal sumaOznacenoNaknada;
//	protected Boolean oznaciSve;
//	protected String appli2;
//	protected String response;
//	protected String pkiPotpis;
//	//Varijable za potpis
//	protected int brojacSmart = 0;
//	protected boolean stopSmartCheck;
//	protected boolean naloziObradjeni;
//	protected boolean showPotpisaniNalozi;
//	protected String packedBlock;
//	protected long endSmartCheck;
//	protected String hashPotpis;
//	protected Boolean potpisSmart = Boolean.FALSE;
//	protected Boolean potpisPki = Boolean.FALSE;
//	protected Boolean potpisToken = Boolean.FALSE;
//	protected Boolean potpisUnsecured = Boolean.FALSE;
//	protected WebSmartPotpisDto filterPotpisCheck;
//	protected TppNalogPlatniDto nalog;
//	protected String nazivDrzave;
//	protected Boolean potpisi = Boolean.FALSE;
//	protected Boolean povuci = Boolean.FALSE;
//	protected IzvjestajZaNalogPlatniDto izvjestaj = new IzvjestajZaNalogPlatniDto();
//	protected String onemoguciAkcija = "true";
//	protected String onemoguciIspisi = "true";
//	protected String renderedOznakaPosla = "false";
//	protected String refTppNaloga = null;
//	protected String redirectLink = null;
//	@Inject
//	IBSecurityConstantsBean constBean;
//	//    List<String[]> listaPlatiteljPodaci = new ArrayList<String[]>();
//	List<WebNalogAkcijaDto> listaAkcijaNaloga = new ArrayList<WebNalogAkcijaDto>();
//	@Inject private WebSmartPotpisDao smartDao;
//	private TppNalogPlatniDto selectedNalogDto;
//
//
//	public PotpisNalogController() {
//		super(TppNalogPlatniDto.class);
//		String[] params = AuthenticationUtils.getRequestParameter(FacesContext.getCurrentInstance(), "id");
//		if(params != null){
//			refTppNaloga = params[0];
//		}
//	}
//
//	@PostConstruct
//	public void init() {
//		if(loginBean.getPrijava() != null && loginBean.getPrijava() instanceof WebPrijavaSmartToken){
//			try {
//				utilBean.updateExpiredSmartPotpis(IBSmartPotpis.TIP_TPP_NALOG);
//			} catch (AbitRuleException e) {
//				LoggerFactory.getLogger(PotpisNalogMultiController.class.getName()).error("Update Expired SmartPotpis: "+e.getMessage());
//			}
//		}
//
//		//Odredimo akciju za potpis
//		if(loginBean.getPrijava() instanceof WebPrijavaSmartToken){
//			potpisSmart = Boolean.TRUE;
//		}else if(loginBean.getPrijava() instanceof WebPrijavaPKIKartica){
//			potpisPki = Boolean.TRUE;
//		}else if(loginBean.getPrijava() instanceof WebPrijavaUnsecured){
//			potpisUnsecured = Boolean.TRUE;
//		}else{
//			potpisToken = Boolean.TRUE;
//		}
//	}
//
//
//	public void initNalog(){
//		//država primatelj naziv
//		if (StringUtils.isNotBlank(createDto.getDrzavaCreditor())) {
//			nazivDrzave = utilBean.getNazivDrzaveBySidva(createDto.getDrzavaCreditor());
//		}
//		//pregled sifra valute
//		if (createDto.getSifraValute() == null) createDto.setSifraValute(Bassx2Constants.Core.VALUTA_SIFRAVAL_HOME_CURRENCY);
//
//		try {
//			if(createDto.getSifraValute() != null){
//				createDto.setOznakaValute(AuthenticationUtils.resolveOznakaValuteBySifra(createDto.getSifraValute()));
//			}
//		} catch (Exception e) {
//			createDto.setOznakaValute(null);
//		}
//		createDto.resolveStatusAndInfo(false);
//		provjeriAkcija();
//
//		// inicijalno je SHA
//		if (StringUtils.isBlank(getCreateDto().getTrosakInoBanke())) getCreateDto().setTrosakInoBanke(GenericBassxConstants.PlatniPromet.DEVIZNI_NALOG_TROSAK_INO_BANKE_SHA);
//
//		//sifra oznake posla
////        toggleOznakaPosla();
//
//		//izracun SHA1
//		if (createDto != null && createDto.getSifra() != null) {
//			List<TppNalogPlatniDto> oznaceni = new ArrayList<>();
//			oznaceni.add(createDto);
//			if(loginBean.getPrijava() instanceof WebPrijavaSxsToken || loginBean.getPrijava() instanceof WebPrijavaSoftToken){
//				WebDigitalSignatureFootprint footprint = new WebDigitalSignatureFootprint(oznaceni);
//				try {
//					appli2 = footprint.calculateHash(new String[]{footprint.getFootprint()});
//				}catch(AbitRuleException ex){
//					showError(ex);
//				}
//			}
//		}
//
//		//Budući da nemamo odabranog korisnika ovdje preko racuna platitelja odredimo vlasnika računa
//		WebKorisnikRacunDto ovlastenje = null;
//		try{
//			ovlastenje = AuthenticationUtils.resolveKorisnikRacunFromOvlastenja(loginBean.getOvlastenja(), createDto.getIbanZaduzenja(), true);
//		}catch(AbitRuleException ex){
//			ovlastenje = null;
//		}
//
//		//Na početku smo uzeli sva ovlaštenja sada znamo koje je točno i samo s tim radimo
//		if(ovlastenje != null) {
//			if(isPotpis) potpisi = Boolean.TRUE; else povuci = Boolean.TRUE;
//			List<WebKorisnikRacunDto> ovlastenjaNew = new ArrayList<>();
//			ovlastenjaNew.add(ovlastenje);
//			loginBean.setOvlastenja(ovlastenjaNew);
//			//End
//
//			loginBean.setOdabraniVlasnikRacuna(ovlastenje.getKomitentDto());
//			createDto.setSifraPartijeZaduzenja(ovlastenje.getPartijaDto().getSifra());
//			//Provjerimo broj potpisa, te dohvatimo i drugu autorizaciju ako postoji
//			if (ovlastenje.getBrojPotrebnihPotpisa() != null && ovlastenje.getBrojPotrebnihPotpisa() > 1) {
//				List<AuthorizationDto> authorizationDtos = null;
//				if(createDto.isInBasket()){
//					authorizationDtos = authDao.getAuthorizationByObjectId(createDto.getSifraBasketa(), Psd2Constants.AuthorizationObjectType.BASKET_AUTHORIZATION.getValue(), null, null, null);
//				}else{
//					authorizationDtos = authDao.getAuthorizationByObjectId(createDto.getSifra(), Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), null, null, null);
//				}
//				if(authorizationDtos != null) {
//					for (AuthorizationDto auth : authorizationDtos) {
//						if (auth.getLinkAuth() == null && auth.getLinkAuth().equals(refTppNaloga)) continue;
//						createDto.addAuthorization(auth);
//					}
//				}
//			}
//		}else{
//			createDto = new TppNalogPlatniDto();
//			onemoguciAkcija = "true";
//			if(isPotpis) potpisi = Boolean.FALSE; else povuci = Boolean.FALSE;
//			showError(InternationalizationUtil.getLocalMessage("potpisNalogaError_nedostatnaPrava",GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//		}
//	}
//
//	public void ukljuciAkcija() {
//		//potpis
//		if (!potpisi) potpisi = true;
//		else potpisi = false;
//		//povlacenje
//		if (!povuci) povuci = true;
//		else povuci = false;
//	}
//
//	public void updateNalog() {
//		if (StringUtils.isNotBlank(createDto.getVrstaNaloga()) && StringUtils.equals(createDto.getVrstaNaloga(), GenericBassxConstants.Ib.NALOG_VRSTA_KUPNJA_DEVIZA)) {
//			TppNalogPlatniDto tppNalogPlatniDto = 	findDtoByPrimaryKey(Integer.toString(createDto.getSifra()));
//			createDto.setStatus(tppNalogPlatniDto.getStatus());
//			createDto.setStatusTpp(tppNalogPlatniDto.getStatusTpp());
//			createDto.setStatusPsd2(tppNalogPlatniDto.getStatusPsd2());
//			createDto.setSifraKnjiznogNaloga(tppNalogPlatniDto.getSifraKnjiznogNaloga());
//		} else {
//			createDto = findDtoByPrimaryKey(Integer.toString(createDto.getSifra()));
//		}
//		ukljuciAkcija();
//		provjeriAkcija();
//		dohvatiAkcijeNaloga();
//		createDto.resolveStatusAndInfo(false);
//		if(isPotpis){
//			nepotpisaniNalozi=new ArrayList<>();
//			nepotpisaniNalozi.add(createDto);
//		} else {
//			zaPovlacenje = new ArrayList<>();
//			zaPovlacenje.add(createDto);
//		}
//	}
//
//	protected void provjeriAkcija(){
//		try {
//			if (createDto != null && createDto.getSifra() != null){
//				if (createDto.getSifraKnjiznogNaloga() != null) potpisi = false;
//				if (createDto.getKorisnikPotpisaoNalog()) potpisi = false;
//				else  potpisi = true;
//			}
//		} catch (Exception e) {
//			potpisi = false;
//		}
//	}
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
//	public void getIcon (){
////		if((potpisi && createDto.getMozePotpis()) || (povuci)) signFormVisible = "true";
////		else signFormVisible = "false";
//		signFormVisible = "true";
//	}
//
//
//	protected void napuniDatasource() {
//		//hvatanje naloga za pregled iz prethodne forme i popunjavanje createDto
//		if(nalog != null){
//			createDto = nalog;
////            postaviPodatkePrimateljaIzNalogaPredloska();
//			dohvatiAkcijeNaloga();
//		}
//		//upozorenje ako je datum valute subota, nedjelja, praznik ovisno o kanalu
//	}
//
//
//	public String pregledajNalog(String key) {
//		try{
//			// otvaranje forme za unos deviznog priljeva
//			putValueInFlash("inputFormVisible", GenericController.divFormVisible);
//			// punjenje deviznog naloga DTO
//
//			putValueInFlash("nalog", findDtoByPrimaryKey(key));
//			// redirect na devizni priljev
//			return "/app/platniNalog.xhtml?faces-redirect=true";
//		} catch (Exception e) {
//			showError(InternationalizationUtil.getLocalMessage("controller_ibPredlozakIzmijeniPredlozakError", new String[]{e.getMessage()}, GenericBassxConstants.Ib.MESSAGE_BUNDLE));
//			return "";
//		}
//	}
//
//	public String redirectNalog() {
////        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
////        session.invalidate();
//		return getCreateDto().getTppRedirectUri() != null ? getCreateDto().getTppRedirectUri() : "";
//	}
//
//	public String obrisiNalog() {
//        /*
//        TppNalogPlatniDto webNalog = nalogDao.findByPrimaryKey(createDto.getSifra());
//        try {
//            nalogBean.obrisiNalog(webNalog);
//        } catch (AbitRuleException are) {
//            showError(are);
//            //showError(InternationalizationUtil.getLocalMessage("ibPotpisNalogaBrisiNalogError", new String[]{e.getMessage()}, GenericBassxConstants.Ib.MESSAGE_BUNDLE));
//            return "";
//        }
//        */
//		putValueInFlash("nalog", null);
//		return "/app/platniNalog.xhtml?faces-redirect=true";
//	}
//
//	protected void dohvatiAkcijeNaloga() {
////		WebNalogAkcijaDto akcija = new WebNalogAkcijaDto();
////		if(createDto.getSifra() != null){
////			akcija.setSifraNaloga(createDto.getSifra());
////			listaAkcijaNaloga = ibNalogAkcijaDao.filterList(akcija);
////		}
//	}
//
//	public void postaviPodatkePlatitelja() {
//		try {
//			getCreateDto().setIbanZaduzenja(odabraniIban != null ? odabraniIban : getCreateDto().getIbanZaduzenja() != null ? getCreateDto().getIbanZaduzenja() : getListaIban().get(1).getValue().toString());
//			WebKorisnikRacunDto ovl = AuthenticationUtils.resolveKorisnikRacunFromOvlastenja(loginBean.getOvlastenja(), getCreateDto().getIbanZaduzenja());
//			getCreateDto().setNazivDebtor(ovl.getPlatiteljNaziv());
//			getCreateDto().setAdresaDebtor(ovl.getPlatiteljAdresa());
//			getCreateDto().setGradDebtor(ovl.getPlatiteljNazivMjesta());
//			getCreateDto().setDrzavaDebtor(ovl.getPlatiteljDrzava());
//			odabraniIban = null;
//		}catch(AbitRuleException ex){
//			//Ne radimo nistaU
//		}
//	}
//
//
//	public void postaviPodatkePlatiteljaLoad() {
//		try {
//			WebKorisnikRacunDto ovl = AuthenticationUtils.resolveKorisnikRacunFromOvlastenja(loginBean.getOvlastenja(), getCreateDto().getIbanZaduzenja());
//			getCreateDto().setNazivDebtor(ovl.getPlatiteljNaziv());
//			getCreateDto().setAdresaDebtor(ovl.getPlatiteljAdresa());
//			getCreateDto().setGradDebtor(ovl.getPlatiteljNazivMjesta());
//			getCreateDto().setDrzavaDebtor(ovl.getPlatiteljDrzava());
//			odabraniIban = null;
//		}catch(AbitRuleException ex){
//			//Ne radimo nista
//		}
//	}
//
//	public void postaviSwiftIzIbana(){
//		if(IbanFunctions.isIban(getCreateDto().getIbanOdobrenja())){
////    		String vbdiOdobrenja = IbanFunctions.resolveVbdiFromIban(getCreateDto().getIbanOdobrenja(), nalogDao.getEntityManager());
////        	if(vbdiOdobrenja != null){
////        		String swift = utilBean.getSwiftBicBankeByVbdi(vbdiOdobrenja);
////            	getCreateDto().setSwiftNalogodavateljaKorisnika(swift);
////            	azurirajPodatkeBanke();
////            }
//		}
//
//	}
//
//	public void ocistiFormuPotpisa() {
//		setAppli2(null);
//		setResponse(null);
//	}
//
//
//	public List<SelectItem> getListaTroskovnaOpcija() {
//		return listaTroskovnaOpcija;
//	}
//
//	public void setListaTroskovnaOpcija(List<SelectItem> listaTroskovnaOpcija) {
//		this.listaTroskovnaOpcija = listaTroskovnaOpcija;
//	}
//
//	@Override
//	public GenericMinDao<?, TppNalogPlatniDto, Integer> getGenericDao() {
//		return null;
//	}
//
//	@Override
//	public TppNalogPlatniDto findDtoByPrimaryKey(String id) {
//		TppNalogPlatniDto dto = nalogDao.getTppNalogBySifra(Integer.parseInt(id));
//		if(dto != null) dto.setAuthorizations(authDao.getAuthorizationByObjectId(
//				dto.isInBasket() ? dto.getSifraBasketa() : dto.getSifra(),
//				dto.isInBasket() ? Psd2Constants.AuthorizationObjectType.BASKET_AUTHORIZATION.getValue() : Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(),
//				null,
//				null,
//				null));
//		return dto;
//	}
//
//	@Override
//	public Map<String, Object> formReportParameters() throws AbitRuleException {
//		Map<String, Object> params = super.formReportParameters();
//		params.put("reportTitle", "Informacija o platnom nalogu");
//		if (izvjestaj != null) {
//			ReportUtil.parseFilterFromMethod(izvjestaj, params);
//		}
//		return params;
//	}
//
//	@Override
//	public List<IzvjestajZaNalogPlatniDto.DetaljniRed> formDataSource() throws AbitRuleException {
//		List<IzvjestajZaNalogPlatniDto.DetaljniRed> lista = null;
//		izvjestaj = LookupMinHelper.getIPlatniDaoMinProxyRemote().fetchIzvjestajZaNalogPlatni(createDto != null ? createDto.getSifraKnjiznogNaloga() : null);
//		return  izvjestaj.getDetaljniRed();
//	}
//
//	@Override
//	public String getCustomReportName() {
//		return "NalogPlatniReport_2";
//	}
//
//	@Override
//	public String getReportOutputType() {
//		return "pdf";
//	}
//
//	public List<SelectItem> getListaHitnost() {
//		return listaHitnost;
//	}
//
//	public void setListaHitnost(List<SelectItem> listaHitnost) {
//		this.listaHitnost = listaHitnost;
//	}
//
//	public List<SelectItem> getListaValuta() {
//		return listaValuta;
//	}
//
//	public void setListaValuta(List<SelectItem> listaValuta) {
//		this.listaValuta = listaValuta;
//	}
//
//	public TppNalogPlatniDto getNalog() {
//		return nalog;
//	}
//
//	public void setNalog(TppNalogPlatniDto nalog) {
//		this.nalog = nalog;
//	}
//
//	public List<SelectItem> getListaKategorijaNamjene() {
//		return listaKategorijaNamjene;
//	}
//
//	public void setListaKategorijaNamjene(List<SelectItem> listaKategorijaNamjene) {
//		this.listaKategorijaNamjene = listaKategorijaNamjene;
//	}
//
//	public List<SelectItem> getListaSifraNamjene() {
//		return listaSifraNamjene;
//	}
//
//	public void setListaSifraNamjene(List<SelectItem> listaSifraNamjene) {
//		this.listaSifraNamjene = listaSifraNamjene;
//	}
//
//	public List<SelectItem> getListSifraOznakePosla() {
//		return listSifraOznakePosla;
//	}
//
//	public void setListSifraOznakePosla(List<SelectItem> listSifraOznakePosla) {
//		this.listSifraOznakePosla = listSifraOznakePosla;
//	}
//
//	public List<SelectItem> getListaNalogodavateljSpp() {
//		return listaNalogodavateljSpp;
//	}
//
//	public void setListaNalogodavateljSpp(List<SelectItem> listaNalogodavateljSpp) {
//		this.listaNalogodavateljSpp = listaNalogodavateljSpp;
//	}
//
//	public String getRaspolozivoStr(){
//		if (trenutnoRaspolozivo == null) trenutnoRaspolozivo = BigDecimal.ZERO;
//		raspolozivoStr = StringFunctions.bigDecimalToStringCurrency(trenutnoRaspolozivo);
//		try {
//			raspolozivoStr = raspolozivoStr + " " + LookupHelper.getICoreDaoProxyRemote().findValutaBySifra(createDto.getSifraValute()).getOznakaValute();
//		} catch (AbitRuleException e) {
//		}
//		return raspolozivoStr;
//	}
//
//	public String getReadonlyNazivCreditor() {
//		return readonlyNazivCreditor;
//	}
//
//	public void setReadonlyNazivCreditor(String readonlyNazivCreditor) {
//		this.readonlyNazivCreditor = readonlyNazivCreditor;
//	}
//
//	public String getReadonlyAdresaCreditor() {
//		return readonlyAdresaCreditor;
//	}
//
//	public void setReadonlyAdresaCreditor(String readonlyAdresaCreditor) {
//		this.readonlyAdresaCreditor = readonlyAdresaCreditor;
//	}
//
//	public String getReadonlyGradCreditor() {
//		return readonlyGradCreditor;
//	}
//
//	public void setReadonlyGradCreditor(String readonlyGradCreditor) {
//		this.readonlyGradCreditor = readonlyGradCreditor;
//	}
//
//	public String getReadonlyDrzavaCreditor() {
//		return readonlyDrzavaCreditor;
//	}
//
//	public void setReadonlyDrzavaCreditor(String readonlyDrzavaCreditor) {
//		this.readonlyDrzavaCreditor = readonlyDrzavaCreditor;
//	}
//
//	public String getReadonlySwiftNalogodavateljaKorisnika() {
//		return readonlySwiftNalogodavateljaKorisnika;
//	}
//
//	public void setReadonlySwiftNalogodavateljaKorisnika(String readonlySwiftNalogodavateljaKorisnika) {
//		this.readonlySwiftNalogodavateljaKorisnika = readonlySwiftNalogodavateljaKorisnika;
//	}
//
//	public String getReadonlyBankaNalogodavateljaKorisnika() {
//		return readonlyBankaNalogodavateljaKorisnika;
//	}
//
//	public void setReadonlyBankaNalogodavateljaKorisnika(String readonlyBankaNalogodavateljaKorisnika) {
//		this.readonlyBankaNalogodavateljaKorisnika = readonlyBankaNalogodavateljaKorisnika;
//	}
//
//	public List<SelectItem> getListaDrzavaPrimatelj() {
//		return listaDrzavaPrimatelj;
//	}
//
//	public void setListaDrzavaPrimatelj(List<SelectItem> listaDrzavaPrimatelj) {
//		this.listaDrzavaPrimatelj = listaDrzavaPrimatelj;
//	}
//
//	public List<WebNalogAkcijaDto> getListaAkcijaNaloga() {
//		return listaAkcijaNaloga;
//	}
//
//	public void setListaAkcijaNaloga(List<WebNalogAkcijaDto> listaAkcijaNaloga) {
//		this.listaAkcijaNaloga = listaAkcijaNaloga;
//	}
//
//	public String getNazivDrzave() {
//		return nazivDrzave;
//	}
//
//	public void setNazivDrzave(String nazivDrzave) {
//		this.nazivDrzave = nazivDrzave;
//	}
//
//	public String getBase24SignValue() {
//		return base24SignValue;
//	}
//
//	public void setBase24SignValue(String base24SignValue) {
//		this.base24SignValue = base24SignValue;
//	}
//	public List<TppNalogPlatniDto> getNepotpisaniNalozi() {
//		return nepotpisaniNalozi;
//	}
//
//	public void setNepotpisaniNalozi(List<TppNalogPlatniDto> nepotpisaniNalozi) {
//		this.nepotpisaniNalozi = nepotpisaniNalozi;
//	}
//
//	public String getSignFormVisible() {
//		return signFormVisible;
//	}
//
//	public void setSignFormVisible(String signFormVisible) {
//		this.signFormVisible = signFormVisible;
//	}
//
//	public String getPredlosciVisible() {
//		return predlosciVisible;
//	}
//
//	public void setPredlosciVisible(String predlosciVisible) {
//		this.predlosciVisible = predlosciVisible;
//	}
//
//	public String getOdabraniIban() {
//		return odabraniIban;
//	}
//
//	public void setOdabraniIban(String odabraniIban) {
//		this.odabraniIban = odabraniIban;
//	}
//
//	public String getSelectedPredlozak() {
//		return selectedPredlozak;
//	}
//
//	public void setSelectedPredlozak(String selectedPredlozak) {
//		this.selectedPredlozak = selectedPredlozak;
//	}
//
//	public Boolean getShowChallengeResponse(){
//		Boolean res = Boolean.FALSE;
//		res = loginBean.getPrijava() instanceof WebPrijavaSxsToken || loginBean.getPrijava() instanceof WebPrijavaSoftToken ? Boolean.TRUE : Boolean.FALSE;
//		return res;
//	}
//
//	public BigDecimal getTrenutnoRaspolozivo() {
//		return trenutnoRaspolozivo;
//	}
//
//	public void setTrenutnoRaspolozivo(BigDecimal trenutnoRaspolozivo) {
//		this.trenutnoRaspolozivo = trenutnoRaspolozivo;
//	}
//
//	public Boolean getPovuci() {
//		return povuci;
//	}
//
//	public void setPovuci(Boolean povuci) {
//		this.povuci = povuci;
//	}
//
//	public Boolean getPotpisi() {
//		return potpisi;
//	}
//
//	public void setPotpisi(Boolean potpisi) {
//		this.potpisi = potpisi;
//	}
//
//	public String getOnemoguciAkcija() {
//		return onemoguciAkcija;
//	}
//
//	public void setOnemoguciAkcija(String onemoguciAkcija) {
//		this.onemoguciAkcija = onemoguciAkcija;
//	}
//
//	public String getOnemoguciIspisi() {
//		if (createDto != null && createDto.getSifra() != null){
//			if(createDto.getSifraKnjiznogNaloga() == null) onemoguciIspisi = "true";
//			else onemoguciIspisi = "false";
//		}
//		else onemoguciIspisi = "false";
//		return onemoguciIspisi;
//	}
//
//	public void setOnemoguciIspisi(String onemoguciIspisi) {
//		this.onemoguciIspisi = onemoguciIspisi;
//	}
//
//	public String getRenderedOznakaPosla() {
//		return renderedOznakaPosla;
//	}
//
//	public void setRenderedOznakaPosla(String renderedOznakaPosla) {
//		this.renderedOznakaPosla = renderedOznakaPosla;
//	}
//	public Integer getBrojOznacenihNaloga() {
//		return brojOznacenihNaloga;
//	}
//
//	public void setBrojOznacenihNaloga(Integer brojOznacenihNaloga) {
//		this.brojOznacenihNaloga = brojOznacenihNaloga;
//	}
//	public BigDecimal getSumaOznacenoNaknada() {
//		return sumaOznacenoNaknada;
//	}
//
//	public void setSumaOznacenoNaknada(BigDecimal sumaOznacenoNaknada) {
//		this.sumaOznacenoNaknada = sumaOznacenoNaknada;
//	}
//
//	public BigDecimal getSumaOznacenihNaloga() {
//		return sumaOznacenihNaloga;
//	}
//
//	public void setSumaOznacenihNaloga(BigDecimal sumaOznacenihNaloga) {
//		this.sumaOznacenihNaloga = sumaOznacenihNaloga;
//	}
//	public Boolean getOznaciSve() {
//		return oznaciSve;
//	}
//
//	public void setOznaciSve(Boolean oznaciSve) {
//		this.oznaciSve = oznaciSve;
//	}
//
//	public String getAppli2() {
//		return appli2;
//	}
//
//	public void setAppli2(String appli2) {
//		this.appli2 = appli2;
//	}
//
//	public String getResponse() {
//		return response;
//	}
//
//	public void setResponse(String response) {
//		this.response = response;
//	}
//	public Boolean getPotpisPki() {
//		return potpisPki;
//	}
//
//	public void setPotpisPki(Boolean potpisPki) {
//		this.potpisPki = potpisPki;
//	}
//
//	public Boolean getPotpisSmart() {
//		return potpisSmart;
//	}
//
//	public void setPotpisSmart(Boolean potpisSmart) {
//		this.potpisSmart = potpisSmart;
//	}
//
//	public Boolean getPotpisToken() {
//		return potpisToken;
//	}
//
//	public void setPotpisToken(Boolean potpisToken) {
//		this.potpisToken = potpisToken;
//	}
//
//	public boolean getShowPotpisaniNalozi() {
//		return potpisaniNalozi != null && potpisaniNalozi.size() > 0;
//	}
//
//	public void setShowPotpisaniNalozi(boolean showPotpisaniNalozi) {
//		this.showPotpisaniNalozi = showPotpisaniNalozi;
//	}
//
//	public Boolean getPotpisUnsecured() {
//		return potpisUnsecured;
//	}
//
//	public void setPotpisUnsecured(Boolean potpisUnsecured) {
//		this.potpisUnsecured = potpisUnsecured;
//	}
//
//	public Boolean getIsPotpis() {
//		return isPotpis;
//	}
//
//	public void setIsPotpis(Boolean isPotpis) {
//		this.isPotpis = isPotpis;
//	}
//
//	public List<TppNalogPlatniDto> getZaPovlacenje() {
//		return zaPovlacenje;
//	}
//
//	public void setZaPovlacenje(List<TppNalogPlatniDto> zaPovlacenje) {
//		this.zaPovlacenje = zaPovlacenje;
//	}
//
//	public Map<WebPrijava.UTILITY_OBJECT_ID, Object> resolveMapUtilData(){
//		Map<WebPrijava.UTILITY_OBJECT_ID, Object> res = new HashMap<>();
//		res.put(WebPrijava.UTILITY_OBJECT_ID.MAC_INPUT, new WebDigitalSignatureFootprint(odabraniNalozi).getFootprint());
//		res.put(WebPrijava.UTILITY_OBJECT_ID.CHALLENGE, appli2);
//		res.put(WebPrijava.UTILITY_OBJECT_ID.RESPONSE, response);
//		res.put(WebPrijava.UTILITY_OBJECT_ID.SIGN_ID, RandomStringUtils.randomAlphanumeric(16));
//		res.put(WebPrijava.UTILITY_OBJECT_ID.LOGIN_ID, loginBean.getIbKorisnik().getSifra().toString());
//		res.put(WebPrijava.UTILITY_OBJECT_ID.IB_KORISNIK_SIFRA, loginBean.getIbKorisnik().getSifra());
//		res.put(WebPrijava.UTILITY_OBJECT_ID.UTIL_BEAN, utilBean);
//		res.put(WebPrijava.UTILITY_OBJECT_ID.AUTH_UTIL_BEAN, authUtilBean);
//		res.put(WebPrijava.UTILITY_OBJECT_ID.IB_PRIJAVA, loginBean.getPrijava());
//		return res;
//	}
//
//	public void resolveOdabraniNalozi() throws AbitRuleException{
//		odabraniNalozi = new ArrayList<TppNalogPlatniDto>();
//		potpisaniNalozi = new ArrayList<TppNalogPlatniDto>();
//
//		if(isPotpis){
//			for (TppNalogPlatniDto nalog : nepotpisaniNalozi) {
//				if (nalog != null && nalog.isSelected()) {
//					odabraniNalozi.add(nalog);
//				}
//			}
//		}
//		else {
//			for (TppNalogPlatniDto nalog : zaPovlacenje) {
//				if (nalog != null && nalog.isSelected()) {
//					odabraniNalozi.add(nalog);
//				}
//			}
//		}
//
//
//		if (odabraniNalozi == null || odabraniNalozi.size() == 0) {
//			throw new AbitRuleException("Nije odabran nijedan nalog za potpis.");
//		}
//	}
//
//	public void obradiNaloge(String signId, boolean isNalog){
//		try {
//			//Ovdje cemo ugraditi provjeru datuma valute BB naloga
//			String poruka = null; //nalogBean.checkDatumValuteBatchBooking(odabraniNalozi, loginBean.getIbGlobals());
//			if(poruka != null){
//				resetNepotpisani();
//				Bassx2Message.addMessage(Bassx2Message.MessageType.INFO, poruka);
//			}else {
//				odabraniNalozi = nalogBean.signNalozi(odabraniNalozi, loginBean.getIbGlobals(), loginBean.getIbKorisnik(), signId);
//				boolean isInBasket = false;
//				//Provjerimo greske i provjerimo broj knjizenih autorizacija
//				if (odabraniNalozi != null) {
//					int brojOk = 0;
//					String greska = null;
//					for (TppNalogPlatniDto nal : odabraniNalozi) {
//						isInBasket = nal.isInBasket();
//						if (nal.getGreska() != null && !nal.getGreska().equals("")) {
//							greska = nal.getGreska();
//						} else if (nal.getSifraKnjiznogNaloga() == null && nal.getDovoljanBrojPotpisa(nal.getBrojPotrebnihPotpisa(), Psd2Constants.AuthorizationType.CREATED.getValue())) {
//							greska = InternationalizationUtil.getLocalMessage("potpisGreska", GenericBassxConstants.Psd2.MESSAGE_BUNDLE);
//						} else {
//							brojOk++;
//						}
//						nal.setSelected(nal.isInBasket() || (oznaciSve != null && oznaciSve)  ? true : false);
//						nal.resolveStatusAndInfo(true);
//					}
//					if(greska != null){
//						if(odabraniNalozi.size() > 1){
//							int notOk = odabraniNalozi.size() - brojOk;
//							greska = isInBasket ?
//									InternationalizationUtil.getLocalMessage("generalnaGreskaViseNalogaBasket",new String[]{String.valueOf(notOk), String.valueOf(odabraniNalozi.size())}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE) :
//									InternationalizationUtil.getLocalMessage("generalnaGreskaViseNaloga",new String[]{String.valueOf(notOk), String.valueOf(odabraniNalozi.size())}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE);
//						}
//						showError(greska);
//					}else{
//						if (odabraniNalozi.size() > 0) {
//							String porukaTmp = odabraniNalozi.size() == 1 ? InternationalizationUtil.getLocalMessage("potpisOkInfo", GenericBassxConstants.Psd2.MESSAGE_BUNDLE) : InternationalizationUtil.getLocalMessage("potpisViseOkInfo", GenericBassxConstants.Psd2.MESSAGE_BUNDLE);
//							Bassx2Message.addMessage(Bassx2Message.MessageType.INFO, porukaTmp);
//							for (TppNalogPlatniDto wn : odabraniNalozi) {
//								if (wn != null && wn.getWarningMessages() != null && !wn.getWarningMessages().isEmpty()) {
//									for (String m : wn.getWarningMessages()) {
//										showWarnning(m);
//									}
//								}
//							}
//						}
//					}
//
//					setAppli2(null);
//					setResponse(null);
//					setBrojOznacenihNaloga(0);
//					setSumaOznacenihNaloga(BigDecimal.ZERO);
//					setSumaOznacenoNaknada(BigDecimal.ZERO);
//					setOznaciSve(false);
//					calculateSHA1();
//				}
//			}
//		} catch (AbitRuleException e) {
//			showError(InternationalizationUtil.getLocalMessage("potpisPlatnog_error", new String[]{e.getMessage()}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//		}
//	}
//
//	public void povuciNaloge(String signId, boolean isNalog){
//		try {
//			//Ovdje cemo ugraditi provjeru datuma valute BB naloga
//			String poruka = null; //nalogBean.checkDatumValuteBatchBooking(odabraniNalozi, loginBean.getIbGlobals());
//			if(poruka != null){
//				resetNepotpisani();
//				Bassx2Message.addMessage(Bassx2Message.MessageType.INFO, poruka);
//			}else {
//				odabraniNalozi = nalogBean.povuciNaloge(odabraniNalozi, loginBean.getIbGlobals(), loginBean.getIbKorisnik(), signId);
//
//				//Provjerimo greske i provjerimo broj knjizenih autorizacija
//				if (odabraniNalozi != null) {
//					int brojOk = 0;
//					String greska = null;
//					for (TppNalogPlatniDto nal : odabraniNalozi) {
//						if (nal.getGreska() != null && !nal.getGreska().equals("")) {
//							greska = nal.getGreska();
//						} else if (nal.getSifraKnjiznogNaloga() == null && nal.getDovoljanBrojPotpisa(nal.getBrojPotrebnihPotpisa(), Psd2Constants.AuthorizationType.CREATED.getValue())) {
//							greska = InternationalizationUtil.getLocalMessage("potpisGreska", GenericBassxConstants.Psd2.MESSAGE_BUNDLE);
//						} else {
//							brojOk++;
//						}
//						nal.setSelected(nal.isInBasket() || oznaciSve ? true : false);
//						nal.resolveStatusAndInfo(true);
//					}
//					if(greska != null){
//						if(odabraniNalozi.size() > 1){
//							int notOk = odabraniNalozi.size() - brojOk;
//							greska = InternationalizationUtil.getLocalMessage("generalnaGreskaViseNaloga",new String[]{String.valueOf(notOk), String.valueOf(odabraniNalozi.size())}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE);
//						}
//						showError(greska);
//					}
//
//					setAppli2(null);
//					setResponse(null);
//					setBrojOznacenihNaloga(0);
//					setSumaOznacenihNaloga(BigDecimal.ZERO);
//					setSumaOznacenoNaknada(BigDecimal.ZERO);
//					setOznaciSve(false);
//					calculateSHA1();
//					if (odabraniNalozi.size() > 0) {
//						String porukaTmp = odabraniNalozi.size() == 1 ? InternationalizationUtil.getLocalMessage("povlacenjeSingleOkInfo", GenericBassxConstants.Psd2.MESSAGE_BUNDLE) : InternationalizationUtil.getLocalMessage("povlacenjeViseOkInfo", GenericBassxConstants.Psd2.MESSAGE_BUNDLE);
//						Bassx2Message.addMessage(Bassx2Message.MessageType.INFO, porukaTmp);
//						for (TppNalogPlatniDto wn : odabraniNalozi) {
//							if (wn != null && wn.getWarningMessages() != null && !wn.getWarningMessages().isEmpty()) {
//								for (String m : wn.getWarningMessages()) {
//									showWarnning(m);
//								}
//							}
//						}
//					}
//				}
//			}
//		} catch (AbitRuleException e) {
//			showError(InternationalizationUtil.getLocalMessage("povlacenjePlatnog_error", new String[]{e.getMessage()}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//		}
//	}
//
//	public void calculateSHA1(){
//		List<TppNalogPlatniDto> calculateList= CollectionUtils.isNotEmpty(nepotpisaniNalozi) ? new ArrayList<>(nepotpisaniNalozi):new ArrayList<>(CollectionUtils.isNotEmpty(zaPovlacenje)?zaPovlacenje:new ArrayList<>());
//		if(CollectionUtils.isNotEmpty(calculateList)) {
//			infoOznacenihNaloga = new HashMap<>();
//			List<TppNalogPlatniDto> oznaceni = new ArrayList<>();
//			brojOznacenihNaloga = 0;
//			sumaOznacenihNaloga = BigDecimal.ZERO;
//			pkiPotpis = "";
//			response = "";
//			for (TppNalogPlatniDto nal : calculateList) {
//				if (nal.isSelected()) {
//					brojOznacenihNaloga++;
//					oznaceni.add(nal);
//					if(loginBean.getPrijava() instanceof WebPrijavaPKIKartica) pkiPotpis += new WebDigitalSignatureFootprint(nal).getFootprint();
//				}
//			}
//
//			//Idemo kreirati info oznacenih naloga
//			sumaOznacenoNaknada = BigDecimal.ZERO;
//			infoOznacenihNaloga = new HashMap<>();
//			for(TppNalogPlatniDto ozn : oznaceni){
//				BigDecimal iznosTmp = ozn.getIznos();
//				if(infoOznacenihNaloga.containsKey(ozn.getOznakaValute())){
//					iznosTmp = iznosTmp.add(infoOznacenihNaloga.get(ozn.getOznakaValute()));
//				}
//				if(ozn.getIznosNaknade() != null) {
//					sumaOznacenoNaknada = sumaOznacenoNaknada.add(ozn.getIznosNaknade());
//				}
//				infoOznacenihNaloga.put(ozn.getOznakaValute(), iznosTmp);
//			}
//			if(infoOznacenihNaloga != null && infoOznacenihNaloga.size() == 0){
//				infoOznacenihNaloga.put("",BigDecimal.ZERO);
//			}
//			WebDigitalSignatureFootprint footprint = new WebDigitalSignatureFootprint(oznaceni);
//			try {
//				appli2 = footprint.getFootprint() != null && !footprint.getFootprint().equals("") ? footprint.calculateHash(new String[]{footprint.getFootprint()}) : "";
//			}catch(AbitRuleException ex){
//				showError(ex);
//			}
//		}
//
//	}
//
//	public void resetNepotpisani() {
//		if(filterDto == null) filterDto = new TppNalogPlatniDto();
//
//		nepotpisaniNalozi= new ArrayList<>();
////		sviNepotpisaniNalozi.clear();
////TODO			filtriraniNalozi = nalogDao.filterList(getFilterDto(), true, true);
////		if(StringUtils.compare(selectedFilter, InternationalizationUtil.getLocalMessage("sviNalozi", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)) == 0){
////			sviNepotpisaniNalozi = filtriraniNalozi;
////		}
////		else if (StringUtils.compare(selectedFilter, InternationalizationUtil.getLocalMessage("naloziDanas", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)) == 0){
////			int i = 0;
////			for (TppNalogPlatniDto nalog : filtriraniNalozi){
////				if (DateUtil.compareDates(nalog.getDatumValute(), DateUtil.today()).compareTo(0) == 0){
////					sviNepotpisaniNalozi.add(nalog);
////					i++;
////				}
////			}
////		}else if (StringUtils.compare(selectedFilter, InternationalizationUtil.getLocalMessage("naloziUNajavi", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)) == 0){
////			sviNepotpisaniNalozi = null;
////			int i = 0;
////			for (TppNalogPlatniDto nalog : filtriraniNalozi){
////				if (DateUtil.compareDates(nalog.getDatumValute(), DateUtil.today()).compareTo(1) == 0){
////					sviNepotpisaniNalozi.add(nalog);
////					i++;
////				}
////			}
////		}else if (StringUtils.compare(selectedFilter, InternationalizationUtil.getLocalMessage("stariNaloziNepotpisani", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)) == 0){
////			sviNepotpisaniNalozi = null;
////			int i = 0;
////			for (TppNalogPlatniDto nalog : filtriraniNalozi){
////				if (DateUtil.compareDates(nalog.getDatumValute(), DateUtil.today()).compareTo(-1) == 0){
////					sviNepotpisaniNalozi.add(nalog);
////					i++;
////				}
////			}
////		}
//
//		listaDatoteka = null;
//
//		Map<Integer, String> mapDatoteke = new HashMap<>();
////		if(sviNepotpisaniNalozi != null){
////			for(TppNalogPlatniDto nal : sviNepotpisaniNalozi){
////TODO					nalogDao.pripremiPodatkePlatniReport(nal);
////					if(nal.getVrstaNaloga() != null && nal.getVrstaNaloga().equals(IBNalog.VRSTA_KUPOPRODAJA)) {
////						ibNalogDao.resolveKuppro(nal);
////					}
////					if(nal.getSifraDatoteke() != null){
////						mapDatoteke.put(nal.getSifraDatoteke(), nal.getNazivDatoteke());
////					}
////			}
////		}
//
////		if(mapDatoteke != null && !mapDatoteke.isEmpty()){
////			listaDatoteka = new ArrayList<>();
////			for(Map.Entry<Integer, String> tmpEntry : mapDatoteke.entrySet()){
////				listaDatoteka.add(new SelectItem(tmpEntry.getKey(), tmpEntry.getValue()));
////			}
////		}
////		} catch (AbitRuleException are) {
////			showError(are);
////		}
//
////		for (TppNalogPlatniDto nalog : sviNepotpisaniNalozi){
////			nepotpisaniNalozi.add(nalog);
////
////			if(!tempListaIban.contains(nalog.getIbanZaduzenja()))
////				tempListaIban.add(nalog.getIbanZaduzenja());
////		}
////
////		for (String iban : tempListaIban){
////			listaIban.add(new SelectItem(iban, iban));
////		}
//
//	}
//
//	public void potpisPki(){
//		try {
//			resolveOdabraniNalozi();
//			Map<WebPrijava.UTILITY_OBJECT_ID, Object> mapObjects = resolveMapUtilData();
//			odabraniNalozi = loginBean.getPrijava().potpisiTppNaloge(odabraniNalozi, mapObjects);
//			obradiNaloge((String)mapObjects.get(WebPrijava.UTILITY_OBJECT_ID.SIGN_ID), false);
//		}catch(AbitRuleException ex){
//			showError(InternationalizationUtil.getLocalMessage("potpisPlatnog_error", new String[]{ex.getMessage()}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//		}
//	}
//
//	public void potpisPkiNalog(TppNalogPlatniDto webNalogDto){
//        try {
//		odabraniNalozi = new ArrayList<TppNalogPlatniDto>();
//		odabraniNalozi.add(webNalogDto);
//		Map<WebPrijava.UTILITY_OBJECT_ID, Object> mapObjects = resolveMapUtilData();
//		odabraniNalozi = loginBean.getPrijava().potpisiTppNaloge(odabraniNalozi, mapObjects);
//		obradiNaloge((String)mapObjects.get(WebPrijava.UTILITY_OBJECT_ID.SIGN_ID), true);
////		WebPrijavaBean requestContext = RequestContext.getCurrentInstance();
////		if (odabraniNalozi != null && odabraniNalozi.size() == 1 && odabraniNalozi.get(0).getGreska() == null && requestContext != null) requestContext.execute("javascript:update();");
//		if (odabraniNalozi != null && odabraniNalozi.size() == 1 && odabraniNalozi.get(0).getGreska() == null && PrimeFaces.current() != null) PrimeFaces.current().executeScript("javascript:update();");
//        }catch(AbitRuleException ex){
//            showError(InternationalizationUtil.getLocalMessage("potpisPlatnog_error", new String[]{ex.getMessage()}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//        }
//	}
//
//	public void potpisToken(){
//		try {
//			resolveOdabraniNalozi();
//			Map<WebPrijava.UTILITY_OBJECT_ID, Object> mapObjects = resolveMapUtilData();
//			odabraniNalozi = loginBean.getPrijava().potpisiTppNaloge(odabraniNalozi, mapObjects);
//			if(isPotpis) {
//				obradiNaloge((String) mapObjects.get(WebPrijava.UTILITY_OBJECT_ID.SIGN_ID), false);
//			}
//			else {
//				povuciNaloge((String) mapObjects.get(WebPrijava.UTILITY_OBJECT_ID.SIGN_ID), false);
//				List<Integer> tppSifraList = new ArrayList<>();
//				for (TppNalogPlatniDto od : odabraniNalozi) {
//					tppSifraList.add(od.getSifra());
//				}
//				zaPovlacenje=new ArrayList<>();
//				List<TppNalogPlatniDto> naloziTpp = nalogDao.getTppNaloziBySifraList(tppSifraList, false);
//
//				if (CollectionUtils.isNotEmpty(naloziTpp)) {
//					for (TppNalogPlatniDto tppNal : naloziTpp) {
//						if(tppNal != null) tppNal.setAuthorizations(authDao.getAuthorizationByObjectId(
//								tppNal.isInBasket() ? tppNal.getSifraBasketa() : tppNal.getSifra(),
//								tppNal.isInBasket() ? Psd2Constants.AuthorizationObjectType.BASKET_AUTHORIZATION.getValue() : Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(),
//								null,
//								null,
//								null));
//						createDto = tppNal;
//						createDto.resolveStatusAndInfo(false);
//						redirectLink = redirectLink == null ? tppNal.getTppRedirectUriCancelation() : redirectLink;
//						zaPovlacenje.add(createDto);
//
//					}
//				}
//			}
//		}catch(AbitRuleException ex){
//			showError(InternationalizationUtil.getLocalMessage("potpisPlatnog_error", new String[]{ex.getMessage()}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//		}
//	}
//	//Dio za potpis
//	public void potpisSmart(){
//		try {
//			naloziObradjeni = false;
////			RequestContext requestContext = RequestContext.getCurrentInstance();
//			resolveOdabraniNalozi();
//			String sesionId = RandomStringUtils.randomAlphanumeric(16);
//			hashPotpis = WebDigitalSignatureFootprint.getBase24Sign(new WebDigitalSignatureFootprint(odabraniNalozi, new Date(), true).getFootprint());
//			endSmartCheck = System.currentTimeMillis() + ((constBean.getSmartExpiriyTime().intValue() + 15) * 1000); //15 zbog otvaranja smartis aplikacije
//			nalogBean.addSmartCheck(odabraniNalozi, sesionId, hashPotpis);
//			setBase24SignValue("pabasign://sign/" + new WebDigitalSignatureFootprint(odabraniNalozi, sesionId, loginBean.getIbKorisnik().getSifra().toString(), WebDigitalSignatureFootprint.TIP_TPP_NALOG, hashPotpis).getBase24Sign());
//			stopSmartCheck = Boolean.FALSE;
//			//filter za check
//			filterPotpisCheck = new WebSmartPotpisDto();
//			filterPotpisCheck.setSifraIbKorisnik(loginBean.getIbKorisnik().getSifra());
//			//filterPotpisCheck.setHash(hashPotpis);
//			filterPotpisCheck.setSessionId(sesionId);
//			filterPotpisCheck.setStatus(IBSmartPotpis.STATUS_POTPISAN);
//			//end filter
////			requestContext.execute("javascript:pabasign('"+getBase24SignValue()+"');");
//			PrimeFaces.current().executeScript("javascript:pabasign('"+getBase24SignValue()+"');");
//		}catch(AbitRuleException ex){
//			showError(InternationalizationUtil.getLocalMessage("potpisPlatnog_error", new String[]{ex.getMessage()}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//		}
//	}
//
//	public void potpisSmartNalog(TppNalogPlatniDto webNalogDto){
//		try {
//			naloziObradjeni = false;
//			odabraniNalozi = new ArrayList<TppNalogPlatniDto>();
//			odabraniNalozi.add(webNalogDto);
////			RequestContext requestContext = RequestContext.getCurrentInstance();
//			String sesionId = RandomStringUtils.randomAlphanumeric(16);
//			hashPotpis = WebDigitalSignatureFootprint.getBase24Sign(new WebDigitalSignatureFootprint(odabraniNalozi, new Date(), true).getFootprint());
//			endSmartCheck = System.currentTimeMillis() + ((constBean.getSmartExpiriyTime().intValue() + 15) * 1000); //15 zbog otvaranja smartis aplikacije
//			nalogBean.addSmartCheck(odabraniNalozi, sesionId, hashPotpis);
//			setBase24SignValue("pabasign://sign/" + new WebDigitalSignatureFootprint(odabraniNalozi, sesionId, loginBean.getIbKorisnik().getSifra().toString(), WebDigitalSignatureFootprint.TIP_TPP_NALOG, hashPotpis).getBase24Sign());
//			stopSmartCheck = Boolean.FALSE;
//			//filter za check
//			filterPotpisCheck = new WebSmartPotpisDto();
//			filterPotpisCheck.setSifraIbKorisnik(loginBean.getIbKorisnik().getSifra());
//			//filterPotpisCheck.setHash(hashPotpis != null && hashPotpis.length() > 100 ? hashPotpis.substring(0, 100) : hashPotpis);
//			filterPotpisCheck.setSessionId(sesionId);
//			filterPotpisCheck.setStatus(IBSmartPotpis.STATUS_POTPISAN);
//			//end filter
////			requestContext.execute("javascript:pabasign('"+getBase24SignValue()+"');");
//			PrimeFaces.current().executeScript("javascript:pabasign('"+getBase24SignValue()+"');");
//		}catch(AbitRuleException ex){
//			showError(InternationalizationUtil.getLocalMessage("potpisPlatnog_error", new String[]{ex.getMessage()}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//		}
//	}
//
//
//
//	public void potpisTokenNalog(TppNalogPlatniDto webNalogDto, String appli2){
//        try {
//			odabraniNalozi = new ArrayList<TppNalogPlatniDto>();
//			odabraniNalozi.add(webNalogDto);
//			setAppli2(appli2);
//			Map<WebPrijava.UTILITY_OBJECT_ID, Object> mapObjects = resolveMapUtilData();
//			odabraniNalozi = loginBean.getPrijava().potpisiTppNaloge(odabraniNalozi, mapObjects);
//			obradiNaloge((String)mapObjects.get(WebPrijava.UTILITY_OBJECT_ID.SIGN_ID), true);
////			RequestContext requestContext = RequestContext.getCurrentInstance();
////			if (odabraniNalozi != null && odabraniNalozi.size() == 1 && odabraniNalozi.get(0).getGreska() == null && requestContext != null) requestContext.execute("javascript:update();");
//			if (odabraniNalozi != null && odabraniNalozi.size() == 1 && odabraniNalozi.get(0).getGreska() == null && PrimeFaces.current() != null) PrimeFaces.current().executeScript("javascript:update();");
//        }catch(AbitRuleException ex){
//            showError(InternationalizationUtil.getLocalMessage("potpisPlatnog_error", new String[]{ex.getMessage()}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//        }
//	}
//
//	public void potpisUnsecured(){
//		try {
//			resolveOdabraniNalozi();
//			Map<WebPrijava.UTILITY_OBJECT_ID, Object> mapObjects = resolveMapUtilData();
//			odabraniNalozi = loginBean.getPrijava().potpisiTppNaloge(odabraniNalozi, mapObjects);
//			if(isPotpis) {
//				obradiNaloge((String) mapObjects.get(WebPrijava.UTILITY_OBJECT_ID.SIGN_ID), false);
//			}else{
//				povuciNaloge((String) mapObjects.get(WebPrijava.UTILITY_OBJECT_ID.SIGN_ID), false);
//				List<Integer> tppSifraList = new ArrayList<>();
//				for (TppNalogPlatniDto od : odabraniNalozi) {
//					tppSifraList.add(od.getSifra());
//				}
//				zaPovlacenje=new ArrayList<>();
//				List<TppNalogPlatniDto> naloziTpp = nalogDao.getTppNaloziBySifraList(tppSifraList, false);
//
//				if (CollectionUtils.isNotEmpty(naloziTpp)) {
//					for (TppNalogPlatniDto tppNal : naloziTpp) {
//						if(tppNal != null) tppNal.setAuthorizations(authDao.getAuthorizationByObjectId(
//								tppNal.isInBasket() ? tppNal.getSifraBasketa() : tppNal.getSifra(),
//								tppNal.isInBasket() ? Psd2Constants.AuthorizationObjectType.BASKET_AUTHORIZATION.getValue() : Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(),
//								null,
//								null,
//								null));
//						createDto = tppNal;
//						createDto.resolveStatusAndInfo(false);
//						redirectLink = redirectLink == null ? tppNal.getTppRedirectUriCancelation() : redirectLink;
//						zaPovlacenje.add(createDto);
//
//					}
//				}
//			}
//			//updateNalog();
//		}catch(AbitRuleException ex){
//			showError(InternationalizationUtil.getLocalMessage("potpisPlatnog_error", new String[]{ex.getMessage()}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//		}
//	}
//
//	public void potpisUnsecuredNalog(){
//		try {
//			odabraniNalozi = new ArrayList<>();
//			odabraniNalozi.add(getCreateDto());
//			Map<WebPrijava.UTILITY_OBJECT_ID, Object> mapObjects = resolveMapUtilData();
//			odabraniNalozi = ((WebPrijavaUnsecured)loginBean.getPrijava()).potpisiTppNaloge(odabraniNalozi, mapObjects);
//			if(isPotpis) {
//				obradiNaloge((String) mapObjects.get(WebPrijava.UTILITY_OBJECT_ID.SIGN_ID), true);
//			}else{
//				povuciNaloge((String) mapObjects.get(WebPrijava.UTILITY_OBJECT_ID.SIGN_ID), true);
//			}
////			RequestContext requestContext = RequestContext.getCurrentInstance();
////			if (odabraniNalozi != null && odabraniNalozi.size() == 1 && odabraniNalozi.get(0).getGreska() == null && requestContext != null) requestContext.execute("javascript:update();");
//			createDto = odabraniNalozi != null && odabraniNalozi.size() == 1 && odabraniNalozi.get(0).getGreska() != null ? odabraniNalozi.get(0) : createDto;
//			updateNalog();
//		}catch(AbitRuleException ex){
//			showError(InternationalizationUtil.getLocalMessage("potpisPlatnog_error", new String[]{ex.getMessage()}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//		}
//	}
//
//	public void potpisPKI() {
//		try {
//			Map<WebPrijava.UTILITY_OBJECT_ID, Object> mapObjects = resolveMapUtilData();
//			resolveOdabraniNalozi();
//			odabraniNalozi = loginBean.getPrijava().potpisiTppNaloge(odabraniNalozi, mapObjects);
//			if(isPotpis) {
//				obradiNaloge((String) mapObjects.get(WebPrijava.UTILITY_OBJECT_ID.SIGN_ID), false);
//			}
//			else {
//				povuciNaloge((String) mapObjects.get(WebPrijava.UTILITY_OBJECT_ID.SIGN_ID), false);
//				List<Integer> tppSifraList = new ArrayList<>();
//				for (TppNalogPlatniDto od : odabraniNalozi) {
//					tppSifraList.add(od.getSifra());
//				}
//				zaPovlacenje=new ArrayList<>();
//				List<TppNalogPlatniDto> naloziTpp = nalogDao.getTppNaloziBySifraList(tppSifraList, false);
//
//				if (CollectionUtils.isNotEmpty(naloziTpp)) {
//					for (TppNalogPlatniDto tppNal : naloziTpp) {
//						if(tppNal != null) tppNal.setAuthorizations(authDao.getAuthorizationByObjectId(
//								tppNal.isInBasket() ? tppNal.getSifraBasketa() : tppNal.getSifra(),
//								tppNal.isInBasket() ? Psd2Constants.AuthorizationObjectType.BASKET_AUTHORIZATION.getValue() : Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(),
//								null,
//								null,
//								null));
//						createDto = tppNal;
//						createDto.resolveStatusAndInfo(false);
//						redirectLink = redirectLink == null ? tppNal.getTppRedirectUriCancelation() : redirectLink;
//						zaPovlacenje.add(createDto);
//
//					}
//				}
//			}
//		} catch (AbitRuleException e) {
//			showError(InternationalizationUtil.getLocalMessage("potpisPlatnog_error", new String[]{e.getMessage()}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//		}
//		resetNepotpisani();
//	}
//
//	public void checkSmartPotpisResult(boolean isNalog){
//		LoggerFactory.getLogger(PotpisNalogMultiController.class.getName()).info("Count");
//		//Ovdje provjeravamo da li je je web servis provjerio naloge i postavio ispravan status na smartCheck
//		List<WebSmartPotpisDto> naloziSmart = smartDao.filterList(filterPotpisCheck);
//		if(System.currentTimeMillis() >= endSmartCheck || (naloziSmart != null && naloziSmart.size() == odabraniNalozi.size())){
//			stopSmartCheck = Boolean.TRUE;
//			LoggerFactory.getLogger(this.getClass()).info("stopSmartCheck = TRUE");
//		}
//		if(stopSmartCheck && !naloziObradjeni){
////			RequestContext requestContext = RequestContext.getCurrentInstance();
////			requestContext.execute("javascript:pabasignEnd();");
//			PrimeFaces.current().executeScript("javascript:pabasignEnd();");
//			List<TppNalogPlatniDto> potpisaniNalozi = new ArrayList<>();
//			for(TppNalogPlatniDto nalTmp : odabraniNalozi){
//				nalTmp.setGreska(InternationalizationUtil.getLocalMessage("potpisPlatniNijePotpisan", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//				for(WebSmartPotpisDto smartPot : naloziSmart){
//					if(smartPot.getSifraNaloga().compareTo(nalTmp.getSifra()) == 0){
//						nalTmp.setSifraSmartPotpisa(smartPot.getSifra());
//						nalTmp.setGreska(null);
//						potpisaniNalozi.add(nalTmp);
//						break;
//					}
//				}
//			}
//			if(odabraniNalozi != null && potpisaniNalozi != null && odabraniNalozi.size() != potpisaniNalozi.size()) {
//				showError(InternationalizationUtil.getLocalMessage("potpisUsbTimeexceeded", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//			}else{
//				LoggerFactory.getLogger(this.getClass()).info("checkSmartPotpisResult - obrada naloga");
//				odabraniNalozi = potpisaniNalozi;
//				if(isPotpis) {
//					obradiNaloge(filterPotpisCheck.getSessionId(), isNalog);
//				}
//				else {
//					povuciNaloge(filterPotpisCheck.getSessionId(), isNalog);
//					List<Integer> tppSifraList = new ArrayList<>();
//					for (TppNalogPlatniDto od : odabraniNalozi) {
//						tppSifraList.add(od.getSifra());
//					}
//					zaPovlacenje=new ArrayList<>();
//					List<TppNalogPlatniDto> naloziTpp = nalogDao.getTppNaloziBySifraList(tppSifraList, false);
//
//					if (CollectionUtils.isNotEmpty(naloziTpp)) {
//						for (TppNalogPlatniDto tppNal : naloziTpp) {
//							if(tppNal != null) tppNal.setAuthorizations(authDao.getAuthorizationByObjectId(
//									tppNal.isInBasket() ? tppNal.getSifraBasketa() : tppNal.getSifra(),
//									tppNal.isInBasket() ? Psd2Constants.AuthorizationObjectType.BASKET_AUTHORIZATION.getValue() : Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(),
//									null,
//									null,
//									null));
//							createDto = tppNal;
//							createDto.resolveStatusAndInfo(false);
//							redirectLink = redirectLink == null ? tppNal.getTppRedirectUriCancelation() : redirectLink;
//							zaPovlacenje.add(createDto);
//
//						}
//					}
//				}
//				naloziObradjeni = true;
//			}
//		}
//	}
//
//	public TppNalogPlatniDto getSelectedNalogDto() {
//		return selectedNalogDto;
//	}
//
//	public void setSelectedNalogDto(TppNalogPlatniDto selectedNalogDto) {
//		this.selectedNalogDto = selectedNalogDto;
//	}
//
//	public void fillDetaljinaloga() {
//		// inicijalizacija
//		selectedNalogDto = new TppNalogPlatniDto();
//		Integer key = (Integer) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("rowIndex");
//		selectedNalogDto = (TppNalogPlatniDto) FacesContext.getCurrentInstance().getExternalContext().getRequestMap().get("j");
//	}
//
//	public void selectAllNone() {
//		if(nepotpisaniNalozi != null){
//			for(TppNalogPlatniDto nalog : nepotpisaniNalozi) {
//				if (nalog.getMozePotpis())
//					nalog.setSelected(oznaciSve);
//				else
//					nalog.setSelected(Boolean.FALSE);
//			}
//			calculateSHA1();
//			response = "";
//		}
//		if(zaPovlacenje!= null){
//			for(TppNalogPlatniDto nalog : zaPovlacenje) {
//				if (nalog.getMozeBrisanjePovlacenje())
//					nalog.setSelected(oznaciSve);
//				else
//					nalog.setSelected(Boolean.FALSE);
//			}
//			calculateSHA1();
//			response = "";
//		}
//	}
//
//	public String getRedirectVisible() {
//		Boolean res = Boolean.TRUE;
////		res = res && ((nepotpisaniNalozi != null && nepotpisaniNalozi.size() > 0) || (zaPovlacenje != null && zaPovlacenje.size() > 0));
//		if(nepotpisaniNalozi != null){
//			for(TppNalogPlatniDto tmpNal : nepotpisaniNalozi){
//				res = res && (!tmpNal.getIsNepotpisan() || tmpNal.getKorisnikPotpisaoNalog());
//				if(!res) break;
//			}
//		}
//		if(zaPovlacenje != null){
//			for(TppNalogPlatniDto tmpNal : zaPovlacenje){
//				res = res && (tmpNal.getIsPovucen());
//				if(!res) break;
//			}
//		}
//		return res ? "true" : "false";
//	}
//
//	public void setRedirectVisible(String redirectVisible) {
//		this.redirectVisible = redirectVisible;
//	}
//
//	public Boolean getRenderPotpisPanel() {
//		Boolean res = Boolean.FALSE;
////		res = res && ((nepotpisaniNalozi != null && nepotpisaniNalozi.size() > 0) || (zaPovlacenje != null && zaPovlacenje.size() > 0));
//		if(nepotpisaniNalozi != null){
//			for(TppNalogPlatniDto tmpNal : nepotpisaniNalozi){
//				res = tmpNal.getIsNepotpisan() && !tmpNal.getKorisnikPotpisaoNalog();
//				if(res) break;
//			}
//		}
//		if(zaPovlacenje != null){
//			for(TppNalogPlatniDto tmpNal : zaPovlacenje){
//				res = !tmpNal.getIsPovucen();
//				if(res) break;
//			}
//		}
//		return res;
//	}
//
//	public String getRedirectLink() {
//		return StringUtils.isNotBlank(redirectLink) ? redirectLink.startsWith("http") ? redirectLink : "http://"+redirectLink : null;
//	}
//
//	public void setRedirectLink(String redirectLink) {
//		this.redirectLink = redirectLink;
//	}
//
//	public boolean getStopSmartCheck() {
//		return stopSmartCheck;
//	}
//	public void setStopSmartCheck(boolean stopSmartCheck) {
//		this.stopSmartCheck = stopSmartCheck;
//	}
//
//	public Boolean getLinkExpired(){
//		Boolean res = Boolean.FALSE;
//		if(isPotpis){
//			if(nepotpisaniNalozi != null){
//				for(TppNalogPlatniDto nalog : nepotpisaniNalozi){
//					if(nalog.getAuthorization().isExpired(LocalDateTime.now())) {
//						res = Boolean.TRUE;
//						break;
//					}
//				}
//			}
//		}else{
//			if(zaPovlacenje != null){
//				for(TppNalogPlatniDto nalog : zaPovlacenje){
//					if(nalog.getAuthorization().isExpired(LocalDateTime.now())) {
//						res = Boolean.TRUE;
//						break;
//					}
//				}
//			}
//		}
//		return res;
//	}
//}
