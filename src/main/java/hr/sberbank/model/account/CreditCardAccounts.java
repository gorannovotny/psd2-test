package hr.sberbank.model.account;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class CreditCardAccounts {

    private @Valid List<CreditCardAccount> creditCardAccounts = new ArrayList<CreditCardAccount>();

    public CreditCardAccounts() {
    }
    @NotNull
    public List<CreditCardAccount> getCreditCardAccounts() {
        return creditCardAccounts;
    }

    public void setCreditCardAccounts(List<CreditCardAccount> creditCardAccounts) {
        this.creditCardAccounts = creditCardAccounts;
    }
}
