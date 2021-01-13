package hr.abc.psd2.model.dto.pspic;

import java.io.Serializable;

import hr.abc.psd2.model.dto.error.ErrorInformationDto;

public class ConfirmationOfFoundsResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean isValid;
    private ErrorInformationDto errorInformationDto;
    private Boolean fundsAvailable;

    //no arg constructor
    public ConfirmationOfFoundsResponseDto() {

    }
    // getters and setters...


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

    public Boolean getFundsAvailable() {
        return fundsAvailable;
    }

    public void setFundsAvailable(Boolean fundsAvailable) {
        this.fundsAvailable = fundsAvailable;
    }
}
