package hr.abc.psd2.model.dto.pis;

import hr.abc.psd2.model.dto.ais.Links;
import hr.abc.psd2.model.dto.error.ErrorInformationDto;

import java.io.Serializable;

/**
 * Created by Tomica on 20-Jan-19.
 */
public class PaymentCancellationResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String transactionStatus;
    private Links _links;

    private Boolean isValid; //ukoiko kod validacije naidemo na grešku --> false
    private ErrorInformationDto errorInformationDto; //Additional Error Information

    //constructors
    public PaymentCancellationResponseDto() {
    }

    //getters & setters
    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public Links get_links() {
        return _links;
    }

    public void set_links(Links _links) {
        this._links = _links;
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
