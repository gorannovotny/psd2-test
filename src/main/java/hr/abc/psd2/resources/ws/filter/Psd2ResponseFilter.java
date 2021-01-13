package hr.abc.psd2.resources.ws.filter;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import hr.abc.psd2.bean.impl.Psd2CertificateSignatureBean;
import hr.abc.psd2.bean.impl.Psd2FilterBean;
import io.vertx.core.http.HttpServerRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Tomica on 17-Feb-19.
 */

@Slf4j
@Provider
public class Psd2ResponseFilter implements ContainerResponseFilter {

	@Context
	private HttpServerRequest httpServletRequest;

	@Context
	private ResourceInfo resourceInfo;

	@Inject
	private Psd2FilterBean psd2FilterBean;

	@Inject
	private Psd2CertificateSignatureBean psd2CertificateSignatureBean;

	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
		log.info("START filter respons for resourceMethod={}", resourceInfo.getResourceMethod().getName());

		//set response header attributes
		log.debug("   setting response headers ");
		psd2FilterBean.setResponseHeaders(resourceInfo, responseContext, httpServletRequest);
		log.debug("   setting response headers finished");
		
		//sign
		log.debug("   signing response");
		psd2FilterBean.signResposne(responseContext);
		log.debug("   response signed");
		
		log.info("END filter respons for resourceMethod={}", resourceInfo.getResourceMethod().getName());

	}

}
