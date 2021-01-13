package hr.abc.psd2.model.dto.ais;

import java.io.Serializable;
import java.util.List;

public class AccountAccessDto implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<AccountReferenceDto> accounts;
    private List<AccountReferenceDto> balances;
    private List<AccountReferenceDto> transactions;
    private String availableAccounts;
    private String allPsd2;


    public List<AccountReferenceDto> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountReferenceDto> accounts) {
        this.accounts = accounts;
    }

    public List<AccountReferenceDto> getBalances() {
        return balances;
    }

    public void setBalances(List<AccountReferenceDto> balances) {
        this.balances = balances;
    }

    public List<AccountReferenceDto> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<AccountReferenceDto> transactions) {
        this.transactions = transactions;
    }

    public String getAvailableAccounts() {
        return availableAccounts;
    }

    public void setAvailableAccounts(String availableAccounts) {
        this.availableAccounts = availableAccounts;
    }

    public String getAllPsd2() {
        return allPsd2;
    }

    public void setAllPsd2(String allPsd2) {
        this.allPsd2 = allPsd2;
    }
}
