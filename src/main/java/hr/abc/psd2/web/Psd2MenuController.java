//package hr.abc.psd2.web;
//
//import hr.abit.awf.menu.AbitMenuItem;
//import hr.abc.psd2.exception.AbitRuleException;
//import hr.abit.b3.biz.core.util.Bassx2Constants;
//import hr.abit.b3.biz.core.util.Bassx2Message;
//import hr.abit.b3.biz.core.util.Bassx2MinDatabaseUtil;
//import hr.abit.b3.biz.ib.bean.AuthenticationUtilBean;
//import hr.abit.b3.biz.ib.bean.WebPrijavaBean;
//import hr.abit.b3.biz.ib.util.AuthenticationUtils;
//import hr.abit.b3.biz.platni.devizno.util.DeviznoUtil;
//import hr.abit.b3.biz.security.WebLoginBean;
//import hr.abit.b3.entity.ib.IBPrijavaOdjava;
//import hr.abit.b3.web.core.GenericMenuController;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import hr.abc.psd2.bean.Psd2MenuBean;
//
//import javax.annotation.PostConstruct;
//import javax.ejb.EJB;
//import javax.enterprise.context.SessionScoped;
//import javax.faces.context.FacesContext;
//import javax.inject.Inject;
//import javax.inject.Named;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.servlet.http.HttpServletRequest;
//import java.io.Serializable;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Level;
//
//@Named
//@SessionScoped
//public class Psd2MenuController extends GenericMenuController implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//    private static final Logger logger = LoggerFactory.getLogger(Psd2MenuController.class);
//
//    @EJB
//    private Psd2MenuBean izbornikBean;
//
//    @Inject
//    private AuthenticationUtilBean ibUtilBean;
//
//    @Inject
//    private WebLoginBean loginBean;
//    @Inject
//    private WebPrijavaBean prijavaBean;
//
//    private @Inject
//    EntityManager em;
//    private String environment;
//
//    private List<AbitMenuItem> menu;
//    private List<AbitMenuItem> miniMenu;
//
//    @PostConstruct
//    public void init() {
//        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
//        String hostname = request.getLocalName();
//        String dbname = Bassx2MinDatabaseUtil.getDbSchema(em);
//        if (Bassx2MinDatabaseUtil.isProdukcija(em))
//            environment = "PRODUKCIJA";
//        else if (Bassx2MinDatabaseUtil.isAnaliza(em))
//            environment = "ANALIZA";
//        else if (Bassx2MinDatabaseUtil.isTest(em))
//            environment = "TEST";
//        else
//            environment = "Razvoj";
//        environment = environment + " (" + hostname + "-" + dbname + ")";
//
//        log.info("END POSTCONSTRUCT");
//
//        setMenu();
//    }
//
//    @Override
//    public List<AbitMenuItem> getMenu() {
//        return menu;
//    }
//
//    public void setMenu(){
////        try {
////            List<String> listUsluga = new ArrayList<>(AuthenticationUtils.resolveSetUsluga(loginBean.getOvlastenja(), true));
//        List<String> listUsluga = new ArrayList<>();
////            if(listUsluga.isEmpty()){
////                Bassx2Message.addMessage(new AbitRuleException("Za odabrano ovlaštenje ne postoji nijedna dodijeljena usluga"));
////            }
//        menu = izbornikBean.createMenu(listUsluga, loginBean.getIbGlobals(), false, loginBean.isIbPravneOsobe());
////        }catch(AbitRuleException ex){
////            Bassx2Message.addMessage(ex);
////        }
//    }
//
//    @Override
//    public List<AbitMenuItem> getMiniMenu() {
//        return miniMenu;
//    }
//
//    public void setMiniMenu() {
//        try {
//            miniMenu = izbornikBean.createMenu(new ArrayList<>(AuthenticationUtils.resolveSetUsluga(loginBean.getOvlastenja(), true)), loginBean.getIbGlobals(), true, loginBean.isIbPravneOsobe());
//        }catch(AbitRuleException ex){
//            Bassx2Message.addMessage(ex);
//        }
//    }
//
//    @Override
//    public String getApplication() {
//        return "Internet bankarstvo";
//    }
//
//    @Override
//    public String getEnvironment() {
//        return environment;
//    }
//
//    public String getUser() {
//        String user = loginBean.getIbKorisnik().getNazivPrijavljenogKorisnika();
//        return user;
//    }
//
//    public String getOvlastenje() {
//        String ovlastenje = loginBean.getOdabraniVlasnikRacuna().getNaziv();
//        return ovlastenje;
//    }
//
//
//    public String logout(){
//        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
//        try {
//            ibUtilBean.logirajPrijavuOdjavu(loginBean.getPrijava(), IBPrijavaOdjava.VRSTA_ODJAVA, true);
//        }catch(AbitRuleException ex){
//            java.util.logging.log.getLogger(Psd2MenuController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return "/login.xhtml?odjava=true&faces-redirect=true";
//    }
//
//    @Override
//    public String getIbKorisnik() {
//        String ibKorisnik = loginBean != null && loginBean.getIbKorisnik() != null ? (loginBean.isIbPravneOsobe() && loginBean.getOdabraniVlasnikRacuna() != null ? loginBean.getOdabraniVlasnikRacuna().getNaziv() : loginBean.getIbKorisnik().getNazivFizickeOsobe() ) : "";
//        if (StringUtils.isNotBlank(ibKorisnik)) {
//            String ibKorisnikTxt = DeviznoUtil.splitTextPower(ibKorisnik, 2, 60);
//            if (StringUtils.isNotBlank(ibKorisnikTxt)) {
//                String[] ibKorisnikSplit = ibKorisnikTxt.split(Bassx2Constants.Core.NEW_LINE);
//                if (ibKorisnikSplit != null && ibKorisnikSplit.length >= 2) {
//                    setIbKorisnik1Row(ibKorisnikSplit[0]);
//                    setIbKorisnik2Row(ibKorisnikSplit[1]);
//                } else {
//                    setIbKorisnik1Row(ibKorisnikTxt);
//                    setIbKorisnik2Row(null);
//                }
//            } else {
//                setIbKorisnik1Row(ibKorisnik);
//                setIbKorisnik2Row(null);
//            }
//        } else {
//            setIbKorisnik1Row(ibKorisnik);
//            setIbKorisnik2Row(null);
//        }
//        return ibKorisnik;
//    }
//
//    @Override
//    public String getIbOsoba() {
//        return loginBean != null && loginBean.getIbKorisnik() != null ? loginBean.getIbKorisnik().getNazivFizickeOsobe() : "";
//    }
//
//    public void invalidateSession() {
//        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
//    }
//
//
//}
