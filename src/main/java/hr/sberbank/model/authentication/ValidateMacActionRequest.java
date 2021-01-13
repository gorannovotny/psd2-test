package hr.sberbank.model.authentication;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class ValidateMacActionRequest {

    private @Valid String profile = null;
    private @Valid List<String> macInputs = new ArrayList<String>();
    private @Valid String mac = null;

    public ValidateMacActionRequest() {
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    @NotNull
    @Size(min=1)
    public List<String> getMacInputs() {
        return macInputs;
    }

    public void setMacInputs(List<String> macInputs) {
        this.macInputs = macInputs;
    }
    @NotNull
    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
