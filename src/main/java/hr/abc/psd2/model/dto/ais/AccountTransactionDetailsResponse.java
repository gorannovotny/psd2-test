package hr.abc.psd2.model.dto.ais;

import java.io.Serializable;

public class AccountTransactionDetailsResponse implements Serializable{

    private static final long serialVersionUID = 1L;
    private AccountTransactionDto transactionsDetails;

    public AccountTransactionDto getTransactionsDetails() {
        return transactionsDetails;
    }

    public void setTransactionsDetails(AccountTransactionDto transactionsDetails) {
        this.transactionsDetails = transactionsDetails;
    }
}
