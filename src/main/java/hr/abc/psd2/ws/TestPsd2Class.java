package hr.abc.psd2.ws;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Base64;

import javax.xml.bind.DatatypeConverter;

import hr.abc.psd2.util.CertificateUtil;

/**
 * Created by Tomica on 11-Jun-19.
 */
public class TestPsd2Class {

    public static void main(String[] args) {
        System.out.println("Digest:");

        validateDigestTest();

    }

    public static String validateDigestTest() {
        String base64ShaString = null;
        String shaStringHex = null;
        MessageDigest md = null;
        byte[] messageDigest = null;
        try {

            InputStream keyStore = new FileInputStream("C:/psd2qseal.jks");
            CertificateUtil.getCertificateSignInfoFromKeyStore(keyStore, "pabaqseal", "pabaqseal");



            //body string
            String body = "{\"remittanceInformationStructured\": {\"reference\": \"HR99\"}, \"remittanceInformationUnstructured\": \"TEST QSEAL\", \"debtorId\": \"debtorID\\n\\r\\\\n\\\\rtest\", \"endToEndIdentification\": \"HR99\", \"creditorAccount\": {\"iban\": \"HR4324020061100912588\"}, \"creditorName\": \"creditorName453425\", \"requestedExecutionDate\": \"2019-06-11\", \"instructedAmount\": {\"currency\": \"HRK\", \"amount\": 123.0}, \"debtorAccount\": {\"iban\": \"HR4124080021101035920\"}}";
//            FileInputStream in = new FileInputStream("/hr/abit/b3/biz/psd2/message/psd2Messages.properties");
//            FileInputStream ina = new FileInputStream("/data/bodyString");
//
//            byte[] bytesBodyString = new byte[in.available()];
//            in.read(bytesBodyString);
//            in.close();
            //digest alg
            String digestAlgorithm = "SHA-256";
            md = MessageDigest.getInstance(digestAlgorithm);
            messageDigest = md.digest(body.getBytes());
            shaStringHex = DatatypeConverter.printHexBinary(messageDigest).toLowerCase();
            base64ShaString = new String(Base64.getEncoder().encode(shaStringHex.getBytes()));
            System.out.print(base64ShaString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return base64ShaString;
    }


}
