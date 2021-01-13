package hr.abc.psd2.model.cards;

import java.util.HashMap;
import java.util.Objects;

import javax.validation.Valid;

public class LinksCardAccountReport extends HashMap<String, HrefType> {
	private @Valid HrefType cardAccount = null;
	private @Valid HrefType card = null;
	private @Valid HrefType first = null;
	private @Valid HrefType next = null;
	private @Valid HrefType previous = null;
	private @Valid HrefType last = null;

	/**
	 **/
	public LinksCardAccountReport cardAccount(HrefType cardAccount) {
		this.cardAccount = cardAccount;
		return this;
	}

	public HrefType getCardAccount() {
		return cardAccount;
	}

	public void setCardAccount(HrefType cardAccount) {
		this.cardAccount = cardAccount;
	}

	/**
	 **/
	public LinksCardAccountReport card(HrefType card) {
		this.card = card;
		return this;
	}

	public HrefType getCard() {
		return card;
	}

	public void setCard(HrefType card) {
		this.card = card;
	}

	/**
	 **/
	public LinksCardAccountReport first(HrefType first) {
		this.first = first;
		return this;
	}

	public HrefType getFirst() {
		return first;
	}

	public void setFirst(HrefType first) {
		this.first = first;
	}

	/**
	 **/
	public LinksCardAccountReport next(HrefType next) {
		this.next = next;
		return this;
	}

	public HrefType getNext() {
		return next;
	}

	public void setNext(HrefType next) {
		this.next = next;
	}

	/**
	 **/
	public LinksCardAccountReport previous(HrefType previous) {
		this.previous = previous;
		return this;
	}

	public HrefType getPrevious() {
		return previous;
	}

	public void setPrevious(HrefType previous) {
		this.previous = previous;
	}

	/**
	 **/
	public LinksCardAccountReport last(HrefType last) {
		this.last = last;
		return this;
	}

	public HrefType getLast() {
		return last;
	}

	public void setLast(HrefType last) {
		this.last = last;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		LinksCardAccountReport _linksCardAccountReport = (LinksCardAccountReport) o;
		return Objects.equals(cardAccount, _linksCardAccountReport.cardAccount) &&
				Objects.equals(card, _linksCardAccountReport.card) &&
				Objects.equals(first, _linksCardAccountReport.first) &&
				Objects.equals(next, _linksCardAccountReport.next) &&
				Objects.equals(previous, _linksCardAccountReport.previous) &&
				Objects.equals(last, _linksCardAccountReport.last);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cardAccount, card, first, next, previous, last);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class LinksCardAccountReport {\n");
		sb.append("    ").append(toIndentedString(super.toString())).append("\n");
		sb.append("    cardAccount: ").append(toIndentedString(cardAccount)).append("\n");
		sb.append("    card: ").append(toIndentedString(card)).append("\n");
		sb.append("    first: ").append(toIndentedString(first)).append("\n");
		sb.append("    next: ").append(toIndentedString(next)).append("\n");
		sb.append("    previous: ").append(toIndentedString(previous)).append("\n");
		sb.append("    last: ").append(toIndentedString(last)).append("\n");
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
