package hr.sberbank.model.payment;

public enum PaymentProductType {

    DOMESTIC("DOMESTIC"),
    DOMESTIC_URGENT("DOMESTIC_URGENT"),
    SEPA("SEPA"),
    SEPA_URGENT("SEPA_URGENT"),
    CROSS_BORDER("CROSS_BORDER"),
    BUYSELL("BUYSELL");

    private String value;

    PaymentProductType(String value) {
        this.value = value;
    }
}
