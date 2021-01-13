package hr.abc.psd2.model.cards;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * List of card accounts with details. 
 **/

@Schema(description = "List of card accounts with details. ")
public class CardAccountList {
	private @Valid List<CardAccountDetails> cardAccounts = new ArrayList<CardAccountDetails>();

	/**
	 **/
	public CardAccountList cardAccounts(List<CardAccountDetails> cardAccounts) {
		this.cardAccounts = cardAccounts;
		return this;
	}

	public List<CardAccountDetails> getCardAccounts() {
		return cardAccounts;
	}

	public void setCardAccounts(List<CardAccountDetails> cardAccounts) {
		this.cardAccounts = cardAccounts;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CardAccountList cardAccountList = (CardAccountList) o;
		return Objects.equals(cardAccounts, cardAccountList.cardAccounts);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cardAccounts);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class CardAccountList {\n");
		sb.append("    cardAccounts: ").append(toIndentedString(cardAccounts)).append("\n");
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
