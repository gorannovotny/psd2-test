package hr.sberbank.model.authentication;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ValidateOtpActionRequest {
    private @Valid String profile = null;
    private @Valid String otp = null;

    public ValidateOtpActionRequest() {
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
    @NotNull
    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
