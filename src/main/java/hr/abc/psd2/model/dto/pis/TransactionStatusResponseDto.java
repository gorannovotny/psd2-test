package hr.abc.psd2.model.dto.pis;

import hr.abc.psd2.model.dto.error.ErrorInformationDto;
import hr.abc.psd2.model.dto.pis.pain002.Document;

import java.io.Serializable;

/**
 * Created by Tomica on 19-Jan-19.
 */
public class TransactionStatusResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String transactionStatus;
    private Boolean fundsAvailable;
    private Document pain002;

    private Boolean isValid; //ukoiko kod validacije naidemo na grešku --> false
    private ErrorInformationDto errorInformationDto; //Additional Error Information

    //constructors
    public TransactionStatusResponseDto() {
    }

    public TransactionStatusResponseDto(String transactionStatus, Boolean isValid) {
        this.transactionStatus = transactionStatus;
        this.isValid=isValid;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public Boolean getFundsAvailable() {
        return fundsAvailable;
    }

    public void setFundsAvailable(Boolean fundsAvailable) {
        this.fundsAvailable = fundsAvailable;
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

    public Document getPain002() {
        return pain002;
    }

    public void setPain002(Document pain002) {
        this.pain002 = pain002;
    }
}
