package hr.abc.psd2.util;


import static hr.abc.psd2.util.Psd2Constants.ResponsetHeaderAttributes.DATE;
import static hr.abc.psd2.util.Psd2Constants.ResponsetHeaderAttributes.DIGEST;
import static hr.abc.psd2.util.Psd2Constants.ResponsetHeaderAttributes.X_REQUEST_ID;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Tomica on 21-May-19.
 */
public class SignatureContants {

    public static final String PSD2_KEYSTORE_FILE ="/data/psd2/psd2keystore.jks";
    public static final String PSD2_KEYSTORE_PASS ="psd2changeit";

    public static final String DIGEST_ALGORITHM_SHA_256 = "SHA-256";
    public static final String DIGEST_ALGORITHM_SHA_512 = "SHA-512";

    public static final String SIGNATURE_HEADERS = "\"" + DIGEST.getValue() + " " + X_REQUEST_ID.getValue() + " " + DATE.getValue() + "\"";

    public static final String QSEAL_SIGN_RESPONSE_CERTIFICATE = "-----BEGIN CERTIFICATE-----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-----END CERTIFICATE-----";
    public static final String QSEAL_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCW4xzFhIat7TaBBfkMzEL6+cGPBCxMQC7w1r9h9VM+Ybra4SnO1eZFVQY0Jx0HUOVT3k4bn8GahKsCoqeyplBDsS8mOlWxe4CbOo+B4IksIlQNowsI41UTrvSVSk8WIg6ai2hblpsK30wTESWwbb7Hxm3BjPwnKZB8WIJB0HqtQhmDy6mToMlkbfZncm4SvYsAvslOizmKJgDx3G+rYxjOI+TfH3XWUUEqZogZ/CCVaUhrkHdx4xzXUgQYx+34QbXuyHsF4uGIdS9anG1fyTydFEntUG/2lSezDLoW99WwZhLZOm8P0WQjnUj3Nbz4B7N47QanRNYC1p0/spRoppHzAgMBAAECggEATS5BAlvG1GK5dYgLm9tEC8MDmldFHX/bQwBmBwKJISi2fs2cKBvL3P0f3j9/nsVneN4G0CSI0LQ6EXAIHd7qwaV5e7FtMgr9fumWjJXBNN0eZ5dVxqcaVqYwn8jR6X1kQPM8X1ULW6Va8Ync4IFSjDfY3p9yP8oICULw5h9xEPwsg1F5Me3BnYQ51V2ku14abFSl3Hh29cluGYfuU1IpPfPCHYMjP2keMRbgoj8iAsqdTd6vtXsY/anpSDBmhSMEyb8FF1nj57UmXvsyYyMTX544ZamGd3KZOnwUkHEWgOwiVCX2T/DFHEXjnLRoyRml9fml8lmehRWxV5RsE4uIUQKBgQDL45jN1jjT2PCRE5SZwso439ORTvtHzCjgQA1plj+UjrxqIr7cqWk90RR8obJV0rYYTIdzFIID0GyDfRhsaU3DVccsTEgVPgFExrc4xW2MzIX0wd0QFNqpSdnOGc0vyLIBBO5KQDEGIi/wOAjb7QxVwEbJsIwRRdeLHBsj3mTaWQKBgQC9c53ZoQ1NMfHjgHPjAzg+BKuyS/Swo36fHDs8GA1b0aTkXM3TYndcnGY+3ttELLK2uhpgzcxjPaDdkSwm7Hsy+b8RwIIdXwKNGQTZOLLM9USaCXns+4JMhU6DJoGBr1NV6C5ICBM+UEZFdb/urYmsNm0h9AttcIPElIXLTu5tKwKBgQDEy7zcyO33BQlGbsjbFRoNhXV3Zu3B4jx+HzUS+jQKVy9jnn/tCxQK17lXhtdUYVrj31JJ8LyBo/ZpgCBH2+h0J2JkzogxALVGxitpf5567ZpKoaiXchmQlzjE50CW47d0pma+gnEPQ4ZT7jIW3Rov1rQ8zsyTi389xXYoy+s6mQKBgEDwIGs4iRasx/zzSsXFlMGtWC0FXBov8HcZdjRjCjEu6xZbuTF7zu4vrdxfB0mXCKsZn3EPGb/qn/lSiY/N/2y5roSnN/JZXsZ+jOroRGF8sMcb+G8CxubzyrWpvTp2nloT+8k0+2OgoHWupttpIIenIEFDJaedqYz4W+QUaiWBAoGAW7UE3shNyzUAZ6P0aHfTRmzJHZrGYveRjHjnz5RiCGp91fc5Mn6dSacQiEoHUcbw5rmEJXk5mj+v5VDtXn6qWpujQqVv318WU22o6zSrNDLSXrWZQDuKqQVozFTCXGmaCaWlHQhIq4xAbD6pSsfV4qzPPCG1TQPPhtEZlWjedTU=";

    //FOR TEST public static final String PSD2_QSEAL_KEYSTORE_FILE ="/data/psd2qseal.jks";
    public static final String PSD2_QSEAL_KEYSTORE_FILE ="/data/psd2/psd2qseal.jks";
    public static final String PSD2_QSEAL_KEYSTORE_PASS ="pabaqseal";
    public static final String PSD2_QSEAL_CERT_ALIAS ="pabaqseal";

    public static List<String> SIGNATURE_HEADER_LIST = new ArrayList<String>(Arrays.asList(DIGEST.getValue(), X_REQUEST_ID.getValue(), DATE.getValue()));

}
