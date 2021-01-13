package hr.abc.psd2.model.dto;

import java.io.Serializable;

import hr.abc.psd2.model.dto.error.ErrorInformationDto;

/**
 * Created by Tomica on 28-Feb-19.
 */
public class ProcessRequestDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Boolean isValid; //is valid
    private String tppId;
    private String tppName;

    private ErrorInformationDto errorInformationDto;

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public String getTppId() {
        return tppId;
    }

    public void setTppId(String tppId) {
        this.tppId = tppId;
    }

    public ErrorInformationDto getErrorInformationDto() {
        return errorInformationDto;
    }

    public String getTppName() {
        return tppName;
    }

    public void setTppName(String tppName) {
        this.tppName = tppName;
    }

    public void setErrorInformationDto(ErrorInformationDto errorInformationDto) {
        this.errorInformationDto = errorInformationDto;

    }
}