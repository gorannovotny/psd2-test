package hr.abc.psd2.bean;

import java.util.List;

import javax.ws.rs.core.UriInfo;

import hr.abc.psd2.model.dto.ais.AccountBalanceResponse;
import hr.abc.psd2.model.dto.ais.AccountResponseDto;
import hr.abc.psd2.model.dto.ais.AccountTransactionReq;
import hr.abc.psd2.model.dto.ais.AccountTransactionsResponse;
import hr.abc.psd2.model.dto.ais.ConsentAccountAisDto;
import hr.abc.psd2.model.dto.ais.ConsentAisDto;
import hr.abc.psd2.model.dto.ais.PrometStanjePartijeDto;

public interface IAccountInformationBean {

    AccountResponseDto formAccountListResponse(ConsentAisDto consent, Boolean withBalance);

    AccountResponseDto formAccountDetailsResponse(String accountResourceId, ConsentAisDto consent, Boolean withBalance);

    AccountBalanceResponse getAccountBalances(List<ConsentAccountAisDto> accountAisDtoList, String resourceId);

    AccountTransactionReq validateAisTransactionRequest(UriInfo queryParam, String resourceId);

    PrometStanjePartijeDto formFilterPartije(AccountTransactionReq accountTransactionReq);

    AccountTransactionsResponse getTransactionList(String acceptedFormat, ConsentAisDto consentAisDtoWs, PrometStanjePartijeDto filterPartijeDto, String resourceId);

}
