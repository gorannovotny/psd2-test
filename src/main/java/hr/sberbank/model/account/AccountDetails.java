package hr.sberbank.model.account;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AccountDetails {

    private @Valid String accountId = null;
    private @Valid String currency = null;
    private @Valid String iban = null;
    private @Valid String type = null;
    private @Valid AccountStatus status = null;
    private @Valid Boolean blocked = null;
    private @Valid LocalDate openingDate = null;
    private @Valid BigDecimal overdraftAmount = null;
    private @Valid String customerId = null;
    private @Valid BalanceList balances = null;


    public AccountDetails() {
    }
    @NotNull
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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
    @Pattern(regexp="[A-Z]{2,2}[0-9]{2,2}[a-zA-Z0-9]{1,30}")
    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    @NotNull
    @Size(max=4)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @NotNull
    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }
    @NotNull
    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }
    @NotNull
    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }
    @NotNull
    public BigDecimal getOverdraftAmount() {
        return overdraftAmount;
    }

    public void setOverdraftAmount(BigDecimal overdraftAmount) {
        this.overdraftAmount = overdraftAmount;
    }
    @NotNull
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    @NotNull
    public BalanceList getBalances() {
        return balances;
    }

    public void setBalances(BalanceList balances) {
        this.balances = balances;
    }
}
