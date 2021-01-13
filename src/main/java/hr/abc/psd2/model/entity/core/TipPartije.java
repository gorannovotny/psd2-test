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
@Table(name = "tip_partije")
public class TipPartije {

    public static final Integer SIFRATIP_GLAVNAKNJIGA = 53;
    public static final Integer SIFRATIP_TREZOR = 55;
    public static final Integer SIFRATIP_BLAGAJNA = 1156;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sifra_tip", nullable = false)
    private Integer sifraTip;
    @Column(name = "naziv_tip", nullable = false, length = 140)
    private String nazivTip;
    @Column(name = "posao_tip", length = 8)
    private String posaoTip;

    @JoinColumn(name = "klapa_tip", referencedColumnName = "sifra_klp")
    @ManyToOne(fetch = FetchType.LAZY)
    private KlasaPartije klapaTip;
    @JoinColumn(name = "sekto_tip", referencedColumnName = "sifra_sek")
    @ManyToOne(fetch = FetchType.LAZY)
    private Sektor sektoTip;
    @Column(name = "namje_tip", length = 1)
    private String namjeTip;         // namjena: 1-namjenski, 0-nenemjenski
    @JoinColumn(name = "rocnt_tip", referencedColumnName = "sifra_roc")
    @ManyToOne(fetch=FetchType.LAZY)
    private Rocnost rocntTip; // ročnost

    protected TipPartije() {
        super();
    }

    @Deprecated
    protected TipPartije(Integer sifraTip, String nazivTip, String posaoTip, KlasaPartije klapaTip, Sektor sektoTip, Rocnost rocntTip) {
        this.sifraTip = sifraTip;
        this.nazivTip = nazivTip;
        this.posaoTip = posaoTip;
        this.klapaTip = klapaTip;
        this.sektoTip = sektoTip;
        this.rocntTip = rocntTip;
    }

    public Integer getSifraTip() {
        return sifraTip;
    }

    @Deprecated
    protected void setSifraTip(Integer sifraTip) {
        this.sifraTip = sifraTip;
    }

    public String getNazivTip() {
        return nazivTip;
    }

    protected void setNazivTip(String nazivTip) {
        this.nazivTip = nazivTip;
    }

    public String getPosaoTip() {
        return posaoTip;
    }

    protected void setPosaoTip(String posaoTip) {
        this.posaoTip = posaoTip;
    }

    public KlasaPartije getKlapaTip() {
        return klapaTip;
    }

    protected void setKlapaTip(KlasaPartije klapaTip) {
        this.klapaTip = klapaTip;
    }

    public Sektor getSektoTip() {
        return sektoTip;
    }

    protected void setSektoTip(Sektor sektoTip) {
        this.sektoTip = sektoTip;
    }

    public String getNamjeTip() {
        return namjeTip;
    }

    protected void setNamjeTip(String namjeTip) {
        this.namjeTip = namjeTip;
    }

    public Rocnost getRocntTip() {
        return rocntTip;
    }

    protected void setRocntTip(Rocnost rocntTip) {
        this.rocntTip = rocntTip;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[sifraTip=" + sifraTip + "]";
    }

}
