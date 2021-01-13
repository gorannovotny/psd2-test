package hr.abc.psd2.bean.impl.sberbank;

import static hr.abc.psd2.bean.impl.ServiceConstants.SERVICE_SBER;
import static hr.abc.psd2.bean.impl.ServiceConstants.TRUE;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import hr.abc.psd2.bean.ICardsBean;
import hr.abc.psd2.bean.impl.AbstractCardsBean;
import hr.abc.psd2.mapper.CardsMapper;
import hr.abc.psd2.model.cards.AccountStatus;
import hr.abc.psd2.model.cards.Amount;
import hr.abc.psd2.model.cards.CardAccountDetails;
import hr.abc.psd2.model.cards.CardAccountList;
import hr.abc.psd2.model.cards.CardAccountsTransactionsResponse200;
import hr.abc.psd2.model.cards.InlineResponse2007;
import hr.abc.psd2.model.cards.LinksAccountDetails;
import hr.abc.psd2.model.cards.ReadCardAccountBalanceResponse200;
import hr.abc.psd2.model.cards.CardAccountDetails.UsageEnum;
import hr.sberbank.model.account.BalanceList;
import hr.sberbank.model.account.CreditCardAccount;
import hr.sberbank.model.account.CreditCardDetails;
import hr.sberbank.resources.AccountRestSrvc;
import hr.sberbank.resources.TransactionsRestSrvc;
import io.quarkus.arc.properties.IfBuildProperty;

@Dependent
@IfBuildProperty(name = SERVICE_SBER, stringValue = TRUE)
@Transactional
public class CardsBeanSber extends AbstractCardsBean implements ICardsBean, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	@RestClient
	AccountRestSrvc accountRestSrvc;

	@Inject
	@RestClient
	TransactionsRestSrvc transactionsRestSrvc;

	@Inject
	CardsMapper cardsMapper;

	@Override
	public CardAccountList getCardAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReadCardAccountBalanceResponse200 getCardAccountBalances(String accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CardAccountsTransactionsResponse200 getCardAccountTransactionList(String accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InlineResponse2007 readCardAccount(String accountId) {
		CreditCardAccount creditCardDetails = accountRestSrvc.creditCardAccountsIdGet(accountId);
		CardAccountDetails cardAccDet = cardsMapper.mapToCardAccountDetails(creditCardDetails);
		//dodati ownera
		return new InlineResponse2007().cardAccount(cardAccDet);		
	}
}
