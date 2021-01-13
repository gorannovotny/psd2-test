package hr.abc.psd2.model.dto.core;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import hr.abc.psd2.model.dto.GenericDto;

/**
 * Transfer object koji reprezentira jedan transakcijski promet.
 * 
 * @author Matija Hlapčić
 */
public class PrometDto extends GenericDto<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	// fields
	private BigDecimal valutniDugovniIznos;
	private BigDecimal valutniPotrazniIznos;
	private BigDecimal domicilniDugovniIznos;
	private BigDecimal domicilniPotrazniIznos;
	private Integer sifraDijelaPartije;
	private Integer sifraKnjiznogNaloga;
	private Integer sifraPartije;
	// optional fields
	private String brojRacuna; // BBAN ili IBAN
	private String maticniBrojKomitenta; // mbr komitenta
	private String oznakaNadDijela; // oznaka nad dijela	
	private String stranaKnjizenja; // dugovna ili potražna strana
	private String sifraValute; // šifra valute prometa
	private String oznakaValute; // oznaka valute prometa
	private BigDecimal iznos; // Iznos prometa u valuti kod zadavanja knjiženja
	private BigDecimal iznosDomicilni; // Iznos prometa u domicilnoj valuti kod zadavanja knjiženja
	private String nazivKomitenta; // Pomoćno polje koje drži naziv komitenta vlasnika partije na koju se knjiži
	private Integer sifraTipaPartije; // šifra tipa partije računa na koji se knjiži
	private String sifraKonta; // pomoćno polje koje nosi konto na koji se knjižilo
	private String nazivKonta; // pomoćno polje koje nosi naziv konta na koji se knjižilo	
	private String poslovnica; // poslovnica pripadnosti partije
	private String adresaPoslovnice;
	private String statusNaloga; // status knjižnog naloga
	private Date datumValute; // datum valute i knjizenja knjižnog naloga
    private Date datumKnjizenja = Calendar.getInstance().getTime(); // koristi se kod izračuna raspoloživog, ako je napunjeno upit radi do tog datuma
	private String svrhaNaloga; // opis ili svrha naloga
	private boolean valutnaKlauzula;
	// extra fields
	private BigDecimal iznosValutni; // pomoćno polje za raspoloživo
	private String indikatorAktive; // aktivni ili pasivni konto
	private BigDecimal iznosTecaja; // iznos tečaja 
    private Integer hash; // hash za identifikaciju prometa (ako je potrebna referenca
    private String statusPartije;
    private String referenca; // referenca naloga
    private Integer brojIzvoda;
    private BigDecimal rezervacija, okvir;
    private boolean skipFastRaspolozivoCheck = false;
    private Boolean domicilnaValutaUvijek = Boolean.FALSE; // flag se diže u slučaju kada se knjiži promet koji je uvijek u kunama (npr. naknada kod deviznih transakcija, gdje se na platnom nalogu šalje strana valuta transakcije)
    // liste dijelova/nad dijelova koje ulaze u raspoloživo ako su zadane
    List<Integer> listSifreDijelovaPartijeRaspolozivo;
    List<String> listOznakeNadDioRaspolozivo;
	
	// constructors
    public PrometDto(String stranaKnjizenja, BigDecimal valutniDugovniIznos, BigDecimal valutniPotrazniIznos, BigDecimal domicilniDugovniIznos, BigDecimal domicilniPotrazniIznos, Integer sifraDijelaPartije, Integer sifraKnjiznogNaloga, Integer sifraPartije, Integer sifraTipaPartije) {
		super();
		this.stranaKnjizenja = stranaKnjizenja;
		this.valutniDugovniIznos = valutniDugovniIznos;
		this.valutniPotrazniIznos = valutniPotrazniIznos;
		this.domicilniDugovniIznos = domicilniDugovniIznos;
		this.domicilniPotrazniIznos = domicilniPotrazniIznos;
		this.sifraDijelaPartije = sifraDijelaPartije;
		this.sifraKnjiznogNaloga = sifraKnjiznogNaloga;
		this.sifraPartije = sifraPartije;
		this.sifraTipaPartije = sifraTipaPartije;
	}
    
	public PrometDto(BigDecimal valutniDugovniIznos, BigDecimal valutniPotrazniIznos, BigDecimal domicilniDugovniIznos, BigDecimal domicilniPotrazniIznos, Integer sifraDijelaPartije, Integer sifraKnjiznogNaloga, Integer sifraPartije) {
		super();
		this.valutniDugovniIznos = valutniDugovniIznos;
		this.valutniPotrazniIznos = valutniPotrazniIznos;
		this.domicilniDugovniIznos = domicilniDugovniIznos;
		this.domicilniPotrazniIznos = domicilniPotrazniIznos;
		this.sifraDijelaPartije = sifraDijelaPartije;
		this.sifraKnjiznogNaloga = sifraKnjiznogNaloga;
		this.sifraPartije = sifraPartije;
	}

	public PrometDto(BigDecimal valutniDugovniIznos, BigDecimal valutniPotrazniIznos, BigDecimal domicilniDugovniIznos, BigDecimal domicilniPotrazniIznos, Integer sifraDijelaPartije, Integer sifraKnjiznogNaloga, Integer sifraPartije, String brojRacuna, String oznakaNadDijela, String stranaKnjizenja, String sifraValute, BigDecimal iznos, String nazivKomitenta, Integer sifraTipaPartije, String sifraKonta, String poslovnica, String statusNaloga, Date datumValute, String svrhaNaloga) {
		super();
		this.valutniDugovniIznos = valutniDugovniIznos;
		this.valutniPotrazniIznos = valutniPotrazniIznos;
		this.domicilniDugovniIznos = domicilniDugovniIznos;
		this.domicilniPotrazniIznos = domicilniPotrazniIznos;
		this.sifraDijelaPartije = sifraDijelaPartije;
		this.sifraKnjiznogNaloga = sifraKnjiznogNaloga;
		this.sifraPartije = sifraPartije;
		this.brojRacuna = brojRacuna;
		this.oznakaNadDijela = oznakaNadDijela;
		this.stranaKnjizenja = stranaKnjizenja;
		this.sifraValute = sifraValute;
		this.iznos = iznos;
		this.nazivKomitenta = nazivKomitenta;
		this.sifraTipaPartije = sifraTipaPartije;
		this.sifraKonta = sifraKonta;
		this.poslovnica = poslovnica;
		this.statusNaloga = statusNaloga;
		this.datumValute = datumValute;
		this.svrhaNaloga = svrhaNaloga;
	}

	public PrometDto(boolean isFirstCall, BigDecimal valutniDugovniIznos, BigDecimal valutniPotrazniIznos, BigDecimal domicilniDugovniIznos, BigDecimal domicilniPotrazniIznos, Integer sifraDijelaPartije, Integer sifraKnjiznogNaloga, Integer sifraPartije, String brojRacuna, String oznakaNadDijela, String stranaKnjizenja, String sifraValute, BigDecimal iznos, BigDecimal iznosDomicilni, String nazivKomitenta, Integer sifraTipaPartije, String sifraKonta, String poslovnica, String statusNaloga, Date datumValute, BigDecimal iznosValutni, String indikatorAktive, BigDecimal iznosTecaja, boolean valutnaKlauzula) {
		super(isFirstCall);
		this.valutniDugovniIznos = valutniDugovniIznos;
		this.valutniPotrazniIznos = valutniPotrazniIznos;
		this.domicilniDugovniIznos = domicilniDugovniIznos;
		this.domicilniPotrazniIznos = domicilniPotrazniIznos;
		this.sifraDijelaPartije = sifraDijelaPartije;
		this.sifraKnjiznogNaloga = sifraKnjiznogNaloga;
		this.sifraPartije = sifraPartije;
		this.brojRacuna = brojRacuna;
		this.oznakaNadDijela = oznakaNadDijela;
		this.stranaKnjizenja = stranaKnjizenja;
		this.sifraValute = sifraValute;
		this.iznos = iznos;
		this.iznosDomicilni = iznosDomicilni;
		this.nazivKomitenta = nazivKomitenta;
		this.sifraTipaPartije = sifraTipaPartije;
		this.sifraKonta = sifraKonta;
		this.poslovnica = poslovnica;
		this.statusNaloga = statusNaloga;
		this.datumValute = datumValute;
		this.iznosValutni = iznosValutni;
		this.indikatorAktive = indikatorAktive;
		this.iznosTecaja = iznosTecaja;
		this.valutnaKlauzula = valutnaKlauzula; 
	}

	public PrometDto() {
		super();
	}

	public PrometDto(String stranaKnjizenja) {
		super();
		this.stranaKnjizenja = stranaKnjizenja;
	}

	// getters & setters
	public BigDecimal getValutniDugovniIznos() {
		return valutniDugovniIznos;
	}

	public void setValutniDugovniIznos(BigDecimal valutniDugovniIznos) {
		this.valutniDugovniIznos = valutniDugovniIznos;
	}

	public BigDecimal getValutniPotrazniIznos() {
		return valutniPotrazniIznos;
	}

	public void setValutniPotrazniIznos(BigDecimal valutniPotrazniIznos) {
		this.valutniPotrazniIznos = valutniPotrazniIznos;
	}

	public BigDecimal getDomicilniDugovniIznos() {
		return domicilniDugovniIznos;
	}

	public void setDomicilniDugovniIznos(BigDecimal domicilniDugovniIznos) {
		this.domicilniDugovniIznos = domicilniDugovniIznos;
	}

	public BigDecimal getDomicilniPotrazniIznos() {
		return domicilniPotrazniIznos;
	}

	public void setDomicilniPotrazniIznos(BigDecimal domicilniPotrazniIznos) {
		this.domicilniPotrazniIznos = domicilniPotrazniIznos;
	}

	public Integer getSifraDijelaPartije() {
		return sifraDijelaPartije;
	}

	public void setSifraDijelaPartije(Integer sifraDijelaPartije) {
		this.sifraDijelaPartije = sifraDijelaPartije;
	}

	public Integer getSifraKnjiznogNaloga() {
		return sifraKnjiznogNaloga;
	}

	public void setSifraKnjiznogNaloga(Integer sifraKnjiznogNaloga) {
		this.sifraKnjiznogNaloga = sifraKnjiznogNaloga;
	}

	public Integer getSifraPartije() {
		return sifraPartije;
	}

	public void setSifraPartije(Integer sifraPartije) {
		this.sifraPartije = sifraPartije;
	}

	public String getBrojRacuna() {
		return brojRacuna;
	}

	public void setBrojRacuna(String brojRacuna) {
		this.brojRacuna = brojRacuna;
	}

	public String getStranaKnjizenja() {
		return stranaKnjizenja;
	}

	public void setStranaKnjizenja(String stranaKnjizenja) {
		this.stranaKnjizenja = stranaKnjizenja;
	}

	public String getOznakaNadDijela() {
		return oznakaNadDijela;
	}

	public void setOznakaNadDijela(String oznakaNadDijela) {
		this.oznakaNadDijela = oznakaNadDijela;
	}

	public String getSifraValute() {
		return sifraValute;
	}

	public void setSifraValute(String sifraValute) {
		this.sifraValute = sifraValute;
	}

	public BigDecimal getIznos() {
		return iznos;
	}

	public void setIznos(BigDecimal iznos) {
		this.iznos = iznos;
	}

	public String getNazivKomitenta() {
		return nazivKomitenta;
	}

	public void setNazivKomitenta(String nazivKomitenta) {
		this.nazivKomitenta = nazivKomitenta;
	}

	public Integer getSifraTipaPartije() {
		return sifraTipaPartije;
	}

	public void setSifraTipaPartije(Integer sifraTipaPartije) {
		this.sifraTipaPartije = sifraTipaPartije;
	}

	public String getSifraKonta() {
		return sifraKonta;
	}

	public void setSifraKonta(String sifraKonta) {
		this.sifraKonta = sifraKonta;
	}

	public String getPoslovnica() {
		return poslovnica;
	}

	public void setPoslovnica(String poslovnica) {
		this.poslovnica = poslovnica;
	}

	public String getStatusNaloga() {
		return statusNaloga;
	}

	public void setStatusNaloga(String statusNaloga) {
		this.statusNaloga = statusNaloga;
	}

	public Date getDatumValute() {
		return datumValute;
	}

	public void setDatumValute(Date datumValute) {
		this.datumValute = datumValute;
	}

	public BigDecimal getIznosValutni() {
		return iznosValutni;
	}

	public void setIznosValutni(BigDecimal iznosValutni) {
		this.iznosValutni = iznosValutni;
	}

	public String getIndikatorAktive() {
		return indikatorAktive;
	}

	public void setIndikatorAktive(String indikatorAktive) {
		this.indikatorAktive = indikatorAktive;
	}

	public BigDecimal getIznosTecaja() {
		return iznosTecaja;
	}

	public void setIznosTecaja(BigDecimal iznosTecaja) {
		this.iznosTecaja = iznosTecaja;
	}

	public BigDecimal getIznosDomicilni() {
		return iznosDomicilni;
	}

	public void setIznosDomicilni(BigDecimal iznosDomicilni) {
		this.iznosDomicilni = iznosDomicilni;
	}

	public String getNazivKonta() {
		return nazivKonta;
	}

	public void setNazivKonta(String nazivKonta) {
		this.nazivKonta = nazivKonta;
	}

    public String getStatusPartije() {
		return statusPartije;
	}

	public void setStatusPartije(String statusPartije) {
		this.statusPartije = statusPartije;
	}

	public String getSvrhaNaloga() {
		return svrhaNaloga;
	}

	public void setSvrhaNaloga(String svrhaNaloga) {
		this.svrhaNaloga = svrhaNaloga;
	}

	public Date getDatumKnjizenja() {
		return datumKnjizenja;
	}

	public void setDatumKnjizenja(Date datumKnjizenja) {
		this.datumKnjizenja = datumKnjizenja;
	}

	public List<Integer> getListSifreDijelovaPartijeRaspolozivo() {
		return listSifreDijelovaPartijeRaspolozivo;
	}

	public void setListSifreDijelovaPartijeRaspolozivo(List<Integer> listSifreDijelovaPartijeRaspolozivo) {
		this.listSifreDijelovaPartijeRaspolozivo = listSifreDijelovaPartijeRaspolozivo;
	}

	public List<String> getListOznakeNadDioRaspolozivo() {
		return listOznakeNadDioRaspolozivo;
	}

	public void setListOznakeNadDioRaspolozivo(List<String> listOznakeNadDioRaspolozivo) {
		this.listOznakeNadDioRaspolozivo = listOznakeNadDioRaspolozivo;
	}

	public String getReferenca() {
		return referenca;
	}

	public void setReferenca(String referenca) {
		this.referenca = referenca;
	}

	public Integer getBrojIzvoda() {
		return brojIzvoda;
	}

	public void setBrojIzvoda(Integer brojIzvoda) {
		this.brojIzvoda = brojIzvoda;
	}

	public String getMaticniBrojKomitenta() {
		return maticniBrojKomitenta;
	}

	public void setMaticniBrojKomitenta(String maticniBrojKomitenta) {
		this.maticniBrojKomitenta = maticniBrojKomitenta;
	}

	public String getOznakaValute() {
		return oznakaValute;
	}

	public void setOznakaValute(String oznakaValute) {
		this.oznakaValute = oznakaValute;
	}

	public String getAdresaPoslovnice() {
		return adresaPoslovnice;
	}

	public void setAdresaPoslovnice(String adresaPoslovnice) {
		this.adresaPoslovnice = adresaPoslovnice;
	}

	public BigDecimal getRezervacija() {
		return rezervacija;
	}

	public void setRezervacija(BigDecimal rezervacija) {
		this.rezervacija = rezervacija;
	}

	public BigDecimal getOkvir() {
		return okvir;
	}

	public void setOkvir(BigDecimal okvir) {
		this.okvir = okvir;
	}

	public boolean isSkipFastRaspolozivoCheck() {
		return skipFastRaspolozivoCheck;
	}

	public void setSkipFastRaspolozivoCheck(boolean skipFastRaspolozivoCheck) {
		this.skipFastRaspolozivoCheck = skipFastRaspolozivoCheck;
	}

	/**
     * @return the hash
     */
    public Integer getHash() {
        return hash;
    }

    /**
     * @param hash the hash to set
     */
    public void setHash(Integer hash) {
        this.hash = hash;
    }
    
	public boolean isValutnaKlauzula() {
		return valutnaKlauzula;
	}

	public void setValutnaKlauzula(boolean valutnaKlauzula) {
		this.valutnaKlauzula = valutnaKlauzula;
	}

	public Boolean isDomicilnaValutaUvijek() {
		return domicilnaValutaUvijek;
	}

	public void setDomicilnaValutaUvijek(Boolean domicilnaValutaUvijek) {
		this.domicilnaValutaUvijek = domicilnaValutaUvijek;
	}

}
