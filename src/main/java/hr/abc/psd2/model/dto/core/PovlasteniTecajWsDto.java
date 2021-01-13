package hr.abc.psd2.model.dto.core;

import hr.abc.psd2.util.GenericDateUtil;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Objekt u kojemu se šalje info za upis povlaštenog tečaja.
 * 
 * 'customer_id': ######, 'currency_code': '###', 'rate_type': '***', 'rate': #.######, 'valid_from': 'YYYY-MM-DD HH:MM:SS', 'valid_to': 'YYYY-MM-DD HH:MM:SS', 'user_id': ####, 'amount': #####.##
 * 
 * @author Mata
 */
@XmlRootElement(name="currencyFlow")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class PovlasteniTecajWsDto {

	// fields
	private Integer customer_id, user_id;
	private String currency_code, rate_type;
	private BigDecimal rate, amount;
	private Date valid_from, valid_to;

	// constructor
	public PovlasteniTecajWsDto() {
		super();
	}
	
//	public PovlasteniTecajWsDto(JSONObject jsonObject) {
//		super();
//		if (jsonObject != null) {
//			this.customer_id = jsonObject.getInt("customer_id");
//			this.user_id = jsonObject.getInt("user_id");
//			this.currency_code = jsonObject.getString("currency_code");
//			this.rate_type = jsonObject.getString("rate_type");
//			this.rate = jsonObject.getBigDecimal("rate");
//			this.amount = jsonObject.opt("amount") != null ? new BigDecimal(jsonObject.opt("amount").toString()) : null;
//			this.valid_from = GenericDateUtil.getDateFromString(jsonObject.getString("valid_from"), GenericDateUtil.SQL_DATETIME_FORMAT);
//			this.valid_to = GenericDateUtil.getDateFromString(jsonObject.getString("valid_to"), GenericDateUtil.SQL_DATETIME_FORMAT);
//		}
//	}

	// getters & setters
	@XmlElement
	public Integer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	@XmlElement
	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	@XmlElement
	public String getCurrency_code() {
		return currency_code;
	}

	public void setCurrency_code(String currency_code) {
		this.currency_code = currency_code;
	}

	@XmlElement
	public String getRate_type() {
		return rate_type;
	}

	public void setRate_type(String rate_type) {
		this.rate_type = rate_type;
	}

	@XmlElement
	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}

	@XmlElement
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@XmlElement
	public Date getValid_from() {
		return valid_from;
	}

	public void setValid_from(Date valid_from) {
		this.valid_from = valid_from;
	}

	@XmlElement
	public Date getValid_to() {
		return valid_to;
	}

	public void setValid_to(Date valid_to) {
		this.valid_to = valid_to;
	}

}
