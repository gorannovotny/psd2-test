package hr.abc.psd2.model.entity.psd2;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "psd2_basket")
public class Basket {

    //fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sifra_bas")
    private Integer sifraBas;

    @Column(name = "cdate_bas")
    private Timestamp creationDateBas;

    @Column(name = "reqid_bas", nullable = false, length = 40)
    private String reqIdBas;

    @Column(name = "psuid_bas", length = 40)
    private String psuIdBas;

    @Column(name = "psuip_bas", length = 140)
    private String psuIpBas;

    @Column(name = "tppid_bas", nullable = false, length = 40)
    private String tppIdBas;

    @Column(name = "tppre_bas", length = 40)
    private String tppReBas;

    @Column(name = "batyp_bas", nullable = false)
    private Integer basketType;

    @Column(name = "statu_bas", length = 4)
    private String statusBas;

    //constructor
    public Basket() {
    }

    public Integer getSifraBas() {
        return sifraBas;
    }

    public void setSifraBas(Integer sifraBas) {
        this.sifraBas = sifraBas;
    }

    public Timestamp getCreationDateBas() {
        return creationDateBas;
    }

    public void setCreationDateBas(Timestamp creationDateBas) {
        this.creationDateBas = creationDateBas;
    }

    public String getReqIdBas() {
        return reqIdBas;
    }

    public void setReqIdBas(String reqIdBas) {
        this.reqIdBas = reqIdBas;
    }

    public String getPsuIdBas() {
        return psuIdBas;
    }

    public void setPsuIdBas(String psuIdBas) {
        this.psuIdBas = psuIdBas;
    }

    public String getPsuIpBas() {
        return psuIpBas;
    }

    public void setPsuIpBas(String psuIpBas) {
        this.psuIpBas = psuIpBas;
    }

    public String getTppIdBas() {
        return tppIdBas;
    }

    public void setTppIdBas(String tppIdBas) {
        this.tppIdBas = tppIdBas;
    }

    public String getTppReBas() {
        return tppReBas;
    }

    public void setTppReBas(String tppReBas) {
        this.tppReBas = tppReBas;
    }

    public Integer getBasketType() {
        return basketType;
    }

    public void setBasketType(Integer basketType) {
        this.basketType = basketType;
    }

    public String getStatusBas() {
        return statusBas;
    }

    public void setStatusBas(String statusBas) {
        this.statusBas = statusBas;
    }

}
