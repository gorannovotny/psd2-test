package hr.abc.psd2.model.entity.core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Created by Kristijan on 08.09.15..
 */
@Entity
@Table(name = "ib_datoteka")
public class IBDatoteka {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sifra_dat", nullable = false)
    private Integer sifraDat;

    @Column (name = "filen_dat", nullable = false, length = 255)
    private String filenDat;

    @JoinColumn(name = "ibkor_dat",referencedColumnName = "sifra_kom", nullable = true)
    @ManyToOne
    private IBKorisnik ibkorDat;

    @Column (name = "vrije_dat")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vrijeDat;

    @Column (name = "check_dat", nullable = true)
    private String cheksumDat;

    @Column (name = "statu_dat", nullable = true, length = 1)
    private String statuDat;

    public IBDatoteka() {
    }

    public Integer getSifraDat() {
        return sifraDat;
    }

    public void setSifraDat(Integer sifraDat) {
        this.sifraDat = sifraDat;
    }

    public String getFilenDat() {
        return filenDat;
    }

    public void setFilenDat(String filenDat) {
        this.filenDat = filenDat;
    }

    public Date getVrijeDat() {
        return vrijeDat;
    }

    public void setVrijeDat(Date vrijeDat) {
        this.vrijeDat = vrijeDat;
    }

    public String getCheksumDat() {
        return cheksumDat;
    }

    public void setCheksumDat(String cheksumDat) {
        this.cheksumDat = cheksumDat;
    }

    public IBKorisnik getIbkorDat() {
        return ibkorDat;
    }

    public void setIbkorDat(IBKorisnik ibkorDat) {
        this.ibkorDat = ibkorDat;
    }

    public String getStatuDat() {
        return statuDat;
    }

    public void setStatuDat(String statuDat) {
        this.statuDat = statuDat;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "[sifraDat=" + getSifraDat() + "]";
    }
}
