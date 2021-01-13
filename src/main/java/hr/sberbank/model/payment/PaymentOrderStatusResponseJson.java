package hr.sberbank.model.payment;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class PaymentOrderStatusResponseJson {
    private @Valid TransactionStatus transactionStatus = null;

    public PaymentOrderStatusResponseJson() {
    }
    @NotNull
    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }
}
