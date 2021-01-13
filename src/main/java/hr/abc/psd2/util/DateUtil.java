package hr.abc.psd2.util;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.StringUtils;

import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.model.dto.core.PraznikDto;

public class DateUtil extends GenericDateUtil {
	public static final Date START_OF_2015 = DateUtil.getDateFromString("01.01.2015", DEFAULT_DATE_FORMAT);
	public static final Date END_OF_2014 = DateUtil.getDateFromString("31.12.2014", DEFAULT_DATE_FORMAT);

	/**
	 * Provjera da li se radi o datumu subote/nedjelja/praznika.
	 *
	 * @param datum
	 * @param listaPraznik
	 * @return
	 * @author Matija Hlapčić
	 */
	public static boolean isSubotaNedjeljaPraznik(Date datum, List<PraznikDto> listaPraznik) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(datum);
		Integer day = cal.get(Calendar.DAY_OF_WEEK); // sub == 7, ned == 1
		Boolean neradniDan = false;
		if (day == 1 || day == 7) {
			neradniDan = true;
			return neradniDan;
		}
		Iterator<?> it = listaPraznik.iterator();
		while (it.hasNext()) {
			PraznikDto p = (PraznikDto) it.next();
			Calendar calPraznik = Calendar.getInstance();
			calPraznik.setTime(p.getDatumPra());
			if (cal.get(Calendar.YEAR) == calPraznik.get(Calendar.YEAR) && cal.get(Calendar.MONTH) == calPraznik.get(Calendar.MONTH) && cal.get(Calendar.DATE) == calPraznik.get(Calendar.DATE)) {
				neradniDan = true;
			}
		}
		return neradniDan;
	}

	/**
	 * Vraća datum sljedećeg anuiteta prema proslijeđenim parametrima kredita i anuiteta
	 * 
	 * @author Damir
	 * 
	 * @param datum
	 * @param datumStavljanjaUOtplatu
	 * @param datumOtvaranjaMoratorija 
	 * @param datumIstekaMoratorija
	 * @param brojAnuitetaGodisnje
	 * @param rasporediSZadnjimUMjesecu
	 * @return
	 * @throws AbitRuleException 
	 */
	public static Date calculateNextDatumAnuiteta(Date datum, Date datumStavljanjaUOtplatu, Date datumOtvaranjaMoratorija, Date datumIstekaMoratorija, String brojAnuitetaGodisnje, String rasporediSZadnjimUMjesecu) throws AbitRuleException {

		Date result = null;

		Date referentniDatum = datumStavljanjaUOtplatu;
		Boolean rasporedSZadnjimUMjesecu = Bassx2Constants.Krediti.KONMJPAR_DA.compareTo(rasporediSZadnjimUMjesecu) == 0;
		Boolean prviAnuitet = datum.equals(datumStavljanjaUOtplatu);

		int day = DateUtil.getDay(referentniDatum);

		if (prviAnuitet)
			result = referentniDatum;
		else
			result = datum;

		int intBrojAnuitetaGodisnje = Integer.parseInt(brojAnuitetaGodisnje);
		int monthsBetween = 12 / intBrojAnuitetaGodisnje;

		Boolean inMoratorij = true;
		while (inMoratorij) {
			if (rasporedSZadnjimUMjesecu) {
				result = DateUtil.getNextDateByEndOfMonth(result, monthsBetween);
			} else {
				result = DateUtil.getNextDateByDay(result, day, monthsBetween);
			}

			if (datumOtvaranjaMoratorija != null && datumIstekaMoratorija != null) {
				if (result.before(datumOtvaranjaMoratorija) || result.after(datumIstekaMoratorija))
					inMoratorij = false;
			} else {
				inMoratorij = false;
			}
		}
		return result;
	}

	/**
	 * Vraća datum prvog anuiteta prema proslijeđenim parametrima kredita i anuiteta
	 * 
	 * @author Damir
	 * 
	 * @param datumStavljanjaUOtplatu
	 * @param datumOtvaranjaMoratorija 
	 * @param datumIstekaMoratorija
	 * @param brojAnuitetaGodisnje
	 * @param rasporediSZadnjimUMjesecu
	 * @return
	 */
	public static Date calculateDatumPrvogAnuiteta(Date datumStavljanjaUOtplatu, Date datumOtvaranjaMoratorija, Date datumIstekaMoratorija, String brojAnuitetaGodisnje, String rasporediSZadnjimUMjesecu) throws AbitRuleException {
		Date datum = datumStavljanjaUOtplatu;
		if (brojAnuitetaGodisnje.equals(Bassx2Constants.Krediti.BRATAPAR_BEZ_ANUITETA)) {
			return datum;
		}
		return calculateNextDatumAnuiteta(datum, datumStavljanjaUOtplatu, datumOtvaranjaMoratorija, datumIstekaMoratorija, brojAnuitetaGodisnje, rasporediSZadnjimUMjesecu);
	}

	public static boolean isPrijestupna(Date datum) {
		LocalDate ld = datum.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return ld.isLeapYear();
	}

	public static boolean isPrijestupna(int godina) {
		LocalDate ld = LocalDate.of(godina, 1, 1);
		return ld.isLeapYear();
	}

	public static boolean isSaturday(Date datumValute) {
		boolean rez = false;
		if (datumValute != null) {
			LocalDate datum = datumValute.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			if (datum.getDayOfWeek().equals(DayOfWeek.SATURDAY)) {
				rez = true;
			}
		}
		return rez;
	}

	public static boolean isSunday(Date datumValute) {
		boolean rez = false;
		if (datumValute != null) {
			LocalDate datum = datumValute.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			if (datum.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
				rez = true;
			}
		}
		return rez;
	}

	/**
	 * Metoda razrješava prvi ponedjeljak iza danog datuma.
	 * 
	 * Koristi se u T2 direktoriju kada u datoteci ne doalzi datum.
	 * Učitavanje je u četvrtak navečer za sljedeći tjedan.
	 * 
	 * @param inputDate
	 * @return
	 * @author Matija Hlapčić
	 */
	public static Date getMondayAfterDate(Date inputDate) {
		Calendar result = GregorianCalendar.getInstance();
		if (inputDate != null) {
			result.setTime(inputDate);
			while (result.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
				result.add(Calendar.DATE, 1);
			}
		}
		return result.getTime();
	}

	/**
	 * Validira da li je proslijeđeni datum u stringu regularan.
	 * @param datum
	 * @param format
	 * @return <code>true</code> ako je datum OK.
	 * @throws AbitRuleException
	 */
	public static Boolean validateDatum(String datum, String format) throws AbitRuleException {
		String dan, mjesec, godina;
		if (format.equals("ddMMyyyy")) {
			dan = datum.substring(0, 2);
			mjesec = datum.substring(2, 4);
			godina = datum.substring(4);
		} else if (format.equals("dd.MM.yyyy") || format.equals("dd.MM.yyyy.")) {
			dan = datum.substring(0, 2);
			mjesec = datum.substring(3, 5);
			godina = datum.substring(6, 10);
		} else if (format.equals("ddMMyy")) {
			dan = datum.substring(0, 2);
			mjesec = datum.substring(2, 4);
			godina = "20" + datum.substring(4);
		} else if (format.equals("yyyy-MM-dd-HH.mm.ss.SSSSSS")) {
			dan = datum.substring(8, 10);
			mjesec = datum.substring(5, 7);
			godina = datum.substring(0, 4);
		} else if (format.equals("yyyyMMdd")) {
			dan = datum.substring(6);
			mjesec = datum.substring(4, 6);
			godina = datum.substring(0, 4);
		} else {
			throw new AbitRuleException("Format datuma za validaciju nije podržan");
		}
		Boolean ok = true;
		if (!Pattern.compile("1|2\\d\\d\\d").matcher(godina).find()) {
			ok = false;
		} else {
			// Godina OK.
			if (!Pattern.compile("(^0{1}[1-9])|(^1{1}[0-2])").matcher(mjesec).find()) {
				ok = false;
			} else {
				// Mjesec OK.
				if (Pattern.compile("01|03|05|07|08|10|12").matcher(mjesec).find()) {
					// Dan je 01 - 31
					if (!Pattern.compile("(^0{1}[1-9])|(^1{1}[0-9])|(^2{1}[0-9])|(^3{1}[0-1])").matcher(dan).find()) {
						ok = false;
					}
				} else if (Pattern.compile("04|06|09|11").matcher(mjesec).find()) {
					// Dan je 01 - 30
					if (!Pattern.compile("(^0{1}[1-9])|(^1{1}[0-9])|(^2{1}[0-9])|^30").matcher(dan).find()) {
						ok = false;
					}
				} else {
					// Dan je 01 - 28(29)
					// Provjera da li je prijestupna godina:
					Calendar c = Calendar.getInstance();
					c.set(Calendar.YEAR, Integer.valueOf(godina));
					c.set(Calendar.MONTH, Calendar.FEBRUARY);
					c.set(Calendar.DAY_OF_MONTH, 28);
					c.add(Calendar.DAY_OF_MONTH, 1);
					if (c.get(Calendar.MONTH) == Calendar.FEBRUARY) {
						// Prijestupna
						if (!Pattern.compile("(^0{1}[1-9])|(^1{1}[0-9])|(^2{1}[0-9])").matcher(dan).find()) {
							ok = false;
						}
					} else {
						if (!Pattern.compile("(^0{1}[1-9])|(^1{1}[0-9])|(^2{1}[0-8])").matcher(dan).find()) {
							ok = false;
						}
					}
				}
			}
		}
		return ok;
	}

	/**
	    * Metoda uspoređuje dva datuma samo vremena HH:mm:ss .<br>
	    * (jednako kao metoda <code>compareDates</code>, ali ne baca Exception)<br>
	    * @param prvi - prvi datum
	    * @param drugi - drugi datum
	    * @return
	    * -1 ako je <code>prvi</code> prije <code>drugi</code><br>
	    * 0 ako je <code>prvi</code> jednak <code>drugi</code><br>
	    * 1 ako je <code>prvi</code> poslije <code>drugi</code><br>
	    * @author Ivica
	    */
	public static Integer compareTimeOnly(Date prvi, Date drugi) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		try {
			prvi = sdf.parse((sdf.format(prvi)));
			drugi = sdf.parse((sdf.format(drugi)));
		} catch (ParseException pe) {
			return null;
		}
		if (prvi.before(drugi))
			return -1;
		else if (prvi.equals(drugi))
			return 0;
		else if (prvi.after(drugi))
			return 1;
		else
			return null;
	}

	/**
	* Returns list of dates within given period (dateFrom-dateTo)
	*
	* @author Kreso
	* @param dateFrom
	* @param dateTo
	* @return
	*/
	public static List<Date> getDatesWithinPeriod(Date dateFrom, Date dateTo) {
		List<Date> result = new ArrayList<Date>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(dateFrom);

		while (!calendar.getTime().after(dateTo)) {
			Date resultado = calendar.getTime();
			result.add(resultado);
			calendar.add(Calendar.DATE, 1);
		}
		return result;
	}

	public static Time getTimeFromString(String time) {
		return Time.valueOf(time);
	}

	/**
	 * Metoda vraća radni datum nakon određenog radnog perioda (bez subota, nedjelja, praznika).
	 *
	 * @param datum
	 * @param period
	 * @param listaPraznik
	 * @return result
	 * @author Vlatko
	 */
	public static Date getDateAfterWorkPeriod(Date datum, Integer period, List<PraznikDto> listaPraznik) {
		Date result = null, date;
		int i = 0, j = 0;
		while (i < period) {
			date = addNumberOfDays(datum, j);
			if (!isSubotaNedjeljaPraznik(date, listaPraznik)) {
				i++;
				result = date;
			}
			j++;
		}
		return result;
	}

	public static int compareDatesYMD(Date prvi, Date drugi) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(prvi);
		cal2.setTime(drugi);
		if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) {
			if (cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) {
				return (Integer.valueOf(cal1.get(Calendar.DAY_OF_YEAR))).compareTo(cal2.get(Calendar.DAY_OF_YEAR));
			} else {
				return (Integer.valueOf(cal1.get(Calendar.MONTH))).compareTo(cal2.get(Calendar.MONTH));
			}
		} else {
			return (Integer.valueOf(cal1.get(Calendar.YEAR))).compareTo(cal2.get(Calendar.YEAR));
		}
	}

	/**
	 * vraća <b>true</b> ukoliko je datum <i>date</i> unutar razdoblja <b><i>dateFrom</i> do <i>dateTo</i></b>
	 * @param date
	 * @param dateFrom
	 * @param dateTo
	 * @return
	 */

	public static boolean isWithinPeriod(Date date, Date dateFrom, Date dateTo) {
		return (date.compareTo(dateFrom) != -1) && (date.compareTo(dateTo) != 1);
	}

	public static Date prebaciUSljedeciRadniDan(Date datum, Boolean radnaSubota, Boolean radnaNedjelja, List<PraznikDto> listaPraznik) {

		Calendar c = Calendar.getInstance();
		c.setTime(datum);
		c.add(Calendar.DAY_OF_MONTH, 1);
		datum = c.getTime();
		datum = checkNeradniDan(datum, radnaSubota, radnaNedjelja, listaPraznik);

		return datum;
	}

	private static Date checkNeradniDan(Date tryDate, Boolean radnaSubota, Boolean radnaNedjelja, List<PraznikDto> listaPraznik) {

		//	ako je prebaceno na praznik povecaj do prvog radnog dana
		tryDate = checkPraznik(tryDate, listaPraznik, radnaSubota, radnaNedjelja);
		return tryDate;
	}

	private static Date checkPraznik(Date tryDate, List<PraznikDto> listaPraznik, Boolean radnaSubota, Boolean radnaNedjelja) {
		//provjera da li je prebaceno u sub/ned
		tryDate = checkSubNed(tryDate, radnaSubota, radnaNedjelja);

		for (PraznikDto praznik : listaPraznik) {
			Integer razlika = 1;
			razlika = DateUtil.compareDatesOnly(tryDate, praznik.getDatumPra());
			if (razlika.compareTo(0) == 0) {
				Calendar c = new GregorianCalendar();
				c.setTime(tryDate);
				c.add(Calendar.DAY_OF_MONTH, 1);
				tryDate = c.getTime();

				return checkPraznik(tryDate, listaPraznik, radnaSubota, radnaNedjelja);
			}
		}
		return tryDate;
	}

	private static Date checkSubNed(Date tryDate, Boolean radnaSubota, Boolean radnaNedjelja) {
		Calendar c = Calendar.getInstance();
		c.setTime(tryDate);
		Integer day = c.get(Calendar.DAY_OF_WEEK); //sub == 7, ned == 1

		//ako subota NIJE radna a nedjelja je
		//Prebaci 1 dan (sa sub na ned)
		if (!radnaSubota && radnaNedjelja) {
			if (day.compareTo(7) == 0)
				c.add(Calendar.DAY_OF_MONTH, 1);
		}

		//ako sub NIJE i ned NIJE
		//prebaci 2 dana
		else if (!radnaSubota && !radnaNedjelja) {
			if (day.compareTo(7) == 0)
				c.add(Calendar.DAY_OF_MONTH, 2);
		}

		tryDate = c.getTime();
		return tryDate;
	}

	public static XMLGregorianCalendar resolveXMLGregorianCalendarWithFormat(Date datum, SimpleDateFormat dateFormat) throws AbitRuleException {
		GregorianCalendar gcal = new GregorianCalendar();
		gcal.setTime(datum);
		String dateTimeString = dateFormat.format(datum);
		XMLGregorianCalendar xmlDate = null;
		try {
			xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(dateTimeString);

		} catch (DatatypeConfigurationException ex) {
			throw new AbitRuleException("Greška kod pretvorbe datuma u XMLGregorianCalendar");
		}
		return xmlDate;
	}

	/**
	 * Metoda račun period za izračun EKS-a na način da kreće od većeg datuma prema manjem.
	 *
	 *
	 * @param firstDate
	 * @param secondDate
	 * @return
	 */
	public static Period getPeriodForEKSOLD(Date firstDate, Date secondDate) throws AbitRuleException {
		Period result = null;
		try {
			int years = 0, months = 0, days = 0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date startDate = firstDate.before(secondDate) ? firstDate : secondDate;
			Date endDate = firstDate.before(secondDate) ? secondDate : firstDate;

			//Sad prvo
			LocalDate l1 = Instant.ofEpochMilli(startDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate l2 = Instant.ofEpochMilli(endDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

			Period perTest = Period.between(l1, l2);

			boolean zadnjiDan = l2.lengthOfMonth() == l2.getDayOfMonth();
			if (zadnjiDan) {
				months = Period.between(l1, l2).getMonths();
				years = Period.between(l1, l2).getYears();
				if (l1.lengthOfMonth() == l1.getDayOfMonth()) {
					if (l2.getDayOfMonth() < l1.getDayOfMonth())
						months = months + 1;
					days = 0;
				} else {
					LocalDate lTemp = l1.withDayOfMonth(l1.lengthOfMonth());
					days = Period.between(l1, lTemp).getDays();
				}
			} else {
				LocalDate lTemp = null;
				if (l1.getDayOfMonth() < l2.getDayOfMonth()) {
					lTemp = l1.withDayOfMonth(l2.getDayOfMonth());
					days = Period.between(l1, lTemp).getDays();
				} else if (l1.getDayOfMonth() == l2.getDayOfMonth()) {
					lTemp = l1;
				} else {
					lTemp = l1.withDayOfMonth(l2.getDayOfMonth());
					lTemp = lTemp.plusMonths(1);
					days = Period.between(l1, lTemp).getDays();
				}
				months = Period.between(lTemp, l2).getMonths();
				years = Period.between(lTemp, l2).getYears();
			}

			result = Period.of(years, months, days);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static Period getPeriodForEKS(Date firstDate, Date secondDate) {
		Period result = Period.of(0, 0, 0);

		LocalDate l1 = Instant.ofEpochMilli(firstDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate l2 = Instant.ofEpochMilli(secondDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();

		try {
			int years = 0, months = 0, days = 0;

			//Sad prvo
			boolean zadnjiDan = l2.lengthOfMonth() == l2.getDayOfMonth();
			if (zadnjiDan) {
				LocalDate l1temp = l1.withDayOfMonth(15);
				LocalDate l2temp = l2.withDayOfMonth(15);
				months = Period.between(l1temp, l2temp).getMonths();
				years = Period.between(l1temp, l2temp).getYears();
				if (l1.lengthOfMonth() == l1.getDayOfMonth()) {
					days = 0;
				} else {
					LocalDate lTemp = l1.withDayOfMonth(l1.lengthOfMonth());
					days = Period.between(l1, lTemp).getDays();
				}
			} else {
				LocalDate lTemp = null;
				if (l1.getDayOfMonth() < l2.getDayOfMonth()) {
					lTemp = l1.withDayOfMonth(l2.getDayOfMonth());
					days = Period.between(l1, lTemp).getDays();
				} else if (l1.getDayOfMonth() == l2.getDayOfMonth()) {
					if (l1.getDayOfMonth() == l1.lengthOfMonth()) {
						lTemp = l1.plusMonths(1);
						LocalDate l1Temp = l1.plusDays(1);
						days = Period.between(l1Temp, lTemp).getDays() + 1;
					} else {
						lTemp = l1;
						days = Period.between(l1, lTemp).getDays();
					}
				} else {
					lTemp = l1.withDayOfMonth(l2.getDayOfMonth());
					lTemp = lTemp.plusMonths(1);
					days = Period.between(l1, lTemp).getDays();
				}
				months = Period.between(lTemp, l2).getMonths();
				years = Period.between(lTemp, l2).getYears();
			}

			result = Period.of(years, months, days);
		} catch (Exception e) {
			//			log.error("getPeriod", e);
		}
		return result;
	}
	
	public static LocalDate resolveDateYYMM(String yymm){
		LocalDate res = null;
		if(!StringUtils.isBlank(yymm)){
			try {
				Integer year = Integer.valueOf("20" + yymm.substring(0, 2));
				res = LocalDate.of(year, Integer.valueOf(yymm.substring(2)) + 1, 1).minusDays(1);
			}catch(Exception ex){
				ex.printStackTrace();
				res = null;
			}
		}
		return res;
	}
	

	public static XMLGregorianCalendar resolveXMLGregorianCalendar(Date datum) throws AbitRuleException {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		XMLGregorianCalendar xmlDate = null;
		try {
			xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(format.format(datum));

		} catch (DatatypeConfigurationException ex) {
			throw new AbitRuleException("Greška kod pretvorbe datuma u XMLGregorianCalendar");
		}
		return xmlDate;
	}

	public static Date resolveDateUtilFromXMLGregorianCalendar(XMLGregorianCalendar datum) throws AbitRuleException {
		Date dateVrati = null;

		try {
			dateVrati = datum.toGregorianCalendar().getTime();
		} catch (Exception ex) {
			throw new AbitRuleException("Greška kod pretvorbe datuma");
		}
		return dateVrati;
	}

	public static Date resolveDateFromString(String datum) throws AbitRuleException {
		Date dateVrati = null;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		try {
			dateVrati = format.parse(datum);

		} catch (Exception ex) {
			throw new AbitRuleException("Greška kod pretvorbe datuma");
		}
		return dateVrati;
	}

	public static Boolean unutar24H(Date datum) {
		if (datum == null)
			return Boolean.FALSE;
		LocalDateTime dateOne = datum.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		dateOne = dateOne.plusDays(1); //dodamo 24h
		LocalDateTime dateTwo = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		return !dateOne.isBefore(dateTwo);
	}

	/**
	 * Otporno na java.sql.date
	 */
	public static LocalDate toLocalDate(Date in) {
		LocalDate datum = null;
		if (in != null) {
			in = new Date(in.getTime());
			datum = in.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		}
		return datum;
	}

	public static boolean isBeforeWithoutTime(Date d1, Date d2) {
		return getDateWithoutTimeUsingCalendar(d1).before(getDateWithoutTimeUsingCalendar(d2));
	}

	public static Date getDateWithoutTimeUsingCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

		return calendar.getTime();
	}

	public static int monthsBetween(Date d1, Date d2) {
		if (d2 == null || d1 == null) {
			return -1;//Error
		}
		Calendar m_calendar = Calendar.getInstance();
		m_calendar.setTime(d1);
		int nMonth1 = 12 * m_calendar.get(Calendar.YEAR) + m_calendar.get(Calendar.MONTH);
		m_calendar.setTime(d2);
		int nMonth2 = 12 * m_calendar.get(Calendar.YEAR) + m_calendar.get(Calendar.MONTH);
		return Math.abs(nMonth2 - nMonth1);
	}
	
	public static Date toDateFromLocalDate(LocalDate localDate) {
		return localDate != null ? Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()) : null;
	}
	
}