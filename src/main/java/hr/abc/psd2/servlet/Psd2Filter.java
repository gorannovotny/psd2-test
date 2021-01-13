package hr.abc.psd2.servlet;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.abc.psd2.security.LoginHandler;
import hr.abc.psd2.util.Psd2Util;

public class Psd2Filter implements Filter {

	final Logger LOGGER = LoggerFactory.getLogger(Psd2Filter.class);

//	@Inject
//	WebLoginBean loginBean;
	@Inject
	LoginHandler loginHandler;

	public Psd2Filter() {
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		if(!loginHandler.processLogin(request, response)){
			httpResponse.sendRedirect(httpResponse.encodeURL(httpRequest.getContextPath() + "/faces/login.xhtml" + "?returnUrl=" + Psd2Util.getReturnPath(httpRequest)));
		}else{
			chain.doFilter(request, response);
		}
	}
}