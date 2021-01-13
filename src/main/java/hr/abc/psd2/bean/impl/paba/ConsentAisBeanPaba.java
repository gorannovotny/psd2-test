package hr.abc.psd2.bean.impl.paba;

import static hr.abc.psd2.bean.impl.ServiceConstants.SERVICE_PABA;
import static hr.abc.psd2.bean.impl.ServiceConstants.TRUE;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

import hr.abc.psd2.bean.IConsentAisBean;
import hr.abc.psd2.bean.impl.AbstractConsentAisBean;
import hr.abc.psd2.dao.ConsentActionAisDao;
import hr.abc.psd2.dao.ConsentAisDao;
import hr.abc.psd2.dao.Psd2UtilDao;
import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.exception.ConsentAISException;
import hr.abc.psd2.model.RequestType;
import hr.abc.psd2.model.dto.ais.ConsentAccountAisDto;
import hr.abc.psd2.model.dto.ais.ConsentAisDto;
import io.quarkus.arc.properties.IfBuildProperty;

/**
 * Created by Tomica on 09-Oct-18.
 */
@Dependent
@IfBuildProperty(name = SERVICE_PABA, stringValue = TRUE)
@Transactional
public class ConsentAisBeanPaba extends AbstractConsentAisBean implements IConsentAisBean, Serializable {

	private static final long serialVersionUID = 1L;

	private @Inject ConsentAisDao consentDao;
	private @Inject Psd2UtilDao psd2UtilDao;
	//test
	private @Inject ConsentActionAisDao actionAisDao;
	private @EJB AuthorizationBeanPaba authorizationBean;

	@Override
	public ConsentAisDto validateConsent(Integer consentID, String requestTpp, String ipAdressPsu, RequestType reqType) throws AbitRuleException, ConsentAISException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConsentAccountAisDto checkConsentTypeForResourceId(List<ConsentAccountAisDto> consentAccountAisDtoList, String type, String resourceId, RequestType reqType) throws ConsentAISException {
		throw new UnsupportedOperationException("Unsuported operation");
	}



}
