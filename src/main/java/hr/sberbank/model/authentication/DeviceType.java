package hr.sberbank.model.authentication;

public enum DeviceType {

    UNKNOWN("UNKNOWN"),
    MTOKEN("MTOKEN"),
    VASCO("VASCO"),
    GEMALTO("GEMALTO");

    private String value;

    DeviceType(String value) {
        this.value = value;
    }
}
