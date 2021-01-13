package hr.abc.psd2.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Tomica on 25-Oct-18.
 */
public class RequestHeaderDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private Map<String, String> headerAttributesMap = new HashMap<>(); //mapa header attributa
    private Map <String,String> errorMap = new HashMap<>();
    private String signingString;
    private Boolean isValid; //is valid


    private List<String> errorList = new ArrayList<>();     //error list

    //getter & setter
    public Map<String, String> getHeaderAttributesMap() {
        return headerAttributesMap;
    }

    public void setHeaderAttributesMap(Map<String, String> headerAttributesMap) {
        this.headerAttributesMap = headerAttributesMap;
    }

    public Map<String, String> getErrorMap() {
        return errorMap;
    }

    public void setErrorMap(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public List<String> getErrorList() {
        return errorList;
    }

    public String getSigningString() {
        return signingString;
    }

    public void setSigningString(String signingString) {
        this.signingString = signingString;
    }

    public void setErrorList(List<String> errorList) {
        this.errorList = errorList;
    }
}
