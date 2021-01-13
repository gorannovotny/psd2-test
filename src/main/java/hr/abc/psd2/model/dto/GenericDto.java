package hr.abc.psd2.model.dto;

import java.io.Serializable;
import java.util.Objects;

import org.apache.commons.lang3.SerializationUtils;

import hr.abc.psd2.model.dto.core.NalogNefinDto;
import hr.abc.psd2.security.Globals;

/**
 * Generic DTO that extends all concrete DTOs that need functionalities like filtering, lookups, etc.
 * 
 * @author Matija Hlapčić
 */
public abstract class GenericDto<PK> implements Serializable {

	private static final long serialVersionUID = 1L;
	protected PK sifra;
	private boolean isFirstCall = false; // flag za prvo pozivnje upita ako se želi ograničiti set podataka koji se dohvaća na početku
	private boolean selected; // da li je u listi naloga konkretni objekt selektiran ili ne
	private boolean searchActionActivated = false; // da li se okinula akcija pretraživanja (kada se filtrira početna lista, nije moguće znati da li se daje ograničena lista ili puna preko filtra)

	private NalogNefinDto nalogNefin;

	private Globals globals;

	public GenericDto() {
		super();
	}

	public GenericDto(PK sifra) {
		this.sifra = sifra;
	}

	public GenericDto(boolean isFirstCall) {
		super();
		this.isFirstCall = isFirstCall;
	}

	public PK getSifra() {
		return sifra;
	}

	public void setSifra(PK sifra) {
		this.sifra = sifra;
	}

	public boolean isFirstCall() {
		return isFirstCall;
	}

	public void setFirstCall(boolean isFirstCall) {
		this.isFirstCall = isFirstCall;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[sifra=" + sifra + "]";
	}

	public Object klone() {
		return SerializationUtils.clone(this);
	}

	public NalogNefinDto getNalogNefin() {
		return nalogNefin;
	}

	public void setNalogNefin(NalogNefinDto nalogNefin) {
		this.nalogNefin = nalogNefin;
	}

	public Globals getGlobals() {
		return globals;
	}

	public void setGlobals(Globals globals) {
		this.globals = globals;
	}

	public boolean isSearchActionActivated() {
		return searchActionActivated;
	}

	public void setSearchActionActivated(boolean searchActionActivated) {
		this.searchActionActivated = searchActionActivated;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (this == o) return true;

		if (Objects.equals(getClass(), o.getClass())) return false;

		GenericDto that = (GenericDto) o;

		return Objects.equals(getSifra(), that.getSifra());
	}

}