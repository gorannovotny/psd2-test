package hr.abc.psd2.model.entity.core;

import java.math.BigDecimal;

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
import javax.persistence.Transient;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "promet")
public class Promet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sifra_prm", nullable = false)
    protected Integer sifraPrm;
    @Column(name = "dugva_prm", nullable = false, columnDefinition = "DEC(17,2)")
    private BigDecimal dugvaPrm;
    @Column(name = "potva_prm", nullable = false, columnDefinition = "DEC(17,2)")
    private BigDecimal potvaPrm;
    @Column(name = "dugkn_prm", nullable = false, columnDefinition = "DEC(17,2)")
    private BigDecimal dugknPrm;
    @Column(name = "potkn_prm", nullable = false, columnDefinition = "DEC(17,2)")
    private BigDecimal potknPrm;

    @JoinColumn(name = "diopa_prm", nullable = false, referencedColumnName = "sifra_dio")
    @ManyToOne(fetch = FetchType.LAZY)
    private DioPartije diopaPrm;

    @JoinColumn(name = "nalog_prm", nullable = false, referencedColumnName = "sifra_nal")
    @ManyToOne(fetch = FetchType.LAZY)
    private Nalog nalogPrm;

    @JoinColumn(name = "spart_prm", nullable = false, referencedColumnName = "sifra_par")
    @ManyToOne(fetch = FetchType.LAZY)
    private Partija spartPrm;

    @Transient
    private Integer hash;

    protected Promet() {
    }

    @Deprecated
    protected Promet(Integer sifraPrm, BigDecimal dugvaPrm, BigDecimal potvaPrm, BigDecimal dugknPrm, BigDecimal potknPrm) {
        this.sifraPrm = sifraPrm;
        this.dugvaPrm = dugvaPrm;
        this.potvaPrm = potvaPrm;
        this.dugknPrm = dugknPrm;
        this.potknPrm = potknPrm;
    }

    protected Promet(Integer sifraPrm, BigDecimal dugvaPrm, BigDecimal potvaPrm, BigDecimal dugknPrm, BigDecimal potknPrm, DioPartije diopaPrm, Nalog nalogPrm, Partija spartPrm) {
        this.sifraPrm = sifraPrm;
        this.dugvaPrm = dugvaPrm;
        this.potvaPrm = potvaPrm;
        this.dugknPrm = dugknPrm;
        this.potknPrm = potknPrm;
        this.diopaPrm = diopaPrm;
        this.nalogPrm = nalogPrm;
        this.spartPrm = spartPrm;
    }

    public Integer getSifraPrm() {
        return sifraPrm;
    }

    @Deprecated
    protected void setSifraPrm(Integer sifraPrm) {
        this.sifraPrm = sifraPrm;
    }

    public BigDecimal getDugvaPrm() {
        return dugvaPrm;
    }

    protected void setDugvaPrm(BigDecimal dugvaPrm) {
        this.dugvaPrm = dugvaPrm;
    }

    public BigDecimal getPotvaPrm() {
        return potvaPrm;
    }

    protected void setPotvaPrm(BigDecimal potvaPrm) {
        this.potvaPrm = potvaPrm;
    }

    public BigDecimal getDugknPrm() {
        return dugknPrm;
    }

    protected void setDugknPrm(BigDecimal dugknPrm) {
        this.dugknPrm = dugknPrm;
    }

    public BigDecimal getPotknPrm() {
        return potknPrm;
    }

    protected void setPotknPrm(BigDecimal potknPrm) {
        this.potknPrm = potknPrm;
    }

    public DioPartije getDiopaPrm() {
        return diopaPrm;
    }

    protected void setDiopaPrm(DioPartije diopaPrm) {
        this.diopaPrm = diopaPrm;
    }

    public Nalog getNalogPrm() {
        return nalogPrm;
    }

    protected void setNalogPrm(Nalog nalogPrm) {
        this.nalogPrm = nalogPrm;
    }

    public Partija getSpartPrm() {
        return spartPrm;
    }

    protected void setSpartPrm(Partija spartPrm) {
        this.spartPrm = spartPrm;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[sifraPrm=" + sifraPrm + "]";
    }

    public Integer getHash() {
        return hash;
    }

    public void setHash(Integer hash) {
        this.hash = hash;
    }

}
