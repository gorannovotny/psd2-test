package hr.abc.psd2.model.dto.ais;

import java.io.Serializable;

public class AccountTransactionDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String transactionId;

    private String creditorName;

    private AccountDto creditorAccount;

    private String debtorName;

    private AccountDto debtorAccount;

    private Amount transactionAmount;

    private String bookingDate;

    private String valueDate;

    private String remittanceInformationUnstructured;

    private String bankTransactionCode;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getCreditorName() {
        return creditorName;
    }

    public void setCreditorName(String creditorName) {
        this.creditorName = creditorName;
    }

    public AccountDto getCreditorAccount() {
        return creditorAccount;
    }

    public void setCreditorAccount(AccountDto creditorAccount) {
        this.creditorAccount = creditorAccount;
    }

    public String getDebtorName() {
        return debtorName;
    }

    public void setDebtorName(String debtorName) {
        this.debtorName = debtorName;
    }

    public AccountDto getDebtorAccount() {
        return debtorAccount;
    }

    public void setDebtorAccount(AccountDto debtorAccount) {
        this.debtorAccount = debtorAccount;
    }

    public Amount getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(Amount transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getValueDate() {
        return valueDate;
    }

    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

    public String getRemittanceInformationUnstructured() {
        return remittanceInformationUnstructured;
    }

    public void setRemittanceInformationUnstructured(String remittanceInformationUnstructured) {
        this.remittanceInformationUnstructured = remittanceInformationUnstructured;
    }

    public String getBankTransactionCode() {
        return bankTransactionCode;
    }

    public void setBankTransactionCode(String bankTransactionCode) {
        this.bankTransactionCode = bankTransactionCode;
    }
}
