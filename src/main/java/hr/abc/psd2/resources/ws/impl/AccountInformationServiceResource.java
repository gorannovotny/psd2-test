package hr.abc.psd2.resources.ws.impl;

import static hr.abc.psd2.model.ConsentTypeAccess.BALANCE;
import static hr.abc.psd2.model.RequestType.ACCOUNT_ID_IN_PATH;
import static hr.abc.psd2.model.RequestType.PATH;
import static hr.abc.psd2.model.RequestType.PAYLOAD;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.ACCEPT;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.CONSENT_ID;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.PSU_IP_ADDRESS;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.TPP_ID;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.X_REQUEST_ID;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import hr.abc.psd2.bean.IAccountInformationBean;
import hr.abc.psd2.bean.IConsentAisBean;
import hr.abc.psd2.model.RequestType;
import hr.abc.psd2.model.dto.ais.AccountBalanceResponse;
import hr.abc.psd2.model.dto.ais.AccountResponseDto;
import hr.abc.psd2.model.dto.ais.AccountTransactionDetailsResponse;
import hr.abc.psd2.model.dto.ais.AccountTransactionDto;
import hr.abc.psd2.model.dto.ais.AccountTransactionReq;
import hr.abc.psd2.model.dto.ais.AccountTransactionsResponse;
import hr.abc.psd2.model.dto.ais.ConsentAccountAisDto;
import hr.abc.psd2.model.dto.ais.ConsentActionAisDto;
import hr.abc.psd2.model.dto.ais.ConsentAisDto;
import hr.abc.psd2.model.dto.ais.PrometStanjePartijeDto;
import hr.abc.psd2.model.dto.error.ErrorCode;
import hr.abc.psd2.resources.ws.IAccountInformationServiceResource;
import hr.abc.psd2.util.AisSpecificHttpErrorCodes;
import hr.abc.psd2.util.GenericBassxConstants;
import hr.abc.psd2.util.InternationalizationUtil;
import hr.abc.psd2.util.Psd2Util;
import lombok.extern.slf4j.Slf4j;

/**
 * Web service resource for Account Information Service .
 * <p>
 * Created by tkorpar on 19.09.2018..
 */
@Slf4j
public class AccountInformationServiceResource implements IAccountInformationServiceResource {

	@Inject
	private IConsentAisBean consentAisBean; // prazni

	@Inject
	private IAccountInformationBean accountInformationBean;

	/**
	 * summary: Read Account List
	 * description:
	 * Read the identifiers of the available payment account together with booking balance information, depending on the consent granted.
	 */
	public Response getAccountList(@Context UriInfo queryParam, ContainerRequestContext ctx, HttpHeaders headers) {
		Response response = null;
		//response object for body
		AccountResponseDto accountResponseDto = new AccountResponseDto();
		accountResponseDto.setIsValid(true);
		try {
			MultivaluedMap<String, String> requestHeaders = headers.getRequestHeaders();
			String xRequestId = requestHeaders.getFirst(X_REQUEST_ID.getValue());
			String consentId = requestHeaders.getFirst(CONSENT_ID.getValue());
			String tppId = requestHeaders.getFirst(TPP_ID.getValue());
			String IpAdressPsu = requestHeaders.getFirst(PSU_IP_ADDRESS.getValue());

			//validate query parameter - with balance
			String withBalanceString = queryParam.getQueryParameters().getFirst("withBalance");
			Boolean withBalance = false;
			if (StringUtils.isNotBlank(withBalanceString)) {
				if (withBalanceString.equals("true")) {
					withBalance = true;
				}
			} else {
				withBalance = false;
			}

			//ConsentAisDto consentAisDtoWs = consentAisBean.validConsent(consentId, tppId, IpAdressPsu); // stari
			ConsentAisDto consentAisDtoWs = consentAisBean.validateConsent(consentId, tppId, IpAdressPsu, PAYLOAD); // prazni

			//tu čemo sjebati resource id
			AccountResponseDto accountResponseWsDto = new AccountResponseDto();
			if (consentAisDtoWs.getIsValid()) {
				//accountResponseWsDto = accountInformationBean.formAccountListResponse(consentAisDtoWs, withBalance); // stari
				accountResponseWsDto = accountInformationBean.formAccountListResponse(consentAisDtoWs, withBalance); // prazni

				if (accountResponseWsDto.getIsValid()) {
					accountResponseWsDto.setIsValid(null);
					response = Response.status(Response.Status.OK).entity(accountResponseWsDto).build();
					//log consent usage action
					// consentAisBean.checkConsentAndLogAction(new ConsentActionAisDto("SUCESS", null, Integer.valueOf(consentId), tppId, xRequestId, IpAdressPsu)); // stari
					consentAisBean.checkConsentAndLogAction(new ConsentActionAisDto("SUCESS", null, Integer.valueOf(consentId), tppId, xRequestId, IpAdressPsu)); // prazni
				} else {
					if (accountResponseWsDto.getErrorInformationDto().getTppMessages() != null && !accountResponseWsDto.getErrorInformationDto().getTppMessages().isEmpty() &&
							accountResponseWsDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.RESOURCE_UNKNOWN.getValue())) {

						response = Response.status(Response.Status.BAD_REQUEST).entity(accountResponseWsDto.getErrorInformationDto()).build();
					} else {
						response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(accountResponseWsDto.getErrorInformationDto()).build();
					}
				}
			} else {
				if ((consentAisDtoWs.getErrorInformationDto().getTppMessages() != null && !consentAisDtoWs.getErrorInformationDto().getTppMessages().isEmpty() &&
						consentAisDtoWs.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.RESOURCE_EXPIRED.getValue()))) {
					response = Response.status(Response.Status.BAD_REQUEST).entity(consentAisDtoWs.getErrorInformationDto()).build();
				}

			}
		} catch (Exception e) {
			log.error("Exception is:", e);
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();
		}
		return response;
	}

	/**
	 * summary: Read Account Details
	 * description:
	 * accountId can be sifra partije or sifra partije + oznaka valute
	 * Reads details about an account, with balances where required.
	 * It is assumed that a consent of the PSU to this access is already given and stored on the ASPSP system.
	 */
	public Response readAccountDetails(@PathParam("accountId") String accountId, @Context UriInfo queryParam, ContainerRequestContext ctx, HttpHeaders headers) {
		Response response = null;
		//response object for body
		AccountResponseDto accountResponseDto = new AccountResponseDto();
		accountResponseDto.setIsValid(true);
		try {
			MultivaluedMap<String, String> requestHeaders = headers.getRequestHeaders();
			String xRequestId = requestHeaders.getFirst(X_REQUEST_ID.getValue());
			String consentId = requestHeaders.getFirst(CONSENT_ID.getValue());
			String tppId = requestHeaders.getFirst(TPP_ID.getValue());
			String IpAdressPsu = requestHeaders.getFirst(PSU_IP_ADDRESS.getValue());
			//path param

			Boolean withBalance = false;

			if (StringUtils.isNotBlank(accountId)) {
				//validate query parameter - with balance
				String withBalanceString = queryParam.getQueryParameters().getFirst("withBalance");
				if (StringUtils.isNotBlank(withBalanceString)) {
					if (withBalanceString.equals("true")) {
						withBalance = true;
					}
				} else {
					withBalance = false;
				}
				//validate consent
				// ConsentAisDto consentAisDtoWs = consentAisBean.validConsent(consentId, tppId, IpAdressPsu); // stari
				ConsentAisDto consentAisDtoWs = consentAisBean.validateConsent(consentId, tppId, IpAdressPsu, ACCOUNT_ID_IN_PATH); // prazni
				if (consentAisDtoWs.getIsValid()) {
					//AccountResponseDto accountResponseWsDto = accountInformationBean.formAccountDetailsResponse(accountId, consentAisDtoWs, withBalance); // stari
					AccountResponseDto accountResponseWsDto = accountInformationBean.formAccountDetailsResponse(accountId, consentAisDtoWs, withBalance); // prazni
					if (accountResponseWsDto.getIsValid()) {
						accountResponseWsDto.setIsValid(null);
						response = Response.status(Response.Status.OK).entity(accountResponseWsDto).build();
						//log consent usage action
						// consentAisBean.checkConsentAndLogAction(new ConsentActionAisDto("SUCESS", null, Integer.valueOf(consentId), tppId, xRequestId, IpAdressPsu)); // stari
						consentAisBean.checkConsentAndLogAction(new ConsentActionAisDto("SUCESS", null, Integer.valueOf(consentId), tppId, xRequestId, IpAdressPsu)); // prazni
					} else {
						//
						if (accountResponseWsDto.getErrorInformationDto().getTppMessages() != null && !accountResponseWsDto.getErrorInformationDto().getTppMessages().isEmpty() &&
								accountResponseWsDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals("RESOURCE_UNKNOWN")) {

							response = Response.status(Response.Status.BAD_REQUEST).entity(accountResponseWsDto.getErrorInformationDto()).build();
						} else {
							response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(accountResponseWsDto.getErrorInformationDto()).build();
						}
					}
				}
			} else {
				response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_account_id_mandatory", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
			}
		} catch (Exception e) {
			log.error("Exception is:", e);
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();
		}
		return response;
	}

	/**
	 * summary: Read Balance
	 * description:
	 * Reads account data from a given account addressed by "account-id".
	 **/
	public Response getBalances(@PathParam("resourceId") String resourceId, ContainerRequestContext ctx, HttpHeaders headers) {
		Response response = null;
		AccountBalanceResponse accountBalanceResponse = new AccountBalanceResponse();
		//accountBalanceResponse.setIsValid(true);
		try {
			MultivaluedMap<String, String> requestHeaders = headers.getRequestHeaders();
			String xRequestId = requestHeaders.getFirst(X_REQUEST_ID.getValue());
			String consentId = requestHeaders.getFirst(CONSENT_ID.getValue());
			String tppId = requestHeaders.getFirst(TPP_ID.getValue());
			String IpAdressPsu = requestHeaders.getFirst(PSU_IP_ADDRESS.getValue());

			//path param
			if (StringUtils.isNotBlank(resourceId)) {

				ConsentAisDto consentAisDtoWs = consentAisBean.validateConsent(consentId, tppId, IpAdressPsu, ACCOUNT_ID_IN_PATH);
				if (consentAisDtoWs.getIsValid()) {
					//validate - is account in consent and if have right type access?
					ConsentAccountAisDto accountAisDto = consentAisBean.checkConsentTypeForResourceId(consentAisDtoWs.getConsentAccountAisDtoList(), BALANCE.getValue(), resourceId, ACCOUNT_ID_IN_PATH);
					List<ConsentAccountAisDto> accountAisDtoList = new ArrayList<>();
					accountAisDtoList.add(accountAisDto);
					accountBalanceResponse = accountInformationBean.getAccountBalances(accountAisDtoList, resourceId);
					if (accountBalanceResponse.getIsValid()) {
						accountBalanceResponse.setIsValid(null);
						response = Response.status(Response.Status.OK).entity(accountBalanceResponse).build();
						//log consent usage action
						// consentAisBean.checkConsentAndLogAction(new ConsentActionAisDto("SUCESS", null, Integer.valueOf(consentId), tppId, xRequestId, IpAdressPsu)); // stari
						consentAisBean.checkConsentAndLogAction(new ConsentActionAisDto("SUCESS", null, Integer.valueOf(consentId), tppId, xRequestId, IpAdressPsu)); // prazni
					} else {
						if (accountBalanceResponse.getErrorInformationDto().getTppMessages() != null && !accountBalanceResponse.getErrorInformationDto().getTppMessages().isEmpty() &&
								accountBalanceResponse.getErrorInformationDto().getTppMessages().get(0).getCode().equals("BAD_REQUEST")) {
							response = Response.status(Response.Status.BAD_REQUEST).entity(accountBalanceResponse.getErrorInformationDto()).build();
						} else {
							response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(accountBalanceResponse.getErrorInformationDto()).build();
						}
					}
				}
			} else {
				response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_account_id_mandatory", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
			}
		} catch (Exception e) {
			log.error("Exception is:", e);
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();
		}
		return response;
	}

	/*
	*
	summary: Read Transaction List
	description:
	    Read transaction reports or transaction lists of a given account ddressed by "account-id", depending on the steering parameter
	    "bookingStatus" together with balances.
	*
	*/
	public Response getTransactionList(@PathParam("resourceId") String resourceId, @Context UriInfo queryParam, HttpHeaders headers) {
		Response response = null;
		//req object for body
		AccountTransactionReq transactionReq;
		//response object for body
		AccountTransactionsResponse accTransResponseDto = new AccountTransactionsResponse();
		accTransResponseDto.setValid(true);
		try {
			MultivaluedMap<String, String> requestHeaders = headers.getRequestHeaders();
			String xRequestId = requestHeaders.getFirst(X_REQUEST_ID.getValue());
			String consentId = requestHeaders.getFirst(CONSENT_ID.getValue());
			String tppId = requestHeaders.getFirst(TPP_ID.getValue());
			String IpAdressPsu = requestHeaders.getFirst(PSU_IP_ADDRESS.getValue());
			String acceptedFormat = requestHeaders.getFirst(ACCEPT.getValue());
			PrometStanjePartijeDto filterPartijeDto; // ovo je iz core-a

			//validate mandatory query params..
			//transactionReq = accountInformationBean.validateAisTransactionRequest(queryParam, resourceId); // stari
			transactionReq = accountInformationBean.validateAisTransactionRequest(queryParam, resourceId); // prazni
			//ConsentAisDto consentAisDtoWs = consentAisBean.validConsent(consentId, tppId, IpAdressPsu); // stari
			ConsentAisDto consentAisDtoWs = consentAisBean.validateConsent(consentId, tppId, IpAdressPsu, PATH); // prazni
			if (transactionReq.getValidReq()) {
				// filterPartijeDto = accountInformationBean.formFilterPartije(transactionReq); // stari
				filterPartijeDto = accountInformationBean.formFilterPartije(transactionReq); // prazni

				if (consentAisDtoWs.getIsValid()) {
					// accTransResponseDto = accountInformationBean.getTransactionList(acceptedFormat, consentAisDtoWs, filterPartijeDto, resourceId); // stari
					accTransResponseDto = accountInformationBean.getTransactionList(acceptedFormat, consentAisDtoWs, filterPartijeDto, resourceId); // prazni
					if (accTransResponseDto.getValid()) {
						if (StringUtils.equals(acceptedFormat, MediaType.APPLICATION_JSON) || StringUtils.isBlank(acceptedFormat)) {
							accTransResponseDto.setValid(null);
							response = Response.status(Response.Status.OK).entity(accTransResponseDto).build();
						} else {
							accTransResponseDto.setValid(null);
							response = Response.status(Response.Status.OK).entity(accTransResponseDto.getDocument()).type(MediaType.APPLICATION_XML).build();
						}
						//log consent usage action
						//consentAisBean.checkConsentAndLogAction(new ConsentActionAisDto("SUCESS", null, Integer.valueOf(consentId), tppId, xRequestId, IpAdressPsu)); // stari
						consentAisBean.checkConsentAndLogAction(new ConsentActionAisDto("SUCESS", null, Integer.valueOf(consentId), tppId, xRequestId, IpAdressPsu)); // prazni
					} else {
						if (StringUtils.equals(acceptedFormat, MediaType.APPLICATION_JSON) || StringUtils.isBlank(acceptedFormat)) {
							if (accTransResponseDto.getErrorInformationDto().getTppMessages() != null && !accTransResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
									accTransResponseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.RESOURCE_UNKNOWN.getValue())) {
								response = Response.status(Response.Status.NOT_FOUND).entity(accTransResponseDto.getErrorInformationDto()).build();
							} else if (accTransResponseDto.getErrorInformationDto().getTppMessages() != null && !accTransResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
									accTransResponseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.REQUESTED_FORMATS_INVALID.getValue())) {
								response = Response.status(Response.Status.NOT_ACCEPTABLE).entity(accTransResponseDto.getErrorInformationDto()).build();
							} else if (accTransResponseDto.getErrorInformationDto().getTppMessages() != null && !accTransResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
									accTransResponseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.AIS_CONSENT_INVALID.getValue())) {
								response = Response.status(Response.Status.BAD_REQUEST).entity(accTransResponseDto.getErrorInformationDto()).build();
							} else {
								response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(accTransResponseDto.getErrorInformationDto()).build();
							}
						} else if (StringUtils.equals(acceptedFormat, MediaType.APPLICATION_XML)) {
							if (accTransResponseDto.getErrorInformationDto().getTppMessages() != null && !accTransResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
									accTransResponseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.RESOURCE_UNKNOWN.getValue())) {
								accTransResponseDto.setErrorInformationXmlDto(Psd2Util.formErrorInformationXml(accTransResponseDto.getErrorInformationDto()));
								accTransResponseDto.setErrorInformationDto(null);
								response = Response.status(Response.Status.NOT_FOUND).entity(accTransResponseDto.getErrorInformationXmlDto()).type(MediaType.APPLICATION_XML).build();
							} else if (accTransResponseDto.getErrorInformationDto().getTppMessages() != null && !accTransResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
									accTransResponseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.REQUESTED_FORMATS_INVALID.getValue())) {
								accTransResponseDto.setErrorInformationXmlDto(Psd2Util.formErrorInformationXml(accTransResponseDto.getErrorInformationDto()));
								accTransResponseDto.setErrorInformationDto(null);
								response = Response.status(Response.Status.NOT_ACCEPTABLE).entity(accTransResponseDto.getErrorInformationXmlDto()).type(MediaType.APPLICATION_XML).build();
							} else if (accTransResponseDto.getErrorInformationDto().getTppMessages() != null && !accTransResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
									accTransResponseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.AIS_CONSENT_INVALID.getValue())) {
								accTransResponseDto.setErrorInformationXmlDto(Psd2Util.formErrorInformationXml(accTransResponseDto.getErrorInformationDto()));
								accTransResponseDto.setErrorInformationDto(null);
								response = Response.status(Response.Status.BAD_REQUEST).entity(accTransResponseDto.getErrorInformationXmlDto()).type(MediaType.APPLICATION_XML).build();
							} else {
								accTransResponseDto.setErrorInformationXmlDto(Psd2Util.formErrorInformationXml(accTransResponseDto.getErrorInformationDto()));
								accTransResponseDto.setErrorInformationDto(null);
								response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(accTransResponseDto.getErrorInformationXmlDto()).type(MediaType.APPLICATION_XML).build();
							}
						}
					}
				} else {
					if (StringUtils.equals(acceptedFormat, MediaType.APPLICATION_JSON) || StringUtils.isBlank(acceptedFormat)) {
						if (consentAisDtoWs.getErrorInformationDto().getTppMessages() != null && !consentAisDtoWs.getErrorInformationDto().getTppMessages().isEmpty() &&
								consentAisDtoWs.getErrorInformationDto().getTppMessages().get(0).getCode().equals("RESOURCE_UNKNOWN")) {
							response = Response.status(Response.Status.NOT_FOUND).entity(consentAisDtoWs.getErrorInformationDto()).build();
						} else if (consentAisDtoWs.getErrorInformationDto().getTppMessages() != null && !consentAisDtoWs.getErrorInformationDto().getTppMessages().isEmpty() &&
								consentAisDtoWs.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.FORMAT_ERROR.getValue())) {
							response = Response.status(Response.Status.BAD_REQUEST).entity(consentAisDtoWs.getErrorInformationDto()).build();
						} else if (consentAisDtoWs.getErrorInformationDto().getTppMessages() != null && !consentAisDtoWs.getErrorInformationDto().getTppMessages().isEmpty() &&
								consentAisDtoWs.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.RESOURCE_EXPIRED.getValue())) {
							response = Response.status(Response.Status.BAD_REQUEST).entity(consentAisDtoWs.getErrorInformationDto()).build();
						} else if (consentAisDtoWs.getErrorInformationDto().getTppMessages() != null && !consentAisDtoWs.getErrorInformationDto().getTppMessages().isEmpty() &&
								consentAisDtoWs.getErrorInformationDto().getTppMessages().get(0).getCode().equals("CONSENT_UNKNOWN")) {
							response = Response.status(Response.Status.FORBIDDEN).entity(consentAisDtoWs.getErrorInformationDto()).build();
						} else if ((consentAisDtoWs.getErrorInformationDto().getTppMessages() != null && !consentAisDtoWs.getErrorInformationDto().getTppMessages().isEmpty() &&
								consentAisDtoWs.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.CONSENT_EXPIRED.getValue()))) {
							response = Response.status(Response.Status.UNAUTHORIZED).entity(consentAisDtoWs.getErrorInformationDto()).build();
						} else if ((consentAisDtoWs.getErrorInformationDto().getTppMessages() != null && !consentAisDtoWs.getErrorInformationDto().getTppMessages().isEmpty() &&
								consentAisDtoWs.getErrorInformationDto().getTppMessages().get(0).getCode().equals("ACCESS_EXCEEDED"))) {
							response = Response.status(AisSpecificHttpErrorCodes.ACCESS_EXCEEDED).entity(consentAisDtoWs.getErrorInformationDto()).build();
						}
					} else if (StringUtils.equals(acceptedFormat, MediaType.APPLICATION_XML)) {
						if (consentAisDtoWs.getErrorInformationDto().getTppMessages() != null && !consentAisDtoWs.getErrorInformationDto().getTppMessages().isEmpty() &&
								consentAisDtoWs.getErrorInformationDto().getTppMessages().get(0).getCode().equals((ErrorCode.FORMAT_ERROR.getValue()))) {
							consentAisDtoWs.setErrorInformationXmlDto(Psd2Util.formErrorInformationXml(consentAisDtoWs.getErrorInformationDto()));
							consentAisDtoWs.setErrorInformationDto(null);
							response = Response.status(Response.Status.BAD_REQUEST).entity(consentAisDtoWs.getErrorInformationXmlDto()).type(MediaType.APPLICATION_XML).build();
						} else if (consentAisDtoWs.getErrorInformationDto().getTppMessages() != null && !consentAisDtoWs.getErrorInformationDto().getTppMessages().isEmpty() &&
								consentAisDtoWs.getErrorInformationDto().getTppMessages().get(0).getCode().equals((ErrorCode.RESOURCE_EXPIRED.getValue()))) {
							consentAisDtoWs.setErrorInformationXmlDto(Psd2Util.formErrorInformationXml(consentAisDtoWs.getErrorInformationDto()));
							consentAisDtoWs.setErrorInformationDto(null);
							response = Response.status(Response.Status.BAD_REQUEST).entity(consentAisDtoWs.getErrorInformationXmlDto()).type(MediaType.APPLICATION_XML).build();
						} else if (consentAisDtoWs.getErrorInformationDto().getTppMessages() != null && !consentAisDtoWs.getErrorInformationDto().getTppMessages().isEmpty() &&
								consentAisDtoWs.getErrorInformationDto().getTppMessages().get(0).getCode().equals("RESOURCE_UNKNOWN")) {
							consentAisDtoWs.setErrorInformationXmlDto(Psd2Util.formErrorInformationXml(consentAisDtoWs.getErrorInformationDto()));
							consentAisDtoWs.setErrorInformationDto(null);
							response = Response.status(Response.Status.NOT_FOUND).entity(consentAisDtoWs.getErrorInformationXmlDto()).type(MediaType.APPLICATION_XML).build();
						} else if (consentAisDtoWs.getErrorInformationDto().getTppMessages() != null && !consentAisDtoWs.getErrorInformationDto().getTppMessages().isEmpty() &&
								consentAisDtoWs.getErrorInformationDto().getTppMessages().get(0).getCode().equals("CONSENT_UNKNOWN")) {
							consentAisDtoWs.setErrorInformationXmlDto(Psd2Util.formErrorInformationXml(consentAisDtoWs.getErrorInformationDto()));
							consentAisDtoWs.setErrorInformationDto(null);
							response = Response.status(Response.Status.FORBIDDEN).entity(consentAisDtoWs.getErrorInformationXmlDto()).type(MediaType.APPLICATION_XML).build();
						} else if ((consentAisDtoWs.getErrorInformationDto().getTppMessages() != null && !consentAisDtoWs.getErrorInformationDto().getTppMessages().isEmpty() &&
								consentAisDtoWs.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.CONSENT_EXPIRED.getValue()))) {
							consentAisDtoWs.setErrorInformationXmlDto(Psd2Util.formErrorInformationXml(consentAisDtoWs.getErrorInformationDto()));
							consentAisDtoWs.setErrorInformationDto(null);
							response = Response.status(Response.Status.UNAUTHORIZED).entity(consentAisDtoWs.getErrorInformationXmlDto()).type(MediaType.APPLICATION_XML).build();
						} else if ((consentAisDtoWs.getErrorInformationDto().getTppMessages() != null && !consentAisDtoWs.getErrorInformationDto().getTppMessages().isEmpty() &&
								consentAisDtoWs.getErrorInformationDto().getTppMessages().get(0).getCode().equals("ACCESS_EXCEEDED"))) {
							consentAisDtoWs.setErrorInformationXmlDto(Psd2Util.formErrorInformationXml(consentAisDtoWs.getErrorInformationDto()));
							consentAisDtoWs.setErrorInformationDto(null);
							response = Response.status(AisSpecificHttpErrorCodes.ACCESS_EXCEEDED).entity(consentAisDtoWs.getErrorInformationXmlDto()).type(MediaType.APPLICATION_XML).build();
						}
					}
				}
			} else {
				if (StringUtils.equals(acceptedFormat, MediaType.APPLICATION_JSON) || StringUtils.isBlank(acceptedFormat)) {
					if ((transactionReq.getErrorInformationDto().getTppMessages() != null && !transactionReq.getErrorInformationDto().getTppMessages().isEmpty() &&
							transactionReq.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.FORMAT_ERROR.getValue()))) {
						response = Response.status(Response.Status.BAD_REQUEST).entity(transactionReq.getErrorInformationDto()).build();
					} else if ((transactionReq.getErrorInformationDto().getTppMessages() != null && !transactionReq.getErrorInformationDto().getTppMessages().isEmpty() &&
							transactionReq.getErrorInformationDto().getTppMessages().get(0).getCode().equals("RESOURCE_UNKNOWN"))) {
						response = Response.status(Response.Status.NOT_FOUND).entity(transactionReq.getErrorInformationDto()).build();
					} else {
						response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();

					}
				} else if (StringUtils.equals(acceptedFormat, MediaType.APPLICATION_XML)) {
					if ((transactionReq.getErrorInformationDto().getTppMessages() != null && !transactionReq.getErrorInformationDto().getTppMessages().isEmpty() &&
							transactionReq.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.FORMAT_ERROR.getValue()))) {
						transactionReq.setErrorInformationXmlDto(Psd2Util.formErrorInformationXml(transactionReq.getErrorInformationDto()));
						transactionReq.setErrorInformationDto(null);
						response = Response.status(Response.Status.BAD_REQUEST).entity(transactionReq.getErrorInformationXmlDto()).type(MediaType.APPLICATION_XML).build();
					} else if ((transactionReq.getErrorInformationDto().getTppMessages() != null && !transactionReq.getErrorInformationDto().getTppMessages().isEmpty() &&
							transactionReq.getErrorInformationDto().getTppMessages().get(0).getCode().equals("RESOURCE_UNKNOWN"))) {
						transactionReq.setErrorInformationXmlDto(Psd2Util.formErrorInformationXml(transactionReq.getErrorInformationDto()));
						transactionReq.setErrorInformationDto(null);
						response = Response.status(Response.Status.NOT_FOUND).entity(transactionReq.getErrorInformationXmlDto()).type(MediaType.APPLICATION_XML).build();
					} else {
						response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationXmlDto("INTERNAL_SERVER_ERROR", new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();

					}
				}
			}
		} catch (Exception e) {
			log.error("Exception is:", e);
			if (StringUtils.equals(headers.getRequestHeaders().getFirst(ACCEPT.getValue()), MediaType.APPLICATION_JSON) || StringUtils.isBlank(headers.getRequestHeaders().getFirst(ACCEPT.getValue()))) {
				response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();
			} else if (StringUtils.equals(headers.getRequestHeaders().getFirst(ACCEPT.getValue()), MediaType.APPLICATION_XML)) {
				response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationXmlDto("INTERNAL_SERVER_ERROR", new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();
			}
		}
		return response;
	}

	/*
	*
	summary: Read Transaction Details
	description:
	    Reads transaction details from a given transaction addressed by "resourceId" on a given account addressed by "account-id".
	    This call is only available on transactions as reported in a JSON format.
	*
	*/
	public Response getTransactionDetails(@PathParam("accountId") String accountId, @PathParam("resourceId") String resourceId, ContainerRequestContext ctx) {
		Response response = null;
		//request object
		AccountTransactionReq transactionReq;

		//response object for body
		AccountTransactionDetailsResponse transactionDetailsResponse = new AccountTransactionDetailsResponse();

		//temp object for storing all transactions
		AccountTransactionsResponse accTransResponseDto = new AccountTransactionsResponse();
		accTransResponseDto.setValid(true);

		try {
			MultivaluedMap<String, String> requestHeaders = ctx.getHeaders();
			String xRequestId = requestHeaders.getFirst(X_REQUEST_ID.getValue());
			String consentId = requestHeaders.getFirst(CONSENT_ID.getValue());
			String tppId = requestHeaders.getFirst(TPP_ID.getValue());
			String IpAdressPsu = requestHeaders.getFirst(PSU_IP_ADDRESS.getValue());
			String acceptedFormat = requestHeaders.getFirst(ACCEPT.getValue());
			PrometStanjePartijeDto filterPartijeDto = new PrometStanjePartijeDto();

			//validate filter partije
			if (StringUtils.isNotBlank(accountId) && accountId.length() > 3 && StringUtils.isNotBlank(resourceId)) {
				filterPartijeDto.setSifraPartije(Integer.parseInt(accountId.substring(0, accountId.length() - 3)));
				filterPartijeDto.setDatumPocetka((Date.from(LocalDate.now().minusYears(2).atStartOfDay(ZoneId.systemDefault()).toInstant())));
				filterPartijeDto.setDatumKraja(new Date());

				//validate consent
				// ConsentAisDto consentAisDtoWs = consentAisBean.validConsent(consentId, tppId, IpAdressPsu); // stari
				ConsentAisDto consentAisDtoWs = consentAisBean.validateConsent(consentId, tppId, IpAdressPsu, ACCOUNT_ID_IN_PATH); // prazni
				if (consentAisDtoWs.getIsValid()) {
					//get transaction list
					// accTransResponseDto = accountInformationBean.getTransactionList(acceptedFormat, consentAisDtoWs, filterPartijeDto, accountId); // stari
					accTransResponseDto = accountInformationBean.getTransactionList(acceptedFormat, consentAisDtoWs, filterPartijeDto, accountId); // prazni
					if (accTransResponseDto.getValid()) {
						accTransResponseDto.setValid(null);

						List<AccountTransactionDto> transactionList = accTransResponseDto.getTransactions().getBooked();
						for (AccountTransactionDto transactionObj : transactionList) {
							if (StringUtils.equals(transactionObj.getTransactionId(), resourceId)) {
								transactionDetailsResponse.setTransactionsDetails(transactionObj);
							}
						}
						response = Response.status(Response.Status.OK).entity(transactionDetailsResponse).build();
						//log consent usage action
						// consentAisBean.checkConsentAndLogAction(new ConsentActionAisDto("SUCESS", null, Integer.valueOf(consentId), tppId, xRequestId, IpAdressPsu));
						consentAisBean.checkConsentAndLogAction(new ConsentActionAisDto("SUCESS", null, Integer.valueOf(consentId), tppId, xRequestId, IpAdressPsu));
					} else {
						if (accTransResponseDto.getErrorInformationDto().getTppMessages() != null && !accTransResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
								accTransResponseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.RESOURCE_UNKNOWN.getValue())) {
							response = Response.status(Response.Status.NOT_FOUND).entity(accTransResponseDto.getErrorInformationDto()).build();
						} else if (accTransResponseDto.getErrorInformationDto().getTppMessages() != null && !accTransResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
								accTransResponseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.REQUESTED_FORMATS_INVALID.getValue())) {
							response = Response.status(Response.Status.NOT_ACCEPTABLE).entity(accTransResponseDto.getErrorInformationDto()).build();
						} else {
							response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(accTransResponseDto.getErrorInformationDto()).build();
						}
					}
				}

			} else {
				if (!StringUtils.isNotBlank(accountId) || !StringUtils.isNotBlank(resourceId)) {
					response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unknown_account", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
				}

			}

		} catch (NumberFormatException nfe) {
			response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unknown_account", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
		} catch (Exception e) {
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();
		}
		return response;

	}

}
