package hr.abc.psd2.model.dto.authorization;

import java.io.Serializable;

/**
 * Created by Tomica on 22-Jan-19.
 */
public class CreateAuthorizationRequestDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String paymentService;
    private String paymentProduct;
    private Integer objectType;
    private String objectId;
    private Integer authorizationType;

    private String xRequestId;
    private String tppId;
    private String psuId;

    public CreateAuthorizationRequestDto() {
    }

    public CreateAuthorizationRequestDto(String paymentService, String paymentProduct, Integer objectType, String objectId, Integer authorizationType, String xRequestId, String tppId, String psuId) {
        this.paymentService = paymentService;
        this.paymentProduct = paymentProduct;
        this.objectType = objectType;
        this.objectId = objectId;
        this.authorizationType = authorizationType;
        this.xRequestId = xRequestId;
        this.tppId = tppId;
        this.psuId = psuId;
    }

    public String getPaymentService() {
        return paymentService;
    }

    public void setPaymentService(String paymentService) {
        this.paymentService = paymentService;
    }

    public String getPaymentProduct() {
        return paymentProduct;
    }

    public void setPaymentProduct(String paymentProduct) {
        this.paymentProduct = paymentProduct;
    }

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Integer getAuthorizationType() {
        return authorizationType;
    }

    public void setAuthorizationType(Integer authorizationType) {
        this.authorizationType = authorizationType;
    }

    public String getxRequestId() {
        return xRequestId;
    }

    public void setxRequestId(String xRequestId) {
        this.xRequestId = xRequestId;
    }

    public String getTppId() {
        return tppId;
    }

    public void setTppId(String tppId) {
        this.tppId = tppId;
    }

    public String getPsuId() {
        return psuId;
    }

    public void setPsuId(String psuId) {
        this.psuId = psuId;
    }
}
