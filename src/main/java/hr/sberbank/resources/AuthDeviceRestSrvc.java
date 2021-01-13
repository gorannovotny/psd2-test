package hr.sberbank.resources;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import hr.sberbank.model.authentication.Device;
import hr.sberbank.model.authentication.DeviceAccountClaimList;
import hr.sberbank.model.authentication.DeviceList;
import hr.sberbank.model.authentication.ValidateMacActionRequest;
import hr.sberbank.model.authentication.ValidateMacActionResponse;
import hr.sberbank.model.authentication.ValidateOtpActionRequest;
import hr.sberbank.model.authentication.ValidateOtpActionResponse;


@RegisterRestClient(configKey = "auth-api")
public interface AuthDeviceRestSrvc {

    @GET
    @Path("/devices")
    @Produces({"application/vnd.sberbank.v1+json"})
    DeviceList customersCustomerIdCreditCardAccountsGet(@QueryParam("query") String query, @QueryParam("page") Integer page, @QueryParam("limit") Integer limit) throws WebApplicationException;

    @GET
    @Path("/devices/{id}")
    @Produces({"application/vnd.sberbank.v1+json"})
    Device devicesIdGet(@PathParam("id") String id) throws WebApplicationException;

    @GET
    @Path("/devices/{id}/account-claims")
    @Produces({"application/vnd.sberbank.v1+json"})
    DeviceAccountClaimList devicesIdAccountClaimsGet(@PathParam("id") String id) throws WebApplicationException;

    @POST
    @Path("/devices/{id}/validate-otp")
    @Consumes({ "application/vnd.sberbank.v1+json" })
    @Produces({ "application/vnd.sberbank.v1+json", "application/json" })
    ValidateOtpActionResponse devicesIdValidateOtpPost(@Valid ValidateOtpActionRequest body, @PathParam("id") String id) throws WebApplicationException;

    @POST
    @Path("/devices/{id}/validate-mac")
    @Consumes({ "application/vnd.sberbank.v1+json" })
    @Produces({ "application/vnd.sberbank.v1+json", "application/json" })
    ValidateMacActionResponse devicesIdValidateMacPost(@Valid ValidateMacActionRequest body, @PathParam("id") String id) throws WebApplicationException;

    @GET
    @Path("/customers/{id}/devices")
    @Produces({"application/vnd.sberbank.v1+json"})
    DeviceList customersIdDevicesGet(@PathParam("id") String id) throws WebApplicationException;

}
