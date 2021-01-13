package hr.abc.psd2.model.entity.core;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Klasa entiteta za tablicu ib_ident
 *
 * @author Kristijan Novak
 *
 */
@Entity
@Table(name = "ib_ident")
public class IBIdentifikacija {

    public static String STATUS_AKTIVAN = "A";
    public static String STATUS_BLOKIRAN = "B";
    public static String STATUS_NEAKTIVAN = "N";
    public static String STATUS_ZA_VERIFIKACIJU = "V";
    public static String STATUS_DEAKTIVIRAN = "D";

    public static String TIP_AUTH_SMARTTOKEN = "S";
    public static String TIP_AUTH_PKI_KARTICA = "K";
    public static String TIP_AUTH_TOKEN = "T";
    public static String TIP_AUTH_UNSECURED = "U";
    public static String TIP_AUTH_SOFT_TOKEN_MOBILE = "M";
    public static String TIP_AUTH_SOFT_TOKEN_MOBILE_NEW= "N";

    public static String TIP_AUTH_SMARTTOKEN_OPIS = "USB (smart) token";
    public static String TIP_AUTH_PKI_KARTICA_OPIS = "PKI Kartica";
    public static String TIP_AUTH_TOKEN_OPIS = "Sxs token";
    public static String TIP_AUTH_UNSECURED_OPIS = "U";
    public static String TIP_AUTH_SOFT_TOKEN_MOBILE_OPIS = "Mobile/Soft token";

    public static  String PRVA_REGISTRACIJA_INIT = "G";
    public static  String PRVA_REGISTRACIJA_REGISTER = "P";
    public static  String PRVA_REGISTRACIJA_CERT = "Z";

    public static final Integer PIN_BROJ_ZNAMENAKA = 6;

    public static final String CITACIDE_PAKET_CITAC = "1";
    public static final String CITACIDE_PAKET_BEZ_CITACA = "0";

    public static final String RAZLOG_ZAMJENE_NE_RADI = "NOTWORK";
    public static final String RAZLOG_ZAMJENE_GUBITAK_OSTECENJE = "GUBOST";
    public static final String RAZLOG_ZAMJENE_OSTALO = "OSTALO";

    //naknade - tarifne stavke
    public static final String TARST_NEVRACENI_TOKEN_NAKON_DEAKTIVACIJE = "SI215";
    public static final String TARST_TOKEN_ZA_DODATNOG_KORISNIKA = "SI214";
    public static final String TARST_NOVI_TOKEN_ZBOG_OSTECENJA_GUBITKA = "SI213";

    public static final String[] SXS_PREFIX = {"20", "36"};

    public static final Map<String, String> NEFIN_POLJA_OPIS = Collections
            .unmodifiableMap(new HashMap<String, String>() {
                {
                    put("sifraIde", "Šifra");
                    put("lockedIde", "Status zaključavanja");
                    put("lockTimeIde", "Vrijeme");
                    put("lockVrstaIde", "Vrsta");
                }
            });

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sifra_ide", nullable = false)
    private Integer sifraIde;

//    @Column(name = "statu_ide", length = 1, nullable = false)  //status
//    private String statuIde;

    @Column(name = "datun_ide", nullable = false)                //vrijeme unosa
    @Temporal(TemporalType.DATE)
    private Date vrijeIde;

    @JoinColumn(name = "komit_ide", referencedColumnName = "sifra_kom", nullable = true)    //vlasnik ident uređaja
    @OneToOne(fetch = FetchType.LAZY)
    private IBKorisnik ibkorIde;

    @Column(name = "datak_ide")
    @Temporal(TemporalType.DATE)
    private Date datakIde;

    @Column(name = "datde_ide")
    @Temporal(TemporalType.DATE)
    private Date datdeIde;

    @Column(name = "pinka_ide", length = 6, nullable = true)
    private String pinIde;

    @Column(name = "prvar_ide", length = 1, nullable = true)
    private String prvarIde;

    @Column(name = "tipid_ide", length = 1, nullable = false)
    private String tipidIde;

    @Column(name = "serij_ide", length = 20, nullable = true)
    private String serijIde;

    @Column(name = "sifex_ide", nullable = true)
    private Integer sifexIde;

    @Column(name = "citac_ide", length = 1, nullable = true)
    private String citacIde;

    @Column(name = "statu_ide", length = 1, nullable = true)
    private String statuIde;

    @Column(name = "lockd_ide", columnDefinition = "CHAR(1)", nullable = true)
    private String lockedIde;

    @Column(name = "lockt_ide", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date lockTimeIde;

    @Column(name = "lockv_ide", columnDefinition = "CHAR(1)", nullable = true)
    private String lockVrstaIde;

    @Column(name = "ibena_ide", length = 1, nullable = true)
    private String ibenaIde;

    @Column(name = "caena_ide", length = 1, nullable = true)
    private String caenaIde;

    public IBIdentifikacija() {
        super();
    }

    public Integer getSifraIde() {
        return sifraIde;
    }

    public void setSifraIde(Integer sifraIde) {
        this.sifraIde = sifraIde;
    }

    public Date getVrijeIde() {
        return vrijeIde;
    }

    public void setVrijeIde(Date vrijeIde) {
        this.vrijeIde = vrijeIde;
    }

    public IBKorisnik getIbkorIde() {
        return ibkorIde;
    }

    public void setIbkorIde(IBKorisnik ibkorIde) {
        this.ibkorIde = ibkorIde;
    }

    public Date getDatakIde() {
        return datakIde;
    }

    public void setDatakIde(Date datakIde) {
        this.datakIde = datakIde;
    }

    public Date getDatdeIde() {
        return datdeIde;
    }

    public void setDatdeIde(Date datdeIde) {
        this.datdeIde = datdeIde;
    }

    public String getPinIde() {
        return pinIde;
    }

    public void setPinIde(String pinIde) {
        this.pinIde = pinIde;
    }

    public String getTipidIde() {
        return tipidIde;
    }

    public void setTipidIde(String tipidIde) {
        this.tipidIde = tipidIde;
    }


    public String getSerijIde() {
        return serijIde;
    }

    public void setSerijIde(String serijIde) {
        this.serijIde = serijIde;
    }

    public String getPrvarIde() {
        return prvarIde;
    }

    public void setPrvarIde(String prvarIde) {
        this.prvarIde = prvarIde;
    }

    public Integer getSifexIde() {
        return sifexIde;
    }

    public void setSifexIde(Integer sifexIde) {
        this.sifexIde = sifexIde;
    }

    public String getCitacIde() {
        return citacIde;
    }

    public void setCitacIde(String citacIde) {
        this.citacIde = citacIde;
    }

    public String getStatuIde() {
        return statuIde;
    }

    public void setStatuIde(String statuIde) {
        this.statuIde = statuIde;
    }

    public String getLockedIde() {
        return lockedIde;
    }

    public void setLockedIde(String lockedIde) {
        this.lockedIde = lockedIde;
    }

    public Date getLockTimeIde() {
        return lockTimeIde;
    }

    public void setLockTimeIde(Date lockTimeIde) {
        this.lockTimeIde = lockTimeIde;
    }

    public String getLockVrstaIde() {
        return lockVrstaIde;
    }

    public void setLockVrstaIde(String lockVrstaIde) {
        this.lockVrstaIde = lockVrstaIde;
    }

    public String getIbenaIde() {
        return ibenaIde;
    }

    public void setIbenaIde(String ibenaIde) {
        this.ibenaIde = ibenaIde;
    }

    public String getCaenaIde() {
        return caenaIde;
    }

    public void setCaenaIde(String caenaIde) {
        this.caenaIde = caenaIde;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[sifraIde=" + sifraIde + "]";
    }

    public Map<String, String> getEntityParametarNameValue(){
        Map<String, String> res = new HashMap<>();
        Class<?> kls = this.getClass();
        Field[] fields = kls.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if(!NEFIN_POLJA_OPIS.containsKey(field.getName())) continue;
                res.put(field.getName(), field.get(this) != null ? field.get(this).toString() : "");
            }catch(IllegalAccessException ex){
                //Nista ne radimo
            }
        }
        return res;
    }
}
