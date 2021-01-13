package hr.abc.psd2.model.entity.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zupanija")
public class Zupanija {

    @Id
    @Column(name = "sifra_zup", nullable = false, columnDefinition="CHAR(2)")
    private String sifraZup;
    @Column(name = "naziv_zup", nullable = false, length = 255)
    private String nazivZup;
    @Column(name = "regij_zup", nullable = false, columnDefinition = "CHAR(1)")
    private String regijZup;

    // constructors
    protected Zupanija() {
    }

    protected Zupanija(String sifraZup, String nazivZup, String regijZup) {
        this.sifraZup = sifraZup;
        this.nazivZup = nazivZup;
        this.regijZup = regijZup;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[sifraZup=" + sifraZup + "]";
    }

    // getters & setters
    public String getSifraZup() {
        return sifraZup;
    }

    protected void setSifraZup(String sifraZup) {
        this.sifraZup = sifraZup;
    }

    public String getNazivZup() {
        return nazivZup;
    }

    protected void setNazivZup(String nazivZup) {
        this.nazivZup = nazivZup;
    }

    public String getRegijZup() {
        return regijZup;
    }

    protected void setRegijZup(String regijZup) {
        this.regijZup = regijZup;
    }
}
