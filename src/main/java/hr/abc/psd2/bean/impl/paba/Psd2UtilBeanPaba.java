package hr.abc.psd2.bean.impl.paba;

import static hr.abc.psd2.bean.impl.ServiceConstants.SERVICE_PABA;
import static hr.abc.psd2.bean.impl.ServiceConstants.TRUE;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import hr.abc.psd2.bean.IPsd2UtilBean;
import hr.abc.psd2.bean.impl.AbstractPsd2UtilBean;
import hr.abc.psd2.dao.ConsentCofDao;
import io.quarkus.arc.properties.IfBuildProperty;

@IfBuildProperty(name = SERVICE_PABA, stringValue = TRUE)
@Transactional
@Dependent
public class Psd2UtilBeanPaba extends AbstractPsd2UtilBean implements IPsd2UtilBean, Serializable {

	private static final long serialVersionUID = 1L;

	//    @Inject
	//    private ObracunskiDatum obracunskiDatum;

	@Inject
	private EntityManager entityManager;

	@Inject
	private ConsentCofDao consentCofDao;

}
