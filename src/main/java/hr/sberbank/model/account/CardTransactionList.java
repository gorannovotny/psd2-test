package hr.sberbank.model.account;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class CardTransactionList {

    private @Valid List<CardTransaction> cardTransactions = new ArrayList<CardTransaction>();

    public CardTransactionList() {
    }

    @NotNull
    public List<CardTransaction> getCardTransactions() {
        return cardTransactions;
    }

    public void setCardTransactions(List<CardTransaction> cardTransactions) {
        this.cardTransactions = cardTransactions;
    }
}
