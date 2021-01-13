package hr.abc.psd2.model.dto.pspic;

import hr.abc.psd2.model.dto.ais.AccountDto;
import hr.abc.psd2.model.dto.ais.Amount;
import hr.abc.psd2.model.dto.error.ErrorInformationDto;

import java.io.Serializable;

public class ConfirmationOfFoundsRequestDto implements Serializable {

    private static final long serialVersionUID = 1L;


    //request body fields

    //Card Number of the card issued by the PIISP. Should be delivered if available.
    private String cardNumber;

    //PSU’s account number
    private AccountDto account;

    //Transaction amount to be checked within the funds check mechanism.
    private Amount instructedAmount;

    //Error information object...
    private ErrorInformationDto errorInformationDto;

    private Boolean isValid;

    //no arg constgructor
    public ConfirmationOfFoundsRequestDto() {

    }

    //getters and setters

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public AccountDto getAccount() {
        return account;
    }

    public void setAccount(AccountDto account) {
        this.account = account;
    }

    public Amount getInstructedAmount() {
        return instructedAmount;
    }

    public void setInstructedAmount(Amount instructedAmount) {
        this.instructedAmount = instructedAmount;
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
