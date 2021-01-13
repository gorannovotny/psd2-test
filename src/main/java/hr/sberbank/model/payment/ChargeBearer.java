package hr.sberbank.model.payment;

public enum ChargeBearer {
    DEBT("DEBT"),
    CRED("CRED"),
    SHAR("SHAR"),
    SLEV("SLEV");

    private String value;

    ChargeBearer(String value) {
        this.value = value;
    }
}
