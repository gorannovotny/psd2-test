package hr.abc.psd2.bean;

import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.model.dto.ConsentCofRequestResponseDto;

public interface IConsentCofBean {

    ConsentCofRequestResponseDto createConsentCof(ConsentCofRequestResponseDto consentCofRequestResponseDto) throws AbitRuleException;

    ConsentCofRequestResponseDto getConsentInformation(String consentId, String tppId);

    ConsentCofRequestResponseDto deleteConsent(String consentId, String tppId);



}
