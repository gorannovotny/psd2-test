package hr.sberbank.model.authentication;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ValidateMacActionResponse {
    public enum StatusEnum {

        MAC_VALID("MAC_VALID"), VALIDATION_FAILED("VALIDATION_FAILED");


        private final String value;

        StatusEnum (String v) {
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
        public static StatusEnum fromValue(String v) {
            for (StatusEnum b : StatusEnum.values()) {
                if (String.valueOf(b.value).equals(v)) {
                    return b;
                }
            }
            return null;
        }
    }
    private @Valid StatusEnum status = null;
    private @Valid ValidateMacActionResponseReason reason = null;

    public ValidateMacActionResponse() {
    }
    @NotNull
    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public ValidateMacActionResponseReason getReason() {
        return reason;
    }

    public void setReason(ValidateMacActionResponseReason reason) {
        this.reason = reason;
    }
}
