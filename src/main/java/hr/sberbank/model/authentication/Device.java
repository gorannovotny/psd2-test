package hr.sberbank.model.authentication;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Device {

    private @Valid String id = null;
    private @Valid DeviceType type = null;
    private @Valid String serialNo = null;
    private @Valid String userName = null;
    private @Valid String customerId = null;

    public Device() {
    }
    @NotNull
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @NotNull
    public DeviceType getType() {
        return type;
    }

    public void setType(DeviceType type) {
        this.type = type;
    }
    @NotNull
    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
