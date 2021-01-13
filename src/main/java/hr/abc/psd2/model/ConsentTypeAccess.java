package hr.abc.psd2.model;

public enum ConsentTypeAccess {
	
    ACCOUNT("account"),
    BALANCE("balance"),
    TRANSACTION("transaction"),
    FUNDS("funds");

    private String value;

    ConsentTypeAccess(String type) {
        this.value = type;
    }

    public String getValue() {
        return value;
    }
}