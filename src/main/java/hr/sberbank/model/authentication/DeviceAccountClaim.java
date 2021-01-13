package hr.sberbank.model.authentication;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class DeviceAccountClaim {

    public enum AccountTypeEnum {

        UNKNOWN("UNKNOWN"), ACCOUNT("ACCOUNT"), CREDIT_CARD_ACCOUNT("CREDIT_CARD_ACCOUNT");


        private final String value;

        AccountTypeEnum(String v) {
            value = v;
        }

        public String value() {
            return value;
        }

        @Override
        @JsonValue
        public String toString() {
            return String.valueOf(value);
        }

        @JsonCreator
        public static AccountTypeEnum fromValue(String v) {
            for (AccountTypeEnum b : AccountTypeEnum.values()) {
                if (String.valueOf(b.value).equals(v)) {
                    return b;
                }
            }
            return null;
        }
    }

    private @Valid AccountTypeEnum accountType = null;
    private @Valid String accountId = null;
    private @Valid Integer requiredSignaturesCount = null;
    private @Valid List<AccountClaim> claims = new ArrayList<AccountClaim>();

    public DeviceAccountClaim() {
    }
    @NotNull
    public AccountTypeEnum getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountTypeEnum accountType) {
        this.accountType = accountType;
    }
    @NotNull
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
    @NotNull
    public Integer getRequiredSignaturesCount() {
        return requiredSignaturesCount;
    }

    public void setRequiredSignaturesCount(Integer requiredSignaturesCount) {
        this.requiredSignaturesCount = requiredSignaturesCount;
    }
    @NotNull
    public List<AccountClaim> getClaims() {
        return claims;
    }

    public void setClaims(List<AccountClaim> claims) {
        this.claims = claims;
    }
}
