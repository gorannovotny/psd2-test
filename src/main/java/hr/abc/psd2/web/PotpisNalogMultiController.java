//package hr.abc.psd2.web;
//
//import hr.abit.b3.biz.core.exception.AbitRuleException;
//import hr.abit.b3.biz.core.util.GenericBassxConstants;
//import hr.abit.b3.biz.core.util.InternationalizationUtil;
//import hr.abit.b3.biz.ib.dataObject.WebKorisnikRacunDto;
//import hr.abc.psd2.dto.authorization.AuthorizationDto;
//import hr.abc.psd2.dto.pis.TppNalogPlatniDto;
//import hr.abc.psd2.util.Psd2Constants;
//import hr.abit.b3.biz.security.WebLoginBean;
//import hr.abit.b3.biz.security.WebPrijavaSoftToken;
//import hr.abit.b3.biz.security.WebPrijavaSxsToken;
//import hr.abit.b3.biz.security.WebPrijavaUnsecured;
//import hr.abit.b3.entity.ib.WebKorisnikDao;
//import hr.abit.b3.web.core.GenericController;
//import org.apache.commons.lang3.StringUtils;
//import hr.abc.psd2.bean.NalogTppBean;
//import hr.abc.psd2.dao.AuthorizationDao;
//import hr.abc.psd2.dao.NalogTppDao;
//import hr.abc.psd2.util.Psd2Util;
//
//import javax.annotation.PostConstruct;
//import javax.ejb.EJB;
//import javax.faces.context.FacesContext;
//import javax.faces.model.SelectItem;
//import javax.faces.view.ViewScoped;
//import javax.inject.Inject;
//import javax.inject.Named;
//import java.math.BigDecimal;
//import java.text.DecimalFormat;
//import java.text.DecimalFormatSymbols;
//import java.time.LocalDateTime;
//import java.util.*;
//
///**
// *
// * 03.02.2016.
// *
// * @author Jasna
// */
//@ViewScoped
//@Named
//public class PotpisNalogMultiController extends PotpisNalogController {
//
//	private static final long serialVersionUID = 1L;
//
//	@Inject private WebLoginBean loginBean;
//	@Inject private NalogTppDao nalogDao;
//	@Inject private WebKorisnikDao webKorisnikDao;
//	@Inject private AuthorizationDao authDao;
//
//	@EJB private NalogTppBean nalogBean;
//
//	private List<TppNalogPlatniDto> nalogPotpisali;
//	private List<Integer> partijeOvlastenja = new ArrayList<>();
//	private List<SelectItem> listaIban = new ArrayList<>();
//	private String odabraniIban = null;
//	private String odabranaValuta = null;
//	private List<String> tempListaIban = new ArrayList<>();
//
//	private List<String> filter = new ArrayList<>();
//	private String selectedFilter;
//	private Integer sifraTmp;
//
//	public PotpisNalogMultiController() {
//		super();
//		setIsPotpis(Boolean.TRUE);
//	}
//
//	@PostConstruct
//	public void init() {
//		super.init();
//
//		//Tu dohvatimo naloge id = broj u autorizaciji
//		if(refTppNaloga != null && !StringUtils.isEmpty(refTppNaloga)){
//			List<AuthorizationDto> authorizationDto = authDao.getAuthorizationsByLink(refTppNaloga, Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue());
//			if(authorizationDto != null) {
//				List<Integer> tppSifraList = new ArrayList<>();
//				Map<Integer, AuthorizationDto> mapAuth = new HashMap<>();
//				for(AuthorizationDto authDto : authorizationDto){
//					if(!Arrays.asList(AuthorizationDto.SCA_STATUSI_ZA_POTPIS).contains(authDto.getScastAuth()))continue;
//					tppSifraList.add(authDto.getObjectAuth());
//					mapAuth.put(authDto.getObjectAuth(), authDto);
//				}
//				if(mapAuth != null && mapAuth.size() > 0) {
//					nepotpisaniNalozi = new ArrayList<>();
//					List<TppNalogPlatniDto> naloziTpp = nalogDao.getTppNaloziBySifraList(tppSifraList, true);
//					if (naloziTpp != null) {
//						boolean isOneExpired = false;
//						for (TppNalogPlatniDto tppNal : naloziTpp) {
//							tppNal.setAuthorization(mapAuth.get(tppNal.getSifra()));
//							createDto = tppNal;
//							initNalog();
//							createDto.resolveStatusAndInfo(false);
//							redirectLink = redirectLink == null ? tppNal.getTppRedirectUri() : redirectLink;
//							if (createDto.getSifraPartijeZaduzenja() != null) {
//								nepotpisaniNalozi.add(createDto);
//							}
//							isOneExpired = isOneExpired || tppNal.getAuthorization().isExpired(LocalDateTime.now());
//						}
//						if(isOneExpired){
//							showError(InternationalizationUtil.getLocalMessage("potpisNalogaError_authExpired", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//						}
//						//Provjerimo listu naloga radi BB
//						nepotpisaniNalozi = Psd2Util.resolveNepotpisani(nepotpisaniNalozi);
//					}else{
//						showError(InternationalizationUtil.getLocalMessage("potpisNalogaError_nePostoji", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//					}
//				}else{
//					showError(InternationalizationUtil.getLocalMessage("potpisNalogaError_nePostojiAutorizacija", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//				}
//			}
//		}else{
//			showError(InternationalizationUtil.getLocalMessage("potpisNalogaError_idNePostoji",GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//		}
//
//
//		listaIban.add(new SelectItem("aaaa", "aaaaa"));
////		listaIban.add(new SelectItem(InternationalizationUtil.getLocalMessage("sviNalozi", GenericBassxConstants.Psd2.MESSAGE_BUNDLE), InternationalizationUtil.getLocalMessage("sviNalozi", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)));
//
////		filter.add(0, InternationalizationUtil.getLocalMessage("sviNalozi", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
////		filter.add(1, InternationalizationUtil.getLocalMessage("naloziDanas", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
////		filter.add(2, InternationalizationUtil.getLocalMessage("naloziUNajavi", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
////		filter.add(3, InternationalizationUtil.getLocalMessage("stariNaloziNepotpisani", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
////
////		selectedFilter = InternationalizationUtil.getLocalMessage("sviNalozi", GenericBassxConstants.Psd2.MESSAGE_BUNDLE);
////
//		//Dohvatimo parametre iz flasha, mozda je upit dosao iz pregleda racuna pa imamo partiju i valutu za koju trazimo naloge
////        if (getValueFromFlash("iban") != null) {
////            String iban = (String) getValueFromFlash("iban");
////            if (StringUtils.isNotBlank(iban)) {
////                odabraniIban = iban;
////            }
////            else {
////                odabraniIban = InternationalizationUtil.getLocalMessage("sviNalozi", GenericBassxConstants.Psd2.MESSAGE_BUNDLE);
////            }
//		//praznimo flash za moguci iduci iban
////            putValueInFlash("iban", null);
////        }
////        else {
////            odabraniIban = InternationalizationUtil.getLocalMessage("sviNalozi", GenericBassxConstants.Psd2.MESSAGE_BUNDLE);
////        }
////        if (getValueFromFlash("sifraValute") != null) {
////            String sifraValute = (String) getValueFromFlash("sifraValute");
////            if (StringUtils.isNotBlank(sifraValute)) {
////                odabranaValuta = sifraValute;
////            }
////            else {
////                odabranaValuta = null;
////            }
//		//praznimo flash za mogucu iducu sifru valute
////            putValueInFlash("sifraValute", null);
////        }
////        else {
////            odabranaValuta = null;
////        }
//
////		resetNepotpisani();
////		filtrirajNaloge();
//		oznaciSve = false;
//		potpisaniNalozi = new ArrayList<>();
//
//	}
//
//
//	public void dohvatiPartijeOvlastenja(){
//		for (WebKorisnikRacunDto dto : loginBean.getOvlastenja()){
//			partijeOvlastenja.add(dto.getPartijaDto().getSifra());
//		}
//	}
//
//	public void checkiraj(){
//		int i = 0;
//		for (TppNalogPlatniDto dto : nepotpisaniNalozi){
//
//		}
//	}
//
//	public List<TppNalogPlatniDto> getNepotpisaniNalozi() {
//		return nepotpisaniNalozi;
//	}
//
//	public void setNepotpisaniNalozi(List<TppNalogPlatniDto> nepotpisaniNalozi) {
//		this.nepotpisaniNalozi = nepotpisaniNalozi;
//	}
//
//	//Kraj dijela za potpis
//
//
//
//
//
///*
//	private static class ComparatorTppNalogPlatniDto implements Comparator<TppNalogPlatniDto> {
//		public int compare(TppNalogPlatniDto o1, TppNalogPlatniDto o2) {
//			int result = 0;
//			if (o1 != null && o2 != null) {
//				if (o1.getSifra() > o2.getSifra()) result = 1;
//				if (o2.getSifra() > o1.getSifra()) result = -1;
//			}
//			return result;
//		}
//	}*/
//
//	public List<TppNalogPlatniDto> getOdabraniNalozi() {
//		return odabraniNalozi;
//	}
//
//	public void setOdabraniNalozi(List<TppNalogPlatniDto> odabraniNalozi) {
//		this.odabraniNalozi = odabraniNalozi;
//	}
//
//	public void brisanjeNaloga() {
//		try{
////    		String key = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("rowIndex");
//			if(sifraTmp != null){
//				TppNalogPlatniDto zaBrisanje = null;
//				for(TppNalogPlatniDto nal : nepotpisaniNalozi){
//					if(nal.getSifra().compareTo(sifraTmp) == 0){
//						zaBrisanje = nal;
//						break;
//					}
//				}
//				if (zaBrisanje == null || zaBrisanje.getSifra() == null) throw new AbitRuleException(InternationalizationUtil.getLocalMessage("ibBrisanjeNalogaNalogNull", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
////TODO				nalogBean.obrisiNalog(zaBrisanje);
//				showInfo(InternationalizationUtil.getLocalMessage("ibBrisanjeNalogaInfoOk", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//				resetNepotpisani();
//			}
//		} catch (AbitRuleException are) {
//			showError(are.getMessage());
//			//showError(InternationalizationUtil.getLocalMessage("ibPotpisNalogaBrisiNalogError", new String[]{e.getMessage()}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//		}
//	}
//
//	public String pregledajNalog() {
//		try{
//			// otvaranje forme za unos deviznog priljeva
//			putValueInFlash("inputFormVisible", GenericController.divFormVisible);
//			// punjenje deviznog naloga DTO
//			String key = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("rowIndex");
//			putValueInFlash("nalog", findDtoByPrimaryKey(key));
//			// redirect na devizni priljev
//			return "/app/platniNalog.xhtml?faces-redirect=true";
//		} catch (Exception e) {
//			showError(InternationalizationUtil.getLocalMessage("controller_ibPredlozakIzmijeniPredlozakError", new String[]{e.getMessage()}, GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//			return "";
//		}
//	}
//
//	public void filtrirajNaloge(){
//		if (StringUtils.compare(odabraniIban, InternationalizationUtil.getLocalMessage("sviNalozi", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)) == 0){
//			nepotpisaniNalozi.clear();
//			for (TppNalogPlatniDto nalog : nepotpisaniNalozi){
//				nepotpisaniNalozi.add(nalog);
//			}
//		}
//		else if (odabraniIban != null && odabranaValuta != null){
//			nepotpisaniNalozi.clear();
//			for (TppNalogPlatniDto nalog : nepotpisaniNalozi){
//				if (StringUtils.compare(odabraniIban, nalog.getIbanZaduzenja()) == 0 && StringUtils.compare(odabranaValuta, nalog.getSifraValute()) == 0)
//					nepotpisaniNalozi.add(nalog);
//			}
//		}
//		odabraniIban = null;
//	}
//
//	public List<SelectItem> getListaDatoteka() {
//		return listaDatoteka;
//	}
//
//	public void setListaDatoteka(List<SelectItem> listaDatoteka) {
//		this.listaDatoteka = listaDatoteka;
//	}
//
//	public List<SelectItem> getListaDatotekaFilter() {
//		return listaDatoteka != null ? listaFilter(listaDatoteka) : listaDatoteka;
//	}
//
//	public void azurirajListuNaloga(){
//		filterDto = new TppNalogPlatniDto();
////		filterDto.setSifraKomitenta(null);
////		filterDto.setZaPotpis(Boolean.TRUE);
//		if(odabranaDatoteka != null) {
//			filterDto.setSifraDatoteke(odabranaDatoteka);
//		}
////		try{
////TODO			nepotpisaniNalozi = ibNalogDao.filterList(getFilterDto(), true, true);
//		setOznaciSve(Boolean.FALSE);
//		selectAllNone();
////		} catch (AbitRuleException are) {
////			showError(are);
////		}
//	}
//
//	public Integer getOdabranaDatoteka() {
//		return odabranaDatoteka;
//	}
//
//	public void setOdabranaDatoteka(Integer odabranaDatoteka) {
//		this.odabranaDatoteka = odabranaDatoteka;
//	}
//
//	private void dohvatListeNepotpisanih(){
////		try{
////TODO			nepotpisaniNalozi = ibNalogDao.filterList(getFilterDto(), true, true);
////		} catch (AbitRuleException are) {
////			showError(are);
////		}
//	}
//
//	public List<SelectItem> listaFilter(List<SelectItem> list) {
//		List<SelectItem> l = new ArrayList<>() ;
//		l.addAll(list);
//		l.add(new SelectItem(null, InternationalizationUtil.getLocalMessage("odaberite", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)));
//		return l;
//	}
//
//	public List<TppNalogPlatniDto> getNalogPotpisali() {
//		return nalogPotpisali;
//	}
//
//	public void setNalogPotpisali(List<TppNalogPlatniDto> nalogPotpisali) {
//		this.nalogPotpisali = nalogPotpisali;
//	}
//
//	public List<Integer> getPartijeOvlastenja() {
//		return partijeOvlastenja;
//	}
//
//	public void setPartijeOvlastenja(List<Integer> partijeOvlastenja) {
//		this.partijeOvlastenja = partijeOvlastenja;
//	}
//
//	public String getBase24SignValue() {
//		return base24SignValue;
//	}
//
//	public void setBase24SignValue(String base24SignValue) {
//		this.base24SignValue = base24SignValue;
//	}
//
//	public boolean getStopSmartCheck() {
//		return stopSmartCheck;
//	}
//
//	public void setStopSmartCheck(boolean stopSmartCheck) {
//		this.stopSmartCheck = stopSmartCheck;
//	}
//
//	public List<String> getFilter() {
//		return filter;
//	}
//
//	public void setFilter(List<String> filter) {
//		this.filter = filter;
//	}
//
//	public String getSelectedFilter() {
//		return selectedFilter;
//	}
//
//	public void setSelectedFilter(String selectedFilter) {
//		this.selectedFilter = selectedFilter;
//	}
//
//	public List<TppNalogPlatniDto> getPotpisaniNalozi() {
//		return potpisaniNalozi;
//	}
//
//	public void setPotpisaniNalozi(List<TppNalogPlatniDto> potpisaniNalozi) {
//		this.potpisaniNalozi = potpisaniNalozi;
//	}
//
////	public List<WebPotpisNalogaHeaderDto> getNepotpisaniNaloziGroup() {
////		return nepotpisaniNaloziGroup;
////	}
////
////	public void setNepotpisaniNaloziGroup(List<WebPotpisNalogaHeaderDto> nepotpisaniNaloziGroup) {
////		this.nepotpisaniNaloziGroup = nepotpisaniNaloziGroup;
////	}
//
//	public List<Map.Entry<String, String>> getInfoZaPotpis() {
////		Set<Map.Entry<String, String>> infoSet = new HashSet<>();
//		Map<String, String> infoMap = new HashMap<>();
//		if(infoOznacenihNaloga != null) {
//			infoMap.put("Broj označenih naloga:", brojOznacenihNaloga != null ? brojOznacenihNaloga.toString() : "");
//			String infoIznosi = "";
//			for (Map.Entry<String, BigDecimal> info : infoOznacenihNaloga.entrySet()) {
//				infoIznosi = infoIznosi + new DecimalFormat("##,###,##0.00", new DecimalFormatSymbols(new Locale("hr","HR"))).format(info.getValue())+" "+info.getKey()+"\r\n";
//			}
//			infoIznosi = StringUtils.removeEnd(infoIznosi,"\n");
//			infoMap.put("Ukupno iznos označenih naloga:",infoIznosi);
//			infoMap.put("Ukupno iznos naknada:", sumaOznacenoNaknada != null ? new DecimalFormat("##,###,##0.00", new DecimalFormatSymbols(new Locale("hr","HR"))).format(sumaOznacenoNaknada) + " HRK" : "");
//		}
//		Set<Map.Entry<String,String>> infoSet = infoMap.entrySet();
//		return new ArrayList<Map.Entry<String,String>>(infoSet);
//	}
//
//	public Boolean getShowChallengeResponse(){
//		Boolean res = Boolean.FALSE;
//		res = loginBean.getPrijava() instanceof WebPrijavaSxsToken || loginBean.getPrijava() instanceof WebPrijavaSoftToken || loginBean.getPrijava() instanceof WebPrijavaUnsecured ? Boolean.TRUE : Boolean.FALSE;
//		return res;
//	}
//
//	public String getPackedBlock() {
//		return packedBlock;
//	}
//
//	public void setPackedBlock(String packedBlock) {
//		this.packedBlock = packedBlock;
//	}
//
//	@Override
//	public List<SelectItem> getListaIban() {
//		return listaIban;
//	}
//
//	@Override
//	public void setListaIban(List<SelectItem> listaIban) {
//		this.listaIban = listaIban;
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
//	public String getOdabranaValuta() {
//		return odabranaValuta;
//	}
//
//	public void setOdabranaValuta(String odabranaValuta) {
//		this.odabranaValuta = odabranaValuta;
//	}
//
//	public List<String> getTempListaIban() {
//		return tempListaIban;
//	}
//
//	public void setTempListaIban(List<String> tempListaIban) {
//		this.tempListaIban = tempListaIban;
//	}
//
//	public Integer getSifraTmp() {
//		return sifraTmp;
//	}
//
//	public void setSifraTmp(Integer sifraTmp) {
//		this.sifraTmp = sifraTmp;
//	}
//
//	public String getPkiPotpis() {
//		return pkiPotpis;
//	}
//
//	public void setPkiPotpis(String pkiPotpis) {
//		this.pkiPotpis = pkiPotpis;
//	}
//}
