package hr.abc.psd2.model.cards;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "A single balance element. ")

public class Balance {
	private Amount balanceAmount = null;
	private BalanceType balanceType = null;
	private Boolean creditLimitIncluded = null;
	private Date lastChangeDateTime = null;
	private LocalDate referenceDate = null;
	private String lastCommittedTransaction = null;

	/**
	 **/
	public Balance balanceAmount(Amount balanceAmount) {
		this.balanceAmount = balanceAmount;
		return this;
	}

	public Amount getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(Amount balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	/**
	 **/
	public Balance balanceType(BalanceType balanceType) {
		this.balanceType = balanceType;
		return this;
	}

	public BalanceType getBalanceType() {
		return balanceType;
	}

	public void setBalanceType(BalanceType balanceType) {
		this.balanceType = balanceType;
	}

	/**
	 * A flag indicating if the credit limit of the corresponding account  is included in the calculation of the balance, where applicable. 
	 **/
	public Balance creditLimitIncluded(Boolean creditLimitIncluded) {
		this.creditLimitIncluded = creditLimitIncluded;
		return this;
	}

	public Boolean isCreditLimitIncluded() {
		return creditLimitIncluded;
	}

	public void setCreditLimitIncluded(Boolean creditLimitIncluded) {
		this.creditLimitIncluded = creditLimitIncluded;
	}

	/**
	 * This data element might be used to indicate e.g. with the expected or booked balance that no action is known  on the account, which is not yet booked. 
	 **/
	public Balance lastChangeDateTime(Date lastChangeDateTime) {
		this.lastChangeDateTime = lastChangeDateTime;
		return this;
	}

	public Date getLastChangeDateTime() {
		return lastChangeDateTime;
	}

	public void setLastChangeDateTime(Date lastChangeDateTime) {
		this.lastChangeDateTime = lastChangeDateTime;
	}

	/**
	 * Indicates the date of the balance.
	 **/
	public Balance referenceDate(LocalDate referenceDate) {
		this.referenceDate = referenceDate;
		return this;
	}

	public LocalDate getReferenceDate() {
		return referenceDate;
	}

	public void setReferenceDate(LocalDate referenceDate) {
		this.referenceDate = referenceDate;
	}

	/**
	 * \&quot;entryReference\&quot; of the last commited transaction to support the TPP in identifying whether all  PSU transactions are already known. 
	 **/
	public Balance lastCommittedTransaction(String lastCommittedTransaction) {
		this.lastCommittedTransaction = lastCommittedTransaction;
		return this;
	}

	public String getLastCommittedTransaction() {
		return lastCommittedTransaction;
	}

	public void setLastCommittedTransaction(String lastCommittedTransaction) {
		this.lastCommittedTransaction = lastCommittedTransaction;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Balance balance = (Balance) o;
		return Objects.equals(balanceAmount, balance.balanceAmount) &&
				Objects.equals(balanceType, balance.balanceType) &&
				Objects.equals(creditLimitIncluded, balance.creditLimitIncluded) &&
				Objects.equals(lastChangeDateTime, balance.lastChangeDateTime) &&
				Objects.equals(referenceDate, balance.referenceDate) &&
				Objects.equals(lastCommittedTransaction, balance.lastCommittedTransaction);
	}

	@Override
	public int hashCode() {
		return Objects.hash(balanceAmount, balanceType, creditLimitIncluded, lastChangeDateTime, referenceDate, lastCommittedTransaction);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Balance {\n");

		sb.append("    balanceAmount: ").append(toIndentedString(balanceAmount)).append("\n");
		sb.append("    balanceType: ").append(toIndentedString(balanceType)).append("\n");
		sb.append("    creditLimitIncluded: ").append(toIndentedString(creditLimitIncluded)).append("\n");
		sb.append("    lastChangeDateTime: ").append(toIndentedString(lastChangeDateTime)).append("\n");
		sb.append("    referenceDate: ").append(toIndentedString(referenceDate)).append("\n");
		sb.append("    lastCommittedTransaction: ").append(toIndentedString(lastCommittedTransaction)).append("\n");
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
