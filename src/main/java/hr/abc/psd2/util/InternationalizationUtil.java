package hr.abc.psd2.util;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

/**
 * Funkcionalnost internacionalizacije - mapiranje poruka s GUI-ja na ključeve u properties datoteci što omogućuje laku promijenu
 * jezika aplikacije.
 *
 * @author Mata
 */
public class InternationalizationUtil {

	public static String getPsd2LocalMessage(String key) {
        return getLocalMessage(key, GenericBassxConstants.CoreHub.LANGUAGE_LOCALE_DEFAULT, GenericBassxConstants.CoreHub.COUNTRY_LOCALE_DEFAULT, GenericBassxConstants.Psd2.MESSAGE_BUNDLE);
    }
	
    /**
     * Metoda vraća lokaliziranu labelu za dani ključ.
     * Podaci o locale-ima se preuzimaju iz konstanti.
     *
     * @param key
     * @param messageBundle
     * @return
     * @author Matija Hlapčić
     */
    public static String getLocalMessage(String key, String messageBundle) {
        return getLocalMessage(key, GenericBassxConstants.CoreHub.LANGUAGE_LOCALE_DEFAULT, GenericBassxConstants.CoreHub.COUNTRY_LOCALE_DEFAULT, messageBundle);
    }

    /**
     * Metoda vrača lokaliziranu labelu za dani ključ.
     * Podaci o lokalima se prosljeđuju u metodu.
     *
     * @param key
     * @param languageLocale
     * @param countryLocale
     * @param messageBundle
     * @return
     * @author Matija Hlapčić
     */
    public static String getLocalMessage(String key, String languageLocale, String countryLocale, String messageBundle) {
        String result = "";
        if (StringUtils.isNotBlank(key)) {
            Locale locale = new Locale(languageLocale, countryLocale);
            ResourceBundle resource = null;
            try {
                resource = ResourceBundle.getBundle(messageBundle, locale);
            } catch (Exception e) {
                LoggerFactory.getLogger(InternationalizationUtil.class.getName()).info("Missing message bundle; error " + e.getMessage());
                resource = null;
                result = messageBundle + "~" + key;
            }
            if (resource != null) {
                try {
                    result = resource.getString(key);
                } catch (Exception e) {
                    LoggerFactory.getLogger(InternationalizationUtil.class.getName()).info("Missing key in message bundle: " + key + "; error " + e.getMessage());
                    result = key;
                }
            }
        }
        return result;
    }

    /**
     * Metoda vraća lokaliziranu labelu za dani ključ. Uz to složena je parametrizacija poruke.
     * Podaci o lokalima se prosljeđuju u metodu.
     *
     * @param key
     * @param messageArguments
     * @param languageLocale
     * @param countryLocale
     * @param messageBundle
     * @return
     * @author Matija Hlapčić
     */
    public static String getLocalMessage(String key, Object[] messageArguments, String languageLocale, String countryLocale, String messageBundle) {
        String message = getLocalMessage(key, languageLocale, countryLocale, messageBundle);
        Locale locale = new Locale(languageLocale, countryLocale);
        MessageFormat formatter = new MessageFormat("");
        formatter.setLocale(locale);
        formatter.applyPattern(message);
        return formatter.format(messageArguments);
    }

    /**
     * Metoda vraća lokaliziranu labelu za dani ključ. Uz to složena je parametrizacija poruke.
     * Podaci o locale-ima se preuzimaju iz konstanti.
     *
     * @param key
     * @param messageArguments
     * @param messageBundle
     * @return
     * @author Matija Hlapčić
     */
    public static String getLocalMessage(String key, Object[] messageArguments, String messageBundle) {
        return getLocalMessage(key, messageArguments, GenericBassxConstants.CoreHub.LANGUAGE_LOCALE_DEFAULT, GenericBassxConstants.CoreHub.COUNTRY_LOCALE_DEFAULT, messageBundle);
    }

    public static final Map<String, String> MAP_CODES_MESSAGE_KEYS = new HashMap<String, String>();

    {
        MAP_CODES_MESSAGE_KEYS.put(GenericBassxConstants.CoreHub.INTERNAL_ERROR_CODE, "internal_error");
        MAP_CODES_MESSAGE_KEYS.put("0001", "core_validation_error");
        MAP_CODES_MESSAGE_KEYS.put(GenericBassxConstants.CoreHub.INTERNAL_INFO_CODE, "action_success");
        MAP_CODES_MESSAGE_KEYS.put(GenericBassxConstants.CoreHub.INTERNAL_WARNING_CODE, "general_warning");
        MAP_CODES_MESSAGE_KEYS.put("1001", "input_parametar_empty");
        MAP_CODES_MESSAGE_KEYS.put("1002", "iban_error");
        MAP_CODES_MESSAGE_KEYS.put("1003", "iban_error");
    }
}
