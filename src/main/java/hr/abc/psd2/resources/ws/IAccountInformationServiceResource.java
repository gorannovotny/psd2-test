package hr.abc.psd2.resources.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.*;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameters;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import hr.abc.psd2.resources.ws.filter.binding.RequestFilterPsd2;
import hr.abc.psd2.resources.ws.filter.binding.ResponseFilterPsd2;
import hr.abc.psd2.resources.ws.filter.binding.ResponseInterceptorPsd2;


/**
 * Web service interface resource for Account Information Service .
 * <p>
 * Created by tkorpar on 19.09.2018..
 */
@RequestFilterPsd2
@ResponseFilterPsd2
@ResponseInterceptorPsd2
@Path("/v1/accounts")
@Produces(MediaType.APPLICATION_JSON)
public interface IAccountInformationServiceResource {

    /**
     * summary: Read Account List
     * description:
     * Read the identifiers of the available payment account together with booking balance information, depending on the consent granted.
     */
	@Timed(name = "getAccountListTime",
			description = "Metrics to monitor the times of getAccountList method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "getAccountListCount",
			description = "Metrics to monitor rate of calls of method getAccountList method.",
			absolute = true)
    @Operation(description = "Read the identifiers of the available payment account together with booking balance information, depending on the consent granted.", summary = "Read Account List")
	@Tag(name = "TEST")
	@APIResponses(value = {
			@APIResponse(responseCode = "20O", description = "Ok"),
			@APIResponse(responseCode = "400", description = "Bad request"),
			@APIResponse(responseCode = "404", description = "Resource not found"),
			@APIResponse(responseCode = "500", description = "Internal server error") })
	@Parameters({
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "ID of the request, unique to the call, as determined by the initiating party.",name = "x-request-id",required = true),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "TPP id ",name = "tpp-id",required = true),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "The   forwarded   IP   Address   header   field consists of the corresponding HTTP request IP Address field between PSU and TPP.",name = "PSU-IP-Address",required = true),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "Shall be   contained since \"Establish   Consent Transaction\" was performed via this API before.",name = "Consent-ID",required = true)
	})
    @GET
    public Response getAccountList(@Context UriInfo queryParam, @Context ContainerRequestContext ctx , @Context HttpHeaders headers);

    /**
     * summary: Read Account Details
     * description:
     * Reads details about an account, with balances where required.
     * It is assumed that a consent of the PSU to this access is already given and stored on the ASPSP system.
     */
	@Timed(name = "readAccountDetailsTime",
			description = "Metrics to monitor the times of readAccountDetails method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "readAccountDetailsCount",
			description = "Metrics to monitor rate of calls of method readAccountDetails method.",
			absolute = true)
    @Operation(description = "Reads details about an account, with balances where required.\n" +
			"It is assumed that a consent of the PSU to this access is already given and stored on the ASPSP system.", summary = "Read Account Details")
	@Tag(name = "TEST")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Ok"),
			@APIResponse(responseCode = "400", description = "Bad request"),
			@APIResponse(responseCode = "404", description = "Resource not found"),
			@APIResponse(responseCode = "500", description = "Internal server error") })
	@Parameters({
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "ID of the request, unique to the call, as determined by the initiating party.",name = "x-request-id",required = true),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "TPP id ",name = "tpp-id",required = true),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "The   forwarded   IP   Address   header   field consists of the corresponding HTTP request IP Address field between PSU and TPP.",name = "PSU-IP-Address",required = true),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "Shall be   contained since \"Establish   Consent Transaction\" was performed via this API before.",name = "Consent-ID",required = true)
	})
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{accountId}")
    public Response readAccountDetails(@PathParam("accountId") String accountId, @Context UriInfo queryParam, @Context ContainerRequestContext ctx, @Context HttpHeaders headers);

    /**
     * summary: Read Balance
     * description:
     * Reads account data from a given account addressed by "account-id".
     **/
	@Timed(name = "getBalancesTime",
			description = "Metrics to monitor the times of getBalances method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "getBalancesCount",
			description = "Metrics to monitor rate of calls of method getBalances method.",
			absolute = true)
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{resourceId}/balances")
    public Response getBalances(@PathParam("resourceId") String resourceId, @Context ContainerRequestContext ctx, @Context HttpHeaders headers);

    /*
    *
    summary: Read Transaction List
    description:
        Read transaction reports or transaction lists of a given account ddressed by "account-id", depending on the steering parameter
        "bookingStatus" together with balances.
    *
    */
	@Timed(name = "getTransactionListTime",
			description = "Metrics to monitor the times of getTransactionList method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "getTransactionListCount",
			description = "Metrics to monitor rate of calls of method getTransactionList method.",
			absolute = true)
	@Operation(description = "Read transaction reports or transaction lists of a given account addressed by \"account-id\", depending on the steering parameter \"bookingStatus\" together with balances.", summary = "Read transaction list of account. ")
	@Tag(name = "DEV")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Ok"),
			@APIResponse(responseCode = "400", description = "Bad request"),
			@APIResponse(responseCode = "404", description = "Resource not found"),
			@APIResponse(responseCode = "500", description = "Internal server error") })
	@Parameters({
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "ID of the request, unique to the call, as determined by the initiating party.",name = "x-request-id",required = true),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "TPP id ",name = "tpp-id",required = true),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "The   forwarded   IP   Address   header   field consists of the corresponding HTTP request IP Address field between PSU and TPP.",name = "PSU-IP-Address",required = true),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "Shall be   contained since \"Establish   Consent Transaction\" was performed via this API before.",name = "Consent-ID",required = true)
	})
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{resourceId}/transactions")
    public Response getTransactionList(@PathParam("resourceId") String resourceId, @Context UriInfo queryParam, @Context HttpHeaders headers);

    /*
    *
    summary: Read Transaction Details
    description:
        Reads transaction details from a given transaction addressed by "resourceId" on a given account addressed by "account-id".
        This call is only available on transactions as reported in a JSON format.
    *
    */
	@Timed(name = "getTransactionDetailsTime",
			description = "Metrics to monitor the times of getTransactionDetails method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "getTransactionDetailsCount",
			description = "Metrics to monitor rate of calls of method getTransactionDetails method.",
			absolute = true)

	@Operation(description = "Reads transaction details from a given transaction addressed by \"resourceId\" on a given account addressed by \"account-id\"..", summary = "Read Transaction Details . ")
	@Tag(name = "DEV")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Ok"),
			@APIResponse(responseCode = "400", description = "Bad request"),
			@APIResponse(responseCode = "404", description = "Resource not found"),
			@APIResponse(responseCode = "500", description = "Internal server error") })
	@Parameters({
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "ID of the request, unique to the call, as determined by the initiating party.",name = "x-request-id",required = true),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "TPP id ",name = "tpp-id",required = true),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "The   forwarded   IP   Address   header   field consists of the corresponding HTTP request IP Address field between PSU and TPP.",name = "PSU-IP-Address",required = true),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "Shall be   contained since \"Establish   Consent Transaction\" was performed via this API before.",name = "Consent-ID",required = true)
	})
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{accountId}/transactions/{resourceId}")
    public Response getTransactionDetails(@PathParam("accountId") String accountId, @PathParam("resourceId") String resourceId,  @Context ContainerRequestContext ctx);

}
