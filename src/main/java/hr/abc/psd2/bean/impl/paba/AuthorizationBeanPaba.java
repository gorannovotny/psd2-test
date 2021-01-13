package hr.abc.psd2.bean.impl.paba;

import static hr.abc.psd2.bean.impl.ServiceConstants.SERVICE_PABA;
import static hr.abc.psd2.bean.impl.ServiceConstants.TRUE;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

import hr.abc.psd2.bean.IAuthorizationBean;
import hr.abc.psd2.bean.impl.AbstractAuthorizationBean;
import hr.abc.psd2.dao.AuthorizationDao;
import hr.abc.psd2.dao.BasketDao;
import hr.abc.psd2.dao.ConsentAisDao;
import hr.abc.psd2.dao.ConsentCofDao;
import hr.abc.psd2.dao.NalogTppDao;
import hr.abc.psd2.dao.Psd2UtilDao;
import hr.abc.psd2.model.dto.authorization.StartAuthorizationResponseDto;
import io.quarkus.arc.properties.IfBuildProperty;
import lombok.extern.slf4j.Slf4j;

@IfBuildProperty(name = SERVICE_PABA, stringValue = TRUE)
@Slf4j
@Dependent
@Transactional
public class AuthorizationBeanPaba extends AbstractAuthorizationBean implements IAuthorizationBean, Serializable {

	private static final long serialVersionUID = 1L;

	private @Inject AuthorizationDao authorizationDao;
	private @Inject NalogTppDao nalogTppDao;
	private @Inject ConsentAisDao consentAisDao;
	private @Inject Psd2UtilDao psd2UtilDao;
	@Inject
	BasketDao basketDao;
	private @EJB AuthorizationBeanPaba thisBean;
	private @EJB Psd2UtilBeanPaba psd2UtilBean;
	@Inject
	SigningBasketBeanPaba basketBean;
	@Inject
	private ConsentCofDao consentCofDao;

	@Override
	protected StartAuthorizationResponseDto validatePaymentAuthBeforeCreate() {
		return null;
	}
}
