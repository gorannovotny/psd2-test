package hr.abc.psd2.model.dto.ais;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Pomoćni objekt kod formiranja izvješća koja prikazuju promete i stanja.
 * 
 * Može se i poželjno je koristiti ga u više različitih izvješća.
 * 
 * @author Matija Hlapčić
 */
public class PrometStanjePartijeDto implements Serializable {

	// fields 
	private static final long serialVersionUID = 1L;
	private Integer sifraModula, sifraKomitenta, sifraPartije, sifraNadDijela, sifraDijela, sifraNaloga, sifraTipaNaloga, sifraKlasePartije, sifraPlatnogNaloga, prioritetNaloga, sifraPrometa;
	private String sifraPoslovnice, sifraValute, sifraKonta, brojPartije, oznakaNadDijela, nazivNadDijela, oznakaValute, sifraPoslovnicePartije, oibKomitenta, maticniBrojKomitenta, nazivKomitenta, adresaKomitenta, kucniBroj, mjestoKomitenta, nazivTipaPartije, nazivTipaNaloga, reportKey, sifraValuteKonverzije, brojAlternativnePartije, vrstaKonta, brojPartijeDruge, ibanPartije, uplata, isplata, modelZaduzenja, pozivNaBrojZaduzenja, modelOdobrenja, pozivNaBrojOdobrenja, nacinIzvrsenja, sifraLikvidatora, racunZaduzenja, racunOdobrenja, nazivZaduzenja, nazivOdobrenja, referencaNaloga;
	private Date datumPocetka, datumKraja, datumKnjizenja, datumValute, vrijemeKnjizenja, vrijemeUnosaNaloga;
	private BigDecimal valutniDugovniIznos, valutniPotrazniIznos, domicilniDugovniIznos, domicilniPotrazniIznos, pocetniValutniSaldo, pocetniDomicilniSaldo, saldoReda, saldoRedaKonvertiran, pocetniValutniSaldoDugovno, pocetniValutniSaldoPotrazno, iznosNaloga;
	private boolean pojedinacno, stanjaRazlicitaOdNule, ukljuciFakture, faktureUpit;
	private boolean withBalance;
	// lookup
	private String opisValute;
	
	private String svrha, nalogodavac, statusNaloga;
	
	// contructors
	public PrometStanjePartijeDto(Integer sifraModula, Integer sifraKomitenta, Integer sifraPartije, Integer sifraNadDijela, Integer sifraDijela, String sifraPoslovnice, String sifraValute, String sifraKonta, Date datumPocetka, Date datumKraja, boolean pojedinacno) {
		super();
		this.sifraModula = sifraModula;
		this.sifraKomitenta = sifraKomitenta;
		this.sifraPartije = sifraPartije;
		this.sifraNadDijela = sifraNadDijela;
		this.sifraDijela = sifraDijela;
		this.sifraPoslovnice = sifraPoslovnice;
		this.sifraValute = sifraValute;
		this.sifraKonta = sifraKonta;
		this.datumPocetka = datumPocetka;
		this.datumKraja = datumKraja;
		this.pojedinacno = pojedinacno;
	}
	
	public PrometStanjePartijeDto(String brojPartije, Integer sifraDijela, String oznakaNadDijela, String nazivNadDijela, String sifraKonta, String sifraValute, String oznakaValute,
                                  Integer sifraNaloga, Date datumKnjizenja, Date datumValute, BigDecimal valutniDugovniIznos, BigDecimal valutniPotrazniIznos, BigDecimal domicilniDugovniIznos, BigDecimal domicilniPotrazniIznos, Integer sifraTipaNaloga) {
		super();
		this.brojPartije = brojPartije;
		this.sifraDijela = sifraDijela;
		this.oznakaNadDijela = oznakaNadDijela;
		this.nazivNadDijela = nazivNadDijela;
		this.sifraKonta = sifraKonta;
		this.sifraValute = sifraValute;
		this.oznakaValute = oznakaValute;
		this.sifraNaloga = sifraNaloga;
		this.datumKnjizenja = datumKnjizenja;
		this.datumValute = datumValute;
		this.valutniDugovniIznos = valutniDugovniIznos;
		this.valutniPotrazniIznos = valutniPotrazniIznos;
		this.domicilniDugovniIznos = domicilniDugovniIznos;
		this.domicilniPotrazniIznos = domicilniPotrazniIznos;
		this.sifraTipaNaloga = sifraTipaNaloga;
	}
	
	// getters & setters
	public PrometStanjePartijeDto() {
		super();
	}

	public Integer getSifraModula() {
		return sifraModula;
	}

	public void setSifraModula(Integer sifraModula) {
		this.sifraModula = sifraModula;
	}

	public Integer getSifraKomitenta() {
		return sifraKomitenta;
	}

	public void setSifraKomitenta(Integer sifraKomitenta) {
		this.sifraKomitenta = sifraKomitenta;
	}

	public Integer getSifraPartije() {
		return sifraPartije;
	}

	public void setSifraPartije(Integer sifraPartije) {
		this.sifraPartije = sifraPartije;
	}

	public Integer getSifraNadDijela() {
		return sifraNadDijela;
	}

	public void setSifraNadDijela(Integer sifraNadDijela) {
		this.sifraNadDijela = sifraNadDijela;
	}

	public Integer getSifraDijela() {
		return sifraDijela;
	}

	public void setSifraDijela(Integer sifraDijela) {
		this.sifraDijela = sifraDijela;
	}

	public String getSifraPoslovnice() {
		return sifraPoslovnice;
	}

	public void setSifraPoslovnice(String sifraPoslovnice) {
		this.sifraPoslovnice = sifraPoslovnice;
	}

	public String getSifraValute() {
		return sifraValute;
	}

	public void setSifraValute(String sifraValute) {
		this.sifraValute = sifraValute;
	}

	public String getSifraKonta() {
		return sifraKonta;
	}

	public void setSifraKonta(String sifraKonta) {
		this.sifraKonta = sifraKonta;
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

	public boolean isPojedinacno() {
		return pojedinacno;
	}

	public void setPojedinacno(boolean pojedinacno) {
		this.pojedinacno = pojedinacno;
	}

	public Integer getSifraNaloga() {
		return sifraNaloga;
	}

	public void setSifraNaloga(Integer sifraNaloga) {
		this.sifraNaloga = sifraNaloga;
	}

	public Integer getSifraTipaNaloga() {
		return sifraTipaNaloga;
	}

	public void setSifraTipaNaloga(Integer sifraTipaNaloga) {
		this.sifraTipaNaloga = sifraTipaNaloga;
	}

	public String getBrojPartije() {
		return brojPartije;
	}

	public void setBrojPartije(String brojPartije) {
		this.brojPartije = brojPartije;
	}

	public String getOznakaNadDijela() {
		return oznakaNadDijela;
	}

	public void setOznakaNadDijela(String oznakaNadDijela) {
		this.oznakaNadDijela = oznakaNadDijela;
	}

	public String getNazivNadDijela() {
		return nazivNadDijela;
	}

	public void setNazivNadDijela(String nazivNadDijela) {
		this.nazivNadDijela = nazivNadDijela;
	}

	public String getOznakaValute() {
		return oznakaValute;
	}

	public void setOznakaValute(String oznakaValute) {
		this.oznakaValute = oznakaValute;
	}

	public Date getDatumKnjizenja() {
		return datumKnjizenja;
	}

	public void setDatumKnjizenja(Date datumKnjizenja) {
		this.datumKnjizenja = datumKnjizenja;
	}

	public Date getDatumValute() {
		return datumValute;
	}

	public void setDatumValute(Date datumValute) {
		this.datumValute = datumValute;
	}

    public Date getVrijemeKnjizenja() {
        return vrijemeKnjizenja;
    }

    public void setVrijemeKnjizenja(Date vrijemeKnjizenja) {
        this.vrijemeKnjizenja = vrijemeKnjizenja;
    }

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

	public BigDecimal getPocetniValutniSaldo() {
		return pocetniValutniSaldo;
	}

	public void setPocetniValutniSaldo(BigDecimal pocetniValutniSaldo) {
		this.pocetniValutniSaldo = pocetniValutniSaldo;
	}

	public BigDecimal getPocetniDomicilniSaldo() {
		return pocetniDomicilniSaldo;
	}

	public void setPocetniDomicilniSaldo(BigDecimal pocetniDomicilniSaldo) {
		this.pocetniDomicilniSaldo = pocetniDomicilniSaldo;
	}

	public String getSifraPoslovnicePartije() {
		return sifraPoslovnicePartije;
	}

	public void setSifraPoslovnicePartije(String sifraPoslovnicePartije) {
		this.sifraPoslovnicePartije = sifraPoslovnicePartije;
	}

	public String getOibKomitenta() {
		return oibKomitenta;
	}

	public void setOibKomitenta(String oibKomitenta) {
		this.oibKomitenta = oibKomitenta;
	}

	public String getMaticniBrojKomitenta() {
		return maticniBrojKomitenta;
	}

	public void setMaticniBrojKomitenta(String maticniBrojKomitenta) {
		this.maticniBrojKomitenta = maticniBrojKomitenta;
	}

	public String getNazivKomitenta() {
		return nazivKomitenta;
	}

	public void setNazivKomitenta(String nazivKomitenta) {
		this.nazivKomitenta = nazivKomitenta;
	}

	public String getAdresaKomitenta() {
		return adresaKomitenta;
	}

	public void setAdresaKomitenta(String adresaKomitenta) {
		this.adresaKomitenta = adresaKomitenta;
	}

	public String getKucniBroj() {
		return kucniBroj;
	}

	public void setKucniBroj(String kucniBroj) {
		this.kucniBroj = kucniBroj;
	}

	public String getMjestoKomitenta() {
		return mjestoKomitenta;
	}

	public void setMjestoKomitenta(String mjestoKomitenta) {
		this.mjestoKomitenta = mjestoKomitenta;
	}

	public String getNazivTipaPartije() {
		return nazivTipaPartije;
	}

	public void setNazivTipaPartije(String nazivTipaPartije) {
		this.nazivTipaPartije = nazivTipaPartije;
	}

	public String getOpisValute() {
		return opisValute;
	}

	public void setOpisValute(String opisValute) {
		this.opisValute = opisValute;
	}

	public boolean isStanjaRazlicitaOdNule() {
		return stanjaRazlicitaOdNule;
	}

	public void setStanjaRazlicitaOdNule(boolean stanjaRazlicitaOdNule) {
		this.stanjaRazlicitaOdNule = stanjaRazlicitaOdNule;
	}

	public String getNazivTipaNaloga() {
		return nazivTipaNaloga;
	}

	public void setNazivTipaNaloga(String nazivTipaNaloga) {
		this.nazivTipaNaloga = nazivTipaNaloga;
	}

	public BigDecimal getSaldoReda() {
		return saldoReda;
	}

	public void setSaldoReda(BigDecimal saldoReda) {
		this.saldoReda = saldoReda;
	}

	public Integer getSifraKlasePartije() {
		return sifraKlasePartije;
	}

	public void setSifraKlasePartije(Integer sifraKlasePartije) {
		this.sifraKlasePartije = sifraKlasePartije;
	}

	public String getReportKey() {
		return reportKey;
	}

	public void setReportKey(String reportKey) {
		this.reportKey = reportKey;
	}

	public String getSifraValuteKonverzije() {
		return sifraValuteKonverzije;
	}

	public void setSifraValuteKonverzije(String sifraValuteKonverzije) {
		this.sifraValuteKonverzije = sifraValuteKonverzije;
	}

	public BigDecimal getSaldoRedaKonvertiran() {
		return saldoRedaKonvertiran;
	}

	public void setSaldoRedaKonvertiran(BigDecimal saldoRedaKonvertiran) {
		this.saldoRedaKonvertiran = saldoRedaKonvertiran;
	}

	public String getSvrha() {
		return svrha;
	}

	public void setSvrha(String svrha) {
		this.svrha = svrha;
	}

	public String getNalogodavac() {
		return nalogodavac;
	}

	public void setNalogodavac(String nalogodavac) {
		this.nalogodavac = nalogodavac;
	}

	public Integer getSifraPlatnogNaloga() {
		return sifraPlatnogNaloga;
	}

	public void setSifraPlatnogNaloga(Integer sifraPlatnogNaloga) {
		this.sifraPlatnogNaloga = sifraPlatnogNaloga;
	}

	public String getStatusNaloga() {
		return statusNaloga;
	}

	public void setStatusNaloga(String statusNaloga) {
		this.statusNaloga = statusNaloga;
	}

	public String getBrojAlternativnePartije() {
		return brojAlternativnePartije;
	}

	public void setBrojAlternativnePartije(String brojAlternativnePartije) {
		this.brojAlternativnePartije = brojAlternativnePartije;
	}

	public String getVrstaKonta() {
		return vrstaKonta;
	}

	public void setVrstaKonta(String vrstaKonta) {
		this.vrstaKonta = vrstaKonta;
	}

	public boolean isUkljuciFakture() {
		return ukljuciFakture;
	}

	public void setUkljuciFakture(boolean ukljuciFakture) {
		this.ukljuciFakture = ukljuciFakture;
	}

	public boolean isFaktureUpit() {
		return faktureUpit;
	}

	public void setFaktureUpit(boolean faktureUpit) {
		this.faktureUpit = faktureUpit;
	}
	
	public String getBrojPartijeDruge() {
		return brojPartijeDruge;
	}

	public void setBrojPartijeDruge(String brojPartijeDruge) {
		this.brojPartijeDruge = brojPartijeDruge;
	}

	public String getIbanPartije() {
		return ibanPartije;
	}

	public void setIbanPartije(String ibanPartije) {
		this.ibanPartije = ibanPartije;
	}

	public BigDecimal getPocetniValutniSaldoDugovno() {
		return pocetniValutniSaldoDugovno;
	}

	public void setPocetniValutniSaldoDugovno(BigDecimal pocetniValutniSaldoDugovno) {
		this.pocetniValutniSaldoDugovno = pocetniValutniSaldoDugovno;
	}

	public BigDecimal getPocetniValutniSaldoPotrazno() {
		return pocetniValutniSaldoPotrazno;
	}

	public void setPocetniValutniSaldoPotrazno(BigDecimal pocetniValutniSaldoPotrazno) {
		this.pocetniValutniSaldoPotrazno = pocetniValutniSaldoPotrazno;
	}

	public String getUplata() {
		return uplata;
	}

	public void setUplata(String uplata) {
		this.uplata = uplata;
	}

	public String getIsplata() {
		return isplata;
	}

	public void setIsplata(String isplata) {
		this.isplata = isplata;
	}

	public String getModelZaduzenja() {
		return modelZaduzenja;
	}

	public void setModelZaduzenja(String modelZaduzenja) {
		this.modelZaduzenja = modelZaduzenja;
	}

	public String getPozivNaBrojZaduzenja() {
		return pozivNaBrojZaduzenja;
	}

	public void setPozivNaBrojZaduzenja(String pozivNaBrojZaduzenja) {
		this.pozivNaBrojZaduzenja = pozivNaBrojZaduzenja;
	}

	public String getModelOdobrenja() {
		return modelOdobrenja;
	}

	public void setModelOdobrenja(String modelOdobrenja) {
		this.modelOdobrenja = modelOdobrenja;
	}

	public String getPozivNaBrojOdobrenja() {
		return pozivNaBrojOdobrenja;
	}

	public void setPozivNaBrojOdobrenja(String pozivNaBrojOdobrenja) {
		this.pozivNaBrojOdobrenja = pozivNaBrojOdobrenja;
	}

	public String getNacinIzvrsenja() {
		return nacinIzvrsenja;
	}

	public void setNacinIzvrsenja(String nacinIzvrsenja) {
		this.nacinIzvrsenja = nacinIzvrsenja;
	}

	public Date getVrijemeUnosaNaloga() {
		return vrijemeUnosaNaloga;
	}

	public void setVrijemeUnosaNaloga(Date vrijemeUnosaNaloga) {
		this.vrijemeUnosaNaloga = vrijemeUnosaNaloga;
	}

	public Integer getPrioritetNaloga() {
		return prioritetNaloga;
	}

	public void setPrioritetNaloga(Integer prioritetNaloga) {
		this.prioritetNaloga = prioritetNaloga;
	}

	public String getSifraLikvidatora() {
		return sifraLikvidatora;
	}

	public void setSifraLikvidatora(String sifraLikvidatora) {
		this.sifraLikvidatora = sifraLikvidatora;
	}

	public BigDecimal getIznosNaloga() {
		return iznosNaloga;
	}

	public void setIznosNaloga(BigDecimal iznosNaloga) {
		this.iznosNaloga = iznosNaloga;
	}

	public String getRacunOdobrenja() {
		return racunOdobrenja;
	}

	public void setRacunOdobrenja(String racunOdobrenja) {
		this.racunOdobrenja = racunOdobrenja;
	}

	public String getRacunZaduzenja() {
		return racunZaduzenja;
	}

	public void setRacunZaduzenja(String racunZaduzenja) {
		this.racunZaduzenja = racunZaduzenja;
	}

	public String getNazivZaduzenja() {
		return nazivZaduzenja;
	}

	public void setNazivZaduzenja(String nazivZaduzenja) {
		this.nazivZaduzenja = nazivZaduzenja;
	}

	public String getNazivOdobrenja() {
		return nazivOdobrenja;
	}

	public void setNazivOdobrenja(String nazivOdobrenja) {
		this.nazivOdobrenja = nazivOdobrenja;
	}

	public String getReferencaNaloga() {
		return referencaNaloga;
	}

	public void setReferencaNaloga(String referencaNaloga) {
		this.referencaNaloga = referencaNaloga;
	}

	public Integer getSifraPrometa() {
		return sifraPrometa;
	}

	public void setSifraPrometa(Integer sifraPrometa) {
		this.sifraPrometa = sifraPrometa;
	}
	
		public boolean isWithBalance() {
		return withBalance;
	}

	public void setWithBalance(boolean withBalance) {
		this.withBalance = withBalance;
	}
}
