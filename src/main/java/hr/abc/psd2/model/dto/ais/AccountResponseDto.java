package hr.abc.psd2.model.dto.ais;

import java.io.Serializable;
import java.util.List;

import hr.abc.psd2.model.dto.error.ErrorInformationDto;

/**
 * @author dhorvat
 *
 */

public class AccountResponseDto implements Serializable{

    private static final long serialVersionUID = 1L;
    //list of accounts
    private List<AccountDto> accounts;

    private AccountDto account;
    private Boolean isValid;
    private ErrorInformationDto errorInformationDto; //Additional Error Information

    public AccountResponseDto() {
        super();
    }



    public List<AccountDto> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDto> accounts) {
        this.accounts = accounts;
    }


    public AccountDto getAccount() {
        return account;
    }

    public void setAccount(AccountDto account) {
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
