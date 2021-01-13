package hr.abc.psd2.resources.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import hr.abc.psd2.model.cards.CardAccountList;
import hr.abc.psd2.model.cards.CardAccountsTransactionsResponse200;
import hr.abc.psd2.model.cards.InlineResponse2007;
import hr.abc.psd2.model.cards.ReadCardAccountBalanceResponse200;
import hr.abc.psd2.model.dto.error.ErrorInformationDto;
import hr.abc.psd2.resources.ws.filter.binding.ResponseInterceptorPsd2;

@ResponseInterceptorPsd2
@Path("/v1/card-accounts")
public interface ICardResources {

	@GET
	@Produces({ "application/json", "application/problem+json" })
	@Operation(summary = "Read a list of card accounts", description = "Reads a list of card accounts with additional information, e.g. balance information.  It is assumed that a consent of the PSU to this access is already given and stored on the ASPSP system.  The addressed list of card accounts depends then on the PSU ID and the stored consent addressed by consentId,  respectively the OAuth2 access token.  ")
	@Tag(name = "TEST")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CardAccountList.class))),
			@APIResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorInformationDto.class))),
			@APIResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorInformationDto.class))),
			@APIResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ErrorInformationDto.class))),
			@APIResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorInformationDto.class))),
			@APIResponse(responseCode = "405", description = "Method Not Allowed", content = @Content(schema = @Schema(implementation = ErrorInformationDto.class))),
			@APIResponse(responseCode = "406", description = "Not Acceptable", content = @Content(schema = @Schema(implementation = ErrorInformationDto.class))),
			@APIResponse(responseCode = "408", description = "Request Timeout"),
			@APIResponse(responseCode = "409", description = "Conflict", content = @Content(schema = @Schema(implementation = ErrorInformationDto.class))),
			@APIResponse(responseCode = "415", description = "Unsupported Media Type"),
			@APIResponse(responseCode = "429", description = "Too Many Requests", content = @Content(schema = @Schema(implementation = ErrorInformationDto.class))),
			@APIResponse(responseCode = "500", description = "Internal Server Error"),
			@APIResponse(responseCode = "503", description = "Service Unavailable")
	})
	public Response getCardAccount(@Context UriInfo queryParam,
			@Context ContainerRequestContext ctx,
			@Context HttpHeaders headers);

	@GET
	@Path("/{account-id}/balances")
	@Produces({ "application/json", "application/problem+json" })
	@Operation(summary = "Read card account balances", description = "Reads balance data from a given card account addressed by  \"account-id\".   Remark: This account-id can be a tokenised identification due  to data protection reason since the path information might be  logged on intermediary servers within the ASPSP sphere.  This account-id then can be retrieved by the  \"Get card account list\" call. ")
	@Tag(name = "TEST")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ReadCardAccountBalanceResponse200.class))),
			@APIResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorInformationDto.class))),
			@APIResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorInformationDto.class))),
			@APIResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ErrorInformationDto.class))),
			@APIResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorInformationDto.class))),
			@APIResponse(responseCode = "405", description = "Method Not Allowed", content = @Content(schema = @Schema(implementation = ErrorInformationDto.class))),
			@APIResponse(responseCode = "406", description = "Not Acceptable", content = @Content(schema = @Schema(implementation = ErrorInformationDto.class))),
			@APIResponse(responseCode = "408", description = "Request Timeout"),
			@APIResponse(responseCode = "409", description = "Conflict", content = @Content(schema = @Schema(implementation = ErrorInformationDto.class))),
			@APIResponse(responseCode = "415", description = "Unsupported Media Type"),
			@APIResponse(responseCode = "429", description = "Too Many Requests", content = @Content(schema = @Schema(implementation = ErrorInformationDto.class))),
			@APIResponse(responseCode = "500", description = "Internal Server Error"),
			@APIResponse(responseCode = "503", description = "Service Unavailable")
	})
	public Response getCardAccountBalances(@PathParam("account-id") String accountId,
			@Context UriInfo queryParam,
			@Context HttpHeaders headers);

	@GET
	@Path("/{account-id}/transactions")
	@Produces({ "application/json", "application/problem+json" })
	@Operation(summary = "Read transaction list of an account", description = "Reads account data from a given card account addressed by \"account-id\". ")
	@Tag(name = "TEST")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CardAccountsTransactionsResponse200.class))),
			@APIResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorInformationDto.class))),
			@APIResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorInformationDto.class))),
			@APIResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ErrorInformationDto.class))),
			@APIResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorInformationDto.class))),
			@APIResponse(responseCode = "405", description = "Method Not Allowed", content = @Content(schema = @Schema(implementation = ErrorInformationDto.class))),
			@APIResponse(responseCode = "406", description = "Not Acceptable", content = @Content(schema = @Schema(implementation = ErrorInformationDto.class))),
			@APIResponse(responseCode = "408", description = "Request Timeout"),
			@APIResponse(responseCode = "409", description = "Conflict", content = @Content(schema = @Schema(implementation = ErrorInformationDto.class))),
			@APIResponse(responseCode = "415", description = "Unsupported Media Type"),
			@APIResponse(responseCode = "429", description = "Too Many Requests", content = @Content(schema = @Schema(implementation = ErrorInformationDto.class))),
			@APIResponse(responseCode = "500", description = "Internal Server Error"),
			@APIResponse(responseCode = "503", description = "Service Unavailable")
	})
	public Response getCardAccountTransactionList(@PathParam("account-id") String accountId,
			@Context UriInfo queryParam,
			@Context HttpHeaders headers);

	@GET
	@Path("/card-accounts/{account-id}")
	@Produces({ "application/json", "application/problem+json" })
	@Operation(summary = "Read details about a card account", description = "Reads details about a card account.  It is assumed that a consent of the PSU to this access is already given  and stored on the ASPSP system. The addressed details of this account depends  then on the stored consent addressed by consentId, respectively the OAuth2  access token. ")
	@Tag(name = "TEST")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = InlineResponse2007.class))),
			@APIResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = ErrorInformationDto.class))),
			@APIResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = ErrorInformationDto.class))),
			@APIResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = ErrorInformationDto.class))),
			@APIResponse(responseCode = "404", description = "Not found", content = @Content(schema = @Schema(implementation = ErrorInformationDto.class))),
			@APIResponse(responseCode = "405", description = "Method Not Allowed", content = @Content(schema = @Schema(implementation = ErrorInformationDto.class))),
			@APIResponse(responseCode = "406", description = "Not Acceptable", content = @Content(schema = @Schema(implementation = ErrorInformationDto.class))),
			@APIResponse(responseCode = "408", description = "Request Timeout"),
			@APIResponse(responseCode = "409", description = "Conflict", content = @Content(schema = @Schema(implementation = ErrorInformationDto.class))),
			@APIResponse(responseCode = "415", description = "Unsupported Media Type"),
			@APIResponse(responseCode = "429", description = "Too Many Requests", content = @Content(schema = @Schema(implementation = ErrorInformationDto.class))),
			@APIResponse(responseCode = "500", description = "Internal Server Error"),
			@APIResponse(responseCode = "503", description = "Service Unavailable")
	})
	public Response readCardAccount(@PathParam("account-id") String accountId,
			@Context UriInfo queryParam,
			@Context HttpHeaders headers);
}
