package hr.abc.psd2.model.entity.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "klasa_naloga", indexes = { @Index(name="idx_kn_grupa", columnList="grupa_kna") })

public class KlasaNaloga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sifra_kna", nullable = false)
    private Integer sifraKna;
    @Column(name = "naziv_kna", nullable = false, length = 140)
    private String nazivKna;
    @Column(name = "grupa_kna", length = 15)
    private String grupaKna;

    @JoinColumn(name = "kla_sifra_kna", referencedColumnName = "sifra_kna")
    @ManyToOne
    private KlasaNaloga klaSifraKna;

    // SS promijenila iz protected u public
    public KlasaNaloga() {
    }

    public KlasaNaloga(Integer sifraKna, String nazivKna, String grupaKna, KlasaNaloga klaSifraKna) {
        super();
        this.sifraKna = sifraKna;
        this.nazivKna = nazivKna;
        this.grupaKna = grupaKna;
        this.klaSifraKna = klaSifraKna;
    }

    public Integer getSifraKna() {
        return sifraKna;
    }

    @Deprecated
    protected void setSifraKna(Integer sifraKna) {
        this.sifraKna = sifraKna;
    }

    public String getNazivKna() {
        return nazivKna;
    }

    protected void setNazivKna(String nazivKna) {
        this.nazivKna = nazivKna;
    }

    public KlasaNaloga getKlaSifraKna() {
        return klaSifraKna;
    }

    protected void setKlaSifraKna(KlasaNaloga klaSifraKna) {
        this.klaSifraKna = klaSifraKna;
    }

    public String getGrupaKna() {
        return grupaKna;
    }

    protected void setGrupaKna(String grupaKna) {
        this.grupaKna = grupaKna;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[sifraKna=" + sifraKna + "]";
    }
}
