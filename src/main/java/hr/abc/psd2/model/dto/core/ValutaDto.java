package hr.abc.psd2.model.dto.core;

import hr.abc.psd2.annotation.IFilter;
import hr.abc.psd2.model.dto.GenericDto;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ValutaDto extends GenericDto<String> implements Serializable{
	
	private static final long serialVersionUID = 1L;
    
    // constants
	public static final String VALUTA_SIFRAVAL_HOME_CURRENCY = "191";
	public static final String VALUTA_OZNKAVAL_HOME_CURRENCY = "HRK";
	public static final String VALUTA_SIFRAVAL_EUR = "978";
	public static final String VALUTA_OZNKAVAL_EUR = "EUR";

	public static final Map<String, String> CurrencyMap = Collections.unmodifiableMap(new HashMap<String, String>() {
				private static final long serialVersionUID = 1L;
				{
					put("ALL", "008"); put("008", "ALL");
					put("AUD", "036"); put("036", "AUD");
					put("CAD", "124"); put("124", "CAD");
					put("CNY", "156"); put("156", "CNY");
					put("HRK", "191"); put("191", "HRK");
					put("CZK", "203"); put("203", "CZK");
					put("DKK", "208"); put("208", "DKK");
					put("HUF", "348"); put("348", "HUF");
					put("ILS", "376"); put("376", "ILS");
					put("JPY", "392"); put("392", "JPY");
					put("MXN", "484"); put("484", "MXN");
					put("NZD", "554"); put("554", "NZD");
					put("NOK", "578"); put("578", "NOK");
					put("RUB", "643"); put("643", "RUB");
					put("SEK", "752"); put("752", "SEK");
					put("CHF", "756"); put("756", "CHF");
					put("MKD", "807"); put("807", "MKD");
					put("GBP", "826"); put("826", "GBP");
					put("USD", "840"); put("840", "USD");
					put("RSD", "941"); put("941", "RSD");
					put("RON", "946"); put("946", "RON");
					put("TRY", "949"); put("949", "TRY");
					put("BGN", "975"); put("975", "BGN");
					put("BAM", "977"); put("977", "BAM");
					put("EUR", "978"); put("978", "EUR");
					put("PLN", "985"); put("985", "PLN");
					put("BRL", "986"); put("986", "BRL");
				}
			});
	
	private String nazivValute;
	private String oznakaValute;
	private Short paritetValute;
	private Integer redoslijed;

	public ValutaDto() {
		super();
	}
	
	public ValutaDto(String sifraValute, String isEuro, String nazivValute, String oznakaValute, Short paritetValute, Integer redoslijed) {
		super();
		this.sifra = sifraValute;
		this.nazivValute = nazivValute;
		this.oznakaValute = oznakaValute;
		this.paritetValute = paritetValute;
		this.redoslijed = redoslijed;
	}

	public ValutaDto(String sifraValute, String oznakaValute) {
		super();
		this.sifra = sifraValute;
		this.oznakaValute = oznakaValute;
	}

	@IFilter(entityField="valuta.sifra_val")
	@Override
	public String getSifra() {
		return super.getSifra();
	}
	
	@Override
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	
	@IFilter(entityField="valuta.naziv_val")
	public String getNazivValute() {
		return nazivValute;
	}
	
	public void setNazivValute(String nazivValute) {
		this.nazivValute = nazivValute;
	}
	
	@IFilter(entityField="valuta.oznka_val")
	public String getOznakaValute() {
		return oznakaValute;
	}
	public void setOznakaValute(String oznakaValute) {
		this.oznakaValute = oznakaValute;
	}
	
	@IFilter(entityField="valuta.parit_val")
	public Short getParitetValute() {
		return paritetValute;
	}
	public void setParitetValute(Short paritetValute) {
		this.paritetValute = paritetValute;
	}
	
	@IFilter(entityField="valuta.redos_val")
	public Integer getRedoslijed() {
		return redoslijed;
	}
	public void setRedoslijed(Integer redoslijed) {
		this.redoslijed = redoslijed;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "[sifra=" + sifra + "]";
	}
	
}
