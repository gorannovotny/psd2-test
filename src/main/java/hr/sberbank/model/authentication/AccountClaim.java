package hr.sberbank.model.authentication;

public enum AccountClaim {

    VIEW_INFORMATION("VIEW_INFORMATION"),
    SUBMIT_TRANSACTION("SUBMIT_TRANSACTION"),
    AUTHORIZE_TRANSACTION("AUTHORIZE_TRANSACTION");

    private String value;

    AccountClaim(String value) {
        this.value = value;
    }
}
