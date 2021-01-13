package hr.abc.psd2.bean.impl.paba;

import static hr.abc.psd2.bean.impl.ServiceConstants.SERVICE_PABA;
import static hr.abc.psd2.bean.impl.ServiceConstants.TRUE;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

import hr.abc.psd2.bean.IConsentCofBean;
import hr.abc.psd2.bean.impl.AbstractConsentCofBean;
import hr.abc.psd2.model.dto.core.PartijaDto;
import hr.abc.psd2.service2.ICoreSrvc;
import io.quarkus.arc.properties.IfBuildProperty;
import lombok.extern.slf4j.Slf4j;

@IfBuildProperty(name = SERVICE_PABA, stringValue = TRUE)
@Slf4j
@Dependent
@Transactional
public class ConsentCofBeanPaba extends AbstractConsentCofBean implements IConsentCofBean, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	AuthorizationBeanPaba authorizationBean;

	@Inject
	ICoreSrvc coreSrvc; // prazni

	@Override
	protected PartijaDto getPartijaDtoByBrojPartije(String partija) {
		return coreSrvc.getPartijaDtoByBrojPartije(partija);
	}
}
