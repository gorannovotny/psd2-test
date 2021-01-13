package hr.abc.psd2.model.dto.ais;

import java.io.Serializable;
import java.util.Objects;

public class Links implements Serializable {

    private static final long serialVersionUID = 1L;
    private Href account;
    private Href self;
    private Href status;
    private Href scaStatus;
    private Href scaRedirect;
    private Href startAuthorisation;
    private Href balances;
    private Href transactions;
    private Href download;



    public Href getAccount() {
        return account;
    }

    public void setAccount(Href account) {
        this.account = account;
    }

    public Href getSelf() {
        return self;
    }

    public void setSelf(Href self) {
        this.self = self;
    }

    public Href getScaStatus() {
        return scaStatus;
    }

    public void setScaStatus(Href scaStatus) {
        this.scaStatus = scaStatus;
    }

    public Href getScaRedirect() {
        return scaRedirect;
    }

    public void setScaRedirect(Href scaRedirect) {
        this.scaRedirect = scaRedirect;
    }

    public Href getBalances() {
        return balances;
    }

    public void setBalances(Href balances) {
        this.balances = balances;
    }

    public Href getTransactions() {
        return transactions;
    }

    public void setTransactions(Href transactions) {
        this.transactions = transactions;
    }

    public Href getDownload() {
        return download;
    }

    public void setDownload(Href download) {
        this.download = download;
    }

    public Href getStatus() {
        return status;
    }

    public void setStatus(Href status) {
        this.status = status;
    }

    public Href getStartAuthorisation() {
        return startAuthorisation;
    }

    public void setStartAuthorisation(Href startAuthorisation) {
        this.startAuthorisation = startAuthorisation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Links)) return false;
        Links links = (Links) o;
        return Objects.equals(account, links.account) &&
                Objects.equals(self, links.self) &&
                Objects.equals(status, links.status) &&
                Objects.equals(scaStatus, links.scaStatus) &&
                Objects.equals(scaRedirect, links.scaRedirect) &&
                Objects.equals(startAuthorisation, links.startAuthorisation) &&
                Objects.equals(balances, links.balances) &&
                Objects.equals(transactions, links.transactions) &&
                Objects.equals(download, links.download);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account, self, status, scaStatus, scaRedirect, startAuthorisation, balances, transactions, download);
    }
}
