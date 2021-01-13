package hr.abc.psd2.model.dto.ais;

import java.io.Serializable;
import java.util.Date;

import hr.abc.psd2.model.dto.error.ErrorInformationDto;
import hr.abc.psd2.model.dto.error.ErrorInformationXmlDto;

public class AccountTransactionReq implements Serializable {
    private static final long serialVersionUID = 1L;

    //fields
    private String accountId;
    private String sifraValute;
    //mandatory queryParams...
    private Date dateFrom;
    //optional query params...
    private Date dateTo;
    //error informationDto
    private ErrorInformationDto errorInformationDto;

    private ErrorInformationXmlDto errorInformationXmlDto;

    private Boolean isValidReq;

    private Boolean withBalance = false;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public ErrorInformationDto getErrorInformationDto() {
        return errorInformationDto;
    }

    public void setErrorInformationDto(ErrorInformationDto errorInformationDto) {
        this.errorInformationDto = errorInformationDto;
    }

    public Boolean getValidReq() {
        return isValidReq;
    }

    public void setValidReq(Boolean validReq) {
        isValidReq = validReq;
    }

    public Boolean getWithBalance() {
        return withBalance;
    }

    public void setWithBalance(Boolean withBalance) {
        this.withBalance = withBalance;
    }

    public ErrorInformationXmlDto getErrorInformationXmlDto() {
        return errorInformationXmlDto;
    }

    public void setErrorInformationXmlDto(ErrorInformationXmlDto errorInformationXmlDto) {
        this.errorInformationXmlDto = errorInformationXmlDto;
    }

    public String getSifraValute() {
        return sifraValute;
    }

    public void setSifraValute(String sifraValute) {
        this.sifraValute = sifraValute;
    }
}
