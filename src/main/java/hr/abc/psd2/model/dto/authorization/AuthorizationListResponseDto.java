package hr.abc.psd2.model.dto.authorization;

import java.io.Serializable;
import java.util.List;

import hr.abc.psd2.model.dto.error.ErrorInformationDto;

public class AuthorizationListResponseDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Boolean isValid; //ukoiko kod validacije naidemo na grešku --> false
    private ErrorInformationDto errorInformationDto; //Additional Error Information

    private List<Integer> authorisationIds;


    public AuthorizationListResponseDto() {
    }

    public AuthorizationListResponseDto(Boolean isValid) {
        this.isValid = isValid;
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

    public List<Integer> getAuthorisationIds() {
        return authorisationIds;
    }

    public void setAuthorisationIds(List<Integer> authorisationIds) {
        this.authorisationIds = authorisationIds;
    }
}
