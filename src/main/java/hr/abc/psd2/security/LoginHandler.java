package hr.abc.psd2.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import hr.abit.b3.biz.security.WebLoginBean;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class LoginHandler {

	private
    @Inject
    EntityManager entityManager;

//	@Inject
//	WebLoginBean loginBean;

	public Boolean processLogin(ServletRequest request, ServletResponse response) throws IOException, ServletException {

		Boolean result = true;
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession httpSession = httpRequest.getSession(false);

		//Provjera da li je korisnik logiran
		try {
//			WebIdentifikacijaDto tmp = loginBean.getIdent();
		}catch(NullPointerException ex){
			result = false;
		}
		return result;
	}
}
