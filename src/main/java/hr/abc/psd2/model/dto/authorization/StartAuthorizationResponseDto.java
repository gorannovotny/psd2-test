package hr.abc.psd2.model.dto.authorization;

import hr.abc.psd2.model.dto.ais.Links;
import hr.abc.psd2.model.dto.error.ErrorInformationDto;

import java.io.Serializable;

public class StartAuthorizationResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String scaStatus;
    private String authorisationId;
    private Links _links;

    private Boolean isValid; //ukoiko kod validacije naidemo na grešku --> false
    private ErrorInformationDto errorInformationDto; //Additional Error Information


    public StartAuthorizationResponseDto() {
    }

    public String getScaStatus() {
        return scaStatus;
    }

    public void setScaStatus(String scaStatus) {
        this.scaStatus = scaStatus;
    }

    public String getAuthorisationId() {
        return authorisationId;
    }

    public void setAuthorisationId(String authorisationId) {
        this.authorisationId = authorisationId;
    }

    public Links get_links() {
        return _links;
    }

    public void set_links(Links _links) {
        this._links = _links;
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
