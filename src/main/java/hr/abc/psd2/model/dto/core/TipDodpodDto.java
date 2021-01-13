package hr.abc.psd2.model.dto.core;

import hr.abc.psd2.annotation.IFilter;
import hr.abc.psd2.lookup.ModulNewLookup;
import hr.abc.psd2.model.dto.GenericDto;

import java.io.Serializable;
import java.util.Objects;

public class TipDodpodDto extends GenericDto<Integer> implements Serializable {
	
	private static final long serialVersionUID = 1L;

	// table name, alias & fields
	public static final String TABLE_NAME = "tip_dodpod";
	public static final String TABLE_ALIAS = "TipDodpod"; // isto kao i entity - ne mijenjati i paziti u DB na tip_dodpod gdje se u tabli_tip koristi ovaj alias
	public static final String SIFRA_FIELD = "sifra_tip";
    public static final String NAZIV_FIELD = "naziv_tip";
    public static final String OBAVE_FIELD = "obave_tip";
    public static final String TABLI_FIELD = "tabli_tip";
    public static final String FORMA_FIELD = "forma_tip";
    public static final String PRIKA_FIELD = "prika_tip";
    public static final String MODUL_FIELD = "modul_tip";
    // constants
	// način prikaza kod unosa
	public static final String NACIN_PRIKAZA_LISTA = "L";
	// tipovi dodatnih podataka
    public static final Integer TIP_ULOGA_KOMITENTA = 1;
    public static final Integer TIP_SEKTOR_NEREZIDENT = 2;
	public static final Integer TIP_MFID = 3;
	public static final Integer TIP_SWIFT_BIC = 4;
    public static final Integer TIP_DRZAVLJANSTVO = 5;
    public static final Integer TIP_FATCA_TIN = 6;
    public static final Integer TIP_SVRHA_POSLOVNI_ODNOS = 7;
    public static final Integer TIP_IZVOR_SREDSTAVA = 8;
    public static final Integer TIP_PREFERIRANI_KANAL_KOMUNIKACIJE = 9;
    public static final Integer TIP_OSTALI_IZVORI_PRIHODA = 11;
    public static final Integer TIP_IZNOS_OSTALIH_PRIHODA = 12;
    public static final Integer TIP_KORISTENJE_GOTOVINSKE_TRANS = 15;
	public static final Integer TIP_DOBAVLJACI = 16;
	public static final Integer TIP_KLIJENTI = 17;
    public static final Integer TIP_NAZIV_ZAJEDNICKI_RACUN_PO = 18;
    public static final Integer TIP_NAZIV_ZAJEDNICKI_RACUN_FO = 19;
    public static final Integer TIP_OIB_STATUSNA_PROMJENA = 20;
    public static final Integer TIP_EMAIL_AUTOMATIKA = 24;
	public static final Integer TIP_SMS_AUTOMATIKA = 25;
	public static final Integer TIP_DODATNA_ADRESA_IZVOD_VALUTA = 26;
	public static final Integer TIP_FAX_IZVOD_VALUTA = 27;
	public static final Integer TIP_EMAIL_IZVOD_VALUTA = 28;
	public static final Integer TIP_FAX_DEV_PRILJEV_ODLJEV = 29;
	public static final Integer TIP_BROJ_POTPISA_IB = 30;
	public static final Integer TIP_DODATNA_ADRESA_IZVOD_KUNE = 31;
	public static final Integer TIP_FAX_IZVOD_KUNE = 32;
	public static final Integer TIP_EMAIL_IZVOD_KUNE = 33;
	public static final Integer TIP_IZBORI = 34;
	public static final Integer TIP_OZNAKA_NAMJENE_PP = 35;
	public static final Integer TIP_IZVOD_FINA_SJEDISTE = 36;
	public static final Integer TIP_MOBITEL_SMS_USLUGE = 37;
	public static final Integer TIP_BROJ_IZDANIH_CITACA = 38;
    public static final Integer TIP_VRSTE_TRANSAKCIJA = 39;
	public static final Integer TIP_TELEFONI_OPOMENE = 41;
	public static final Integer TIP_POREZNI_BROJ = 42;
    public static final Integer TIP_PASIVNA_NEFINANCIJSKA_PO = 43;
	public static final Integer TIP_KOMENTAR_BANKE = 1000;
	public static final Integer TIP_KONTAKT_OSOBA_1 = 1001;
	public static final Integer TIP_BROJ_TELEFONA_1 = 1002;
	public static final Integer TIP_BROJ_MOBITELA_1 = 1003;
	public static final Integer TIP_BROJ_FAKSA_1 = 1004;
	public static final Integer TIP_EMAIL_1 = 1005;
    public static final Integer TIP_POSTOTAK_POREZA = 1007;
	public static final Integer TIP_KONTAKT_OSOBA_2 = 1011;
	public static final Integer TIP_BROJ_TELEFONA_2 = 1012;
	public static final Integer TIP_BROJ_MOBITELA_2 = 1013;
	public static final Integer TIP_BROJ_FAKSA_2 = 1014;
	public static final Integer TIP_EMAIL_2 = 1015;
	public static final Integer TIP_MOBITEL_3D_SECURE = 1016;
	public static final Integer TIP_SMS_SERVIS = 1100;
	public static final Integer TIP_PARTNERnet = 1101;
	public static final Integer TIP_PARTNERmobile = 1102;
	public static final Integer TIP_VRSTE_TRANSAKCIJA_RACUN = 1103;
	public static final Integer TIP_VRSTE_TRANSAKCIJA_RACUN_OSTALO_OPIS = 1104;
	public static final Integer TIP_KORISTENJE_ZA_GOTOVINU_RACUN = 1105;
	public static final Integer TIP_KORISTENJE_ZA_GOTOVINU_RACUN_OPIS = 1106;
	public static final Integer TIP_OCEKIVANI_GOD_PROMET_PO_RACUNU = 1107;
	public static final Integer TIP_OZNAKA_NAMJENE_RACUNA = 1108;
	public static final Integer TIP_OGRANICENJE_OBRADE_DOR_PARTIJA = 5000;
	public static final Integer TIP_OGRANICENJE_OBRADE_DOR_KOMITENT = 5001;
	// u bazi ali se više ne koriste
    @Deprecated public static final Integer TIP_SVRHA_POSLOVNI_ODNOS_OLD = 10; // otvoren pod drugom šifrom - 7 - drugačiji način unosa
    @Deprecated public static final Integer TIP_ODNOS_S_BANKOM_U_IME_DRUGOGA = 13; // Lana komentar - eApps
    @Deprecated public static final Integer TIP_DOKAZ_OVLASTENOSTI = 14; // Lana komentar - eApps
    @Deprecated public static final Integer TIP_OPCINA_ZA_PRIREZ = 40; // podatak je preseljen na komitent - prirz_kom

	// fields
	private String nazivTip;
    private String obaveTip;
	private String tablicaTip;
    private String formaTip;
    private String nacinPrikaza;
    private ModulNewLookup modulNewLookup;
	// help fields
	private String nazivModula;

	// constructors
	public TipDodpodDto(){
		super();
	}

    @Override
    public String toString() { return getClass().getSimpleName() + "[" + TABLE_ALIAS + "=" + sifra + "]"; }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null && !(o instanceof TipDodpodDto)) return false;
		TipDodpodDto that = (TipDodpodDto) o;
		return Objects.equals(getSifra(), that.getSifra());
	}

	@Override
	public int hashCode() { return Objects.hash(getClass().getSimpleName(), getSifra()); }

	// getters & setters
	@Override
    @IFilter(entityField = TABLE_ALIAS + "." + SIFRA_FIELD)
	public Integer getSifra() { return super.getSifra(); }
	
	@Override
	public void setSifra(Integer sifra) { this.sifra = sifra; }

    @IFilter(entityField = TABLE_ALIAS + "." + NAZIV_FIELD)
	public String getNazivTip() { return nazivTip; }
	
	public void setNazivTip(String nazivTip) { this.nazivTip = nazivTip; }

    @IFilter(entityField = TABLE_ALIAS + "." + OBAVE_FIELD)
    public String getObaveTip() { return obaveTip; }

    public void setObaveTip(String obaveTip) { this.obaveTip = obaveTip; }

    @IFilter(entityField = TABLE_ALIAS + "." + TABLI_FIELD)
	public String getTablicaTip() { return tablicaTip; }
	
	public void setTablicaTip(String tablicaTip) { this.tablicaTip = tablicaTip; }

    @IFilter(entityField = TABLE_ALIAS + "." + FORMA_FIELD)
	public String getFormaTip() { return formaTip; }
	
	public void setFormaTip(String formaTip) { this.formaTip = formaTip; }

    @IFilter(entityField = TABLE_ALIAS + "." + PRIKA_FIELD)
    public String getNacinPrikaza() { return nacinPrikaza; }

    public void setNacinPrikaza(String nacinPrikaza) { this.nacinPrikaza = nacinPrikaza; }

	@IFilter(entityField = TABLE_ALIAS + "." + MODUL_FIELD)
	public ModulNewLookup getModulNewLookup() {
		if (modulNewLookup == null) {
			modulNewLookup = new ModulNewLookup();
		}
		return modulNewLookup;
	}

	public void setModulNewLookup(ModulNewLookup modulNewLookup) {
		this.modulNewLookup = modulNewLookup;
	}

	public String getNazivModula() { return nazivModula; }

	public void setNazivModula(String nazivModula) { this.nazivModula = nazivModula; }
}