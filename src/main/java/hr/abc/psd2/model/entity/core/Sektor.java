package hr.abc.psd2.model.entity.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sektor")
public class Sektor {

    @Id
    @Column(name = "sifra_sek", nullable = false, columnDefinition = "CHAR(2)")
    private String sifraSek;
    @Column(name = "naziv_sek", nullable = false, length = 255)
    private String nazivSek;

    protected Sektor() {
    }

    protected Sektor(String sifraSek) {
        this.sifraSek = sifraSek;
    }

    protected Sektor(String sifraSek, String nazivSek) {
        this.sifraSek = sifraSek;
        this.nazivSek = nazivSek;
    }

    public String getSifraSek() {
        return sifraSek;
    }

    protected void setSifraSek(String sifraSek) {
        this.sifraSek = sifraSek;
    }

    public String getNazivSek() {
        return nazivSek;
    }

    protected void setNazivSek(String nazivSek) {
        this.nazivSek = nazivSek;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[sifraSek=" + sifraSek + "]";
    }
}
