package hr.abc.psd2.bean.impl.sberbank;

import static hr.abc.psd2.bean.impl.ServiceConstants.SERVICE_SBER;
import static hr.abc.psd2.bean.impl.ServiceConstants.TRUE;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

import hr.sberbank.model.account.Transaction;
import hr.sberbank.model.account.TransactionDetails;
import hr.sberbank.resources.TransactionsRestSrvc;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import hr.abc.psd2.bean.IAccountInformationBean;
import hr.abc.psd2.bean.impl.AbstractAccountInformationBean;
import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.mapper.AccountMapper;
import hr.abc.psd2.model.dto.ais.AccountBalanceResponse;
import hr.abc.psd2.model.dto.ais.ConsentAccountAisDto;
import hr.abc.psd2.model.dto.ais.PrometStanjePartijeDto;
import hr.abc.psd2.model.dto.core.Psd2WebPregledRacunaDto;
import hr.abc.psd2.model.sepa.camt.Document;
import hr.sberbank.model.account.AccountDetails;
import hr.sberbank.model.account.AccountList;
import hr.sberbank.resources.AccountRestSrvc;
import io.quarkus.arc.properties.IfBuildProperty;

@Dependent
@IfBuildProperty(name = SERVICE_SBER, stringValue = TRUE)
@Transactional
public class AccountInformationBeanSber extends AbstractAccountInformationBean implements IAccountInformationBean, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	@RestClient
	AccountRestSrvc accountRestSrvc;

	@Inject
	@RestClient
	TransactionsRestSrvc transactionsRestSrvc;

	@Inject
	AccountMapper accontMapper;

	@Override
	protected List<Psd2WebPregledRacunaDto> getAccounts(String iban, String currency) {
		List<Psd2WebPregledRacunaDto> listOfAccounts = new ArrayList<>();
		AccountList aa = accountRestSrvc.accountsGet(iban, currency);
		listOfAccounts.addAll(accontMapper.listOfAccounts(aa.getAccounts()));
		return listOfAccounts;
	}

	@Override
	protected AccountBalanceResponse loadAccountBalances(List<ConsentAccountAisDto> accAisLst, String resourceId) throws AbitRuleException {
		AccountDetails accDts = accountRestSrvc.accountsAccountIdGet(resourceId);
    	AccountBalanceResponse accBalRsp = accontMapper.mapAccountBalanceResponse(accDts);
    	return accBalRsp;
	}

	@Override
	protected List<Psd2WebPregledRacunaDto> getPopisRacunaFromIb(List<Integer> listSifraParOvlastenja) {
		return null;
	}

	@Override
	protected Document getIzvodiCAMT(String sifraPartije, String patriPar, Date datumOd, Date datumDo, String sifraValute, List<String> listaValuta, Boolean isPravna) {
		return null;
	}

	@Override
	protected List<String> getSifraValuteListBySifraPar(String iban) {
		return accontMapper.listCurrencyOfAccount(accountRestSrvc.accountsGet(iban, null));
	}

	@Override
	protected List<PrometStanjePartijeDto> getPrometiKontraStrana(PrometStanjePartijeDto filterDto, String reportType, boolean isPravna) {
		return null;
	}

	@Override
	protected String getPartiParBySifraPar(String sifraPar) {
		AccountDetails accountDetails = accountRestSrvc.accountsAccountIdGet(sifraPar);
		return accountDetails.getIban().substring(accountDetails.getIban().length() - 10);
	}

	@Override
	protected List<TransactionDetails> getTransactionsByAccount(String account) {

		List<TransactionDetails> transactionList= new ArrayList<>();
		accountRestSrvc.accountsAccountIdTransactionsGet(account).getTransactions().forEach(transaction ->
			transactionList.add(transactionsRestSrvc.transactionsTransactionIdDetailsGet(transaction.getId()))
		);

		return transactionList;
	}
}
