package hr.sberbank.model.account;

public enum BalanceType {
    CURRENT("CURRENT"),
    AVAILABLE("AVAILABLE");

    private String value;

    BalanceType(String value) {
        this.value = value;
    }
}
