package hr.abc.psd2.bean.impl;

import hr.abc.psd2.bean.IAuthorizationBean;
import hr.abc.psd2.bean.IConsentAisBean;
import hr.abc.psd2.dao.ConsentActionAisDao;
import hr.abc.psd2.dao.ConsentAisDao;
import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.exception.ConsentAISException;
import hr.abc.psd2.model.RequestType;
import hr.abc.psd2.model.dto.ConsentRequestResponseDto;
import hr.abc.psd2.model.dto.ais.ConsentAccountAisDto;
import hr.abc.psd2.model.dto.ais.ConsentActionAisDto;
import hr.abc.psd2.model.dto.ais.ConsentAisDto;
import hr.abc.psd2.model.dto.authorization.AuthorizationDto;
import hr.abc.psd2.model.dto.authorization.ScaStatus;
import hr.abc.psd2.model.dto.error.ErrorCode;
import hr.abc.psd2.util.GenericBassxConstants;
import hr.abc.psd2.util.InternationalizationUtil;
import hr.abc.psd2.util.Psd2Constants;
import hr.abc.psd2.util.Psd2Util;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

import static hr.abc.psd2.model.ConsentStatus.EXPIRED;
import static hr.abc.psd2.model.ConsentStatus.TERMINATED_BY_TPP;
import static hr.abc.psd2.model.dto.error.ErrorCode.INTERNAL_SERVER_ERROR;
import static hr.abc.psd2.model.dto.error.ErrorCode.RESOURCE_UNKNOWN;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Slf4j
public abstract class AbstractConsentAisBean implements IConsentAisBean, Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	ConsentActionAisDao actionAisDao;

	@Inject
	protected IAuthorizationBean authorizationBean;

	@Inject
	ConsentAisDao consentDao;

	@Override
	public ConsentAisDto validateConsent(Integer consentID, String requestTpp, RequestType reqType) throws AbitRuleException, ConsentAISException {
		try {
			return validateConsent(consentID, requestTpp, null, reqType);
		} catch (Exception e) {
			throw new ConsentAISException.Builder().withCode(INTERNAL_SERVER_ERROR)
					.withThrowable(e)
					.withErrors("Internal server error")
					.build();
		}
	}

	@Override
	public ConsentAisDto validateConsent(String consentId, String tppId, String ipAdressPsu, RequestType requestType) throws ConsentAISException {
		ConsentAisDto consentAisDtoResult = null;
		try {
			consentAisDtoResult = validateConsent(Integer.valueOf(consentId), tppId, ipAdressPsu, requestType);
		} catch (NumberFormatException nfe) {
			throw new ConsentAISException.Builder().withCode(RESOURCE_UNKNOWN)
					.withThrowable(nfe)
					.withErrors("psd2_unknown_consent")
					.build();
		} catch (Exception e) {
			throw new ConsentAISException.Builder().withCode(INTERNAL_SERVER_ERROR)
					.withThrowable(e)
					.withErrors("Internal server error")
					.build();
		}

		return consentAisDtoResult;
	}

	@Override
	public void checkConsentAndLogAction(ConsentActionAisDto consentActionDto) throws AbitRuleException {
		try {
			if (consentActionDto != null && consentActionDto.getSiconAct() != null && !StringUtils.isBlank(consentActionDto.getTppidAct()) &&
					!StringUtils.isBlank(consentActionDto.getTppidAct())) {
				//get consent from db
				ConsentAisDto consentAisDto = consentDao.getConsentAisBySifra(consentActionDto.getSiconAct());
				if (consentActionDto != null) {
					//check exipiration - if for update? -->provjera da li je todat_con (datum do kad consent vrijedi) istekao a status nije "EXPIRED" - u to slučaju trebamo promijeniti status
					if (consentDao.checkOnExperation(consentAisDto, consentActionDto.getTppidAct())) {
						consentAisDto.setStateCon(EXPIRED.toString());
						consentAisDto.setLastActionDate(LocalDate.now());
						consentDao.edit(consentAisDto);
					}
					if (consentAisDto.getLastActionDate().compareTo(LocalDate.now()) == 0) {
						if (consentAisDto.getTppUsageCounterCon() > 0) {
							if (StringUtils.isBlank(consentActionDto.getIpAdressPsu())) {
								consentAisDto.setTppUsageCounterCon(consentAisDto.getTppUsageCounterCon() - 1);
							}
							consentAisDto.setLastActionDate(LocalDate.now());
							consentDao.edit(consentAisDto);
						}
						actionAisDao.logConsentActionAis(consentActionDto);
					} else {
						consentAisDto.setTppUsageCounterCon(consentAisDto.getFrequencyPerDayCon() - 1);
						consentAisDto.setLastActionDate(LocalDate.now());
						consentDao.edit(consentAisDto);
						actionAisDao.logConsentActionAis(consentActionDto);
					}
				}
			}
		} catch (AbitRuleException e) {
			log.error("Exception is:", e);
		}
	}

	@Override
	public ConsentRequestResponseDto createConsentAis(ConsentRequestResponseDto consentRequestResponseDto) throws AbitRuleException {
		ConsentRequestResponseDto consentRequestResponseDtoValidated = null;
		try {
			//validacija
			consentRequestResponseDtoValidated = consentDao.validateCreateConsentRequestBody(consentRequestResponseDto);
			if (consentRequestResponseDtoValidated.getIsValid()) {
				//form object for data base
				ConsentAisDto consentDto = consentDao.formConsentAisDtoFromRequest(consentRequestResponseDtoValidated);
				if (consentDto.getIsValid()) {
					//create consent
					consentDto = consentDao.createConsent(consentDto);
					//set consent ID
					consentRequestResponseDtoValidated.setConsentId(consentDto.getSifra().toString());
					//create consent accesses
					consentDao.insertConsentAccountList(consentDto);
					consentRequestResponseDtoValidated.setConsentStatus(consentDto.getStateCon());
					//getBrojPotpisnika
					Map<Integer, Integer> mapaPartija = new HashMap<>();
					for (ConsentAccountAisDto aisDto : consentDto.getConsentAccountAisDtoList()) {
						mapaPartija.put(aisDto.getSifraPar(), aisDto.getSifraPar());
					}
					List<Integer> listaPartija = new ArrayList(mapaPartija.values());
					//Integer brojPotpisnika = psd2UtilDao.getMaxBrojIbPotpisnika(listaPartija, null);  //UVIJEK IMPLICIT SCA
					AuthorizationDto authorizationDto = null;
					//if (brojPotpisnika == null || brojPotpisnika < 2) {
					//create authorization --> implicit sca
					authorizationDto = authorizationBean.createAuthorization(new AuthorizationDto(ScaStatus.SCAMETHODSELECTED.getValue(), Psd2Constants.ScaMethod.REDIRECT_IMPLICT.toString(), consentDto.getSifra().toString(), Psd2Constants.AuthorizationObjectType.CONSENT_AUTHORIZATION.getValue(), Psd2Constants.AuthorizationType.CREATED.getValue(), consentDto.getTppIdCon(), null,
							consentDto.getxRequestIdCon(), Psd2Util.formAutorizationScaRedirectLink(consentDto.getSifra().toString())));
					//}
					consentRequestResponseDtoValidated.set_links(Psd2Util.formLinksConsent(authorizationDto, consentDto));

				} else {
					consentRequestResponseDtoValidated.setIsValid(false);
					consentRequestResponseDtoValidated.setErrorInformationDto(consentDto.getErrorInformationDto());
				}
			}
		} catch (Exception e) {
			log.error("Exception is:", e);
			consentRequestResponseDtoValidated = new ConsentRequestResponseDto();
			consentRequestResponseDtoValidated.setIsValid(false);
			consentRequestResponseDtoValidated.setErrorInformationDto(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<String>(Arrays.asList("Internal server error"))));
		}
		return consentRequestResponseDtoValidated;
	}

	@Override
	public ConsentRequestResponseDto getConsentStatus(String consentId, String tppId) {
		ConsentRequestResponseDto consentRequestResponseDtoResult = null;
		try {
			consentRequestResponseDtoResult = getConsentStatusBySifra(Integer.valueOf(consentId), tppId);
		} catch (NumberFormatException nfe) {
			consentRequestResponseDtoResult = new ConsentRequestResponseDto();
			consentRequestResponseDtoResult.setIsValid(false);
			consentRequestResponseDtoResult.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unknown_consent", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
		} catch (Exception e) {
			log.error("Exception is:", e);
			consentRequestResponseDtoResult.setIsValid(false);
			consentRequestResponseDtoResult.setErrorInformationDto(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<String>(Arrays.asList("Internal server error"))));
		}
		return consentRequestResponseDtoResult;
	}

	@Override
	public ConsentRequestResponseDto getConsentInformation(String consentId, String tppId) {
		ConsentRequestResponseDto consentRequestResponseDtoResult = null;
		try {
			consentRequestResponseDtoResult = consentDao.getConsentReqestBySifra(Integer.valueOf(consentId), tppId);
		} catch (NumberFormatException nfe) {
			consentRequestResponseDtoResult = new ConsentRequestResponseDto();
			consentRequestResponseDtoResult.setIsValid(false);
			consentRequestResponseDtoResult.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unknown_consent", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
		} catch (Exception e) {
			log.error("Exception is:", e);
			consentRequestResponseDtoResult = new ConsentRequestResponseDto();
			consentRequestResponseDtoResult.setIsValid(false);
			consentRequestResponseDtoResult.setErrorInformationDto(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<String>(Arrays.asList("Internal server error"))));
		}
		return consentRequestResponseDtoResult;
	}

	@Override
	public ConsentRequestResponseDto deleteConsent(String consentId, String tppId) throws AbitRuleException {
		ConsentRequestResponseDto consentRequestResponseDtoResult = null;
		try {
			if (StringUtils.isNotBlank(consentId)) {
				consentRequestResponseDtoResult = consentDao.updateConsentStatus(Integer.valueOf(consentId), TERMINATED_BY_TPP.getValue(), tppId);
			}
		} catch (NumberFormatException nfe) {
			consentRequestResponseDtoResult = new ConsentRequestResponseDto();
			consentRequestResponseDtoResult.setIsValid(false);
			consentRequestResponseDtoResult.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unknown_consent", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
		} catch (Exception e) {
			log.error("Exception is:", e);
			consentRequestResponseDtoResult = new ConsentRequestResponseDto();
			consentRequestResponseDtoResult.setIsValid(false);
			consentRequestResponseDtoResult.setErrorInformationDto(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<String>(Arrays.asList("Internal server error"))));
		}
		return consentRequestResponseDtoResult;
	}

	@Override
	public ConsentRequestResponseDto getConsentRequestBySifra(Integer sifraConsenta, String tppId) throws AbitRuleException {
		return consentDao.getConsentReqestBySifra(sifraConsenta, tppId);
	}

	@Override
	public List<ConsentAisDto> getConsentAisDtoListByKom(Integer sifraKom) throws AbitRuleException {

		return consentDao.getConsentAisDtoListByKom(sifraKom);
	}

	@Override
	public ConsentRequestResponseDto getConsentStatusBySifra(Integer sifraConsenta, String tppId) throws AbitRuleException {
		return consentDao.getConsentStatusBySifra(sifraConsenta, tppId);
	}

	@Override
	public ConsentAisDto getConsentAisBySifra(Integer sifraConsenta) throws AbitRuleException {
		return consentDao.getConsentAisBySifra(sifraConsenta);
	}
}
