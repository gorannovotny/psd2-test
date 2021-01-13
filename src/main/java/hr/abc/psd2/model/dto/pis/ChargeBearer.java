package hr.abc.psd2.model.dto.pis;

/**
 * Created by Tomica on 10-Dec-18.
 */
public enum ChargeBearer {

    DEBT("DEBT"), //All transaction charges are to be borne by the debtor.
    CRED("CRED"), //All transaction charges are to be borne by the creditor.
    SHAR("SHAR"), //In a credit transfer context, means that transaction charges on the sender side are to be borne by the debtor, transaction charges on the receiver side are to be borne by the creditor. In a direct debit context, means that transaction charges on the sender side are to be borne by the creditor, transaction charges on the receiver side are to be borne by the debtor.
    SLEV("SLEV"); //Charges are to be applied following the rules agreed in the service level and/or scheme.

    private String value;

    ChargeBearer(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ChargeBearer getByName(String name) {
        for(ChargeBearer e : values()) {
            if(e.value.equals(name)) return e;
        }
        return null;
    }
}


