package hr.sberbank.model.authentication;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ValidateOtpActionResponse {

    public ValidateOtpActionResponse() {
    }

    public enum StatusEnum {

        OTP_VALID("OTP_VALID"), VALIDATION_FAILED("VALIDATION_FAILED");


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
    private @Valid ValidateOtpActionResponseReason reason = null;

    @NotNull
    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public ValidateOtpActionResponseReason getReason() {
        return reason;
    }

    public void setReason(ValidateOtpActionResponseReason reason) {
        this.reason = reason;
    }
}
