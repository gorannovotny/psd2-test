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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;

import hr.abc.psd2.resources.ws.filter.binding.RequestFilterPsd2;
import hr.abc.psd2.resources.ws.filter.binding.ResponseFilterPsd2;
import hr.abc.psd2.resources.ws.filter.binding.ResponseInterceptorPsd2;

/**
 * Web service interaface for Payment Information Service - Bulk Payment.
 * <p>
 * Created by tkorpar on 12.02.2019.
 */
@RequestFilterPsd2
@ResponseFilterPsd2
@ResponseInterceptorPsd2
@Path("/v1/bulk-payments")
public interface IBulkPaymentsServiceResource {

    /**
     * summary: Payment initiation request
     * description: This method is used to initiate a payment at the ASPSP.
     * This method to initiate a payment initiation at the ASPSP can be sent with
     * either a JSON body or an pain.001 body depending on the payment product in the path.
     **/
	@Timed(name = "initiateBulkPaymentTime",
			description = "Metrics to monitor the times of initiateBulkPayment method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "initiateBulkPaymentCount",
			description = "Metrics to monitor rate of calls of method initiateBulkPayment method.",
			absolute = true)
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{payment-product}")
    public Response initiateBulkPayment(String psd2Nalog, @PathParam("payment-product") String paymentProduct, @Context ContainerRequestContext ctx);

	@Timed(name = "cancelBulkPaymentTime",
			description = "Metrics to monitor the times of cancelBulkPayment method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "cancelBulkPaymentCount",
			description = "Metrics to monitor rate of calls of method cancelBulkPayment method.",
			absolute = true)
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{payment-product}/{paymentId}")
    public Response cancelBulkPayment(@PathParam("payment-product") String paymentProduct, @PathParam("paymentId") String paymentId, @Context ContainerRequestContext ctx);

    /**
     * summary: Get Payment Information
     * description: Returns the content of a payment object
     */
	@Timed(name = "getBulkPaymentInformationTime",
			description = "Metrics to monitor the times of getBulkPaymentInformation method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "getBulkPaymentInformationCount",
			description = "Metrics to monitor rate of calls of method getBulkPaymentInformation method.",
			absolute = true)
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{payment-product}/{paymentId}")
    public Response getBulkPaymentInformation(@PathParam("payment-product") String paymentProduct, @PathParam("paymentId") String paymentId, @Context ContainerRequestContext ctx);

    /**
     * summary: Payment initiation status request
     * description: Check the transaction status of a payment initiation.
     * operationId: getPaymentInitiationStatus
     */
	@Timed(name = "getBulkPaymentInitiationStatusTime",
			description = "Metrics to monitor the times of getBulkPaymentInitiationStatus method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "getBulkPaymentInitiationStatusCount",
			description = "Metrics to monitor rate of calls of method getBulkPaymentInitiationStatus method.",
			absolute = true)
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path("/{payment-product}/{paymentId}/status")
    public Response getBulkPaymentInitiationStatus(@PathParam("payment-product") String paymentProduct, @PathParam("paymentId") String paymentId, @Context ContainerRequestContext ctx);

    /**
     * summary: Start the authorisation process for a payment initiation
     */
	@Timed(name = "startBulkPaymentAuthorisationTime",
			description = "Metrics to monitor the times of startBulkPaymentAuthorisation method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "startBulkPaymentAuthorisationCount",
			description = "Metrics to monitor rate of calls of method startBulkPaymentAuthorisation method.",
			absolute = true)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{payment-product}/{paymentId}/authorisations")
    public Response startBulkPaymentAuthorisation(@PathParam("payment-product") String paymentProduct, @PathParam("paymentId") String paymentId, @Context ContainerRequestContext ctx);


    /**
     * summary: Get Payment Initiation Authorisation Sub-Resources Request
     * description: |
     * Read a list of all authorisation subresources IDs which have been created.
     * This function returns an array of hyperlinks to all generated authorisation sub-resources.
     **/
	@Timed(name = "getBulkPaymentInitiationAuthorisationTime",
			description = "Metrics to monitor the times of getBulkPaymentInitiationAuthorisation method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "getBulkPaymentInitiationAuthorisationCount",
			description = "Metrics to monitor rate of calls of method getBulkPaymentInitiationAuthorisation method.",
			absolute = true)
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{payment-product}/{paymentId}/authorisations")
    public Response getBulkPaymentInitiationAuthorisation(@PathParam("payment-product") String paymentProduct,
                                                      @PathParam("paymentId") String paymentId, @Context ContainerRequestContext ctx);

    /**
     * summary: Read the SCA Status of the payment authorisation
     * description: |
     * This method returns the SCA status of a payment initiation's authorisation sub-resource.
     */
	@Timed(name = "getBulkPaymentInitiationScaStatusTime",
			description = "Metrics to monitor the times of getBulkPaymentInitiationScaStatus method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "getBulkPaymentInitiationScaStatusCount",
			description = "Metrics to monitor rate of calls of method getBulkPaymentInitiationScaStatus method.",
			absolute = true)
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{payment-product}/{paymentId}/authorisations/{authorisationId}")
    public Response getBulkPaymentInitiationScaStatus(@PathParam("payment-product") String paymentProduct, @PathParam("paymentId") String paymentId, @PathParam("authorisationId") String authorisationId,
                                                  @Context ContainerRequestContext ctx);

    /**
     * summary: Start the authorisation cancellation process for a payment initiation
     */
	@Timed(name = "startBulkPaymentInitiationCancellationAuthorisationTime",
			description = "Metrics to monitor the times of startBulkPaymentInitiationCancellationAuthorisation method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "startBulkPaymentInitiationCancellationAuthorisationCount",
			description = "Metrics to monitor rate of calls of method startBulkPaymentInitiationCancellationAuthorisation method.",
			absolute = true)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{payment-product}/{paymentId}/cancellation-authorisations")
    public Response startBulkPaymentInitiationCancellationAuthorisation(@PathParam("payment-product") String paymentProduct, @PathParam("paymentId") String paymentId, @Context ContainerRequestContext ctx);

    /**
     * summary: Will deliver an array of resource identifications to all generated cancellation authorisation sub-resources.
     * description: |
     * Retrieve a list of all created cancellation authorisation sub-resources.
     */
	@Timed(name = "getBulkPaymentInitiationCancellationAuthorisationInformationTime",
			description = "Metrics to monitor the times of getBulkPaymentInitiationCancellationAuthorisationInformation method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "getBulkPaymentInitiationCancellationAuthorisationInformationCount",
			description = "Metrics to monitor rate of calls of method getBulkPaymentInitiationCancellationAuthorisationInformation method.",
			absolute = true)
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{payment-product}/{paymentId}/cancellation-authorisations")
    public Response getBulkPaymentInitiationCancellationAuthorisationInformation(@PathParam("payment-product") String paymentProduct,
                                                                             @PathParam("paymentId") String paymentId, @Context ContainerRequestContext ctx);

    /**
     * summary: Read the SCA status of the payment cancellation's authorisation.
     * description: |
     * This method returns the SCA status of a payment initiation's authorisation sub-resource.
     */
	@Timed(name = "getBulkPaymentCancellationScaStatusTime",
			description = "Metrics to monitor the times of getBulkPaymentCancellationScaStatus method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "getBulkPaymentCancellationScaStatusCount",
			description = "Metrics to monitor rate of calls of method getBulkPaymentCancellationScaStatus method.",
			absolute = true)
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{payment-product}/{paymentId}/cancellation-authorisations/{cancellationId}")
    public Response getBulkPaymentCancellationScaStatus(@PathParam("payment-product") String paymentProduct, @PathParam("paymentId") String paymentId, @PathParam("cancellationId") String cancellationAuthorisationId,
                                                    @Context ContainerRequestContext ctx);

}
