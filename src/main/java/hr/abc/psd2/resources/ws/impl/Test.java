package hr.abc.psd2.resources.ws.impl;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("v1/test")
public class Test {

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getConsentStatus() {

        return Response.status(Response.Status.OK).entity("TEST").build();
    }
}
