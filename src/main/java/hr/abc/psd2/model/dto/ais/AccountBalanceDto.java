package hr.abc.psd2.model.dto.ais;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author  dhorvat
 */

public class AccountBalanceDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private String balanceType; // closingBooked, expected,opening_booked, booked, authorised
	private Amount balanceAmount;
	private LocalDateTime referenceDate;
	private Date lastChangeDateTime;

	public AccountBalanceDto() {
		super();
	}

	public String getBalanceType() {
		return balanceType;
	}

	public void setBalanceType(String balanceType) {
		this.balanceType = balanceType;
	}

	public Amount getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(Amount balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public LocalDateTime getReferenceDate() {
		return referenceDate;
	}

	public void setReferenceDate(LocalDateTime referenceDate) {
		this.referenceDate = referenceDate;
	}

	public Date getLastChangeDateTime() {
		return lastChangeDateTime;
	}

	public void setLastChangeDateTime(Date lastChangeDateTime) {
		this.lastChangeDateTime = lastChangeDateTime;
	}
}
