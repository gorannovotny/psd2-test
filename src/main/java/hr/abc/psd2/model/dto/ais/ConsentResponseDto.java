package hr.abc.psd2.model.dto.ais;

import java.io.Serializable;

import hr.abc.psd2.model.dto.error.ErrorInformationDto;

/**
 *
 *
 * @author dhorvat
 *
 */
public class ConsentResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;
    // header fields
    private String xRequestID;
    private String tppID;
    private String psuID;

    private String authorization;

    // body fields
    private String consentStatus; //  status of the consent
    private String consentId; //Identification of the consent resource
    private String scaMethods;
    private AccountAccessDto access;
    private Boolean recurringIndicator;
    private String validUntil; //datum istjecanja
    private String frequencyPerDay; // mogući broj upita po danu
    private Boolean combinedServiceIndicator; //If "true" indicates that a payment initiation service will be addressed in the same "session"
    private String lastActionDate;

    private Links _links;


    private Boolean isValid; //ukoiko kod validacije naidemo na grešku --> false
    private ErrorInformationDto errorInformationDto; //Additional Error Information

    public String getxRequestID() {
        return xRequestID;
    }

    public void setxRequestID(String xRequestID) {
        this.xRequestID = xRequestID;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getPsuID() {
        return psuID;
    }

    public void setPsuID(String psuID) {
        this.psuID = psuID;
    }

    public String getTppID() {
        return tppID;
    }

    public void setTppID(String tppID) {
        this.tppID = tppID;
    }

    public String getConsentStatus() {
        return consentStatus;
    }

    public void setConsentStatus(String consentStatus) {
        this.consentStatus = consentStatus;
    }

    public String getConsentId() {
        return consentId;
    }

    public void setConsentId(String consentId) {
        this.consentId = consentId;
    }

    public String getScaMethods() {
        return scaMethods;
    }

    public void setScaMethods(String scaMethods) {
        this.scaMethods = scaMethods;
    }

    public AccountAccessDto getAccess() {
        return access;
    }

    public void setAccess(AccountAccessDto access) {
        this.access = access;
    }

    public String getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(String validUntil) {
        this.validUntil = validUntil;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public Links get_links() {
        return _links;
    }

    public void set_links(Links _links) {
        this._links = _links;
    }

    public Boolean getRecurringIndicator() {
        return recurringIndicator;
    }

    public void setRecurringIndicator(Boolean recurringIndicator) {
        this.recurringIndicator = recurringIndicator;
    }

    public String getFrequencyPerDay() {
        return frequencyPerDay;
    }

    public void setFrequencyPerDay(String frequencyPerDay) {
        this.frequencyPerDay = frequencyPerDay;
    }

    public Boolean getCombinedServiceIndicator() {
        return combinedServiceIndicator;
    }

    public void setCombinedServiceIndicator(Boolean combinedServiceIndicator) {
        this.combinedServiceIndicator = combinedServiceIndicator;
    }

    public ErrorInformationDto getErrorInformationDto() {
        return errorInformationDto;
    }

    public void setErrorInformationDto(ErrorInformationDto errorInformationDto) {
        this.errorInformationDto = errorInformationDto;
    }

    public String getLastActionDate() {
        return lastActionDate;
    }

    public void setLastActionDate(String lastActionDate) {
        this.lastActionDate = lastActionDate;
    }
}







