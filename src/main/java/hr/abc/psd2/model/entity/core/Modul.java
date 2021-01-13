package hr.abc.psd2.model.entity.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "modul")
public class Modul {

    @Id
    @Column(name = "sifra_mod", nullable = false)
    private Integer sifraMod;


    // naziv modula
    @Column(name = "naziv_mod", nullable = false, length = 50)
    private String nazivMod;

    protected Modul() {
    }

    public Integer getSifraMod() {
        return sifraMod;
    }

    public void setSifraMod(Integer sifraMod) {
        this.sifraMod = sifraMod;
    }

    public String getNazivMod() {
        return nazivMod;
    }

    protected void setNazivMod(String nazivMod) {
        this.nazivMod = nazivMod;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[sifraMod=" + sifraMod + "]";
    }
}
