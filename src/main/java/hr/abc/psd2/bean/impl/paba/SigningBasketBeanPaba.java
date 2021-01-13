package hr.abc.psd2.bean.impl.paba;

import static hr.abc.psd2.bean.impl.ServiceConstants.SERVICE_PABA;
import static hr.abc.psd2.bean.impl.ServiceConstants.TRUE;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import hr.abc.psd2.bean.ISigningBasketBean;
import hr.abc.psd2.bean.impl.AbstractSigningBasketBean;
import hr.abc.psd2.dao.AuthorizationDao;
import hr.abc.psd2.dao.BasketDao;
import hr.abc.psd2.dao.NalogTppDao;
import hr.abc.psd2.model.dto.core.PartijaDto;
import hr.abc.psd2.service2.ICoreSrvc;
import io.quarkus.arc.properties.IfBuildProperty;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Tomica on 14-May-19.
 */
@IfBuildProperty(name = SERVICE_PABA, stringValue = TRUE)
@Slf4j
@Dependent
@Transactional
public class SigningBasketBeanPaba extends AbstractSigningBasketBean implements ISigningBasketBean, Serializable {

	private static final long serialVersionUID = 1L;

	private @Inject NalogTppDao nalogTppDao;

	private @Inject BasketDao basketDao;

	private @Inject AuthorizationDao authorizationDao;

	// resources
	@Inject
	private EntityManager entityManager;

	@Inject
	private ICoreSrvc coreSrvc;

	@Override
	protected PartijaDto getPartijaDtoByBrojPartije(String brojPartije) {
		return coreSrvc.getPartijaDtoByBrojPartije(brojPartije);
	}

}
