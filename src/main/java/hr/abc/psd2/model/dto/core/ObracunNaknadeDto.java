package hr.abc.psd2.model.dto.core;

import hr.abc.psd2.annotation.IFilter;
import hr.abc.psd2.model.dto.GenericDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Osnovni DTO za obraÄŤun nakande koji se koristi kod samo obraÄŤuna i kod komunikacije s kif/kuf modulom.
 * 
 * @author Matija HlapÄŤiÄ‡
 */
public class ObracunNaknadeDto extends GenericDto<Object> implements Serializable {

	private static final long serialVersionUID = 1L;
	// fields
	private Integer sifraTarifneStavke; // tarifna stavka prema kojoj se obraÄŤunava tarifna stavka
	private Integer sifraTipaNalogaObracuna; // tip naloga prema kojemu se obraÄŤunava naknada - veza grupa naknade
	private Integer sifraTipaNalogaKnjizenja; // Ĺˇifra tipa naloga prema kojoj se obraÄŤun naknade knjiĹľi
	private Integer sifraKlaseNalogaKnjizenja; // Ĺˇifra klase naloga prema kojoj se obraÄŤun naknade knjiĹľi
	private String sifraValute; // Ĺˇifra valute naknade/rspona
	private Date datumObracunaNaknade; // datum obraÄŤuna naknade - default today
	private BigDecimal iznosTransakcije; // iznos transakcije
	private String sifraValuteTransakcije; // valuta transakcije
	private Integer naplataNaknade; // naplata naknade (0 - po fakturi, 1 - odmah)
	private String stranaNaplate; // da li je strana naknade dugovna ili potraĹľna (D/P)
	
	private Integer sifraNaknadaArh;
	private BigDecimal posto; // postotak naknade
	private BigDecimal minIznos; // minimalni iznos naknade
	private BigDecimal maxIznos; // maksimalni iznos naknade
	private BigDecimal donjiRasponIznosa; // donji raspon iznosa
	private BigDecimal gornjiRasponIznosa; // gornji raspon iznosa
	
	private Date datumPocetka; // poÄŤetak intervala
	private Date datumKraja; // kraj intervala
	
	private Integer sifraPartije; // povlaĹˇtena naknada za partiju
	private Integer sifraTipPartije;	
	private String brojPartije; // broj partije
	
	private Integer sifraPaketa; // povlaĹˇtena naknada za paket
	private Integer sifraProizvoda; // povlaĹˇtena naknada za proizvod
	private Integer sifraNaloga; // sifra naloga na osnovu kojeg se knjizi na temelju kojeg nastaju prometi fakture
	private Integer sifraNalogaIzvor; // sifra izvornog naloga na osnovu kojeg nastaju stavke fakture
	private String sektorPartije; // sifra sektora partije
	
	private BigDecimal brutoIznos; // bruto iznos naknade
	
	private Integer modulIniciranja; //modul koji inicira kreiranje fakture
	
	// povlaštena naknada za komitenta
	private Integer sifraKomitentaPovlastena;
	
	private BigDecimal dodIznos; // dodatni iznos naknade

	/** ura ira polja */
	
	private BigDecimal netoIznos; // neto iznos naknade
	private BigDecimal porezIznos; // iznos poreza
	private BigDecimal priznatiPorezIznos; // iznos priznatog poreza
	
	private Integer sifraPartijeFakture; // partija fakture iz ura/ira
	private String brojFakture; // broj partije fakture
    private String oznakaFakture; // oznaka fakture (alter_par)
    private Integer nivoPovjerljivostiPartijeAnalitike; // nivo povjerljivosti (nivop_par)
	
	private Integer sifraTipaPartijeFakture;
	private String sifraPoslovnice; //sifra poslovnice
	private Integer sifraKomitenta; //sifra komitenta
    private String sifraBlagajne;
	private String status; 
	private Integer kolicina; //sifra komitenta
	private String sifraTipaVezeNak;  //sifra tipa veze naknade na temelju koje se određuje tip izlazne fakture
	private String opisFakture;
	private boolean storno = false;
	private boolean sef = false;
	private boolean pdv = false;
    private boolean mjesecna = false;
	private Date datumNaplate; //datum naplate koji se unosi kod fakturiranja
    private String partijaNaplate;
	
	/** ura ira polja */
	
	// utility fields
	private String oznakaTarifneStavke; // oznaka tarifne stavke
	private String nazivTarifneStavke; // naziv tarifne stavke
	
	private String identifikatorProizvoda; // id proizvoda
	
	private Integer sifraPorez; //sifra porez
	
	/** vremenska razgraniÄŤenja */
	private Date datumRazgranicenja; 
	
	private Integer brojRata;
	private String nadDioPrihod, nadDioPrihodRasknjizenje, sifraValutePrihod, sifraValutePrihodRasknjizenje;
	/** vremenska razgraniÄŤenja */
	
	@IFilter(entityField = "tip_partije.klapa_tip")
	private Integer sifraKlasePartije;
    
    private Integer sifraPartijeZaStorno;    
	
	// constructors
	public ObracunNaknadeDto() {
		super();
	}

	public ObracunNaknadeDto(Integer sifraTarifneStavke, Integer sifraTipaNalogaObracuna, Integer sifraKlaseNalogaKnjizenja, String sifraValute, Date datumObracunaNaknade, BigDecimal posto, BigDecimal minIznos, BigDecimal maxIznos, Integer sifraPartije, Integer sifraPaketa, Integer sifraProizvoda, BigDecimal iznosTransakcije, BigDecimal brutoIznos, BigDecimal netoIznos, BigDecimal porezIznos) {
		super();
		this.sifraTarifneStavke = sifraTarifneStavke;
		this.sifraTipaNalogaObracuna = sifraTipaNalogaObracuna;
		this.sifraKlaseNalogaKnjizenja = sifraKlaseNalogaKnjizenja;
		this.sifraValute = sifraValute;
		this.datumObracunaNaknade = datumObracunaNaknade;
		this.posto = posto;
		this.minIznos = minIznos;
		this.maxIznos = maxIznos;
		this.sifraPartije = sifraPartije;
		this.sifraPaketa = sifraPaketa;
		this.sifraProizvoda = sifraProizvoda;
		this.iznosTransakcije = iznosTransakcije;
		this.brutoIznos = brutoIznos;
		this.netoIznos = netoIznos;
		this.porezIznos = porezIznos;
	}

	public ObracunNaknadeDto(Integer sifraTarifneStavke, Integer sifraTipaNalogaObracuna, Date datumObracunaNaknade, BigDecimal iznosTransakcije, String sifraValuteTransakcije) {
		super();
		this.sifraTarifneStavke = sifraTarifneStavke;
		this.sifraTipaNalogaObracuna = sifraTipaNalogaObracuna;
		this.datumObracunaNaknade = datumObracunaNaknade;
		this.iznosTransakcije = iznosTransakcije;
		this.sifraValuteTransakcije = sifraValuteTransakcije;
	}

	// getters & setters
	public Integer getSifraTarifneStavke() {
		return sifraTarifneStavke;
	}

	public void setSifraTarifneStavke(Integer sifraTarifneStavke) {
		this.sifraTarifneStavke = sifraTarifneStavke;
	}

	public Integer getSifraTipaNalogaObracuna() {
		return sifraTipaNalogaObracuna;
	}

	public void setSifraTipaNalogaObracuna(Integer sifraTipaNalogaObracuna) {
		this.sifraTipaNalogaObracuna = sifraTipaNalogaObracuna;
	}

	public Integer getSifraKlaseNalogaKnjizenja() {
		return sifraKlaseNalogaKnjizenja;
	}

	public void setSifraKlaseNalogaKnjizenja(Integer sifraKlaseNalogaKnjizenja) {
		this.sifraKlaseNalogaKnjizenja = sifraKlaseNalogaKnjizenja;
	}

	public String getSifraValute() {
		return sifraValute;
	}

	public void setSifraValute(String sifraValute) {
		this.sifraValute = sifraValute;
	}

	public Integer getSifraPartije() {
		
		return sifraPartije;
	}

	public void setSifraPartije(Integer sifraPartije) {
		this.sifraPartije = sifraPartije;
	}

	public Integer getSifraPaketa() {
		return sifraPaketa;
	}

	public void setSifraPaketa(Integer sifraPaketa) {
		this.sifraPaketa = sifraPaketa;
	}

	public Integer getSifraProizvoda() {
		return sifraProizvoda;
	}

	public void setSifraProizvoda(Integer sifraProizvoda) {
		this.sifraProizvoda = sifraProizvoda;
	}

	public BigDecimal getBrutoIznos() {
		return brutoIznos;
	}

	public void setBrutoIznos(BigDecimal brutoIznos) {
		this.brutoIznos = brutoIznos;
	}

	public BigDecimal getNetoIznos() {
		return netoIznos;
	}

	public void setNetoIznos(BigDecimal netoIznos) {
		this.netoIznos = netoIznos;
	}

	public BigDecimal getPorezIznos() {
		return porezIznos;
	}

	public void setPorezIznos(BigDecimal porezIznos) {
		this.porezIznos = porezIznos;
	}

	public BigDecimal getPriznatiPorezIznos() {
		return priznatiPorezIznos;
	}

	public void setPriznatiPorezIznos(BigDecimal priznatiPorezIznos) {
		this.priznatiPorezIznos = priznatiPorezIznos;
	}

	public Date getDatumObracunaNaknade() {
		return datumObracunaNaknade;
	}

	public void setDatumObracunaNaknade(Date datumObracunaNaknade) {
		this.datumObracunaNaknade = datumObracunaNaknade;
	}

	public BigDecimal getPosto() {
		return posto;
	}

	public void setPosto(BigDecimal posto) {
		this.posto = posto;
	}

	public BigDecimal getMinIznos() {
		return minIznos;
	}

	public void setMinIznos(BigDecimal minIznos) {
		this.minIznos = minIznos;
	}

	public BigDecimal getMaxIznos() {
		return maxIznos;
	}

	public void setMaxIznos(BigDecimal maxIznos) {
		this.maxIznos = maxIznos;
	}

	public BigDecimal getIznosTransakcije() {
		return iznosTransakcije;
	}

	public void setIznosTransakcije(BigDecimal iznosTransakcije) {
		this.iznosTransakcije = iznosTransakcije;
	}

	public String getOznakaTarifneStavke() {
		return oznakaTarifneStavke;
	}

	public void setOznakaTarifneStavke(String oznakaTarifneStavke) {
		this.oznakaTarifneStavke = oznakaTarifneStavke;
	}

	public Date getDatumPocetka() {
		return datumPocetka;
	}

	public void setDatumPocetka(Date datumPocetka) {
		this.datumPocetka = datumPocetka;
	}

	public Date getDatumKraja() {
		return datumKraja;
	}

	public void setDatumKraja(Date datumKraja) {
		this.datumKraja = datumKraja;
	}

	public String getBrojPartije() {
		return brojPartije;
	}

	public void setBrojPartije(String brojPartije) {
		this.brojPartije = brojPartije;
	}

	public String getIdentifikatorProizvoda() {
		return identifikatorProizvoda;
	}

	public void setIdentifikatorProizvoda(String identifikatorProizvoda) {
		this.identifikatorProizvoda = identifikatorProizvoda;
	}

	public BigDecimal getDonjiRasponIznosa() {
		return donjiRasponIznosa;
	}

	public void setDonjiRasponIznosa(BigDecimal donjiRasponIznosa) {
		this.donjiRasponIznosa = donjiRasponIznosa;
	}

	public BigDecimal getGornjiRasponIznosa() {
		return gornjiRasponIznosa;
	}

	public void setGornjiRasponIznosa(BigDecimal gornjiRasponIznosa) {
		this.gornjiRasponIznosa = gornjiRasponIznosa;
	}

	public Integer getSifraPartijeFakture() {
		return sifraPartijeFakture;
	}

	public void setSifraPartijeFakture(Integer sifraPartijeFakture) {
		this.sifraPartijeFakture = sifraPartijeFakture;
	}
	
	public String getSifraPoslovnice() {
		return sifraPoslovnice;
	}

	public void setSifraPoslovnice(String sifraPoslovnice) {
		this.sifraPoslovnice = sifraPoslovnice;
	}

	public Integer getSifraKomitenta() {
		return sifraKomitenta;
	}

	public void setSifraKomitenta(Integer sifraKomitenta) {
		this.sifraKomitenta = sifraKomitenta;
	}

    public String getSifraBlagajne() {
        return sifraBlagajne;
    }

    public void setSifraBlagajne(String sifraBlagajne) {
        this.sifraBlagajne = sifraBlagajne;
    }

    public Integer getSifraTipaPartijeFakture() {
		return sifraTipaPartijeFakture;
	}

	public void setSifraTipaPartijeFakture(Integer sifraTipaPartijeFakture) {
		this.sifraTipaPartijeFakture = sifraTipaPartijeFakture;
	}

	public Integer getSifraNaloga() {
		return sifraNaloga;
	}

	public void setSifraNaloga(Integer sifraNaloga) {
		this.sifraNaloga = sifraNaloga;
	}

	public String getSektorPartije() {
		return sektorPartije;
	}

	public void setSektorPartije(String sektorPartije) {
		this.sektorPartije = sektorPartije;
	}

	public Integer getModulIniciranja() {
		return modulIniciranja;
	}

	public void setModulIniciranja(Integer modulIniciranja) {
		this.modulIniciranja = modulIniciranja;
	}

	public String getSifraValuteTransakcije() {
		return sifraValuteTransakcije;
	}

	public void setSifraValuteTransakcije(String sifraValuteTransakcije) {
		this.sifraValuteTransakcije = sifraValuteTransakcije;
	}

	public Integer getSifraNalogaIzvor() {
		return sifraNalogaIzvor;
	}

	public void setSifraNalogaIzvor(Integer sifraNalogaIzvor) {
		this.sifraNalogaIzvor = sifraNalogaIzvor;
	}
	
		public String getNazivTarifneStavke() {
		return nazivTarifneStavke;
	}

	public void setNazivTarifneStavke(String nazivTarifneStavke) {
		this.nazivTarifneStavke = nazivTarifneStavke;
	}

	public Integer getSifraNaknadaArh() {
		return sifraNaknadaArh;
	}

	public void setSifraNaknadaArh(Integer sifraNaknadaArh) {
		this.sifraNaknadaArh = sifraNaknadaArh;
	}

	public Integer getBrojRata() {
		return brojRata;
	}

	public void setBrojRata(Integer brojRata) {
		this.brojRata = brojRata;
	}

	public String getNadDioPrihod() {
		return nadDioPrihod;
	}

	public void setNadDioPrihod(String nadDioPrihod) {
		this.nadDioPrihod = nadDioPrihod;
	}

	public String getNadDioPrihodRasknjizenje() {
		return nadDioPrihodRasknjizenje;
	}

	public void setNadDioPrihodRasknjizenje(String nadDioPrihodRasknjizenje) {
		this.nadDioPrihodRasknjizenje = nadDioPrihodRasknjizenje;
	}

	public String getSifraValutePrihod() {
		return sifraValutePrihod;
	}

	public void setSifraValutePrihod(String sifraValutePrihod) {
		this.sifraValutePrihod = sifraValutePrihod;
	}

	public String getSifraValutePrihodRasknjizenje() {
		return sifraValutePrihodRasknjizenje;
	}

	public void setSifraValutePrihodRasknjizenje(String sifraValutePrihodRasknjizenje) {
		this.sifraValutePrihodRasknjizenje = sifraValutePrihodRasknjizenje;
	}

    public String getStranaNaplate() {
        return stranaNaplate;
    }

    public void setStranaNaplate(String stranaNaplate) {
        this.stranaNaplate = stranaNaplate;
    }

    public Integer getNaplataNaknade() {
        return naplataNaknade;
    }

    public void setNaplataNaknade(Integer naplataNaknade) {
        this.naplataNaknade = naplataNaknade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getKolicina() {
        return kolicina;
    }

    public void setKolicina(Integer kolicina) {
        this.kolicina = kolicina;
    }

    public String getBrojFakture() {
        return brojFakture;
    }

    public void setBrojFakture(String brojFakture) {
        this.brojFakture = brojFakture;
    }

    public String getOznakaFakture() {
        return oznakaFakture;
    }

    public void setOznakaFakture(String oznakaFakture) {
        this.oznakaFakture = oznakaFakture;
    }

    public Integer getNivoPovjerljivostiPartijeAnalitike() {
        return nivoPovjerljivostiPartijeAnalitike;
    }

    public void setNivoPovjerljivostiPartijeAnalitike(Integer nivoPovjerljivostiPartijeAnalitike) {
        this.nivoPovjerljivostiPartijeAnalitike = nivoPovjerljivostiPartijeAnalitike;
    }

    public Integer getSifraKlasePartije() {
		return sifraKlasePartije;
	}

	public void setSifraKlasePartije(Integer sifraKlasePartije) {
		this.sifraKlasePartije = sifraKlasePartije;
	}

	public boolean isStorno() {
		return storno;
	}

	public void setStorno(boolean storno) {
		this.storno = storno;
	}

	public String getSifraTipaVezeNak() {
		return sifraTipaVezeNak;
	}

	public void setSifraTipaVezeNak(String sifraTipaVezeNak) {
		this.sifraTipaVezeNak = sifraTipaVezeNak;
	}

	public Integer getSifraTipaNalogaKnjizenja() {
		return sifraTipaNalogaKnjizenja;
	}

	public void setSifraTipaNalogaKnjizenja(Integer sifraTipaNalogaKnjizenja) {
		this.sifraTipaNalogaKnjizenja = sifraTipaNalogaKnjizenja;
	}

    /**
     * @return the sifraPartijeZaStorno
     */
    public Integer getSifraPartijeZaStorno() {
        return sifraPartijeZaStorno;
    }

    /**
     * @param sifraPartijeZaStorno the sifraPartijeZaStorno to set
     */
    public void setSifraPartijeZaStorno(Integer sifraPartijeZaStorno) {
        this.sifraPartijeZaStorno = sifraPartijeZaStorno;
    }
    
    public Integer getSifraTipPartije() {
		return sifraTipPartije;
	}

	public void setSifraTipPartije(Integer sifraTipPartije) {
		this.sifraTipPartije = sifraTipPartije;
	}
    
	public boolean isSef() {
		return sef;
	}

	public void setSef(boolean sef) {
		this.sef = sef;
	}
	
	public Integer getSifraPorez() {
		return sifraPorez;
	}

	public void setSifraPorez(Integer sifraPorez) {
		this.sifraPorez = sifraPorez;
	}
	
	public Date getDatumRazgranicenja() {
		return datumRazgranicenja;
	}

	public void setDatumRazgranicenja(Date datumRazgranicenja) {
		this.datumRazgranicenja = datumRazgranicenja;
	}

	public Integer getSifraKomitentaPovlastena() {
		return sifraKomitentaPovlastena;
	}

	public void setSifraKomitentaPovlastena(Integer sifraKomitentaPovlastena) {
		this.sifraKomitentaPovlastena = sifraKomitentaPovlastena;
	}

	public BigDecimal getDodIznos() {
		return dodIznos;
	}

	public void setDodIznos(BigDecimal dodIznos) {
		this.dodIznos = dodIznos;
	}

	public String getOpisFakture() {
		return opisFakture;
	}

	public void setOpisFakture(String opisFakture) {
		this.opisFakture = opisFakture;
	}

	public boolean isPdv() {
		return pdv;
	}

	public void setPdv(boolean pdv) {
		this.pdv = pdv;
	}

    public boolean isMjesecna() {
        return mjesecna;
    }

    public void setMjesecna(boolean mjesecna) {
        this.mjesecna = mjesecna;
    }

	public Date getDatumNaplate() {
		return datumNaplate;
	}

	public void setDatumNaplate(Date datumNaplate) {
		this.datumNaplate = datumNaplate;
	}

    public String getPartijaNaplate() {
        return partijaNaplate;
    }

    public void setPartijaNaplate(String partijaNaplate) {
        this.partijaNaplate = partijaNaplate;
    }
}
