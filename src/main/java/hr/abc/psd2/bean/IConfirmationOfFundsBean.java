package hr.abc.psd2.bean;

import hr.abc.psd2.model.dto.cof.ConsentCofDto;
import hr.abc.psd2.model.dto.pspic.ConfirmationOfFoundsRequestDto;
import hr.abc.psd2.model.dto.pspic.ConfirmationOfFoundsResponseDto;

public interface IConfirmationOfFundsBean {

    ConfirmationOfFoundsResponseDto resolveFounds(ConfirmationOfFoundsRequestDto foundsRequestDto, ConsentCofDto consentCofDto);

}
