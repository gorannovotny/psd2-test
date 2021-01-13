package hr.abc.psd2.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Tu bi trebalo ptrbaciti B2Constants i onda ovo obrisati
 * ivica
 */
@Deprecated
public class Bassx2MinConstants extends GenericBassxConstants {

	public static class Avpo {
		public static final Integer TIP_NALOGA_SIFRAPOD_FORMIRANJE_IZVODA = 168;
	}

	public static class PlatniPromet {

		public static final Integer KLASA_NALOGA_FINANCIJSKI = 50200;
		public static final Integer KLASA_NALOGA_SIFRAKNA_SCT_INTERNI = 50202;
		public static final Integer KLASA_NALOGA_SIFRAKNA_SCT_INTERNI_VALUTA = 50222;
		public static final Integer KLASA_NALOGA_SIFRAKNA_SCT_HITNI_HSVP = 50203;
		public static final Integer KLASA_NALOGA_SIFRAKNA_SCT_REDOVNI_NKS = 50204;
		public static final Integer KLASA_NALOGA_SIFRAKNA_SCT_HITNI_T2 = 50205;
		public static final Integer KLASA_NALOGA_SIFRAKNA_SCT_REDOVNI_ENKS = 50206;
		public static final Integer KLASA_NALOGA_SIFRAKNA_NABANKU_DOMICILNA = 50215;
		public static final Integer KLASA_NALOGA_SIFRAKNA_NABANKU_VALUTA = 50216;
		public static final Integer KLASA_NALOGA_SIFRAKNA_FO_NA_FO = 50232;

		public static final String UTILMAP_KEY_EXCEPTION = "exceptionList";
		public static final String NACIN_IZVRSENJA_INTERNI_NALOG = "I";
		public static final String NACIN_IZVRSENJA_DEVIZNI_INTERNI_NALOG = "D";
		public static final String NACIN_IZVRSENJA_SWIFT_NALOG = "S";
		public static final String NACIN_IZVRSENJA_HSVP = "H";
		public static final String NACIN_IZVRSENJA_HRK_NKS = "N";
		public static final String NACIN_IZVRSENJA_EUR_NKS = "E";
		public static final String NACIN_IZVRSENJA_TARGET2 = "T";
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
		public static final String NKSBANKA_VBDINBAN_BANKA = "2408002";
		public static final String DRZAVA_HOME_OZNKATRI = "HRV";
		public static final String DRZAVA_HOME_OZNKADVA = "HR";
		public static final String SIFRA_SEKTORA_NEREZIDENT_START = "9";
		public static final Integer PLATNI_NALOG_HITNOST_REDOVNO = 0;
		public static final Integer PLATNI_NALOG_HITNOST_HITNO = 1;
		public static final String ODLJEV = "O";
		public static final String PRILJEV = "P";
		public static final Integer TIP_NALOGA_INTERNI = 20502;
		public static final Integer TIP_NALOGA_HSVP_ODLJEV = 20503;
		public static final Integer TIP_NALOGA_NKS_ODLJEV = 20504;
		public static final Integer TIP_NALOGA_T2_ODLJEV = 20505;
		public static final Integer TIP_NALOGA_EURO_NKS_ODLJEV = 20506;
		public static String IDENT_SPLITER = "-";
		public static String IDENT_PARTIJA = "part";
		public static String IDENT_NADDIO_OZNAKA = "ozNad";
		public static final String GRUPA_RACUNA_17 = "17";
		public static final String GRUPA_RACUNA_18 = "18";

	}
	public static class Core {
		public static final String NEW_LINE = "\r\n";
		public static final String MESSAGE_BUNDLE = "hr.abit.b3.biz.core.message.coreMessages";
		public static final String NALOG_STATUNAL_UPISAN = "0";
		public static final String NALOG_STATUNAL_PROKNJIZEN = "7";
		public static final String TIP_LOKACIJE_ADRESA = "1";
		public static final String VBDINBAN_BANKA = PlatniPromet.NKSBANKA_VBDINBAN_BANKA;
		public static final String NADDIO_OZNKANAD_OBRACUNATA_NAKNADA = "34";
		public static final String STATUS_IZUZECE_OVRHA_AKTIVAN_OIB = "A";
		public static final Integer TIP_NAPLATE_NAKNADE_PO_FAKTURI = 0;
		public static final Integer TIP_NAPLATE_NAKNADE_ODMAH = 1;
		public static final Integer KLASA_PARTIJE_SIFRAKLP_AVISTA_PO = 2;
	}

}
