package hr.abc.psd2.model.dto.core;

import hr.abc.psd2.annotation.IFilter;
import hr.abc.psd2.model.dto.GenericDto;

import java.io.Serializable;
import java.util.Objects;

/**
 * Data transfer object for business logic and representation of entity Drzava.
 *
 */
public class DrzavaDto extends GenericDto<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    // table name, alias & fields
    public static final String TABLE_NAME = "drzava";
    public static final String TABLE_ALIAS = "Drzava";    // isto kao i entity - paziti u DB na tip_dodpod gdje se u tabli_tip koristi ovaj alias
    public static final String SIFRA_FIELD = "sifra_drz";
    public static final String SIDVA_FIELD = "sidva_drz";
    public static final String SITRI_FIELD = "sitri_drz";
    public static final String NAZIV_FIELD = "naziv_drz";
    public static final String NAZEN_FIELD = "nazen_drz";
    public static final String OFFSH_FIELD = "offsh_drz";
    public static final String TELBR_FIELD = "telbr_drz";
    public static final String TRTYP_FIELD = "trtyp_drz";
    // constants
	public static final String HOME_COUNTRY = "191";
	public static final String HOME_COUNTRY_2 = "HR";
	public static final String HOME_COUNTRY_3 = "HRV";
    public static final String OFFSHORE = "D";
    public static final String NEPOZNATA_SIFRA = "999";
    // fields
	private String dvoslovnaSifra;
	private String troslovnaSifra;
	private String naziv;
	private String engleskiNaziv;
	private String isOffshore;
	private String telefonskiPredbroj;
	private String transactionType;
	// constructors
	public DrzavaDto() {
		super();
	}

	public DrzavaDto(String naziv, String troslovnaSifra) {
		super();
		this.naziv = naziv;
		this.troslovnaSifra = troslovnaSifra;
	}

    @Override
    public String toString() { return getClass().getSimpleName() + "[" + TABLE_ALIAS + "=" + sifra.trim() + "]"; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null && !(o instanceof DrzavaDto)) return false;
        DrzavaDto that = (DrzavaDto) o;
        return Objects.equals(getSifra(), that.getSifra());
    }

    @Override
    public int hashCode() { return Objects.hash(getClass().getSimpleName(), getSifra()); }

    // getters & setters
	@Override
	@IFilter(entityField = TABLE_ALIAS + "." + SIFRA_FIELD)
	public String getSifra() {
		return super.getSifra();
	}

	@IFilter(entityField = TABLE_ALIAS + "." + SIDVA_FIELD)
	public String getDvoslovnaSifra() {
		return dvoslovnaSifra;
	}

	public void setDvoslovnaSifra(String dvoslovnaSifra) {
		this.dvoslovnaSifra = dvoslovnaSifra;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + SITRI_FIELD)
	public String getTroslovnaSifra() {
		return troslovnaSifra;
	}

	public void setTroslovnaSifra(String troslovnaSifra) {
		this.troslovnaSifra = troslovnaSifra;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + NAZIV_FIELD)
	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + NAZEN_FIELD)
	public String getEngleskiNaziv() {
		return engleskiNaziv;
	}

	public void setEngleskiNaziv(String engleskiNaziv) {
		this.engleskiNaziv = engleskiNaziv;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + OFFSH_FIELD)
	public String getIsOffshore() { return isOffshore; }

	public void setIsOffshore(String isOffshore) {
		this.isOffshore = isOffshore;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + TELBR_FIELD)
	public String getTelefonskiPredbroj() {
		return telefonskiPredbroj;
	}

	public void setTelefonskiPredbroj(String telefonskiPredbroj) {
		this.telefonskiPredbroj = telefonskiPredbroj;
	}

    @IFilter(entityField = TABLE_ALIAS + "." + TRTYP_FIELD)
	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
}