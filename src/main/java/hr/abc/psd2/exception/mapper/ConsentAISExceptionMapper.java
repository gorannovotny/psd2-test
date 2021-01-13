package hr.abc.psd2.exception.mapper;

import static hr.abc.psd2.model.RequestType.ACCOUNT_ID_IN_PATH;
import static hr.abc.psd2.model.RequestType.PATH;
import static hr.abc.psd2.model.RequestType.PAYLOAD;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.collections.CollectionUtils;

import hr.abc.psd2.exception.ConsentAISException;
import hr.abc.psd2.model.RequestType;
import hr.abc.psd2.model.dto.error.ErrorCode;
import hr.abc.psd2.model.dto.error.ErrorInformationDto;
import hr.abc.psd2.model.dto.error.ErrorMessageDto;
import hr.abc.psd2.util.AisSpecificHttpErrorCodes;
import hr.abc.psd2.util.InternationalizationUtil;
import hr.abc.psd2.util.Psd2Util;
import lombok.extern.slf4j.Slf4j;

@Dependent
@Provider
@Slf4j
public class ConsentAISExceptionMapper implements ExceptionMapper<ConsentAISException> {

	@Context
	UriInfo info;

	@Override
	public Response toResponse(ConsentAISException exception) {

		String urlPath = info.getPath();

		ErrorCode code = exception.getCode();
		RequestType reqType = exception.getReqType();
		List<String> errors = exception.getErrors();
		Throwable thtowable = exception.getThrowable();

		List<String> tppMessages = internationalizeMessages(errors);
		ErrorInformationDto errorInfo = Psd2Util.setErrorInformationDto(code.toString(), tppMessages);

		Response res = buildResponse(code, errorInfo, reqType);

		log.error("Request for resource {} with reqType={} , resulted with errorCode={} -> translated to HTTP status code <{}> . TPP messages={}", 
				urlPath, reqType, code, res.getStatus(), tppMessages, thtowable);
		
		return res;
	}

	private Response buildResponse(ErrorCode code, ErrorInformationDto errorInfo, RequestType reqType) {

		Response res;

		List<ErrorMessageDto> tppMessages = errorInfo.getTppMessages();

		ResponseBuilder rspBuilder;
		if (CollectionUtils.isNotEmpty(tppMessages)) {
			switch (code) {
			case RESOURCE_EXPIRED:
				if (PAYLOAD.equals(reqType)) {
					rspBuilder = Response.status(Response.Status.BAD_REQUEST);//400
				} else {
					rspBuilder = Response.status(Response.Status.FORBIDDEN);//403
				}
				break;
			case ACCESS_EXCEEDED:
				rspBuilder = Response.status(AisSpecificHttpErrorCodes.ACCESS_EXCEEDED);//429
				break;
			case CONSENT_UNKNOWN:
				rspBuilder = Response.status(Response.Status.FORBIDDEN);//403
				break;
			case CONSENT_EXPIRED:
				rspBuilder = Response.status(Response.Status.UNAUTHORIZED);//401
				break;
			case FORMAT_ERROR:
				rspBuilder = Response.status(Response.Status.BAD_REQUEST);//400
				break;
			case RESOURCE_UNKNOWN: // po specki
				if (ACCOUNT_ID_IN_PATH.equals(reqType)) {
					rspBuilder = Response.status(Response.Status.NOT_FOUND);//404
				} else if (PATH.equals(reqType)) {
					rspBuilder = Response.status(Response.Status.FORBIDDEN);//403
				} else {
					//PAYLOAD
					rspBuilder = Response.status(Response.Status.BAD_REQUEST);//400
				}

				break;
			case BAD_REQUEST: // po specki
				rspBuilder = Response.status(Response.Status.BAD_REQUEST);//400
				break;
			default:
				rspBuilder = Response.status(Response.Status.INTERNAL_SERVER_ERROR);//500
			}
		} else {
			rspBuilder = Response.status(Response.Status.INTERNAL_SERVER_ERROR);//500
		}

		res = rspBuilder.entity(errorInfo)
				.build();

		return res;

	}

	private List<String> internationalizeMessages(List<String> msgCodes) {
		List<String> messages = new ArrayList<>();
		msgCodes.forEach(m -> messages.add(InternationalizationUtil.getPsd2LocalMessage(m)));
		return messages;
	}
}