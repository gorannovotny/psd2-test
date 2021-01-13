package hr.abc.psd2.model.dto.authorization;

import java.io.Serializable;

import hr.abc.psd2.model.dto.error.ErrorInformationDto;

public class ScaStatusResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean isValid; //ukoiko kod validacije naidemo na grešku --> false
    private ErrorInformationDto errorInformationDto; //Additional Error Information
    private String scaStatus;

    public ScaStatusResponseDto() {
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

    public String getScaStatus() {
        return scaStatus;
    }

    public void setScaStatus(String scaStatus) {
        this.scaStatus = scaStatus;
    }
}
