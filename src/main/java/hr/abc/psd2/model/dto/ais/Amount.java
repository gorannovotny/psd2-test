package hr.abc.psd2.model.dto.ais;

import java.io.Serializable;

public class Amount implements Serializable {

    private static final long serialVersionUID = 1L;
    private String currency;
    private String amount;

    public Amount() {
        super();
    }

    public Amount(String currency, String amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
