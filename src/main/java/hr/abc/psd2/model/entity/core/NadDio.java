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
@Table(name = "nad_dio")
public class NadDio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sifra_nad", nullable = false)
    private Integer sifraNad;
    @JoinColumn(name = "oznka_nad", referencedColumnName = "sifra_ozn", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private OznakaNadDio oznkaNad;
    @Column(name = "bilan_nad", nullable = false, columnDefinition = "CHAR(1)")
    private String bilanNad;
    @Column(name = "vrdug_nad", nullable = false, columnDefinition = "CHAR(2)")
    private String vrdugNad;
    @Column(name = "reval_nad", nullable = false, columnDefinition = "CHAR(2)")
    private String revalNad;
    @Column(name = "kamat_nad", nullable = false, columnDefinition = "CHAR(2)")
    private String kamatNad;
    @Column(name = "nakna_nad", nullable = false, columnDefinition = "CHAR(2)")
    private String naknaNad;
    @Column(name = "redna_nad")
    private Integer rednaNad;
    @Column(name = "rizna_nad")
    private Integer riznaNad;
    @Column(name = "jedvr_nad")
    private Integer jedvrNad;
    @Column(name = "brjed_nad")
    private Integer brjedNad;
    @Column(name = "inter_nad", nullable = true)
    private Short interNad;
    //@Column(name="klapa_nad")


    @JoinColumn(name = "klapa_nad", referencedColumnName = "sifra_klp")
    @ManyToOne(fetch = FetchType.LAZY)
    private KlasaPartije klapaNad;

    @JoinColumn(name = "isklj_nad", referencedColumnName = "sifra_nad")
    @ManyToOne(fetch = FetchType.LAZY)
    private NadDio iskljNad;

    @JoinColumn(name = "preth_nad", referencedColumnName = "sifra_nad")
    @ManyToOne(fetch = FetchType.LAZY)
    private NadDio prethNad;

    @JoinColumn(name = "otpis_nad", referencedColumnName = "sifra_nad")
    @ManyToOne(fetch = FetchType.LAZY)
    private NadDio otpisNad;

    @JoinColumn(name = "rezer_nad", referencedColumnName = "sifra_nad")
    @ManyToOne(fetch = FetchType.LAZY)
    private NadDio rezerNad;

    @JoinColumn(name = "suspe_nad", referencedColumnName = "sifra_nad")
    @ManyToOne(fetch = FetchType.LAZY)
    private NadDio suspeNad;

    @Column(name = "vanal_nad")
    private String vanalNad; // viša analitika - pomoć kod internih naloga

    protected NadDio() {
    }

    @Deprecated
    protected NadDio(Integer sifraNad, OznakaNadDio oznkaNad, String bilanNad, String vrdugNad, String revalNad, String kamatNad, String naknaNad, KlasaPartije klapaNad) {
        this.sifraNad = sifraNad;
        this.oznkaNad = oznkaNad;
        this.bilanNad = bilanNad;
        this.vrdugNad = vrdugNad;
        this.revalNad = revalNad;
        this.kamatNad = kamatNad;
        this.naknaNad = naknaNad;
        this.klapaNad = klapaNad;
    }

    public Integer getSifraNad() {
        return sifraNad;
    }

    @Deprecated
    protected void setSifraNad(Integer sifraNad) {
        this.sifraNad = sifraNad;
    }

    public OznakaNadDio getOznkaNad() {
        return oznkaNad;
    }

    protected void setOznkaNad(OznakaNadDio oznkaNad) {
        this.oznkaNad = oznkaNad;
    }

    public String getBilanNad() {
        return bilanNad;
    }

    protected void setBilanNad(String bilanNad) {
        this.bilanNad = bilanNad;
    }

    public String getVrdugNad() {
        return vrdugNad;
    }

    protected void setVrdugNad(String vrdugNad) {
        this.vrdugNad = vrdugNad;
    }

    public String getRevalNad() {
        return revalNad;
    }

    protected void setRevalNad(String revalNad) {
        this.revalNad = revalNad;
    }

    public String getKamatNad() {
        return kamatNad;
    }

    protected void setKamatNad(String kamatNad) {
        this.kamatNad = kamatNad;
    }

    public String getNaknaNad() {
        return naknaNad;
    }

    protected void setNaknaNad(String naknaNad) {
        this.naknaNad = naknaNad;
    }

    public Integer getRednaNad() {
        return rednaNad;
    }

    protected void setRednaNad(Integer rednaNad) {
        this.rednaNad = rednaNad;
    }

    public Integer getRiznaNad() {
        return riznaNad;
    }

    protected void setRiznaNad(Integer riznaNad) {
        this.riznaNad = riznaNad;
    }

    public Integer getJedvrNad() {
        return jedvrNad;
    }

    protected void setJedvrNad(Integer jedvrNad) {
        this.jedvrNad = jedvrNad;
    }

    public Integer getBrjedNad() {
        return brjedNad;
    }

    protected void setBrjedNad(Integer brjedNad) {
        this.brjedNad = brjedNad;
    }

    public KlasaPartije getKlapaNad() {
        return klapaNad;
    }

    protected void setKlapaNad(KlasaPartije klapaNad) {
        this.klapaNad = klapaNad;
    }

    public NadDio getIskljNad() {
        return iskljNad;
    }

    protected void setIskljNad(NadDio iskljNad) {
        this.iskljNad = iskljNad;
    }

    public NadDio getPrethNad() {
        return prethNad;
    }

    protected void setPrethNad(NadDio prethNad) {
        this.prethNad = prethNad;
    }

    public NadDio getOtpisNad() {
        return otpisNad;
    }

    protected void setOtpisNad(NadDio otpisNad) {
        this.otpisNad = otpisNad;
    }

    public NadDio getRezerNad() {
        return rezerNad;
    }

    protected void setRezerNad(NadDio rezerNad) {
        this.rezerNad = rezerNad;
    }

    public NadDio getSuspeNad() {
        return suspeNad;
    }

    protected void setSuspeNad(NadDio suspeNad) {
        this.suspeNad = suspeNad;
    }

    public Short getInterNad() {
        return interNad;
    }

    protected void setInterNad(Short interNad) {
        this.interNad = interNad;
    }

    public String getVanalNad() {
        return vanalNad;
    }

    public void setVanalNad(String vanalNad) {
        this.vanalNad = vanalNad;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[sifraNad=" + sifraNad + "]";
    }
}
