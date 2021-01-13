package hr.sberbank.model.payment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class PaymentOrderInformationResponse {

    private @Valid String endToEndIdentification = null;
    private @Valid AccountReference debtorAccount = null;
    private @Valid String ultimateDebtor = null;
    private @Valid Amount instructedAmount = null;
    private @Valid AccountReference creditorAccount = null;
    private @Valid String creditorAgentName = null;
    private @Valid String creditorAgent = null;
    private @Valid String creditorName = null;
    private @Valid Address creditorAddress = null;
    private @Valid String ultimateCreditor = null;
    private @Valid String purposeCode = null;
    private @Valid ChargeBearer chargeBearer = null;
    private @Valid String remittanceInformationUnstructured = null;
    private @Valid RemittanceInformationStructured remittanceInformationStructured = null;
    private @Valid LocalDate requestedExecutionDate = null;
    private @Valid TransactionStatus transactionStatus = null;
    public enum PaymentProductEnum {

        SEPA_CREDIT_TRANSFERS(String.valueOf("sepa-credit-transfers")), TARGET_2_PAYMENTS(String.valueOf("target-2-payments")), CROSS_BORDER_CREDIT_TRANSFERS(String.valueOf("cross-border-credit-transfers")), DOMESTIC_CREDIT_TRANSFERS_HR(String.valueOf("domestic-credit-transfers-hr")), HR_RTGS_PAYMENTS(String.valueOf("hr-rtgs-payments")), BUYSELL_TRANSFERS(String.valueOf("buysell-transfers"));


        private String value;

        PaymentProductEnum (String v) {
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
        public static PaymentProductEnum fromValue(String v) {
            for (PaymentProductEnum b : PaymentProductEnum.values()) {
                if (String.valueOf(b.value).equals(v)) {
                    return b;
                }
            }
            return null;
        }
    }
    private @Valid PaymentProductEnum paymentProduct = null;
    private @Valid String paymentRequestId = null;

    public PaymentOrderInformationResponse() {
    }
    @Size(max=35)
    public String getEndToEndIdentification() {
        return endToEndIdentification;
    }

    public void setEndToEndIdentification(String endToEndIdentification) {
        this.endToEndIdentification = endToEndIdentification;
    }
    @NotNull
    public AccountReference getDebtorAccount() {
        return debtorAccount;
    }

    public void setDebtorAccount(AccountReference debtorAccount) {
        this.debtorAccount = debtorAccount;
    }
    @Size(max=70)
    public String getUltimateDebtor() {
        return ultimateDebtor;
    }

    public void setUltimateDebtor(String ultimateDebtor) {
        this.ultimateDebtor = ultimateDebtor;
    }
    @NotNull
    public Amount getInstructedAmount() {
        return instructedAmount;
    }

    public void setInstructedAmount(Amount instructedAmount) {
        this.instructedAmount = instructedAmount;
    }
    @NotNull
    public AccountReference getCreditorAccount() {
        return creditorAccount;
    }

    public void setCreditorAccount(AccountReference creditorAccount) {
        this.creditorAccount = creditorAccount;
    }
    @Size(max=70)
    public String getCreditorAgentName() {
        return creditorAgentName;
    }

    public void setCreditorAgentName(String creditorAgentName) {
        this.creditorAgentName = creditorAgentName;
    }
    @JsonProperty("creditorAgent")
    @Pattern(regexp="[A-Z]{6,6}[A-Z2-9][A-NP-Z0-9]([A-Z0-9]{3,3}){0,1}")
    public String getCreditorAgent() {
        return creditorAgent;
    }

    public void setCreditorAgent(String creditorAgent) {
        this.creditorAgent = creditorAgent;
    }
    @Size(max=70)
    @NotNull
    public String getCreditorName() {
        return creditorName;
    }

    public void setCreditorName(String creditorName) {
        this.creditorName = creditorName;
    }

    public Address getCreditorAddress() {
        return creditorAddress;
    }

    public void setCreditorAddress(Address creditorAddress) {
        this.creditorAddress = creditorAddress;
    }
    @Size(max=70)
    public String getUltimateCreditor() {
        return ultimateCreditor;
    }

    public void setUltimateCreditor(String ultimateCreditor) {
        this.ultimateCreditor = ultimateCreditor;
    }

    public String getPurposeCode() {
        return purposeCode;
    }

    public void setPurposeCode(String purposeCode) {
        this.purposeCode = purposeCode;
    }

    public ChargeBearer getChargeBearer() {
        return chargeBearer;
    }

    public void setChargeBearer(ChargeBearer chargeBearer) {
        this.chargeBearer = chargeBearer;
    }
    @Size(max=140)
    public String getRemittanceInformationUnstructured() {
        return remittanceInformationUnstructured;
    }

    public void setRemittanceInformationUnstructured(String remittanceInformationUnstructured) {
        this.remittanceInformationUnstructured = remittanceInformationUnstructured;
    }
    @Size(max=140)
    public RemittanceInformationStructured getRemittanceInformationStructured() {
        return remittanceInformationStructured;
    }

    public void setRemittanceInformationStructured(RemittanceInformationStructured remittanceInformationStructured) {
        this.remittanceInformationStructured = remittanceInformationStructured;
    }

    public LocalDate getRequestedExecutionDate() {
        return requestedExecutionDate;
    }

    public void setRequestedExecutionDate(LocalDate requestedExecutionDate) {
        this.requestedExecutionDate = requestedExecutionDate;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
    @NotNull
    public PaymentProductEnum getPaymentProduct() {
        return paymentProduct;
    }

    public void setPaymentProduct(PaymentProductEnum paymentProduct) {
        this.paymentProduct = paymentProduct;
    }
    @NotNull
    public String getPaymentRequestId() {
        return paymentRequestId;
    }

    public void setPaymentRequestId(String paymentRequestId) {
        this.paymentRequestId = paymentRequestId;
    }
}
