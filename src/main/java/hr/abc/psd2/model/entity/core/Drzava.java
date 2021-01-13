package hr.abc.psd2.model.entity.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "drzava")
public class Drzava {

    @Id
    @Column(name = "sifra_drz", nullable = false, columnDefinition = "CHAR(3)")
    private String sifraDrz;
    @Column(name = "sidva_drz", nullable = false, columnDefinition = "CHAR(2)")
    private String sidvaDrz;
    @Column(name = "sitri_drz", nullable = false, columnDefinition = "CHAR(3)")
    private String sitriDrz;
    @Column(name = "naziv_drz", nullable = false, length = 255)
    private String nazivDrz;
    @Column(name = "nazen_drz", length = 255)
    private String nazenDrz;
    @Column(name = "offsh_drz", columnDefinition = "CHAR(1)")
    private String offshDrz;
    @Column(name = "telbr_drz", columnDefinition = "CHAR(3)")
    private String telbrDrz;

    @Column(name = "trtyp_drz", columnDefinition = "CHAR(1)") // tip transakcije - nacionalna (N), međunarodna (M), prekogranična (P)
    private String trtypDrz;
    // constructors
    protected Drzava() {
        super();
    }

    protected Drzava(String sifraDrz, String nazivDrz) {
        super();
        this.sifraDrz = sifraDrz;
        this.nazivDrz = nazivDrz;
    }

    // getters & setters
    public String getSifraDrz() {
        return sifraDrz;
    }

    protected void setSifraDrz(String sifraDrz) {
        this.sifraDrz = sifraDrz;
    }

    protected String getSidvaDrz() {
        return sidvaDrz;
    }

    protected void setSidvaDrz(String sidvaDrz) {
        this.sidvaDrz = sidvaDrz;
    }

    protected String getSitriDrz() {
        return sitriDrz;
    }

    protected void setSitriDrz(String sitriDrz) {
        this.sitriDrz = sitriDrz;
    }

    public String getNazivDrz() {
        return nazivDrz;
    }

    protected void setNazivDrz(String nazivDrz) {
        this.nazivDrz = nazivDrz;
    }

    public String getNazenDrz() {
        return nazenDrz;
    }

    public void setNazenDrz(String nazenDrz) {
        this.nazenDrz = nazenDrz;
    }

    protected String getOffshDrz() {
        return offshDrz;
    }

    protected void setOffshDrz(String offshDrz) {
        this.offshDrz = offshDrz;
    }

    protected String getTelbrDrz() {
        return telbrDrz;
    }

    protected void setTelbrDrz(String telbrDrz) {
        this.telbrDrz = telbrDrz;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[sifraDrz=" + sifraDrz.trim() + "]";
    }
	public String getTrtypDrz() {
		return trtypDrz;
	}

	public void setTrtypDrz(String trtypDrz) {
		this.trtypDrz = trtypDrz;
	}

}