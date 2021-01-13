package hr.abc.psd2.model.dto.pis;

import java.io.Serializable;

/**
 * Created by Tomica on 10-Dec-18.
 */
public class RemittanceDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String reference;
    private String referenceType;
    private String referenceIssuer;

    public RemittanceDto() {
    }

    public RemittanceDto(String reference, String referenceType, String referenceIssuer) {
        this.reference = reference;
        this.referenceType = referenceType;
        this.referenceIssuer = referenceIssuer;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }

    public String getReferenceIssuer() {
        return referenceIssuer;
    }

    public void setReferenceIssuer(String referenceIssuer) {
        this.referenceIssuer = referenceIssuer;
    }
}
