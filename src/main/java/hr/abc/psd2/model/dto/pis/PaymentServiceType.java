package hr.abc.psd2.model.dto.pis;

/**
 * Created by Tomica on 14-Jan-19.
 */
public enum PaymentServiceType {
    BULK("bulk-payments"),
    PERIODIC("periodic-payments"),
    SINGLE("payments");

    private String value;

    PaymentServiceType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

