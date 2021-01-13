package hr.sberbank.model.payment;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

public class AccountReference {

    private @Valid String iban = null;
    private @Valid String bban = null;
    private @Valid String currency= null;

    public AccountReference() {
    }

    @Pattern(regexp="[A-Z]{2,2}[0-9]{2,2}[a-zA-Z0-9]{1,30}")
    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @Pattern(regexp="[a-zA-Z0-9]{1,30}")
    public String getBban() {
        return bban;
    }

    public void setBban(String bban) {
        this.bban = bban;
    }

    @Pattern(regexp="[A-Z]{3}")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
