package hr.abc.psd2.mapper;

import hr.sberbank.model.account.CreditCardDetails;

import static hr.abc.psd2.model.cards.BalanceType.INTERIMBOOKED;
import static hr.abc.psd2.model.cards.BalanceType.NONINVOICED;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.Dependent;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import hr.abc.psd2.model.cards.AccountStatus;
import hr.abc.psd2.model.cards.Amount;
import hr.abc.psd2.model.cards.Balance;
import hr.abc.psd2.model.cards.BalanceList;
import hr.abc.psd2.model.cards.BalanceType;
import hr.abc.psd2.model.cards.CardAccountDetails;
import hr.abc.psd2.model.cards.HrefType;
import hr.abc.psd2.model.cards.LinksAccountDetails;
import hr.abc.psd2.model.cards.CardAccountDetails.UsageEnum;

import hr.sberbank.model.account.CreditCardAccount;
import hr.sberbank.model.account.CreditCardAccount.CreditAccountStatusEnum;
import hr.sberbank.model.account.CreditCardBalances;
import hr.sberbank.model.account.CreditCardDetails.CardStatusEnum;

@Dependent
public class CardsMapper implements Serializable {

	private static final long serialVersionUID = 1L;

	public CardAccountDetails mapToCardAccountDetails(CreditCardAccount crdDet) {

		Optional<CreditCardDetails> cardDetails = crdDet.getCards().stream()
				.filter(c -> c.getCardStatus().equals(CardStatusEnum.ACTIVE))
				.findFirst();

		String pan = "";
		if (cardDetails.isPresent()) {
			pan = cardDetails.get().getPan();
		}
		return new CardAccountDetails().resourceId(crdDet.getCreditAccount())
				.maskedPan(pan)
				.currency(crdDet.getCurrency())
				//.ownerName(crdDet.)
				//.name(crdDet.getCreditType())
				//.displayName(crdDet.)
				.product(crdDet.getCreditType())
				//.debitAccounting(crdDet.)
				.status(mapCardAccountDetailsStatus(crdDet.getCreditAccountStatus()))
				//.usage(crdDet.)
				//.details(crdDet.)
				.creditLimit(new Amount().amount(crdDet.getBalances().getCreditLimit().toPlainString()).currency(crdDet.getCurrency()))
				.balances(mapBalances(crdDet.getBalances(), crdDet.getCurrency()))
				._links(buildAccountDetailsLinks(crdDet, crdDet.getCreditAccount()));
	}

	private AccountStatus mapCardAccountDetailsStatus(CreditAccountStatusEnum creditAccountStatus) {

		AccountStatus ret;

		switch (creditAccountStatus) {
		case ENTERED:
			ret = AccountStatus.ENABLED;// pitati
			break;
		case ACTIVE:
			ret = AccountStatus.ENABLED;
			break;
		case BLOCKED:
			ret = AccountStatus.BLOCKED;
			break;
		case CANCELED:
			ret = AccountStatus.DELETED;
			break;
		case CLOSED:
			ret = AccountStatus.DELETED;
			break;
		case UNKNOWN:
			ret = AccountStatus.BLOCKED; // pitati
			break;
		default:
			ret = AccountStatus.BLOCKED; // pitati
		}

		return ret;
	}

	private LinksAccountDetails buildAccountDetailsLinks(CreditCardAccount crdDet, String accId) {
		return new LinksAccountDetails()
				.balances(new HrefType().href(MessageFormat.format("/v1/card-accounts/{0}/balances", accId)))
				.transactions(new HrefType().href(MessageFormat.format("/v1/card-accounts/{0}/transactions", accId)));
	}

	private BalanceList mapBalances(@NotNull CreditCardBalances balances, String currency) {
		BalanceList bl = new BalanceList();
		if (balances != null) {
			bl.add(new Balance()
					.balanceAmount(new Amount().amount(balances.getNewExpenses().toPlainString()).currency(currency))
					//.timestamp()
					.balanceType(NONINVOICED));

			bl.add(new Balance()
					.balanceAmount(new Amount().amount(balances.getNonDuePrincipal().toPlainString()).currency(currency))
					//.timestamp()
					.balanceType(INTERIMBOOKED));
		}
		//		CLOSINGBOOKED("closingBooked"), 
		//		EXPECTED("expected"), 
		//		OPENINGBOOKED("openingBooked"), 
		//		INTERIMAVAILABLE("interimAvailable"), 
		//		INTERIMBOOKED("interimBooked"), 
		//		FORWARDAVAILABLE("forwardAvailable"), 
		//		NONINVOICED("nonInvoiced");		

		return bl;
	}
}