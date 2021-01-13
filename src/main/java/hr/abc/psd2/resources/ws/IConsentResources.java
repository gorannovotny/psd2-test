package hr.abc.psd2.resources.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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

import hr.abc.psd2.model.dto.ConsentRequestResponseDto;
import hr.abc.psd2.resources.ws.filter.binding.RequestFilterPsd2;
import hr.abc.psd2.resources.ws.filter.binding.ResponseFilterPsd2;
import hr.abc.psd2.resources.ws.filter.binding.ResponseInterceptorPsd2;
import io.vertx.axle.core.http.HttpServerRequest;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameters;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

/**
 * Web service resource for Account Information Consent .
 * <p>
 * Created by tkorpar on 19.09.2018..
 */
@RequestFilterPsd2
@ResponseFilterPsd2
@ResponseInterceptorPsd2
@Path("/v1/consents")
public interface IConsentResources {

    /**
     * summary: Create consent
     * description:
     * This method create a consent resource, defining access rights to dedicated accounts of
     * a given PSU-ID. These accounts are addressed explicitly in the method as
     * parameters as a core function.
     */
	@Timed(name = "createConsentTime",
			description = "Metrics to monitor the times of createConsent method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "createConsentCount",
			description = "Metrics to monitor rate of calls of method createConsent method.",
			absolute = true)
	@Operation(description = "This method create a consent resource, defining access rights to dedicated accounts of a given PSU-ID. These accounts are addressed explicitly in the method as " +
			"parameters as a core function.", summary = "Create consent")
	@Tag(name = "TEST")
	@APIResponses(value = {
			@APIResponse(responseCode = "201", description = "Created consent"),
			@APIResponse(responseCode = "400", description = "Bad request"),
			@APIResponse(responseCode = "404", description = "Resource not found"),
			@APIResponse(responseCode = "500", description = "Internal server error") })
	@Parameters({
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "ID of the request, unique to the call, as determined by the initiating party.",name = "x-request-id",required = true),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "TPP id ",name = "tpp-id",required = true),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "TPP name ",name = "tpp-name",required = true),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "URI  of  the  TPP,  where  the  transaction flow   shall   be   redirected   to   after   a Redirect. Mandated  for  the  Redirect  SCA Approach (including OAuth2 SCA approach),  specifically  when  TPP-Redirect-Preferred equals \"true\" ",name = "tpp-redirect-uri",required = true),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "Client ID of the PSU in the ASPSP client interface.   Might   be  mandated  in   the ASPSP’s documentation. ",name = "psu-id")
	})
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createConsent(ConsentRequestResponseDto consentRequestResponseDto, @Context ContainerRequestContext ctx, @Context HttpServerRequest request, @Context HttpHeaders headers);

    /**
     * summary: Consent status request
     * description:
     * Read the status of an account information consent resource.
     */
	@Timed(name = "getConsentStatusTime",
			description = "Metrics to monitor the times of getConsentStatus method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "getConsentStatusCount",
			description = "Metrics to monitor rate of calls of method getConsentStatus method.",
			absolute = true)
	@Operation(description = "Read the status of an account information consent resource.", summary = "Consent Status Request")
	@Tag(name = "TEST")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Ok"),
			@APIResponse(responseCode = "400", description = "Bad request"),
			@APIResponse(responseCode = "404", description = "Resource not found"),
			@APIResponse(responseCode = "500", description = "Internal server error") })
	@Parameters({
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "ID of the request, unique to the call, as determined by the initiating party.",name = "x-request-id",required = true),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "TPP id ",name = "tpp-id",required = true),
	})
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{consentId}/status")
    public Response getConsentStatus(@PathParam("consentId") String consentId, @Context ContainerRequestContext ctx, @Context HttpHeaders headers);

    /**
     * summary: Get Consent Request
     * description:
     * Returns the content of an account information consent object.
     * This is returning the data for the TPP especially in cases,
     * where the consent was directly managed between ASPSP and PSU e.g. in a re-direct SCA Approach.
     */
	@Timed(name = "getConsentInformationTime",
			description = "Metrics to monitor the times of getConsentInformation method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "getConsentInformationCount",
			description = "Metrics to monitor rate of calls of method getConsentInformation method.",
			absolute = true)
	@Operation(description = "Returns the content of an account information consent object. This is returning the data for the TPP especially in cases, where the consent was directly managed between ASPSP and PSU e.g. in a re-direct SCA Approach.", summary = "Get Consent Request")
	@Tag(name = "TEST")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Ok"),
			@APIResponse(responseCode = "400", description = "Bad request"),
			@APIResponse(responseCode = "404", description = "Resource not found"),
			@APIResponse(responseCode = "500", description = "Internal server error") })
	@Parameters({
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "ID of the request, unique to the call, as determined by the initiating party.",name = "x-request-id",required = true),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "TPP id ",name = "tpp-id",required = true),
	})
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{consentId}")
    public Response getConsentInformation(@PathParam("consentId") String consentId, @Context ContainerRequestContext ctx , @Context HttpHeaders headers);

    /**
     * summary: Delete Consent
     * description:
     * The TPP can delete an account information consent object if needed.
     */
	@Timed(name = "deleteConsentTime",
			description = "Metrics to monitor the times of deleteConsent method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "deleteConsentCount",
			description = "Metrics to monitor rate of calls of method deleteConsent method.",
			absolute = true)
	@Operation(description = "The TPP can delete an account information consent object if needed.", summary = "Delete Consent")
	@Tag(name = "TEST")
	@APIResponses(value = {
			@APIResponse(responseCode = "204", description = "No content"),
			@APIResponse(responseCode = "400", description = "Bad request"),
			@APIResponse(responseCode = "404", description = "Resource not found"),
			@APIResponse(responseCode = "500", description = "Internal server error") })
	@Parameters({
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "ID of the request, unique to the call, as determined by the initiating party.",name = "x-request-id",required = true),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "TPP id ",name = "tpp-id",required = true),
	})
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{consentId}")
    public Response deleteConsent(@PathParam("consentId") String consentId, @Context ContainerRequestContext ctx, @Context HttpHeaders headers);

    /**
     * summary: Start the authorisation process for a consent
     * description:
     * Create an authorisation sub-resource and start the authorisation process of a consent.
     * The message might in addition transmit authentication and authorisation related data.
     */
	@Timed(name = "startConsentAuthorisationTime",
			description = "Metrics to monitor the times of startConsentAuthorisation method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "startConsentAuthorisationCount",
			description = "Metrics to monitor rate of calls of method startConsentAuthorisation method.",
			absolute = true)
	@Operation(description = "Start the authorisation process for a consent", summary = "Start Consent Authorization")
	@Tag(name = "TEST")
	@APIResponses(value = {
			@APIResponse(responseCode = "201", description = "Authorization created"),
			@APIResponse(responseCode = "400", description = "Bad request"),
			@APIResponse(responseCode = "404", description = "Resource not found"),
			@APIResponse(responseCode = "500", description = "Internal server error") })
	@Parameters({
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "ID of the request, unique to the call, as determined by the initiating party.",name = "x-request-id",required = true),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "TPP id ",name = "tpp-id",required = true),
	})
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{consentId}/authorisations")
    public Response startConsentAuthorisation(@PathParam("consentId") String consentId, @Context ContainerRequestContext ctx);

    /**
     * summary: Get Consent Authorisation Sub-Resources Request
     * description:
     * Return a list of all authorisation subresources IDs which have been created.
     **/
	@Timed(name = "getConsentAuthorisationTime",
			description = "Metrics to monitor the times of getConsentAuthorisation method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "getConsentAuthorisationCount",
			description = "Metrics to monitor rate of calls of method getConsentAuthorisation method.",
			absolute = true)
	@Operation(description = "Return a list of all authorisation subresources IDs which have been created. " +
			"This function returns an array of hyperlinks to all generated authorisation sub-resources.", summary = "Get Consent Authorisation Sub-Resources Request")
	@Tag(name = "TEST")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Ok"),
			@APIResponse(responseCode = "400", description = "Bad request"),
			@APIResponse(responseCode = "404", description = "Resource not found"),
			@APIResponse(responseCode = "500", description = "Internal server error") })
	@Parameters({
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "ID of the request, unique to the call, as determined by the initiating party.",name = "x-request-id",required = true),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "TPP id ",name = "tpp-id",required = true),
	})
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{consentId}/authorisations")
    public Response getConsentAuthorisation(@PathParam("consentId") String consentId, @Context ContainerRequestContext ctx,@Context HttpHeaders headers);

    /**
     * summary: Read the SCA status of the consent authorisation.
     * description:
     * This method returns the SCA status of a consent initiation's authorisation sub-resource.
     */
	@Timed(name = "getConsentScaStatusTime",
			description = "Metrics to monitor the times of getConsentScaStatus method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "getConsentScaStatusCount",
			description = "Metrics to monitor rate of calls of method getConsentScaStatus method.",
			absolute = true)
	@Operation(description = "This method returns the SCA status of a consent initiation's authorisation sub-resource.", summary = "Read the SCA status of the consent authorisation.")
	@Tag(name = "TEST")
	@APIResponses(value = {
			@APIResponse(responseCode = "200", description = "Ok"),
			@APIResponse(responseCode = "400", description = "Bad request"),
			@APIResponse(responseCode = "404", description = "Resource not found"),
			@APIResponse(responseCode = "500", description = "Internal server error") })
	@Parameters({
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "ID of the request, unique to the call, as determined by the initiating party.",name = "x-request-id",required = true),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "TPP id ",name = "tpp-id",required = true),
	})
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{consentId}/authorisations/{authorisationId}")
    public Response getConsentScaStatus(@PathParam("consentId") String consentId, @PathParam("authorisationId") String authorisationId, @Context ContainerRequestContext ctx, @Context HttpHeaders headers);

    /*
    *
    summary: Update PSU Data for consents
    description:
         This method update PSU data on the consents  resource if needed.
         It may authorise a consent within the Embedded SCA Approach where needed.
    *
    */
	@Timed(name = "updateConsentsPsuDataTime",
			description = "Metrics to monitor the times of updateConsentsPsuData method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "updateConsentsPsuDataCount",
			description = "Metrics to monitor rate of calls of method updateConsentsPsuData method.",
			absolute = true)
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{consentId}/authorisations/{authorisationId}")
    public void updateConsentsPsuData(@PathParam("consentId") String consentId, @PathParam("authorisationId") String authorisationId);

}
