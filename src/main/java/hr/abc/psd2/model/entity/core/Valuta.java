package hr.abc.psd2.model.entity.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "valuta")
public class Valuta {

    @Id
    @Column(name = "sifra_val", nullable = false, columnDefinition = "CHAR(3)")
    private String sifraVal;
    @Column(name = "oznka_val", nullable = false, length = 3)
    private String oznkaVal;
    @Column(name = "naziv_val", nullable = false, length = 35)
    private String nazivVal;
    @Column(name = "parit_val", nullable = false)
    private Short paritVal;
    @Column(name = "redos_val")
    private Integer redosVal;

    protected Valuta() {
    }

    protected Valuta(String sifraVal, String oznkaVal) {
        this.sifraVal = sifraVal;
        this.oznkaVal = oznkaVal;
    }

    protected Valuta(String sifraVal, String oznkaVal, String nazivVal, Short paritVal) {
        this.sifraVal = sifraVal;
        this.oznkaVal = oznkaVal;
        this.nazivVal = nazivVal;
        this.paritVal = paritVal;
    }

    public String getSifraVal() {
        return sifraVal;
    }

    @Deprecated
    protected void setSifraVal(String sifraVal) {
        this.sifraVal = sifraVal;
    }

    public String getOznkaVal() {
        return oznkaVal;
    }

    protected void setOznkaVal(String oznkaVal) {
        this.oznkaVal = oznkaVal;
    }

    public String getNazivVal() {
        return nazivVal;
    }

    protected void setNazivVal(String nazivVal) {
        this.nazivVal = nazivVal;
    }

    public Short getParitVal() {
        return paritVal;
    }

    protected void setParitVal(Short paritVal) {
        this.paritVal = paritVal;
    }

    public Integer getRedosVal() {
        return redosVal;
    }

    protected void setRedosVal(Integer redosVal) {
        this.redosVal = redosVal;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[sifraVal=" + sifraVal + "]";
    }
}
