package hr.sberbank.model.account;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CreditCardBalances {

    private @Valid BigDecimal creditLimit = null;
    private @Valid BigDecimal usedLimit = null;
    private @Valid BigDecimal newExpenses = null;
    private @Valid BigDecimal authorized = null;
    private @Valid BigDecimal dueDebt = null;
    private @Valid BigDecimal nonDuePrincipal = null;
    private @Valid BigDecimal available = null;
    private @Valid BigDecimal current = null;

    public CreditCardBalances() {
    }
    @NotNull
    public BigDecimal getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }
    @NotNull
    public BigDecimal getUsedLimit() {
        return usedLimit;
    }

    public void setUsedLimit(BigDecimal usedLimit) {
        this.usedLimit = usedLimit;
    }
    @NotNull
    public BigDecimal getNewExpenses() {
        return newExpenses;
    }

    public void setNewExpenses(BigDecimal newExpenses) {
        this.newExpenses = newExpenses;
    }
    @NotNull
    public BigDecimal getAuthorized() {
        return authorized;
    }

    public void setAuthorized(BigDecimal authorized) {
        this.authorized = authorized;
    }
    @NotNull
    public BigDecimal getDueDebt() {
        return dueDebt;
    }

    public void setDueDebt(BigDecimal dueDebt) {
        this.dueDebt = dueDebt;
    }
    @NotNull
    public BigDecimal getNonDuePrincipal() {
        return nonDuePrincipal;
    }

    public void setNonDuePrincipal(BigDecimal nonDuePrincipal) {
        this.nonDuePrincipal = nonDuePrincipal;
    }
    @NotNull
    public BigDecimal getAvailable() {
        return available;
    }

    public void setAvailable(BigDecimal available) {
        this.available = available;
    }
    @NotNull
    public BigDecimal getCurrent() {
        return current;
    }

    public void setCurrent(BigDecimal current) {
        this.current = current;
    }
}
