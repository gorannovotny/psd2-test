package hr.abc.psd2.model.dto.core;

import hr.abc.psd2.annotation.IFilter;
import hr.abc.psd2.model.dto.GenericDto;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class TipVezeDto extends GenericDto<Integer> implements Serializable {

	private static final long serialVersionUID = 1L;

	// table name, alias & fields
	public static final String TABLE_NAME = "tip_veze";
	public static final String TABLE_ALIAS = "TipVeze";   // isto kao i entity - ne mijenjati i paziti u DB na tip_dodpod gdje se u tabli_tip koristi ovaj alias
	public static final String SIFRA_FIELD = "sifra_tpv";
	public static final String NAZIV_FIELD = "naziv_tpv";
	public static final String OZNKA_FIELD = "oznka_tpv";
	public static final String SMJER_FIELD = "smjer_tpv";
	public static final String OPISV_FIELD = "opisv_tpv";
	public static final String GRUPA_FIELD = "grupa_tpv";
	// constants
	public static final Integer POTPISNIK_KARTONA_PRAVNA_OSOBA = 2;
	public static final Integer STVARNI_VLASNIK_PRAVNA_OSOBA = 3;
	public static final Integer ZAKONSKI_ZASTUPNIK_PRAVNA_OSOBA = 4;
	public static final Integer OBRTNIK_SAMOSTALNA_DJELATNOST_PO = 5;
	public static final Integer PROKURIST_PUNOMOCNIK_PRAVNA_OSOBA = 6;
	public static final Integer SIFRA_DODATNI_VLASNIK_PARTIJA = 7;
	public static final Integer EPOTPISNIK_PRAVNA_OSOBA = 9;
	public static final Integer SIFRA_RACUNI_NAPLATA_NAKNADE = 91;
	public static final Integer SIFRA_KOLATERAL = 141;
	public static final Integer SIFRA_JAMAC = 142;
	public static final Integer SIFRA_SUDUZNIK = 143;
	public static final Integer SIFRA_IZVOR_FINANCIRANJA = 144;
	public static final Integer SIFRA_OKVIRNI_KREDIT = 145;
	public static final Integer SIFRA_SUBVENCIJA_KAMATA = 148;
	public static final Integer SIFRA_OGRANICENJE_DOR_PARTIJA = 150;
	public static final Integer SIFRA_OGRANICENJE_DOR_KOMITENT = 151;
	public static final Integer SIFRA_OPUNOMOCENIK_PARTIJA = 201;
	public static final Integer SIFRA_ZAKONSKI_ZASTUPNIK_PARTIJA = 202;
	public static final Integer SIFRA_UPLATITELJ_ZASTICENI_PARTIJA = 203;

	public static final List<Integer> TIP_VEZE_LIST_KOMITENT = Arrays.asList(STVARNI_VLASNIK_PRAVNA_OSOBA, ZAKONSKI_ZASTUPNIK_PRAVNA_OSOBA, OBRTNIK_SAMOSTALNA_DJELATNOST_PO, PROKURIST_PUNOMOCNIK_PRAVNA_OSOBA);

	public static final List<Integer> TIP_VEZE_LIST_KOMITENT_STVARNI_VLASNIK_I_ZAKONSKI_ZASTUPNIK = Arrays.asList(STVARNI_VLASNIK_PRAVNA_OSOBA, ZAKONSKI_ZASTUPNIK_PRAVNA_OSOBA);

	// fields
	private String oznaka;
	private String smjer;
	private String naziv;
	private String opis;
	private Integer grupa;
	// help fields
	private String nazivGrupe;

	// getters & setters
	@Override
	@IFilter(entityField = TABLE_ALIAS + "." + SIFRA_FIELD)
	public Integer getSifra() {
		return super.getSifra();
	}

	@IFilter(entityField = TABLE_ALIAS + "." + OZNKA_FIELD)
	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + SMJER_FIELD)
	public String getSmjer() {
		return smjer;
	}

	public void setSmjer(String smjer) {
		this.smjer = smjer;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + NAZIV_FIELD)
	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + OPISV_FIELD)
	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	@IFilter(entityField = TABLE_ALIAS + "." + GRUPA_FIELD)
	public Integer getGrupa() {
		return grupa;
	}

	public void setGrupa(Integer grupa) {
		this.grupa = grupa;
	}

	public String getNazivGrupe() {
		return nazivGrupe;
	}

	public void setNazivGrupe(String nazivGrupe) {
		this.nazivGrupe = nazivGrupe;
	}
}