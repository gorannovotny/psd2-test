package hr.abc.psd2.model.dto.core;

import hr.abc.psd2.annotation.IFilter;
import hr.abc.psd2.model.dto.GenericDto;

import java.io.Serializable;

/**
 * Data transfer object for business logic and representation of entity Modul.
 */
public class ModulDto extends GenericDto<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String TABLE_NAME = "modul";
	public static final String TABLE_ALIAS = "Modul"; // isto kao i entity - paziti u DB na tip_dodpod gdje se u tabli_tip koristi ovaj alias
	public static final String SIFRA_FIELD = "sifra_mod";
	public static final String NAZIV_FIELD = "naziv_mod";
	
	@IFilter(entityField="modul.naziv_mod")
	private String naziv; 

	public ModulDto() {
		super();
	}

	@IFilter(entityField="modul.sifra_mod")
    @Override
	public Integer getSifra() {
		return super.getSifra();
	}

    @Override
	public void setSifra(Integer sifra) {
		super.setSifra(sifra);
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "[sifra=" + sifra + "]";
	}
	
}
