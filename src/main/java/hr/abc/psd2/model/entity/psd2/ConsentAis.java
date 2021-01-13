package hr.abc.psd2.model.entity.psd2;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * Created by Tomica on 09-Oct-18.
 *
 * PSD2 Acount Information Service (ais) Consent
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "psd2_consent_ais")
public class ConsentAis {

    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sifra_con", nullable = false)
    private Integer sifraCon;   // šifra Consenta
    @Column(name = "state_con", nullable = false, length = 25)
    private String stateCon;    // Consent Status
    @Column(name = "frequ_con", nullable = false)
    private Integer frequCon;   // Frequency Per Day
    @Column(name = "recur_con", nullable = false)
    private  Integer recurCon;  // Recurring Indicator
    @Column(name = "reqid_con", nullable = false, length = 40)
    private String reqidCon;    // X-Request id
    @Column(name = "tppid_con", nullable = false, length = 40)
    private String tppidCon;    // TPP-ID
    @Column(name = "uctpp_con")
    private  Integer uctppCon;  // kod kreiranja je jednak tppFrequencyPerDayCon a nakon toga se smanjuje kod korištenja consenta
    @Column(name = "psuid_con", length = 40)
    private String psuidCon;    // PSU-ID
    @Column(name = "cdate_con", nullable = false)
    Timestamp cdateCon;     // Create datetime
    @Column(name = "todat_con", nullable = false)
    Date todatCon;     // Valid Until (Expire Date)
    @Column(name = "comsr_con", nullable = false)
    Integer comsr_Con;
    @Column(name = "tppfr_con", nullable = false)
    private Integer tppfrCon;   // TPP frequency per day - parametarski određen limit  ukoliko u freque_con dođe veći broj
    @Column(name = "lacda_con", nullable = false)
    Date lacda_con;     // Last action date consenta
    @Column(name = "komit_con", nullable = false)
    private Integer komitCon;

    @Column(name = "tppre_con", length = 140)
    private String tppRedirectUri;

    // constructors
    public ConsentAis() {
        super();
    }

    // getters & setters
    public Integer getSifraCon() {
        return sifraCon;
    }

    public void setSifraCon(Integer sifraCon) {
        this.sifraCon = sifraCon;
    }

    public String getStateCon() {
        return stateCon;
    }

    public void setStateCon(String stateCon) {
        this.stateCon = stateCon;
    }

    public Integer getFrequCon() {
        return frequCon;
    }

    public void setFrequCon(Integer frequCon) {
        this.frequCon = frequCon;
    }

    public Integer getRecurCon() {
        return recurCon;
    }

    public void setRecurCon(Integer recurCon) {
        this.recurCon = recurCon;
    }

    public String getReqidCon() {
        return reqidCon;
    }

    public void setReqidCon(String reqidCon) {
        this.reqidCon = reqidCon;
    }

    public String getTppidCon() {
        return tppidCon;
    }

    public void setTppidCon(String tppidCon) {
        this.tppidCon = tppidCon;
    }

    public String getPsuidCon() {
        return psuidCon;
    }

    public void setPsuidCon(String psuidCon) {
        this.psuidCon = psuidCon;
    }

    public Timestamp getCdateCon() {
        return cdateCon;
    }

    public void setCdateCon(Timestamp cdateCon) {
        this.cdateCon = cdateCon;
    }

    public Date getTodatCon() {
        return todatCon;
    }

    public void setTodatCon(Date todatCon) {
        this.todatCon = todatCon;
    }

    public Integer getComsr_Con() {
        return comsr_Con;
    }

    public void setComsr_Con(Integer comsr_Con) {
        this.comsr_Con = comsr_Con;
    }

    public Integer getUctppCon() {
        return uctppCon;
    }

    public void setUctppCon(Integer uctppCon) {
        this.uctppCon = uctppCon;
    }

    public Integer getTppfrCon() {
        return tppfrCon;
    }

    public void setTppfrCon(Integer tppfrCon) {
        this.tppfrCon = tppfrCon;
    }

    public Date getLacda_con() {
        return lacda_con;
    }

    public void setLacda_con(Date lacda_con) {
        this.lacda_con = lacda_con;
    }

    public Integer getKomitCon() {
        return komitCon;
    }

    public void setKomitCon(Integer komitCon) {
        this.komitCon = komitCon;
    }

    public String getTppRedirectUri() {
        return tppRedirectUri;
    }

    public void setTppRedirectUri(String tppRedirectUri) {
        this.tppRedirectUri = tppRedirectUri;
    }

}

