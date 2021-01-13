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
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "partija")
public class Partija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sifra_par", nullable = false)
    private Integer sifraPar;
    @Column(name = "parti_par", nullable = false, length = 18, unique = true)
    private String partiPar;
    @Column(name = "statu_par", columnDefinition = "CHAR(1)")
    private String statuPar;
    @Column(name = "datot_par")
    @Temporal(TemporalType.DATE)
    private Date datotPar;
    @Column(name = "datzt_par")
    @Temporal(TemporalType.DATE)
    private Date datztPar;
    @JoinColumn(name = "vlasn_par", nullable = false, referencedColumnName = "sifra_kom")
    @ManyToOne(fetch = FetchType.LAZY)
    private Komitent vlasnPar;
    @JoinColumn(name = "pjpar_par", nullable = false, referencedColumnName = "sifra_poj")
    @ManyToOne(fetch = FetchType.LAZY)
    private Pojed pjparPar;
    @JoinColumn(name = "tippa_par", nullable = false, referencedColumnName = "sifra_tip")
    @ManyToOne(fetch = FetchType.LAZY)
    private TipPartije tippaPar;
    @JoinColumn(name = "proiz_par", referencedColumnName = "sifra_pro")
    @ManyToOne(fetch = FetchType.LAZY)
    private Proizvod proizPar;
    @Column(name = "nivop_par")
    private Integer nivopPar;
    @Column(name = "intbl_par", columnDefinition = "CHAR(1)")
    private String intblPar; // Interna blokada (B, K, D)
    @Column(name = "ozniz_par", columnDefinition = "CHAR(1)")
    private String oznizPar; // oznaka izuzeća
    @Column(name = "oznsn_par", columnDefinition = "CHAR(1)")
    private String oznsnPar; // oznaka specifične namjene
    @Column(name = "oznzr_par", columnDefinition = "CHAR(1)")
    private String oznzrPar; // oznaka zajedničkog računa
    @Column(name = "napom_par", length = 140)
    private String napomPar; // napomena
    @Column(name = "alter_par", length = 50)
    private String alterPar; // alternativna oznaka partije
    @Version
    @Column(name = "versi_par", nullable = false)
    private Integer version;

    protected Partija() { super(); }

    @Deprecated
    protected Partija(Integer sifraPar, String partiPar, String statuPar) {
        this.sifraPar = sifraPar;
        this.partiPar = partiPar;
        this.statuPar = statuPar;
    }

    protected Partija(String partiPar, String statuPar) {
        this.partiPar = partiPar;
        this.statuPar = statuPar;
    }

    public Partija(Integer sifraPar, Integer version, String partiPar, String statuPar, Integer nivopPar, Komitent vlasnPar, Pojed pjparPar, TipPartije tippaPar, Proizvod proizPar, Date datotPar, Date datztPar, String intblPar, String alterPar, String napomPar) {
        super();
        this.sifraPar = sifraPar;
        this.version = version;
        this.partiPar = partiPar;
        this.statuPar = statuPar;
        this.nivopPar = nivopPar;
        this.vlasnPar = vlasnPar;
        this.pjparPar = pjparPar;
        this.tippaPar = tippaPar;
        this.proizPar = proizPar;
        this.datotPar = datotPar;
        this.datztPar = datztPar;
        this.intblPar = intblPar;
        this.alterPar = alterPar;
        this.napomPar = napomPar;
    }

    public Integer getSifraPar() {
        return sifraPar;
    }

    protected void setSifraPar(Integer sifraPar) {
        this.sifraPar = sifraPar;
    }

    public String getPartiPar() {
        if (partiPar != null) return partiPar.trim();
        return partiPar;
    }

    protected void setPartiPar(String partiPar) {
        this.partiPar = partiPar;
    }

    public String getStatuPar() {
        return statuPar;
    }

    protected void setStatuPar(String statuPar) {
        this.statuPar = statuPar;
    }

    public Date getDatotPar() {
        return datotPar;
    }

    protected void setDatotPar(Date datotPar) {
        this.datotPar = datotPar;
    }

    public Date getDatztPar() {
        return datztPar;
    }

    protected void setDatztPar(Date datztPar) {
        this.datztPar = datztPar;
    }

    public Komitent getVlasnPar() {
        return vlasnPar;
    }

    protected void setVlasnPar(Komitent vlasnPar) {
        this.vlasnPar = vlasnPar;
    }

    public Pojed getPjparPar() {
        return pjparPar;
    }

    protected void setPjparPar(Pojed pjparPar) {
        this.pjparPar = pjparPar;
    }

    public TipPartije getTippaPar() {
        return tippaPar;
    }

    protected void setTippaPar(TipPartije tippaPar) {
        this.tippaPar = tippaPar;
    }

    public Proizvod getProizPar() {
        return proizPar;
    }

    protected void setProizPar(Proizvod proizPar) {
        this.proizPar = proizPar;
    }

    public Integer getNivopPar() {
        return nivopPar;
    }

    protected void setNivopPar(Integer nivopPar) {
        this.nivopPar = nivopPar;
    }

    public String getIntblPar() {
        return intblPar;
    }

    public void setIntblPar(String intblPar) {
        this.intblPar = intblPar;
    }

    public String getOznizPar() { return oznizPar; }

    public void setOznizPar(String oznizPar) { this.oznizPar = oznizPar; }

    public String getOznsnPar() { return oznsnPar; }

    public void setOznsnPar(String oznsnPar) { this.oznsnPar = oznsnPar; }

    public String getOznzrPar() { return oznzrPar; }

    public void setOznzrPar(String oznzrPar) { this.oznzrPar = oznzrPar; }

    public String getNapomPar() {
        return napomPar;
    }

    public void setNapomPar(String napomPar) {
        this.napomPar = napomPar;
    }

    public String getAlterPar() {
        return alterPar;
    }

    protected void setAlterPar(String alterPar) {
        this.alterPar = alterPar;
    }

    public Integer getVersion() {
        return version;
    }

    protected void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[sifraPar=" + sifraPar + "]";
    }
}
