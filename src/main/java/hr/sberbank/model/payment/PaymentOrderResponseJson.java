package hr.sberbank.model.payment;

import javax.validation.constraints.NotNull;

public class PaymentOrderResponseJson {

    private String paymentRequestId= null;

    public PaymentOrderResponseJson() {
    }

    @NotNull
    public String getPaymentRequestId() {
        return paymentRequestId;
    }

    public void setPaymentRequestId(String paymentRequestId) {
        this.paymentRequestId = paymentRequestId;
    }
}
