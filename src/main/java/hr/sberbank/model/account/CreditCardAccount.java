package hr.sberbank.model.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CreditCardAccount {

    private @Valid String customerId = null;
    private @Valid String creditAccount = null;
    private @Valid String currency = null;

    public CreditCardAccount() {
    }
    @NotNull
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    @NotNull
    public String getCreditAccount() {
        return creditAccount;
    }

    public void setCreditAccount(String creditAccount) {
        this.creditAccount = creditAccount;
    }
    @NotNull
    @Pattern(regexp="[A-Z]{3}")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public enum CreditAccountStatusEnum {

        ENTERED("ENTERED"), 
        ACTIVE("ACTIVE"), 
        BLOCKED("BLOCKED"), 
        CANCELED("CANCELED"), 
        CLOSED("CLOSED"), 
        UNKNOWN("UNKNOWN");


        private final String value;

        CreditAccountStatusEnum (String v) {
            value = v;
        }

        public String value() {
            return value;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static CreditAccountStatusEnum fromValue(String v) {
            for (CreditAccountStatusEnum b : CreditAccountStatusEnum.values()) {
                if (String.valueOf(b.value).equals(v)) {
                    return b;
                }
            }
            return null;
        }
    }
    private @Valid CreditAccountStatusEnum creditAccountStatus = null;
    private @Valid String creditType = null;
    private @Valid BigDecimal revolvingInterestRate = null;
    private @Valid BigDecimal penaltyInterestRate = null;
    private @Valid BigDecimal minimalPaymentAmount = null;
    private @Valid String repaymentWay = null;
    private @Valid LocalDate dueDate = null;
    private @Valid LocalDate accountOpeningDate = null;
    private @Valid CreditCardBalances balances = null;
    private @Valid List<CreditCardDetails> cards = new ArrayList<CreditCardDetails>();

    public void setCreditAccountStatus(CreditAccountStatusEnum creditAccountStatus) {
        this.creditAccountStatus = creditAccountStatus;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    public void setRevolvingInterestRate(BigDecimal revolvingInterestRate) {
        this.revolvingInterestRate = revolvingInterestRate;
    }

    public void setPenaltyInterestRate(BigDecimal penaltyInterestRate) {
        this.penaltyInterestRate = penaltyInterestRate;
    }

    public void setMinimalPaymentAmount(BigDecimal minimalPaymentAmount) {
        this.minimalPaymentAmount = minimalPaymentAmount;
    }

    public void setRepaymentWay(String repaymentWay) {
        this.repaymentWay = repaymentWay;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setAccountOpeningDate(LocalDate accountOpeningDate) {
        this.accountOpeningDate = accountOpeningDate;
    }

    public void setBalances(CreditCardBalances balances) {
        this.balances = balances;
    }

    public void setCards(List<CreditCardDetails> cards) {
        this.cards = cards;
    }

    @NotNull
    public CreditAccountStatusEnum getCreditAccountStatus() {
        return creditAccountStatus;
    }
    @NotNull
    public String getCreditType() {
        return creditType;
    }
    @NotNull
    public BigDecimal getRevolvingInterestRate() {
        return revolvingInterestRate;
    }
    @NotNull
    public BigDecimal getPenaltyInterestRate() {
        return penaltyInterestRate;
    }
    @NotNull
    public BigDecimal getMinimalPaymentAmount() {
        return minimalPaymentAmount;
    }
    @NotNull
    public String getRepaymentWay() {
        return repaymentWay;
    }
    @NotNull
    public LocalDate getDueDate() {
        return dueDate;
    }
    @NotNull
    public LocalDate getAccountOpeningDate() {
        return accountOpeningDate;
    }
    @NotNull
    public CreditCardBalances getBalances() {
        return balances;
    }
    @NotNull
    public List<CreditCardDetails> getCards() {
        return cards;
    }
}
