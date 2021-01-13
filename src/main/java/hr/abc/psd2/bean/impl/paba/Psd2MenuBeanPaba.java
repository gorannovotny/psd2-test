package hr.abc.psd2.bean.impl.paba;
//package hr.abc.psd2.bean;
//
//import hr.abit.awf.menu.AbitMenuItem;
//import hr.abc.psd2.util.Bassx2Constants;
//import hr.abc.psd2.util.GenericBassxConstants;
//import hr.abc.psd2.util.InternationalizationUtil;
//import hr.abc.psd2.util.MathFunctions;
//import hr.abc.psd2.security.Globals;
//import hr.abit.b3.biz.security.WebLoginBean;
//import hr.abit.b3.entity.ib.IBUsluga;
//
//import javax.annotation.Resource;
//import javax.annotation.security.DeclareRoles;
//import javax.ejb.SessionContext;
//import javax.ejb.Stateless;
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@DeclareRoles({"Abit"})
//
//public class Psd2MenuBean {
//
//    @Inject
//    EntityManager em;
//
//    @Resource
//    SessionContext ctx;
//
//	@Inject
//	private WebLoginBean loginBean;
//
//
//    public List<AbitMenuItem> createMenu(List<String> setUsluga, Globals globals, boolean isMiniMenu, boolean isPravnaOsoba) {
//		List<Object> tree = new ArrayList<>();
//		List<AbitMenuItem> menu = new ArrayList<>();
////		if(!setUsluga.isEmpty()) {
////			tree = getMenuTree(setUsluga, globals, isMiniMenu, isPravnaOsoba);
////			menu = buildRecursiveMenu(tree, null, isMiniMenu);
////		}
//        //dodavanje ovlastenja ako ima
////        List<AbitMenuItem> submenuOvlastenja = new ArrayList<>();
////		if (isPravnaOsoba && !isMiniMenu && AuthenticationUtils.isAdminIbPravne(loginBean.getOvlastenja())){
////			submenuOvlastenja.add(administracija());
////		}
////		if(!isMiniMenu && loginBean.getVlasniciRacuna() != null && loginBean.getVlasniciRacuna().size() > 1){
////			submenuOvlastenja.add(promjenaVlasnikaRacuna());
////		}
////        if (submenuOvlastenja != null && !submenuOvlastenja.isEmpty()) {
////            menu.add(menu.size(), ovlastenja(submenuOvlastenja));
////        }
////
//		return menu;
//    }
//
//	private AbitMenuItem ovlastenja(List<AbitMenuItem> submenuOvlastenja) {
//		AbitMenuItem items = new AbitMenuItem();
//		items.setId(null);
//		items.setLabel(InternationalizationUtil.getLocalMessage("ovlastenja", Bassx2Constants.Authentication.MESSAGE_BUNDLE));
//		items.setOutcome(null);
//		items.setDisabled(false);
//		items.setTitle(null);
//		items.setSubmenu(submenuOvlastenja);
//		return items;
//	}
//
//    private AbitMenuItem obavijesti(boolean isPravnaOsoba) {
//        AbitMenuItem items = new AbitMenuItem();
//        items.setId(null);
//        items.setLabel(InternationalizationUtil.getLocalMessage("obavijest", Bassx2Constants.Authentication.MESSAGE_BUNDLE));
//        items.setOutcome(null);
//        items.setDisabled(false);
//        items.setTitle(null);
//        items.setSubmenu(submenuObavijesti(isPravnaOsoba));
//        return items;
//    }
//
//	private AbitMenuItem administracija() {
//		AbitMenuItem item = new AbitMenuItem();
//		item.setId(null);
//		item.setLabel(InternationalizationUtil.getLocalMessage("administracija", Bassx2Constants.Authentication.MESSAGE_BUNDLE));
//		item.setOutcome("/app/ovlasti.xhtml");
//		item.setDisabled(false);
//		item.setTitle(null);
//		item.setSubmenu(null);
//		return item;
//	}
//
//	private AbitMenuItem promjenaVlasnikaRacuna() {
//		AbitMenuItem item = new AbitMenuItem();
//		item.setId(null);
//		item.setLabel(InternationalizationUtil.getLocalMessage("menuOdabirOvlastenja", Bassx2Constants.Authentication.MESSAGE_BUNDLE));
//		item.setOutcome("/app/odabirKorisnika.xhtml?faces-redirect=true");
//		item.setDisabled(false);
//		item.setTitle(null);
//		item.setSubmenu(null);
//		return item;
//	}
//
//    private List<AbitMenuItem> submenuObavijesti(boolean isPravnaOsoba) {
//        List<AbitMenuItem> submenuObavijesti = new ArrayList<>();
//        //obavijesti
//        AbitMenuItem obavijesti = new AbitMenuItem();
//        obavijesti.setId(null);
//        obavijesti.setLabel(InternationalizationUtil.getLocalMessage("obavijest", Bassx2Constants.Authentication.MESSAGE_BUNDLE));
//        obavijesti.setOutcome("/app/obavijesti.xhtml");
//        obavijesti.setDisabled(false);
//        obavijesti.setTitle(null);
//        obavijesti.setSubmenu(null);
//        submenuObavijesti.add(obavijesti);
//        //korisničke upute
//        AbitMenuItem upute = new AbitMenuItem();
//        upute.setId(null);
//        upute.setLabel(InternationalizationUtil.getLocalMessage("korisnickaUputa", Bassx2Constants.Authentication.MESSAGE_BUNDLE));
//        if (isPravnaOsoba) {
//            upute.setOutcome("/app/korisnickaUputaPO.xhtml");
//        } else {
//            upute.setOutcome("/app/korisnickaUputaFO.xhtml");
//        }
//        upute.setDisabled(false);
//        upute.setTitle(null);
//        upute.setSubmenu(null);
//        submenuObavijesti.add(upute);
//        return submenuObavijesti;
//    }
//
//    private List<AbitMenuItem> buildRecursiveMenu(List<Object> tree, Integer parent, boolean isMiniMenu) {
//		List<Object> children = getChildren(tree, parent);
//		List<AbitMenuItem> items = new ArrayList<>();
//		for (Object obj : children) {
//			Object[] arr = (Object[]) obj;
//			AbitMenuItem item = new AbitMenuItem();
//	//			item.setId("menu_" + counter++);
//			String labelKey = (String) arr[2];
//			String label = InternationalizationUtil.getLocalMessage(isMiniMenu ? labelKey + "_mini" : labelKey, GenericBassxConstants.Authentication.MESSAGE_BUNDLE);
//			item.setLabel(label);
//			item.setSubmenu(buildRecursiveMenu(tree, MathFunctions.resolveOracleNumberToInteger(arr[0]), isMiniMenu));
//			if (item.getSubmenu().isEmpty()) {
//				item.setOutcome((String) arr[1]);
//			} else {
//				item.setOutcome(null); // AWF ne gleda da li ima djecu, nego da li je Outcome prazno
//			}
//	//            String roles = (String) arr[5];
//	//            if (StringUtils.isNotBlank(roles)) {
//	//                if (!StringUtils.startsWith(roles, "NOT_")) {
//	//                    roles = roles + ";Abit";
//	//                }
//	//                item.setDisabled(true);
//	//                item.setTitle("Not in role(s) " + roles);
//	//                for (String rola : roles.split(";")) {
//	//                    try {
//	//                        if (ctx.isCallerInRole(rola)) {
//	//                            item.setDisabled(false);
//	//                            item.setTitle(null);
//	//                            break;
//	//                        }
//	//                    } catch (IllegalStateException e) {
//	//                        LoggerFactory.getLogger(this.getClass().getName()).warn("XXXX Rola " + rola + " nije u @DeclareRoles XXXX");
//	//                    }
//	//                }
//	//            }
//			Boolean alwaysVisible = true;
//			if (arr[4] == null || arr[4] == BigDecimal.ZERO) {
//				alwaysVisible = false;
//			}
//			if (item.getDisabled() == null) {
//				item.setDisabled(false);
//			}
//			if (!item.getDisabled() || alwaysVisible) {
//				items.add(item);
//			}
//
//		}
//		return items;
//    }
//
//    private List<Object> getChildren(List<Object> tree, Integer parent) {
//		List<Object> sub = new ArrayList<>();
//		for (Object object : tree) {
//			Object[] arrobj = (Object[]) object;
//			Integer newParent = (Integer) arrobj[3];
//			if ((parent == null && newParent == null) || (newParent != null && newParent.equals(parent))) {
//				sub.add(object);
//			}
//		}
//		return sub;
//    }
//
//    private List<Object> getMenuTree(List<String> setUsluga, Globals globals, boolean isMinMenu, boolean isPravnaOsoba) {
//    	String sql = "";
//    	if (isMinMenu) {
//    		sql = "select distinct izb.sifra_izb, izb.link_izb, izb.naziv_izb, NULL\\:\\:INTEGER, izb.visib_izb, izb.roles_izb, izb.order_izb, u.prika_usl " +
//    			  "from izbornik izb " +
//    			  "inner join ib_usluga_izbornik uiz on uiz.izbor_uiz = izb.sifra_izb " +
//    			  "inner join ib_usluga u on u.sifra_usl = uiz.uslug_uiz " +
////    			  "inner join ib_korisnik_racun_usluga kru on kru.ibusl_ibu = u.sifra_usl " +
////    			  "inner join ib_korisnik_racun ibkr on ibkr.sifra_ibr = kru.ibrac_ibu " +
////    			  "inner join ib_komitent ibk on ibk.sifra_kom = ibkr.komit_ibr " +
//    			  "where izb.modul_izb = :modul " +
//				  "and izb.visib_izb = '1' "+
////    			  "and ibkr.sifra_ibr in :listaOvlastenja ";
//				  "and u.sifra_usl in :setUsluga ";
//			if(isPravnaOsoba) sql+= "and izb.naziv_izb in ('izbornikPregledRacuna','izbornikNoviNalog','izbornikKupoprodaja') ";
//			else sql += "and izb.naziv_izb in ('izbornikPregledRacuna','izbornikNoviNalog','izbornikKupoprodaja','izbornikStednjaPregled') ";
//			sql += "order by 4 nulls first, 7, 1 ";
//    	} else {
//    		sql = "select distinct izb.sifra_izb, izb.link_izb, izb.naziv_izb, izb.rodit_izb, izb.visib_izb, izb.roles_izb, izb.order_izb, u.prika_usl " +
//    			  "from izbornik izb " +
//    			  "inner join ib_usluga_izbornik uiz on uiz.izbor_uiz = izb.sifra_izb " +
//    			  "inner join ib_usluga u on u.sifra_usl = uiz.uslug_uiz " +
////    			  "inner join ib_korisnik_racun_usluga kru on kru.ibusl_ibu = u.sifra_usl " +
////    			  "inner join ib_korisnik_racun ibkr on ibkr.sifra_ibr = kru.ibrac_ibu " +
////    			  "inner join ib_komitent ibk on ibk.sifra_kom = ibkr.komit_ibr " +
//    			  "where izb.modul_izb = :modul " +
//				  "and izb.visib_izb = '1' "+
////    			  "and ibkr.sifra_ibr in :listaOvlastenja " +
//				  "and u.sifra_usl in :setUsluga " +
//    			  "order by 4 nulls first, 7, 1 ";
//    	}
//		Query query = em.createNativeQuery(sql);
//		query.setParameter("modul", GenericBassxConstants.Core.MODUL_SIFRAMOD_IB_MENU);
//		query.setParameter("setUsluga", setUsluga != null && !setUsluga.equals("()") ? setUsluga : "(-1)");
//		@SuppressWarnings("unchecked")
//		List<Object> objList = query.getResultList();
//		List<Object> objListFilter = new ArrayList<Object>();
//		Set<Integer> parents = new HashSet<>();
//		for(Object obj : objList){
//			Object[] arrobj = (Object[]) obj;
//			if(arrobj[7] != null){
//				if(arrobj[7].toString().equals(IBUsluga.PRIKAZ_PRAVNI) && globals.getSifraLikvidatora().compareTo(GenericBassxConstants.Core.SIFRALIK_IBPRAVNE_LIKVIDATOR) == 0){
//					objListFilter.add(obj);
//				}
//				if(arrobj[7].toString().equals(IBUsluga.PRIKAZ_GRADANI) && globals.getSifraLikvidatora().compareTo(GenericBassxConstants.Core.SIFRALIK_IBG_LIKVIDATOR) == 0){
//					objListFilter.add(obj);
//				}
//			}else{
//				objListFilter.add(obj);
//			}
//			if(arrobj[3] != null){
//				parents.add((Integer)arrobj[3]);
//			}
//		}
//
//		//Buduci da je usluga vezana na subMenu, trebamo dohvatiti i roditelje od submenua
//		if (parents != null && !parents.isEmpty()) {
//			sql = "select distinct sifra_izb, link_izb, naziv_izb, rodit_izb, visib_izb, roles_izb, order_izb " +
//					"from izbornik "+
//					"where modul_izb = :modul " +
//					"and rodit_izb is null " +
//					"and sifra_izb in :listMainMenu " +
//					"order by order_izb ";
//			query = em.createNativeQuery(sql);
//			query.setParameter("modul", GenericBassxConstants.Core.MODUL_SIFRAMOD_IB_MENU);
//			query.setParameter("listMainMenu", parents);
//			@SuppressWarnings("unchecked")
//			List<Object> objParents = query.getResultList();
//			for(Object obj : objParents){
//				objListFilter.add(obj);
//			}
//		}
//		return objListFilter;
//    }
//}
