package hr.abc.psd2.model.entity.core;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "opcina")

public class Opcina {

    @Id
    @Column(name = "sifra_opc", nullable = false, length = 5)
    private String sifraOpc;
    @Column(name = "naziv_opc", nullable = false, length = 255)
    private String nazivOpc;
    @JoinColumn(name = "zupan_opc", referencedColumnName = "sifra_zup")
    @ManyToOne
    private Zupanija zupanOpc;
    @Column(name = "prire_opc", columnDefinition = "DEC(15,2)")
    private BigDecimal prireOpc;
    @Column(name = "racun_opc", length = 21)
    private String racunOpc;
    @JoinColumn(name = "komit_opc", referencedColumnName = "sifra_kom")
    @ManyToOne(fetch = FetchType.LAZY)
    private Komitent komitOpc;

    // constructors
    public Opcina() { super(); }

    public Opcina(String sifraOpc) {
        this.sifraOpc = sifraOpc;
    }

    public Opcina(String sifraOpc, String nazivOpc) {
        this.sifraOpc = sifraOpc;
        this.nazivOpc = nazivOpc;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[sifraOpc=" + sifraOpc + "]";
    }

    // getters & setters
    public String getSifraOpc() {
        return sifraOpc;
    }

    protected void setSifraOpc(String sifraOpc) {
        this.sifraOpc = sifraOpc;
    }

    public String getNazivOpc() {
        return nazivOpc;
    }

    protected void setNazivOpc(String nazivOpc) {
        this.nazivOpc = nazivOpc;
    }

    public Zupanija getZupanOpc() {
        return zupanOpc;
    }

    protected void setZupanOpc(Zupanija zupanOpc) {
        this.zupanOpc = zupanOpc;
    }

    public BigDecimal getPrireOpc() {
        return prireOpc;
    }

    protected void setPrireOpc(BigDecimal prireOpc) {
        this.prireOpc = prireOpc;
    }

    public String getRacunOpc() {
        return racunOpc;
    }

    protected void setRacunOpc(String racunOpc) {
        this.racunOpc = racunOpc;
    }

    public Komitent getKomitOpc() {
        return komitOpc;
    }

    public void setKomitOpc(Komitent komitOpc) {
        this.komitOpc = komitOpc;
    }
}