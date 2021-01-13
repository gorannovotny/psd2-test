package hr.sberbank.resources;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import hr.sberbank.model.payment.BulkPaymentOrdersStatus;
import hr.sberbank.model.payment.PaymentOrderInformationResponse;
import hr.sberbank.model.payment.PaymentOrderRequestJson;
import hr.sberbank.model.payment.PaymentOrderStatusResponseJson;

@RegisterRestClient(configKey = "payment-api")
public interface PaymentRestSrvc {

    @POST
    @Path("/payments/{payment-product}")
    @Consumes({ "application/vnd.sberbank.v1+json" })
    @Produces({ "application/vnd.sberbank.v1+json" })
    Response paymentsPaymentProductPost(@Valid PaymentOrderRequestJson body, @NotNull @PathParam("payment-product") String paymentProduct, @NotNull  @HeaderParam("X-Request-ID") UUID xRequestID) throws WebApplicationException;

    @POST
    @Path("/payments/bulk")
    @Consumes({ "application/vnd.sberbank.v1+json" })
    @Produces({ "application/vnd.sberbank.v1+json" })
    BulkPaymentOrdersStatus paymentsBulkPost(@Valid Object body, @NotNull  @HeaderParam("X-Request-ID") UUID xRequestID) throws WebApplicationException;

    @GET
    @Path("/payments/{payment-product}/{paymentRequestId}")
    @Produces(APPLICATION_JSON)
    PaymentOrderInformationResponse paymentsPaymentProductPaymentRequestIdGet(@NotNull @PathParam("payment-product") String paymentProduct, @PathParam("paymentRequestId") String paymentRequestId, @NotNull  @HeaderParam("X-Request-ID")  UUID xRequestID) throws WebApplicationException;

    @GET
    @Path("/payments/{payment-product}/{paymentRequestId}/status")
    @Produces(APPLICATION_JSON)
    PaymentOrderStatusResponseJson paymentsPaymentProductPaymentRequestIdStatusGet(@NotNull @PathParam("payment-product") String paymentProduct, @PathParam("paymentRequestId") String paymentRequestId, @NotNull  @HeaderParam("X-Request-ID")  UUID xRequestID) throws WebApplicationException;

    @DELETE
    @Path("/payments/bulk/cancel/{payment-request-id}")
    @Produces(APPLICATION_JSON)
    Response paymentsBulkCancelPaymentRequestIdDelete(@PathParam("payment-request-id") String paymentRequestId, @NotNull  @HeaderParam("X-Request-ID")  UUID xRequestID, @HeaderParam("X-Channel") @DefaultValue("PSD2") String xChannel) throws WebApplicationException;

}
