package hr.abc.psd2.model.entity.core;

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

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tip_naloga")
public class TipNaloga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sifra_pod", nullable = false)
    private Integer sifraPod;
    @Column(name = "ident_pod", nullable = false, length = 20)
    private String identPod;
    @Column(name = "naziv_pod", nullable = false, length = 80)
    private String nazivPod;
    @JoinColumn(name = "klana_pod", referencedColumnName = "sifra_kna")
    @ManyToOne(fetch = FetchType.LAZY)
    private KlasaNaloga klanaPod;
    @JoinColumn(name = "modul_pod", referencedColumnName = "sifra_mod")
    @ManyToOne(fetch = FetchType.LAZY)
    private Modul modulPod;

    @Column(name = "naciz_pod", length = 1)
    private String nacizPod; // način izvršenja

    protected TipNaloga() {
    }

    public TipNaloga(Integer sifraPod, String identPod, String nazivPod) {
        this.sifraPod = sifraPod;
        this.identPod = identPod;
        this.nazivPod = nazivPod;
    }

    @Deprecated
    protected TipNaloga(Integer sifraPod, String identPod, String nazivPod, KlasaNaloga klanaPod, Modul modulPod, String nacizPod) {
        super();
        this.sifraPod = sifraPod;
        this.identPod = identPod;
        this.nazivPod = nazivPod;
        this.klanaPod = klanaPod;
        this.modulPod = modulPod;
        this.nacizPod = nacizPod;
    }

    public Integer getSifraPod() {
        return sifraPod;
    }

    @Deprecated
    protected void setSifraPod(Integer sifraPod) {
        this.sifraPod = sifraPod;
    }


    public String getIdentPod() {
        return identPod;
    }

    protected void setIdentPod(String identPod) {
        this.identPod = identPod;
    }

    public String getNazivPod() {
        return nazivPod;
    }

    protected void setNazivPod(String nazivPod) {
        this.nazivPod = nazivPod;
    }


    public KlasaNaloga getKlanaPod() {
        return klanaPod;
    }

    protected void setKlanaPod(KlasaNaloga klanaPod) {
        this.klanaPod = klanaPod;
    }


    public Modul getModulPod() {
        return modulPod;
    }

    protected void setModulPod(Modul modulPod) {
        this.modulPod = modulPod;
    }


    public String getNacizPod() {
        return nacizPod;
    }

    protected void setNacizPod(String nacizPod) {
        this.nacizPod = nacizPod;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[sifraPod=" + sifraPod + "]";
    }

}
