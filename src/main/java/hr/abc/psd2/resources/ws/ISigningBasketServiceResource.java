package hr.abc.psd2.resources.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;

import hr.abc.psd2.model.dto.basket.BasketRequestDto;
import hr.abc.psd2.resources.ws.filter.binding.RequestFilterPsd2;
import hr.abc.psd2.resources.ws.filter.binding.ResponseFilterPsd2;
import hr.abc.psd2.resources.ws.filter.binding.ResponseInterceptorPsd2;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameters;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;


/**
 * Web service interface resource for Signing Basket .
 * <p>
 * Created by tkorpar on 07.05.2019.
 */
@RequestFilterPsd2
@ResponseFilterPsd2
@ResponseInterceptorPsd2
@Path("/v1/signing-baskets")
@Produces(MediaType.APPLICATION_JSON)
public interface ISigningBasketServiceResource {

    /**
     * summary: Create a signing basket resource
     * description:
     * Create a signing basket resource for authorising several transactions with one SCA method.
     * The resource identifications of these transactions are contained in the  payload of this access method
     */
	@Timed(name = "createSigningBasketTime",
			description = "Metrics to monitor the times of createSigningBasket method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "createSigningBasketCount",
			description = "Metrics to monitor rate of calls of method createSigningBasket method.",
			absolute = true)
	@Operation(description = "Create a signing basket resource for authorising several transactions with one SCA method. The resource identifications of these transactions are contained in the payload of this access method.\n" +
			"\n" +
			"Signing baskets for the same Payment product are allowed (only individual payments).", summary = "Create a signing basket resource")
	@Tag(name = "DEV")
	@APIResponses(value = {
			@APIResponse(responseCode = "201", description = "Created payment"),
			@APIResponse(responseCode = "400", description = "Bad request"),
			@APIResponse(responseCode = "404", description = "Resource not found"),
			@APIResponse(responseCode = "500", description = "Internal server error") })
	@Parameters({
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "ID of the request, unique to the call, as determined by the initiating party.",name = "x-request-id",required = true),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "TPP id ",name = "tpp-id"),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "The forwarded IP Address header field consists of the corresponding http request IP Address field between PSU and TPP.",name = "PSU-IP-Address"),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "URI  of  the  TPP,  where  the  transaction flow   shall   be   redirected   to   after   a Redirect. Mandated  for  the  Redirect  SCA Approach (including OAuth2 SCA approach),  specifically  when  TPP-Redirect-Preferred equals \"true\" ",name = "TPP-Redirect-URI",required = true)
	})
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createSigningBasket(BasketRequestDto basketRequestDto, @Context HttpHeaders httpHeaders);

    /**
     * summary: Returns the content of an signing basket object
     * description:
     * Returns the content of a signing basket object.
     */
	@Timed(name = "getSigningBasketTime",
			description = "Metrics to monitor the times of getSigningBasket method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "getSigningBasketCount",
			description = "Metrics to monitor rate of calls of method getSigningBasket method.",
			absolute = true)
	@Operation(description = "Returns the content of an signing basket object.", summary = "Returns the content of an signing basket object")
	@Tag(name = "DEV")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Ok"),
			@APIResponse(responseCode = "400", description = "Bad request"),
			@APIResponse(responseCode = "404", description = "Resource not found"),
			@APIResponse(responseCode = "500", description = "Internal server error") })
	@Parameters({
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "ID of the request, unique to the call, as determined by the initiating party.",name = "x-request-id",required = true),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "TPP id ",name = "tpp-id"),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "The forwarded IP Address header field consists of the corresponding http request IP Address field between PSU and TPP.",name = "PSU-IP-Address")
	})
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{basketId}")
    public Response getSigningBasket(@PathParam("basketId") String basketId, @Context HttpHeaders httpHeaders);

    /**
     *
     * Returns the status of a signing basketobject.
     */
	@Timed(name = "getSigningBasketStatusRequestTime",
			description = "Metrics to monitor the times of getSigningBasketStatusRequest method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "getSigningBasketStatusRequestCount",
			description = "Metrics to monitor rate of calls of method getSigningBasketStatusRequest method.",
			absolute = true)
	@Operation(description = "Returns the status of a signing basket object.", summary = "Read the status of the signing basket.")
	@Tag(name = "DEV")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Ok"),
			@APIResponse(responseCode = "400", description = "Bad request"),
			@APIResponse(responseCode = "404", description = "Resource not found"),
			@APIResponse(responseCode = "500", description = "Internal server error") })
	@Parameters({
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "ID of the request, unique to the call, as determined by the initiating party.",name = "x-request-id",required = true),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "TPP id ",name = "tpp-id"),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "The forwarded IP Address header field consists of the corresponding http request IP Address field between PSU and TPP.",name = "PSU-IP-Address")
	})
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{basketId}/status")
    public Response getSigningBasketStatusRequest(@PathParam("basketId") String basketId, @Context HttpHeaders httpHeaders);



    /**
     * summary: Delete the signing basket
     * description:
     * Delete the signing basket structure as long as no (partial) authorisation has yet been applied.
     * The undlerying transactions are not affected by this deletion.
     */
	@Timed(name = "deleteSigningBasketTime",
			description = "Metrics to monitor the times of deleteSigningBasket method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "deleteSigningBasketCount",
			description = "Metrics to monitor rate of calls of method deleteSigningBasket method.",
			absolute = true)
	@Operation(description = "Delete the signing basket structure as long as no (partial) authorisation has yet been applied. The undlerying transactions are not affected by this deletion.", summary = "Delete the signing basket.")
	@Tag(name = "DEV")
	@APIResponses(value = {
			@APIResponse(responseCode = "204", description = "No content"),
			@APIResponse(responseCode = "400", description = "Bad request"),
			@APIResponse(responseCode = "404", description = "Resource not found"),
			@APIResponse(responseCode = "500", description = "Internal server error") })
	@Parameters({
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "ID of the request, unique to the call, as determined by the initiating party.",name = "x-request-id",required = true),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "TPP id ",name = "tpp-id"),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "The forwarded IP Address header field consists of the corresponding http request IP Address field between PSU and TPP.",name = "PSU-IP-Address")
	})
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{basketId}")
    public Response deleteSigningBasket(@PathParam("basketId") String basketId, @Context HttpHeaders httpHeaders);

    /**
     * summary: Start the authorisation process for a signing basket
     * description:
     * Create an authorisation sub-resource and start the authorisation process of a signing basket.
     */
	@Timed(name = "startSigningBasketAuthorisationTime",
			description = "Metrics to monitor the times of startSigningBasketAuthorisation method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "startSigningBasketAuthorisationCount",
			description = "Metrics to monitor rate of calls of method startSigningBasketAuthorisation method.",
			absolute = true)
	@Operation(description = "Create an authorisation sub-resource and start the authorisation process of a signing basket. The message might in addition transmit authentication and authorisation related data.", summary = "Start the authorisation process for a signing basket.")
	@Tag(name = "DEV")
	@APIResponses(value = {
			@APIResponse(responseCode = "201", description = "Created payment"),
			@APIResponse(responseCode = "400", description = "Bad request"),
			@APIResponse(responseCode = "404", description = "Resource not found"),
			@APIResponse(responseCode = "500", description = "Internal server error") })
	@Parameters({
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "ID of the request, unique to the call, as determined by the initiating party.",name = "x-request-id",required = true),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "TPP id ",name = "tpp-id"),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "The forwarded IP Address header field consists of the corresponding http request IP Address field between PSU and TPP.",name = "PSU-IP-Address"),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "URI  of  the  TPP,  where  the  transaction flow   shall   be   redirected   to   after   a Redirect. Mandated  for  the  Redirect  SCA Approach (including OAuth2 SCA approach),  specifically  when  TPP-Redirect-Preferred equals \"true\" ",name = "TPP-Redirect-URI",required = true)
	})
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{basketId}/authorisations")
    public Response startSigningBasketAuthorisation(@PathParam("basketId") String basketId, @Context HttpHeaders httpHeaders);

    /**
     * summary: Read the SCA status of the signing basket authorisation
     * description:
     * This method returns the SCA status of a signing basket's authorisation sub-resource.
     */
	@Timed(name = "getSigningBasketScaStatusTime",
			description = "Metrics to monitor the times of getSigningBasketScaStatus method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "getSigningBasketScaStatusCount",
			description = "Metrics to monitor rate of calls of method getSigningBasketScaStatus method.",
			absolute = true)
	@Operation(description = "This method returns the SCA status of a signing basket's authorisation sub-resource.", summary = "Read the SCA status of the signing basket authorisation.")
	@Tag(name = "DEV")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Ok"),
			@APIResponse(responseCode = "400", description = "Bad request"),
			@APIResponse(responseCode = "404", description = "Resource not found"),
			@APIResponse(responseCode = "500", description = "Internal server error") })
	@Parameters({
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "ID of the request, unique to the call, as determined by the initiating party.",name = "x-request-id",required = true),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "TPP id ",name = "tpp-id"),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "The forwarded IP Address header field consists of the corresponding http request IP Address field between PSU and TPP.",name = "PSU-IP-Address")
	})
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{basketId}/authorisations/{authorisationId}")
    public Response getSigningBasketScaStatus(@PathParam("basketId") String basketId, @PathParam("authorisationId") String authorizationId, @Context HttpHeaders httpHeaders);


}
