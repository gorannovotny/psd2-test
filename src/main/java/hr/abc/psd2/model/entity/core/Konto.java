package hr.abc.psd2.model.entity.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "konto")
public class Konto {
    @Id
    @Column(name = "sifra_kon", nullable = false, length = 12)
    private String sifraKon;
    @Column(name = "naziv_kon", nullable = false, length = 150)
    private String nazivKon;
    @Column(name = "statu_kon", columnDefinition = "CHAR(1)")
    private String statuKon;  // status ( O-otvoren Z-zatvoren, M - migracija - nema ih kod njih, ali po sektorizaciji bi trebali se nalaziti na dijelu partije)
    @Column(name = "anasi_kon", columnDefinition = "CHAR(1)")
    private String anasiKon; // ANALITIKA - A   SINTETIKA - S
    @Column(name = "vkont_kon", columnDefinition = "CHAR(1)")
    private String vkontKon; // vrsta konta  (H-kunski D-devizni V-valutna klauzula J-jednosmjerna VK )
    @Column(name = "akpas_kon", columnDefinition = "CHAR(1)")
    private String akpasKon; // AKTIVA-A PASIVA-P
    @Column(name = "podbi_kon", columnDefinition = "CHAR(1)")
    private String podbiKon; // podbilans AKTIVNI-A PASIVNI-P NEUTRALNI-N
    @Column(name = "mmf1_kon", length = 15)
    private String mmf1Kon; // Pozicija MMF_1
    @Column(name = "mmf2_kon", length = 15)
    private String mmf2Kon; // Pozicija MMF_2
    @Column(name = "ommf1_kon", length = 15)
    private String ommf1Kon; // Stara pozicija MMF_1
    @Column(name = "ommf2_kon", length = 15)
    private String ommf2Kon; // Stara pozicija MMF_2
    @Column(name = "tecra_kon", nullable = true)
    private Integer tecraKon;  //0- nema obračuna tec.raz., 1- ima obracuna tec.raz.
    @Column(name = "raspo_kon", columnDefinition = "CHAR(1)")
    private String raspoKon; // da li se računa raspoloživo - ako je popunjeno onda se računa, inače ne - trenutni modovi: A - raspoloživo konta aktive; P - raspoloživo konta pasive

    @Version
    @Column(name = "versn_kon", nullable = false)
    private Integer version;


    protected Konto() {
    }

    protected Konto(String sifraKon, String nazivKon) {
        this.sifraKon = sifraKon;
        this.nazivKon = nazivKon;
    }

    public Konto(String sifraKon, String nazivKon, String statuKon,
                 String anasiKon, String vkontKon, String akpasKon) {
        super();
        this.sifraKon = sifraKon;
        this.nazivKon = nazivKon;
        this.statuKon = statuKon;
        this.anasiKon = anasiKon;
        this.vkontKon = vkontKon;
        this.akpasKon = akpasKon;
    }

    public Integer getVersion() {
        return version;
    }

    protected void setVersion(Integer version) {
        this.version = version;
    }

    public String getSifraKon() {
        return sifraKon;
    }

    protected void setSifraKon(String sifraKon) {
        this.sifraKon = sifraKon;
    }

    public String getNazivKon() {
        return nazivKon;
    }

    protected void setNazivKon(String nazivKon) {
        this.nazivKon = nazivKon;
    }

    public String getStatuKon() {
        return statuKon;
    }

    protected void setStatuKon(String statuKon) {
        this.statuKon = statuKon;
    }

    public String getAnasiKon() {
        return anasiKon;
    }

    protected void setAnasiKon(String indAnalKon) {
        this.anasiKon = indAnalKon;
    }

    public String getVkontKon() {
        return vkontKon;
    }

    protected void setVkontKon(String vkontKon) {
        this.vkontKon = vkontKon;
    }

    public String getAkpasKon() {
        return akpasKon;
    }

    protected void setAkpasKon(String akpasKon) {
        this.akpasKon = akpasKon;
    }

    public String getMmf1Kon() {
        return mmf1Kon;
    }

    protected void setMmf1Kon(String mmf1Kon) {
        this.mmf1Kon = mmf1Kon;
    }

    public String getMmf2Kon() {
        return mmf2Kon;
    }

    protected void setMmf2Kon(String mmf2Kon) {
        this.mmf2Kon = mmf2Kon;
    }

    public String getOmmf1Kon() {
        return ommf1Kon;
    }

    protected void setOmmf1Kon(String ommf1Kon) {
        this.ommf1Kon = ommf1Kon;
    }

    public String getOmmf2Kon() {
        return ommf2Kon;
    }

    protected void setOmmf2Kon(String ommf2Kon) {
        this.ommf2Kon = ommf2Kon;
    }

    public String getPodbiKon() {
        return podbiKon;
    }

    public void setPodbiKon(String podbiKon) {
        this.podbiKon = podbiKon;
    }

    public Integer getTecraKon() {
        return tecraKon;
    }

    public void setTecraKon(Integer tecraKon) {
        this.tecraKon = tecraKon;
    }

    public String getRaspoKon() {
        return raspoKon;
    }

    public void setRaspoKon(String raspoKon) {
        this.raspoKon = raspoKon;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[sifraKon=" + sifraKon + "]";
    }

}
