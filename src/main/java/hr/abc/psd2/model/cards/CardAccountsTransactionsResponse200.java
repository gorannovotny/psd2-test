package hr.abc.psd2.model.cards;

import java.util.Objects;

import javax.validation.Valid;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
@Schema(description = "Body of the JSON response for a successful read card account transaction list request. This card account report contains transactions resulting from the query parameters. ")
public class CardAccountsTransactionsResponse200   {
  private @Valid AccountReference cardAccount = null;
  private @Valid Boolean debitAccounting = null;
  private @Valid CardAccountReport cardTransactions = null;
  private @Valid BalanceList balances = null;
  private @Valid LinksDownload _links = null;

  /**
   **/
  public CardAccountsTransactionsResponse200 cardAccount(AccountReference cardAccount) {
    this.cardAccount = cardAccount;
    return this;
  }

  
  public AccountReference getCardAccount() {
    return cardAccount;
  }
  public void setCardAccount(AccountReference cardAccount) {
    this.cardAccount = cardAccount;
  }

  /**
   **/
  public CardAccountsTransactionsResponse200 debitAccounting(Boolean debitAccounting) {
    this.debitAccounting = debitAccounting;
    return this;
  }

  
  public Boolean getDebitAccounting() {
    return debitAccounting;
  }
  public void setDebitAccounting(Boolean debitAccounting) {
    this.debitAccounting = debitAccounting;
  }

  /**
   **/
  public CardAccountsTransactionsResponse200 cardTransactions(CardAccountReport cardTransactions) {
    this.cardTransactions = cardTransactions;
    return this;
  }

  
  public CardAccountReport getCardTransactions() {
    return cardTransactions;
  }
  public void setCardTransactions(CardAccountReport cardTransactions) {
    this.cardTransactions = cardTransactions;
  }

  /**
   **/
  public CardAccountsTransactionsResponse200 balances(BalanceList balances) {
    this.balances = balances;
    return this;
  }

 
  public BalanceList getBalances() {
    return balances;
  }
  public void setBalances(BalanceList balances) {
    this.balances = balances;
  }

  /**
   **/
  public CardAccountsTransactionsResponse200 _links(LinksDownload _links) {
    this._links = _links;
    return this;
  }

  
  public LinksDownload getLinks() {
    return _links;
  }
  public void setLinks(LinksDownload _links) {
    this._links = _links;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CardAccountsTransactionsResponse200 cardAccountsTransactionsResponse200 = (CardAccountsTransactionsResponse200) o;
    return Objects.equals(cardAccount, cardAccountsTransactionsResponse200.cardAccount) &&
        Objects.equals(debitAccounting, cardAccountsTransactionsResponse200.debitAccounting) &&
        Objects.equals(cardTransactions, cardAccountsTransactionsResponse200.cardTransactions) &&
        Objects.equals(balances, cardAccountsTransactionsResponse200.balances) &&
        Objects.equals(_links, cardAccountsTransactionsResponse200._links);
  }

  @Override
  public int hashCode() {
    return Objects.hash(cardAccount, debitAccounting, cardTransactions, balances, _links);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CardAccountsTransactionsResponse200 {\n");
    
    sb.append("    cardAccount: ").append(toIndentedString(cardAccount)).append("\n");
    sb.append("    debitAccounting: ").append(toIndentedString(debitAccounting)).append("\n");
    sb.append("    cardTransactions: ").append(toIndentedString(cardTransactions)).append("\n");
    sb.append("    balances: ").append(toIndentedString(balances)).append("\n");
    sb.append("    _links: ").append(toIndentedString(_links)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
