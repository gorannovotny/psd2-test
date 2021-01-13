package hr.abc.psd2.model.cards;


/**
 * Account status. The value is one of the following:   - \&quot;enabled\&quot;: account is available   - \&quot;deleted\&quot;: account is terminated   - \&quot;blocked\&quot;: account is blocked e.g. for legal reasons If this field is not used, than the account is available in the sense of this specification. 
 **/
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Account status. The value is one of the following:   - \&quot;enabled\&quot;: account is available   - \&quot;deleted\&quot;: account is terminated   - \&quot;blocked\&quot;: account is blocked e.g. for legal reasons If this field is not used, than the account is available in the sense of this specification. 
 */
public enum AccountStatus {
	ENABLED("enabled"), DELETED("deleted"), BLOCKED("blocked");

	private String value;

	AccountStatus(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static AccountStatus fromValue(String text) {
		for (AccountStatus b : AccountStatus.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}
}
