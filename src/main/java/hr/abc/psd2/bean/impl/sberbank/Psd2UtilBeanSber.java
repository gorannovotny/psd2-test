package hr.abc.psd2.bean.impl.sberbank;

import static hr.abc.psd2.bean.impl.ServiceConstants.SERVICE_SBER;
import static hr.abc.psd2.bean.impl.ServiceConstants.TRUE;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import hr.abc.psd2.bean.IPsd2UtilBean;
import hr.abc.psd2.bean.impl.AbstractPsd2UtilBean;
import io.quarkus.arc.properties.IfBuildProperty;

@Dependent
@IfBuildProperty(name = SERVICE_SBER, stringValue = TRUE)
public class Psd2UtilBeanSber extends AbstractPsd2UtilBean implements IPsd2UtilBean, Serializable {

	private static final long serialVersionUID = 1L;

}
