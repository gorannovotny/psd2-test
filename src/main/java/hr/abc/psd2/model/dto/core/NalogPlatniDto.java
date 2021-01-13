package hr.abc.psd2.model.dto.core;

import hr.abc.psd2.annotation.IFilter;
import hr.abc.psd2.model.dto.GenericDto;
import hr.abc.psd2.util.GenericBassxConstants;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * Transfer object za platni nalog.
 *
 * @author Matija Hlapčić
 */
public class NalogPlatniDto extends GenericDto<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	// fields
	protected BigDecimal iznos; // iznos naloga
	protected String sifraValute; // valuta u kojoj je zadana transakcija
	private String sifraValutePokrica; // valuta pokrića naloga
	protected String ibanZaduzenja; // IBAN zaduženja
	private String nazivDebtor, adresaDebtor, gradDebtor, drzavaDebtor, idDebtor, ultimateDebtorNaziv, ultimateDebtorId; // terećenje
	protected String ibanOdobrenja; // IBAN odobrenja
	private String nazivCreditor, adresaCreditor, gradCreditor, drzavaCreditor, idCreditor, ultimateCreditorNaziv, ultimateCreditorId; // odobrenje
	private String modelZaduzenja; // model zaduženja
	private String pozivNaBrojZaduzenja; // poziv na broj zaduženja
	private String modelOdobrenja; // model odobrenja
	private String pozivNaBrojOdobrenja; // poziv na broj odobrenja
	private String nacinIzvrsenja; // način izvršenja (vanjski sustavi, interni nalog, MQ, itd.)
	private String smjer; // smjer zadavanja naloga (D ili P)
	private String referenca; // referenca
	private String pmtInfId; // Payment information id - korisnički id kod batch bookinga
	private String greska; // greška
	private Integer sifraKnjiznogNaloga; // knjižni nalog uz koji je vezan platni nalog
	private NalogKnjizniDto knjizniNalog; // knjižni nalog koji je vezan za platni nalog
	private String swiftNalogodavateljaKorisnika, swiftNalogodavateljaKorisnikaNaziv; // SWIFT BIC nalogodavatelja/korisnika - krajnje odredište gdje se isplaćuju sredstva
	private String trosakInoBanke; // trošak ino banke BEN, SHA, OUR
	private String sifraNamjene; // šifra namjene - Purpose code
	private String kategorijaNamjene, sifraKategorijaNamjene; // kategorija namjene
	private String sifraOpisaPlacanja; // šifra opisa plačanja 190 - sužbeni put, 270 - božićnica ...
    private String statusObrade; // status obrade koji ide nezavisno od statusa knjižnog naloga - npr. rasknjiženje izvoda, raspored priljeva, itd.
    private Integer nalogodavateljSpp; // nalogodavatelj za SPP
	private Integer sifraOznakePosla; // šifra oznake posla - šifarnik
	// extra filter fields
	protected Date datumValute;
	protected Date datumKnjizenja;
	protected String status;
	protected String svrha;
	protected Integer sifraTipaNaloga;
	private Integer sifraPrethodnogNaloga; // PrethTrn - platni nalog
	private Integer sifraNalogaPrethodnika; // PrenaNal - knjizni nalog
	private String sifraHsvpPosla; // šifra HSVP posla
	private String tipPoruke; // tip poruke
    private String hsvppTrn; // HSVP - polje
    private String hsvpoTrn; // HSVP - polje
    private String grpidTrn; // group ID
    // statistika PP
    private Integer nalogodavatelj, izvorSredstava;
    private String osobaPrimatelj;
	// entity versioning
	private Integer version;
	// knjižne i transakcijske opcije
	private String valutaZaduzenja;
	private String valutaOdobrenja;
	private String tipTecajaZaduzenja;
	private String tipTecajaOdobrenja;
	private BigDecimal tecajZaduzenja;
	private BigDecimal tecajOdobrenja;
	private BigDecimal iznosZaduzenja;
	private BigDecimal iznosOdobrenja;
	private Integer sifraPartijeZaduzenja;
	private Integer sifraPartijeOdobrenja;
	private String statusPartijaZaduzenja;
	private String statusPartijaOdobrenja;
	// lookups
	private String nazivKomitentaZaduzenja;
	private String nazivKomitentaOdobrenja;
	private String oznakaValuteZaduzenja;
	private String oznakaValuteOdobrenja;
	private String nazivValuteZaduzenja;
	private String nazivValuteOdobrenja;
	private String smjerTecaja; // smjer zadavanja tečaja naloga (D ili P)
	// komitent lookup helper fileds
	private String maticniBrojKomitenta, oibKomitenta;
	private String nazivKomitenta;
	// klasa naloga
	private Integer sifraKlaseNaloga;
	private String oznakaKlaseNaloga, nazivKlaseNaloga;
	private String oznakaValute;
	private Integer sifraLikvidatora, sifraModula;
	private String sifraPoslovnice;
	private Integer sifraNalogaStorna;
	// raspoloživo zaduženja/odobrenja
	private BigDecimal raspolozivoZaduzenja, raspolozivoOdobrenja;
	private BigDecimal iznosRezervacije;
	private String medij;
	private String vrstaTransakcije; // vrsta transakcije (M - međunarodna P - prekogranična N - nacionalna)
	private String ident;
	private String oznakaDijelaOdobrenja;
	private GenericDto<Integer> nalogAnalitika;
	private String brojPartijeUpp;
	private Integer brojPromjena, brojIzvoda;
	private Integer hitnost; // 1 - hitno, 0 - redovno  (default), 2 - instant
	// kod uplata na kredite treba znati da li je uplata od vlasnika ili jamca
	private Integer sifraVlasnikJamac;
	// tranzitna polja
	private String terecenjeOpis, odobrenjeOpis;
	// utility mapa za raznu upotrebu
	private Map<String, Object> utilMap = new HashMap<String, Object>();
	private PartijaDto partijaZaduzenja, partijaOdobrenja;
	//za reportove verifikacije
	private Date vrijemeNaloga;
    // Ivan dodano radi reporta:
    private String likvidator;
    private String oznakaValutePokrica;
    private String nazivBanke;
    private String adresaBanke;
    private String postaBanke;
    private String drzavaBanke;

	//Kristijan dodao radi ocuvanja informacije
	private Boolean batchBooking;
	private String painPaymentInfoId;
	private String painMessageId;
	private String painInitiatingPartyName;
	private String painInitiatingPartyOrgId;
	private String painPaymentInformationId;
	private String painPaymentMethod;

	// public no argument constructor
	public NalogPlatniDto() {
		super();
	}

	// private contructor from builder
	protected NalogPlatniDto(NalogPlatniDtoBuilder<?> builder) {
		super();
		this.sifra = builder.sifra;
		this.iznos = builder.iznos;
		this.sifraValute = builder.sifraValute;
		this.sifraValutePokrica = builder.sifraValutePokrica;
		this.ibanZaduzenja = builder.ibanZaduzenja;
		this.nazivDebtor = builder.nazivDebtor;
		this.adresaDebtor = builder.adresaDebtor;
		this.drzavaDebtor = builder.drzavaDebtor;
		this.gradDebtor = builder.gradDebtor;
		this.ultimateDebtorNaziv = builder.ultimateDebtorNaziv;
		this.ultimateDebtorId = builder.ultimateDebtorId;
		this.ibanOdobrenja = builder.ibanOdobrenja;
		this.nazivCreditor = builder.nazivCreditor;
		this.adresaCreditor = builder.adresaCreditor;
		this.gradCreditor = builder.gradCreditor;
		this.drzavaCreditor = builder.drzavaCreditor;
		this.ultimateCreditorNaziv = builder.ultimateCreditorNaziv;
		this.ultimateCreditorId = builder.ultimateCreditorId;
		this.modelZaduzenja = builder.modelZaduzenja;
		this.pozivNaBrojZaduzenja = builder.pozivNaBrojZaduzenja;
		this.modelOdobrenja = builder.modelOdobrenja;
		this.pozivNaBrojOdobrenja = builder.pozivNaBrojOdobrenja;
		this.nacinIzvrsenja = builder.nacinIzvrsenja;
		this.smjer = builder.smjer;
		this.referenca = builder.referenca;
		this.greska = builder.greska;
		this.sifraKnjiznogNaloga = builder.sifraKnjiznogNaloga;
		this.knjizniNalog = builder.knjizniNalog;
		this.sifraKlaseNaloga = builder.sifraKlaseNaloga;
		this.sifraPrethodnogNaloga = builder.sifraPrethodnogNaloga;
		this.swiftNalogodavateljaKorisnika = builder.swiftNalogodavateljaKorisnika;
		this.trosakInoBanke = builder.trosakInoBanke;
		this.sifraHsvpPosla = builder.sifraHsvpPosla;
		this.tipPoruke = builder.tipPoruke;
        this.statusObrade = builder.statusObrade;
        this.nalogodavateljSpp = builder.nalogodavateljSpp;
	}

	public NalogPlatniDto(boolean isFirstCall) {
		super(isFirstCall);
	}

	// builder
	public static abstract class NalogPlatniDtoBuilder<T extends NalogPlatniDtoBuilder<T>> {
		// mandate fields
		private String sifraValute, ibanZaduzenja, ibanOdobrenja, nacinIzvrsenja, smjer;
		private BigDecimal iznos;
		private Integer sifraKnjiznogNaloga;

		// optional fields + initialization
        private Integer sifra, sifraKlaseNaloga, sifraPrethodnogNaloga, nalogodavateljSpp;
        private String sifraValutePokrica, modelZaduzenja, pozivNaBrojZaduzenja, modelOdobrenja, pozivNaBrojOdobrenja, referenca, greska, nazivDebtor, adresaDebtor, gradDebtor, drzavaDebtor, idDebtor, ultimateDebtorNaziv, ultimateDebtorId, nazivCreditor, adresaCreditor, gradCreditor, drzavaCreditor, idCreditor, ultimateCreditorNaziv, ultimateCreditorId, swiftNalogodavateljaKorisnika, trosakInoBanke, sifraHsvpPosla, tipPoruke, statusObrade;
		private NalogKnjizniDto knjizniNalog;

		// mandate constructor
		public NalogPlatniDtoBuilder(String sifraValute, String ibanZaduzenja, String ibanOdobrenja, String nacinIzvrsenja, String smjer, BigDecimal iznos, Integer sifraKnjiznogNaloga) {
			super();
			this.sifraValute = sifraValute;
			this.ibanZaduzenja = ibanZaduzenja;
			this.ibanOdobrenja = ibanOdobrenja;
			this.nacinIzvrsenja = nacinIzvrsenja;
			this.smjer = smjer;
			this.iznos = iznos;
			this.sifraKnjiznogNaloga = sifraKnjiznogNaloga;
		}

		// reference on concrete class that implements builder
		protected abstract T self();

		// methods for optional parameters
		public T sifra(Integer sifra) {
			this.sifra = sifra;
			return self();
		}

		public T valutaPokrica(String sifraValutePokrica) {
			this.sifraValutePokrica = sifraValutePokrica;
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

		public T referenca(String referenca) {
			this.referenca = referenca;
			return self();
		}

		public T greska(String greska) {
			this.greska = greska;
			return self();
		}

		public T knjizniNalog(NalogKnjizniDto knjizniNalog) {
			this.knjizniNalog = knjizniNalog;
			return self();
		}

		public T sifraKlaseNaloga(Integer sifraKlaseNaloga) {
			this.sifraKlaseNaloga = sifraKlaseNaloga;
			return self();
		}

		public T sifraPrethodnogNaloga(Integer sifraPrethodnogNaloga) {
			this.sifraPrethodnogNaloga = sifraPrethodnogNaloga;
			return self();
		}

		public T nazivDebtor(String nazivDebtor) {
			this.nazivDebtor = nazivDebtor;
			return self();
		}

		public T adresaDebtor(String adresaDebtor) {
			this.adresaDebtor = adresaDebtor;
			return self();
		}

		public T gradDebtor(String gradDebtor) {
			this.gradDebtor = gradDebtor;
			return self();
		}

		public T drzavaDebtor(String drzavaDebtor) {
			this.drzavaDebtor = drzavaDebtor;
			return self();
		}

		public T idDebtor(String idDebtor) {
			this.idDebtor = idDebtor;
			return self();
		}

		public T ultimateDebtorNaziv(String ultimateDebtorNaziv) {
			this.ultimateDebtorNaziv = ultimateDebtorNaziv;
			return self();
		}

		public T ultimateDebtorId(String ultimateDebtorId) {
			this.ultimateDebtorId = ultimateDebtorId;
			return self();
		}

		public T nazivCreditor(String nazivCreditor) {
			this.nazivCreditor = nazivCreditor;
			return self();
		}

		public T adresaCreditor(String adresaCreditor) {
			this.adresaCreditor = adresaCreditor;
			return self();
		}

		public T gradCreditor(String gradCreditor) {
			this.gradCreditor = gradCreditor;
			return self();
		}

		public T drzavaCreditor(String drzavaCreditor) {
			this.drzavaCreditor = drzavaCreditor;
			return self();
		}

		public T idCreditor(String idCreditor) {
			this.idCreditor = idCreditor;
			return self();
		}

		public T ultimateCreditorNaziv(String ultimateCreditorNaziv) {
			this.ultimateCreditorNaziv = ultimateCreditorNaziv;
			return self();
		}

		public T ultimateCreditorId(String ultimateCreditorId) {
			this.ultimateCreditorId = ultimateCreditorId;
			return self();
		}

		public T swiftNalogodavateljaKorisnika(String swiftNalogodavateljaKorisnika) {
			this.swiftNalogodavateljaKorisnika = swiftNalogodavateljaKorisnika;
			return self();
		}

		public T trosakInoBanke(String trosakInoBanke) {
			this.trosakInoBanke = trosakInoBanke;
			return self();
		}

		public T sifraHsvpPosla(String sifraHsvpPosla) {
			this.sifraHsvpPosla = sifraHsvpPosla;
			return self();
		}

		public T tipPoruke(String tipPoruke) {
			this.tipPoruke = tipPoruke;
			return self();
		}

        public T statusObrade(String statusObrade) {
        	this.statusObrade = statusObrade;
        	return self();
        }
        public T nalogodavateljSpp(Integer nalogodavateljSpp) {
        	this.nalogodavateljSpp = nalogodavateljSpp;
        	return self();
        }
		// build method
		public NalogPlatniDto build() {
			return new NalogPlatniDto(this);
		}
	}

	// implementation of abstract class that enables ingeritence
	private static class NalogPlatniDtoImplementationBuilder extends NalogPlatniDtoBuilder<NalogPlatniDtoImplementationBuilder> {

		// constructor
		public NalogPlatniDtoImplementationBuilder(String sifraValute, String ibanZaduzenja, String ibanOdobrenja, String nacinIzvrsenja, String smjer, BigDecimal iznos, Integer sifraKnjiznogNaloga) {
			super(sifraValute, ibanZaduzenja, ibanOdobrenja, nacinIzvrsenja, smjer, iznos, sifraKnjiznogNaloga);
		}

		@Override
		protected NalogPlatniDtoImplementationBuilder self() {
			return this;
		}

	}

	// returns implementation of builder
	public static NalogPlatniDtoBuilder<?> builder(String sifraValute, String ibanZaduzenja, String ibanOdobrenja, String nacinIzvrsenja, String smjer, BigDecimal iznos, Integer sifraKnjiznogNaloga) {
		return new NalogPlatniDtoImplementationBuilder(sifraValute, ibanZaduzenja, ibanOdobrenja, nacinIzvrsenja, smjer, iznos, sifraKnjiznogNaloga);
	}

	// getters & setters
	@IFilter(entityField = "nalogPlatni.sifra_trn")
	@Override
	public Integer getSifra() {
		return super.getSifra();
	}

	@IFilter(entityField = "nalogPlatni.iznos_trn")
	public BigDecimal getIznos() {
		return iznos;
	}

	public void setIznos(BigDecimal iznos) {
		this.iznos = iznos;
	}

	@IFilter(entityField = "nalogPlatni.valut_trn")
	public String getSifraValute() {
		return sifraValute;
	}

	public void setSifraValute(String sifraValute) {
		this.sifraValute = sifraValute;
	}

	@IFilter(entityField = "nalogPlatni.valpk_trn")
	public String getSifraValutePokrica() {
		return sifraValutePokrica;
	}

	public void setSifraValutePokrica(String sifraValutePokrica) {
		this.sifraValutePokrica = sifraValutePokrica;
	}

	@IFilter(entityField = "nalogPlatni.ibanz_trn")
	public String getIbanZaduzenja() {
		return ibanZaduzenja;
	}

	public void setIbanZaduzenja(String ibanZaduzenja) {
		this.ibanZaduzenja = ibanZaduzenja;
	}

	@IFilter(entityField = "nalogPlatni.ibano_trn")
	public String getIbanOdobrenja() {
		return ibanOdobrenja;
	}

	public void setIbanOdobrenja(String ibanOdobrenja) {
		this.ibanOdobrenja = ibanOdobrenja;
	}

	@IFilter(entityField = "nalogPlatni.mozad_trn")
	public String getModelZaduzenja() {
		return modelZaduzenja;
	}

	public void setModelZaduzenja(String modelZaduzenja) {
		this.modelZaduzenja = modelZaduzenja;
	}

	@IFilter(entityField = "nalogPlatni.pozad_trn")
	public String getPozivNaBrojZaduzenja() {
		return pozivNaBrojZaduzenja;
	}

	public void setPozivNaBrojZaduzenja(String pozivNaBrojZaduzenja) {
		this.pozivNaBrojZaduzenja = pozivNaBrojZaduzenja;
	}

	@IFilter(entityField = "nalogPlatni.moodo_trn")
	public String getModelOdobrenja() {
		return modelOdobrenja;
	}

	public void setModelOdobrenja(String modelOdobrenja) {
		this.modelOdobrenja = modelOdobrenja;
	}

	@IFilter(entityField = "nalogPlatni.poodo_trn")
	public String getPozivNaBrojOdobrenja() {
		return pozivNaBrojOdobrenja;
	}

	public void setPozivNaBrojOdobrenja(String pozivNaBrojOdobrenja) {
		this.pozivNaBrojOdobrenja = pozivNaBrojOdobrenja;
	}

	@IFilter(entityField = "nalogPlatni.naciz_trn")
	public String getNacinIzvrsenja() {
		return nacinIzvrsenja;
	}

	public void setNacinIzvrsenja(String nacinIzvrsenja) {
		this.nacinIzvrsenja = nacinIzvrsenja;
	}

	@IFilter(entityField = "nalogPlatni.smjer_trn")
	public String getSmjer() {
		return smjer;
	}

	public void setSmjer(String smjer) {
		this.smjer = smjer;
	}

	@IFilter(entityField = "nalogPlatni.gresk_trn")
	public String getGreska() {
		return greska;
	}

	@IFilter(entityField = "nalogPlatni.sinam_trn")
	public String getSifraNamjene() {
		return sifraNamjene;
	}

	public void setSifraNamjene(String sifraNamjene) {
		this.sifraNamjene = sifraNamjene;
	}

	public void setGreska(String greska) {
		if (greska != null && greska.length() > 80) {
			greska = greska.substring(0, 80);
		}
		this.greska = greska;
	}

	@IFilter(entityField = "nalogKnjizni.sifra_nal")
	public Integer getSifraKnjiznogNaloga() {
		return sifraKnjiznogNaloga;
	}

	public void setSifraKnjiznogNaloga(Integer sifraKnjiznogNaloga) {
		this.sifraKnjiznogNaloga = sifraKnjiznogNaloga;
	}

	public NalogKnjizniDto getKnjizniNalog() {
		if (knjizniNalog == null) {
			setKnjizniNalog(new NalogKnjizniDto());
		}
		return knjizniNalog;
	}

	public void setKnjizniNalog(NalogKnjizniDto knjizniNalog) {
		this.knjizniNalog = knjizniNalog;
	}

	@IFilter(entityField = "nalogKnjizni.datva_nal")
	public Date getDatumValute() {
		return datumValute;
	}

	public void setDatumValute(Date datumValute) {
		this.datumValute = datumValute;
	}

	@IFilter(entityField = "nalogKnjizni.datkn_nal")
	public Date getDatumKnjizenja() {
		return datumKnjizenja;
	}

	public void setDatumKnjizenja(Date datumKnjizenja) {
		this.datumKnjizenja = datumKnjizenja;
	}

	@IFilter(entityField = "nalogKnjizni.statu_nal")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@IFilter(entityField = "nalogKnjizni.svrha_nal")
	public String getSvrha() {
		return svrha;
	}

	public void setSvrha(String svrha) {
		this.svrha = svrha;
	}

	@IFilter(entityField = "tipNaloga.sifra_pod")
	public Integer getSifraTipaNaloga() {
		return sifraTipaNaloga;
	}

	public void setSifraTipaNaloga(Integer sifraTipaNaloga) {
		this.sifraTipaNaloga = sifraTipaNaloga;
	}

	@IFilter(entityField = "kategorijaNamjene.naziv_kan")
	public String getKategorijaNamjene() {
		return kategorijaNamjene;
	}

	public void setKategorijaNamjene(String kategorijaNamjene) {
		this.kategorijaNamjene = kategorijaNamjene;
	}

	@IFilter(entityField = "nalogPlatni.sifop_trn")
	public String getSifraOpisaPlacanja() {
		return sifraOpisaPlacanja;
	}

	public void setSifraOpisaPlacanja(String sifraOpisaPlacanja) {
		this.sifraOpisaPlacanja = sifraOpisaPlacanja;
	}

	@IFilter(entityField = "nalogPlatni.siozp_trn")
	public Integer getSifraOznakePosla() {
		return sifraOznakePosla;
	}

	public void setSifraOznakePosla(Integer sifraOznakePosla) {
		this.sifraOznakePosla = sifraOznakePosla;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getValutaZaduzenja() {
		return valutaZaduzenja;
	}

	public void setValutaZaduzenja(String valutaZaduzenja) {
		this.valutaZaduzenja = valutaZaduzenja;
	}

	public String getValutaOdobrenja() {
		return valutaOdobrenja;
	}

	public void setValutaOdobrenja(String valutaOdobrenja) {
		this.valutaOdobrenja = valutaOdobrenja;
	}

	public String getTipTecajaZaduzenja() {
		return tipTecajaZaduzenja;
	}

	public void setTipTecajaZaduzenja(String tipTecajaZaduzenja) {
		this.tipTecajaZaduzenja = tipTecajaZaduzenja;
	}

	public String getTipTecajaOdobrenja() {
		return tipTecajaOdobrenja;
	}

	public void setTipTecajaOdobrenja(String tipTecajaOdobrenja) {
		this.tipTecajaOdobrenja = tipTecajaOdobrenja;
	}

	public BigDecimal getTecajZaduzenja() {
		return tecajZaduzenja;
	}

	public void setTecajZaduzenja(BigDecimal tecajZaduzenja) {
		this.tecajZaduzenja = tecajZaduzenja;
	}

	public BigDecimal getTecajOdobrenja() {
		return tecajOdobrenja;
	}

	public void setTecajOdobrenja(BigDecimal tecajOdobrenja) {
		this.tecajOdobrenja = tecajOdobrenja;
	}

	public BigDecimal getIznosZaduzenja() {
		return iznosZaduzenja;
	}

	public void setIznosZaduzenja(BigDecimal iznosZaduzenja) {
		this.iznosZaduzenja = iznosZaduzenja;
	}

	public BigDecimal getIznosOdobrenja() {
		return iznosOdobrenja;
	}

	public void setIznosOdobrenja(BigDecimal iznosOdobrenja) {
		this.iznosOdobrenja = iznosOdobrenja;
	}

	public Integer getSifraPartijeZaduzenja() {
		return sifraPartijeZaduzenja;
	}

	public void setSifraPartijeZaduzenja(Integer sifraPartijeZaduzenja) {
		this.sifraPartijeZaduzenja = sifraPartijeZaduzenja;
	}

	public Integer getSifraPartijeOdobrenja() {
		return sifraPartijeOdobrenja;
	}

	public void setSifraPartijeOdobrenja(Integer sifraPartijeOdobrenja) {
		this.sifraPartijeOdobrenja = sifraPartijeOdobrenja;
	}

	public String getSmjerTecaja() {
		return smjerTecaja;
	}

	public void setSmjerTecaja(String smjerTecaja) {
		this.smjerTecaja = smjerTecaja;
	}

	@IFilter(entityField = "komitent.matbr_kom")
	public String getMaticniBrojKomitenta() {
		return maticniBrojKomitenta;
	}

	public void setMaticniBrojKomitenta(String maticniBrojKomitenta) {
		this.maticniBrojKomitenta = maticniBrojKomitenta;
	}

	@IFilter(entityField = "komitent.naziv_kom")
	public String getNazivKomitenta() {
		return nazivKomitenta;
	}

	public void setNazivKomitenta(String nazivKomitenta) {
		this.nazivKomitenta = nazivKomitenta;
	}

	public String getNazivKomitentaZaduzenja() {
		return nazivKomitentaZaduzenja;
	}

	public void setNazivKomitentaZaduzenja(String nazivKomitentaZaduzenja) {
		this.nazivKomitentaZaduzenja = nazivKomitentaZaduzenja;
	}

	public String getNazivKomitentaOdobrenja() {
		return nazivKomitentaOdobrenja;
	}

	public void setNazivKomitentaOdobrenja(String nazivKomitentaOdobrenja) {
		this.nazivKomitentaOdobrenja = nazivKomitentaOdobrenja;
	}

	public String getOznakaValuteZaduzenja() {
		return oznakaValuteZaduzenja;
	}

	public void setOznakaValuteZaduzenja(String oznakaValuteZaduzenja) {
		this.oznakaValuteZaduzenja = oznakaValuteZaduzenja;
	}

	public String getOznakaValuteOdobrenja() {
		return oznakaValuteOdobrenja;
	}

	public void setOznakaValuteOdobrenja(String oznakaValuteOdobrenja) {
		this.oznakaValuteOdobrenja = oznakaValuteOdobrenja;
	}

	public String getNazivValuteZaduzenja() {
		return nazivValuteZaduzenja;
	}

	public void setNazivValuteZaduzenja(String nazivValuteZaduzenja) {
		this.nazivValuteZaduzenja = nazivValuteZaduzenja;
	}

	public String getNazivValuteOdobrenja() {
		return nazivValuteOdobrenja;
	}

	public void setNazivValuteOdobrenja(String nazivValuteOdobrenja) {
		this.nazivValuteOdobrenja = nazivValuteOdobrenja;
	}

	public Integer getSifraKlaseNaloga() {
		return sifraKlaseNaloga;
	}

	public void setSifraKlaseNaloga(Integer sifraKlaseNaloga) {
		this.sifraKlaseNaloga = sifraKlaseNaloga;
	}

	public String getOznakaKlaseNaloga() {
		return oznakaKlaseNaloga;
	}

	public void setOznakaKlaseNaloga(String oznakaKlaseNaloga) {
		this.oznakaKlaseNaloga = oznakaKlaseNaloga;
	}

	public String getNazivKlaseNaloga() {
		return nazivKlaseNaloga;
	}

	public void setNazivKlaseNaloga(String nazivKlaseNaloga) {
		this.nazivKlaseNaloga = nazivKlaseNaloga;
	}

	@IFilter(entityField = "nalogPlatni.preth_trn")
	public Integer getSifraPrethodnogNaloga() {
		return sifraPrethodnogNaloga;
	}

	public void setSifraPrethodnogNaloga(Integer sifraPrethodnogNaloga) {
		this.sifraPrethodnogNaloga = sifraPrethodnogNaloga;
	}

	public String getStatusPartijaZaduzenja() {
		return statusPartijaZaduzenja;
	}

	public void setStatusPartijaZaduzenja(String statusPartijaZaduzenja) {
		this.statusPartijaZaduzenja = statusPartijaZaduzenja;
	}

	public String getStatusPartijaOdobrenja() {
		return statusPartijaOdobrenja;
	}

	public void setStatusPartijaOdobrenja(String statusPartijaOdobrenja) {
		this.statusPartijaOdobrenja = statusPartijaOdobrenja;
	}

	public String getOznakaValute() {
		return oznakaValute;
	}

	public void setOznakaValute(String oznakaValute) {
		this.oznakaValute = oznakaValute;
	}
	@IFilter(entityField = "nalog.slikv_nal")
	public Integer getSifraLikvidatora() {
		return sifraLikvidatora;
	}

	public void setSifraLikvidatora(Integer sifraLikvidatora) {
		this.sifraLikvidatora = sifraLikvidatora;
	}

	public BigDecimal getRaspolozivoZaduzenja() {
		return raspolozivoZaduzenja;
	}

	public void setRaspolozivoZaduzenja(BigDecimal raspolozivoZaduzenja) {
		this.raspolozivoZaduzenja = raspolozivoZaduzenja;
	}

	public BigDecimal getRaspolozivoOdobrenja() {
		return raspolozivoOdobrenja;
	}

	public void setRaspolozivoOdobrenja(BigDecimal raspolozivoOdobrenja) {
		this.raspolozivoOdobrenja = raspolozivoOdobrenja;
	}

	public String getVrstaTransakcije() {
		return vrstaTransakcije;
	}

	public void setVrstaTransakcije(String vrstaTransakcije) {
		this.vrstaTransakcije = vrstaTransakcije;
	}

	public Integer getSifraNalogaStorna() {
		return sifraNalogaStorna;
	}

	public void setSifraNalogaStorna(Integer sifraNalogaStorna) {
		this.sifraNalogaStorna = sifraNalogaStorna;
	}

	public void napuniTerecenje() {
		String rez = getNazivDebtor() != null ? getNazivDebtor() : "";
		rez = rez.concat(GenericBassxConstants.Core.NEW_LINE);
		rez = rez.concat(getAdresaDebtor() != null ? getAdresaDebtor() : "");
		rez = rez.concat(GenericBassxConstants.Core.NEW_LINE);
		rez = rez.concat(getDrzavaDebtor() != null ? getDrzavaDebtor() : "");
		rez = rez.concat(GenericBassxConstants.Core.NEW_LINE);
		setTerecenjeOpis(rez);
	}

	public void napuniOdobrenje() {
		String rez = getNazivCreditor() != null ? getNazivCreditor() : "";
		rez = rez.concat(GenericBassxConstants.Core.NEW_LINE);
		rez = rez.concat(getAdresaCreditor() != null ? getAdresaCreditor() : "");
		rez = rez.concat(GenericBassxConstants.Core.NEW_LINE);
		rez = rez.concat(getDrzavaCreditor() != null ? getDrzavaCreditor() : "");
		rez = rez.concat(GenericBassxConstants.Core.NEW_LINE);
		setOdobrenjeOpis(rez);
	}
	
	public void parseOpisTerecenja() {
		if (StringUtils.isNotBlank(getTerecenjeOpis())) {
			StringTokenizer tokenizer = new StringTokenizer(getTerecenjeOpis(), GenericBassxConstants.Core.NEW_LINE);
			setNazivDebtor(tokenizer.hasMoreTokens() ? tokenizer.nextToken().trim() : "");
			setAdresaDebtor(tokenizer.hasMoreTokens() ? tokenizer.nextToken().trim() : "");
			setGradDebtor(tokenizer.hasMoreTokens() ? tokenizer.nextToken().trim() : "");
			setDrzavaDebtor(tokenizer.hasMoreTokens() ? tokenizer.nextToken().trim() : "");
		} else {
			setNazivDebtor("");
			setAdresaDebtor("");
			setGradDebtor("");
			setDrzavaDebtor("");
		}
	}
	
	public void parseOpisOdobrenja() {
		if (StringUtils.isNotBlank(getOdobrenjeOpis())) {
			StringTokenizer tokenizer = new StringTokenizer(getOdobrenjeOpis(), GenericBassxConstants.Core.NEW_LINE);
			setNazivCreditor(tokenizer.hasMoreTokens() ? tokenizer.nextToken().trim() : "");
			setAdresaCreditor(tokenizer.hasMoreTokens() ? tokenizer.nextToken().trim() : "");
			setGradCreditor(tokenizer.hasMoreTokens() ? tokenizer.nextToken().trim() : "");
			setDrzavaCreditor(tokenizer.hasMoreTokens() ? tokenizer.nextToken().trim() : "");
		} else {
			setNazivCreditor("");
			setAdresaCreditor("");
			setGradCreditor("");
			setDrzavaCreditor("");
		}
	}

	@IFilter(entityField  = "nalog.aplik_nal")
	public Integer getSifraModula() {
		return sifraModula;
	}

	public void setSifraModula(Integer sifraModula) {
		this.sifraModula = sifraModula;
	}

    @IFilter(entityField  = "nalog.pojed_nal")
	public String getSifraPoslovnice() {
		return sifraPoslovnice;
	}

	public void setSifraPoslovnice(String sifraPoslovnice) {
		this.sifraPoslovnice = sifraPoslovnice;
	}

	@IFilter(entityField = "nalogPlatni.medij_trn")
	public String getMedij() {
		return medij;
	}

	public void setMedij(String medij) {
		this.medij = medij;
	}

	public Map<String, Object> getUtilMap() {
		return utilMap;
	}

	public void setUtilMap(Map<String, Object> utilMap) {
		this.utilMap = utilMap;
	}

	public GenericDto<Integer> getNalogAnalitika() {
		return nalogAnalitika;
	}

	public void setNalogAnalitika(GenericDto<Integer> nalogAnalitika) {
		this.nalogAnalitika = nalogAnalitika;
	}

	public Integer getSifraNalogaPrethodnika() {
		return sifraNalogaPrethodnika;
	}

	public void setSifraNalogaPrethodnika(Integer sifraNalogaPrethodnika) {
		this.sifraNalogaPrethodnika = sifraNalogaPrethodnika;
	}

	public String getBrojPartijeUpp() {
		return brojPartijeUpp;
	}

	public void setBrojPartijeUpp(String brojPartijeUpp) {
		this.brojPartijeUpp = brojPartijeUpp;
	}

	public void addExceptions(Exception e) {
		@SuppressWarnings("unchecked")
		List<Exception> lista = (List<Exception>) this.getUtilMap().get(GenericBassxConstants.Core.UTILMAP_KEY_EXCEPTION);
		if (lista == null) {
			lista = new ArrayList<Exception>();
		}
		lista.add(e);
		this.getUtilMap().put(GenericBassxConstants.Core.UTILMAP_KEY_EXCEPTION, lista);
	}

	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	public Integer getBrojPromjena() {
		return brojPromjena;
	}

	public void setBrojPromjena(Integer brojPromjena) {
		this.brojPromjena = brojPromjena;
	}

	public BigDecimal getIznosRezervacije() {
		return iznosRezervacije;
	}

	public void setIznosRezervacije(BigDecimal iznosRezervacije) {
		this.iznosRezervacije = iznosRezervacije;
	}

	public Integer getBrojIzvoda() {
		return brojIzvoda;
	}

	public void setBrojIzvoda(Integer brojIzvoda) {
		this.brojIzvoda = brojIzvoda;
	}

	public Integer getHitnost() {
		return hitnost;
	}

	public void setHitnost(Integer hitnost) {
		this.hitnost = hitnost;
	}

	public Integer getSifraVlasnikJamac() {
		return sifraVlasnikJamac;
	}

	public void setSifraVlasnikJamac(Integer sifraVlasnikJamac) {
		this.sifraVlasnikJamac = sifraVlasnikJamac;
	}

	@IFilter(entityField = "komitent.oib_kom")
	public String getOibKomitenta() {
		return oibKomitenta;
	}

	public void setOibKomitenta(String oibKomitenta) {
		this.oibKomitenta = oibKomitenta;
	}

	@IFilter(entityField = "nalogPlatni.nazad_trn")
	public String getNazivDebtor() {
		return nazivDebtor;
	}

	public void setNazivDebtor(String nazivDebtor) {
		this.nazivDebtor = nazivDebtor;
	}

	@IFilter(entityField = "nalogPlatni.adzad_trn")
	public String getAdresaDebtor() {
		return adresaDebtor;
	}

	public void setAdresaDebtor(String adresaDebtor) {
		this.adresaDebtor = adresaDebtor;
	}

	@IFilter(entityField = "nalogPlatni.grzad_trn")
	public String getGradDebtor() {
		return gradDebtor;
	}

	public void setGradDebtor(String gradDebtor) {
		this.gradDebtor = gradDebtor;
	}

	@IFilter(entityField = "nalogPlatni.drzad_trn")
	public String getDrzavaDebtor() {
		return drzavaDebtor;
	}

	public void setDrzavaDebtor(String drzavaDebtor) {
		this.drzavaDebtor = drzavaDebtor;
	}

	@IFilter(entityField = "nalogPlatni.idzad_trn")
	public String getIdDebtor() { return idDebtor; }

	public void setIdDebtor(String idDebtor) { this.idDebtor = idDebtor; }

	@IFilter(entityField = "nalogPlatni.ulnzd_trn")
	public String getUltimateDebtorNaziv() {
		return ultimateDebtorNaziv;
	}

	public void setUltimateDebtorNaziv(String ultimateDebtorNaziv) {
		this.ultimateDebtorNaziv = ultimateDebtorNaziv;
	}

	@IFilter(entityField = "nalogPlatni.ulizd_trn")
	public String getUltimateDebtorId() {
		return ultimateDebtorId;
	}

	public void setUltimateDebtorId(String ultimateDebtorId) {
		this.ultimateDebtorId = ultimateDebtorId;
	}

	@IFilter(entityField = "nalogPlatni.naodo_trn")
	public String getNazivCreditor() {
		return nazivCreditor;
	}

	public void setNazivCreditor(String nazivCreditor) {
		this.nazivCreditor = nazivCreditor;
	}

	@IFilter(entityField = "nalogPlatni.adodo_trn")
	public String getAdresaCreditor() {
		return adresaCreditor;
	}

	public void setAdresaCreditor(String adresaCreditor) {
		this.adresaCreditor = adresaCreditor;
	}

	@IFilter(entityField = "nalogPlatni.grodo_trn")
	public String getGradCreditor() {
		return gradCreditor;
	}

	public void setGradCreditor(String gradCreditor) {
		this.gradCreditor = gradCreditor;
	}

	@IFilter(entityField = "nalogPlatni.drodo_trn")
	public String getDrzavaCreditor() {
		return drzavaCreditor;
	}

	public void setDrzavaCreditor(String drzavaCreditor) {
		this.drzavaCreditor = drzavaCreditor;
	}

	@IFilter(entityField = "nalogPlatni.idodo_trn")
	public String getIdCreditor() { return idCreditor; }

	public void setIdCreditor(String idCreditor) { this.idCreditor = idCreditor; }

	@IFilter(entityField = "nalogPlatni.ulnod_trn")
	public String getUltimateCreditorNaziv() {
		return ultimateCreditorNaziv;
	}

	public void setUltimateCreditorNaziv(String ultimateCreditorNaziv) {
		this.ultimateCreditorNaziv = ultimateCreditorNaziv;
	}

	@IFilter(entityField = "nalogPlatni.uliod_trn")
	public String getUltimateCreditorId() {
		return ultimateCreditorId;
	}

	public void setUltimateCreditorId(String ultimateCreditorId) {
		this.ultimateCreditorId = ultimateCreditorId;
	}

	@IFilter(entityField = "nalogPlatni.refer_trn")
	public String getReferenca() {
		return referenca;
	}

	public void setReferenca(String referenca) {
		this.referenca = referenca;
	}

	@IFilter(entityField = "nalogPlatni.nazad_trn")
	public String getTerecenjeOpis() {
        if (StringUtils.isBlank(terecenjeOpis)) {
            napuniTerecenje();
        }
		return terecenjeOpis;
	}

	public void setTerecenjeOpis(String terecenjeOpis) {
		this.terecenjeOpis = terecenjeOpis;
	}

	@IFilter(entityField = "nalogPlatni.naodo_trn")
	public String getOdobrenjeOpis() {
        if (StringUtils.isBlank(odobrenjeOpis)) {
            napuniOdobrenje();
        }
		return odobrenjeOpis;
	}

	public void setOdobrenjeOpis(String odobrenjeOpis) {
		this.odobrenjeOpis = odobrenjeOpis;
	}

	@IFilter(entityField = "nalogPlatni.bicko_trn")
	public String getSwiftNalogodavateljaKorisnika() {
		return swiftNalogodavateljaKorisnika;
	}

	public void setSwiftNalogodavateljaKorisnika(String swiftNalogodavateljaKorisnika) {
		this.swiftNalogodavateljaKorisnika = swiftNalogodavateljaKorisnika;
	}

	public String getSwiftNalogodavateljaKorisnikaNaziv() {
		return swiftNalogodavateljaKorisnikaNaziv;
	}

	public PartijaDto getPartijaZaduzenja() {
		return partijaZaduzenja;
	}

	public void setPartijaZaduzenja(PartijaDto partijaZaduzenja) {
		this.partijaZaduzenja = partijaZaduzenja;
	}

	public PartijaDto getPartijaOdobrenja() {
		return partijaOdobrenja;
	}

	public void setPartijaOdobrenja(PartijaDto partijaOdobrenja) {
		this.partijaOdobrenja = partijaOdobrenja;
	}

	public void setSwiftNalogodavateljaKorisnikaNaziv(String swiftNalogodavateljaKorisnikaNaziv) {

		this.swiftNalogodavateljaKorisnikaNaziv = swiftNalogodavateljaKorisnikaNaziv;
	}

	@IFilter(entityField = "nalogPlatni.tropc_trn")
	public String getTrosakInoBanke() {
		return trosakInoBanke;
	}

	public void setTrosakInoBanke(String trosakInoBanke) {
		this.trosakInoBanke = trosakInoBanke;
	}

	public Integer getNalogodavatelj() {
		return nalogodavatelj;
	}

	public void setNalogodavatelj(Integer nalogodavatelj) {
		this.nalogodavatelj = nalogodavatelj;
	}

	public Integer getIzvorSredstava() {
		return izvorSredstava;
	}

	public void setIzvorSredstava(Integer izvorSredstava) {
		this.izvorSredstava = izvorSredstava;
	}

	public String getOznakaDijelaOdobrenja() {
		return oznakaDijelaOdobrenja;
	}

	public void setOznakaDijelaOdobrenja(String oznakaDijelaOdobrenja) {
		this.oznakaDijelaOdobrenja = oznakaDijelaOdobrenja;
	}

	public String getOsobaPrimatelj() {
		return osobaPrimatelj;
	}

	public void setOsobaPrimatelj(String osobaPrimatelj) {
		this.osobaPrimatelj = osobaPrimatelj;
	}

	public String getSifraHsvpPosla() {
		return sifraHsvpPosla;
	}

	public void setSifraHsvpPosla(String sifraHsvpPosla) {
		this.sifraHsvpPosla = sifraHsvpPosla;
	}

	public String getTipPoruke() {
		return tipPoruke;
	}

	public String getPmtInfId() {
		return pmtInfId;
	}

	public void setPmtInfId(String pmtInfId) {
		this.pmtInfId = pmtInfId;
	}

    @IFilter(entityField = "nalogPlatni.stobr_trn")
	public String getStatusObrade() {
		return statusObrade;
	}

	public void setStatusObrade(String statusObrade) {
		this.statusObrade = statusObrade;
	}
	public Integer getNalogodavateljSpp() {
		return nalogodavateljSpp;
	}
	public void setNalogodavateljSpp(Integer nalogodavateljSpp) {
		this.nalogodavateljSpp = nalogodavateljSpp;
	}

	public String getHsvppTrn() {
		return hsvppTrn;
	}

	public void setHsvppTrn(String hsvppTrn) {
		this.hsvppTrn = hsvppTrn;
	}

	public String getHsvpoTrn() {
		return hsvpoTrn;
	}

	public void setHsvpoTrn(String hsvpoTrn) {
		this.hsvpoTrn = hsvpoTrn;
	}

    public String getSifraKategorijaNamjene() {
        return sifraKategorijaNamjene;
    }

    public void setSifraKategorijaNamjene(String sifraKategorijaNamjene) {
        this.sifraKategorijaNamjene = sifraKategorijaNamjene;
    }

	public Date getVrijemeNaloga() {
		return vrijemeNaloga;
	}

	public void setVrijemeNaloga(Date vrijemeNaloga) {
		this.vrijemeNaloga = vrijemeNaloga;
	}

	public String getLikvidator() {
		return likvidator;
	}

	public void setLikvidator(String likvidator) {
		this.likvidator = likvidator;
	}

	public String getOznakaValutePokrica() {
		return oznakaValutePokrica;
	}

	public void setOznakaValutePokrica(String oznakaValutePokrica) {
		this.oznakaValutePokrica = oznakaValutePokrica;
	}

	public String getNazivBanke() {
		return nazivBanke;
	}

	public void setNazivBanke(String nazivBanke) {
		this.nazivBanke = nazivBanke;
	}

	public String getAdresaBanke() {
		return adresaBanke;
	}

	public void setAdresaBanke(String adresaBanke) {
		this.adresaBanke = adresaBanke;
	}

	public String getPostaBanke() {
		return postaBanke;
	}

	public void setPostaBanke(String postaBanke) {
		this.postaBanke = postaBanke;
	}

	public String getDrzavaBanke() {
		return drzavaBanke;
	}

	public void setDrzavaBanke(String drzavaBanke) {
		this.drzavaBanke = drzavaBanke;
	}

	public void setTipPoruke(String tipPoruke) {
		this.tipPoruke = tipPoruke;
	}

	public Boolean getBatchBooking() {
		return batchBooking;
	}

	public void setBatchBooking(Boolean batchBooking) {
		this.batchBooking = batchBooking;
	}

	public String getPainPaymentInfoId() {
		return painPaymentInfoId;
	}

	public void setPainPaymentInfoId(String painPaymentInfoId) {
		this.painPaymentInfoId = painPaymentInfoId;
	}

	public String getPainMessageId() {
		return painMessageId;
	}

	public void setPainMessageId(String painMessageId) {
		this.painMessageId = painMessageId;
	}

	public String getPainInitiatingPartyName() {
		return painInitiatingPartyName;
	}

	public void setPainInitiatingPartyName(String painInitiatingPartyName) {
		this.painInitiatingPartyName = painInitiatingPartyName;
	}

	public String getPainInitiatingPartyOrgId() {
		return painInitiatingPartyOrgId;
	}

	public void setPainInitiatingPartyOrgId(String painInitiatingPartyOrgId) {
		this.painInitiatingPartyOrgId = painInitiatingPartyOrgId;
	}

	public String getPainPaymentInformationId() {
		return painPaymentInformationId;
	}

	public void setPainPaymentInformationId(String painPaymentInformationId) {
		this.painPaymentInformationId = painPaymentInformationId;
	}

	public String getPainPaymentMethod() {
		return painPaymentMethod;
	}

	public void setPainPaymentMethod(String painPaymentMethod) {
		this.painPaymentMethod = painPaymentMethod;
	}

	public String getGrpidTrn() {
		return grpidTrn;
	}

	public void setGrpidTrn(String grpidTrn) {
		this.grpidTrn = grpidTrn;
	}
}
