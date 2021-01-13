package hr.abc.psd2.model.entity.psd2;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "psd2_nalog_tpp")
public class TppNalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sifra_tpp", nullable = false)
    private Integer sifraTpp;

    @Column(name = "vrije_tpp", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date vrijeTpp;

    @Column(name = "tppid_tpp", nullable = false)
    private String tppidTpp;

    @Column(name = "reqid_tpp", nullable = false)
    private String reqidTpp;

    @Column(name = "conty_tpp", nullable = false)
    private String contyTpp;

    @Column(name = "pyprd_tpp", nullable = false)
    private String pyprdTpp;

    @Column(name = "pyser_tpp", nullable = false)
    private String pyserTpp;

    @Column(name = "psuip_tpp", nullable = true)
    private String psuIpTpp;

    @Column(name = "pymid_tpp", nullable = false)
    private String paymentRequestId;

    @Column(name = "baskt_tpp", nullable = true)
    private Integer basketId;

    @Column(name = "trsts_tpp", nullable = false)
    private String transactoinStatus;

    public TppNalog() {
    }

    public Integer getSifraTpp() {
        return sifraTpp;
    }

    public void setSifraTpp(Integer sifraTpp) {
        this.sifraTpp = sifraTpp;
    }

    public Date getVrijeTpp() {
        return vrijeTpp;
    }

    public void setVrijeTpp(Date vrijeTpp) {
        this.vrijeTpp = vrijeTpp;
    }

    public String getTppidTpp() {
        return tppidTpp;
    }

    public void setTppidTpp(String tppidTpp) {
        this.tppidTpp = tppidTpp;
    }

    public String getReqidTpp() {
        return reqidTpp;
    }

    public void setReqidTpp(String reqidTpp) {
        this.reqidTpp = reqidTpp;
    }

    public String getContyTpp() {
        return contyTpp;
    }

    public void setContyTpp(String contyTpp) {
        this.contyTpp = contyTpp;
    }

    public String getPyprdTpp() {
        return pyprdTpp;
    }

    public void setPyprdTpp(String pyprdTpp) {
        this.pyprdTpp = pyprdTpp;
    }

    public String getPyserTpp() {
        return pyserTpp;
    }

    public void setPyserTpp(String pyserTpp) {
        this.pyserTpp = pyserTpp;
    }

    public String getPsuIpTpp() {
        return psuIpTpp;
    }

    public void setPsuIpTpp(String psuIpTpp) {
        this.psuIpTpp = psuIpTpp;
    }

    public String getPaymentRequestId() {
        return paymentRequestId;
    }

    public void setPaymentRequestId(String paymentRequestId) {
        this.paymentRequestId = paymentRequestId;
    }

    public Integer getBasketId() {
        return basketId;
    }

    public void setBasketId(Integer basketId) {
        this.basketId = basketId;
    }

    public String getTransactoinStatus() {
        return transactoinStatus;
    }

    public void setTransactoinStatus(String transactoinStatus) {
        this.transactoinStatus = transactoinStatus;
    }
}
