package hr.abc.psd2.security;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class Globals implements Serializable {

    private static final long serialVersionUID = 1L;

    private static volatile Map<String, Globals> globalsMap = new ConcurrentHashMap<>();

    private Integer sifraLikvidatora;
    private String sifraPoslovnice;
    private Integer sifraModula;
    private Date vrijemePrijave;
    private String nazivLikvidatora;
    private Boolean produkcija;

    private Globals() {
    }

    public static void updateGlobals(String sessionId, Integer sifraLikvidatora, String sifraPoslovnice, Integer sifraModula, String nazivLikvidatora, Boolean isProd) {
        Globals globals = globalsMap.get(sessionId);
        if (globals == null) globals = new Globals();
        globals.sifraLikvidatora = sifraLikvidatora;
        globals.sifraPoslovnice = sifraPoslovnice;
        globals.sifraModula = sifraModula;
        globals.vrijemePrijave = new Date();
        globals.nazivLikvidatora = nazivLikvidatora;
        globals.produkcija = isProd;
        globalsMap.put(sessionId, globals);
    }

    public static void removeGlobals(String sessionId) {
        globalsMap.remove(sessionId);
    }

    public static Globals createBackgroundGlobals(Integer sifraLikvidatora, String sifraPoslovnice, Integer sifraModula, Boolean isProd) {
        Globals backgroundGlobals = new Globals();
        backgroundGlobals.sifraLikvidatora = sifraLikvidatora;
        backgroundGlobals.sifraPoslovnice = sifraPoslovnice;
        backgroundGlobals.sifraModula = sifraModula;
        backgroundGlobals.vrijemePrijave = new Date();
        backgroundGlobals.produkcija = isProd;
        return backgroundGlobals;
    }

    @Deprecated
    public static Globals createBackgroundGlobals(Integer sifraLikvidatora, String sifraPoslovnice, Integer sifraModula) {
        return Globals.createBackgroundGlobals(sifraLikvidatora, sifraPoslovnice, sifraModula, false);
    }

    public static Globals getGlobals() {
        Globals result = null;
        String sessionId = getSessionId();
        // ovo je uvedeno jer remote poziv, ib, bg. task i sl. nema sessionId
        if (sessionId != null) {
            result = globalsMap.get(sessionId);
        }
        return result;
    }

    public static Globals getGlobals(String sessionId) {
        Globals g = globalsMap.get(sessionId);
        Globals result = Globals.clone(g);
        return result;
    }

    private static String getSessionId() {
        String result = null;
        if (FacesContext.getCurrentInstance() != null) {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            if (request != null && request.getSession() != null) {
                result = request.getSession().getId();
            }
        }
        return result;
    }

    private static Globals clone(Globals globals) {
        Globals result = null;
        if (globals != null) {
            result = new Globals();
            result.nazivLikvidatora = globals.getNazivLikvidatora();
            result.sifraLikvidatora = globals.getSifraLikvidatora();
            result.sifraModula = globals.getSifraModula();
            result.sifraPoslovnice = globals.getSifraPoslovnice();
            result.vrijemePrijave = globals.getVrijemePrijave();
            result.produkcija = globals.produkcija;
        }
        return result;
    }

    public Integer getSifraLikvidatora() {
        return sifraLikvidatora;
    }

    public String getSifraPoslovnice() {
        return sifraPoslovnice;
    }

    public Integer getSifraModula() {
        return sifraModula;
    }

    public Date getVrijemePrijave() {
        return vrijemePrijave;
    }

    public String getNazivLikvidatora() {
        return nazivLikvidatora;
    }

    public Boolean isProdukcija() {
        return produkcija;
    }

}
