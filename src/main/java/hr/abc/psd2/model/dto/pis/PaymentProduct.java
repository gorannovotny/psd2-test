package hr.abc.psd2.model.dto.pis;

import java.util.ArrayList;
import java.util.List;

public enum PaymentProduct {

    SEPA_CREDIT_TANSFER("sepa-credit-transfers"),  //EuroNKS	E
    INSTANT_SEPA_CREDIT_TANSFER("instant-sepa-credit-transfers"), //Instant plaćanje	trenutno ne podržavamno
    TARGET2_PAYMENTS("target-2-payments"),  //TARGET2	T
    CROSS_BORDER_CREDIT_TRANSFER("cross-border-credit-transfers"),  //SWIFT	S
    HR_DOMESTIC_PAYMENTS("domestic-credit-transfers-hr"), //NKS	N
    HR_INSTANT_DOMESTIC_PAYMENTS("instant-domestic-credit-transfers-hr"), //Instant plaćanje	trenutno ne podržavamno
    HR_RTGS_PAYMENTS("hr-rtgs-payments"),   //HSVP	H

    PAIN_SEPA_CREDIT_TANSFER("pain.001-credit-transfers"), //EuroNKS	E
    PAIN_INSTANT_SEPA_CREDIT_TANSFER("pain.001-instant-sepa-credit-transfers"), //Instant plaćanje	trenutno ne podržavamno
    PAIN_TARGET2_PAYMENTS("pain.001-target-2-payments"),    //TARGET2	T
    PAIN_CROSS_BORDER_CREDIT_TRANSFER("pain.001-cross-border-credit-transfers"),    //SWIFT	S
    PAIN_HR_DOMESTIC_PAYMENTS("pain.001-hr-domestic-payments"), //NKS	N
    PAIN_HR_INSTANT_DOMESTIC_PAYMENTS("pain.001-hr-instant-domestic-payments"), //Instant plaćanje	trenutno ne podržavamno
    PAIN_HR_RTGS_PAYMENTS("pain.001-hr-rtgs-payments"); //HSVP	H

    private String code;

    PaymentProduct(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static List<String> getListPaymentProduct() {
        List<String> paymentProductList = new ArrayList<>();
        paymentProductList.add(SEPA_CREDIT_TANSFER.getCode());
        paymentProductList.add(INSTANT_SEPA_CREDIT_TANSFER.getCode());
        paymentProductList.add(TARGET2_PAYMENTS.getCode());
        paymentProductList.add(CROSS_BORDER_CREDIT_TRANSFER.getCode());
        paymentProductList.add(PAIN_SEPA_CREDIT_TANSFER.getCode());
        paymentProductList.add(PAIN_INSTANT_SEPA_CREDIT_TANSFER.getCode());
        paymentProductList.add(PAIN_TARGET2_PAYMENTS.getCode());
        paymentProductList.add(PAIN_CROSS_BORDER_CREDIT_TRANSFER.getCode());
        paymentProductList.add(HR_DOMESTIC_PAYMENTS.getCode());
        paymentProductList.add(HR_INSTANT_DOMESTIC_PAYMENTS.getCode());
        paymentProductList.add(HR_RTGS_PAYMENTS.getCode());
        paymentProductList.add(PAIN_HR_DOMESTIC_PAYMENTS.getCode());
        paymentProductList.add(PAIN_HR_INSTANT_DOMESTIC_PAYMENTS.getCode());
        paymentProductList.add(PAIN_HR_RTGS_PAYMENTS.getCode());

        return paymentProductList;
    }

    public static List<String> getListBulkPaymentProduct() {
        List<String> paymentProductList = new ArrayList<>();
        paymentProductList.add(PAIN_SEPA_CREDIT_TANSFER.getCode());
        paymentProductList.add(PAIN_INSTANT_SEPA_CREDIT_TANSFER.getCode());
        paymentProductList.add(PAIN_TARGET2_PAYMENTS.getCode());
        paymentProductList.add(PAIN_CROSS_BORDER_CREDIT_TRANSFER.getCode());
        paymentProductList.add(PAIN_HR_DOMESTIC_PAYMENTS.getCode());
        paymentProductList.add(PAIN_HR_INSTANT_DOMESTIC_PAYMENTS.getCode());
        paymentProductList.add(PAIN_HR_RTGS_PAYMENTS.getCode());

        return paymentProductList;
    }

    public static List<String> getListBulkPaymentProductHr() {
        List<String> paymentProductList = new ArrayList<>();
        paymentProductList.add(PAIN_SEPA_CREDIT_TANSFER.getCode());
        paymentProductList.add(PAIN_HR_INSTANT_DOMESTIC_PAYMENTS.getCode());
        return paymentProductList;
    }

    public static List<String> getListPaymentProductHitno() {
        List<String> paymentProductList = new ArrayList<>();
        paymentProductList.add(TARGET2_PAYMENTS.getCode());
        paymentProductList.add(PAIN_TARGET2_PAYMENTS.getCode());
        paymentProductList.add(HR_RTGS_PAYMENTS.getCode());
        paymentProductList.add(PAIN_HR_RTGS_PAYMENTS.getCode());
        return paymentProductList;
    }

    public static List<String> getListPaymentProductInst() {
        List<String> paymentProductList = new ArrayList<>();
        paymentProductList.add(HR_INSTANT_DOMESTIC_PAYMENTS.getCode());
        paymentProductList.add(PAIN_HR_INSTANT_DOMESTIC_PAYMENTS.getCode());
        return paymentProductList;
    }

}

