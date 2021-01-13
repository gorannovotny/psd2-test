package hr.abc.psd2.model.dto.core;

import hr.abc.psd2.annotation.IFilter;
import hr.abc.psd2.model.dto.GenericDto;
import hr.abc.psd2.util.GenericBassxConstants;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * DTO za entitet partije.
 *
 * Koristi se zbog mogućnosti zajedničke logike nad pristupom podacima partije u DAO-u.
 *
 * @author Matija Hlapčić
 */
public class PartijaDto extends GenericDto<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	// table name, alias & fields
	public static final String TABLE_NAME = "partija";
	public static final String TABLE_ALIAS = "Partija";
	public static final String SIFRA_FIELD = "sifra_par";
	public static final String PARTI_FIELD = "parti_par";
	public static final String STATU_FIELD = "statu_par";
	public static final String DATOT_FIELD = "datot_par";
	public static final String DATZT_FIELD = "datzt_par";
	public static final String VLASN_FIELD = "vlasn_par";
	public static final String PJPAR_FIELD = "pjpar_par";
	public static final String TIPPA_FIELD = "tippa_par";
	public static final String PROIZ_FIELD = "proiz_par";
	public static final String NIVOP_FIELD = "nivop_par";
	public static final String INTBL_FIELD = "intbl_par";
	public static final String OZNIZ_FIELD = "ozniz_par";
	public static final String OZNSN_FIELD = "oznsn_par";
	public static final String OZNZR_FIELD = "oznzr_par";
	public static final String NAPOM_FIELD = "napom_par";
	public static final String ALTER_FIELD = "alter_par";
	public static final String VERSI_FIELD = "versi_par";

	// constants
	public static final String STATUS_OTVORENA = "O";
	public static final String STATUS_BLOKIRANA = "B";
	public static final String STATUS_BLOKIRANA_KUNE = "K";
	public static final String STATUS_BLOKIRANA_DEVIZE = "D";
	public static final String STATUS_NAJAVA_ZATVARANJA = "N";
	public static final String STATUS_STECAJ = "L";
	public static final String STATUS_MIGRACIJA = "M";
	public static final String STATUS_SALDACIJA = "S";
	public static final String STATUS_UPISANA = "U";
	public static final String STATUS_ZATVORENA = "Z";
	public static final String BEZ_IZUZECA_SPECIFICNE_NAMJENE = "0";
	public static final String IZUZETA_U_POTPUNOSTI = "1";


	protected String oibVlasnika; // oib vlasnika partije
	protected String maticniBrojVlasnika; // mbr vlasnika partije
	protected String sifraPoslovnice; // šifra poslovnice ili organizacijske jedinice pripadnosti partije
	protected String nazivPoslovnice; // naziv poslovnice ili organizacijske jedinice pripadnosti partije
	protected String nazivTipaPartije;
	// fields
	private String broj; // broj partije (partiPar)
	private String status; // status partije (aktivna, zatvorena, saldirana, ...)
	private Integer nivoPovjerljivosti; // nivo povjerljivosti partije
	private Integer sifraVlasnika; // šifra komitenta vlasnika partije
	private String nazivVlasnika; // naziv komitenta vlasnika partije
	private Integer sifraTipaPartije; // šifra tipa partije
	private Integer sifraProizvoda; // šifra proizvoda
	private Integer sifraPaketa; // šifra paketa
	private Date datumOtvaranja; // datum otvaranja partije
	private Date datumZatvaranja; // datum zatvaranja partije
	private String oznakaInterneBlokade; // Interna blokada (B, K, D)
	private Integer version; // verzioniranje entiteta
	private String alternativnaPartija; // alternativna oznaka partije
	private String napomena; // napomena
	private String oznakaIzuzeca;
	private String oznakaSpecificneNamjene;
	private String oznakaZajednickogRacuna;
	private Integer sifraKlasePartije, sifraFaktura;
	private Integer sifraModula;
	private String nazivProizvoda;
	private String opisStatusa;
	private String opisPodstatusa;
	private String OznakaRizicnosti;
	private BigDecimal PostoRizicnosti;
	private Date DatumRizicnosti;
	private String sifraSektora;
	// pomoćna polja koja se mogu koristiti kod obrada nad partijama koja nemaju djecu
	private Date datumObracuna;
	private Integer frekvencijaObracuna;
	private String sifraValuteObracuna;
	private BigDecimal osnovicaObracuna;
	private Integer namjenaTipaPartije;
	private String alternativniIban, brojKartice;
	private Integer sifraTipaNalogaInicijatorNaknade;
	private Integer sifraTipaNalogaObracunaNaknade;
	private String posaoTip;
	private String vrstakTipaPartije;
	
	// dodatni podatci
	private String sifraDodatnogPodatka, nazivDodatnogPodatka;

	// constructors
	public PartijaDto() {
		super();
	}

	public PartijaDto(boolean isFirstCall) {
		super(isFirstCall);
	}

	public PartijaDto(Integer sifra) {
		super(sifra);
	}

	public PartijaDto(Integer sifra, Integer sifraTipaPartije) {
		super(sifra);
		this.sifraTipaPartije = sifraTipaPartije;
	}

	public PartijaDto(Integer sifra, String broj, String nazivVlasnika, String oibVlasnika) {
		super(sifra);
		this.broj = broj;
		this.nazivVlasnika = nazivVlasnika;
		this.oibVlasnika = oibVlasnika;
	}

	public PartijaDto(Integer sifra, String broj, String status, Integer nivoPovjerljivosti, Integer sifraVlasnika, String sifraPoslovnice, Integer sifraTipaPartije, Integer sifraProizvoda, Date datumOtvaranja, Date datumZatvaranja, String oznakaInterneBlokade, Integer version, String nazivVlasnika, String napomena) {
		super();
		this.sifra = sifra;
		this.broj = broj;
		this.status = status;
		this.nivoPovjerljivosti = nivoPovjerljivosti;
		this.sifraVlasnika = sifraVlasnika;
		this.sifraPoslovnice = sifraPoslovnice;
		this.sifraTipaPartije = sifraTipaPartije;
		this.sifraProizvoda = sifraProizvoda;
		this.datumOtvaranja = datumOtvaranja;
		this.datumZatvaranja = datumZatvaranja;
		this.oznakaInterneBlokade = oznakaInterneBlokade;
		this.version = version;
		this.nazivVlasnika = nazivVlasnika;
		this.napomena = napomena;
	}

	// utility methods
	public boolean isUpisana() {
		Boolean result = false;
		result = getStatus() != null && GenericBassxConstants.Core.PARTIJA_STATUPAR_UPISANA.equals(getStatus());
		return result;
	}

	public boolean isClosed() {
		Boolean result = false;
		result = getStatus() != null && (getStatus().equals(GenericBassxConstants.Core.PARTIJA_STATUPAR_ZATVORENA));
		return result;
	}

	public boolean isLikvidacija() {
		Boolean result = false;
		result = getStatus() != null && GenericBassxConstants.Core.STATUPAR_BLOKIRAN_NAJAVA_ZATVARANJA_STECAJ.equals(getStatus());
		return result;
	}

	public boolean isBlocked() {
		return isBlocked(true, true);
	}

	public boolean isInternoBlocked() {
		return isBlocked(true, false);
	}

	public boolean isExternoBlocked() {
		return isBlocked(false, true);
	}

	public boolean isBlocked(boolean interno, boolean eksetrno) {
		Boolean result = false;
		if (eksetrno && getStatus() != null) {
			result = result || GenericBassxConstants.Core.PARTIJA_STATUPAR_BLOKIRANA.equals(getStatus());
		}
		if (interno && getOznakaInterneBlokade() != null) {
			result = result || GenericBassxConstants.Core.PARTIJA_INTBLPAR_BLOKIRANA.equals(getOznakaInterneBlokade());
		}
		return result;
	}

	public boolean isNajavaZatvaranja() {
		Boolean result = false;
		result = getStatus() != null && GenericBassxConstants.Core.PARTIJA_STATUPAR_NAJAVA_ZATVARANJA.equals(getStatus());
		return result;
	}

	public boolean isOpenActive() {
		Boolean result = false;
		result = getStatus() == null || getStatus().equals(GenericBassxConstants.Core.PARTIJA_STATUPAR_OTVORENA);
		return result;
	}

	public boolean isUnActive() {
		Boolean result = false;
		result = getStatus() != null && getStatus().equals(GenericBassxConstants.Core.PARTIJA_STATUPAR_NEAKTIVNA);
		return result;
	}

	// getters & setters
	@Override
	@IFilter(entityField = TABLE_ALIAS + "." + SIFRA_FIELD)
	public Integer getSifra() {
		return super.getSifra();
	}

	@IFilter(entityField = TABLE_ALIAS + "." + PARTI_FIELD)
	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + STATU_FIELD)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + OZNIZ_FIELD)
	public String getOznakaIzuzeca() { return oznakaIzuzeca; }

	public void setOznakaIzuzeca(String oznakaIzuzeca) { this.oznakaIzuzeca = oznakaIzuzeca; }

	@IFilter(entityField = TABLE_ALIAS + "." + OZNSN_FIELD)
	public String getOznakaSpecificneNamjene() { return oznakaSpecificneNamjene; }

	public void setOznakaSpecificneNamjene(String oznakaSpecificneNamjene) { this.oznakaSpecificneNamjene = oznakaSpecificneNamjene; }

	@IFilter(entityField = TABLE_ALIAS + "." + OZNZR_FIELD)
	public String getOznakaZajednickogRacuna() { return oznakaZajednickogRacuna; }

	public void setOznakaZajednickogRacuna(String oznakaZajednickogRacuna) { this.oznakaZajednickogRacuna = oznakaZajednickogRacuna; }

	@IFilter(entityField = TABLE_ALIAS + "." + NIVOP_FIELD)
	public Integer getNivoPovjerljivosti() {
		return nivoPovjerljivosti;
	}

	public void setNivoPovjerljivosti(Integer nivoPovjerljivosti) {
		this.nivoPovjerljivosti = nivoPovjerljivosti;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + VLASN_FIELD)
	public Integer getSifraVlasnika() {
		return sifraVlasnika;
	}

	public void setSifraVlasnika(Integer sifraVlasnika) {
		this.sifraVlasnika = sifraVlasnika;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + PJPAR_FIELD)
	public String getSifraPoslovnice() {
		return sifraPoslovnice;
	}

	public void setSifraPoslovnice(String sifraPoslovnice) {
		this.sifraPoslovnice = sifraPoslovnice;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + TIPPA_FIELD)
	public Integer getSifraTipaPartije() {
		return sifraTipaPartije;
	}

	public void setSifraTipaPartije(Integer sifraTipaPartije) {
		this.sifraTipaPartije = sifraTipaPartije;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + DATOT_FIELD)
	public Date getDatumOtvaranja() {
		return datumOtvaranja;
	}

	public void setDatumOtvaranja(Date datumOtvaranja) {
		this.datumOtvaranja = datumOtvaranja;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + DATZT_FIELD)
	public Date getDatumZatvaranja() {
		return datumZatvaranja;
	}

	public void setDatumZatvaranja(Date datumZatvaranja) {
		this.datumZatvaranja = datumZatvaranja;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + INTBL_FIELD)
	public String getOznakaInterneBlokade() {
		return oznakaInterneBlokade;
	}

	public void setOznakaInterneBlokade(String oznakaInterneBlokade) {
		this.oznakaInterneBlokade = oznakaInterneBlokade;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@IFilter(entityField = "komitent.naziv_kom")
	public String getNazivVlasnika() {
		return nazivVlasnika;
	}

	public void setNazivVlasnika(String nazivVlasnika) {
		this.nazivVlasnika = nazivVlasnika;
	}

	@IFilter(entityField = "komitent.oib_kom")
	public String getOibVlasnika() {
		return oibVlasnika;
	}

	public void setOibVlasnika(String oibVlasnika) {
		this.oibVlasnika = oibVlasnika;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + PROIZ_FIELD)
	public Integer getSifraProizvoda() {
		return sifraProizvoda;
	}

	public void setSifraProizvoda(Integer sifraProizvoda) {
		this.sifraProizvoda = sifraProizvoda;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + ALTER_FIELD)
	public String getAlternativnaPartija() {
		return alternativnaPartija;
	}

	public void setAlternativnaPartija(String alternativnaPartija) {
		this.alternativnaPartija = alternativnaPartija;
	}

	public String getNazivTipaPartije() {
		return nazivTipaPartije;
	}

	public void setNazivTipaPartije(String nazivTipaPartije) {
		this.nazivTipaPartije = nazivTipaPartije;
	}

	public Integer getSifraKlasePartije() {
		return sifraKlasePartije;
	}

	public void setSifraKlasePartije(Integer sifraKlasePartije) {
		this.sifraKlasePartije = sifraKlasePartije;
	}

	public String getNazivProizvoda() {
		return nazivProizvoda;
	}

	public void setNazivProizvoda(String nazivProizvoda) {
		this.nazivProizvoda = nazivProizvoda;
	}

	public Integer getSifraModula() {
		return sifraModula;
	}

	public void setSifraModula(Integer sifraModula) {
		this.sifraModula = sifraModula;
	}

	/*
	 * Radi se na ovaj način jer nema opisa statusa u tablica nego samo u konstantama - i za otvorenu i zatvorenu partiju se razlikuju. U tablici je označena sa O, registrima sa A. Ako već opis nije postavljen, gleda da li je barem oznaka statusa
	 * postavljena (A, N itd) te prema tome vraća opis.
	 */
	public String getOpisStatusa() {
		String opis = null;
		if (!StringUtils.isBlank(opisStatusa)) {
			opis = opisStatusa;
		} else {
			if (!StringUtils.isBlank(status)) {
				if (StringUtils.equals(status, GenericBassxConstants.Core.PARTIJA_STATUPAR_OTVORENA)) {
					opis = GenericBassxConstants.Core.PARTIJA_STATUPAR_OTVORENA_OPIS;
				}
				if (StringUtils.equals(status, GenericBassxConstants.Core.PARTIJA_STATUPAR_ZATVORENA)) {
					opis = GenericBassxConstants.Core.PARTIJA_STATUPAR_ZATVORENA_OPIS;
				}
				if (StringUtils.equals(status, GenericBassxConstants.Core.PARTIJA_STATUPAR_BLOKIRANA)) {
					opis = GenericBassxConstants.Core.PARTIJA_STATUPAR_BLOKIRANA_OPIS;
				}

				if (StringUtils.equals(status, GenericBassxConstants.Core.PARTIJA_STATUPAR_NEAKTIVNA)) {
					opis = GenericBassxConstants.Core.PARTIJA_STATUPAR_NEAKTIVNA_OPIS;
				}
				if (StringUtils.equals(status, GenericBassxConstants.Core.PARTIJA_STATUPAR_UPISANA)) {
					opis = GenericBassxConstants.Core.PARTIJA_STATUPAR_UPISANA_OPIS;
				}
			}
		}
		return opis;
	}

	public void setOpisStatusa(String opisStatusa) {
		this.opisStatusa = opisStatusa;
	}

	public String getOznakaRizicnosti() {
		return OznakaRizicnosti;
	}

	public void setOznakaRizicnosti(String oznakaRizicnosti) {
		OznakaRizicnosti = oznakaRizicnosti;
	}

	public BigDecimal getPostoRizicnosti() {
		return PostoRizicnosti;
	}

	public void setPostoRizicnosti(BigDecimal postoRizicnosti) {
		PostoRizicnosti = postoRizicnosti;
	}

	public Date getDatumRizicnosti() {
		return DatumRizicnosti;
	}

	public void setDatumRizicnosti(Date datumRizicnosti) {
		DatumRizicnosti = datumRizicnosti;
	}

	public String getSifraSektora() {
		return sifraSektora;
	}

	public void setSifraSektora(String sifraSektora) {
		this.sifraSektora = sifraSektora;
	}

	public Date getDatumObracuna() {
		return datumObracuna;
	}

	public void setDatumObracuna(Date datumObracuna) {
		this.datumObracuna = datumObracuna;
	}

	public Integer getFrekvencijaObracuna() {
		return frekvencijaObracuna;
	}

	public void setFrekvencijaObracuna(Integer frekvencijaObracuna) {
		this.frekvencijaObracuna = frekvencijaObracuna;
	}

	public String getSifraValuteObracuna() {
		return sifraValuteObracuna;
	}

	public void setSifraValuteObracuna(String sifraValuteObracuna) {
		this.sifraValuteObracuna = sifraValuteObracuna;
	}

	public BigDecimal getOsnovicaObracuna() {
		return osnovicaObracuna;
	}

	public void setOsnovicaObracuna(BigDecimal osnovicaObracuna) {
		this.osnovicaObracuna = osnovicaObracuna;
	}

	public Integer getNamjenaTipaPartije() {
		return namjenaTipaPartije;
	}

	public void setNamjenaTipaPartije(Integer namjenaTipaPartije) {
		this.namjenaTipaPartije = namjenaTipaPartije;
	}

	public String getOpisPodstatusa() {
		return opisPodstatusa;
	}

	public void setOpisPodstatusa(String opisPodstatusa) {
		this.opisPodstatusa = opisPodstatusa;
	}

	public String getAlternativniIban() {
		return alternativniIban;
	}

	public void setAlternativniIban(String alternativniIban) {
		this.alternativniIban = alternativniIban;
	}

	public String getBrojKartice() {
		return brojKartice;
	}

	public void setBrojKartice(String brojKartice) {
		this.brojKartice = brojKartice;
	}

	public String getSifraDodatnogPodatka() {
		return sifraDodatnogPodatka;
	}

	public void setSifraDodatnogPodatka(String sifraDodatnogPodatka) {
		this.sifraDodatnogPodatka = sifraDodatnogPodatka;
	}

	public String getNazivDodatnogPodatka() {
		return nazivDodatnogPodatka;
	}

	public void setNazivDodatnogPodatka(String nazivDodatnogPodatka) {
		this.nazivDodatnogPodatka = nazivDodatnogPodatka;
	}

	public Integer getSifraFaktura() {
		return sifraFaktura;
	}

	public void setSifraFaktura(Integer sifraFaktura) {
		this.sifraFaktura = sifraFaktura;
	}

	public Integer getSifraTipaNalogaObracunaNaknade() {
		return sifraTipaNalogaObracunaNaknade;
	}

	public void setSifraTipaNalogaObracunaNaknade(Integer sifraTipaNalogaObracunaNaknade) {
		this.sifraTipaNalogaObracunaNaknade = sifraTipaNalogaObracunaNaknade;
	}
	public Integer getSifraTipaNalogaInicijatorNaknade() {
		return sifraTipaNalogaInicijatorNaknade;
	}

	public void setSifraTipaNalogaInicijatorNaknade(Integer sifraTipaNalogaInicijatorNaknade) {
		this.sifraTipaNalogaInicijatorNaknade = sifraTipaNalogaInicijatorNaknade;
	}

	public Integer getSifraPaketa() {
		return sifraPaketa;
	}

	public void setSifraPaketa(Integer sifraPaketa) {
		this.sifraPaketa = sifraPaketa;
	}

	@IFilter(entityField="pojed.naziv_poj")
	public String getNazivPoslovnice() {
		return nazivPoslovnice;
	}

	public void setNazivPoslovnice(String nazivPoslovnice) {
		this.nazivPoslovnice = nazivPoslovnice;
	}

	@IFilter(entityField="komitent.matbr_kom")
	public String getMaticniBrojVlasnika() {
		return maticniBrojVlasnika;
	}

	public void setMaticniBrojVlasnika(String maticniBrojVlasnika) {
		this.maticniBrojVlasnika = maticniBrojVlasnika;
	}

	@IFilter(entityField="partija.napom_par")
	public String getNapomena() {
		return napomena;
	}

	public void setNapomena(String napomena) {
		this.napomena = napomena;
	}

	public String getPosaoTip() {
		return posaoTip;
	}

	public void setPosaoTip(String posaoTip) {
		this.posaoTip = posaoTip;
	}

	public String getVrstakTipaPartije() {
		return vrstakTipaPartije;
	}

	public void setVrstakTipaPartije(String vrstakTipaPartije) {
		this.vrstakTipaPartije = vrstakTipaPartije;
	}

}
