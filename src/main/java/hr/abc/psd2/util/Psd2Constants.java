package hr.abc.psd2.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Psd2Constants {
    public static final String DATE_PSD2_FORMAT = "yyyy-MM-dd";
    
    // HTTP ststus constants
    public static final String HTTP_STATUS_CODE_NOT_FOUND_404 = "404";
    public static final String HTTP_STATUS_CODE_UNAUTHORIZED_401 = "401";
    public static final String HTTP_STATUS_CODE_FORBIDDEN_403 = "403";
    public static final String HTTP_STATUS_CODE_REQUEST_TIMEOUT_408 = "408";
    public static final String HTTP_STATUS_CODE_INTERNAL_ERROR_500 = "500";
    public static final List<String> LIST_HTTP_STATUS_ERROR_CODES = Arrays.asList(HTTP_STATUS_CODE_NOT_FOUND_404, HTTP_STATUS_CODE_UNAUTHORIZED_401, HTTP_STATUS_CODE_FORBIDDEN_403, HTTP_STATUS_CODE_REQUEST_TIMEOUT_408, HTTP_STATUS_CODE_INTERNAL_ERROR_500);


    //HTTP URI
    public static final String PARAMETAR_PSD2_LINK = "PSD2_LINK";
    public static final String LINK_PSD2_BACK = "/psd2/resources/psd2/";
    public static final String LINK_IB = "/ib/resources/ib-psd2-xs2a/";
    public static final String LINK_PSD2_BACK_UTIL = "/psd2-xs2a-back/resources/psd2-xs2a-back/util/";
    public static final String LINK_PLATNI = "/platni/resources/platni-psd2-xs2a/";

    public static final String PARAMETAR_PSD2_REDIRECT_LINK = "PSD2_RED_LINK";
    //dijelovi linka za scaRedirect potpisivanje - nalog, consent
    public static final String LINK_SCA_REDIRECT_SIGN_CREATE_NALOG = "/psd2/app/tppSignSingle.xhtml?id=";
    public static final String LINK_SCA_REDIRECT_SIGN_MULTI_CREATE_NALOG = "/psd2/app/tppSignMulti.xhtml?id=";
    public static final String LINK_SCA_REDIRECT_SIGN_CANCEL_NALOG = "/psd2/app/tppCancelSingle.xhtml?id=";
    public static final String LINK_SCA_REDIRECT_SIGN_MULTI_CANCEL_NALOG = "/psd2/app/tppCancelMulti.xhtml?id=";
    public static final String LINK_SCA_REDIRECT_SIGN_CREATE_COSENT = "/psd2/app/tppSignConsent.xhtml?id=";
    public static final String LINK_SCA_REDIRECT_SIGN_CANCEL_COSENT = "/psd2/app/tppCancelConsent.xhtml?id=";
    public static final String LINK_SCA_REDIRECT_SIGN_CREATE_BASKET = "/psd2/app/tppSignBasket.xhtml?id=";
    public static final String LINK_SCA_REDIRECT_SIGN_CREATE_CONSENT_COF = "/psd2/app/tppSignConsentCof.xhtml?id=";
    public static final String LINK_SCA_REDIRECT_SIGN_CANCEL_CONSENT_COF = "/psd2/app/tppCancelConsentCof.xhtml?id=";
    public static final String LINK_SCA_REDIRECT_SIGN_CREATE_EXCHANGE_NALOG = "/psd2/app/tppSignExchange.xhtml?id=";

    //ELASTIC SEARCH
    public static final String PARAMETAR_ELASTIC_SEARCH_LINK = "PSD2_ES_LINK";
    public static final String SINGLE_PAYMENT_INIT_REQ = "Single Payment Initiation Request";
    public static final String SINGLE_PAYMENT_INIT_RES = "Single Payment Initiation Response";
    public static final String SINGLE_PAYMENT_CANCEL_REQ = "Payment Cancellation Request";
    public static final String SINGLE_PAYMENT_CANCEL_RES = "Payment Cancellation Response";
    public static final String SINGLE_PAYMENT_GET_PAYMENT_INFO_REQ = "Get Payment Information Request";
    public static final String SINGLE_PAYMENT_GET_PAYMENT_INFO_RES = "Get Payment Information Response";
    public static final String SINGLE_PAYMENT_GET_PAYMENT_STATE_REQ = "Payment Initiation Status Request";
    public static final String SINGLE_PAYMENT_GET_PAYMENT_STATE_RES = "Payment Initiation Status Response";
    public static final String SINGLE_PAYMENT_START_AUTH_REQ = "Start single payment authorisation process request";
    public static final String SINGLE_PAYMENT_START_AUTH_RES = "Start single payment authorisation process response";
    public static final String SINGLE_PAYMENT_GET_AUTH_REQ = "Get payment initiation authorization sub-resources request";
    public static final String SINGLE_PAYMENT_GET_AUTH_RES = "Get payment initiation authorization sub-resources response";

    public static final String BULK_PAYMENT_INIT_REQ = "Bulk Payment Initiation Request";
    public static final String BULK_PAYMENT_INIT_RES = "Bulk Payment Initiation Response";
    public static final String BULK_PAYMENT_GET_PAYMENT_STATE_REQ = "Payment Initiation Status Request";
    public static final String BULK_PAYMENT_GET_PAYMENT_STATE_RES = "Payment Initiation Status Response";
    public static final String BULK_PAYMENT_GET_PAYMENT_INFO_REQ = "Get Bulk Payment Information Request";
    public static final String BULK_PAYMENT_GET_PAYMENT_INFO_RES = "Get Bulk Payment Information Response";
    public static final String BULK_PAYMENT_CANCEL_REQ = "Bulk Payment Cancellation Request";
    public static final String BULK_PAYMENT_CANCEL_RES = "Bulk Payment Cancellation Response";
    public static final String BULK_PAYMENT_START_AUTH_REQ = "Start bulk payment authorisation process request";
    public static final String BULK_PAYMENT_START_AUTH_RES = "Start bulk payment authorisation process response";
    public static final String BULK_PAYMENT_GET_AUTH_REQ = "Get bulk payment initiation authorization sub-resources request";
    public static final String BULK_PAYMENT_GET_AUTH_RES = "Get bulk payment initiation authorization sub-resources response";
    public static final String BULK_PAYMENT_GET_SCA_STA_AUTH_REQ = "Get SCA status of the bulk payment authorisation request";
    public static final String BULK_PAYMENT_GET_SCA_STA_AUTH_RES = "Get SCA status of the bulk payment authorisation response";
    public static final String BULK_PAYMENT_POST_CANC_AUTH_REQ = "Start bulk payment cancellation authorization process request";
    public static final String BULK_PAYMENT_POST_CANC_AUTH_RES = "Start bulk payment cancellation authorization process response";
    public static final String BULK_PAYMENT_GET_CANC_AUTH_REQ = "Get all created cancellation authorisation sub-resources request(bulk payment)";
    public static final String BULK_PAYMENT_GET_CANC_AUTH_RES = "Get all created cancellation authorisation sub-resources response(bulk payment)";
    public static final String BULK_PAYMENT_GET_SCA_ST_CANC_AUTH_REQ = "Get SCA status of the bulk payment cancellation's authorisation request";
    public static final String BULK_PAYMENT_GET_SCA_ST_CANC_AUTH_RES = "Get SCA status of the bulk payment cancellation's authorisation response";


    public static final String SINGLE_PAYMENT_GET_SCA_ST_CANC_AUTH_REQ = "Get SCA status of the payment cancellation's authorisation request";
    public static final String SINGLE_PAYMENT_GET_SCA_ST_CANC_AUTH_RES = "Get SCA status of the payment cancellation's authorisation response";

    public static final String SINGLE_PAYMENT_GET_CANC_AUTH_REQ = "Get all created cancellation authorisation sub-resources request";
    public static final String SINGLE_PAYMENT_GET_CANC_AUTH_RES = "Get all created cancellation authorisation sub-resources response";

    public static final String SINGLE_PAYMENT_POST_CANC_AUTH_REQ = "Start cancellation authorization process request";
    public static final String SINGLE_PAYMENT_POST_CANC_AUTH_RES = "Start cancellation authorization process response";

    public static final String SINGLE_PAYMENT_GET_SCA_STA_AUTH_REQ = "Get SCA status of the payment authorisation request";
    public static final String SINGLE_PAYMENT_GET_SCA_STA_AUTH_RES = "Get SCA status of the payment authorisation response";

    public static final String CONFIRMATION_OF_FOUNDS_REQ = "Confirmation of founds request";
    public static final String CONFIRMATION_OF_FOUNDS_RES = "Confirmation of founds response";

    public static final String SIGNING_BASKET_CREATE_REQ = "Create a signing basket request";
    public static final String SIGNING_BASKET_CREATE_RES = "Create a signing basket response";
    public static final String SIGNING_BASKET_GET__REQ = "Get signing basket request";
    public static final String SIGNING_BASKET_GET__RES = "Get signing basket response";
    public static final String DELETE_SIGNING_BASKET_REQ = "Delete signing basket request";
    public static final String DELETE_SIGNING_BASKET_RES = "Delete signing basket response";
    public static final String START_SIGNING_BASKET_AUTH_REQ = "Start signing basket authorisation request";
    public static final String START_SIGNING_BASKET_AUTH_RES = "Start signing basket authorisation response";
    public static final String GET_SIGNING_BASKET_SCA_STATUS_REQ = "Get signing basket sca status request";
    public static final String GET_SIGNING_BASKET_SCA_STATUS_RES = "Get signing basket sca status response";
	public static final String GET_SIGNING_BASKET_STATUS_REQUEST_REQ = "Get signing basket status request";
    public static final String GET_SIGNING_BASKET_STATUS_REQUEST_RES = "Get signing basket status response";

    //confirmation of founds consent
    public static final String CREATE_CONSENT_COF_REQ = "Create confirmation of founds consent request";
    public static final String CREATE_CONSENT_COF_RES = "Create confirmation of founds consent response";
    public static final String GET_CONSENT_COF_REQ = "Get confirmation of founds consent request";
    public static final String GET_CONSENT_COF_RES = "Get confirmation of founds consent response";
    public static final String GET_CONSENT_COF_STATUS_REQ = "Get confirmation of founds consent status request";
    public static final String GET_CONSENT_COF_STATUS_RES = "Get confirmation of founds consent status response";
    public static final String DELETE_CONSENT_COF_REQ = "Delete confirmation of founds consent request";
    public static final String DELETE_CONSENT_COF_RES = "Delete confirmation of founds consent response";
    public static final String START_CONSENT_COF_AUTH_REQ = "Start confirmation of founds consent authorisation request";
    public static final String START_CONSENT_COF_AUTH_RES = "Start confirmation of founds consent authorisation response";
    public static final String GET_CONSENT_COF_AUTH_REQ = "Get confirmation of founds consent authorisation request";
    public static final String GET_CONSENT_COF_AUTH_RES = "Get confirmation of founds consent authorisation response";
    public static final String GET_CONSENT_COF_SCA_STAT_REQ = "Get confirmation of founds consent sca status request";
    public static final String GET_CONSENT_COF_SCA_STAT_RES = "Get confirmation of founds consent sca status response";



    //METHODS
    //AIS CONSENT
    public static final String METHOD_CREATE_AIS_CONSENT = "consent/createAisConsent";
    //AIS
    public static final String METHOD_GET_ACCOUNT_DETAILS = "accounts/getAccountList";
    public static final String METHOD_GET_ACCOUNT_BALANCESS = "accounts/getAccountBalances";
    //PIS
    public static final String METHOD_CREATE_TPP_NALOG = "payments/createTppNalog";
    public static final String METHOD_VALIDATE_PSD_NALOG = "payments/validateNalog";


    //MODUL
    public static final String MODUL_PSD2_BACK = "PSD2_BACK";
    public static final String MODUL_IB = "IB";
    public static final String MODUL_PLATNI = "PLATNI";

    //MESSAGES
    public static final String MESSAGE_BUNDLE_PSD_HUB = "hr.abit.b3.psd2.xs2a.message.psd2HubMessages";

    //PSD2 AUTHORIZATION VALIDATE UNTIL PARAM
    public static final String PARAMETAR_PSD2_AUTH_TODATE = "PSD2_AUT_VALI";

    public enum RequestHeaderAttributes {
        ACCEPT("Accept"),
        X_REQUEST_ID("X-Request-ID"),
        PSU_ID("PSU-ID"),
        CONSENT_ID("Consent-ID"),
        TPP_REGISTRATION_NUMBER("tpp-registration-number"),
        TPP_NAME("tpp-name"),
        TPP_ID("tpp-id"),
        TPP_ROLE("tpp-role"),
        CONTENT_TYPE("Content-Type"),
        PSU_IP_ADDRESS("PSU-IP-Address"),
        X_FORWARDED_FOR("X-Forwarded-For"),
        DIGEST("Digest"),
        SIGNATURE("Signature"),
        TPP_SIGNATURE_CERTIFICATE("TPP-Signature-Certificate"),
        PSU_ID_TYPE("PSU-ID-Type"),
        PSU_CORPORATE_ID("PSU-Corporate-ID"),
        PSU_CORPORATE_ID_TYPE("PSU-Corporate-ID-Type"),
        AUTHORIZATION("Authorization"),
        TPP_REDIRECT_PREFERRED("TPP-Redirect-Preferred"),
        TPP_REDIRECT_URI("TPP-Redirect-URI"),
        TPP_NOK_REDIRECT_URI("TPP-Nok-Redirect-URI"),
        TPP_SEPERATE_AUTHORISATION_PREFERRED("TPP-Separate-Authorisation-Preferred");

        private String value;

        RequestHeaderAttributes(String status) {
            this.value = status;
        }

        public String getValue() {
            return value;
        }
    }

    public enum ResponsetHeaderAttributes {
        X_REQUEST_ID("X-Request-ID"),
        LOCATION("Location"),
        ASPSP_SCA_APPROACH("ASPSP-SCA-Approach"),
        DIGEST("Digest"),
        TPP_SIGNATURE_CERTIFICATE("TPP-Signature-Certificate"),
        SIGNATURE("Signature"),
        DATE("Date");

        private String value;

        ResponsetHeaderAttributes(String status) {
            this.value = status;
        }

        public String getValue() {
            return value;
        }
    }

    public static final String PARAMETAR_PSD2_VALIDATE_X_REQUEST_ID = "PSD2_XREQ_ID";
    public static final String PSD2_X_REQUEST_ID_DONT_VALIDATE = "0";
    public static final String PSD2_X_REQUEST_ID_VALIDATE = "1";

    public enum TppRole {
        PSP_AI,  //     Account Information Service Provider
        PSP_AS,  // Account Servicing Payment Service Provider
        PSP_IC,  // Payment Service Provider Issuing Card-based payment instruments
        PSP_PI  //Payment Initiation Service Provider
    }

    public enum BalanceType {
        CLOSING_BOOKED("closingBooked"),
        EXPECTED("expected"),
        OPENING_BOOKED("openingBooked"),
        INTERIM_AVAILABLE("interimAvailable"),
        //INTERIM_BOOKED("interimBooked"),
        FORWARD_AVAILABLE("forwardAvailable"),
        NON_INVOICED("nonInvoiced");
        //AVAILABLE("available ");

        private String value;

        BalanceType(String status) {
            this.value = status;
        }

        public String getValue() {
            return value;
        }
    }

    public enum ScaMethod {
        REDIRECT_IMPLICT,
        REDIRECT_EXPLICIT,
    }

    public enum AuthorizationObjectType {
        NALOG_TPP_AUTHORIZATION(1),
        CONSENT_AUTHORIZATION(2),
        BASKET_AUTHORIZATION(3),
        CONSENT_COF_AUTHORUZATION(4);

        private final int value;

        AuthorizationObjectType(final int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    public enum AuthorizationType {
        CREATED(1),
        CANCELLED(2);

        private final int value;

        AuthorizationType(final int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

    public enum BasketType {
        NALOG(1),
        CONSENT(2);

        private final int value;

        BasketType(final int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

    public enum BasketStatus {
        /**
         * summary: Basket status
         * description:
         * RECEIVED: Payment initiation has been received by the receiving
         * agent.
         * -----------------------------------------------------------------------------
         * ACCEPTED_TECHNICAL_VALIDATION: Authentication and syntactical and semantical
         * validation are successful
         * ------------------------------------------------------------------------------
         * PARTIALLY_ACCEPTED_TECHNICAL_CORRECT: Payment initiations which are at least authorised by one PSU, but which are not yet finally
         * authorised by all applicable PSU
         * ------------------------------------------------------------------------------
         * CANC: Payment initiation has been cancelled before execution
         * ------------------------------------------------------------------------------
         * REJECTED: Payment initiation or individual transaction included in
         * the payment initiation has been rejected
         * ------------------------------------------------------------------------------
         */
        RECEIVED ("RCVD"),
        ACCEPTED_TECHNICAL_VALIDATION ("ACTC"),
        PARTIALLY_ACCEPTED_TECHNICAL_CORRECT ("PATC"),
        CANCELLED ("CANC"),
        REJECTED ("RJCT");
        private String value;

        BasketStatus(String status) {
            this.value = status;
        }

        public String getValue() {
            return value;
        }
    }

    public enum ConsentTip{
        AIS ("AIS"),
        COF ("COF");
        private String value;
        ConsentTip (String status){
            this.value = status;
        }
        public String getValue() {
            return value;
        }
    }

    public static List<String> getTroskoviInoBanke() {
        List<String> listaTroskovnihOpcija = new ArrayList<>();
        listaTroskovnihOpcija.add(Bassx2Constants.PlatniPromet.DEVIZNI_NALOG_TROSAK_INO_BANKE_BEN);
        listaTroskovnihOpcija.add(Bassx2Constants.PlatniPromet.DEVIZNI_NALOG_TROSAK_INO_BANKE_OUR);
        listaTroskovnihOpcija.add(Bassx2Constants.PlatniPromet.DEVIZNI_NALOG_TROSAK_INO_BANKE_SHA);
        return listaTroskovnihOpcija;
    }

    public static final Map<String, String> PSD2_METHODS_REQUEST_LOG = Collections
            .unmodifiableMap(new HashMap<String, String>() {
                {
                    //consent
                    put("createConsent", "Create consent Request");
                    put("getConsentStatus", "Consent status Request");
                    put("getConsentInformation", "Get Consent Request");
                    put("deleteConsent", "Delete Consent Request");
                    put("startConsentAuthorisation", "Start Consent Authorization Request");
                    put("getConsentAuthorisation", "Consent initiation authorization Request");
                    put("getConsentScaStatus", "Consent SCA status Request");

                    //account information
                    put("getAccountList", "Read Account List Request");
                    put("readAccountDetails", "Read Account Details Request");
                    put("getBalances", "Read Balance Request");
                    put("getTransactionList", "Read Account Transactions Request");
                    put("getTransactionDetails", "Read Transaction Details Request");

                    //pis
                    put("initiatePayment", Psd2Constants.SINGLE_PAYMENT_INIT_REQ);
                    put("cancelPayment", Psd2Constants.SINGLE_PAYMENT_CANCEL_REQ);
                    put("getPaymentInformation", Psd2Constants.SINGLE_PAYMENT_GET_PAYMENT_INFO_REQ);
                    put("getPaymentInitiationStatus", Psd2Constants.SINGLE_PAYMENT_GET_PAYMENT_STATE_REQ);
                    put("startPaymentAuthorisation", Psd2Constants.SINGLE_PAYMENT_START_AUTH_REQ);
                    put("getPaymentInitiationAuthorisation", Psd2Constants.SINGLE_PAYMENT_GET_AUTH_REQ);
                    put("getPaymentInitiationScaStatus", Psd2Constants.SINGLE_PAYMENT_GET_SCA_STA_AUTH_REQ);
                    put("startPaymentInitiationCancellationAuthorisation", Psd2Constants.SINGLE_PAYMENT_POST_CANC_AUTH_REQ);
                    put("getPaymentInitiationCancellationAuthorisationInformation", Psd2Constants.SINGLE_PAYMENT_GET_CANC_AUTH_REQ);
                    put("getPaymentCancellationScaStatus", Psd2Constants.SINGLE_PAYMENT_GET_SCA_ST_CANC_AUTH_REQ);

                    //pis bulk
                    put("initiateBulkPayment", Psd2Constants.BULK_PAYMENT_INIT_REQ);
                    put("getBulkPaymentInitiationStatus", Psd2Constants.BULK_PAYMENT_GET_PAYMENT_STATE_REQ);
                    put("getBulkPaymentInformation", Psd2Constants.BULK_PAYMENT_GET_PAYMENT_INFO_REQ);
                    put("cancelBulkPayment", Psd2Constants.BULK_PAYMENT_CANCEL_REQ);
                    put("startBulkPaymentAuthorisation", Psd2Constants.BULK_PAYMENT_START_AUTH_REQ);
                    put("getBulkPaymentInitiationAuthorisation", Psd2Constants.BULK_PAYMENT_GET_AUTH_REQ);
                    put("getBulkPaymentInitiationScaStatus", Psd2Constants.BULK_PAYMENT_GET_SCA_STA_AUTH_REQ);
                    put("startBulkPaymentInitiationCancellationAuthorisation", Psd2Constants.BULK_PAYMENT_POST_CANC_AUTH_REQ);
                    put("getBulkPaymentInitiationCancellationAuthorisationInformation", Psd2Constants.BULK_PAYMENT_GET_CANC_AUTH_REQ);
                    put("getBulkPaymentCancellationScaStatus", Psd2Constants.BULK_PAYMENT_GET_SCA_ST_CANC_AUTH_REQ);

                    //founds confirmation
                    put("foundsConfirmations", Psd2Constants.CONFIRMATION_OF_FOUNDS_REQ);

                    //basket
                    put("createSigningBasket", Psd2Constants.SIGNING_BASKET_CREATE_REQ);
                    put("getSigningBasket", Psd2Constants.SIGNING_BASKET_GET__REQ);
                    put("deleteSigningBasket", Psd2Constants.DELETE_SIGNING_BASKET_REQ);
                    put("startSigningBasketAuthorisation", Psd2Constants.START_SIGNING_BASKET_AUTH_REQ);
                    put("getSigningBasketScaStatus", Psd2Constants.GET_SIGNING_BASKET_SCA_STATUS_REQ);
					put("getSigningBasketStatusRequest", Psd2Constants.GET_SIGNING_BASKET_STATUS_REQUEST_REQ);
                    //cof consent
                    put("createConsentConfirmationOfFunds", Psd2Constants.CONFIRMATION_OF_FOUNDS_REQ);
                    put("getConsentConfirmationOfFundsStatus", Psd2Constants.GET_CONSENT_COF_STATUS_REQ);
                    put("getConsentConfirmationOfFunds", Psd2Constants.GET_CONSENT_COF_REQ);
                    put("deleteConsentConfirmationOfFunds", Psd2Constants.DELETE_CONSENT_COF_REQ);
                    put("startConsentCofAuthorisation", Psd2Constants.START_CONSENT_COF_AUTH_REQ);
                    put("getConsentCofAuthorisation", Psd2Constants.GET_CONSENT_COF_AUTH_REQ);
                    put("getConsentCofScaStatus", Psd2Constants.GET_CONSENT_COF_SCA_STAT_REQ);

                }
            });

    public static final Map<String, String> PSD2_METHODS_RESPONSE_LOG = Collections
            .unmodifiableMap(new HashMap<String, String>() {
                {
                    //consent
                    put("createConsent", "Create consent Response");
                    put("getConsentStatus", "Consent status Response");
                    put("getConsentInformation", "Get Consent Response");
                    put("deleteConsent", "Delete Consent Response");
                    put("startConsentAuthorisation", "Start Consent Authorization Response");
                    put("getConsentAuthorisation", "Consent initiation authorization Response");
                    put("getConsentScaStatus", "Consent SCA status Response");

                    //account information
                    put("getAccountList", "Read Account List Response");
                    put("readAccountDetails", "Read Account Details Response");
                    put("getBalances", "Read Balance Response");
                    put("getTransactionList", "Read Account Transactions");
                    put("getTransactionDetails", "Read Transaction Details Response");

                    //pis
                    put("initiatePayment", Psd2Constants.SINGLE_PAYMENT_INIT_RES);
                    put("cancelPayment", Psd2Constants.SINGLE_PAYMENT_CANCEL_RES);
                    put("getPaymentInformation", Psd2Constants.SINGLE_PAYMENT_GET_PAYMENT_INFO_RES);
                    put("getPaymentInitiationStatus", Psd2Constants.SINGLE_PAYMENT_GET_PAYMENT_STATE_RES);
                    put("startPaymentAuthorisation", Psd2Constants.SINGLE_PAYMENT_START_AUTH_RES);
                    put("getPaymentInitiationAuthorisation", Psd2Constants.SINGLE_PAYMENT_GET_AUTH_RES);
                    put("getPaymentInitiationScaStatus", Psd2Constants.SINGLE_PAYMENT_GET_SCA_STA_AUTH_RES);
                    put("startPaymentInitiationCancellationAuthorisation", Psd2Constants.SINGLE_PAYMENT_POST_CANC_AUTH_RES);
                    put("getPaymentInitiationCancellationAuthorisationInformation", Psd2Constants.SINGLE_PAYMENT_GET_CANC_AUTH_RES);
                    put("getPaymentCancellationScaStatus", Psd2Constants.SINGLE_PAYMENT_GET_SCA_ST_CANC_AUTH_RES);

                    //pis bulk
                    put("initiateBulkPayment", Psd2Constants.BULK_PAYMENT_INIT_RES);
                    put("getBulkPaymentInitiationStatus", Psd2Constants.BULK_PAYMENT_GET_PAYMENT_STATE_RES);
                    put("getBulkPaymentInformation", Psd2Constants.BULK_PAYMENT_GET_PAYMENT_INFO_RES);
                    put("cancelBulkPayment", Psd2Constants.BULK_PAYMENT_CANCEL_RES);
                    put("startBulkPaymentAuthorisation", Psd2Constants.BULK_PAYMENT_START_AUTH_RES);
                    put("getBulkPaymentInitiationAuthorisation", Psd2Constants.BULK_PAYMENT_GET_AUTH_RES);
                    put("getBulkPaymentInitiationScaStatus", Psd2Constants.BULK_PAYMENT_GET_SCA_STA_AUTH_RES);
                    put("startBulkPaymentInitiationCancellationAuthorisation", Psd2Constants.BULK_PAYMENT_POST_CANC_AUTH_RES);
                    put("getBulkPaymentInitiationCancellationAuthorisationInformation", Psd2Constants.BULK_PAYMENT_GET_CANC_AUTH_RES);
                    put("getBulkPaymentCancellationScaStatus", Psd2Constants.BULK_PAYMENT_GET_SCA_ST_CANC_AUTH_RES);

                    //founds confirmation
                    put("foundsConfirmations", Psd2Constants.CONFIRMATION_OF_FOUNDS_RES);

                    //basket
                    put("createSigningBasket", Psd2Constants.SIGNING_BASKET_CREATE_RES);
                    put("getSigningBasket", Psd2Constants.SIGNING_BASKET_GET__RES);
                    put("deleteSigningBasket", Psd2Constants.DELETE_SIGNING_BASKET_RES);
                    put("startSigningBasketAuthorisation", Psd2Constants.START_SIGNING_BASKET_AUTH_RES);
                    put("getSigningBasketScaStatus", Psd2Constants.GET_SIGNING_BASKET_SCA_STATUS_RES);
					put("getSigningBasketStatusRequest", Psd2Constants.GET_SIGNING_BASKET_STATUS_REQUEST_RES);
                    //cof consent
                    put("createConsentConfirmationOfFunds", Psd2Constants.CONFIRMATION_OF_FOUNDS_RES);
                    put("getConsentConfirmationOfFundsStatus", Psd2Constants.GET_CONSENT_COF_STATUS_RES);
                    put("getConsentConfirmationOfFunds", Psd2Constants.GET_CONSENT_COF_RES);
                    put("deleteConsentConfirmationOfFunds", Psd2Constants.DELETE_CONSENT_COF_RES);
                    put("startConsentCofAuthorisation", Psd2Constants.START_CONSENT_COF_AUTH_RES);
                    put("getConsentCofAuthorisation", Psd2Constants.GET_CONSENT_COF_AUTH_RES);
                    put("getConsentCofScaStatus", Psd2Constants.GET_CONSENT_COF_SCA_STAT_RES);


                }
            });

    public static final Map<String, ArrayList<String>> PSD2_METHODS_MANDATORY_PARAM_MAP = Collections
            .unmodifiableMap(new HashMap<String, ArrayList<String>>() {
                {
                    //consent
                    put("createConsent", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(), RequestHeaderAttributes.TPP_REDIRECT_URI.getValue())));
                    put("getConsentStatus", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getConsentInformation", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("deleteConsent", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("startConsentAuthorisation", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getConsentAuthorisation", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getConsentScaStatus", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));

                    //account information
                    put("getAccountList", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.CONSENT_ID.getValue())));
                    put("readAccountDetails", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.CONSENT_ID.getValue())));
                    put("getBalances", new ArrayList<>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.CONSENT_ID.getValue())));
                    put("getTransactionList", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.CONSENT_ID.getValue())));
                    put("getTransactionDetails", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.CONSENT_ID.getValue())));

                    //pis
                    put("initiatePayment", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(), RequestHeaderAttributes.CONTENT_TYPE.getValue(),
                            RequestHeaderAttributes.PSU_IP_ADDRESS.getValue(), RequestHeaderAttributes.TPP_REDIRECT_URI.getValue(),
                            RequestHeaderAttributes.CONTENT_TYPE.getValue())));
                    put("cancelPayment", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getPaymentInformation", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getPaymentInitiationStatus", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("startPaymentAuthorisation", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getPaymentInitiationAuthorisation", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getPaymentInitiationScaStatus", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("startPaymentInitiationCancellationAuthorisation", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getPaymentInitiationCancellationAuthorisationInformation", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getPaymentCancellationScaStatus", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));

                    //bulk payments
                    put("initiateBulkPayment", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.CONTENT_TYPE.getValue(),
                            RequestHeaderAttributes.PSU_IP_ADDRESS.getValue(), RequestHeaderAttributes.TPP_REDIRECT_URI.getValue(),
                            RequestHeaderAttributes.CONTENT_TYPE.getValue())));
                    put("getBulkPaymentInitiationStatus", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getBulkPaymentInformation", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("cancelBulkPayment", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("startBulkPaymentAuthorisation", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getBulkPaymentInitiationAuthorisation", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getBulkPaymentInitiationScaStatus", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("startBulkPaymentInitiationCancellationAuthorisation", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getBulkPaymentInitiationCancellationAuthorisationInformation", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getBulkPaymentCancellationScaStatus", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));


                    //confirmation of founds
                    put("foundsConfirmations", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(), RequestHeaderAttributes.CONSENT_ID.getValue())));

                    //basket
                    put("createSigningBasket", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.PSU_IP_ADDRESS.getValue(), RequestHeaderAttributes.TPP_REDIRECT_URI.getValue())));
                    put("getSigningBasket", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("deleteSigningBasket", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("startSigningBasketAuthorisation", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getSigningBasketScaStatus", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));
					put("getSigningBasketStatusRequest", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));

                    //consent cof
                    put("createConsentConfirmationOfFunds", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getConsentConfirmationOfFundsStatus", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getConsentConfirmationOfFunds", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("deleteConsentConfirmationOfFunds", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("startConsentCofAuthorisation", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getConsentCofAuthorisation", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getConsentCofScaStatus", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));

                }
            });

    public static final Map<String, ArrayList<String>> PSD2_METHODS_MANDATORY_PARAM_MAP_OLD = Collections
            .unmodifiableMap(new HashMap<String, ArrayList<String>>() {
                {
                    //consent
                    put("createConsent", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.TPP_ID.getValue())));
                    put("getConsentStatus", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.TPP_ID.getValue())));
                    put("getConsentInformation", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.TPP_ID.getValue())));
                    put("deleteConsent", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.TPP_ID.getValue())));
                    put("startConsentAuthorisation", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.TPP_ID.getValue())));
                    put("getConsentAuthorisation", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.TPP_ID.getValue())));
                    put("getConsentScaStatus", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.TPP_ID.getValue())));


                    //account information
                    put("getAccountList", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.CONSENT_ID.getValue(), RequestHeaderAttributes.TPP_ID.getValue())));
                    put("readAccountDetails", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.CONSENT_ID.getValue(), RequestHeaderAttributes.TPP_ID.getValue())));
                    put("getBalances", new ArrayList<>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.CONSENT_ID.getValue(), RequestHeaderAttributes.TPP_ID.getValue())));
                    put("getTransactionList", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.CONSENT_ID.getValue(), RequestHeaderAttributes.TPP_ID.getValue())));
                    put("getTransactionDetails", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.CONSENT_ID.getValue(), RequestHeaderAttributes.TPP_ID.getValue())));

                    //pis
                    put("initiatePayment", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.TPP_ID.getValue(), RequestHeaderAttributes.CONTENT_TYPE.getValue(),
                            RequestHeaderAttributes.PSU_IP_ADDRESS.getValue(), RequestHeaderAttributes.TPP_REDIRECT_URI.getValue(),
                            RequestHeaderAttributes.CONTENT_TYPE.getValue())));
                    put("cancelPayment", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.TPP_ID.getValue())));
                    put("getPaymentInformation", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.TPP_ID.getValue())));
                    put("getPaymentInitiationStatus", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.TPP_ID.getValue())));
                    put("startPaymentAuthorisation", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.TPP_ID.getValue())));
                    put("getPaymentInitiationAuthorisation", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.TPP_ID.getValue())));
                    put("getPaymentInitiationScaStatus", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.TPP_ID.getValue())));
                    put("startPaymentInitiationCancellationAuthorisation", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.TPP_ID.getValue())));
                    put("getPaymentInitiationCancellationAuthorisationInformation", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.TPP_ID.getValue())));
                    put("getPaymentCancellationScaStatus", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.TPP_ID.getValue())));

                    //bulk payments
                    put("initiateBulkPayment", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.TPP_ID.getValue(), RequestHeaderAttributes.CONTENT_TYPE.getValue(),
                            RequestHeaderAttributes.PSU_IP_ADDRESS.getValue(), RequestHeaderAttributes.TPP_REDIRECT_URI.getValue(),
                            RequestHeaderAttributes.CONTENT_TYPE.getValue())));
                    put("getBulkPaymentInitiationStatus", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.TPP_ID.getValue())));
                    put("getBulkPaymentInformation", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.TPP_ID.getValue())));
                    put("cancelBulkPayment", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.TPP_ID.getValue())));
                    put("startBulkPaymentAuthorisation", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.TPP_ID.getValue())));
                    put("getBulkPaymentInitiationAuthorisation", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.TPP_ID.getValue())));
                    put("getBulkPaymentInitiationScaStatus", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.TPP_ID.getValue())));
                    put("startBulkPaymentInitiationCancellationAuthorisation", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.TPP_ID.getValue())));
                    put("getBulkPaymentInitiationCancellationAuthorisationInformation", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.TPP_ID.getValue())));
                    put("getBulkPaymentCancellationScaStatus", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue(),
                            RequestHeaderAttributes.TPP_ID.getValue())));

                    //confirmation of founds
                    put("foundsConfirmations", new ArrayList<String>(Arrays.asList(RequestHeaderAttributes.X_REQUEST_ID.getValue())));

                    //test methods
                    put("testGenSignature", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                }
            });


    public static final Map<String, ArrayList<String>> PSD2_METHODS_MANDATORY_RESPONE_PARAM_MAP = Collections
            .unmodifiableMap(new HashMap<String, ArrayList<String>>() {
                {
                    //consent

                    //ne treba lokacija jer se več postavlja u resursima
                    put("createConsent", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));

                    put("getConsentStatus", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getConsentInformation", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("deleteConsent", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("startConsentAuthorisation", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getConsentAuthorisation", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getConsentScaStatus", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));


                    //account information
                    put("getAccountList", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("readAccountDetails", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getBalances", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getTransactionList", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue(), RequestHeaderAttributes.ACCEPT.getValue())));
                    put("getTransactionDetails", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));

                    //pis

                    //ne treba lokacija jer se več postavlja u resursima
                    put("initiatePayment", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));

                    put("cancelPayment", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getPaymentInformation", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getPaymentInitiationStatus", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("startPaymentAuthorisation", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getPaymentInitiationAuthorisation", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getPaymentInitiationScaStatus", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("startPaymentInitiationCancellationAuthorisation", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getPaymentInitiationCancellationAuthorisationInformation", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getPaymentCancellationScaStatus", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));

                    //bulk payments
                    put("initiateBulkPayment", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));

                    put("cancelBulkPayment", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getBulkPaymentInformation", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getBulkPaymentInitiationStatus", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("startBulkPaymentAuthorisation", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getBulkPaymentInitiationAuthorisation", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getBulkPaymentInitiationScaStatus", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("startBulkPaymentInitiationCancellationAuthorisation", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getBulkPaymentInitiationCancellationAuthorisationInformation", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getBulkPaymentCancellationScaStatus", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));

                    //confirmation of founds
                    put("foundsConfirmations", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue(),
                            ResponsetHeaderAttributes.LOCATION.getValue())));

                    //basket
                    put("createSigningBasket", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getSigningBasket", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("deleteSigningBasket", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("startSigningBasketAuthorisation", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getSigningBasketScaStatus", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getSigningBasketStatusRequest", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));

                    //consent cof
                    put("createConsentConfirmationOfFunds", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getConsentConfirmationOfFundsStatus", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getConsentConfirmationOfFunds", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("deleteConsentConfirmationOfFunds", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("startConsentCofAuthorisation", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getConsentCofAuthorisation", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                    put("getConsentCofScaStatus", new ArrayList<String>(Arrays.asList(ResponsetHeaderAttributes.X_REQUEST_ID.getValue())));
                }
            });


    public static final Map<String, String> PSD2_METHODS_ROLE_MAP = Collections
            .unmodifiableMap(new HashMap<String, String>() {
                {
                    //consent
                    put("createConsent", TppRole.PSP_AI.toString());
                    put("getConsentStatus", TppRole.PSP_AI.toString());
                    put("getConsentInformation", TppRole.PSP_AI.toString());
                    put("deleteConsent", TppRole.PSP_AI.toString());
                    put("startConsentAuthorisation", TppRole.PSP_AI.toString());
                    put("getConsentAuthorisation", TppRole.PSP_AI.toString());
                    put("getConsentScaStatus", TppRole.PSP_AI.toString());

                    //account information
                    put("getAccountList", TppRole.PSP_AI.toString());
                    put("readAccountDetails", TppRole.PSP_AI.toString());
                    put("getBalances", TppRole.PSP_AI.toString());
                    put("getTransactionList", TppRole.PSP_AI.toString());
                    put("getTransactionDetails", TppRole.PSP_AI.toString());

                    //pis
                    put("initiatePayment", TppRole.PSP_PI.toString());
                    put("cancelPayment", TppRole.PSP_PI.toString());
                    put("getPaymentInformation", TppRole.PSP_PI.toString());
                    put("getPaymentInitiationStatus", TppRole.PSP_PI.toString());
                    put("startPaymentAuthorisation", TppRole.PSP_PI.toString());
                    put("getPaymentInitiationAuthorisation", TppRole.PSP_PI.toString());
                    put("getPaymentInitiationScaStatus", TppRole.PSP_PI.toString());
                    put("startPaymentInitiationCancellationAuthorisation", TppRole.PSP_PI.toString());
                    put("getPaymentInitiationCancellationAuthorisationInformation", TppRole.PSP_PI.toString());
                    put("getPaymentCancellationScaStatus", TppRole.PSP_PI.toString());

                    //bulk TODO
                    put("initiateBulkPayment", TppRole.PSP_PI.toString());
                    put("cancelBulkPayment", TppRole.PSP_PI.toString());
                    put("getBulkPaymentInitiationStatus", TppRole.PSP_PI.toString());
                    put("getBulkPaymentInformation", TppRole.PSP_PI.toString());
                    put("getBulkPaymentInitiationAuthorisation", TppRole.PSP_PI.toString());
                    put("getBulkPaymentInitiationScaStatus", TppRole.PSP_PI.toString());
                    put("getBulkPaymentInitiationCancellationAuthorisationInformation", TppRole.PSP_PI.toString());
                    put("getBulkPaymentCancellationScaStatus", TppRole.PSP_PI.toString());
                    put("startBulkPaymentAuthorisation", TppRole.PSP_PI.toString());
                    put("startBulkPaymentInitiationCancellationAuthorisation", TppRole.PSP_PI.toString());


                    //psp_ic
                    put("foundsConfirmations", TppRole.PSP_IC.toString());

                    //basket
                    put("createSigningBasket", TppRole.PSP_PI.toString());
                    put("getSigningBasket", TppRole.PSP_PI.toString());
                    put("deleteSigningBasket", TppRole.PSP_PI.toString());
                    put("startSigningBasketAuthorisation", TppRole.PSP_PI.toString());
                    put("getSigningBasketScaStatus", TppRole.PSP_PI.toString());
                    put("getSigningBasketStatusRequest", TppRole.PSP_PI.toString());

                    //consent_cof
                    put("createConsentConfirmationOfFunds", TppRole.PSP_IC.toString());
                    put("getConsentConfirmationOfFundsStatus", TppRole.PSP_IC.toString());
                    put("getConsentConfirmationOfFunds", TppRole.PSP_IC.toString());
                    put("deleteConsentConfirmationOfFunds", TppRole.PSP_IC.toString());
                    put("startConsentCofAuthorisation", TppRole.PSP_IC.toString());
                    put("getConsentCofAuthorisation", TppRole.PSP_IC.toString());
                    put("getConsentCofScaStatus", TppRole.PSP_IC.toString());


                    //test methods
                    put("testGenSignature", TppRole.PSP_PI.toString());
                }
            });

    //CERTIFICATE
    public static final String QC_STATEMENTS_CERTIFICATE_EXTENSION_OID = "1.3.6.1.5.5.7.1.3";
    public static final String ORGANIZATION_ID_OID = "OID.2.5.4.97=";
    public static final String ORGANIZATION_NAME_OID = "O=";
    public static final String ID_ETSI_PSD2_QC_STATEMENT = "0.4.0.19495.2";
    public static final String ID_ETSI_PSD2_ROLE_PSP_AS = "0.4.0.19495.1.1";
    public static final String ID_ETSI_PSD2_ROLE_PSP_PI = "0.4.0.19495.1.2";
    public static final String ID_ETSI_PSD2_ROLE_PSP_AI = "0.4.0.19495.1.3";
    public static final String ID_ETSI_PSD2_ROLE_PSP_IC = "0.4.0.19495.1.4";

    public static final String CERTIFICATE_TYPE_QWAC = "QWAC";
    public static final String CERTIFICATE_TYPE_QSEAL = "QSEAL";

    //QSeal Cert Use
    public static final String PARAMETAR_PSD2_QSEAL_USE = "PSD2_QSEAL_US";
    public static final String PSD2_QSEAL_DONT_USE = "0";
    public static final String PSD2_QSEAL_USE = "1";
    public static final String PSD2_QSEAL_REGISTER = "2";
    public static final String PARAMETAR_PSD2_QWAC_USE = "PSD2_QWAC_US";
    public static final String PSD2_QWAC_DONT_USE = "0";
    public static final String PSD2_QWAC_USE = "1";
    public static final String PSD2_SIGN_RESPONSE = "1";

    public static final Integer REMITTANCE_INFO_MAX_LENGTH_IP = 140;
    
    //MULTICURRENCY ACCOUNT
    public static final String MULTI_CURRENCY_ACCOUNT = "MVR";

    public static final String DEBIT= "DEBIT";
    public static final String CREDIT= "CREDIT";

}
