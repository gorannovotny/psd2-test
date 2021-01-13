package hr.abc.psd2.util;

/**
 * Konstante potrebne za obradu SEPA poruka
 */
public class SepaConstants {

    public static final String SEPA_PARAM_PREFIX = "SEPA";
    public static final String SEPA_DIRECTORY = "/data/SEPA/";
    public static final String SEPA_DIRECTORY_OUT = "OUT/";
    public static final String SEPA_DIRECTORY_IN = "IN/";
    public static final String SEPA_DIRECTORY_ARCHIVE = "ARHIVA/";
    public static final String SEPA_INPUT_LABEL = "I";
    public static final String SEPA_OUTPUT_LABEL = "O";
    public static final String SEPA_FILE_EXTENSION_XML = ".XML";
    public static final String SEPA_FILE_EXTENSION_ZIP = ".ZIP";
    public static final String SEPA_FILE_PATTERN = "*" + SEPA_FILE_EXTENSION_XML;
    public static final String SEPA_REGEX_ALLOWED_CHARACTERS_TEXT = "[ćčđšžĆČĐŠŽa-zA-Z0-9&/?:().,'+ -]";
    public static final String SEPA_REGEX_ALLOWED_CHARACTERS_FIRST_CHARACTER = "[ćčđšžĆČĐŠŽa-zA-Z0-9]";
    public static final String SEPA_REGEX_ALLOWED_CHARACTERS_IBAN = "[a-zA-Z0-9-]";

    public static final String FINA_SWIFT_BIC8 = "FNAGHR22";
    public static final String FINA_SWIFT_BIC8_TEST = "FNAGHR20";
    public static final String SEPA_SCT_INPUT_FILE_REFERENCE_PREFIX_INP = "INP";
    public static final String SEPA_SCT_INPUT_FILE_REFERENCE_PREFIX_INQ = "INQ";
    public static final String SEPA_SCT_OUTPUT_FILE_REFERENCE_PREFIX_OUT = "OUT";
    public static final String SEPA_SCT_OUTPUT_FILE_REFERENCE_PREFIX_OUQ = "OUQ";
    public static final String SEPA_CLEARING_HRK = "NKK";
    public static final String SEPA_CLEARING_EUR = "NKE";
    public static final String SEPA_CODE_PRODUCTION = "P";
    public static final String SEPA_CODE_TEST = "T";
    public static final String SEPA_SCT_INP_HRK = "111";
    public static final String SEPA_SCT_INP_EUR = "311";
    public static final String SEPA_SCT_OUT_HRK = "151";
    public static final String SEPA_SCT_OUT_EUR = "351";
    public static final String SEPA_SCT_INQ_HRK = "116";
    public static final String SEPA_SCT_INQ_EUR = "316";
    public static final String SEPA_SCT_OUQ_HRK = "156";
    public static final String SEPA_SCT_OUQ_EUR = "356";
    public static final String SEPA_CREATION_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    public static final String SEPA_MESSAGE_PACS002 = "PACS.002";
    public static final String SEPA_MESSAGE_PACS004 = "PACS.004";
    public static final String SEPA_MESSAGE_PACS008 = "PACS.008";
    public static final String SEPA_MESSAGE_PACS028 = "PACS.028";
    public static final String SEPA_MESSAGE_CAMT027 = "CAMT.027";
    public static final String SEPA_MESSAGE_CAMT029 = "CAMT.029";
    public static final String SEPA_MESSAGE_CAMT029V03 = "CAMT.029v03";
    public static final String SEPA_MESSAGE_CAMT029V08 = "CAMT.029v08";
    public static final String SEPA_MESSAGE_CAMT056 = "CAMT.056";
    public static final String SEPA_MESSAGE_CAMT087 = "CAMT.087";

    public static final String SEPA_MESSAGE_PACS002_TRANSACTION_STATUS = "TRANSACTION STATUS";
    public static final String SEPA_MESSAGE_PACS008_CREDIT_TRANSFER = "CREDIT TRANSFERS";
    public static final String SEPA_MESSAGE_CAMT056_CANCELATION_REQUEST = "CANCELATION REQUESTS";
    public static final String SEPA_MESSAGE_PACS004_RETURN_TRANSACTION = "RETURN TRANSACTIONS";
    public static final String SEPA_MESSAGE_CAMT029_CANCELATION_DETAILS = "CANCELATION DETAILS";
    public static final String SEPA_MESSAGE_CAMT029_MODIFICATION_DETAILS = "MODIFICATION DETAILS";
    public static final String SEPA_MESSAGE_CAMT027_CAMT087_MODIFICATION_REQUEST = "MODIFICATION REQUESTS";
    public static final String SEPA_MESSAGE_PACS028_INQUIRY_STATUS = "INQUIRY STATUS";

    public static final int SEPA_SCT_FILE_ID_LENGTH = 7;
    public static final int SEPA_SCT_FILE_REF_LENGTH = 16;

    public static final String SEPA_CLEARING_SYSTEM = "NKS9999998";
    public static final String SEPA_SETTLEMENT_METHOD_CLRG = "CLRG";
    public static final String SEPA_SERVICE_LEVEL_CODE_SEPA = "SEPA";
    public static final String SEPA_CHARGE_BEARER_SLEV = "SLEV";
    public static final String SEPA_REMITTANCE_INFORMATION_STRUCTURED_TYPE_CODE_SCOR = "SCOR";

    public static final String SEPA_TRANSACTION_STATUS_REASON_CODE_FOCR = "FOCR";
    public static final String SEPA_IDENTIFICATION_TYPE_ORGANISATION = "O";
    public static final String SEPA_IDENTIFICATION_TYPE_PERSONAL = "P";

    public static final String SEPA_CODE_AC03 = "AC03";
    public static final String SEPA_CODE_AC04 = "AC04";
    public static final String SEPA_CODE_ACNR = "ACNR";
    public static final String SEPA_CODE_ACVA = "ACVA";
    public static final String SEPA_CODE_AM04 = "AM04";
    public static final String SEPA_CODE_AM09 = "AM09";
    public static final String SEPA_CODE_ARDT = "ARDT";
    public static final String SEPA_CODE_ARJT = "ARJT";
    public static final String SEPA_CODE_ASCS = "ACSC";
    public static final String SEPA_CODE_CUST = "CUST";
    public static final String SEPA_CODE_CVAA = "CVAA";
    public static final String SEPA_CODE_DUPL = "DUPL";
    public static final String SEPA_CODE_FRAD = "FRAD";
    public static final String SEPA_CODE_INQR = "INQR";
    public static final String SEPA_CODE_LEGL = "LEGL";
    public static final String SEPA_CODE_MODI = "MODI";
    public static final String SEPA_CODE_NOAS = "NOAS";
    public static final String SEPA_CODE_NOOR = "NOOR";
    public static final String SEPA_CODE_RJCR = "RJCR";
    public static final String SEPA_CODE_RJNR = "RJNR";
    public static final String SEPA_CODE_RJCT = "RJCT";
    public static final String SEPA_CODE_RJVA = "RJVA";
    public static final String SEPA_CODE_RNPR = "RNPR";
    public static final String SEPA_CODE_RR04 = "RR04";
    public static final String SEPA_CODE_VADA = "VADA";


    public static final String SEPA_PACS004_MSGID_PREFIX = "RTN";
    public static final String SEPA_PACS008_MSGID_PREFIX = "SCT";
    public static final String SEPA_PACS028_MSGID_PREFIX = "PSR";
    public static final String SEPA_CAMT029_MSGID_PREFIX = "ROI";
    public static final String SEPA_CAMT029_MSGID_PREFIX_OUQ = "ROQ";
    public static final String SEPA_CAMT056_MSGID_PREFIX = "PCR";
    public static final String SEPA_CAMT027_MSGID_PREFIX = "CNR";
    public static final String SEPA_CAMT087_MSGID_PREFIX = "RMP";
    public static final String SEPA_MODEL_DEFAULT_OLD = "99";
    public static final String SEPA_MODEL_DEFAULT = "HR99";
    public static final String SEPA_MODEL_POZIV_DEFAULT = "HR00";
    public static final String SEPA_REFERENCE_NOTPROVIDED = "NOTPROVIDED";

}
