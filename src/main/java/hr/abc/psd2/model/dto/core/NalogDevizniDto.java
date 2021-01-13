package hr.abc.psd2.model.dto.core;

import hr.abc.psd2.annotation.IFilter;
import hr.abc.psd2.util.GenericBassxConstants;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Data transfer object za entitet devizni nalog.
 *
 * @author Matija Hlapčić & Snježana
 */
public class NalogDevizniDto extends NalogDevizniCommonDto implements Serializable {

    // fields
    private static final long serialVersionUID = 1L;
    private Integer sifraPartijeKontokorenta; // partija kontokorenta nalogodavatelja/korisnika
    private String swiftKorespodenta; // SWIFT BIC korespodenta nalogodavatelja/korisnika - kontokorent odnosno rambursna banka
    private String sifraDrzaveNalogodavateljaKorisnika, nazivDrzaveNalogodavateljaKorisnika; // država nalogodavatelja/korisnika
    private String bankaNalogodavateljaKorisnika; // banka korisnika/nalogodavatelja - opisno polje koje sadrži podatke koje daje korisnik/nalogodavatelj i koji se ne smiju mjenjati
    private String sifraStraneOsobe; // šifra strane osobe (1 - pravne, 2 - fizičke)
    private Integer sifraOsnove; // osnova plaćanja/naplate
    private Integer sifraDeviznogInstrumenta; // devizni instrument
    private String brojKreditnePrijave; // broj kreditne prijave
    private String vrijednosniPapir; // vrijednosni papir
    private String statusObrade; // status obrade deviznog naloga - koristi se kod daljnjih obrada deviznih naloga nakon knjiženja same transakcije
    private String vezanaReferenca; // vezana referenca za SWIFT poruku (21)
    private String tipSwiftPoruke; // tip SWIFT poruke ako postoji
    private String podTipSwiftPoruke; // pod tip SWIFT poruke ako postoji
    private String statusSwiftObrade; // status SWIFT obrade
    private BigDecimal iznosInoTroska; // ino trošak deviznog naloga pošiljatelja
    private BigDecimal iznosInoTroskaPrimatelja; // ino trošak deviznog naloga primatelja
    private Integer sifraDeviznogNalogaSwift; // dodatni i opcionalni podaci koji se koriste kod formiranja SWIFT poruka
	private NalogDevizniSwiftDto nalogDevizniSwiftDto; // dodatni i opcionalni podaci koji se koriste kod formiranja SWIFT poruka - objekt
	private Integer sifraDeviznogNalogaExtended; // dodatni i opcionalni podaci kojima se proširuje devizni nalog
	private NalogDevizniExtendedDto nalogDevizniExtendedDto; // dodatni i opcionalni podaci kojima se proširuje devizni nalog
	private Integer sifraNalogaAkcije; // nalog akcije (swift)
	private Integer brojDanaUnaprijed; // broj dana u najavu kako se zadaje transakcija (doznaka)
	private boolean swiftMessageSimulation = false; // da li se radi o simulaciji SWIFT poruke - kako se ne bi generirao nepotrebno UETR
	protected BigDecimal ukupniIznos33B; // ukupni iznos transakcije
	private String troskovi71D; // opisno troškovi
	// platni promet
	private String modelZaduzenja; // model zaduženja
	private String pozivNaBrojZaduzenja; // poziv na broj zaduženja
	private String modelOdobrenja; // model odobrenja
	private String pozivNaBrojOdobrenja; // poziv na broj odobrenja
	private String sifraHsvpPosla; // šifra HSVP posla
	private String opisHsvpPosla; // opis HSVP posla
	private Integer nalogodavateljSpp; // nalogodavatelj SPP
    // polja SWIFT-a
	private Integer swiftInitiator, sifraSwiftHeadera, redniBrojAkcije;
	private String tipOperacije22A, zajednickaReferenca22C, pravilaSifra, pravilaOpis, infoPrimatelju, dodatniId, opisDobaraUsluga, dodatnaPotrebnaDokumentacija, dodatniUvjeti, codeAppRule, descriptionAppRule;
	private Date datumPopratnogPisma, datumPoruke;
	private boolean garancija; // označava da li se radi o garanciji u SWIFT obradi
	private String swiftBicKorespodentDugovna, swiftBicKorespodentDugovnaOpis, swiftBicKorespodentPotrazni, swiftBicKorespodentPotrazniOpis; // banke u riznici
	private String swiftBicIntermediaryDugovna, swiftBicIntermediaryDugovnaOpis, swiftBicIntermediaryPotrazni, swiftBicIntermediaryPotrazniOpis; // banke u riznici
	// dodatna partija
    private BigDecimal iznosPartije, iznosDomicilni;
    private String sifraValutePartije, brojPartijeAnalitike;
    private Integer sifraPartijeAnalitike;
	// broj (IBAN) partije pokrića - u slučaju da korisnik odabere račun različit od partije zaduženja
	private Integer sifraPartijePokrica;
	private String partijaPokrica, opisPartijePokrica;
	// partija kontokorentnog računa na koju se knjiži doznaka/prima priljev/itd.
	private Integer sifraTipaPartijeKontokorenta;
	private String partijaKontokorenta, opisPartijeKontokorenta;
	private BigDecimal iznosPolozenoPokrice;
    // naknada
    private BigDecimal iznosObracunateNaknade;
    private Boolean existNaknada; // da li se za transakciju obračunava/naplaćuje naknada
    private String partijaNaplateNaknade, oznakaTarifneStavke, sifraValuteNaknade, sifraValuteNaplateNaknade, sifraPoslovniceNaknade, sifraSektoraNaknade; // partija s koje se naplaćuje naknada
    private Integer sifraTarifneStavke, sifraPartijeNaknade, sifraPaketaNakande, sifraProizvodaNaknade, sifraKomitentaNaknade, sifraPartijeFakture; // partija/paket/proizvod po kojoj se obračunava/naplaćuje naknada
    private Date datumRazgranicenja; // datum do kojeg se razgraničava naknada
    private Date datumPosla; // datum posla
    // izvod
    private Integer sifraDeviznogIzvoda, redniBrojIzvoda; 
    // help fields
    private String grupaKlaseNaloga, tipProizvoda, tipTecaja, oznakaNadDijela, momentDospijecaDesc, loginLikvidatora, nazivPoslovnice, nazivAplikacije, smjerTecaja, swiftField, nazivDeviznogInstrumenta, nazivOsnove;
    private Integer sifraDeviznoNaloga, sifraKnjiznogNalogaDevizni, momentDospijeca, sifraDeviznogNalogaPrethodnika, sifraDioPartije;
    private BigDecimal iznosNative, iznosGlavnica, iznosKamata, iznosKns, iznosRata, dogovoreniTecaj;
    private Boolean swiftMessageEnabled;
    private List<PrometWithLookupsDto> listPrometWithLookupsDto; // pomoćna lista za template
    private List<ObracunNaknadeDto> listObracunNaknadeDetails; // detalji obračuna naknade
    private List<String> messagesFromBusinessLayer; // poruke poslovnog sloja
    private List<SpecifikacijaPokricaDto> listSpecifikacijaPokrica; // unaprijed zadano pokriće (retail)
    // dodatni složeni filtri
    private Date datumValuteOd, datumValuteDo;
	// ovlaštenik/neklijent
	private FizKomitentDto neklijent;
	private Integer sifraKomitentaStranke;
	private String oibBrojKomitentaStranke, nazivKomitentaStranke, sifraSektoraStranke, brojIdentifikacijeStranke;
	// tip veze naknade
	private String sifraTipaVezeNak;
	// povlašteni tečaj
	private BigDecimal povlasteniTecajZaduzenja, povlasteniTecajOdobrenja, regularniTecajZaduzenja, regularniTecajOdobrenja, maxIznosZaduzenja, maxIznosOdobrenja;
	private Integer sifraPovlastenogTecaja;

    // constructors
    public NalogDevizniDto() {
        super();
    }

    public NalogDevizniDto(boolean isFirstCall) {
        super(isFirstCall);
    }
    
    public NalogDevizniDto(NalogPlatniDto platniNalog) {
    	super();
    	if (platniNalog != null) {
    		this.setAdresaCreditor(platniNalog.getAdresaCreditor());
    		this.setAdresaDebtor(platniNalog.getAdresaDebtor());
    		this.setDrzavaCreditor(platniNalog.getDrzavaCreditor());
    		this.setDrzavaDebtor(platniNalog.getDrzavaDebtor());
    		this.setGradCreditor(platniNalog.getGradCreditor());
    		this.setGradDebtor(platniNalog.getGradDebtor());
    		this.setIbanOdobrenja(platniNalog.getIbanOdobrenja());
    		this.setIbanZaduzenja(platniNalog.getIbanZaduzenja());
    		this.setIznos(platniNalog.getIznos());
    		this.setKnjizniNalog(platniNalog.getKnjizniNalog());
    		this.setMedij(platniNalog.getMedij());
    		this.setNacinIzvrsenja(platniNalog.getNacinIzvrsenja());
    		this.setNazivCreditor(platniNalog.getNazivCreditor());
    		this.setNazivDebtor(platniNalog.getNazivDebtor());
    		this.setReferenca(platniNalog.getReferenca());
    		this.setSifraKnjiznogNaloga(platniNalog.getKnjizniNalog() != null && platniNalog.getKnjizniNalog().getSifra() != null ? platniNalog.getKnjizniNalog().getSifra() : platniNalog.getSifraKnjiznogNaloga());
    		this.setSifraValute(platniNalog.getSifraValute());
    		this.setSifraValutePokrica(platniNalog.getSifraValutePokrica());
    		this.setSmjer(platniNalog.getSmjer());
    		this.setSmjerTecaja(platniNalog.getSmjerTecaja());
    		this.setSwiftNalogodavateljaKorisnika(platniNalog.getSwiftNalogodavateljaKorisnika());
    		this.setTecajOdobrenja(platniNalog.getTecajOdobrenja());
    		this.setTecajZaduzenja(platniNalog.getTecajZaduzenja());
    		this.setTipTecajaOdobrenja(platniNalog.getTipTecajaOdobrenja());
    		this.setTipTecajaZaduzenja(platniNalog.getTipTecajaZaduzenja());
    		this.setTrosakInoBanke(platniNalog.getTrosakInoBanke());
    		this.setUltimateCreditorId(platniNalog.getUltimateCreditorId());
    		this.setUltimateCreditorNaziv(platniNalog.getUltimateCreditorNaziv());
    		this.setUltimateDebtorId(platniNalog.getUltimateDebtorId());
    		this.setUltimateDebtorNaziv(platniNalog.getUltimateDebtorNaziv());
    	}
    }
    
    public NalogDevizniDto(NalogSepaDto platniNalog) {
    	super();
    	if (platniNalog != null) {
    		this.setAdresaCreditor(platniNalog.getAdresaCreditor());
    		this.setAdresaDebtor(platniNalog.getAdresaDebtor());
    		this.setDrzavaCreditor(platniNalog.getDrzavaCreditor());
    		this.setDrzavaDebtor(platniNalog.getDrzavaDebtor());
    		this.setGradCreditor(platniNalog.getGradCreditor());
    		this.setGradDebtor(platniNalog.getGradDebtor());
    		this.setIbanOdobrenja(platniNalog.getIbanOdobrenja());
    		this.setIbanZaduzenja(platniNalog.getIbanZaduzenja());
    		this.setIznos(platniNalog.getIznos());
    		this.setKnjizniNalog(platniNalog.getKnjizniNalog());
    		this.setMedij(platniNalog.getMedij());
    		this.setNacinIzvrsenja(platniNalog.getNacinIzvrsenja());
    		this.setNazivCreditor(platniNalog.getNazivCreditor());
    		this.setNazivDebtor(platniNalog.getNazivDebtor());
    		this.setReferenca(platniNalog.getReferenca());
    		this.setSifraKnjiznogNaloga(platniNalog.getKnjizniNalog() != null && platniNalog.getKnjizniNalog().getSifra() != null ? platniNalog.getKnjizniNalog().getSifra() : platniNalog.getSifraKnjiznogNaloga());
    		this.setSifraValute(platniNalog.getSifraValute());
    		this.setSifraValutePokrica(platniNalog.getSifraValutePokrica());
    		this.setSmjer(platniNalog.getSmjer());
    		this.setSwiftNalogodavateljaKorisnika(platniNalog.getSwiftBicNalogodavateljaKorisnika());
    		this.setTecajOdobrenja(platniNalog.getTecajOdobrenja());
    		this.setTecajZaduzenja(platniNalog.getTecajZaduzenja());
    		this.setTipTecajaOdobrenja(platniNalog.getTipTecajaOdobrenja());
    		this.setTipTecajaZaduzenja(platniNalog.getTipTecajaZaduzenja());
    		this.setTrosakInoBanke(platniNalog.getTrosakInoBanke());
    		this.setUltimateCreditorId(platniNalog.getUltimateCreditorId());
    		this.setUltimateCreditorNaziv(platniNalog.getUltimateCreditorNaziv());
    		this.setUltimateDebtorId(platniNalog.getUltimateDebtorId());
    		this.setUltimateDebtorNaziv(platniNalog.getUltimateDebtorNaziv());
    	}
    }

	public NalogDevizniDto(NalogSepaDto platniNalog, String swiftBicKorisnikaNaziv) {
		super();
		if (platniNalog != null) {
			this.setAdresaCreditor(platniNalog.getAdresaCreditor());
			this.setAdresaDebtor(platniNalog.getAdresaDebtor());
			this.setDrzavaCreditor(platniNalog.getDrzavaCreditor());
			this.setDrzavaDebtor(platniNalog.getDrzavaDebtor());
			this.setGradCreditor(platniNalog.getGradCreditor());
			this.setGradDebtor(platniNalog.getGradDebtor());
			this.setIbanOdobrenja(platniNalog.getIbanOdobrenja());
			this.setIbanZaduzenja(platniNalog.getIbanZaduzenja());
			this.setIznos(platniNalog.getIznos());
			this.setKnjizniNalog(platniNalog.getKnjizniNalog());
			this.setMedij(platniNalog.getMedij());
			this.setNacinIzvrsenja(platniNalog.getNacinIzvrsenja());
			this.setNazivCreditor(platniNalog.getNazivCreditor());
			this.setNazivDebtor(platniNalog.getNazivDebtor());
			this.setReferenca(platniNalog.getReferenca());
			this.setSifraKnjiznogNaloga(platniNalog.getKnjizniNalog() != null && platniNalog.getKnjizniNalog().getSifra() != null ? platniNalog.getKnjizniNalog().getSifra() : platniNalog.getSifraKnjiznogNaloga());
			this.setSifraValute(platniNalog.getSifraValute());
			this.setSifraValutePokrica(platniNalog.getSifraValutePokrica());
			this.setSmjer(platniNalog.getSmjer());
			this.setSwiftNalogodavateljaKorisnika(platniNalog.getSwiftBicNalogodavateljaKorisnika());
			this.setTecajOdobrenja(platniNalog.getTecajOdobrenja());
			this.setTecajZaduzenja(platniNalog.getTecajZaduzenja());
			this.setTipTecajaOdobrenja(platniNalog.getTipTecajaOdobrenja());
			this.setTipTecajaZaduzenja(platniNalog.getTipTecajaZaduzenja());
			this.setTrosakInoBanke(platniNalog.getTrosakInoBanke());
			this.setUltimateCreditorId(platniNalog.getUltimateCreditorId());
			this.setUltimateCreditorNaziv(platniNalog.getUltimateCreditorNaziv());
			this.setUltimateDebtorId(platniNalog.getUltimateDebtorId());
			this.setUltimateDebtorNaziv(platniNalog.getUltimateDebtorNaziv());
			this.setBankaNalogodavateljaKorisnika(StringUtils.isNotBlank(swiftBicKorisnikaNaziv)?swiftBicKorisnikaNaziv:null);
		}
	}



	// builder
    // private contructor for builder
    protected NalogDevizniDto(NalogDevizniDtoBuilder<?> builder) {
        super(builder);
        setSifraPartijeKontokorenta(builder.sifraPartijeKontokorenta);
        setSifraOsnove(builder.sifraOsnove); 
        setSifraDeviznogInstrumenta(builder.sifraDeviznogInstrumenta); 
        setSifraDeviznogNalogaSwift(builder.sifraDeviznogNalogaSwift); 
        setSifraDeviznogNalogaExtended(builder.sifraDeviznogNalogaExtended);
        setSwiftKorespodenta(builder.swiftKorespodenta); 
        setSifraDrzaveNalogodavateljaKorisnika(builder.sifraDrzaveNalogodavateljaKorisnika); 
        setBankaNalogodavateljaKorisnika(builder.bankaNalogodavateljaKorisnika); 
        setSifraStraneOsobe(builder.sifraStraneOsobe); 
        setBrojKreditnePrijave(builder.brojKreditnePrijave); 
        setVrijednosniPapir(builder.vrijednosniPapir); 
        setVezanaReferenca(builder.vezanaReferenca); 
        setTipSwiftPoruke(builder.tipSwiftPoruke); 
        setStatusSwiftObrade(builder.statusSwiftObrade); 
        setGrupaKlaseNaloga(builder.grupaKlaseNaloga);
        setIznosInoTroska(builder.iznosInoTroska);
        setNalogDevizniSwiftDto(builder.nalogDevizniSwiftDto);
    	setNalogDevizniExtendedDto(builder.nalogDevizniExtendedDto);
    	setDatumPosla(builder.datumPosla);
    	setIznosGlavnica(builder.iznosGlavnica); 
    	setIznosKamata(builder.iznosKamata);
    	setIznosKns(builder.iznosKns);
    	setDogovoreniTecaj(builder.dogovoreniTecaj);
    	setSifraKnjiznogNalogaDevizni(builder.sifraKnjiznogNalogaDevizni);
    	setSifraPartijeAnalitike(builder.sifraPartijeAnalitike);
    	setZajednickaReferenca22C(builder.zajednickaReferenca22C);
    	setSwiftBicKorespodentDugovna(builder.swiftBicKorespodentDugovna); 
    	setSwiftBicKorespodentDugovnaOpis(builder.swiftBicKorespodentDugovnaOpis); 
    	setSwiftBicKorespodentPotrazni(builder.swiftBicKorespodentPotrazni);
    	setSwiftBicKorespodentPotrazniOpis(builder.swiftBicKorespodentPotrazniOpis);
    	setSwiftBicIntermediaryDugovna(builder.swiftBicIntermediaryDugovna);
    	setSwiftBicIntermediaryDugovnaOpis(builder.swiftBicIntermediaryDugovnaOpis);
    	setSwiftBicIntermediaryPotrazni(builder.swiftBicIntermediaryPotrazni);
    	setSwiftBicIntermediaryPotrazniOpis(builder.swiftBicIntermediaryPotrazniOpis);
    	setTipOperacije22A(builder.tipOperacije22A);
    	setSmjerTecaja(builder.smjerTecaja);
    	setSifraNalogaAkcije(builder.sifraNalogaAkcije);
    	setNazivOsnove(builder.nazivOsnove);
    	setModelZaduzenja(builder.modelZaduzenja);
		setPozivNaBrojZaduzenja(builder.pozivNaBrojZaduzenja);
		setModelOdobrenja(builder.modelOdobrenja);
		setPozivNaBrojOdobrenja(builder.pozivNaBrojOdobrenja);
		setNalogodavateljSpp(builder.nalogodavateljSpp);
		setSifraTipaVezeNak(builder.sifraTipaVezeNak);
		setPravilaSifra(builder.pravilaSifra);
		setPravilaOpis(builder.pravilaOpis);
		setInfoPrimatelju(builder.infoPrimatelju);
		setDodatniId(builder.dodatniId);
		setDatumPopratnogPisma(builder.datumPopratnogPisma);
		setRedniBrojAkcije(builder.redniBrojAkcije);
		setSwiftField(builder.swiftField);
		setPodTipSwiftPoruke(builder.podTipSwiftPoruke);
		setBrojPartijeAnalitike(builder.brojPartijeAnalitike);
		setStatus(builder.status);
		setSvrha(builder.svrha);
		setSifraTipaNaloga(builder.sifraTipaNaloga);
		setUkupniIznos33B(builder.ukupniIznos33B);
		setIznosInoTroskaPrimatelja(builder.iznosInoTroskaPrimatelja);
    	if (StringUtils.isNotBlank(builder.statusObrade)) {
    		setStatusObrade(builder.statusObrade); 
    	} else {
    		setStatusObrade(GenericBassxConstants.PlatniPromet.DEVIZNI_NALOG_STATUS_INICIRAN);
    	}
    	if (StringUtils.isNotBlank(builder.statusSwiftObrade)) {
    		setStatusSwiftObrade(builder.statusSwiftObrade); 
    	} else {
    		setStatusSwiftObrade(GenericBassxConstants.PlatniPromet.SWIFT_NALOG_STATUS_INICIRAN);
    	}
    	setTroskovi71D(builder.troskovi71D);
    }

    // builder
    public static abstract class NalogDevizniDtoBuilder<T extends NalogDevizniDtoBuilder<T>> extends NalogDevizniCommonDto.NalogDevizniCommonDtoBuilder<T> {

        // fields
        private Integer sifraPartijeKontokorenta, sifraOsnove, sifraDeviznogInstrumenta, sifraDeviznogNalogaSwift, sifraDeviznogNalogaExtended, sifraKnjiznogNalogaDevizni, sifraPartijeAnalitike, sifraNalogaAkcije, nalogodavateljSpp, redniBrojAkcije, sifraTipaNaloga;
        private String swiftKorespodenta, sifraDrzaveNalogodavateljaKorisnika, bankaNalogodavateljaKorisnika, sifraStraneOsobe, brojKreditnePrijave, vrijednosniPapir, statusObrade, vezanaReferenca, tipSwiftPoruke, statusSwiftObrade, grupaKlaseNaloga, zajednickaReferenca22C, swiftBicKorespodentDugovna, swiftBicKorespodentDugovnaOpis, swiftBicKorespodentPotrazni, swiftBicKorespodentPotrazniOpis, swiftBicIntermediaryDugovna, swiftBicIntermediaryDugovnaOpis, swiftBicIntermediaryPotrazni, swiftBicIntermediaryPotrazniOpis, tipOperacije22A, smjerTecaja, nazivOsnove, modelZaduzenja, pozivNaBrojZaduzenja, modelOdobrenja, pozivNaBrojOdobrenja, sifraTipaVezeNak, pravilaSifra, pravilaOpis, infoPrimatelju, dodatniId, swiftField, podTipSwiftPoruke, brojPartijeAnalitike, status, svrha, troskovi71D;
        private BigDecimal iznosInoTroska, iznosInoTroskaPrimatelja, iznosGlavnica, iznosKamata, iznosKns, dogovoreniTecaj, ukupniIznos33B;
        private Date datumPosla, datumPopratnogPisma;
        private NalogDevizniSwiftDto nalogDevizniSwiftDto;
    	private NalogDevizniExtendedDto nalogDevizniExtendedDto;

        // constructor
        public NalogDevizniDtoBuilder(String sifraValute, String ibanZaduzenja, String ibanOdobrenja, String nacinIzvrsenja, String smjer, BigDecimal iznos, Integer sifraKnjiznogNaloga, String grupaKlaseNaloga) {
            super(sifraValute, ibanZaduzenja, ibanOdobrenja, nacinIzvrsenja, smjer, iznos, sifraKnjiznogNaloga);
            this.grupaKlaseNaloga = grupaKlaseNaloga;
        }

        // methods for optional parameters
        public T sifraPartijeKontokorenta(Integer sifraPartijeKontokorenta) {
        	this.sifraPartijeKontokorenta = sifraPartijeKontokorenta;
        	return self();
        }

        public T sifraOsnove(Integer sifraOsnove) {
        	this.sifraOsnove = sifraOsnove;
        	return self();
        }

        public T sifraDeviznogInstrumenta(Integer sifraDeviznogInstrumenta) {
        	this.sifraDeviznogInstrumenta = sifraDeviznogInstrumenta;
        	return self();
        }

        public T sifraDeviznogNalogaSwift(Integer sifraDeviznogNalogaSwift) {
        	this.sifraDeviznogNalogaSwift = sifraDeviznogNalogaSwift;
        	return self();
        }

        public T sifraDeviznogNalogaExtended(Integer sifraDeviznogNalogaExtended) {
        	this.sifraDeviznogNalogaExtended = sifraDeviznogNalogaExtended;
        	return self();
        }
        
        public T swiftKorespodenta(String swiftKorespodenta) {
			this.swiftKorespodenta = swiftKorespodenta;
			return self();
		}

        public T sifraDrzaveNalogodavateljaKorisnika(String sifraDrzaveNalogodavateljaKorisnika) {
        	this.sifraDrzaveNalogodavateljaKorisnika = sifraDrzaveNalogodavateljaKorisnika;
        	return self();
        }

        public T bankaNalogodavateljaKorisnika(String bankaNalogodavateljaKorisnika) {
        	this.bankaNalogodavateljaKorisnika = bankaNalogodavateljaKorisnika;
        	return self();
        }

        public T sifraStraneOsobe(String sifraStraneOsobe) {
        	this.sifraStraneOsobe = sifraStraneOsobe;
        	return self();
        }

        public T brojKreditnePrijave(String brojKreditnePrijave) {
        	this.brojKreditnePrijave = brojKreditnePrijave;
        	return self();
        }

        public T vrijednosniPapir(String vrijednosniPapir) {
        	this.vrijednosniPapir = vrijednosniPapir;
        	return self();
        }

        public T statusObrade(String statusObrade) {
        	this.statusObrade = statusObrade;
        	return self();
        }

        public T vezanaReferenca(String vezanaReferenca) {
        	this.vezanaReferenca = vezanaReferenca;
        	return self();
        }

        public T tipSwiftPoruke(String tipSwiftPoruke) {
        	this.tipSwiftPoruke = tipSwiftPoruke;
        	return self();
        }

        public T statusSwiftObrade(String statusSwiftObrade) {
        	this.statusSwiftObrade = statusSwiftObrade;
        	return self();
        }
        
        public T grupaKlaseNaloga(String grupaKlaseNaloga) {
        	this.grupaKlaseNaloga = grupaKlaseNaloga;
        	return self();
        }

        public T iznosInoTroska(BigDecimal iznosInoTroska) {
        	this.iznosInoTroska = iznosInoTroska;
        	return self();
        }

        public T nalogDevizniSwiftDto(NalogDevizniSwiftDto nalogDevizniSwiftDto) {
        	this.nalogDevizniSwiftDto = nalogDevizniSwiftDto;
        	return self();
        }

        public T nalogDevizniExtendedDto(NalogDevizniExtendedDto nalogDevizniExtendedDto) {
        	this.nalogDevizniExtendedDto = nalogDevizniExtendedDto;
        	return self();
        }

        public T sifraNalogaAkcije(Integer sifraNalogaAkcije) {
        	this.sifraNalogaAkcije = sifraNalogaAkcije;
        	return self();
        }
        
        public T iznosInoTroskaPrimatelja(BigDecimal iznosInoTroskaPrimatelja) {
        	this.iznosInoTroskaPrimatelja = iznosInoTroskaPrimatelja;
        	return self();
        }

        // help fields
        public T datumPosla(Date datumPosla) {
        	this.datumPosla = datumPosla;
        	return self();
        }

        public T iznosGlavnica(BigDecimal iznosGlavnica) {
        	this.iznosGlavnica = iznosGlavnica;
        	return self();
        }

        public T iznosKamata(BigDecimal iznosKamata) {
        	this.iznosKamata = iznosKamata;
        	return self();
        }

        public T iznosKns(BigDecimal iznosKns) {
        	this.iznosKns = iznosKns;
        	return self();
        }

        public T dogovoreniTecaj(BigDecimal dogovoreniTecaj) {
        	this.dogovoreniTecaj = dogovoreniTecaj;
        	return self();
        }

        public T sifraKnjiznogNalogaDevizni(Integer sifraKnjiznogNalogaDevizni) {
        	this.sifraKnjiznogNalogaDevizni = sifraKnjiznogNalogaDevizni;
        	return self();
        }

        public T sifraPartijeAnalitike(Integer sifraPartijeAnalitike) {
        	this.sifraPartijeAnalitike = sifraPartijeAnalitike;
        	return self();
        }
        
        public T zajednickaReferenca22C(String zajednickaReferenca22C) {
        	this.zajednickaReferenca22C = zajednickaReferenca22C;
        	return self();
        }
        
        public T swiftBicKorespodentDugovna(String swiftBicKorespodentDugovna) {
        	this.swiftBicKorespodentDugovna = swiftBicKorespodentDugovna;
        	return self();
        }
        
        public T swiftBicKorespodentDugovnaOpis(String swiftBicKorespodentDugovnaOpis) {
        	this.swiftBicKorespodentDugovnaOpis = swiftBicKorespodentDugovnaOpis;
        	return self();
        }
        
        public T swiftBicKorespodentPotrazni(String swiftBicKorespodentPotrazni) {
        	this.swiftBicKorespodentPotrazni = swiftBicKorespodentPotrazni;
        	return self();
        }
        
        public T swiftBicKorespodentPotrazniOpis(String swiftBicKorespodentPotrazniOpis) {
        	this.swiftBicKorespodentPotrazniOpis = swiftBicKorespodentPotrazniOpis;
        	return self();
        }
        
        public T swiftBicIntermediaryDugovna(String swiftBicIntermediaryDugovna) {
        	this.swiftBicIntermediaryDugovna = swiftBicIntermediaryDugovna;
        	return self();
        }
        
        public T swiftBicIntermediaryDugovnaOpis(String swiftBicIntermediaryDugovnaOpis) {
        	this.swiftBicIntermediaryDugovnaOpis = swiftBicIntermediaryDugovnaOpis;
        	return self();
        }
        
        public T swiftBicIntermediaryPotrazni(String swiftBicIntermediaryPotrazni) {
        	this.swiftBicIntermediaryPotrazni = swiftBicIntermediaryPotrazni;
        	return self();
        }
        
        public T swiftBicIntermediaryPotrazniOpis(String swiftBicIntermediaryPotrazniOpis) {
        	this.swiftBicIntermediaryPotrazniOpis = swiftBicIntermediaryPotrazniOpis;
        	return self();
        }

        public T tipOperacije22A(String tipOperacije22A) {
        	this.tipOperacije22A = tipOperacije22A;
        	return self();
        }

        public T smjerTecaja(String smjerTecaja) {
        	this.smjerTecaja = smjerTecaja;
        	return self();
        }

        public T nazivOsnove(String nazivOsnove) {
        	this.nazivOsnove = nazivOsnove;
        	return self();
        }
        
        public T modelZaduzenja(String modelZaduzenja) {
        	this.modelZaduzenja = modelZaduzenja;
        	return self();
        }
        
        public T pozivNaBrojZaduzenja(String pozivNaBrojZaduzenja) {
        	this.pozivNaBrojZaduzenja = pozivNaBrojZaduzenja;
        	return self();
        }
        
        public T modelOdobrenja(String modelOdobrenja) {
        	this.modelOdobrenja = modelOdobrenja;
        	return self();
        }
        
        public T pozivNaBrojOdobrenja(String pozivNaBrojOdobrenja) {
        	this.pozivNaBrojOdobrenja = pozivNaBrojOdobrenja;
        	return self();
        }

        public T nalogodavateljSpp(Integer nalogodavateljSpp) {
        	this.nalogodavateljSpp = nalogodavateljSpp;
        	return self();
        }
        
        public T sifraTipaVezeNak(String sifraTipaVezeNak) {
        	this.sifraTipaVezeNak = sifraTipaVezeNak;
        	return self();
        }
        
        public T pravilaSifra(String pravilaSifra) {
        	this.pravilaSifra = pravilaSifra;
        	return self();
        }

        public T pravilaOpis(String pravilaOpis) {
        	this.pravilaOpis = pravilaOpis;
        	return self();
        }

        public T infoPrimatelju(String infoPrimatelju) {
        	this.infoPrimatelju = infoPrimatelju;
        	return self();
        }

        public T dodatniId(String dodatniId) {
        	this.dodatniId = dodatniId;
        	return self();
        }

        public T datumPopratnogPisma(Date datumPopratnogPisma) {
        	this.datumPopratnogPisma = datumPopratnogPisma;
        	return self();
        }

        public T redniBrojAkcije(Integer redniBrojAkcije) {
        	this.redniBrojAkcije = redniBrojAkcije;
        	return self();
        }

        public T swiftField(String swiftField) {
        	this.swiftField = swiftField;
        	return self();
        }

        public T podTipSwiftPoruke(String podTipSwiftPoruke) {
        	this.podTipSwiftPoruke = podTipSwiftPoruke;
        	return self();
        }

        public T brojPartijeAnalitike(String brojPartijeAnalitike) {
        	this.brojPartijeAnalitike = brojPartijeAnalitike;
        	return self();
        }

        public T status(String status) {
        	this.status = status;
        	return self();
        }

        public T svrha(String svrha) {
        	this.svrha = svrha;
        	return self();
        }

        public T sifraTipaNaloga(Integer sifraTipaNaloga) {
        	this.sifraTipaNaloga = sifraTipaNaloga;
        	return self();
        }
        
        public T ukupniIznos33B(BigDecimal ukupniIznos33B) {
        	this.ukupniIznos33B = ukupniIznos33B;
        	return self();
        }
        
        public T troskovi71D(String troskovi71D) {
        	this.troskovi71D = troskovi71D;
        	return self();
        }
        
        // build method
        public NalogDevizniDto build() {
            return new NalogDevizniDto(this);
        }
    }

    // implementation of abstract class that enables inheritence 
    protected static class NalogDevizniDtoImplementationBuilder extends NalogDevizniDtoBuilder<NalogDevizniDtoImplementationBuilder> {

        // contructor
        public NalogDevizniDtoImplementationBuilder(String sifraValute, String ibanZaduzenja, String ibanOdobrenja, String nacinIzvrsenja, String smjer, BigDecimal iznos, Integer sifraKnjiznogNaloga, String grupaKlaseNaloga) {
            super(sifraValute, ibanZaduzenja, ibanOdobrenja, nacinIzvrsenja, smjer, iznos, sifraKnjiznogNaloga, grupaKlaseNaloga);
        }

        @Override
        protected NalogDevizniDtoImplementationBuilder self() {
            return this;
        }
    }

    // returns implementation of builder
    public static NalogDevizniDtoBuilder<?> builder(String sifraValute, String ibanZaduzenja, String ibanOdobrenja, String nacinIzvrsenja, String smjer, BigDecimal iznos, Integer sifraKnjiznogNaloga, String grupaKlaseNaloga) {
        return new NalogDevizniDtoImplementationBuilder(sifraValute, ibanZaduzenja, ibanOdobrenja, nacinIzvrsenja, smjer, iznos, sifraKnjiznogNaloga, grupaKlaseNaloga);
    }

    // getters & setters
    @Override
    @IFilter(entityField = "nalogDevizni.tczad_nal")
    public BigDecimal getTecajZaduzenja() {
    	return super.getTecajZaduzenja();
    }
    
    @Override
    @IFilter(entityField = "nalogDevizni.tcodo_nal")
    public BigDecimal getTecajOdobrenja() {
    	return super.getTecajOdobrenja();
    }

    @IFilter(entityField = "nalogDevizni.knkrt_nal")
	public Integer getSifraPartijeKontokorenta() {
		return sifraPartijeKontokorenta;
	}

	public void setSifraPartijeKontokorenta(Integer sifraPartijeKontokorenta) {
		this.sifraPartijeKontokorenta = sifraPartijeKontokorenta;
	}

	@IFilter(entityField = "nalogDevizni.kores_nal")
	public String getSwiftKorespodenta() {
		return swiftKorespodenta;
	}

	public void setSwiftKorespodenta(String swiftKorespodenta) {
		this.swiftKorespodenta = swiftKorespodenta;
	}

	@IFilter(entityField = "nalogDevizni.drzav_nal")
	public String getSifraDrzaveNalogodavateljaKorisnika() {
		return sifraDrzaveNalogodavateljaKorisnika;
	}

	public void setSifraDrzaveNalogodavateljaKorisnika(String sifraDrzaveNalogodavateljaKorisnika) {
		this.sifraDrzaveNalogodavateljaKorisnika = sifraDrzaveNalogodavateljaKorisnika;
	}

	@IFilter(entityField = "nalogDevizni.banka_nal")
	public String getBankaNalogodavateljaKorisnika() {
		return bankaNalogodavateljaKorisnika;
	}
	
	public void setBankaNalogodavateljaKorisnika(String bankaNalogodavateljaKorisnika) {
		this.bankaNalogodavateljaKorisnika = bankaNalogodavateljaKorisnika;
	}

	@IFilter(entityField = "nalogDevizni.sistr_nal")
	public String getSifraStraneOsobe() {
		return sifraStraneOsobe;
	}


	public void setSifraStraneOsobe(String sifraStraneOsobe) {
		this.sifraStraneOsobe = sifraStraneOsobe;
	}

	@IFilter(entityField = "nalogDevizni.osnov_nal")
	public Integer getSifraOsnove() {
		return sifraOsnove;
	}

	public void setSifraOsnove(Integer sifraOsnove) {
		this.sifraOsnove = sifraOsnove;
	}

	@IFilter(entityField = "nalogDevizni.instr_nal")
	public Integer getSifraDeviznogInstrumenta() {
		return sifraDeviznogInstrumenta;
	}

	public void setSifraDeviznogInstrumenta(Integer sifraDeviznogInstrumenta) {
		this.sifraDeviznogInstrumenta = sifraDeviznogInstrumenta;
	}

	@IFilter(entityField = "nalogDevizni.krepr_nal")
	public String getBrojKreditnePrijave() {
		return brojKreditnePrijave;
	}

	public void setBrojKreditnePrijave(String brojKreditnePrijave) {
		this.brojKreditnePrijave = brojKreditnePrijave;
	}

	@IFilter(entityField = "nalogDevizni.vpapr_nal")
	public String getVrijednosniPapir() {
		return vrijednosniPapir;
	}

	public void setVrijednosniPapir(String vrijednosniPapir) {
		this.vrijednosniPapir = vrijednosniPapir;
	}

	@IFilter(entityField = "nalogDevizni.stobr_nal")
	public String getStatusObrade() {
		return statusObrade;
	}

	public void setStatusObrade(String statusObrade) {
		this.statusObrade = statusObrade;
	}

	@IFilter(entityField = "nalogDevizni.rlref_nal")
	public String getVezanaReferenca() {
		return vezanaReferenca;
	}

	public void setVezanaReferenca(String vezanaReferenca) {
		this.vezanaReferenca = vezanaReferenca;
	}

	@IFilter(entityField = "nalogDevizni.swtyp_nal")
	public String getTipSwiftPoruke() {
		return tipSwiftPoruke;
	}

	public void setTipSwiftPoruke(String tipSwiftPoruke) {
		this.tipSwiftPoruke = tipSwiftPoruke;
	}

	@IFilter(entityField = "nalogDevizni.swsta_nal")
	public String getStatusSwiftObrade() {
		return statusSwiftObrade;
	}

	public void setStatusSwiftObrade(String statusSwiftObrade) {
		this.statusSwiftObrade = statusSwiftObrade;
	}

	@IFilter(entityField = "nalogDevizni.trosk_nal")
	public BigDecimal getIznosInoTroska() {
		return iznosInoTroska;
	}

	public void setIznosInoTroska(BigDecimal iznosInoTroska) {
		this.iznosInoTroska = iznosInoTroska;
	}

	public Integer getSifraDeviznogNalogaSwift() {
		return sifraDeviznogNalogaSwift;
	}

	public void setSifraDeviznogNalogaSwift(Integer sifraDeviznogNalogaSwift) {
		this.sifraDeviznogNalogaSwift = sifraDeviznogNalogaSwift;
	}

	public NalogDevizniSwiftDto getNalogDevizniSwiftDto() {
		return nalogDevizniSwiftDto;
	}

	public void setNalogDevizniSwiftDto(NalogDevizniSwiftDto nalogDevizniSwiftDto) {
		this.nalogDevizniSwiftDto = nalogDevizniSwiftDto;
	}

	public Integer getSifraDeviznogNalogaExtended() {
		return sifraDeviznogNalogaExtended;
	}

	public void setSifraDeviznogNalogaExtended(Integer sifraDeviznogNalogaExtended) {
		this.sifraDeviznogNalogaExtended = sifraDeviznogNalogaExtended;
	}

	public NalogDevizniExtendedDto getNalogDevizniExtendedDto() {
		return nalogDevizniExtendedDto;
	}

	public void setNalogDevizniExtendedDto(NalogDevizniExtendedDto nalogDevizniExtendedDto) {
		this.nalogDevizniExtendedDto = nalogDevizniExtendedDto;
	}

	public BigDecimal getIznosPartije() {
		return iznosPartije;
	}

	public void setIznosPartije(BigDecimal iznosPartije) {
		this.iznosPartije = iznosPartije;
	}

	public BigDecimal getIznosDomicilni() {
		return iznosDomicilni;
	}

	public void setIznosDomicilni(BigDecimal iznosDomicilni) {
		this.iznosDomicilni = iznosDomicilni;
	}

	public String getSifraValutePartije() {
		return sifraValutePartije;
	}

	public void setSifraValutePartije(String sifraValutePartije) {
		this.sifraValutePartije = sifraValutePartije;
	}

	public BigDecimal getIznosObracunateNaknade() {
		return iznosObracunateNaknade;
	}

	public void setIznosObracunateNaknade(BigDecimal iznosObracunateNaknade) {
		this.iznosObracunateNaknade = iznosObracunateNaknade;
	}

	public Boolean getExistNaknada() {
		return existNaknada;
	}

	public void setExistNaknada(Boolean existNaknada) {
		this.existNaknada = existNaknada;
	}

	public String getPartijaNaplateNaknade() {
		return partijaNaplateNaknade;
	}

	public void setPartijaNaplateNaknade(String partijaNaplateNaknade) {
		this.partijaNaplateNaknade = partijaNaplateNaknade;
	}

	public Integer getSifraPartijeNaknade() {
		return sifraPartijeNaknade;
	}

	public void setSifraPartijeNaknade(Integer sifraPartijeNaknade) {
		this.sifraPartijeNaknade = sifraPartijeNaknade;
	}

	public Integer getSifraPaketaNakande() {
		return sifraPaketaNakande;
	}

	public void setSifraPaketaNakande(Integer sifraPaketaNakande) {
		this.sifraPaketaNakande = sifraPaketaNakande;
	}

	public Integer getSifraProizvodaNaknade() {
		return sifraProizvodaNaknade;
	}

	public void setSifraProizvodaNaknade(Integer sifraProizvodaNaknade) {
		this.sifraProizvodaNaknade = sifraProizvodaNaknade;
	}

	public Integer getSifraKomitentaNaknade() {
		return sifraKomitentaNaknade;
	}

	public void setSifraKomitentaNaknade(Integer sifraKomitentaNaknade) {
		this.sifraKomitentaNaknade = sifraKomitentaNaknade;
	}

	@IFilter(entityField = "nalogDevizni.datpo_nrz")
	public Date getDatumPosla() {
		return datumPosla;
	}

	public void setDatumPosla(Date datumPosla) {
		this.datumPosla = datumPosla;
	}

	public String getGrupaKlaseNaloga() {
		return grupaKlaseNaloga;
	}

	public void setGrupaKlaseNaloga(String grupaKlaseNaloga) {
		this.grupaKlaseNaloga = grupaKlaseNaloga;
	}

	public String getPartijaPokrica() {
		return partijaPokrica;
	}

	public void setPartijaPokrica(String partijaPokrica) {
		this.partijaPokrica = partijaPokrica;
	}

	public String getTipProizvoda() {
		return tipProizvoda;
	}

	public void setTipProizvoda(String tipProizvoda) {
		this.tipProizvoda = tipProizvoda;
	}

	public String getTipTecaja() {
		return tipTecaja;
	}

	public void setTipTecaja(String tipTecaja) {
		this.tipTecaja = tipTecaja;
	}

	public String getOznakaNadDijela() {
		return oznakaNadDijela;
	}

	public void setOznakaNadDijela(String oznakaNadDijela) {
		this.oznakaNadDijela = oznakaNadDijela;
	}

	public String getMomentDospijecaDesc() {
		return momentDospijecaDesc;
	}

	public void setMomentDospijecaDesc(String momentDospijecaDesc) {
		this.momentDospijecaDesc = momentDospijecaDesc;
	}

	public String getLoginLikvidatora() {
		return loginLikvidatora;
	}

	public void setLoginLikvidatora(String loginLikvidatora) {
		this.loginLikvidatora = loginLikvidatora;
	}

	public String getNazivPoslovnice() {
		return nazivPoslovnice;
	}

	public void setNazivPoslovnice(String nazivPoslovnice) {
		this.nazivPoslovnice = nazivPoslovnice;
	}

	public String getNazivAplikacije() {
		return nazivAplikacije;
	}

	public void setNazivAplikacije(String nazivAplikacije) {
		this.nazivAplikacije = nazivAplikacije;
	}

	@IFilter(entityField = "nalogKnjizni.sifra_nal")
	public Integer getSifraKnjiznogNalogaDevizni() {
		return sifraKnjiznogNalogaDevizni;
	}

	public void setSifraKnjiznogNalogaDevizni(Integer sifraKnjiznogNalogaDevizni) {
		this.sifraKnjiznogNalogaDevizni = sifraKnjiznogNalogaDevizni;
	}

	public Integer getMomentDospijeca() {
		return momentDospijeca;
	}

	public void setMomentDospijeca(Integer momentDospijeca) {
		this.momentDospijeca = momentDospijeca;
	}

	public BigDecimal getIznosNative() {
		return iznosNative;
	}

	public void setIznosNative(BigDecimal iznosNative) {
		this.iznosNative = iznosNative;
	}

	public BigDecimal getIznosGlavnica() {
		return iznosGlavnica;
	}

	public void setIznosGlavnica(BigDecimal iznosGlavnica) {
		this.iznosGlavnica = iznosGlavnica;
	}

	public BigDecimal getIznosKamata() {
		return iznosKamata;
	}

	public void setIznosKamata(BigDecimal iznosKamata) {
		this.iznosKamata = iznosKamata;
	}

	public BigDecimal getIznosKns() {
		return iznosKns;
	}

	public void setIznosKns(BigDecimal iznosKns) {
		this.iznosKns = iznosKns;
	}

	public BigDecimal getIznosRata() {
		return iznosRata;
	}

	public void setIznosRata(BigDecimal iznosRata) {
		this.iznosRata = iznosRata;
	}

	public BigDecimal getDogovoreniTecaj() {
		return dogovoreniTecaj;
	}

	public void setDogovoreniTecaj(BigDecimal dogovoreniTecaj) {
		this.dogovoreniTecaj = dogovoreniTecaj;
	}

	public List<PrometWithLookupsDto> getListPrometWithLookupsDto() {
		return listPrometWithLookupsDto;
	}

	public void setListPrometWithLookupsDto(List<PrometWithLookupsDto> listPrometWithLookupsDto) {
		this.listPrometWithLookupsDto = listPrometWithLookupsDto;
	}

	public List<ObracunNaknadeDto> getListObracunNaknadeDetails() {
		return listObracunNaknadeDetails;
	}

	public void setListObracunNaknadeDetails(List<ObracunNaknadeDto> listObracunNaknadeDetails) {
		this.listObracunNaknadeDetails = listObracunNaknadeDetails;
	}

	public List<String> getMessagesFromBusinessLayer() {
		return messagesFromBusinessLayer;
	}

	public void setMessagesFromBusinessLayer(List<String> messagesFromBusinessLayer) {
		this.messagesFromBusinessLayer = messagesFromBusinessLayer;
	}

	public Integer getSifraDeviznoNaloga() {
		return sifraDeviznoNaloga;
	}

	public void setSifraDeviznoNaloga(Integer sifraDeviznoNaloga) {
		this.sifraDeviznoNaloga = sifraDeviznoNaloga;
	}

	public Boolean getSwiftMessageEnabled() {
		return swiftMessageEnabled;
	}

	public void setSwiftMessageEnabled(Boolean swiftMessageEnabled) {
		this.swiftMessageEnabled = swiftMessageEnabled;
	}

	public String getOznakaTarifneStavke() {
		return oznakaTarifneStavke;
	}

	public void setOznakaTarifneStavke(String oznakaTarifneStavke) {
		this.oznakaTarifneStavke = oznakaTarifneStavke;
	}

	public Integer getSifraTarifneStavke() {
		return sifraTarifneStavke;
	}

	public void setSifraTarifneStavke(Integer sifraTarifneStavke) {
		this.sifraTarifneStavke = sifraTarifneStavke;
	}

	public String getTipOperacije22A() {
		return tipOperacije22A;
	}

	public void setTipOperacije22A(String tipOperacije22A) {
		this.tipOperacije22A = tipOperacije22A;
	}

	public String getSmjerTecaja() {
		return smjerTecaja;
	}

	public void setSmjerTecaja(String smjerTecaja) {
		this.smjerTecaja = smjerTecaja;
	}

	@IFilter(entityField = "bankuNrz.bicco_bic")
	public String getSwiftBicKorespodentDugovna() {
		return swiftBicKorespodentDugovna;
	}

	public void setSwiftBicKorespodentDugovna(String swiftBicKorespodentDugovna) {
		this.swiftBicKorespodentDugovna = swiftBicKorespodentDugovna;
	}

	public String getSwiftBicKorespodentDugovnaOpis() {
		return swiftBicKorespodentDugovnaOpis;
	}

	public void setSwiftBicKorespodentDugovnaOpis(String swiftBicKorespodentDugovnaOpis) {
		this.swiftBicKorespodentDugovnaOpis = swiftBicKorespodentDugovnaOpis;
	}

	@IFilter(entityField = "banprNrz.bicco_bic")
	public String getSwiftBicKorespodentPotrazni() {
		return swiftBicKorespodentPotrazni;
	}

	public void setSwiftBicKorespodentPotrazni(String swiftBicKorespodentPotrazni) {
		this.swiftBicKorespodentPotrazni = swiftBicKorespodentPotrazni;
	}

	public String getSwiftBicKorespodentPotrazniOpis() {
		return swiftBicKorespodentPotrazniOpis;
	}

	public void setSwiftBicKorespodentPotrazniOpis(String swiftBicKorespodentPotrazniOpis) {
		this.swiftBicKorespodentPotrazniOpis = swiftBicKorespodentPotrazniOpis;
	}

	public String getSwiftBicIntermediaryDugovna() {
		return swiftBicIntermediaryDugovna;
	}

	public void setSwiftBicIntermediaryDugovna(String swiftBicIntermediaryDugovna) {
		this.swiftBicIntermediaryDugovna = swiftBicIntermediaryDugovna;
	}

	public String getSwiftBicIntermediaryDugovnaOpis() {
		return swiftBicIntermediaryDugovnaOpis;
	}

	public void setSwiftBicIntermediaryDugovnaOpis(String swiftBicIntermediaryDugovnaOpis) {
		this.swiftBicIntermediaryDugovnaOpis = swiftBicIntermediaryDugovnaOpis;
	}

	public String getSwiftBicIntermediaryPotrazni() {
		return swiftBicIntermediaryPotrazni;
	}

	public void setSwiftBicIntermediaryPotrazni(String swiftBicIntermediaryPotrazni) {
		this.swiftBicIntermediaryPotrazni = swiftBicIntermediaryPotrazni;
	}

	public String getSwiftBicIntermediaryPotrazniOpis() {
		return swiftBicIntermediaryPotrazniOpis;
	}

	public void setSwiftBicIntermediaryPotrazniOpis(String swiftBicIntermediaryPotrazniOpis) {
		this.swiftBicIntermediaryPotrazniOpis = swiftBicIntermediaryPotrazniOpis;
	}

	@IFilter(entityField = "nalogDevizni.coref_nrz")
	public String getZajednickaReferenca22C() {
		return zajednickaReferenca22C;
	}

	public void setZajednickaReferenca22C(String zajednickaReferenca22C) {
		this.zajednickaReferenca22C = zajednickaReferenca22C;
	}

	public Integer getSifraDeviznogNalogaPrethodnika() {
		return sifraDeviznogNalogaPrethodnika;
	}

	public void setSifraDeviznogNalogaPrethodnika(Integer sifraDeviznogNalogaPrethodnika) {
		this.sifraDeviznogNalogaPrethodnika = sifraDeviznogNalogaPrethodnika;
	}

	public Integer getSifraPartijeAnalitike() {
		return sifraPartijeAnalitike;
	}

	public void setSifraPartijeAnalitike(Integer sifraPartijeAnalitike) {
		this.sifraPartijeAnalitike = sifraPartijeAnalitike;
	}

	public String getSwiftField() {
		return swiftField;
	}

	public void setSwiftField(String swiftField) {
		this.swiftField = swiftField;
	}

	public String getNazivDrzaveNalogodavateljaKorisnika() {
		return nazivDrzaveNalogodavateljaKorisnika;
	}

	public void setNazivDrzaveNalogodavateljaKorisnika(String nazivDrzaveNalogodavateljaKorisnika) {
		this.nazivDrzaveNalogodavateljaKorisnika = nazivDrzaveNalogodavateljaKorisnika;
	}

	public Integer getSifraNalogaAkcije() {
		return sifraNalogaAkcije;
	}

	public void setSifraNalogaAkcije(Integer sifraNalogaAkcije) {
		this.sifraNalogaAkcije = sifraNalogaAkcije;
	}

	public String getSifraValuteNaplateNaknade() {
		return sifraValuteNaplateNaknade;
	}

	public void setSifraValuteNaplateNaknade(String sifraValuteNaplateNaknade) {
		this.sifraValuteNaplateNaknade = sifraValuteNaplateNaknade;
	}

	public String getSifraPoslovniceNaknade() {
		return sifraPoslovniceNaknade;
	}

	public void setSifraPoslovniceNaknade(String sifraPoslovniceNaknade) {
		this.sifraPoslovniceNaknade = sifraPoslovniceNaknade;
	}

	public Integer getSwiftInitiator() {
		return swiftInitiator;
	}

	public void setSwiftInitiator(Integer swiftInitiator) {
		this.swiftInitiator = swiftInitiator;
	}

	public Integer getSifraSwiftHeadera() {
		return sifraSwiftHeadera;
	}

	public void setSifraSwiftHeadera(Integer sifraSwiftHeadera) {
		this.sifraSwiftHeadera = sifraSwiftHeadera;
	}

	public Integer getSifraDeviznogIzvoda() {
		return sifraDeviznogIzvoda;
	}

	public void setSifraDeviznogIzvoda(Integer sifraDeviznogIzvoda) {
		this.sifraDeviznogIzvoda = sifraDeviznogIzvoda;
	}

	public Integer getRedniBrojIzvoda() {
		return redniBrojIzvoda;
	}

	public void setRedniBrojIzvoda(Integer redniBrojIzvoda) {
		this.redniBrojIzvoda = redniBrojIzvoda;
	}

	public String getSifraSektoraNaknade() {
		return sifraSektoraNaknade;
	}

	public void setSifraSektoraNaknade(String sifraSektoraNaknade) {
		this.sifraSektoraNaknade = sifraSektoraNaknade;
	}

	public String getNazivDeviznogInstrumenta() {
		return nazivDeviznogInstrumenta;
	}

	public void setNazivDeviznogInstrumenta(String nazivDeviznogInstrumenta) {
		this.nazivDeviznogInstrumenta = nazivDeviznogInstrumenta;
	}

	public String getNazivOsnove() {
		return nazivOsnove;
	}

	public void setNazivOsnove(String nazivOsnove) {
		this.nazivOsnove = nazivOsnove;
	}

	public Integer getBrojDanaUnaprijed() {
		return brojDanaUnaprijed;
	}

	public void setBrojDanaUnaprijed(Integer brojDanaUnaprijed) {
		this.brojDanaUnaprijed = brojDanaUnaprijed;
	}

	public FizKomitentDto getNeklijent() {
		if (neklijent == null) neklijent = new FizKomitentDto();
		return neklijent;
	}

	public void setNeklijent(FizKomitentDto neklijent) {
		this.neklijent = neklijent;
	}

	public Integer getSifraKomitentaStranke() {
		return sifraKomitentaStranke;
	}

	public void setSifraKomitentaStranke(Integer sifraKomitentaStranke) {
		this.sifraKomitentaStranke = sifraKomitentaStranke;
	}

	public String getOibBrojKomitentaStranke() {
		return oibBrojKomitentaStranke;
	}

	public void setOibBrojKomitentaStranke(String oibBrojKomitentaStranke) {
		this.oibBrojKomitentaStranke = oibBrojKomitentaStranke;
	}

	public String getNazivKomitentaStranke() {
		return nazivKomitentaStranke;
	}

	public void setNazivKomitentaStranke(String nazivKomitentaStranke) {
		this.nazivKomitentaStranke = nazivKomitentaStranke;
	}

	public String getSifraSektoraStranke() {
		return sifraSektoraStranke;
	}

	public void setSifraSektoraStranke(String sifraSektoraStranke) {
		this.sifraSektoraStranke = sifraSektoraStranke;
	}

	public String getBrojIdentifikacijeStranke() {
		return brojIdentifikacijeStranke;
	}

	public void setBrojIdentifikacijeStranke(String brojIdentifikacijeStranke) {
		this.brojIdentifikacijeStranke = brojIdentifikacijeStranke;
	}

	public List<SpecifikacijaPokricaDto> getListSpecifikacijaPokrica() {
		return listSpecifikacijaPokrica;
	}

	public void setListSpecifikacijaPokrica(List<SpecifikacijaPokricaDto> listSpecifikacijaPokrica) {
		this.listSpecifikacijaPokrica = listSpecifikacijaPokrica;
	}

	public Integer getSifraPartijePokrica() {
		return sifraPartijePokrica;
	}

	public void setSifraPartijePokrica(Integer sifraPartijePokrica) {
		this.sifraPartijePokrica = sifraPartijePokrica;
	}

	public String getOpisPartijePokrica() {
		return opisPartijePokrica;
	}

	public void setOpisPartijePokrica(String opisPartijePokrica) {
		this.opisPartijePokrica = opisPartijePokrica;
	}

	public Integer getSifraDioPartije() {
		return sifraDioPartije;
	}

	public void setSifraDioPartije(Integer sifraDioPartije) {
		this.sifraDioPartije = sifraDioPartije;
	}

	public Integer getSifraTipaPartijeKontokorenta() {
		return sifraTipaPartijeKontokorenta;
	}

	public void setSifraTipaPartijeKontokorenta(Integer sifraTipaPartijeKontokorenta) {
		this.sifraTipaPartijeKontokorenta = sifraTipaPartijeKontokorenta;
	}

	public String getPartijaKontokorenta() {
		return partijaKontokorenta;
	}

	public void setPartijaKontokorenta(String partijaKontokorenta) {
		this.partijaKontokorenta = partijaKontokorenta;
	}

	public String getOpisPartijeKontokorenta() {
		return opisPartijeKontokorenta;
	}

	public void setOpisPartijeKontokorenta(String opisPartijeKontokorenta) {
		this.opisPartijeKontokorenta = opisPartijeKontokorenta;
	}

	public BigDecimal getIznosPolozenoPokrice() {
		return iznosPolozenoPokrice;
	}

	public void setIznosPolozenoPokrice(BigDecimal iznosPolozenoPokrice) {
		this.iznosPolozenoPokrice = iznosPolozenoPokrice;
	}

	public String getSifraValuteNaknade() {
		return sifraValuteNaknade;
	}

	public void setSifraValuteNaknade(String sifraValuteNaknade) {
		this.sifraValuteNaknade = sifraValuteNaknade;
	}

	public Integer getSifraPartijeFakture() {
		return sifraPartijeFakture;
	}

	public void setSifraPartijeFakture(Integer sifraPartijeFakture) {
		this.sifraPartijeFakture = sifraPartijeFakture;
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

	public String getSifraHsvpPosla() {
		return sifraHsvpPosla;
	}

	public void setSifraHsvpPosla(String sifraHsvpPosla) {
		this.sifraHsvpPosla = sifraHsvpPosla;
	}

	public String getOpisHsvpPosla() {
		return opisHsvpPosla;
	}

	public void setOpisHsvpPosla(String opisHsvpPosla) {
		this.opisHsvpPosla = opisHsvpPosla;
	}

	public Date getDatumValuteOd() {
		return datumValuteOd;
	}

	public void setDatumValuteOd(Date datumValuteOd) {
		this.datumValuteOd = datumValuteOd;
	}

	public Date getDatumValuteDo() {
		return datumValuteDo;
	}

	public void setDatumValuteDo(Date datumValuteDo) {
		this.datumValuteDo = datumValuteDo;
	}

	public Integer getNalogodavateljSpp() {
		return nalogodavateljSpp;
	}

	public void setNalogodavateljSpp(Integer nalogodavateljSpp) {
		this.nalogodavateljSpp = nalogodavateljSpp;
	}

	public String getSifraTipaVezeNak() {
		return sifraTipaVezeNak;
	}

	public void setSifraTipaVezeNak(String sifraTipaVezeNak) {
		this.sifraTipaVezeNak = sifraTipaVezeNak;
	}

	public Integer getRedniBrojAkcije() {
		return redniBrojAkcije;
	}

	public void setRedniBrojAkcije(Integer redniBrojAkcije) {
		this.redniBrojAkcije = redniBrojAkcije;
	}

	public String getPravilaSifra() {
		return pravilaSifra;
	}

	public void setPravilaSifra(String pravilaSifra) {
		this.pravilaSifra = pravilaSifra;
	}

	public String getPravilaOpis() {
		return pravilaOpis;
	}

	public void setPravilaOpis(String pravilaOpis) {
		this.pravilaOpis = pravilaOpis;
	}

	public String getInfoPrimatelju() {
		return infoPrimatelju;
	}

	public void setInfoPrimatelju(String infoPrimatelju) {
		this.infoPrimatelju = infoPrimatelju;
	}

	public String getDodatniId() {
		return dodatniId;
	}

	public void setDodatniId(String dodatniId) {
		this.dodatniId = dodatniId;
	}

	public String getOpisDobaraUsluga() {
		return opisDobaraUsluga;
	}

	public void setOpisDobaraUsluga(String opisDobaraUsluga) {
		this.opisDobaraUsluga = opisDobaraUsluga;
	}

	public String getDodatnaPotrebnaDokumentacija() {
		return dodatnaPotrebnaDokumentacija;
	}

	public void setDodatnaPotrebnaDokumentacija(String dodatnaPotrebnaDokumentacija) {
		this.dodatnaPotrebnaDokumentacija = dodatnaPotrebnaDokumentacija;
	}

	public String getDodatniUvjeti() {
		return dodatniUvjeti;
	}

	public void setDodatniUvjeti(String dodatniUvjeti) {
		this.dodatniUvjeti = dodatniUvjeti;
	}

	public String getCodeAppRule() {
		return codeAppRule;
	}

	public void setCodeAppRule(String codeAppRule) {
		this.codeAppRule = codeAppRule;
	}

	public String getDescriptionAppRule() {
		return descriptionAppRule;
	}

	public void setDescriptionAppRule(String descriptionAppRule) {
		this.descriptionAppRule = descriptionAppRule;
	}

	public Date getDatumPopratnogPisma() {
		return datumPopratnogPisma;
	}

	public void setDatumPopratnogPisma(Date datumPopratnogPisma) {
		this.datumPopratnogPisma = datumPopratnogPisma;
	}

	public Date getDatumPoruke() {
		return datumPoruke;
	}

	public void setDatumPoruke(Date datumPoruke) {
		this.datumPoruke = datumPoruke;
	}

	public String getPodTipSwiftPoruke() {
		return podTipSwiftPoruke;
	}

	public void setPodTipSwiftPoruke(String podTipSwiftPoruke) {
		this.podTipSwiftPoruke = podTipSwiftPoruke;
	}

	public String getBrojPartijeAnalitike() {
		return brojPartijeAnalitike;
	}

	public void setBrojPartijeAnalitike(String brojPartijeAnalitike) {
		this.brojPartijeAnalitike = brojPartijeAnalitike;
	}

	public Date getDatumRazgranicenja() {
		return datumRazgranicenja;
	}

	public void setDatumRazgranicenja(Date datumRazgranicenja) {
		this.datumRazgranicenja = datumRazgranicenja;
	}

	public boolean isGarancija() {
		return garancija;
	}

	public void setGarancija(boolean garancija) {
		this.garancija = garancija;
	}

	public BigDecimal getUkupniIznos33B() {
		return ukupniIznos33B;
	}

	public void setUkupniIznos33B(BigDecimal ukupniIznos33B) {
		this.ukupniIznos33B = ukupniIznos33B;
	}
	
	public BigDecimal getIznosInoTroskaPrimatelja() {
		return iznosInoTroskaPrimatelja;
	}

	public void setIznosInoTroskaPrimatelja(BigDecimal iznosInoTroskaPrimatelja) {
		this.iznosInoTroskaPrimatelja = iznosInoTroskaPrimatelja;
	}

	public boolean isSwiftMessageSimulation() {
		return swiftMessageSimulation;
	}

	public void setSwiftMessageSimulation(boolean swiftMessageSimulation) {
		this.swiftMessageSimulation = swiftMessageSimulation;
	}

	public String getTroskovi71D() {
		return troskovi71D;
	}

	public void setTroskovi71D(String troskovi71d) {
		troskovi71D = troskovi71d;
	}

	public BigDecimal getPovlasteniTecajZaduzenja() {
		return povlasteniTecajZaduzenja;
	}

	public void setPovlasteniTecajZaduzenja(BigDecimal povlasteniTecajZaduzenja) {
		this.povlasteniTecajZaduzenja = povlasteniTecajZaduzenja;
	}

	public BigDecimal getPovlasteniTecajOdobrenja() {
		return povlasteniTecajOdobrenja;
	}

	public void setPovlasteniTecajOdobrenja(BigDecimal povlasteniTecajOdobrenja) {
		this.povlasteniTecajOdobrenja = povlasteniTecajOdobrenja;
	}

	public BigDecimal getRegularniTecajZaduzenja() {
		return regularniTecajZaduzenja;
	}

	public void setRegularniTecajZaduzenja(BigDecimal regularniTecajZaduzenja) {
		this.regularniTecajZaduzenja = regularniTecajZaduzenja;
	}

	public BigDecimal getRegularniTecajOdobrenja() {
		return regularniTecajOdobrenja;
	}

	public void setRegularniTecajOdobrenja(BigDecimal regularniTecajOdobrenja) {
		this.regularniTecajOdobrenja = regularniTecajOdobrenja;
	}

	public BigDecimal getMaxIznosZaduzenja() {
		return maxIznosZaduzenja;
	}

	public void setMaxIznosZaduzenja(BigDecimal maxIznosZaduzenja) {
		this.maxIznosZaduzenja = maxIznosZaduzenja;
	}

	public BigDecimal getMaxIznosOdobrenja() {
		return maxIznosOdobrenja;
	}

	public void setMaxIznosOdobrenja(BigDecimal maxIznosOdobrenja) {
		this.maxIznosOdobrenja = maxIznosOdobrenja;
	}

	public Integer getSifraPovlastenogTecaja() {
		return sifraPovlastenogTecaja;
	}

	public void setSifraPovlastenogTecaja(Integer sifraPovlastenogTecaja) {
		this.sifraPovlastenogTecaja = sifraPovlastenogTecaja;
	}

}
