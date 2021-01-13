package hr.abc.psd2.model.dto.core;

import org.apache.commons.lang3.StringUtils;

import hr.abc.psd2.model.dto.GenericDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Data transfer object za entitet devizni nalog extended koji predstavlja dodatna i opcionalna polja deviznog naloga.
 * 
 * @author Matija Hlapčić
 */
public class NalogDevizniExtendedDto extends GenericDto<Integer> implements Serializable {

	// fields
	private static final long serialVersionUID = 1L;
	private String sifraDrzaveBanke; // država banke nalogodavatelja/korisnika (stari drzbaNal)
	private Integer sifraPartijeNaplatneBanke; // Partija naplatne banke
	private String brojUgovora; // Broj ugovora
	private Integer godina; // Godina
	private String mjesto; // Mjesto na nalogu za potrebe Internet bankarstva
	private String napomena; // Napomena na nalogu za potrebe Internet bankarstva
	private String troskoviVanjskoTrgovinskogPosla; // Troškovi vanjsko trgovinskog posla (stari trvposNal)
	private Date datum; // Polje se koristi u svrhu pamćenja 3. datuma kod deviznih naloga - npr. datum pisma kod akreditiva koji ulazi u SWIFT
	private String statusSlanja; // status slanja obavijesti o deviznoj transakciji
	private String nalogodavateljKorisnikSwift; // opis nalogodavatelja/korisnika iz SWIFT-a koji se na platnom nalogu može zamijeniti
	private BigDecimal zastupnickaProvizija; // zastupnička provizija
	// hellper fileds for lookup
	private String brojPartijeNaplatneBanke, nazivKomitentaNaplatneBanke, oibNaplatneBanke;
	private String nazivDrzaveBanke;

	// contructors
	public NalogDevizniExtendedDto() {
		super();
	}

	// builder
	// private contructor for builder
	private NalogDevizniExtendedDto(NalogDevizniExtendedDtoBuilder builder) {
		super();
		setSifra(builder.sifra);
		setSifraDrzaveBanke(builder.sifraDrzaveBanke);
		setSifraPartijeNaplatneBanke(builder.sifraPartijeNaplatneBanke);
		setBrojUgovora(builder.brojUgovora);
		setGodina(builder.godina);
		setMjesto(builder.mjesto);
		setNapomena(builder.napomena);
		setTroskoviVanjskoTrgovinskogPosla(builder.troskoviVanjskoTrgovinskogPosla);
		setDatum(builder.datum);
		setStatusSlanja(builder.statusSlanja);
		setNalogodavateljKorisnikSwift(builder.nalogodavateljKorisnikSwift);
	}
	
	// utility methods
	/**
	 * Metoda provjerava da li je bilo promjena po DTO.
	 * 
	 * Ako je barem jedno polje popunjeno, tada se vraća true.
	 *  
	 * @return
	 * @author Matija Hlapčić
	 */
	public boolean existChange() {
		boolean result = false;
		result = result ||  
				getSifra() != null || 
				StringUtils.isNotBlank(getSifraDrzaveBanke()) ||
				getSifraPartijeNaplatneBanke() != null ||
				StringUtils.isNotBlank(getBrojUgovora()) || 
				getGodina() != null || 
				StringUtils.isNotBlank(getMjesto()) || 
				StringUtils.isNotBlank(getNapomena()) || 
				StringUtils.isNotBlank(getTroskoviVanjskoTrgovinskogPosla()) || 
				getDatum() != null ||
				getStatusSlanja() != null ||
				StringUtils.isNotBlank(getNalogodavateljKorisnikSwift());
		return result;
	}

	// builder
	public static class NalogDevizniExtendedDtoBuilder {
		// fields
		private Integer sifra, godina, sifraPartijeNaplatneBanke;
		private String brojUgovora, troskoviVanjskoTrgovinskogPosla, mjesto, napomena, sifraDrzaveBanke, statusSlanja, nalogodavateljKorisnikSwift;
		private Date datum;

		// constructor
		public NalogDevizniExtendedDtoBuilder() {
			super();
		}

		// methods for optional parameters
		public NalogDevizniExtendedDtoBuilder sifra(Integer sifra) {
			this.sifra = sifra;
			return this;
		}

		public NalogDevizniExtendedDtoBuilder godina(Integer godina) {
			this.godina = godina;
			return this;
		}

		public NalogDevizniExtendedDtoBuilder sifraPartijeNaplatneBanke(Integer sifraPartijeNaplatneBanke) {
			this.sifraPartijeNaplatneBanke = sifraPartijeNaplatneBanke;
			return this;
		}

		public NalogDevizniExtendedDtoBuilder brojUgovora(String brojUgovora) {
			this.brojUgovora = brojUgovora;
			return this;
		}

		public NalogDevizniExtendedDtoBuilder troskoviVanjskoTrgovinskogPosla(String troskoviVanjskoTrgovinskogPosla) {
			this.troskoviVanjskoTrgovinskogPosla = troskoviVanjskoTrgovinskogPosla;
			return this;
		}

		public NalogDevizniExtendedDtoBuilder mjesto(String mjesto) {
			this.mjesto = mjesto;
			return this;
		}

		public NalogDevizniExtendedDtoBuilder napomena(String napomena) {
			this.napomena = napomena;
			return this;
		}

		public NalogDevizniExtendedDtoBuilder sifraDrzaveBanke(String sifraDrzaveBanke) {
			this.sifraDrzaveBanke = sifraDrzaveBanke;
			return this;
		}

		public NalogDevizniExtendedDtoBuilder statusSlanja(String statusSlanja) {
			this.statusSlanja = statusSlanja;
			return this;
		}

		public NalogDevizniExtendedDtoBuilder nalogodavateljKorisnikSwift(String nalogodavateljKorisnikSwift) {
			this.nalogodavateljKorisnikSwift = nalogodavateljKorisnikSwift;
			return this;
		}

		public NalogDevizniExtendedDtoBuilder datum(Date datum) {
			this.datum = datum;
			return this;
		}

		// build method
		public NalogDevizniExtendedDto build() {
			return new NalogDevizniExtendedDto(this);
		}
	}

	// getters & setters
	public Integer getSifraPartijeNaplatneBanke() {
		return sifraPartijeNaplatneBanke;
	}

	public void setSifraPartijeNaplatneBanke(Integer sifraPartijeNaplatneBanke) {
		this.sifraPartijeNaplatneBanke = sifraPartijeNaplatneBanke;
	}

	public String getNalogodavateljKorisnikSwift() {
		return nalogodavateljKorisnikSwift;
	}

	public void setNalogodavateljKorisnikSwift(String nalogodavateljKorisnikSwift) {
		this.nalogodavateljKorisnikSwift = nalogodavateljKorisnikSwift;
	}
	
	public String getBrojUgovora() {
		return brojUgovora;
	}

	public void setBrojUgovora(String brojUgovora) {
		this.brojUgovora = brojUgovora;
	}

	public String getTroskoviVanjskoTrgovinskogPosla() {
		return troskoviVanjskoTrgovinskogPosla;
	}

	public void setTroskoviVanjskoTrgovinskogPosla(String troskoviVanjskoTrgovinskogPosla) {
		this.troskoviVanjskoTrgovinskogPosla = troskoviVanjskoTrgovinskogPosla;
	}

	public Integer getGodina() {
		return godina;
	}

	public void setGodina(Integer godina) {
		this.godina = godina;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getMjesto() {
		return mjesto;
	}

	public void setMjesto(String mjesto) {
		this.mjesto = mjesto;
	}

	public String getNapomena() {
		return napomena;
	}

	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}

	public String getBrojPartijeNaplatneBanke() {
		return brojPartijeNaplatneBanke;
	}

	public void setBrojPartijeNaplatneBanke(String brojPartijeNaplatneBanke) {
		this.brojPartijeNaplatneBanke = brojPartijeNaplatneBanke;
	}

	public String getNazivKomitentaNaplatneBanke() {
		return nazivKomitentaNaplatneBanke;
	}

	public void setNazivKomitentaNaplatneBanke(String nazivKomitentaNaplatneBanke) {
		this.nazivKomitentaNaplatneBanke = nazivKomitentaNaplatneBanke;
	}

	public String getSifraDrzaveBanke() {
		return sifraDrzaveBanke;
	}

	public void setSifraDrzaveBanke(String sifraDrzaveBanke) {
		this.sifraDrzaveBanke = sifraDrzaveBanke;
	}

	public String getNazivDrzaveBanke() {
		return nazivDrzaveBanke;
	}

	public void setNazivDrzaveBanke(String nazivDrzaveBanke) {
		this.nazivDrzaveBanke = nazivDrzaveBanke;
	}

	public String getStatusSlanja() {
		return statusSlanja;
	}

	public void setStatusSlanja(String statusSlanja) {
		this.statusSlanja = statusSlanja;
	}

	public BigDecimal getZastupnickaProvizija() {
		return zastupnickaProvizija;
	}

	public void setZastupnickaProvizija(BigDecimal zastupnickaProvizija) {
		this.zastupnickaProvizija = zastupnickaProvizija;
	}

	public String getOibNaplatneBanke() {
		return oibNaplatneBanke;
	}

	public void setOibNaplatneBanke(String oibNaplatneBanke) {
		this.oibNaplatneBanke = oibNaplatneBanke;
	}

}
