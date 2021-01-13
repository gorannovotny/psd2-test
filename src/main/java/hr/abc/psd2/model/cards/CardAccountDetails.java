package hr.abc.psd2.model.cards;

import java.util.Objects;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Card account details. ")
public class CardAccountDetails {
	private String resourceId = null;
	private String maskedPan = null;
	private String currency = null;
	private String ownerName = null;
	private String name = null;
	private String displayName = null;
	private String product = null;
	private Boolean debitAccounting = null;
	private AccountStatus status = null;
	private UsageEnum usage = null;
	private String details = null;
	private Amount creditLimit = null;
	private BalanceList balances = null;
	private LinksAccountDetails _links = null;

	/**
	 * This is the data element to be used in the path when retrieving data from a dedicated account. This shall be filled, if addressable resource are created by the ASPSP on the /card-accounts endpoint. 
	 **/
	public CardAccountDetails resourceId(String resourceId) {
		this.resourceId = resourceId;
		return this;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	/**
	 **/
	public CardAccountDetails maskedPan(String maskedPan) {
		this.maskedPan = maskedPan;
		return this;
	}

	public String getMaskedPan() {
		return maskedPan;
	}

	public void setMaskedPan(String maskedPan) {
		this.maskedPan = maskedPan;
	}

	/**
	 **/
	public CardAccountDetails currency(String currency) {
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
	public CardAccountDetails ownerName(String ownerName) {
		this.ownerName = ownerName;
		return this;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	/**
	 * Name of the account, as assigned by the ASPSP,  in agreement with the account owner in order to provide an additional means of identification of the account. 
	 **/
	public CardAccountDetails name(String name) {
		this.name = name;
		return this;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 **/
	public CardAccountDetails displayName(String displayName) {
		this.displayName = displayName;
		return this;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * Product Name of the Bank for this account, proprietary definition. 
	 **/
	public CardAccountDetails product(String product) {
		this.product = product;
		return this;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	/**
	 **/
	public CardAccountDetails debitAccounting(Boolean debitAccounting) {
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
	public CardAccountDetails status(AccountStatus status) {
		this.status = status;
		return this;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	/**
	 * Specifies the usage of the account:   * PRIV: private personal account   * ORGA: professional account 
	 **/
	public CardAccountDetails usage(UsageEnum usage) {
		this.usage = usage;
		return this;
	}

	public UsageEnum getUsage() {
		return usage;
	}

	public void setUsage(UsageEnum usage) {
		this.usage = usage;
	}

	/**
	 * Specifications that might be provided by the ASPSP:   - characteristics of the account   - characteristics of the relevant card 
	 **/
	public CardAccountDetails details(String details) {
		this.details = details;
		return this;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	/**
	 **/
	public CardAccountDetails creditLimit(Amount creditLimit) {
		this.creditLimit = creditLimit;
		return this;
	}

	public Amount getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(Amount creditLimit) {
		this.creditLimit = creditLimit;
	}

	/**
	 **/
	public CardAccountDetails balances(BalanceList balances) {
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
	public CardAccountDetails _links(LinksAccountDetails _links) {
		this._links = _links;
		return this;
	}

	public LinksAccountDetails getLinks() {
		return _links;
	}

	public void setLinks(LinksAccountDetails _links) {
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
		CardAccountDetails cardAccountDetails = (CardAccountDetails) o;
		return Objects.equals(resourceId, cardAccountDetails.resourceId) &&
				Objects.equals(maskedPan, cardAccountDetails.maskedPan) &&
				Objects.equals(currency, cardAccountDetails.currency) &&
				Objects.equals(ownerName, cardAccountDetails.ownerName) &&
				Objects.equals(name, cardAccountDetails.name) &&
				Objects.equals(displayName, cardAccountDetails.displayName) &&
				Objects.equals(product, cardAccountDetails.product) &&
				Objects.equals(debitAccounting, cardAccountDetails.debitAccounting) &&
				Objects.equals(status, cardAccountDetails.status) &&
				Objects.equals(usage, cardAccountDetails.usage) &&
				Objects.equals(details, cardAccountDetails.details) &&
				Objects.equals(creditLimit, cardAccountDetails.creditLimit) &&
				Objects.equals(balances, cardAccountDetails.balances) &&
				Objects.equals(_links, cardAccountDetails._links);
	}

	@Override
	public int hashCode() {
		return Objects.hash(resourceId, maskedPan, currency, ownerName, name, displayName, product, debitAccounting, status, usage, details, creditLimit, balances, _links);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class CardAccountDetails {\n");

		sb.append("    resourceId: ").append(toIndentedString(resourceId)).append("\n");
		sb.append("    maskedPan: ").append(toIndentedString(maskedPan)).append("\n");
		sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
		sb.append("    ownerName: ").append(toIndentedString(ownerName)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    displayName: ").append(toIndentedString(displayName)).append("\n");
		sb.append("    product: ").append(toIndentedString(product)).append("\n");
		sb.append("    debitAccounting: ").append(toIndentedString(debitAccounting)).append("\n");
		sb.append("    status: ").append(toIndentedString(status)).append("\n");
		sb.append("    usage: ").append(toIndentedString(usage)).append("\n");
		sb.append("    details: ").append(toIndentedString(details)).append("\n");
		sb.append("    creditLimit: ").append(toIndentedString(creditLimit)).append("\n");
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

	public enum UsageEnum {

		PRIV(String.valueOf("PRIV")), ORGA(String.valueOf("ORGA"));

		private String value;

		UsageEnum(String v) {
			value = v;
		}

		public String value() {
			return value;
		}

		public String toString() {
			return String.valueOf(value);
		}

		public static UsageEnum fromValue(String v) {
			for (UsageEnum b : UsageEnum.values()) {
				if (String.valueOf(b.value).equals(v)) {
					return b;
				}
			}
			return null;
		}
	}
}
