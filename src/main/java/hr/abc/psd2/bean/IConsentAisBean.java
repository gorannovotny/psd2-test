package hr.abc.psd2.bean;

import java.util.List;

import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.exception.ConsentAISException;
import hr.abc.psd2.model.RequestType;
import hr.abc.psd2.model.dto.ConsentRequestResponseDto;
import hr.abc.psd2.model.dto.ais.ConsentAccountAisDto;
import hr.abc.psd2.model.dto.ais.ConsentActionAisDto;
import hr.abc.psd2.model.dto.ais.ConsentAisDto;

public interface IConsentAisBean {

    ConsentAisDto validateConsent(String consentId, String tppId, String ipAdressPsu, RequestType reqType) throws ConsentAISException;

    ConsentAisDto validateConsent(Integer consentID, String requestTpp, String ipAdressPsu , RequestType reqType) throws AbitRuleException, ConsentAISException;
    
    ConsentAisDto validateConsent(Integer consentID, String requestTpp, RequestType reqType) throws AbitRuleException, ConsentAISException;
    
    void checkConsentAndLogAction(ConsentActionAisDto consentActionDto) throws AbitRuleException;

    ConsentRequestResponseDto createConsentAis(ConsentRequestResponseDto consentRequestResponseDto) throws AbitRuleException;

    ConsentRequestResponseDto getConsentStatus(String consentId, String tppId);

    ConsentRequestResponseDto getConsentInformation(String consentId, String tppId);

    ConsentRequestResponseDto deleteConsent(String consentId, String tppId) throws AbitRuleException;

    ConsentRequestResponseDto getConsentStatusBySifra(Integer sifraConsenta, String tppId) throws AbitRuleException;

    ConsentRequestResponseDto getConsentRequestBySifra(Integer sifraConsenta, String tppId) throws AbitRuleException;

    ConsentAisDto getConsentAisBySifra(Integer sifraConsenta) throws AbitRuleException;
    
    List<ConsentAisDto> getConsentAisDtoListByKom(Integer sifraKom) throws AbitRuleException;
    
	ConsentAccountAisDto checkConsentTypeForResourceId(List<ConsentAccountAisDto> consentAccountAisDtoList, String type, String resourceId, RequestType reqType) throws ConsentAISException;

}
