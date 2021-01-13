package hr.abc.psd2.model.dto;

import java.util.Date;

/**
 * Created by Tomica on 19-Nov-18.
 */
public class Psd2Xs2aLogDto {

    private static final long serialVersionUID = 1L;
    private String dateInString;
    private Date time;
    private String messageType;
    private String messageName;
    private String interfaceName;
    private String sessionId;
    private String apiCall;
    private String xRequestId;
    private String certIssueDate;
    private String certValidToDate;
    private String tppAddress;
    private String tppName;
    private String tppId;
    private String tppRole;
    private String competentAuthorityId;
    private String competentAuthorityName;
    private String psuId;
    private String psuAddress;
    private String tppRedirectUri;
    private String tppMessage;
    private String aspsApiCall;
    private String aspsXRequestId;
    private String aspsLocation;
    private String aspsMessage;
    private String aspsHttpResponseCode;
    private String remoteHost;


    public Psd2Xs2aLogDto() {
        super();
    }

    public String getDateInString() {
        return dateInString;
    }

    public void setDateInString(String dateInString) {
        this.dateInString = dateInString;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getApiCall() {
        return apiCall;
    }

    public void setApiCall(String apiCall) {
        this.apiCall = apiCall;
    }

    public String getxRequestId() {
        return xRequestId;
    }

    public void setxRequestId(String xRequestId) {
        this.xRequestId = xRequestId;
    }

    public String getCertIssueDate() {
        return certIssueDate;
    }

    public void setCertIssueDate(String certIssueDate) {
        this.certIssueDate = certIssueDate;
    }

    public String getCertValidToDate() {
        return certValidToDate;
    }

    public void setCertValidToDate(String certValidToDate) {
        this.certValidToDate = certValidToDate;
    }

    public String getTppAddress() {
        return tppAddress;
    }

    public void setTppAddress(String tppAddress) {
        this.tppAddress = tppAddress;
    }

    public String getTppName() {
        return tppName;
    }

    public void setTppName(String tppName) {
        this.tppName = tppName;
    }

    public String getTppRole() {
        return tppRole;
    }

    public void setTppRole(String tppRole) {
        this.tppRole = tppRole;
    }

    public String getCompetentAuthorityName() {
        return competentAuthorityName;
    }

    public void setCompetentAuthorityName(String competentAuthorityName) {
        this.competentAuthorityName = competentAuthorityName;
    }

    public String getPsuId() {
        return psuId;
    }

    public void setPsuId(String psuId) {
        this.psuId = psuId;
    }

    public String getPsuAddress() {
        return psuAddress;
    }

    public void setPsuAddress(String psuAddress) {
        this.psuAddress = psuAddress;
    }

    public String getTppRedirectUri() {
        return tppRedirectUri;
    }

    public void setTppRedirectUri(String tppRedirectUri) {
        this.tppRedirectUri = tppRedirectUri;
    }

    public String getTppMessage() {
        return tppMessage;
    }

    public void setTppMessage(String tppMessage) {
        this.tppMessage = tppMessage;
    }

    public String getAspsApiCall() {
        return aspsApiCall;
    }

    public void setAspsApiCall(String aspsApiCall) {
        this.aspsApiCall = aspsApiCall;
    }

    public String getAspsXRequestId() {
        return aspsXRequestId;
    }

    public void setAspsXRequestId(String aspsXRequestId) {
        this.aspsXRequestId = aspsXRequestId;
    }

    public String getAspsLocation() {
        return aspsLocation;
    }

    public void setAspsLocation(String aspsLocation) {
        this.aspsLocation = aspsLocation;
    }

    public String getAspsMessage() {
        return aspsMessage;
    }

    public void setAspsMessage(String aspsMessage) {
        this.aspsMessage = aspsMessage;
    }

    public String getAspsHttpResponseCode() {
        return aspsHttpResponseCode;
    }

    public void setAspsHttpResponseCode(String aspsHttpResponseCode) {
        this.aspsHttpResponseCode = aspsHttpResponseCode;
    }

    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public String getTppId() {
        return tppId;
    }

    public void setTppId(String tppId) {
        this.tppId = tppId;
    }

    public String getCompetentAuthorityId() {
        return competentAuthorityId;
    }

    public void setCompetentAuthorityId(String competentAuthorityId) {
        this.competentAuthorityId = competentAuthorityId;
    }

    public String getRemoteHost() {
        return remoteHost;
    }

    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
    }

    @Override
    public String toString() {
        return "Psd2Xs2aLogDto{" +
                "dateInString='" + dateInString + '\'' +
                ", time=" + time +
                ", messageType='" + messageType + '\'' +
                ", messageName='" + messageName + '\'' +
                ", interfaceName='" + interfaceName + '\'' +
                ", sessionId='" + sessionId + '\'' +
                ", apiCall='" + apiCall + '\'' +
                ", xRequestId='" + xRequestId + '\'' +
                ", certIssueDate='" + certIssueDate + '\'' +
                ", certValidToDate='" + certValidToDate + '\'' +
                ", tppAddress='" + tppAddress + '\'' +
                ", tppName='" + tppName + '\'' +
                ", tppId='" + tppId + '\'' +
                ", tppRole='" + tppRole + '\'' +
                ", competentAuthorityId='" + competentAuthorityId + '\'' +
                ", competentAuthorityName='" + competentAuthorityName + '\'' +
                ", psuId='" + psuId + '\'' +
                ", psuAddress='" + psuAddress + '\'' +
                ", tppRedirectUri='" + tppRedirectUri + '\'' +
                ", tppMessage='" + tppMessage + '\'' +
                ", aspsApiCall='" + aspsApiCall + '\'' +
                ", aspsXRequestId='" + aspsXRequestId + '\'' +
                ", aspsLocation='" + aspsLocation + '\'' +
                ", aspsMessage='" + aspsMessage + '\'' +
                ", aspsHttpResponseCode='" + aspsHttpResponseCode + '\'' +
                ", remoteHost='" + remoteHost + '\'' +
                '}';
    }
}
