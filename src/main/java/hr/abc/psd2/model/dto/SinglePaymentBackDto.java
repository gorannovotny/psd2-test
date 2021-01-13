package hr.abc.psd2.model.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import hr.abc.psd2.model.dto.ais.AccountReferenceDto;
import hr.abc.psd2.model.dto.ais.Amount;
import hr.abc.psd2.model.dto.core.NalogSepaDto;
import hr.abc.psd2.model.dto.pis.AddressDto;
import hr.abc.psd2.model.dto.pis.ChargeBearer;
import hr.abc.psd2.model.dto.pis.RemittanceDto;

/**
 * Created by Tomica on 10-Dec-18.
 */
public class SinglePaymentBackDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String endToEndIdentification;
    private AccountReferenceDto debtorAccount;
    private String debtorId;
    private String ultimateDebtor;
    private Amount instructedAmount;
    private String transactionCurrency;
    private AccountReferenceDto creditorAccount;
    private String creditorAgent;
    private String creditorAgentName;
    private String creditorName;
    private String creditorId;
    private AddressDto creditorAddress;
    private String ultimateCreditor;
    private String purposeCode;
    private ChargeBearer chargeBearer;
    private String remittanceInformationUnstructured;
    private ArrayList<String> remittanceInformationUnstructuredArray;
    private RemittanceDto remittanceInformationStructured;
    private String requestedExecutionDate;
    private String requestedExecutionTime;

    private String paymentProduct;
    private String paymentService;
    private String xRequestId;
    private String tppId;
    private String contentType;
    private boolean changed = false;

    private NalogSepaDto nalogSepa;
    private List<NalogSepaDto> nalogSepaList;

    public SinglePaymentBackDto() {
    }

    public String getEndToEndIdentification() {
        return endToEndIdentification;
    }

    public void setEndToEndIdentification(String endToEndIdentification) {
        this.endToEndIdentification = endToEndIdentification;
    }

    public AccountReferenceDto getDebtorAccount() {
        return debtorAccount;
    }

    public void setDebtorAccount(AccountReferenceDto debtorAccount) {
        this.debtorAccount = debtorAccount;
    }

    public String getDebtorId() {
        return debtorId;
    }

    public void setDebtorId(String debtorId) {
        this.debtorId = debtorId;
    }

    public String getUltimateDebtor() {
        return ultimateDebtor;
    }

    public void setUltimateDebtor(String ultimateDebtor) {
        this.ultimateDebtor = ultimateDebtor;
    }

    public String getTransactionCurrency() {
        return transactionCurrency;
    }

    public void setTransactionCurrency(String transactionCurrency) {
        this.transactionCurrency = transactionCurrency;
    }

    public AccountReferenceDto getCreditorAccount() {
        return creditorAccount;
    }

    public void setCreditorAccount(AccountReferenceDto creditorAccount) {
        this.creditorAccount = creditorAccount;
    }

    public String getCreditorAgent() {
        return creditorAgent;
    }

    public void setCreditorAgent(String creditorAgent) {
        this.creditorAgent = creditorAgent;
    }

    public String getCreditorAgentName() {
        return creditorAgentName;
    }

    public void setCreditorAgentName(String creditorAgentName) {
        this.creditorAgentName = creditorAgentName;
    }

    public String getCreditorName() {
        return creditorName;
    }

    public void setCreditorName(String creditorName) {
        this.creditorName = creditorName;
    }

    public String getCreditorId() {
        return creditorId;
    }

    public void setCreditorId(String creditorId) {
        this.creditorId = creditorId;
    }

    public AddressDto getCreditorAddress() {
        return creditorAddress;
    }

    public void setCreditorAddress(AddressDto creditorAddress) {
        this.creditorAddress = creditorAddress;
    }

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

    public String getRemittanceInformationUnstructured() {
        return remittanceInformationUnstructured;
    }

    public void setRemittanceInformationUnstructured(String remittanceInformationUnstructured) {
        this.remittanceInformationUnstructured = remittanceInformationUnstructured;
    }

    public ArrayList<String> getRemittanceInformationUnstructuredArray() {
        return remittanceInformationUnstructuredArray;
    }

    public void setRemittanceInformationUnstructuredArray(ArrayList<String> remittanceInformationUnstructuredArray) {
        this.remittanceInformationUnstructuredArray = remittanceInformationUnstructuredArray;
    }

    public RemittanceDto getRemittanceInformationStructured() {
        return remittanceInformationStructured;
    }

    public void setRemittanceInformationStructured(RemittanceDto remittanceInformationStructured) {
        this.remittanceInformationStructured = remittanceInformationStructured;
    }

    public String getRequestedExecutionDate() {
        return requestedExecutionDate;
    }

    public void setRequestedExecutionDate(String requestedExecutionDate) {
        this.requestedExecutionDate = requestedExecutionDate;
    }

    public String getRequestedExecutionTime() {
        return requestedExecutionTime;
    }

    public void setRequestedExecutionTime(String requestedExecutionTime) {
        this.requestedExecutionTime = requestedExecutionTime;
    }

    public Amount getInstructedAmount() {
        return instructedAmount;
    }

    public void setInstructedAmount(Amount instructedAmount) {
        this.instructedAmount = instructedAmount;
    }

    public String getPaymentProduct() {
        return paymentProduct;
    }

    public void setPaymentProduct(String paymentProduct) {
        this.paymentProduct = paymentProduct;
    }

    public String getPaymentService() {
        return paymentService;
    }

    public void setPaymentService(String paymentService) {
        this.paymentService = paymentService;
    }

    public String getxRequestId() {
        return xRequestId;
    }

    public void setxRequestId(String xRequestId) {
        this.xRequestId = xRequestId;
    }

    public String getTppId() {
        return tppId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setTppId(String tppId) {
        this.tppId = tppId;

    }

    public NalogSepaDto getNalogSepa() {
        return nalogSepa;
    }

    public void setNalogSepa(NalogSepaDto nalogSepa) {
        this.nalogSepa = nalogSepa;
    }

    public boolean isChanged() {
        return changed;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public List<NalogSepaDto> getNalogSepaList() {
        return nalogSepaList;
    }

    public void setNalogSepaList(List<NalogSepaDto> nalogSepaList) {
        this.nalogSepaList = nalogSepaList;
    }
}
