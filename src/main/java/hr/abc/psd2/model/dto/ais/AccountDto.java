package hr.abc.psd2.model.dto.ais;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class AccountDto implements Serializable{

	// fields
	private static final long serialVersionUID = 1L;
	private String resourceId;
	private String iban;
	private String currency;
	private String product;
	private String cashAccountType;
	private String name;
	private String sifraVal;
	private List<Links> _links;

	// constructors
	public AccountDto() {
		super();
	}

	public AccountDto(String resourceId, String iban, String currency, String product, String cashAccountType, String name) {
		super();
		this.resourceId = resourceId;
		this.iban = iban;
		this.currency = currency;
		this.product = product;
		this.cashAccountType = cashAccountType;
		this.name = name;
		
	}

	// getters & setters
	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getCashAccountType() {
		return cashAccountType;
	}

	public void setCashAccountType(String cashAccountType) {
		this.cashAccountType = cashAccountType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Links> get_links() {
		return _links;
	}

	public String getSifraVal() {
		return sifraVal;
	}

	public void setSifraVal(String sifraVal) {
		this.sifraVal = sifraVal;
	}

	public void set_links(List<Links> _links) {
		this._links = _links;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AccountDto)) return false;
		AccountDto that = (AccountDto) o;
		return Objects.equals(resourceId, that.resourceId) &&
				Objects.equals(iban, that.iban) &&
				Objects.equals(currency, that.currency) &&
				Objects.equals(product, that.product) &&
				Objects.equals(cashAccountType, that.cashAccountType) &&
				Objects.equals(name, that.name) &&
				Objects.equals(sifraVal, that.sifraVal) &&
				Objects.equals(_links, that._links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(resourceId, iban, currency, product, cashAccountType, name, sifraVal, _links);
	}
}
