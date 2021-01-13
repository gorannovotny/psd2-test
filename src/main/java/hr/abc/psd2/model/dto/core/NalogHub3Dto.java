package hr.abc.psd2.model.dto.core;

import hr.abc.psd2.util.Bassx2MinConstants;
import hr.abc.psd2.util.IbanFunctions;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Entitet koji vrši CRUD operacije prema bazi podataka, a vezan je za novi HUB 3 obrazac.
 * 
 * @author Mata
 *
 */
public class NalogHub3Dto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	// constants
	public static final String PARAM_IZNOS_NAKNADE = "iznosNaknade";
	public static final String PARAM_VALUTA_NAKNADE = "valutaNaknade";
	public static final String PARAM_TIP_TRANSAKCIJE = "tipTransakcije";
	public static final String PARAM_ENTITY_MANAGER = "entityManager";
	public static final String PARAM_NALOG_STORNA = "nalogStorna";
	public static final String PARAM_PARTIJA_POKRICA_AKREDITIVA = "partijaPokricaAkreditiva";
	// bitno je provjeriti uz slaganje grupe računa i duljinu računa (mora biti duljina 10) - kako partije kredita ne bi ulazile u izvješće (duljina 11)
	public static final Integer DULJINA_TRANSAKCIJSKOG_RACUNA = 10;
	public static List<String> RACUNI_ZA_STATISTIKU = null, RACUNI_ZA_STATISTIKU_BEZ_RACUNA_BANKE = null, RACUNI_BANKE_ZA_STATISTIKU = null, RACUNI_OROCENJA_ZA_STATISTIKU = null;
	static {
		RACUNI_ZA_STATISTIKU = Arrays.asList(new String[] { "10","11","13","14","15","17","18","19","31","32","33","35","36","51","55","73" });
		RACUNI_ZA_STATISTIKU_BEZ_RACUNA_BANKE = Arrays.asList(new String[] { "11","13","14","15","17","18","19","31","32","33","35","36","51","55","73" });
		RACUNI_OROCENJA_ZA_STATISTIKU = Arrays.asList(new String[] { "75","85" });
		RACUNI_BANKE_ZA_STATISTIKU = Arrays.asList(new String[] { Bassx2MinConstants.CoreHub.RACUN_BANKE_NKS_FORMAT, Bassx2MinConstants.CoreHub.RACUN_BANKE_OSNIVACKI_POLOG });
	}
	// konstante za nalogodavatelja
	public static final Integer NLGDVHUB_VLASNIK_OPUNOMOCENIK = 1;
	public static final Integer NLGDVHUB_NEVLASNIK_NEOPUNOMOCENIK = 2;
	public static final Integer NLGDVHUB_BANKA = 3;
	public static final Integer NLGDVHUB_NEPOZNAT_OSTALO = 4;
	public static final Integer NLGDVHUB_OVRHA = 5;
	// konstante za izvor sredstva 
	public static final Integer IZVORHUB_DRUGI_PLATNI_RACUN = 2;
	
	//za model plaćanja na prva 2 mjesta dolazi HR
	public static final String MODEL_PREFIX = "HR";
	
	// fields
    private Integer sifra, sifraKnjiznogNaloga, tipNaloga, nalogodavatelj, izvorSredstava;
    private String sifraValute, ibanZaduzenja, ibanOdobrenja, modelZaduzenja, modelOdobrenja, pozivNaBrojZaduzenja, pozivNaBrojOdobrenja, opisTeret, opisOdobrenje, svrha, sifraNamjene, bicBankePrimatelja, opisBankePrimatelja, osobaPrimatelj, troskovnaOpcija, sifraValutePokrica, partijaZaduzenja, partijaOdobrenja, ziroZaduzenjaTrans, ziroOdobrenjaTrans;
    private BigDecimal iznos, tecaj;
    private Date datumIzvrsenja;
	
	// constructors
	public NalogHub3Dto() {
		super();
	}
	
	public NalogHub3Dto(Integer sifra, Integer sifraKnjiznogNaloga, Integer tipNaloga, Integer nalogodavatelj, Integer izvorSredstava, String sifraValute, String ibanZaduzenja, String ibanOdobrenja, String modelZaduzenja, String modelOdobrenja, String pozivNaBrojZaduzenja, String pozivNaBrojOdobrenja, String opisTeret, String opisOdobrenje, String svrha, String sifraNamjene, String bicBankePrimatelja, String opisBankePrimatelja, String osobaPrimatelj, String troskovnaOpcija, String sifraValutePokrica, String partijaZaduzenja, String partijaOdobrenja, String ziroZaduzenjaTrans, String ziroOdobrenjaTrans, BigDecimal iznos, BigDecimal tecaj, Date datumIzvrsenja) {
		super();
		this.sifra = sifra;
		this.sifraKnjiznogNaloga = sifraKnjiznogNaloga;
		this.tipNaloga = tipNaloga;
		this.nalogodavatelj = nalogodavatelj;
		this.izvorSredstava = izvorSredstava;
		this.sifraValute = sifraValute;
		this.ibanZaduzenja = ibanZaduzenja;
		this.ibanOdobrenja = ibanOdobrenja;
		this.modelZaduzenja = modelZaduzenja;
		this.modelOdobrenja = modelOdobrenja;
		this.pozivNaBrojZaduzenja = pozivNaBrojZaduzenja;
		this.pozivNaBrojOdobrenja = pozivNaBrojOdobrenja;
		this.opisTeret = opisTeret;
		this.opisOdobrenje = opisOdobrenje;
		this.svrha = svrha;
		this.sifraNamjene = sifraNamjene;
		this.bicBankePrimatelja = bicBankePrimatelja;
		this.opisBankePrimatelja = opisBankePrimatelja;
		this.osobaPrimatelj = osobaPrimatelj;
		this.troskovnaOpcija = troskovnaOpcija;
		this.sifraValutePokrica = sifraValutePokrica;
		this.partijaZaduzenja = partijaZaduzenja;
		this.partijaOdobrenja = partijaOdobrenja;
		this.ziroZaduzenjaTrans = ziroZaduzenjaTrans;
		this.ziroOdobrenjaTrans = ziroOdobrenjaTrans;
		this.iznos = iznos;
		this.tecaj = tecaj;
		this.datumIzvrsenja = datumIzvrsenja;
	}

	public NalogHub3Dto(Integer sifra_hub, Integer nalog_hub, Integer tipna_hub, Integer nlgdv_hub, Integer izvor_hub, String sifva_hub, String ibanz_hub, String ibano_hub, String modlz_hub, String modlo_hub, String pobrz_hub, String pobro_hub, String teret_hub, String odobr_hub, String svrha_hub, String sinam_hub, String bicko_hub, String banka_hub, String osoba_hub, String trban_hub, String pokrc_hub, String pazad_hub, String paodo_hub, BigDecimal iznos_hub, BigDecimal tecaj_hub, Date datiz_hub) {
		this.sifra = sifra_hub;
		this.sifraKnjiznogNaloga = nalog_hub;
		this.tipNaloga = tipna_hub;
		this.nalogodavatelj = nlgdv_hub;
		this.izvorSredstava = izvor_hub;
		this.sifraValute = sifva_hub;
		this.ibanZaduzenja = ibanz_hub;
		this.ibanOdobrenja = ibano_hub;
		this.modelZaduzenja = modlz_hub;
		this.modelOdobrenja = modlo_hub;
		this.pozivNaBrojZaduzenja = pobrz_hub;
		this.pozivNaBrojOdobrenja = pobro_hub;
		this.opisTeret = teret_hub;
		this.opisOdobrenje = odobr_hub;
		this.svrha = svrha_hub;
		this.sifraNamjene = sinam_hub;
		this.bicBankePrimatelja = bicko_hub;
		this.opisBankePrimatelja = banka_hub;
		this.osobaPrimatelj = osoba_hub;
		this.troskovnaOpcija = trban_hub;
		this.sifraValutePokrica = pokrc_hub;
		this.partijaZaduzenja = pazad_hub;
		this.partijaOdobrenja = paodo_hub;
		this.iznos = iznos_hub;
		this.tecaj = tecaj_hub;
		this.datumIzvrsenja = datiz_hub;
	}

	// static factory
	public static NalogHub3Dto getInstance(NalogPlatniDto platniNalog) {
		NalogHub3Dto result = null;
		if (platniNalog != null) {
			// formiranje opisa terećenja/odobrenja
			platniNalog.napuniTerecenje();
			platniNalog.napuniOdobrenje();
			// dohvt partija iz IBAN-a
			Map<String, String> partijaZaduzenja = IbanFunctions.resolveVbdiPartijaFromText(platniNalog.getIbanZaduzenja());
			Map<String, String> partijaOdobrenja = IbanFunctions.resolveVbdiPartijaFromText(platniNalog.getIbanOdobrenja());
			// inicijalizacija HUB 3 kolekcije
			result = new NalogHub3Dto(null, platniNalog.getKnjizniNalog() != null ? platniNalog.getKnjizniNalog().getSifra() : null,
					platniNalog.getKnjizniNalog() != null ? platniNalog.getKnjizniNalog().getSifraTipaNaloga() : null, platniNalog.getNalogodavatelj(), platniNalog.getIzvorSredstava(),
					platniNalog.getSifraValute(), platniNalog.getIbanZaduzenja(), platniNalog.getIbanOdobrenja(), platniNalog.getModelZaduzenja(), platniNalog.getModelOdobrenja(),
					platniNalog.getPozivNaBrojZaduzenja(), platniNalog.getPozivNaBrojOdobrenja(), platniNalog.getTerecenjeOpis(), platniNalog.getOdobrenjeOpis(),
					platniNalog.getKnjizniNalog().getSvrha(), platniNalog.getSifraNamjene(), platniNalog.getSwiftNalogodavateljaKorisnika(), platniNalog.getSwiftNalogodavateljaKorisnikaNaziv(),
					platniNalog.getOsobaPrimatelj(), platniNalog.getTrosakInoBanke(), platniNalog.getSifraValutePokrica(), null, null,
					partijaZaduzenja != null ? partijaZaduzenja.get(IbanFunctions.PARTIJA_ID) : null, partijaOdobrenja != null ? partijaOdobrenja.get(IbanFunctions.PARTIJA_ID) : null,
					platniNalog.getIznos(), platniNalog.getTecajZaduzenja(), platniNalog.getKnjizniNalog() != null ? platniNalog.getKnjizniNalog().getDatumValute() : null);
			//dodavanje prefixa za model plaćanja ako postoji 
			if(result.getModelZaduzenja() != null && result.getModelZaduzenja().trim().length() == 2){
				result.setModelZaduzenja(NalogHub3Dto.MODEL_PREFIX.concat(result.getModelZaduzenja()));
			}
			if(result.getModelOdobrenja() != null && result.getModelOdobrenja().trim().length() == 2){
				result.setModelOdobrenja(NalogHub3Dto.MODEL_PREFIX.concat(result.getModelOdobrenja()));
			}
		}
		return result;
	}
	
	// metoda puni na hub 3 entitetu koji već postoji u bazi podataka nova polja 
	public void rewriteFields(NalogHub3Dto hub3) {
		if (hub3 != null) {
			setSifraValute(hub3.getSifraValute());
			setIznos(hub3.getIznos());
			setIbanZaduzenja(hub3.getIbanZaduzenja());
			setIbanOdobrenja(hub3.getIbanOdobrenja());
			setModelZaduzenja(hub3.getModelZaduzenja());
			setModelOdobrenja(hub3.getModelOdobrenja());
			setPozivNaBrojZaduzenja(hub3.getPozivNaBrojZaduzenja());
			setPozivNaBrojOdobrenja(hub3.getPozivNaBrojOdobrenja());
			setOpisTeret(hub3.getOpisTeret());
			setOpisOdobrenje(hub3.getOpisOdobrenje());
			setSvrha(hub3.getSvrha());
			setSifraNamjene(hub3.getSifraNamjene());
			setDatumIzvrsenja(hub3.getDatumIzvrsenja());
			setBicBankePrimatelja(hub3.getBicBankePrimatelja());
			setOpisBankePrimatelja(hub3.getOpisBankePrimatelja());
			setOsobaPrimatelj(hub3.getOsobaPrimatelj());
			setTroskovnaOpcija(hub3.getTroskovnaOpcija());
			setSifraValutePokrica(hub3.getSifraValutePokrica());
			setSifraKnjiznogNaloga(hub3.getSifraKnjiznogNaloga());
			setTipNaloga(hub3.getTipNaloga());
			setZiroZaduzenjaTrans(hub3.getZiroZaduzenjaTrans());
			setZiroOdobrenjaTrans(hub3.getZiroOdobrenjaTrans());
			setTecaj(hub3.getTecaj());
			setNalogodavatelj(hub3.getNalogodavatelj());
		}
	}
	
	// utility functions
	public static String resolveIbanBanke() {
		return Bassx2MinConstants.CoreHub.IBAN_BANKE;
	}
	
	public static String resolveOpisBanke() {
		return Bassx2MinConstants.CoreHub.NAZIV_BANKE_SWIFT;
	}

	// getters & setters
	public Integer getSifra() {
		return sifra;
	}

	public void setSifra(Integer sifra) {
		this.sifra = sifra;
	}

	public Integer getSifraKnjiznogNaloga() {
		return sifraKnjiznogNaloga;
	}

	public void setSifraKnjiznogNaloga(Integer sifraKnjiznogNaloga) {
		this.sifraKnjiznogNaloga = sifraKnjiznogNaloga;
	}

	public Integer getTipNaloga() {
		return tipNaloga;
	}

	public void setTipNaloga(Integer tipNaloga) {
		this.tipNaloga = tipNaloga;
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

	public String getSifraValute() {
		return sifraValute;
	}

	public void setSifraValute(String sifraValute) {
		this.sifraValute = sifraValute;
	}

	public String getIbanZaduzenja() {
		return ibanZaduzenja;
	}

	public void setIbanZaduzenja(String ibanZaduzenja) {
		this.ibanZaduzenja = ibanZaduzenja;
	}

	public String getIbanOdobrenja() {
		return ibanOdobrenja;
	}

	public void setIbanOdobrenja(String ibanOdobrenja) {
		this.ibanOdobrenja = ibanOdobrenja;
	}

	public String getModelZaduzenja() {
		return modelZaduzenja;
	}

	public void setModelZaduzenja(String modelZaduzenja) {
		this.modelZaduzenja = modelZaduzenja;
	}

	public String getModelOdobrenja() {
		return modelOdobrenja;
	}

	public void setModelOdobrenja(String modelOdobrenja) {
		this.modelOdobrenja = modelOdobrenja;
	}

	public String getPozivNaBrojZaduzenja() {
		return pozivNaBrojZaduzenja;
	}

	public void setPozivNaBrojZaduzenja(String pozivNaBrojZaduzenja) {
		this.pozivNaBrojZaduzenja = pozivNaBrojZaduzenja;
	}

	public String getPozivNaBrojOdobrenja() {
		return pozivNaBrojOdobrenja;
	}

	public void setPozivNaBrojOdobrenja(String pozivNaBrojOdobrenja) {
		this.pozivNaBrojOdobrenja = pozivNaBrojOdobrenja;
	}

	public String getOpisTeret() {
		return opisTeret;
	}

	public void setOpisTeret(String opisTeret) {
		this.opisTeret = opisTeret;
	}

	public String getOpisOdobrenje() {
		return opisOdobrenje;
	}

	public void setOpisOdobrenje(String opisOdobrenje) {
		this.opisOdobrenje = opisOdobrenje;
	}

	public String getSvrha() {
		return svrha;
	}

	public void setSvrha(String svrha) {
		this.svrha = svrha;
	}

	public String getSifraNamjene() {
		return sifraNamjene;
	}

	public void setSifraNamjene(String sifraNamjene) {
		this.sifraNamjene = sifraNamjene;
	}

	public String getBicBankePrimatelja() {
		return bicBankePrimatelja;
	}

	public void setBicBankePrimatelja(String bicBankePrimatelja) {
		this.bicBankePrimatelja = bicBankePrimatelja;
	}

	public String getOpisBankePrimatelja() {
		return opisBankePrimatelja;
	}

	public void setOpisBankePrimatelja(String opisBankePrimatelja) {
		this.opisBankePrimatelja = opisBankePrimatelja;
	}

	public String getOsobaPrimatelj() {
		return osobaPrimatelj;
	}

	public void setOsobaPrimatelj(String osobaPrimatelj) {
		this.osobaPrimatelj = osobaPrimatelj;
	}

	public String getTroskovnaOpcija() {
		return troskovnaOpcija;
	}

	public void setTroskovnaOpcija(String troskovnaOpcija) {
		this.troskovnaOpcija = troskovnaOpcija;
	}

	public String getSifraValutePokrica() {
		return sifraValutePokrica;
	}

	public void setSifraValutePokrica(String sifraValutePokrica) {
		this.sifraValutePokrica = sifraValutePokrica;
	}

	public String getPartijaZaduzenja() {
		return partijaZaduzenja;
	}

	public void setPartijaZaduzenja(String partijaZaduzenja) {
		this.partijaZaduzenja = partijaZaduzenja;
	}

	public String getPartijaOdobrenja() {
		return partijaOdobrenja;
	}

	public void setPartijaOdobrenja(String partijaOdobrenja) {
		this.partijaOdobrenja = partijaOdobrenja;
	}

	public String getZiroZaduzenjaTrans() {
		return ziroZaduzenjaTrans;
	}

	public void setZiroZaduzenjaTrans(String ziroZaduzenjaTrans) {
		this.ziroZaduzenjaTrans = ziroZaduzenjaTrans;
	}

	public String getZiroOdobrenjaTrans() {
		return ziroOdobrenjaTrans;
	}

	public void setZiroOdobrenjaTrans(String ziroOdobrenjaTrans) {
		this.ziroOdobrenjaTrans = ziroOdobrenjaTrans;
	}

	public BigDecimal getIznos() {
		return iznos;
	}

	public void setIznos(BigDecimal iznos) {
		this.iznos = iznos;
	}

	public BigDecimal getTecaj() {
		return tecaj;
	}

	public void setTecaj(BigDecimal tecaj) {
		this.tecaj = tecaj;
	}

	public Date getDatumIzvrsenja() {
		return datumIzvrsenja;
	}

	public void setDatumIzvrsenja(Date datumIzvrsenja) {
		this.datumIzvrsenja = datumIzvrsenja;
	}

}
