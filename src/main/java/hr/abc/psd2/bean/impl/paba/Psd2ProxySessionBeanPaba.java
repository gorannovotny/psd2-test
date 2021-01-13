package hr.abc.psd2.bean.impl.paba;

import static hr.abc.psd2.bean.impl.ServiceConstants.SERVICE_PABA;
import static hr.abc.psd2.bean.impl.ServiceConstants.TRUE;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.transaction.Transactional;

import hr.abc.psd2.dao.AuthorizationDao;
import hr.abc.psd2.dao.ConsentAisDao;
import hr.abc.psd2.dao.ConsentCofDao;
import hr.abc.psd2.dao.NalogTppDao;
import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.model.dto.ais.ConsentAisDto;
import hr.abc.psd2.model.dto.cof.ConsentCofDto;
import hr.abc.psd2.model.dto.core.TppNalogFilterDto;
import hr.abc.psd2.model.dto.pis.TppNalogPlatniDto;
import io.quarkus.arc.properties.IfBuildProperty;

@IfBuildProperty(name = SERVICE_PABA, stringValue = TRUE)
//@Stateless (mappedName = "Psd2ProxySessionBean")
@Transactional	
@Dependent
public class Psd2ProxySessionBeanPaba implements IPsd2ProxyRemotePaba , Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ConsentAisDao consentDao;
    @Inject
    private ConsentCofDao consentCofDao;

    @Inject
    private NalogTppBeanPaba nalogTppBean;

    @Inject
    private AuthorizationDao authorizationDao;

    @Inject
    private NalogTppDao nalogTppDao;


    @Override
    public List<ConsentAisDto> getListOfConsentsFromPsd2(Integer sifraKom) throws AbitRuleException {
        return consentDao.getConsentAisDtoListByKom(sifraKom);
    }

    @Override
    public List<ConsentCofDto> getListOfConsentsCofFromPsd2(Integer sifraKom) throws AbitRuleException {
        return consentCofDao.getConsentCofDtoListByKom(sifraKom);
    }

    @Override
    public ConsentAisDto povuciConsentAisFomIb(ConsentAisDto consent, String signId, Integer sifraKorisnika) throws AbitRuleException{
        return nalogTppBean.povuciConsentAisFomIb(consent, signId, sifraKorisnika);
    }

    @Override
    public List<TppNalogPlatniDto> getNalogTpplist(TppNalogFilterDto filterDto) throws AbitRuleException {
        return nalogTppDao.getTppNalogList(filterDto);
    }

    public TppNalogPlatniDto findDtoByPrimaryKey(String id) {
        return nalogTppDao.getTppNalogBySifra(Integer.valueOf(id));
    }

    @Override
    public ConsentAisDto povuciConsentCofFomIb(ConsentAisDto consent, String signId, Integer sifraKorisnika) throws AbitRuleException {
        return nalogTppBean.povuciConsentCofFomIb(consent, signId, sifraKorisnika);
    }
    @Override
    public String addConsentSmartCheck(List<?> nalozi, String sessionId, String hash) throws AbitRuleException{
        return  nalogTppBean.addSmartCheck(nalozi,sessionId,hash);
    }

}
