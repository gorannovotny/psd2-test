package hr.abc.psd2.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Bassx2Constants extends GenericBassxConstants {

	public static class Ib {
		public static final String MESSAGE_BUNDLE = "hr.abit.b3.biz.ib.message.webMessages";
		public static final String NALOG_VRSTA_KUNSKI = "K";
		public static final String NALOG_VRSTA_KUPNJA_DEVIZA = "D";

		public static final String TIP_KORISNIKA_FIZICKI = "FIOS";
		public static final String TIP_KORISNIKA_PRAVNI = "PROS";

		public static final String USLUGA_POTPIS = "POT_KN";

		public static final String TIP_USLUGE_UNOS = "UN";
		public static final String TIP_USLUGE_POTPIS = "PO";
		public static final String TIP_USLUGE_PREGLED = "PR";

		public static final Integer TIP_NALOGA_BON2_ZAHTJEV = 8012;
		public static final Integer TIP_NALOGA_ZAHTJEV_ZA_ISPLATU_GOTOVINE = 8009;
		public static final Integer TIP_NALOGA_SIFRA_POD_IBS_CLANARINA = 8040;
		public static final Integer TIP_NALOGA_SIFRA_POD_IBG_CLANARINA = 20025;
		public static final Integer TIP_NALOGA_SIFRA_POD_AUTOMATSKI_STORNO_BB_LOG = 8100;
		public static final Integer TIP_NALOGA_SIFRA_POD_AUTOMATSKI_STORNO_BB_PSD2_LOG = 8101;
		public static final Integer TIP_NALOGA_SIFRA_POD_AUTOMATSKI_STORNO_BASKET_PSD2_LOG = 8102;

		public static final String IB_IZBORNIK_IZVODI = "izbornikIzvodi";
		@Deprecated
		public static final String TIPDODPOD_SIFRATIP_BR_IZDANIH_CITACA = "BR_CIT";
		@Deprecated
		public static final String TIPDODPOD_SIFRATIP_BROJ_POTREBNIH_POTPISA = "IBPOT";
		public static final String TIP_IZVODA_KUNSKI = "K";
		public static final String TIP_IZVODA_DEVIZNI = "D";

		public static final String PARAMETAR_SIFRAPAR_MAIL_IB_ZAHTJEV = "MailIbZahtjev";

		public static final String NALOG_VRSTA_UPLATA_OROCENJA = "O";
		public static final String USLUGA_POTPIS_NALOGA = "POT_NAL";
		public static final String USLUGA_POTPIS_KUPOPRODAJA = "POT_KUPPRO";
		
		public static final String IB_IDENT_STATUS_AKTIVAN = "A";
		public static final String IB_FLAG_YES = "Y";

	}

	public static class CoreHub extends GenericBassxConstants.CoreHub {

	}

	public static class Avpo extends GenericBassxConstants.Avpo {
		public static final String MESSAGE_BUNDLE = "hr.abit.b3.biz.avpo.message.avpoMessages";
		public static final String MOZAD_PLACA = "67";
		public static final String MAX_FIRST_LIST_SIZE = "100";
		public static final Integer TIP_NALOGA_SIFRAPOD_FORMIRANJE_IZVODA = 168;
		public static final Integer TIP_OBAVIJESTI_IZVOD = 1;
		public static final String TIP_DODPOD_FORMA_AVPO = "PartijaAvpo";
		public static final Integer TIP_DODPOD_SIFRA_MJESTO_OPSLUZIVANJA = 50;

		public static final Integer TIP_DODPOD_SIFRA_KUNSKI_IZVOD = 32;
		public static final Integer TIP_DODPOD_SIFRA_DEVIZNI_IZVOD = 27;

		public static final String TIP_DODPOD_SIFRA_FAH = "FH";
		public static final String GDJEBPAR_BANKA = "B";
		public static final String GDJEBPAR_FINA = "F";
		public static final String VIPKOPAR_VIP_KOMITENT = "1";
		public static final String VIPKOPAR_NIJE_VIP_KOMITENT = "2";
		public static final Integer SIFRA_LIKVIDATORA_MQFINA = 993;
		public static final String SIFRA_POSLOVNICE_MQFINA = "993";

		public static final Integer TIP_NALOGA_SIFRAPOD_FINA_BEZGOTOVINSKI_NAPLATA_OSPOL = 79;
		public static final Integer TIP_NALOGA_SIFRAPOD_FINA_BEZGOTOVINSKI_INTERNI_ELEK = 90;
		public static final Integer TIP_NALOGA_SIFRAPOD_FINA_BEZGOTOVINSKI_INTERNI_ELEK_NAJAVA = 91;
		public static final Integer TIP_NALOGA_SIFRAPOD_FINA_BEZGOTOVINSKI_INTERNI_MM = 92;
		public static final Integer TIP_NALOGA_SIFRAPOD_FINA_BEZGOTOVINSKI_INTERNI_MM_NAJAVA = 93;
		public static final Integer TIP_NALOGA_SIFRAPOD_FINA_BEZGOTOVINSKI_INTERNI_PAPIR = 94;
		public static final Integer TIP_NALOGA_SIFRAPOD_FINA_BEZGOTOVINSKI_INTERNI_PAPIR_NAJAVA = 95;
		public static final Integer TIP_NALOGA_SIFRAPOD_FINA_GOTOVINA_UPLATA = 96;
		public static final Integer TIP_NALOGA_SIFRAPOD_FINA_GOTOVINA_UPLATA_DNT = 89;
		public static final Integer TIP_NALOGA_SIFRAPOD_FINA_GOTOVINA_UPLATA_OSTALO = 87;
		public static final Integer TIP_NALOGA_SIFRAPOD_FINA_GOTOVINA_UPLATA_605 = 85;
		public static final Integer TIP_NALOGA_SIFRAPOD_FINA_GOTOVINA_ISPLATA = 97;
		public static final Integer TIP_NALOGA_SIFRAPOD_FINA_UPIT_U_STANJE = 88;
		public static final Integer TIP_NALOGA_SIFRAPOD_FINA_BEZGOTOVINSKI_EKSTERNI_ELEK = 101;
		public static final Integer TIP_NALOGA_SIFRAPOD_FINA_BEZGOTOVINSKI_EKSTERNI_ELEK_NAJAVA = 100;
		public static final Integer TIP_NALOGA_SIFRAPOD_FINA_BEZGOTOVINSKI_EKSTERNI_MM = 105;
		public static final Integer TIP_NALOGA_SIFRAPOD_FINA_BEZGOTOVINSKI_EKSTERNI_MM_NAJAVA = 102;
		public static final Integer TIP_NALOGA_SIFRAPOD_FINA_BEZGOTOVINSKI_EKSTERNI_PAPIR = 103;
		public static final Integer TIP_NALOGA_SIFRAPOD_FINA_BEZGOTOVINSKI_EKSTERNI_PAPIR_NAJAVA = 104;
		public static final Integer TIP_NALOGA_SIFRAPOD_FINA_BEZGOTOVINSKI_BATCHBOOKING = 20507;
		public static final Integer TIP_NALOGA_SIFRAPOD_FINA_BEZGOTOVINSKI_BATCHBOOKING_NAJAVA = 20508;
		public static final Integer TIP_NALOGA_SIFRAPOD_PSD2_BATCHBOOKING = 20513;
		public static final Integer TIP_NALOGA_SIFRAPOD_PSD2_BATCHBOOKING_NAJAVA = 20514;
		public static final Integer TIP_NALOGA_KREIRANJE_OD_DATOTEKE = 10121;
		public static final Integer TIP_NALOGA_FAKTURA = 515;
		public static final Integer TIP_NALOGA_NAPLATA_FAKTURE = 20510;
		public static final Integer TIP_NALOGA_NAPLATA_FAKTURE_OBRADA = 20511;
		public static final Integer TIP_NALOGA_ZATVARANJE_PRETPLATA_FAKTURA = 20512;
		public static final Integer TIP_NALOGA_KUPOPRODJA = 20699;
		public static final Integer TIP_NALOGA_NAKNADA_ODRZAVANJE_MJESECNO = 7;
		public static final Integer TIP_NALOGA_NAKNADA_SMS_MJESECNA = 8;
		public static final Integer TIP_NALOGA_NAKNADA_PAKETA_BUSINESS = 16;
		public static final Integer TIP_NALOGA_NAKNADA_PAKETA_BUSINESS_PLUS = 17;
		public static final Integer TIP_NALOGA_NAKNADA_SMS_PRVI = 9000;
		public static final Integer TIP_NALOGA_NAKNADA_SMS_SLJEDECI = 9001;
		public static final Integer TIP_NALOGA_NAKNADA_FAKTURIRANJE_PARTIJE = 144;
		public static final Integer TIP_NALOGA_NAKNADA_FAKTURIRANJE_PARTIJE_B3 = 515;
		public static final Integer TIP_NALOGA_NAKNADA_MANIPULATIVNI = 80;
		public static final Integer TIP_NALOGA_NAKNADA_FAKTURIRANJE = 142;
		public static final Integer TIP_NALOGA_NAKNADA_FAKTURIRANJE_TS_REINIT = 20514;
		public static final Integer TIP_NALOGA_KAMATA_AVPO = 146;

		public static final Integer KLASA_NALOGA_SIFRAKNA_OBRACUN_FAKTURA = 1005;
		public static final Integer KLASA_NALOGA_SIFRAKNA_NAPLATA_FAKTURA = 50229;
		//		public static final Integer KLASA_NALOGA_SIFRAKNA_NAPLATA_NAKANDE_ANALITIKA = 1001;
		//		public static final Integer KLASA_NALOGA_SIFRAKNA_NAPLATA_FAKTURA_ANALITIKA = 50225;
		public static final Integer KLASA_NALOGA_SIFRAKNA_KUPOPRODJA = 50230;

		public static final Integer TIP_PARTIJE_OSNIVACKI_POLOG = 5;
		public static final Integer TIP_PARTIJE_IZDVOJENI_PO_NALOGU_SUDA = 6;
		public static final Integer TIP_PARTIJE_OSNIVACKI_POLOG_10 = 54;

		public static final String NADDIO_OZNKA_PROLAZNI_AVPO = "52";

		// public static final String TIP_DODPOD_SIFRA_ADRESA_DOMICILNA = "K1";
		// public static final String TIP_DODPOD_SIFRA_TELEFAX_DOMICILNA = "K2";
		// public static final String TIP_DODPOD_SIFRA_EMAIL_DOMICILNA = "K3";
		// public static final String TIP_DODPOD_EMAIL_POGRESNI_PRILJEVI = "99";
		public static final String DEFAULT_MODEL = "HR99";
		public static final int TIP_NAPLATE_FAKTURE_POTPUNI_IZNOS = 0;
		public static final int TIP_NAPLATE_FAKTURE_POTPUNI_IZNOS_AKO_MOGUCE = 1;
		public static final int TIP_NAPLATE_FAKTURE_DJELOMICNO = 2;
		public static final int TIP_NAPLATE_FAKTURE_POTPUNI_IZNOS_BEZ_PROVJERE = 3;
		public static final String SPECIFIKACIJAPOKRICA_NAKNADA = "sNak";
		public static final String SPECIFIKACIJAPOKRICA_TRANSKACIJA = "sTrn";
		public static final String SIFRAPAR_PUTANJA_POREZNA_UPLATE_ISPLATE = "CD_PorUIOut";
		public static final String SIFRAPAR_PUTANJA_FAKTURE_IZLAZNA_DATOTEKA_FINA_ARHIVA = "CD_FaktureOut";
		public static final String INTERNI_STATUS_PARTIJE_PREDSTECAJNA_NAGODBA = "PN";

		public static final int GRUPA_VEZE_SMS = 7;

		public static final String PARAMETAR_SIFRAPAR_NAPLATA_NAKNADE_ROK_REKLAMACIJE = "NNRekl";

		public static final String UVID_U_STANJE_REPORT_PROMETI = "PROMETI";
		public static final String UVID_U_STANJE_REPORT_OBRADENI_NALOZI = "OBRADENI_NALOZI";
		public static final String UVID_U_STANJE_REPORT_EKSTERNI_ODLJEVI_OBRADA = "EKSTERNI_ODLJEVI_OBRADA";
		public static final String UVID_U_STANJE_REPORT_INTERNI_ODLJEVI_OBRADA = "INTERNI_ODLJEVI_OBRADA";
		public static final String UVID_U_STANJE_REPORT_NALOZI_NAJAVA = "NALOZI_NAJAVA";
		public static final String UVID_U_STANJE_REPORT_NALOZI_PRIORITETNI = "NALOZI_PRIORITETNI";
		public static final String UVID_U_STANJE_REPORT_KARTICA_DUG = "KARTICA_DUG";
		public static final String UVID_U_STANJE_REPORT_AUTORIZACIJE = "AUTORIZACIJE";

		public static final String SIFRAKOB_IZVODI_KUNSKI_SALTER = "B0";
		public static final String SIFRAKOB_IZVODI_KUNSKI_POSTA = "B1";
		public static final String SIFRAKOB_IZVODI_KUNSKI_DISKETA = "B5";
		public static final String SIFRAKOB_IZVODI_KUNSKI_NE_PRIMA = "B8";
		public static final String SIFRAKOB_IZVODI_DEVIZNI_SALTER = "D0";
		public static final String SIFRAKOB_IZVODI_DEVIZNI_POSTA = "D1";
		public static final String SIFRAKOB_IZVODI_DEVIZNI_NE_PRIMA = "D8";

		public static final String SIFRAKOB_IZVODI_KUNSKI_PREFIX = "B%";
		public static final String SIFRAKOB_IZVODI_DEVIZNI_PREFIX = "D%";

		public static final String SIFRAKOB_IZVODI_KUNSKI_MAIL = "B9";
		public static final String SIFRAKOB_IZVODI_DEVIZNI_MAIL = "D9";
		public static final String SIFRAKOB_IZVODI_KUNSKI_FAX = "B2";
		public static final String SIFRAKOB_IZVODI_DEVIZNI_FAX = "D2";

		public static final String SIFRAKOB_IZVODI_FINA_PREFIX = "F%";
		public static final String SIFRAKOB_IZVODI_STANDARDNI = "F0";
		public static final String SIFRAKOB_IZVODI_STANDARDNI_I_DISKETA = "F1";
		public static final String SIFRAKOB_IZVODI_E_PLACANJE = "F2";
		public static final String SIFRAKOB_IZVODI_STANDARDNI_I_ONLINE = "F3";
		public static final String SIFRAKOB_IZVODI_SKRACENI = "F4";
		public static final String SIFRAKOB_IZVODI_DISKETA = "F5";
		public static final String SIFRAKOB_IZVODI_INFO_FINA = "F6";
		public static final String SIFRAKOB_IZVODI_ONLINE = "F7";
		public static final String SIFRAKOB_IZVODI_NE_PRIMA = "F8";
		public static final String SIFRAKOB_IZVODI_PAPIR_SKRACENI = "F9";
		public static final String SIFRAKOB_IZVODI_PAPIR_I_E_PLACANJE = "FA";
		public static final String SIFRAKOB_IZVODI_PAPIR_I_INFO_FINA = "FB";
		public static final String SIFRAKOB_IZVODI_E_PLACANJE_I_DATOTEKA = "FC";
		public static final String SIFRAKOB_IZVODI_INFO_FINA_I_DATOTEKA = "FD";
		public static final String SIFRAKOB_IZVODI_E_PLACANJE_I_FINA_ONLINE = "FE";
		public static final String SIFRAKOB_IZVODI_INFO_FINA_I_FINA_ONLINE = "FF";
		public static final String SIFRAKOB_IZVODI_E_PLACANJE_I_INFO_FINA = "FG";
		public static final String SIFRAKOB_IZVODI_CAMT_053 = "FH";
		public static final String SIFRAKOB_IZVODI_CAMT_053_I_PAPIR = "FI";
		public static final String SIFRAKOB_IZVODI_CAMT_053_I_DATOTEKA = "FJ";

		public static final Integer PAKET_BUSINESS = 1026;
		public static final Integer PAKET_BUSINESS_PLUS = 1022;

		//izvodi
		public static final String SIFRAVAL_HOME_CURRENCY = "191";
		public static final Integer SIFRA_LOK_STALNA_ADRESA = 1;
		public static final Integer SIFRA_LOK_DODATNA_ADRESA = 1001;
		public static final String LF_CR = "\r\n";
		public static final Integer S_IZVOD = 1;
		public static final Character STATUNAL_PROKNJIZEN = new Character('7');
		public static final Character STATUNAL_STORNIRAN = new Character('9');
		public static final Character STATUNAL_UPISAN = new Character('0');
		public static final Integer SIFRAPOD_PLATNIPROMET_FINA_IZVOD_DATOTEKA_TIP_451 = 14200;
		public static final Integer SIFRAPOD_PLATNIPROMET_FINA_IZVOD_DATOTEKA_TIP_251 = 14210;
		public static final Integer SIFRAPOD_SALTER_NAKNADA_MULTIVALUTNI_NAKNADNI_IZVOD = 14257;
		public static final String NAZIV_IZVOD_HOME_BANK = "PARTNER BANKA d.d. ZAGREB";
		public static final String OIBKOM_HOME_BANK = "71221608291";
		public static final Short PRIORNAL_DEFAULT = new Short("99");
		public static final String FINA_SJEDISTE_ONLINE_E_PLACANJE_INFO = "30100";
		public static final String SIFRAPAR_PUTANJA_FINA_IZVADAK_TIP_451 = "CD_FinaIzv451";
		public static final String SIFRAPAR_PUTANJA_FINA_IZVADAK_TIP_18 = "CD_FinaIzv18";
		public static final String PARAM_FAX_MAIL = "FaxMail";
		public static final String PO_CEMU_DATUM = "datum";
		public static final String PO_CEMU_BROJ = "broj";
		public static final String PARAM_CDFINA_OUT = "CD_FinaOut";


		public static final String NACINIZVRSENJA_NKS = "N";
		public static final String NACINIZVRSENJA_NKS_IBG = "G";
		public static final String NACINIZVRSENJA_HSVP = "H";
		public static final String NACINIZVRSENJA_HSVP_IBG = "J";
		public static final String NACINIZVRSENJA_INTERNI = "I";
		public static final String NACINIZVRSENJA_SWIFT = "S";
		public static final String NACINIZVRSENJA_DEVIZNO_INTERNI = "D";

		public static final Integer SIFRAPOD_SWIFT_OPCI_DEV_NALOG4000 = 4000;
		public static final Integer SIFRAPOD_PLATNIPROMET_STORNO = 9999;
		public static final Integer SIFRAPOD_PLATNIPROMET_TN_POVLACENJE_NAKNADE = 137;
		public static final Integer SIFRALIK_FINA_MQ_LIKVIDATOR = 993;
		public static final String drugaDecimalaTrnPo = "1,3,4,5,7,8";
		public static final String prvaDecimalaTrnPo = "1";


	}

	public static class PlatniPromet extends GenericBassxConstants.PlatniPromet {
		public static final String XSD_PAIN_001_PATH = "hr/abit/b3/biz/core-hub/sepa.hr.pain.001.001.03_07052015.xsd";
		public static final String MESSAGE_BUNDLE = "hr.abit.b3.biz.platni.message.platniMessages";
		public static final String ROCS_STATUS_DEL = "deleted";
		public static final String ROCS_IN = "ROCS_IN";
		public static final String PAIN_IN = "PAIN_IN";
		public static final String PAIN_TAYPE = "hr.abit.b3.biz.pain.dataObject.Document";
		public static final String RACUN_ZA_UPLATE_POREZA_PRIREZA = "HR1210010051700013000";
		// utilMap
		public static final String UTILMAP_KEY_ISKLJUCI_PROVJERA_BASIC = "checkBasic";
		public static final String UTILMAP_KEY_ISKLJUCI_PROVJERA_SVRHA_REGEXP = "checkSvrhaRegexp";
		public static final String UTILMAP_KEY_ISKLJUCI_PROVJERA_PLATNI = "checkPlatni";
		public static final String UTILMAP_KEY_ISKLJUCI_PROVJERA_BIC = "checkBic";
		public static final String UTILMAP_KEY_ISKLJUCI_PROVJERA_DRZAVA = "checkDrz";
		public static final String UTILMAP_KEY_ISKLJUCI_PROVJERA_SHA = "checkSha";
		public static final String UTILMAP_KEY_ISKLJUCI_PROVJERA_IPAY = "checkIpay";
		public static final String UTILMAP_KEY_ISKLJUCI_PROVJERA_PNB = "pnb";
		public static final String UTILMAP_KEY_ISKLJUCI_PROVJERA_PNB_ZAD = "pnb_zad";
		public static final String UTILMAP_KEY_ISKLJUCI_PROVJERA_PNB_ODO = "pnb_odo";
		public static final String UTILMAP_KEY_ISKLJUCI_PROVJERA_PRIHOD = "prihod";
		public static final String UTILMAP_KEY_ISKLJUCI_PROVJERA_SDA = "sda";
		public static final String UTILMAP_KEY_ISKLJUCI_ROOTING = "rooting";
		public static final String UTILMAP_KEY_ISKLJUCI_REROOTING = "rerooting";
		public static final String UTILMAP_KEY_ISKLJUCI_VRSTUTRANSKACIJE = "vrstatranskacije";
		public static final String UTILMAP_KEY_ISKLJUCI_KNJIZENJE = "knjizenje";
		public static final String UTILMAP_KEY_ISKLJUCI_KNJIZENJE_UPIS = "knjizenjeUpis";
		public static final String UTILMAP_KEY_ISKLJUCI_INKREMENT_STATUSA = "inkrementStatusa";
		public static final String UTILMAP_KEY_ISKLJUCI_HUB3 = "hub3Iskljuci";
		public static final String UTILMAP_KEY_ISKLJUCI_OBRACUNSKI_DATUM = "obracunskiDatum";
		public static final String UTILMAP_KEY_ISKLJUCI_AVPO_NALOG = "avpoNalog";
		public static final String UTILMAP_KEY_NKS_BANKA = "nksBanka";
		public static final String UTILMAP_KEY_RSAPOLOZIVO = "raspolozivo";
		public static final String UTILMAP_KEY_ISKLJUCI_OBRACUN_NAKNADE = "obrNak";
		public static final String UTILMAP_KEY_ISKLJUCI_FAKTURA = "fakt";
		public static final String UTILMAP_KEY_ISKLJUCI_PROVJERA_NACIZ = "naziz";
		public static final String UTILMAP_KEY_ISKLJUCI_AVPO_HUB3 = "hub3";
		public static final String UTILMAP_KEY_ISKLJUCI_FORM_DOP = "dop67";
		public static final String UTILMAP_KEY_ISKLJUCI_CHECK_UPLATA_NA_ZASTICENI = "chkZasticen";
		public static final String UTILMAP_KEY_PNOP = "pnop";
		public static final String UTILMAP_KEY_ISKLJUCI_NAPLATA_FAKTURA = "napFak";
		public static final String UTILMAP_KEY_ISKLJUCI_NAJAVA = "najava";
		public static final String UTILMAP_KEY_ISKLJUCI_PROVJERA_IBAN = "checkIban";
		public static final String UTILMAP_KEY_ISKLJUCI_POKRICE = "pkrice_avpo";
		public static final String UTILMAP_KEY_PP_POKRICE = "pp_pokrice";
		public static final String UTILMAP_KEY_PP_POKRICE_LIST = "ppPokriceList";
		public static final String UTILMAP_KEY_PP_POKRICE_NAK_LIST = "ppPokNakListNak";
		public static final String UTILMAP_KEY_PP_WARNINGS = "ppWarList";
		public static final String UTILMAP_KEY_KLON = "nalogKlon";

		public static final int SIFRA_PARTIJE_BANKE = CoreHub.SIFRA_RACUNA_BANKE;

		public static final String PREFIX_MODEL_PLACANJA_HR = "HR";
		public static final String MODELPNB_MODEL_00 = "00";
		public static final String MODELPNB_MODEL_99 = "99";

		// način izvršenja
		public static final String NACIN_IZVRSENJA_INTERNI_NALOG = GenericBassxConstants.PlatniPromet.NACIN_IZVRSENJA_INTERNI_NALOG;
		public static final String NACIN_IZVRSENJA_DEVIZNI_INTERNI_NALOG = GenericBassxConstants.PlatniPromet.NACIN_IZVRSENJA_DEVIZNI_INTERNI_NALOG;
		public static final String NACIN_IZVRSENJA_SWIFT_NALOG = GenericBassxConstants.PlatniPromet.NACIN_IZVRSENJA_SWIFT_NALOG;
		public static final String NACIN_IZVRSENJA_HSVP = GenericBassxConstants.PlatniPromet.NACIN_IZVRSENJA_HSVP;
		public static final String NACIN_IZVRSENJA_HRK_NKS = GenericBassxConstants.PlatniPromet.NACIN_IZVRSENJA_HRK_NKS;
		public static final String NACIN_IZVRSENJA_EUR_NKS = GenericBassxConstants.PlatniPromet.NACIN_IZVRSENJA_EUR_NKS;
		public static final String NACIN_IZVRSENJA_TARGET2 = GenericBassxConstants.PlatniPromet.NACIN_IZVRSENJA_TARGET2;
        public static final String NACIN_IZVRSENJA_INSTANT = GenericBassxConstants.PlatniPromet.NACIN_IZVRSENJA_INSTANTPAY;
		public static final List<String> NACIN_IZVRSENJA_LIST_S_MOGUCIM_POVRATOM_NALOGA = Arrays.asList(NACIN_IZVRSENJA_HSVP, NACIN_IZVRSENJA_HRK_NKS, NACIN_IZVRSENJA_EUR_NKS, NACIN_IZVRSENJA_TARGET2);
		public static final List<String> NACIN_IZVRSENJA_LIST_OUR_TROSAK_IB = Arrays.asList(NACIN_IZVRSENJA_TARGET2, NACIN_IZVRSENJA_SWIFT_NALOG);
		public static final String PARAM_IZVRSENJA_INTERNI_NALOG_SUBOTA = "IS";
		// public static final String NACIN_IZVRSENJA_DEVIZNA_PENZIJA = "P";
		// public static final String NACIN_IZVRSENJA_SWIFT_NALOG = "S";
		// public static final String NACIN_IZVRSENJA_GC_BRANCH = "B";
		// public static final String NACIN_IZVRSENJA_GC_CENTRE = "C";

		// Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_NKS +"~" +
		// Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_INTERNI_NALOG+"~"+Bassx2Constants.PlatniPromet.NACIN_IZVRSENJA_HSVP;
		//

		// public static final boolean SUBOTA_RADNA = true;
		public static final Map<String, String> VALUTA_OZNAKA_SIFRA = Collections
				.unmodifiableMap(new HashMap<String, String>() {
					{
						put("NOK", "578");
						put("HUF", "348");
						put("CHF", "756");
						put("CAD", "124");
						put("DKK", "208");
						put("JPY", "392");
						put("CZK", "203");
						put("GBP", "826");
						put("SEK", "752");
						put("EUR", "978");
						put("AUD", "036");
						put("USD", "840");
						put("PLN", "985");
						put("BAM", "977");
						put("HRK", "191");
					}
				});
		public static final List<String> ListCurrencyWithoutTrailingZeros = Arrays.asList(Core.VALUTA_SIFRAVAL_JPY);
		public static final String NKSBANKA_VBDINBAN_BANKA = CoreHub.VBDI_BANKE;
		public static final String VBDI_HNB = "1001005";
		public static final String VBDINBAN_VBDI_SDA = VBDI_HNB;
		public static final String IBAN_SDA_PROLAZNI_VRIJEDNOSNI_PAPIRI = "HR0810010051310020013"; // žiro račun središnje depozitne agencije za rad s vrijednosnim papirima
		public static final String NAZIV_SDA_PROLAZNI_VRIJEDNOSNI_PAPIRI = "SREDISNJE KLIRINSKO DEPOZITARNO DRUSTVO D.D.";
		public static final String BROJ_RACUNA_NKS_FORMAT_SDA_PROLAZNI_VRIJEDNOSNI_PAPIRI = "1310020013";
		public static final String IBAN_SDA_GLAVNI = "HR6210010051011111116";
		public static final String BROJ_RACUNA_NKS_FORMAT_SDA_GLAVNI = "1011111116";
		public static final String BROJ_RACUNA_OBVEZNA_PRIČUVA = "2525000174";
		public static final String IBAN_OBVEZNA_PRIČUVA = "HR3124080022525000174";
		public static final String NAZIV_OBVEZNA_PRIČUVA = "PARTNER BANKA - OBVEZNA PRIČUVA";
		public static final String IBAN_EVIDENCIJE_GOTOVOG_NOVCA = "HR6724080020100000011";
		public static final String BROJ_RACUNA_NKS_FORMAT_EVIDENCIJE_GOTOVOG_NOVCA = "0100000011";
		public static final String IBAN_RACUNA_BANKE_ZA_GOTOVINU_U_HNB = "HR8710010050224080020";
		public static final String BROJ_RACUNA_NKS_FORMAT_ZA_GOTOVINU_U_HNB = "0224080020";
		public static final String GRUPA_RACUNA_17 = "17";
		public static final String GRUPA_RACUNA_18 = "18";
		public static final String GRUPE_RACUNA_PARTIJA_PLATNI = "11,13,14,15,17,18,19";
		public static final String GRUPE_RACUNA_PARTIJA_RETAIL = "31,32,35,36,33";
		public static final String GRUPA_RACUNA_35 = "35";
		public static final String GRUPA_RACUNA_75 = "75";
		public static final String GRUPA_RACUNA_85 = "85";
		public static final String GRUPA_RACUNA_86 = "86";
		public static final String DRZAVA_HOME_OZNKATRI = "HRV";
		public static final String DRZAVA_HOME_OZNKADVA = "HR";

		public static final String POSLOVNICA_PLATNOG_PROMETA = "130";
		public static final String POSLOVNICA_RACUNOVODSTVO = "030";
		public static final String POSLOVNICA_RIZNICA = "250";
		public static final String POSLOVNICA_PSD2 = "994";

		public static final String POZIV_NA_BROJ_IZDVAJANJE_GOTOVINE_ZADUZENJE = "1680-" + POSLOVNICA_PLATNOG_PROMETA + "-" + BROJ_RACUNA_NKS_FORMAT_EVIDENCIJE_GOTOVOG_NOVCA;
		public static final String POZIV_NA_BROJ_IZDVAJANJE_GOTOVINE_ODOBRENJE = "1000-" + POSLOVNICA_RACUNOVODSTVO + "-" + BROJ_RACUNA_NKS_FORMAT_ZA_GOTOVINU_U_HNB;

		public static final Integer NKSBANKA_ISNKS = 1;
		public static final int DUZINA_RAC = 10;
		public static final short DEFAULT_PRIORITET = 90;
		public static final short DEFAULT_PRIORITET_MQFINA = 19;
		public static final short DEFAULT_PRIORITET_IZDVAJANJE_SREDSTVA = 19;
		// public static final int DUZINA_RAC_KREMIG = 10;
		public static final String IDENT_DELIMITER = "~";

		public static final String SIFRA_SEKTORA_NEREZIDENT_START = "9";
		// public static final String RACUN_NERASPOZNATI_PRILJEVI =
		// "1029990100000073";
		// public static final String RACUN_BANKE_NERASPOZNATO =
		// RACUN_NERASPOZNATI_PRILJEVI;
		// public static final String RACUN_POGRESNI_PRILJEVI =
		// "1029990100000170";
		public static final Integer DEVIZNI_INSTRUMENT_SIFRAINS_DOZNAKA = 10;
		// public static final Integer
		// DEVIZNI_INSTRUMENT_SIFRAINS_STRANA_GOTOVINA = 13;
		// public static final Integer DEVIZNI_INSTRUMENT_SIFRAINS_KUNE_GOTOVINA
		// = 14;
		// public static final Integer
		// DEVIZNI_INSTRUMENT_SIFRAINS_AKREDITIV_KORISTENJE = 72;
		public static final String DEVIZNI_NALOG_SIFRA_STRANCA_PRAVNI = "1";
		public static final String DEVIZNI_NALOG_SIFRA_STRANCA_FIZICKI = "2";
		public static final String DEVIZNI_NALOG_TROSAK_VANJSKOG_POSLA_OSNOVNI_POSAO = "1";
		public static final String DEVIZNI_NALOG_TROSAK_VANJSKOG_POSLA_ZATEZNA_KAMATA_PENALI = "2";
		public static final String DEVIZNI_NALOG_TROSAK_INO_BANKE_OUR = "OUR";
		public static final String DEVIZNI_NALOG_TROSAK_INO_BANKE_BEN = "BEN";
		public static final String DEVIZNI_NALOG_TROSAK_INO_BANKE_SHA = "SHA";
		public static final String FINA_NALOG_TROSAK_INO_BANKE_OUR = "1";
		public static final String FINA_NALOG_TROSAK_INO_BANKE_BEN = "2";
		public static final String FINA_NALOG_TROSAK_INO_BANKE_SHA = "3";
		public static final Integer PLATNI_NALOG_HITNOST_REDOVNO = GenericBassxConstants.PlatniPromet.PLATNI_NALOG_HITNOST_REDOVNO;
		public static final Integer PLATNI_NALOG_HITNOST_HITNO = GenericBassxConstants.PlatniPromet.PLATNI_NALOG_HITNOST_HITNO;
        public static final Integer PLATNI_NALOG_HITNOST_INSTANT = GenericBassxConstants.PlatniPromet.PLATNI_NALOG_HITNOST_INSTANT;
		// //Status
		// public static final String STATUS_TABLE_PARTIJA = "PartijaPlatni";
		// public static final String STATUS_FIELD_TIPRAC = "tipraPar";
		// public static final String TIP_DODPOD_FORMA_UPP ="partijaPlatni";
		// public static final String UVJET_KLASA_PARTIJE = "2','4','5";
		// public static final BigDecimal DEFAULT_LIMIT = new
		// BigDecimal("250000");
		// public static final BigDecimal GC_LIMIT = new BigDecimal("10000");
		public static final BigDecimal RETYPE_LIMIT = new BigDecimal("5000");
		// public static final String MEDIJ_DATOTEKA = "Q";
		// public static final String MEDIJ_PRAZNJENJE =
		// Bassx2Constants.PlatniPromet.MEDIJ_DATOTEKA ;
		// public static final String MinFinMatBR = "4200575050003";
		// public static final String FZZOMatBR = "4201117120007";
		public static final String PARTIJA_FAKTURE = "FAKTURA";
		// // šifra osnove i bla bal
		public static final String SIFRA_OSNOVE_GRUPA_DOZNAKE = "D";
		public static final String SIFRA_OSNOVE_GRUPA_NAPLATE = "N";
		public static final String SIFRA_OSNOVE_GRUPA_NEUTRALNI = "O";
		// public static final String RACUN_DISTRIKT_BRCKO = "0000080000001161";
		// public static final String SIFRA_SVRHE_KOMPENZACIJA = "999";
		// //proizvod - posaopro
		public static final String PROIZVOD_POSAOPRO_UPP = "UPP";
		// //
		public static final String ODLJEV = "O";
		public static final String PRILJEV = "P";
		public static final String EKSTERNI = "E";
		public static final String INTERNI = "I";
		public static final String GOTOVINA = "G";
		public static final String BEZGOTOVINA = "B";
		public static final String NAJAVA = "N";
		public static final String PLATNI_NALOG_MEDIJ_ELEKTRONSKO_BANKARSTVO = "E";
		public static final String PLATNI_NALOG_MEDIJ_TRAJNI_NALOG = "T";
		public static final String PLATNI_NALOG_MEDIJ_PAPIR = "P";
		public static final String PLATNI_NALOG_AUTOMATSKA_OBRADA = "A";
		public static final String PLATNI_NALOG_MEDIJ_MAGNETNI = "M";
		public static final String PLATNI_NALOG_MEDIJ_PISP = "Q";
		public static final String PLATNI_NALOG_MEDIJ_MB = "C";
		// public static final Integer NALOG_UPP_ZAHTJEV_PETI_PRIMJERAK = 1;
		// // naddio
		// public static final String NADDIO_OZNKANAD_CAS_RACUN = "204";
		// // klase naloga
		public static final String KLASA_NALOGA_GRUPA_PREGLED_PLATNIH_NALOGA = "'klasaNalogaPPN'";
		public static final String KLASA_NALOGA_GRUPA_OUT = "OUT";
		public static final String KLASA_NALOGA_GRUPA_IN = "IN";
		public static final String KLASA_NALOGA_GRUPA_VERIF = "VRF";
		public static final Integer KLASA_NALOGA_SIFRAKNA_DEVIZNO_TRZISTE_PRAVNE_OSOBE = 140;
		public static final Integer KLASA_NALOGA_SIFRAKNA_DEVIZNO_TRZISTE_FIZICKE_OSOBE = 141;
		public static final Integer KLASA_NALOGA_SIFRAKNA_DEVIZNI_PRILJEV_NERASPOREDEN = 1200;
		public static final Integer KLASA_NALOGA_SIFRAKNA_DEVIZNI_PRILJEV_RASPOREDEN = 1210;
		public static final Integer KLASA_NALOGA_SIFRAKNA_POVRAT_SLUZBENOG_PUTA = 1240;
		public static final Integer KLASA_NALOGA_SIFRAKNA_DEVIZNI_PRILJEV_KAPITALNI_POSAO_PO = 1250;
		public static final Integer KLASA_NALOGA_SIFRAKNA_RASPORED_DEVIZNOG_PRILJEVA_INTERNI = 1280;
		public static final Integer KLASA_NALOGA_SIFRAKNA_RASPORED_DEVIZNOG_PRILJEVA_BEZ_CVRSTOG_RN_INTERNI = 1281;
		public static final Integer KLASA_NALOGA_SIFRAKNA_RASPORED_DEVIZNOG_PRILJEVA_EKSTERNI = 1285;
		public static final Integer KLASA_NALOGA_SIFRAKNA_RASPORED_DEVIZNOG_PRILJEVA_BEZ_CVRSTOG_RN_EKSTERNI = 1286;
		public static final Integer KLASA_NALOGA_SIFRAKNA_DEVIZNI_PRILJEV_NERASPOREDEN_RETAIL = 1260;
		public static final Integer KLASA_NALOGA_SIFRAKNA_DEVIZNI_PRILJEV_RASPOREDEN_RETAIL = 1270;
		public static final Integer KLASA_NALOGA_SIFRAKNA_DEVIZNI_PRILJEV_NNACIONALNI_PREKOGRANICNI_RETAIL = 1221;
		public static final Integer KLASA_NALOGA_SIFRAKNA_DEVIZNI_PRILJEV_KAPITALNI_POSAO_FO = 1290;
		public static final Integer KLASA_NALOGA_SIFRAKNA_POKRICE_DEVIZNOG_NALOGA = 1490;
		public static final Integer KLASA_NALOGA_SIFRAKNA_POKRICE_NAKANDE_DEVIZNOG_NALOGA = 1499;
		public static final Integer KLASA_NALOGA_SIFRAKNA_NAPLATA_NAKANDE_CVRSTI_RACUN = 1800;
		public static final Integer KLASA_NALOGA_SIFRAKNA_NAPLATA_NAKANDE_PROLAZNI_RACUN_071 = 1801;
		public static final Integer KLASA_NALOGA_SIFRAKNA_NAPLATA_NAKANDE_KAPITALNI_POSAO = 1802;
		public static final Integer KLASA_NALOGA_SIFRAKNA_NAPLATA_NAKANDE_CVRSTI_RACUN_IRA = 1803;
		public static final Integer KLASA_NALOGA_SIFRAKNA_NAPLATA_NAKANDE_PROLAZNI_RACUN_071_IRA = 1804;
		public static final Integer KLASA_NALOGA_SIFRAKNA_NAPLATA_NAKANDE_KAPITALNI_POSAO_IRA = 1805;
		public static final Integer KLASA_NALOGA_SIFRAKNA_NAPLATA_NAKANDE_CVRSTI_RACUN_SWIFT = 1810;
		public static final Integer KLASA_NALOGA_SIFRAKNA_NAPLATA_NAKANDE_PROLAZNI_RACUN_071_SWIFT = 1811;
		public static final Integer KLASA_NALOGA_SIFRAKNA_PLASMAN_UPLATA_KREDIT = 4999;
		public static final String KLASA_NALOGA_GRUPA_IZDVAJANJE_SREDSTVA = "IZDVSRED";
		public static final Integer KLASA_NALOGA_SIFRAPOD_IZDVAJANJE_NKS = 1900;
		public static final Integer KLASA_NALOGA_SIFRAPOD_IZDVAJANJE_GOTOVINE_FINA = 1901;
		public static final Integer KLASA_NALOGA_SIFRAPOD_IZDVAJANJE_PREKONOCNI_DEPOZIT_HNB = 1902;
		public static final Integer KLASA_NALOGA_SIFRAPOD_IZDVAJANJE_EURO_NKS = 1903;
		public static final Integer KLASA_NALOGA_SIFRAPOD_IZDVAJANJE_OBVEZNE_PRICUVE = 1904;
		public static final Integer KLASA_NALOGA_SIFRAPOD_IZDVAJANJE_NKS_INSTANT = 1905;
		public static final Integer KLASA_NALOGA_SIFRAPOD_IZDVAJANJE_SKDD = 1906;
		public static final Integer TIP_NALOGA_SIFRAPOD_IZDVAJANJE_NKS = 19000;
		public static final Integer TIP_NALOGA_SIFRAPOD_IZDVAJANJE_GOTOVINE_FINA = 19010;
		public static final Integer TIP_NALOGA_SIFRAPOD_IZDVAJANJE_PREKONOCNI_DEPOZIT_HNB = 19020;
		public static final Integer TIP_NALOGA_SIFRAPOD_IZDVAJANJE_EURO_NKS = 19030;
		public static final Integer TIP_NALOGA_SIFRAPOD_IZDVAJANJE_OBVEZNE_PRICUVE = 19040;
		public static final Integer TIP_NALOGA_SIFRAPOD_IZDVAJANJE_NKS_INSTANT = 19050;
		public static final Integer TIP_NALOGA_SIFRAPOD_IZDVAJANJE_SKDD = 19060;

		public static final Integer KLASA_NALOGA_SWIFT_KNJIZENJE_ZATVARANJA_IZVODA = 1610;
		// public static final Integer KLASA_NALOGA_SIFRAKNA_ANA_UPLATA = 37;
		public static final String KLASA_NALOGA_GRUPA_OPCI_DEVIZNI_NALOG = "KNODN";
		public static final String KLASA_NALOGA_GRUPA_DEVIZNI_PRILJEV = "KNDP10";
		public static final String KLASA_NALOGA_GRUPA_RASPORED_DEVIZNOG_PRILJEVA_INTERNI = "KNRPI";
		public static final String KLASA_NALOGA_GRUPA_RASPORED_DEVIZNOG_PRILJEVA_EKSTERNI = "KNRPE";
		public static final String KLASA_NALOGA_GRUPA_DEVIZNI_ODLJEV_PRAVNA_LICA = "KNDO14";
		public static final String KLASA_NALOGA_GRUPA_RASKNJIZENJE_IZVODA_INTERNI = "KNRIBI";
		public static final String KLASA_NALOGA_GRUPA_RASKNJIZENJE_IZVODA_EKSTERNI = "KNRIZE";
		public static final String KLASA_NALOGA_GRUPA_SEPA_EKSTERNI_ODOLAZNI = "VRF_OUT";
		public static final String KLASA_NALOGA_GRUPA_DEVIZNI_ODLJEV_ISPLATA_SLUZBENOG_PUTA = "SLPT";
		public static final String KLASA_NALOGA_GRUPA_DEVIZNI_ODLJEV_POVRAT_SLUZBENOG_PUTA = "PSLP";
		public static final String KLASA_NALOGA_GRUPA_DEVIZNI_PRILJEV_POLOG_DEVIZNI_RACUN = "POLDR";
		public static final String KLASA_NALOGA_GRUPA_DEVIZNI_ODLJEV_FIZICKA_LICA = "KNDO14R";
		public static final String KLASA_NALOGA_GRUPA_SWIFT_ODLAZNE_PORUKE = "SWI";
		// public static final String KLASA_NALOGA_GRUPA_KUPOPRODAJA = "KNKD";
		// public static final String KLASA_NALOGA_GRUPA_UPP_ODLJEV = "UPPO";
		public static final String KLASA_NALOGA_GRUPA_POKRICE_DEVIZNOG_NALOGA = "KNPOK";
		public static final String KLASA_NALOGA_GRUPA_POKRICE_DEVIZNE_NAKNADE = "KNNPK";
		// public static final Integer
		// KLASA_NALOGA_SIFRAKNA_UPP_POVRAT_POGRESNIH_PRILJEVA = 85;
		public static final Integer KLASA_NALOGA_SIFRAPOD_POLOG_NA_DEVIZNI_RACUN_NERASPOREDEN = 1241;
		public static final Integer KLASA_NALOGA_SIFRAPOD_POLOG_NA_DEVIZNI_RACUN_RASPOREDEN = 1242;
		// public static final Integer KLASA_NALOGA_SIFRAKNA_FAKTURA = 52;
		// target 2
		public static final Integer KLASA_NALOGA_SIFRAKNA_TARGET2_PRILJEV = 300;
		public static final Integer KLASA_NALOGA_SIFRAKNA_INSTAPAY_PRILJEV = 400;
		public static final Integer KLASA_NALOGA_SIFRAKNA_INSTAPAY_004_ODLJEV = 403;

		public static final Integer KLASA_NALOGA_SIFRAKNA_TARGET2_PRILJEV_ODBIJEN = 301;
		public static final Integer KLASA_NALOGA_SIFRAKNA_TARGET2_PRILJEV_ODBIJEN_POVRAT = 302;
		public static final Integer KLASA_NALOGA_SIFRAKNA_TARGET2_PRILJEV_KAPITALNI_POSAO = 305;
		public static final Integer KLASA_NALOGA_SIFRAKNA_TGT_EURONKS_DEBIT_900 = 1960;
		public static final Integer KLASA_NALOGA_SIFRAKNA_TGT_EURONKS_CREDIT_910 = 1961;
		public static final String KLASA_NALOGA_GRUPAKNA_TARGET2_PRILJEV = "KNT2P";
		public static final String KLASA_NALOGA_GRUPAKNA_RETAIL_IB = "RIB_OLD";
		public static final Integer TIP_NALOGA_SIFRAPOD_TARGET2_PRILJEV_ODBIJEN = 30001;
		public static final Integer TIP_NALOGA_SIFRAPOD_TARGET2_PRILJEV_ODBIJEN_POVRAT = 30002;
		public static final Integer TIP_NALOGA_SIFRAKNA_TGT_EURONKS_DEBIT_900 = 1960;
		public static final Integer TIP_NALOGA_SIFRAKNA_TGT_EURONKS_CREDIT_910 = 1961;
		public static final String FIELD_25_TGT_INDIKATOR = "HRPPAZG" + PlatniPromet.NKSBANKA_VBDINBAN_BANKA;
		public static final String FIELD_25_TGT_INDIKATOR_ODREDISTE = "HRSPAZG" + PlatniPromet.NKSBANKA_VBDINBAN_BANKA;
		//
		public static final Integer KLASA_NALOGA_SIFRAKNA_INSTAPAY_PRILJEV_ODBIJEN = 401;
		public static final Integer TIP_NALOGA_SIFRAPOD_INSTAPAY_PRILJEV_ODBIJEN = 4900;
		// HSVP
		public static final Integer KLASA_NALOGA_SIFRAKNA_HSVP_PRILJEV = 310;
		public static final Integer KLASA_NALOGA_SIFRAKNA_HSVP_PRILJEV_ODBIJEN = 311;
		public static final Integer KLASA_NALOGA_SIFRAKNA_HSVP_PRILJEV_ODBIJEN_POVRAT = 312;
		public static final Integer KLASA_NALOGA_SIFRAKNA_HSVP_NKS_POZICIJA = 1950;
		public static final Integer KLASA_NALOGA_SIFRAKNA_HSVP_NKS_POVRAT_LIMITA = 1951;
		public static final Integer KLASA_NALOGA_SIFRAKNA_HSVP_NKS_POVRAT_POZICIJE = 1952;
		public static final String KLASA_NALOGA_GRUPAKNA_HSVP_PRILJEV = "KNHSVPP";
		public static final Integer TIP_NALOGA_SIFRAPOD_HSVP_PRILJEV_ODBIJEN = 30101;
		public static final Integer TIP_NALOGA_SIFRAPOD_HSVP_PRILJEV_ODBIJEN_POVRAT = 30102;
		public static final Integer TIP_NALOGA_SIFRAPOD_HSVP_NKS_POZICIJA = 1950;
		public static final Integer TIP_NALOGA_SIFRAPOD_HSVP_NKS_POVRAT_LIMITA = 1951;
		public static final Integer TIP_NALOGA_SIFRAPOD_HSVP_NKS_POVRAT_POZICIJE = 1952;
		// SEPA NKS
		public static final String KLASA_NALOGA_GRUPAKNA_NKS_PRILJEV = "KNNKS";
		public static final Integer KLASA_NALOGA_SIFRAKNA_NKS_PRILJEV_GLAVNICA = 50208;
		public static final Integer KLASA_NALOGA_SIFRAKNA_NKS_PRILJEV_DIO_ZA_UPLATE = 50210;
		public static final Integer KLASA_NALOGA_SIFRAKNA_NKS_PRILJEV_NERASPOZNATO = 50212;
		public static final Integer KLASA_NALOGA_SIFRAKNA_NKS_POVRAT = 50220;
		public static final Integer TIP_NALOGA_SIFRAPOD_NKS_PRILJEV_GLAVNICA_PACS008_PRAVNA_OSOBA = 50208;
		public static final Integer TIP_NALOGA_SIFRAPOD_NKS_PRILJEV_DIO_ZA_UPLATE_PACS008 = 50210;
		public static final Integer TIP_NALOGA_SIFRAPOD_NKS_PRILJEV_NERASPOZNATO_PACS008 = 50212;
		public static final Integer TIP_NALOGA_SIFRAPOD_NKS_PRILJEV_GLAVNICA_PACS004 = 50214;
		public static final Integer TIP_NALOGA_SIFRAPOD_NKS_PRILJEV_DIO_ZA_UPLATE_PACS004 = 50216;
		public static final Integer TIP_NALOGA_SIFRAPOD_NKS_PRILJEV_NERASPOZNATO_PACS004 = 50218;
		public static final Integer TIP_NALOGA_SIFRAPOD_NKS_POVRAT = 50220;
		public static final Integer TIP_NALOGA_SIFRAPOD_NKS_PRILJEV_GLAVNICA_PACS008_FIZICKA_OSOBA = 50222;

		public static final String TIP_NALOGA_IDENTIFIKATOR_OPCI_DEVIZNI_NALOG = "DEV_TPL";

		// SEPA EURO-NKS
		public static final String KLASA_NALOGA_GRUPAKNA_EURONKS_PRILJEV = "KNEURNKS";
		public static final Integer KLASA_NALOGA_SIFRAKNA_EURONKS_PRILJEV_GLAVNICA = 50207;
		public static final Integer KLASA_NALOGA_SIFRAKNA_EURONKS_PRILJEV_DIO_ZA_UPLATE = 50209;
		public static final Integer KLASA_NALOGA_SIFRAKNA_EURONKS_PRILJEV_NERASPOZNATO = 50211;
		public static final Integer KLASA_NALOGA_SIFRAKNA_EURONKS_POVRAT = 50219;
		public static final Integer TIP_NALOGA_EURONKS_PRILJEV_GLAVNICA_PO_NACIONALNI = 50231;
		public static final Integer TIP_NALOGA_EURONKS_PRILJEV_GLAVNICA_PO_PREKOGRANICNI = 50232;
		public static final Integer TIP_NALOGA_EURONKS_PRILJEV_GLAVNICA_PO_MEDJUNARODNI = 50233;
		public static final Integer TIP_NALOGA_SIFRAPOD_EURONKS_PRILJEV_DIO_ZA_UPLATE_PACS008 = 50209;
		public static final Integer TIP_NALOGA_SIFRAPOD_EURONKS_PRILJEV_NERASPOZNATO_PACS008 = 50211;
		public static final Integer TIP_NALOGA_SIFRAPOD_EURONKS_PRILJEV_GLAVNICA_PACS004 = 50213;
		public static final Integer TIP_NALOGA_SIFRAPOD_EURONKS_PRILJEV_DIO_ZA_UPLATE_PACS004 = 50215;
		public static final Integer TIP_NALOGA_SIFRAPOD_EURONKS_PRILJEV_NERASPOZNATO_PACS004 = 50217;
		public static final Integer TIP_NALOGA_EURONKS_POVRAT = 50219;
		public static final Integer TIP_NALOGA_EURONKS_PRILJEV_GLAVNICA_FO_NACIONALNI = 50234;
		public static final Integer TIP_NALOGA_EURONKS_PRILJEV_GLAVNICA_FO_PREKOGRANICNI = 50235;
		public static final Integer TIP_NALOGA_EURONKS_PRILJEV_GLAVNICA_FO_MEDJUNARODNI = 50236;

		// SEPA NAKNADA PRILJEVI
		public static final Integer KLASA_NALOGA_SIFRAKNA_SEPA_NAPLATA_NAKNADE = 50226;
		public static final Integer TIP_NALOGA_SIFRAPOD_EURONKS_NAKNADA_OBRACUN = 50801;
		public static final Integer TIP_NALOGA_SIFRAPOD_EURONKS_NAKNADA_NAPLATA = 50901;
		public static final Integer TIP_NALOGA_SIFRAPOD_NKS_NAKNADA_OBRACUN = 50802;
		public static final Integer TIP_NALOGA_SIFRAPOD_NKS_NAKNADA_NAPLATA = 50902;
		public static final Integer TIP_NALOGA_OBRADA_NALOGA_NAJAVI = 50652;

		// // RAČUN BANKE - rasknjiženja
		// public static final Integer
		// KLASA_NALOGA_SIFRAKNA_NERASPOZNATI_NA_GLAVNICU = 30001;
		// public static final Integer
		// KLASA_NALOGA_SIFRAKNA_NERASPOZNATI_NA_DIO_ZA_UPLATE = 30002;
		// public static final Integer
		// KLASA_NALOGA_SIFRAKNA_NERASPOZNATI_POVRAT_GC = 30003;
		// public static final Integer
		// KLASA_NALOGA_SIFRAKNA_NERASPOZNATI_POVRAT_RTGS = 30004;
		// // tip naloga
		// //KAMATA
		// public static final String TIP_OBRACUNA_KAMATE_NEDOSPJELA = "0";
		// public static final String TIP_OBRACUNA_KAMATE_DOSPJELA_SPECUVJETI =
		// "1";
		// public static final String TIP_OBRACUNA_KAMATE_MINFIN = "2";
		// // tip specifikacije pokrića/rasporeda
		public static final Integer TIP_NALOGA_XML_ROCS = 20700;
		public static final Integer TIP_NALOGA_XML_PAIN = 20701;

		// public static final String TIP_SPECIFIKACIJE_RASPORED_NAPLATE = "P";
		// public static final String TIP_SPECIFIKACIJE_POKRICE_DOZNAKE = "O";
		public static final Integer TIP_NALOGA_MIGRACIJA_RIZNICA = 2;
		// public static final Integer TIP_NALOGA_MIGRACIJA_UPP = 4;
		public static final Integer TIP_NALOGA_SIFRAPOD_FORMIRANJE_IZVODA = 168;
		// public static final Integer TIP_NALOGA_MIGRACIJA_IPP = 5;
		// public static final Integer TIP_NALOGA_MIGRACIJA_POGONI = 10;
		// public static final Integer
		// TIP_NALOGA_MIGRACIJA_POTRAZIVANJA_NAKNADE_ZATVORENE_PP = 12;
		// public static final Integer
		// TIP_NALOGA_MIGRACIJA_POTRAZIVANJA_NAKNADE_OTVORENE_PP = 13;
		// public static final Integer TIP_NALOGA_MIGRACIJA_DEVIZNI_IZVOD = 14;
		// public static final Integer TIP_NALOGA_REZERVACIJA_OTVARANJE = 700;
		// public static final Integer TIP_NALOGA_REZERVACIJA_POVLACENJE = 708;
		// public static final Integer TIP_NALOGA_UCITAVANJE_CRR_DATOTEKE = 701;
		// public static final Integer TIP_NALOGA_FORMIRANJE_CRR_DATOTEKE = 702;
		// public static final Integer TIP_NALOGA_FORMIRANJE_CRR_FL_DATOTEKE =
		// 707;
		// public static final Integer TIP_NALOGA_ZATVARANJE_PARTIJE = 703;
		public static final Integer TIP_NALOGA_SIFRAPOD_NAPLATA_NAKANDE_CVRSTI_RACUN = 18000;
		public static final Integer TIP_NALOGA_SIFRAPOD_NAPLATA_NAKANDE_PROLAZNI_RACUN_071 = 18001;
		public static final Integer TIP_NALOGA_SIFRAPOD_NAPLATA_NAKANDE_KAPITALNI_POSAO = 18002;
		public static final Integer TIP_NALOGA_SIFRAPOD_NAPLATA_NAKANDE_CVRSTI_RACUN_IRA = 18003;
		public static final Integer TIP_NALOGA_SIFRAPOD_NAPLATA_NAKANDE_PROLAZNI_RACUN_071_IRA = 18004;
		public static final Integer TIP_NALOGA_SIFRAPOD_NAPLATA_NAKANDE_KAPITALNI_POSAO_IRA = 18005;
		public static final Integer TIP_NALOGA_SIFRAPOD_NAPLATA_NAKANDE_CVRSTI_RACUN_SWIFT = 18010;
		public static final Integer TIP_NALOGA_SIFRAPOD_NAPLATA_NAKANDE_PROLAZNI_RACUN_071_SWIFT = 18011;
		// // SWIFT obrada

		public static final Integer TIP_NALOGA_SIFRAPOD_PLATNIPROMET_ISPLATA_POREZ_PRIREZ_NA_KAMATU = 510;
		public static final Integer TIP_NALOGA_SWIFT_OBRACUN_NAPLATA_NAKNADE_ISPIS = 9300;
		public static final Integer TIP_NALOGA_SWIFT_CREATE_FILE_IPP = 1500;
		public static final Integer TIP_NALOGA_SWIFT_CREATE_MESSAGE_IPP = 1501;
		public static final Integer TIP_NALOGA_SWIFT_CREATE_MESSAGE_MT191991 = 1505;
		public static final Integer TIP_NALOGA_SWIFT_UPLOAD_FILE_IPP = 1600;
		public static final Integer TIP_NALOGA_SWIFT_UPLOAD_MESSAGE_IPP = 1601;
		public static final Integer TIP_NALOGA_SWIFT_KNJIZENJE_ZATVARANJA_IZVODA = 1610;
		// TARGET2 obrada
		public static final Integer TIP_NALOGA_TARGET2_CREATE_FILE_UPP = 15010;
		public static final Integer TIP_NALOGA_TARGET2_CREATE_MESSAGE_UPP = 15011;
		public static final Integer TIP_NALOGA_TARGET2_UPLOAD_FILE_UPP = 15012;
		public static final Integer TIP_NALOGA_TARGET2_UPLOAD_MESSAGE_UPP = 15013;
		// HSVP obrada
		public static final Integer TIP_NALOGA_HSVP_CREATE_FILE_UPP = 15020;
		public static final Integer TIP_NALOGA_HSVP_CREATE_MESSAGE_UPP = 15021;
		public static final Integer TIP_NALOGA_HSVP_UPLOAD_FILE_UPP = 15022;
		public static final Integer TIP_NALOGA_HSVP_UPLOAD_MESSAGE_UPP = 15023;
		// SEPA
		public static final Integer TIP_NALOGA_SEPA_I_SCT_NKS_FILE = 15300;
		public static final Integer TIP_NALOGA_SEPA_I_SCT_NKS_PACS002 = 15301;
		public static final Integer TIP_NALOGA_SEPA_I_SCT_NKS_PACS008 = 15302;
		public static final Integer TIP_NALOGA_SEPA_I_SCT_NKS_CAMT056 = 15303;
		public static final Integer TIP_NALOGA_SEPA_I_SCT_NKS_PACS004 = 15304;
		public static final Integer TIP_NALOGA_SEPA_I_SCT_NKS_CAMT029 = 15305;
		public static final Integer TIP_NALOGA_SEPA_I_SCT_NKS_PACS028 = 15306;
		public static final Integer TIP_NALOGA_SEPA_I_SCT_NKS_CAMT027 = 15307;
		public static final Integer TIP_NALOGA_SEPA_I_SCT_NKS_CAMT087 = 15308;
		public static final Integer TIP_NALOGA_SEPA_I_SCT_EURONKS_FILE = 15400;
		public static final Integer TIP_NALOGA_SEPA_I_SCT_EURONKS_PACS002 = 15401;
		public static final Integer TIP_NALOGA_SEPA_I_SCT_EURONKS_PACS008 = 15402;
		public static final Integer TIP_NALOGA_SEPA_I_SCT_EURONKS_CAMT056 = 15403;
		public static final Integer TIP_NALOGA_SEPA_I_SCT_EURONKS_PACS004 = 15404;
		public static final Integer TIP_NALOGA_SEPA_I_SCT_EURONKS_CAMT029 = 15405;
		public static final Integer TIP_NALOGA_SEPA_I_SCT_EURONKS_PACS028 = 15406;
		public static final Integer TIP_NALOGA_SEPA_I_SCT_EURONKS_CAMT027 = 15407;
		public static final Integer TIP_NALOGA_SEPA_I_SCT_EURONKS_CAMT087 = 15408;
		public static final Integer TIP_NALOGA_SEPA_O_SCT_NKS_FILE = 16300;
		public static final Integer TIP_NALOGA_SEPA_O_SCT_NKS_PACS002 = 16301;
		public static final Integer TIP_NALOGA_SEPA_O_SCT_NKS_PACS008 = 16302;
		public static final Integer TIP_NALOGA_SEPA_O_SCT_NKS_CAMT056 = 16303;
		public static final Integer TIP_NALOGA_SEPA_O_SCT_NKS_PACS004 = 16304;
		public static final Integer TIP_NALOGA_SEPA_O_SCT_NKS_CAMT029 = 16305;
		public static final Integer TIP_NALOGA_SEPA_O_SCT_EURONKS_FILE = 16400;
		public static final Integer TIP_NALOGA_SEPA_O_SCT_EURONKS_PACS002 = 16401;
		public static final Integer TIP_NALOGA_SEPA_O_SCT_EURONKS_PACS008 = 16402;
		public static final Integer TIP_NALOGA_SEPA_O_SCT_EURONKS_CAMT056 = 16403;
		public static final Integer TIP_NALOGA_SEPA_O_SCT_EURONKS_PACS004 = 16404;
		public static final Integer TIP_NALOGA_SEPA_O_SCT_EURONKS_CAMT029 = 16405;
		// // OPĆI DEVIZNI NALOG
		public static final Integer TIP_NALOGA_ODN_OBRADA_190_191 = 40002;
		public static final Integer TIP_NALOGA_ODN_OBRADA_PRIJENOS_NA_ISPLATNU_BANKU = 40003;
		public static final Integer TIP_NALOGA_SIFRAPOD_OBRADA_STAVKE_DEVIZNOG_IZVODA = 4014;
		public static final Integer TIP_NALOGA_SIFRAKNA_KNJIZENJE_UNATRAG = 4444;
		public static final Integer TIP_NALOGA_SIFRAPOD_DEVIZNI_STORNO = 9999;
		// // DEVIZNI PRILJEV
		public static final Integer TIP_NALOGA_SIFRAPOD_POVRAT_SLUZBENOG_PUTA = 12400;
		public static final Integer TIP_NALOGA_SIFRAPOD_POLOG_NA_DEVIZNI_RACUN_NERASPOREDEN = 12410;
		public static final Integer TIP_NALOGA_SIFRAPOD_POLOG_NA_DEVIZNI_RACUN_RASPOREDEN = 12420;
		public static final Integer TIP_NALOGA_SIFRAPOD_RASPORED_DEVIZNOG_PRILJEVA_INTERNI = 12800;
		public static final Integer TIP_NALOGA_SIFRAPOD_RASPORED_DEVIZNOG_PRILJEVA_BEZ_CVRSTOG_RN_INTERNI = 12810;
		public static final Integer TIP_NALOGA_SIFRAPOD_RASPORED_DEVIZNOG_PRILJEVA_EKSTERNI = 12850;
		public static final Integer TIP_NALOGA_SIFRAPOD_RASPORED_DEVIZNOG_PRILJEVA_BEZ_CVRSTOG_RN_EKSTERNI = 12860;
		//STATUS POSLANE OBAVIJESTI ZA DEVIZNI PRILJEV
		public static final Integer OBAVIJEST_O_DEVIZNOM_PRILJEVU_NIJE_POSLANA = 0;
		public static final Integer OBAVIJEST_O_DEVIZNOM_PRILJEVU_POSLANA = 7;
		// // tipovi deviznih reporta
		public static final String DEVIZNI_REPORT_TYPE_NALOG = "nalog";
		public static final String DEVIZNI_REPORT_TYPE_SAMO_VEZANI = "samoVezani";
		public static final String DEVIZNI_REPORT_TYPE_NALOG_S_VEZANIMA = "nalogSVezanima";
		public static final String DEVIZNI_REPORT_TYPE_NALOG_NAKNADE = "nalogNaknade";
		public static final String DEVIZNI_REPORT_TYPE_NALOG_S_VEZANIMA_IPP = "nalogSVezanimaIpp";
		public static final String DEVIZNI_REPORT_TYPE_NALOG_S_VEZANIMA_POKRICE_IPP = "nalogSVezanimaPokriceIpp";
		public static final String DEVIZNI_REPORT_TYPE_SAMO_VEZANI_RETAIL = "samoVezaniRetail";
		public static final Integer KLASA_NALOGA_SWIFT_CREATE_FILE_IPP = 1500;
		// // DEVIZNI ODLJEV
		public static final Integer KLASA_NALOGA_SIFRAKNA_OPCI_DEVIZNI_ODLJEV_PL = 1450;
		public static final Integer KLASA_NALOGA_SIFRAKNA_OPCI_DEVIZNI_ODLJEV_FL = 1470;
		public static final Integer KLASA_NALOGA_SIFRAKNA_ISPLATA_SLUZBENOG_PUTA = 1480;
		public static final Integer TIP_NALOGA_SIFRAPOD_POKRICE_DEVIZNOG_NALOGA = 14900;
		public static final Integer TIP_NALOGA_SIFRAPOD_POKRICE_DEVIZNOG_NAKNADE = 14990;
		public static final List<Integer> KLASA_NALOGA_DOZNAKE_RETAIL = Arrays.asList(KLASA_NALOGA_SIFRAKNA_OPCI_DEVIZNI_ODLJEV_FL);
		// STATUS POSLANE OBAVIJESTI ZA DEVIZNI ODLJEV
		public static final Integer OBAVIJEST_O_DEVIZNOM_ODLJEVU_NIJE_POSLANA = 0;
		public static final Integer OBAVIJEST_O_DEVIZNOM_ODLJEVU_POSLANA = 7;
		// //Tip Veze
		public static final Integer TIP_VEZE_SIFRATPV_OVLASTENJE_UPP = 12;
		public static final Integer TIP_VEZE_SIFRATPV_OVLASTENJE_IPP = 13;
		public static final Integer TIP_VEZE_SIFRATPV_SWAP_TRANSAKCIJA = 999;
		// // konto
		public static final String KONTO_PREFIX_GLAVNICA_KONTOKORENTA = "31*";
		// defualtni broj dana unaprijed za doznaku
		public static final Integer DEVIZNA_DOZNAKA_BROJA_DANA_UNAPRIJED_T_PLUS_0 = 0;
		public static final Integer DEVIZNA_DOZNAKA_BROJA_DANA_UNAPRIJED_DEFAULT = 1;
		public static final Integer DEVIZNA_DOZNAKA_BROJA_DANA_UNAPRIJED_T_PLUS_2 = 2;
		// // status obrade upp naloga
		public static final String NALOG_STATUS_UPP_INICIRAN = "0";
		public static final String NALOG_STATUS_UPP_VERIFICIRAN = "1";
		public static final String NALOG_STATUS_UPP_AUTORIZIRAN = "2";
		public static final String NALOG_STATUS_UPP_LIKVIDNOST = "3";
		public static final String NALOG_STATUS_UPP_POSLAN = "4";
		public static final String NALOG_STATUS_ZAVRSEN = "7";
		public static final String NALOG_STATUS_POVUCEN = "8";
		public static final String NALOG_STATUS_POVUCEN_B2 = "6";

		// status obrade platnog naloga
		public static final String PLATNI_NALOG_STATUS_INICIRAN = "0";
		public static final String PLATNI_NALOG_STATUS_ZAVRSEN = "7";
		public static final String PLATNI_NALOG_STATUS_POVUCEN = "8";
		public static final Integer BROJ_DANA_UNATRAG_DEVIZNO_POSLOVANJE = 0;
		public static final Integer BROJ_DANA_UNATRAG_SEPA_POSLOVANJE = 0;
		public static final Integer BROJ_DANA_UNATRAG_TARGET2_DIRECTORY = 28;
		public static final Integer BROJ_DANA_UNATRAG_UPP = 0;
		// public static final Integer BROJ_DANA_UNATRAG_DEVIZNI_IZVOD = 7;
		// public static final List<String> GRUPE_RACUNA_PARTIJA_PLATNI =
		// Arrays.asList(Core.GRUPA_RACUNA_PLATNI,
		// Core.GRUPA_RACUNA_PLATNI_NEREZ);
		// public static final List<String> GRUPE_RACUNA_PARTIJA_KREDITI =
		// Arrays.asList(Krediti.GRUPA_RACUNA_DUGOROCNI_KREDITI_FIZICKA_LICA,
		// Krediti.GRUPA_RACUNA_KRATKOROCNI_KREDITI_FIZICKA_LICA,
		// Krediti.GRUPA_RACUNA_DUGOROCNI_KREDITI_PRAVNA_LICA,
		// Krediti.GRUPA_RACUNA_KRATKOROCNI_KREDITI_PRAVNA_LICA);
		// public static final List<String> GRUPE_RACUNA_PARTIJA_RETAIL =
		// Arrays.asList(Retail.TIPPARTIJE_POSAOTIP_TEKUCI,
		// Retail.TIPPARTIJE_POSAOTIP_PREPAID, Retail.TIPPARTIJE_POSAOTIP_ZIRO,
		// Retail.TIPPARTIJE_POSAOTIP_AVISTA_DOMICILNA,
		// Retail.TIPPARTIJE_POSAOTIP_BLAGAJNA,
		// Retail.TIPPARTIJE_POSAOTIP_TREZOR);
		// public static final List<String> GRUPE_RACUNA_PARTIJA_DEPOZIT =
		// Arrays.asList(Depoziti.GRUPA_RACUNA_OROCENJA_FIZICKI,
		// Depoziti.GRUPA_RACUNA_OROCENJA_FIZICKI_16,
		// Depoziti.GRUPA_RACUNA_OROCENJA_PRAVNI,
		// Depoziti.GRUPA_RACUNA_OROCENJA_AVISTA);
		// public static final List<String> GRUPE_RACUNA_PARTIJA_OSTALO =
		// Arrays.asList(Ostalo.PARTIJA_GRUPA_RACUNA_OSTALO);
		public static final String STATUS_TABLE_OPCI_DEVIZNI_NALOG = "OpciDevizniNalog";
		public static final String STATUS_FIELD_TIP_SWIFT_PORUKE = "swiftMessageType";
		// //putanja za datoteke koje se šalju mailom
		// public static final String PUTANJA_SLANJE_E_MAIL =
		// "/data/OUT/tmpEMAIL";
		// public static final String E_MAIL_IZVODA_TEST =
		// "test.abit@unionbank.ba";
		// //racuni ministarstva financija koji imaju partija_platni.prkam_par =
		// 2
		// // utilMap
		// public static final String UTILMAP_KEY_ISKLJUCI_PROVJERA_BASIC =
		// "checkBasic";
		// public static final String UTILMAP_KEY_ISKLJUCI_PROVJERA_JAVNI_PRIHOD
		// = "checkJP";
		// public static final String UTILMAP_KEY_ISKLJUCI_PROVJERA_CLASS =
		// "checkClass";
		// public static final String UTILMAP_KEY_ISKLJUCI_PROVJERA_ANALITIKA =
		// "checkSpecial";
		// public static final String UTILMAP_KEY_ISKLJUCI_OBRACUN_NAKNADE =
		// "obrNak";
		// public static final String UTILMAP_KEY_ISKLJUCI_FAKTURA = "fakt";
		// public static final String UTILMAP_KEY_ISKLJUCI_NAPLATA_FAKTURA =
		// "napFak";
		// public static final String UTILMAP_KEY_ISKLJUCI_PROVJERU_MATICNI =
		// "checkMaticni";
		// public static final String UTILMAP_KEY_ISKLJUCI_CHECK_RASPOLOZIVO =
		// "checkRaspolozivo";
		// public static final String UTILMAP_KEY_ULAZ_DATOTEKA = "izDatoteke";
		public static final String EURONKS_PRODUCT_TYPE_SCT = "SCT";
		// generic params
		public static final String MAX_FIRST_LIST_SIZE = "100";
		public static final String OZNKA_NAD_ODBIJENI_PRILJEVI_HRK = "50";
		public static final String OZNKA_NAD_ODBIJENI_PRILJEVI_EUR = "074";
		public static final String SIFRAPAR_PLATNIPROMET_NAJAVA_VRIJEME_START = "NAJstart";
		public static final String SIFRAPAR_PLATNIPROMET_NAJAVA_VRIJEME_START_EX = "NAJstarE";
		public static final String SIFRAPAR_PLATNIPROMET_NAJAVA_VRIJEME_START_6 = "NAJstar6";
		public static final String SIFRAPAR_PLATNIPROMET_NAJAVA_VRIJEME_END = "NAJendIN";
		public static final String SIFRAPAR_PLATNIPROMET_NAJAVA_VRIJEME_END_EX = "NAJendEX";
		public static final String SIFRAPAR_PLATNIPROMET_NAJAVA_VRIJEME_END_6 = "NAJendI6";
		public static final String SIFRAPAR_PLATNI_BR_DANA_U_NAJAVI = "NAJBrDan";
		public static final String HSVP_VRSTA_POSLA_IZDVAJANJE_NKS_SIFRA = "51020000";
		public static final String HSVP_VRSTA_POSLA_IZDVAJANJE_KOP = "71020000";
		public static final String HSVP_VRSTA_POSLA_KUPOPRODAJA_DEVIZA = "45";
		public static final String HSVP_VRSTA_POSLA_POLOG_PREKONOCNOG_DEPOZITA = "09020000";
		public static final String B2STATUS_TABLE_SPP_NALOGODAVATELJ = "NalogHub3";
		public static final String B2STATUS_FIELD_SPP_NALOGODAVATELJ = "nlgdvHub";
		public static final String B2STATUS_FIELD_PRIORITET = "nacizTrn";
		public static final String B2STATUS_TABLE_NALOG_PLATNI = "NalogPlatni";
		public static final String SIFRA_OPISA_PLACANJA_BBN = "BBN";
		public static final Integer SPP_NALOGODAVATELJ_NAKNADA = 7;
		//public static final String TIP_NALOGA_DETALJ_NACIONALNOST_PREKOGRANICNI = "M";
		//public static final String TIP_NALOGA_DETALJ_NACIONALNOST_NACIONALNI = "N";
		public static final Integer TIP_NALOGA_DETALJ_NAJAVA = 1;
		public static final Integer TIP_NALOGA_DETALJ_REDOVAN = 0;
		public static final String TIP_NALOGA_DETALJ_KANAL_FINA = "fina";
		public static final String TIP_NALOGA_DETALJ_KANAL_BANKA = "banka";
		public static final Integer TIP_NALOGA_DETALJ_NEVAZNO_INT = 100;
		public static final String TIP_NALOGA_DETALJ_NEVAZNO = "X";
		public static final String KLASA_PARTIJE_SIFRAKLP_SPP = Core.KLASA_PARTIJE_SIFRAKLP_TRANSAKCIJSKI_RACUNI_PRAVNIH_OSOBA_AVPO + "," + Core.KLASA_PARTIJE_SIFRAKLP_AVISTA_HRK + "," + Core.KLASA_PARTIJE_SIFRAKLP_AVISTA_DEVIZNA + "," + Core.KLASA_PARTIJE_SIFRAKLP_ZIRO_RACUN + "," + Core.KLASA_PARTIJE_SIFRAKLP_TEKUCI_RACUN + "," + Core.KLASA_PARTIJE_SIFRAKLP_OROCENI_DEPOZITI_FIZICKA_LICA + ","
				+ Core.KLASA_PARTIJE_SIFRAKLP_OROCENI_DEPOZITI_PRAVNA_LICA + "," + Core.KLASA_PARTIJE_SIFRAKLP_BLAGAJNA + "," + Retail.KLASAPARTIJE_SIFRAKLP_RETAIL_MJENJAC + "," + Core.KLASA_PARTIJE_SIFRAKLP_KREDITI_FIZICKA_LICA + "," + Core.KLASA_PARTIJE_SIFRAKLP_KREDITI_PRAVNA_LICA + "," + Core.KLASA_PARTIJE_ESKONT_MJENICA + "," + AkreditiviGarancije.KLASA_PARTIJE_GARANCIJE_NOSTRO + ","
				+ AkreditiviGarancije.KLASA_PARTIJE_GARANCIJE_LORO + "," + AkreditiviGarancije.KLASA_PARTIJE_AKREDITIVI_NOSTRO + "," + AkreditiviGarancije.KLASA_PARTIJE_AKREDITIVI_LORO;
		public static final String PARTIJA_GRUPA_RACUNA_98 = "98";
		public static final String VBDI4 = "2408";
		// public static final Integer PROIZVOD_SIFRAPRO_NAPLATA_FAKTURA = 200;
		// public static final Integer PROIZVOD_SIFRAPRO_PAUSAL= 201;
		// //likvidirana partija
		// public static final String PARTIJA_PLATNI_STATUPAR_LIKVIDIRANA = "L";
		// public static final String PARTIJA_PLATNI_STATUPAR_LIKVIDIRANA_OPIS =
		// "LIKVIDIRANA";
		// public static final String JRR_STATUS_UGASENA = "U";
		// public static final String TIP_NAL_TEMPLATE_PRIPIS_KAMATE = "1";
		// public static final String B2_STATUS_OPISSTA_JRR_ISK =
		// "Iskljuceni iz JRR-a";
		// public static Integer SIFRA_RACUNA_NERASPOZNATI_PRILJEVI = 2;
		// public static Integer SIFRA_RACUNA_POGRESNI_PRILJEVI = 3;
		public static String MSG_TYPE_PINE = "hr.abit.b3.biz.pain.";
		public static String IDENT_SPLITER = "-";
		public static String IDENT_PARTIJA = "part";
		public static String IDENT_NADDIO_OZNAKA = "ozNad";
		public static String IDENT_TIP_ALGOTITMA = "ident";
		public static String T2_PARTICIPATION_DIRECT = "01";
		public static String MBU_NALOG1_AUTORNAL_AUTORIZACIJA = "A";
		public static String MBU_NALOG1_OBRADNAL_NEOBRADJEN = "0";
		public static String MBU_NALOG1_PREDZNAL_CREDIT = "C";
		public static String MBU_NALOG1_PREDZNAL_REVERSAL_DEBIT = "RD";
		public static String OZNKANAD_KARTICNO_DOSPJELI_TROSAK = "66";
		public static String OZNKANAD_KARTICNO_DOSPJELA_NAKNADA = "67";

		public static String OZNKANAD_KARTICNO_PRIHODI_KARTICE = "68";
		public static String OZNKANAD_KARTICNO_PRIHODI_KARTICNE_TRANSAKCIJE = "69";
		public static String OZNKANAD_KARTICNO_DOSPJELA_POTRAZIVANJA = "74";
		// tipovi odlaznih SWIFT poruka koji se kontroliraju kod formiranja izvoda klijenta
		public static final String REPRTREP_IZVOD_KLIJENTA_SWIFT = "izvodKlijenta";
		public static final String FIELDREP_IZVOD_KLIJENTA_SWIFT_CONTROLLED_TYPES = "tipPoruke";

		public static final List<Integer> KLASA_PARTIJE_SIFRAKLP_KUPOPRODAJA = Arrays.asList(Core.KLASA_PARTIJE_SIFRAKLP_TRANSAKCIJSKI_RACUNI_PRAVNIH_OSOBA_AVPO, Core.KLASA_PARTIJE_SIFRAKLP_AVISTA_HRK, Core.KLASA_PARTIJE_SIFRAKLP_AVISTA_DEVIZNA, Core.KLASA_PARTIJE_SIFRAKLP_ZIRO_RACUN, Core.KLASA_PARTIJE_SIFRAKLP_TEKUCI_RACUN, Core.KLASA_PARTIJE_SIFRAKLP_DOK_GRADJANI);
		public static final String SIFRA_PARAM_DONJA_GRANICA_LIM_INSTA = "D_LIMT_INPAY";
		public static final String SIFRA_PARAM_GORNJA_GRANICA_LIM_INSTA = "G_LIMT_INPAY";
		public static final String MAIL_LIMIT_INSTA_PAY = "MAIL_LIMIT";
		public static final String MAX_IZNOS_LIMIT_IPAY = "IPayLim";
		public static final String MAIL_DOSTUPNOST = "MAIL_DOSTUPNO";
	}

	public static class Core extends GenericBassxConstants.Core {
		public static final String NEW_LINE = "\r\n";
		public static final String NEW_LINE_UNIX = "\n";
		public static final String SPACE = " ";
		public static final int HTTP_STATUS_CODE_200 = 200; // Zahtjev je uspješno obrađen.
		public static final int HTTP_STATUS_CODE_400 = 400; // Nužan parametar nije specificiran ili je predan u neispravnoj sintaksi, provjerite parametre predane kroz HTTP zaglavlja i pokušajte ponovo.
		public static final int HTTP_STATUS_CODE_500 = 500; // Interna greška NKSInst sustava. Ponovite slanje zahtjeva kasnije, a učestala ponavljanje ove greške prijavite podršci.
		public static final int HTTP_STATUS_CODE_503 = 503; // Servis nije dostupan. Za upite o dostupnosti servisa i eventualnim planiranim ispadima konzultirajte stranice Fine.

		public static final String CODEPAGE_Cp1250 = "Cp1250";

		public static final String DUGOVNA_STRANA = "D";
		public static final String POTRAZNA_STRANA = "P";

		public static final Integer BROJ_DANA_UNATRAG_BBM = 7;

		public static final String GRUPA_RACUNA_PLATNI = "00";
		public static final String GRUPA_RACUNA_PLATNI_NEREZ = "02";

		public static final String DEFAULT_ORGANIZACIJSKA_JEDINICA_MIGRACIJA = "409";

		public static final String MAX_FIRST_LIST_SIZE = "100";

		public static final Integer KLASANALOGA_SIFRAKNA_MIGRACIJA = 1;
		public static final Integer KLASANALOGA_SIFRAKNA_TEMELJNICA = 50;

		public static final Integer TIP_NAPLATE_NAKNADE_PO_FAKTURI = 0;
		public static final Integer TIP_NAPLATE_NAKNADE_ODMAH = 1;

		// klasa naloga
		public static final String KLASA_NALOGA_GRUPA_OBRACUN_NAKNADE = "OBRNAK";
		public static final String KLASA_NALOGA_GRUPA_OBRACUN_NAKNADE_RAZGRANICENJE = "OBRNAKRAS";
		public static final String KLASA_NALOGA_GRUPA_NAPLATA_NAKNADE = "NPLNK";

		// država

		// modul constants

		public static final Integer MODUL_SIFRAMOD_MATICNI = 199;
		public static final Integer MODUL_SIFRAMOD_GIA = 1003;
		public static final Integer MODUL_SIFRAMOD_AVPO = 3;
		public static final Integer MODUL_SIFRAMOD_PLATNI = 20;
		public static final Integer MODUL_SIFRAMOD_DEPOZITI = 4;
		public static final Integer MODUL_SIFRAMOD_RETAIL = 5;
		public static final Integer MODUL_SIFRAMOD_KREDITI = 6;
		public static final Integer MODUL_SIFRAMOD_RETAIL_BACK = 7;
		public static final Integer MODUL_SIFRAMOD_LEDGER = 8;
		public static final Integer MODUL_SIFRAMOD_RIZNICA = 50;
		public static final Integer MODUL_SIFRAMOD_TREZOR = 11;
		public static final Integer MODUL_SIFRAMOD_KARTICNO = 200;
		public static final Integer MODUL_SIFRAMOD_OSTALO = 13;
		public static final Integer MODUL_SIFRAMOD_URAIRA = 23;
		public static final Integer MODUL_SIFRAMOD_REGISTAR_OSIGURANJA = 15;
		public static final Integer MODUL_SIFRAMOD_TRAJNI_NALOZI = 12;
		public static final Integer MODUL_SIFRAMOD_IZVOD_BANKE = 89;
		public static final Integer MODUL_SIFRAMOD_BEZ_KONTOLE = 13;
		public static final Integer MODUL_SIFRAMOD_PNOP = 66;
		public static final Integer SIFRAMOD_BANKA = 0;
		public static final Integer MODUL_SIFRAMOD_AUTOMATIKA = 98;
		public static final Integer MODUL_SIFRAMOD_IBFO = 992;
		public static final Integer MODUL_SIFRAMOD_IBPS = 997;
		public static final Integer MODUL_SIFRAMOD_PSD2 = 400;
		public static final String MESSAGE_BUNDLE = "hr.abit.b3.biz.core.message.coreMessages";
		// nalog
		public static final Short NALOG_PRIORNAL_DEFAULT = Short.valueOf("99");
		public static final Short NALOG_PRIORNAL_BLAGAJNA = Short.valueOf("11");
		public static final String NALOG_STATUNAL_INTERNET = "#";
		public static final String NALOG_STATUNAL_UPISAN = "0";
		public static final String NALOG_STATUNAL_VERIFICIRAN = "1";
		public static final String NALOG_STATUNAL_LIKVIDNOST = "3";
		public static final String NALOG_STATUNAL_POSLAN = "4";
		public static final String NALOG_STATUNAL_PROKNJIZEN = "7";
		public static final String NALOG_STATUNAL_POVUCEN_B2 = "6";
		public static final String NALOG_STATUNAL_POVUCEN = "8";
		public static final String NALOG_STATUNAL_STORNIRAN_B2 = "9";

		public static final String NALOG_STOZNNAL_STORNO = "Y";

		public static final Integer PROIZVOD_SIFRAPRO_GLAVNA_KNJIGA = 3;

		public static final Integer PROMOBA_TIP_OBAVIJESTI_IZVOD = 1;
		public static final Integer PROMOBA_TIPOB_IZVOD_PO_DOMICILNI = 1;
		public static final Integer PROMOBA_TIPOB_IZVOD_PO_DEVIZNI = 2;
		public static final Integer PROMOBA_TIPOB_IZVOD_PO_OROCENJE = 3;

		// status
		public static final String STATUS_TABLE_NALOG_DEVIZNI = "NalogDevizni";
		public static final String STATUS_FIELD_TROSKOVI_INO_BANKE = "trosakInoBanke";
		public static final String STATUS_FIELD_REZIM = "rezimExt";
		public static final String STATUS_FIELD_IZVOR_SREDSTAVA = "izsreExt";
		public static final String STATUS_FIELD_OBLIK_UVOZNOG_POSLA = "uvozpExt";
		public static final String STATUS_FIELD_TROSKOVI_VANJ_TRG_POSLA = "troskoviVanjskoTrgovinskogPosla";
		public static final String STATUS_FIELD_SIFRA_STRANE_OSOBE = "sifraStraneOsobe";
		public static final String STATUS_TABLE_NIVO_POVJERLJIVOSTI = "Partija";
		public static final String STATUS_FIELD_NIVO_POVJERLJIVOSTI = "nivoPovjerljivosti";
		public static final String STATUS_TABLE_PARTIJA_DEVIZNOG_DEPOZITA = "PartijaDeviznogDepozita";
		public static final String STATUS_FIELD_ULOGA = "oroleDep";
		public static final String STATUS_FIELD_TIP_KAMATNE_STOPE = "tipksDep";
		public static final String STATUS_FIELD_TIP_OPERACIJE = "tipOperacije";
		public static final String STATUS_TABLE_NAKNADA = "Naknada";
		public static final String STATUS_FIELD_NAKNADA_NAPLATA = "napltNak";
		public static final String STATUS_TABLE_GRUPANAKNADE = "GrupaNaknade";
		public static final String STATUS_FIELD_GRUPANAKNADE_NAPLATA = "napdpGnk";
		public static final String STATUS_TABLE_AKREDITIV = "PartijaAkreditiva";
		public static final String STATUS_FIELD_AKREDITIV_TIP_PARTIJE = "tipPartije";
		public static final String STATUS_FIELD_AKREDITIV_KONFIRMACIJA = "konfirmacija";
		public static final String STATUS_FIELD_AKREDITIV_POKRICE = "pokrice";
		public static final String STATUS_FIELD_AKREDITIV_REZIDENTNOST = "rezidentnost";
		public static final String STATUS_TABLE_GARANCIJA = "PartijaVrijPapir";
		public static final String STATUS_FIELD_GARANCIJA_POKRICE = "pokrice";
		public static final String STATUS_FIELD_NAD_DIO_DEV_IZVOD_DEVIZNI_RACUNI = "nadDioIzvodDevRn";
		public static final String STATUS_FIELD_NAD_DIO_DEV_IZVOD_DOMICILNI_RACUNI = "nadDioIzvodDomRn";
		public static final String STATUS_TABLE_SEPA_REASON_CODE = "Pacs004ReasonCode";
		public static final String STATUS_FIELD_SEPA_REASON_CODE = "ReasonCode";

		// status depoziti
		public static final String STATUS_TABLE_PARTIJA_OROCENJA = "PartijaOrocenja";
		public static final String STATUS_TABLE_OBRACUN_KAMATE = "ObracunKamate";
		public static final String STATUS_TABLE_PARTIJA_OROCENJA_FIZICKI = "PartijaOrocenjaFizicki";
		public static final String STATUS_TABLE_PARTIJA_OROCENJA_PRAVNI = "PartijaOrocenjaPravni";
		public static final String STATUS_TABLE_NALOG_OROCENJA = "NalogOrocenja";
		public static final String STATUS_FIELD_FIKSNOST_KAMATNE_STOPE = "fiksnPar";
		public static final String STATUS_FIELD_OTVORENOST = "nasukPar";
		public static final String STATUS_FIELD_PONASANJE = "ponasPar";
		public static final String STATUS_FIELD_RENTA = "rentaPar";
		public static final String STATUS_FIELD_FREKVENCIJA_RENTE = "frekvPar";
		public static final String STATUS_FIELD_VALUTA = "sifvaPar";
		public static final String STATUS_FIELD_ROCNOSTI = "rocnost";
		public static final String STATUS_FIELD_NACIN_UPLATE = "nacinUplate";
		public static final String STATUS_FIELD_NAMJENA = "namjePar";
		public static final String STATUS_FIELD_FILIJALA = "pjparPar";
		public static final String STATUS_FIELD_TIP_PROIZVODA = "tipPro";
		public static final String STATUS_FIELD_VRSTA_PROIZVODA = "vrstaPro";
		public static final String STATUS_FIELD_INDEKSACIJA = "indexPar";
		public static final String STATUS_FIELD_BROJ_MJESECI = "brojMje";
		public static final String STATUS_FIELD_NIVO_STOPE = "nivoStope";
		public static final String STATUS_FIELD_PROIZVOD_KAMATA_PROIZVOD = "PRO_KAM";
		public static final String STATUS_FIELD_OBRACUN_KAMATE = "obracun";
		public static final String STATUS_FIELD_TIP_ISPLATA = "tipIsplata";
		public static final String STATUS_FIELD_UGOVOR_RAZROCENJE_OPCIJA = "ugovorRazrocOpcija";
		// status krediti
		public static final String STATUS_TABLE_PARTIJA_KREDUG = "PartijaKredug";
		public static final String STATUS_TABLE_NALOG_KREDITNI = "NalogKreditni";
		public static final String STATUS_FIELD_TECAJ_PLASMANA = "plasmPar";
		public static final String STATUS_FIELD_VRSTA_KREDITA = "vrstaPar";
		public static final String STATUS_FIELD_NACIN_OBRACUNA_INTERKALARNE = "izintPar";
		public static final String STATUS_FIELD_TECAJ_POVRATA = "indexPar";
		public static final String STATUS_FIELD_KARAKTER_KAMATNE_STOPE = "fiksnPar";
		public static final String STATUS_FIELD_UVJETNO_KORISTENJE = "uvkorPar";
		public static final String STATUS_FIELD_METODA_OBRACUNA_KAMATE = "nacobPar";
		public static final String STATUS_FIELD_MOMENT_OBRACUNA_KAMATE_U_KORISTENJU = "perraPar";
		public static final String STATUS_FIELD_MOMENT_OBRACUNA_KAMATE_U_OTPLATI = "nackaPar";
		public static final String STATUS_FIELD_TRETMAN_KAMATE_U_KORISTENJU = "nacprPar";
		public static final String STATUS_FIELD_TRETMAN_KAMATE_U_OTPLATI = "kamotPar";
		public static final String STATUS_FIELD_MOMENT_OBRACUNA_NAKANDE_U_KORISTENJU = "naconPar";
		public static final String STATUS_FIELD_MOMENT_OBRACUNA_NAKANDE_U_OTPLATI = "nakotPar";
		public static final String STATUS_FIELD_BROJ_ANUITETA_GODISNJE = "brataPar";
		public static final String STATUS_FIELD_NACIN_OTPLATE = "tipraPar";
		public static final String STATUS_FIELD_RASPOREDI_ZADNJEG_U_MJESECU = "konmjPar";
		public static final String STATUS_FIELD_MOMENT_OBRACUNA_KAMATE_U_MORATORIJU = "permoPar";
		public static final String STATUS_FIELD_TRETMAN_KAMATE_U_MORATORIJU = "kammoPar";
		public static final String STATUS_FIELD_SIFRA_VALUTE = "sifvaPar";
		public static final String STATUS_FIELD_REVOLVING = "revolPar";
		public static final String STATUS_FIELD_VRSTA_PROMETA = "vrstaPrm";
		public static final String STATUS_FIELD_MINIMALNI_IZNOS = "iznosMin";
		public static final String STATUS_FIELD_MAKSIMALNI_IZNOS = "iznosMax";
		public static final String STATUS_FIELD_MINIMALNI_ROK = "godinMin";
		public static final String STATUS_FIELD_MAKSIMALNI_ROK = "godinMax";
		public static final String STATUS_FIELD_MINIMALNA_STOPA = "kamstMin";
		public static final String STATUS_FIELD_MAKSIMALNA_STOPA = "kamstMax";
		public static final String STATUS_FIELD_ZATEZNA_MINIMALNA_STOPA = "kamstZatMin";
		public static final String STATUS_FIELD_ZATEZNA_MAKSIMALNA_STOPA = "kamstZatMax";
		public static final String STATUS_FIELD_MINIMALNA_NAKNADA = "naknaMin";
		public static final String STATUS_FIELD_MAKSIMALNA_NAKNADA = "naknaMax";
		public static final String STATUS_FIELD_MINIMALNI_POCEK = "pocekMin";
		public static final String STATUS_FIELD_MAKSIMALNI_POCEK = "pocekMax";
		public static final String STATUS_FIELD_TIP_NALOGA_NAKNADE_OBRADA = "tipNalogaObrada";
		public static final String STATUS_FIELD_TIP_NALOGA_ADMINISTRATIVNI_TROSAK = "tipNalogaTrosak";
		public static final String STATUS_FIELD_NOSITELJ_TROSKOVA = "nostrPar";

		public static final String STATUS_TABLE_NALOG_PLATNI = "NalogPlatni";
		public static final String STATUS_FIELD_SVRHA_NAL = "svrhaNal";
		public static final String STATUS_TABLE_NERASPOZNATI_PRILJEVI = "NeraspoznatiPriljevi";
		public static final String STATUS_FIELD_NERASPOZNATI_PRILJEVI_VLASNIK_JAMAC = "vlasnikJamac";

		// status avpo
		public static final String STATUS_TABLE_PARTIJA_AVPO = "PartijaAvpo";
		public static final String STATUS_FIELD_OZNAKA_IZUZECA_PRISILNA_NAKNADA = "oznIzuzeca";
		public static final String STATUS_FIELD_OZNAKA_ZAJEDNICKOG_RACUNA = "oznZajRacuna";
		public static final String STATUS_FIELD_OZNAKA_SPECIFICNE_NAMJENE = "oznSpecNamjene";
		public static final String STATUS_FIELD_OZNAKA_NOV_SREDSTAVA_DUZNIKA_STECAJ = "oznNovSreDuzStecaj";
		public static final String STATUS_FIELD_OZNAKA_ZALOZNOG_PRAVA = "oznZalPrava";
		public static final String STATUS_FIELD_GRUPA_RACUNA_VBDI = "vbdi";
		public static final String STATUS_FIELD_ISPIS_BONITETA = "bonitet";
		public static final String STATUS_FIELD_GDJE_BLOKADA = "gdjeBlokada";
		public static final String STATUS_FIELD_MJESTO_OPSLUZIVANJA = "mjestoOpsluzivanja";

		// dio partije
		public static final String DIO_PARTIJE_OZNKADIO_OSNOVNI = "O";
		public static final String DIO_PARTIJE_OZNKADIO_MIGRIRANI = "M";

		// tip partije
		public static final Integer TIP_PARTIJE_SIFRATIP_IZVOR_FINANCIRANJA_DOMICILNI_UZETI_KREDIT_DOMICILNA = 1;
		public static final Integer TIP_PARTIJE_SIFRATIP_IZVOR_FINANCIRANJA_DOMICILNI_UZETI_KREDIT_DEVIZNA = 2;
		public static final Integer TIP_PARTIJE_SIFRATIP_IZVOR_FINANCIRANJA_DOMICILNI_UZETI_KREDIT_VALUTNA_KLAUZULA = 3;
		public static final Integer TIP_PARTIJE_SIFRATIP_IZVOR_FINANCIRANJA_INOZEMNI_UZETI_KREDIT_DOMICILNA = 4;
		public static final Integer TIP_PARTIJE_SIFRATIP_IZVOR_FINANCIRANJA_INOZEMNI_UZETI_KREDIT_DEVIZNA = 5;
		public static final Integer TIP_PARTIJE_SIFRATIP_IZVOR_FINANCIRANJA_INOZEMNI_UZETI_KREDIT_VALUTNA_KLAUZULA = 6;
		public static final Integer TIP_PARTIJE_SIFRATIP_IZVOR_FINANCIRANJA_LIMIT = 7;

		public static final String TIP_PARTIJE_NAMJETIP_NAMJENSKI = "1";
		public static final String TIP_PARTIJE_NAMJETIP_NENAMJENSKI = "0";
		public static final Integer TIP_PARTIJE_SIFRATIP_DOMICILNI_KONTOKORENTI = 22;
		public static final Integer TIP_PARTIJE_SIFRATIP_STRANI_KONTOKORENTI = 21;
		public static final Integer TIP_PARTIJE_SIFRATIP_UZETI_KREDITI_ODRAZ = 8;

		public static final Integer TIP_PARTIJE_SIFRATIP_PP_VLADA_BUDZET = 100;
		public static final Integer TIP_PARTIJE_SIFRATIP_PP_VLADA_FEDERACIJE_BUDZET = 101;
		public static final Integer TIP_PARTIJE_SIFRATIP_PP_VLADA_RS_BUDZET = 102;
		public static final Integer TIP_PARTIJE_SIFRATIP_PP_VLADA_KANTONA_BUDZET = 103;
		public static final Integer TIP_PARTIJE_SIFRATIP_PP_OPSTINA_BUDZET = 104;
		public static final Integer TIP_PARTIJE_SIFRATIP_PP_VLADA_VANBUDZET = 105;
		public static final Integer TIP_PARTIJE_SIFRATIP_PP_VLADA_FEDERACIJE_VANBUDZET = 106;
		public static final Integer TIP_PARTIJE_SIFRATIP_PP_VLADA_RS_VANBUDZET = 107;
		public static final Integer TIP_PARTIJE_SIFRATIP_PP_VLADA_KANTONA_VANBUDZET = 108;
		public static final Integer TIP_PARTIJE_SIFRATIP_PP_OPSTINA_VANBUDZET = 109;
		public static final Integer TIP_PARTIJE_SIFRATIP_PP_JAVNA_PODUZECA = 110;
		public static final Integer TIP_PARTIJE_SIFRATIP_PP_JAVNA_PODUZECA_DIJELOVI = 111;
		public static final Integer TIP_PARTIJE_SIFRATIP_PP_PRIVATNA_PODUZECA_ZADRUGE = 120;
		public static final Integer TIP_PARTIJE_SIFRATIP_PP_PRIVATNA_PODUZECA_ZADRUGE_POSLOVNA_JEDINICA = 121;
		public static final Integer TIP_PARTIJE_SIFRATIP_PP_PRIVATNA_PODUZECA_ZADRUGE_DRUGA_FIRMA_ISTOG_VLASNIKA = 122;
		public static final Integer TIP_PARTIJE_SIFRATIP_PP_PRIVATNA_PODUZECA_ZADRUGE_DRUGI_RACUN_ISTOG_DEPOZITARA = 123;
		public static final Integer TIP_PARTIJE_SIFRATIP_PP_PRIVATNA_PODUZECA_ZADRUGE_DIJELOVI_PODUZECA = 124;
		public static final Integer TIP_PARTIJE_SIFRATIP_PP_NEPROFITNE_ORGANIZACIJE = 130;
		public static final Integer TIP_PARTIJE_SIFRATIP_PP_NEPROFITNE_ORGANIZACIJE_POSLOVNA_JEDINICA = 131;
		public static final Integer TIP_PARTIJE_SIFRATIP_PP_NEPROFITNE_ORGANIZACIJE_DIJELOVI_PODUZECA = 132;
		public static final Integer TIP_PARTIJE_SIFRATIP_PP_KOMERCIJALNE_BANKE = 140;
		public static final Integer TIP_PARTIJE_SIFRATIP_PP_NEBANKARSKE_FINANCIJSKE_ORGANIZACIJE = 150;
		public static final Integer TIP_PARTIJE_SIFRATIP_PP_NEBANKARSKE_FINANCIJSKE_ORGANIZACIJE_POSLOVNA_JEDINICA = 151;
		public static final Integer TIP_PARTIJE_SIFRATIP_PP_FIZICKA_LICA_SAMOSTALNA_DJELATNOST = 167;
		public static final Integer TIP_PARTIJE_SIFRATIP_PP_NEPOZNATO = 999;
		public static final Integer TIP_PARTIJE_SIFRATIP_KAR_PRIJELAZNE_PARTIJE_BAMCARD_BANAKA = 1301;
		public static final Integer TIP_PARTIJE_SIFRATIP_KAR_PRIJELAZNE_PARTIJE_TRGOVACA = 1302;
		public static final Integer TIP_PARTIJE_SIFRATIP_PRIJELAZNE_PARTIJE_KLIJENATA_APR = 1330;
		public static final Integer TIP_PARTIJE_SIFRATIP_ZASTICENI_RACUNI_IZUZETI_IZ_OVRHE = 1342;
		public static final Integer TIP_PARTIJE_SIFRATIP_RACUN_GLAVNE_KNJIGE = 53;
		public static final Integer TIPPARTIJE_SIFRATIP_TRAJNI_NALOG_INTERNI = 3001;
		public static final Integer TIPPARTIJE_SIFRATIP_TRAJNI_NALOG_EKSTERNI = 3002;
		public static final String TIPPARTIJE_POSAOTIP_TRAJNI_NALOG = "30";
		public static final String TIPPARTIJE_POSAOTIP_OROCENJE_PRAVNI_VALUTNA_KLAUZULA = "17VK";
		public static final Integer TIP_PARTIJE_KUNSKA_KNJIZICA_AVISTA = 1290;
		public static final Integer TIP_PARTIJE_DEVIZNA_KNJIZICA_AVISTA = 1292;

		// matrica
		public static final String MATRICA_TIP_VEZE_TIP_PARTIJE_UZETOG_KREDITA = "uzetiKreditTipPartije";

		// valuta
		public static final String VALUTA_SIFRAVAL_HOME_CURRENCY = "191";
		public static final String VALUTA_OZNKAVAL_HOME_CURRENCY = "HRK";
		public static final String VALUTA_OZNKAVAL_HOME_CURRENCY_2_LETTERS = "KN";
		public static final String VALUTA_SIFRAVAL_EUR = "978";
		public static final String VALUTA_OZNKAVAL_EUR = "EUR";
		public static final String VALUTA_SIFRAVAL_USD = "840";
		public static final String VALUTA_OZNKAVAL_USD = "USD";
		public static final String VALUTA_SIFRAVAL_GBP = "826";
		public static final String VALUTA_OZNKAVAL_GBP = "GBP";
		public static final String VALUTA_OZNKAVAL_BAM = "BAM";
		public static final String VALUTA_FORSEB_TRUE = "1";

		public static final String VALUTA_SIFRAVAL_AUD = "036";
		public static final String VALUTA_SIFRAVAL_CAD = "124";
		public static final String VALUTA_SIFRAVAL_HRK = "191";
		public static final String VALUTA_SIFRAVAL_DKK = "208";
		public static final String VALUTA_SIFRAVAL_JPY = "392";
		public static final String VALUTA_SIFRAVAL_NOK = "578";
		public static final String VALUTA_SIFRAVAL_SEK = "752";
		public static final String VALUTA_SIFRAVAL_CHF = "756";
		public static final String VALUTA_SIFRAVAL_RSD = "941";
		public static final String VALUTA_SIFRAVAL_BAM = "977";

		public static final BigDecimal VALUTA_TECAJ_EURO_BAM = new BigDecimal("1.955830");

		public static final String REPORT_TYPE_VALUTA = "valuta";
		public static final String REPORT_TYPE_KONTO = "konto";
		public static final String REPORT_TYPE_PARTIJA = "partija";
		public static final String REPORT_TYPE_KOMITENT = "komitent";

		// partija

		// interni statusi
		public static final String PARTIJA_INTBLPAR_BLOKIRANA = "B";
		public static final String PARTIJA_INTBLPAR_BLOKIRANA_KUNE = "K";
		public static final String PARTIJA_INTBLPAR_BLOKIRANA_DEVIZE = "D";
		public static final String PARTIJA_INTBLPAR_OTVORENA = "O";
		public static final String PARTIJA_INTBLPAR_SUSPENDIRANA = "S";
		public static final String PARTIJA_INTBLPAR_UTUZENA = "T";
		public static final String PARTIJA_INTBLPAR_OVRHA = "O";
		public static final String PARTIJA_INTBLPAR_POCETA_LIKVIDACIJA = "P";
		public static final String PARTIJA_INTBLPAR_LIKVIDIRANA = "L";

		public static final String VRSTA_KOMITENTA_PRAVNA_OSOBA_NEREZIDENT = "PN";
		public static final String VRSTA_KOMITENTA_PRAVNA_OSOBA_REZIDENT = "PR";

		public static final String PARTIJA_PARTIPAR_PREFIX_POSEBNI_RACUN_VRSTA_94 = "94";
		public static final String PARTIJA_PARTIPAR_PREFIX_31 = "31";
		public static final String PARTIJA_PARTIPAR_PREFIX_32 = "32";
		public static final String PARTIJA_PARTIPAR_PREFIX_33 = "33";
		public static final String PARTIJA_PARTIPAR_PREFIX_73 = "73";
		public static final String PARTIJA_PARTIPAR_PREFIX_75 = "75";
		public static final String PARTIJA_PARTIPAR_PREFIX_85 = "85";
		public static final String PARTIJA_PARTIPAR_PREFIX_86 = "86";
		public static final String PARTIJA_PARTIPAR_PREFIX_48 = "48";

		// prefix za hrvatski IBAN
		public static final String PREFIX_IBAN_HOME = "HR";

		// statusi partije
		public static final String PARTIJA_STATUPAR_UPISANA = "U";
		public static final String PARTIJA_STATUPAR_INFORMATIVNA_PONUDA = "I";
		public static final String PARTIJA_STATUPAR_BLOKIRANA = "B";
		public static final String PARTIJA_STATUPAR_BLOKIRANA_KUNE = "K";
		// public static final String PARTIJA_STATUPAR_BLOKIRANA_DEVIZE = "D";
		public static final String PARTIJA_STATUPAR_NAJAVA_ZATVARANJA = "S";
		public static final String PARTIJA_STATUPAR_ZA_SALDACIJU = "S";
		public static final String PARTIJA_STATUPAR_OTVORENA = "O";
		public static final String PARTIJA_STATUPAR_ZATVORENA = "Z";
		public static final String STATUPAR_NAJAVA_ZATVARANJA = "N";
		public static final String STATUPAR_BLOKIRAN_NAJAVA_ZATVARANJA_STECAJ = "L";

		public static final String PARTIJA_STATUPAR_STECAJ_OPIS = "BLOKIRANA-NAJAVA ZATVARANJA-STEČAJ";

		public static final String PARTIJA_STATUPAR_NEAKTIVNA = "N";
		public static final String NEAKTIVNAPARTIJA_STATUNKT_NEAKTIVNA = "N";

		public static final String PARTIJA_STATUPAR_NAJAVA = "R"; // tražili u
																	// azf-u ovu
																	// oznaku
		@Deprecated
		public static final String PARTIJA_STATUPAR_AMORTIZOVANA = "M";
		@Deprecated
		public static final String PARTIJA_STATUPAR_BLOKIRANA_LIKVIDACIJA = "U";
		@Deprecated
		public static final String PARTIJA_STATUPAR_OROCENA_VEZANA_ZA_KREDIT = "K";
		@Deprecated
		public static final String PARTIJA_STATUPAR_OROCENA_VEZANA_ZA_KREDIT_OPIS = "OROČENA ŠTEDNJA VEZANA ZA ODOBRENI KREDIT";
		public static final String PARTIJA_STATUPAR_OTVORENA_OPIS = "OTVORENA";
		public static final String PARTIJA_STATUPAR_ZATVORENA_OPIS = "ZATVORENA";
		public static final String PARTIJA_STATUPAR_BLOKIRANA_OPIS = "BLOKIRANA";
		public static final String PARTIJA_STATUPAR_NEAKTIVNA_OPIS = "NEAKTIVNA";
		public static final String PARTIJA_STATUPAR_UPISANA_OPIS = "UPISANA";
		public static final String PARTIJA_STATUPAR_ZA_SALDACIJU_OPIS = "SALDACIJA";
		public static final String PARTIJA_STATUPAR_NAJAVA_ZATVARANJA_OPIS = "NAJAVA ZATVARANJA";
		public static final String PARTIJA_STATUPAR_BLOKIRANA_LIKVIDACIJA_OPIS = "BLOKIRANA KOD LIKVIDACIJE";
		public static final String PARTIJA_STATUPAR_AMORTIZOVANA_OPIS = "AMORTIZOVANA";
		@Deprecated
		public static final String PARTIJA_STATUPAR_ZAHTJEV_KARTICA = "Z";
		@Deprecated
		public static final String PARTIJA_STATUPAR_ZAHTJEV_KARTICA_OPIS = "ZAHTJEV KARTICA";
		@Deprecated
		public static final String PARTIJA_STATUPAR_TUZEN_OPIS = "TUZEN";
		@Deprecated
		public static final String PARTIJA_STATUPAR_TUZEN = "T";
		@Deprecated
		public static final String PARTIJA_STATUPAR_SUSPENDOVANA_OPIS = "SUSPENDOVANA";
		@Deprecated
		public static final String PARTIJA_STATUPAR_SUSPENDOVANA = "S";

		public static final String PARTIJA_OZNIZPAR_SREDSTVA_NISU_IZUZETA = "0";
		public static final String PARTIJA_OZNIZPAR_SREDSTVA_U_POTPUNOSTI_IZUZETA = "1";

		// tip tečaja
		public static final String TIP_TECAJA_KUPOVNI = "KUP";
		public static final String TIP_TECAJA_SREDNJI = "SRE";
		public static final String TIP_TECAJA_PRODAJNI = "PRO";
		public static final String TIP_TECAJA_EFEKTIVA_KUPOVNI = "KUE";
		public static final String TIP_TECAJA_EFEKTIVA_PRODAJNI = "PRE";
		public static final String TIP_TECAJA_DOGOVOR = "DOG";
		public static final String TIP_TECAJA_OVLASTENI_MJENJAC_EFEKTIVA_KUPOVNI = "KOM";
		public static final String TIP_TECAJA_OVLASTENI_MJENJAC_EFEKTIVA_PRODAJNI = "POM";

		// Konto
		public static final String KONTO_ANASI_ANALITIKA = "A";
		public static final String KONTO_ANASI_SINTETIKA = "S";

		public static final String KONTO_STATUS_OTVOREN = "O";
		public static final String KONTO_STATUS_ZATVOREN = "Z";
		public static final String KONTO_VKONT_KUNSKI = "H";
		public static final String KONTO_VKONT_DEVIZNI = "D";
		public static final String KONTO_VKONT_VALUTNA_KLAUZULA = "V";
		public static final String KONTO_VKONT_JEDNOSMJERNA_VALUTNA_KLAUZULA = "J";

		public static final String KONTO_AKTIVA = "A";
		public static final String KONTO_PASIVA = "P";

		public static final String KONTO_PODBILANS_AKTIVNI = "A";
		public static final String KONTO_PODBILANS_PASIVNI = "P";
		public static final String KONTO_PODBILANS_NEUTRALNI = "N";

		public static final Integer KONTO_TECAJNERAZLIKE_IMA = 1;
		public static final Integer KONTO_TECAJNERAZLIKE_NEMA = 0;

		public static final String VERIFIKATOR_STATUS_AKTIVAN = "A";

		public static final String DRZAVA_SIFRA_DOMICIL = "191";
		public static final String DRZAVA_NAZIV_DOMICIL = "HRVATSKA";

		// tip_veze
		@Deprecated
		public static final Integer TIP_VEZE_SIFRA_OPUNOMOCENIK_UPP = 12;
		@Deprecated
		public static final Integer TIP_VEZE_SIFRA_OPUNOMOCENIK_IPP = 13;
		@Deprecated
		public static final Integer TIP_VEZE_SIFRA_SUVLASNICI = 121;
		@Deprecated
		public static final Integer TIP_VEZE_SIFRA_OPUNOMOCENIK = 30;
		@Deprecated
		public static final Integer TIP_VEZE_SIFRA_ZAKONSKI_ZASTUPNIK = 31;

		public static final Integer TIP_VEZE_SIFRA_PRODAVATELJA = 140;
		public static final Integer TIP_VEZE_SIFRA_REMITENTA = 108;
		public static final Integer TIP_VEZE_SIFRA_DODATNI_VLASNIK_PARTIJE = 70;
		public static final Integer TIP_VEZE_SIFRA_POTPISNIK_KARTONA = 71;

		public static final Integer GRUPA_VEZE_SIFRA_NEDEFINIRANO = 99;

		// tip obavijesti
		public static final Integer TIPOBAVIJESTI_SIFRATOB_IZVOD_DOMICILNI = 1;
		public static final Integer TIPOBAVIJESTI_SIFRATOB_OBAVIJEST_O_PROMJENI_NA_RACUNU_SMS = 10;

		// nad dijelovi
		public static final String NADDIO_OZNKANAD_GLAVNICA = "01";
		public static final String NADDIO_OZNKANAD_GLAVNICA_U_BLOKADI = "009";
		public static final String NADDIO_OZNKANAD_AKTIVNA_KAMATA = "021";
		public static final String NADDIO_OZNKANAD_PASIVNA_KAMATA = "022";
		public static final String NADDIO_OZNKANAD_AKTIVNA_EVIDENTNA_KAMATA = "023";
		public static final String NADDIO_OZNKANAD_PASIVNA_EVIDENTNA_KAMATA = "024";
		public static final String NADDIO_OZNKANAD_EVIDENTNA_KAMATA_PABA_RIZNICA = "20";
		public static final String NADDIO_OZNKANAD_ZATEZNA_KAMATA = "031";
		public static final String NADDIO_OZNKANAD_OBRACUNATA_NAKNADA = "34";
		public static final String NADDIO_OZNKANAD_PRIHOD_NAKNADA = "35";
		public static final String NADDIO_OZNKANAD_OBRACUNATA_NAKNADA_SWIFT = "38";
		public static final String NADDIO_OZNKANAD_OBRACUNATA_NAKNADA_IRA = "041";
		public static final String NADDIO_OZNKANAD_IZDVAJANJE_NKS_1005 = "41";
		public static final String NADDIO_OZNKANAD_POREZ_NAKNADA = "50";
		public static final String NADDIO_OZNKANAD_KLIRING_RTGS = "200";
		public static final String NADDIO_OZNKANAD_KLIRING_GC = "201";
		public static final String NADDIO_OZNKANAD_TECAJNE_RAZLIKE_DUGOVNO = "36";
		public static final String NADDIO_OZNKANAD_TECAJNE_RAZLIKE_POTRAZNO = "36";
		public static final String NADDIO_OZNKANAD_TECAJNE_RAZLIKE_DUGOVNO_MVR_FO = "16";
		public static final String NADDIO_OZNKANAD_TECAJNE_RAZLIKE_POTRAZNO_MVR_FO = "16";
		public static final String NADDIO_OZNKANAD_TECAJNE_RAZLIKE_RETAIL = "16";
		public static final String NADDIO_OZNKANAD_TECAJNE_RAZLIKE_SREDNJI_TECAJ_DUGOVNO = "065";
		public static final String NADDIO_OZNKANAD_TECAJNE_RAZLIKE_SREDNJI_TECAJ_POTRAZNO = "066";
		public static final String NADDIO_OZNKANAD_TECAJNE_RAZLIKE_SREDNJI_TECAJ_WESTERN_UNION_DUGOVNO = "067";
		public static final String NADDIO_OZNKANAD_TECAJNE_RAZLIKE_SREDNJI_TECAJ_WESTERN_UNION_POTRAZNO = "068";
		public static final String NADDIO_OZNKANAD_NERASPOREDENA_UPLATA = "071"; // u pravilu općenita ili SWIFT
		public static final String NADDIO_OZNKANAD_NERASPOREDENA_UPLATA_HSVP = "072";
		public static final String NADDIO_OZNKANAD_NERASPOREDENA_UPLATA_NKS = "073";
		public static final String NADDIO_OZNKANAD_NERASPOREDENA_UPLATA_TARGET2 = "074";
		public static final String NADDIO_OZNKANAD_NERASPOREDENA_UPLATA_EURONKS = "075";
		public static final String NADDIO_OZNKANAD_NERASPOREDENA_UPLATA_OSTALO = "076";
		public static final String NADDIO_OZNKANAD_NERASPOREDENA_UPLATA_INSTANT_PAYMNET = "073";
		public static final String NADDIO_OZNKANAD_NERASPOREDENA_UPLATA_PLATNI_PROMET_SQL = "'" + NADDIO_OZNKANAD_NERASPOREDENA_UPLATA_HSVP + "','" + NADDIO_OZNKANAD_NERASPOREDENA_UPLATA_NKS + "','" + NADDIO_OZNKANAD_NERASPOREDENA_UPLATA_TARGET2 + "','" + NADDIO_OZNKANAD_NERASPOREDENA_UPLATA_EURONKS + "','" + NADDIO_OZNKANAD_NERASPOREDENA_UPLATA_OSTALO + "','" + NADDIO_OZNKANAD_NERASPOREDENA_UPLATA_INSTANT_PAYMNET + "'";
		public static final List<String> NADDIO_OZNKANAD_NERASPOREDENA_UPLATA_PLATNI_PROMET_LIST = Arrays.asList(NADDIO_OZNKANAD_NERASPOREDENA_UPLATA_HSVP, NADDIO_OZNKANAD_NERASPOREDENA_UPLATA_NKS, NADDIO_OZNKANAD_NERASPOREDENA_UPLATA_TARGET2, NADDIO_OZNKANAD_NERASPOREDENA_UPLATA_EURONKS, NADDIO_OZNKANAD_NERASPOREDENA_UPLATA_OSTALO, NADDIO_OZNKANAD_NERASPOREDENA_UPLATA_INSTANT_PAYMNET);
		public static final String NADDIO_OZNKANAD_PRIJELAZNI_RACUN_GLAVNICE = "100";
		public static final String NADDIO_OZNKANAD_NAKNADA_VREMENSKO_RAZGRANICENJE = "171";
		public static final String NADDIO_OZNKANAD_SITNA_SALDA_PRIHOD = "222";
		public static final String NADDIO_OZNKANAD_SITNA_SALDA_TROSAK = "223";
		public static final String NADDIO_OZNKANAD_ZAMRZNUTI_PNOP_OKVIR = "239";
		public static final String NADDIO_OZNKANAD_PROLAZNI_DEVIZNI_RACUN = "300";
		public static final String NADDIO_OZNKANAD_KORISTENJE_AKREDITIVA = "303";
		public static final String NADDIO_OZNKANAD_NERASPOREDENA_UPLATA_OSNIVACKOG_POLOGA = "321";
		public static final String NADDIO_OZNKANAD_PASIVNA_KAMATA_SIMULACIJA = "322";
		public static final String NADDIO_OZNKANAD_NEDOSPJELA_KNS = "323";
		public static final String NADDIO_OZNKANAD_PASIVNA_EVIDENTNA_KAMATA_SIMULACIJA = "324";
		public static final String NADDIO_OZNKANAD_DOSPJELA_KNS = "325";
		public static final String NADDIO_OZNKANAD_OBVEZA_PREMA_BANCI = "03";
		public static final String NADDIO_OZNKANAD_POTRAZIVANJE_OD_BANAKA = "04";
		public static final String NADDIO_OZNKANAD_PROLAZNI_DEVIZNI_RIZNICA = "99";
		public static final String NADDIO_OZNKANAD_STAVKA_VANBILANCE_RIZNICA = "352";
		public static final String NADDIO_OZNKANAD_BLAGAJNA_PRIJELAZNI_RACUN = "601";
		public static final String NADDIO_OZNKANAD_BLAGAJNA_GOTOVINA_NA_PUTU = "602";
		public static final String NADDIO_OZNKANAD_OTPIS_PLASMAN = "831";
		public static final String NADDIO_OZNKANAD_OSTALE_OBVEZE = "900";
		public static final String NADDIO_OZNKANAD_WESTERN_UNION_ODLJEV = "960";
		public static final String NADDIO_OZNKANAD_WESTERN_UNION_PRILJEV = "961";
		public static final String NADDIO_OZNKANAD_ANALITIKE_UPLATA_PROLAZNI = "149";
		public static final String NADDIO_OZNKANAD_REZERVACIJA = "131";
		public static final String NADDIO_OZNKANAD_REZERVACIJA_PROTUKONTO = "860";
		public static final String NADDIO_OZNKANAD_OBVEZA_S_ODLOZENIM_POKRICEM_NOSTRO = "1000";
		public static final String NADDIO_OZNKANAD_OBVEZA_S_POLOZENIM_POKRICEM_NOSTRO = "1001";
		public static final String NADDIO_OZNKANAD_OBVEZA_S_ODLOZENIM_POKRICEM_LORO = "1002";
		public static final String NADDIO_OZNKANAD_OBVEZA_S_POLOZENIM_POKRICEM_LORO = "1003";
		public static final String NADDIO_OZNKANAD_UZETI_KREDITI_PROTUSTAVKA_VANBILANCE = "353";
		public static final String NADDIO_OZNKANAD_DEVIZNO_TRZISTE_VANBILANCA_PRODAJA = "1900";
		public static final String NADDIO_OZNKANAD_DEVIZNO_TRZISTE_VANBILANCA_KUPNJA = "1902";
		public static final String NADDIO_OZNKANAD_TROSAK_PROVIZIJE_KONTOKORENTA = "TK";
		public static final String NADDIO_OZNKANAD_DOSPJELA_POTRAZIVANJA_AKREDITIVI_GARANCIJE_JAMSTVA = "GJ";

		public static final String NADDIO_OZNKANAD_SMECE = "999";
		public static final String NADDIO_OZNKANAD_NAKNADA_UPP = "IFBT72";

		public static final String NADDIO_OZNKANAD_DEPOZITI_DIO_ZA_ISPLATU = "500";
		public static final String NADDIO_OZNKANAD_DEPOZITI_AVISTA_NEISPLACENA_KAMATA = "501";
		public static final String NADDIO_OZNKANAD_DEPOZITI_PASIVNA_NEDOSPJELA_KAMATA_AVISTA = "502";
		public static final String NADDIO_OZNKANAD_DEPOZITI_OSTALE_OBAVEZE = "560";
		public static final String NADDIO_OZNKANAD_DEPOZITI_OSLOBODENO_POKRICE_PO_KREDITU = "562";
		public static final String NADDIO_OZNKANAD_TROSAK_PASIVNA_KAMATA_KM = "224";
		public static final String NADDIO_OZNKANAD_TROSAK_PASIVNA_KAMATA_DEVIZE = "227";
		public static final String NADDIO_OZNKANAD_VREMENSKO_RAZGRANICENJE = "241";
		public static final String NADDIO_OZNKANAD_VREMENSKO_RAZGRANICENJE_KARTICE_12MJESECI = "541";
		public static final String NADDIO_OZNKANAD_PTIHOD_NAKNADA = "IFPRI14";
		public static final String NADDIO_OZNKANAD_NAKNADA_POREZ = "249";

		public static final String NADDIO_OZNKANAD_POREZ_PO_OSNOVU_KAMATA = "58";
		public static final String NADDIO_OZNKANAD_PRIREZ_PO_OSNOVU_KAMATA = "59";

		public static final String NADDIO_VRDUGNAD_NEDOSPJELA_KAMATA = "NK";
		public static final String NADDIO_VRDUGNAD_AVISTA_DIO = "AV";
		public static final String NADDIO_VRDUGNAD_WESTERN_UNION = "WU";
		public static final String NADDIO_VRDUGNAD_TECAJNE_RAZLIKE = "TR";

		public static final String NADDIO_KAMATNAD_AVISTA = "AV";

		// krediti
		public static final String NADDIO_OZNKANAD_PROLAZNI_PLASMAN_KREDITA = "148";

		public static final String NADDIO_VRDUGNAD_GLAVNICA = "GL";
		public static final String NADDIO_VRDUGNAD_DOSPIJELI_DUG = "DD";
		public static final String NADDIO_VRDUGNAD_DOSPIJELI_DUG_REDOVNA_KAMATA = "DK";
		public static final String NADDIO_VRDUGNAD_DOSPIJELI_DUG_ZATEZNA_KAMATA = "DZ";
		public static final String NADDIO_VRDUGNAD_DOSPIJELI_DUG_NAKNADA = "DN";
		public static final String NADDIO_VRDUGNAD_DOSPIJELI_DUG_GLAVNICA = "DG";
		public static final String NADDIO_VRDUGNAD_ISKLJUCENI_PRIHOD = "IS";
		public static final String NADDIO_VRDUGNAD_POSEBNE_REZERVE = "PR";
		public static final String NADDIO_VRDUGNAD_OTPISANA_POTRAZIVANJA = "OT";
		public static final String NADDIO_VRDUGNAD_UTUZENA_POTRAZIVANJA = "UT";
		public static final String NADDIO_VRDUGNAD_OSTALA_POTRAZIVANJA = "OS";
		public static final String NADDIO_VRDUGNAD_PROTUSTAVKE = "PS";
		public static final String NADDIO_VRDUG_KREDITI_ODOBRENI_NEISKORISTENI_KREDIT = "OK";
		public static final String NADDIO_BILANNAD_BILANCNI = "B";
		public static final String NADDIO_BILANNAD_VANBILANCNI = "V";
		public static final String NADDIO_VRDUGNAD_OV = "OV";

		public static final String NADDIO_OZNKANAD_LIST_TECAJNE_RAZLIKE_SQL_CONDITION = "'" + NADDIO_OZNKANAD_TECAJNE_RAZLIKE_DUGOVNO + "','" + NADDIO_OZNKANAD_TECAJNE_RAZLIKE_POTRAZNO + "','" + NADDIO_OZNKANAD_TECAJNE_RAZLIKE_SREDNJI_TECAJ_DUGOVNO + "','" + NADDIO_OZNKANAD_TECAJNE_RAZLIKE_SREDNJI_TECAJ_POTRAZNO + "','" + NADDIO_OZNKANAD_SITNA_SALDA_PRIHOD + "','"
				+ NADDIO_OZNKANAD_SITNA_SALDA_TROSAK + "'";
		public static final List<String> NADDIO_OZNKANAD_LIST_TECAJNE_RAZLIKE = Arrays.asList(new String[] { NADDIO_OZNKANAD_TECAJNE_RAZLIKE_DUGOVNO, NADDIO_OZNKANAD_TECAJNE_RAZLIKE_POTRAZNO, NADDIO_OZNKANAD_TECAJNE_RAZLIKE_SREDNJI_TECAJ_DUGOVNO, NADDIO_OZNKANAD_TECAJNE_RAZLIKE_SREDNJI_TECAJ_POTRAZNO, NADDIO_OZNKANAD_SITNA_SALDA_PRIHOD, NADDIO_OZNKANAD_SITNA_SALDA_TROSAK });

		// Parametar
		public static final Integer PARAMETAR_VRIJEDPAR_DEFAULT_MAXIMUM_NUMBER_OF_SQL_IN_CLAUSE_ELEMENTS = 100;
		public static final String PARAMETAR_SIFRAPAR_PLATNIPROMET_NEGATIVNA_TEC_RAZLIKA = "NEGTEC";
		public static final String PARAMETAR_SIFRAPAR_PLATNIPROMET_POZITIVNA_TEC_RAZLIKA = "POZTEC";
		public static final String PARAMETAR_SIFRA_LIMIT = "SPN_RIZ_LIMIT";
		public static final String PARAMETAR_VALUTE_OSTALO = "DIO_PAR_VAL";
		public static final String PARAMETAR_SIFRAPAR_MIN_DATUM_OBRACUNA_NEDOSPJELE_KAM_FIZICKI = "MinDatNedF";
		public static final String PARAMETAR_SIFRAPAR_MIN_DATUM_OBRACUNA_NEDOSPJELE_KAM_PRAVNI = "MinDatNedP";
		public static final String PARAMETAR_SIFRAPAR_GO_LIVE_DATE = "GoLiveDate";
		public static final String PARAMETAR_SIFRAPAR_GO_LIVE_DATE_B3 = "GoLiveDateB3";
		public static final String PARAMETAR_SIFRAPAR_IS_STORNIRANJE_NEDOSPJELE_FIZICKI = "ORF_NED_STOR";
		public static final String PARAMETAR_SIFRAPAR_STARI_OBRACUN_KAMATE = "StariObrKam";
		public static final String DOZNAKA_DATUM_VALUTE_UNAPRIJED = "DOZN_DAT_VAL";
		public static final String PARAMETAR_SIFRAPAR_MAIL_ADRESA_BACKOFFICE = "MAIL_BACKOFF";
		public static final String PARAMETAR_SIFRAPAR_MAIL_ADRESA_BACKOFFICE_TEST = "MAIL_BACKTST";
		public static final String PARAMETAR_SIFRAPAR_GORNJA_GRANICA_DODATNE_KAMATNE_STOPE = "GG_DKS";
		public static final String PARAMETAR_SIFRAPAR_VERIFIKATOR_SAM_SEBE = "SAL_VERIF";
		public static final String PARAMETAR_SIFRAPAR_VERIFIKATOR_SAM_SEBE_SUBOTA = "SAL_VERIF_SUB";
		public static final String PARAMETAR_SIFRAPAR_SPN_MAX_UPLATA = "SPN_GOT_MAX";
		public static final String PARAMETAR_SIFRAPAR_LINK_TECAJNA_LISTA = "CurrExchLink";
		public static final String SIFRAPAR_IZVOD_OUTER_SYSTEM_CONTROL = "IZVD_OUT_SYS";

		// OKVIRI
		public static final String PARAMETAR_SIFRAPAR_MINIMALNI_MJESECNI_OKVIR_DJELATNIK = "OKVMJMIND";
		public static final String PARAMETAR_SIFRAPAR_MAXIMALNI_MJESECNI_OKVIR_DJELATNIK = "OKVMJMAXD";
		public static final String PARAMETAR_SIFRAPAR_MINIMALNI_MJESECNI_OKVIR_GRADANI = "OKVMJMING";
		public static final String PARAMETAR_SIFRAPAR_MAXIMALNI_MJESECNI_OKVIR_GRADANI = "OKVMJMAXG";
		public static final String PARAMETAR_SIFRAPAR_MINIMALNI_MJESECNI_OKVIR_UMIROVLJENIK = "OKVMJMINM";
		public static final String PARAMETAR_SIFRAPAR_MAXIMALNI_MJESECNI_OKVIR_UMIROVLJENIK = "OKVMJMAXM";
		public static final String PARAMETAR_SIFRAPAR_MJESECNI_OKVIR_TOLERANCIJA = "OKVMJTOL";

		public static final String PARAMETAR_SIFRAPAR_MINIMALNI_DNEVNI_OKVIR_DJELATNIK = "OKVDNMIND";
		public static final String PARAMETAR_SIFRAPAR_MAXIMALNI_DNEVNI_OKVIR_DJELATNIK = "OKVDNMAXD";
		public static final String PARAMETAR_SIFRAPAR_MINIMALNI_DNEVNI_OKVIR_GRADANI = "OKVDNMING";
		public static final String PARAMETAR_SIFRAPAR_MAXIMALNI_DNEVNI_OKVIR_GRADANI = "OKVDNMAXG";
		public static final String PARAMETAR_SIFRAPAR_DNEVNI_OKVIR_TOLERANCIJA = "OKVDNTOL";

		public static final String PARAMETAR_SIFRAPAR_SAL_VERIF = "SAL_VERIF";
		public static final String PARAMETAR_SIFRAPAR_SAL_VERIF_SUB = "SAL_VERIF_SUB";
		public static final String PARAMETAR_SIFRAPAR_GRUPE_RACUNA_STANOVNISTVO = "GR_RAC_STAN";
		public static final String PARAMETAR_SIFRAPAR_GRUPE_RACUNA_SALTER_VIRMAN_TERECENJE = "GR_RAC_SAL_VT";
		public static final String PARAMETAR_SIFRAPAR_GRUPE_RACUNA_SALTER_VIRMAN_ODOBRENJE = "GR_RAC_SAL_VO";

		public static final String PARAMETAR_SIFRAPAR_PNOP_PROD = "PNOP_PROD";
		public static final String PARAMETAR_SIFRAPAR_PNOP_XSD = "PNOP_XSD";

		public static final String PARAMETAR_SIFRAPAR_SINGLE_PROMET_PER_NALOG_ACTIVE = "SINGLE_TRANS";
		public static final String PARAMETAR_SIFRAPAR_IZVOD_OUTER_SYSTEM_CONTROL = "IZVD_OUT_SYS";
		public static final String PARAMETAR_SIFRAPAR_IZVOD_BOOKING_BACK = "IZVOD_BOOKING";
		public static final String PARAMETAR_SIFRAPAR_DIRECT_TRANSINO_ACTIVATE = "DRCT_TINO_ACT";
		public static final String PARAMETAR_SIFRAPAR_MAX_BROJ_SLOGOVA_BEZ_FILTRA = "MAX_BR_SLOG";
		public static final String PARAMETAR_SIFRAPAR_IRREGULAR_CHARACTERS_CONTROL = "IRREG_CHARS_C";

		public static final String PARAMETAR_SIFRAPAR_LIKVIDATOR_KONVERZIJA_BLOKIRANA_PARTIJA = "BLK_PO_DEV";
		public static final String PARAMETAR_SIFRAPAR_LIKVIDATOR_KONVERZIJA_BLOKIRANA_PARTIJA_TIP_NALOGA = "BLK_PO_DEV_TN";

		public static final String PARAMETAR_SIFRAPAR_FAX_SLANJE_OBAVIJESTI_ODLJEV_PREFIX = "FDO";
		public static final String PARAMETAR_SIFRAPAR_SERVER_DIREKTORIJ = "ftpDir";
		public static final String PARAMETAR_SIFRAPAR_SERVER_ARHIVA = "ftpArh";

		public static final String PARAMETAR_SIFRAPAR_MBU_DATOTEKE_IZLAZ = "MBUIz";
		public static final String PARAMETAR_SIFRAPAR_MBU_DATOTEKE_IZLAZ_HOST = "MBUIzftpHost";
		public static final String PARAMETAR_SIFRAPAR_MBU_DATOTEKE_IZLAZ_DIR = "MBUIzftpDir";
		public static final String PARAMETAR_SIFRAPAR_MBU_DATOTEKE_ARHIVA = "MBUAr";
		public static final String PARAMETAR_SIFRAPAR_MBU_DATOTEKE_ARHIVA_HOST = "MBUArftpHost";
		public static final String PARAMETAR_SIFRAPAR_MBU_DATOTEKE_ARHIVA_DIR = "MBUArftpDir";
		public static final String PARAMETAR_SIFRAPAR_KARTICNO_NAPLATA_PRAVNE = "KART_NAPLATA";

		public static final String PARAMETAR_SIFRAPAR_LIKVIDATOR_DOZVOLA_PO_BLOKIRANIM_PARTIJAMA_FO = "BLK_FO_OK";
		public static final String PARAMETAR_SIFRAPAR_LIKVIDATOR_INT_PRIJENOS_BEZ_CHK_SALDO = "NO_SALDO";
		// ciklusi i vremena INT, NKS i HSVP naloga
		public static final String PARAMETAR_SIFRAPAR_VRIJEME_UNOSA_INTERNIH_NALOGA = "INTNA";
		public static final String PARAMETAR_SIFRAPAR_VRIJEME_UNOSA_INTERNIH_NALOGA_SUBOTA = "INTNA6";
		public static final String PARAMETAR_SIFRAPAR_VRIJEME_UNOSA_ZA_CIKLUS_NKS_1 = "NKSc1";
		public static final String PARAMETAR_SIFRAPAR_VRIJEME_UNOSA_ZA_CIKLUS_NKS_2 = "NKSc2";
		public static final String PARAMETAR_SIFRAPAR_VRIJEME_UNOSA_ZA_CIKLUS_NKS_3 = "NKSc3";
		public static final String PARAMETAR_SIFRAPAR_VRIJEME_UNOSA_ZA_CIKLUS_NKS_4 = "NKSc4";
		public static final String PARAMETAR_SIFRAPAR_VRIJEME_UNOSA_ZA_CIKLUS_NKS_ZADNJI = "NKSc4";
		public static final String PARAMETAR_SIFRAPAR_VRIJEME_UNOSA_ZA_CIKLUS_NKS_ZADNJI_IBG = "NKSc4IBG";
		public static final String PARAMETAR_SIFRAPAR_VRIJEME_DANASNJI_DATUM_VALUTE_HSVP_NALOGA = "HSVPd";
		public static final String PARAMETAR_SIFRAPAR_VRIJEME_DANASNJI_DATUM_VALUTE_T2_NALOGA = "T2d";
		public static final String PARAMETAR_SIFRAPAR_VRIJEME_DANASNJI_DATUM_VALUTE_IPAY_NALOGA = "IPayd";
		public static final String PARAMETAR_SIFRAPAR_VRIJEME_DANASNJI_DATUM_VALUTE_SWIFT_NALOGA = "Swiftd";
		public static final String PARAMETAR_SIFRAPAR_VRIJEME_DANASNJI_DATUM_VALUTE_T2_NALOGA_IB = "T2dIB";
		public static final String PARAMETAR_SIFRAPAR_VRIJEME_DANASNJI_DATUM_VALUTE_EURONKS_NALOGA = "EURNKSd";
		public static final String PARAMETAR_SIFRAPAR_VRIJEME_DANASNJI_DATUM_VALUTE_EURONKS_NALOGA_IB = "EURNKSdIB";
		public static final String PARAMETAR_SIFRAPAR_VRIJEME_DANASNJI_DATUM_VALUTE_HSVP_NALOGA_IBG = "HSVPibg";
		public static final String PARAMETAR_SIFRAPAR_MAX_BROJ_DANA_ZA_ZADAVANJE_NALOGA = "MAXd";
		public static final String PARAMETAR_SIFRAPAR_CUT_OFF_TIME_SWIFT = "SwiftCutOffTm";

		public static final String PARAMETAR_SIFRAPAR_FIZ_KOMITENT_DOZVOLJENE_POSLOVNICE = "KOM_FIZ_POJED";
		public static final String PARAMETAR_SIFRAPAR_FIZ_KOMITENT_DOZVOLJENI_SEKTORI = "KOM_FIZ_SEKTO";
		public static final String PARAMETAR_SIFRAPAR_PRAV_KOMITENT_DOZVOLJENE_POSLOVNICE = "KOM_PRA_POJED";
		public static final String PARAMETAR_SIFRAPAR_PRAV_KOMITENT_DOZVOLJENI_SEKTORI = "KOM_PRA_SEKTO";
		public static final String PARAMETAR_SIFRAPAR_IZVJESCE_SUMA_PO_RAZINI_POVJERLJIVOSTI = "REP_RAZ_POVJ";

		// HUB 3 migracija
		public static final String PARAMETAR_SIFRAPAR_HUB3_MIGRACIJA_START = "HUB_MIG_START";
		public static final String PARAMETAR_SIFRAPAR_HUB3_MIGRACIJA_END = "HUB_MIG_END";
		public static final String PARAMETAR_SIFRAPAR_TERROR_THRESHOLD = "TERROR_THRESH";
		public static final String PARAMETAR_SIFRAPAR_FORMAT_RACUNA_NKS_IBAN = "FormatRacuna";
		public static final String PARAMETAR_SIFRAPAR_HUB3_BRISANJE_MIGRACIJA = "HUB3_DELETE";
		public static final String PARAMETAR_SIFRAPAR_MAX_KAM_STOPA_FIZICKE = "MAXKAMSTF";
		public static final String PARAMETAR_SIFRAPAR_MAX_KAM_STOPA_PRAVNE = "MAXKAMSTP";

		public static final String PARAMETAR_SIFRAPAR_TRAJANJE_PREKONOCNOG_DEPOZITA = "TRAPREKDEP";
		public static final String PARAMETAR_SIFRAPAR_PUTANJA_JOPPD_OUT = "CD_JOPPD_OUT";
		public static final String PARAMETAR_SIFRAPAR_CHK_JOP = "CHK_JOPPD";

		// banka BOX
		public static final String PARAMETAR_SIFRAPAR_BANKA_BOX = "BNKBOX";
		public static final String PARAMETAR_SIFRAPAR_BANKA_BOX_IBAN = "BNKBOXIBAN";
		public static final String PARAMETAR_SIFRAPAR_BANKA_IBAN = "BNKIBAN";
		// APR dijelovi koji podliježu verifikaciji kod šalterskih transakcija
		public static final String PARAMETAR_SIFRAPAR_APR_DIJELOVI_ZA_VERIFIKACIJU = "VERIF_ST_APR";
		// verifikacija oročenih depozita građana
		public static final String PARAMETAR_SIFRAPAR_VERIFIKACIJA_RAZROCENJA_DEPOZITA_GRADJANA = "VERIF_RAZ_DEP";
		public static final String PARAMETAR_SIFRAPAR_FAX_MAIL = "FaxMail";
		// proizvod
		public static final String PROIZVOD_STATUPRO_OTVOREN = "O";
		public static final String PROIZVOD_STATUPRO_NEAKTIVAN = "N"; // neaktivan
																		// status
																		// u
																		// kojem
																		// proizvod
																		// čeka
																		// aktivaciju
																		// kako
																		// bi se
																		// mogle
																		// otvarati
																		// partije
																		// po
																		// njemu
		public static final String PROIZVOD_STATUPRO_ZATVOREN = "Z";
		public static final String PROIZVOD_STATUPRO_MIGRACIJA = "M";
		// razred
		public static final String RAZRED_STATURAZ_OTVOREN = "O";
		public static final String RAZRED_STATURAZ_ZATVOREN = "Z";
		public static final String RAZRED_STATURAZ_MIGRACIJA = "M";
		public static final String RAZRED_TIP_OBRACUNA_UGOVORENI_IZNOS = "U";
		public static final String RAZRED_TIP_OBRACUNA_PROSJEK = "P";
		public static final String RAZRED_TIP_OBRACUNA_STANJE = "S";
		public static final String RAZRED_TIP_OBRACUNA_VREMENSKI = "V";
		// stavka proizvoda
		public static final String STAVKA_PROIZVODA_ENTITET_PARTIJA_DEVIZNOG_DEPOZITA = "PartijaDeviznogDepozita";
		public static final String STAVKA_PROIZVODA_POLJE_SEKTOR = "sektor";
		public static final String STAVKA_PROIZVODA_POLJE_ULOGA = "oroleDep";
		public static final String STAVKA_PROIZVODA_POLJE_ROCNOST = "rocntDep";
		public static final String STAVKA_PROIZVODA_POLJE_TIP_PARTIJE = "tipPartije";
		// način obračuna kamate
		public static final String NACOBPAR_PROPORCIONALNI_OBRACUN = "1";
		public static final String NACOBPAR_KONFORMNI_OBRACUN = "2";
		public static final String NACOBPAR_ANTICIPATIVNI_OBRACUN = "3";
		public static final String NACOBPAR_PROPORCIONALNI_360OBRACUN = "4";
		public static final String NACOBPAR_KONFORMNI_360OBRACUN = "5";
		public static final String NACOBPAR_DISKONTNI = "6";
		// tip kamatne stope
		public static final Integer TIP_KAMATNE_STOPE_LIMIT_REDOVNA = 1;
		public static final Integer TIP_KAMATNE_STOPE_LIMIT_ZATEZNA = 2;
		public static final Integer TIP_KAMATNE_STOPE_UZETI_KREDIT_REDOVNA = 3;
		public static final Integer TIP_KAMATNE_STOPE_UZETI_KREDIT_ZATEZNA = 4;
		public static final Integer TIP_KAMATNE_STOPE_KREDIT_DISKONT_PROPOR_KALENDARSKI = 5;
		public static final Integer TIP_KAMATNE_STOPE_KREDIT_DISKONT_PROPOR_360_DANA = 6;
		public static final Integer TIP_KAMATNE_STOPE_OROCENJE = 40;
		public static final Integer TIP_KAMATNE_STOPE_OROCENJE_DODATNA = 41;
		public static final Integer TIP_KAMATNE_STOPE_OROCENJE_EKS = 42;
		public static final Integer TIP_KAMATNE_STOPE_AVISTA_ZA_OROCENJE = 43;
		//	public static final Integer TIP_KAMATNE_STOPE_OROCENJE_KONFORMNO = 46;
		public static final Integer TIP_KAMATNE_STOPE_OROCENJE_RAZROCENJE_KORISNICKA = 47;
		public static final Integer TIP_KAMATNE_STOPE_OROCENJE_RAZROCENJE = 48;
		public static final Integer TIP_KAMATNE_STOPE_KREDIT_ZATEZNA_PROPORCIONALNO = 21;
		public static final Integer TIP_KAMATNE_STOPE_KREDIT_EKS = 22;
		public static final Integer TIP_KAMATNE_STOPE_KREDIT_REDOVNA_PROPORCIONALNO = 25;
		public static final Integer TIP_KAMATNE_STOPE_KREDIT_REDOVNA_KONFORMNO = 24;
		//public static final Integer TIP_KAMATNE_STOPE_ANTICIPATIVNO = 25;
		public static final Integer TIP_KAMATNE_STOPE_DISKONTNO = 26;
		public static final Integer TIP_KAMATNE_STOPE_KREDIT_REDOVNA_PROPORCIONALNO_360 = 27;
		public static final Integer TIP_KAMATNE_STOPE_KREDIT_INTERKALARNA_PROPORCIONALNO = 23;
		public static final Integer TIP_KAMATNE_STOPE_KREDIT_INTERKALARNA_KONFORMNO = 28;
		public static final Integer TIP_KAMATNE_STOPE_KREDIT_INTERKALARNA_PROPORCIONALNO_360 = 34;
		public static final Integer TIP_KAMATNE_STOPE_KREDIT_REDOVNA_KONFORMNO_360 = 34;
		public static final Integer TIP_KAMATNE_STOPE_KREDIT_INTERKALARNA_KONFORMNO_360 = 36;
		public static final Integer TIP_KAMATNE_STOPE_KREDIT_BONUS = 63;
		public static final Integer TIP_KAMATNE_STOPE_KREDIT_MAXIMALNA = 30;
		public static final String TIP_KAMATNE_STOPE_REDOVNA = "R";
		public static final String TIP_KAMATNE_STOPE_NEDOSPJELA = "N";
		public static final String TIP_KAMATNE_STOPE_ZATEZNA = "Z";
		public static final Integer TIP_KAMATNE_STOPE_ESKONT_REDOVNA = 10;
		public static final Integer TIP_KAMATNE_STOPE_ESKONT_ZATEZNA = 11;
		public static final String TIP_KAMATE_OBILJTKS_OSNOVNA_REDOVNA = "1";
		public static final String TIP_KAMATE_OBILJTKS_OSNOVNA_DODATNA = "2";
		public static final String TIP_KAMATE_OBILJTKS_EFEKTIVNA = "5";
		public static final String TIP_KAMATE_OBILJTKS_OROCENJE_AVISTA = "6";
		public static final Integer TIP_KAMATNE_STOPE_KOLATERAL_REDOVNA = 37;
		public static final Integer TIP_KAMATNE_STOPE_KOLATERAL_ZATEZNA = 38;
		public static final Integer TIP_KAMATNE_STOPE_DEVIZNI_DEPOZIT_DANI = 6;
		public static final Integer TIP_KAMATNE_STOPE_DEVIZNI_DEPOZIT_PRIMLJENI = 7;
		// rocnost
		public static final String ROCNOST_VRSTA_KAMATA = "kamata";
		public static final String ROCNOST_VRSTA_KONTO = "konto";

		public static final Integer POREZ_SIFRA_POREZA_POREZ_NA_KAMATU = 5;
		public static final String OPCINA_SIFRA_OPCINE_NEREZIDENT = "00000";
		public static final String OPCINA_SIFRA_OPCINE_NEPOZNATO = "99999";

		public static final Integer TIP_NALOGA_SIFRAPOD_BLOKRAC_LOG = 10001;
		public static final Integer TIP_NALOGA_SIFRAPOD_DEBLOKRAC_LOG = 10002;
		public static final Integer TIP_NALOGA_SIFRAPOD_NEAKTIVAN_LOG = 10003;
		public static final Integer TIP_NALOGA_SIFRAPOD_AKTIVAN_LOG = 10004;
		public static final Integer TIP_NALOGA_SIFRAPOD_OSNOVNI_CJENIK_LOG = 10005;
		public static final Integer TIP_NALOGA_SIFRAPOD_POVLASTENA_PO_PARTIJI = 10006;
		public static final Integer TIP_NALOGA_SIFRAPOD_POVLASTENA_PO_PAKETU = 10007;
		public static final Integer TIP_NALOGA_SIFRAPOD_POVLASTENA_PO_PROIZVODU = 10008;
		public static final Integer TIP_NALOGA_SIFRAPOD_VEZA_LOG = 10009;
		public static final Integer TIP_NALOGA_SIFRAPOD_PARTIJA_BLAGAJNE_LOG = 10010;
		public static final Integer TIP_NALOGA_SIFRAPOD_PARTIJA_RETAIL_LOG = 10011;
		public static final Integer TIP_NALOGA_SIFRAPOD_PARTIJA_OROCENJA_FIZICKI_UPIS_LOG = 10012;
		public static final Integer TIP_NALOGA_SIFRAPOD_PARTIJA_OROCENJA_PRAVNI_UPIS_LOG = 10013;
		public static final Integer TIP_NALOGA_SIFRAPOD_PARTIJA_OROCENJA_FIZICKI_AKTIVACIJA_LOG = 10014;
		public static final Integer TIP_NALOGA_SIFRAPOD_PARTIJA_OROCENJA_PRAVNI_AKTIVACIJA_LOG = 10015;
		public static final Integer TIP_NALOGA_SIFRAPOD_PARTIJA_KREDITA_LOG = 10016;
		public static final Integer TIP_NALOGA_SIFRAPOD_PARTIJA_OROCENJA_IZMJENA_PARTIJE_ZA_PRIJENOS_FIZICKI = 10017;
		public static final Integer TIP_NALOGA_SIFRAPOD_PARTIJA_OROCENJA_IZMJENA_PARTIJE_ZA_PRIJENOS_PRAVNI = 10018;
		public static final Integer TIP_NALOGA_SIFRAPOD_PARTIJA_OROCENJA_IZMJENA_DATUMA_DOSPIJECA_PRAVNI = 10019;
		public static final Integer TIP_NALOGA_SIFRAPOD_PARTIJA_OROCENJA_PRIJAVA_PREKIDA_OBNOVE_ROKA_FIZICKI = 10020;
		public static final Integer TIP_NALOGA_SIFRAPOD_PARTIJA_AVPO_LOG = 10031;
		public static final Integer TIP_NALOGA_SIFRAPOD_PARTIJA_PLATNI = 10031;
		public static final Integer TIP_NALOGA_SIFRAPOD_DIO_PARTIJE_UNOS_IZMJENA_PO_KONTU = 10021;
		public static final Integer TIP_NALOGA_SIFRAPOD_PROIZVOD_OTVARANJE_PROIZVODA_FIZICKI = 10022;
		public static final Integer TIP_NALOGA_SIFRAPOD_PROIZVOD_OTVARANJE_PROIZVODA_PRAVNI = 10023;
		public static final Integer TIP_NALOGA_SIFRAPOD_KAMATA_ARH_LOG = 10024;
		public static final Integer TIP_NALOGA_SIFRAPOD_RAZRED_PARTIJA_OTVARANJE = 10025;
		public static final Integer TIP_NALOGA_SIFRAPOD_RAZRED_PROIZVOD_ROCNOST_OTVARANJE = 10026;
		public static final Integer TIP_NALOGA_SIFRAPOD_PARTIJA_MJENJACA_LOG = 10027;
		public static final Integer TIP_NALOGA_SIFRAPOD_PARTIJA_OTVORENA = 10031;
		public static final Integer TIP_NALOGA_SIFRAPOD_KAMATA_LOG = 10033;
		public static final Integer TIP_NALOGA_SIFRAPOD_RAZRED_LOG = 10035;
		public static final Integer TIP_NALOGA_SIFRAPOD_RAZRED_EDIT_LOG = 10036;
		public static final Integer TIP_NALOGA_SIFRAPOD_PARTIJA_TRAJNI_NALOG_LOG = 14161;//10039;
		public static final Integer TIP_NALOGA_SIFRAPOD_PARTIJA_INTERNA_BLOKADA_LOG = 10040;
		public static final Integer TIP_NALOGA_SIFRAPOD_PARTIJA_INTERNA_DEBLOKADA_LOG = 10041;
		public static final Integer TIP_NALOGA_SIFRAPOD_PARTIJA_RETAIL_ZATVARANJE = 10042;
		public static final Integer TIP_NALOGA_SIFRAPOD_PROIZVOD_OTVARANJE_KREDITI = 10043;
		public static final Integer TIP_NALOGA_SIFRAPOD_PARTIJA_TRAJNI_NALOG_ZATVARANJE = 14160;
		public static final Integer TIP_NALOGA_SIFRAPOD_PARTIJA_OROCENJA_FIZICKI_ZATVARANJE = 10045;
		public static final Integer TIP_NALOGA_SIFRAPOD_PARTIJA_OROCENJA_PRAVNI_ZATVARANJE = 10046;
		public static final Integer TIP_NALOGA_SIFRAPOD_PARTIJA_OROCENJA_FIZICKI_IZMJENA = 10047;
		public static final Integer TIP_NALOGA_SIFRAPOD_PREKORACENJE_OTVARANJE = 10048;
		public static final Integer TIP_NALOGA_SIFRAPOD_PARTIJA_OROCENJA_PRAVNI_IZMJENA = 10051;
		public static final Integer TIP_NALOGA_SIFRAPOD_PARTIJA_RETAIL_LIKVIDACIJA = 10061;
		public static final Integer TIP_NALOGA_SIFRAPOD_PARTIJA_OROCENJA_ANEKS_VAL_KLAUZULA = 10065;
		public static final Integer TIP_NALOGA_SIFRAPOD_PARTIJA_OROCENJA_ANEKS_UKIDANJE_VAL_KLAUZULA = 10072;
		public static final Integer TIP_NALOGA_SIFRAPOD_PARTIJA_OROCENJA_ANEKS_IZNOS = 10066;
		public static final Integer TIP_NALOGA_SIFRAPOD_PARTIJA_OROCENJA_ANEKS_ROK = 10067;
		public static final Integer TIP_NALOGA_SIFRAPOD_PARTIJA_OROCENJA_ANEKS_KAM_STOPA = 10068;
		public static final Integer TIP_NALOGA_SIFRAPOD_UTUZENO_OVRHA = 10069;
		public static final Integer TIP_NALOGA_SIFRAPOD_PARTIJA_RETAIL_UTUZENJE = 10076;
		public static final Integer TIP_NALOGA_SIFRAPOD_PARTIJA_RETAIL_UKIDANJE_UTUZENJA = 10077;
		public static final Integer TIP_NALOGA_SIFRAPOD_OBRADA_ISPLATNE_BANKE = 10078;
		public static final Integer TIP_NALOGA_SIFRAPOD_KREIRANJE_SWIFT_PORUKE = 10079;
		public static final Integer TIP_NALOGA_SIFRAPOD_PONISTENJE_OBRADE_DOZNAKE = 10080;
		public static final Integer TIP_NALOGA_SIFRAPOD_OBRADA_TARGET2_DIREKTORIJA = 10081;
		public static final Integer TIP_NALOGA_SIFRAPOD_POVLACENJE_OBAVIJESTI_PRILJEV = 10082;
		public static final Integer TIP_NALOGA_SIFRAPOD_SLANJE_OBAVIJESTI_PRILJEV = 10083;
		public static final Integer TIP_NALOGA_SIFRAPOD_PONOVI_OBAVIJESTI_PRILJEV = 10084;
		public static final Integer TIP_NALOGA_SIFRAPOD_PRIJAVA_POVLASTENOG_TECAJA = 10090;
		public static final Integer TIP_NALOGA_SIFRAPOD_AKTIVACIJA_POVLASTENOG_TECAJA = 10091;
		public static final Integer TIP_NALOGA_SIFRAPOD_POVLACENJE_POVLASTENOG_TECAJA = 10092;
		public static final Integer TIP_NALOGA_SIFRAPOD_POVLACENJE_OBAVIJESTI_ODLJEV = 10093;
		public static final Integer TIP_NALOGA_SIFRAPOD_SLANJE_OBAVIJESTI_ODLJEV = 10094;
		public static final Integer TIP_NALOGA_SIFRAPOD_PONOVI_OBAVIJESTI_ODLJEV = 10095;
		public static final Integer TIP_NALOGA_UCITAVANJE_JRR_DATOTEKE = 10100;
		public static final Integer TIP_NALOGA_KREIRANJE_JRR_DATOTEKE = 10101;
		public static final Integer TIP_NALOGA_SIFRAPOD_SLANJE_SWIFT_PORUKE_MAIL = 10112;
		public static final Integer TIP_NALOGA_SIFRAPOD_OBRADA_RMA_DIREKTORIJA = 10199;
		public static final Integer TIP_NALOGA_SIFRAPOD_PREBACIVANJE_PLATNOG_NALOGA = 777;
		public static final Integer TIP_NALOGA_OROCENJE_PROMJENA_PODATAKA_ZA_REGISTAR = 10140;
		public static final Integer TIP_NALOGA_UPLOAD_JRR_PRORACUNSKI_KORISNICI = 10170;
		public static final Integer TIP_NALOGA_SIFRAPOD_REZERVIRANA_PARTIJA_UPIS_LOG = 423;
		public static final Integer TIP_NALOGA_KREIRANJE_JOPPD_DATOTEKE = 60;
		public static final Integer TIP_NALOGA_NEFIN01_UPIS = 28;
		public static final Integer TIP_NALOGA_NEFIN02_IZMJENA = 29;
		public static final Integer TIP_NALOGA_NEFIN03_BRISANJE = 30;
		public static final Integer SIFRAPOD_MATICNAEVIDENCIJA_TN_PROMJENA_PODVRSTE_POZITIVNI_IZNOS = 415;
		public static final Integer SIFRAPOD_MATICNAEVIDENCIJA_TN_PROMJENA_PODVRSTE_NEGATIVNI_IZNOS = 416; //tip naloga za promjenu tipa partije

		// entity stack
		public static final Integer KLASANALOGA_SIFRAPOD_ENTITY_STACK = 10101;
		public static final Integer KLASANALOGA_SIFRAPOD_ENTITY_STACK_CORE = 10102;

		public static final Integer TIPNALOGA_SIFRAPOD_ENTITY_STACK_DRZAVA = 10101;
		public static final Integer TIPNALOGA_SIFRAPOD_ENTITY_STACK_OSNOVNA_KAMATNA_STOPA = 10102;
		// entity stack

		public static final Integer TIPNALOGA_SIFRAPOD_TEMELJNICA = 50;
		// spn
		public static final BigDecimal GOTOVINA_LIMIT_ZA_REPORT = new BigDecimal(
				"30000");
		public static final BigDecimal GOTOVINA_LIMIT_ZA_TEKUCI = new BigDecimal(
				"7000");
		public static final BigDecimal GOTOVINA_LIMIT_ZA_AVISTA_STEDNJU = new BigDecimal(
				"25000");
		public static final BigDecimal GOTOVINA_LIMIT_ZA_OROCENU_STEDNJU = new BigDecimal(
				"28000");
		public static final BigDecimal GOTOVINA_LIMIT_NA_PARTIJU_POLITICKI_IZLOZ = new BigDecimal(
				"10000");
		public static final String GOTOVINA_PARAMETAR_LIMIT_ZA_TEKUCI = "SPN_TEK_LIMIT";
		public static final String GOTOVINA_PARAMETAR_LIMIT_ZA_AVISTA_STEDNJU = "SPN_AVR_LIMIT";
		public static final String GOTOVINA_PARAMETAR_LIMIT_ZA_OROCENU_STEDNJU = "SPN_ORO_LIMIT";
		public static final String GOTOVINA_PARAMETAR_LIMIT_NA_PARTIJU_POLITICKI_IZLOZ = "SPN_POI_LIMIT";
		public static final String GOTOVINA_PARAMETAR_LIMIT_ZA_ZIRO = "SPN_ZIR_LIMIT";
		public static final String GOTOVINA_PARAMETAR_LIMIT_ZA_RIZICNE_OSOBE = "SPN_RIZ_LIMIT";
		public static final String GOTOVINA_PARAMETAR_LIMIT_ZA_MJENJACE = "SPN_MJE_LIMIT";
		public static final String GOTOVINA_PARAMETAR_LIMIT_FOO = "SPN_GOT_LIMIT";
		public static final String GOTOVINA_PARAMETAR_LIMIT_BEZGOTOVINA = "SPN_BEZG_LIMIT";
		public static final String UN_LISTA_DOWNLOAD_LINK = "http://www.un.org/sc/committees/1267/AQList.xml";
		public static final String EU_LISTA_DOWNLOAD_LINK = "http://ec.europa.eu/external_relations/cfsp/sanctions/list/version4/global/global.xml";
		public static final String UN_LISTA_DOWNLOAD_ALQAIDA_PARAMETAR = "SPN_UN_ALQAID";
		public static final String UN_LISTA_DOWNLOAD_TALIBAN_PARAMETAR = "SPN_UN_TALIBA";
		public static final String EU_LISTA_DOWNLOAD_PARAMETAR = "SPN_EU_LIST";
		public static final String SPN_STATUS_AKTIVAN = "A";
		public static final String SPN_STATUS_NEAKTIVAN = "N";
		public static final String SPN_IZBOR_DOKUMENTACIJA = "D";
		public static final String SPN_IZBOR_KONTAKT = "K";
		public static final String SPN_IZBOR_LIMIT = "L";
		public static final String SPN_IZBOR_UPRAVA = "U";
		public static final Integer TIPKAMATE_SIFRATKP_AVISTA_PLATNI_REDOVNA = 37;
		// standardni FX parovi
		public static final String REPRTREP_STANDARDNI_FX_PAROVI = "nalog14";
		public static final String FIELDREP_STANDARDNI_FX_PAROVI = "standardniFXparovi";
		// obrasci 1, 2, 3, 4
		public static final String REPRTREP_STANDARDNI_OBRAZAC1 = "obrazac1";
		public static final String REPRTREP_STANDARDNI_OBRAZAC2 = "obrazac2";
		public static final String REPRTREP_STANDARDNI_OBRAZAC3 = "obrazac3";
		public static final String REPRTREP_STANDARDNI_OBRAZAC4 = "obrazac4";
		public static final String FIELDREP_STANDARDNI_OBRAZAC1_TIP_NALOGA = "tipNaloga";
		// map constants
		public static final String KEY_ERR_MSG = "errorMessages";
		public static final String KEY_OK_DTOS = "success";
		public static final Integer TIP_NALOGA_UCITAVANJE_UN_LIST = 11000;
		public static final Integer TIP_NALOGA_UCITAVANJE_EU_LIST = 11001;
		public static final Integer TIP_NALOGA_UNOS_GRUPA_IZLOZENOSTI = 10059;
		public static final Integer TIP_NALOGA_VERIFIKACIJA_GRUPA_IZLOZENOSTI = 10060;
		public static final Integer TIP_NALOGA_ODOBRENJE_GRUPA_IZLOZENOSTI = 10062;
		public static final Integer TIP_NALOGA_PROMJENA_DODATNOG_PODATKA = 10063;
		// tarifne stavke
		public static final String TARIFNASTAVKA_OSNOVTRS_PARTIJA = "PARTIJA";
		public static final Integer TARIFNASTAVKA_SIFRA_NAKNADA_ESKONTA = 1;
		public static final Integer TARIFNASTAVKA_SIFRA_NAKNADA_PLASMANA = 2;
		public static final Integer TARIFNASTAVKA_SIFRA_NAKNADA_KOLATERAL = 6;

		public static final String TARIFNASTAVKA_TARSTTRS_VODJENE_RACUNA_TEKUCI_STANDARDNI = "SI113";
		public static final String TARIFNASTAVKA_TARSTTRS_VODJENE_RACUNA_TEKUCI_UMIROVLJENICI_RODILJE_STUDENTI = "SI114";
		public static final String TARIFNASTAVKA_TARSTTRS_VODJENE_RACUNA_ZIRO = "SI122";
		public static final String TARIFNASTAVKA_OPIS_VODJENE_RACUNA_TEKUCI = "Vođenje računa (tekući)";
		public static final String TARIFNASTAVKA_OPIS_VODJENE_RACUNA_ZIRO = "Vođenje računa (žiro i ostali)";

		// grupa izloženosti
		public static final String GRUPA_IZLOZENOSTI_STATUS_AKTIVNA = "A";
		public static final String GRUPA_IZLOZENOSTI_STATUS_ZATVORENA = "Z";
		public static final String GRUPA_IZLOZENOSTI_STATUS_UPISANA = "U";
		public static final String GRUPA_IZLOZENOSTI_STATUS_IZMJENA_NEVERIF = "NI";
		public static final String GRUPA_IZLOZENOSTI_STATUS_ZATVARANJE_NEVERIF = "NZ";
		public static final String STATUS_VERIFICIRANI_UNOS = "VU";
		public static final String STATUS_VERIFICIRANA_IZMJENA = "VI";
		public static final String STATUS_VERIFICIRANO_ZATVARANJE = "VZ";
		public static final String STATUS_ARHIVIRANA_GRUPA = "AR"; //arhiva zbog izvještavanja
		public static final Integer GRUPAVEZE_SIFRA_NEDEFINIRANA_GRUPA = 99;
		public static final String TIPVEZE_INDIKATOR_IZLOZENOSTI_DA = "D";
		public static final Integer NADDIO_DOZVOLA_INTERNI_NALOG_DA = 1;
		public static final Integer NADDIO_DOZVOLA_INTERNI_NALOG_NE = 0;
		// jrr
		public static final String DATOTEKA_JRR_LINE_END = "\";";
		public static final String DATOTEKA_JRR_LINE_START = "\"";
		public static final String DATOTEKA_JRR_DATE_FORMAT = "yyyyMMdd";
		public static final String VBDINBAN_BANKA = PlatniPromet.NKSBANKA_VBDINBAN_BANKA;
		public static final String VBDINBAN_FINA = "7777779";
		// klasa partije
		public static final Integer KLASA_PARTIJE_RACUN_BANKE = 1;
		public static final Integer KLASA_PARTIJE_SMECE = 3;
		public static final Integer KLASA_PARTIJE_KOLATERAL = 4;
		public static final Integer KLASA_PARTIJE_SIFRAKLP_TUDJI_KONTOKORENTNI_RACUN_KOD_NAS = 5;
		public static final Integer KLASA_PARTIJE_SIFRAKLP_DEVIZNI_DEPOZITI_DANI = 6;
		public static final Integer KLASA_PARTIJE_SIFRAKLP_DEVIZNI_DEPOZITI_PRIMLJENI = 90;
		public static final Integer KLASA_PARTIJE_SIFRAKLP_OKVIRNI_KREDITI_PRAVNA_LICA = 74;
		public static final Integer KLASA_PARTIJE_RACUN_GLAVNE_KNJIGE = 53;
		public static final Integer KLASA_PARTIJE_OKVIRI = 75;
		public static final Integer TIPOBAVIJESTI_SIFRATOB_IZVOD_VALUTA = 2;
		public static final Integer TIP_VEZE_SIFRA_OKVIR = 146;
		public static final Integer TIP_VEZE_SIFRA_MINISTARSTVO = 47;
		public static final Integer TIP_DODPOD_ESKONT_IBAN_PLASMANA = 50;
		public static final String PARTIJA_INTBLPAR_VERIFICIRANA = "V";
		public static final String NADDIO_VRDUGNAD_NEDOSPJELA_GLAVNICA_RETAIL = "NG";
		public static final Integer TIP_DODPOD_REVOLVING_NAPLATA_DRUGE_NAKNADE = 57;
		public static final List<String> NADDIO_OZNKANAD_RASPOLOZIVO_AVPO = Arrays.asList(NADDIO_OZNKANAD_GLAVNICA);
		public static String PARAMETAR_VRIJEDPAR_BANKA_BOX = "";
		public static String PARAMETAR_VRIJEDPAR_BANKA_BOX_IBAN = "";
		public static String PARAMETAR_VRIJEDPAR_BANKA_IBAN = "";
		public static String PARAMETAR_VRIJEDPAR_APR_DIJELOVI_ZA_VERIFIKACIJU = "DC,DF,EC,EE";

		// JRR
		public static final String PARAMETAR_SIFRAPAR_JRR_PRORACUNSKI_KORISNICI = "JRRPR";
		public static final String PATTERN_DOLAZNE_DATOTEKE_JRR_PRORACUNSKI_KORISNICI = "*.RSP";
	}

	public static class RegistarOsiguranja {
		public static final String MESSAGE_BUNDLE = "hr.abit.b3.biz.regosig.message.regosigMessages";

		public static final String GRUPA_RACUNA_OKVIR = "75";

		public static final String SPORAZUM_STATUS_OTVOREN = "O";
		public static final String SPORAZUM_STATUS_ZATVOREN = "Z";

		// klasa partije
		public static final Integer KLASA_PARTIJE_OKVIR = 75;

		// tip partije
		public static final Integer TIP_PARTIJE_OKVIR_NEOBVEZUJUCI_OPOZIVI = 68;
		public static final Integer TIP_PARTIJE_OKVIR_S_VALUTNOM_KLAUZULOM = 67;
		public static final Integer TIP_PARTIJE_OKVIR_OBVEZUJUCI_NEOPOZIVI = 66;
		public static final Integer TIP_PARTIJE_OKVIR_NERASPOZNATI = 65;

		public static final String TIPPARTIJE_POSAOTIP_KOLATERALI = "43";
		public static final String PARTIJA_GRUPA_RACUNA_KOLATERALI = "43";
		public static final Integer TIPPARTIJE_SIFRA_KOLATERAL_HIPOTEKA = 4301;
		public static final Integer TIPPARTIJE_SIFRA_KOLATERAL_HIPOTEKA1 = 43011;
		public static final Integer TIPPARTIJE_SIFRA_KOLATERAL_HIPOTEKA_STANOVNISTVO = 43012;
		public static final Integer TIPPARTIJE_SIFRA_KOLATERAL_HIPOTEKA_STARI = 43013;
		public static final Integer TIPPARTIJE_SIFRA_KOLATERAL_DEPOZIT = 4302;
		public static final Integer TIPPARTIJE_SIFRA_KOLATERAL_DEPOZIT1 = 43021;
		public static final Integer TIPPARTIJE_SIFRA_KOLATERAL_DEPOZIT2 = 43022;
		public static final Integer TIPPARTIJE_SIFRA_KOLATERAL_DEPOZIT_STANOVNISTVO = 43023;
		public static final Integer TIPPARTIJE_SIFRA_KOLATERAL_DEPOZIT_STANOVNISTVO1 = 43024;
		public static final Integer TIPPARTIJE_SIFRA_KOLATERAL_DEPOZIT_STANOVNISTVO2 = 43025;
		public static final Integer TIPPARTIJE_SIFRA_KOLATERAL_MJENICE = 4303;
		public static final Integer TIPPARTIJE_SIFRA_KOLATERAL_OSTALO = 4610;
		public static final Integer TIPPARTIJE_SIFRA_KOLATERAL_OSTALO1 = 46101;
		public static final Integer TIPPARTIJE_SIFRA_KOLATERAL_OSTALO2 = 46102;
		public static final Integer TIPPARTIJE_SIFRA_KOLATERAL_OSTALO3 = 46103;
		public static final Integer TIPPARTIJE_SIFRA_KOLATERAL_GARANCIJE = 4607;
		public static final Integer TIPPARTIJE_SIFRA_KOLATERAL_GARANCIJE1 = 46071;
		public static final Integer TIPPARTIJE_SIFRA_KOLATERAL_GARANCIJE2 = 46072;
		public static final Integer TIPPARTIJE_SIFRA_KOLATERAL_GARANCIJE3 = 46073;
		public static final Integer TIPPARTIJE_SIFRA_KOLATERAL_GARANCIJE4 = 46074;
		public static final Integer TIPPARTIJE_SIFRA_KOLATERAL_DIONICE = 4605;
		public static final Integer TIPPARTIJE_SIFRA_KOLATERAL_POKRETNINA = 4304;
		public static final Integer TIPPARTIJE_SIFRA_KOLATERAL_POKRETNINA1 = 43041;
		public static final Integer TIPPARTIJE_SIFRA_KOLATERAL_POKRETNINA2 = 43042;

		public static final String VRSTA_KOLATERALA_NEKRETNINA = "001";
		public static final String VRSTA_KOLATERALA_DEPOZIT = "002";
		public static final String VRSTA_KOLATERALA_VRIJEDNOSNI_PAPIR = "003";
		public static final String VRSTA_KOLATERALA_POKRETNINA = "004";
		public static final String VRSTA_KOLATERALA_OSTALO = "005";

		public static final String STATUS_KOLATERALA_UNESEN = "U";
		public static final String STATUS_KOLATERALA_ODOBREN = "O";
		public static final String STATUS_KOLATERALA_AKTIVAN = "A";
		public static final String STATUS_KOLATERALA_REGISTRIRAN = "R";
		public static final String STATUS_KOLATERALA_ODBIJENI = "N";
		public static final String STATUS_KOLATERALA_WITHDRAWN = "W";
		public static final String STATUS_KOLATERALA_POKRENUTA_NAPLATA_PLASMANA = "G";
		public static final String STATUS_KOLATERALA_NAPLACEN = "H";
		public static final String STATUS_KOLATERALA_STECEN = "S";
		public static final String STATUS_KOLATERALA_ZATVOREN = "Z";

		public static final Integer KLASA_NALOGA_SIFRAKNA_REGOSIG_VANBILANCNO_KNJIZENJE_OKVIRA = 3958;
		public static final Integer KLASA_NALOGA_SIFRAKNA_REGOSIG_VANBILANCNO_ISKNJIZENJE_OKVIRA = 3959;
		public static final String KLASA_NALOGA_GRUPA_OKVIRA = "OKV";

		public static final Integer KLASA_NALOGA_SIFRA_KNJIZENJE_PROCJENE = 5702; // 2002
		public static final Integer KLASA_NALOGA_SIFRA_ISKNJIZENJE_PROCJENE = 5703; // 2003
		public static final Integer KLASA_NALOGA_SIFRA_KNJIZENJE_ALOKACIJE = 5704; // 2004
		public static final Integer TIP_NALOGA_SIFRA_REGOSIG_MIGRACIJA = 5700;
		public static final Integer TIP_NALOGA_SIFRA_KNJIZENJE_PROCJENE = 5702; // 2002
		public static final Integer TIP_NALOGA_SIFRA_ISKNJIZENJE_PROCJENE = 5703; // 2003
		public static final Integer TIP_NALOGA_SIFRA_KNJIZENJE_ALOKACIJE = 5704; // 2004
		public static final Integer TIP_NALOGA_SIFRA_INTERNI_NALOG_KOLATERAL = 5701; // 2001
		public static final Integer TIP_NALOGA_SIFRA_ZATVARANJE_PARTIJE_KOLATERALA = 10052;
		public static final Integer TIP_NALOGA_SIFRA_PROMJENA_STATUSA_KOLATERALA = 10053;

		public static final Integer TIP_NALOGA_NEFINLOG_OTVARANJE_OKVIRA = 10049;
		public static final Integer TIP_NALOGA_NEFINLOG_ZATVARANJE_OKVIRA = 10050;
		public static final Integer TIP_NALOGA_MIGRACIJA_OKVIRA = 20001;

		public static final Integer TIP_NALOGA_SIFRAPOD_REGOSIG_OKVIR_VANBILANCNO_KNJIZENJE = 3958;
		public static final Integer TIP_NALOGA_SIFRAPOD_REGOSIG_OKVIR_VANBILANCNO_ISKNJIZENJE = 3959;

		public static final String PODVRSTAOSIGURANJA_IDENTVOS_OKVIR = "999";

		public static final String PROMET_DUGUJE = "D";
		public static final String PROMET_POTRAZUJE = "P";
		public static final String PROMET_DUGUJE_STORNO = "DS";
		public static final String PROMET_POTRAZUJE_STORNO = "PS";

		public static final String VRSTA_REEVALUACIJE_AUTOMATIKA = "A";
		public static final String PROCJENA_SPORAZUMA_VRSTA_PROCJENA_VJESTAKA = "PV";
		public static final String PROCJENA_SPORAZUMA_VRSTA_REEVALUACIJA = "RE";
		public static final String VRIJEDNOST_POV = "O"; // za izvještaj
														// alokacije
		public static final String VRIJEDNOST_PVSV = "V"; // za izvještaj
															// alokacije

		public static final String NADDIO_OZNAKA_ALOKACIJA = "199";
		public static final String NACIN_IZVRSENJA_DEVIZNI_INTERNI_NALOG = "D";
	}

	public static class Trezor {
		public static final String MESSAGE_BUNDLE = "hr.abit.b3.biz.retail.message.retailMessages";

		public static final Integer KLASAPARTIJE_SIFRAKLP_SEF = 99;

		public static final Integer TIPPARTIJE_SIFRATIP_SEF = 20;

		public static final String TIPPARTIJE_POSAOTIP_SEF = "20";

		public static final Integer KLASANALOGA_SIFRAKNA_TREZOR_ULAZ_BANKA = 107;
		public static final Integer KLASANALOGA_SIFRAKNA_TREZOR_IZLAZ_CB_EKSPERIZA_NOVCANICA = 108;
		public static final Integer KLASANALOGA_SIFRAKNA_TREZOR_IZLAZ_BANKA = 109;
		public static final Integer KLASANALOGA_SIFRAKNA_OPCI_INTERNI_NALOG = 110;
		public static final Integer KLASANALOGA_SIFRAKNA_TREZOR_ULAZ = 111;
		public static final Integer KLASANALOGA_SIFRAKNA_TREZOR_IZLAZ = 112;
		public static final Integer KLASANALOGA_SIFRAKNA_SEF_OBRACUN_NAKNADE_RAZGRANICENJE = 120;
		public static final Integer KLASANALOGA_SIFRAKNA_SEF_OBRACUN_NAKNADE_PRIHODOVANJE = 129;
		public static final Integer KLASANALOGA_SIFRAKNA_SEF_PROMJENA_STATUSA = 134;

		public static final Integer TIPNALOGA_SIFRAPOD_TREZOR_FINANCIJSKI_ZAKLJUCAK = 204;
		public static final Integer TIPNALOGA_SIFRAPOD_TREZOR_OPCI_INTERNI_NALOG = 300;
		public static final Integer TIPNALOGA_SIFRAPOD_TREZOR_ULAZ = 301;
		public static final Integer TIPNALOGA_SIFRAPOD_TREZOR_IZLAZ = 302;
		public static final Integer TIPNALOGA_SIFRAPOD_TREZOR_IZLAZ_BANKA = 303;
		public static final Integer TIPNALOGA_SIFRAPOD_TREZOR_IZLAZ_SLANJE_NOVCANICA_NA_EKSPERTIZU = 304;
		public static final Integer TIPNALOGA_SIFRAPOD_TREZOR_ULAZ_BANKA = 305;
		public static final Integer TIPNALOGA_SIFRAPOD_SEF_OBRACUN_NAKNADE = 320;
		public static final Integer TIPNALOGA_SIFRAPOD_SEF_PROMJENA_STATUSA = 322;
		public static final Integer TIPNALOGA_SIFRAPOD_SEF_OBRACUN_NAKNADE_KLJUC = 323;
		public static final Integer TIPNALOGA_SIFRAPOD_SEF_OBRACUN_NAKNADE_PRISILNO_OTVARANJE = 324;
		public static final Integer TIPNALOGA_SIFRAPOD_SEF_OBRACUN_NAKNADE_PREUZIMANJE_SADRZAJA = 325;

		public static final String PROIZVOD_VRSTAPRO_RETAIL_SEF = "RET_SEF";
		public static final String SEF_STATUS_SLOBODAN = "S";
		public static final String SEF_STATUS_BLOKIRAN = "B";
		public static final String SEF_STATUS_ZAUZET = "Z";

		public static final String PARTIJASEF_UGPROPAR_BEZ_PRODULJENJA = "0";
		public static final String PARTIJASEF_UGPROPAR_PRODULJENJE = "1";

		public static final Integer TIP_VEZE_SEF_OVLASTENJA = 51;
		public static final Integer TIP_VEZE_SEF_PRODULJENJA = 52;

		public static final String PROIZVOD_SEF = "SEF_";

		// tarifna_stavka
		public static final Integer TARIFNASTAVKA_SIFRATRS_I_TROMJESECNO_FL = 161;
		public static final Integer TARIFNASTAVKA_SIFRATRS_I_POLUGODISNJE_FL = 162;
		public static final Integer TARIFNASTAVKA_SIFRATRS_I_GODISNJE_FL = 163;
		public static final Integer TARIFNASTAVKA_SIFRATRS_II_TROMJESECNO_FL = 164;
		public static final Integer TARIFNASTAVKA_SIFRATRS_II_POLUGODISNJE_FL = 165;
		public static final Integer TARIFNASTAVKA_SIFRATRS_II_GODISNJE_FL = 166;
		public static final Integer TARIFNASTAVKA_SIFRATRS_III_TROMJESECNO_FL = 167;
		public static final Integer TARIFNASTAVKA_SIFRATRS_III_POLUGODISNJE_FL = 168;
		public static final Integer TARIFNASTAVKA_SIFRATRS_III_GODISNJE_FL = 169;
		public static final Integer TARIFNASTAVKA_SIFRATRS_IV_TROMJESECNO_FL = 170;
		public static final Integer TARIFNASTAVKA_SIFRATRS_IV_POLUGODISNJE_FL = 171;
		public static final Integer TARIFNASTAVKA_SIFRATRS_IV_GODISNJE_FL = 172;
		public static final Integer TARIFNASTAVKA_SIFRATRS_V_TROMJESECNO_FL = 173;
		public static final Integer TARIFNASTAVKA_SIFRATRS_V_POLUGODISNJE_FL = 174;
		public static final Integer TARIFNASTAVKA_SIFRATRS_V_GODISNJE_FL = 175;
		public static final Integer TARIFNASTAVKA_SIFRATRS_VI_TROMJESECNO_FL = 176;
		public static final Integer TARIFNASTAVKA_SIFRATRS_VI_POLUGODISNJE_FL = 177;
		public static final Integer TARIFNASTAVKA_SIFRATRS_VI_GODISNJE_FL = 178;
		public static final Integer TARIFNASTAVKA_SIFRATRS_VII_TROMJESECNO_FL = 179;
		public static final Integer TARIFNASTAVKA_SIFRATRS_VII_POLUGODISNJE_FL = 180;
		public static final Integer TARIFNASTAVKA_SIFRATRS_VII_GODISNJE_FL = 181;
		public static final Integer TARIFNASTAVKA_SIFRATRS_VIII_TROMJESECNO_FL = 182;
		public static final Integer TARIFNASTAVKA_SIFRATRS_VIII_POLUGODISNJE_FL = 183;
		public static final Integer TARIFNASTAVKA_SIFRATRS_VIII_GODISNJE_FL = 184;
		public static final Integer TARIFNASTAVKA_SIFRATRS_OSTALO_FL = 185;
		public static final Integer TARIFNASTAVKA_SIFRATRS_NASILNO_OTVARANJE_FL = 186;
		public static final Integer TARIFNASTAVKA_SIFRATRS_IZGUBLJENI_KLUC_FL = 187;
		public static final Integer TARIFNASTAVKA_SIFRATRS_PREUZIMANJE_SADRZAJA_SEFA_FL = 188;

		public static final Integer TARIFNASTAVKA_SIFRATRS_I_TROMJESECNO_PL = 331;
		public static final Integer TARIFNASTAVKA_SIFRATRS_I_POLUGODISNJE_PL = 332;
		public static final Integer TARIFNASTAVKA_SIFRATRS_I_GODISNJE_PL = 333;
		public static final Integer TARIFNASTAVKA_SIFRATRS_II_TROMJESECNO_PL = 334;
		public static final Integer TARIFNASTAVKA_SIFRATRS_II_POLUGODISNJE_PL = 335;
		public static final Integer TARIFNASTAVKA_SIFRATRS_II_GODISNJE_PL = 336;
		public static final Integer TARIFNASTAVKA_SIFRATRS_III_TROMJESECNO_PL = 337;
		public static final Integer TARIFNASTAVKA_SIFRATRS_III_POLUGODISNJE_PL = 338;
		public static final Integer TARIFNASTAVKA_SIFRATRS_III_GODISNJE_PL = 339;
		public static final Integer TARIFNASTAVKA_SIFRATRS_IV_TROMJESECNO_PL = 340;
		public static final Integer TARIFNASTAVKA_SIFRATRS_IV_POLUGODISNJE_PL = 341;
		public static final Integer TARIFNASTAVKA_SIFRATRS_IV_GODISNJE_PL = 342;
		public static final Integer TARIFNASTAVKA_SIFRATRS_V_TROMJESECNO_PL = 343;
		public static final Integer TARIFNASTAVKA_SIFRATRS_V_POLUGODISNJE_PL = 344;
		public static final Integer TARIFNASTAVKA_SIFRATRS_V_GODISNJE_PL = 345;
		public static final Integer TARIFNASTAVKA_SIFRATRS_VI_TROMJESECNO_PL = 346;
		public static final Integer TARIFNASTAVKA_SIFRATRS_VI_POLUGODISNJE_PL = 347;
		public static final Integer TARIFNASTAVKA_SIFRATRS_VI_GODISNJE_PL = 348;
		public static final Integer TARIFNASTAVKA_SIFRATRS_VII_TROMJESECNO_PL = 349;
		public static final Integer TARIFNASTAVKA_SIFRATRS_VII_POLUGODISNJE_PL = 350;
		public static final Integer TARIFNASTAVKA_SIFRATRS_VII_GODISNJE_PL = 351;
		public static final Integer TARIFNASTAVKA_SIFRATRS_VIII_TROMJESECNO_PL = 352;
		public static final Integer TARIFNASTAVKA_SIFRATRS_VIII_POLUGODISNJE_PL = 353;
		public static final Integer TARIFNASTAVKA_SIFRATRS_VIII_GODISNJE_PL = 354;
		public static final Integer TARIFNASTAVKA_SIFRATRS_OSTALO_PL = 355;
		public static final Integer TARIFNASTAVKA_SIFRATRS_NASILNO_OTVARANJE_PL = 356;
		public static final Integer TARIFNASTAVKA_SIFRATRS_IZGUBLJENI_KLUC_PL = 357;
		public static final Integer TARIFNASTAVKA_SIFRATRS_PREUZIMANJE_SADRZAJA_SEFA_PL = 358;
	}

	public static class Retail extends GenericBassxConstants.Retail {

		public static final Integer TIPNALOGA_SIFRAPOD_SEF_1 = 11401;
		public static final Integer TIPNALOGA_SIFRAPOD_SEF_2 = 11402;
		public static final Integer TIPNALOGA_SIFRAPOD_SEF_3 = 11403;
		public static final Integer TIPNALOGA_SIFRAPOD_SEF_4 = 11404;
		public static final Integer TIPNALOGA_SIFRAPOD_SEF_5 = 11405;
		public static final Integer TIPNALOGA_SIFRAPOD_SEF_6 = 11406;
		public static final Integer TIPNALOGA_SIFRAPOD_SEF_7 = 11407;
		public static final Integer TIPNALOGA_SIFRAPOD_SEF_8 = 11408;
		public static final Integer TIPNALOGA_SIFRAPOD_SEF_9 = 11409;
		public static final Integer TIPNALOGA_SIFRAPOD_SEF_10 = 11400;
		public static final Integer TIPNALOGA_SIFRAPOD_SEF_11 = 11411;
		public static final Integer TIPNALOGA_SIFRAPOD_SEF_12 = 11412;
		public static final Integer TIPNALOGA_SIFRAPOD_SEF_OBRACUN_NAKNADE_KLJUC = 11413;
		public static final Integer TIPNALOGA_SIFRAPOD_SEF_OBRACUN_NAKNADE_DVA_KLJUCA = 11414;

		public static final String MESSAGE_BUNDLE = "hr.abit.b3.biz.retail.message.retailMessages";

		// cek
		public static final String CEK_STATUCEK_AKTIVAN = "A";
		public static final String CEK_STATUCEK_REALIZIRAN = "R";
		public static final String CEK_STATUCEK_PONISTEN = "P";

		// >>> klasa naloga
		public static final String KLASANALOGA_GRUPAKNA_RETAIL_OSTALE_TRANSAKCIJE = "RFO";
		public static final String KLASANALOGA_GRUPAKNA_RETAIL_TEMPLATE = "RF_T%";

		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_UPLATA_OSTALO = 2;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_FINANCIJSKI = 10;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_NEFINANCIJSKI = 11;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_DOGADJAJNI = 12;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_BLAGAJNA_ULAZ = 13;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_BLAGAJNA_IZLAZ = 14;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_ISPLATA_OSTALO = 15;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_OSTALE_TRANSAKCIJE_S_NAKNADOM = 16;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_PREKORACENJE_PROMJENA_IZNOSA = 17;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_PREKORACENJE_PROMJENA_KORISTENJA = 18;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_OTKUP_EFEKTIVE_MJENJAC = 20;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_UPLATA_KREDITA = 21;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_ISPLATA_KREDITA = 22;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_PRIJENOS_PARTIJA_2 = 25;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_BLAGAJNA_PRIMOPREDAJA_ULAZ = 27;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_BLAGAJNA_PRIMOPREDAJA_IZLAZ = 28;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_PO_POLOG_UTRSKA = 29;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_PRIJENOS_NA_OSTALO = 31;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_PRIJENOS_S_OSTALO = 32;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_ATM_PUNJENJE = 41;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_ATM_PRAZNJENJE = 42;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_APOENSKA_STRUKTURA = 43;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_BLAGAJNA_MANJAK = 46;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_BLAGAJNA_VISAK = 47;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_RASPODJELA_DOSPJELOG_DUGA = 58;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_SITNI_SALDO = 59;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_NAKNADA_NAPLATA_FAKTURE_ASIMETRICNO = 60;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_NAKNADA_NAPLATA_UPP = 63;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_NAKNADA_PRIHODOVANJE_PO_PARTIJI_ZADUZENJA = 65;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_NAKNADA_PRIHODOVANJE_PO_PARTIJI_ODOBRENJA = 66;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_PO_UPLATA_NA_AVISTA_RACUN = 70;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_PRIJENOS_NA_KREDIT = 72;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_PO_PRIJENOS_NA_AVISTA_RACUN = 73;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_PO_ISPLATA_S_AVISTA_RACUNA = 75;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_PRIJENOS_S_KREDITA = 76;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_PO_PRIJENOS_S_AVISTA_RACUNA = 78;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_OSTALE_TRANSAKCIJE = 79;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_ZBIRNI_OBRACUN_KAMATE_AVISTA = 83;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_OBRACUN_PASIVNE_KAMATE_AVISTA = 84;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_ZBIRNI_OBRACUN_KAMATE_ZIRO = 86;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_OBRACUN_PASIVNE_KAMATE_ZIRO = 87;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_NAPLATA_KREDITNE_KAMATE_TEKUCI = 93;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_NAPLATA_ZATEZNE_KAMATE_TEKUCI = 94;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_PRIPIS_PASIVNE_KAMATE_AVISTA = 95;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_PRIPIS_PASIVNE_KAMATE_ZIRO = 96;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_ZBIRNI_RASPODJELA_PREKORACENJA = 97;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_ZBIRNI_RASPODJELA_DOSPJELOG_DUGA = 98;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_ZBIRNI_OBRACUN_KAMATE_DEVIZNI = 99;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_ZBIRNI_NAPLATA_DOSPJELOG_DUGA = 103;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_NAKNADA_PRIHODOVANJE_PO_DEVIZNOM_PRILJEVU = 104;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_ZBIRNI_PRIPIS_PASIVNE_KAMATE = 119;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_UPLATA_FAKTURA = 121;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_OBRACUN_OBUSTAVLJENE_KAMATE_NEUTUZENO = 123;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_OBRACUN_OBUSTAVLJENE_KAMATE_UTUZENO = 124;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_OBRACUN_OBUSTAVLJENE_NAKNADE_NEUTUZENO = 125;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_OBRACUN_OBUSTAVLJENE_NAKNADE_UTUZENO = 126;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_UPLATA_KARTICA_POS = 131;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_ISPLATA_KARTICA_POS = 132;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_IZVOD_SLANJE_EMAIL = 133;
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_KARTICA_AKCIJA = 139;

		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_INTERNI_OPCI_NALOG = 400;

		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_OBRACUN_NAKNADE = 1001;

		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_OBRACUN_NAKNADE_SEF = 10103;

		// <<< klasa naloga

		// klasa partije
		public static final Integer KLASAPARTIJE_SIFRAKLP_RETAIL_AVISTA_KUNSKI_RACUN = 10;
		public static final Integer KLASAPARTIJE_SIFRAKLP_RETAIL_AVISTA_DEVIZNI_RACUN = 12;
		public static final Integer KLASAPARTIJE_SIFRAKLP_RETAIL_ZIRO_RACUN = 14;
		public static final Integer KLASAPARTIJE_SIFRAKLP_RETAIL_TEKUCI_RACUN = 15;
		public static final Integer KLASAPARTIJE_SIFRAKLP_RETAIL_MJENJAC = 19;

		// knjizenje
		public static final String KNJIZENJE_OPCJAKNJ_ZADAN_IZNOS_DUGOVNO = "D";
		public static final String KNJIZENJE_OPCJAKNJ_ZADAN_IZNOS_POTRAZNO = "P";

		// likvidator blagajna
		public static final String LIKVIDATORBLAGAJNA_STATULBL_AKTIVAN = "A";

		// nad dio za retail
		public static final String NADDIO_OZNKANAD_RETAIL_PREKORACENJE_DOZVOLJENO = "002";
		public static final String NADDIO_OZNKANAD_RETAIL_PREKORACENJE = "008";
		public static final String NADDIO_OZNKANAD_RETAIL_PREKORACENJE_NEDOZVOLJENO = "011";
		public static final String NADDIO_OZNKANAD_RETAIL_PREKORACENJE_NEISKORISTENO = "097";
		public static final String NADDIO_OZNKANAD_RETAIL_PREKORACENJE_NEISKORISTENO_PROTUSTAVKA = "297";

		public static final String NADDIO_OZNKANAD_RETAIL_NAKNADA_OBRACUNATA = "041";

		public static final String NADDIO_OZNKANAD_RETAIL_NAKNADA_UTUZENA_KOJA_NIJE_PRIHOD = "105";
		public static final String NADDIO_OZNKANAD_RETAIL_NAKNADA_NEUTUZENA_KOJA_NIJE_PRIHOD = "106";

		public static final String NADDIO_OZNKANAD_RETAIL_UPP_PORAVNANJE_INTERNO = "147";
		public static final String NADDIO_OZNKANAD_RETAIL_UPP_PORAVNANJE_GIRO_CLEARING = "148";
		public static final String NADDIO_OZNKANAD_RETAIL_NAKNADA_PRIHOD = "35";
		public static final String NADDIO_OZNKANAD_RETAIL_NAKNADA_RAZGRANICENJE = "341";

		public static final String NADDIO_OZNKANAD_RETAIL_NAKNADA_PRIHOD_OD_DOZNAKA = "342";
		public static final String NADDIO_OZNKANAD_RETAIL_NAKNADA_PRIHOD_MJENJACKI_POSLOVI = "347";
		public static final String NADDIO_OZNKANAD_RETAIL_NAKNADA_PDV = "349";
		public static final String NADDIO_OZNKANAD_RETAIL_KAMATA_OTPISANA_POTRAZIVANJA = "356";
		public static final String NADDIO_OZNKANAD_RETAIL_NAKNADA_OTPISANA_POTRAZIVANJA = "357";
		public static final String NADDIO_OZNKANAD_RETAIL_NAKNADA_RAZGRANICENJE_12MJESECI = "541";
		public static final String NADDIO_OZNKANAD_RETAIL_BLAGAJNA_MANJAK = "603";
		public static final String NADDIO_OZNKANAD_RETAIL_BLAGAJNA_VISAK = "604";
		public static final String NADDIO_OZNKANAD_RETAIL_BLAGAJNA_REKLAMACIJA = "605";
		public static final String NADDIO_OZNKANAD_RETAIL_KAMATA_AKTIVNA_KREDITNA = "19";
		public static final String NADDIO_OZNKANAD_RETAIL_KAMATA_AKTIVNA_ZATEZNA = "05";
		public static final String NADDIO_OZNKANAD_RETAIL_KAMATA_PASIVNA_REDOVNA = "44";

		public static final String NADDIO_OZNKANAD_RETAIL_OBAVEZA_PO_NEAKTIVNOJ_PARTIJI = "971";

		public static final String NADDIO_VRDUGNAD_PRIHOD_OD_NAKNADE = "PN";
		public static final String OZNKANAD_DOK_PRISTIGLE_UPLATE_NA_PARTIJU_BLOKIRANU_PO_FINI = "F0";

		public static final String NALOGRETAIL_SMJERNRT_POTRAZNO = "P";

		public static final Integer OBAVIJESTVEZA_SIFRAOBV_TEKUCI_IZVOD_KM_POSLOVNICA = 1;
		public static final Integer OBAVIJESTVEZA_SIFRAOBV_TEKUCI_IZVOD_KM_POSTA = 2;
		public static final Integer OBAVIJESTVEZA_SIFRAOBV_TEKUCI_IZVOD_KM_EMAIL = 3;
		public static final Integer OBAVIJESTVEZA_SIFRAOBV_ZIRO_IZVOD_KM_POSTA = 4;
		public static final Integer OBAVIJESTVEZA_SIFRAOBV_TEKUCI_SMS_NOTIFIKACIJA = 5;

		// parametar
		public static final String PARAMETAR_KONTROLA_PRAVA_LIKVIDATORA_NA_BLAGAJNU = "RET_LIKBLGCHK";
		public static final String PARAMETAR_PRIPIS_PASIVNE_KAMATE_U_SKLOPU_OBRACUNA = "RET_PRIPISKAM";
		public static final String PARAMETAR_PREPAID_RACUN_REZERVIRANI_IZNOS = "RET_PREPAIDRZ";
		public static final String PARAMETAR_DIJELOVI_NERASPOREDJENIH_SREDSTAVA = "RET_NERSRED";
		public static final String PARAMETAR_TIPOVI_NALOGA_KOJI_NISU_AKTIVNOST_PARTIJE = "RET_TIP_NEAKT";
		public static final String PARAMETAR_LIMIT_ZA_DETALJNO_EVIDENTIRANJE_NEKLIJENTA_KOD_TRANSAKCIJE_EFEKTIVE = "SPN_BLG_LIMIT";
		public static final String PARAMETAR_LIMIT_ZA_DETALJNO_EVIDENTIRANJE_NEKLIJENTA_KOD_TRANSAKCIJE_MJENJACNICE = "SPN_BLM_LIMIT";
		public static final String PARAMETAR_LOG_EVENT_DURATION = "RET_LOG_DUR";
		public static final String PARAMETAR_OKVIRNI_KREDIT_FOR_PREKORACENJE = "RET_OKV_KRED";
		public static final String PARAMETAR_PROIZVODI_KOJI_ULAZE_U_OBRACUN_NAKNADE = "RET_PROIZ_NAK";
		public static final String PARAMETAR_GRUPE_RACUNA_FIZICKIH_OSOBA = "RET_GRPRAC_FO";

		public static final String PARAMETAR_PUTANJA_IZLAZNE_DATOTEKE_CRR_FL = "CD_CRRFL_OUT";
		public static final String PARAMETAR_PUTANJA_ULAZNE_DATOTEKE_CRR_FL = "CD_CRRFL_IN";
		public static final String PARAMETAR_INICIJALNI_UNOS_CRR_FL = "CRR_INIT_RET";

		public static final String VRIJEDPAR_FISKALIZACIJA_JKS_ALIAS = "FSK_JKS_ALIAS";
		public static final String VRIJEDPAR_FISKALIZACIJA_SERVICE_URL = "FSK_SRV_URL";
		public static final String VRIJEDPAR_FISKALIZACIJA_SERVICE_NAME = "FSK_SRV_URL";
		public static final String VRIJEDPAR_FISKALIZACIJA_NACIN_RADA = "FSK_MODE";
		public static final String VRIJEDPAR_FISKALIZACIJA_TIPOVI_NALOGA = "FSK_TIP_NAL";

		public static final Integer PARTIJA_NIVOPPAR_DEFAULT = 1;

		public static final String PARTIJABLAGAJNE_PARAMPAR_TREZORSKA_BLAGAJNA = "T";

		// proizvod
		public static final Integer PROIZVOD_SIFRAPRO_RETAIL_TEKUCI_UTUZEN = 4;
		public static final Integer PROIZVOD_SIFRAPRO_RETAIL_AVISTA_PRICUVA = 9;
		public static final Integer PROIZVOD_SIFRAPRO_RETAIL_KUNSKI_RACUN_NEREZIDENTA = 10;
		public static final Integer PROIZVOD_SIFRAPRO_RETAIL_AVISTA_SREDSTVA_IZUZETA_OD_OVRHE = 19;
		public static final Integer PROIZVOD_SIFRAPRO_RETAIL_PREPAID = 23;
		public static final Integer PROIZVOD_SIFRAPRO_RETAIL_TEKUCI = 25;
		public static final Integer PROIZVOD_SIFRAPRO_RETAIL_ZIRO = 26;
		public static final Integer PROIZVOD_SIFRAPRO_RETAIL_AVISTA = 27;
		public static final Integer PROIZVOD_SIFRAPRO_RETAIL_BLAGAJNA = 28;
		public static final Integer PROIZVOD_SIFRAPRO_RETAIL_MJENJAC = 29;

		public static final String PROIZVOD_VRSTAPRO_PREPAID_RETAIL = "RET_PREP";
		public static final String PROIZVOD_VRSTAPRO_TEKUCI_RETAIL = "RET_TEK";
		public static final String PROIZVOD_VRSTAPRO_ZIRO_RETAIL = "RET_ZIRO";
		public static final String PROIZVOD_VRSTAPRO_AVISTA_RETAIL = "RET_AVI";
		public static final String PROIZVOD_VRSTAPRO_RETAIL_BLAGAJNA = "RET_BLAG";
		public static final String PROIZVOD_VRSTAPRO_RETAIL_MJENJAC = "RET_MJE";

		// tarifna_stavka

		// tip dodatnog podatka
		public static final Integer TIPDODPOD_SIFRATIP_RETAIL_PODATAK_ZA_IZVOD_DOM = 55;
		public static final Integer TIPDODPOD_SIFRATIP_RETAIL_PODATAK_ZA_IZVOD_DEV = 56;
		public static final Integer TIPDODPOD_SIFRATIP_RETAIL_UPRAVITELJ_ZIRO_RACUNA_PRICUVE = 59;

		// tip naloga
		public static final String TIPNALOGA_NACIZPOD_RETAIL_STROJNA_OBRADA = "S";

		public static final Integer TIPNALOGA_SIFRAPOD_B2_SALTERSKI_VIRMAN_EKSTERNI_PRIJE_DATUMA_VALUTE = 190;
		public static final Integer TIPNALOGA_SIFRAPOD_B2_SALTERSKI_VIRMAN_INTERNI_PRIJE_DATUMA_VALUTE = 191;
		public static final Integer TIPNALOGA_SIFRAPOD_B2_SALTERSKI_VIRMAN_EKSTERNI_NAKNADA = 192;
		public static final Integer TIPNALOGA_SIFRAPOD_B2_SALTERSKI_VIRMAN_INTERNI_NAKNADA = 193;

		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_OBRACUN_NAKNADE_RUCNO = 20;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_PROMJENA_TR_BEZ_KARTICE_U_S_KARTICOM = 24;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_OSTALO_FAKTURA_UPLATA = 25;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_OSTALO_FAKTURA_ISPLATA = 26;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_OTKUP_EFEKTIVE_MJENJAC = 27;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_OBRACUN_NAKNADE_PRIVATNO_BANKARSTVO = 28;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_UPLATA_OSTALO = 29;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_ISPLATA_OSTALO = 30;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_OSTALO_UPLATA_POLICE_OSIGURANJA = 31;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_OBRACUN_NAKNADE_VODJENJE_RACUNA_TR_NEREZIDENT = 32;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_OBRACUN_NAKNADE_ZA_IZDAVANJE_KARTICE = 34;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_OBRACUN_NAKNADE_ZA_REIZDAVANJE_KARTICE = 35;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_NAKNADA_FAKTURIRANJE = 60;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_NAKNADA_NAPLATA = 61;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_UPLATA_KARTICA_POS = 70;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_BLAGAJNA_ULAZ = 71;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_BLAGAJNA_IZLAZ = 72;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_BLAGAJNA_PRIMOPREDAJA_ULAZ = 73;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_BLAGAJNA_PRIMOPREDAJA_IZLAZ = 74;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_ATM_PUNJENJE = 75;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_ATM_PRAZNJENJE = 76;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_APOENSKA_STRUKTURA = 77;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_ISPLATA_KARTICA_POS = 80;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_PRIJENOS_NA_KREDIT = 83;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_PO_PRIJENOS_AVISTA = 84;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_OBRACUN_NAKNADE_VODJENJE_RACUNA_TR = 85;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_OBRACUN_PASIVNE_KAMATE_AVISTA = 86;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_PRIJENOS_S_KREDITA = 88;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_PO_PRIJENOS_S_AVISTE = 89;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_PO_ISPLATA_AVISTA = 91;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_OBRACUN_PASIVNE_KAMATE_ZIRO = 92;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_NAPLATA_KREDITNE_KAMATE_TEKUCI = 97;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_NAPLATA_ZATEZNE_KAMATE_TEKUCI = 98;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_FAKTURA_PARTIJA = 99;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_FAKTURA_BLAGAJNA = 100;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_PRIJENOS_NA_OSTALO = 101;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_PRIJENOS_S_OSTALO = 102;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_PRIPIS_PASIVNE_KAMATE_AVISTA = 103;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_PRIPIS_PASIVNE_KAMATE_ZIRO = 104;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_OBRACUN_PASIVNE_KAMATE_DEVIZNI = 105;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_PRIPIS_PASIVNE_KAMATE_DEVIZNI = 106;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_OSTALO_UPLATA = 107;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_OSTALO_ISPLATA = 108;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_PO_POLOG_UTRSKA = 126;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_PO_UPLATA_AVISTA = 127;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_RASPODJELA_DOSPJELOG_DUGA = 128;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_PRIJENOS_PARTIJA_2 = 133;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_UPLATA_KREDITA = 136;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_ISPLATA_KREDITA = 137;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_BLAGAJNA_MANJAK = 141;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_BLAGAJNA_VISAK = 142;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_OTKUP_EFEKTIVE_OSTECENA_NOVCANICA = 143;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_ZBIRNI_PRIPIS_PASIVNE_KAMATE = 150;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_ZBIRNI_OBRACUN_NAKNADE_TEKUCI = 151;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_ZBIRNI_OBRACUN_KAMATE_AVISTA = 152;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_ZBIRNI_OBRACUN_KAMATE_ZIRO = 153;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_ZBIRNI_RASPODJELA_PREKORACENJA = 155;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_ZBIRNI_RASPODJELA_DOSPJELOG_DUGA = 156;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_ZBIRNI_OBRACUN_KAMATE_DEVIZNI = 157;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_ZBIRNI_SALDACIJA_PARTIJE = 158;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_NAPLATA_NAKNADE = 161;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_ZBIRNI_OBRACUN_NAKNADE_KARTICA = 162;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_ZBIRNI_SLANJE_IZVODA_NA_EMAIL = 163;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_IZVOD = 167;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_IZVOD_TEKUCI = 168;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_IZVOD_ZIRO = 169;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_SMS_TEKUCI = 170;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_IZVOD_SLANJE_EMAIL = 171;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_KARTICA_AKCIJA = 172;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_PREKORACENJE_PROMJENA_IZNOSA = 191;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_PREKORACENJE_PROMJENA_KORISTENJA = 192;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_FINANCIJSKI_ZAKLJUCAK = 203;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_ISPIS_IZVODA_ZA_GRADJANE = 205;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_PREBACIVANJE_PARTIJE_U_STATUS_NEAKTIVNA = 206;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_UPLATA_FAKTURA = 321;

		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_INTERNI_OPCI_NALOG = 400;
		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_INTERNI_OPCI_NALOG_UPLATA_NA_PARTIJU = 401;

		public static final Integer TIPNALOGA_SIFRAPOD_RETAIL_GENERIRANJE_CRR_FL_DATOTEKE = 10086;

		public static final Integer TIPNALOGA_SIFRAPOD_OPCI_DEVIZNI_PRILJEV = 1262;
		public static final Integer TIPNALOGA_SIFRAPOD_SALTER_TN_BLOKADA_PARTIJE_B2 = 417;
		public static final Integer TIPNALOGA_SIFRAPOD_PLATNIPROMET_TN_BLOKADA_1 = 170;

		public static final Integer TIPNALOGA_SIFRAPOD_RASPODJELA_SALDA = 71;
		public static final Integer TIPNALOGA_SIFRAPOD_PRIPIS_DOSPJELE_KAMATE = 630;

		// tip partije
		public static final Integer TIPPARTIJE_SIFRATIP_RETAIL_TEKUCI_RACUN_GRADJANA_UTUZEN = 55;
		public static final Integer TIPPARTIJE_SIFRATIP_RETAIL_AVISTA_KUNSKI_RACUN = 1001;
		public static final Integer TIPPARTIJE_SIFRATIP_RETAIL_AVISTA_RACUN_SREDSTAVA_IZUZETIH_OD_OVRHE = 1002;
		public static final Integer TIPPARTIJE_SIFRATIP_RETAIL_AVISTA_KM_GRADJANI = 1101;
		public static final Integer TIPPARTIJE_SIFRATIP_RETAIL_AVISTA_KM_GRADJANI_VERIFIKOVANA_STARA_DEVIZNA_STEDNJA = 1102;
		public static final Integer TIPPARTIJE_SIFRATIP_RETAIL_AVISTA_KM_GRADJANI_NEREZIDENTI = 1111;
		public static final Integer TIPPARTIJE_SIFRATIP_RETAIL_AVISTA_RACUN = 1201;
		public static final Integer TIPPARTIJE_SIFRATIP_RETAIL_AVISTA_STEDNJA = 1202;
		public static final Integer TIPPARTIJE_SIFRATIP_RETAIL_AVISTA_RACUN_ZA_PLACANJE_U_INOZEMSTVO = 1203;
		public static final Integer TIPPARTIJE_SIFRATIP_RETAIL_AVISTA_RACUN_NEREZIDENT = 1211;
		public static final Integer TIPPARTIJE_SIFRATIP_RETAIL_AVISTA_STEDNJA_NEREZIDENT = 1212;
		public static final Integer TIPPARTIJE_SIFRATIP_RETAIL_AVISTA_RACUN_ZA_PLACANJE_U_INOZEMSTVO_NEREZIDENT = 1213;
		public static final Integer TIPPARTIJE_SIFRATIP_RETAIL_ZIRO_RACUN_GRADJANA = 1401;
		public static final Integer TIPPARTIJE_SIFRATIP_RETAIL_ZIRO_RACUN_PRICUVE = 1402;
		public static final Integer TIPPARTIJE_SIFRATIP_RETAIL_ZIRO_RACUN_STRANCA = 1411;
		public static final Integer TIPPARTIJE_SIFRATIP_RETAIL_TEKUCI_BEZ_DEBITNE_KARTICE = 1500;
		public static final Integer TIPPARTIJE_SIFRATIP_RETAIL_TEKUCI_RACUN_GRADJANA = 1501;
		public static final Integer TIPPARTIJE_SIFRATIP_RETAIL_TEKUCI_RACUN_STRANCA = 1511;
		public static final Integer TIPPARTIJE_SIFRATIP_RETAIL_DEPOZIT_UZ_PREPAID_KARTICU = 1599;
		public static final Integer TIPPARTIJE_SIFRATIP_RETAIL_MJENJAC_TRGOVACKO_DRUSTVO = 1901;
		public static final Integer TIPPARTIJE_SIFRATIP_RETAIL_MJENJAC_OBRT = 1902;
		public static final Integer TIPPARTIJE_SIFRATIP_RETAIL_ZASTICENI_RACUN_GRADJANA = 1342;

		public static final Integer TIP_PARTIJE_RETAIL_TEKUCI_NEREZIDENTI = 1158;
		public static final Integer TIP_PARTIJE_RETAIL_ZIRO_REZIDENTI = 1159;
		public static final Integer TIP_PARTIJE_RETAIL_ZIRO_NEREZIDENTI = 1160;
		public static final Integer TIP_PARTIJE_RETAIL_TEKUCI_DOZVOLA_BORAVKA = 1161;
		public static final Integer TIP_PARTIJE_RETAIL_ZIRO_NEREZIDENTI_DOZVOLA_BORAVKA = 1162;

		public static final String TIPPARTIJE_POSAOTIP_TREZOR = "07";
		public static final String TIPPARTIJE_POSAOTIP_BLAGAJNA = "08";
		public static final String TIPPARTIJE_POSAOTIP_BANKOMAT = "09";
		public static final String TIPPARTIJE_POSAOTIP_AVISTA_DOMICILNA = "10";
		public static final String TIPPARTIJE_POSAOTIP_AVISTA_DEVIZNA = "12";
		public static final String TIPPARTIJE_POSAOTIP_ZIRO = "31";
		public static final String TIPPARTIJE_POSAOTIP_PREPAID = "13";
		public static final String TIPPARTIJE_POSAOTIP_MJENJAC = "80";

		public static final Integer TIPKAMATE_PASIVNA_REDOVNA = 5;
		public static final Integer TIPKAMATE_AKTIVNA_KREDITNA = 6;
		public static final Integer TIPKAMATE_AKTIVNA_ZATEZNA = 7;
		public static final Integer TIPKAMATE_PASIVNA_AVISTA_STEDNJA = 19;

		public static final Integer BROJ_DANA_UNATRAG_DEVIZNO_POSLOVANJE = 0;

		public static final String POJED_SIFRAPOJ_TIM_ZA_TREZOR_UPRAVLJANJE_GOTOVINOM = "574";
		public static final String POJED_SIFRAPOJ_TIM_ZA_LIKVIDANOST = "575";

		// partija retail
		public static final String PARTIJA_RETAIL_DEVPOPAR_DOMICILNI_RACUN = "0";
		public static final String PARTIJA_RETAIL_DEVPOPAR_DOMICILNI_I_DEVIZNI_RACUN = "1";

		public static final String PARTIJA_RETAIL_VRODOPAR_NEMA_PREKORACENJA = "N";
		public static final String PARTIJA_RETAIL_VRODOPAR_RUCNO_POSTAVLJANJE_PREKORACENJA = "R";
		public static final String PARTIJA_RETAIL_VRODOPAR_AUTOMATSKO_POSTAVLJANJE_PREKORACENJA = "A";
		//spiskovi
		public static final Integer KLASANALOGA_SIFRAKNA_RETAIL_SPISAK = 10005;
		public static final String DIREKTORIJ_DATOTEKE_SPISAK_HZMO_MIROVINE = "/data/IN/SPISAK/HZMO/";
		public static final String DIREKTORIJ_DATOTEKE_SPISAK_HZMO_DD = "/data/IN/SPISAK/DD/";
		public static final String DIREKTORIJ_DATOTEKE_SPISAK_HZZZ = "/data/IN/SPISAK/HZZZ/";
		public static final String DIREKTORIJ_DATOTEKE_SPISAK_ARHIVA = "/data/IN/SPISAK/ARHIVA/";
		public static final String PARTIJA_PARTIPAR_ZADUZENJE_MIROVINA = "9940002326";
		public static final String PARTIJA_PARTIPAR_ZADUZENJE_DJECJI = "9940002326";
		public static final String PARTIJA_PARTIPAR_ZADUZENJE_HZZZ = "9940002406";
		public static final String PARTIJA_PARTIPAR_ZADUZENJE_RODILJE = "9940002406";
		public static final String KONTO_SIFRAKON_ZADUZENJE_DJECJI = "1629";
		public static final String KONTO_SIFRAKON_ZADUZENJE_MIROVINA = "1629";
		public static final String KONTO_SIFRAKON_ZADUZENJE_HZZZ = "1629";
		public static final String KONTO_SIFRAKON_ZADUZENJE_RODILJE = "1629";
		public static final String NADDIO_OZNAKA_ZADUZENJE_DJECJI = "K10045000";
		public static final String NADDIO_OZNAKA_ZADUZENJE_MIROVINA = "K14818000";
		public static final String NADDIO_OZNAKA_ZADUZENJE_HZZZ = "K10045000";
		public static final String NADDIO_OZNAKA_ODOBRENJE_UMRLI = "K26993000";
		public static final String OZNKANAD_SPISAK_PLATA = "90";
		public static final String OZNKANAD_SPISAK_KREDITI = "91";
		public static final String OZNKANAD_SPISAK_CEKOVI_POSTE = "92";
		public static final String OZNKANAD_SPISAK_CEKOVI_DRUGE_BANKE = "93";
		public static final String OZNKANAD_SPISAK_CEKOVI_FINA = "94";
		public static final String OZNKANAD_SPISAK_MIROVINE_HZMO = "95";
		public static final String VRDUGNAD_SPISAK = "XX";

		public static final Integer TIPNALOGA_SIFRAPOD_SPISAK_AUTOMATIKA_ZBIRNI = 14132; //zbirni nalog kod datoteke mirovina
		public static final Integer TIPNALOGA_SIFRAPOD_SPISAK_AUTOMATIKA_DD_ZBIRNI = 14131;
		public static final Integer TIPNALOGA_SIFRAPOD_SPISAK_AUTOMATIKA_NN_ZBIRNI = 14140;
		public static final Integer TIPNALOGA_SIFRAPOD_SPISAK_KREDIT_OSTALI = 14134;
		public static final Integer TIPNALOGA_SIFRAPOD_SPISAK_PLACE = 14135;
		public static final Integer TIPNALOGA_SIFRAPOD_SPISAK_DJECJI_DOPLATAK = 14142;
		public static final Integer TIPNALOGA_SIFRAPOD_SPISAK_HZMO_ALIMENTACIJA = 14143;
		public static final Integer TIPNALOGA_SIFRAPOD_SPISAK_HZMO_MIROVINE = 14144;
		public static final Integer TIPNALOGA_SIFRAPOD_SPISAK_HZZZ = 14145;
		//kraj za spisak		

		public static final List<String> LIST_DIJELOVA_SALDO_BEZ_KAMATE = Arrays.asList(new String[] { "01", "02", "03" });
		public static final String OZNKANAD_OSNOVNA_SREDSTVA = "01";
		public static final List<String> LIST_DIJELOVA_SALDO = Arrays.asList(new String[] { "01", "02", "03", "05", "19" });
	}

	public static class Riznica extends GenericBassxConstants.Riznica {
		// messages
		public static final String MESSAGE_BUNDLE = "hr.abit.b3.biz.riznica.message.riznicaMessages";

		// generic params
		public static final String MAX_FIRST_LIST_SIZE = "100";
		public static final String IS_COVER_MESAGE = "Y";

		// status SWIFT poruke
		public static final String SWIFT_STATUS_CREATED = "0";
		public static final String SWIFT_STATUS_RETYPED = "1";
		public static final String SWIFT_STATUS_MATCHED = "3";
		public static final String SWIFT_STATUS_SENT = "4";
		public static final String SWIFT_STATUS_FINISHED = "7";
		public static final String SWIFT_STATUS_CANCELED = "8";
		// status obrade naloga riznice
		public static final String NALOG_RIZNICE_SWIFT_STATUS_INICIRAN = "0";
		public static final String NALOG_RIZNICE_SWIFT_STATUS_SPREMAN = "1";
		public static final String NALOG_RIZNICE_SWIFT_STATUS_ZAVRSEN = "7";
		public static final String NALOG_RIZNICE_SWIFT_STATUS_POVUCEN = "8";
		// status obrade izvoda
		public static final String SWIFT_IZVOD_STATUS_INICIRAN = "0";
		public static final String SWIFT_IZVOD_STATUS_ZAVRSEN = "7";
		public static final String SWIFT_IZVOD_STATUS_POVUCEN = "8";
		public static final String SWIFT_IZVOD_OPIS_RTGS = "RTGS";
		public static final String SWIFT_IZVOD_OPIS_DEVIZNI = "devizni";
		// partije banke
		public static final String PARTIJA_KONOTKORENTA_BANKE = "1022222220";
		public static final String SWIFTBIC_BICCOBIC_HOME_BANK_TEST = "PAZGHR20XXX";
		public static final String SWIFTBIC_BICCOBIC_DEFAULT_SWIFT_BIC = "XXXXXXXXXXX";
		public static final String SWIFTBIC_BICCOBIC_HNB_INTERNATIONAL = "NBHRHR2XXXX";
		public static final String SWIFTBIC_BICCOBIC_FINA = "FNAGHR22XXX";
		public static final List<String> LIST_SWIFTBIC_BICCOBIC_HNB = Arrays.asList(SWIFTBIC_BICCOBIC_HNB_INTERNATIONAL, SWIFTBIC_BICCOBIC_HNB_DOMESTIC);
		public static final String SWIFTBIC_BICCOBIC_HNB_HSVP = "HSVPHR2AXXX";
		public static final String SWIFTBIC_BICCOBIC_SDA = "SDAHHR22XXX";
		public static final String SWIFTBIC_BICCOBIC_TARGET2 = "TRGTXE3MXXX";
		public static final String SWIFTBIC_BICCOBIC_TARGET2_LIMIT = "TRGTXEPMASI";
		public static final String SWIFTBIC_BICCOBIC_TARGET2_TRGTXECBNLX = "TRGTXECBNLX";
		public static final String SWIFTBIC_BICCOBIC_TARGET2_ODGOVOR_MT012 = "EBEBXXXXXXX";
		public static final String SWIFTBIC_BICCOBIC_TARGET2_IZVOD = "TRGTXE3MXXX";
		public static final String SWIFTBIC_BICCOBIC_FINA_NKS_INSTATNT = "FNAGHR22XXX";
		public static final List<String> SWIFTBIC_BICCOBIC_TARGET2_LIST = Arrays.asList(new String[] { SWIFTBIC_BICCOBIC_TARGET2 });
		public static final List<String> SWIFTBIC_BICCOBIC_HSVP_LIST = Arrays.asList(SWIFTBIC_BICCOBIC_HNB_HSVP);
		public static final String VALUE_DATE = "valueDate";
		public static final String CURRENCY = "currency";
		public static final String AMOUNT = "amount";
		public static final String MESS_COUNT = "count";
		// grupe računa
		public static final String PARTIJA_GRUPA_RACUNA_BANKE = "10";
		public static final String PARTIJA_GRUPA_RACUNA_UZETI_KREDITI_LIMIT = "76";
		public static final String PARTIJA_GRUPA_RACUNA_UZETI_KREDITI_IZVOR_FINANCIRANJA = "77";
		public static final String PARTIJA_GRUPA_RACUNA_KONTOKORENTNI_RACUN_DOMICILNI = "90";
		public static final String PARTIJA_GRUPA_RACUNA_KONTOKORENTNI_RACUN_STRANI = "91";
		public static final String PARTIJA_GRUPA_RACUNA_MEDUBANKARSKI_DANI_DEPOZIT_DOMICILNI = "331";
		public static final String PARTIJA_GRUPA_RACUNA_MEDUBANKARSKI_DANI_DEPOZIT_STRANI = "335";
		public static final String PARTIJA_GRUPA_RACUNA_MEDUBANKARSKI_OBAVEZNA_PRIČUVA = "338";
		public static final String PARTIJA_GRUPA_RACUNA_KORESPODENTNI = "30";
		// račun ino valuta
		public static final String RACUN_INO_VALUTA_STATUS_AKTIVNA = "A";
		public static final String RACUN_INO_VALUTA_STATUS_NEAKTIVNA = "N";
		// tip naloga
		public static final Integer TIP_NALOGA_MIGRACIJA_KONTOKORENTNIH_RACUNA = 2;
		public static final Integer TIP_NALOGA_MIGRACIJA_AVPO_RACUNA = 3;
		public static final Integer TIP_NALOGA_MIGRACIJA_PARTIJA_DEVIZNIH_DEPOZITA = 7;
		public static final Integer TIP_NALOGA_DEVIZNI_DEPOZIT_NEWT = 3000;
		public static final Integer TIP_NALOGA_DEVIZNI_DEPOZIT_AMND = 3001;
		public static final Integer TIP_NALOGA_DEVIZNI_DEPOZIT_ROLL = 3002;
		public static final Integer TIP_NALOGA_DEVIZNI_DEPOZIT_MATU = 3003;
		public static final Integer TIP_NALOGA_DEVIZNI_OBRACUN_NEDOSPJELE_KAMATE = 3004;
		public static final Integer TIP_NALOGA_DEVIZNI_DEPOZIT_CANC = 3005;
		public static final Integer TIP_NALOGA_UZETI_KREDIT_OTPLATNI_PLAN = 3010;
		public static final Integer TIP_NALOGA_RASKNJIZENJE_GLAVNICE_LENDER = 3050;
		public static final Integer TIP_NALOGA_RASKNJIZENJE_GLAVNICE_BORROWER = 3051;
		public static final Integer TIP_NALOGA_KNJIZENJE_GLAVNICE_LENDER = 3100;
		public static final Integer TIP_NALOGA_KNJIZENJE_GLAVNICE_BORROWER = 3101;
		public static final Integer TIP_NALOGA_KNJIZENJE_KAMATE_LENDER_ACTIVE = 3200;
		public static final Integer TIP_NALOGA_KNJIZENJE_KAMATE_BORROWER_PASSIVE = 3201;
		public static final Integer TIP_NALOGA_KNJIZENJE_NEDOSPJELE_KAMATE_LENDER_ACTIVE = 3210;
		public static final Integer TIP_NALOGA_KNJIZENJE_NEDOSPJELE_KAMATE_BORROWER_PASSIVE = 3211;
		public static final Integer TIP_NALOGA_KNJIZENJE_POVRATA_LENDER = 3300;
		public static final Integer TIP_NALOGA_KNJIZENJE_POVRATA_BORROWER = 3301;
		public static final Integer TIP_NALOGA_TRZISTE_KUPNJA_DEVIZA = 3401;
		public static final Integer TIP_NALOGA_TRZISTE_PRODAJA_DEVIZA = 3402;
		public static final Integer TIP_NALOGA_TRZISTE_TRANSFER = 3403;
		public static final Integer TIP_NALOGA_TRZISTE_ARBITRAZA = 3404;
		public static final Integer TIP_NALOGA_TRZISTE_KUPNJA_SWAP = 3411;
		public static final Integer TIP_NALOGA_TRZISTE_PRODAJA_SWAP = 3412;
		public static final Integer TIP_NALOGA_TRZISTE_TRANSFER_TGT = 3413;
		public static final Integer TIP_NALOGA_TRZISTE_VANBILANSNO_KNJIZENJE_KUPNJA = 3421;
		public static final Integer TIP_NALOGA_TRZISTE_VANBILANSNO_ISKNJIZENJE_KUPNJA = 3422;
		public static final Integer TIP_NALOGA_TRZISTE_VANBILANSNO_KNJIZENJE_PRODAJA = 3423;
		public static final Integer TIP_NALOGA_TRZISTE_VANBILANSNO_ISKNJIZENJE_PRODAJA = 3424;
		public static final Integer TIP_NALOGA_SIFRAPOD_UZETI_KREDIT_OBRACUN_NAKNADE_LIMIT = 901;
		public static final Integer TIP_NALOGA_SIFRAPOD_UZETI_KREDIT_OBRACUN_NAKNADE_UZETI_KREDIT = 902;
		public static final Integer TIP_NALOGA_UZETI_KREDIT_PUNJENJE_NEDOSPJELE_GLAVNICE = 3002;
		public static final Integer TIP_NALOGA_UZETI_KREDIT_OTPLATNI_PLAN_DOSPIJECE_MJESECNO = 3450;
		public static final Integer TIP_NALOGA_UZETI_KREDIT_OTPLATNI_PLAN_DOSPIJECE_KVARTALNO = 3451;
		public static final Integer TIP_NALOGA_UZETI_KREDIT_KNJIZENJE_VANBILANCE_LIMITA = 3452;
		public static final Integer TIP_NALOGA_UZETI_KREDIT_ISKNJIZENJE_VANBILANCE_LIMITA = 3453;
		public static final Integer TIP_NALOGA_UZETI_KREDIT_KNJIZENJE_VANBILANCE_PARTIJE_UZETOG_KREDITA = 3454;
		public static final Integer TIP_NALOGA_UZETI_KREDIT_ISKNJIZENJE_VANBILANCE_PARTIJE_UZETOG_KREDITA = 3455;
		public static final Integer TIP_NALOGA_UZETI_KREDIT_ISPLATA_DOSPJELIH_OBVEZA_INTERNI = 3456;
		public static final Integer TIP_NALOGA_UZETI_KREDIT_ISPLATA_DOSPJELIH_OBVEZA_EKSTERNI = 3457;
		public static final Integer TIP_NALOGA_SIFRAPOD_RIZNICA_OBRACUN_NEDOSPJELE_KAMATE_ZBIRNI = 3800;
		public static final Integer TIP_NALOGA_SIFRAPOD_RIZNICA_OBRACUN_DOSPJELE_KAMATE_ZBIRNI = 3801;
		public static final Integer TIP_NALOGA_SIFRAPOD_RIZNICA_VREMENSKO_RAZGRANICENJE_TROSKA_NAKNADE_LIMITA_ZBIRNI = 3802;
		public static final Integer TIP_NALOGA_SIFRAPOD_RIZNICA_VREMENSKO_RAZGRANICENJE_TROSKA_NAKNADE_UZETIH_KREDITA_ZBIRNI = 3803;
		public static final Integer TIP_NALOGA_SIFRAPOD_RIZNICA_OBRACUN_TECAJNIH_RAZLIKA_UZETIH_KREDITA_ZBIRNI = 3804;
		public static final Integer TIP_NALOGA_KNJIZENJE_NEDOSPJELE_KAMATE_UZETOG_KREDITA = 3810;
		public static final Integer TIP_NALOGA_KNJIZENJE_DOSPJELE_KAMATE_UZETOG_KREDITA = 3811;
		public static final Integer TIP_NALOGA_KNJIZENJE_RIZNICA_VREMENSKO_RAZGRANICENJE_TROSKA_NAKNADE_LIMITA = 3812;
		public static final Integer TIP_NALOGA_KNJIZENJE_RIZNICA_VREMENSKO_RAZGRANICENJE_TROSKA_NAKNADE_UZETIH_KREDITA = 3813;
		public static final Integer TIP_NALOGA_NEFINLOG_OTVARANJE_DEPOZITA = 10029;
		public static final Integer TIP_NALOGA_NEFINLOG_ROLLOVER_DEPOZITA = 10030;
		public static final Integer TIP_NALOGA_NEFINLOG_ZATVARANJE_DEPOZITA = 10038;
		public static final Integer TIP_NALOGA_NEFINLOG_CANCELLATION_DEPOZITA = 10058;
		public static final Integer TIP_NALOGA_NEFINLOG_CLOSE_DEVIZNI_IZVOD = 10054;
		public static final Integer TIP_NALOGA_NEFINLOG_CLOSE_STAVKA_DEVIZNOG_IZVODA = 10055;
		public static final Integer TIP_NALOGA_NEFINLOG_ZATVARANJE_KONTOKORENTA_KORESPODENTA = 10057;
		public static final Integer TIP_NALOGA_NEFINLOG_UZETI_KREDIT_PROIZVOD = 10064;
		public static final Integer TIP_NALOGA_NEFINLOG_UZETI_KREDIT_PARTIJA = 10069;
		public static final Integer TIP_NALOGA_NEFINLOG_UZETI_KREDIT_PARTIJA_NULTE_TRANSE = 10070;
		public static final Integer TIP_NALOGA_NEFINLOG_AMENDMENT_DEPOZITA = 10073;
		public static final Integer TIP_NALOGA_NEFINLOG_UZETI_KREDIT_KOMITENT_ZA_PLASMAN = 10074;
		public static final Integer TIP_NALOGA_NEFINLOG_PARTIJA_KONTOKORENTA = 10075;
		public static final Integer TIP_NALOGA_NEFINLOG_PARTIJA_KORESPODENTA = 10200;
		public static final Integer TIP_NALOGA_NEFINLOG_SPREAD_VALUTA = 10196;
		public static final Integer TIP_NALOGA_NEFINLOG_AZURIRANJE_TECAJNE_LISTE = 10197;
		public static final Integer TIP_NALOGA_NEFINLOG_IZMJENA_TECAJNE_LISTE = 10198;
		public static final Integer TIP_NALOGA_NEFINLOG_SWIFT_BIC_FILE_UPLOAD = 10333;
		public static final Integer TIP_NALOGA_NEFINLOG_SWIFT_BIC_FILE_PROCESS = 10334;
		public static final Integer TIP_NALOGA_RETURN_SWIFT_MESSAGE = 2501;
		public static final Integer TIP_NALOGA_REJECT_SWIFT_MESSAGE = 2502;
		public static final Integer TIP_NALOGA_MT199_CONFIRMATION_SWIFT_MESSAGE = 2503;
		public static final Integer TIP_NALOGA_MT199_REJECT_SWIFT_MESSAGE = 2504;
		public static final Integer TIP_NALOGA_MT199_PROGRESS_SWIFT_MESSAGE = 2505;
		public static final String KLASA_NALOGA_GRUPA_OTPLATNI_PLAN_UZETI_KREDIT = "KNOPU";
		public static final String KLASA_NALOGA_GRUPA_OTPLATNI_PLAN_UZETI_KREDIT_NALOZI_DOSPIJECA_MJESECNO = "KNUKD";
		public static final String KLASA_NALOGA_GRUPA_OTPLATNI_PLAN_UZETI_KREDIT_NALOZI_DOSPIJECA_KVARTALNO = "KNUKK";
		public static final String KLASA_NALOGA_GRUPA_UZETI_KREDIT_VANBILANCA = "KNUVB";
		public static final String KLASA_NALOGA_GRUPA_UZETI_KREDIT_OBRACUN_KAMATE = "KNSOK";
		public static final String KLASA_NALOGA_GRUPA_UZETI_KREDIT_ISPLATA_DOSPJELIH_OBVEZA = "KNUIO";
		public static final String KLASA_NALOGA_GRUPA_UZETI_KREDIT_VREMENSKO_RAZGRANICENJE_NAKNADE = "KNVRN";
		public static final String KLASA_NALOGA_GRUPA_UZETI_KREDIT_PUNJENJE_NEDOSPJELE_GLAVNICE = "KNNGK";
		public static final Integer KLASA_NALOGA_UZETI_KREDIT_OTPLATNI_PLAN = 3001;
		public static final Integer KLASA_NALOGA_UZETI_KREDIT_PUNJENJE_NEDOSPJELE_GLAVNICE = 3002;
		public static final Integer KLASA_NALOGA_SIFRAKNA_UZETI_KREDIT_DOSPIJECE_MJESECNO = 3450;
		public static final Integer KLASA_NALOGA_SIFRAKNA_UZETI_KREDIT_DOSPIJECE_KVARTALNO = 3451;
		public static final Integer KLASA_NALOGA_SIFRAKNA_KNJIZENJE_VANBILANCE_IZVORA_FINANCIRANJA = 3452;
		public static final Integer KLASA_NALOGA_SIFRAKNA_ISKNJIZENJE_VANBILANCE_IZVORA_FINANCIRANJA = 3453;
		public static final Integer KLASA_NALOGA_SIFRAKNA_ISPLATA_DOSPJELIH_OBVEZA_INTERNI = 3456;
		public static final Integer KLASA_NALOGA_SIFRAKNA_ISPLATA_DOSPJELIH_OBVEZA_EKSTERNI = 3457;
		public static final Integer KLASA_NALOGA_KNJIZENJE_NEDOSPJELE_KAMATE_UZETOG_KREDITA = 3810;
		public static final Integer KLASA_NALOGA_KNJIZENJE_DOSPJELE_KAMATE_UZETOG_KREDITA = 3811;
		public static final Integer KLASA_NALOGA_KNJIZENJE_VREMENSKO_RAZGRANICENJE_NAKNADE_LIMITA = 3812;
		public static final Integer KLASA_NALOGA_KNJIZENJE_VREMENSKO_RAZGRANICENJE_NAKNADE_UZETOG_KREDITA = 3813;
		public static final Integer TIP_VEZE_SIFRATPV_POVEZANE_PRISTUPNICE_LIMITA_IZVORA_FINANCIRANJA = 10;
		public static final Integer TIP_VEZE_SIFRATPV_POVEZANE_PRISTUPNICE_PLASMANA_IZVORA_FINANCIRANJA = 20;
		public static final Integer TIP_VEZE_SIFRATPV_POVEZANE_PRISTUPNICE_AVANS = 21;
		// FINA
		public static final String FINA_MESSAGE_TYPE_300 = "300";
		public static final String FINA_MESSAGE_TYPE_301 = "301";
		public static final String FINA_MESSAGE_TYPE_309 = "309";
		public static final String FINA_MESSAGE_TYPE_399 = "399";
		public static final String FINA_VRSTA_NALOGA_NACIONALNA_PLACANJA_HRK = "1";
		public static final String FINA_VRSTA_NALOGA_MEDJUNARODNA_PLACANJA = "2";
		public static final String FINA_VRSTA_NALOGA_NACIONALNA_PLACANJA_STRANA_VALUTA = "3";
		public static final String FINA_VRSTA_NALOGA_PLACE_PRIMANJA = "4";
		// oznake drugih analitika
		public static final String RIZNICA_OZNAKA_DRUGE_ANALITIKE_UPP = "upp";
		// globali
		public static final String FILE_HANDLER_PARAM = "file";
		public static final String PARAMETAR_SIFRAPAR_DIR = "ftpDir";
		public static final String PARAMETAR_SIFRAPAR_DIR_IN = "ftpDirI";
		public static final String PARAMETAR_SIFRAPAR_DIR_OUT = "ftpDirO";
		public static final String PARAMETAR_SIFRAPAR_DIR_ARHIVE = "ftpDirA";
		public static final String PARAMETAR_SIFRAPAR_REMOTE_TRANSFER = "RemTransf";
		// SWIFT obrada
		public static final String PARAMETAR_MAIL_TECAJNA_LISTA = "MAIL_TECAJ";
		public static final Integer TIP_NALOGA_SWIFT_CREATE_MESSAGE_RIZNICA = 1502;
		/**
		 * DEVIZNI DEPOZITI
		 */
		public static final String DEVIZNI_DEPOZIT_POSAO = "92";
		public static final String DEVIZNI_DEPOZIT_OROLEDEP_LENDER = "L";
		public static final String DEVIZNI_DEPOZIT_OROLEDEP_BORROWER = "B";
		public static final String DEVIZNI_DEPOZIT_ETYPEDEP_OTVARANJE = "CONF";
		public static final String DEVIZNI_DEPOZIT_ETYPEDEP_ROLLOVER = "ROLL";
		public static final String DEVIZNI_DEPOZIT_ETYPEDEP_DOSPIJECE_MATURITY = "MATU";
		public static final String DEVIZNI_DEPOZIT_ETYPEDEP_DOSPIJECE_CALL = "SETT";
		public static final String RIZNICA_OTYPE_AMENDATION = "AMND";
		public static final String RIZNICA_OTYPE_CANCELATION = "CANC";
		public static final String RIZNICA_OTYPE_DUPLICATION = "DUPL";
		public static final String RIZNICA_OTYPE_EXECRISETION = "EXOP";
		public static final String RIZNICA_OTYPE_NEW_CONFIRMATION = "NEWT";
		public static final String DEVIZNI_DEPOZIT_TIP_KAMATNE_STOPE_SWIFT_ACT365 = "ACT/365";
		public static final String DEVIZNI_DEPOZIT_TIP_KAMATNE_STOPE_SWIFT_ACT360 = "ACT/360";
		public static final String DEVIZNI_DEPOZIT_TIP_KAMATNE_STOPE_SWIFT_360360 = "360/360";
		public static final String DEVIZNI_DEPOZIT_TIP_KAMATNE_STOPE_SWIFT_AFI365 = "AFI/365";
		// klasa naloga
		public static final String KLASA_NALOGA_PREFIX_KNJIZENJE_GLAVNICE = "31";
		public static final String KLASA_NALOGA_PREFIX_KNJIZENJE_KAMATE = "32";
		public static final String KLASA_NALOGA_PREFIX_KNJIZENJE_POVRATA_GLAVNICE = "33";
		public static final Integer KLASA_NALOGA_KNJIZENJE_GLAVNICE_LENDER = 3100;
		public static final Integer KLASA_NALOGA_KNJIZENJE_GLAVNICE_BORROWER = 3101;
		public static final Integer KLASA_NALOGA_KNJIZENJE_KAMATE_LENDER_ACTIVE = 3200;
		public static final Integer KLASA_NALOGA_KNJIZENJE_KAMATE_BORROWER_PASSIVE = 3201;
		public static final Integer KLASA_NALOGA_KNJIZENJE_NEDOSPJELE_KAMATE_LENDER_ACTIVE = 3210;
		public static final Integer KLASA_NALOGA_KNJIZENJE_NEDOSPJELE_KAMATE_BORROWER_PASIVE = 3211;
		public static final Integer KLASA_NALOGA_KNJIZENJE_POVRATA_LENDER = 3300;
		public static final Integer KLASA_NALOGA_KNJIZENJE_POVRATA_BORROWER = 3301;
		public static final Integer KLASA_NALOGA_RASKNJIZENJE_GLAVNICE_LENDER = 3050;
		public static final Integer KLASA_NALOGA_RASKNJIZENJE_GLAVNICE_BORROWER = 3051;
		public static final Integer KLASA_NALOGA_TRZISTE_KUPNJA_DEVIZA = 3401;
		public static final Integer KLASA_NALOGA_TRZISTE_PRODAJA_DEVIZA = 3402;
		public static final Integer KLASA_NALOGA_TRZISTE_TRANSFER = 3403;
		public static final Integer KLASA_NALOGA_TRZISTE_TRANSFER_TARGET2 = 3413;
		public static final Integer KLASA_NALOGA_TRZISTE_ARBITRAZA = 3404;
		public static final Integer KLASA_NALOGA_TRZISTE_KUPNJA_SWAP = 3411;
		public static final Integer KLASA_NALOGA_TRZISTE_PRODAJA_SWAP = 3412;
		public static final Integer KLASA_NALOGA_TRZISTE_VANBILANSNO_KNJIZENJE_KUPNJA = 3421;
		public static final Integer KLASA_NALOGA_TRZISTE_VANBILANSNO_ISKNJIZENJE_KUPNJA = 3422;
		public static final Integer KLASA_NALOGA_TRZISTE_VANBILANSNO_KNJIZENJE_PRODAJA = 3423;
		public static final Integer KLASA_NALOGA_TRZISTE_VANBILANSNO_ISKNJIZENJE_PRODAJA = 3424;
		public static final String KLASA_NALOGA_GRUPA_DEVIZNI_DEPOZIT = "KNDD";
		public static final String KLASA_NALOGA_GRUPA_DEVIZNO_TRZISTE = "KNDT";
		public static final String KLASA_NALOGA_GRUPA_DEVIZNO_TRZISTE_TRANSFER = "KNDTT";
		public static final String KLASA_NALOGA_GRUPA_DEVIZNO_TRZISTE_NAJAVA = "KNDTN";
		public static final String DEFAULT_ORGANIZACIJSKA_JEDINICA_RIZNICA = "999";
		public static final String PROIZVOD_IDENTIFIKATOR_DEVIZNI_DEPOZITI = "DEVDEPOZ";
		public static final String PROIZVOD_POSAO_UZETI_KREDITI = "TAKECREDIT";
		public static final String PROIZVOD_TIP_DANI_DEPOZIT_STRANCI = "DDDSB";
		/**
		 * DEVIZNO TRŽIŠTE
		 */
		public static final List<String> TIP_OPERACIJE_ALLOWED = Arrays.asList(
				RIZNICA_OTYPE_AMENDATION, RIZNICA_OTYPE_CANCELATION,
				RIZNICA_OTYPE_DUPLICATION, RIZNICA_OTYPE_EXECRISETION,
				RIZNICA_OTYPE_NEW_CONFIRMATION);
		/**
		 * DEVIZNO TRŽIŠTE
		 */

		public static final Integer BROJ_DANA_UNATRAG_RIZNICA = 0;
		public static final Integer BROJ_DANA_UNATRAG_RIZNICA_BACKOFFICE = 60;
		public static final Integer BROJ_DANA_UNATRAG_IZVOD_BANKE = 7;
		public static final Integer BROJ_DANA_UNATRAG_RMA_AZURIRANJE = 14;
		/** DEVIZNI DEPOZITI */
		public static final String SIFRA_OSNOVE_SPECIFIKACIJA = "999";
		// osnove plaćanja/naplate
		public static final String SIFRA_OSNOVE_TRANSFER_NAPLATA = "561";
		public static final String SIFRA_OSNOVE_TRANSFER_PLACANJE = "161";
		public static final String SIFRA_OSNOVE_ARBITRAZA_NAPLATA = "577";
		public static final String SIFRA_OSNOVE_ARBITRAZA_PLACANJE = "177";
		public static final String SIFRA_OSNOVE_PRODAJA_PLACANJE = "155";
		public static final String SIFRA_OSNOVE_KUPNJA_NAPLATA = "555";
		public static final String SIFRA_OSNOVE_DANI_DEPOZIT_GLAVNICA = "105";
		public static final String SIFRA_OSNOVE_DANI_DEPOZIT_NAPLACENA_KAMATA = "511";
		public static final String SIFRA_OSNOVE_DANI_DEPOZIT_POVRAT_GLAVNICE = "505";
		public static final String STAVKA_PROIZVODA_UZETI_KREDIT_ENTITY = "uzetiKredit";
		public static final String STAVKA_PROIZVODA_UZETI_KREDIT_FIELD_VALUTA = "valuta";
		public static final String STAVKA_PROIZVODA_UZETI_KREDIT_FIELD_IZNOS = "iznos";
		public static final String STAVKA_PROIZVODA_UZETI_KREDIT_FIELD_TIP_PROIZVODA = "tipProizvoda";
		public static final String STAVKA_PROIZVODA_UZETI_KREDIT_FIELD_TIP_PROIZVODA_STVARNI = "tipProizvodaStvarni";
		public static final String STAVKA_PROIZVODA_UZETI_KREDIT_FIELD_TIP_KNJIZENJA = "tipKnjizenja";
		public static final String STAVKA_PROIZVODA_UZETI_KREDIT_FIELD_EKONOMSKI_SEKTOR = "ekonomskiSektor";
		public static final String STAVKA_PROIZVODA_UZETI_KREDIT_FIELD_REGIJA = "regija";
		public static final String STAVKA_PROIZVODA_UZETI_KREDIT_FIELD_SVRHA_KREDITA = "svrhaKredita";
		public static final String STAVKA_PROIZVODA_UZETI_KREDIT_FIELD_PARTNER = "partneri";
		public static final String STAVKA_PROIZVODA_UZETI_KREDIT_FIELD_TIP_KAMATE = "tipKamate";
		public static final String STAVKA_PROIZVODA_UZETI_KREDIT_FIELD_KREDITNA_LINIJA = "kreditnaLinija";
		public static final String STAVKA_PROIZVODA_UZETI_KREDIT_FIELD_DATUM_PUSTANJA = "datumPustanja";
		public static final String STAVKA_PROIZVODA_UZETI_KREDIT_FIELD_DATUM_DOSPIJECA = "datumDospijeca";
		public static final String STAVKA_PROIZVODA_UZETI_KREDIT_FIELD_NACIN_OBRACUNA_KAMATE = "nacinObracunaKamate";
		public static final String STAVKA_PROIZVODA_UZETI_KREDIT_FIELD_TIP_KAMATNE_STOPE = "tipKamatneStope";
		public static final String STAVKA_PROIZVODA_UZETI_KREDIT_FIELD_MOMENT_DOSPIJECA = "momentDospijeca";
		public static final String STAVKA_PROIZVODA_UZETI_KREDIT_FIELD_DOSPJELI_NAD_DIJELOVI = "dospjeliNadDijelovi";
		public static final String TIP_PROIZVODA_VALUTA_DOMICILNI = "Domicilni";
		public static final String TIP_PROIZVODA_VALUTA_DEVIZNI = "Devizni";
		public static final String TIP_PROIZVODA_VALUTA_VALUTNA_KLAUZULA = "Valutna klauzula";
		public static final String DEVIZNI_IZVOD_NALOG_PREDZNAK_DEBIT = "D";
		public static final String DEVIZNI_IZVOD_NALOG_PREDZNAK_CREDIT = "C";
		public static final String DEVIZNI_IZVOD_NALOG_PREDZNAK_RC = "RC";
		public static final String DEVIZNI_IZVOD_NALOG_PREDZNAK_RD = "RD";
		// SWIFT RMA
		public static final String SWIFT_RMA_YES = "1";
		public static final String SWIFT_RMA_NO = "0";
		/**
		 * Dolazne SWIFT poruke koje se ne smiju povlačiti.
		 */
//		public static List<String> listTransactionMessages = Arrays.asList(
//				SwiftConstants.MT_103, SwiftConstants.MT_190,
//				SwiftConstants.MT_191, SwiftConstants.MT_202,
//				SwiftConstants.MT_205, SwiftConstants.MT_900,
//				SwiftConstants.MT_910, SwiftConstants.MT_940,
//				SwiftConstants.MT_941, SwiftConstants.MT_950);
//		
		// potvrda MT199 - BIC primatelja
		public static final String BIC_MT199_TRACKER = "TRCKCHZZXXX";
		public static final String BIC_MT199_TRACKER_TEST = "TRCKCHZ0XXX";
	}

	public static class Depoziti extends GenericBassxConstants.Depoziti {
		public static final String MESSAGE_BUNDLE = "hr.abit.b3.biz.depoziti.message.depozitiMessages";

		public static final String OROCENI_DEPOZITI_STANOVNISTVO = "STAN";
		public static final String OROCENI_DEPOZITI_PRAVNA_LICA = "PRAV";

		public static final String PROIZVOD_POSAOPRO_OROCENI_DEPOZITI_STANOVNISTVO = "OROCST";
		public static final String PROIZVOD_POSAOPRO_OROCENI_DEPOZITI_PRAVNA_LICA = "OROCPR";
		public static final String PROIZVOD_VRSTAPRO_KAMATA_PROIZVOD = "PRO_KAM";
		public static final String PROIZVOD_VRSTAPRO_KAMATA_RAZRED = "RAZ_KAM";
		public static final String PROIZVOD_VRSTAPRO_KAMATA_DOGOVORNA = "DOG_KAM";

		// vrste oročene štednje
		public static final String NASUKPAR_OBICNA_OROCENA_STEDNJA = "O";
		public static final String NASUKPAR_SUKCESIVNE_UPLATE = "U";
		public static final String NASUKPAR_SUKCESIVNE_ISPLATE = "I";
		public static final String NASUKPAR_SUKCESIVNE_UPLATE_I_ISPLATE = "S";

		// ponašanje oročenja
		public static final String PONASPAR_BEZ_PRODUZENJA = "B";
		public static final String PONASPAR_PRODUZENJE_PRIPIS = "P";
		public static final String PONASPAR_PRODUZENJE_ISPLATA = "I";

		// fiksnost kamate
		public static final Integer FIKSKPAR_FIKSNA_KAMATNA_STOPA = 1;
		public static final Integer FIKSKPAR_PROMJENJIVA_KAMATNA_STOPA = 2;
		public static final String FIKSKPAR_FIKSNA_KAMATNA_STOPA_OPIS = "Fiksna";
		public static final String FIKSKPAR_PROMJENJIVA_KAMATNA_STOPA_OPIS = "Promjenjiva";

		// grupa računa
		public static final String GRUPA_RACUNA_OROCENJA_FIZICKI_DOMICILNI = "85";
		public static final String GRUPA_RACUNA_OROCENJA_FIZICKI_DEVIZNI = "75";
		public static final String GRUPA_RACUNA_OROCENJA_PRAVNI_DOMICILNI = "82";
		public static final String GRUPA_RACUNA_OROCENJA_PRAVNI_DEVIZNI = "72";

		public static final String GRUPA_RACUNA_DEVIZNA_OROCENJA_PABA = "75";
		public static final String GRUPA_RACUNA_KUNSKA_OROCENJA_PABA = "85";

		public static final Integer TIPKAMATE_SIFRAKAM_STEDNJE_REDOVNA = 11;
		public static final Integer TIPKAMATE_SIFRAKAM_STEDNJE_DODATNA = 14;

		// frekvencija isplate rente
		public static final Integer FREKVPAR_MJESECNO = 1;
		// public static final String FREKVPAR_KVARTALNO = "3";
		// public static final String FREKVPAR_POLUGODISNJE = "6";
		// public static final String FREKVPAR_GODISNJE = "12";
		// public static final String FREKVPAR_PO_DOSPIJECU = "99";
		public static final Integer FREKVPAR_PO_DOSPIJECU = 999;

		// isplata/pripis kamate
		public static final String RENTAPAR_ISPLATA = "1";
		public static final String RENTAPAR_PRIPIS = "0";

		// način uplate
		public static final String NACINUPLATE_INTERNI_PRIJENOS = "I";
		public static final String NACINUPLATE_GOTOVINA = "G";

		// nalog oročenja
		public static final String NALOGOROCENJA_SMJERNOR_DUGOVNO = "D";
		public static final String NALOGOROCENJA_SMJERNOR_POTRAZNO = "P";

		// >>> indeksacija
		public static final String INDEXPRO_INDEKSACIJA_KUNSKI_DEPOZIT = "1";
		public static final String INDEXPRO_INDEKSACIJA_VAL_KLAUZULA_PORAST_SRE = "2";
		public static final String INDEXPRO_INDEKSACIJA_VAL_KLAUZULA_PORAST_PRO = "3";
		public static final String INDEXPRO_INDEKSACIJA_DEVIZNI_DEPOZIT = "4";
		public static final String INDEXPRO_INDEKSACIJA_VAL_KLAUZULA_SRE = "7";
		public static final String INDEXPRO_INDEKSACIJA_VAL_KLAUZULA_PRO = "8";
		public static final String INDEXPRO_INDEKSACIJA_REVALORIZACIJA = "9";
		// <<< indeksacija

		public static final String REZERVIRANA_PARTIJA_STATUREZ_OTVOREN = "O";
		public static final String REZERVIRANA_PARTIJA_STATUREZ_ZATVOREN = "Z";

		// tip partije
		public static final Integer TIPPARTIJE_SIFRATIP_OROCENJE_FIZ_DEPOZITI_AVISTA_KREDITI = 1624;
		public static final Integer TIPPARTIJE_SIFRATIP_OROCENJE_FIZ_AVISTA_KAMATA_VISE_ULOGA = 1866;
		public static final Integer TIPPARTIJE_SIFRATIP_OROCENJE_FIZ_AVISTA_KAMATA_BEZ_GLAVNICE = 1867;
		public static final String LIST_TIPPARTIJE_OROCENJE_FIZ_AVISTA = TIPPARTIJE_SIFRATIP_OROCENJE_FIZ_DEPOZITI_AVISTA_KREDITI
				+ ", "
				+ TIPPARTIJE_SIFRATIP_OROCENJE_FIZ_AVISTA_KAMATA_VISE_ULOGA
				+ ", "
				+ TIPPARTIJE_SIFRATIP_OROCENJE_FIZ_AVISTA_KAMATA_BEZ_GLAVNICE;

		// nad_dio
		public static final String NADDIO_OZNKANAD_DEPOZITI_NEDOSPJELA_KAMATA = "024";

		public static final String KAMATNAD_DEPOZITI_KAMATA_REDOVNA = "RE";
		public static final String KAMATNAD_DEPOZITI_KAMATA_AVISTA = "AV";

		// ugovor
		public static final String UGOVOR_RAZROCENJE_OPCIJA_3 = "Opcija3";

		// klasa naloga
		public static final Integer KLASANALOGA_SIFRAKNA_UPLATA_NA_OROCENJE = 8001;
		public static final Integer KLASANALOGA_SIFRAKNA_PRIJENOS_NENEMJENSKO_NAMJENSKO_OROCENJE = 8003;
		public static final Integer KLASANALOGA_SIFRAKNA_DEPOZITI_FIZICKI_OBRACUN_NEDOSPJELE_KAMATE_AVISTA = 8020;
		public static final Integer KLASANALOGA_SIFRAKNA_DEPOZITI_FIZICKI_OBRACUN_NEDOSPJELE_KAMATE = 8021;
		public static final Integer KLASANALOGA_SIFRAKNA_DEPOZITI_FIZICKI_ZATVARANJE_NEDOSPJELE_KAMATE = 8022;
		public static final Integer KLASANALOGA_SIFRAKNA_DEPOZITI_FIZICKI_INTERNI_NALOG = 8304;
		public static final Integer KLASANALOGA_SIFRAKNA_DEPOZITI_FIZICKI_RAZROCENJE_DEPOZITA = 8005;
		public static final Integer KLASANALOGA_SIFRAKNA_DEPOZITI_FIZICKI_OBRACUN_REDOVNE_KAMATE = 8006;
		public static final Integer KLASANALOGA_SIFRAKNA_DEPOZITI_FIZICKI_PRIPIS_ISPLATA_KAMATE = 8008;
		public static final Integer KLASANALOGA_SIFRAKNA_DEPOZITI_FIZICKI_DNEVNA_OBRADA_OROCENJA = 8009;
		public static final Integer KLASANALOGA_SIFRAKNA_DEPOZITI_FIZICKI_PRIJENOS_GLAVNICE = 8310;
		public static final Integer KLASANALOGA_SIFRAKNA_DEPOZITI_FIZICKI_OBRACUN_KAMATE_AVISTA = 8011;
		public static final Integer KLASANALOGA_SIFRAKNA_DEPOZITI_FIZICKI_PRIPIS_KAMATE_AVISTA = 8012;
		public static final Integer KLASANALOGA_SIFRAKNA_DEPOZITI_FIZICKI_UMANJENJE_GLAVNICE = 8013;
		public static final Integer KLASANALOGA_SIFRAKNA_DEPOZITI_FIZICKI_ISPLATA_AVISTA_DIO = 8014;
		public static final Integer KLASANALOGA_SIFRAKNA_DEPOZITI_FIZICKI_PRIPIS_NEDOSPJELE_KAMATE_PARTIJA = 8023;
		public static final Integer KLASANALOGA_SIFRAKNA_DEPOZITI_FIZICKI_ZATVARANJE_NEDOSPJELE_KAMATE_PRETHODNE_GODINE = 8323;

		public static final Integer KLASANALOGA_SIFRAKNA_UPLATA_NA_OROCENJE_PRAVNI = 8501;
		public static final Integer KLASANALOGA_SIFRAKNA_DEPOZITI_PRAVNI_INTERNI_NALOG = 8504;
		public static final Integer KLASANALOGA_SIFRAKNA_DEPOZITI_PRAVNI_RAZROCENJE_DEPOZITA = 8505;
		public static final Integer KLASANALOGA_SIFRAKNA_DEPOZITI_PRAVNI_OBRACUN_REDOVNE_KAMATE = 8506;
		public static final Integer KLASANALOGA_SIFRAKNA_DEPOZITI_PRAVNI_OBRADA_DOSPJELE_KAMATE = 8507;
		public static final Integer KLASANALOGA_SIFRAKNA_DEPOZITI_PRAVNI_ISPLATA_KAMATE = 8508;
		public static final Integer KLASANALOGA_SIFRAKNA_DEPOZITI_PRAVNI_DNEVNA_OBRADA_OROCENJA = 8509;
		public static final Integer KLASANALOGA_SIFRAKNA_DEPOZITI_PRAVNI_OBRADA_ISPLATA_KAMATE = 8511;
		public static final Integer KLASANALOGA_SIFRAKNA_DEPOZITI_PRAVNI_ISPLATA_KAMATE_IPP_PROLAZNI = 8512;
		public static final Integer KLASANALOGA_SIFRAKNA_DEPOZITI_PRAVNI_UMANJENJE_GLAVNICE = 8513;
		public static final Integer KLASANALOGA_SIFRAKNA_DEPOZITI_PRAVNI_PRIPIS_KAMATE_GLAVNICI = 8515;
		public static final Integer KLASANALOGA_SIFRAKNA_DEPOZITI_PRAVNI_ISPLATA_KAMATE_IPP = 8516;
		public static final Integer KLASANALOGA_SIFRAKNA_DEPOZITI_PRAVNI_ISPLATA_UPP = 8518;
		public static final Integer KLASANALOGA_SIFRAKNA_DEPOZITI_PRAVNI_OBRACUN_NEDOSPJELE_KAMATE = 8521;
		public static final Integer KLASANALOGA_SIFRAKNA_DEPOZITI_PRAVNI_ZATVARANJE_NEDOSPJELE_KAMATE = 8522;
		public static final Integer KLASANALOGA_SIFRAKNA_DEPOZITI_PRAVNI_ZATVARANJE_NEDOSPJELE_KAMATE_PRETHODNE_GODINE = 8523;
		public static final Integer KLASANALOGA_SIFRAKNA_DEPOZITI_PRAVNI_PREKNJIZENJE = 8524;

		public static final String KLASA_NALOGA_GRUPA_OROCENJE_PRAVNI_UPLATA = "ORPU";
		public static final String KLASA_NALOGA_GRUPA_OROCENJE_FIZICKI_UPLATA = "ORFU";
		public static final String KLASA_NALOGA_GRUPA_PRAVNI_INTERNI_NALOG = "ORPI";
		public static final String KLASA_NALOGA_GRUPA_FIZICKI_INTERNI_NALOG = "ORFI";
		public static final String KLASA_NALOGA_GRUPA_PRAVNI_RAZROCENJE_DEPOZITA = "ORPRD";

		// tip naloga
		public static final Integer TIP_NALOGA_MIGRACIJA_OROCENI_DEPOZITI = 6;
		public static final Integer TIP_NALOGA_MIGRACIJA_OROCENI_DEPOZITI_KAMATA_16_ISKLJUCITI = 8;
		public static final Integer TIP_NALOGA_MIGRACIJA_OROCENI_DEPOZITI_PRAVNI = 9;

		public static final Integer TIPNALOGA_SIFRAPOD_INTERNI_PRIJENOS_NA_OROCENJE_FIZICKI = 8001;
		public static final Integer TIPNALOGA_SIFRAPOD_GOTOVINSKA_UPLATA_NA_OROCENJE_FIZICKI = 8002;
		public static final Integer TIPNALOGA_SIFRAPOD_PRIJENOS_NENAMJENSKO_NAMJENSKO_OROCENJE_FIZICKI = 8003;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_FIZICKI_INTERNI_NALOG = 8304;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_FIZICKI_RAZROCENJE = 670;                       //    8005;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_FIZICKI_OBRACUN_REDOVNE_KAMATE = 600;              // 8006;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_FIZICKI_DNEVNA_OBRADA_RENTE = 605;                     // 8007;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_FIZICKI_PRIPIS_ISPLATA_KAMATE = 8008;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_FIZICKI_DNEVNA_OBRADA_OROCENJA = 604;           // 8009;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_FIZICKI_PRIJENOS_GLAVNICE = 8310;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_FIZICKI_OBRACUN_KAMATE_AVISTA = 8011;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_FIZICKI_PRIJENOS_KAMATE_AVISTA = 8012;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_FIZICKI_UMANJENJE_GLAVNICE = 8013;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_FIZICKI_ISPLATA_AVISTA_DIO = 8014;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_FIZICKI_OBRACUN_NEDOSPJELE_KAMATE_AVISTA_ZBIRNI = 8120;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_FIZICKI_OBRACUN_NEDOSPJELE_KAMATE_ZBIRNI = 603;                      // 8121;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_FIZICKI_OBRACUN_NEDOSPJELE_KAMATE_AVISTA = 8020;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_FIZICKI_OBRACUN_NEDOSPJELE_KAMATE = 610;                      // 8021;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_FIZICKI_ZATVARANJE_NEDOSPJELE_KAMATE = 8022;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_FIZICKI_PRIJENOS_NA_KREDIT = 8525;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_FIZICKI_PRIPIS_NEDOSPJELE_KAMATE_PARTIJA = 660;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_FIZICKI_KNJIZENJE_RAZROCENJE = 8026;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_FIZIICKI_ZATVARANJE_NEDOSPJELE_KAMATE_PRETHODNE_GODINE = 8323;

		public static final Integer TIP_NALOGA_SIFRAPOD_FORMIRANJE_IZVODA_OROCENJA = 8500;
		public static final Integer TIPNALOGA_SIFRAPOD_INTERNI_PRIJENOS_NA_OROCENJE_PRAVNI = 8501;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_PRAVNI_INTERNI_NALOG = 8504;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_PRAVNI_RAZROCENJE = 8505;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_PRAVNI_OBRACUN_REDOVNE_KAMATE = 8506;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_PRAVNI_OBRACUN_DOSPJELE_KAMATE_ZBIRNI = 8507;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_PRAVNI_DNEVNA_OBRADA_OROCENJA = 8509;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_PRAVNI_ISPLATA_KAMATE = 8508;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_PRAVNI_ISPLATA_KAMATE_ZBIRNI = 8511;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_PRAVNI_ISPLATA_KAMATE_IPP_PROLAZNI = 8512;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_PRAVNI_UMANJENJE_GLAVNICE = 8513;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_PRAVNI_PRIPIS_KAMATE_GLAVNICI = 8515;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_PRAVNI_ISPLATA_KAMATE_IPP = 8516;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_PRAVNI_ISPLATA_GLAVNICE_UPP = 8518;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_PRAVNI_ISPLATA_KAMATE_UPP = 8519;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_PRAVNI_ISPLATA_PROLAZNI_UPP = 8520;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_PRAVNI_OBRACUN_NEDOSPJELE_KAMATE_ZBIRNI = 8621;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_PRAVNI_OBRACUN_NEDOSPJELE_KAMATE = 8521;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_PRAVNI_ZATVARANJE_NEDOSPJELE_KAMATE = 8522;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_PRAVNI_ZATVARANJE_NEDOSPJELE_KAMATE_PRETHODNE_GODINE = 8523;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_PRAVNI_PREKNJIZENJE = 8524;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_PRAVNI_PRIJENOS_NA_KREDIT = 8525;
		public static final Integer TIPNALOGA_SIFRAPOD_DEPOZITI_PRAVNI_KNJIZENJE_RAZROCENJE = 8526;

	}

	public static class Krediti extends GenericBassxConstants.Krediti {
		public static final String MESSAGE_BUNDLE = "hr.abit.b3.biz.krediti.message.kreditiMessages";
		public static final String PROIZVOD_POSAOPRO_DUGOROCNI_KREDITI_STANOVNISTVA = "KREDUGFIZ";
		public static final String PROIZVOD_POSAOPRO_KRATKOROCNI_KREDITI_PRAVNIH_OSOBA = "KREKRAPRA";
		public static final String PROIZVOD_POSAOPRO_DUGOROCNI_KREDITI_PRAVNIH_OSOBA = "KREDUGPRA";
		public static final String PROIZVOD_POSAOPRO_KREDITI_PRAVNE_MIGRACIJA = "MIG";
		public static final String PROIZVOD_VRSTAPRO_FIZICKE_DUGOROCNI = "71";
		public static final String PROIZVOD_VRSTAPRO_FIZICKE_KRATKOROCNI = "72";
		public static final String PROIZVOD_VRSTAPRO_PRAVNE_DUGOROCNI = "73";
		public static final String PROIZVOD_VRSTAPRO_PRAVNE_KRATKOROCNI = "74";
		public static final String PROIZVOD_VRSTAPRO_ESKONT_MJENICA = "76";
		public static final String PROIZVOD_VRSTAPRO_KREDITI = "6";
		public static final String PROIZVOD_STATUPRO_AKTIVAN = "O";

		public static final String RACUN_NERASPOZNATI_PRILJEVI_KREDITI_PL = "1029990100000267";
		public static final String RACUN_NERASPOZNATI_PRILJEVI_KREDITI_FL = "1029990100000364";
		public static final String PARAMETAR_PRAG_MATERIJALNOSTI_STANOVNISTVO = "PRAG_MAT_STAN";
		public static final String PARAMETAR_PRAG_MATERIJALNOSTI_PRAVNA_LICA = "PRAG_MAT_PRA";
		public static final String PARAMETAR_BROJ_MJESECI_POSMATRANJA_PRIJE_ULASKA_U_STATUS_NEIZMIRENJA = "MJES_POS_PRIJ";
		public static final String PARAMETAR_BROJ_MJESECI_POSMATRANJA_NAKON_ULASKA_U_STATUS_NEIZMIRENJA = "MJES_POS_NAKO";
		public static final String PARAMETAR_TOLERANCIJA_POTRAZIVANJA_PREMA_BASEL_II_ZA_PRAVNA_LICA = "BASEL_TOL_PRA";
		public static final String PARAMETAR_TOLERANCIJA_POTRAZIVANJA_PREMA_BASEL_II_ZA_FIZICKA_LICA = "BASEL_TOL_STA";
		public static final String PARAMETAR_POSTOTAK_UKUPNE_IZLOZENOSTI_PREMA_BASEL_II_ZA_PRAVNA_LICA = "BASEL_POS_PRA";
		public static final String PARAMETAR_POSTOTAK_UKUPNE_IZLOZENOSTI_PREMA_BASEL_II_ZA_FIZICKA_LICA = "BASEL_POS_STA";
		public static final String PARAMETAR_POJEDINACNA_ZNACAJNOST_PRAVNA_LICA = "ZNAC_PRA";
		public static final String PARAMETAR_POJEDINACNA_ZNACAJNOST_KREDITI_STANOVNISTVA = "ZNAC_KRED_STA";
		public static final String PARAMETAR_POJEDINACNA_ZNACAJNOST_KREDITNE_KARTICE = "ZNAC_KRE_KARD";
		public static final String PARAMETAR_POJEDINACNA_ZNACAJNOST_DEBITNE_KARTICE = "ZNAC_DEB_KARD";
		public static final Integer TARIFNA_STAVKA_NAKNADA_ESKONT = 1;
		public static final Integer ANEKSPAR_DEFAULT = 0;
		public static final String LF_CR = "\r\n";
		public static final BigDecimal IZNOS_ANUITETA_DEFAULT = BigDecimal.ZERO;
		public static final String FIKSNPAR_FIKSNA = "F";
		public static final String FIKSNPAR_PROMJENJIVA = "P";
		public static final String FIKSNPAR_PROMJENJIVA_RKS = "R";
		public static final String FIKSNPAR_MJESOVITA_VISE_STOPA = "M";
		public static final String INDKOPAR_KORISTENJE = "0";
		public static final String INDKOPAR_OTPLATA = "1";
		public static final Integer VRIJEDPAR_DEFAULT_MAXIMUM_NUMBER_OF_SQL_IN_CLAUSE_ELEMENTS = 100;
		public static final String VRIJEDPAR_LOG_METHOD_CALL_DURATION = "1";
		public static final String PROMET_STRANA_KNJIZENJA_POTRAZNA = "P";
		public static final String PROMET_STRANA_KNJIZENJA_DUGOVNA = "D";
		public static final String KAMATNAD_KREDITI_KAMATA_REDOVNA = "RE";
		public static final String KAMATNAD_KREDITI_KAMATA_ZATEZNA = "ZA";
		public static final String KAMATNAD_KREDITI_KAMATA_BEZ = "NE";
		public static final String KAMATNAD_KREDITI_NAKNADA_DA = "DA";
		public static final String KAMATNAD_KREDITI_NAKNADA_NE = "NE";
		public static final Integer KLASANALOGA_SIFRAKNA_INTERNI_KREDITNI_NALOG = 5501;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_INTERNI_NALOG = 5501;
		public static final Integer KLASANALOGA_SIFRAKNA_KREDITI_DOSPIJECE_GLAVNICE = 5502;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_DOSPIJECE_GLAVNICE = 5502;
		public static final Integer KLASANALOGA_SIFRAKNA_KREDITI_DOSPIJECE_KAMATE = 5503;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_DOSPIJECE_KAMATE = 5503;
		public static final Integer KLASANALOGA_SIFRAKNA_KREDITI_DOSPIJECE_NAKNADE = 5504;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_DOSPIJECE_NAKNADE = 5504;
		public static final Integer KLASANALOGA_SIFRAKNA_OBRADA_NEISKORISTENIG_IZNOSA_KREDITA = 5505;
		public static final Integer KLASANALOGA_SIFRAKNA_OBRADA_NEISKORISTENIG_IZNOSA_KREDITA_ORIGINALNA_OBAVEZA = 5055;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_NEISKORISTENI_IZNOS = 5505;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_NEISKORISTENI_IZNOS_ORIGINALNA_OBAVEZA = 5055;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_NEISKORISTENI_IZNOS_ZBIRNI = 5605;
		public static final Integer KLASANALOGA_SIFRAKNA_KREDITI_RASKNJIZENJE_PLASMANA = 5006;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_RASKNJIZENJE_PLASMANA = 5006;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_RASKNJIZENJE_PLASMANA_ZBIRNI = 5106;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_GENERIRANJE_PLANA_OTPLATE = 5507;
		public static final Integer KLASANALOGA_SIFRAKNA_KREDITI_PRIJENOS_U_OTPLATU = 5508;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_PRIJENOS_U_OTPLATU = 5508;
		public static final Integer KLASANALOGA_SIFRAKNA_KREDITI_CISCENJE_MALIH_SALDA = 5510;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_CISCENJE_MALIH_SALDA = 5510;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_CISCENJE_MALIH_SALDA_ZBIRNI = 5610;
		public static final Integer KLASANALOGA_SIFRAKNA_KREDITI_VREMENSKO_RAZGRANICENJE_NAKNADE = 5032;
		public static final Integer KLASANALOGA_SIFRAKNA_KREDITI_VREMENSKO_RAZGRANICENJE_KAMATE = 5045;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_VREMENSKO_RAZGRANICENJE_NAKNADE_ZBIRNI = 5132;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_KNJIZENJE_MINIMALNOG_IZNOSA_ZBIRNI = 5147;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_VREMENSKO_RAZGRANICENJE_NAKNADE = 5032;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_VREMENSKO_RAZGRANICENJE_NAKNADE_12MJESECI = 5045;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_KNJIZENJE_MINIMALNOG_IZNOSA = 5047;
		public static final Integer KLASANALOGA_SIFRAKNA_KREDITI_RASPORED_ANUITETA = 5009;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_RASPORED_ANUITETA = 5009;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_PRIJENOS_U_OTPLATU_ZBIRNI = 5608;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_OBRADA_DOR = 5555;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_RASPORED_ANUITETA_ZBIRNI = 5609;
		public static final Integer KLASANALOGA_SIFRAKNA_KREDITI_RASKNJIZENJE_UPLATE = 5511;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_RASKNJIZENJE_UPLATE = 5511;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_RASKNJIZENJE_UPLATE_ZBIRNI = 5611;
		public static final Integer KLASANALOGA_SIFRAKNA_KREDITI_GENERIRANJE_PLANA_OTPLATE = 5507;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_OBRACUN_REDOVNE_DOSPJELE_KAMATE = 5512;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_OBRACUN_REDOVNE_DOSPJELE_KAMATE = 5512;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_OBRACUN_REDOVNE_DOSPJELE_KAMATE_ZBIRNI = 5612;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_PRIPIS_REDOVNE_DOSPJELE_KAMATE = 5013;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_PRIPIS_REDOVNE_DOSPJELE_KAMATE = 5013;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_OBRACUN_ISKLJUCENE_DOSPJELE_REDOVNE_KAMATE = 5025;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_OBRACUN_ISKLJUCENE_DOSPJELE_REDOVNE_KAMATE = 5025;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_OBRACUN_NEDOSPJELE_KAMATE = 5514;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_OBRACUN_NEDOSPJELE_KAMATE = 5514;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_OBRACUN_NEDOSPJELE_KAMATE_ZBIRNI = 5614;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_PRIJEVREMENA_DJELOMIČNA_OTPLATA = 5526;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_NAKNADA_ZA_OBRADU = 1003;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_NAKNADA_ZA_OBRADU = 5515;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_NAKNADA_ZA_PRIJEVREMENU_OTPLATU = 5517;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_NAKNADA_ZA_PRIJEVREMENU_OTPLATU = 5517;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_PRIJEVREMENA_DJELOMIČNA_OTPLATA = 5526;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_PRIJEVREMENA_DJELOMIČNA_OTPLATA_ZBIRNI = 5626;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_OBRACUN_ZATEZNE_KAMATE = 5527;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_OBRACUN_ZATEZNE_KAMATE = 5527;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_ZATVARANJE_ZATEZNE_KAMATE_S_PREPLATOM = 5528;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_ZATVARANJE_ZATEZNE_KAMATE_S_PREPLATOM = 5528;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_OBRACUN_ZATEZNE_KAMATE_ZBIRNI = 5627;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_OBRACUN_ISKLJUCENE_ZATEZNE_KAMATE = 5028;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_OBRACUN_ISKLJUCENE_ZATEZNE_KAMATE = 5028;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_ZATVARANJE_NEDOSPJELE_KAMATE = 5029;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_ZATVARANJE_NEDOSPJELE_KAMATE = 5029;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_NAKNADA_U_FAZI_OTPLATE = 5030;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_NAKNADA_U_FAZI_OTPLATE = 5030;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_OBRADA_IZRACUN_RIZICNOSTI_FBA = 5033;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_OBRADA_IZRACUN_RIZICNOSTI_FBA_ZBIRNI = 5133;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_OBRADA_TIPA_ISPRAVKI_VRIJEDNOSTI_MRS = 5050;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_OBRADA_TIPA_ISPRAVKI_VRIJEDNOSTI_MRS_ZBIRNI = 5150;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_RUCNI_UNOS_RIZICNOSTI = 5034;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_RUCNI_UNOS_RIZICNOSTI = 5034;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_UNOS_CASH_FLOW = 5035;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_UNOS_CASH_FLOW = 5035;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_OBRADA_IZRACUN_RIZICNOSTI_SRI = 5036;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_OBRADA_IZRACUN_RIZICNOSTI_SRI = 5036;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_OBRADA_IZRACUN_RIZICNOSTI_SRI_ZBIRNI = 5136;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_STORNO_RASKNJIZENJE_UPLATE_ZBIRNI = 5137;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_ZATVARANJE_GLAVNOG_DUGA_S_PREPLATOM_ZBIRNI = 5638;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_ZATVARANJE_GLAVNOG_DUGA_S_PREPLATOM = 5538;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_ZATVARANJE_GLAVNOG_DUGA_S_PREPLATOM = 5538;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_DOSPIJECE_REVOLVING_KREDITA_ZBIRNI = 5639;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_DOSPIJECE_REVOLVING_KREDITA = 5539;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_DOSPIJECE_REVOLVING_KREDITA = 5539;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_KNJIZENJE_REZERVACIJA_ZBIRNI = 5140;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_KNJIZENJE_REZERVACIJA = 5040;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_KNJIZENJE_REZERVACIJA = 5040;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_KNJIZENJE_REZERVACIJA_GLAVINCE = 5040;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_KNJIZENJE_REZERVACIJA_GLAVINCE = 5040;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_KNJIZENJE_REZERVACIJA_REDOVNE_KAMATE = 5040;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_KNJIZENJE_REZERVACIJA_REDOVNE_KAMATE = 5040;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_KNJIZENJE_REZERVACIJA_ZATEZNE_KAMATE = 5040;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_KNJIZENJE_REZERVACIJA_ZATEZNE_KAMATE = 5040;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_KNJIZENJE_REZERVACIJA_NAKNADE = 5040;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_KNJIZENJE_REZERVACIJA_NAKNADE = 5040;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_KNJIZENJE_REZERVACIJA_OSTALIH_TROSKOVA = 5040;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_KNJIZENJE_REZERVACIJA_OSTALIH_TROSKOVA = 5040;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_KNJIZENJE_REZERVACIJA_POVRAT_ISKLJUCENIH_PRIHODA_REDOVNA_KAMATA = 5040;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_KNJIZENJE_REZERVACIJA_POVRAT_ISKLJUCENIH_PRIHODA_REDOVNA_KAMATA = 5040;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_KNJIZENJE_REZERVACIJA_POVRAT_ISKLJUCENIH_PRIHODA_ZATEZNA_KAMATA = 5040;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_KNJIZENJE_REZERVACIJA_POVRAT_ISKLJUCENIH_PRIHODA_ZATEZNA_KAMATA = 5040;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_KNJIZENJE_REZERVACIJA_POVRAT_ISKLJUCENIH_PRIHODA_NAKNADA = 5040;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_KNJIZENJE_REZERVACIJA_POVRAT_ISKLJUCENIH_PRIHODA_NAKNADA = 5040;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_KONACNA_OTPLATA_ZBIRNI = 5641;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_REPROGRAM = 5542;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_REPROGRAM_ZBIRNI = 5642;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_PREKNJIZENJE_ROCNOSTI_ZBIRNI = 5143;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_PREKNJIZENJE_ROCNOSTI = 5043;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_ZATVARANJE_PARTIJA_ZBIRNI = 5644;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_ZATVARANJE_PARTIJA = 5544;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_PRIJAVA_RESTRUKTURIRANIH_KREDITA = 5046;
		public static final Integer TIPNANAL_MIGRACIJA_KREDITI_ROCNOST = 33;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_IZRACUN_PD_PARAMETRA_ZBIRNI = 5151;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_IZRACUN_PD_PARAMETRA = 5051;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_NAKNADA_ZA_ODOBRENJE_OKVIRA = 5064;
		public static final String STATUS_TABLE_KLASIFIKACIJA_OSTALO = "KlasifikacijaOstalo";
		public static final String STATUS_FIELD_VRSTA_KLASIFIKACIJE = "vrstaKla";
		public static final String STATUS_FIELD_MRS = "tpmrsKla";
		public static final String STATUS_FIELD_FBA = "tpfbaKla";
		public static final String STATUS_FIELD_KRITERIJI = "kriteKla";
		public static final String STATUS_TABLE_OCEKIVANI_PRILJEV = "OcekivaniPriljev";
		public static final String STATUS_FIELD_VRSTA_ALOKACIJE = "vrstaAlokacije";
		public static final String STATUS_TABLE_RESTRUKTURA_KREDITA = "RestrukturaKredita";
		public static final String STATUS_FIELD_VRSTA_RESTRUKTURE = "vrstaRes";
		public static final String STATUS_FIELD_PODVRSTA_RESTRUKTURE = "podvrRes";
		public static final String RESTRUKTURA_VRSTA_PRIJE_ULASKA_U_STATUS_NEIZMIRENJA_OBVEZE = "1";
		public static final String RESTRUKTURA_VRSTA_NAKON_ULASKA_U_STATUS_NEIZMIRENJA_OBVEZE = "2";
		public static final String REPROGRAM_USLIJED_FINANCIJSKIH_TESKOCA = "1";
		public static final String REPROGRAM_PREMA_ODLUCI_FBA_PRIVREMENE_MJERE = "3";
		public static final String REPROGRAM_USLIJED_USKLADJIVANJA_SA_TRZISNIM_UVJETIMA = "2";
		public static final String NALOGKREDITNI_SMJERNRT_DUGOVNO = "D";
		public static final String NALOGKREDITNI_SMJERNRT_POTRAZNO = "P";
		public static final String NALOGKREDITNI_PROMET_POTRAZUJE = "P";
		public static final String NALOGKREDITNI_PROMET_DUGUJE = "D";
		public static final String NALOGKREDITNI_PROMET_POTRAZUJE_STORNO = "PS";
		public static final String NALOGKREDITNI_PROMET_DUGUJE_STORNO = "DS";
		public static final String STATUS_TABLE_HOMOGENE_SKUPINE = "HomogeneSkupine";
		public static final String STATUS_FIELD_PORTFOLIO = "portfHom";
		public static final String STATUS_TABLE_RIZICNOST = "Rizicnost";
		public static final String STATUS_FIELD_OZNAKA_RIZICNOSTI = "oznkaRiz";
		public static final String STATUS_TABLE_VEZE_HOMOGENIH_SKUPINA = "VezeHomogenihSkupina";
		public static final String STATUS_FIELD_PODSKUPINA_SKUPINE = "podskVhm";
		public static final String STATUS_FIELD_SEGMENT_SKUPINE = "segmeVhm";
		public static final String STATUS_FIELD_TIP_PRILJEVA = "tipprPri";
		public static final String STATUS_FIELD_VRSTA_AKTIVE = "vraktKla";
		public static final String STATUS_TABLE_PARTIJA_KREDITA = "PartijaKredita";
		public static final String STATUS_FIELD_REGRES = "regres";
		public static final String STATUS_FIELD_TIP_ISPISA_FAKTURE = "tipIspisaFakture";
		public static final Integer KLASANALOGA_SIFRAKNA_KREDITI_PLASMAN = 5548;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_PLASMAN = 5548;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_PLASMAN_ZBIRNI = 5648;
		public static final Integer KLASANALOGA_SIFRAKNA_KREDITI_NAPLATA_NAKNADE_IZ_PLASMANA = 5516;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_NAPLATA_NAKNADE_IZ_PLASMANA = 5516;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_ISKNJIZENJE_KOLATERALA = 5049;
		public static final Integer KLASANALOGA_SIFRAKNA_KREDITI_ISKNJIZENJE_KOLATERALA = 5049;
		public static final Integer TIP_NALOGA_SIFRAPOD_KREDITI_OBRACUN_NAKNADE_ZA_OBRADU_KREDITA = 5515;
		public static final Integer TIP_NALOGA_SIFRAPOD_KREDITI_OBRACUN_NAKNADE_ZBIRNI = 5615;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_PROMJENA_STATUSA = 5554;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_NAKNADA_OKVIRNI_KREDIT_PRAVNE = 5067;

		//konstante za eskont
		public static final Integer TIPNALOGA_SIFRAPOD_ESKONT_AKTIVACIJA_ZBIRNI = 5170;
		public static final Integer TIPNALOGA_SIFRAPOD_ESKONT_DISKONT = 5071;
		public static final Integer KLASANALOGA_SIFRAKNA_ESKONT_DISKONT = 5071;
		public static final Integer TIPNALOGA_SIFRAPOD_ESKONT_NAKNADA_NAPLATA = 5072;
		public static final Integer KLASANALOGA_SIFRAKNA_ESKONT_NAKNADA_NAPLATA = 5072;
		public static final Integer TIPNALOGA_SIFRAPOD_ESKONT_UGOVORENI_IZNOS = 5073;
		public static final Integer KLASANALOGA_SIFRAKNA_ESKONT_UGOVORENI_IZNOS = 5073;
		public static final Integer TIPNALOGA_SIFRAPOD_ESKONT_OBRACUN_DISKONTA = 5074;
		public static final Integer TIPNALOGA_SIFRAPOD_ESKONT_OBRACUN_DISKONTA_ZBIRNI = 5174;
		public static final Integer TIPNALOGA_SIFRAPOD_ESKONT_VREMENSKO_RAZGRANICENJE_DISKONTA = 5075;
		public static final Integer TIPNALOGA_SIFRAPOD_ESKONT_VREMENSKO_RAZGRANICENJE_DISKONTA_ZBIRNI = 5175;
		public static final Integer KLASANALOGA_SIFRAKNA_ESKONT_VREMENSKO_RAZGRANICENJE_DISKONTA = 5075;
		public static final Integer TIPNALOGA_SIFRAPOD_ESKONT_PRODAJA_MJENICE_PRIJE_DOSPIJECA_ZBIRNI = 5176;

		public static final Integer HOMOGENA_SKUPINA_KREDITI_2_POSTO = 1;
		public static final Integer HOMOGENA_SKUPINA_KREDITI_PRIMJENOM_PARAMETRA = 2;
		public static final Integer HOMOGENA_SKUPINA_OPOZIVI_OKVIRI = 3;
		public static final Integer HOMOGENA_SKUPINA_NEOPOZIVI_OKVIRI = 4;
		public static final Integer HOMOGENA_SKUPINA_GARANCIJE_I_AKREDITIVI = 5;
		public static final Integer HOMOGENA_SKUPINA_PROJEKCIJA_NAPLATE_IZ_NOVCANIH_TOKOVA_PRAVNA_LICA = 6;
		public static final Integer HOMOGENA_SKUPINA_PROJEKCIJA_NAPLATE_IZ_KOLATERALA_PRAVNA_LICA = 7;
		public static final Integer HOMOGENA_SKUPINA_SKUPINA_KREDITI_OSTALO_PRAVNA_LICA = 8;
		public static final Integer HOMOGENA_SKUPINA_KREDITI = 9;
		public static final Integer HOMOGENA_SKUPINA_KREDITI_SA_30062012 = 25;
		public static final Integer HOMOGENA_SKUPINA_KREDITI_OD_30062012 = 26;
		public static final Integer HOMOGENA_SKUPINA_KREDITNE_KARTICE = 10;
		public static final Integer HOMOGENA_SKUPINA_DEBITNE_KARTICE = 11;
		public static final Integer HOMOGENA_SKUPINA_PROJEKCIJA_NAPLATE_IZ_NOVCANIH_TOKOVA_STANOVNISTVO = 12;
		public static final Integer HOMOGENA_SKUPINA_PROJEKCIJA_NAPLATE_IZ_NOVCANIH_TOKOVA_STANOVNISTVO_CF = 13;
		public static final Integer HOMOGENA_SKUPINA_PROJEKCIJA_NAPLATE_IZ_KOLATERALA_STANOVNISTVO = 14;
		public static final Integer HOMOGENA_SKUPINA_PROJEKCIJA_NAPLATE_IZ_KOLATERALA_STANOVNISTVO_HIP = 15;
		public static final Integer HOMOGENA_SKUPINA_100_POSTO = 16;
		public static final Integer HOMOGENA_SKUPINA_PORAVNANJA_PO_KARTICNOM = 17;
		public static final Integer HOMOGENA_SKUPINA_OSTALA_POTRAZIVANJA_STANOVNISTVU = 18;
		public static final Integer HOMOGENA_SKUPINA_IPP = 19;
		public static final Integer HOMOGENA_SKUPINA_SEFOVI = 20;
		public static final Integer HOMOGENA_SKUPINA_OPSTI_POSLOVI = 21;
		public static final Integer HOMOGENA_SKUPINA_UPP = 22;
		public static final Integer HOMOGENA_SKUPINA_RACUNOVODTSVO = 23;
		public static final Integer HOMOGENA_SKUPINA_VRIJEDNOSTNI_PAPIRI = 24;
		public static final String DEFAULT_VRIJEDNOST_ESKONT = "0";
		// nositelj troskova
		public static final String NOSITELJ_TROSKOVA_PRODAVATELJ = "1";
		public static final String NOSITELJ_TROSKOVA_DUZNIK = "2";
		public static final String OBILJKAM_KREDITI_REDOVNA_KAMATA = "KRK";
		public static final String OBILJKAM_KREDITI_INTERKALARNA_KAMATA = "KRI";
		public static final String OBILJKAM_KREDITI_ZATEZNA_KAMATA = "KZK";
		public static final String OBILJKAM_KREDITI_EFEKTIVNA_KAMATA = "KFK";
		public static final String OBILJKAM_KREDITI_BONUS = "KRB";
		public static final String TIP_KAMATNA_STOPA_REDOVNA = "R";
		public static final String TIP_KAMATNA_STOPA_ZATEZNA = "Z";
		public static final String TIP_KAMATNA_STOPA_EKS = "E";
		public static final String TIP_KAMATNA_STOPA_BONUS = "B";
		public static final String TIP_KAMATNA_STOPA_NEDOSPJELA = "N";
		public static final String TIP_STOPA_NAKANDE = "N";
		public static final String FAKTURA_TIP_ISPISA_OBRACUN = "1";
		public static final String FAKTURA_TIP_ISPISA_RAZGRANICENJE = "2";
		// nad dio za kredite
		public static final String NADDIO_OZNKANAD_KREDITI_PREDUJAM = "001"; // korištenje
		public static final String NADDIO_OZNKANAD_KREDITI_NEISKORISTENI_KREDIT = "097";
		public static final String NADDIO_OZNKANAD_KREDITI_ORIGINALNA_OBAVEZA = "098";
		public static final String NADDIO_OZNKANAD_KREDITI_PROTUSTAVKA_NEISKORISTENI_KREDIT = "297";
		public static final String NADDIO_OZNKANAD_KREDITI_PLASMAN_PROLAZNI = "148";
		public static final String NADDIO_OZNKANAD_KREDITI_DOSPJELA_GLAVNICA_NEMA_KASNJENJA = "011";
		public static final String NADDIO_OZNKANAD_KREDITI_DOSPJELA_GLAVNICA_KASNI_DO_30_DANA = "012";
		public static final String NADDIO_OZNKANAD_KREDITI_DOSPJELA_GLAVNICA_KASNI_DO_60_DANA = "013";
		public static final String NADDIO_OZNKANAD_KREDITI_DOSPJELA_GLAVNICA_KASNI_DO_90_DANA = "014";
		public static final String NADDIO_OZNKANAD_KREDITI_DOSPJELA_GLAVNICA_KASNI_DO_120_DANA = "015";
		public static final String NADDIO_OZNKANAD_KREDITI_DOSPJELA_GLAVNICA_KASNI_DO_150_DANA = "016";
		public static final String NADDIO_OZNKANAD_KREDITI_DOSPJELA_GLAVNICA_KASNI_DO_180_DANA = "017";
		public static final String NADDIO_OZNKANAD_KREDITI_DOSPJELA_GLAVNICA_KASNI_DO_270_DANA = "018";
		public static final String NADDIO_OZNKANAD_KREDITI_DOSPJELA_GLAVNICA_KASNI_DO_GODINU_DANA = "019";
		public static final String NADDIO_OZNKANAD_KREDITI_DOSPJELA_GLAVNICA_KASNI_VISE_OD_GODINU_DANA = "020";
		public static final String NADDIO_OZNKANAD_KREDITI_KREDIT_U_OTPLATI = "002";
		public static final String NADDIO_OZNKANAD_KREDITI_KREDIT_U_OTPLATI_1_DO_3MJ = "003";
		public static final String NADDIO_OZNKANAD_KREDITI_KREDIT_U_OTPLATI_3_DO_12MJ = "004";
		public static final String NADDIO_OZNKANAD_KREDITI_KREDIT_U_OTPLATI_1_DO_2GOD = "005";
		public static final String NADDIO_OZNKANAD_KREDITI_KREDIT_U_OTPLATI_2_DO_3GOD = "006";
		public static final String NADDIO_OZNKANAD_KREDITI_KREDIT_U_OTPLATI_PREKO_3GOD = "007";
		public static final String NADDIO_OZNKANAD_KREDITI_AKTIVNA_KAMATA = "021";
		public static final String NADDIO_OZNKANAD_KREDITI_NEPRIHODOVANE_KAMATE_U_C_I_D = "069";
		public static final String NADDIO_OZNKANAD_KREDITI_PROTUSTAVKA_NEPRIHODOVANE_KAMATE_U_C_I_D = "269";
		public static final String NADDIO_OZNKANAD_KREDITI_AKTIVNA_KAMATA_KASNI_DO_30_DANA = "025";
		public static final String NADDIO_OZNKANAD_KREDITI_AKTIVNA_KAMATA_KASNI_DO_90_DANA = "026";
		public static final String NADDIO_OZNKANAD_KREDITI_AKTIVNA_KAMATA_KASNI_DO_180_DANA = "027";
		public static final String NADDIO_OZNKANAD_KREDITI_AKTIVNA_KAMATA_KASNI_DO_270_DANA = "028";
		public static final String NADDIO_OZNKANAD_KREDITI_AKTIVNA_KAMATA_KASNI_DO_GODINU_DANA = "029";
		public static final String NADDIO_OZNKANAD_KREDITI_AKTIVNA_KAMATA_KASNI_VISE_OD_GODINU_DANA = "030";
		public static final String NADDIO_OZNKANAD_KREDITI_ZATEZNA_KAMATA = "031";
		public static final String NADDIO_OZNKANAD_KREDITI_ZATEZNA_KAMATA_KASNI_DO_30_DANA = "032";
		public static final String NADDIO_OZNKANAD_KREDITI_ZATEZNA_KAMATA_KASNI_DO_90_DANA = "036";
		public static final String NADDIO_OZNKANAD_KREDITI_ZATEZNA_KAMATA_KASNI_DO_180_DANA = "037";
		public static final String NADDIO_OZNKANAD_KREDITI_ZATEZNA_KAMATA_KASNI_DO_270_DANA = "038";
		public static final String NADDIO_OZNKANAD_KREDITI_ZATEZNA_KAMATA_KASNI_DO_GODINU_DANA = "039";
		public static final String NADDIO_OZNKANAD_KREDITI_ZATEZNA_KAMATA_KASNI_VISE_OD_GODINU_DANA = "040";
		public static final String NADDIO_OZNKANAD_KREDITI_PROTUSTAVKA_PRIHOD_PO_AKTIVNOJ_KAMATI = "221";
		public static final String NADDIO_OZNKANAD_KREDITI_PROTUSTAVKA_PRIHOD_PO_ZATEZNOJ_KAMATI = "231";
		public static final String NADDIO_OZNKANAD_KREDITI_NAKNADA = "041";
		public static final String NADDIO_OZNKANAD_KREDITI_NAKNADA_KASNI_DO_30_DANA = "042";
		public static final String NADDIO_OZNKANAD_KREDITI_NAKNADA_KASNI_DO_90_DANA = "046";
		public static final String NADDIO_OZNKANAD_KREDITI_NAKNADA_KASNI_DO_180_DANA = "047";
		public static final String NADDIO_OZNKANAD_KREDITI_NAKNADA_KASNI_DO_270_DANA = "048";
		public static final String NADDIO_OZNKANAD_KREDITI_NAKNADA_KASNI_DO_GODINU_DANA = "049";
		public static final String NADDIO_OZNKANAD_KREDITI_NAKNADA_KASNI_VISE_OD_GODINU_DANA = "050";
		public static final String NADDIO_OZNKANAD_KREDITI_PROTUSTAVKA_PRIHOD_NAKNADA = "341";
		public static final String NADDIO_OZNKANAD_KREDITI_PROLAZNI_RACUN_UPLATA = "149";
		public static final String NADDIO_OZNKANAD_KREDITI_PROTUSTAVKA_CISCENJE_MALIH_SALDA_PRIHOD = "222";
		public static final String NADDIO_OZNKANAD_KREDITI_PROTUSTAVKA_CISCENJE_MALIH_SALDA_RASHOD = "223";
		public static final String NADDIO_OZNKANAD_KREDITI_AKTIVNA_EVIDENTNA_KAMATA = "023";
		public static final String NADDIO_OZNKANAD_KREDITI_IZRAVNO_ISKLJUCENI_KAMATNI_PRIHODI_REDOVNA_KAMATA = "092";
		public static final String NADDIO_OZNKANAD_KREDITI_PROTUSTAVKA_ISKLJKUCENE_REDOVNE_KAMATE = "292";
		public static final String NADDIO_OZNKANAD_KREDITI_IZRAVNO_ISKLJUCENI_KAMATNI_PRIHODI_NEDOSPJELA_KAMATA = "096";
		public static final String NADDIO_OZNKANAD_KREDITI_IZRAVNO_ISKLJUCENI_KAMATNI_PRIHODI_ZATEZNA_KAMATA = "093";
		public static final String NADDIO_OZNKANAD_KREDITI_PROTUSTAVKA_ISKLJKUCENE_ZATEZNE_KAMATE = "293";
		public static final String NADDIO_OZNKANAD_KREDITI_VREMENSKO_RAZGRANICENJE = "069";
		public static final String NADDIO_OZNKANAD_KREDITI_VREMENSKO_RAZGRANICENJE_PRIHOD = "241";
		public static final String NADDIO_OZNKANAD_KREDITI_VREMENSKO_RAZGRANICENJE_12MJESECI = "541";
		public static final String NADDIO_OZNKANAD_KREDITI_IZRAVNO_ISKLJUCENI_PRIHODI_PO_NAKNADI = "094";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_TROSKA = "135";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_GLAVNICE_ORKG = "141";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_KAMATE_ORKG = "142";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_ODOBRENI_NEIS_KREDIT_ORKG = "143";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_NAKNADE_ORKG = "144";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_OSTALIH_POTRAZIVANJA_ORKG = "145";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_GLAVNICE_PROTUSTAVKA_ORKG = "441";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_KAMATE_PROTUSTAVKA_ORKG = "441";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_NAKNADE_PROTUSTAVKA_ORKG = "441";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_OSTALIH_POTRAZIVANJA_PROTUSTAVKA_ORKG = "441";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_GLAVNICE_PRKG = "181";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_KAMATE_PRKG = "182";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_NAKNADE_PRKG = "183";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_ODOBRENI_NEIS_KREDIT_PRKG = "184";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_OSTALIH_POTRAZIVANJA_PRKG = "185";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_GLAVNICE_PROTUSTAVKA_PRKG = "442";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_KAMATE_PROTUSTAVKA_PRKG = "442";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_NAKNADE_PROTUSTAVKA_PRKG = "442";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_OSTALIH_POTRAZIVANJA_PROTUSTAVKA_PRKG = "442";
		public static final String NADDIO_OZNKANAD_KREDITI_PROTUSTAVKA_BILANCE = "355";
		public static final String NADDIO_OZNKANAD_KREDITI_PROTUSTAVKA_VANBILANCE = "353";
		public static final String NADDIO_OZNKANAD_KREDITI_TROSAK = "051";
		public static final String NADDIO_OZNKANAD_KREDITI_ISPRAVAK_VRIJEDNOSTI_KREDITA = "151";
		public static final String NADDIO_OZNKANAD_KREDITI_ISPRAVAK_VRIJEDNOSTI_KREDITA_NAKNADA = "158";
		public static final String NADDIO_OZNKANAD_KREDITI_ISPRAVAK_VRIJEDNOSTI_KAMATA = "152";
		public static final String NADDIO_OZNKANAD_KREDITI_ISPRAVAK_VRIJEDNOSTI_PROTUSTAVKA = "452";
		public static final String NADDIO_OZNKANAD_KREDITI_ISPRAVAK_VRIJEDNOSTI_KREDITA_GLAVNICA_SR_GRUPNA = "153";
		public static final String NADDIO_OZNKANAD_KREDITI_ISPRAVAK_VRIJEDNOSTI_KAMATA_SR_GRUPNA = "154";
		public static final String NADDIO_OZNKANAD_KREDITI_ISPRAVAK_VRIJEDNOSTI_NAKNADA_SR_GRUPNA = "155";
		public static final String NADDIO_OZNKANAD_KREDITI_ISPRAVAK_VRIJEDNOSTI_KREDITA_GLAVNICA_SR_POJEDINACNA = "172";
		public static final String NADDIO_OZNKANAD_KREDITI_ISPRAVAK_VRIJEDNOSTI_KAMATA_SR_POJEDINACNA = "173";
		public static final String NADDIO_OZNKANAD_KREDITI_ISPRAVAK_VRIJEDNOSTI_NAKNADA_SR_POJEDINACNA = "174";
		public static final String NADDIO_OZNKANAD_KREDITI_NEPRIHODOVANE_KAMATE_E = "076";
		public static final String NADDIO_OZNKANAD_KREDITI_ZATEZNA_KAMATA_PO_NAKNADAMA_I_PROVIZIJAMA = "077";
		public static final String NADDIO_OZNKANAD_KREDITI_ZATEZNA_KAMATA_PO_NAKNADAMA_I_PROVIZIJAMA_KASNI_DO_90_DANA = "078";
		public static final String NADDIO_OZNKANAD_KREDITI_NAKNADA_REGISTRA_ZALOGA = "079";
		public static final String NADDIO_OZNKANAD_KREDITI_NAKNADA_REGISTRA_ZALOGA_KASNI_DO_30_DANA = "080";
		public static final String NADDIO_OZNKANAD_KREDITI_NAKNADA_REGISTRA_ZALOGA_KASNI_DO_90_DANA = "081";
		public static final String NADDIO_OZNKANAD_KREDITI_PRKG_OSTALA_POTRAZIVANJA = "185";
		public static final String NADDIO_OZNKANAD_KREDITI_NEPRIHODOVANE_KAMATE = "186";
		public static final String NADDIO_OZNKANAD_KREDITI_NAKNADA_UTUZENA = "105";
		public static final String NADDIO_OZNKANAD_KREDITI_NAKNADA_NEUTUZENA = "106";
		public static final String NADDIO_OZNKANAD_KREDITI_PROTUSTAVKA_BILANCE_KAMATA = "356";
		public static final String NADDIO_OZNKANAD_KREDITI_PROTUSTAVKA_BILANCE_NAKNADA = "357";
		public static final String NADDIO_OZNKANAD_KREDITI_PROTUSTAVKA_9949 = "298";
		public static final String NADDIO_OZNKANAD_KREDITI_IZRAVNO_ISKLJUCENA_REDOVNA_KAMATA = "740";
		public static final String NADDIO_OZNKANAD_KREDITI_IZRAVNO_ISKLJUCENA_ZATEZNA_KAMATA = "741";
		public static final String NADDIO_OZNKANAD_KREDITI_NEIZRAVNO_ISKLJUCENA_REDOVNA_KAMATA = "742";
		public static final String NADDIO_OZNKANAD_KREDITI_NEIZRAVNO_ISKLJUCENA_ZATEZNA_KAMATA = "743";
		public static final String NADDIO_OZNKANAD_KREDITI_OTPISANA_KAMATA_NAKNADA = "829";
		public static final String NADDIO_OZNKANAD_KREDITI_OTPISANI_SUDSKI_PARNICNI_TROSAK = "830";
		public static final String NADDIO_OZNKANAD_KREDITI_OTPISANA_POTRAZIVANJA_PO_OSNOVI_PLASMANA = "831";
		// vrsta duga
		public static final String NADDIO_VRDUG_KREDITI_DOSPJELI_DUG = "DD";
		public static final String NADDIO_VRDUG_KREDITI_DOSPJELA_GLAVNICA = "DG";
		public static final String NADDIO_VRDUG_KREDITI_DOSPJELA_NAKNADA = "DN";
		public static final String NADDIO_VRDUG_KREDITI_DOSPJELA_KAMATA_REDOVNA = "DK";
		public static final String NADDIO_VRDUG_KREDITI_DOSPJELA_KAMATA_ZATEZNA = "DZ";
		public static final String NADDIO_VRDUG_KREDITI_GLAVNI_DUG = "GL";
		public static final String NADDIO_VRDUG_KREDITI_PROTUSTAVKA = "PS";
		public static final String NADDIO_VRDUG_KREDITI_ISKLJUCENI = "IS";
		public static final String NADDIO_VRDUG_KREDITI_UTUZENI = "UT";
		public static final String NADDIO_VRDUG_KREDITI_OSTALO = "OS";
		public static final String NADDIO_VRDUG_KREDITI_POSEBNE_REZERVE = "PR";
		// klasa partije
		public static final Integer KLASA_PARTIJE_DUGOROCNI_KREDITI_FIZICKA_LICA = 71;
		public static final Integer KLASA_PARTIJE_REFERENTNE_KS = 72;
		public static final Integer KLASA_PARTIJE_DUGOROCNI_KREDITI_PRAVNE_OSOBE = 73;
		public static final Integer KLASA_PARTIJE_KRATKOROCNI_KREDITI_PRAVNE_OSOBE = 74;
		public static final Integer KLASA_PARTIJE_PLASMANI_ESKONT_MJENICA = 76;
		public static final Integer KLASA_PARTIJE_GARANCIJE = 79;
		// tip partije
		public static final Integer TIP_PARTIJE_SIFRA_POTROSACKI_NENAMJ_DUGOR = 1693;
		public static final Integer TIP_PARTIJE_SIFRA_POTROSACKI_NENAMJ_KRATKOR = 1694;
		public static final Integer TIP_PARTIJE_SIFRA_POTROSACKI_PODUZET = 1695;
		public static final Integer TIP_PARTIJE_SIFRA_POTROSACKI_STAMB = 1696;
		public static final Integer TIP_PARTIJE_SIFRA_POTROSACKI_VOZILA = 1697;
		public static final Integer TIP_PARTIJE_SIFRA_POTROSACKI_STAMB_PROMJ_KAM = 1698;
		public static final Integer TIP_PARTIJE_SIFRA_JAVNA_POD_OSNOVNA_SREDSTVA_DUG = 1701;
		public static final Integer TIP_PARTIJE_SIFRA_JAVNA_POD_OBRTNA_SREDSTVA_DUG = 1702;
		public static final Integer TIP_PARTIJE_SIFRA_JAVNA_POD_OSTALE_NAMJENE_DUG = 1703;
		public static final Integer TIP_PARTIJE_SIFRA_PRIVATNA_OSNOVNA_SREDSTVA_DUG = 1704;
		public static final Integer TIP_PARTIJE_SIFRA_PRIVATNA_OBRTNA_SREDSTVA_DUG = 1705;
		public static final Integer TIP_PARTIJE_SIFRA_PRIVATNA_OSTALE_NAMJENE_DUG = 1706;
		public static final Integer TIP_PARTIJE_SIFRA_NEPROFITNE_OSNOVNA_SREDSTVA_DUG = 1707;
		public static final Integer TIP_PARTIJE_SIFRA_NEPROFITNE_OBRTNA_SREDSTVA_DUG = 1708;
		public static final Integer TIP_PARTIJE_SIFRA_STANOVNISTVO_PODUZETNISTVO_DUG = 1709;
		public static final Integer TIP_PARTIJE_SIFRA_PRIVATNA_PODUZECA_KOMISIONI_TUDJI_RACUN = 1710;
		public static final Integer TIP_PARTIJE_SIFRA_PRIVATNA_PODUZECA_KOMISIONI_TUDJI_RACUN_BORCI = 1711;
		public static final Integer TIP_PARTIJE_SIFRA_JAVNA_POD_OBRTNA_SREDSTVA_KRA = 1720;
		public static final Integer TIP_PARTIJE_SIFRA_JAVNA_POD_UVOZ_KRA = 1721;
		public static final Integer TIP_PARTIJE_SIFRA_JAVNA_POD_TUDJI_RACUN_KRA = 1722;
		public static final Integer TIP_PARTIJE_SIFRA_PRIVATNA_OBRTNA_SREDSTVA_KRA = 1723;
		public static final Integer TIP_PARTIJE_SIFRA_PRIVATNA_UVOZ_KRA = 1724;
		public static final Integer TIP_PARTIJE_SIFRA_NEPROFITNE_OBRTNA_SREDSTVA_KRA = 1725;
		public static final Integer TIP_PARTIJE_SIFRA_STANOVNISTVO_PODUZETNISTVO_KRA = 1726;
		public static final Integer TIP_PARTIJE_SIFRA_STANOVNISTVO_GARANCIJE_KRA = 1727;
		public static final Integer TIP_PARTIJE_SIFRA_JAVNA_POD_GARANCIJE_KRA = 1728;
		public static final Integer TIP_PARTIJE_SIFRA_OSNOVNA_SREDSTVA_VLADA = 1729;
		public static final Integer TIP_PARTIJE_SIFRA_OSNOVNA_SREDSTVA_JAVNA_PODUZECA_OSTALO = 1730;
		public static final Integer TIP_PARTIJE_SIFRA_OBRTNA_SREDSTVA_JAVNA_PODUZECA_OBNOVA = 1731;
		public static final Integer TIP_PARTIJE_SIFRA_OBRTNA_SREDSTVA_PRIVATNA_PODUZECA = 1732;
		public static final Integer TIP_PARTIJE_SIFRA_PRIVATNA_PODUZECA_KOMISIONI_TUDJI_RACUN_KM = 1733;
		public static final Integer TIP_PARTIJE_SIFRA_PRIVATNA_PODUZECA_KOMISIONI_TUDJI_RACUN_EUR = 1734;
		public static final Integer TIP_PARTIJE_SIFRA_AKTIVIRANE_GARNACIJE_AVALI_OSNOVNA_SREDSTVA = 1735;
		public static final Integer TIP_PARTIJE_SIFRA_OSNOVNA_SREDTSVA_JAVNA_PODUZECA = 1736;
		public static final Integer TIP_PARTIJE_SIFRA_JAVNA_POD_OBRTNA_SREDSTVA_KRA_PRIVATNA = 1737;
		public static final Integer TIP_PARTIJE_SIFRA_BUDZET_KANTON = 1740;
		public static final Integer TIP_PARTIJE_SIFRA_OKVIRNI_KREDIT_FIZICKOG_REZIDENTA = 4501;
		public static final Integer TIP_PARTIJE_SIFRA_OKVIRNI_KREDIT_FIZICKOG_REZIDENTA_ZIRO = 4502;
		public static final Integer TIP_PARTIJE_SIFRA_OKVIRNI_KREDIT_FIZICKOG_NEREZIDENTA = 4511;
		public static final Integer TIP_PARTIJE_SIFRA_KOLATERAL_DEPOZIT = 9;
		// proizvod
		public static final Integer PROIZVOD_SIFRA_OKVIRNI_KREDIT_PRAVNE_OSOBE = 1;
		public static final Integer PROIZVOD_SIFRA_OKVIRNI_KREDIT_FIZICKE_OSOBE = 2;
		public static final Integer PROIZVOD_SIFRA_KRATKOROCNI_JAVNA_PODUZECA = 692;
		public static final Integer PROIZVOD_SIFRA_KRATKOROCNI_PRIVATNA_PODUZECA = 693;
		public static final Integer PROIZVOD_SIFRA_KRATKOROCNI_REVOLVING = 694;
		public static final Integer PROIZVOD_SIFRA_KRATKOROCNI_STANOVNISTVO = 695;
		public static final Integer PROIZVOD_SIFRA_BUSINESS_KARTICE = 726;
		public static final Integer PROIZVOD_SIFRA_DUGOROCNI_JAVNA_PODUZECA = 696;
		public static final Integer PROIZVOD_SIFRA_DUGOROCNI_PRIVATNA_PODUZECA = 697;
		public static final Integer PROIZVOD_SIFRA_DUGOROCNI_NEPROFITNE = 698;
		public static final Integer PROIZVOD_SIFRA_MIGRACIJA = 699;
		public static final Integer PROIZVOD_SIFRA_PREKORACENJE_TEKUCI = 687;
		public static final Integer PROIZVOD_SIFRA_KREDITNA_KARTICA_STANOVNISTVO = 712;
		// grupa računa
		public static final String GRUPA_RACUNA_OKVIRNI_KREDIT_FIZICKE_OSOBE = "69";
		public static final String GRUPA_RACUNA_DUGOROCNI_KREDITI_FIZICKA_LICA = "71";
		public static final String GRUPA_RACUNA_KRATKOROCNI_KREDITI_FIZICKA_LICA = "72";
		public static final String GRUPA_RACUNA_DUGOROCNI_KREDITI_PRAVNA_LICA = "73";
		public static final String GRUPA_RACUNA_KRATKOROCNI_KREDITI_PRAVNA_LICA = "74";
		public static final String PARTIJA_GRUPA_RACUNA_KOLATERALI = "71";
		public static final String PARTIJA_GRUPA_RACUNA_ESKONT_MJENICA = "76";
		// >>> tretman kamate u otplati
		public static final String KAMOTPAR_KAMATA_SE_NAPLACUJE = "2";
		// >>> moment obračuna kamate u otplati
		public static final String NACKAPAR_O_DOSPIJECU = "0";
		// <<< tretman kamate u otplati
		public static final String NACKAPAR_MJESECNO_NA_DAN = "1";
		// <<< tretman kamate u otplati
		public static final String NACKAPAR_MJESECNO_S_KONCEM_MJESECA = "2";
		public static final String NACKAPAR_MJESECNO_I_O_DOSPIJECU = "3";
		public static final String NACKAPAR_TROMJESECNO_NA_DAN = "4";
		public static final String NACKAPAR_TROMJESECNO_S_KONCEM_MJESECA = "5";
		public static final String NACKAPAR_TROMJESECNO_I_O_DOSPIJECU = "6";
		public static final String NACKAPAR_SESTOMJESECNO_NA_DAN = "7";
		public static final String NACKAPAR_SESTOMJESECNO_S_KONCEM_MJESECA = "8";
		public static final String NACKAPAR_SESTOMJESECNO_I_O_DOSPIJECU = "9";
		public static final String NACKAPAR_GODISNJE_NA_DAN = "A";
		public static final String NACKAPAR_GODISNJE_S_KONCEM_MJESECA = "B";
		public static final String NACKAPAR_GODISNJE_I_O_DOSPIJECU = "C";
		public static final String NACKAPAR_S_RATOM = "D";
		public static final String NACKAPAR_BEZ_KAMATE = "E";
		public static final String NACKAPAR_KVARTALNO = "F";
		public static final String NACKAPAR_POLUGODISNJE = "H";
		public static final String NACKAPAR_ODMAH = "I";
		public static final String NACOBPAR_PROPORCIONALNI_OBRACUN = "1";
		public static final String NACOBPAR_KONFORMNI_OBRACUN = "2";
		public static final String NACOBPAR_PROPORCIONALNI_OBRACUN_360 = "4";
		public static final String NACOBPAR_POSTOTNI_OBRACUN = "7";
		// <<< moment obračuna kamate u otplati
		public static final String NACOBPAR_ANTICIPATIVNI_OBRACUN = "3";
		public static final String NACOBPAR_PROPORCIONALNI_360OBRACUN = "4";
		public static final String NACOBPAR_KONFORMNI_360OBRACUN = "5";
		public static final String NACOBPAR_DISKONTNI = "6";
		// >>> moment obračuna naknade u korištenju
		public static final String NACONPAR_BEZ_NAKNADE = "0";
		public static final String NACONPAR_JEDNOKRATNO_NA_CIJELI_PUSTENI_IZNOS = "1";
		public static final String NACONPAR_JEDNOKRATNO_NA_SVAKI_PUSTENI_IZNOS = "2";
		public static final String NACONPAR_JEDNOKRATNO_NA_UGOVORENI_IZNOS = "3";
		public static final String NACONPAR_JEDNOKRATNO_NA_KRAJU_FAZE_KORISTENJA = "4";
		public static final String NACONPAR_UNAPRIJED_MJESECNO_NA_UGOVORENI_IZNOS = "5";
		public static final String NACONPAR_UNAPRIJED_MJESECNO_NA_SALDO = "6";
		public static final String NACONPAR_UNAZAD_MJESECNO_NA_SALDO = "7";
		public static final String NACONPAR_JEDNOKRATNO_I_MJESECNO_NA_UGOVORENI_IZNOS = "9";
		public static final String NACONPAR_KVARTALNO = "A";
		// >>> tretman kamate u korištenju
		public static final String NACPRPAR_KAMATA_SE_NAPLACUJE = "2";
		// >>> moment obračuna naknade u otplati
		public static final String NAKOTPAR_BEZ_NAKNADE = "0";
		// <<< moment obračuna naknade u korištenju
		public static final String NAKOTPAR_UNAPRIJED_SVAKI_MJESEC_NA_SALDO = "6";
		// >>> tretman kamate u korištenju
		public static final String NAKOTPAR_UNAZAD_SVAKI_MJESEC_NA_SALDO = "7";
		public static final String NAKOTPAR_JEDNOKRATNO_NA_ISKORISTENI_IZNOS = "8";
		public static final String NAKOTPAR_UNAPRIJED_TROMJESEČNO_NA_SALDO = "A";
		public static final String NAKOTPAR_UNAZAD_TROMJESEČNO_NA_SALDO = "B";
		public static final String NAKOTPAR_S_ANUITETOM_RATOM = "C";
		// >>> moment obračuna kamate u korištenju
		public static final String PERRAPAR_BEZ_KAMATE_U_KORISTENJU = "0";
		public static final String PERRAPAR_KOD_PRIJENOSA_U_OTPLATU = "1";
		// <<< moment obračuna naknade u otplati
		public static final String PERRAPAR_MJESECNO_NA_DAN = "2";
		public static final String PERRAPAR_MJESECNO_S_KONCEM_MJESECA = "3";
		public static final String PERRAPAR_TROMJESECNO_NA_DAN = "4";
		public static final String PERRAPAR_TROMJESECNO_S_KONCEM_MJESECA = "5";
		public static final String PERRAPAR_SESTOMJESECNO_NA_DAN = "6";
		public static final String PERRAPAR_SESTOMJESECNO_S_KONCEM_MJESECA = "7";
		public static final String PERRAPAR_GODISNJE_NA_DAN = "8";
		public static final String PERRAPAR_GODISNJE_S_KONCEM_MJESECA = "9";
		public static final String PERRAPAR_O_DOSPIJECU = "A";
		public static final String PERRAPAR_MJESECNO_I_O_DOSPIJECU = "B";
		public static final String PERRAPAR_KVARTALNO = "C";
		public static final String PERRAPAR_POLUGODISNJE = "D";
		public static final String PERRAPAR_DVOMJESECNO_NA_DAN = "E";
		public static final String PERRAPAR_DVOMJESECNO_KONCEM_MJESECA = "F";
		public static final String PERRAPAR_KOD_OTVARANJA = "G";
		public static final String PLASMPAR_PO_INDEKSACIJI_PARTIJE = "0";
		public static final String PLASMPAR_PO_SREDNJEM_TECAJU = "7";
		// <<< moment obračuna kamate u korištenju
		public static final String UVKORPAR_DA = "D";
		public static final String UVKORPAR_NE = "N";
		// tretman kamate
		public static final String TRETMAN_KAMATE_PRIPIS_GLAVNICI = "1";
		public static final String TRETMAN_KAMATE_NAPLATA = "2";
		public static final String BRATAPAR_MJESECNO = "12";
		public static final String BRATAPAR_GODISNJI = "1";
		public static final String BRATAPAR_POLUGODISNJI = "2";
		public static final String BRATAPAR_TROMJESECNI = "4";
		public static final String BRATAPAR_DVOMJESECNI = "6";
		public static final String BRATAPAR_BEZ_ANUITETA = "0";
		public static final String BRATAPAR_3_UZASTOPNA_MJESECA_GODISNJE = "A";
		public static final String BRATAPAR_4_UZASTOPNA_MJESECA_GODISNJE = "B";
		public static final String BRATAPAR_6_UZASTOPNA_MJESECA_GODISNJE = "C";
		public static final String BRATAPAR_2_UZASTOPNA_MJESECA_GODISNJE = "D";
		public static final String BRATAPAR_5_UZASTOPNA_MJESECA_GODISNJE = "E";
		public static final String BRATAPAR_7_UZASTOPNA_MJESECA_GODISNJE = "F";
		public static final String BRATAPAR_8_UZASTOPNA_MJESECA_GODISNJE = "G";
		public static final String BRATAPAR_9_UZASTOPNA_MJESECA_GODISNJE = "H";
		public static final String BRATAPAR_10_UZASTOPNA_MJESECA_GODISNJE = "I";
		public static final String BRATAPAR_11_UZASTOPNA_MJESECA_GODISNJE = "J";
		public static final String KONMJPAR_DA = "D";
		public static final String KONMJPAR_NE = "N";
		public static final String REVOLPAR_DA = "D"; // revolving
		public static final String REVOLPAR_NE = "N"; // nejednake rate
		public static final String REVOLPAR_ANUITETI = "A"; // jednake rate/anuiteti

		// >>> tretman kamate u moratoriju
		public static final String KAMMOPAR_KAMATA_SE_NAPLACUJE = "2";
		// >>> moment obračuna kamate u moratoriju
		public static final String PERMOPAR_MJESECNO_NA_DAN = "0";
		public static final String PERMOPAR_MJESECNO_S_KONCEM_MJESECA = "1";
		// <<< tretman kamate u moratoriju
		public static final String PERMOPAR_TROMJESECNO_NA_DAN = "2";
		public static final String PERMOPAR_TROMJESECNO_S_KONCEM_MJESECA = "3";
		public static final String PERMOPAR_SESTOMJESECNO_NA_DAN = "4";
		public static final String PERMOPAR_SESTOMJESECNO_S_KONCEM_MJESECA = "5";
		public static final String PERMOPAR_GODISNJE_NA_DAN = "6";
		public static final String PERMOPAR_GODISNJE_S_KONCEM_MJESECA = "7";
		public static final String PERMOPAR_KVARTALNO = "8";
		public static final String PERMOPAR_POLUGODISNJE = "9";
		public static final String PERMOPAR_BEZ_KAMATE_U_MORATORIJU = "A";
		// >>> nacini otplata
		public static final String TIPRAPAR_RAZLICITI_ANUITETI = "0";
		public static final String TIPRAPAR_JEDNAKI_ANUITETI_KONFORMNO = "1";
		// <<< moment obračuna naknade u moratoriju
		public static final String TIPRAPAR_JEDNOKRATNO_NA_UGOVORENI_IZNOS_O_DOSPIJECU = "2";
		public static final String TIPRAPAR_JEDNAKE_RATE_KVOTE = "3";
		public static final String TIPRAPAR_SUKCESIVNO_UDIO_REVALORIZACIJE_U_VRACENOM_IZNOSU = "4"; // ne
																									// koristi
																									// se
		public static final String TIPRAPAR_JEDNAKI_OBROCI_S_OSTATKOM_VRIJEDNOSTI = "5";
		public static final String TIPRAPAR_JEDNAKI_ANUITETI_PROPORCIONALNO = "6";
		public static final String TIPRAPAR_JEDNAKI_ANUITETI_PROPORCIONALNO_PO_SALDU = "7"; // ne
																							// koristi
																							// se
		public static final String TIPRAPAR_IREGULARNI_PLAN_OTPLTE = "8"; // ne
																			// koristi
																			// se
		public static final String TIPRAPAR_PROMJENJIVE_RATE = "9";
		public static final String TIPRAPAR_JEDNAKI_ANUITETI_PROPORCIONALNO_LEASING = "A";
		public static final String TIPRAPAR_REVOLVING_KREDITI = "B";
		public static final String TIPRAPAR_JEDNOKRATNO_SUKCESIVNO = "2";
		public static final String TIPRAPAR_JEDNAKE_RATE_S_OSTATKOM_VRIJEDNOSTI = "5";
		public static final String TIPRAPAR_DOPUSTENO_PREKORACENJE = "4";
		public static final String NAZIV_TIPRAPAR_JEDNAKI_ANUITETI_KONFORMNO = "Jednaki anuiteti konformno";
		public static final String NAZIV_TIPRAPAR_JEDNAKI_ANUITETI_PROPORCIONALNO = "Jednaki anuiteti proporcionalno";
		public static final String NAZIV_TIPRAPAR_JEDNAKE_RATE_KVOTE = "Jednake rate kvote";
		public static final String NAZIV_TIPRAPAR_PROMJENJIVE_RATE = "Promjenjive rate";
		// >>> tecaji povrata kredita
		public static final Set<String> INDEXPRO_VALUTNA_KLAUZULA = new HashSet<String>();
		public static final String INDEXPRO_INDEKSACIJA_KUNSKI_KREDIT = "2";
		// <<< nacini otplata
		public static final String INDEXPRO_INDEKSACIJA_VAL_KLAUZULA_PORAST_SRE = "5";
		public static final String INDEXPRO_INDEKSACIJA_VAL_KLAUZULA_PORAST_PRO = "5";
		public static final String INDEXPRO_INDEKSACIJA_DEVIZNI_KREDIT = "2";
		public static final String INDEXPRO_INDEKSACIJA_VAL_KLAUZULA_SRE = "3";
		public static final String INDEXPRO_INDEKSACIJA_VAL_KLAUZULA_PRO = "3";
		public static final String INDEXPRO_INDEKSACIJA_REVALORIZACIJA = "5";
		// >>> oznake rizicnosti
		public static final String OZNKARIZ_RIZICNOST_A = "A";
		public static final String OZNKARIZ_RIZICNOST_B = "B";
		public static final String OZNKARIZ_RIZICNOST_B1 = "B1";
		public static final String OZNKARIZ_RIZICNOST_B2 = "B2";
		public static final String OZNKARIZ_RIZICNOST_B3 = "B3";
		public static final String OZNKARIZ_RIZICNOST_C = "C";
		// <<< tecaji povrata kredita
		public static final String OZNKARIZ_RIZICNOST_D = "D";
		public static final String OZNKARIZ_RIZICNOST_E = "E";
		public static final String LATENTNA_NA_GRUPNOJ_OSNOVI = "LLg";
		public static final String SPECIFICNA_NA_GRUPNOJ_OSNOVI = "SRg";
		public static final String SPECIFICNA_NA_INDIVIDUALNOJ_OSNOVI = "SRi";
		public static final String RUCNO_UNESENA_RIZICNA_SKUPINA = "R";
		public static final String PROGRAMSKI_UNESENA_RIZICNA_SKUPINA = "P";
		public static final String MIGRACIJA_KLASIFIKACIJE = "M";
		public static final String KLASIFIKACIJA_FBA_DA = "1";
		public static final String KLASIFIKACIJA_FBA_NE = "0";
		public static final String KLASIFIKACIJA_MRS_DA = "1";
		public static final String KLASIFIKACIJA_MRS_NE = "0";
		public static final String NACIN_IZRACUNA_ZBIRNO = "0";
		public static final String NACIN_IZRACUNA_POJEDINACNO_PO_PARTIJI = "1";
		public static final String NACIN_IZRACUNA_POJEDINACNO_PO_KOMITENTU = "2";
		public static final String KRITERIJ_IZRACUNA_KLASIFIKACIJE_DANI_KASNJENJA = "0";
		public static final String KRITERIJ_IZRACUNA_KLASIFIKACIJE_PO_KREDITNIM_PLASMANIMA = "1";
		public static final String KRITERIJ_IZRACUNA_KLASIFIKACIJE_FIKSNO_A = "2";
		public static final String KRITERIJ_IZRACUNA_KLASIFIKACIJE_DANI_ILI_FIKSNA_KATEGORIJA = "3";
		public static final String KRITERIJ_IZRACUNA_KLASIFIKACIJE_FIKSNO_A_ILI_SUBJEKTIVNO = "4";
		public static final String KRITERIJ_IZRACUNA_KLASIFIKACIJE_FIKSNO_E_ILI_SUBJEKTIVNO = "5";
		public static final String RIZICNE_PARTIJE = "'" + OZNKARIZ_RIZICNOST_B
				+ "', '" + OZNKARIZ_RIZICNOST_C + "', '" + OZNKARIZ_RIZICNOST_D
				+ "', '" + OZNKARIZ_RIZICNOST_E + "'";
		public static final String REVALNAD_KREDITI_REVALORIZACIJA_DA = "DA";
		public static final String REVALNAD_KREDITI_REVALORIZACIJA_NE = "NE";
		public static final String KLASIFIKACIJA_NA_TEMELJU_BROJA_DANA_KASNJENJA = "Klasifikacija po broju dana kašnjenja";
		// <<< oznake rizicnosti
		public static final String KLASIFIKACIJA_NA_TEMELJU_BROJA_DANA_KASNJENJA_NIVO_KOMITENT = "Klasifikacija po broju dana kašnjenja na nivou komitenta";
		public static final String KLASIFIKACIJA_NA_TEMELJU_UTUZENOSTI_PARTIJE = "Partija kredita utužena";
		public static final String KLASIFIKACIJA_POJEDINACNO_PO_PARTIJI_FIKSNO_A = "Pojedinačno po partiji fiksno A";
		public static final String KLASIFIKACIJA_POJEDINACNO_PO_KOMITENTU_OSTALA_AKTIVA = "Pojedinačno po komitentu ostala aktiva";
		public static final String KLASIFIKACIJA_POJEDINACNO_PO_KOMITENTU_OSTALA_AKTIVA_OPSTI_POSLOVI = "Pojedinačno po komitentu ostala aktiva opšti poslovi";
		public static final String KLASIFIKACIJA_POJEDINACNO_PO_PARTIJI_OSTALA_AKTIVA_DANI_KASNJENJA = "Pojedinačno po partiji ostala aktiva dani kašnjenja";
		public static final String KLASIFIKACIJA_POJEDINACNO_PO_PARTIJI_FIKSNO_E = "Pojedinačno po partiji fiksno E";
		public static final String KLASIFIKACIJA_POJEDINACNO_PO_PARTIJI_POTRAZIVANJA_RACUNOVODSTVO_FIKSNO_E = "Pojedinačno po partiji potraživanja računovodstvo fiksno E";
		public static final String KLASIFIKACIJA_ZBIRNO_FIKSNO_A = "Zbirno fiksno A";
		public static final String KLASIFIKACIJA_ZBIRNO_OSTALA_POTRAZIVANJA_STANOVNISTVO_FIKSNO_A = "Zbirno ostala potraživanja stanovništvo fiksno A";
		public static final String KLASIFIKACIJA_ZBIRNO_PORAVNANJA_PO_KARTICNOM_FIKSNO_A = "Zbirno poravnanja po kartičnom fiksno A";
		public static final String KLASIFIKACIJA_NA_TEMELJU_NACINA_OTPLATE_JAMCA = "Kredit vraća jamac";
		public static final String KLASIFIKACIJA_IZ_ECM = "Klasifikacija iz ECM-a";
		public static final String KLASIFIKACIJA_NA_TEMELJU_REPROGRAMA = "Klasifikacija na temelju reprograma";
		// Parametri za pomoć pri hendlanju daoteka na serveru
		public static final String PARAMETAR_SIFRAPAR_REMOTE_TRANSFER = "RemTransf";
		public static final String FILE_HANDLER_PARAM = "SWTfile";
		public static final String PARAMETAR_SIFRAPAR_DIR_SWIFT = "ftpDir";
		public static final String PARAMETAR_SIFRAPAR_DIR_SWIFT_OUT = "ftpDirO";
		public static final boolean CASE_SENSITIVE_DOLAZNE_DATOTEKE = false;
		// Tablica ocekivani_priljev, polje tippr_pri
		public static final Integer OCEKIVANI_PRILJEV_TIP_NOVCANI_TOKOVI = 1;
		public static final Integer OCEKIVANI_PRILJEV_TIP_KOLATERALI = 2;
		// Tablica klasifikacija_ostalo, polje vrakt_kla
		public static final String KLASIFIKACIJA_OSTALO_AKTIVA_POTRAZIVANJA_KREDITI_GARANCIJE = "1";
		public static final String KLASIFIKACIJA_OSTALO_AKTIVA_OSTALA_POTRAZIVANJA_STAN = "2";
		public static final String KLASIFIKACIJA_OSTALO_AKTIVA_OSTALA_POTRAZIVANJA_KART = "3";
		public static final String KLASIFIKACIJA_OSTALO_AKTIVA_VRIJEDNOSNI_PAPIRI = "4";
		public static final String KLASIFIKACIJA_OSTALO_AKTIVA_IZDATI_SEFOVI = "5";
		public static final String KLASIFIKACIJA_OSTALO_AKTIVA_OPSTI_POSLOVI = "6";
		public static final String KLASIFIKACIJA_OSTALO_AKTIVA_UPP = "7";
		public static final String KLASIFIKACIJA_OSTALO_AKTIVA_IZDATI_IPP = "8";
		public static final String KLASIFIKACIJA_OSTALO_AKTIVA_RACUNOVODSTVO = "9";
		// Tabela izloženosti
		public static final String BI_TABELA_IZLOZENOSTI_REZERVACIJA_GRUPNA = "Grupna procjena";
		public static final String BI_TABELA_IZLOZENOSTI_REZERVACIJA_POJEDINACNA = "Pojedinačna procjena";
		public static final String BI_TABELA_IZLOZENOSTI_KLASIFIKACIJA_IBNR = "IBNR";
		public static final String BI_TABELA_IZLOZENOSTI_KLASIFIKACIJA_SRI = "SR pojedinačna";
		public static final String BI_TABELA_IZLOZENOSTI_KLASIFIKACIJA_SRP = "SR grupna";
		// podvrste osiguranja
		public static final String PODVRSTA_OSIGURANJA_IDENTIFIKATOR_PRIMLJENA_ZADUZNICA = "301";
		public static final String PODVRSTA_OSIGURANJA_IDENTIFIKATOR_ZADUZNICA_PREDANA_U_BANKU = "302";
		public static final String PODVRSTA_OSIGURANJA_IDENTIFIKATOR_IZDANA_ZADUZNICA = "303";
		public static final String PODVRSTA_OSIGURANJA_IDENTIFIKATOR_PRIMLJENA_MJENICA = "304";
		public static final String PODVRSTA_OSIGURANJA_IDENTIFIKATOR_IZDANA_MJENICA = "305";
		public static Integer SIFRA_RACUNA_NERASPOZNATI_PRILJEVI_KREDITI_PL = 4;
		public static Integer SIFRA_RACUNA_NERASPOZNATI_PRILJEVI_KREDITI_FL = 5;

		public static final String NAZIV_TIPRAPAR_JEDNAKI_ANUITETI_PROPORCIONALNO_SALDO_METODA = "Jednaki anuiteti proporcionalno po saldo metodi";
		public static final String NAZIV_TIPRAPAR_JEDNAKI_ANUITETI_PROPORCIONLANO_SALDO_METODA_BEZ_GLAVNICA = "Jednaki anuiteti proporcionalno po saldo metodi bez glavnica";
		public static final String TIPRAPAR_JEDNAKI_ANUITETI_PROPORCIONALNO_PO_SALDU_BEZ_GLAVNICA = "D";
		public static final Integer TARIFNA_STAVKA_NAKNADA_KREDITI = 600;
		public static final String NADDIO_OZNKANAD_KREDITI_PROTUSTAVKA_IZRAVNO_ISKLJKUCENE_REDOVNE_KAMATE = "840";
		public static final String NADDIO_OZNKANAD_KREDITI_PRIHOD_ISKLJUECNA_REDOVNA_KAMATA = "356";
		public static final String NADDIO_OZNKANAD_KREDITI_PRIHOD_ISKLJUECNA_ZATEZNA_KAMATA = "357";
		public static final String NADDIO_OZNKANAD_KREDITI_PROTUSTAVKA_IZRAVNO_ISKLJUCENE_ZATEZNE_KAMATE = "841";
		public static final String NADDIO_OZNKANAD_KREDITI_RAZGRANICENJE_NAKNADE = "069";
		public static final String NADDIO_OZNKANAD_KREDITI_IZRAVNO_ISKLJUCENI_NAKNADA = "746";
		public static final String NADDIO_OZNKANAD_KREDITI_NEIZRAVNO_ISKLJUCENI_NAKNADA = "745";
		public static final String NADDIO_OZNKANAD_KREDITI_PRIHOD_NAKNADA = "241";
		public static final Integer KLASA_PARTIJE_OKVIR = 75;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_ZATVARANJE_GLAVNOG_DUGA_S_PREPLATOM_002 = 5541;
		public static final Integer KLASANALOGA_SIFRAKNA_KREDITI_RASPODJELA_SALDA = 5580;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_RASPODJELA_SALDA = 5580;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_RASPODJELA_SALDA_ZBIRNI = 5680;
		public static final Integer KLASANALOGA_SIFRAKNA_KREDITI_RASPODJELA_SALDA_UMANJENJE = 5581;
		public static final Integer TIPPARTIJE_SIFRATIP_KREDITI_FAKTORING = 7603;
		public static final Integer TARIFNA_STAVKA_NAKNADA_FAKTORING = 298;
		public static final String DEFAULT_VRIJEDNOST_PARTIJA_KREDITA = "0";
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_NAKNADA_OPOMENA = 5566;
		public static final Integer KLASANALOGA_SIFRAKNA_KREDITI_NAPLATA_NAKNADE_ODOBRENJE_OKVIRA = 5065;
		public static final Integer TARIFNA_STAVKA_NAKNADA_OKVIR = 181;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_NAPLATA_NAKNADE_ODOBRENJE_OKVIRA = 5065;
		public static final Integer KLASANALOGA_SIFRAKNA_KREDITI_NAPLATA_NAKNADE_PRIJEVREMENA_OTPLATA = 5520;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_NAPLATA_NAKNADE_PRIJEVREMENA_OTPLATA = 5520;
		public static final Integer KLASANALOGA_SIFRAPOD_ESKONT_PRODAJA_MJENICE_DISKONT = 5078;
		public static final Integer TIPNALOGA_SIFRAPOD_ESKONT_PRODAJA_MJENICE_DISKONT = 5078;
		public static final Integer KLASANALOGA_SIFRAKNA_KREDITI_POVRAT_PREPLATE = 5562;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_POVRAT_PREPLATE = 5562;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_DOSPIJECE_REVOLVING_KREDITA_002 = 5142;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_NAKNADA_BEZ_RAZGRANICENJA = 1001;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_GENERIRANJE_OPOMENA_ZBIRNI = 5666;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_KNJIZENJE_REZERVACIJA_HNB_ZBIRNI = 5140;
		public static final String POSTOTAK_KNJIZENJA_RIZICNOSTI_ZA_SKUPINU_A = "POS_KNJ_KLA_A";
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_KNJIZENJE_REZERVACIJA_HNB = 5040;
		public static final String PARAMETAR_ZATVORI_KLASU_506 = "ZATVORI_506";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_GLAVNICE_KLASA_A = "712";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_KAMATE_KLASA_A = "713";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_NAKNADE_KLASA_A = "714";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_NEISKORISTENOG_KREDITA = "718";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_GLAVNICE_PROTUSTAVKA_POVECANJE = "804";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_GLAVNICE_PROTUSTAVKA_SMANJENJE = "805";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_KAMATE_PROTUSTAVKA_POVECANJE = "806";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_KAMATE_PROTUSTAVKA_SMANJENJE = "807";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_A_GLAVNICA_PROTUSTAVKA = "812";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_A_KAMATA_PROTUSTAVKA = "813";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_A_NAKNADA_PROTUSTAVKA = "814";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_NAKNADE_PROTUSTAVKA_POVECANJE = "816";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_NAKNADE_PROTUSTAVKA_SMANJENJE = "817";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_NEISKORISTENOG_PROTUSTAVKA = "818";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_NAKNADA_KLASA_BC = "700";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_DOSPJELA_GLAVNICA = "704";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_NEDOSPJELA_GLAVNICA = "710";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_GLAVNICA_KORISTENJE = "711";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_REDOVNA_KAMATA_KLASA_BC = "705";
		public static final String NADDIO_OZNKANAD_KREDITI_REZERVACIJA_ZATEZNA_KAMATA_KLASA_BC = "706";
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_KNJIZENJE_REZERVACIJA_HNB = 5040;
		public static final Integer KLASANALOGA_SIFRAKNA_KREDITI_ISPLATA_PLASMANA = 5547;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_ISPLATA_PLASMANA = 5547;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_NAPLATA_INTERKALARNE_KAMATE_IZ_PLASMANA = 5063;
		public static final Integer KLASANALOGA_SIFRAKNA_KREDITI_NAPLATA_INTERKALARNE_KAMATE_IZ_PLASMANA = 5063;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_ZATVARANJE_REDOVNE_KAMATE_S_PREPLATOM = 5560;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_ZATVARANJE_DOSPJELE_GLAVNICE_S_PREPLATOM = 5561;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_NAKNADA_ZA_PROLONGAT_KREDITA = 5518;
		public static final String PROIZVOD_VRSTAPRO_OKVIRNI_KREDITI_PRAVNE_OSOBE = "44";
		public static final String PROIZVOD_VRSTAPRO_OKVIR = "75";
		public static final Integer TARIFNA_STAVKA_NAKNADA_OKVIRNI_KREDIT_PRAVNI = 277;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_NAKNADA_OKVIRNI_KREDIT_PRAVNE_BEZ_RAZGRANICENJA = 5069;
		public static final Integer KLASANALOGA_SIFRAPOD_KREDITI_PROLONNGAT_OKVIRNI_KREDIT_PRAVNE = 5068;
		public static final Integer TIPNALOGA_SIFRAPOD_KREDITI_PROLONGAT_OKVIRNI_KREDIT_PRAVNE = 5068;
		public static final Integer EKS_VRSTA_OSIGURANJA_SIFRA_TRANSE = 999;
		public static final Integer EKS_VRSTA_OSIGURANJA_SIFRA_PROCJENE = 20;
		public static final Integer EKS_VRSTA_OSIGURANJA_SIFRA_DEPOZIT = 1;
		public static final Integer EKS_VRSTA_OSIGURANJA_SIFRA_PREMIJA_ZIVOTNOG = 2;
		public static final Integer EKS_VRSTA_OSIGURANJA_SIFRA_TROSAK_PROCJENE_NEKRETNINE = 3;
		public static final Integer EKS_VRSTA_OSIGURANJA_SIFRA_TROSAK_RIZIKO_POLICE = 4;
		public static final Integer EKS_VRSTA_OSIGURANJA_SIFRA_TROSAK_POLICE_NEKRETNINE = 5;
		public static final Integer EKS_VRSTA_OSIGURANJA_SIFRA_OSTALI_TROSKOVI = 6;
		public static final Integer EKS_VRSTA_OSIGURANJA_SIFRA_TROSAK_JAVNOG_BILJEZNIKA = 7;
		public static final Integer EKS_VRSTA_OSIGURANJA_SIFRA_KOMUNALNI_TROSKOVI = 8;
		public static final Integer EKS_VRSTA_OSIGURANJA_SIFRA_ADMINISTRATIVNI_TROSKOVI = 9;
		public static final Integer EKS_VRSTA_OSIGURANJA_SIFRA_POREZ_NEKRETNINA = 10;
		public static final Integer EKS_VRSTA_OSIGURANJA_SIFRA_OSTALI_POREZI = 11;
		public static final Integer EKS_DODATNE_OBVEZE_TEKUCI_RACUN = 21;
		public static final Integer EKS_DODATNE_OBVEZE_PREUSMJERITI_PRIMANJA = 22;
		public static final Integer EKS_DODATNE_OBVEZE_POLICA_OSIGURANJA_NEKRETNINE = 23;
		public static final Integer EKS_DODATNE_OBVEZE_POLICA_ZIVOTNOG_OSIGURANJA = 24;
		public static final Integer EKS_DODATNE_OBVEZE_DEPOZIT = 25;
		public static final Integer EKS_DODATNE_OBVEZE_BEZ_OBVEZA = 26;
		//		public static String DOD_PODACI_SIFRA_TELEFONI_OPOMENE="120";
		public static String OPOMENA_OZNAKA_NULA = "0";
		public static String PARTIJA_STATU_PAR_INFORMATIVNA_PONUDA = "I";
		public static String TIP_OSIGURANJA_NAKNADE = "N";
		public static String TIP_OSIGURANJA_TROSKOVI = "E";
		public static String TIP_OSIGURANJA_DEPOZIT = "D";
		public static String TIP_OSIGURANJA_PREMIJA = "O";
		public static String TIP_OSIGURANJA_PROCJENA = "P";
		public static String TIP_OSIGURANJA_DODATNE_OBAVEZE = "X";
		public static String TIP_OSIGURANJA_INSTRUMENTI_OSIGURANJA = "I";
		public static String TIP_OSIGURANJA_TRANSE = "T";
		public static String EKS_FREKVENCIJA_JEDNOKRATNO = "0";
		public static String EKS_FREKVENCIJA_MJESECNO = "1";
		public static String EKS_FREKVENCIJA_DVOMJESECNO = "2";
		public static String EKS_FREKVENCIJA_TROMJESECNO = "3";
		public static String EKS_FREKVENCIJA_SESTOMJESECNO = "4";
		public static String EKS_FREKVENCIJA_GODISNJE = "5";
		public static String NACIN_KORISTENJA_JEDNOKRATNO = "1";
		public static String NACIN_KORISTENJA_SUKCESIVNO = "2";
		public static String NACIN_OBRACUNA_INTERKALARNE_CIJELI_IZNOS = "1";
		public static String NACIN_OBRACUNA_INTERKALARNE_ISKORISTENI_IZNOS = "2";
		public static Integer TARIFNA_STAVKA_EKS_NAKNADA_VODJENJE_TR = 606;

		public static String PARAMETAR_SIFRA_MIN_RKS = "KRMINRKS";
		public static String PARAMETAR_SIFRA_MAX_RKS = "KRMAXRKS";
		public static String PARAMETAR_SIFRA_MIN_KS_PO = "KRMINPO";
		public static String PARAMETAR_SIFRA_MIN_KS_FO = "KRMINFO";
		public static String PARAMETAR_SIFRA_STANDARDNA_MARZA_PO = "KRSTMARPO";
		public static String PARAMETAR_SIFRA_STANDARDNA_MARZA_FO = "KRSTMARFO";
		public static String PARAMETAR_SIFRA_MINIMALNA_MARZA_PO = "KRMINMARPO";
		public static String PARAMETAR_SIFRA_MINIMALNA_MARZA_FO = "KRMINMARFO";
		public static String PARAMETAR_SIFRA_MAKSIMALNA_MARZA_PO = "KRMAXMARPO";
		public static String PARAMETAR_SIFRA_MAKSIMALNA_MARZA_FO = "KRMAXMARFO";
		public static String PARAMETAR_SIFRA_MINIMALNA_KS_DEPOZIT_PO = "KRMINDEPPO";
		public static String PARAMETAR_SIFRA_MINIMALNA_KS_DEPOZIT_FO = "KRMINDEPFO";
		public static String PARAMETAR_SIFRA_MAKSIMALNA_KS_DEPOZIT_PO = "KRMAXDEPPO";
		public static String PARAMETAR_SIFRA_MAKSIMALNA_KS_DEPOZIT_FO = "KRMAXDEPFO";

		public static Integer TARIFNA_STAVKA_EKS_NAKNADE_OBRADA_KREDITA = 600;
		public static Integer TARIFNA_STAVKA_EKS_NAKNADE_POVLACENJE_TRANSE = 603;
		public static Integer TARIFNA_STAVKA_EKS_NAKNADE_VODENJE_PLASMANA = 604;
		public static Integer TARIFNA_STAVKA_EKS_NAKNADE_NEISKORISTENI_IZNOS_PLASMANA = 605;
		public static Integer TARIFNA_STAVKA_EKS_NAKNADE_VODENJE_TRANSAKCIJSKOG_RACUNA = 606;
		public static Integer TARIFNA_STAVKA_EKS_NAKNADE_VODENJE_TRANSAKCIJSKOG_RACUNA_3104 = 3104;
		public static Integer TARIFNA_STAVKA_EKS_NAKNADE_VODENJE_TRANSAKCIJSKOG_RACUNA_3105 = 3105;
		public static Integer TARIFNA_STAVKA_EKS_NAKNADE_VODENJE_TRANSAKCIJSKOG_RACUNA_3110 = 3110;
		public static String ROCNOST_RKS_OZNAKA3M = "3M";
		public static String ROCNOST_RKS_OZNAKA6M = "6M";
		public static String ROCNOST_RKS_OZNAKA12M = "12M";
		public static String ROCNOST_RKS_OZNAKA1Y = "1Y";
		public static String ROCNOST_RKS_OZNAKA3Y = "3Y";
		public static String ROCNOST_RKS_OZNAKA5Y = "5Y";
		public static String ROCNOST_RKS_OZNAKA10Y = "10Y";
		public static Integer RKS_SIFRA_NRS1 = 72;
		public static Integer RKS_SIFRA_NRS2 = 73;
		public static Integer RKS_SIFRA_NRS3 = 74;
		public static Integer RKS_SIFRA_EURIBOR = 75;
		public static Integer RKS_SIFRA_LIBOR = 76;
		public static Integer RKS_SIFRA_ZIBOR = 77;
		public static Integer RKS_SIFRA_OBVEZNICE_RH = 78;
		public static Integer TIP_KAMATE_SIFRA_PROSJECNA_KS = 27;
		public static Integer TIP_KAMATE_SIFRA_PROSJECNA_PONDERIRANA_STAMBENI = 62;
		public static Integer TIP_KAMATE_SIFRA_PROSJECNA_PONDERIRANA_OSTALI = 64;
		public static String GRUPA_VRSTA_KREDITA_STAMBENI = "S";
		public static String GRUPA_VRSTA_KREDITA_OSTALI = "O";
		public static String VRSTA_KREDITA_STAMBENI = "2";
		public static String NEKRETNINA_BEZ = "1";
		public static String NEKRETNINA_STAMBENA = "2";
		public static String NEKRETNINA_POSLOVNA = "3";

		public static String EKS_CIKLUS_USKLADJENJA_RKS_MJESECNO = "1";
		public static String EKS_CIKLUS_USKLADJENJA_RKS_KVARTALNO = "2";
		public static String EKS_CIKLUS_USKLADJENJA_RKS_POLUGODISNJE = "3";
		public static String EKS_CIKLUS_USKLADJENJA_RKS_GODISNJE = "4";

		public static Integer SIFRA_DUMMY_KOMITENTA_29501 = 29501;
		public static Integer SIFRA_DUMMY_KOMITENTA_16602 = 16602;
		public static Integer SIFRA_DUMMY_KOMITENTA_16600 = 16600;
		public static Integer SIFRA_DUMMY_KOMITENTA_16601 = 16601;
		public static final String DOR_OUT = "DOR_OUT";
		public static final String PARAMETAR_MINIMALNI_IZNOS_ZA_SLANJE_U_SRI = "LIMIT_CRNA";
		public static final String PARAMETAR_SIFRA_INSTITUCIJE_SRI = "SIFRA_DOR";
		public static final String PARAMETAR_SIFRA_AUTORIZACIJSKI_KOD_SRI = "KOD_DOR";
		public static final String PARAMETAR_BROJ_DANA_KASNJENJA_ZA_SLANJE_U_SRI = "DANI_CRNA";

		public static final String LOOKUP_KOMITENT_KEY = "1";
		public static final String LOOKUP_PARTIJA_KEY = "2";

		//stari poslovi B1 koji se vode u napom_par
		public static final String B1_POSAO_ESKONT = "47";
		public static final String B1_POSAO_GARANCIJE_KUNSKE = "49";
		public static final String B1_POSAO_GARANCIJE_DEVIZNE = "19";

		static {
			INDEXPRO_VALUTNA_KLAUZULA
					.add(INDEXPRO_INDEKSACIJA_VAL_KLAUZULA_PORAST_SRE);
			INDEXPRO_VALUTNA_KLAUZULA
					.add(INDEXPRO_INDEKSACIJA_VAL_KLAUZULA_SRE);
			INDEXPRO_VALUTNA_KLAUZULA
					.add(INDEXPRO_INDEKSACIJA_VAL_KLAUZULA_PORAST_PRO);
		}

	}

	public static class AkreditiviGarancije {
		public static final String MESSAGE_BUNDLE = "hr.abit.b3.biz.gia.message.giaMessages";

		public static final Character OZNAKA_ZA_GENERATORA_MATICNOG_BROJA = 'K';

		public static final Integer BROJ_DANA_UNATRAG_AKREDITIVI = 0;
		public static final Integer BROJ_DANA_UNATRAG_MT202 = 10;

		public static final Character OTVORENA = 'O';
		public static final Character ZATVORENA = 'Z';

		public static final String PARTIJA_AKREDITIVA_PRICOAKR_NOTURR = "NOTURR";
		public static final String PARTIJA_AKREDITIVA_PRICOAKR_URR_LATEST_VERSION = "URR LATEST VERSION";

		public static final String DEFAULT_ORGANIZACIJSKA_JEDINICA_GIA = "0001";

		public static final Integer TARIFNA_STAVKA_SWIFT = 2993;
		public static final Integer TARIFNA_STAVKA_KONFIRMACIJA = 3068;

		public static final Integer DEFAULT_NACIN_OBRACUNA_NAKNADE_KVARTALNO = 3;

		// grupe računa
		public static final String PARTIJA_GRUPA_RACUNA_VANBILANSNI_INSTRUMENTI = "89";
		public static final String PARTIJA_GRUPA_RACUNA_GARANCIJE_NOSTRO_KUNSKE = "97";
		public static final String PARTIJA_GRUPA_RACUNA_GARANCIJE_LORO_KUNSKE = "971";
		public static final String PARTIJA_GRUPA_RACUNA_GARANCIJE_NOSTRO_DEVIZNE = "98";
		public static final String PARTIJA_GRUPA_RACUNA_GARANCIJE_LORO_DEVIZNE = "981";
		public static final String PARTIJA_GRUPA_RACUNA_AKREDITIV_NOSTRO = "57";
		public static final String PARTIJA_GRUPA_RACUNA_AKREDITIV_LORO = "16";
		public static final String PARTIJA_GRUPA_RACUNA_INKASO_LORO = "23";

		// klasa partije
		public static final Integer KLASA_PARTIJE_GARANCIJE_NOSTRO = 91;
		public static final Integer KLASA_PARTIJE_GARANCIJE_LORO = 92;
		public static final Integer KLASA_PARTIJE_AKREDITIVI_NOSTRO = 5;
		public static final Integer KLASA_PARTIJE_AKREDITIVI_LORO = 160;

		// tip partije
		public static final Integer TIP_PARTIJE_AKREDITIVI_LORO_NEKONFIRMIRANI = 961;
		public static final Integer TIP_PARTIJE_AKREDITIVI_LORO = 160;
		public static final Integer TIP_PARTIJE_INKASO_LORO = 161;
		public static final List<Integer> LIST_TIP_PARTIJE_LORO_INKASO = Arrays.asList(TIP_PARTIJE_AKREDITIVI_LORO, TIP_PARTIJE_INKASO_LORO);

		// kodovi za validacije text polja
		public static final List<String> ALLOWED_CODES_NARRATIVE_STUCTURED_TEXT = Arrays.asList(new String[] { "ADD", "DELETE", "REPALL" });
		public static final List<String> ALLOWED_CODES_NARRATIVE_Z_TYPE = Arrays.asList(new String[] { "/TELEBEN/", "/PHONBEN/", "/BENCON/" });

		public static final Integer TIP_PARTIJE_GARANCIJE_GOTOVINSKA_PLACANJA_POSLOVI_INOSTRANSTVO_PRIVATNA_PREDUZECA = 81;
		public static final Integer TIP_PARTIJE_GARANCIJE_CINIDBENE_LICITACIONE_KREDITI_PRIVATNA_PREDUZECA = 82;
		public static final Integer TIP_PARTIJE_GARANCIJE_CINIDBENE_AVANSNE_KREDITI_PRIVATNA_PREDUZECA = 83;
		public static final Integer TIP_PARTIJE_GARANCIJE_CINIDBENE_DOBRO_IZVRSENJE_POSLA_JAVNA_PREDUZECA = 84;
		public static final Integer TIP_PARTIJE_GARANCIJE_CINIDBENE_CARINSKE_KREDITI_PRIVATNA_PREDUZECA = 85;
		public static final Integer TIP_PARTIJE_GARANCIJE_PLATIVE_CARINSKE_KREDITI_PRIVATNA_PREDUZECA = 86;
		public static final Integer TIP_PARTIJE_GARANCIJE_PLATIVE_ZA_UREDNO_PLACANJE_PRIVATNA_PREDUZECA = 87;
		public static final Integer TIP_PARTIJE_GARANCIJE_CINIDBENE_GARANT_DEPOZIT_KREDITI_PRIVATNA_PREDUZECA = 88;
		public static final Integer TIP_PARTIJE_GARANCIJE_AVANSNE_PRIVATNA_PREDUZECA = 89;
		public static final Integer TIP_PARTIJE_GARANCIJE_LICITACIONE_POSLOVI_INOSTRANSTVO_PRIVATNA_PREDUZECA = 90;
		public static final Integer TIP_PARTIJE_GARANCIJE_AVANSNE_POSLOVI_INOSTRANSTVO_PRIVATNA_PREDUZECA = 91;
		public static final Integer TIP_PARTIJE_GARANCIJE_DOBRO_IZVRSENJE_POSLA_POSLOVI_INOSTRANSTVO_PRIVATNA_PREDUZECA = 92;
		public static final Integer TIP_PARTIJE_GARANCIJE_LICITACIONE_IPP_PRIVATNA_PREDUZECA = 93;
		public static final Integer TIP_PARTIJE_INOGARANCIJE_BEZ_SOPSTVENE_OBAVEZE = 94;
		public static final Integer TIP_PARTIJE_GARANCIJE_DRUGI_OBLICI_JEMSTVA = 95;
		public static final Integer TIP_PARTIJE_INOGARANCIJE_SA_SOPSTVENOM_OBAVEZOM = 97;
		public static final Integer TIP_PARTIJE_GARANCIJE_CINIDBENE_DOBRO_IZVRSENJE_POSLA_PRIVATNA_PREDUZECA = 98;
		public static final Integer TIP_PARTIJE_GARANCIJE_STARO = 99;
		public static final Integer TIP_PARTIJE_GARANCIJE_CINIDBENE_LICITACIONE_POSLOVI_INOSTRANSTVO_POKRIVENE_FIZICKA_LICA = 500;
		public static final Integer TIP_PARTIJE_GARANCIJE_CINIDBENE_LICITACIONE_KREDITI_JAVNA_PREDUZECA = 501;
		public static final Integer TIP_PARTIJE_GARANCIJE_CINIDBENE_LICITACIONE_KREDITI_NEPROFITNE_ORGANIZACIJE = 502;
		public static final Integer TIP_PARTIJE_GARANCIJE_CINIDBENE_LICITACIONE_KREDITI_KOMERCIJALNE_BANKE = 503;
		public static final Integer TIP_PARTIJE_GARANCIJE_CINIDBENE_LICITACIONE_KREDITI_FIZICKA_LICA = 504;
		public static final Integer TIP_PARTIJE_GARANCIJE_CINIDBENE_AVANSNE_KREDITI_JAVNA_PREDUZECA = 505;
		public static final Integer TIP_PARTIJE_GARANCIJE_CINIDBENE_AVANSNE_KREDITI_NEPROFITNE_ORGANIZACIJE = 506;
		public static final Integer TIP_PARTIJE_GARANCIJE_CINIDBENE_AVANSNE_KREDITI_FIZICKA_LICA = 507;
		public static final Integer TIP_PARTIJE_GARANCIJE_CINIDBENE_AVANSNE_KREDITI_KOMERCIJALNE_BANKE = 508;
		public static final Integer TIP_PARTIJE_GARANCIJE_CINIDBENE_ZA_DOBRO_IZVRSENJE_POSLA_KREDITI_KOMERCIJALNE_BANKE = 510;
		public static final Integer TIP_PARTIJE_GARANCIJE_CINIDBENE_ZA_DOBRO_IZVRSENJE_POSLA_KREDITI_FIZICKA_LICA = 511;
		public static final Integer TIP_PARTIJE_GARANCIJE_CINIDBENE_ZA_DOBRO_IZVRSENJE_POSLA_KREDITI_NEPROFITNE_ORGANIZACIJE = 512;
		public static final Integer TIP_PARTIJE_GARANCIJE_CINIDBENE_CARINSKE_KREDITI_KOMERCIJALNE_BANKE = 513;
		public static final Integer TIP_PARTIJE_GARANCIJE_CINIDBENE_CARINSKE_KREDITI_JAVNA_PREDUZECA = 514;
		public static final Integer TIP_PARTIJE_GARANCIJE_PLATIVE_CARINSKE_KREDITI_JAVNA_PREDUZECA = 515;
		public static final Integer TIP_PARTIJE_GARANCIJE_PLATIVE_CARINSKE_KREDITI_FIZICKA_LICA = 516;
		public static final Integer TIP_PARTIJE_GARANCIJE_PLATIVE_ZA_UREDNO_PLACANJE_KREDITI_JAVNA_PREDUZECA = 517;
		public static final Integer TIP_PARTIJE_GARANCIJE_PLATIVE_ZA_UREDNO_PLACANJE_KREDITI_FIZICKA_LICA = 518;
		public static final Integer TIP_PARTIJE_GARANCIJE_PLATIVE_ZA_UREDNO_PLACANJE_KREDITI_KOMERCIJALNE_BANKE = 519;
		public static final Integer TIP_PARTIJE_GARANCIJE_CINIDBENE_ZA_GARANT_DEPOZIT_KREDITI_JAVNA_PREDUZECA = 520;
		public static final Integer TIP_PARTIJE_GARANCIJE_CINIDBENE_ZA_GARANT_DEPOZIT_KREDITI_KOMERCIJALNE_BANKE = 521;
		public static final Integer TIP_PARTIJE_GARANCIJE_CINIDBENE_ZA_GARANT_DEPOZIT_KREDITI_FIZICKA_LICA = 522;
		public static final Integer TIP_PARTIJE_GARANCIJE_LICITACIONE_PSI_JAVNA_PREDUZECA = 523;
		public static final Integer TIP_PARTIJE_GARANCIJE_LICITACIONE_PSI_FIZICKA_LICA = 524;
		public static final Integer TIP_PARTIJE_GARANCIJE_AVANSNE_PSI_JAVNA_PREDUZECA = 525;
		public static final Integer TIP_PARTIJE_GARANCIJE_AVANSNE_PSI_FIZICKA_LICA = 526;
		public static final Integer TIP_PARTIJE_GARANCIJE_ZA_DOBRO_IZVRSENJE_POSLA_PSI_JAVNA_PREDUZECA = 527;
		public static final Integer TIP_PARTIJE_GARANCIJE_CINIDBENE_LICITACIONE_POSLOVI_INOSTRANSTVO_POKRIVENE_PRIVATNA = 528;
		public static final Integer TIP_PARTIJE_GARANCIJE_CINIDBENE_LICITACIONE_POSLOVI_INOSTRANSTVO_POKRIVENE_KOMERC_BANKE = 529;

		// klasa naloga nostro akreditiva
		public static final Integer KLASA_NALOGA_SIFRAKNA_GIA_VANBILANCNO_KNJIZENJE_NOSTRO_ODLOZENO = 3900;
		public static final Integer KLASA_NALOGA_SIFRAKNA_GIA_VANBILANCNO_ISKNJIZENJE_NOSTRO_ODLOZENO = 3901;
		public static final Integer KLASA_NALOGA_SIFRAKNA_GIA_LIKVIDACIJA = 3902;
		public static final Integer KLASA_NALOGA_SIFRAKNA_GIA_POKRICE = 3903;
		public static final Integer KLASA_NALOGA_SIFRAKNA_GIA_PRIJENOS_POKRICA = 3904;
		public static final Integer KLASA_NALOGA_SIFRAKNA_GIA_VANBILANCNO_KNJIZENJE_NOSTRO_POLOZENO = 3905;
		public static final Integer KLASA_NALOGA_SIFRAKNA_GIA_VANBILANCNO_ISKNJIZENJE_NOSTRO_POLOZENO = 3906;
		public static final Integer KLASA_NALOGA_SIFRAKNA_GIA_LIKVIDACIJA_POKRICE_PRENESENO_INO = 3907;
		public static final Integer KLASA_NALOGA_SIFRAKNA_GIA_POKRICE_PRENESENO_INO_BANCI = 3908;
		public static final Integer KLASA_NALOGA_SIFRAPOD_GIA_AKREDITIV_OBRACUN_NAKNADE_DRAFT_NEWT = 3932;
		public static final Integer KLASA_NALOGA_SIFRAPOD_GIA_AKREDITIV_OBRACUN_NAKNADE_DRAFT_AMND = 3933;

		public static final Integer KLASA_NALOGA_SIFRAKNA_GIA_VANBILANCNO_KNJIZENJE_NOSTRO_INKASO = 3917;
		public static final Integer KLASA_NALOGA_SIFRAKNA_GIA_VANBILANCNO_ISKNJIZENJE_NOSTRO_INKASO = 3918;

		// klasa naloga loro akreditiva
		public static final Integer KLASA_NALOGA_SIFRAKNA_GIA_VANBILANCNO_KNJIZENJE_LORO_ODLOZENO = 3910;
		public static final Integer KLASA_NALOGA_SIFRAKNA_GIA_VANBILANCNO_ISKNJIZENJE_LORO_ODLOZENO = 3911;
		public static final Integer KLASA_NALOGA_SIFRAKNA_GIA_VANBILANCNO_KNJIZENJE_LORO_POLOZENO = 3915;
		public static final Integer KLASA_NALOGA_SIFRAKNA_GIA_VANBILANCNO_ISKNJIZENJE_LORO_POLOZENO = 3916;

		public static final String KLASA_NALOGA_GRUPA_AKREDITIVI = "AKRED";
		public static final String KLASA_NALOGA_GRUPA_AKREDITIVI_POKRICE = "AKPOK";

		public static final String KLASA_NALOGA_GRUPA_GARANCIJE = "GAR";

		public static final Integer KLASA_NALOGA_SIFRAKNA_GIA_VANBILANCNO_KNJIZENJE_GARANCIJA = 3950;
		public static final Integer KLASA_NALOGA_SIFRAKNA_GIA_VANBILANCNO_ISKNJIZENJE_GARANCIJA = 3951;
		public static final Integer KLASA_NALOGA_SIFRAKNA_GIA_IZMJENA_GARANCIJA = 3952;
		public static final Integer KLASA_NALOGA_SIFRAKNA_GIA_PRIJENOS_POKRICE_GARANCIJA = 3953;
		public static final Integer KLASA_NALOGA_SIFRAKNA_GIA_PRIJENOS_GARANCIJE_NA_TERET_BANKE = 3954;
		public static final Integer KLASA_NALOGA_SIFRAKNA_GIA_VANBILANCNO_KNJIZENJE_GARANCIJA_LORO = 3955;
		public static final Integer KLASA_NALOGA_SIFRAKNA_GIA_VANBILANCNO_ISKNJIZENJE_GARANCIJA_LORO = 3956;

		public static final Integer KLASA_NALOGA_SIFRAPOD_GIA_GARANCIJA_NOSTRO_OBRACUN_NAKNADE_PERIODSKI_KNJIZNI_KREDITNA = 3860;
		public static final Integer KLASA_NALOGA_SIFRAPOD_GIA_GARANCIJA_NOSTRO_OBRACUN_NAKNADE_PERIODSKI_KNJIZNI_INOGARANCIJA = 3861;
		public static final Integer KLASA_NALOGA_SIFRAPOD_GIA_GARANCIJA_LORO_OBRACUN_NAKNADE_PERIODSKI_KNJIZNI = 3870;

		public static final Integer KLASA_NALOGA_SIFRAPOD_GIA_GARANCIJA_NOSTRO_NAKNADA_IZMJENA_KREDITNE_GARANCIJE = 3862;
		public static final Integer KLASA_NALOGA_SIFRAPOD_GIA_GARANCIJA_NOSTRO_NAKNADA_IZMJENA_INOGARANCIJE = 3863;

		public static final Integer KLASA_NALOGA_SIFRAPOD_GIA_GARANCIJA_NOSTRO_OBRADA_ZAHTJEVA_KREDITNA_DO_GODINE = 3864;
		public static final Integer KLASA_NALOGA_SIFRAPOD_GIA_GARANCIJA_NOSTRO_OBRADA_ZAHTJEVA_KREDITNA_PREKO_GODINE = 3865;
		public static final Integer KLASA_NALOGA_SIFRAPOD_GIA_GARANCIJA_NOSTRO_OBRADA_ZAHTJEVA_INOGARANCIJA_DO_GODINE = 3866;
		public static final Integer KLASA_NALOGA_SIFRAPOD_GIA_GARANCIJA_NOSTRO_OBRADA_ZAHTJEVA_INOGARANCIJA_PREKO_GODINE = 3867;
		public static final Integer KLASA_NALOGA_SIFRAPOD_GIA_GARANCIJA_NOSTRO_OBRACUN_NAKNADE_DEFAULT = 3871;

		// tip naloga
		public static final Integer TIP_NALOGA_SIFRAPOD_NAPLATA_NAKANDE_AKREDITIVI = 1803;
		public static final Integer TIP_NALOGA_MIGRACIJA_PROMETA_AKREDITIVA_NOSTRO = 3501;
		public static final Integer TIP_NALOGA_MIGRACIJA_PROMETA_AKREDITIVA_LORO = 3502;
		public static final Integer TIP_NALOGA_ZBIRNI_AKREDITIVA_LORO = 3503;
		public static final Integer TIP_NALOGA_ZBIRNI_GARANCIJA_NOSTRO = 3504;
		public static final Integer TIP_NALOGA_ZBIRNI_GARANCIJA_LORO = 3505;
		public static final Integer TIP_NALOGA_MIGRACIJA_PARTIJA_GARANCIJA = 3506;
		public static final Integer TIP_NALOGA_MIGRACIJA_PROMETA_INKASA = 3507;

		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_AKREDITIV_NOSTRO_VANBILANCNO_ISKNJIZENJE = 3901;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_LIKVIDACIJA = 3902;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_AMANDMAN_VANBILANCNO_KNJIZENJE = 3903;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_AMANDMAN_VANBILANCNO_ISKNJIZENJE = 3904;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_POKRICE = 3905;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_AKREDITIV_LORO_VANBILANCNO_KNJIZENJE = 3906;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_AKREDITIV_LORO_VANBILANCNO_ISKNJIZENJE = 3907;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_INKASO_LORO_VANBILANCNO_KNJIZENJE = 3908;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_INKASO_LORO_VANBILANCNO_ISKNJIZENJE = 3909;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_LIKVIDACIJA_POKRICE_PRENESENO_INO = 3910;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_INKASO_NOSTRO_VANBILANCNO_KNJIZENJE = 3911;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_INKASO_NOSTRO_VANBILANCNO_ISKNJIZENJE = 3912;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_AMANDMAN_LORO_VANBILANCNO_KNJIZENJE = 3913;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_AMANDMAN_LORO_VANBILANCNO_ISKNJIZENJE = 3914;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_AKREDITIV_NOSTRO_NAKNADA_SWIFT_TROSAK = 3915;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_AKREDITIV_NOSTRO_NAKNADA_KONFIRMACIJE_POLOZENO = 3935;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_AKREDITIV_NOSTRO_NAKNADA_KONFIRMACIJE_ODGODENO = 3936;

		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_AKREDITIV_OBRACUN_NAKNADE_PERIODSKI_ZBIRNI = 3920;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_AKREDITIV_OBRACUN_NAKNADE_PERIODSKI_NOSTRO_POLOZENO_POKRICE = 3930;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_AKREDITIV_OBRACUN_NAKNADE_PERIODSKI_NOSTRO_ODLOZENO_POKRICE = 3931;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_AKREDITIV_OBRACUN_NAKNADE_DRAFT_NEWT = 3932;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_AKREDITIV_OBRACUN_NAKNADE_DRAFT_AMND = 3933;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_AKREDITIV_OBRACUN_NAKNADE_PERIODSKI_LORO = 3940;

		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_GARANCIJA_OBRACUN_NAKNADE_PERIODSKI_ZBIRNI = 3850;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_GARANCIJA_OBRACUN_NAKNADE_PERIODSKI_NOSTRO_CINIDBENA_KREDITNA = 3860;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_GARANCIJA_OBRACUN_NAKNADE_PERIODSKI_NOSTRO_PLATIVA_KREDITNA = 3861;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_GARANCIJA_OBRACUN_NAKNADE_PERIODSKI_NOSTRO_SUPERGARANCIJA_KONTRAGARANCIJA_KREDITNA = 3862;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_GARANCIJA_OBRACUN_NAKNADE_PERIODSKI_LORO_KONFIRMACIJA = 3870;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_GARANCIJA_OBRACUN_NAKNADE_PERIODSKI_NOSTRO_CINIDBENA_INOGARANCIJA = 3863;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_GARANCIJA_OBRACUN_NAKNADE_PERIODSKI_NOSTRO_PLATIVA_INOGARANCIJA = 3864;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_GARANCIJA_OBRACUN_NAKNADE_PERIODSKI_NOSTRO_SUPERGARANCIJA_KONTRAGARANCIJA_INOGARANCIJA = 3865;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_GARANCIJA_OBRACUN_NAKNADE_PERIODSKI_NOSTRO = 3866;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_GARANCIJA_OBRACUN_NAKNADE_PERIODSKI_LORO = 3871;

		public static final Integer TIP_NALOGA_SWIFT_CREATE_MESSAGE_AKREDITIVI = 1503;
		public static final Integer TIP_NALOGA_SWIFT_CREATE_MESSAGE_GARANCIJA = 1504;

		public static final Integer TIP_NALOGA_NEFINLOG_OTVARANJE_AKREDITIVA = 10161;
		public static final Integer TIP_NALOGA_NEFINLOG_AMANDMAN_AKREDITIVA = 10162;
		public static final Integer TIP_NALOGA_NEFINLOG_ZATVARANJE_AKREDITIVA = 10163;
		public static final Integer TIP_NALOGA_NEFINLOG_OSTALI_POSLOVI_AKREDITIVA = 10164;

		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_GARANCIJA_NOSTRO_VANBILANCNO_KNJIZENJE = 3950;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_GARANCIJA_NOSTRO_VANBILANCNO_ISKNJIZENJE = 3951;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_LIKVIDACIJA_GARANCIJA = 3952;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_GARANCIJA_LORO_IZMJENA = 3953;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_GARANCIJA_NAKNADA_IZMJENA_LORO = 3954;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_PRIJENOS_POKRICE_GARANCIJA = 3955;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_GARANCIJA_LORO_VANBILANCNO_KNJIZENJE = 3956;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_GARANCIJA_LORO_VANBILANCNO_ISKNJIZENJE = 3957;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_GARANCIJA_NOSTRO_IZMJENA_KNJIZENJE = 3960;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_GARANCIJA_NOSTRO_PRIJENOS_ISKNJIZENJE = 3961;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_GARANCIJA_NOSTRO_PRIJENOS_KNJIZENJE = 3962;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_GARANCIJA_NOSTRO_IZMJENA_ISKNJIZENJE = 3963;

		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_GARANCIJA_NAKNADA_IZMJENA_KREDITNA = 3866;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_GARANCIJA_NAKNADA_IZMJENA_INOGARANCIJA = 3867;

		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_GARANCIJA_OBRADA_ZAHTJEVA_KREDITNA_DO_GODINE = 3871;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_GARANCIJA_OBRADA_ZAHTJEVA_KREDITNA_PREKO_GODINE = 3872;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_GARANCIJA_OBRADA_ZAHTJEVA_INOGARANCIJA_DO_GODINE = 3873;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_GARANCIJA_OBRADA_ZAHTJEVA_INOGARANCIJA_PREKO_GODINE = 3874;

		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_GARANCIJE_OBRACUN_NAKNADE_OPCENITO = 908;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_GARANCIJE_OBRACUN_NAKNADE_RAZGRANICENJE = 909;

		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_GARANCIJA_OBRACUN_NAKNADE_PERIODSKI_CINIDBENA_DEFAULT = 3875;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_GARANCIJA_OBRACUN_NAKNADE_PERIODSKI_PLATIVA_DEFAULT = 3876;
		public static final Integer TIP_NALOGA_SIFRAPOD_GIA_GARANCIJA_OBRACUN_NAKNADE_PERIODSKI_SUPERGARANCIJA_DEFAULT = 3877;

		public static final Integer TIP_NALOGA_NEFINLOG_OTVARANJE_GARANCIJA = 10032;
		public static final Integer TIP_NALOGA_NEFINLOG_ZATVARANJE_GARANCIJA = 10152;
		public static final Integer TIP_NALOGA_NEFINLOG_IZMJENA_GARANCIJA = 10056;
		public static final Integer TIP_NALOGA_NEFINLOG_PRIJENOS_GARANCIJE = 10071;
		public static final Integer TIP_NALOGA_NEFINLOG_IZMJENA_GARANCIJA_LORO = 10174;
		public static final Integer KLASA_NALOGA_PRAZNI_OPCI_DEVIZNI = 4000;

		// vrste akreditiva
		public static final String VRSTARKR_IRREVOCABLE = "IRREVOCABLE";
		public static final String VRSTARKR_IRREVOCABLE_TRANSFERABLE = "IRREVOCABLE TRANSFERABLE";
		public static final String VRSTARKR_IRREVOCABLE_STANDBY = "IRREVOCABLE STANDBY";
		public static final String VRSTARKR_IRREVOC_TRANS_STANDBY = "IRREVOC TRANS STANDBY";

		// vrsta troška izmjene 71N
		public static final String TROSAK_IZMJENE_AKREDITIVA_APPL = "APPL";
		public static final String TROSAK_IZMJENE_AKREDITIVA_BENE = "BENE";
		public static final String TROSAK_IZMJENE_AKREDITIVA_OTHR = "OTHR";

		// vrste korištenja akreditiva - ovo ide preko opisnih polja jer se
		// polje 41A formira na temelju vrste korištenja akreditiva i načina
		// korištenja akreditiva, a oba su potrebna za izvještaje
		public static final String KOKODAKR_KOD_AKREDITIVNE_BANKE = "VAS";
		public static final String KOKODAKR_KOD_AVIZIRAJUCE_BANKE = "AVIZIRAJUĆE BANKE";
		public static final String KOKODAKR_KOD_BILO_KOJE_BANKE = "BILO KOJE BANKE";

		// način korištenja akreditiva - ovo ide preko opisnih polja jer se
		// polje 41A formira na temelju vrste korištenja akreditiva i načina
		// korištenja akreditiva, a oba su potrebna za izvještaje
		public static final String NACKOAKR_BY_PAYMENT = "BY PAYMENT";
		public static final String NACKOAKR_BY_DEF_PAYMENT = "BY DEF PAYMENT";
		public static final String NACKOAKR_OTHER = "OTHER";

		public static final String NACKOAKR_PLACANJE_PO_VIDENJU = "PLAĆANJE PO VIĐENJU";
		public static final String NACKOAKR_PLACANJE_S_ODGODOM = "S ODGOĐENIM PLAĆANJEM";
		public static final String NACKOAKR_OSTALO = "OSTALO";

		// način akvizicije akreditiva
		public static final String KONFIAKR_UZ_DODAVANJE_KONFIRMACIJE = "0";
		public static final String KONFIAKR_BEZ_DODAVANJA_KONFIRMACIJE = "2";
		public static final String KONFIAKR_KONFIRMACIJA_SE_MOZE_DODATI = "1";

		// način akvizicije akreditiva - status parametrizacija tipa partije
		public static final String AKREDITIV_SA_KONFIRMACIJOM = "PK";
		public static final String AKREDITIV_BEZ_KONFIRMACIJE = "NK";

		// avizicija garancija - status parametrizacija tipa partije
		public static final String GARANCIJA_S_KONFIRMACIJOM = "PK";
		public static final String GARANCIJA_BEZ_KONFIRMACIJE = "NK";

		// pravila primjenjena na akreditiv
		public static final String PRIMJAKR_UCP_LATEST_VERSION = "UCP LATEST VERSION";
		public static final String PRIMJAKR_EUCP_LATEST_VERSION = "EUCP LATEST VERSION";
		public static final String PRIMJAKR_UCPURR_LATEST_VERSION = "UCPURR LATEST VERSION";
		public static final String PRIMJAKR_EUCPURR_LATEST_VERSION = "EUCPURR LATEST VERSION";
		public static final String PRIMJAKR_ISP_LATEST_VERSION = "ISP LATEST VERSION";
		public static final String PRIMJAKR_OTHR = "OTHR";

		// pravila primjenjena na akreditiv (cover message)
		public static final String PRICOAKR_NOTURR = "NOTURR";
		public static final String PRICOAKR_URR_LATEST_VERSION = "URR LATEST VERSION";

		// djelomična isporuka
		public static final Integer DJISPAKR_NIJE_DOPUSTENA_DJELOMICNA_ISPORUKA = 0;
		public static final Integer DJISPAKR_DOPUSTENA_DJELOMICNA_ISPORUKA = 1;
		public static final Integer DJISPAKR_UVJETNO_DOPUSTENA_DJELOMICNA_ISPORUKA = 2;

		// pretovar
		public static final Integer PRETVAKR_PRETOVAR_NIJE_DOPUSTEN = 0;
		public static final Integer PRETVAKR_PRETOVAR_DOPUSTEN = 1;
		public static final Integer PRETVAKR_PRETOVAR_UVJETNO_DOPUSTEN = 2;

		// troškovi na teret
		public static final Integer TERECENJE_TROSKA_NALOGODAVAC = 1;
		public static final Integer TERECENJE_TROSKA_KORISNIK = 2;

		// načini obračuna naknade akreditiva
		public static final Integer OBRACUN_NAKNADE_AKREDITIVA_MJESECNI = 1;
		public static final Integer OBRACUN_NAKNADE_AKREDITIVA_TROMJESECNI = 3;
		public static final Integer OBRACUN_NAKNADE_AKREDITIVA_POLUGODISNJI = 6;
		public static final Integer OBRACUN_NAKNADE_AKREDITIVA_GODISNJI = 12;

		// troškovi na teret nalogodavca/korisnika
		public static final Integer TROSKOVI_NA_TERET_NALOGODAVCA = 1;
		public static final Integer TROSKOVI_NA_TERET_KORISNIKA = 2;

		// tipovi nostro akreditiva
		public static final Integer NEPOKRIVENI_NOSTRO_AKREDITIV = 0;
		public static final Integer POKRIVENI_NOSTRO_AKREDITIV = 1;
		public static final Integer POKRICE_PRENESENO_INOSTRANSTVO_NOSTRO_AKREDITIV = 2;

		// tipovi nostro akreditiva - status parametrizacija tipa partije
		public static final String AKREDITIV_ODLOZENO_POKRICE = "PO";
		public static final String AKREDITIV_POLOZENO_POKRICE = "PP";

		// opće konstante
		public static final String DAYS = "DAYS";
		public static final String DAYS_AFTER = "DAYS AFTER";
		public static final String DOPUSTENO = "ALLOWED";
		public static final String NIJE_DOPUSTENO = "NOT ALLOWED";
		public static final String UVJETNO_DOPUSTENO = "CONDITIONAL";

		// status
		public static final String STATUS_TABLE_PARTIJA_AKREDITIVA = "PartijaAkreditiva";
		public static final String STATUS_FIELD_PARTIJA_AKREDITIVA = "swiftMessageType";

		// rezidentnost akreditiva - status parametrizacija tipa partije
		public static final String AKREDITIV_REZIDENT = "R";
		public static final String AKREDITIV_NEREZIDENT = "N";

		// parametar
		public static final String PARAMETAR_DEFAULTNA_POSLOVNA_JEDINICA = "507";

		// tipovi garancija prema namjeni - za obračun naknada
		public static final Integer GARANCIJA_NAMJENA_CINIDBENA = 1;
		public static final Integer GARANCIJA_NAMJENA_PLATIVA = 2;
		public static final Integer GARANCIJA_NAMJENA_SUPERGARANCIJA_KONTRAGARANCIJA = 3;

		// porijeklo garancije
		public static final String GARANCIJA_U_ZEMLJI = "D";
		public static final String GARANCIJA_IZ_POSLOVA_S_INOZEMSTVOM = "S";

		// garancija pala na teret banke
		public static final String GARANCIJA_PALA_NA_TERET_BANKE = "P";
		public static final String GARANCIJA_DJELOMICNO_PALA_NA_TERET_BANKE = "D";

		// tarifna_stavka
		public static final Integer TARIFNASTAVKA_OBRADA_ZAHTJEVA_NOSTRO_GARANCIJE = 251;
		public static final Integer TARIFNASTAVKA_OBRADA_ZAHTJEVA_LORO_GARANCIJE = 258;
		public static final Integer TARIFNASTAVKA_TROMJESECNA_KONFIRMACIJA_LORO_GARANCIJE = 260;

		// načini obračuna naknade garancija nostro
		public static final String OBRACUN_NAKNADE_GARANCIJE_MJESECNI = "1";
		public static final String OBRACUN_NAKNADE_GARANCIJE_TROMJESECNI = "3";
		public static final String OBRACUN_NAKNADE_GARANCIJE_POLUGODISNJI = "6";
		public static final String OBRACUN_NAKNADE_GARANCIJE_GODISNJI = "12";
		public static final String OBRACUN_NAKNADE_GARANCIJE_PO_DOSPIJECU = "0";

		// načini obračuna naknade garancija loro
		public static final Character OBRACUN_NAKNADE_GARANCIJE_LORO_MJESECNI = '1';
		public static final Character OBRACUN_NAKNADE_GARANCIJE_LORO_TROMJESECNI = '3';
		public static final Character OBRACUN_NAKNADE_GARANCIJE_LORO_POLUGODISNJI = '6';
		public static final Character OBRACUN_NAKNADE_GARANCIJE_LORO_GODISNJI = 'A';

		// vrste garancija nostro
		public static final Integer VRSTA_GARANCIJE_PLATEZNA = 1;
		public static final Integer VRSTA_GARANCIJE_CARINSKA = 2;
		public static final Integer VRSTA_GARANCIJE_PONUDBENA = 3;
		public static final Integer VRSTA_GARANCIJE_DOBRO_IZVRSENJE_POSLA = 4;
		public static final Integer VRSTA_GARANCIJE_POVRAT_AVANSA = 5;
		public static final Integer VRSTA_GARANCIJE_KONTRAGARANCIJA = 6;

		//PDV garancije flag
		public static final Integer GARANCIJA_PDV_BOOLEAN_DA = 1;
		public static final Integer GARANCIJA_PDV_BOOLEAN_NE = 0;

	}

	public static class Karticno {
		public static final String MESSAGE_BUNDLE = "hr.abit.b3.biz.karticno.message.karticnoMessages";

		public static final Boolean OLB_AKTIVAN = Boolean.FALSE;
		public static final String KOMITENT_LOKACIJA_ADRESA_ATM_POS = "3";
		public static final String KOMITENT_LOKACIJA_ADRESA_DOSTAVA_PINA = "4";
		public static Integer[] TIPOVI_NALOGA_ATM_NAKNADA_STANOVNISTVO = { 7052, 7053, 7054 };
		// Tip partije
		public static final Integer TIP_PARTIJE_SIFRATIP_PARTIJE_MBU_BANAKA = 1330;
		public static final Integer TIP_PARTIJE_SIFRATIP_PARTIJE_POS_TERMINALA = 1331;
		public static final Integer KLASA_PARTIJE_SIFRAKLP_PARTIJE_KARTICNO = 20;
		public static final String PARTIJA_GRUPA_RACUNA_KARTICNO_PRIJELAZNI = "95";
		// INCIDENTI
		// Tip transakcije
		public static final Map<String, String> INCIDENTI_TIP_TRANSAKCIJE_POS = Collections
				.unmodifiableMap(new HashMap<String, String>() {
					{
						put("010", "Normal purchase");
						put("011", "Preauthorization purchase");
						put("012", "Preauthorization purchase completion");
						put("013", "Mail/phone order");
						put("014", "Merchandise return");
						put("015", "Cash advance");
						put("016", "Card verification");
						put("017", "Balance inquiry");
						put("018", "Purchase with cash back");
						put("019", "Check verification");
						put("020", "Check guarantee");
						put("021", "Purchase adjustment");
						put("022", "Adjustment--merchandise return");
						put("023", "Adjustment--cash advance");
						put("024", "Adjustment--purchase with cash back");
						put("038", "POS instalment");
						put("039", "POS instalment - storno");
						put("05x", "POS closure problem");
					}
				});
		public static final Map<String, String> INCIDENTI_TIP_TRANSAKCIJE_ATM = Collections
				.unmodifiableMap(new HashMap<String, String>() {
					{
						put("003", "Check Guarantee");
						put("004", "Check Verify");
						put("010", "Withdrawal");
						put("011", "Cash Check");
						put("018", "ATM instalment");
						put("019", "Card Activation on ATM");
						put("020", "Deposit");
						put("024", "Deposit with Cash Back");
						put("025", "Mobile Top-Up");
						put("026", "Mobile Top-Up with Funds");
						put("030", "Balance Inquiry");
						put("040", "Transfer");
						put("041", "Load Value");
						put("050", "Payment from/to");
						put("051", "Payment Enclosed");
						put("060", "Message Enclosed to Financial Institution");
						put("061", "Log Only");
						put("062", "Card Review");
						put("070", "Statement printon");
						put("081", "PIN change");
						put("082", "EMV PIN Unblock");
						put("0U1", "SVC Balance Inquiry");
					}
				});
		// Tip terminala
		public static final Map<String, String> INCIDENTI_TIP_TERMINALA = Collections
				.unmodifiableMap(new HashMap<String, String>() {
					{
						put("000", "POINT OF SALE (POS). Unutar MBNET-a");
						put("001", "AUTOMATIC TELLER MACHINE (ATM) Unutar MBNET-a");
						put("003", "IMPRINTER");
						put("004", "DUMMY VALUE - INTERNAL USE ONLY (ATM-VISA)");
						put("005", "POINT OF SALE (POS). Izvan MBNET-a");
						put("006", "AUTOMATIC TELLER MACHINE (ATM) Izvan MBNET-a plaćanje GSM bona (na bankomatu ili preko mobitela (VPOS-a)");
					}
				});
		// Klasa naloga
		public static final Integer KLASANALOGA_SIFRAKNA_TRANSAKCIJE = 7001;
		public static final Integer KLASANALOGA_SIFRAKNA_NAKNADE = 7002;
		// Tip naloga
		public static final String TIP_NALOGA_IDENT_IZDAVANJE_OSNOVNE = "IZD_OSN";
		public static final String TIP_NALOGA_IDENT_IZDAVANJE_DODATNE = "ISD_DOD";
		public static final String TIP_NALOGA_IDENT_REPRINT_PINA = "REP_PIN";
		public static final String TIP_NALOGA_IDENT_ZAMJENA_GUBITAK_KRADJA = "ZAM_GK";
		public static final String TIP_NALOGA_IDENT_ZAMJENA_OSTECENJE_PROMJENA_PODATAKA = "ZAM_GOP";
		public static final String TIP_NALOGA_IDENT_BLOKADA = "BLOK";
		public static final String TIP_NALOGA_IDENT_PRODULJENJE = "PROD";
		public static final String TIP_NALOGA_IDENT_ATM_ISPLATA_BANKA = "ATM";
		public static final String TIP_NALOGA_IDENT_ATM_ISPLATA_MBNET = "ATM_MB";
		public static final String TIP_NALOGA_IDENT_ATM_ISPLATA_RH = "ATM_RH";
		public static final String TIP_NALOGA_IDENT_ATM_ISPLATA_EU = "ATM_EU";
		public static final String TIP_NALOGA_IDENT_ATM_ISPLATA_VAN_EU = "ATM_EUX";
		public static final String TIP_NALOGA_IDENT_POSG_BANKA = "POSG";
		public static final String TIP_NALOGA_IDENT_POSG_MBNET = "POSG_MB";
		public static final String TIP_NALOGA_IDENT_POSG_RH = "POSG_RH";
		public static final String TIP_NALOGA_IDENT_POSG_EU = "POSG_EU";
		public static final String TIP_NALOGA_IDENT_POSG_VAN_EU = "POSG_EUX";
		public static final String TIP_NALOGA_IDENT_POSK_BANKA = "POSK";
		public static final String TIP_NALOGA_IDENT_POSK_MBNET = "POSK_MB";
		public static final String TIP_NALOGA_IDENT_POSK_RH = "POSK_RH";
		public static final String TIP_NALOGA_IDENT_POSK_EU = "POSK_EU";
		public static final String TIP_NALOGA_IDENT_POSK_VAN_EU = "POSK_EUX";
		public static final String TIP_NALOGA_IDENT_BONOVI_TERMINAL_BANKE = "BON_BAN";
		public static final String TIP_NALOGA_IDENT_BONOVI_TERMINAL_TUDJI = "BON_BANX";
		public static final String TIP_NALOGA_IDENT_DEPOZIT = "DEP";
		public static final String TIP_NALOGA_IDENT_TUDJA_ATM = "TUDJA_ATM";
		public static final String TIP_NALOGA_IDENT_TUDJA_BON = "TUDJA_BON";
		public static final String TIP_NALOGA_IDENT_TUDJA_POSG = "TUDJA_POSG";
		public static final String TIP_NALOGA_IDENT_TUDJA_POSK = "TUDJA_POSK";
		public static final Integer TIP_NALOGA_SIFRAPOD_MIGRACIJA_UPLATA_POTRAZIVANJA = 7195;
		public static final Integer TIP_NALOGA_SIFRAPOD_MIGRACIJA_POS_PROMETI = 7196;
		public static final Integer TIP_NALOGA_SIFRAPOD_PLACANJE_OBVEZA_MBU_BANKE = 7190;
		public static final Integer TIP_NALOGA_SIFRAPOD_UPLATA_POTRAZIVANJA_MBU_BANKE = 7191;
		// Nad dijelovi
		public static final String NADDIO_OZNKANAD_OBVEZE_PREMA_BANKAMA = "270";
		public static final String NADDIO_OZNKANAD_POTRAZIVANJA_OD_BANAKA = "271";
		// Totali
		public static final String KART_TOTALI_VRPLATOT_5 = "5";
		// public static final String
		// NADDIO_OZNKANAD_POTRAZIVANJA_PO_KARTICNOM_VLASTITE = "162";
		// public static final String NADDIO_OZNKANAD_OBAVEZE_PO_KARTICNOM_TUDJE
		// = "161";
		// public static final String
		// NADDIO_OZNKANAD_OBAVEZE_PO_KARTICNOM_VLASTITE = "163";
		// public static final String
		// NADDIO_OZNKANAD_OSTALE_OBAVEZE_PO_BANKOVNIM_KARTICAMA = "164";
		// public static final String
		// NADDIO_OZNKANAD_NAKNADE_ZA_BANKARSKE_USLUGE_KARTICE = "165";
		// public static final String
		// NADDIO_OZNKANAD_USLUGE_PLATNOG_PROMETA_KARTICNO = "166";
		// public static final String
		// NADDIO_OZNKANAD_UNAPRIJED_PLACENI_TROSKOVI_KARTICE = "167";
		// public static final String
		// NADDIO_OZNKANAD_OSTALA_POTRAZIVANJA_PO_BANKOVNIM_KARTICAMA = "168";
		// public static final String NADDIO_OZNKANAD_TROSKOVI_VISA_NET = "169";
		// public static final String NADDIO_OZNKANAD_VISA_KARTICE_DRUGE_BANKE =
		// "170";
		// public static final String NADDIO_OZNKANAD_CAS_RACUN = "207"; //Mario
		// public static final String NADDIO_OZNKANAD_OBVEZE_POS_TERMINALI =
		// "177";
		// public static final String NADDIO_OZNKANAD_NAKNADE_KARTICNO = "178";
		// public static final String NADDIO_OZNKANAD_PORAVNANJE_VISA =
		// "K22431227";
		public static final String KART_TOTALI_VRPLATOT_1 = "1";
		public static final String KART_TOTALI_TIPLATOT_4 = "4";
		public static final String KART_TOTALI_TIPLATOT_5 = "5";
		public static final String KART_TOTALI_OBRADTOT_OBRADJEN = "1";
		public static final String KART_TOTALI_OBRADTOT_NEOBRADJEN = "0";
		public static final String KART_TOTALI_OBRADTOT_ZATVOREN = "2";

		// Datoteke
		public static String KART_DATOTEKA_PATH_IN_PARAM = "DAT_PATH_IN";
		public static String KART_DATOTEKA_PATH_OUT_PARAM = "DAT_PATH_OUT";
		public static String KART_DATOTEKA_PATH_ARH_PARAM = "DAT_PATH_ARH";
		public static String KART_DATOTEKA_TIP_DATOTEKE_ULAZNA = "U";
		public static String KART_DATOTEKA_TIP_DATOTEKE_IZLAZNA = "I";
		/**
		 * //Kartica public static String KART_KARTICA_OZNVL_OSNOVNA = "M";
		 * //Mandatory public static String KART_KARTICA_OZNVL_OSNOVNA_OPIS =
		 * "Osnovna kartica"; //Mandatory public static String
		 * KART_KARTICA_OZNVL_DODATNA = "S"; //Supplementary public static
		 * String KART_KARTICA_OZNVL_DODATNA_OPIS = "Dodatna kartica";
		 * //Supplementary public static Integer KART_KARTICA_SKRACENO_IME_MAX =
		 * 14; public static Integer KART_KARTICA_SKRACENO_PREZIME_MAX = 16;
		 * public static Integer KART_KARTICA_SKRACENO_FIRMA_MAX = 30;
		 *
		 * //Karticno banke public static Integer KART_BANKA_BAMCARD = 0; public
		 * static Integer KART_BANKA_BAMCARD_TIP_PARTIJE = 120; public static
		 * String KART_BANKA_BAMCARD_MBR = "4200602470001"; public static String
		 * KART_BANKA_UNION_MBR = "4200640130001";
		 *
		 * //Karticno prepaid public static String KART_PREPAID_IME =
		 * "ZA SVAKU"; public static String KART_PREPAID_PREZIME = "PRILIKU";
		 */
		// Kartica template
		public static String BOOLEAN_YES = "Y";
		public static String BOOLEAN_NO = "N";
		public static String KART_TEMPLATE_VRSTA_KARTICE_DEBITNA_KEY = "D";
		public static String KART_TEMPLATE_VRSTA_KARTICE_KREDITNA_KEY = "K";
		public static String KART_TEMPLATE_VRSTA_KARTICE_PREPAID_KEY = "P";
		public static String KART_TEMPLATE_VRSTA_KARTICE_DEBITNA_OPIS = "DEBITNA";
		public static String KART_TEMPLATE_VRSTA_KARTICE_KREDITNA_OPIS = "KREDITNA";
		public static String KART_TEMPLATE_VRSTA_KARTICE_PREPAID_OPIS = "PREPAID";
		public static String KART_TEMPLATE_VRSTA_VLASNISTVA_FIZICKA_KEY = "F";
		public static String KART_TEMPLATE_VRSTA_VLASNISTVA_PRAVNA_KEY = "P";
		public static String KART_TEMPLATE_VRSTA_VLASNISTVA_FIZICKA_OPIS = "STANOVNIŠTVO";
		public static String KART_TEMPLATE_VRSTA_VLASNISTVA_PRAVNA_OPIS = "GOSPODARSTVO";
		public static Integer KART_TEMPLATE_MIN_PAN_PREFIX = 6;
		/**
		 * //Karticni nalog public static String KART_NALOG_PREDZNAK_DEBIT =
		 * "D"; public static String KART_NALOG_PREDZNAK_CREDIT = "C"; public
		 * static String KART_NALOG_PREDZNAK_REVERSAL_DEBIT = "RD"; public
		 * static String KART_NALOG_PREDZNAK_REVERSAL_CREDIT = "RC";
		 */

		// Kartica izlaz
		public static String MAP_OZNAKA_STARA = "stara";
		public static String MAP_OZNAKA_NOVA = "nova";
		public static String KART_IZLAZ_OBRADKIZ_OBRADJENO = "1";
		public static String KART_IZLAZ_OBRADKIZ_NEOBRADJENO = "0";
		// Kartica slogovi
		public static Integer KART_SLOGOVI_BROJ_DANA_ZA_KONTROLU_HEADERA = 100;
		public static String KART_SLOGOVI_STATUS_UPISAN = "U";
		public static String KART_SLOGOVI_STATUS_OBRADJEN = "O";
		public static String KART_SLOGOVI_STATUS_GRESKA = "G";
		public static String KART_SLOGOVI_STATUS_RUCNO_POSTAVLJEN_NA_OBRADJEN = "X";
		public static String KART_SLOGOVI_STATUS_AUTOMATSKI_POSTAVLJEN_NA_OBRADJEN = "A";
		public static String KART_SLOGOVI_TIP_SLOGA_FILE_HEADER = "F";
		public static String KART_SLOGOVI_TIP_SLOGA_SLOG_HEADER = "H";
		public static String KART_SLOGOVI_TIP_SLOGA_SLOG = "B";
		public static String KART_SLOGOVI_TIP_SLOGA_SLOG_TRAILER = "T";
		public static String KART_SLOGOVI_TIP_SLOGA_FILE_TRAILER = "E";
		public static String KART_SLOGOVI_SLOGOVI_ID = "slog";
		public static String KART_SLOGOVI_TIP_PODATKA_NUMERIC = "numerička";
		public static String KART_SLOGOVI_TIP_PODATKA_DATE = "datumska";
		public static String KART_SLOGOVI_LOG_PERIOD_ID = "logging period";
		public static String KART_SLOGOVI_INCIDENT_ID = "incident";
		public static String KART_TERMINAL_TIP_BANKOMAT = "B";
		public static String KART_TERMINAL_TIP_POS = "P";
		public static String KART_TERMINAL_TRANSAKCIJA_SIFRA = "T";
		public static String KART_TERMINAL_PUNJENJE_PRAZNJENJE_SIFRA = "P";
		public static String KART_TERMINAL_TRANSAKCIJA_OPIS = "TRANSAKCIJE";
		public static String KART_TERMINAL_PUNJENJE_PRAZNJENJE_OPIS = "PUNJENJE/PRAŽNJENJE";
	}

	public static class UraIra {
		public static final String MESSAGE_BUNDLE = "hr.abit.b3.biz.uraira.message.urairaMessages";

		public static final String PRINTER_TEST = "http://193.0.0.27:8085";

		public static final Integer PROIZVOD_SIFRA_KIF_SEF = 723;
		public static final Integer PROIZVOD_SIFRA_KIF_OSATALO = 725;

		public static final String DEFAULT_ORGANIZACIJSKA_JEDINICA_FAKTURA = "999";

		public static final String TIP_VEZE_PLATNI_PROMET_USLUGE = "PPU";
		public static final String TIP_VEZE_PLATNI_PROMET_OSTALO = "PPO";
		public static final String TIP_VEZE_KREDIT = "KRE";
		public static final String TIP_VEZE_KREDIT_OPOMENA = "OPO";
		public static final String TIP_VEZE_KARTICNO = "KAR";
		public static final String TIP_VEZE_GARANCIJA = "GAR";
		public static final String TIP_VEZE_BRISOVNICA = "BRI";
		public static final String TIP_VEZE_AKREDITIVI = "AKR";
		public static final String TIP_VEZE_RETAIL = "AST";
		public static final String TIP_VEZE_RETAIL_ASD = "ASD";
		public static final String TIP_VEZE_KREDITI_OBRADA = "KRE";
		public static final String TIP_VEZE_KREDITI_RAZGRANICENJE = "RAZ";
		public static final String TIP_VEZE_KREDITI_SPORAZUM = "SPO";
		public static final String TIP_VEZE_KREDITI_TROSAK = "TRO";
		public static final String TIP_VEZE_KREDITI_OPOMENA = "OPO";
		public static final String TIP_VEZE_KREDITI_OSTALO = "OST";
		public static final String TIP_VEZE_KREDITI_BRISOVNO = "BRI";
		public static final String TIP_VEZE_KREDITI_NEPRIDRZAVANJE_UGOVORNE_OBAVEZE = "NEU";
		public static final String TIP_VEZE_KREDITI_POTRAŽIVANJA_SUDSKIH_POLOGA = "SUP";
		public static final String TIP_VEZE_KREDITI_IZRAVNO_ISKLJUCENI = "IIS";
		public static final String TIP_VEZE_KREDITI_NEIZRAVNO_ISKLJUCENI = "NIS";
		public static final String TIP_VEZE_SEF = "SEF";

		public static final String RACUN_NERASPOZNATI_PRILJEVI_KIFKUF = "1029990100000849";
		public static final String RACUN_INO_PLACANJE = "1020000000000023";
		public static final String STATUS_FAKTURE_UNESEN = "U";
		public static final String STATUS_FAKTURE_UNESEN_NAZIV = "Unesena";
		public static final String STATUS_FAKTURE_VERIFICIRAN_NAZIV = "Verificirana";
		public static final String STATUS_FAKTURE_VERIFICIRAN = "V";
		public static final String STATUS_FAKTURE_PLACEN_NAZIV = "Plaćena";
		public static final String STATUS_FAKTURE_PLACEN = "P";
		public static final String STATUS_FAKTURE_KNJIZEN_NAZIV = "Knjižena";
		public static final String STATUS_FAKTURE_KNJIZEN = "K";
		public static final String STATUS_FAKTURE_PLACEN_I_KNJIZEN = "PK";

		public static final String STATUS_FAKTURE_PLACEN_I_KNJIZEN_NAZIV = "Plaćena i knjižena";
		public static final String STATUS_FAKTURE_ZATVORENA = "Z";
		public static final String STATUS_FAKTURE_ZATVORENA_NAZIV = "Zatvorena";
		public static final String ULAZNA_FAKTURA = "U";
		public static final String IZLAZNA_FAKTURA = "I";
		public static final String RASKNJIZENJE_FAKTURA = "B";
		public static final String KNJIGA_ULAZNIH_FAKTURA = "URA";
		public static final String KNJIGA_IZLAZNIH_FAKTURA = "IRA";
		public static final String URAIRA_VBDI = "RA";
		public static final String URA_GRUPA_RACUNA = "05";
		public static final String IRA_GRUPA_RACUNA = "06";
		public static final List<String> LIST_IRA_GRUPA_RACUNA = Arrays.asList(IRA_GRUPA_RACUNA);
		public static final String POSAO_SIFRA_OSTALO = "32";
		public static final String TIP_PARTIJE_POSAO_NEREGISTROVANI = "01";
		public static final String TIP_PARTIJE_POSAO_REGISTROVANI = "02";
		public static final Integer TIP_PARTIJE_SIFRA_PREDRACUNSKO_PLACANJE = 4417;
		public static final Integer TIP_PARTIJE_SIFRA_AVANSI = 4418;
		public static final Integer TIP_PARTIJE_SIFRA_REDOVNA = 4420;
		public static final Integer TIP_PARTIJE_SIFRA_PREDRACUNSKO_PLACANJE_OB = 4421;
		public static final Integer TIP_PARTIJE_SIFRA_PREDRACUNSKO_PLACANJE_OS = 4422;
		public static final Integer TIP_PARTIJE_SIFRA_AVANSI_OB = 4423;
		public static final Integer TIP_PARTIJE_SIFRA_AVANSI_OS = 4424;
		public static final Integer TIP_PARTIJE_SIFRA_REDOVNA_OB = 4425;
		public static final Integer TIP_PARTIJE_SIFRA_REDOVNA_OS = 4426;
		public static final Integer TIP_PARTIJE_SIFRA_OSTALO = 46116;
		public static final Integer TIP_PARTIJE_SIFRA_RACUN_BANKE = 10;
		public static final Integer TIP_PARTIJE_SIFRA_FINA_FAKTURA = 1421;

		// Migracija
		public static final Integer TIP_PARTIJE_URA_MIGRACIJA = 80100;
		public static final Integer TIP_NALOGA_URA_RACUNA = 3;
		public static final Integer TIP_NALOGA_MIGRACIJA_IRA = 8;
		public static final Integer TIP_NALOGA_MIGRACIJA_URA = 10;
		public static final Integer TIP_NALOGA_MIGRACIJA_RAZGRANICENJE = 16;

		public static final String TIP_RASKNJIZENJA_PO_ROCNOSTI = "ROC";
		public static final String TIP_RASKNJIZENJA_PREPLATE = "PRE";
		public static final String TIP_RASKNJIZENJA_PREPLATE_PO_PARTIJI = "PPP";

		public static final Integer KLASA_PARTIJE_ULAZNA_FAKTURA = 80;
		public static final Integer KLASA_PARTIJE_IZLAZNA_FAKTURA = 81;

		public static final Integer IF_TIP_PARTIJE_KVAZI_AVANS = 1;
		public static final Integer IF_TIP_PARTIJE_AVANS = 2;
		public static final Integer IF_TIP_PARTIJE_REDOVNA = 3;
		public static final String VRDUG_NAD_TROSAK = "TS";
		public static final String VRDUG_NAD_AKTIVNA_VREMENSKA_RAZGRANICENJA = "AV";
		public static final String VRDUG_NAD_PASIVNA_VREMENSKA_RAZGRANICENJA = "PV";
		public static final String VRDUG_NAD_OSNOVNA_SREDSTVA = "OS";
		public static final String VRDUG_NAD_MATERIJALNI_TROSAK = "TB";
		public static final String VRDUG_NAD_AVR = "TV";
		public static final String VRDUG_NAD_PRIHOD = "PH";
		public static final String VRDUG_NAD_RAZGRANICENJE = "RZ";
		public static final Integer FAKTURA_BOOLEAN_NE = 0;
		public static final Integer FAKTURA_BOOLEAN_DA = 1;
		public static final String NAD_DIO_VRDUG_NAD_BRUTO = "BT";
		public static final String NAD_DIO_VRDUG_NAD_BRUTO_AVANS = "AV";
		public static final String NAD_DIO_VRDUG_NAD_TROSAK_OTPISA = "TO";
		public static final String NAD_DIO_VRDUG_NAD_ISPRAVKA_VRIJEDNOSTI = "IV";

		// public static final String NAD_DIO_OZNKA_NAD_BRUTO = "DO1";
		// public static final String NAD_DIO_OZNKA_NAD_AVANS = "AO1";
		// public static final String NAD_DIO_OZNKA_NAD_UKUPNI_POREZ = "PDVN";
		// public static final String NAD_DIO_OZNKA_NAD_PRIZNATI_POREZ = "PDVM";
		// public static final String NAD_DIO_OZNKA_NAD_UKUPNI_POREZ_GOTOVINA =
		// "UPG";
		// public static final String NAD_DIO_OZNKA_NAD_UKUPNI_POREZ_NEPOSLOVNE
		// = "UPE";

		public static final String NAD_DIO_VRDUG_NAD_POREZ_GOTOVINA = "PG";
		public static final String NAD_DIO_VRDUG_NAD_POREZ_NEPOSLOVNA = "PE";
		public static final String NAD_DIO_VRDUG_NAD_POREZ_REDOVNA = "PN";
		public static final String NAD_DIO_VRDUG_NAD_BRUTO_TROSAK = "BB";
		public static final String NAD_DIO_VRDUG_NAD_POREZ_MOZE = "PD";
		public static final String NAD_DIO_VRDUG_NAD_UPLATA = "UP";
		public static final String NAD_DIO_VRDUG_NAD_ISPLATA = "IP";
		public static final String NAD_DIO_VRDUG_NAD_KVAZI = "KV";
		public static final String NAD_DIO_OZNKA_NAD_UPLATA = "148";
		public static final String NAD_DIO_OZNKA_NAD_RAZGRANICENJE_UF = "068";
		public static final String NAD_DIO_OZNKA_NAD_TROSAK = "341";
		public static final String NAD_DIO_OZNKA_NAD_ISPLATA = "149";
		public static final String NAD_DIO_OZNKA_NAD_RAZGRANICENJE_IF = "069";
		public static final String NAD_DIO_OZNKA_NAD_PRIHOD = "241";
		public static final String NAD_DIO_OZNKA_NAD_REDOVNI_POREZ = "249";
		public static final String NAD_DIO_OZNKA_NAD_POREZ_MOZE = "348";
		public static final String NAD_DIO_OZNKA_NAD_REZERVACIJE_TROSKOVA = "723";
		public static final String NAD_DIO_REVAL_NAD_OSNOVNO_SREDSTVO = "OS";
		public static final String NAD_DIO_REVAL_NAD_OBRTNO_SREDSTVO = "OB";
		public static final String NAD_DIO_REVAL_NAD_SLUZBENI_PUT = "SP";
		public static final String NAD_DIO_OZNKA_NAD_BRUTO_UF = "041";
		public static final String NAD_DIO_OZNKA_NAD_BRUTO_SUMNJIVA_I_SPORNA_POTRAZIVANJA = "045";
		public static final String NAD_DIO_OZNKA_NAD_REZERVACIJE_NAKNADE = "700";
		public static final String NAD_DIO_OZNKA_NAD_POVECANJE_REZERVACIJE_NAKNADE = "816";
		public static final String NAD_DIO_OZNKA_NAD_SMANJENJE_REZERVACIJE_NAKNADE = "817";
		public static final String SIFRA_TIP_FAKTURE_REDOVNE = "01";
		public static final String SIFRA_TIP_FAKTURE_REDOVNE_INO = "IN01";
		public static final String SIFRA_TIP_FAKTURE_AVANSI = "02";
		public static final String SIFRA_TIP_FAKTURE_NEFAKTURIRANI = "03";
		public static final String SIFRA_TIP_FAKTURE_HRVATSKE_SUME = "04";
		public static final String SIFRA_TIP_FAKTURE_HRVAT_GOSPODARSKA_KOMORA = "05";
		public static final String SIFRA_TIP_FAKTURE_SPOMENICKA_RENTA = "06";
		public static final String SIFRA_TIP_FAKTURE_TROSAK_POREZA = "07";
		public static final String TIP_FAKTURE_REDOVNA = "REDOVNA FAKTURA";
		public static final String TIP_FAKTURE_REDOVNA_INO = "REDOVNA INO FAKTURA";
		public static final String TIP_FAKTURE_REDOVNA_NEFAKTURIRANI = "NEFAKTURIRANI TROŠKOVI";
		public static final String TIP_FAKTURE_AVANS = "AVANS";
		public static final String TIP_FAKTURE_HRVATSKE_SUME = "HRVATSKE ŠUME";
		public static final String TIP_FAKTURE_HRVAT_GOSPODARSKA_KOMORA = "HRVAT. GOSPODARSKA KOMORA";
		public static final String TIP_FAKTURE_SPOMENICKA_RENTA = "SPOMENIČKA RENTA";
		public static final String TIP_FAKTURE_TROSAK_POREZA = "TROŠAK POREZA";
		public static final Integer SIFRA_TIP_PARTIJE_IZLAZNA_FAKTURA_POREZ = 81100;
		public static final Integer SIFRA_TIP_PARTIJE_ULAZNA_FAKTURA_SITAN_INVENTAR = 80114;
		public static final Integer FAKTURA_TIP_POREZA_GOTOVINA = 1;
		public static final Integer FAKTURA_TIP_POREZA_NEPOSLOVNA = 2;
		public static final Integer FAKTURA_TIP_POREZA_REDOVNA = 3;
		public static final Integer FAKTURA_TIP_POREZA_RACUN = 4;
		public static final Integer FAKTURA_TIP_POREZA_GOTOVINA_NEPOSLOVNA = 5;
		public static final Integer FAKTURA_TIP_POREZA_RACUN_NEPOSLOVNA = 6;
		public static final Integer POREZ_SIFRA_POREZA_PDV_0 = 1;
		public static final Integer POREZ_SIFRA_POREZA_PDV_25 = 2;
		public static final Integer POREZ_SIFRA_POREZA_PDV_5 = 3;
		public static final Integer POREZ_SIFRA_POREZA_PDV_13 = 4;
		public static final Integer FAKTURA_POSAO_PREDRACUN = 1;
		public static final Integer FAKTURA_POSAO_AVANSNA = 2;
		public static final Integer FAKTURA_POSAO_KONACNA_PO_AVANSNOJ = 3;
		public static final Integer FAKTURA_POSAO_REDOVNA = 4;
		public static final Integer FAKTURA_POSAO_OSTALO = 5;
		public static final String FAKTURA_POSAO_PREDRACUN_OB = "01";
		public static final String FAKTURA_POSAO_PREDRACUN_OS = "02";
		public static final String FAKTURA_POSAO_AVANSI_OB = "02-OB";
		public static final String FAKTURA_POSAO_AVANSI_OS = "02-OS";
		public static final String FAKTURA_POSAO_REDOVNA_OB = "01-OB";
		public static final String FAKTURA_POSAO_REDOVNA_OS = "01-OS";
		public static final String FAKTURA_POSAO_NEFAKTURIRANI_OB = "03-OB";
		public static final String FAKTURA_POSAO_NEFAKTURIRANI_OS = "03-OS";
		public static final String FAKTURA_POSAO_HRVATSKE_SUME_OB = "04-OB";
		public static final String FAKTURA_POSAO_HRVAT_GOSPODARSKA_KOMORA_OB = "05-OB";
		public static final String FAKTURA_POSAO_SPOMENICKA_RENTA_OB = "06-OB";
		public static final String FAKTURA_POSAO_TROSAK_POREZA_OB = "07-OB";
		public static final Integer PLACANJE_ULAZNE_FAKTURE_PREDRACUN = 1;
		public static final Integer PLACANJE_ULAZNE_FAKTURE_AVANS = 2;
		public static final Integer PLACANJE_ULAZNE_FAKTURE_INO = 3;
		public static final Integer PLACANJE_ULAZNE_FAKTURE_GOTOVINA = 4;
		public static final Integer PLACANJE_ULAZNE_FAKTURE_TRANSAKCIJASKI = 5;
		public static final String FAKTURA_STATUS_PLACANJA_PLACEN = "P";
		public static final String STAVKA_REGISTRIRANI = "RE";
		public static final String STAVKA_NEREGISTRIRANI = "NE";
		public static final Integer STAVKA_IRA_FAKTURE_ARTIKL_NE = 0;
		public static final Integer STAVKA_IRA_FAKTURE_ARTIKL_DA = 1;
		public static final String STAVKA_TIP_ZA_RASKNJIZENJE_SLUZBENI_PUT = "KT916111";
		public static final Integer NACIN_PLACANJA_TRANSAKCIJASKI = 1;
		public static final Integer NACIN_PLACANJA_GOTOVINA = 2;

		public static final Integer VRIJEME_REKLAMACIJE_NAPLATE_NAKNADE = 8;

		// public static final String PLACANJE_KONTO_PARTIJA =
		// "1029999900176047";
		public static final String PLACANJE_KONTO_OZNAKA_NADDIO = "PGUF"; // konto
		// 28761125
		// public static final String PLACANJE_INO_OZNAKA_NADDIO = "UPIFA";
		// //konto 534012027
		public static final String PLACANJE_INO_OZNAKA_NADDIO = "300"; // konto
		// 534012027
		// tipovi i klase naloga
		public static final Integer TIPNALOGA_SIFRAPOD_KNJIZENJE_FAKTURE_ULAZNA = 6001;
		public static final Integer TIPNALOGA_SIFRAPOD_PLACANJE_FAKTURE_ULAZNA = 6002;
		public static final Integer TIPNALOGA_SIFRAPOD_REZERVACIJE_FAKTURE_ULAZNA = 6003;
		public static final Integer TIPNALOGA_SIFRAPOD_RASKNJIZENJE_FAKTURE_ULAZNA = 6100;
		public static final Integer TIPNALOGA_SIFRAPOD_KNJIZENJE_FAKTURE_IZLAZNA = 6201;
		public static final Integer TIPNALOGA_SIFRAPOD_PLACANJE_FAKTURE_IZLAZNA = 6202;
		public static final Integer TIPNALOGA_SIFRAPOD_POVRAT_FAKTURE_IZLAZNA = 6203;
		public static final Integer TIPNALOGA_SIFRAPOD_RASKNJIZENJE_FAKTURE_IZLAZNA = 6300;
		public static final Integer TIPNALOGA_SIFRAPOD_RASKNJIZENJE_PREPLATE = 6121;
		public static final Integer KLASANALOGA_SIFRAKNA_ULAZNA_FAKTURA = 6000;
		public static final Integer KLASANALOGA_SIFRAKNA_FAKTURE_ULAZNA = 6001;
		public static final Integer KLASANALOGA_SIFRAKNA_IZLAZNA_FAKTURA = 6200;
		public static final Integer KLASANALOGA_SIFRAKNA_FAKTURE_IZLAZNA = 6201;
		public static final String KLASANALOGA_GRUPAKNA_URAIRA = "URAIRA";
		public static final String KLASA_NALOGA_GRUPA_OPCI_DEVIZNI_NALOG_URAIRA = "ODNUI";
		public static final Integer TIP_NALOGA_PRAZNI_OPCI_DEVIZNI_URAIRA = 40000;
		public static final Integer TIP_NALOGA_KNJIZENJE_UNATRAG_URAIRA = 4444;

		// Razgranicenje obrada
		public static final Integer TIPNALOGA_SIFRAPOD_RASKNJIZENJE_FAKTURA_GRUPNI_NALOG = 6400;
		public static final Integer TIPNALOGA_SIFRAPOD_RASKNJIZENJE_FAKTURA_URA = 6401;
		public static final Integer TIPNALOGA_SIFRAPOD_RASKNJIZENJE_FAKTURA_IRA = 6402;

		// Pdv obrada
		public static final Integer TIPNALOGA_SIFRAPOD_PDV_OBRADA_ZBIRNI_NALOG = 6410;
		public static final Integer TIPNALOGA_SIFRAPOD_PDV_OBRADA = 6411;
		public static final Integer KLASANALOGA_PDV_OBRADA = 6401;
		public static final String MINISTARSTVO_FINANCIJA_MATBR = "03205991";

		public static final Integer KLASANALOGA_SIFRAKNA_FAKTURE_ULAZNA_RAZGRANICENJA = 6100;
		public static final Integer KLASANALOGA_SIFRAKNA_FAKTURE_IZLAZNA_RAZGRANICENJA = 6300;

		public static final Integer KLASANALOGA_SIFRAKNA_FAKTURE_ULAZNA_REZERVACIJE = 6003;

		public static final Integer KLASANALOGA_SIFRAKNA_RASKNJIZENJE_KIF_FAKTURA = 6110;
		public static final Integer KLASANALOGA_SIFRAKNA_RASKNJIZENJE_PREPLATE = 6120;

		public static final Integer KLASANALOGA_OBRACUN_NAKNADE_RAZGRANICENJE = 1003;
		public static final Integer KLASANALOGA_OBRACUN_NAKNADE_URAIRA = 1005;

		// Obavijest veze
		public static final Integer TIPNALOGA_SIFRAPOD_FORMIRANJE_FAKTURA_ISPIS = 6500;
		public static final Integer OBAVIJEST_VEZA_FAKTURA_POSLOVNICA = 80;
		public static final Integer OBAVIJEST_VEZA_FAKTURA_POSTA = 81;

		// Otpis naknada po računima
		public static final Integer TIPNALOGA_SIFRAPOD_OTPIS_NAKNADA_OBRADA_ZBIRNI_NALOG = 6600;
		public static final Integer TIPNALOGA_SIFRAPOD_OTPIS_NAKNADA_OBRADA = 6601;
		public static final Integer KLASANALOGA_OTPIS_NAKNADA_OBRADA = 6601;

		// Rezervacije
		public static final Integer TIPNALOGA_SIFRAPOD_REZERVACIJE_NAKNADE_OBRADA_ZBIRNI_NALOG = 6700;
		public static final Integer TIPNALOGA_SIFRAPOD_POVECANJE_REZERVACIJE_NAKNADE_OBRADA = 6701;
		public static final Integer TIPNALOGA_SIFRAPOD_SMANJENJE_REZERVACIJE_NAKNADE_OBRADA = 6702;
		public static final Integer KLASANALOGA_POVECANJE_REZERVACIJE_NAKNADE_OBRADA = 6701;
		public static final Integer KLASANALOGA_SMANJENJE_REZERVACIJE_NAKNADE_OBRADA = 6702;

		public static final String PROIZVOD_KVAZI_SEF = "S";
		public static final String PROIZVOD_KVAZI_ZAKUPNINA = "Z";
		public static final String PROIZVOD_KVAZI_OSTALO = "O";
		public static final Integer FAKTURA_TIP_IF_KVAZI_AVANS = 1;
		public static final Integer FAKTURA_TIP_IF_AVANS = 2;
		public static final Integer FAKTURA_TIP_IF_REDOVNA = 3;
		public static final String POTRAZIVANJA_OPOREZIVA_PDV_NEMA_KASNJENA_U_NAPLATI_ZAKUP = "IFBT1";
		public static final String POTRAZIVANJA_OPOREZIVA_PDV_KASNI_DO_30_DANA_ZAKUP = "IFBT11";
		public static final String POTRAZIVANJA_OPOREZIVA_PDV_KASNI_DO_31_90_DANA_ZAKUP = "IFBT111";
		public static final String POTRAZIVANJA_OPOREZIVA_PDV_KASNI_DO_91_180_DANA_ZAKUP = "IFBT1111";
		public static final String POTRAZIVANJA_OPOREZIVA_PDV_KASNI_DO_181_270_DANA_ZAKUP = "IFBT11111";
		public static final String POTRAZIVANJA_OPOREZIVA_PDV_KASNI_DO_271_GODINA_DANA_ZAKUP = "IFBT111111";
		public static final String POTRAZIVANJA_OPOREZIVA_PDV_PREKO_GODINU_DANA_ZAKUP = "IFBT1111111";
		public static final String POTRAZIVANJA_OPOREZIVA_PDV_NEMA_KASNJENA_U_NAPLATI_SEFOVI_I_OSTAVE = "IFBT2";
		public static final String POTRAZIVANJA_OPOREZIVA_PDV_KASNI_DO_30_DANA_SEFOVI_I_OSTAVE = "IFBT21";
		public static final String POTRAZIVANJA_OPOREZIVA_PDV_KASNI_DO_31_90_SEFOVI_I_OSTAVE = "IFBT211";
		public static final String POTRAZIVANJA_OPOREZIVA_PDV_KASNI_DO_91_180_SEFOVI_I_OSTAVE = "IFBT2111";
		public static final String POTRAZIVANJA_OPOREZIVA_PDV_KASNI_DO_181_270_SEFOVI_I_OSTAVE = "IFBT21111";
		public static final String POTRAZIVANJA_OPOREZIVA_PDV_KASNI_DO_271_GODINA_SEFOVI_I_OSTAVE = "IFBT211111";
		public static final String POTRAZIVANJA_OPOREZIVA_PDV_PREKO_GODINU_DANA_SEFOVI_I_OSTAVE = "IFBT2111111";
		public static final String POTRAZIVANJA_OPOREZIVA_PDV_NEMA_KASNJENA_U_NAPLATI_OSTALO = "IFBT3";
		public static final String POTRAZIVANJA_OPOREZIVA_PDV_KASNI_DO_30_DANA_OSTALO = "IFBT31";
		public static final String POTRAZIVANJA_OPOREZIVA_PDV_KASNI_DO_31_90_OSTALO = "IFBT311";
		public static final String POTRAZIVANJA_OPOREZIVA_PDV_KASNI_DO_91_180_OSTALO = "IFBT3111";
		public static final String POTRAZIVANJA_OPOREZIVA_PDV_KASNI_DO_181_270_OSTALO = "IFBT31111";
		public static final String POTRAZIVANJA_OPOREZIVA_PDV_KASNI_DO_271_GODINA_OSTALO = "IFBT311111";
		public static final String POTRAZIVANJA_OPOREZIVA_PDV_PREKO_GODINU_DANA_OSTALO = "IFBT3111111";
		public static final String NEMA_KASNJENA_U_NAPLATI_NAKNADA_IPP = "IFBT5";
		public static final String KASNI_DO_30_DANA_NAKNADA_IPP = "IFBT51";
		public static final String KASNI_DO_31_90_NAKNADA_IPP = "IFBT511";
		public static final String KASNI_DO_91_180_NAKNADA_IPP = "IFBT5111";
		public static final String KASNI_DO_181_270_NAKNADA_IPP = "IFBT51111";
		public static final String KASNI_DO_271_GODINA_NAKNADA_IPP = "IFBT511111";
		public static final String KASNI_PREKO_GODINU_DANA_NAKNADA_IPP = "IFBT5111111";
		public static final String POTRAZIVANJA_OPOREZIVA_PDV_NEMA_KASNJENA_U_NAPLATI_STANOVNISTVO = "IFBT6";
		public static final String POTRAZIVANJA_OPOREZIVA_PDV_KASNI_DO_30_DANA_STANOVNISTVO = "IFBT61";
		public static final String POTRAZIVANJA_OPOREZIVA_PDV_KASNI_DO_31_90_STANOVNISTVO = "IFBT611";
		public static final String POTRAZIVANJA_OPOREZIVA_PDV_KASNI_DO_91_180_STANOVNISTVO = "IFBT6111";
		public static final String POTRAZIVANJA_OPOREZIVA_PDV_KASNI_DO_181_270_STANOVNISTVO = "IFBT61111";
		public static final String POTRAZIVANJA_OPOREZIVA_PDV_KASNI_DO_271_GODINA_STANOVNISTVO = "IFBT611111";
		public static final String POTRAZIVANJA_OPOREZIVA_PDV_PREKO_GODINU_DANA_STANOVNISTVO = "IFBT6111111";
		public static final String NEMA_KASNJENA_U_NAPLATI_PROVIZIJA_UPP = "IFBT7";
		public static final String KASNI_DO_30_DANA_PROVIZIJA_UPP = "IFBT71";
		public static final String KASNI_DO_31_90_PROVIZIJA_UPP = "IFBT711";
		public static final String KASNI_DO_91_180_PROVIZIJA_UPP = "IFBT7111";
		public static final String KASNI_DO_181_270_PROVIZIJA_UPP = "IFBT71111";
		public static final String KASNI_DO_271_GODINA_PROVIZIJA_UPP = "IFBT711111";
		public static final String KASNI_PREKO_GODINU_DANA_PROVIZIJA_UPP = "IFBT7111111";
		public static final String PROVIZIJA_UPP_PROMPTNO_NAPLECNO = "IFBT72";
		public static final String NEMA_KASNJENA_U_NAPLATI_NAKNADA_UPP = "IFBT73";
		public static final String KASNI_DO_30_DANA_NAKNADA_UPP = "IFBT731";
		public static final String KASNI_DO_31_90_NAKNADA_UPP = "IFBT7311";
		public static final String KASNI_DO_91_180_NAKNADA_UPP = "IFBT73111";
		public static final String KASNI_DO_181_270_NAKNADA_UPP = "IFBT731111";
		public static final String KASNI_DO_271_GODINA_NAKNADA_UPP = "IFBT7311111";
		public static final String KASNI_PREKO_GODINU_DANA_NAKNADA_UPP = "IFBT73111111";
		public static final String BTO_189_PROMPTNO = "IFBT74";
		public static final String NAKNADE_OSTALA_POTRAZIVANJA_287X = "IFBT8";
		public static final String STANOVNISTVO_182_BRUTO = "IFBT999111";
		public static final String AVANS_KONACNA_BRUTO_ZAKUP = "IFKO1";
		public static final String AVANS_KONACNA_BRUTO_SEFOVI = "IFKO2";
		public static final String AVANS_KONACNA_BRUTO_OSTALO = "IFKO3";
		public static final String REDIRECT_ULAZNA_FAKTURA = "/uraira/abit/fakture/ulaznaFaktura?faces-redirect=true";
		public static final String REDIRECT_STAVKE_URA_FAKTURE = "/uraira/abit/stavke/stavkeUraFakture?faces-redirect=true&id=";
		public static final String REDIRECT_STAVKE_URA_INO_FAKTURE = "/uraira/abit/stavke/stavkeUraInoFakture?faces-redirect=true&id=";
		public static final String REDIRECT_PLACANJE_UFAKTURE_GOTOVINA = "/uraira/abit/placanje/placanjeUFaktureGotovina?faces-redirect=true&id=";
		public static final String REDIRECT_PLACANJE_UFAKTURE_RACUN = "/uraira/abit/placanje/placanjeUFaktureRacun?faces-redirect=true&id=";
		public static final String REDIRECT_PLACANJE_UFAKTURE = "/uraira/abit/placanje/placanjeUFakture?faces-redirect=true&id=";
		public static final String REDIRECT_IZLAZNA_FAKTURA = "/uraira/abit/fakture/izlaznaFaktura?faces-redirect=true";
		public static final String REDIRECT_STAVKE_IRA_FAKTURE = "/uraira/abit/stavke/stavkeIraFakture?faces-redirect=true&id=";
		public static final String NAD_DIO_OZNKA_NAD_INO_BT = "BTREINO";
		public static final String NAD_DIO_OZNKA_NAD_INO_PD = "PDREINO";
		public static final String NAD_DIO_OZNKA_NAD_INO_PN = "PNREINO";
		public static final Integer TIPNALOGA_SIFRAPOD_RASKNJIZENJE_FAKTURA = 6101;
		public static final Integer TIPNALOGA_SIFRAPOD_RASKNJIZENJE_KIF_FAKTURA = 6111;
		public static final Integer KLASANALOGA_SIFRAKNA_RASKNJIZENJE_FAKTURA = 6100;
		public static final String ISPIS_VRSTA_FAKTURE_MJESECNE = "MJESECNE";
		public static final String ISPIS_VRSTA_FAKTURE_POJEDINACNE = "POJEDINACNE";
		public static final String ISPIS_VRSTA_FAKTURE_SVE = "SVE";
		public static final String ISPIS_FAKTURE_ORDER_BY_PARTIJA = "PARTIJA";
		public static final String ISPIS_FAKTURE_ORDER_BY_KOMITENT = "KOMITENT";
		public static final String ISPIS_FAKTURE_ORDER_BY_PTTBR = "PTTBR";
		public static final String ISPIS_FAKTURE_ORDER_BY_DATUM_IZDAVANJA = "DAT_IZD";
		public static final String ISPIS_FAKTURE_KANAL_POSTA = "POSTA";
		public static final String ISPIS_FAKTURE_KANAL_SALTER = "SALTER";
		public static final String ISPIS_FAKTURE_KANAL_TELEFAKS = "TELEFAKS";
		public static final String ISPIS_FAKTURE_KANAL_FINA = "FINA";
		public static final String ISPIS_FAKTURE_KANAL_NE_PRIMA = "NE_PRIMA";
		public static final String ISPIS_FAKTURE_KANAL_BANKA = "BANKA";
		public static Integer SIFRA_RACUNA_NERASPOZNATI_PRILJEVI_KIFKUF = 10;
		public static Integer TIP_RACUNA_NERASPOZNATI_PRILJEVI_KIFKUF = 38;
		public static Integer REZERVACIJE_NAKNADE_90_DANA = 90;

		public static final String REPRTREP_IZVJESCEONAKNADAMA = "IzvjesceONaknadama";
		public static final String REPRTREP_IZVJESCEONAKNADAMA1 = "IzvjesceONaknadama1";
		public static final String REPRTREP_IZVJESCEONAKNADAMA11 = "IzvjesceONaknadama11";
		public static final String REPRTREP_IZVJESCEONAKNADAMA2 = "IzvjesceONaknadama2";
		public static final String REPRTREP_IZVJESCEONAKNADAMA3 = "IzvjesceONaknadama3";
		public static final String REPRTREP_IZVJESCEONAKNADAMA4 = "IzvjesceONaknadama4";
		public static final String REPRTREP_IZVJESCEONAKNADAMA5 = "IzvjesceONaknadama5";

	}

	public static class Ostalo {
		public static final String MESSAGE_BUNDLE = "hr.abit.b3.biz.ostalo.message.ostaloMessages";

		public static final String PARTIJA_GRUPA_RACUNA_OSTALO = "99";
		// tip partije
		public static final Integer TIP_PARTIJE_SIFRA_PRIJELAZNI_RACUN_KLIJENTA = 1300;
		public static final Integer TIP_PARTIJE_SIFRA_OSTALO_NERASPOREDENO = 1305;
		public static final Integer TIP_PARTIJE_SIFRA_OSTALO_ABIT_INTERFACE = 1306;
		public static final Integer TIP_PARTIJE_SIFRA_OSTALO_PASIVNI_PODBALANS = 1381;
		public static final Integer TIP_PARTIJE_SIFRA_OSTALO_NEUTRALNI_PODBALANS = 1382;
		public static final Integer TIP_PARTIJE_SIFRA_OSTALO_MATERIJALNO_KNJIGOVODSTVO = 1383;
		public static final Integer TIP_PARTIJE_SIFRA_OSTALO_PLATE_I_NAKNADE_PLATA = 1384;
		public static final Integer TIP_PARTIJE_SIFRA_OSTALO_OSTALA_PRIMANJA_ZAPOSLENIH = 1385;
		public static final Integer TIP_PARTIJE_SIFRA_OSTALO_SLUZBENI_PUT_ZAPOSLENIKA = 1386;
		public static final Integer TIP_PARTIJE_SIFRA_OSTALO_REZERVISANJA_MRS19 = 1387;
		public static final Integer TIP_PARTIJE_SIFRA_OSTALO_REZERVISANJA_TUZBE_MRS37 = 1388;
		public static final Integer TIP_PARTIJE_SIFRA_OSTALO_ZATVARANJE_KLASE_9 = 1389;
		public static final Integer TIP_PARTIJE_SIFRA_OSTALO_POREZ_NA_DOBIT = 1390;
		public static final Integer TIP_PARTIJE_SIFRA_OSTALO_OBVEZE_PO_OSTALIM_JAVNIM_PRIHODIMA = 1391;
		public static final Integer TIP_PARTIJE_SIFRA_OSTALO_UGOVORI_O_DJELU_I_HONORARI = 1392;
		public static final Integer TIP_PARTIJE_SIFRA_OSTALO_NAKNADE_ODBORIMA_BANKE = 1393;
		public static final Integer TIP_PARTIJE_SIFRA_OSTALO_POTRAZIVANJA_OD_OSIGURANJA = 1394;
		public static final Integer TIP_PARTIJE_SIFRA_OSTALO_KAPITAL_REZERVE = 1395;
		public static final Integer TIP_PARTIJE_SIFRA_OSTALO_KAPITAL_DIONICE = 1396;
		public static final Integer TIP_PARTIJE_SIFRA_OSTALO_VRIJDNOSNI_PAPIRI = 1397;

		public static final Integer TIP_PARTIJE_SIFRA_RACUN_BANKE = 10;

		// nad-dio
		public static final String NAD_DIO_OZNKA_OSTALO_BEGINING = "K";

		// tipovi naloga i klase naloga
		public static final Integer KLASANALOGA_SIFRAKNA_INTERNI_OSTALO_NALOG = 9001;
		public static final Integer TIPNALOGA_SIFRAPOD_OSTALO_INTERNI_NALOG = 9001;
		public static final Integer TIPNALOGA_SIFRAPOD_OSTALO_INTERNI_RETAIL = 9002;
		public static final Integer TIPNALOGA_SIFRAPOD_OSTALO_PLATNI = 9004;
		public static final Integer KLASANALOGA_SIFRAKNA_SPISKOVI = 9002;
		public static final Integer TIP_NALOGA_SIFRAPOD_OSTALO_OSTALO_SPISAK = 9006;
		public static final Integer TIP_NALOGA_SIFRAPOD_OSTALO_OSTALO_SPISAK_MATERIJALNO = 9007;
		public static final Integer TIP_NALOGA_SIFRAPOD_OSTALO_OSTALO_SPISAK_POREZ = 9008;
		public static final Integer TIP_NALOGA_MIGRACIJA_PARTIJA_OSTALO = 9009;
		public static final Integer TIP_NALOGA_IZMJENI_PARTIJA_OSTALO = 9011;
		public static final Integer KLASANALOGA_SIFRAKNA_OSTALO_PLATNI_PRIJENOS = 9004;

		public static final String NADDIO_OSTALO_PLATNI_PLASMAN_PROLAZNI_RACUN = "148";
		public static final String NADDIO_OSTALO_PLATNI_UPLATE_PROLAZNI_RACUN = "149";

		public static final String NALOGOSTALO_SMJER_DUGOVNO = "D";
		public static final String NALOGOSTALO_SMJER_POTRAZNO = "P";
		public static final String STATUS_TABLE_NALOG_OSTALO = "NalogOstalo";
		public static final String NALOGOSTALO_SMJERNRT_DUGOVNO = "D";
		public static final String NALOGOSTALO_SMJERNRT_POTRAZNO = "P";
		public static final String NALOGOSTALO_PROMET_POTRAZUJE = "P";
		public static final String NALOGOSTALO_PROMET_DUGUJE = "D";
		public static final String NALOGOSTALO_PROMET_POTRAZUJE_STORNO = "PS";
		public static final String NALOGOSTALO_PROMET_DUGUJE_STORNO = "DS";

		public static final String PROMET_EXTERNAL_STATUS_UPISANO = "0";
		public static final String PROMET_EXTERNAL_STATUS_ISPLACENO = "7";
		public static final String PROMET_EXTERNAL_STATUS_DUGOVNO = "8";

		// spisak
		public static final Integer SPISAK_VRSTA_AKCIJE_UPLATE = 1;
		public static final Integer SPISAK_VRSTA_AKCIJE_OBUSTAVE = 2;
		public static final Integer SPISAK_VRSTA_AKCIJE_STALNA_SREDSTVA = 3;
		public static final Integer SPISAK_VRSTA_AKCIJE_LICNA_PRIMANJA = 4;
		public static final Integer SPISAK_VRSTA_AKCIJE_MATERIJALNO = 5;
		public static final Integer SPISAK_VRSTA_AKCIJE_POVRAT_POREZA = 6;

		public static final String SPISAK_AKCIJE_STATUS_ZA_UCITAVANJE = "3";
		public static final String SPISAK_AKCIJE_STATUS_UCITANO_DJELOMICNO = "6";
		public static final String SPISAK_AKCIJE_STATUS_UCITANO_POTPUNO = "7";
		public static final String SPISAK_AKCIJE_STATUS_ODBIJENO_NEISPRAVNO = "8";
	}

	public static class Ledger {

		public static final String MESSAGE_BUNDLE = "hr.abit.b3.biz.ledger.message.ledgerMessages";

		public static final Integer KLASANALOGA_SIFRAKNA_LEDGER = 9;
		public static final Integer KLASANALOGA_SIFRAKNA_LEDGER_TEMELJNICA = 10;
		public static final Integer KLASANALOGA_SIFRAKNA_LEDGER_TECAJNE_RAZLIKE_ZBIRNI = 510;
		public static final Integer KLASANALOGA_SIFRAKNA_LEDGER_TECAJNE_RAZLIKE = 511;

		public static final String KONTO_PREFIKS_VANBILANCA = "99";

		public static final Integer TIPNALOGA_SIFRAPOD_LEDGER_TEMELJNICA = 1;
		public static final Integer TIPNALOGA_SIFRAPOD_LEDGER_ZBIRNI_OBRACUN_TECAJNIH_RAZLIKA = 51;
		public static final Integer TIPNALOGA_SIFRAPOD_LEDGER_OBRACUN_TECAJNIH_RAZLIKA = 52;

	}

	public static class Trajni {
		public static final String MESSAGE_BUNDLE = "hr.abit.b3.biz.trajni.message.trajniMessages";
		public static final String TRAJNI_NALOG_GRUPA_RACUNA = "66";
		public static final String TRAJNI_NALOG_VRSTA_BESKONACNI = "1";
		//public static final String TRAJNI_NALOG_VRSTA_KONAČNI = "2";
		public static final Integer KLASAPARTIJE_SIFRAKLP_TRAJNI = 30;
		public static final Integer TIPPARTIJE_SIFRATIP_TRAJNI_NALOG_INTERNI = 3001;
		public static final Integer TIPPARTIJE_SIFRATIP_TRAJNI_NALOG_EKSTERNI = 3002;
		public static final Integer TIPPARTIJE_SIFRATIP_TRAJNI_NALOG_EKSTERNI_PROMJENJIVI = 3003;
		public static final Integer TIPPARTIJE_SIFRATIP_TRAJNI_NALOG_EKSTERNI_S_OROCENJA = 3005;
		public static final Integer TIPPARTIJE_SIFRATIP_TRAJNI_NALOG_INTERNI_PROMJENJIVI = 3004;

		public static final Integer KLASANALOGA_SIFRAKNA_TRAJNI = 111;//1700;
		public static final Integer KLASANALOGA_SIFRAKNA_TRAJNI_PROMJENJIVI = 113;
		public static final Integer TIPNALOGA_SIFRAPOD_TRAJNI_OBRADA_ZBIRNI = 14152;
		public static final Integer TIPNALOGA_SIFRAPOD_TRAJNI_OBRADA_ZBIRNI_HRT = 14149;
		public static final Integer TIPNALOGA_SIFRAPOD_TRAJNI_OBRADA_ZBIRNI_HT = 14153;
		public static final Integer TIPNALOGA_SIFRAPOD_TRAJNI_OTVORENI_DATOTEKA_HT = 14275;
		public static final Integer TIPNALOGA_SIFRAPOD_TRAJNI_UCITAVANJE_DATOTEKA_HT = 14271;
		public static final Integer TIPNALOGA_SIFRAPOD_TRAJNI_FORMALNA_DATOTEKA_HT = 14272;
		public static final Integer TIPNALOGA_SIFRAPOD_TRAJNI_OTVORENI_DATOTEKA_HRT = 14270;
		public static final Integer TIPNALOGA_SIFRAPOD_TRAJNI_UCITAVANJE_DATOTEKA_HRT = 14156;
		public static final Integer TIPNALOGA_SIFRAPOD_TRAJNI_FORMALNA_DATOTEKA_HRT = 14157;
		public static final Integer TIPNALOGA_SIFRAPOD_TRAJNI_OBRADA_INTERNI = 1701;
		public static final Integer TIPNALOGA_SIFRAPOD_TRAJNI_OBRADA_EXTERNI = 1702;
		public static final Integer TIPNALOGA_SIFRAPOD_TRAJNI_OBRADA_INTERNI_S_NAKNADOM = 1704;
		public static final Integer TIPNALOGA_SIFRAPOD_TRAJNI_OBRADA_EXTERNI_S_NAKNADOM = 1703;
		public static final Integer TIP_NALOGA_SIFRA_PROMJENA_TRAJNOG_NALOGA = 14162;
		public static final Integer TIPNALOGA_SIFRAPOD_TRAJNI_NAKNADA_ZA_IZVRSENJE_TRAJNOG_NALOGA = 14277;

		//	public static final String TRAJNI_NALOG_GRUPA_RACUNA = "66";
		//	public static final String TRAJNI_NALOG_VRSTA_BESKONACNI = "1";
		public static final String TRAJNI_NALOG_VRSTA_KONACNI = "2";
		public static final String TRAJNI_NALOG_VRSTA_PO_PRILJEVU_BESKONACNI = "3";
		public static final String TRAJNI_NALOG_VRSTA_HRT = "4";
		public static final String TRAJNI_NALOG_VRSTA_HT = "5";
		public static final String TRAJNI_NALOG_VRSTA_KAMATA = "6";
		public static final String TRAJNI_NALOG_VRSTA_PROMJENJIVI = "7";
		public static final Integer PROIZVOD_SIFRAPRO_TRAJNI_INTERNI_BEZ_NAKNADE = 430;
		public static final Integer PROIZVOD_SIFRAPRO_TRAJNI_INTERNI_S_NAKNADOM = 431;
		public static final Integer PROIZVOD_SIFRAPRO_TRAJNI_EXTERNI_BEZ_NAKNADE = 432;
		public static final Integer PROIZVOD_SIFRAPRO_TRAJNI_EXTERNI_S_NAKNADOM = 433;
		public static final String TRAJNI_NALOG_STATUS_NEVERIFICIRAN = "U";

		public static final String PARAMETAR_SIFRAPAR_PUTANJA_IZLAZNA_HRT_DATOTEKA = "TNHrtIzlaz";
		public static final String PARAMETAR_SIFRAPAR_PUTANJA_IZLAZNA_HT_DATOTEKA = "TNHtIzlaz";
		public static final String PARAMETAR_SIFRAPAR_PUTANJA_ULAZNA_HRT_DATOTEKA = "TNHrtUlaz";
		public static final String PARAMETAR_SIFRAPAR_PUTANJA_ULAZNA_HT_DATOTEKA = "TNHtUlaz";
		public static final String PARAMETAR_SIFRAPAR_PUTANJA_IZLAZNA_HRT_DATOTEKA_IZVRSENI_NEIZVRSENI = "TNHrtIzvrs";
		public static final String PARAMETAR_SIFRAPAR_PUTANJA_IZLAZNA_HT_DATOTEKA_IZVRSENI_NEIZVRSENI = "TNHtIzvrs";
		public static final String MATBRKOM_HT = "01414887";

	}

	public static class IzvodBanke {

		// messages
		public static final String MESSAGE_BUNDLE = "hr.abit.b3.biz.izvodBanke.message.izvodBankeMessages";

		// klasa naloga
		public static final Integer KLASA_NALOGA_SIFRAKNA_IZVOD_BANKE_INTERNI = 380;
		public static final Integer KLASA_NALOGA_SIFRAKNA_IZVOD_BANKE_EKSTERNI = 390;

		public static final String KLASA_NALOGA_GRUPAKNA_INTERNI = "KNIBINT";
		public static final String KLASA_NALOGA_GRUPAKNA_EKSTERNI = "KNIBEXT";

		// tip naloga
		public static final Integer TIP_NALOGA_SIFRAKNA_IZVOD_BANKE_INTERNI_DOMICILNI = 38000;
		public static final Integer TIP_NALOGA_SIFRAKNA_IZVOD_BANKE_INTERNI_DEVIZNI = 38010;
		public static final Integer TIP_NALOGA_SIFRAKNA_IZVOD_BANKE_EKSTERNI_NKS = 39000;
		public static final Integer TIP_NALOGA_SIFRAKNA_IZVOD_BANKE_EKSTERNI_HSVP = 39010;
		public static final Integer TIP_NALOGA_SIFRAKNA_IZVOD_BANKE_EKSTERNI_EURO_NKS = 39020;
		public static final Integer TIP_NALOGA_SIFRAKNA_IZVOD_BANKE_EKSTERNI_TARGET2 = 39030;
		public static final Integer TIP_NALOGA_SIFRAKNA_IZVOD_BANKE_EKSTERNI_SWIFT = 39040;

	}

	public static class OsobnaPrimanja {

		public static final String OP_PARAM_PREFIX = "OP";
		public static final String OP_DIRECTORY = "/data/OsobnaPrimanja/";
		public static final String OP_DIRECTORY_ARCHIVE = "ARHIVA/";
		public static final String OP_FILE_EXTENSION_XML = ".XML";
		public static final String OP_FILE_PATTERN = "*" + OP_FILE_EXTENSION_XML;

	}

	public static class Pnop {
		public static final String MESSAGE_BUNDLE = "hr.abit.b3.biz.core.message.pnopMessages";
		public static final Integer SIFRA_LIKVIDATORA_PNOP = 994;
		public static final String SIFRA_POSLOVNICE_PNOP = "994";
		// tipovi naloga
		public static final Integer TIP_NALOGA_SIFRA_UPIS_PNOP_PORUKE_A = 66601;
		public static final Integer TIP_NALOGA_SIFRA_UPIS_PNOP_PORUKE_B = 66602;
	}
	
	public static class Bauto {
		public static final String MESSAGE_BUNDLE = "hr.abc.leonus.bauto.message.bautoMessages";
		
	    // internal codes
	    public static final String INTERNAL_ERROR_CODE = "0000";
	    public static final String INTERNAL_INFO_CODE = "0002";
	    public static final String INTERNAL_WARNING_CODE = "0003";
		
	    public static final Integer TIP_NALOGA_NEFINLOG_JOB_CREATE = 10260;
	    public static final Integer TIP_NALOGA_NEFINLOG_JOB_EDIT = 10261;
		public static final Integer TIP_NALOGA_NEFINLOG_TRIGGER_CREATE = 10262;
		public static final Integer TIP_NALOGA_NEFINLOG_TRIGGER_EDIT = 10263;
		public static final Integer TIP_NALOGA_NEFINLOG_REPORTING_CREATE = 10264;
		public static final Integer TIP_NALOGA_NEFINLOG_REPORTING_EDIT = 10265;
		public static final Integer TIP_NALOGA_NEFINLOG_SCHEDULER_CREATE = 10266;
		public static final Integer TIP_NALOGA_NEFINLOG_SCHEDULER_EDIT = 10267;
		public static final Integer TIP_NALOGA_NEFINLOG_JOB_DELETE = 10268;
		public static final Integer TIP_NALOGA_NEFINLOG_TRIGGER_DELETE = 10269;
		public static final Integer TIP_NALOGA_NEFINLOG_REPORTING_DELETE = 10270;
		public static final Integer TIP_NALOGA_NEFINLOG_SCHEDULER_DELETE = 10271;
		
		public static final String PRAZNIK_REZIM_POSEBAN_DA = "1"; 
		public static final String PRAZNIK_REZIM_POSEBAN_NE = "0";
		
		public static final String LOGGER_TASK_TYPE_START = "start"; 
		public static final String LOGGER_TASK_TYPE_COMPLETE = "complete"; 
		public static final String LOGGER_TASK_TYPE_ERROR = "error"; 
		
		public static final String LOGGER_TASK_STATUS_INIT = "0"; 
		public static final String LOGGER_TASK_STATUS_COMPLETE = "7"; 
		public static final String LOGGER_TASK_STATUS_WITHDRAW = "8"; 
		
		public static final String REPORTING_RESULT_SUCCESS = "success"; 
		public static final String REPORTING_RESULT_FAILURE = "failure"; 
		
		public static final String REPORTING_CHANNEL_MAIL = "mail"; 
		
		public static final String TASK_RESULT_SUCCESS = "success"; 
		public static final String TASK_RESULT_FAILURE = "failure"; 
		
		public static final String TASK_IDENTIFICATOR_REPORTING = "reportingId";
		public static final String TASK_IDENTIFICATOR_LOAD_EXCHANGE_LIST = "loadExchangeRateList";
		public static final String TASK_IDENTIFICATOR_PREORDER_PROCESSING = "preOrderProcessing";
		public static final String TASK_IDENTIFICATOR_STATEMENT_PROCESSING = "clientStatementProcessing";
		public static final String TASK_IDENTIFICATOR_FINA_FILE_STATEMENT_PROCESSING = "finaFileStatementProcessing";
		public static final String TASK_IDENTIFICATOR_FINA_FILE_STATETREASURY_STATEMENT_PROCESSING = "finaFileStateTreasuryStatementProcessing";
		public static final String TASK_IDENTIFICATOR_SEND_EMAIL_LIMIT = "sendEmailLimit";
		public static final String TASK_IDENTIFICATOR_SEND_EMAIL_LIMIT_AFTER_WT = "SendingLimitMailAfterWorkTime";
		public static final String TASK_IDENTIFICATOR_SEND_AVAILABILITY_SYSTEM = "sendEmailAvailabilitySystemProxy";

		public static final Long TASK_TIMEOUT_MINUTES = 240L;
		
		public static final Integer TASK_BAUTO_LIKVIDATOR = 989;
		public static final String TASK_BAUTO_POSLOVNICA = "989";
		
	}
	
}
