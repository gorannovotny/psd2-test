package hr.abc.psd2.util;


import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.model.dto.core.DrzavaDto;
import hr.abc.psd2.model.dto.core.IbanFormatDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IbanFunctions {

    public static final String VBDI_ID = "vbdi";
    public static final String PARTIJA_ID = "partija";

    public static boolean isIban(String iban) {
        try {
            String tempIban = "";
            if (iban.trim().length() >= 5 && iban.matches("[A-Z][A-Z](.*)")) {
                tempIban = iban.trim().substring(4, iban.trim().length()) + iban.trim().substring(0, 4);
                tempIban = IbanFunctions.changeLetters(tempIban);
            } else {
                return false;
            }
            Integer result = IbanFunctions.myMod(tempIban, 97);
            if (result.equals(1)) {
                return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    /**
     * Metoda na temelju valjanog IBAN vraća državu korisnika.
     *
     * @param iban
     * @return drzava
     */
    public static DrzavaDto resolveDrzavaFromIban(String iban, EntityManager em) {
        DrzavaDto drzava = null;
        if (iban != null && iban.length() >= IbanFormatDto.IBAN_COUNTRY_CODE_LENGTH && StringUtils.isAlpha(iban.substring(0, IbanFormatDto.IBAN_COUNTRY_CODE_LENGTH))) {
            try {
			    drzava = new DrzavaDto();
                String sql = "select " + DrzavaDto.SIFRA_FIELD + "," + DrzavaDto.NAZIV_FIELD + "," + DrzavaDto.NAZEN_FIELD + "," + DrzavaDto.SIDVA_FIELD + "," + DrzavaDto.SITRI_FIELD+ "," + DrzavaDto.OFFSH_FIELD + "," + DrzavaDto.TELBR_FIELD +
                        " from " + DrzavaDto.TABLE_NAME +
                        " where " + DrzavaDto.SIDVA_FIELD + " = :parSidva";
                Query query = em.createNativeQuery(sql);
                query.setParameter("parSidva", iban.trim().replace("/", "").substring(0, IbanFormatDto.IBAN_COUNTRY_CODE_LENGTH).toUpperCase());
                Object[] entityArray = (Object[]) query.getSingleResult();
                drzava.setSifra(entityArray[0] != null ? (String) entityArray[0] : "");
                drzava.setNaziv(entityArray[1] != null ? (String) entityArray[1] : "");
                drzava.setEngleskiNaziv(entityArray[2] != null ? (String) entityArray[2] : "");
                drzava.setDvoslovnaSifra(entityArray[3] != null ? (String) entityArray[3] : "");
                drzava.setTroslovnaSifra(entityArray[4] != null ? (String) entityArray[4] : "");
                drzava.setIsOffshore(entityArray[5] != null ? entityArray[5].toString() : "");
                drzava.setTelefonskiPredbroj(entityArray[6] != null ? (String) entityArray[6] : "");
                drzava.setTransactionType(entityArray[7] != null ? entityArray[8].toString() : "");
            } catch (NoResultException|NonUniqueResultException nre) {
                log.info(nre.getMessage());
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return drzava;
    }

    /**
     * Metoda na temelju valjanog IBAN vraća slovčanu oznaku države korisnika.
     *
     * @param iban
     * @return drzava
     */
    public static String resolveDrzavaOznkaFromIban(String iban) {
        String oznakaDrz = null;
        if (iban != null && iban.length() >= IbanFormatDto.IBAN_COUNTRY_CODE_LENGTH && StringUtils.isAlpha(iban.substring(0, IbanFormatDto.IBAN_COUNTRY_CODE_LENGTH))) {
            oznakaDrz = iban.trim().replace("/", "").substring(0, IbanFormatDto.IBAN_COUNTRY_CODE_LENGTH).toUpperCase();

        }
        return oznakaDrz;
    }

    /**
     * Metoda na temelju valjanog IBAN vraća vbdi korisnika.
     *
     * @param iban
     * @return vbdi
     */
    public static String resolveVbdiFromIban(String iban, EntityManager em) {
        String vbdi = null;
        if (StringUtils.isNotBlank(iban)) {
            iban = StringUtils.replace(iban.trim(),"/", "");
            if (iban.length() >= IbanFormatDto.IBAN_MIN_LENGTH && StringUtils.isAlpha(iban.substring(0, IbanFormatDto.IBAN_COUNTRY_CODE_LENGTH))) {
                try {
                    Query query = em.createNativeQuery("SELECT banfd_ibf, banld_ibf, brafd_ibf, brald_ibf FROM iban_format where iban_format.ctry2_ibf = :drzava");
                    query.setParameter("drzava", iban.substring(0, IbanFormatDto.IBAN_COUNTRY_CODE_LENGTH).toUpperCase());
                    Object[] obj = (Object[]) query.getSingleResult();
                    IbanFormatDto ibanFormatDto = new IbanFormatDto();
                    int i = 0;
                    ibanFormatDto.setBankFirstDigitPosition((Integer) (obj[i] != null ? obj[i] : null));
                    ibanFormatDto.setBankLastDigitPosition((Integer) (obj[++i] != null ? obj[i] : null));
                    ibanFormatDto.setBranchFirstDigitPosition((Integer) (obj[i] != null ? obj[i] : null));
                    ibanFormatDto.setBranchLastDigitPosition((Integer) (obj[++i] != null ? obj[i] : null));
                    if (ibanFormatDto.getNationalSortingCodeFirstPosition() != IbanFormatDto.NO_DIGIT_POSITION && ibanFormatDto.getNationalSortingCodeLastPosition() != IbanFormatDto.NO_DIGIT_POSITION) {
                        vbdi = StringUtils.substring(iban, ibanFormatDto.getNationalSortingCodeFirstPosition(), ibanFormatDto.getNationalSortingCodeLastPosition());
                    }
                } catch (NoResultException nre) {
                    log.info(nre.getMessage());
                } catch (NonUniqueResultException nre) {
                	log.info(nre.getMessage());
                } catch (Exception e) {
                	log.error(e.getMessage());
                }
            }
        }
        return vbdi;
    }

    /**
     * Metoda na temelju valjanog IBAN-a i pripadajućeg formata vraća vbdi banke.
     *
     * @param iban
     * @param ibanFormatDto
     * @return vbdi
     */
    public static String resolveVbdiFromIbanFormat(String iban, IbanFormatDto ibanFormatDto) {
        String vbdi = null;
        if (StringUtils.isNotBlank(iban) && ibanFormatDto != null && ibanFormatDto.getNationalSortingCodeFirstPosition() != IbanFormatDto.NO_DIGIT_POSITION && ibanFormatDto.getNationalSortingCodeLastPosition() != IbanFormatDto.NO_DIGIT_POSITION) {
            vbdi = StringUtils.substring(iban, ibanFormatDto.getNationalSortingCodeFirstPosition(), ibanFormatDto.getNationalSortingCodeLastPosition());
        }
        return vbdi;
    }

    /**
     * Metoda na temelju valjanog IBAN vraća partiju
     *
     * @param iban
     * @return partija
     */
    public static String resolvePartijaFromIban(String iban) {
        String partija = iban != null && iban.length() >= 11 ? iban.trim().replace("/", "").substring(11) : null;
        return partija;
    }

    private static String changeLetters(String iban) {
        String result = "", asc = "";
        for (int i = 0; i < iban.length(); i++) {
            asc = String.valueOf(iban.charAt(i));
            if (asc.compareTo("A") >= 0 && asc.compareTo("Z") <= 0) {
                int num = (int) iban.charAt(i) - 55;
                asc = "" + num;
            }
            result = result + asc;
        }
        return result;
    }

    private static Integer myMod(String iban, Integer num) {
        String rem = "";
        for (int i = 0; i < iban.length(); i++) {
            rem += iban.charAt(i);
            rem = String.valueOf(Integer.valueOf(rem) % num);
        }
        return Integer.valueOf(rem);
    }

    /**
     * Metoda iz tekstualnog polja pokušava pronaći partiju koja može biti u obliku VBDI-partija, partija, IBAN, itd.
     * Vrijedi samo za HR tip ibana/bbana
     *
     * @param input
     * @return
     */
    public static Map<String, String> resolveVbdiPartijaFromText(String input) {
        Map<String, String> result = new HashMap<String, String>();
        String vbdi = "", partija = "";
        if (StringUtils.isNotBlank(input)) {
            switch (input.length()) {
                case 10:
                    vbdi = Bassx2MinConstants.PlatniPromet.NKSBANKA_VBDINBAN_BANKA;
                    partija = input;
                    break;
                case 17:
                    vbdi = input.substring(0, 7);
                    partija = input.substring(7, 17);
                    break;
                case 18:
                    vbdi = input.substring(0, 7);
                    partija = input.substring(8, 18);
                    break;
                case 21:
                    if (IbanFunctions.isIban(input.substring(0, 21))) {
                        vbdi = input.substring(4, 11);
                        partija = input.substring(11, 21);
                    }
                    break;
            }
        }
        result.put(VBDI_ID, vbdi);
        result.put(PARTIJA_ID, partija);
        return result;
    }

    /**
     * Metoda iz polja SWIFT-a razrješuje IBAN korisnika.
     *
     * @param field
     * @return
     */
    public static String resolveIbanFromSwift(String field) {
        String iban = null;
        if (StringUtils.isNotBlank(field)) {
            if (field.contains("\r\n")) {
                iban = field.substring(0, field.indexOf("\r\n"));
            } else {
                iban = field.substring(0);
            }
            // uklanjanje specijalnog znaka za početak računa '/'
            iban = iban.replace("/", "");
            if (!IbanFunctions.isIban(iban)) {
                iban = null;
            }
        }
        return iban;
    }

    /**
     * Prema VBDI-u i partiji metoda formira HR iban.
     *
     * @param vbdi
     * @param partija
     * @return
     */
    public static String getIbanHr(String vbdi, String partija) {
        BigInteger x = new BigInteger(vbdi.trim() + partija.trim() + "172700");
        DecimalFormat f = new DecimalFormat("00");
        x = new BigInteger("98").subtract(x.mod(new BigInteger("97")));
        return "HR" + f.format(x) + vbdi.trim() + partija.trim();
    }

    public static String validateAndCreateIban(String racun, EntityManager em) throws AbitRuleException {
        String iban = "";
        if (StringUtils.isNotBlank(racun)) {
//            if (Pattern.compile("[A-Z]{2}").matcher(racun).find()) { ivica -
                //formatiranje ulaznog argumenta
                String racunTemp = racun.trim().replace("-", "").replace(" ", "").toUpperCase();
                iban = racunTemp;
                //if (racunTemp.startsWith("HR") || Character.isDigit(racunTemp.charAt(0))) {
                if (racunTemp.startsWith("HR")) {
                    iban = validateAndCreateIbanHr(racunTemp, em);
                }
                if (iban.length() > IbanFormatDto.IBAN_MAX_LENGTH) {
                    throw new AbitRuleException(InternationalizationUtil.getLocalMessage("ibanFunctions_ibanMaxLenght", GenericBassxConstants.CoreHub.MESSAGE_BUNDLE ));
                }
                if (!IbanFunctions.isIban(iban)) {
                    throw new AbitRuleException(InternationalizationUtil.getLocalMessage("ibanFunctions_ibanValidationError", GenericBassxConstants.CoreHub.MESSAGE_BUNDLE));
                }
//            } else {
//                iban = racun;
//            }
        } else {
            throw new AbitRuleException(InternationalizationUtil.getLocalMessage("ibanFunctions_ibanWrongFormat", GenericBassxConstants.CoreHub.MESSAGE_BUNDLE));
        }
        return iban;
    }

    @SuppressWarnings("unused")
    public static String validateAndCreateIbanHr(String racun, EntityManager em) throws AbitRuleException {
        String iban = "";
        if (StringUtils.isNotBlank(racun)) {
            //formatiranje ulaznog argumenta
            String racunTemp = racun.trim().replace("-", "").replace(" ", "").toUpperCase();
            iban = racunTemp;
            if (racunTemp.length() == GenericBassxConstants.CoreHub.DULJINA_RACUNA) {
                //prosljeđena partija, formira se iban iz NksBanka.VBDINBAN_HOME_BANK i partije
                iban = IbanFunctions.getIbanHr(Bassx2MinConstants.PlatniPromet.NKSBANKA_VBDINBAN_BANKA, racunTemp);
            } else if (racunTemp.trim().length() == 17 || racunTemp.trim().length() == 18) {
                //prosljeđeno VBDI+partija, formira se iban
                HashMap<String, String> ibanMap = (HashMap<String, String>) IbanFunctions.resolveVbdiPartijaFromText(racunTemp);
                String vbdi = ibanMap.get(IbanFunctions.VBDI_ID);
                String partija = ibanMap.get(IbanFunctions.PARTIJA_ID);
                iban = IbanFunctions.getIbanHr(vbdi, partija);
            } else {
                if (racunTemp.length() != GenericBassxConstants.CoreHub.DULJINA_RACUNA_IBAN) {
                    throw new AbitRuleException(InternationalizationUtil.getLocalMessage("ibanFunctions_wrongHrIbanLength", GenericBassxConstants.CoreHub.MESSAGE_BUNDLE));
                } else if (!racunTemp.startsWith("HR")) {
                    throw new AbitRuleException(InternationalizationUtil.getLocalMessage("ibanFunctions_wrongHrIbanCountryCode", GenericBassxConstants.CoreHub.MESSAGE_BUNDLE));
                } else if (!IbanFunctions.isIban(racunTemp)) {
                    throw new AbitRuleException(InternationalizationUtil.getLocalMessage("ibanFunctions_ibanValidationError", GenericBassxConstants.CoreHub.MESSAGE_BUNDLE));
                } else {
                    //ivica - ovaj dio koda nije imao smisla, nije radio ništa, pa sam maknul
                }
            }
        } else {
            throw new AbitRuleException(InternationalizationUtil.getLocalMessage("ibanFunctions_ibanWrongFormat", GenericBassxConstants.CoreHub.MESSAGE_BUNDLE));
        }
        return iban;
    }

    public static String getIbanHrHtml(String iban) {
        String ibanHtml = iban;
        if (ibanHtml != null && ibanHtml.trim().length() == GenericBassxConstants.CoreHub.DULJINA_RACUNA_IBAN) {
            ibanHtml = ibanHtml.substring(0, 4) + " " +
                    ibanHtml.substring(4, 8) + " " +
                    ibanHtml.substring(8, 11) + "<b>" + ibanHtml.substring(11, 12) + " " +
                    ibanHtml.substring(12, 16) + " " +
                    ibanHtml.substring(16, 20) + " " +
                    ibanHtml.substring(20) + "</b>";
        }
        return ibanHtml;
    }

    public static String razdjeliIban(String iban) {
        String ibanHtml = iban;
        if (ibanHtml != null && ibanHtml.trim().length() == GenericBassxConstants.CoreHub.DULJINA_RACUNA_IBAN) {
            ibanHtml = ibanHtml.substring(0, 4) + " " +
                    ibanHtml.substring(4, 8) + " " +
                    ibanHtml.substring(8, 12) + " " +
                    ibanHtml.substring(12, 16) + " " +
                    ibanHtml.substring(16, 20) + " " +
                    ibanHtml.substring(20);
        }
        return ibanHtml;
    }
    
}