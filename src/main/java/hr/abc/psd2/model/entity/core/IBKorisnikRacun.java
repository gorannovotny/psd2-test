package hr.abc.psd2.model.entity.core;

import java.lang.reflect.Field;
import java.math.BigDecimal;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Klasa entiteta za tablicu ib_korisnik_racun
 *
 * @author Vlatko Spoljaric
 *
 */
@Entity
@Table(name = "ib_korisnik_racun")
public class IBKorisnikRacun {
    public static String OZNAKA_YES = "Y";
    public static String OZNAKA_NO = "N";
    public static String STATUS_AKTIVAN = "A";
    public static String STATUS_NEAKTIVAN = "N";
    public static String STATUS_BLOKIRAN = "B";
    public static String STATUS_ZATVOREN = "Z";

    public static final Map<String, String> NEFIN_POLJA_OPIS = Collections
            .unmodifiableMap(new HashMap<String, String>() {
                {
                    put("sifraIbr", "Šifra");
                    put("isadmIbr", "Administrator");
                    put("statuIbr", "Status ovlaštenja");
                    put("datodIbr", "Početak ovlaštenja");
                    put("datdoIbr", "Datum zatvaranja");
                    put("komitIbr", "IB korisnik");
                    put("identIbr", "Autentikacijski uređaj");
                    put("likviIbr", "Likvidator");
                    put("partiIbr", "Šifra partije");
                }
            });

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sifra_ibr", nullable = false) //sifra ib_korisnik_racun
    private Integer sifraIbr;

    @Column(name = "datod_ibr", length = 25, nullable = true)
    @Temporal(TemporalType.DATE) //datum
    private Date datodIbr;

    @Column(name = "datdo_ibr", length = 25, nullable = true)
    @Temporal(TemporalType.DATE) //datum
    private Date datdoIbr;

    @Column(name = "isadm_ibr", length = 1, nullable = false) //flag isAdmin (Y, N)
    private String isadmIbr;

    @Column(name = "statu_ibr", length = 1, nullable = false) //status ib_korisnik_racun (N, A)
    private String statuIbr;

    @JoinColumn(name = "komit_ibr", referencedColumnName = "sifra_kom", nullable = true) //sifra ib_komitent (vanjski kljuc na tablicu ib_komitent)
    @ManyToOne(fetch = FetchType.LAZY)
    private IBKorisnik komitIbr;

    @JoinColumn(name = "ident_ibr", referencedColumnName = "sifra_ide", nullable = false) //sifra ib_ident (vanjski kljuc na tablicu ib_ident)
    @ManyToOne(fetch = FetchType.LAZY)
    private IBIdentifikacija identIbr;

    @JoinColumn(name = "parti_ibr", referencedColumnName = "sifra_par", nullable = false) //sifra partija (vanjski kljuc na tablicu partija)
    @ManyToOne(fetch = FetchType.LAZY)
    private Partija partiIbr;

    @Column(name = "potpi_ibr", length = 2) //potpis
    private String potpiIbr;

    @Column(name = "vrije_ibr", length = 25, nullable = false) //vrijeme
    @Temporal(TemporalType.TIMESTAMP)
    private Date vrijeIbr;

    @JoinColumn(name = "likvi_ibr", referencedColumnName = "sifra_lik", nullable = false) //sifra likvidator (vanjski kljuc na tablicu likvidator)
    @ManyToOne(fetch = FetchType.LAZY)
    private Likvidator likviIbr;

    @Column(name = "autor_ibr", nullable = false, columnDefinition = "DEC(17,2)")
    private BigDecimal autorIbr;

    @Column(name = "verif_ibr", nullable = false, columnDefinition = "DEC(17,2)")
    private BigDecimal verifIbr;

    public IBKorisnikRacun() {
        super();
    }

    public Integer getSifraIbr() {
        return sifraIbr;
    }

    public void setSifraIbr(Integer sifraIbr) {
        this.sifraIbr = sifraIbr;
    }

    public String getIsadmIbr() {
        return isadmIbr;
    }

    public void setIsadmIbr(String isadmIbr) {
        this.isadmIbr = isadmIbr;
    }

    public String getStatuIbr() {
        return statuIbr;
    }

    public void setStatuIbr(String statuIbr) {
        this.statuIbr = statuIbr;
    }

    public IBKorisnik getKomitIbr() {
        return komitIbr;
    }

    public void setKomitIbr(IBKorisnik komitIbr) {
        this.komitIbr = komitIbr;
    }

    public Partija getPartiIbr() {
        return partiIbr;
    }

    public void setPartiIbr(Partija partiIbr) {
        this.partiIbr = partiIbr;
    }

    public String getPotpiIbr() {
        return potpiIbr;
    }

    public void setPotpiIbr(String potpiIbr) {
        this.potpiIbr = potpiIbr;
    }

    public Date getDatodIbr() {
        return datodIbr;
    }

    public void setDatodIbr(Date datodIbr) {
        this.datodIbr = datodIbr;
    }

    public Date getDatdoIbr() {
        return datdoIbr;
    }

    public void setDatdoIbr(Date datdoIbr) {
        this.datdoIbr = datdoIbr;
    }

    public Date getVrijeIbr() {
        return vrijeIbr;
    }

    public void setVrijeIbr(Date vrijeIbr) {
        this.vrijeIbr = vrijeIbr;
    }

    public Likvidator getLikviIbr() {
        return likviIbr;
    }

    public void setLikviIbr(Likvidator likviIbr) {
        this.likviIbr = likviIbr;
    }

    public BigDecimal getAutorIbr() {
        return autorIbr;
    }

    public void setAutorIbr(BigDecimal autorIbr) {
        this.autorIbr = autorIbr;
    }

    public BigDecimal getVerifIbr() {
        return verifIbr;
    }

    public void setVerifIbr(BigDecimal verifIbr) {
        this.verifIbr = verifIbr;
    }

    public IBIdentifikacija getIdentIbr() {
        return identIbr;
    }

    public void setIdentIbr(IBIdentifikacija identIbr) {
        this.identIbr = identIbr;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[sifraIbr=" + sifraIbr + "]";
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