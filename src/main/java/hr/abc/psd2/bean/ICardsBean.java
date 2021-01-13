package hr.abc.psd2.bean;

import hr.abc.psd2.model.cards.CardAccountList;
import hr.abc.psd2.model.cards.CardAccountsTransactionsResponse200;
import hr.abc.psd2.model.cards.InlineResponse2007;
import hr.abc.psd2.model.cards.ReadCardAccountBalanceResponse200;

public interface ICardsBean {

	public CardAccountList getCardAccount();

	public ReadCardAccountBalanceResponse200 getCardAccountBalances(String accountId);

	public CardAccountsTransactionsResponse200 getCardAccountTransactionList(String accountId);

	public InlineResponse2007 readCardAccount(String accountId);
}