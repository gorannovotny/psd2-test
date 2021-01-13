package hr.abc.psd2.model.dto.ais;

import hr.abc.psd2.model.dto.error.ErrorInformationDto;
import hr.abc.psd2.model.dto.error.ErrorInformationXmlDto;
import hr.abc.psd2.model.sepa.camt.Document;

import java.io.Serializable;

public class AccountTransactionsResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    // transaction- booked, pending
    private AccountTransactionListDto transactions;
    private AccountReferenceDto account;
    private AccountBalanceDto balances;
    private Boolean isValid;
    private Links _links;
    private Document document;
    private ErrorInformationDto errorInformationDto;
    private ErrorInformationXmlDto errorInformationXmlDto;


    public AccountTransactionListDto getTransactions() {
        return transactions;
    }

    public void setTransactions(AccountTransactionListDto transactions) {
        this.transactions = transactions;
    }

    public AccountReferenceDto getAccount() {
        return account;
    }

    public void setAccount(AccountReferenceDto account) {
        this.account = account;
    }

    public AccountBalanceDto getBalances() {
        return balances;
    }

    public void setBalances(AccountBalanceDto balances) {
        this.balances = balances;
    }

    public Boolean getValid() {
        return isValid;
    }

    public void setValid(Boolean valid) {
        isValid = valid;
    }

    public Links get_links() {
        return _links;
    }

    public void set_links(Links _links) {
        this._links = _links;
    }

    public ErrorInformationDto getErrorInformationDto() {
        return errorInformationDto;
    }

    public void setErrorInformationDto(ErrorInformationDto errorInformationDto) {
        this.errorInformationDto = errorInformationDto;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public ErrorInformationXmlDto getErrorInformationXmlDto() {
        return errorInformationXmlDto;
    }

    public void setErrorInformationXmlDto(ErrorInformationXmlDto errorInformationXmlDto) {
        this.errorInformationXmlDto = errorInformationXmlDto;
    }
}
