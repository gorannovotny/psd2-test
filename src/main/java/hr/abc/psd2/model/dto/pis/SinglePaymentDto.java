package hr.abc.psd2.model.dto.pis;

import hr.abc.psd2.model.dto.ais.AccountReferenceDto;
import hr.abc.psd2.model.dto.ais.Amount;
import hr.abc.psd2.model.dto.core.NalogSepaDto;
import hr.abc.psd2.model.dto.error.ErrorInformationDto;
import hr.sberbank.model.payment.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomica on 10-Dec-18.
 */

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SinglePaymentDto  {

    private static final long serialVersionUID = 1L;

    private String endToEndIdentification;
    private AccountReferenceDto debtorAccount;
    private String debtorId;
    private String ultimateDebtor;
    private Amount instructedAmount;
    private String currencyOfTransfer;
    private AccountReferenceDto creditorAccount;
    private String creditorAgent;
    private String creditorAgentName;
    private String creditorName;
    private String creditorId;
    private AddressDto creditorAddress;
    private String ultimateCreditor;
    private String purposeCode;
    private String chargeBearer;
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
    private String tppRedirectUri;
    private String psuIpAddress;
    private Boolean changed;
    private String paymentRequestId;

    private NalogSepaDto nalogSepa;
    private List<NalogSepaDto> nalogSepaList;

    private String painXml; //pain xml String
    private ArrayList<SinglePaymentDto> listaSinglePaymentDtos; //bulk json nalozi


    private Boolean isValid; //ukoiko kod validacije naidemo na grešku --> false
    private ErrorInformationDto errorInformationDto; //Additional Error Information

    private TransactionStatus transactionStatus = null;


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

    public String getCurrencyOfTransfer() {
        return currencyOfTransfer;
    }

    public void setCurrencyOfTransfer(String currencyOfTransfer) {
        this.currencyOfTransfer = currencyOfTransfer;
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

    public String getChargeBearer() {
        return chargeBearer;
    }

    public void setChargeBearer(String chargeBearer) {
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

    public Boolean getIsValid() {
        return isValid;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public ErrorInformationDto getErrorInformationDto() {
        return errorInformationDto;
    }

    public void setErrorInformationDto(ErrorInformationDto errorInformationDto) {
        this.errorInformationDto = errorInformationDto;
    }

    public String getTppRedirectUri() {
        return tppRedirectUri;
    }

    public void setTppRedirectUri(String tppRedirectUri) {
        this.tppRedirectUri = tppRedirectUri;
    }

    public String getPsuIpAddress() {
        return psuIpAddress;
    }

    public void setPsuIpAddress(String psuIpAddress) {
        this.psuIpAddress = psuIpAddress;
    }

    public String getPainXml() {
        return painXml;
    }

    public void setPainXml(String painXml) {
        this.painXml = painXml;
    }

    public ArrayList<SinglePaymentDto> getListaSinglePaymentDtos() {
        return listaSinglePaymentDtos;
    }

    public void setListaSinglePaymentDtos(ArrayList<SinglePaymentDto> listaSinglePaymentDtos) {
        this.listaSinglePaymentDtos = listaSinglePaymentDtos;
    }

    public Boolean isChanged() {
        return changed;
    }

    public void setChanged(Boolean changed) {
        this.changed = changed;
    }

    public NalogSepaDto getNalogSepa() {
        return nalogSepa;
    }

    public void setNalogSepa(NalogSepaDto nalogSepa) {
        this.nalogSepa = nalogSepa;
    }

    public List<NalogSepaDto> getNalogSepaList() {
        return nalogSepaList;
    }

    public void setNalogSepaList(List<NalogSepaDto> nalogSepaList) {
        this.nalogSepaList = nalogSepaList;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getPaymentRequestId() {
        return paymentRequestId;
    }

    public void setPaymentRequestId(String paymentRequestId) {
        this.paymentRequestId = paymentRequestId;
    }
}
