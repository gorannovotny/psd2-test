package hr.abc.psd2.resources.ws.filter;
import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

import hr.abc.psd2.bean.impl.Psd2FilterBean;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Tomica on 08-Aug-19.
 */
//VRATITI @Provider
@Slf4j
public class Psd2ResponseInterceptor implements WriterInterceptor {

    @Context
    private HttpServletRequest httpServletRequest;
    @Context
    private ResourceInfo resourceInfo;
    @Context
    private HttpServletResponse httpServletResponse;

    @EJB
    private Psd2FilterBean psd2FilterBean;

    @Override
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {

        //log response
       /* String logMessageString = psd2FilterBean.logResponse2(resourceInfo, httpServletRequest, context, httpServletResponse);
        //log to DB
        Object xRequestIdObject = context.getHeaders().getFirst(X_REQUEST_ID.getValue());
        String xRequestId = null;
        Object tppIdObject = httpServletRequest.getAttribute(TPP_ID.getValue());
        String tppId = null;
        if (tppIdObject != null) tppId = tppIdObject.toString();
        if (xRequestIdObject != null) xRequestId = xRequestIdObject.toString();
        psd2FilterBean.logMessageToDB(new Psd2MessageLogDto(xRequestId, RESPONSE.toString(), Timestamp.valueOf(LocalDateTime.now()), logMessageString, tppId,
                httpServletRequest.getHeader(X_FORWARDED_FOR.getValue()), httpServletRequest.getRemoteHost(), httpServletRequest.getHeader(PSU_IP_ADDRESS.getValue())));*/
    }

}
