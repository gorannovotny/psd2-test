package hr.abc.psd2.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class GenericBassxConstants {

	public static class CoreHub {
		public static final Integer DEPRECATED = -1;
		public static final String LANGUAGE_LOCALE_DEFAULT = "hr";
		public static final String COUNTRY_LOCALE_DEFAULT = "HR";
		public static final String MESSAGE_BUNDLE = "hr.abit.b3.biz.core.message.coreHubMessages";
		public static final String FAX_FILE_DATE_FORMAT = "ddMMyyyy";
		public static final String AMOUNT_NUMBER_PATTERN = "#,##0.00;-#,##0.00";
		public static final SimpleDateFormat FAX_FILE_DATE_FORMATTER = new SimpleDateFormat(FAX_FILE_DATE_FORMAT);
		public static final String MATICNI_BROJ_BANKE = "03726177";
		public static final String OIB_BANKE = "71221608291";
		public static final String NAZIV_BANKE = "PARTNER BANKA";
		public static final String NAZIV_BANKE_SWIFT = "PARTNER BANKA D.D.ZAGREB";
		public static final String NAZIV_SREDISNJA_BANKA = "HRVATSKA NARODNA BANKA";
		public static final String ADRESA_BANKE = "Von\u010Dinina 2, 10000 Zagreb";
        public static final String TELEFON_BANKE = "+385 1 4602 222";
        public static final String TELEFAKS_BANKE = "+385 1 4602 289";
        public static final String TELEFON_BANKE_INFO = "01 4602 304";
        public static final String MBS_BANKE = "080003730";
		public static final String VBDI_BANKE = "2408002";
		public static final String VBDI4_BANKE = "2408";
		public static final String RACUN_BANKE_NKS_FORMAT = "1000002870";
		public static final String RACUN_BANKE_DUMMY_KONTOKORENT = "9000000462";
		public static final String RACUN_BANKE_ODBIJENA_PARTIJA_NKS_FORMAT = "9800000015";
		public static final String RACUN_BANKE_OSNIVACKI_POLOG = "1000000013";
		public static final String IBAN_BANKE = "HR94" + VBDI_BANKE + RACUN_BANKE_NKS_FORMAT;
		public static final String EMAIL_BANKE = "partner@paba.hr";
        public static final String EMAIL_BANKE_REKLAMACIJE = "reklamacije@paba.hr";
        public static final String WEB_BANKE = "www.paba.hr";
		public static final Integer SIFRA_RACUNA_BANKE = 2053;
		public static final Integer SIFRA_KOMITENTA_BANKE = 14467;
        public static final String ZASTUPNIK_BANKE = "Direktor, Petar Repušić";
        public static final Integer DULJINA_RACUNA = 10;
		public static final Integer DULJINA_RACUNA_IBAN = 21;
		public static final String BANK_SWIFT_BIC8 = "PAZGHR2X";
		public static final String BANK_SWIFT_BIC8_TEST = "PAZGHR20";
		public static final String BANKA_BOX = NAZIV_BANKE_SWIFT + Core.NEW_LINE + ADRESA_BANKE + Core.NEW_LINE + "OIB: " + OIB_BANKE + Core.NEW_LINE + "IBAN: " + IBAN_BANKE;
        
        public static final String ROWS_LIMIT_DEFAULT = "100";

		public static final String GENERIC_YES_CHAR_1 = "Y";
		public static final String GENERIC_NO_CHAR_1 = "N";
        public static final String GENERIC_DESCRIPTION_YES = "DA";
		public static final String GENERIC_DESCRIPTION_NO = "NE";
		public static final String INTERNAL_ERROR_CODE = "0000";
		public static final String INTERNAL_VALIDATION_ERROR_CORE = "0001";
		public static final String INTERNAL_INFO_CODE = "0002";
		public static final String INTERNAL_WARNING_CODE = "0003";

		// REGEXP
		public static final String REGEXP_ENGLISH_LETTERS_ONE_OR_MORE = "[a-zA-Z]+";
		public static final String REGEXP_DIGITS_ONE_OR_MORE = "[0-9]+";
		public static final String REGEXP_IBAN_ALLOWED_ONE_OR_MORE = "[0-9A-Z]+";

		public static final String SERIAL_SQL = "select DBINFO('sqlca.sqlerrd1') from systables where tabid = 1";

		/** obračuna kamate */
		public static final String OBRACUN_KAMATE_TIP_STOPE_GODISNJA = "G";
		public static final String OBRACUN_KAMATE_TIP_STOPE_MJESECNA = "M";
	    public static final Integer OBRACUN_KAMATE_TIP_OBRACUNA_PROPORCIONALNI_OBRACUN = 1;
	    public static final Integer OBRACUN_KAMATE_TIP_OBRACUNA_KONFORMNI_OBRACUN = 2;
		public static final Integer OBRACUN_KAMATE_TIP_OBRACUNA_ANTICIPATIVNI_OBRACUN = 3;
		public static final Integer OBRACUN_KAMATE_TIP_OBRACUNA_PROPORCIONALNI_360OBRACUN = 4;
	    public static final Integer OBRACUN_KAMATE_TIP_OBRACUNA_KONFORMNI_360OBRACUN = 5;
	    public static final Integer OBRACUN_KAMATE_TIP_OBRACUNA_DISKONTNO = 6;
	    public static final String OBRACUN_KAMATE_OBILJEZJE_STOPE_OSNOVNA = "1";
	    public static final String OBRACUN_KAMATE_OBILJEZJE_STOPE_DODATNA = "2";
	    public static final String OBRACUN_KAMATE_OBILJEZJE_STOPE_DOGOVORNA = "3";
	    public static final int OBRACUN_KAMATE_SAMO_POZITIVNA_SALDA = 1;
	    public static final int OBRACUN_KAMATE_SAMO_NEGATIVNA_SALDA = -1;
	    public static final int OBRACUN_KAMATE_SVA_SALDA = 0;
	    /** obračuna kamate */

		public static final Integer BROJ_DANA_KNJIZENJA_UNATRAG = 1000;
		public static final Integer BROJ_DANA_KNJIZENJA_UNAPRIJED = 1000;
		public static final String SIFRA_SEKTORA_STANOVNISTVO_REZIDENT = "40";
		public static final String SIFRA_SEKTORA_STANOVNISTVO_BORAVAK = "41";
		public static final String SIFRA_SEKTORA_STANOVNISTVO_NEREZIDENT = "96";
//		public static final String NALOG_STATUS_ZAVRSEN = "7";
//		public static final String NALOG_STATUS_POVUCEN = "8";
//		public static final String NALOG_STATUS_STORNIRAN = "9";
	    public static final String TIP_NALOGA_SET_NE_ULAZI_U_RASPOLOZIVO = "622";
		public static final String MAP_KEY_ERRORS = "ERRORS";
		public static final String MAP_KEY_MESSAGES = "MESSAGES";
		public static final String MAP_KEY_REPORT_TITLE = "reportTitle";
	    public static final String MAP_VALUE_REPORT_OUTPUT_TYPE_PDF = "pdf";
	    public static final String OZNAKA_DUGUJE = "D";
	    public static final String OZNAKA_POTRAZUJE = "P";

		public static Date VRIJEDPAR_MINIMALNI_DATUM_ZADNJEG_OBRACUNA_REDOVNE = GenericDateUtil.anuliranjeDatuma(GenericDateUtil.getLastOfPreviousMonth(GenericDateUtil.goLiveDate()));
		public static Date VRIJEDPAR_MINIMALNI_DATUM_ZADNJEG_OBRACUNA_ZATEZNE = GenericDateUtil.anuliranjeDatuma(GenericDateUtil.getLastOfPreviousMonth(GenericDateUtil.goLiveDate()));
		
		public static final String LIMIT_LEVEL_CLIENT = "K";
		public static final String LIMIT_LEVEL_ACCOUNT = "R";
		
		public static final String LIMIT_TYPE_DEFAULT = "D";
		public static final String LIMIT_TYPE_MAX = "M";

		public static final String LIMIT_CATEGORY_PER_TRANSACTION = "T";
		public static final String LIMIT_CATEGORY_OVERALL = "U";
		// WS client constants
		public static final int URL_CONNECTION_READ_TIMEOUT = 15000;
		public static final int URL_CONNECTION_CONNECTION_TIMEOUT = 30000;
		
	}
    
    public static class Core {
		public static final String NEW_LINE = "\r\n";
        
        public static final String UTILMAP_KEY_EXCEPTION = "exceptionList";

		public static final String LIMIT_NIVO_RACUN = "R";
		public static final String LIMIT_NIVO_KLIJENT = "K";
		public static final String LIMIT_OSOBA_PRAVNA = "P";
		public static final String LIMIT_OSOBA_FIZICKA = "S";
		public static final String LIMIT_TIP_DEFAULT = "D";
		public static final String LIMIT_TIP_MAX = "M";

        
        //statusi partije
        public static final String PARTIJA_STATUPAR_UPISANA = "U";
        public static final String PARTIJA_STATUPAR_NAJAVA_ZATVARANJA = "S";
		public static final String PARTIJA_STATUPAR_ZA_SALDACIJU = "S";
		public static final String PARTIJA_STATUPAR_OTVORENA = "A";
		public static final String PARTIJA_STATUPAR_ZATVORENA = "Z";
        public static final String PARTIJA_STATUPAR_BLOKIRANA = "B";
        public static final String PARTIJA_STATUPAR_NEAKTIVNA = "N";
		public static final String PARTIJA_INTBLPAR_BLOKIRANA_KUNE = "K";
		public static final String PARTIJA_STATUPAR_BLOKIRANA_KUNE = "K";
		public static final String PARTIJA_INTBLPAR_BLOKIRANA_DEVIZE = "D";
		public static final String PARTIJA_STATUPAR_BLOKIRANA_DEVIZE = "D";
        
		public static final String STATUPAR_BLOKIRAN_NAJAVA_ZATVARANJA_STECAJ = "L";
        
        // interni statusi
		public static final String PARTIJA_INTBLPAR_BLOKIRANA = "B";
        
        public static final String PARTIJA_STATUPAR_OTVORENA_OPIS = "OTVORENA";
		public static final String PARTIJA_STATUPAR_ZATVORENA_OPIS = "ZATVORENA";
		public static final String PARTIJA_STATUPAR_BLOKIRANA_OPIS = "BLOKIRANA";
		public static final String PARTIJA_STATUPAR_NEAKTIVNA_OPIS = "NEAKTIVNA";
		public static final String PARTIJA_STATUPAR_UPISANA_OPIS = "UPISANA";

		public static final Integer MODUL_SIFRA_MODULA_PLATNI = 3;
		public static final Integer MODUL_SIFRA_MODULA_PLATNI_SEPA = 20;
		public static final Integer MODUL_SIFRAMOD_AVPO = 3;
		public static final Integer MODUL_SIFRAMOD_RETAIL = 5;
		public static final Integer MODUL_SIFRAMOD_KREDITI = 6;
		public static final Integer MODUL_SIFRAMOD_IB_GRADANI = 992;
		public static final Integer MODUL_SIFRAMOD_IB_PRAVNE_OSOBE = 997;
		public static final Integer MODUL_SIFRAMOD_IB_ADMIN = 990;
		public static final Integer MODUL_SIFRAMOD_IB_MENU = 991;
		public static final Integer MODUL_SIFRAMOD_REGISTAR_OSIGURANJA = 15;
		
		public static final String VALUTA_SIFRAVAL_HOME_CURRENCY = "191";
		public static final String VALUTA_OZNKAVAL_HOME_CURRENCY = "HRK";
		
		public static final String DEFAULT_LOCALE_LANGUAGE = "hr";
		public static final String DEFAULT_LOCALE_COUNTRY = "HR";
		
		// komitent
		public static final Integer KOMITENT_SIFRAKOM_HOMEBANK = CoreHub.SIFRA_KOMITENTA_BANKE;
		public static final String KOMITENT_STATUS_AKTIVAN = "A";
		public static final String KOMITENT_STATUS_NEKLIJENT = "N";
		public static final String KOMITENT_STATUS_ZATVOREN = "Z";
		public static final String KOMITENT_STATUS_BLOKIRAN = "B";

		// klasa partije
		public static final Integer KLASA_PARTIJE_RACUN_BANKE = 1;
		public static final Integer KLASA_PARTIJE_RACUN_GLAVNE_KNJIGE = 53;
		public static final Integer KLASA_PARTIJE_SIFRAKLP_ZASTICENI_RACUN = 13;
		// avpo
		public static final Integer KLASA_PARTIJE_SIFRAKLP_AVISTA_PO = 2;
		public static final Integer KLASA_PARTIJE_SIFRAKLP_TRANSAKCIJSKI_RACUNI_PRAVNIH_OSOBA_AVPO = 2;
		public static final Integer KLASA_PARTIJE_LIMIT = 2;
		// devizno
		public static final Integer KLASA_PARTIJE_UZETI_KREDIT = 3;
		public static final Integer KLASA_PARTIJE_SIFRAKLP_NAS_KONTOKORENTNI_RACUN = 4;
		public static final Integer KLASA_PARTIJE_SIFRAKLP_TUDJI_KONTOKORENTNI_RACUN_KOD_NAS = 5;
		public static final Integer KLASA_PARTIJE_SIFRAKLP_DEVIZNI_DEPOZITI_DANI = 6;
		public static final Integer KLASA_PARTIJE_SIFRAKLP_KORESPODENTI = 9;
		public static final Integer KLASA_PARTIJE_SIFRAKLP_DEVIZNI_DEPOZITI_PRIMLJENI = 9;
		// krediti
		public static final Integer KLASA_PARTIJE_SIFRAKLP_KREDITI_FIZICKA_LICA = 71;
		public static final Integer KLASA_PARTIJE_SIFRAKLP_KRATKOROCNI_KREDITI_FIZICKA_LICA = 72;
		public static final Integer KLASA_PARTIJE_SIFRAKLP_KREDITI_PRAVNA_LICA = 73;
		public static final Integer KLASA_PARTIJE_SIFRAKLP_KREDITI_MINUSI_PO_ŽIRO = 74;
		public static final Integer KLASA_PARTIJE_ESKONT_MJENICA = 76;
		public static final Integer KLASA_PARTIJE_GARANCIJE = 79;
		// retail
		public static final Integer KLASA_PARTIJE_SIFRAKLP_OROCENI_DEPOZITI_PRAVNA_LICA = 17;
		public static final Integer KLASA_PARTIJE_SIFRAKLP_BLAGAJNA = 100;
		public static final Integer KLASA_PARTIJE_SIFRAKLP_MVR_I_ZIRO_RETAIL = 101;
		public static final Integer KLASA_PARTIJE_SIFRAKLP_STEDNJE_OROCENJA = 102;
		public static final Integer KLASA_PARTIJE_SIFRAKLP_AVISTA_RETAIL = 103;
		public static final Integer KLASA_PARTIJE_SIFRAKLP_DOK_GRADJANI = 110;

		@Deprecated public static final Integer KLASA_PARTIJE_SIFRAKLP_TEKUCI_RACUN = KLASA_PARTIJE_SIFRAKLP_MVR_I_ZIRO_RETAIL;
		@Deprecated public static final Integer KLASA_PARTIJE_SIFRAKLP_ZIRO_RACUN = KLASA_PARTIJE_SIFRAKLP_MVR_I_ZIRO_RETAIL;
		@Deprecated public static final Integer KLASA_PARTIJE_SIFRAKLP_AVISTA_HRK = KLASA_PARTIJE_SIFRAKLP_AVISTA_RETAIL;
		@Deprecated public static final Integer KLASA_PARTIJE_SIFRAKLP_AVISTA_DEVIZNA = KLASA_PARTIJE_SIFRAKLP_AVISTA_RETAIL;
		@Deprecated public static final Integer KLASA_PARTIJE_SIFRAKLP_PARTIJA_STEDNJE_AVISTA = KLASA_PARTIJE_SIFRAKLP_AVISTA_RETAIL;
		@Deprecated public static final Integer KLASA_PARTIJE_SIFRAKLP_PARTIJA_STEDNJE_OROCENJA = KLASA_PARTIJE_SIFRAKLP_STEDNJE_OROCENJA;
		@Deprecated public static final Integer KLASA_PARTIJE_SIFRAKLP_OROCENI_DEPOZITI_FIZICKA_LICA = KLASA_PARTIJE_SIFRAKLP_STEDNJE_OROCENJA;
		// URA-IRA
		public static final Integer KLASA_PARTIJE_SIFRAKLP_FAKTURE_URA = 80;
		public static final Integer KLASA_PARTIJE_SIFRAKLP_FAKTURE_IRA = 81;



		public static final Integer KLASA_PARTIJE_SIFRAKLP_DEPOZIT_PREPAID = 13;
		public static final Integer KLASA_PARTIJE_SIFRAKLP_OSTALO = 20;
		public static final Integer KLASA_PARTIJE_SIFRAKLP_KOLATERALI = 43;
		public static final Integer KLASA_PARTIJE_SIFRAKLP_BAMCARD_BANKE = 21;





		public static final Integer KLASA_PARTIJE_SIFRAKLP_OKVIRNI_KREDIT_PRAVNA_LICA = 44;
		
		// Grupe dodatnih podataka
		public static enum GrupaDodPod {

		    UNOS_AVPO
		}
		
		public static final String VBDINBAN_BANKA = PlatniPromet.NKSBANKA_VBDINBAN_BANKA;
        
		//pojed
		public static final String POJED_SIFRAPOJ_IB_STANOVNISTVO = "992";
		public static final String POJED_SIFRAPOJ_IB_PRAVNE_OSOBE = "997";

		//likvidator
		public static final Integer SIFRALIK_IBG_LIKVIDATOR = 992;
		public static final Integer SIFRALIK_IBPRAVNE_LIKVIDATOR = 997;
		public static final Integer SIFRALIK_INSTAPAY = 991;
		public static final List<Integer> LIST_LIKVIDATOR_IB = Arrays.asList(SIFRALIK_IBG_LIKVIDATOR, SIFRALIK_IBPRAVNE_LIKVIDATOR);
		public static final Integer SIFRALIK_PSD2_TPP = 904;

		public static final String NADDIO_OZNKANAD_NAKNADA_UPP = "IFBT72";

		//tip tecaja
		public static final String TIP_TECAJA_KUPOVNI = "KUP";
		public static final String TIP_TECAJA_SREDNJI = "SRE";
		public static final String TIP_TECAJA_PRODAJNI = "PRO";
		public static final String TIP_TECAJA_EFEKTIVA_KUPOVNI = "KUE";
		public static final String TIP_TECAJA_EFEKTIVA_PRODAJNI = "PRE";
		public static final String TIP_TECAJA_DOGOVOR = "DOG";

		//nalog statusi
		public static final String NALOG_STATUNAL_INTERNET = "#";
		public static final String NALOG_STATUNAL_UPISAN = "0";
		public static final String NALOG_STATUNAL_PROKNJIZEN = "7";
		public static final String NALOG_STATUNAL_LIKVIDNOST = "3";
		public static final String NALOG_STATUNAL_VERIFICIRAN = "1";
		public static final String NALOG_STATUS_AUTORIZIRAN = "2";
		public static final String NALOG_STATUNAL_POSLAN = "4";
		public static final String NALOG_STATUNAL_POVUCEN = "8";
		public static final String NALOG_STATUNAL_STORNIRAN_B2 = "9";
		public static final String NALOG_STATUNAL_POVUCEN_B2 = "6";
		
		public static final String IB_NALOG_STATUNAL_OBRISAN = "X";

		//Konto
		public static final String KONTO_AKTIVA = "A";
		public static final String KONTO_PASIVA = "P";

		//tip naloga
		public static final Integer TIP_NALOGA_UNOS_KORISNIKA_IB = 10108;
		public static final Integer TIP_NALOGA_IZMJENA_IB_PARTIJE_OVLASTENJA = 10118;
		public static final Integer TIP_NALOGA_DEAKTIVACIJA_KORISNIKA_IB = 10111;
		public static final Integer TIP_NALOGA_BLOKADA_KORISNIKA_IB = 10109;
		public static final Integer TIP_NALOGA_DEBLOKADA_KORISNIKA_IB = 10110;
		public static final Integer TIP_NALOGA_DEAKTIVACIJA_IB_IDENTA = 10115;
		public static final Integer TIP_NALOGA_AKTIVACIJA_IB_IDENTA = 10117;
		public static final Integer TIP_NALOGA_ZAMJENA_IB_IDENTA = 10116;
		public static final Integer TIP_NALOGA_BLOKADA_IB_IDENTA = 10113;
		public static final Integer TIP_NALOGA_DEBLOKADA_IB_IDENTA = 10114;
		public static final Integer TIP_NALOGA_NOVI_IB_IDENT = 10122;
		public static final Integer TIP_NALOGA_DODAVANJE_CITACA_ZA_PKI_KARTICE = 10123;
		public static final Integer TIP_NALOGA_AZURIRANJE_BROJA_POTREBNIH_POTPISA = 10124;
		public static final Integer TIP_NALOGA_NOVO_OVLASTENJE_IB = 10125;
		public static final Integer TIP_NALOGA_AKTIVACIJA_IB_OVLASTENJA = 10126;
		public static final Integer TIP_NALOGA_BLOKADA_IB_OVLASTENJA = 10127;
		public static final Integer TIP_NALOGA_AKTIVACIJA_IB_KORISNIKA = 10128;
		public static final Integer TIP_NALOGA_DODAVANJE_USLUGE_IB = 10129;
		public static final Integer TIP_NALOGA_BRISANJE_USLUGE_IB = 10130;
		public static final Integer TIP_NALOGA_AZURIANJE_OVLASTENJA_BY_ADMIN_USER  = 10131;
		public static final Integer TIP_NALOGA_EDIT_IB_IDENT  = 10132;
		public static final Integer TIP_NALOGA_IZVRSENJE_IB_ZAHTJEVA_BON2_ILI_ISPLATA_GOTOVINE = 10133;
		public static final Integer TIP_NALOGA_POVLACENJE_IB_ZAHTJEVA_BON2_ILI_ISPLATA_GOTOVINE = 10134;
		public static final Integer TIP_NALOGA_EDIT_IB_OVLASTENJE = 10135;
		public static final Integer TIP_NALOGA_KREIRANJE_IB_OBAVIJESTI = 10136;
		public static final Integer TIP_NALOGA_BRISANJE_IB_OBAVIJESTI = 10137;
		public static final Integer TIP_NALOGA_DEAKTIVACIJA_IB_OVLASTENJA = 10138;
		public static final Integer TIP_NALOGA_EDIT_IB_NALOGA = 10139;
		public static final Integer TIP_NALOGA_LOCK_IB_IDENT = 10140;
		public static final Integer TIP_NALOGA_UNLOCK_IB_IDENT = 10141;
		public static final Integer TIPNALOGA_SIFRAPOD_TRAJNI_NAKNADA_ZA_IZVRSENJE_TRAJNOG_NALOGA = 14277;
		public static final Integer TIPNALOGA_SIFRAPOD_B2_SALTERSKI_VIRMAN_EKSTERNI_NAKNADA = 192;
		public static final Integer TIP_NALOGA_EVIDENCIJA_OIB_ZASTICENI = 10666;

		public static final Integer TIP_NALOGA_IB_USLUGA_PR_RAC_ADD = 11001;
		public static final Integer TIP_NALOGA_IB_USLUGA_PR_RAC_DEL = 11002;
		public static final Integer TIP_NALOGA_IB_USLUGA_PR_PRM_ADD = 11003;
		public static final Integer TIP_NALOGA_IB_USLUGA_PR_PRM_DEL = 11004;
		public static final Integer TIP_NALOGA_IB_USLUGA_PR_FAK_ADD = 11005;
		public static final Integer TIP_NALOGA_IB_USLUGA_PR_FAK_DEL = 11006;
		public static final Integer TIP_NALOGA_IB_USLUGA_UN_NAL_ADD = 11007;
		public static final Integer TIP_NALOGA_IB_USLUGA_UN_NAL_DEL = 11008;
		public static final Integer TIP_NALOGA_IB_USLUGA_UN_DAT_ADD = 11009;
		public static final Integer TIP_NALOGA_IB_USLUGA_UN_DAT_DEL = 11010;
		public static final Integer TIP_NALOGA_IB_USLUGA_PR_NAL_ADD = 11011;
		public static final Integer TIP_NALOGA_IB_USLUGA_PR_NAL_DEL = 11012;
		public static final Integer TIP_NALOGA_IB_USLUGA_POT_NAL_ADD = 11013;
		public static final Integer TIP_NALOGA_IB_USLUGA_POT_NAL_DEL = 11014;
		public static final Integer TIP_NALOGA_IB_USLUGA_UN_KUPPRO_ADD = 11015;
		public static final Integer TIP_NALOGA_IB_USLUGA_UN_KUPPRO_DEL = 11016;
		public static final Integer TIP_NALOGA_IB_USLUGA_PR_TEC_ADD = 11017;
		public static final Integer TIP_NALOGA_IB_USLUGA_PR_TEC_DEL = 11018;
		public static final Integer TIP_NALOGA_IB_USLUGA_PR_KUPPRO_ADD = 11019;
		public static final Integer TIP_NALOGA_IB_USLUGA_PR_KUPPRO_DEL = 11020;
		public static final Integer TIP_NALOGA_IB_USLUGA_POT_KUPPRO_ADD = 11021;
		public static final Integer TIP_NALOGA_IB_USLUGA_POT_KUPPRO_DEL = 11022;
		public static final Integer TIP_NALOGA_IB_USLUGA_UN_ZAH_ADD = 11023;
		public static final Integer TIP_NALOGA_IB_USLUGA_UN_ZAH_DEL = 11024;
		public static final Integer TIP_NALOGA_IB_USLUGA_PR_ZAH_ADD = 11025;
		public static final Integer TIP_NALOGA_IB_USLUGA_PR_ZAH_DEL = 11026;
		public static final Integer TIP_NALOGA_IB_USLUGA_POT_ZAH_ADD = 11027;
		public static final Integer TIP_NALOGA_IB_USLUGA_POT_ZAH_DEL = 11028;
		public static final Integer TIP_NALOGA_IB_USLUGA_PR_IZV_ADD = 11029;
		public static final Integer TIP_NALOGA_IB_USLUGA_PR_IZV_DEL = 11030;
		public static final Integer TIP_NALOGA_IB_USLUGA_PR_ORO_ADD = 11031;
		public static final Integer TIP_NALOGA_IB_USLUGA_PR_ORO_DEL = 11032;
		public static final Integer TIP_NALOGA_IB_USLUGA_PR_KRE_ADD = 11033;
		public static final Integer TIP_NALOGA_IB_USLUGA_PR_KRE_DEL = 11034;
		public static final Integer TIP_NALOGA_IB_USLUGA_UN_ORO_ADD = 11035;
		public static final Integer TIP_NALOGA_IB_USLUGA_UN_ORO_DEL = 11036;
		public static final Integer TIP_NALOGA_IB_USLUGA_UP_ORO_ADD = 11037;
		public static final Integer TIP_NALOGA_IB_USLUGA_UP_ORO_DEL = 11038;
		public static final Integer TIP_NALOGA_IB_USLUGA_CH_NAL_ADD = 11039;
		public static final Integer TIP_NALOGA_IB_USLUGA_CH_NAL_DEL = 11040;
		public static final Integer TIP_NALOGA_IB_USLUGA_DE_NAL_ADD = 11041;
		public static final Integer TIP_NALOGA_IB_USLUGA_DE_NAL_DEL = 11042;
		public static final Integer TIP_NALOGA_IB_USLUGA_PR_BATCH_ADD = 11043;
		public static final Integer TIP_NALOGA_IB_USLUGA_PR_BATCH_DEL = 11044;
		public static final Integer TIP_NALOGA_IB_USLUGA_PR_TPP_NAL_ADD = 11045;
		public static final Integer TIP_NALOGA_IB_USLUGA_PR_TPP_NAL_DEL = 11046;
		public static final Integer TIP_NALOGA_IB_USLUGA_POT_CON_ADD = 11047;
		public static final Integer TIP_NALOGA_IB_USLUGA_POT_CON_DEL = 11048;
		public static final Integer TIP_NALOGA_IB_USLUGA_PR_PRI_ADD = 11049;
		public static final Integer TIP_NALOGA_IB_USLUGA_PR_PRI_DEL = 11050;

		//valuta
		public static final String VALUTA_SIFRAVAL_USD = "840";
		public static final String VALUTA_SIFRAVAL_EUR = "978";        

		//nad dijelovi
		public static final String NADDIO_OZNKANAD_GLAVNICA = "01";
		public static final String NADDIO_OZNKANAD_GLAVNICA_001 = "001";
		public static final String NADDIO_OZNKANAD_REZERVACIJA = "131";
		public static final String NADDIO_OZNKANAD_PROLAZNI_DEVIZNI_RACUN = "300";

		//lokacija
		public static final String TIP_LOKACIJE_ADRESA = "1";

		public static final String DUGOVNA_STRANA = "D";
		public static final String POTRAZNA_STRANA = "P";

		public static final String IZVOD_TIP_ISPISA_DAN = "D";
		public static final String IZVOD_TIP_ISPISA_MJESEC = "M";
		public static final String IZVOD_TIP_ISPISA_RAZDOBLJE = "R";

		public static final Integer PROMOBA_TIPOB_IZVOD_PO_DEVIZNI = 2;
		public static final Integer PROMOBA_TIPOB_IZVOD_PO_DOMICILNI = 1;

		public static final String REPORT_TYPE_PARTIJA = "partija";

		//tip veze
		public static final String TIP_VEZE_SIFRA_OPUNOMOCENIK = "201";
		public static final String TIP_VEZE_SIFRA_ZAKONSKI_ZASTUPNIK = "202";
		
		public static final String WARNING_MESSAGES = "warningMessages";
    }

	public static class ErrorCodes {
		// codes
		public static final String ABIT_ERROR_CODE_OPTIMISTIC_LOCKING_CODE = "AEC: 1";

		// labels
		public static final String ABIT_ERROR_CODE_OPTIMISTIC_LOCKING_LABEL = "Row was updated or deleted by another transaction";
	}

	/**
	 * Konstante za modul IB koje ovisi samo o minimalnom core-hub-u.
	 * 
	 * @author Mata
	 */
	public static class Ib {
		public static final Integer MODUL_SIFRAMOD_IB_GRADANI = 992;
		public static final Integer MODUL_SIFRAMOD_IB_PRAVNE_OSOBE = 997;
		
		public static final String IZNOS_FORMAT="##,###,###,##0.00";


		public static final String MESSAGE_BUNDLE = "hr.abit.b3.biz.ib.message.webMessages";

		public static final String NALOG_VRSTA_KUNSKI = "K";
		public static final String NALOG_VRSTA_KUPNJA_DEVIZA = "D";
		public static final String NALOG_VRSTA_UPLATA_OROCENJA = "O";

		public static final String USLUGA_POTPIS = "POT_KN";

		public static final String TIP_USLUGE_UNOS = "UN";
		public static final String TIP_USLUGE_POTPIS = "PO";
		public static final String TIP_USLUGE_PREGLED = "PR";
		
		public static final String VRSTA_STEDNJE_OROCENJA = "OROCENJA";
		public static final String VRSTA_STEDNJE_RENTNA = "RENTNA";
		
		public static final Integer TEMELJ_DODATNE_KAMATNE_STOPE_PARTNERNET = 62;
		public static final BigDecimal OSNOVNA_DODATNA_KAMATNA_STOPA_PARTNERNET = new BigDecimal("0.1");

		public static final Integer TIP_NALOGA_ZAMJENA_USB = 8070;
		public static final Integer TIP_NALOGA_ZAMJENA_PKI_ZA_USB = 8071;
		public static final Integer TIP_NALOGA_ZAMJENA_USB_GUBITAK_OSTECENJE = 8072;
		public static final Integer TIP_NALOGA_NEVRACENI_USB_NAKON_DEAKTIVACIJE = 8073;
		public static final Integer TIP_NALOGA_IZDAVANJE_PRVOG_USB_UREDJAJA = 8074;
		public static final Integer TIP_NALOGA_IZDAVANJE_USB_UREDJAJA_PA311_PLUS = 8075;
		public static final Integer TIP_NALOGA_IZDAVANJE_USB_UREDJAJA = 20022;

	}
	
	public static class PlatniPromet {
		// status obrade deviznog naloga
		public static final String DEVIZNI_NALOG_STATUS_INICIRAN = "0";
		public static final String DEVIZNI_NALOG_STATUS_VERIFICIRAN = "1";
		public static final String DEVIZNI_NALOG_STATUS_ZAVRSEN = "7";
		public static final String DEVIZNI_NALOG_STATUS_POVUCEN = "8";
		
		// status SWIFT obrade deviznog naloga
		public static final String SWIFT_NALOG_STATUS_INICIRAN = "0";
		public static final String SWIFT_NALOG_STATUS_ZAVRSEN = "7";
		public static final String SWIFT_NALOG_STATUS_POVUCEN = "8";
		
		public static final String UTILMAP_KEY_EXCEPTION = "exceptionList";
		
		public static final String NKSBANKA_VBDINBAN_BANKA = CoreHub.VBDI_BANKE;
		public static final String DRZAVA_HOME_OZNKADVA = "HR";
		public static final String GRUPA_RACUNA_17 = "17";
		public static final String GRUPA_RACUNA_18 = "18";
		public static final String PREFIX_MODEL_PLACANJA_HR = "HR";

		public static final Integer PLATNI_NALOG_HITNOST_REDOVNO = 0;
		public static final Integer PLATNI_NALOG_HITNOST_HITNO = 1;
		public static final Integer PLATNI_NALOG_HITNOST_INSTANT = 2;

		//nacin izvrsenja
		public static final String NACIN_IZVRSENJA_INTERNI_NALOG = "I";
		public static final String NACIN_IZVRSENJA_DEVIZNI_INTERNI_NALOG = "D";
		public static final String NACIN_IZVRSENJA_SWIFT_NALOG = "S";
		public static final String NACIN_IZVRSENJA_HSVP = "H";
		public static final String NACIN_IZVRSENJA_HRK_NKS = "N";
		public static final String NACIN_IZVRSENJA_EUR_NKS = "E";
		public static final String NACIN_IZVRSENJA_TARGET2 = "T";
		public static final String NACIN_IZVRSENJA_INSTANTPAY = "M"; //(momentary)

		public static final String B2STATUS_TABLE_SPP_NALOGODAVATELJ = "NalogHub3";
		public static final String B2STATUS_FIELD_SPP_NALOGODAVATELJ = "nlgdvHub";

		public static final String DEVIZNI_NALOG_TROSAK_INO_BANKE_SHA = "SHA";
		public static final String DEVIZNI_NALOG_TROSAK_INO_BANKE_OUR = "OUR";
		public static final String DEVIZNI_NALOG_TROSAK_INO_BANKE_BEN = "BEN";
		public static final String DEVIZNI_NALOG_TROSAK_INO_BANKE_SLEV = "SLEV";

		public static final String PLATNI_NALOG_MEDIJ_ELEKTRONSKO_BANKARSTVO = "E";
		public static final Integer DEVIZNI_INSTRUMENT_SIFRAINS_DOZNAKA = 10;


		//klasa naloga
		public static final Integer KLASA_NALOGA_SIFRAKNA_DEVIZNO_TRZISTE_PRAVNE_OSOBE = 140;
        public static final Integer KLASA_NALOGA_SIFRAKNA_DEVIZNO_TRZISTE_FIZICKE_OSOBE = 141;
		public static final Integer KLASA_NALOGA_SIFRAKNA_BATCHBOOKING_ZBIRNI = 50214;

		public static final Integer KLASA_NALOGA_SIFRAKNA_FO_NA_FO = 50232;
		public static final Integer KLASA_NALOGA_FINANCIJSKI = 50200;
		public static final Integer KLASA_NALOGA_SIFRAKNA_SCT_INTERNI = 50202;
		public static final Integer KLASA_NALOGA_SIFRAKNA_SCT_INTERNI_VALUTA = 50222;
		public static final Integer KLASA_NALOGA_SIFRAKNA_SCT_HITNI_HSVP = 50203;
		public static final Integer KLASA_NALOGA_SIFRAKNA_SCT_REDOVNI_NKS = 50204;
		public static final Integer KLASA_NALOGA_SIFRAKNA_SCT_HITNI_T2 = 50205;
		public static final Integer KLASA_NALOGA_SIFRAKNA_SCT_REDOVNI_ENKS = 50206;
		public static final Integer KLASA_NALOGA_SIFRAKNA_NABANKU_DOMICILNA = 50215;
		public static final Integer KLASA_NALOGA_SIFRAKNA_NABANKU_VALUTA = 50216;
		public static final Integer KLASA_NALOGA_SIFRAKNA_SCT_INSTANTPAY_EUR = 50223;
		public static final Integer KLASA_NALOGA_SIFRAKNA_SCT_INSTANTPAY_HOME = 50221;
        
        // kupoprodaja
		public static final Integer TIP_NALOGA_SIFRAPOD_SWIFT_OPCI_DEV_NALOG4110 = 4110;
		public static final Integer TIP_NALOGA_SIFRAPOD_SWIFT_OPCI_DEV_NALOG4120 = 4120;
		public static final Integer TIP_NALOGA_SIFRAPOD_SWIFT_OPCI_DEV_NALOG4130 = 4130;
		public static final Integer TIP_NALOGA_SIFRAPOD_SWIFT_OPCI_DEV_NALOG4140 = 4140;
        public static final Integer TIP_NALOGA_SIFRAPOD_SWIFT_OPCI_DEV_NALOG41101 = 41101;
        public static final Integer TIP_NALOGA_SIFRAPOD_SWIFT_OPCI_DEV_NALOG41201 = 41201;
        public static final Integer TIP_NALOGA_SIFRAPOD_SWIFT_OPCI_DEV_NALOG41301 = 41301;
        public static final Integer TIP_NALOGA_SIFRAPOD_SWIFT_OPCI_DEV_NALOG41401 = 41401;
		public static final Integer TIP_NALOGA_INTERNI = 20502;
		public static final Integer TIP_NALOGA_HSVP_ODLJEV = 20503;
		public static final Integer TIP_NALOGA_NKS_ODLJEV = 20504;
		public static final Integer TIP_NALOGA_T2_ODLJEV = 20505;
		public static final Integer TIP_NALOGA_EURO_NKS_ODLJEV = 20506;
		public static final Integer TIP_NALOGA_INSTANTPAY_ODLJEV = 20521;
		public static final Integer TIP_NALOGA_EURO_INSTANTPAY_ODLJEV = 20523;

        public static final String LIST_TIP_NALOGA_KUPOPRODAJA_DEVIZA = TIP_NALOGA_SIFRAPOD_SWIFT_OPCI_DEV_NALOG4110 + ", " + TIP_NALOGA_SIFRAPOD_SWIFT_OPCI_DEV_NALOG4120 + ", " + TIP_NALOGA_SIFRAPOD_SWIFT_OPCI_DEV_NALOG4130 + ", " + TIP_NALOGA_SIFRAPOD_SWIFT_OPCI_DEV_NALOG4140;
        public static final String LIST_TIP_NALOGA_KUPOPRODAJA_DEVIZA_ALL = TIP_NALOGA_SIFRAPOD_SWIFT_OPCI_DEV_NALOG4110 + ", " + TIP_NALOGA_SIFRAPOD_SWIFT_OPCI_DEV_NALOG4120 + ", " + TIP_NALOGA_SIFRAPOD_SWIFT_OPCI_DEV_NALOG4130 + ", " + TIP_NALOGA_SIFRAPOD_SWIFT_OPCI_DEV_NALOG4140 + ", " + TIP_NALOGA_SIFRAPOD_SWIFT_OPCI_DEV_NALOG41101 + ", " + TIP_NALOGA_SIFRAPOD_SWIFT_OPCI_DEV_NALOG41201 + ", " + TIP_NALOGA_SIFRAPOD_SWIFT_OPCI_DEV_NALOG41301 + ", " + TIP_NALOGA_SIFRAPOD_SWIFT_OPCI_DEV_NALOG41401;

        public static final Integer TIP_NALOGA_SIFRAPOD_PSD2_OPCI_DEV_NALOG4110 = 41102;
        public static final Integer TIP_NALOGA_SIFRAPOD_PSD2_OPCI_DEV_NALOG4120 = 41202;
        public static final Integer TIP_NALOGA_SIFRAPOD_PSD2_OPCI_DEV_NALOG4130 = 41302;
        public static final Integer TIP_NALOGA_SIFRAPOD_PSD2_OPCI_DEV_NALOG4140 = 41402;
        public static final Integer TIP_NALOGA_SIFRAPOD_PSD2_OPCI_DEV_NALOG41101 = 41103;
        public static final Integer TIP_NALOGA_SIFRAPOD_PSD2_OPCI_DEV_NALOG41201 = 41203;
        public static final Integer TIP_NALOGA_SIFRAPOD_PSD2_OPCI_DEV_NALOG41301 = 41303;
        public static final Integer TIP_NALOGA_SIFRAPOD_PSD2_OPCI_DEV_NALOG41401 = 41403;

        public static final String SIFRA_OPISA_PLACANJA_BBX = "BBX";
        public static final String TIP_NALOGA_DETALJ_NACIONALNOST_MEDUNARODNI = "M";
        public static final String TIP_NALOGA_DETALJ_NACIONALNOST_PREKOGRANICNI = "P";
        public static final String TIP_NALOGA_DETALJ_NACIONALNOST_NACIONALNI = "N";
		public static final Integer TIP_NALOGA_DETALJ_NAJAVA = 1;
		public static final Integer TIP_NALOGA_DETALJ_REDOVAN = 0;
		public static final String TIP_NALOGA_DETALJ_KANAL_FINA = "fina";
		public static final String TIP_NALOGA_DETALJ_KANAL_BANKA = "banka";
		public static final Integer TIP_NALOGA_IZMJENA_TPP_NALOGA_KOD_UNOSA = 11400;

		public static final String NADDIO_OZNKANAD_INSTAPAY = "203";

	}
    
	public static class Krediti {
		
		public static final String REVOLVING_DA="D";
		public static final String REVOLVING_NE="N";
		
		public static final Integer TIP_PARTIJE_SIFRA_NENAMJ_KRATKOR_KARTICE=1700;
		public static final Integer TIP_PARTIJE_SIFRA_BUSINESS_KARTICE=1738;
		public static final Integer TIP_PARTIJE_SIFRA_BUSINESS_KARTICE_UNION=1739;
		
	}
	
	public static class Depoziti {
		public static final String TIPPARTIJE_POSAOTIP_OROCENJE_FIZ_AVISTA = "16AVISTA";
	}
	
	public static class Riznica {
		public static final String SWIFTBIC_BICCOBIC_HOME_BANK = "PAZGHR2XXXX";
		public static final String SWIFTBIC_BICCOBIC_HNB_NKS = "NKSHHR22XXX";
		public static final String SWIFTBIC_BICCOBIC_HNB_DOMESTIC = "NBHRHR2DXXX";
	}
	
	public static class Avpo {
		public static final String DEFAULT_MODEL="HR99";
		public static final Integer SIFRA_LIKVIDATORA_MQFINA = 993;
	}

	public static class Retail {
		public static final String NALOGRETAIL_SMJERNRT_DUGOVNO = "D";
		
		public static final String TIPPARTIJE_POSAOTIP_TEKUCI = "32";


	}

	public static class Authentication {
		public static final String MESSAGE_BUNDLE = "hr.abit.b3.biz.auth.message.authMessages";
	}

	public static class Psd2 {
		public static final String MESSAGE_BUNDLE = "hr.abit.b3.biz.psd2.message.psd2Messages";
	}
}
