package hr.sberbank.model.payment;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RemittanceInformationStructured {
    private @Valid String reference = null;
    private @Valid String referenceType = null;
    private @Valid String referenceIssuer = null;

    public RemittanceInformationStructured() {
    }

    @NotNull
    @Size(max=35)
    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Size(max=35)
    public String getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(String referenceType) {
        this.referenceType = referenceType;
    }

    @Size(max=35)
    public String getReferenceIssuer() {
        return referenceIssuer;
    }

    public void setReferenceIssuer(String referenceIssuer) {
        this.referenceIssuer = referenceIssuer;
    }
}
