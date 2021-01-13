package hr.abc.psd2.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.apache.commons.validator.routines.DateValidator;

public class GenericDateUtil {

	public static final long ONE_HOUR = 60 * 60 * 1000L;
	public static final String DEFAULT_DATE_FORMAT = "dd.MM.yyyy";
	public static final String JP_DATE_FORMAT = "ddMMyy";
	//public static final String SQL_DATE_FORMAT = "yyyy-MM-dd 00:00:00";
	public static final String SQL_DATE_ONLY_FORMAT = "dd.MM.yyyy";
	public static final String SQL_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
    public static final String DEFAULT_TIME_FORMAT_SKUP = "HHmmss";
    public static final String DEFAULT_DATETIME_FORMAT = "dd.MM.yyyy HH:mm:ss";
    public static final String SQL_DATETIME_TO_DATE_FORMAT = "%Y-%m-%d %H:%M:%S";
    public static final String DATE_FORMAT_YYYYMMDD="yyyyMMdd";
    public static final String DATE_FORMAT_YYMMDD = "yyMMdd";
    public static final String DATETIME_FORMAT_MILISECONDS = "yyyyMMddHHmmssSSS";
    public static final String DATE_FORMAT_FINA = "yyyyMMdd";
    public static final String DATE_FORMAT_TECAJNA_LISTA = "ddMMyyyy";
    public static final String DATE_FORMAT_YEAR = "yyyy";
    public static final String DATE_FORMAT_MONTH = "MM";
    public static final String DATE_FORMAT_YEAR_MONTH_DAY = "yyyy-MM-dd";
    public static final String DATE_FORMAT_YYMMDDHHMM_WITH_ZONE = "yyMMddHHmmZ";
    public static final SimpleDateFormat DEFAULT_DATE_FORMATTER = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
    public static final SimpleDateFormat DEFAULT_DATETIME_FORMATTER = new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
    private static Calendar calendar = GregorianCalendar.getInstance();

    /**
     * Izračun dana između početnog i krajnjeg datuma.
     *
     * @param startDate
     * @param endDate
     * @return
     * @author Božidarka
     */
    public static long daysBetween(Date startDate, Date endDate) {
    	startDate = getStartOfDay(startDate);
    	endDate = getStartOfDay(endDate);
        return ((endDate.getTime() - startDate.getTime() + ONE_HOUR) / (ONE_HOUR * 24));
    }

	/**
	 * Vraća se prvi u mjesecu prema referentnom datumu.
     *
     * @param date referentni datum
	 * @return
	 */
	public static Date getFirstOfCurrentMonth(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
        Integer year = calendar.get(Calendar.YEAR);
        Integer month = calendar.get(Calendar.MONTH);
        calendar.set(year, month, 1);
        return calendar.getTime();
    }

	/**
	 * Vraća se posljednji u mjesecu prema referentnom datumu.
     *
     * @param date referentni datum
	 * @return
	 */
    public static Date getLastOfCurrentMonth(Date date) {
    	Calendar calendar = GregorianCalendar.getInstance();
    	calendar.setTime(date);
        Integer year = calendar.get(Calendar.YEAR);
        Integer month = calendar.get(Calendar.MONTH);
        Integer day = calendar.getActualMaximum(Calendar.DATE);
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    public static Date getFirstOfMonth(Date date, Integer monthsBetween) {
        Integer month = getMonth(date);
        Integer year = getYear(date);
        month = month + monthsBetween;
        year = year + month / 12;
        month = month % 12;
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        return calendar.getTime();
    }


    /**
     * Vraća se zadnji dan godine prema referentnom datumu.
     * @param date
     * @return
     */
    public static Date getLastOfCurrentYear(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        Date resultDate = calendar.getTime();
        return resultDate;
    }

	/**
	 * Vraća broj mjeseci izmedju dva datuma
	 * @author Damir
	 * @return
	 */
    public static Integer getNumberOfMonthBetweenTwoDates (Date firstDate, Date secondDate) {

    	Calendar startDate = GregorianCalendar.getInstance();
    	Calendar endDate = GregorianCalendar.getInstance();
    	int totalNumberOfMonth = 0;

    	startDate.setTime(firstDate);
    	endDate.setTime(secondDate);

    	 while (!startDate.after(endDate)) {
             startDate.add(Calendar.MONTH, 1);
             totalNumberOfMonth++;
             }

    	return totalNumberOfMonth;
    }

    /**
     * Vraćanje datum u odabranom formatu.
     *
     * @param date
     * @param format
     * @return
     * @author Mata
     */
    public static String getDateAsString(Date date, String format) {
        if (date != null) {
        	SimpleDateFormat f = new SimpleDateFormat(format);
        	return f.format(date);
        }
        return null;
    }
    
    /**
     * Vraćanje datum u odabranom formatu.
     *
     * @param date
     * @param format
     * @param locale
     * @return
     * @author Mata
     */
    public static String getDateAsString(Date date, String format, Locale locale) {
    	if (date != null) {
    		SimpleDateFormat f = new SimpleDateFormat(format, locale);
    		return f.format(date);
    	}
    	return null;
    }

    /**
     * Usporedba datuma.
     *
     * @param prvi
     * @param drugi
     * @return
     * @author Mata
     */
    public static Integer compareDates(Date prvi, Date drugi){
 	   SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
 	   try {
 		   prvi = sdf.parse((sdf.format(prvi)));
 		   drugi = sdf.parse((sdf.format(drugi)));
 	   } catch (ParseException pe){
 		   return null;
 	   }
 	   if (prvi.before(drugi))
 		   return -1;
 	   else if (prvi.equals(drugi))
 		   return 0;
 	   else if (prvi.after(drugi))
 		   return 1;
 	   else return null;
    }

    /**
     * Metoda uspoređuje dva datuma.<br>
     * (jednako kao metoda <code>compareDates</code>, ali ne baca Exception)<br>
     * @param prvi - prvi datum
     * @param drugi - drugi datum
     * @return
     * -1 ako je <code>prvi</code> prije <code>drugi</code><br>
     * 0 ako je <code>prvi</code> jednak <code>drugi</code><br>
     * 1 ako je <code>prvi</code> poslije <code>drugi</code><br>
     * @author Smamson
     * @see GenericDateUtilCore.biz.util.GenericDateUtil.compareDates
     */
    public static Integer compareDatesOnly(Date prvi, Date drugi){
 	   SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
 	   try {
 		   prvi = sdf.parse((sdf.format(prvi)));
 		   drugi = sdf.parse((sdf.format(drugi)));
 	   } catch (ParseException pe){
 		   return null;
 	   }
 	   if (prvi.before(drugi))
 		   return -1;
 	   else if (prvi.equals(drugi))
 		   return 0;
 	   else if (prvi.after(drugi))
 		   return 1;
 	   else return null;
    }

    /**
     * Uspoređuje se trnutno vrijeme i vrijeme iz kao string - (vrijeme iz parametra)
     * -1 ako je <code>toTime</code> prije current time
     * 0 ako je <code>toTime</code> jednak current time
     * 1 ako je <code>toTime</code> poslije current time
     * @param toTime
     * @return
     * @author ivica
     */
    public static Integer compareToCurrent(String toTime){
    	DateFormat df = new SimpleDateFormat(DEFAULT_TIME_FORMAT);
    	try {
			df.parse(toTime);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
    	Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, df.getCalendar().get(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, df.getCalendar().get(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, df.getCalendar().get(Calendar.SECOND));
    	Date inTime = calendar.getTime();
    	return inTime.compareTo(Calendar.getInstance().getTime());
     }


    /**
     * Vraća prvi nadolazeći kraj kvartala u odnosu na proslijeđeni datum
     *
     * @author Damir
     *
     * @param date
     * @return
     */
    public static Date getQuarterByQuarterOfYear(Date date) {

        Calendar calendar = GregorianCalendar.getInstance();
        int year = GenericDateUtil.getYear(date);
        int month = GenericDateUtil.getMonth(date);
        month++;
        int day = GenericDateUtil.getDay(date);

        if ((month < 3) || ((month == 3) && (day < 31))) {
            month = 3;
            day = 31;
        } else if ((month < 6) || ((month == 6) && (day < 30))) {
            month = 6;
            day = 30;
        } else if ((month < 9) || ((month == 9) && (day < 30))) {
            month = 9;
            day = 30;
        } else if ((month < 12) || ((month == 12) && (day < 31))) {
            month = 12;
            day = 31;
        } else {
            year++;
            month = 3;
            day = 31;
        }

        month--;

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        Date resultDate = calendar.getTime();
        return resultDate;
    }


    /**
     * Vraća prvi nadolazeći kraj (polu)godišta u odnosu na proslijeđeni datum
     *
     * @author Damir
     *
     * @param date
     * @return
     */
    public static Date getHalfByHalfOfYear(Date date) {

        Calendar calendar = GregorianCalendar.getInstance();
        int year = GenericDateUtil.getYear(date);
        int month = GenericDateUtil.getMonth(date);
        month++;
        int day = GenericDateUtil.getDay(date);

        if ((month < 6) || ((month == 6) && (day < 30))) {
            month = 6;
            day = 30;
        } else if ((month < 12) || ((month == 12) && (day < 31))) {
            month = 12;
            day = 31;
        } else {
            year++;
            month = 6;
            day = 30;
        }

        month--;

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        Date resultDate = calendar.getTime();
        return resultDate;
    }


    /**
   	 * Metoda koja anulira sate, minute, sekunde te milisekunde datuma.
   	 * @param datum - datum koji se anullira
   	 * @return cal.getTime() - datum s anuliranim vremenom
   	 * @version 1.0
     * @author Matija
     */
   	public static Date anuliranjeDatuma(Date datum){
   		Calendar cal = GregorianCalendar.getInstance();
   		cal.setTime(datum);
   		cal.set(Calendar.HOUR_OF_DAY, 0);
   		cal.set(Calendar.MINUTE, 0);
   		cal.set(Calendar.SECOND, 0);
   		cal.set(Calendar.MILLISECOND, 0);
   		return cal.getTime();
   	}

    public static Date setCurrentTime(Date datum){
        Calendar cal = GregorianCalendar.getInstance();
        Calendar now = GregorianCalendar.getInstance();
        cal.setTime(datum);
        cal.set(Calendar.HOUR_OF_DAY, now.get(Calendar.HOUR_OF_DAY));
        cal.set(Calendar.MINUTE, now.get(Calendar.MINUTE));
        cal.set(Calendar.SECOND, now.get(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, now.get(Calendar.MILLISECOND));
        return cal.getTime();
    }


    /**
	 * Vraća se datum sa hh:mm:ss 00:00:00
     *
     * @param date referentni datum
	 * @return
	 */
	public static Date getStartOfDay(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

	/**
	 * Vraća se datum sa hh:mm:ss 23:59:59
     *
     * @param date referentni datum
	 * @return
	 */
	public static Date getEndOfDay(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }

	/**
	 * <p>vraća današnji datum</p>
	 * @return danasnji datum
	 */
	public static Date today(){
        Calendar now = Calendar.getInstance();
        return new GregorianCalendar(now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH)).getTime();
   }

	/**
	 * <p>vraća jučerašnji datum</p>
	 * @return jucerasnji datum
	 */
	public static Date yesterday() {
        return GenericDateUtil.getRelativeDate(GenericDateUtil.today(), -1);
    }

    /**
	 * <p>vraća sutrašnji datum</p>
	 * @return sutrasnji datum
	 */
	public static Date tomorrow() {
        return GenericDateUtil.getRelativeDate(GenericDateUtil.today(), 1);
    }


    public static Date getDateFromString(String date, String format) {
        Date result = null;
        DateFormat df = new SimpleDateFormat(format);
        try {
             result =  df.parse(date);
        } catch (Exception ex) {}
        return result;
    }

    /**
     * Metoda validira da li ulazni string odgovara datumu formata koji se šalje u pattern parametru.
     * @param date string reprezentacija datuma
     * @param pattern predložak datuma
     * @return vraća true ako je datum ispravan prema formatu u patternu, false ako nije
     */
    public static Boolean isValidDate(String date, String pattern) {
    	DateValidator dv = DateValidator.getInstance();
    	return dv.isValid(date, pattern);
    }

    /**
     * returns DAY OF MONTH for given date (1 - 31)
     */
    public static Integer getDay(Date date) {
    	Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
	 * Vraća datum - zadnji dan u mjesecu - pomaknut za dani broj mjeseci u odnosu na proslijeđeni datum
     *
     * @author Damir
     *
     * @param date
	 * @param monthsBetween
	 * @return
	 */
    public static Date getNextDateByEndOfMonth(Date date, Integer monthsBetween) {

        Integer months = monthsBetween;
        if (!GenericDateUtil.isEndOfMonth(date)) {
            months--;
        }
        Date resultDate = getNextDateByDay(date, 1, months);
        resultDate = GenericDateUtil.getLastOfCurrentMonth(resultDate);
        return resultDate;
    }

    public static boolean isEndOfMonth(Date date) {
        Date tmpDate = getLastOfCurrentMonth(date);
        return GenericDateUtil.compareDates(date, tmpDate) == 0;
    }

    public static boolean isEndOfYear(Date date) {
        Integer year = getYear(date);
        Date tmpDate = getEndOfYear(year);
        return GenericDateUtil.compareDates(date, tmpDate) == 0;
    }

    /**
	 * Vraća datum na zadani dan pomaknut za dani broj mjeseci u odnosu na proslijeđeni datum
     *
     * @author Damir
     *
     * @param date
	 * @param day
	 * @param monthsBetween
	 * @return
	 */
    public static Date getNextDateByDay(Date date, Integer day, Integer monthsBetween) {

        Calendar calendar = GregorianCalendar.getInstance();
        Integer month = GenericDateUtil.getMonth(date);
        Integer year = GenericDateUtil.getYear(date);
        month = month + monthsBetween;
        year = year + month / 12;
        month = month % 12;
        calendar.setTime(date);
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.YEAR, year);
        Date resultDate = calendar.getTime();
        resultDate = GenericDateUtil.getLastOfCurrentMonth(resultDate);
        Integer daysInMonth = GenericDateUtil.getDay(resultDate);
        if (day < daysInMonth) {
            calendar.set(Calendar.DAY_OF_MONTH, day);
            resultDate = calendar.getTime();
        }
        return resultDate;
    }

    /**
     * returns MONTH for given date (0 - 11)
     */
    public static Integer getMonth(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }

    public static Integer getYear(Date date){
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    public static Date getEndOfYear(int year) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, Calendar.DECEMBER);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);

        Date resultDate = calendar.getTime();
        return resultDate;
    }

    //datum početnog stanja
    public static Date getDatumPocetnogStanja() {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(Calendar.YEAR, 2014);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 1);

        Date resultDate = calendar.getTime();
        return resultDate;
    }

    /**
     *  <p>Vraca datum pomaknut za 'daysBetween' dana<p>
     *
     *  Napomena: ako se ide kronološki unatrag, 'daysBetween' mora biti negativan
     */
    public static Date getRelativeDate(Date referenceDate, int daysBetween) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(referenceDate);
        calendar.add(Calendar.DATE, daysBetween);
        return calendar.getTime();
    }

    /**
     *  <p>Vraca datum pomaknut za 'monthsBetween' mjesece<p>
     *
     *  Napomena: ako se ide kronološki unatrag, 'monthsBetween' mora biti negativan
     */
   public static Date getRelativeDateByMonths(Date referenceDate, int monthsBetween) {
       Calendar calendar = GregorianCalendar.getInstance();
       calendar.setTime(referenceDate);
       calendar.add(Calendar.MONTH, monthsBetween);
       return calendar.getTime();
   }

    /**
     *  <p>Vraca datum pomaknut za 'yearsBetween' godina<p>
     *
     *  Napomena: ako se ide kronološki unatrag, 'yearsBetween' mora biti negativan
     */
    public static Date getRelativeDateByYears(Date referenceDate, int yearsBetween) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(referenceDate);
        calendar.add(Calendar.YEAR, yearsBetween);
        return calendar.getTime();
    }

    public static Date getFirstOfYear(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        Integer year = calendar.get(Calendar.YEAR);
        calendar.set(year, 0, 1);
        return calendar.getTime();
    }

    /**
     * Vraća parametarski definirani broj dana u povijest u odnosu na prosljeđeni datum.
     *
     * @param startDate - start point
     * @param daysBack - days back in history
     * @return
     * @author Matija Hlapčić
     */
    public static Date getDefinedDaysBackInHistory(Date startDate, Integer daysBack) {
    	Calendar result = GregorianCalendar.getInstance();
    	if (startDate != null && daysBack != null) {
    		result.setTime(startDate);
    		result.add(Calendar.DATE, daysBack * (-1));
    	}
    	return result.getTime();
    }

    /**
     * Vraća parametarski definirani broj dana u budućnost u odnosu na prosljeđeni datum.
     *
     * @param startDate - start point
     * @param daysForward - days forward in future
     * @return
     * @author Matija Hlapčić
     */
    public static Date getDefinedDaysInFuture(Date startDate, Integer daysForward) {
    	Calendar result = GregorianCalendar.getInstance();
    	if (startDate != null && daysForward != null) {
    		result.setTime(startDate);
    		result.add(Calendar.DATE, daysForward);
    	}
    	return result.getTime();
    }

    /**
     * broj dana u godini u kojoj je zadani referenti datum
     */
    public static int daysInYear(Date referenceDate) {
        Calendar calendar = GregorianCalendar.getInstance();
    	int year = getYear(referenceDate);
    	Date endOfYear = getEndOfYear(year);
    	calendar.setTime(endOfYear);
    	return calendar.get(Calendar.DAY_OF_YEAR);
    }

    public static Date getFirstOfPreviousMonth(Date date) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date);
    	cal.add(Calendar.MONTH, -1);
    	cal.set(Calendar.DAY_OF_MONTH, 1);
    	return cal.getTime();
    }

    public static Date getLastOfPreviousMonth(Date date) {
        Date firstOfPreviousMonth = getFirstOfPreviousMonth(date);
        return getLastOfCurrentMonth(firstOfPreviousMonth);
    }

    public static Date getFirstOfNextMonth(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        Integer year = calendar.get(Calendar.YEAR);
        Integer month = calendar.get(Calendar.MONTH);
        if (month < 12) {
            month++;
        } else {
            year++;
            month = 1;
        }
        calendar.set(year, month, 1);
        return calendar.getTime();
    }

    public static Date getLastOfNextMonth(Date date) {
        Date firstOfNextMonth = getFirstOfNextMonth(date);
        return getLastOfCurrentMonth(firstOfNextMonth);
    }

    public static boolean isLastOfMonth(Date date) {
        calendar.setTime(date);
        Integer year = calendar.get(Calendar.YEAR);
        Integer month = calendar.get(Calendar.MONTH);
        Integer day = calendar.getActualMaximum(Calendar.DATE);
        calendar.set(year, month, day);
        Date lastDay = calendar.getTime();
        return date.compareTo(lastDay) == 0;
    }

    public static Date addNumberOfDays(Date currentDate, Integer numberOfDays) {
    	calendar.setTime(currentDate);
    	if (numberOfDays != null) {
    		calendar.add(Calendar.DATE, numberOfDays);
    	}
    	return calendar.getTime();
    }


    public static Date addNumberOfYears(Date currentDate, Integer numberOfYears) {
        calendar.setTime(currentDate);
        if (numberOfYears != null) {
            calendar.add(Calendar.YEAR, numberOfYears);
        }
        return calendar.getTime();
    }

    /**
     * Vraća prvi dan produkcije.
     *
     * @return
     */
    @Deprecated
    public static Date goLiveDate() {
    	Calendar cal = Calendar.getInstance();
		cal.set(2015, Calendar.APRIL, 1);
		return cal.getTime();
    }


    public static String getSQLTermForDate(Date date) {
        String result = null;
        if (date != null) {
            result = " TO_DATE('" + GenericDateUtil.DEFAULT_DATE_FORMATTER.format(date) + "', '%d.%m.%Y') ";
        }
        return result;
    }
    
    public static Date addNumberOfMonths(Date currentDate, Integer numberOfMonths) {
    	calendar.setTime(currentDate);
    	if (numberOfMonths != null) {
    		calendar.add(Calendar.MONTH, numberOfMonths);
    	}
    	return calendar.getTime();
    }
    
    /**
     * returns DAY OF WEEK for given date (0 - 6)
     */
    public static Integer getDayOfWeek(Date date) {
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
    
}
