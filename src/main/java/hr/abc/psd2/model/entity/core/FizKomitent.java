package hr.abc.psd2.model.entity.core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sanja
 */
@Entity
@Table(name = "fiz_komitent")
public class FizKomitent extends Komitent {

    @Column(name = "imeee_kom", length = 255)
    private String imeeeKom;    // ime
    @Column(name = "prezi_kom", length = 255)
    private String preziKom;    // prezime
    @JoinColumn(name = "drzro_kom", referencedColumnName = "sifra_drz")
    @ManyToOne(fetch = FetchType.LAZY)
    private Drzava drzroKom;    //država rođenja
    @Column(name = "mjero_kom", length = 255)
    private String mjeroKom;    //mjesto rođenja
    @Column(name = "datro_kom")
    @Temporal(TemporalType.DATE)
    private Date datroKom;    //datum rođenja
    @Column(name = "piosb_kom", columnDefinition = "CHAR(1)")
    private String piosbKom;  // politički izložena osoba


    // constructors
    protected FizKomitent() {
        super();
    }

    public FizKomitent(Drzava drzavKom, Pojed pojedKom, String statuKom, Integer rizikKom, Integer version, String imeeeKom, String preziKom) {
        super(imeeeKom + " " + preziKom, drzavKom, pojedKom, statuKom, rizikKom, version);
        this.imeeeKom = imeeeKom;
        this.preziKom = preziKom;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[sifraKom=" + this.getSifraKom() + "]";
    }

    // getters & setters
    protected String getImeeeKom() {
        return imeeeKom;
    }

    protected void setImeeeKom(String imeeeKom) {
        this.imeeeKom = imeeeKom;
    }

    protected String getPreziKom() {
        return preziKom;
    }

    protected void setPreziKom(String preziKom) {
        this.preziKom = preziKom;
    }

    protected Drzava getDrzroKom() {
        return drzroKom;
    }

    protected void setDrzroKom(Drzava drzroKom) {
        this.drzroKom = drzroKom;
    }

    protected String getMjeroKom() {
        return mjeroKom;
    }

    protected void setMjeroKom(String mjeroKom) {
        this.mjeroKom = mjeroKom;
    }

    protected Date getDatroKom() {
        return datroKom;
    }

    protected void setDatroKom(Date datroKom) {
        this.datroKom = datroKom;
    }

    protected String getPiosbKom() { return piosbKom; }

    protected void setPiosbKom(String piosbKom) { this.piosbKom = piosbKom; }
}