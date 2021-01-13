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
 * Web service interaface for Payment Information Service - Single Payment.
 * <p>
 * Created by tkorpar on 19.09.2018..
 */
@RequestFilterPsd2
@ResponseFilterPsd2
@ResponseInterceptorPsd2
@Path("/v1/payments")
public interface IPaymentsServiceResource {

    /**
     * summary: Payment initiation request
     * description: This method is used to initiate a payment at the ASPSP.
     * This method to initiate a payment initiation at the ASPSP can be sent with
     * either a JSON body or an pain.001 body depending on the payment product in the path.
     **/
	@Timed(name = "initiatePaymentTime",
			description = "Metrics to monitor the times of initiatePayment method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "initiatePaymentCount",
			description = "Metrics to monitor rate of calls of method initiatePayment method.",
			absolute = true)
	@Operation(description = "This method is used to initiate a payment at the ASPSP. " +
			"" +
			"All payment initiation endpoints expect payment information in JSON format.\n" +
			"Supported payment products:\n" +
			"\n" +
			"    sepa-credit-transfers\n" +
			"    target-2-payments\n" +
			"    cross-border-credit-transfers\n" +
			"    domestic-credit-transfers-hr\n" +
			"    hr-rtgs-payments\n" +
			"    buysell-transfers\n" +
			"\n" +
			"The list of required JSON request elements depends on the payment-product.", summary = "Payment inititation request")
	@Tag(name = "DEV")
	@APIResponses(value = {
			@APIResponse(responseCode = "201", description = "Created payment"),
			@APIResponse(responseCode = "400", description = "Bad request"),
			@APIResponse(responseCode = "404", description = "Resource not found"),
			@APIResponse(responseCode = "500", description = "Internal server error") })
	@Parameters({
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "ID of the request, unique to the call, as determined by the initiating party.",name = "x-request-id",required = true),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "TPP id ",name = "tpp-id"),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "The forwarded IP Address header field consists of the corresponding http request IP Address field between PSU and TPP.",name = "PSU-IP-Address")
	})
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{payment-product}")
    public Response initiatePayment(String psd2Nalog, @PathParam("payment-product") String paymentProduct, @Context HttpHeaders headers);

    /**
     * summary: Payment Cancellation Request
     * description: This method initiates the cancellation of a payment.
     * Depending on the payment-service, the payment-product and the ASPSP's implementation,
     * this TPP call might be sufficient to cancel a payment.
     */
	@Timed(name = "cancelPaymentTime",
			description = "Metrics to monitor the times of cancelPayment method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "cancelPaymentCount",
			description = "Metrics to monitor rate of calls of method cancelPayment method.",
			absolute = true)
	@Operation(description = "This method initiates the cancellation of a payment.", summary = "Payment cancellation request.")
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
    @Path("/{payment-product}/{paymentId}")
    public Response cancelPayment(@PathParam("payment-product") String paymentProduct, @PathParam("paymentId") String paymentId, @Context HttpHeaders httpHeaders);

    /**
     * summary: Get Payment Information
     * description: Returns the content of a payment object
     */
	@Timed(name = "getPaymentInformationTime",
			description = "Metrics to monitor the times of getPaymentInformation method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "getPaymentInformationCount",
			description = "Metrics to monitor rate of calls of method getPaymentInformation method.",
			absolute = true)
    @GET
	@Operation(description = "Returns the content of a payment object", summary = "Get payment information.")
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
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{payment-product}/{paymentId}")
    public Response getPaymentInformation(@PathParam("payment-product") String paymentProduct, @PathParam("paymentId") String paymentId, @Context HttpHeaders headers);

    /**
     * summary: Payment initiation status request
     * description: Check the transaction status of a payment initiation.
     * operationId: getPaymentInitiationStatus
     */
	@Timed(name = "getPaymentInitiationStatusTime",
			description = "Metrics to monitor the times of getPaymentInitiationStatus method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "getPaymentInitiationStatusCount",
			description = "Metrics to monitor rate of calls of method getPaymentInitiationStatus method.",
			absolute = true)
	@Operation(description = "Check the transaction status of a payment initiation.", summary = "Payment initiation status request.")
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
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{payment-product}/{paymentId}/status")
    public Response getPaymentInitiationStatus(@PathParam("payment-product") String paymentProduct, @PathParam("paymentId") String paymentId, @Context HttpHeaders headers);


    /**
     * summary: Start the authorisation process for a payment initiation
     */
	@Timed(name = "startPaymentAuthorisationTime",
			description = "Metrics to monitor the times of startPaymentAuthorisation method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "startPaymentAuthorisationCount",
			description = "Metrics to monitor rate of calls of method startPaymentAuthorisation method.",
			absolute = true)
	@Operation(description = "Create an authorisation sub-resource and start the authorisation process.", summary = "Start the authorisation process for a payment initiation.")
	@Tag(name = "DEV")
	@APIResponses(value = {
			@APIResponse(responseCode = "201", description = "Created"),
			@APIResponse(responseCode = "400", description = "Bad request"),
			@APIResponse(responseCode = "404", description = "Resource not found"),
			@APIResponse(responseCode = "500", description = "Internal server error") })
	@Parameters({
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "ID of the request, unique to the call, as determined by the initiating party.",name = "x-request-id",required = true),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "TPP id ",name = "tpp-id"),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "The forwarded IP Address header field consists of the corresponding http request IP Address field between PSU and TPP.",name = "PSU-IP-Address")
	})
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{payment-product}/{paymentId}/authorisations")
    public Response startPaymentAuthorisation(@PathParam("payment-product") String paymentProduct, @PathParam("paymentId") String paymentId, @Context HttpHeaders httpHeaders);

    /**
     * summary: Get Payment Initiation Authorisation Sub-Resources Request
     * description: |
     * Read a list of all authorisation subresources IDs which have been created.
     * This function returns an array of hyperlinks to all generated authorisation sub-resources.
     **/
	@Timed(name = "getPaymentInitiationAuthorisationTime",
			description = "Metrics to monitor the times of getPaymentInitiationAuthorisation method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "getPaymentInitiationAuthorisationCount",
			description = "Metrics to monitor rate of calls of method getPaymentInitiationAuthorisation method.",
			absolute = true)
	@Operation(description = "Read a list of all authorisation subresources IDs which have been created.\n" +
			"\n" +
			"This function returns an array of hyperlinks to all generated authorisation sub-resources.", summary = "Get payment initiation authorisations sub resources request.")
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
    @Path("/{payment-product}/{paymentId}/authorisations")
    public Response getPaymentInitiationAuthorisation(@PathParam("payment-product") String paymentProduct,
                                                      @PathParam("paymentId") String paymentId, @Context HttpHeaders httpHeaders);

    /**
     * summary: Read the SCA Status of the payment authorisation
     * description: |
     * This method returns the SCA status of a payment initiation's authorisation sub-resource.
     */
	@Timed(name = "getPaymentInitiationScaStatusTime",
			description = "Metrics to monitor the times of getPaymentInitiationScaStatus method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "getPaymentInitiationScaStatusCount",
			description = "Metrics to monitor rate of calls of method getPaymentInitiationScaStatus method.",
			absolute = true)
	@Operation(description = "Read a list of all authorisation subresources IDs which have been created.\n" +
			"\n" +
			"This function returns an array of hyperlinks to all generated authorisation sub-resources.", summary = "Read the SCA status of the payment authorisation.")
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
    @Path("/{payment-product}/{paymentId}/authorisations/{authorisationId}")
    public Response getPaymentInitiationScaStatus(@PathParam("payment-product") String paymentProduct, @PathParam("paymentId") String paymentId, @PathParam("authorisationId") String authorisationId,
                                                  @Context  HttpHeaders httpHeaders);

    /**
     * summary: Start the authorisation process for the cancellation of the addressed payment
     * description: |
     * Creates an authorisation sub-resource and start the authorisation process of the cancellation of the addressed payment.
     * The message might in addition transmit authentication and authorisation related data.
     */
	@Timed(name = "startPaymentInitiationCancellationAuthorisationTime",
			description = "Metrics to monitor the times of startPaymentInitiationCancellationAuthorisation method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "startPaymentInitiationCancellationAuthorisationCount",
			description = "Metrics to monitor rate of calls of method startPaymentInitiationCancellationAuthorisation method.",
			absolute = true)
	@Operation(description = "Creates an authorisation sub-resource and start the authorisation process of the cancellation of the addressed payment.", summary = "Start the authorisation process for the cancellation of the addressed payment.")
	@Tag(name = "DEV")
	@APIResponses(value = {
			@APIResponse(responseCode = "201", description = "Created"),
			@APIResponse(responseCode = "400", description = "Bad request"),
			@APIResponse(responseCode = "404", description = "Resource not found"),
			@APIResponse(responseCode = "500", description = "Internal server error") })
	@Parameters({
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "ID of the request, unique to the call, as determined by the initiating party.",name = "x-request-id",required = true),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "TPP id ",name = "tpp-id"),
			@Parameter(in = ParameterIn.HEADER,schema = @Schema(type = SchemaType.STRING),description = "The forwarded IP Address header field consists of the corresponding http request IP Address field between PSU and TPP.",name = "PSU-IP-Address")
	})
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{payment-product}/{paymentId}/cancellation-authorisations")
    public Response startPaymentInitiationCancellationAuthorisation(@PathParam("payment-product") String paymentProduct, @PathParam("paymentId") String paymentId, @Context HttpHeaders httpHeaders);

    /**
     * summary: Will deliver an array of resource identifications to all generated cancellation authorisation sub-resources.
     * description: |
     * Retrieve a list of all created cancellation authorisation sub-resources.
     */
	@Timed(name = "getPaymentInitiationCancellationAuthorisationInformationTime",
			description = "Metrics to monitor the times of getPaymentInitiationCancellationAuthorisationInformation method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "getPaymentInitiationCancellationAuthorisationInformationCount",
			description = "Metrics to monitor rate of calls of method getPaymentInitiationCancellationAuthorisationInformation method.",
			absolute = true)
	@Operation(description = "Retrieve a list of all created cancellation authorisation sub-resources.", summary = "Will deliver an array of resource identifications to all generated cancellation authorisation sub-resources.")
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
    @Path("/{payment-product}/{paymentId}/cancellation-authorisations")
    public Response getPaymentInitiationCancellationAuthorisationInformation(@PathParam("payment-product") String paymentProduct,
                                                                             @PathParam("paymentId") String paymentId, @Context HttpHeaders httpHeaders);

    /**
     * summary: Read the SCA status of the payment cancellation's authorisation.
     * description: |
     * This method returns the SCA status of a payment initiation's authorisation sub-resource.
     */
	@Timed(name = "getPaymentCancellationScaStatusTime",
			description = "Metrics to monitor the times of getPaymentCancellationScaStatus method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "getPaymentCancellationScaStatusCount",
			description = "Metrics to monitor rate of calls of method getPaymentCancellationScaStatus method.",
			absolute = true)
	@Operation(description = "This method returns the SCA status of a payment initiation's authorisation sub-resource.", summary = "Read the SCA status of the payment cancellation's authorisation.")
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
    @Path("/{payment-product}/{paymentId}/cancellation-authorisations/{cancellationId}")
    public Response getPaymentCancellationScaStatus(@PathParam("payment-product") String paymentProduct, @PathParam("paymentId") String paymentId, @PathParam("cancellationId") String cancellationAuthorisationId,
                                                    @Context HttpHeaders httpHeaders);

/*


    /*
   *
   summary: Update PSU data for payment initiation
   description: |
       This methods updates PSU data on the authorisation resource if needed.
       It may authorise a payment within the Embedded SCA Approach where needed.

       Independently from the SCA Approach it supports e.g. the selection of
       the authentication method and a non-SCA PSU authentication.
   *
   */
    /*
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{payment-service}/{payment-product}/{paymentId}/authorisations/{authorisationId}")
    public void updatePaymentPsuData(@PathParam("payment-service") String
                                             paymentService, @PathParam("payment-product") String paymentProduct, @PathParam("paymentId") String
                                             paymentId, @PathParam("authorisationId") String authorisationId) {
//
//        } catch ( ) {
//            e.printStackTrace();
//        }
//        return null;
    }

    /*
    *
    summary: Update PSU Data for payment initiation cancellation
    description: |
        This method updates PSU data on the cancellation authorisation resource if needed.

    *
    */
      /*
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{payment-service}/{payment-product}/{paymentId}/cancellation-authorisations/{cancellationId}")
    public void updatePaymentCancellationPsuData(@PathParam("payment-service") String
                                                         paymentService, @PathParam("payment-product") String paymentProduct, @PathParam("paymentId") String
                                                         paymentId, @PathParam("cancellationId") String cancellationId) {
//
//        } catch ( ) {
//            e.printStackTrace();
//        }
//        return null;
    }
*/

}
