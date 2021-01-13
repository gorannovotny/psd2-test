package hr.abc.psd2.model.dto;

import java.io.Serializable;
import java.time.LocalDate;

import hr.abc.psd2.model.dto.ais.AccountDto;
import hr.abc.psd2.model.dto.ais.Links;
import hr.abc.psd2.model.dto.error.ErrorInformationDto;

public class ConsentCofRequestResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String xRequestID;
    private String tppRedirectUri;
    private String tppID;
    private String psuID;
    private String tppName;
    private AccountDto account;
    //private AccountAccessDto access;
    private String cardNumber;
    private LocalDate cardExpiryDate;
    private String cardInformation;
    private String registrationInformation;

    private Links _links;
    private String consentStatus; //  status of the consent
    private String consentId; //Identification of the consent resource
    private String scaMethods;
    private Boolean recurringIndicator;
    private String validUntil; //datum istjecanja
    private Integer frequencyPerDay; // mogući broj upita po danu
    private Boolean combinedServiceIndicator;
    private String lastActionDate;
    private Boolean isValid; //ukoiko kod validacije naidemo na grešku --> false
    private ErrorInformationDto errorInformationDto; //Additional Error Information

    //no-arg constructor
    public ConsentCofRequestResponseDto() {
    }


    //getters and setters
    public String getxRequestID() {
        return xRequestID;
    }

    public void setxRequestID(String xRequestID) {
        this.xRequestID = xRequestID;
    }

    public String getTppRedirectUri() {
        return tppRedirectUri;
    }

    public void setTppRedirectUri(String tppRedirectUri) {
        this.tppRedirectUri = tppRedirectUri;
    }

    public AccountDto getAccount() {
        return account;
    }

    public void setAccount(AccountDto account) {
        this.account = account;
    }

//    public AccountAccessDto getAccess() {
//        return access;
//    }
//
//    public void setAccess(AccountAccessDto access) {
//        this.access = access;
//    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(LocalDate cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    public String getCardInformation() {
        return cardInformation;
    }

    public void setCardInformation(String cardInformation) {
        this.cardInformation = cardInformation;
    }

    public String getRegistrationInformation() {
        return registrationInformation;
    }

    public void setRegistrationInformation(String registrationInformation) {
        this.registrationInformation = registrationInformation;
    }

    public Links get_links() {
        return _links;
    }

    public void set_links(Links _links) {
        this._links = _links;
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

    public Boolean getRecurringIndicator() {
        return recurringIndicator;
    }

    public void setRecurringIndicator(Boolean recurringIndicator) {
        this.recurringIndicator = recurringIndicator;
    }

    public String getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(String validUntil) {
        this.validUntil = validUntil;
    }

    public Integer getFrequencyPerDay() {
        return frequencyPerDay;
    }

    public void setFrequencyPerDay(Integer frequencyPerDay) {
        this.frequencyPerDay = frequencyPerDay;
    }

    public Boolean getCombinedServiceIndicator() {
        return combinedServiceIndicator;
    }

    public void setCombinedServiceIndicator(Boolean combinedServiceIndicator) {
        this.combinedServiceIndicator = combinedServiceIndicator;
    }

    public String getLastActionDate() {
        return lastActionDate;
    }

    public void setLastActionDate(String lastActionDate) {
        this.lastActionDate = lastActionDate;
    }

    public String getTppID() {
        return tppID;
    }

    public void setTppID(String tppID) {
        this.tppID = tppID;
    }

    public String getPsuID() {
        return psuID;
    }

    public void setPsuID(String psuID) {
        this.psuID = psuID;
    }

    public String getTppName() {
        return tppName;
    }

    public void setTppName(String tppName) {
        this.tppName = tppName;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public ErrorInformationDto getErrorInformationDto() {
        return errorInformationDto;
    }

    public void setErrorInformationDto(ErrorInformationDto errorInformationDto) {
        this.errorInformationDto = errorInformationDto;
    }
}
