package hr.abc.psd2.bean.impl;

import static hr.abc.psd2.model.ConsentStatus.RECEIVED;
import static hr.abc.psd2.model.ConsentStatus.TERMINATED_BY_TPP;
import static hr.abc.psd2.model.ConsentTypeAccess.FUNDS;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import hr.abc.psd2.bean.impl.paba.ConsentAisBeanPaba;
import hr.abc.psd2.service2.ICoreSrvc;
import org.apache.commons.lang3.StringUtils;

import hr.abc.psd2.bean.IAuthorizationBean;
import hr.abc.psd2.bean.IConsentCofBean;
import hr.abc.psd2.dao.ConsentCofDao;
import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.model.ConsentStatus;
import hr.abc.psd2.model.ConsentTypeAccess;
import hr.abc.psd2.model.dto.ConsentCofRequestResponseDto;
import hr.abc.psd2.model.dto.ais.AccountDto;
import hr.abc.psd2.model.dto.authorization.AuthorizationDto;
import hr.abc.psd2.model.dto.authorization.ScaStatus;
import hr.abc.psd2.model.dto.cof.ConsentAccountCofDto;
import hr.abc.psd2.model.dto.cof.ConsentCofDto;
import hr.abc.psd2.model.dto.core.PartijaDto;
import hr.abc.psd2.model.dto.error.ErrorCode;
import hr.abc.psd2.util.GenericBassxConstants;
import hr.abc.psd2.util.InternationalizationUtil;
import hr.abc.psd2.util.Psd2Constants;
import hr.abc.psd2.util.Psd2Util;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Slf4j
public abstract class AbstractConsentCofBean implements IConsentCofBean , Serializable {

    private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(AbstractConsentCofBean.class);

	@Inject
	protected ConsentCofDao consentCofDao;

	@Inject
	protected IAuthorizationBean authorizationBean;

	@Inject
	private ICoreSrvc coreSrvc;

	protected abstract PartijaDto getPartijaDtoByBrojPartije(String partija);

	@Override
	public ConsentCofRequestResponseDto createConsentCof(ConsentCofRequestResponseDto consentCofRequestResponseDto) throws AbitRuleException {
		ConsentCofRequestResponseDto consentCofRequestResponseDtorRsult = new ConsentCofRequestResponseDto();
		Boolean insertInDb = true;
		try {
			if (consentCofDao.validateAisConsentRequestAccount(consentCofRequestResponseDto.getAccount().getIban())) {
				ConsentCofDto consentCofDto = formConsentCofDtoFromRequest(consentCofRequestResponseDto);
				consentCofDto = consentCofDao.createConnsent(consentCofDto);
				if (consentCofDto.getSifra() != null) {
					consentCofRequestResponseDtorRsult.setConsentId(consentCofDto.getSifra().toString());
					consentCofDao.insertConsentAccountCof(consentCofDto);
					AuthorizationDto authorizationDto = null;
					authorizationDto = authorizationBean.createAuthorization(new AuthorizationDto(ScaStatus.SCAMETHODSELECTED.getValue(), Psd2Constants.ScaMethod.REDIRECT_IMPLICT.toString(), consentCofDto.getSifra().toString(), Psd2Constants.AuthorizationObjectType.CONSENT_COF_AUTHORUZATION.getValue(), Psd2Constants.AuthorizationType.CREATED.getValue(), consentCofDto.getTppIdCon(), null,
							consentCofDto.getxRequestIdCon(), Psd2Util.formAutorizationScaRedirectLink(consentCofDto.getSifra().toString())));
					consentCofRequestResponseDtorRsult.set_links(Psd2Util.formLinksConsentCof(authorizationDto, consentCofDto));

					consentCofRequestResponseDtorRsult.setConsentStatus(consentCofDto.getStateCon());
					consentCofRequestResponseDtorRsult.setValid(true);
				} else {
					consentCofRequestResponseDtorRsult.setValid(false);
					consentCofRequestResponseDtorRsult.setErrorInformationDto(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<String>(Arrays.asList("Internal server error"))));
				}
			} else {
				consentCofRequestResponseDtorRsult.setValid(false);
				consentCofRequestResponseDtorRsult.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_psp_ic_iban_error", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));

			}

		} catch (Exception e) {
			consentCofRequestResponseDtorRsult.setValid(false);
			consentCofRequestResponseDtorRsult.setErrorInformationDto(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<String>(Arrays.asList("Internal server error"))));
			e.printStackTrace();
		}
		return consentCofRequestResponseDtorRsult;
	}

	private ConsentCofDto formConsentCofDtoFromRequest(ConsentCofRequestResponseDto consentCofRequestResponseDto) throws AbitRuleException {
		ConsentCofDto consentCofDto = new ConsentCofDto();
		//consent
		consentCofDto.setPsuIdCon(StringUtils.isNoneBlank(consentCofRequestResponseDto.getPsuID()) ? consentCofRequestResponseDto.getPsuID() : null);
		consentCofDto.setStateCon(RECEIVED.getValue());
		consentCofDto.setTppIdCon(consentCofRequestResponseDto.getTppID());
		consentCofDto.setxRequestIdCon(consentCofRequestResponseDto.getxRequestID());
		consentCofDto.setTppRedirectUri(consentCofRequestResponseDto.getTppRedirectUri());
		consentCofDto.setTppName(consentCofRequestResponseDto.getTppName());

		//acess
		ConsentAccountCofDto accountCofDto = new ConsentAccountCofDto();
		//iban
		accountCofDto.setIbanAcc(consentCofRequestResponseDto.getAccount().getIban());
		//partija
		// PartijaDto partijaDto = LookupHelper.getICoreDaoProxyRemote().getPartijaDtoByBrojPartije(accountCofDto.getIbanAcc().substring(accountCofDto.getIbanAcc().length() - 10)); // stari
//		PartijaDto partijaDto = getPartijaDtoByBrojPartije(accountCofDto.getIbanAcc().substring(accountCofDto.getIbanAcc().length() - 10)); // prazni

		//mock
		accountCofDto.setSifraPar(1);
		//type
		accountCofDto.setTypeAcc(FUNDS.getValue());
		consentCofDto.setConsentAccountCofDto(accountCofDto);

		consentCofDto.setSifraKom(Integer.valueOf(coreSrvc.getCustomerIdByIban(consentCofRequestResponseDto.getAccount().getIban())));
		return consentCofDto;
	}

	public ConsentCofRequestResponseDto deleteConsent(String consentId, String tppId) {
		ConsentCofRequestResponseDto consentCofRequestResponseDto = null;
		try {
			if (StringUtils.isNotBlank(consentId)) {
				consentCofRequestResponseDto = consentCofDao.updateConsentCofStatus(Integer.valueOf(consentId), TERMINATED_BY_TPP.getValue(), tppId);
			}
		} catch (NumberFormatException nfe) {
			consentCofRequestResponseDto = new ConsentCofRequestResponseDto();
			consentCofRequestResponseDto.setValid(false);
			consentCofRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unknown_consent", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
		} catch (Exception e) {
			log.error("Exception is:", e);
			consentCofRequestResponseDto = new ConsentCofRequestResponseDto();
			consentCofRequestResponseDto.setValid(false);
			consentCofRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<String>(Arrays.asList("Internal server error"))));
		}
		return consentCofRequestResponseDto;
	}
	

    public ConsentCofRequestResponseDto getConsentInformation(String consentId, String tppId) {
        ConsentCofRequestResponseDto consentCofRequestResponseDto = null;
        ConsentCofDto resultDto = null;
        try {
            if (StringUtils.isNotBlank(consentId) && StringUtils.isNotBlank(tppId)) {
                resultDto = consentCofDao.getConsentCofBySifra(Integer.valueOf(consentId));
                if (resultDto != null) {
                    if (StringUtils.equals(resultDto.getTppIdCon(), tppId)) {
                        consentCofRequestResponseDto = new ConsentCofRequestResponseDto();
                        AccountDto cofAccount = new AccountDto();
                        cofAccount.setIban(resultDto.getConsentAccountCofDto().getIbanAcc());
                        consentCofRequestResponseDto.setConsentStatus(resultDto.getStateCon());
                        consentCofRequestResponseDto.setAccount(cofAccount);
                        consentCofRequestResponseDto.setValid(true);
                    } else {
                        consentCofRequestResponseDto.setValid(false);
                        consentCofRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unknown_consent", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                    }
                } else {
                    consentCofRequestResponseDto = new ConsentCofRequestResponseDto();
                    consentCofRequestResponseDto.setValid(false);
                    consentCofRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unknown_consent", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
                }
            } else {
                consentCofRequestResponseDto = new ConsentCofRequestResponseDto();
                consentCofRequestResponseDto.setValid(false);
                consentCofRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_consent_tpp_mandatory", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
            }
        } catch (NumberFormatException nfe) {
            consentCofRequestResponseDto = new ConsentCofRequestResponseDto();
            consentCofRequestResponseDto.setValid(false);
            consentCofRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<String>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unknown_consent", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
        } catch (Exception e) {
            log.error("Exception is:", e);
            consentCofRequestResponseDto = new ConsentCofRequestResponseDto();
            consentCofRequestResponseDto.setValid(false);
            consentCofRequestResponseDto.setErrorInformationDto(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<String>(Arrays.asList("Internal server error"))));
        }
        return consentCofRequestResponseDto;
    }
}
