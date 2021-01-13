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
@Table(name = "psd2_authorization")
public class Authorization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aut", nullable = false)
    private Integer idAut;

    @Column(name = "scast_aut", nullable = false)
    private String scastAut;

    @Column(name = "scamt_aut", nullable = false)
    private String scamtAut;

    @Column(name = "cdate_aut", nullable = false)
    private Timestamp cdateAut;

    @Column(name = "objct_aut", nullable = false)
    private String objctAut;

    @Column(name = "otype_aut", nullable = false)
    private Integer otypeAut;

    @Column(name = "atype_aut", nullable = false)
    private Integer atypeAut;

    @Column(name = "tppid_aut", nullable = true)
    private String tppIdAut;

    @Column(name = "psuid_aut", nullable = true)
    private String psuIdAut;

    @Column(name = "reqid_aut", nullable = false)
    private String reqIdAut;

    @Column(name = "link_aut", nullable = false)
    private String linkAut;

    @Column(name = "todat_aut")
    private Timestamp toDateAuth;

    @Column(name = "m_date")
    private Timestamp mDate;

    @Column(name = "ohash_aut", nullable = false)
    private String oHashAut;

    public Authorization() {
    }

    public Integer getIdAut() {
        return idAut;
    }

    public void setIdAut(Integer idAut) {
        this.idAut = idAut;
    }

    public String getScastAut() {
        return scastAut;
    }

    public void setScastAut(String scastAut) {
        this.scastAut = scastAut;
    }

    public String getScamtAut() {
        return scamtAut;
    }

    public void setScamtAut(String scamtAut) {
        this.scamtAut = scamtAut;
    }

    public Timestamp getCdateAut() {
        return cdateAut;
    }

    public void setCdateAut(Timestamp cdateAut) {
        this.cdateAut = cdateAut;
    }

    public String getObjctAut() {
        return objctAut;
    }

    public void setObjctAut(String objctAut) {
        this.objctAut = objctAut;
    }

    public Integer getOtypeAut() {
        return otypeAut;
    }

    public void setOtypeAut(Integer otypeAut) {
        this.otypeAut = otypeAut;
    }

    public Integer getAtypeAut() {
        return atypeAut;
    }

    public void setAtypeAut(Integer atypeAut) {
        this.atypeAut = atypeAut;
    }

    public String getTppIdAut() {
        return tppIdAut;
    }

    public void setTppIdAut(String tppIdAut) {
        this.tppIdAut = tppIdAut;
    }

    public String getPsuIdAut() {
        return psuIdAut;
    }

    public void setPsuIdAut(String psuIdAut) {
        this.psuIdAut = psuIdAut;
    }

    public String getReqIdAut() {
        return reqIdAut;
    }

    public void setReqIdAut(String reqIdAut) {
        this.reqIdAut = reqIdAut;
    }

    public String getLinkAut() {
        return linkAut;
    }

    public void setLinkAut(String linkAut) {
        this.linkAut = linkAut;
    }

    public Timestamp getToDateAuth() {
        return toDateAuth;
    }

    public void setToDateAuth(Timestamp toDateAuth) {
        this.toDateAuth = toDateAuth;
    }

    public Timestamp getmDate() {
        return mDate;
    }

    public void setmDate(Timestamp mDate) {
        this.mDate = mDate;
    }

    public String getoHashAut() {
        return oHashAut;
    }

    public void setoHashAut(String oHashAut) {
        this.oHashAut = oHashAut;
    }
}
