package hr.abc.psd2.bean;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ResourceInfo;

import hr.abc.psd2.model.dto.ProcessRequestDto;
import io.vertx.core.http.HttpServerRequest;


public interface IPsd2FilterBean {

    ProcessRequestDto processRequest(ResourceInfo resourceInfo, ContainerRequestContext ctx, HttpServerRequest httpServletRequest);

}
