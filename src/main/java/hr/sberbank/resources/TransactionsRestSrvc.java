package hr.sberbank.resources;

import hr.sberbank.model.account.TransactionDetails;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;

@RegisterRestClient(configKey = "transactions-api")
public interface TransactionsRestSrvc {

    @GET
    @Path("/transactions/{transactionId}/details")
    @Produces({"application/vnd.sberbank.v1+json"})
    TransactionDetails transactionsTransactionIdDetailsGet(@NotNull @PathParam("transactionId") String transactionId) throws WebApplicationException;

}
