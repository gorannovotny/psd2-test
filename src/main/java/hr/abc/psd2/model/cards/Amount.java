package hr.abc.psd2.model.cards;

import java.util.Objects;

public class Amount {
	private String currency = null;
	private String amount = null;

	/**
	 **/
	public Amount currency(String currency) {
		this.currency = currency;
		return this;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 **/
	public Amount amount(String amount) {
		this.amount = amount;
		return this;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Amount amount = (Amount) o;
		return Objects.equals(currency, amount.currency) &&
				Objects.equals(amount, amount.amount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(currency, amount);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Amount {\n");

		sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
		sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
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
