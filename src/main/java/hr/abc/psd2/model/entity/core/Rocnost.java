package hr.abc.psd2.model.entity.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rocnost")
public class Rocnost {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sifra_roc", nullable = false)
    private Integer sifraRoc;
    @JoinColumn(name = "klapa_roc", referencedColumnName = "sifra_klp", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private KlasaPartije klapaRoc;
    @Column(name = "oznka_roc", length = 15)
    private String oznkaRoc;
    @Column(name = "naziv_roc", length = 35)
    private String nazivRoc;
    @Column(name = "vrsta_roc", length = 10)
    private String vrstaRoc;

    protected Rocnost() {
        super();
    }

    public Rocnost(Integer sifraRoc, KlasaPartije klapaRoc, String oznkaRoc, String nazivRoc, String vrstaRoc) {
        super();
        this.sifraRoc = sifraRoc;
        this.klapaRoc = klapaRoc;
        this.oznkaRoc = oznkaRoc;
        this.nazivRoc = nazivRoc;
        this.vrstaRoc = vrstaRoc;
    }

    public Integer getSifraRoc() {
        return sifraRoc;
    }

    protected void setSifraRoc(Integer sifraRoc) {
        this.sifraRoc = sifraRoc;
    }

    public KlasaPartije getKlapaRoc() {
        return klapaRoc;
    }

    protected void setKlapaRoc(KlasaPartije klapaRoc) {
        this.klapaRoc = klapaRoc;
    }

    public String getOznkaRoc() {
        return oznkaRoc;
    }

    protected void setOznkaRoc(String oznkaRoc) {
        this.oznkaRoc = oznkaRoc;
    }

    public String getNazivRoc() {
        return nazivRoc;
    }

    protected void setNazivRoc(String nazivRoc) {
        this.nazivRoc = nazivRoc;
    }


    public String getVrstaRoc() {
        return vrstaRoc;
    }

    protected void setVrstaRoc(String vrstaRoc) {
        this.vrstaRoc = vrstaRoc;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[sifraRoc=" + sifraRoc + "]";
    }
}
