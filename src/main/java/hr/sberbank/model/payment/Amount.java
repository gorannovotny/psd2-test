package hr.sberbank.model.payment;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Amount {

    private @Valid String currency = null;
    private @Valid String amount = null;

    public Amount() {
    }

    @NotNull
    @Pattern(regexp="[A-Z]{3}")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
    @NotNull
    @Pattern(regexp=" -? [0-9]{1,14} (?:\\.[0-9]{1,3})?")
    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
