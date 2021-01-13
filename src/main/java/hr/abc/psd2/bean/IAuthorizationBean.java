package hr.abc.psd2.bean;

import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.model.dto.ais.ConsentAisDto;
import hr.abc.psd2.model.dto.authorization.AuthorizationDto;
import hr.abc.psd2.model.dto.authorization.AuthorizationListResponseDto;
import hr.abc.psd2.model.dto.authorization.CreateAuthorizationRequestDto;
import hr.abc.psd2.model.dto.authorization.ScaStatusResponseDto;
import hr.abc.psd2.model.dto.authorization.StartAuthorizationResponseDto;
import hr.abc.psd2.model.dto.pis.SinglePaymentDto;
import hr.abc.psd2.model.dto.pis.TppNalogPlatniDto;

public interface IAuthorizationBean {

    StartAuthorizationResponseDto startAuthorisation(CreateAuthorizationRequestDto authorizationRequestDto);

    AuthorizationListResponseDto getBulkAuthorizationSubResources(Integer objIdAuth, String paymentProd, String tppId, Integer authorizationType);

    ScaStatusResponseDto getScaStatus(String objIdAuth, Integer objTypeAuth, String authId, String paymentSrvTpp, String paymentProduct, String tppId, Integer authorizationType);

    AuthorizationListResponseDto getAuthorizationSubResources(String objIdAuth, Integer objTypeAuth, String paymentSrvTpp, String paymentProd, String tppId, Integer authorizationType);

    AuthorizationDto getPaymentAuthorization(Integer objTypeAuth, Integer idAuth, Integer objIdAuth, String paymentSerTpp, String paymentPrdTpp);

    AuthorizationDto getConsentAuthorization(Integer objecIdAuth, Integer objTypeAuth, Integer idAuth);

    AuthorizationListResponseDto getListOfAuthIds(String objectIdAuth, Integer objTypeAuth, String paymentSrv, String paymentProduct, String tppId, Integer authotizationType);

    AuthorizationDto createAuthorization(AuthorizationDto authorizationDto) throws AbitRuleException;

    StartAuthorizationResponseDto validateBeforeCreateAutorizationConsent(CreateAuthorizationRequestDto authorizationRequestDto, ConsentAisDto consentAisDto);

    StartAuthorizationResponseDto validateBeforeCreateAutorizationNalogCreate(CreateAuthorizationRequestDto authorizationRequestDto, TppNalogPlatniDto tppNalogPlatniDto);

    StartAuthorizationResponseDto validateBeforeCreateAutorizationNalogCancel(CreateAuthorizationRequestDto authorizationRequestDto, TppNalogPlatniDto tppNalogPlatniDto);

}
