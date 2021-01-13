package hr.sberbank.model.payment;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class BulkPaymentOrdersStatus {
    private @Valid String paymentBulkId = null;
    private @Valid List<BulkPaymentOrderStatus> initiatedPaymentOrders = new ArrayList<BulkPaymentOrderStatus>();

    public BulkPaymentOrdersStatus() {
    }
    @NotNull
    public String getPaymentBulkId() {
        return paymentBulkId;
    }

    public void setPaymentBulkId(String paymentBulkId) {
        this.paymentBulkId = paymentBulkId;
    }

    @NotNull
    public List<BulkPaymentOrderStatus> getInitiatedPaymentOrders() {
        return initiatedPaymentOrders;
    }

    public void setInitiatedPaymentOrders(List<BulkPaymentOrderStatus> initiatedPaymentOrders) {
        this.initiatedPaymentOrders = initiatedPaymentOrders;
    }
}
