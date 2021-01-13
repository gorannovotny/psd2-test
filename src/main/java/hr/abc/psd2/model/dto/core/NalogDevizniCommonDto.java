package hr.abc.psd2.model.dto.core;

import hr.abc.psd2.annotation.IFilter;
import hr.abc.psd2.model.dto.GenericDto;
import hr.abc.psd2.util.GenericBassxConstants;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;

/**
 * Transfer object za zajednički dio deviznog naloga.
 * 
 * @author Matija Hlapčić
 */
public class NalogDevizniCommonDto extends GenericDto<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	// fields
	protected BigDecimal iznos; // iznos naloga
	protected String sifraValute; // valuta u kojoj je zadana transakcija
	private String sifraValutePokrica; // valuta pokrića naloga
	protected String ibanZaduzenja; // IBAN zaduženja
	private String nazivDebtor, adresaDebtor, gradDebtor, drzavaDebtor, idDebtor, ultimateDebtorNaziv, ultimateDebtorId; // terećenje
	protected String ibanOdobrenja; // IBAN odobrenja
	private String nazivCreditor, adresaCreditor, gradCreditor, drzavaCreditor, idcreditor, ultimateCreditorNaziv, ultimateCreditorId; // odobrenje
	private String nacinIzvrsenja; // način izvršenja (vanjski sustavi, interni nalog, MQ, itd.)
	private String smjer; // smjer zadavanja naloga (D ili P)
	private String referenca; // referenca
	private Integer sifraKnjiznogNaloga; // knjižni nalog uz koji je vezan platni nalog
	private NalogKnjizniDto knjizniNalog; // knjižni nalog koji je vezan za platni nalog
	private String swiftNalogodavateljaKorisnika, swiftNalogodavateljaKorisnikaNaziv; // SWIFT BIC nalogodavatelja/korisnika - krajnje odredište gdje se isplaćuju sredstva
	private String trosakInoBanke; // trošak ino banke BEN, SHA, OUR
	private String medij; // medije - papir, elektronski, itd.
	// extra filter fields
	protected Date datumValute, datumKnjizenja;
	protected String status;
	protected String svrha;
	protected Integer sifraTipaNaloga;
	private Integer sifraPrethodnogNaloga; // PrethTrn - platni nalog
	private Integer sifraNalogaPrethodnika; // PrenaNal - knjizni nalog
	// entity versioning
	private Integer version;
	// knjižne i transakcijske opcije
	private String sifraValuteZaduzenja, oznakaValuteZaduzenja, nazivValuteZaduzenja;
	private String sifraValuteOdobrenja, oznakaValuteOdobrenja, nazivValuteOdobrenja;
	private String tipTecajaZaduzenja;
	private String tipTecajaOdobrenja;
	private BigDecimal tecajZaduzenja, iznosZaduzenja;
	private BigDecimal tecajOdobrenja, iznosOdobrenja;
	private Integer sifraPartijeZaduzenja;
	private Integer sifraPartijeOdobrenja;
	private String brojPartijaZaduzenja, statusPartijaZaduzenja;
	private String brojPartijaOdobrenja, statusPartijaOdobrenja;
	// lookups
	private String nazivKomitentaZaduzenja;
	private String nazivKomitentaOdobrenja;
	private String smjerTecaja; // smjer zadavanja tečaja naloga (D ili P)
	// komitent lookup helper fileds
	private Integer sifraKomitenta;
	private String maticniBrojKomitenta, oibKomitenta;
	private String nazivKomitenta;
	// klasa naloga
	private Integer sifraKlaseNaloga;
	private String oznakaKlaseNaloga, nazivKlaseNaloga;
	private String oznakaValute, nazivValute;
	private String oznakaValutePokrica, nazivValutePokrica;
	private Integer sifraLikvidatora, sifraModula;
	private String sifraPoslovnice;
	private Integer sifraNalogaStorna;
	// tranzitna polja
	private String terecenjeOpis, odobrenjeOpis;
	// utility mapa za raznu upotrebu
	private Map<String, Object> utilMap = new HashMap<String, Object>();

	// public no argument constructor
	public NalogDevizniCommonDto() {
		super();
	}

	// private contructor from builder
	protected NalogDevizniCommonDto(NalogDevizniCommonDtoBuilder<?> builder) {
		super();
		this.sifra = builder.sifra;
		this.iznos = builder.iznos;
		this.sifraValute = builder.sifraValute;
		this.sifraValutePokrica = builder.sifraValutePokrica;
		this.ibanZaduzenja = builder.ibanZaduzenja;
		this.nazivDebtor = builder.nazivDebtor;
		this.adresaDebtor = builder.adresaDebtor;
		this.drzavaDebtor = builder.drzavaDebtor;
		this.idDebtor = builder.idDebtor;
		this.gradDebtor = builder.gradDebtor;
		this.ultimateDebtorNaziv = builder.ultimateDebtorNaziv;
		this.ultimateDebtorId = builder.ultimateDebtorId;
		this.ibanOdobrenja = builder.ibanOdobrenja;
		this.nazivCreditor = builder.nazivCreditor;
		this.adresaCreditor = builder.adresaCreditor;
		this.gradCreditor = builder.gradCreditor;
		this.idcreditor = builder.idCreditor;
		this.drzavaCreditor = builder.drzavaCreditor;
		this.ultimateCreditorNaziv = builder.ultimateCreditorNaziv;
		this.ultimateCreditorId = builder.ultimateCreditorId;
		this.nacinIzvrsenja = builder.nacinIzvrsenja;
		this.smjer = builder.smjer;
		this.referenca = builder.referenca;
		this.sifraKnjiznogNaloga = builder.sifraKnjiznogNaloga;
		this.knjizniNalog = builder.knjizniNalog;
		this.sifraKlaseNaloga = builder.sifraKlaseNaloga;
		this.sifraPrethodnogNaloga = builder.sifraPrethodnogNaloga;
		this.swiftNalogodavateljaKorisnika = builder.swiftNalogodavateljaKorisnika;
		this.swiftNalogodavateljaKorisnikaNaziv = builder.swiftNalogodavateljaKorisnikaNaziv;
		this.trosakInoBanke = builder.trosakInoBanke;
		this.sifraValuteZaduzenja = builder.sifraValuteZaduzenja; 
		this.oznakaValuteZaduzenja = builder.oznakaValuteZaduzenja; 
		this.nazivValuteZaduzenja = builder.nazivValuteZaduzenja; 
		this.sifraValuteOdobrenja = builder.sifraValuteOdobrenja; 
		this.oznakaValuteOdobrenja = builder.oznakaValuteOdobrenja; 
		this.nazivValuteOdobrenja = builder.nazivValuteOdobrenja;
		this.datumValute = builder.datumValute;
		this.datumKnjizenja = builder.datumKnjizenja;
		this.oznakaValute = builder.oznakaValute;
	}

	public NalogDevizniCommonDto(boolean isFirstCall) {
		super(isFirstCall);
	}

	// builder
	public static abstract class NalogDevizniCommonDtoBuilder<T extends NalogDevizniCommonDtoBuilder<T>> {
		// mandate fields
		private String sifraValute, ibanZaduzenja, ibanOdobrenja, nacinIzvrsenja, smjer;
		private BigDecimal iznos;
		private Integer sifraKnjiznogNaloga;

		// optional fields + initialization
		private Integer sifra, sifraKlaseNaloga, sifraPrethodnogNaloga;
		private String sifraValutePokrica, referenca, nazivDebtor, adresaDebtor, gradDebtor, drzavaDebtor, idDebtor, ultimateDebtorNaziv, ultimateDebtorId, nazivCreditor, adresaCreditor, gradCreditor, drzavaCreditor, idCreditor, ultimateCreditorNaziv, ultimateCreditorId, swiftNalogodavateljaKorisnika, trosakInoBanke, sifraValuteZaduzenja, oznakaValuteZaduzenja, nazivValuteZaduzenja, sifraValuteOdobrenja, oznakaValuteOdobrenja, nazivValuteOdobrenja, swiftNalogodavateljaKorisnikaNaziv, oznakaValute;
		private Date datumValute, datumKnjizenja;
		private NalogKnjizniDto knjizniNalog;

		// mandate constructor
		public NalogDevizniCommonDtoBuilder(String sifraValute, String ibanZaduzenja, String ibanOdobrenja, String nacinIzvrsenja, String smjer, BigDecimal iznos, Integer sifraKnjiznogNaloga) {
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

		public T referenca(String referenca) {
			this.referenca = referenca;
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

		public T sifraValuteZaduzenja(String sifraValuteZaduzenja) {
			this.sifraValuteZaduzenja = sifraValuteZaduzenja;
			return self();
		}
		
		public T oznakaValuteZaduzenja(String oznakaValuteZaduzenja) {
			this.oznakaValuteZaduzenja = oznakaValuteZaduzenja;
			return self();
		}
		
		public T nazivValuteZaduzenja(String nazivValuteZaduzenja) {
			this.nazivValuteZaduzenja = nazivValuteZaduzenja;
			return self();
		}
		
		public T sifraValuteOdobrenja(String sifraValuteOdobrenja) {
			this.sifraValuteOdobrenja = sifraValuteOdobrenja;
			return self();
		}
		
		public T oznakaValuteOdobrenja(String oznakaValuteOdobrenja) {
			this.oznakaValuteOdobrenja = oznakaValuteOdobrenja;
			return self();
		}
		
		public T nazivValuteOdobrenja(String nazivValuteOdobrenja) {
			this.nazivValuteOdobrenja = nazivValuteOdobrenja;
			return self();
		}
		
		public T swiftNalogodavateljaKorisnikaNaziv(String swiftNalogodavateljaKorisnikaNaziv) {
			this.swiftNalogodavateljaKorisnikaNaziv = swiftNalogodavateljaKorisnikaNaziv;
			return self();
		}

		public T datumValute(Date datumValute) {
			this.datumValute = datumValute;
			return self();
		}
		
		public T datumKnjizenja(Date datumKnjizenja) {
			this.datumKnjizenja = datumKnjizenja;
			return self();
		}

		public T oznakaValute(String oznakaValute) {
			this.oznakaValute = oznakaValute;
			return self();
		}
		
		// build method
		public NalogDevizniCommonDto build() {
			return new NalogDevizniCommonDto(this);
		}
	}

	// implementation of abstract class that enables ingeritence
	private static class NalogDevizniCommonDtoImplementationBuilder extends NalogDevizniCommonDtoBuilder<NalogDevizniCommonDtoImplementationBuilder> {

		// constructor
		public NalogDevizniCommonDtoImplementationBuilder(String sifraValute, String ibanZaduzenja, String ibanOdobrenja, String nacinIzvrsenja, String smjer, BigDecimal iznos, Integer sifraKnjiznogNaloga) {
			super(sifraValute, ibanZaduzenja, ibanOdobrenja, nacinIzvrsenja, smjer, iznos, sifraKnjiznogNaloga);
		}

		@Override
		protected NalogDevizniCommonDtoImplementationBuilder self() {
			return this;
		}

	}

	// returns implementation of builder
	public static NalogDevizniCommonDtoBuilder<?> builder(String sifraValute, String ibanZaduzenja, String ibanOdobrenja, String nacinIzvrsenja, String smjer, BigDecimal iznos, Integer sifraKnjiznogNaloga) {
		return new NalogDevizniCommonDtoImplementationBuilder(sifraValute, ibanZaduzenja, ibanOdobrenja, nacinIzvrsenja, smjer, iznos, sifraKnjiznogNaloga);
	}

	// getters & setters
	@IFilter(entityField = "nalogDevizniCommon.sifra_nal")
	@Override
	public Integer getSifra() {
		return super.getSifra();
	}

	@IFilter(entityField = "nalogDevizniCommon.iznos_nal")
	public BigDecimal getIznos() {
		return iznos;
	}

	public void setIznos(BigDecimal iznos) {
		this.iznos = iznos;
	}

	@IFilter(entityField = "nalogDevizniCommon.valut_nal")
	public String getSifraValute() {
		return sifraValute;
	}

	public void setSifraValute(String sifraValute) {
		this.sifraValute = sifraValute;
	}

	@IFilter(entityField = "nalogDevizniCommon.valpk_nal")
	public String getSifraValutePokrica() {
		return sifraValutePokrica;
	}

	public void setSifraValutePokrica(String sifraValutePokrica) {
		this.sifraValutePokrica = sifraValutePokrica;
	}

	@IFilter(entityField = "nalogDevizniCommon.ibanz_nal")
	public String getIbanZaduzenja() {
		return ibanZaduzenja;
	}

	public void setIbanZaduzenja(String ibanZaduzenja) {
		this.ibanZaduzenja = ibanZaduzenja;
	}

	@IFilter(entityField = "nalogDevizniCommon.ibano_nal")
	public String getIbanOdobrenja() {
		return ibanOdobrenja;
	}

	public void setIbanOdobrenja(String ibanOdobrenja) {
		this.ibanOdobrenja = ibanOdobrenja;
	}

	@IFilter(entityField = "nalogDevizniCommon.naciz_nal")
	public String getNacinIzvrsenja() {
		return nacinIzvrsenja;
	}

	public void setNacinIzvrsenja(String nacinIzvrsenja) {
		this.nacinIzvrsenja = nacinIzvrsenja;
	}

	@IFilter(entityField = "nalogDevizniCommon.smjer_nal")
	public String getSmjer() {
		return smjer;
	}

	public void setSmjer(String smjer) {
		this.smjer = smjer;
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

	@IFilter(entityField = "nalog.statu_nal")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@IFilter(entityField = "nalog.svrha_nal")
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getSifraValuteZaduzenja() {
		return sifraValuteZaduzenja;
	}

	public void setSifraValuteZaduzenja(String sifraValuteZaduzenja) {
		this.sifraValuteZaduzenja = sifraValuteZaduzenja;
	}

	public String getSifraValuteOdobrenja() {
		return sifraValuteOdobrenja;
	}

	public void setSifraValuteOdobrenja(String sifraValuteOdobrenja) {
		this.sifraValuteOdobrenja = sifraValuteOdobrenja;
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

	@IFilter(entityField = "klasaNaloga.sifra_kna")
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

	@IFilter(entityField = "nalogDevizniCommon.preth_nal")
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

	public Integer getSifraLikvidatora() {
		return sifraLikvidatora;
	}

	public void setSifraLikvidatora(Integer sifraLikvidatora) {
		this.sifraLikvidatora = sifraLikvidatora;
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
		rez = rez.concat(getAdresaDebtor() != null ? getAdresaDebtor().concat(GenericBassxConstants.Core.NEW_LINE) : "");
		rez = rez.concat(getGradDebtor() != null ? getGradDebtor().concat(GenericBassxConstants.Core.NEW_LINE) : "");
		rez = rez.concat(getDrzavaDebtor() != null ? getDrzavaDebtor().concat(GenericBassxConstants.Core.NEW_LINE) : "");
		setTerecenjeOpis(rez);
	}

	public void napuniOdobrenje() {
		String rez = getNazivCreditor() != null ? getNazivCreditor() : "";
		rez = rez.concat(GenericBassxConstants.Core.NEW_LINE);
		rez = rez.concat(getAdresaCreditor() != null ? getAdresaCreditor().concat(GenericBassxConstants.Core.NEW_LINE) : "");
		rez = rez.concat(getGradCreditor() != null ? getGradCreditor().concat(GenericBassxConstants.Core.NEW_LINE) : "");
		rez = rez.concat(getDrzavaCreditor() != null ? getDrzavaCreditor().concat(GenericBassxConstants.Core.NEW_LINE) : "");
		setOdobrenjeOpis(rez);
	}
	
	public void rastepiTerecenje() {
		if (StringUtils.isNotBlank(terecenjeOpis)) {
			for (String s : terecenjeOpis.split(GenericBassxConstants.Core.NEW_LINE)) {
				if (StringUtils.isBlank(nazivDebtor)) {
					setNazivDebtor(s);
				} else if (StringUtils.isBlank(adresaDebtor)) {
					setAdresaDebtor(s);
				} else if (StringUtils.isBlank(gradDebtor)) {
					setGradDebtor(s);
				} else if (StringUtils.isBlank(drzavaDebtor)) {
					setDrzavaDebtor(s);
				}
			}
		}
	}
	
	public void rastepiOdobrenje() {
		if (StringUtils.isNotBlank(odobrenjeOpis)) {
			for (String s : odobrenjeOpis.split(GenericBassxConstants.Core.NEW_LINE)) {
				if (StringUtils.isBlank(nazivCreditor)) {
					setNazivCreditor(s);
				} else if (StringUtils.isBlank(adresaCreditor)) {
					setAdresaCreditor(s);
				} else if (StringUtils.isBlank(gradCreditor)) {
					setGradCreditor(s);
				} else if (StringUtils.isBlank(drzavaCreditor)) {
					setDrzavaCreditor(s);
				}
			}
		}
	}

	public Integer getSifraModula() {
		return sifraModula;
	}

	public void setSifraModula(Integer sifraModula) {
		this.sifraModula = sifraModula;
	}

	public String getSifraPoslovnice() {
		return sifraPoslovnice;
	}

	public void setSifraPoslovnice(String sifraPoslovnice) {
		this.sifraPoslovnice = sifraPoslovnice;
	}

	public Map<String, Object> getUtilMap() {
		return utilMap;
	}

	public void setUtilMap(Map<String, Object> utilMap) {
		this.utilMap = utilMap;
	}

	public Integer getSifraNalogaPrethodnika() {
		return sifraNalogaPrethodnika;
	}

	public void setSifraNalogaPrethodnika(Integer sifraNalogaPrethodnika) {
		this.sifraNalogaPrethodnika = sifraNalogaPrethodnika;
	}

	public void addExceptions(Exception e) {
		@SuppressWarnings("unchecked")
		List<Exception> lista = (List<Exception>) this.getUtilMap().get(GenericBassxConstants.PlatniPromet.UTILMAP_KEY_EXCEPTION);
		if (lista == null) {
			lista = new ArrayList<Exception>();
		}
		lista.add(e);
		this.getUtilMap().put(GenericBassxConstants.PlatniPromet.UTILMAP_KEY_EXCEPTION, lista);
	}

	@IFilter(entityField = "komitent.oib_kom")
	public String getOibKomitenta() {
		return oibKomitenta;
	}

	public void setOibKomitenta(String oibKomitenta) {
		this.oibKomitenta = oibKomitenta;
	}

	@IFilter(entityField = "nalogDevizniCommon.nazad_nal")
	public String getNazivDebtor() {
		return nazivDebtor;
	}

	public void setNazivDebtor(String nazivDebtor) {
		this.nazivDebtor = nazivDebtor;
	}

	@IFilter(entityField = "nalogDevizniCommon.adzad_nal")
	public String getAdresaDebtor() {
		return adresaDebtor;
	}

	public void setAdresaDebtor(String adresaDebtor) {
		this.adresaDebtor = adresaDebtor;
	}

	@IFilter(entityField = "nalogDevizniCommon.grzad_nal")
	public String getGradDebtor() {
		return gradDebtor;
	}

	public void setGradDebtor(String gradDebtor) {
		this.gradDebtor = gradDebtor;
	}

	@IFilter(entityField = "nalogDevizniCommon.idzad_nal")
	public String getIdDebtor() { return idDebtor; }

	public void setIdDebtor(String idDebtor) { this.idDebtor = idDebtor; }

	@IFilter(entityField = "nalogDevizniCommon.drzad_nal")
	public String getDrzavaDebtor() {
		return drzavaDebtor;
	}

	public void setDrzavaDebtor(String drzavaDebtor) {
		this.drzavaDebtor = drzavaDebtor;
	}

	@IFilter(entityField = "nalogDevizniCommon.ulnzd_nal")
	public String getUltimateDebtorNaziv() {
		return ultimateDebtorNaziv;
	}

	public void setUltimateDebtorNaziv(String ultimateDebtorNaziv) {
		this.ultimateDebtorNaziv = ultimateDebtorNaziv;
	}

	@IFilter(entityField = "nalogDevizniCommon.ulizd_nal")
	public String getUltimateDebtorId() {
		return ultimateDebtorId;
	}

	public void setUltimateDebtorId(String ultimateDebtorId) {
		this.ultimateDebtorId = ultimateDebtorId;
	}

	@IFilter(entityField = "nalogDevizniCommon.naodo_nal")
	public String getNazivCreditor() {
		return nazivCreditor;
	}

	public void setNazivCreditor(String nazivCreditor) {
		this.nazivCreditor = nazivCreditor;
	}

	@IFilter(entityField = "nalogDevizniCommon.adodo_nal")
	public String getAdresaCreditor() {
		return adresaCreditor;
	}

	public void setAdresaCreditor(String adresaCreditor) {
		this.adresaCreditor = adresaCreditor;
	}

	@IFilter(entityField = "nalogDevizniCommon.grodo_nal")
	public String getGradCreditor() {
		return gradCreditor;
	}

	public void setGradCreditor(String gradCreditor) {
		this.gradCreditor = gradCreditor;
	}

	@IFilter(entityField = "nalogDevizniCommon.idodo_nal")
	public String getIdcreditor() {return idcreditor; }

	public void setIdcreditor(String idcreditor) { this.idcreditor = idcreditor; }

	@IFilter(entityField = "nalogDevizniCommon.drodo_nal")
	public String getDrzavaCreditor() {
		return drzavaCreditor;
	}

	public void setDrzavaCreditor(String drzavaCreditor) {
		this.drzavaCreditor = drzavaCreditor;
	}

	@IFilter(entityField = "nalogDevizniCommon.ulnod_nal")
	public String getUltimateCreditorNaziv() {
		return ultimateCreditorNaziv;
	}

	public void setUltimateCreditorNaziv(String ultimateCreditorNaziv) {
		this.ultimateCreditorNaziv = ultimateCreditorNaziv;
	}

	@IFilter(entityField = "nalogDevizniCommon.uliod_nal")
	public String getUltimateCreditorId() {
		return ultimateCreditorId;
	}

	public void setUltimateCreditorId(String ultimateCreditorId) {
		this.ultimateCreditorId = ultimateCreditorId;
	}

	@IFilter(entityField = "nalogDevizniCommon.refer_nal")
	public String getReferenca() {
		return referenca;
	}

	public void setReferenca(String referenca) {
		this.referenca = referenca;
	}

	public String getTerecenjeOpis() {
		if (StringUtils.isBlank(terecenjeOpis)) napuniTerecenje();
		return terecenjeOpis;
	}

	public void setTerecenjeOpis(String terecenjeOpis) {
		this.terecenjeOpis = terecenjeOpis;
	}

	public String getOdobrenjeOpis() {
		if (StringUtils.isBlank(odobrenjeOpis)) napuniOdobrenje();
		return odobrenjeOpis;
	}

	public void setOdobrenjeOpis(String odobrenjeOpis) {
		this.odobrenjeOpis = odobrenjeOpis;
	}

	@IFilter(entityField = "nalogDevizniCommon.bicko_nal")
	public String getSwiftNalogodavateljaKorisnika() {
		return swiftNalogodavateljaKorisnika;
	}

	public void setSwiftNalogodavateljaKorisnika(String swiftNalogodavateljaKorisnika) {
		this.swiftNalogodavateljaKorisnika = swiftNalogodavateljaKorisnika;
	}

	public String getSwiftNalogodavateljaKorisnikaNaziv() {
		return swiftNalogodavateljaKorisnikaNaziv;
	}

	public void setSwiftNalogodavateljaKorisnikaNaziv(String swiftNalogodavateljaKorisnikaNaziv) {
		this.swiftNalogodavateljaKorisnikaNaziv = swiftNalogodavateljaKorisnikaNaziv;
	}

	@IFilter(entityField = "nalogDevizniCommon.tropc_nal")
	public String getTrosakInoBanke() {
		return trosakInoBanke;
	}

	public void setTrosakInoBanke(String trosakInoBanke) {
		this.trosakInoBanke = trosakInoBanke;
	}

	public Integer getSifraKomitenta() {
		return sifraKomitenta;
	}

	public void setSifraKomitenta(Integer sifraKomitenta) {
		this.sifraKomitenta = sifraKomitenta;
	}

	public String getNazivValute() {
		return nazivValute;
	}

	public void setNazivValute(String nazivValute) {
		this.nazivValute = nazivValute;
	}

	public String getMedij() {
		return medij;
	}

	public void setMedij(String medij) {
		this.medij = medij;
	}

	public String getOznakaValutePokrica() {
		return oznakaValutePokrica;
	}

	public void setOznakaValutePokrica(String oznakaValutePokrica) {
		this.oznakaValutePokrica = oznakaValutePokrica;
	}

	public String getNazivValutePokrica() {
		return nazivValutePokrica;
	}

	public void setNazivValutePokrica(String nazivValutePokrica) {
		this.nazivValutePokrica = nazivValutePokrica;
	}

	public String getBrojPartijaZaduzenja() {
		return brojPartijaZaduzenja;
	}

	public void setBrojPartijaZaduzenja(String brojPartijaZaduzenja) {
		this.brojPartijaZaduzenja = brojPartijaZaduzenja;
	}

	public String getBrojPartijaOdobrenja() {
		return brojPartijaOdobrenja;
	}

	public void setBrojPartijaOdobrenja(String brojPartijaOdobrenja) {
		this.brojPartijaOdobrenja = brojPartijaOdobrenja;
	}

}
