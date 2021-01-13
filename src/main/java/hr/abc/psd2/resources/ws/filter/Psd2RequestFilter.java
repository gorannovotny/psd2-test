
package hr.abc.psd2.resources.ws.filter;

import static hr.abc.psd2.model.dto.error.ErrorCode.CERTIFICATE_INVALID;
import static hr.abc.psd2.model.dto.error.ErrorCode.FORMAT_ERROR;
import static hr.abc.psd2.model.dto.error.ErrorCode.INTERNAL_SERVER_ERROR;
import static hr.abc.psd2.model.dto.error.ErrorCode.INVALID_TPP_ROLE;
import static hr.abc.psd2.model.dto.error.ErrorCode.SIGNATURE_INVALID;
import static hr.abc.psd2.model.dto.error.ErrorCode.SIGNATURE_MISSING;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.TPP_ID;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.TPP_NAME;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.StringUtils;

import hr.abc.psd2.bean.IPsd2FilterBean;
import hr.abc.psd2.model.dto.ProcessRequestDto;
import hr.abc.psd2.util.Psd2Util;
import io.vertx.core.http.HttpServerRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Tomica on 14-Feb-19.
 */
@Slf4j
@Provider
public class Psd2RequestFilter implements ContainerRequestFilter {

	@Inject
	private IPsd2FilterBean psd2FilterSrvc;

	@Context
	UriInfo info;

	@Context
	private ResourceInfo resourceInfo;

	@Context
	HttpServerRequest request;

	public void filter(ContainerRequestContext ctx) throws IOException {
		log.info("START filter request for resourceMethod={}", resourceInfo.getResourceMethod().getName());

		try {
			ProcessRequestDto processRequestDto = psd2FilterSrvc.processRequest(resourceInfo, ctx, request); // prazni
			if (!processRequestDto.getIsValid()) {
				if (StringUtils.equals(processRequestDto.getErrorInformationDto().getTppMessages().get(0).getCode(), INVALID_TPP_ROLE.getValue())) {
					ctx.abortWith(Response.status(Response.Status.UNAUTHORIZED)
							.entity(processRequestDto.getErrorInformationDto())
							.build());
					log.error("   ABORTED with  HTTP status code <{}>. Internal TPP code={}", Response.Status.UNAUTHORIZED, INVALID_TPP_ROLE);
				}
				if (StringUtils.equals(processRequestDto.getErrorInformationDto().getTppMessages().get(0).getCode(), CERTIFICATE_INVALID.getValue())) {
					ctx.abortWith(Response.status(Response.Status.UNAUTHORIZED)
							.entity(processRequestDto.getErrorInformationDto())
							.build());
					log.error("   ABORTED with  HTTP status code <{}>. Internal TPP code={}", Response.Status.UNAUTHORIZED, CERTIFICATE_INVALID);
				}
				if (StringUtils.equals(processRequestDto.getErrorInformationDto().getTppMessages().get(0).getCode(), SIGNATURE_MISSING.getValue())) {
					ctx.abortWith(Response.status(Response.Status.UNAUTHORIZED)
							.entity(processRequestDto.getErrorInformationDto())
							.build());
					log.error("   ABORTED with  HTTP status code <{}>. Internal TPP code={}", Response.Status.UNAUTHORIZED, SIGNATURE_MISSING);
				}
				if (StringUtils.equals(processRequestDto.getErrorInformationDto().getTppMessages().get(0).getCode(), FORMAT_ERROR.getValue())) {
					ctx.abortWith(Response.status(Response.Status.BAD_REQUEST)
							.entity(processRequestDto.getErrorInformationDto())
							.build());
					log.error("   ABORTED with  HTTP status code <{}>. Internal TPP code={}", Response.Status.BAD_REQUEST, FORMAT_ERROR);
				}
				if (StringUtils.equals(processRequestDto.getErrorInformationDto().getTppMessages().get(0).getCode(), INTERNAL_SERVER_ERROR.getValue())) {
					ctx.abortWith(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
							.entity(processRequestDto.getErrorInformationDto())
							.build());
					log.error("   ABORTED with  HTTP status code <{}>. Internal TPP code={}", Response.Status.INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR);
				}
				if (StringUtils.equals(processRequestDto.getErrorInformationDto().getTppMessages().get(0).getCode(), SIGNATURE_INVALID.getValue())) {
					ctx.abortWith(Response.status(Response.Status.UNAUTHORIZED)
							.entity(processRequestDto.getErrorInformationDto())
							.build());
					log.error("   ABORTED with  HTTP status code <{}>. Internal TPP code={}", Response.Status.UNAUTHORIZED, SIGNATURE_INVALID);
				}
			} else {
				ctx.getHeaders().addFirst(TPP_ID.getValue(), processRequestDto.getTppId());
				if (StringUtils.isNotBlank(processRequestDto.getTppName())) {
					ctx.getHeaders().addFirst(TPP_NAME.getValue(), processRequestDto.getTppName());
				}
				log.info("   VALID for TPP_ID={}, TPP_NAME={}", TPP_ID.getValue(), TPP_NAME.getValue());
			}
		} catch (Exception e) {
			ctx.abortWith(Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto(INTERNAL_SERVER_ERROR.getValue(), new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build());
			log.error("ERROR filtering request. HTTP status code <{}>", Response.Status.INTERNAL_SERVER_ERROR, e);
		}
		log.info("END filter request for resourceMethod={}", resourceInfo.getResourceMethod().getName());
	}
}
