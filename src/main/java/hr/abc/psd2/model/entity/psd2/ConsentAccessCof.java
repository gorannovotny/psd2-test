package hr.abc.psd2.model.entity.psd2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by dKosutar on 12-06-19.
 *
 *  PSD2 Confirmation of Founds (cof) Consent Access
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "psd2_consent_access_cof")
public class ConsentAccessCof {
    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sifra_acc", nullable = false)
    private Integer sifraAcc;   // šifra Consent Access-a
    @Column(name = "iban_acc", nullable = false, length = 40)
    private String ibanAcc;    // IBAN
    @Column(name = "curr_acc", nullable = false, length = 3)
    private String currAcc;    // Currency
    @Column(name = "type_acc", nullable = false, length = 25)
    private String typeCon;    // Type
    @JoinColumn(name = "sicon_acc", nullable = false, referencedColumnName = "sifra_con")
    @ManyToOne(fetch = FetchType.LAZY)
    private ConsentAis siconAcc; // Consent dio kojeg je Access

    // constructors
    public ConsentAccessCof() {
        super();
    }

    // getters & setters


    public Integer getSifraAcc() {
        return sifraAcc;
    }

    public void setSifraAcc(Integer sifraAcc) {
        this.sifraAcc = sifraAcc;
    }

    public String getIbanAcc() {
        return ibanAcc;
    }

    public void setIbanAcc(String ibanAcc) {
        this.ibanAcc = ibanAcc;
    }

    public String getCurrAcc() {
        return currAcc;
    }

    public void setCurrAcc(String currAcc) {
        this.currAcc = currAcc;
    }

    public String getTypeCon() {
        return typeCon;
    }

    public void setTypeCon(String typeCon) {
        this.typeCon = typeCon;
    }

    public ConsentAis getSiconAcc() {
        return siconAcc;
    }

    public void setSiconAcc(ConsentAis siconAcc) {
        this.siconAcc = siconAcc;
    }
}
