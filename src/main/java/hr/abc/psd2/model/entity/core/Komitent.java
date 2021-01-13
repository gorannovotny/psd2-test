package hr.abc.psd2.model.entity.core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "komitent")
@Inheritance(strategy=InheritanceType.JOINED)
public class Komitent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sifra_kom", nullable = false)
    private Integer sifraKom;  // šifra
    @Column(name = "oib_kom", length = 11)
    private String oibKom; // osobni identifikacijski broj
    @Column(name = "matbr_kom", length = 13)
    private String matbrKom;  // matični broj
    @Column(name = "naziv_kom", nullable = false, length = 255)
    private String nazivKom;  // naziv
    @JoinColumn(name = "drzav_kom", referencedColumnName = "sifra_drz", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Drzava drzavKom;  // država
    @JoinColumn(name = "sektr_kom", referencedColumnName = "sifra_sek")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sektor sektrKom;  // sektor
    @JoinColumn(name = "pojed_kom", referencedColumnName = "sifra_poj")
    @ManyToOne(fetch = FetchType.LAZY)
    private Pojed pojedKom;  // poslovnica
    @JoinColumn(name = "bankr_kom", referencedColumnName = "sifra_lik")
    @ManyToOne(fetch = FetchType.LAZY)
    private Likvidator bankrKom;  // osobni bankar
    @Column(name = "datpr_kom")  //
    @Temporal(TemporalType.DATE)
    private Date datprKom;  // datum pristupanja
    @Column(name = "statu_kom", nullable = false, columnDefinition = "CHAR(1)")
    private String statuKom;  // status A-aktivan, N-Neaktivan, P-povremeni, K-trenutno neaktivan, Z-zatvoren, B-brisan
    @Column(name = "prvst_kom", columnDefinition = "CHAR(1)")
    private String prvstKom;  // pravni status
    @Column(name = "extbl_kom", columnDefinition = "CHAR(1)")
    private String extblKom;  // eksterna oznaka blokade
    @Column(name = "intbl_kom", columnDefinition = "CHAR(1)")
    private String intblKom;  // interna oznaka blokade
    @Column(name = "rizik_kom")
    private Integer rizikKom;  // rizičnost klijenta
    @JoinColumn(name = "prirz_kom", referencedColumnName = "sifra_opc")
    @ManyToOne(fetch = FetchType.LAZY)
    private Opcina prirzKom;  // općina za prirez
    @Version
    @Column(name = "versi_kom", nullable = false)
    private Integer version;  // verzioniranje entiteta

    // constructors
    protected Komitent() {
        super();
    }

    public Komitent(String nazivKom, Drzava drzavKom, Pojed pojedKom, String statuKom, Integer rizikKom, Integer version) {
        this.nazivKom = nazivKom;
        this.drzavKom = drzavKom;
        this.pojedKom = pojedKom;
        this.statuKom = statuKom;
        this.rizikKom = rizikKom;
        this.version = version;
    }

    protected Komitent(String matbrKom, String oibKom, String nazivKom) {
        this.matbrKom = matbrKom;
        this.oibKom = oibKom;
        this.nazivKom = nazivKom;
    }

    public Komitent(Integer version, String matbrKom, String nazivKom, String statuKom, Drzava drzavKom, Pojed pojedKom, Sektor sektrKom, Likvidator bankrKom, Integer rizikKom, Date datprKom, String napomKom) {
        super();
        this.version = version;
        this.matbrKom = matbrKom;
        this.nazivKom = nazivKom;
        this.statuKom = statuKom;
        this.drzavKom = drzavKom;
        this.pojedKom = pojedKom;
        this.sektrKom = sektrKom;
        this.bankrKom = bankrKom;
        this.rizikKom = rizikKom;
        this.datprKom = datprKom;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[sifraKom=" + sifraKom + "]";
    }

    // getters & setters
    public Integer getSifraKom() {
        return sifraKom;
    }

    protected void setSifraKom(Integer sifraKom) {
        this.sifraKom = sifraKom;
    }

    public String getOibKom() {
        return oibKom;
    }

    protected void setOibKom(String oibKom) {
        this.oibKom = oibKom;
    }

    public String getMatbrKom() {
        return matbrKom;
    }

    protected void setMatbrKom(String matbrKom) {
        this.matbrKom = matbrKom;
    }

    public String getNazivKom() {
        return nazivKom;
    }

    protected void setNazivKom(String nazivKom) {
        this.nazivKom = nazivKom;
    }

    public Drzava getDrzavKom() {
        return drzavKom;
    }

    protected void setDrzavKom(Drzava drzavKom) {
        this.drzavKom = drzavKom;
    }

    public Sektor getSektrKom() {
        return sektrKom;
    }

    protected void setSektrKom(Sektor sektrKom) {
        this.sektrKom = sektrKom;
    }

    protected Pojed getPojedKom() {
        return pojedKom;
    }

    protected void setPojedKom(Pojed pojedKom) {
        this.pojedKom = pojedKom;
    }

    protected Likvidator getBankrKom() {
        return bankrKom;
    }

    protected void setBankrKom(Likvidator bankrKom) {
        this.bankrKom = bankrKom;
    }

    protected Date getDatprKom() {
        return datprKom;
    }

    protected void setDatprKom(Date datprKom) {
        this.datprKom = datprKom;
    }

    protected String getStatuKom() {
        return statuKom;
    }

    protected void setStatuKom(String statuKom) {
        this.statuKom = statuKom;
    }

    public String getPrvstKom() { return prvstKom; }

    public void setPrvstKom(String prvstKom) { this.prvstKom = prvstKom; }

    public String getExtblKom() { return extblKom; }

    public void setExtblKom(String extblKom) { this.extblKom = extblKom; }

    public String getIntblKom() { return intblKom; }

    public void setIntblKom(String intblKom) { this.intblKom = intblKom; }

    public Integer getRizikKom() {
        return rizikKom;
    }

    protected void setRizikKom(Integer rizikKom) {
        this.rizikKom = rizikKom;
    }

    public Opcina getPrirzKom() { return prirzKom; }

    protected void setPrirzKom(Opcina prirzKom) { this.prirzKom = prirzKom; }

    protected Integer getVersion() {
        return version;
    }

    protected void setVersion(Integer version) {
        this.version = version;
    }
}