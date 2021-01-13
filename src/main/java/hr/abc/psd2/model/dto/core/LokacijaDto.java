package hr.abc.psd2.model.dto.core;

import hr.abc.psd2.annotation.IFilter;
import hr.abc.psd2.lookup.DrzavaNewLookup;
import hr.abc.psd2.lookup.KomitentNewLookup;
import hr.abc.psd2.lookup.MjestoNewLookup;
import hr.abc.psd2.lookup.PartijaNewLookup;
import hr.abc.psd2.model.dto.GenericDto;

import java.io.Serializable;

public class LokacijaDto extends GenericDto<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;
	// table name, alias & fields
	public static final String TABLE_NAME = "lokacija";
	public static final String TABLE_ALIAS = "Lokacija";  // isto kao i entity - ne mijenjati i paziti u DB na tip_dodpod gdje se u tabli_tip koristi ovaj alias
	public static final String SIFRA_FIELD = "sifra_lok";
	public static final String TIPLO_FIELD = "tiplo_lok";
	public static final String KOMIT_FIELD = "komit_lok";
	public static final String PARTI_FIELD = "parti_lok";
	public static final String ADRES_FIELD = "adres_lok";
	public static final String KUCBR_FIELD = "kucbr_lok";
	public static final String MJREZ_FIELD = "mjrez_lok";
	public static final String MJNER_FIELD = "mjner_lok";
	public static final String POSTA_FIELD = "posta_lok";
	public static final String DRZAV_FIELD = "drzav_lok";
	public static final String NASLO_FIELD = "naslo_lok";
	// fields
	private String sifraTipaLokacije;
	private String adresa;
	private String kucniBroj;
	private KomitentNewLookup komitentNewLookup;
	private PartijaNewLookup partijaNewLookup;
	private DrzavaNewLookup drzavaNewLookup;
	private MjestoNewLookup mjestoNewLookup;
	private String mjestoNerezident;
	private String postaNerezident;
	private String naslov;
	// help fields
	private String nazivTipaLokacije;
	private String nazivKomitenta;
	private String mjnerLok;

	private Integer sifraMjesta;
	private String mjesto;
	private String pttbr;

	private Integer sifraPartije;
	private Integer sifraKomitenta;
	private String maticniBrojKomitenta;
	private String oibKomitenta;
	private String ime;
	private String prezime;
	private String oznakaDrzaveDvoslovna;

	// constants
	public static final String TIP_LOKACIJE_ADRESA = "1";
	public static final String TIP_LOKACIJE_ADRESA_OVLASTENI_MJENJAC = "2";
	public static final String TIP_LOKACIJE_ADRESA_ATM_POS_TERMINALA = "3";
	public static final String TIP_LOKACIJE_ADRESA_DOSTAVE_PINA = "4";
	public static final String TIP_LOKACIJE_ADRESA_BORAVISTE = "5";
	public static final String TIP_LOKACIJE_ADRESA_KORESPODENCIJA = "1001";

	// constructors
	public LokacijaDto() {
		super();
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[" + TABLE_ALIAS + "=" + sifra + "]";
	}

	// getters & setters
	@Override
	@IFilter(entityField = TABLE_ALIAS + "." + SIFRA_FIELD)
	public Integer getSifra() {
		return super.getSifra();
	}

	@IFilter(entityField = TABLE_ALIAS + "." + TIPLO_FIELD)
	public String getSifraTipaLokacije() {
		return sifraTipaLokacije;
	}

	public void setSifraTipaLokacije(String sifraTipaLokacije) {
		this.sifraTipaLokacije = sifraTipaLokacije;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + ADRES_FIELD)
	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + KUCBR_FIELD)
	public String getKucniBroj() {
		return kucniBroj;
	}

	public void setKucniBroj(String kucniBroj) {
		this.kucniBroj = kucniBroj;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + KOMIT_FIELD)
	public KomitentNewLookup getKomitentNewLookup() {
		if (komitentNewLookup == null) {
			komitentNewLookup = new KomitentNewLookup();
		}
		return komitentNewLookup;
	}

	public void setKomitentNewLookup(KomitentNewLookup komitentNewLookup) {
		this.komitentNewLookup = komitentNewLookup;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + PARTI_FIELD)
	public PartijaNewLookup getPartijaNewLookup() {
		if (partijaNewLookup == null) {
			partijaNewLookup = new PartijaNewLookup();
		}
		return partijaNewLookup;
	}

	public void setPartijaNewLookup(PartijaNewLookup partijaNewLookup) {
		this.partijaNewLookup = partijaNewLookup;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + DRZAV_FIELD)
	public DrzavaNewLookup getDrzavaNewLookup() {
		if (drzavaNewLookup == null) {
			drzavaNewLookup = new DrzavaNewLookup();
		}
		return drzavaNewLookup;
	}

	public void setDrzavaNewLookup(DrzavaNewLookup drzavaNewLookup) {
		this.drzavaNewLookup = drzavaNewLookup;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + MJREZ_FIELD)
	public MjestoNewLookup getMjestoNewLookup() {
		if (mjestoNewLookup == null) {
			mjestoNewLookup = new MjestoNewLookup();
		}
		return mjestoNewLookup;
	}

	public void setMjestoNewLookup(MjestoNewLookup mjestoNewLookup) {
		this.mjestoNewLookup = mjestoNewLookup;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + MJNER_FIELD)
	public String getMjestoNerezident() {
		return mjestoNerezident;
	}

	public void setMjestoNerezident(String mjestoNerezident) {
		this.mjestoNerezident = mjestoNerezident;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + POSTA_FIELD)
	public String getPostaNerezident() {
		return postaNerezident;
	}

	public void setPostaNerezident(String postaNerezident) {
		this.postaNerezident = postaNerezident;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + NASLO_FIELD)
	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getNazivKomitenta() {
		return nazivKomitenta;
	}

	public void setNazivKomitenta(String nazivKomitenta) {
		this.nazivKomitenta = nazivKomitenta;
	}

	public String getNazivTipaLokacije() {
		return nazivTipaLokacije;
	}

	public void setNazivTipaLokacije(String nazivTipaLokacije) {
		this.nazivTipaLokacije = nazivTipaLokacije;
	}

	public String getMjnerLok() {
		return mjnerLok;
	}

	public void setMjnerLok(String mjnerLok) {
		this.mjnerLok = mjnerLok;
	}

	public Integer getSifraMjesta() {
		return sifraMjesta;
	}

	public void setSifraMjesta(Integer sifraMjesta) {
		this.sifraMjesta = sifraMjesta;
	}

	public String getMjesto() {
		return mjesto;
	}

	public void setMjesto(String mjesto) {
		this.mjesto = mjesto;
	}

	public String getPttbr() {
		return pttbr;
	}

	public void setPttbr(String pttbr) {
		this.pttbr = pttbr;
	}

	public Integer getSifraPartije() {
		return sifraPartije;
	}

	public void setSifraPartije(Integer sifraPartije) {
		this.sifraPartije = sifraPartije;
	}

	public Integer getSifraKomitenta() {
		return sifraKomitenta;
	}

	public void setSifraKomitenta(Integer sifraKomitenta) {
		this.sifraKomitenta = sifraKomitenta;
	}

	public String getMaticniBrojKomitenta() {
		return maticniBrojKomitenta;
	}

	public void setMaticniBrojKomitenta(String maticniBrojKomitenta) {
		this.maticniBrojKomitenta = maticniBrojKomitenta;
	}

	public String getOibKomitenta() {
		return oibKomitenta;
	}

	public void setOibKomitenta(String oibKomitenta) {
		this.oibKomitenta = oibKomitenta;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getOznakaDrzaveDvoslovna() {
		return oznakaDrzaveDvoslovna;
	}

	public void setOznakaDrzaveDvoslovna(String oznakaDrzaveDvoslovna) {
		this.oznakaDrzaveDvoslovna = oznakaDrzaveDvoslovna;
	}

}