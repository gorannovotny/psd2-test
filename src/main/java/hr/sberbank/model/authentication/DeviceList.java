package hr.sberbank.model.authentication;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class DeviceList {

    private @Valid List<Device> devices = new ArrayList<Device>();

    public DeviceList() {
    }
    @NotNull
    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}
