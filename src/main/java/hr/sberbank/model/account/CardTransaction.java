package hr.sberbank.model.account;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class CardTransaction {

    private @Valid String creditAccount = null;
    private @Valid BigDecimal cardCode = null;
    private @Valid String reference = null;
    private @Valid LocalDate dateOfEntry = null;
    private @Valid LocalDate dateOfCurrency = null;
    private @Valid String description = null;
    private @Valid BigDecimal expenseAmount = null;
    private @Valid BigDecimal incomeAmount = null;
    private @Valid BigDecimal transactionId = null;
    private @Valid Boolean isVoid = null;
    private @Valid String transactionType = null;
    private @Valid BigDecimal orderId = null;
    private @Valid String orderStatus = null;
    private @Valid Boolean authorized = null;

    public CardTransaction() {
    }
    @NotNull
    public String getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(String creditAccount) {
        this.creditAccount = creditAccount;
    }
    @NotNull
    public BigDecimal getCardCode() {
        return cardCode;
    }

    public void setCardCode(BigDecimal cardCode) {
        this.cardCode = cardCode;
    }
    @NotNull
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
    @NotNull
    public LocalDate getDateOfEntry() {
        return dateOfEntry;
    }

    public void setDateOfEntry(LocalDate dateOfEntry) {
        this.dateOfEntry = dateOfEntry;
    }
    @NotNull
    public LocalDate getDateOfCurrency() {
        return dateOfCurrency;
    }

    public void setDateOfCurrency(LocalDate dateOfCurrency) {
        this.dateOfCurrency = dateOfCurrency;
    }
    @NotNull
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @NotNull
    public BigDecimal getExpenseAmount() {
        return expenseAmount;
    }

    public void setExpenseAmount(BigDecimal expenseAmount) {
        this.expenseAmount = expenseAmount;
    }
    @NotNull
    public BigDecimal getIncomeAmount() {
        return incomeAmount;
    }

    public void setIncomeAmount(BigDecimal incomeAmount) {
        this.incomeAmount = incomeAmount;
    }
    @NotNull
    public BigDecimal getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(BigDecimal transactionId) {
        this.transactionId = transactionId;
    }
    @NotNull
    public Boolean getVoid() {
        return isVoid;
    }

    public void setVoid(Boolean aVoid) {
        isVoid = aVoid;
    }
    @NotNull
    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }
    @NotNull
    public BigDecimal getOrderId() {
        return orderId;
    }

    public void setOrderId(BigDecimal orderId) {
        this.orderId = orderId;
    }
    @NotNull
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    @NotNull
    public Boolean getAuthorized() {
        return authorized;
    }

    public void setAuthorized(Boolean authorized) {
        this.authorized = authorized;
    }
}
