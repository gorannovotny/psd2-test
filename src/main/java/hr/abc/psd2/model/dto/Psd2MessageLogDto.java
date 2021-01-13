package hr.abc.psd2.model.dto;

import java.io.Serializable;
import java.util.Date;

public class Psd2MessageLogDto extends GenericDto<Integer> implements Serializable {

    // fields
    private String messageType; // tip PSD2 poruke (request / response)
    private String requestId; //xRequestID
    private String message; // PSD2 poruka
    private Date creationDate; // vrijeme spremanja/nastanka poruke
    private String tppId; //tppId
    private String tppadMsg; // tppAddress
    private String rhostMsg; // remote host
    private String psuadMsg; // psu address

    // constructors
    public Psd2MessageLogDto() {
        super();
    }

    public Psd2MessageLogDto(String requestId, String messageType, Date creationDate, String message, String tppId, String tppadMsg, String rhostMsg, String psuadMsg) {
        this.requestId = requestId;
        this.messageType = messageType;
        this.creationDate = creationDate;
        this.message = message;
        this.tppId = tppId;
        this.tppadMsg = tppadMsg;
        this.rhostMsg = rhostMsg;
        this.psuadMsg = psuadMsg;
    }

    // getters & setters
    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getTppId() {
        return tppId;
    }

    public void setTppId(String tppId) {
        this.tppId = tppId;
    }

    public String getTppadMsg() {
        return tppadMsg;
    }

    public void setTppadMsg(String tppadMsg) {
        this.tppadMsg = tppadMsg;
    }

    public String getPsuadMsg() {
        return psuadMsg;
    }

    public void setPsuadMsg(String psuadMsg) {
        this.psuadMsg = psuadMsg;
    }

    public String getRhostMsg() {
        return rhostMsg;
    }

    public void setRhostMsg(String rhostMsg) {
        this.rhostMsg = rhostMsg;
    }

    @Override
    public String toString() {
        return "Psd2MessageLogDto{" +
                "messageType='" + messageType + '\'' +
                ", requestId='" + requestId + '\'' +
                ", message='" + message + '\'' +
                ", creationDate=" + creationDate +
                ", tppId='" + tppId + '\'' +
                ", tppadMsg='" + tppadMsg + '\'' +
                ", rhostMsg='" + rhostMsg + '\'' +
                ", psuadMsg='" + psuadMsg + '\'' +
                '}';
    }
}
