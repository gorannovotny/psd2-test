package hr.sberbank.resources;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.ws.rs.*;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import hr.sberbank.model.account.AccountDetails;
import hr.sberbank.model.account.AccountList;
import hr.sberbank.model.account.CardTransactionList;
import hr.sberbank.model.account.CreditCardAccount;
import hr.sberbank.model.account.CreditCardAccounts;
import hr.sberbank.model.account.CreditCardDetails;
import hr.sberbank.model.account.TransactionList;

@RegisterRestClient(configKey = "account-api")
public interface AccountRestSrvc {

	@GET
	@Path("/customers/{customerId}/accounts")
	@Produces({ "application/vnd.sberbank.v1+json" })
	AccountList customersCustomerIdAccountsGet(@NotNull @PathParam("customerId") String customerId) throws WebApplicationException;

	@GET
	@Path("/accounts/{accountId}")
	@Produces({ "application/vnd.sberbank.v1+json" })
	AccountDetails accountsAccountIdGet(@NotNull @PathParam("accountId") String accountId) throws WebApplicationException;

	@GET
	@Path("/accounts")
	@Produces({ "application/vnd.sberbank.v1+json" })
	AccountList accountsGet(@NotNull @QueryParam("iban") String iban, @Null @QueryParam("currency") String currency) throws WebApplicationException;

	@GET
	@Path("/accounts/{accountId}/transactions")
	@Produces({ "application/vnd.sberbank.v1+json" })
	TransactionList accountsAccountIdTransactionsGet(@NotNull @PathParam("accountId") String accountId) throws WebApplicationException;

	@GET
	@Path("/credit-card-accounts/{id}")
	@Produces({ "application/vnd.sberbank.v1+json" })
	CreditCardAccount creditCardAccountsIdGet(@NotNull @PathParam("id") String id) throws WebApplicationException;

	@GET
	@Path("/credit-card-accounts/{id}/transactions")
	@Produces({ "application/vnd.sberbank.v1+json" })
	CardTransactionList creditCardAccountsIdTransactionsGet(@NotNull @PathParam("id") String id) throws WebApplicationException;

	@GET
	@Path("/customers/{customerId}/credit-card-accounts")
	@Produces({ "application/vnd.sberbank.v1+json" })
	CreditCardAccounts customersCustomerIdCreditCardAccountsGet(@NotNull @PathParam("customerId") String customerId) throws WebApplicationException;

}
