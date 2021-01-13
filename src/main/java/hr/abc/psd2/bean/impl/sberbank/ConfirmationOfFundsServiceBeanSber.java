package hr.abc.psd2.bean.impl.sberbank;

import static hr.abc.psd2.bean.impl.ServiceConstants.SERVICE_SBER;
import static hr.abc.psd2.bean.impl.ServiceConstants.TRUE;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import hr.abc.psd2.bean.IConfirmationOfFundsBean;
import hr.abc.psd2.bean.impl.AbstractConfirmationOfFundsBean;
import hr.abc.psd2.mapper.AccountMapper;
import hr.sberbank.model.account.AccountDetails;
import hr.sberbank.resources.AccountRestSrvc;
import io.quarkus.arc.properties.IfBuildProperty;

@Dependent
@IfBuildProperty(name = SERVICE_SBER, stringValue = TRUE)
public class ConfirmationOfFundsServiceBeanSber extends AbstractConfirmationOfFundsBean implements IConfirmationOfFundsBean, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	@RestClient
	AccountRestSrvc accountRestSrvc;
	
	@Inject
	AccountMapper accontMapper;

	@Override
	public String getPartiParBySifraPar(String sifraPar) {
		AccountDetails accountDetails = accountRestSrvc.accountsAccountIdGet(sifraPar);
		return accountDetails.getIban().substring(accountDetails.getIban().length() - 10);
	}

	@Override
	public List<String> getSifraValuteListBySifraPar(String iban) {
		return accontMapper.listCurrencyOfAccount(accountRestSrvc.accountsGet(iban, null));
	}
}
