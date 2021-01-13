//package hr.abc.psd2.web;
//
//import hr.abc.psd2.exception.AbitRuleException;
//import hr.abc.psd2.util.GenericBassxConstants;
//import hr.abc.psd2.util.InternationalizationUtil;
//import hr.abc.psd2.dto.WebKorisnikRacunDto;
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
//public class PovuciNalogMultiController extends PotpisNalogController {
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
//	public PovuciNalogMultiController() {
//		super();
//		setIsPotpis(Boolean.FALSE);
//
//	}
//
//	@PostConstruct
//	public void init() {
//		super.init();
//
//		//Tu dohvatimo naloge id = sifra datoteke u autorizaciji
//		if(refTppNaloga != null && !StringUtils.isEmpty(refTppNaloga)){
//			setOznaciSve(Boolean.FALSE);
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
//					zaPovlacenje = new ArrayList<>();
//					List<TppNalogPlatniDto> naloziTpp = nalogDao.getTppNaloziBySifraList(tppSifraList, false);
//					if (naloziTpp != null) {
//						for (TppNalogPlatniDto tppNal : naloziTpp) {
//							tppNal.setAuthorization(mapAuth.get(tppNal.getSifra()));
//							createDto = tppNal;
//							initNalog();
//							createDto.resolveStatusAndInfo(false);
//							if(tppNal.getAuthorization().isExpired(LocalDateTime.now())){
//								showError(InternationalizationUtil.getLocalMessage("potpisNalogaError_authExpired", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
//							}
//							redirectLink = redirectLink == null ? tppNal.getTppRedirectUri() : redirectLink;
//							if (createDto.getSifraPartijeZaduzenja() != null) {
//								zaPovlacenje.add(createDto);
//							}
//						}
//						//Provjerimo listu naloga radi BB
//						zaPovlacenje = Psd2Util.resolveNepotpisani(zaPovlacenje);
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
//		potpisaniNalozi = new ArrayList<>();
//
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
//
//}
