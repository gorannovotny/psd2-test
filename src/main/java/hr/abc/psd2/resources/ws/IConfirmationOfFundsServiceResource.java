package hr.abc.psd2.resources.ws;


import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.metrics.annotation.Timed;

import hr.abc.psd2.model.dto.pspic.ConfirmationOfFoundsRequestDto;
import hr.abc.psd2.resources.ws.filter.binding.RequestFilterPsd2;
import hr.abc.psd2.resources.ws.filter.binding.ResponseFilterPsd2;
import hr.abc.psd2.resources.ws.filter.binding.ResponseInterceptorPsd2;

/**
 * Web service interface resource for Confirmation of Funds Service .
 * <p>
 * Created by dKosutar on 25.03.2019..
 */
@RequestFilterPsd2
@ResponseFilterPsd2
@ResponseInterceptorPsd2
@Path("/v1/funds-confirmations")
@Produces(MediaType.APPLICATION_JSON)
public interface IConfirmationOfFundsServiceResource {

    /**
     * summary: Read Available Founds
     * description:
     * Creates a confirmation of funds request at the ASPSP.
     */
	@Timed(name = "foundsConfirmationsTime",
			description = "Metrics to monitor the times of foundsConfirmations method.",
			unit = MetricUnits.MILLISECONDS,
			absolute = true)
	@Metered(name = "foundsConfirmationsCount",
			description = "Metrics to monitor rate of calls of method foundsConfirmations method.",
			absolute = true)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response foundsConfirmations (ConfirmationOfFoundsRequestDto confirmationOfFoundsRequestDto, @Context ContainerRequestContext ctx);
}
