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

import hr.abc.psd2.model.dto.ConsentCofRequestResponseDto;
import hr.abc.psd2.resources.ws.filter.binding.RequestFilterPsd2;
import hr.abc.psd2.resources.ws.filter.binding.ResponseFilterPsd2;

/**
 * Web service resource for Confirmation of Founds Consent .
 * <p>
 * Created by dKosutar on 14.06.2019..
 */
@RequestFilterPsd2
@ResponseFilterPsd2
@Path("/v2/consents/confirmation-of-funds")
public interface IConsentCofResources {
    /**
     * summary: Create consent
     * description:
     * This method creates a confirmation of funds consent resource at the ASPSP regarding confirmation of funds access
     * to an account specified in this request.
    */
	@Timed(name = "createConsentConfirmationOfFundsTime",
			description = "Metrics to monitor the times of createConsentConfirmationOfFunds method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "createConsentConfirmationOfFundsCount",
			description = "Metrics to monitor rate of calls of method createConsentConfirmationOfFunds method.",
			absolute = true)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createConsentConfirmationOfFunds (ConsentCofRequestResponseDto consentCofRequestResponseDto, @Context HttpHeaders headers);

    /**
     * summary: Get Consent Status
     * description:
     * Can check the status of an account information consent resource.
     */
	@Timed(name = "getConsentConfirmationOfFundsStatusTime",
			description = "Metrics to monitor the times of getConsentConfirmationOfFundsStatus method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "getConsentConfirmationOfFundsStatusCount",
			description = "Metrics to monitor rate of calls of method getConsentConfirmationOfFundsStatus method.",
			absolute = true)
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{consentId}/status")
    public Response getConsentConfirmationOfFundsStatus (@PathParam("consentId") String consentId, @Context HttpHeaders headers);

    /**
     * summary:Get Consent Content
     * description:
     * Returns the content of an account information consent object.
     * This is returning the data for the TPP especially in cases, where the consent was directly managed between
     * ASPSP and PSU e.g. in a re-direct SCA Approach.
     */
	@Timed(name = "getConsentConfirmationOfFundsTime",
			description = "Metrics to monitor the times of getConsentConfirmationOfFunds method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "getConsentConfirmationOfFundsCount",
			description = "Metrics to monitor rate of calls of method getConsentConfirmationOfFunds method.",
			absolute = true)
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{consentId}")
    public Response getConsentConfirmationOfFunds (@PathParam("consentId") String consentId, @Context HttpHeaders headers);

    /**
     * summary: Delete Consent
     * description:
     * Deletes a given consent.
     */
	@Timed(name = "deleteConsentConfirmationOfFundsTime",
			description = "Metrics to monitor the times of deleteConsentConfirmationOfFunds method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "deleteConsentConfirmationOfFundsCount",
			description = "Metrics to monitor rate of calls of method deleteConsentConfirmationOfFunds method.",
			absolute = true)
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{consentId}")
    public Response deleteConsentConfirmationOfFunds (@PathParam("consentId") String consentId, @Context HttpHeaders headers);

    /**
     * summary: Start the authorisation process for a consent
     * description:
     * Create an authorisation sub-resource and start the authorisation process of a consent.
     * The message might in addition transmit authentication and authorisation related data.
     */
	@Timed(name = "startConsentCofAuthorisationTime",
			description = "Metrics to monitor the times of startConsentCofAuthorisation method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "startConsentCofAuthorisationCount",
			description = "Metrics to monitor rate of calls of method startConsentCofAuthorisation method.",
			absolute = true)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{consentId}/authorisations")
    public Response startConsentCofAuthorisation(@PathParam("consentId") String consentId, @Context HttpHeaders headers);

    /**
     * summary: Get Consent Authorisation Sub-Resources Request
     * description:
     * Return a list of all authorisation subresources IDs which have been created.
     **/
	@Timed(name = "getConsentCofAuthorisationTime",
			description = "Metrics to monitor the times of getConsentCofAuthorisation method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "getConsentCofAuthorisationCount",
			description = "Metrics to monitor rate of calls of method getConsentCofAuthorisation method.",
			absolute = true)
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{consentId}/authorisations")
    public Response getConsentCofAuthorisation(@PathParam("consentId") String consentId, @Context HttpHeaders headers);

    /**
     * summary: Read the SCA status of the consent authorisation.
     * description:
     * This method returns the SCA status of a consent initiation's authorisation sub-resource.
     */
	@Timed(name = "getConsentCofScaStatusTime",
			description = "Metrics to monitor the times of getConsentCofScaStatus method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "getConsentCofScaStatusCount",
			description = "Metrics to monitor rate of calls of method getConsentCofScaStatus method.",
			absolute = true)
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{consentId}/authorisations/{authorisationId}")
    public Response getConsentCofScaStatus(@PathParam("consentId") String consentId, @PathParam("authorisationId") String authorisationId, @Context HttpHeaders headers);


}
