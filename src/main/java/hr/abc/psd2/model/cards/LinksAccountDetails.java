package hr.abc.psd2.model.cards;

import java.util.HashMap;
import java.util.Objects;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * Links to the account, which can be directly used for retrieving account information from this dedicated account.  Links to \&quot;balances\&quot; and/or \&quot;transactions\&quot;  These links are only supported, when the corresponding consent has been already granted. 
 **/

@Schema(description = "Links to the account, which can be directly used for retrieving account information from this dedicated account.  Links to \"balances\" and/or \"transactions\"  These links are only supported, when the corresponding consent has been already granted. ")
public class LinksAccountDetails extends HashMap<String, HrefType> {

	private static final long serialVersionUID = 1L;

	private HrefType balances = null;
	private HrefType transactions = null;

	/**
	 **/
	public LinksAccountDetails balances(HrefType balances) {
		this.balances = balances;
		return this;
	}

	public HrefType getBalances() {
		return balances;
	}

	public void setBalances(HrefType balances) {
		this.balances = balances;
	}

	/**
	 **/
	public LinksAccountDetails transactions(HrefType transactions) {
		this.transactions = transactions;
		return this;
	}

	public HrefType getTransactions() {
		return transactions;
	}

	public void setTransactions(HrefType transactions) {
		this.transactions = transactions;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		LinksAccountDetails _linksAccountDetails = (LinksAccountDetails) o;
		return Objects.equals(balances, _linksAccountDetails.balances) &&
				Objects.equals(transactions, _linksAccountDetails.transactions);
	}

	@Override
	public int hashCode() {
		return Objects.hash(balances, transactions);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class LinksAccountDetails {\n");
		sb.append("    ").append(toIndentedString(super.toString())).append("\n");
		sb.append("    balances: ").append(toIndentedString(balances)).append("\n");
		sb.append("    transactions: ").append(toIndentedString(transactions)).append("\n");
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
