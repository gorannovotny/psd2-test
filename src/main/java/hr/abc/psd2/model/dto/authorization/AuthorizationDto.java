package hr.abc.psd2.model.dto.authorization;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AuthorizationDto implements Serializable {

    public static final String[] SCA_STATUSI_ZA_POTPIS = {ScaStatus.RECEIVED.getValue(), ScaStatus.SCAMETHODSELECTED.getValue()};
    private static final long serialVersionUID = 1L;
    private Integer idAuth;             //šifra
    private String scastAuth;           //CSA status
    private String scamtAuth;           //SCA metoda - REDIRECT_IMPLICT ili REDIRECT_EXPLICIT (u slučaju više potpisnika)   (Psd2Constants.ScaMethod.REDIRECT_IMPLICT, Psd2Constants.ScaMethod.REDIRECT_EXPLICIT)
    private LocalDateTime cdateAuth;    //vrijeme kreiranja
    private String objectAuth;         //šifra tpp naloga ili šifra consenta (resurs koji autoriziramo)
    private Integer objTypeAuth;        // 1- nalog/ 2 -consent (Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue() /Psd2Constants. AuthorizationObjectType.CONSENT_AUTHORIZATION.getValue())
    private Integer autTypeAuth;        // 1 - kreiranje / 2 - povlačenje (Psd2Constants.AuthorizationType.CREATED.getValue() / Psd2Constants.AuthorizationType.CANCELLED.getValue()
    private String tppIdAuth;           //id TPP-a koji je inicirao kreiranje autorizacije
    private String psuIdAuth;           //klijent koji autorizira (ib korisnik)
    private String reqIdAuth;           //psd2 request id
    private String linkAuth;            //id u SCA REDIRECT linku
    private LocalDateTime todateAuth;   //vrijeme do kada je autorizacija valjana
    private LocalDateTime mDate;        //vrijeme kad promjena statusa autorizacije (vrijeme potpisa)
    private String objectHashAut;       //hash objekta koji se potpisuje (zahtjev 12439 )

    public AuthorizationDto() {
    }

    public AuthorizationDto(String scastAuth, String scamtAuth, String objectAuth, Integer objTypeAuth, Integer autTypeAuth, String tppIdAuth, String psuIdAuth, String reqIdAuth, String linkAuth) {
        this.scastAuth = scastAuth;
        this.scamtAuth = scamtAuth;
        this.objectAuth = objectAuth;
        this.objTypeAuth = objTypeAuth;
        this.tppIdAuth = tppIdAuth;
        this.autTypeAuth = autTypeAuth;
        this.psuIdAuth = psuIdAuth;
        this.reqIdAuth = reqIdAuth;
        this.linkAuth = linkAuth;
    }

    public AuthorizationDto(String scastAuth, String scamtAuth, String objectAuth, Integer objTypeAuth, Integer autTypeAuth, String tppIdAuth, String psuIdAuth, String reqIdAuth, String linkAuth, LocalDateTime todateAuth) {
        this.scastAuth = scastAuth;
        this.scamtAuth = scamtAuth;
        this.objectAuth = objectAuth;
        this.objTypeAuth = objTypeAuth;
        this.tppIdAuth = tppIdAuth;
        this.autTypeAuth = autTypeAuth;
        this.psuIdAuth = psuIdAuth;
        this.reqIdAuth = reqIdAuth;
        this.linkAuth = linkAuth;
        this.todateAuth = todateAuth;
    }

    public Integer getIdAuth() {
        return idAuth;
    }

    public void setIdAuth(Integer idAuth) {
        this.idAuth = idAuth;
    }

    public String getScastAuth() {
        return scastAuth;
    }

    public void setScastAuth(String scastAuth) {
        this.scastAuth = scastAuth;
    }

    public String getScamtAuth() {
        return scamtAuth;
    }

    public void setScamtAuth(String scamtAuth) {
        this.scamtAuth = scamtAuth;
    }

    public LocalDateTime getCdateAuth() {
        return cdateAuth;
    }

    public void setCdateAuth(LocalDateTime cdateAuth) {
        this.cdateAuth = cdateAuth;
    }

    public String getObjectAuth() {
        return objectAuth;
    }

    public void setObjectAuth(String objectAuth) {
        this.objectAuth = objectAuth;
    }

    public Integer getObjTypeAuth() {
        return objTypeAuth;
    }

    public void setObjTypeAuth(Integer objTypeAuth) {
        this.objTypeAuth = objTypeAuth;
    }

    public Integer getAutTypeAuth() {
        return autTypeAuth;
    }

    public void setAutTypeAuth(Integer auttypeAuth) {
        this.autTypeAuth = auttypeAuth;
    }

    public String getTppIdAuth() {
        return tppIdAuth;
    }

    public void setTppIdAuth(String tppIdAuth) {
        this.tppIdAuth = tppIdAuth;
    }

    public String getPsuIdAuth() {
        return psuIdAuth;
    }

    public void setPsuIdAuth(String psuIdAuth) {
        this.psuIdAuth = psuIdAuth;
    }

    public String getReqIdAuth() {
        return reqIdAuth;
    }

    public void setReqIdAuth(String reqIdAuth) {
        this.reqIdAuth = reqIdAuth;
    }

    public String getLinkAuth() {
        return linkAuth;
    }

    public void setLinkAuth(String linkAuth) {
        this.linkAuth = linkAuth;
    }

    public LocalDateTime getTodateAuth() {
        return todateAuth;
    }

    public void setTodateAuth(LocalDateTime todateAuth) {
        this.todateAuth = todateAuth;
    }

    public boolean isExpired(LocalDateTime datum) {
        return this.todateAuth != null && this.todateAuth.isAfter(datum);
    }

    public LocalDateTime getmDate() {
        return mDate;
    }

    public void setmDate(LocalDateTime mDate) {
        this.mDate = mDate;
    }

    public String getObjectHashAut() {
        return objectHashAut;
    }

    public void setObjectHashAut(String objectHashAut) {
        this.objectHashAut = objectHashAut;
    }
}
