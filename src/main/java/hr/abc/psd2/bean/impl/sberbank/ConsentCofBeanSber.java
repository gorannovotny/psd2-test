package hr.abc.psd2.bean.impl.sberbank;

import static hr.abc.psd2.bean.impl.ServiceConstants.SERVICE_SBER;
import static hr.abc.psd2.bean.impl.ServiceConstants.TRUE;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import hr.abc.psd2.bean.IConsentCofBean;
import hr.abc.psd2.bean.impl.AbstractConsentCofBean;
import hr.abc.psd2.mapper.AccountMapper;
import hr.abc.psd2.model.dto.core.PartijaDto;
import hr.sberbank.resources.AccountRestSrvc;
import io.quarkus.arc.properties.IfBuildProperty;

@Dependent
@IfBuildProperty(name = SERVICE_SBER, stringValue = TRUE)
public class ConsentCofBeanSber extends AbstractConsentCofBean implements IConsentCofBean, Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    @RestClient
    AccountRestSrvc accountRestSrvc;

    @Inject
	AccountMapper accontMapper;
    
    @Override
    protected PartijaDto getPartijaDtoByBrojPartije(String partija) {
        return accontMapper.mapAccountDetailsToPartijaDto(accountRestSrvc.accountsAccountIdGet(partija));
    }
}
