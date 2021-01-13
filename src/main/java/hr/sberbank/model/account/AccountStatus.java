package hr.sberbank.model.account;

public enum AccountStatus {
    ACTIVE("ACTIVE"),
    CLOSED("CLOSED");

    private String value;

    AccountStatus(String value) {
        this.value = value;
    }

}
