package hr.abc.psd2.bean.impl.paba;

import static hr.abc.psd2.bean.impl.ServiceConstants.SERVICE_PABA;
import static hr.abc.psd2.bean.impl.ServiceConstants.TRUE;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

import hr.abc.psd2.bean.IConfirmationOfFundsBean;
import hr.abc.psd2.bean.impl.AbstractConfirmationOfFundsBean;
import hr.abc.psd2.dao.AccountInformationDao;
import io.quarkus.arc.properties.IfBuildProperty;

@IfBuildProperty(name = SERVICE_PABA, stringValue = TRUE)
@Dependent
@Transactional
public class ConfirmationOfFundsServiceBeanPaba extends AbstractConfirmationOfFundsBean implements IConfirmationOfFundsBean, Serializable {

    private static final long serialVersionUID = 1L;

    
    @Inject
    private AccountInformationDao accountDao;



    @Override
    public String getPartiParBySifraPar(String sifraPar) {
       return accountDao.getPartiParBySifraPar(sifraPar);
    }

    @Override
    public List<String> getSifraValuteListBySifraPar(String iban) {

        // stari return accountDao.getSifraValuteListBySifraPar(sifraPar);
        //promijenjeno u iban
        return null;
    }
}
