package hr.sberbank.model.authentication;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ValidateOtpActionResponseReason {

    public enum CodeEnum {

        UNSUPPORTED_DEVICE_TYPE("UNSUPPORTED_DEVICE_TYPE"), TRANSACTION_NOT_OK("TRANSACTION_NOT_OK"), INVALID_OTP("INVALID_OTP"), TOKEN_NOT_EXISTING("TOKEN_NOT_EXISTING"), USER_NOT_EXISTING("USER_NOT_EXISTING"), NOT_HAVING_REQUIRED_SERVICE("NOT_HAVING_REQUIRED_SERVICE"), TOKEN_BLOCKED("TOKEN_BLOCKED"), OTP_TRIES_DEPLETED("OTP_TRIES_DEPLETED"), TOO_MANY_TOKENS_ASSIGNED("TOO_MANY_TOKENS_ASSIGNED"), UNKNOWN_TRANSACTION_STATUS("UNKNOWN_TRANSACTION_STATUS"), OTHER("OTHER");


        private final String value;

        CodeEnum (String v) {
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
        public static CodeEnum fromValue(String v) {
            for (CodeEnum b : CodeEnum.values()) {
                if (String.valueOf(b.value).equals(v)) {
                    return b;
                }
            }
            return null;
        }
    }
    private @Valid CodeEnum code = null;
    private @Valid String message = null;

    public ValidateOtpActionResponseReason() {
    }
    @NotNull
    public CodeEnum getCode() {
        return code;
    }

    public void setCode(CodeEnum code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
