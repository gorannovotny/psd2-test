package hr.abc.psd2.util;
import static hr.abc.psd2.model.MessageType.REQUEST;
import static hr.abc.psd2.model.MessageType.RESPONSE;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.PSU_ID;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.PSU_IP_ADDRESS;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.TPP_NAME;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.TPP_REDIRECT_URI;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.TPP_ROLE;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.X_FORWARDED_FOR;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.X_REQUEST_ID;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.WriterInterceptorContext;

import org.apache.commons.lang3.StringUtils;

import hr.abc.psd2.dao.Psd2MessageLogDao;
import hr.abc.psd2.model.MessageType;
import hr.abc.psd2.model.dto.CertificateInfoDto;
import hr.abc.psd2.model.dto.Psd2Xs2aLogDto;
import lombok.extern.slf4j.Slf4j;

//import org.elasticsearch.common.xcontent.XContentBuilder;
//import org.elasticsearch.common.xcontent.XContentFactory;

@Slf4j
public class ElasticUtil {

    private
    @Inject
    Psd2MessageLogDao psd2MessageLogDao;

    // constants
    public static final String ES_MESSAGE_TYPE_INFO = "info";
    public static final String ES_MESSAGE_TYPE_WARNING = "warning";
    public static final String ES_MESSAGE_TYPE_ERROR = "error";
    public static final String DEFAULT_DATE_FORMAT = "dd.MM.yyyy";
    public static final String DEFAULT_DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm:ss";
    public static final String LOG_DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm:ss_SSS";


    public static String logMessage(MessageType messageType, String messageName, HttpServletRequest request, EntityManager entityManager, String tppMessage, WriterInterceptorContext responseContext, CertificateInfoDto certificateInfoDto, HttpServletResponse httpServletResponse) {
        String logMessageString = null;
        Psd2Xs2aLogDto logDto = null;
        try {
            String interfaceEnvironment = Psd2Util_origin.getInterfaceEnvironment(entityManager);
            if (messageType.equals(REQUEST)) {
                logDto = formRequestLogDto(messageName, request, interfaceEnvironment, tppMessage, certificateInfoDto);
                logMessageString = addRequestMessageInfoToFileLog(logDto, request);
            } else {
                logDto = formResponseFilterLogDto2(messageName, request, interfaceEnvironment, httpServletResponse, responseContext);
                logMessageString = addResponseMessageInfoToFileLog2(logDto, responseContext);
            }
        } catch (Exception e) {
            log.error("Exception is:", e);
        }
        return logMessageString;
    }

    private static Psd2Xs2aLogDto formRequestLogDto(String messageName, HttpServletRequest request, String interfaceEnvironment, String tppMessage, CertificateInfoDto certificateInfoDto) {
        Psd2Xs2aLogDto logDto = new Psd2Xs2aLogDto();
        Calendar time = GregorianCalendar.getInstance();
        logDto.setDateInString(getDateAsString(time.getTime(), LOG_DATE_TIME_FORMAT));
        logDto.setTime(time.getTime());
        logDto.setMessageType(REQUEST.toString());
        logDto.setMessageName(messageName);
        logDto.setInterfaceName(interfaceEnvironment);
        logDto.setSessionId(request.getSession().getId());
        logDto.setApiCall(request.getMethod() + " - " + request.getRequestURL());
        logDto.setxRequestId(request.getHeader(X_REQUEST_ID.getValue()));
        if (certificateInfoDto != null) {
            if (certificateInfoDto.getValidFrom() != null) {
                logDto.setCertIssueDate(Psd2Util.getStringFromLocalDatetime(certificateInfoDto.getValidFrom(), DEFAULT_DATE_TIME_FORMAT));
            }
            if (certificateInfoDto.getValidTo() != null) {
                logDto.setCertValidToDate(Psd2Util.getStringFromLocalDatetime(certificateInfoDto.getValidTo(), DEFAULT_DATE_TIME_FORMAT));
            }
            if (StringUtils.isNotBlank(certificateInfoDto.getOrganizationName())) {
                logDto.setTppName(certificateInfoDto.getOrganizationName());
            }
            if (StringUtils.isNotBlank(certificateInfoDto.getOrganizationID())) {
                logDto.setTppId(certificateInfoDto.getOrganizationID());
            }
            if (certificateInfoDto.getTppRoles() != null && certificateInfoDto.getTppRoles().size() > 0) {
                logDto.setTppRole(StringFunctions.getDelimitedTextFromList(certificateInfoDto.getTppRoles(), ","));
            }
            if (StringUtils.isNotBlank(certificateInfoDto.getCompetentAuthorityName())) {
                logDto.setCompetentAuthorityName(certificateInfoDto.getCompetentAuthorityName());
            }
            if (StringUtils.isNotBlank(certificateInfoDto.getCompetentAuthorityID())) {
                logDto.setCompetentAuthorityId(certificateInfoDto.getCompetentAuthorityID());
            }
        } else {
            logDto.setTppName(request.getHeader(TPP_NAME.getValue()));
            logDto.setTppRole(request.getHeader(TPP_ROLE.getValue()));
        }
        logDto.setRemoteHost(request.getRemoteHost());
        logDto.setTppAddress(request.getHeader(X_FORWARDED_FOR.getValue()));
        logDto.setPsuId(request.getHeader(PSU_ID.getValue()));
        logDto.setPsuAddress(request.getHeader(PSU_IP_ADDRESS.getValue()));
        logDto.setTppRedirectUri(request.getHeader(TPP_REDIRECT_URI.getValue()));
        logDto.setTppMessage(tppMessage);
        return logDto;
    }

    /**
     * Form responseLogDto - u slučaju kad smo logirali unutar svake metode (taj dio sada prebačen u filter)
     */
    private static Psd2Xs2aLogDto formResponseLogDto(String messageName, HttpServletRequest request, String interfaceEnvironment, Response response) {
        Psd2Xs2aLogDto logDto = new Psd2Xs2aLogDto();
        Calendar time = GregorianCalendar.getInstance();
        logDto.setDateInString(getDateAsString(time.getTime(), DEFAULT_DATE_FORMAT));
        logDto.setTime(time.getTime());
        logDto.setMessageType(RESPONSE.toString());
        logDto.setMessageName(messageName);
        logDto.setInterfaceName(interfaceEnvironment);
        logDto.setSessionId(request.getSession().getId());
        logDto.setAspsApiCall(request.getMethod() + "-" + request.getRequestURL());
        logDto.setCertIssueDate(null);
        logDto.setCertValidToDate(null);
        logDto.setTppRedirectUri(request.getHeader(TPP_REDIRECT_URI.getValue()));
        logDto.setAspsXRequestId((String) response.getHeaders().getFirst(X_REQUEST_ID.getValue()));
        logDto.setAspsLocation(response.getStatus() == 201 ? response.getLocation().toString() : null);
        logDto.setAspsMessage(response.getEntity() != null ? Psd2Util_origin.getObjectAsString(response.getEntity()) : null);
        logDto.setAspsHttpResponseCode(String.valueOf(response.getStatus()));
        return logDto;
    }

    /**
     * Form responseLogDto - logiranje response-a preko filtera
     */
    private static Psd2Xs2aLogDto formResponseFilterLogDto2(String messageName, HttpServletRequest request, String interfaceEnvironment, HttpServletResponse httpServletResponse, WriterInterceptorContext responseContext) {
        Psd2Xs2aLogDto logDto = new Psd2Xs2aLogDto();
        Calendar time = GregorianCalendar.getInstance();
        logDto.setDateInString(getDateAsString(time.getTime(), LOG_DATE_TIME_FORMAT));
        logDto.setTime(time.getTime());
        logDto.setMessageType(RESPONSE.toString());
        logDto.setMessageName(messageName);
        logDto.setInterfaceName(interfaceEnvironment);
        logDto.setSessionId(request.getSession().getId());
        logDto.setAspsApiCall(request.getMethod() + "-" + request.getRequestURL());
        logDto.setCertIssueDate(null);
        logDto.setCertValidToDate(null);
        logDto.setTppRedirectUri(request.getHeader(TPP_REDIRECT_URI.getValue()));
        logDto.setAspsXRequestId(httpServletResponse.getHeader(X_REQUEST_ID.getValue()));
        //logDto.setAspsLocation(httpServletResponse.getStatus() == 201 ? httpServletResponse.getLocation().toString() : null);
        logDto.setAspsMessage(Psd2Util.getObjectAsString(responseContext));
        logDto.setAspsHttpResponseCode(String.valueOf(httpServletResponse.getStatus()));
        logDto.setTppAddress(request.getHeader(X_FORWARDED_FOR.getValue()));
        return logDto;
    }


    /**
     * Form responseLogDto - logiranje response-a preko interceptora
     */
    private static Psd2Xs2aLogDto formResponseFilterLogDto(String messageName, HttpServletRequest request, String interfaceEnvironment, ContainerResponseContext responseContext) {
        Psd2Xs2aLogDto logDto = new Psd2Xs2aLogDto();
        Calendar time = GregorianCalendar.getInstance();
        logDto.setDateInString(getDateAsString(time.getTime(), LOG_DATE_TIME_FORMAT));
        //logDto.setTime(time.getTime());
        logDto.setMessageType(RESPONSE.toString());
        logDto.setMessageName(messageName);
        logDto.setInterfaceName(interfaceEnvironment);
        logDto.setSessionId(request.getSession().getId());
        logDto.setAspsApiCall(request.getMethod() + "-" + request.getRequestURL());
        logDto.setCertIssueDate(null);
        logDto.setCertValidToDate(null);
        logDto.setTppRedirectUri(request.getHeader(TPP_REDIRECT_URI.getValue()));
        logDto.setAspsXRequestId((String) responseContext.getHeaders().getFirst(X_REQUEST_ID.getValue()));
        logDto.setAspsLocation(responseContext.getStatus() == 201 ? responseContext.getLocation().toString() : null);
        logDto.setAspsMessage(responseContext.hasEntity() ? Psd2Util_origin.getObjectAsString(responseContext.getEntity()) : null);
        logDto.setAspsHttpResponseCode(String.valueOf(responseContext.getStatus()));
        return logDto;
    }


    /**
     *
     * Insert dto-a u log (Elastic Search) --> umjesto Elastic-a prelazimo na file log (PABA security), ova meotda neka ostane ukoliko se vratimo
     * na logiranje direktno u Elastic
     *
     * @param logDto
     */
//	private static void addMessageInfoToLog(Psd2Xs2aLogDto logDto) {
//		XContentBuilder builder = null;
//		try {
//			builder = XContentFactory.jsonBuilder().startObject()
//					.field("messageType", logDto.getMessageType())
//					.field("date", logDto.getDateInString())
//					.field("time", logDto.getTime())
//					.field("interfaceName", logDto.getInterfaceName())
//					.field("messageName", logDto.getMessageName())
//					.field("sessionId", logDto.getSessionId())
//					.field("apiCall", logDto.getApiCall())
//					.field("xRequestID", logDto.getxRequestId())
//					.field("certIssueDate", logDto.getCertIssueDate())
//					.field("certValidToDate", logDto.getCertValidToDate())
//					.field("tppAddress", logDto.getTppAddress())
//					.field("tppName", logDto.getTppName())
//					.field("tppRole", logDto.getTppRole())
//					.field("competentAuthorityName", logDto.getCompetentAuthorityName())
//					.field("psuId", logDto.getPsuId())
//					.field("psuAddress", logDto.getPsuAddress())
//					.field("tppRedirectUri", logDto.getTppRedirectUri())
//					.field("tppMessage", logDto.getTppMessage())
//					.field("aspsApiCall", logDto.getAspsApiCall())
//					.field("aspsXRequestId", logDto.getAspsXRequestId())
//					.field("aspsLocation", logDto.getAspsLocation())
//					.field("aspsMessage", logDto.getAspsMessage())
//					.field("aspsHttpResponseCode", logDto.getAspsHttpResponseCode())
//					.endObject();
//			if (ElasticListener.getClient() != null) ElasticListener.getClient().prepareIndex("psd2-xs2a", "psd2-xs2a-log").setSource(builder).get();
//		} catch (IOException e) {
//			log.error("Exception is:", e);
//		} finally {
//			if (builder != null) builder.close();
//		}
//	}


    /**
     * Insert dto-a u server log //TODO u poseban log file
     *
     * @param logDto
     */
    private static void addMessageInfoToFileLog(Psd2Xs2aLogDto logDto) {
        String l = "messageType:" + logDto.getMessageType() +
                ",date:" + logDto.getDateInString() +
                ",time:" + logDto.getTime() +
                ",interfaceName:" + logDto.getInterfaceName() +
                ",messageName:" + logDto.getMessageName() +
                ",sessionId:" + logDto.getSessionId() +
                ",apiCall" + logDto.getApiCall() +
                ",xRequestID:" + logDto.getxRequestId() +
                ",certIssueDate:" + logDto.getCertIssueDate() +
                ",certValidToDate:" + logDto.getCertValidToDate() +
                ",tppAddress:" + logDto.getTppAddress() +
                ",tppId:" + logDto.getTppId() +
                ",tppName:" + logDto.getTppName() +
                ",tppRole:" + logDto.getTppRole() +
                ",competentAuthorityId:" + logDto.getCompetentAuthorityId() +
                ",competentAuthorityName:" + logDto.getCompetentAuthorityName() +
                ",psuId:" + logDto.getPsuId() +
                ",psuAddress:" + logDto.getPsuAddress() +
                ",tppRedirectUri:" + logDto.getTppRedirectUri() +
                ",tppMessage:" + logDto.getTppMessage() +
                ",aspsApiCall:" + logDto.getAspsApiCall() +
                ",aspsXRequestId:" + logDto.getAspsXRequestId() +
                ",aspsLocation:" + logDto.getAspsLocation() +
                ",aspsMessage:" + logDto.getAspsMessage() +
                ",aspsHttpResponseCode:" + logDto.getAspsHttpResponseCode();
        log.info("PSD2_XS2A_LOG -----> " + l);
    }

    /**
     * Log psd2 request u server log //TODO u poseban log file
     *
     * @param logDto
     */
    private static String addRequestMessageInfoToFileLog(Psd2Xs2aLogDto logDto, HttpServletRequest request) {
        Map<String, String> headersMap =null; //Psd2Util.getHeadersInfo(request);
        String l = "messageType:" + logDto.getMessageType() +
                ",dateTime:" + logDto.getDateInString() +
                ",time:" + logDto.getTime() +
                ",interfaceName:" + logDto.getInterfaceName() +
                ",messageName:" + logDto.getMessageName() +
                ",sessionId:" + logDto.getSessionId() +
                ",apiCall:" + logDto.getApiCall() +
                ",xRequestID:" + logDto.getxRequestId() +
                ",certIssueDate:" + logDto.getCertIssueDate() +
                ",certValidToDate:" + logDto.getCertValidToDate() +
                ",tppAddress:" + logDto.getTppAddress() +
                ",remoteHost:" + logDto.getRemoteHost() +
                ",tppId:" + logDto.getTppId() +
                ",tppName:" + logDto.getTppName() +
                ",tppRole:" + logDto.getTppRole() +
                ",competentAuthorityId:" + logDto.getCompetentAuthorityId() +
                ",competentAuthorityName:" + logDto.getCompetentAuthorityName() +
                ",psuId:" + logDto.getPsuId() +
                ",psuAddress:" + logDto.getPsuAddress() +
                ",tppRedirectUri:" + logDto.getTppRedirectUri() +
                ",tppMessage:" + logDto.getTppMessage() +
                ",aspsApiCall:" + logDto.getAspsApiCall() +
                ",aspsXRequestId:" + logDto.getAspsXRequestId() +
                ",aspsLocation:" + logDto.getAspsLocation() +
                ",aspsMessage:" + logDto.getAspsMessage() +
                ",aspsHttpResponseCode:" + logDto.getAspsHttpResponseCode();
        l = l + ",requestHeaders--->";
        for (Map.Entry<String, String> entry : headersMap.entrySet()) {
            l = l + "," + entry.getKey() + ":" + entry.getValue();
        }
        log.info("PSD2_XS2A_LOG -----> " + l);
        return l;
    }

    /**
     * Log psd2 response u server log //TODO u poseban log file
     *
     * @param logDto
     */
    private static String addResponseMessageInfoToFileLog(Psd2Xs2aLogDto logDto, ContainerResponseContext reponse) {
        MultivaluedMap<String, String> headersMap = reponse.getStringHeaders();
        String l = "messageType:" + logDto.getMessageType() +
                ",dateTime:" + logDto.getDateInString() +
                ",time:" + logDto.getTime() +
                ",interfaceName:" + logDto.getInterfaceName() +
                ",messageName:" + logDto.getMessageName() +
                ",sessionId:" + logDto.getSessionId() +
                ",apiCall" + logDto.getApiCall() +
                ",xRequestID:" + logDto.getxRequestId() +
                ",certIssueDate:" + logDto.getCertIssueDate() +
                ",certValidToDate:" + logDto.getCertValidToDate() +
                ",tppAddress:" + logDto.getTppAddress() +
                ",tppId:" + logDto.getTppId() +
                ",tppName:" + logDto.getTppName() +
                ",tppRole:" + logDto.getTppRole() +
                ",competentAuthorityId:" + logDto.getCompetentAuthorityId() +
                ",competentAuthorityName:" + logDto.getCompetentAuthorityName() +
                ",psuId:" + logDto.getPsuId() +
                ",psuAddress:" + logDto.getPsuAddress() +
                ",tppRedirectUri:" + logDto.getTppRedirectUri() +
                ",tppMessage:" + logDto.getTppMessage() +
                ",aspsApiCall:" + logDto.getAspsApiCall() +
                ",aspsXRequestId:" + logDto.getAspsXRequestId() +
                ",aspsLocation:" + logDto.getAspsLocation() +
                ",aspsMessage:" + logDto.getAspsMessage() +
                ",aspsHttpResponseCode:" + logDto.getAspsHttpResponseCode();
        l = l + ",responseHeaders--->";
        for (String header : headersMap.keySet()) {
            l = l + "," + header + ":" + headersMap.getFirst(header);
        }
        log.info("PSD2_XS2A_LOG -----> " + l);
        return l;
    }

    /**
     * Log psd2 response u server log //TODO u poseban log file
     *
     * @param logDto
     */
    private static String addResponseMessageInfoToFileLog2(Psd2Xs2aLogDto logDto, WriterInterceptorContext reponse) {
        String l = "messageType:" + logDto.getMessageType() +
                ",dateTime:" + logDto.getDateInString() +
                ",time:" + logDto.getTime() +
                ",interfaceName:" + logDto.getInterfaceName() +
                ",messageName:" + logDto.getMessageName() +
                ",sessionId:" + logDto.getSessionId() +
                ",apiCall" + logDto.getApiCall() +
                ",xRequestID:" + logDto.getxRequestId() +
                ",certIssueDate:" + logDto.getCertIssueDate() +
                ",certValidToDate:" + logDto.getCertValidToDate() +
                ",tppAddress:" + logDto.getTppAddress() +
                ",tppId:" + logDto.getTppId() +
                ",tppName:" + logDto.getTppName() +
                ",tppRole:" + logDto.getTppRole() +
                ",competentAuthorityId:" + logDto.getCompetentAuthorityId() +
                ",competentAuthorityName:" + logDto.getCompetentAuthorityName() +
                ",psuId:" + logDto.getPsuId() +
                ",psuAddress:" + logDto.getPsuAddress() +
                ",tppRedirectUri:" + logDto.getTppRedirectUri() +
                ",tppMessage:" + logDto.getTppMessage() +
                ",aspsApiCall:" + logDto.getAspsApiCall() +
                ",aspsXRequestId:" + logDto.getAspsXRequestId() +
                ",aspsLocation:" + logDto.getAspsLocation() +
                ",aspsMessage:" + logDto.getAspsMessage() +
                ",aspsHttpResponseCode:" + logDto.getAspsHttpResponseCode();
        l = l + ",responseHeaders--->";

        MultivaluedMap<String, Object> headersMap = reponse.getHeaders();
        for (String header : headersMap.keySet()) {
            l = l + "," + header + ":" + headersMap.getFirst(header);
        }
        log.info("PSD2_XS2A_LOG -----> " + l);
        return l;
    }

    /**
     * Vraćanje datum u odabranom formatu.
     *
     * @param date
     * @param format
     * @return
     * @author Mata
     */
    public static String getDateAsString(Date date, String format) {
        if (date != null) {
            SimpleDateFormat f = new SimpleDateFormat(format);
            return f.format(date);
        }
        return null;
    }


//	/**
//	 * Logiranje akcije.
//	 *
//	 * @param imei
//	 * @param actionId
//	 * @param messageType
//	 * @param description
//	 * @param location
//	 * @author Mata
//	 */
//	public static void addToLog(String imei, String actionId, String messageType, String description, String location) {
//		XContentBuilder builder = null;
//		try {
//			Calendar time = GregorianCalendar.getInstance();
//			builder = XContentFactory.jsonBuilder().startObject()
//					"imei", StringUtils.isNotBlank(imei) ? (imei.contains(":") ? imei.split(":")[1] : imei) : "unknown")
//					"mobileUser", StringUtils.isNotBlank(imei) ? (imei.contains(":") ? imei.split(":")[0] : imei) : "unknown")
//					"location", StringUtils.isNotBlank(location) ? location : "unknown")
//					"actionId", actionId)
//					"messageType", messageType)
//					"desc", description)
//					"time", time.getTime())
//				//	"date", DateUtil.getDateAsString(time.getTime(), DateUtil.DEFAULT_DATE_FORMAT))
//				.endObject();
//			if (ElasticListener.getClient() != null) ElasticListener.getClient().prepareIndex(MobileConstants.ES_LOG_INDEX, MobileConstants.ES_LOG_TYPE).setSource(builder).get();
//		} catch (IOException e) {
//			log.getLogger(ElasticUtil.class).warn(e.getMessage());
//		} finally {
//			if (builder != null) builder.close();
//		}
//	}

//	/**
//	 * Dohvat podataka iz indexa temeljnenog na parametrima pretaživanja u formi key - value.
//	 *
//	 * Dohvat se radi samo ako postoje filtri, inace nema smisla vuci sve.
//	 *
//	 * @param filter
//	 * @author Mata
//	 * @throws AbitRuleException
//	 */
//	public static List<MobileActionLogDto> readFromLog(MobileActionLogDto filter) throws AbitRuleException {
//        // inicijalizacija
//		List<MobileActionLogDto> resultList = null;
//		try {
//        	if (filter != null && ElasticListener.getClient() != null) {
//        		// formiranje OR uvjeta za tipove poruka
//        		List<String> messageTypes = new ArrayList<>();
//        		if (filter.getInfoType() || filter.getWarningType() || filter.getErrorType()) {
//        			if (filter.getInfoType()) {
//        				messageTypes.add(ElasticUtil.ES_MESSAGE_TYPE_INFO);
//        			}
//        			if (filter.getWarningType()) {
//        				messageTypes.add(ElasticUtil.ES_MESSAGE_TYPE_WARNING);
//        			}
//        			if (filter.getErrorType()) {
//        				messageTypes.add(ElasticUtil.ES_MESSAGE_TYPE_ERROR);
//        			}
//        		}
//        		// formiranje uvjeta
//        		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
//        		if (filter.getFromDate() != null && filter.getToDate() != null && DateUtil.compareDatesOnly(filter.getFromDate(), filter.getToDate()) <= 0) {
//        			List<String> listDates = new ArrayList<>();
//        			Calendar countDate = GregorianCalendar.getInstance();
//        			countDate.setTime(filter.getFromDate());
//        			while (DateUtil.compareDatesOnly(countDate.getTime(), filter.getToDate()) <= 0) {
//        				listDates.add(DateUtil.getDateAsString(countDate.getTime(), DateUtil.DEFAULT_DATE_FORMAT));
//        				countDate.add(Calendar.DATE, 1);
//        			}
//        			queryBuilder.filter(QueryBuilders.termsQuery("date", listDates.stream().toArray(String[]::new)));
//        		} else {
//        			queryBuilder.must(QueryBuilders.matchQuery("date", DateUtil.getDateAsString(DateUtil.today(), DateUtil.DEFAULT_DATE_FORMAT)));
//        		}
//        		if (StringUtils.isNotBlank(filter.getActionId())) {
//        			queryBuilder.must(QueryBuilders.matchQuery("actionId", filter.getActionId()));
//        		}
//        		if (StringUtils.isNotBlank(filter.getImei())) {
//        			queryBuilder.must(QueryBuilders.matchQuery("imei", filter.getImei()));
//        		}
//        		if (filter.getMobileUserId() != null) {
//        			queryBuilder.must(QueryBuilders.matchQuery("mobileUser", filter.getMobileUserId().toString()));
//        		}
//        		if (StringUtils.isNotBlank(filter.getDescription())) {
//        			queryBuilder.must(QueryBuilders.matchQuery("desc", filter.getDescription()));
//        		}
//        		if (messageTypes != null && !messageTypes.isEmpty()) {
//        			queryBuilder.filter(QueryBuilders.termsQuery("messageType", messageTypes.stream().toArray(String[]::new)));
//        		} else {
//        			queryBuilder.must(QueryBuilders.matchQuery("messageType", "-1"));
//        		}
//        		// parametrizacija upita i dohvat odgovora
//        		SearchResponse response = ElasticListener.getClient().prepareSearch(MobileConstants.ES_LOG_INDEX)
//                        .setTypes(MobileConstants.ES_LOG_TYPE)
//                        .setSearchType(SearchType.QUERY_THEN_FETCH)
//                        .setQuery(queryBuilder)
//                        .setFrom(0)
//                        .setSize(filter != null && filter.getNumberOfRecords() != null ? filter.getNumberOfRecords() : MobileConstants.ES_LOG_NUMBER_OF_RECORDS_DEFAULT)
//                        .get();
//        		// parsiranje odgovora
//        		if (response != null) {
//        			// reinicijalizacija
//        			resultList = new ArrayList<>();
//        			MobileActionLogDto tempLog = null;
//        			int tableId = 0;
//        			for (SearchHit hit : response.getHits().getHits()) {
//        				tempLog = new MobileActionLogDto(tableId++);
//        				for (Map.Entry<String, Object> m : hit.getSourceAsMap().entrySet()) {
//							if (m != null && StringUtils.isNotBlank(m.getKey())) {
//								switch (m.getKey()) {
//									case "date" : tempLog.setDateText((String) m.getValue()); break;
//									case "messageType" : tempLog.setMessageType((String) m.getValue()); break;
//									case "mobileUser" : tempLog.setMobileUserId(m.getValue() != null && StringUtils.isNumeric(m.getValue().toString()) ? Long.parseLong(m.getValue().toString()) : null); break;
//									case "imei" : tempLog.setImei((String) m.getValue()); break;
//									case "actionId" : tempLog.setActionId((String) m.getValue()); break;
//									case "location" : tempLog.setLocation((String) m.getValue()); break;
//									case "time" : tempLog.setTime(m.getValue() != null ? DateUtil.getDateFromString(m.getValue().toString().replace("T", " ").substring(0, m.getValue().toString().length() - 1), DateUtil.ES_DATETIME_FORMAT) : null); break;
//									case "desc" : tempLog.setDescription((String) m.getValue()); break;
//								}
//							}
//						}
//        				resultList.add(tempLog);
//        				tempLog = null;
//					}
//        		}
//        	}
//        } catch (Exception e) {
//            log.getLogger(ElasticUtil.class.getName()).error(e.getMessage());
//            throw new AbitRuleException(e.getMessage());
//        }
//		return resultList;
//	}

    /**
     * Dohvat IP adrese ou request objekta.
     *
     * @param request
     * @return
     * @author Mata
     */
    public static String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }
        return remoteAddr;
    }

}
