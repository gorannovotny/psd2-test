/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
@Table(name = "klasa_partije")
public class KlasaPartije {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sifra_klp", nullable = false)
    private Integer sifraKlp;
    @Column(name = "naziv_klp", nullable = false, length = 35)
    private String nazivKlp;
    @JoinColumn(name = "nadkl_klp", referencedColumnName = "sifra_klp")
    @ManyToOne(fetch = FetchType.LAZY)
    private KlasaPartije nadklKlp;
    @JoinColumn(name = "modul_klp", referencedColumnName = "sifra_mod")
    @ManyToOne(fetch = FetchType.LAZY)
    private Modul modulKlp;

    protected KlasaPartije() {
    }

    public KlasaPartije(Integer sifraKlp, String nazivKlp, KlasaPartije nadklKlp, Modul modulKlp) {
        super();
        this.sifraKlp = sifraKlp;
        this.nazivKlp = nazivKlp;
        this.nadklKlp = nadklKlp;
        this.modulKlp = modulKlp;
    }

    @Deprecated
    protected KlasaPartije(Integer sifraKlp, String nazivKlp) {
        this.sifraKlp = sifraKlp;
        this.nazivKlp = nazivKlp;
    }

    public Integer getSifraKlp() {
        return sifraKlp;
    }

    @Deprecated
    protected void setSifraKlp(Integer sifraKlp) {
        this.sifraKlp = sifraKlp;
    }


    public String getNazivKlp() {
        return nazivKlp;
    }

    protected void setNazivKlp(String nazivKlp) {
        this.nazivKlp = nazivKlp;
    }

    public KlasaPartije getNadklKlp() {
        return nadklKlp;
    }

    protected void setNadklKlp(KlasaPartije nadklKlp) {
        this.nadklKlp = nadklKlp;
    }

    public Modul getModulKlp() {
        return modulKlp;
    }

    protected void setModulKlp(Modul modulKlp) {
        this.modulKlp = modulKlp;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[sifraKlp=" + sifraKlp + "]";
    }
}
