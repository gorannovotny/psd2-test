package hr.abc.psd2.bean.impl.paba;

import java.util.List;

import javax.ejb.Remote;

import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.model.dto.ais.ConsentAisDto;
import hr.abc.psd2.model.dto.cof.ConsentCofDto;
import hr.abc.psd2.model.dto.core.TppNalogFilterDto;
import hr.abc.psd2.model.dto.pis.TppNalogPlatniDto;

@Remote
public interface IPsd2ProxyRemotePaba {
    public List<ConsentAisDto> getListOfConsentsFromPsd2(Integer sifraKom) throws AbitRuleException;

    public List<ConsentCofDto> getListOfConsentsCofFromPsd2(Integer sifraKom) throws AbitRuleException;

    public ConsentAisDto povuciConsentAisFomIb(ConsentAisDto consent, String signId, Integer sifraKorisnika) throws AbitRuleException;

    public List<TppNalogPlatniDto> getNalogTpplist(TppNalogFilterDto filterDto) throws AbitRuleException;

    public TppNalogPlatniDto findDtoByPrimaryKey(String id) throws AbitRuleException;

    public ConsentAisDto povuciConsentCofFomIb(ConsentAisDto consent, String signId, Integer sifraKorisnika) throws AbitRuleException;

    public String addConsentSmartCheck(List<?> nalozi, String sessionId, String hash) throws AbitRuleException;

}
