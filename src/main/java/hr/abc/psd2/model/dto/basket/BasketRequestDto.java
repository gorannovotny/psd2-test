package hr.abc.psd2.model.dto.basket;

import java.io.Serializable;
import java.util.ArrayList;

import hr.abc.psd2.model.dto.error.ErrorInformationDto;

/**
 * Created by Tomica on 14-May-19.
 */
public class BasketRequestDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private ArrayList<String> paymentIds; //for POST request
    private ArrayList<String> payments; //for GET request

    private ArrayList<String> consentIds;
    private String transactionStatus;

    private String xRequestId;
    private String tppId;
    private String tppRedirectUri;
    private String psuIpAddress;
    private Boolean isValid;
    private ErrorInformationDto errorInformationDto;

    public BasketRequestDto() {
    }

    public ArrayList<String> getPaymentIds() {
        return paymentIds;
    }

    public void setPaymentIds(ArrayList<String> paymentIds) {
        this.paymentIds = paymentIds;
    }

    public ArrayList<String> getPayments() {
        return payments;
    }

    public void setPayments(ArrayList<String> payments) {
        this.payments = payments;
    }

    public ArrayList<String> getConsentIds() {
        return consentIds;
    }

    public void setConsentIds(ArrayList<String> consentIds) {
        this.consentIds = consentIds;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getxRequestId() {
        return xRequestId;
    }

    public void setxRequestId(String xRequestId) {
        this.xRequestId = xRequestId;
    }

    public String getTppId() {
        return tppId;
    }

    public void setTppId(String tppId) {
        this.tppId = tppId;
    }

    public String getTppRedirectUri() {
        return tppRedirectUri;
    }

    public void setTppRedirectUri(String tppRedirectUri) {
        this.tppRedirectUri = tppRedirectUri;
    }

    public String getPsuIpAddress() {
        return psuIpAddress;
    }

    public void setPsuIpAddress(String psuIpAddress) {
        this.psuIpAddress = psuIpAddress;
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
