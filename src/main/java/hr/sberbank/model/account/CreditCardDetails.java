package hr.sberbank.model.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CreditCardDetails {

    public CreditCardDetails() {
    }

    private @Valid BigDecimal cardCode = null;
    private @Valid String pan = null;
    private @Valid String cardIssuingDate = null;



    public enum CardStatusEnum {

        PREPARED("PREPARED"), ACTIVE("ACTIVE"), BLOCKED("BLOCKED"), INACTIVE("INACTIVE"), DEACTIVATED("DEACTIVATED"), ERROR("ERROR"), UNKNOWN("UNKNOWN");


        private final String value;

        CardStatusEnum (String v) {
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
        public static CardStatusEnum fromValue(String v) {
            for (CardStatusEnum b : CardStatusEnum.values()) {
                if (String.valueOf(b.value).equals(v)) {
                    return b;
                }
            }
            return null;
        }
    }
    private @Valid CardStatusEnum cardStatus = null;
    private @Valid String type = null;
    @NotNull
    public BigDecimal getCardCode() {
        return cardCode;
    }

    public void setCardCode(BigDecimal cardCode) {
        this.cardCode = cardCode;
    }
    @NotNull
    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }
    @NotNull
    public String getCardIssuingDate() {
        return cardIssuingDate;
    }

    public void setCardIssuingDate(String cardIssuingDate) {
        this.cardIssuingDate = cardIssuingDate;
    }
    @NotNull
    public CardStatusEnum getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatusEnum cardStatus) {
        this.cardStatus = cardStatus;
    }
    @NotNull
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
