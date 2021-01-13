package hr.abc.psd2.model.entity.psd2;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "psd2_message_log")
public class Psd2MessageLog {

    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_msg", nullable = false)
    private Integer idMsg; // šifra psd2 poruke

    @Column(name = "mtype_msg ", nullable = false, length = 20)
    private String mtypeMsg; // tip psd2 poruke (request / response)

    @Column(name = "reqid_msg ", nullable = false, length = 20)
    private String reqidMsg; // xRequestID

    @Column(name = "cdate_msg ", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date cdateMsg; // vrijeme spremanja/nastanka poruke

    @Lob
    @Column(name = "messg_msg", nullable = false)
    private String messgMsg; // PSD2 poruka

    @Column(name = "tppid_msg ", length = 40)
    private String tppidMsg; // tppId

    @Column(name = "tppad_msg ", length = 100)
    private String tppadMsg; // tppAddress

    @Column(name = "rhost_msg ", length = 100)
    private String rhostMsg; // remote host

    @Column(name = "psuad_msg ", length = 100)
    private String psuadMsg; // psu address



    // constructors
    public Psd2MessageLog() {
        super();
    }

    // getters & setters
    public Integer getIdMsg() {
        return idMsg;
    }

    public void setIdMsg(Integer idMsg) {
        this.idMsg = idMsg;
    }

    public String getMtypeMsg() {
        return mtypeMsg;
    }

    public void setMtypeMsg(String mtypeMsg) {
        this.mtypeMsg = mtypeMsg;
    }

    public Date getCdateMsg() {
        return cdateMsg;
    }

    public void setCdateMsg(Date cdateMsg) {
        this.cdateMsg = cdateMsg;
    }

    public String getMessgMsg() {
        return messgMsg;
    }

    public void setMessgMsg(String messgMsg) {
        this.messgMsg = messgMsg;
    }

    public String getReqidMsg() {
        return reqidMsg;
    }

    public void setReqidMsg(String reqidMsg) {
        this.reqidMsg = reqidMsg;
    }

    public String getTppidMsg() {
        return tppidMsg;
    }

    public void setTppidMsg(String tppidMsg) {
        this.tppidMsg = tppidMsg;
    }

    public String getRhostMsg() {
        return rhostMsg;
    }

    public void setRhostMsg(String rhostMsg) {
        this.rhostMsg = rhostMsg;
    }

    public String getPsuadMsg() {
        return psuadMsg;
    }

    public void setPsuadMsg(String psuadMsg) {
        this.psuadMsg = psuadMsg;
    }

    public String getTppadMsg() {
        return tppadMsg;
    }

    public void setTppadMsg(String tppadMsg) {
        this.tppadMsg = tppadMsg;
    }
}
