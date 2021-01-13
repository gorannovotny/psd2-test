package hr.abc.psd2.bean.impl.sberbank;

import static hr.abc.psd2.bean.impl.ServiceConstants.SERVICE_SBER;
import static hr.abc.psd2.bean.impl.ServiceConstants.TRUE;
import static hr.abc.psd2.model.dto.error.ErrorCode.CONSENT_UNKNOWN;
import static hr.abc.psd2.util.Psd2Constants.MULTI_CURRENCY_ACCOUNT;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import hr.abc.psd2.bean.IConsentAisBean;
import hr.abc.psd2.bean.impl.AbstractConsentAisBean;
import hr.abc.psd2.dao.ConsentAisDao;
import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.exception.ConsentAISException;
import hr.abc.psd2.model.RequestType;
import hr.abc.psd2.model.dto.ais.ConsentAccountAisDto;
import hr.abc.psd2.model.dto.ais.ConsentAisDto;
import hr.sberbank.model.account.AccountDetails;
import hr.sberbank.resources.AccountRestSrvc;
import io.quarkus.arc.properties.IfBuildProperty;

@Dependent
@IfBuildProperty(name = SERVICE_SBER, stringValue = TRUE)
public class ConsentAisBeanSber extends AbstractConsentAisBean implements IConsentAisBean, Serializable {

	@Inject
	private ConsentAisDao consentDao;

	@Inject
	@RestClient
	AccountRestSrvc accountRestSrvc;

	private static final long serialVersionUID = 1L;

	@Override
	public ConsentAisDto validateConsent(Integer consentID, String requestTpp, String ipAdressPsu, RequestType reqType) throws AbitRuleException, ConsentAISException {
		return consentDao.validateConsent(consentID, requestTpp, ipAdressPsu, reqType);
	}

	@Override
	public ConsentAccountAisDto checkConsentTypeForResourceId(List<ConsentAccountAisDto> consentAccountAisDtoList, String type, String resourceId, RequestType reqType) throws ConsentAISException {
		ConsentAccountAisDto result = null;
		AccountDetails accDts = accountRestSrvc.accountsAccountIdGet(resourceId);
		for (ConsentAccountAisDto consentAccountAis : consentAccountAisDtoList) {
			if (consentAccountAis.getIbanAcc().equals(accDts.getIban())
					&& (consentAccountAis.getCurrencyAcc().equals(accDts.getCurrency()) || consentAccountAis.getCurrencyAcc().equals(MULTI_CURRENCY_ACCOUNT))
					&& consentAccountAis.getTypeAcc().equals(type)) {
				result = consentAccountAis;
				break;
			}
		}
		if (result == null) {
			throw new ConsentAISException.Builder().withCode(CONSENT_UNKNOWN)
					.withErrors("Traženi račun nema BALANCE access type")
					.withReqType(reqType)
					.build();
		}
		return result;
	}
}