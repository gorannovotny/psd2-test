package hr.abc.psd2.model.entity.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author ivica
 */
@Entity
@Table(name = "oznaka_naddio")
public class OznakaNadDio {
    @Id
    @Column(name = "sifra_ozn", nullable = false, length = 15)
    private String sifraOzn;
    @Column(name = "naziv_ozn", nullable = false, length = 150)
    private String nazivOzn;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[sifraOzn=" + sifraOzn + "]";
    }

    public String getSifraOzn() {
        return sifraOzn;
    }

    protected void setSifraOzn(String sifraOzn) {
        this.sifraOzn = sifraOzn;
    }

    public String getNazivOzn() {
        return nazivOzn;
    }

    protected void setNazivOzn(String nazivOzn) {
        this.nazivOzn = nazivOzn;
    }


}