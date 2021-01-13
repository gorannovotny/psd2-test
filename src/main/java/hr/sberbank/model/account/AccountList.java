package hr.sberbank.model.account;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class AccountList {
    private @Valid List<AccountDetails> accounts = new ArrayList<AccountDetails>();

    public AccountList() {
    }
    @NotNull
    public List<AccountDetails> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDetails> accounts) {
        this.accounts = accounts;
    }
}
