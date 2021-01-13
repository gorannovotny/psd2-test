package hr.abc.psd2.model.dto.pis;

public enum TransactionStatus {

    ACCP("ACCP"),  //Preceding check of technical validation was successful. Customer profile check was also successful
    ACSC("ACSC"),  //Settlement on the debtor's account has been completed. Usage : this can be used by the first agent to report to the debtor that the transaction has been completed. Warning : this status is provided for transaction status reasons, not for financial information. It can only be used after bilateral agreement"),
    ACSP("ACSP"),  //All preceding checks such as technical validation and customer profile were successful and therefore the payment initiation has been accepted for execution
    ACTC("ACTC"),  //AuthenticationObject and syntactical and semantical validation are successful"),
    ACWC("ACWC"),  //Instruction is accepted but a change will be made, such as date or remittance not sent
    ACWP("ACWP"),  //Payment instruction included in the credit transfer is accepted without being posted to the creditor customer’s account
    RCVD("RCVD"),  //Payment initiation has been received by the receiving agent
    PDNG("PDNG"),  //Payment initiation or individual transaction included in the payment initiation is pending. Further checks and status update will be performed
    RJCT("RJCT"),  //Payment initiation or individual transaction included in the payment initiation has been rejected
    CANC("CANC"), //Payment initiation has been cancelled before execution
    PATC("PATC"),
    ACFC("ACFC");


    private String transactionStatus;

    TransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public static TransactionStatus getByName(String name) {
        for(TransactionStatus e : values()) {
            if(e.transactionStatus.equals(name)) return e;
        }
        return null;
    }
}
