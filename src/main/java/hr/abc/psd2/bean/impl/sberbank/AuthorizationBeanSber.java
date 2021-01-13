package hr.abc.psd2.bean.impl.sberbank;

import static hr.abc.psd2.bean.impl.ServiceConstants.SERVICE_SBER;
import static hr.abc.psd2.bean.impl.ServiceConstants.TRUE;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

import hr.abc.psd2.bean.IAuthorizationBean;
import hr.abc.psd2.bean.impl.AbstractAuthorizationBean;
import hr.abc.psd2.model.dto.authorization.StartAuthorizationResponseDto;
import hr.abc.psd2.model.dto.pis.SinglePaymentDto;
import io.quarkus.arc.properties.IfBuildProperty;

@Dependent
@IfBuildProperty(name = SERVICE_SBER, stringValue = TRUE)
public class AuthorizationBeanSber extends AbstractAuthorizationBean implements IAuthorizationBean, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    protected StartAuthorizationResponseDto validatePaymentAuthBeforeCreate() {
        return null;
    }
}
