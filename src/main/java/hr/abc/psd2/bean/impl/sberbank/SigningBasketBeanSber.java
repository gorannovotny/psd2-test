package hr.abc.psd2.bean.impl.sberbank;

import static hr.abc.psd2.bean.impl.ServiceConstants.SERVICE_SBER;
import static hr.abc.psd2.bean.impl.ServiceConstants.TRUE;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import hr.abc.psd2.bean.ISigningBasketBean;
import hr.abc.psd2.bean.impl.AbstractSigningBasketBean;
import hr.abc.psd2.model.dto.core.PartijaDto;
import io.quarkus.arc.properties.IfBuildProperty;

@Dependent
@IfBuildProperty(name = SERVICE_SBER, stringValue = TRUE)
public class SigningBasketBeanSber extends AbstractSigningBasketBean implements ISigningBasketBean, Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	protected PartijaDto getPartijaDtoByBrojPartije(String brojPartije) {
		throw new UnsupportedOperationException("Unsuported operation");
	}
}
