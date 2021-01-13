package hr.abc.psd2.bean;

import java.time.LocalDateTime;
import java.util.List;

import hr.abc.psd2.model.dto.cof.ConsentCofDto;
import hr.abc.psd2.model.dto.core.NalogSepaDto;
import hr.abc.psd2.model.dto.pis.TppNalogPlatniDto;
import hr.abc.psd2.model.dto.pspic.ConfirmationOfFoundsRequestDto;

public interface IPsd2UtilBean {

    ConfirmationOfFoundsRequestDto validateConfirmationOfFoundsRequestBody(ConfirmationOfFoundsRequestDto confirmationOfFoundsRequestDto);

    ConsentCofDto validateConsentCof(String consentId, String tppId);

	LocalDateTime getVrijemeTrajanjaAutorizacijeSepaNaloga(List<NalogSepaDto> asList);

	LocalDateTime getVrijemeTrajanjaAutorizacijeTppNaloga(List<TppNalogPlatniDto> tppNaloziFinal);

}
