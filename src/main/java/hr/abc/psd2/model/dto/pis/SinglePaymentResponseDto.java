package hr.abc.psd2.model.dto.pis;

import hr.abc.psd2.model.dto.ais.Amount;
import hr.abc.psd2.model.dto.ais.Links;
import hr.abc.psd2.model.dto.error.ErrorInformationDto;

import java.io.Serializable;

/**
 * Created by Tomica on 09-Jan-19.
 */
public class SinglePaymentResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private TransactionStatus transactionStatus;
    private String paymentId;
    private Amount transactionFees;
    private Boolean transactionFeeIndicator;

    private Links _links;
    private String psuMessage;

    private Boolean isValid; //ukoiko kod validacije naidemo na grešku --> false
    private ErrorInformationDto errorInformationDto; //Additional Error Information

    //constructors
    public SinglePaymentResponseDto() {
    }

    public SinglePaymentResponseDto(Boolean isValid, TransactionStatus transactionStatus, String paymentId, Amount transactionFees, Boolean transactionFeeIndicator, Links links) {
        this.isValid = isValid;
        this.transactionStatus = transactionStatus;
        this.paymentId = paymentId;
        this.transactionFees = transactionFees;
        this.transactionFeeIndicator = transactionFeeIndicator;
        this._links = links;
    }

    public SinglePaymentResponseDto(Boolean isValid, ErrorInformationDto errorInformationDto) {
        this.isValid = isValid;
        this.errorInformationDto = errorInformationDto;
    }

    //getters and setters


    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Amount getTransactionFees() {
        return transactionFees;
    }

    public void setTransactionFees(Amount transactionFees) {
        this.transactionFees = transactionFees;
    }

    public Boolean getTransactionFeeIndicator() {
        return transactionFeeIndicator;
    }

    public void setTransactionFeeIndicator(Boolean transactionFeeIndicator) {
        this.transactionFeeIndicator = transactionFeeIndicator;
    }

    public Links get_links() {
        return _links;
    }

    public void set_links(Links _links) {
        this._links = _links;
    }

    public String getPsuMessage() {
        return psuMessage;
    }

    public void setPsuMessage(String psuMessage) {
        this.psuMessage = psuMessage;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public ErrorInformationDto getErrorInformationDto() {
        return errorInformationDto;
    }

    public void setErrorInformationDto(ErrorInformationDto errorInformationDto) {
        this.errorInformationDto = errorInformationDto;
    }
}
