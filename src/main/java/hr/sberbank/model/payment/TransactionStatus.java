package hr.sberbank.model.payment;

public enum TransactionStatus {

    ACCC("ACCC"),
    ACCP("ACCP"),
    ACSC("ACSC"),
    ACSP("ACSP"),
    ACTC("ACTC"),
    ACWC("ACWC"),
    ACWP("ACWP"),
    RCVD("RCVD"),
    PDNG("PDNG"),
    RJCT("RJCT"),
    CANC("CANC"),
    ACFC("ACFC"),
    PATC("PATC"),
    PART("PART");

    private String value;

    TransactionStatus(String value) {
        this.value = value;
    }
}
