package hr.abc.psd2.model.entity.core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "proizvod")
public class Proizvod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sifra_pro", nullable = false)
    private Integer sifraPro;
    @Column(name = "ident_pro", length = 40, nullable = false)
    private String identPro;
    @Column(name = "naziv_pro", length = 140, nullable = false)
    private String nazivPro;
    @Column(name = "posao_pro", length = 10)
    private String posaoPro;
    @Column(name = "statu_pro", length = 1, nullable = false)
    private String statuPro;
    @Column(name = "vrsta_pro", length = 8)
    private String vrstaPro;
    @Column(name = "datot_pro", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date datotPro;
    @Column(name = "datzt_pro")
    @Temporal(TemporalType.DATE)
    private Date datztPro;

    protected Proizvod() {
    }

    protected Proizvod(String identPro, String nazivPro, String posaoPro, String statuPro, String vrstaPro, Date datotPro, Date datztPro) {
        this.identPro = identPro;
        this.nazivPro = nazivPro;
        this.posaoPro = posaoPro;
        this.statuPro = statuPro;
        this.vrstaPro = vrstaPro;
        this.datotPro = datotPro;
        this.datztPro = datztPro;
    }

    public Proizvod(String identPro, String nazivPro, String posaoPro, String vrstaPro, String statuPro, Date datotPro) {
        this.identPro = identPro;
        this.nazivPro = nazivPro;
        this.posaoPro = posaoPro;
        this.statuPro = statuPro;
        this.vrstaPro = vrstaPro;
        this.datotPro = datotPro;
    }

    public Integer getSifraPro() {
        return sifraPro;
    }

    protected void setSifraPro(Integer sifraPro) {
        this.sifraPro = sifraPro;
    }

    public String getIdentPro() {
        return identPro;
    }

    protected void setIdentPro(String identPro) {
        this.identPro = identPro;
    }

    public String getNazivPro() {
        return nazivPro;
    }

    protected void setNazivPro(String nazivPro) {
        this.nazivPro = nazivPro;
    }

    public String getPosaoPro() {
        return posaoPro;
    }

    protected void setPosaoPro(String posaoPro) {
        this.posaoPro = posaoPro;
    }

    public String getStatuPro() {
        return statuPro;
    }

    protected void setStatuPro(String statuPro) {
        this.statuPro = statuPro;
    }

    public Date getDatotPro() {
        return datotPro;
    }

    protected void setDatotPro(Date datotPro) {
        this.datotPro = datotPro;
    }

    public Date getDatztPro() {
        return datztPro;
    }

    protected void setDatztPro(Date datztPro) {
        this.datztPro = datztPro;
    }

    public String getVrstaPro() {
        return this.vrstaPro;
    }

    protected void setVrstaPro(String vrstaPro) {
        this.vrstaPro = vrstaPro;
    }

    public void postaviSifru(Integer sifra) {
        this.sifraPro = sifra;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[sifraPro=" + sifraPro + "]";
    }
}
