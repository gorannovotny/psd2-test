package hr.abc.psd2.model.entity.core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name = "pojed")
public class Pojed {

    @Id
    @Column(name = "sifra_poj", nullable = false, columnDefinition = "CHAR(3)")
    private String sifraPoj;

    @Column(name = "naziv_poj", nullable = false, length = 100)
    private String nazivPoj;

    @JoinColumn(name = "nadpo_poj", referencedColumnName = "sifra_poj")
    @ManyToOne(fetch = FetchType.LAZY)
    private Pojed nadpoPoj;

    @Column(name = "datot_poj")
    @Temporal(TemporalType.DATE)
    private Date datotPoj;

    @Column(name = "datpr_poj")
    @Temporal(TemporalType.DATE)
    private Date datprPoj;


    @Column(name = "statu_poj", columnDefinition = "CHAR(1)")
    private String statuPoj;

    @Version
    @Column(name = "versn_poj", nullable = false)
    private Integer version;

    @Column(name = "adres_poj", length = 100)
    private String adresPoj;

    @Column(name = "mjest_poj", length = 100)
    private String mjestPoj;

    @Column(name = "fispr_poj", length = 50)
    private String fisprPoj;

    @Column(name = "oznka_poj", length = 3)
    private String oznkaPoj;

    protected Pojed() {
    }

    protected Pojed(String sifraPoj) {
        this.sifraPoj = sifraPoj;
    }

    protected Pojed(String sifraPoj, String nazivPoj) {
        this.sifraPoj = sifraPoj;
        this.nazivPoj = nazivPoj;
    }

    public String getSifraPoj() {
        return sifraPoj;
    }

    protected void setSifraPoj(String sifraPoj) {
        this.sifraPoj = sifraPoj;
    }

    public String getNazivPoj() {
        return nazivPoj;
    }

    protected void setNazivPoj(String nazivPoj) {
        this.nazivPoj = nazivPoj;
    }

    public Pojed getNadpoPoj() {
        return nadpoPoj;
    }

    protected void setNadpoPoj(Pojed nadpoPoj) {
        this.nadpoPoj = nadpoPoj;
    }

    public Date getDatotPoj() {
        return datotPoj;
    }

    public void setDatotPoj(Date datotPoj) {
        // TODO treba pretumbati aplikaciju da ovo postane protected
        this.datotPoj = datotPoj;
    }

    public Date getDatprPoj() {
        return datprPoj;
    }

    public void setDatprPoj(Date datprPoj) {
        this.datprPoj = datprPoj;
    }

    public String getStatuPoj() {
        return statuPoj;
    }

    public void setStatuPoj(String statuPoj) {
        // TODO treba pretumbati aplikaciju da ovo postane protected
        this.statuPoj = statuPoj;
    }

    public Integer getVersion() {
        return version;
    }

    protected void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {

        return getClass().getSimpleName() + "[sifraPoj=" + sifraPoj + "]";
    }

    public String getAdresPoj() {
        return adresPoj;
    }

    public void setAdresPoj(String adresPoj) {
        this.adresPoj = adresPoj;
    }

    public String getMjestPoj() {
        return mjestPoj;
    }

    public void setMjestPoj(String mjestPoj) {
        this.mjestPoj = mjestPoj;
    }

    public String getFisprPoj() {
        return fisprPoj;
    }

    public void setFisprPoj(String fisprPoj) {
        this.fisprPoj = fisprPoj;
    }

    public String getOznkaPoj() { return oznkaPoj; }

    public void setOznkaPoj(String oznkaPoj) { this.oznkaPoj = oznkaPoj; }
}
