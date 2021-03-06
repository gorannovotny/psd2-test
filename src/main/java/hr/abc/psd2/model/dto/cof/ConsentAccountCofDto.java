package hr.abc.psd2.model.dto.cof;

import java.io.Serializable;

/**
 * Created by dKosutar on 13-06-19.
 *
 *  PSD2 Confirmation of Founds (cof) Consent Access DTO
 */
public class ConsentAccountCofDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer sifraAcc;
    private String ibanAcc;
    private String currencyAcc;
    private String typeAcc;
    private Integer sifraConAcc;
    private Integer sifraPar;

    // constructors
    public ConsentAccountCofDto() {
        super();
    }

    // getters & setters
    public Integer getSifraAcc() {
        return sifraAcc;
    }

    public void setSifraAcc(Integer sifraAcc) {
        this.sifraAcc = sifraAcc;
    }

    public String getIbanAcc() {
        return ibanAcc;
    }

    public void setIbanAcc(String ibanAcc) {
        this.ibanAcc = ibanAcc;
    }

    public String getCurrencyAcc() {
        return currencyAcc;
    }

    public void setCurrencyAcc(String currencyAcc) {
        this.currencyAcc = currencyAcc;
    }

    public String getTypeAcc() {
        return typeAcc;
    }

    public void setTypeAcc(String typeAcc) {
        this.typeAcc = typeAcc;
    }

    public Integer getSifraConAcc() {
        return sifraConAcc;
    }

    public void setSifraConAcc(Integer sifraConAcc) {
        this.sifraConAcc = sifraConAcc;
    }

    public Integer getSifraPar() {
        return sifraPar;
    }

    public void setSifraPar(Integer sifraPar) {
        this.sifraPar = sifraPar;
    }

}
