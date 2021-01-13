package hr.abc.psd2.bean;

import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.model.dto.basket.BasketDto;
import hr.abc.psd2.model.dto.basket.BasketRequestDto;
import hr.abc.psd2.model.dto.basket.BasketResponseDto;

public interface ISigningBasketBean {

    BasketResponseDto create(BasketRequestDto basketRequestDto) throws AbitRuleException;

    BasketDto getAndValidateBasket(String basketId, BasketRequestDto basketRequestDto);

    BasketRequestDto getSigningBasket(BasketDto basketDto);

    BasketResponseDto cancellSingningBasket(BasketDto basketDto);

}
