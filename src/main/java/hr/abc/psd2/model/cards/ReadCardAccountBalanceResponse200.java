package hr.abc.psd2.model.cards;

import java.util.Objects;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import hr.sberbank.model.account.BalanceList;

/**
 * Body of the response for a successful read balance for a card account request.
 **/

@Schema(description = "Body of the response for a successful read balance for a card account request.")
public class ReadCardAccountBalanceResponse200 {
	private AccountReference cardAccount = null;
	private Boolean debitAccounting = null;
	private BalanceList balances = null;

	/**
	 **/
	public ReadCardAccountBalanceResponse200 cardAccount(AccountReference cardAccount) {
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
	public ReadCardAccountBalanceResponse200 debitAccounting(Boolean debitAccounting) {
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
	public ReadCardAccountBalanceResponse200 balances(BalanceList balances) {
		this.balances = balances;
		return this;
	}

	public BalanceList getBalances() {
		return balances;
	}

	public void setBalances(BalanceList balances) {
		this.balances = balances;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ReadCardAccountBalanceResponse200 readCardAccountBalanceResponse200 = (ReadCardAccountBalanceResponse200) o;
		return Objects.equals(cardAccount, readCardAccountBalanceResponse200.cardAccount) &&
				Objects.equals(debitAccounting, readCardAccountBalanceResponse200.debitAccounting) &&
				Objects.equals(balances, readCardAccountBalanceResponse200.balances);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cardAccount, debitAccounting, balances);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ReadCardAccountBalanceResponse200 {\n");

		sb.append("    cardAccount: ").append(toIndentedString(cardAccount)).append("\n");
		sb.append("    debitAccounting: ").append(toIndentedString(debitAccounting)).append("\n");
		sb.append("    balances: ").append(toIndentedString(balances)).append("\n");
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
