/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package hr.abc.psd2.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.lang3.StringUtils;

import hr.abc.psd2.exception.AbitRuleException;


/**
 *
 * @author Saša
 */
public class StringFunctions {
    
	public static final String DEFAULT_NUMBER_PATTERN = "#,##0.00";
	public static final String TECAJ_NUMBER_PATTERN = "#,##0.000000";
	public static final String NAKNADA_NUMBER_PATTERN = "#,##0.000";
	public static final String NEW_LINE = "\r\n";	
	
	
	
    public StringFunctions() {
	
    }
    
    public static String trimRight(String text) {
    	return StringUtils.stripEnd(text, null);
    } 
    
    public static String getPrimaryKeyFromEntityDesignation(String entityDesignation) {
    	String result = entityDesignation.substring(entityDesignation.lastIndexOf('=') + 1, entityDesignation.lastIndexOf(']'));
    	return result;
    }
    
    public static char[] createEmptyLine(int chars) {
    	char[] tmp=new char[chars];
    	for(int i=0;i<chars;i++)
    		tmp[i]=' ';
    	return tmp;
    }
    
    public static String trimStringTo(String string,int length) {
    	return StringUtils.rightPad(string, length,' ');
    }

	public static String vodeceNule(int i, int length) {
		return vodeceNule(String.valueOf(i), length);
	}

    public static String vodeceNule(String i, int length) {
		if(i != null) i = i.trim();
		String tmp = StringUtils.leftPad(i, length, '0');
		return tmp;
	}

    public static String charsetconvunicode_1250( String s ){
	if (s==null) return "";

	char znak;
	StringBuffer x=new StringBuffer();
	for ( int i=0; i<s.length(); i++ )  {
	    znak=s.charAt( i );			
	    if (znak=='č') x.append((char)232);
	    else if (znak=='ć') x.append((char)230);
	    else if (znak=='š') x.append((char)154);
	    else if (znak=='đ') x.append((char)240);
	    else if (znak=='ž') x.append((char)158);
	    else if (znak=='Č') x.append((char)200);
	    else if (znak=='Ć') x.append((char)198);
	    else if (znak=='Š') x.append((char)138);
	    else if (znak=='Đ') x.append((char)208);
	    else if (znak=='Ž') x.append((char)142);
	    else if (znak=='<') x.append("&lt;");
	    else if (znak=='>') x.append("&gt;");
	    else {
		x.append(znak);
	    }
	}
        return x.toString();
	}
    
    
    public static String hr_en( String s ){
    	if (s==null) return "";

    	char znak;
    	StringBuffer x=new StringBuffer();
    	for ( int i=0; i<s.length(); i++ )  {
    	    znak=s.charAt( i );			
    	    if (znak=='č') x.append('c');
    	    else if (znak=='ć') x.append('c');
    	    else if (znak=='š') x.append('s');
    	    else if (znak=='đ') x.append('d');
    	    else if (znak=='ž') x.append('z');
    	    else if (znak=='Č') x.append('C');
    	    else if (znak=='Ć') x.append('C');
    	    else if (znak=='Š') x.append('S');
    	    else if (znak=='Đ') x.append('D');
    	    else if (znak=='Ž') x.append('Z');

    	    else {
    		x.append(znak);
    	    }
    	}
            return x.toString();
    }
    
    
    
    public static boolean isEmpty(String string) {
    	return StringUtils.isEmpty(string);
    }
    
    public static boolean isBlank(String string) {
    	return StringUtils.isBlank(string);
    }
    
	public static Boolean isBlank(Object object) {
		Boolean result = true;
		result = result && object == null;
		if (!result) result = StringFunctions.isBlank(object.toString());
		return result;
	}
    
    
    public static boolean equalsIgnoreCase(String string1, String string2) {
    	return StringUtils.equalsIgnoreCase(string1, string2);
    }
    
    public static String getDelimitedTextFromList(List<?> list, String delimiter) {
    	StringBuilder resultBuilder = new StringBuilder();
    	for (Object o : list) {
    		resultBuilder = resultBuilder.append(o.toString());
    		resultBuilder = resultBuilder.append(delimiter);
    	}
    	String result = resultBuilder.toString();
    	int pozicijaZadnjegDelimitera = result.lastIndexOf(delimiter);
    	if (pozicijaZadnjegDelimitera > -1) result = result.substring(0, pozicijaZadnjegDelimitera);
    	return result;
    }
    
    public static List<String> getListFromDelimitedText(String text, String delimiter) throws PatternSyntaxException {
    	String[] splitString =StringUtils.splitByWholeSeparatorPreserveAllTokens(text,delimiter); 
    	List<String> result = Arrays.asList(splitString);
    	return result;
    }
    
    public static String getDelimitedTextFromSet(Set<?> set, String delimiter) {
    	String result = "";
    	for (Object o : set) {
    		result = result.concat(o.toString());
    		result = result.concat(delimiter);
    	}
    	if (result.length() > 0) result = result.substring(0, result.length() - delimiter.length());
    	return result.toString();
    }
    
    
    public static String appendSqlCondition(String sql, String condition){
    	if(!isBlank(sql)){
    		if(!isBlank(condition)){
    			if(condition.trim().toUpperCase().startsWith("ORDER BY ") || condition.trim().toUpperCase().startsWith("GROUP BY ")){
    				return sql +" "+condition;
    			}else{
    				if(sql.toUpperCase().contains(" WHERE ")){
    					return sql +" AND "+condition;
    				}else{
    					return sql +" WHERE "+condition;
    				}
    			}
    		}
    	}
    	return sql;
    }
    
    /**
     * 
     * Primjer: "TecajnaLista" -> "tecajna_lista"
     * 
     */
    public static String getTableNameFromEntityName(String entityName) {
    	StringBuffer result = new StringBuffer();
    	
    	// exceptions
    	if (entityName.equals("PravnaOsoba")) return "prav_komitent";
    	
    	char ch;
    	String str;
    	
    	int cnt = 0;
    	ch = entityName.charAt(cnt);
    	
    	str = new Character(ch).toString().toLowerCase();
    	result.append(str);
    	
    	cnt++;
    	while (cnt < entityName.length()) {
    		ch = entityName.charAt(cnt);
    		if (ch < 97) { // 97 = 'a'
    			ch = entityName.charAt(cnt);
    			str = "_" + new Character(ch).toString().toLowerCase();
    		} else {
    			str = new Character(ch).toString();    			
    		}
   	    	result.append(str);    			
   	    	cnt++;
    	}
    	
    	return result.toString();
    }
    
    
    
    /**
     * 
     * Primjer: "tecajna_lista" -> "TecajnaLista" 
     * 
     */
    public static String getEntityNameFromTableName(String tableName) {
    	
    	StringBuffer result = new StringBuffer();    	
    	
    	char ch;
    	String str;
    	
    	int cnt = 0;
    	ch = tableName.charAt(cnt);
    	
    	str = new Character(ch).toString().toUpperCase();
    	result.append(str);
    	
    	cnt++;
    	while (cnt < tableName.length()) {
    		ch = tableName.charAt(cnt);
    		if (ch == '_') {
    			cnt++;
    			ch = tableName.charAt(cnt);
    			str = new Character(ch).toString().toUpperCase();
    		} else {
    			str = new Character(ch).toString();    			
    		}
   	    	result.append(str);    			
   	    	cnt++;
    	}
    	
    	return result.toString();
    }
    
    
    
    /**
	 * igorova metoda koja popunjava numeričko polje s nulama ispred
	 * @param duljina
	 * @param polje
	 * @return
	 * @throws AbitRuleException
	 */
	public static String popuniNPolje(int duljina, String polje){
		int brojNula = duljina;
		if(polje != null){
			polje = polje.trim();
			brojNula = duljina - polje.length();
		}else{
			polje = "";
		}
		for(int i=0; i < brojNula; i++){
			polje = "0" + polje;
		}
		return polje;
	}

	/**
	 * pretvara BigDecimal u "defaultno" formatirani String
	 * 
	 * @param number BigDecimal koji se pretvara
	 * @return
	 *  
	 */
    public static String bigDecimalToString(BigDecimal number) {
        String result = null; 
    	if (number != null) {
    		Locale locale = new Locale("hr", "HR");
    		NumberFormat nf = NumberFormat.getNumberInstance(locale);
    		DecimalFormat df = (DecimalFormat) nf;
    		
    		StringBuilder pattern = new StringBuilder();
    		pattern.append("##############0");
    		int scale = number.scale();
    		if (scale > 0)
    			pattern.append('.');
    		for (int cnt = 0; cnt < scale; cnt++)
    			pattern.append('0');
    		
    		df.applyPattern(pattern.toString());
    		result = df.format(number);
    	}
        return result;
    }
    
    /**
	 * pretvara BigDecimal u "defaultno" formatirani String
	 * 
	 * @param number BigDecimal koji se pretvara
	 * @return
	 *  
	 */
    public static String bigDecimalToString(BigDecimal number, String numberPattern) {
        Locale locale = new Locale("hr", "HR");
        NumberFormat nf = NumberFormat.getNumberInstance(locale);
        DecimalFormat df = (DecimalFormat) nf;
        
        StringBuilder pattern = new StringBuilder(StringUtils.isNotBlank(numberPattern) ? numberPattern : DEFAULT_NUMBER_PATTERN);
        df.applyPattern(pattern.toString());

        return df.format(number);
    }
    
    public static String bigDecimalToStringCurrency(BigDecimal iznos) {
        Locale locale = new Locale("hr", "HR");
        NumberFormat nf = NumberFormat.getNumberInstance(locale);
        DecimalFormat df = (DecimalFormat) nf;

        StringBuilder pattern = new StringBuilder();
        pattern.append("#,##0.00");
        df.applyPattern(pattern.toString());
        return df.format(iznos);
    }
    
    
    public static BigDecimal getBigDecimalFromString(String value) {
    	if (StringUtils.isBlank(value)) return null;
    	String trimmedValue = value.trim();
        try {
        	trimmedValue = trimmedValue.replace(".", "");
        	trimmedValue = trimmedValue.replace(",", ".");
            return new BigDecimal(trimmedValue);
        }
        catch (NumberFormatException ex) {}
        return null;
    }
    
    
    
    /**
     * returns true if string consists of characters ranging from 0 to 9, otherwise false
     * 
     * @param string
     * @return
     */
    public static boolean StringIsInteger(String string) {
    	boolean result = Pattern.matches("^\\d*$", string);
    	return result;
    }

    public static String normalize(String s) {
		String ss = s.replaceAll("[^-/?.,;:_()\"'#!1234567890qwertzuiopasdfghjklyxcvbnmQWERTZUIOPASDFGHJKLYXCVBNM šđčćžŠĐČĆŽ]", " ");
		return ss;
	}
    
    public static String ripEnter(String tekst) {
		StringBuffer sb=new StringBuffer();
		while(tekst.indexOf("\r\n")>=0) {
			int n=tekst.indexOf("\r\n");
			sb.append(tekst.substring(0,n));
			sb.append(", ");
			tekst=tekst.substring(n+2,tekst.length());
		}
		while(tekst.indexOf("\n")>=0) {
			int n=tekst.indexOf("\n");
			sb.append(tekst.substring(0,n));
			sb.append(", ");
			tekst=tekst.substring(n+1,tekst.length());
		}
		sb.append(tekst);
		return sb.toString();
	}
    
    public static String maskirajTekst(String text, char mask, int startLength, int endLength){
    	String res = "";
    	if(text == null) text = "";
    	text = text.trim();
    	int ukupnaDuzina = startLength+endLength;
    	if(text.length() < ukupnaDuzina){
    		return text;
    	}
    	int duzinaMaske = text.length() - ukupnaDuzina;
    	StringBuffer maska = new StringBuffer();
    	for(int i=0;i<duzinaMaske;i++){
    		maska.append(mask);
    	}
    	res = text.substring(0, startLength)+maska+text.substring(startLength+duzinaMaske);
    	return res;
    }
    
    public static Boolean startsWithOneOfListTerms(String term, List<String> terms) {
        Boolean result = Boolean.FALSE;
        if (StringUtils.isNotBlank(term) && terms != null && !terms.isEmpty()) {
            for (String listTerm : terms) {
                result = (term.startsWith(listTerm));
                if (result) {
                    break;
                }
            }
        }
        return result;
    }
    

	/**
	 * Metoda validira da li su znakovi u ulaznom stringu iz dozvoljenog skupa znakova.
	 * Nepoznati ili nedozvoljeni znakove se mijenjaju u blank.
	 * @param inputString
	 * @param regex
	 * @return outputString
	 */
	public static String checkIrregularCharacters(String inputString, String regex) {
		StringBuffer outputBuffer = null;
		if (StringUtils.isNotBlank(inputString)) {
			outputBuffer = new StringBuffer(inputString.length());
			Pattern swiftAllowedPattern = Pattern.compile(regex);
			Matcher swiftAllowedMatcher;
			for (String s : inputString.trim().split("")) {
				if (s != null && !s.equals("")) {
					swiftAllowedMatcher = swiftAllowedPattern.matcher(s);
					outputBuffer.append(swiftAllowedMatcher.matches() ? s : " ");
				}
			}
		}
		return outputBuffer != null ? outputBuffer.toString() : "";
	}
}
