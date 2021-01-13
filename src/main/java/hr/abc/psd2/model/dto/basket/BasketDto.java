package hr.abc.psd2.model.dto.basket;

import hr.abc.psd2.model.dto.GenericDto;
import hr.abc.psd2.model.dto.error.ErrorInformationDto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BasketDto extends GenericDto<Integer> implements Serializable {

    private static final long serialVersionUID = 1L;

    //fields

    private LocalDateTime creationDateBas;

    private String requestIdBas;

    private String psuIdBas;

    private String psuIpBas;

    private String tppIdBas;

    private String tppRedirectUriBas;

    private Integer basketType;

    private String statusBas;

    private Boolean isValid;

    private ErrorInformationDto errorInformationDto;


    //constructor
    public BasketDto() {
        super();
    }

    public LocalDateTime getCreationDateBas() {
        return creationDateBas;
    }

    public void setCreationDateBas(LocalDateTime creationDateBas) {
        this.creationDateBas = creationDateBas;
    }

    public String getRequestIdBas() {
        return requestIdBas;
    }

    public void setRequestIdBas(String requestIdBas) {
        this.requestIdBas = requestIdBas;
    }

    public String getPsuIdBas() {
        return psuIdBas;
    }

    public void setPsuIdBas(String psuIdBas) {
        this.psuIdBas = psuIdBas;
    }

    public String getPsuIpBas() {
        return psuIpBas;
    }

    public void setPsuIpBas(String psuIpBas) {
        this.psuIpBas = psuIpBas;
    }

    public String getTppIdBas() {
        return tppIdBas;
    }

    public void setTppIdBas(String tppIdBas) {
        this.tppIdBas = tppIdBas;
    }

    public String getTppRedirectUriBas() {
        return tppRedirectUriBas;
    }

    public void setTppRedirectUriBas(String tppRedirectUriBas) {
        this.tppRedirectUriBas = tppRedirectUriBas;
    }

    public Integer getBasketType() {
        return basketType;
    }

    public void setBasketType(Integer basketType) {
        this.basketType = basketType;
    }

    public String getStatusBas() {
        return statusBas;
    }

    public void setStatusBas(String statusBas) {
        this.statusBas = statusBas;
    }

    public ErrorInformationDto getErrorInformationDto() {
        return errorInformationDto;
    }

    public void setErrorInformationDto(ErrorInformationDto errorInformationDto) {
        this.errorInformationDto = errorInformationDto;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }
}
