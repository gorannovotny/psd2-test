package hr.abc.psd2.mapper;

import hr.abc.psd2.model.dto.ais.AccountBalanceDto;
import hr.abc.psd2.model.dto.ais.AccountBalanceResponse;
import hr.abc.psd2.model.dto.ais.AccountReferenceDto;
import hr.abc.psd2.model.dto.ais.Amount;
import hr.abc.psd2.model.dto.ais.PrometStanjePartijeDto;
import hr.abc.psd2.model.dto.core.PartijaDto;
import hr.abc.psd2.model.dto.core.Psd2WebPregledRacunaDto;
import hr.abc.psd2.model.dto.core.WebPregledRacunaDto;
import hr.sberbank.model.account.AccountDetails;
import hr.sberbank.model.account.AccountList;
import hr.sberbank.model.account.BalanceList;
import hr.sberbank.model.account.TransactionList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.Dependent;

@Dependent
public class AccountMapper implements Serializable {

	private static final long serialVersionUID = 1L;

	public PartijaDto mapAccountDetailsToPartijaDto(AccountDetails accountDetails) {
		PartijaDto partijaDto = new PartijaDto();
		partijaDto.setSifra(Integer.parseInt(accountDetails.getAccountId()));
		partijaDto.setSifraVlasnika(Integer.parseInt(accountDetails.getCustomerId()));
		return partijaDto;
	}

	public List<String> listCurrencyOfAccount(AccountList accountLists) {
		return accountLists.getAccounts()
				.stream()
				.map(AccountDetails::getCurrency)
				.collect(Collectors.toList());
	}

	public List<Psd2WebPregledRacunaDto> listOfAccounts(List<AccountDetails> accountDetailsList) {
		List<Psd2WebPregledRacunaDto> listOfAccounts = new ArrayList<>();
		accountDetailsList.forEach(accountDetails -> {
			listOfAccounts.add(new Psd2WebPregledRacunaDto(accountDetails.getAccountId(), accountDetails.getCurrency(), accountDetails.getType(), accountDetails.getIban()));
		});
		return listOfAccounts;
	}

	public List<PrometStanjePartijeDto> listTransactions(TransactionList list) {
		List<PrometStanjePartijeDto> transactionList = new ArrayList<>();
		list.getTransactions().forEach(transaction -> {
			transactionList.add(new PrometStanjePartijeDto());
		});
		return transactionList;
	}

	public AccountBalanceResponse mapAccountBalanceResponse(AccountDetails accDts) {
		AccountBalanceResponse accBlcRsp = new AccountBalanceResponse();
		accBlcRsp.setAccount(new AccountReferenceDto(accDts.getIban(), accDts.getCurrency()));
		accBlcRsp.setBalances(mapAccountBalances(accDts.getBalances(), accDts.getCurrency()));
		return accBlcRsp;
	}

	public List<AccountBalanceDto> mapAccountBalances(BalanceList blcList, String currency) {
		List<AccountBalanceDto> accBlcLs = new ArrayList<>();
		blcList.forEach(balance -> {
			AccountBalanceDto accBlcDto = new AccountBalanceDto();
			Amount amount = new Amount(currency, balance.getAmount().toString());
			accBlcDto.setBalanceAmount(amount);
			accBlcDto.setBalanceType(balance.getType().toString());
			accBlcDto.setReferenceDate(new java.sql.Timestamp(balance.getTimestamp().getTime()).toLocalDateTime());
			accBlcLs.add(accBlcDto);
		});
		return accBlcLs;
	}

}
