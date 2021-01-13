package hr.sberbank.model.authentication;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class DeviceAccountClaimList {

    private @Valid List<DeviceAccountClaim> accountClaims = new ArrayList<DeviceAccountClaim>();

    public DeviceAccountClaimList() {
    }
    @NotNull
    public List<DeviceAccountClaim> getAccountClaims() {
        return accountClaims;
    }

    public void setAccountClaims(List<DeviceAccountClaim> accountClaims) {
        this.accountClaims = accountClaims;
    }
}
