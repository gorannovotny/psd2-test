package hr.abc.psd2.model.dto;

import java.io.Serializable;
import java.security.cert.X509Certificate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CertificateInfoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    LocalDateTime validFrom;
    LocalDateTime validTo;
    String organizationID; //TPP-a ID
    String organizationName; //TPP name
    String competentAuthorityID; //ID nadležnog tijela (CA)
    String competentAuthorityName; //ime nadležnog tijela (CA)

    List<String> tppRoles;

    X509Certificate certificate = null;

    private Boolean isValid; //is valid

    private List<String> errorList = new ArrayList<>();     //error list

    //getter & setter
    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDateTime getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDateTime validTo) {
        this.validTo = validTo;
    }

    public String getOrganizationID() {
        return organizationID;
    }

    public void setOrganizationID(String organizationID) {
        this.organizationID = organizationID;
    }

    public String getCompetentAuthorityID() {
        return competentAuthorityID;
    }

    public void setCompetentAuthorityID(String competentAuthorityID) {
        this.competentAuthorityID = competentAuthorityID;
    }

    public String getCompetentAuthorityName() {
        return competentAuthorityName;
    }

    public void setCompetentAuthorityName(String competentAuthorityName) {
        this.competentAuthorityName = competentAuthorityName;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public List<String> getTppRoles() {
        return tppRoles;
    }

    public void setTppRoles(List<String> tppRoles) {
        this.tppRoles = tppRoles;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }

    public X509Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(X509Certificate cert) { this.certificate = cert; }
}
