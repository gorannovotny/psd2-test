package hr.abc.psd2.model.dto.ais;


import java.io.Serializable;
import java.util.List;

import hr.abc.psd2.model.dto.error.ErrorInformationDto;

/**
 * @author dhorvat
 */

public class AccountBalanceResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    // list of balances
    private List<AccountBalanceDto> balances;
    private AccountReferenceDto account;
    private Boolean isValid;
    private ErrorInformationDto errorInformationDto; //Additional Error Information

    public AccountBalanceResponse() {
        super();
    }

    public List<AccountBalanceDto> getBalances() {
        return balances;
    }

    public void setBalances(List<AccountBalanceDto> balances) {
        this.balances = balances;
    }


    public AccountReferenceDto getAccount() {
        return account;
    }

    public void setAccount(AccountReferenceDto account) {
        this.account = account;
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
