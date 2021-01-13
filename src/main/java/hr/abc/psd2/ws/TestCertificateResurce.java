package hr.abc.psd2.ws;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.EncryptedPrivateKeyInfo;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

import org.bouncycastle.asn1.x509.AccessDescription;
import org.bouncycastle.asn1.x509.AuthorityInformationAccess;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.X509ObjectIdentifiers;
import org.bouncycastle.x509.extension.X509ExtensionUtil;

import hr.abc.psd2.model.dto.ProcessRequestDto;
import hr.abc.psd2.util.CertificateUtil;
import hr.abc.psd2.util.SignatureContants;
import hr.abc.psd2.util.SignatureUtil;

@Path("/testCertificate")
@Produces("application/json")


public class TestCertificateResurce {

    @GET
    @Path("/test1/{jksPath}/{jksPassword}")
    public String testCertificate(@PathParam("jksPath") String jksPath, @PathParam("jksPassword") String jksPassword) {
        CertificateUtil.getCertificateDetails(jksPath, jksPassword);
        return "OK";
    }


    @GET
    @Path("/testSignature")
    public String testGenSignature__() throws IOException, GeneralSecurityException {
        byte[] signingStingByte = signingString.getBytes(StandardCharsets.UTF_8);
        try {
            //PrivateKey privateKey = readPrivateKey(new File("/data/jebenprivetekey5"));
            PrivateKey privateKey = getPrivateKeyFinal();
            //SignatureUtil.signMessage(privateKey, privateKey.getAlgorithm(), signingStingByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "OK";
    }


    @GET
    @Path("/generateVerifyDigSignature")
    public String testGenSignature(@Context HttpServletRequest httpServletRequest) {
        try {
            //generate signature
            String signingStringRequest = httpServletRequest.getHeader("signingString");
            String certificateRequest = httpServletRequest.getHeader("certificate");

            // read key bytes
            FileInputStream in = new FileInputStream("/data/signingString");
            byte[] bytesSigningString = new byte[in.available()];
            in.read(bytesSigningString);
            in.close();

            //byte[] signingStringByte = signingStringRequest.getBytes(StandardCharsets.UTF_8);
            X509Certificate qSealCertificate = CertificateUtil.parseCertificate(certificateRequest);
            //test const cert
            X509Certificate pabaCert = CertificateUtil.parseCertificate(SignatureContants.QSEAL_SIGN_RESPONSE_CERTIFICATE);

            getCertDetails(pabaCert);
            getCertDetails(qSealCertificate);

            PrivateKey privateKey = getPrivateKeyFinal();
            PrivateKey privateKeyPaba = getPrivateKeyFinal1();
            byte[] encodedSignature = SignatureUtil.signMessage(pabaCert, privateKeyPaba, bytesSigningString);

            // /verifySignature
            ProcessRequestDto processRequestDto = SignatureUtil.verifySignature(pabaCert.getSigAlgName(), pabaCert.getPublicKey(), bytesSigningString, Base64.getDecoder().decode(encodedSignature));
            if (processRequestDto.getIsValid()) {
                System.out.println("Potpis je validan!");
            } else {
                System.out.println("Potpis nije validan!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }


    public void getCertDetails(X509Certificate certificate) {
        try {
            // get Authority Information Access extension (will be null if extension is not present)
            byte[] extVal = certificate.getExtensionValue("1.3.6.1.5.5.7.1.1");
            AuthorityInformationAccess aia = AuthorityInformationAccess.getInstance(X509ExtensionUtil.fromExtensionValue(extVal));

            // check if there is a URL to issuer's certificate
            AccessDescription[] descriptions = aia.getAccessDescriptions();
            for (AccessDescription ad : descriptions) {
                // check if it's a URL to issuer's certificate
                if (ad.getAccessMethod().equals(X509ObjectIdentifiers.id_ad_caIssuers)) {
                    GeneralName location = ad.getAccessLocation();
                    if (location.getTagNo() == GeneralName.uniformResourceIdentifier) {
                        String issuerUrl = location.getName().toString();
                        // http URL to issuer (test in your browser to see if it's a valid certificate)
                        // you can use java.net.URL.openStream() to create a InputStream and create
                        // the certificate with your CertificateFactory
                        URL url = new URL(issuerUrl);
                        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                        X509Certificate issuer = (X509Certificate) certificateFactory.generateCertificate(url.openStream());
                        String a = "";
                    }
                }
            }
        } catch (Exception e) {

        }

    }


//    private static PrivateKey readPrivateKey1(File keyFile) throws Exception {
//        // read key bytes
//        FileInputStream in = new FileInputStream(keyFile);
//        PKCS8Key pkcs8 = new PKCS8Key( in, "paba1503!".toCharArray() );
//
//        byte[] decrypted = pkcs8.getDecryptedBytes();
//        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec( decrypted );
//    }


    public PrivateKey getPrivateKeyFinal()
            throws NoSuchAlgorithmException,
            InvalidKeySpecException, IOException {

//        final InputStream inputStream = getClass().getClassLoader()
//                .getResourceAsStream("/data/jebenprivetekey5");
//        byte[] privKeyBytes = null;
//        try {
//            privKeyBytes = IOUtils.toByteArray(inputStream);
//        } catch (final IOException exception) {
//            exception.printStackTrace();
//            IOUtils.closeQuietly(inputStream);
//        }

        // read key bytes
        FileInputStream in = new FileInputStream("/data/jebenprivetekey5");
        byte[] keyBytes = new byte[in.available()];
        in.read(keyBytes);
        in.close();

        //log.debug("privKeyBytes: {}", privKeyBytes);

        String BEGIN = "-----BEGIN PRIVATE KEY-----";
        String END = "-----END PRIVATE KEY-----";
        String str = new String(keyBytes);
        if (str.contains(BEGIN) && str.contains(END)) {
            str = str.substring(BEGIN.length(), str.lastIndexOf(END));
        }

        KeyFactory fac = KeyFactory.getInstance("RSA");
        EncodedKeySpec privKeySpec =
                new PKCS8EncodedKeySpec(Base64.getDecoder().decode(str.getBytes()));
        return fac.generatePrivate(privKeySpec);
    }

    public PrivateKey getPrivateKeyFinal1()
            throws NoSuchAlgorithmException,
            InvalidKeySpecException, IOException {

        KeyFactory fac = KeyFactory.getInstance("RSA");
        EncodedKeySpec privKeySpec =
                new PKCS8EncodedKeySpec(Base64.getDecoder().decode(SignatureContants.QSEAL_PRIVATE_KEY.getBytes()));
        return fac.generatePrivate(privKeySpec);
    }


    public static PrivateKey readPrivateKey(File keyFile) throws Exception {
        // read key bytes
//        FileInputStream in = new FileInputStream(keyFile);
//        byte[] keyBytes = new byte[in.available()];
//        in.read(keyBytes);
//        in.close();
//
//        String privateKey = new String(keyBytes, "UTF-8");
//        privateKey = privateKey.replaceAll("(-+BEGIN RSA PRIVATE KEY-+\\r?\\n|-+END RSA PRIVATE KEY-+\\r?\\n?)", "");
//
//        // don't use this for real projects!
//        BASE64Decoder decoder = new BASE64Decoder();
//        keyBytes = decoder.decodeBuffer(privateKey);
//
//        // generate private key
//        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return null;
    }

    @GET
    @Path("/test2")
    public String testGenSignature2() throws IOException, GeneralSecurityException {
        byte[] signingStingByte = signingString.getBytes(StandardCharsets.UTF_8);
        PrivateKey privateKey = getPrivateKeyFromStringPKCS8New(getPrivateKeyString1);
        //SignatureUtil.signMessage(privateKey, privateKey.getAlgorithm(), signingStingByte);

        return "OK";
    }

    public static PrivateKey getPrivateKeyFromStringPKCS8New(String key) throws IOException, GeneralSecurityException {
        String encrypted = key;

        EncryptedPrivateKeyInfo encryptPKInfo = new EncryptedPrivateKeyInfo(Base64.getDecoder().decode(encrypted));
        Cipher cipher = Cipher.getInstance(encryptPKInfo.getAlgName());
        String passwd = "paba1503!";
        PBEKeySpec pbeKeySpec = new PBEKeySpec(passwd.toCharArray());
        SecretKeyFactory secFac = SecretKeyFactory.getInstance(encryptPKInfo.getAlgName());
        Key pbeKey = secFac.generateSecret(pbeKeySpec);
        AlgorithmParameters algParams = encryptPKInfo.getAlgParameters();
        cipher.init(Cipher.DECRYPT_MODE, pbeKey, algParams);
        KeySpec pkcs8KeySpec = encryptPKInfo.getKeySpec(cipher);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey encryptedPrivateKey = kf.generatePrivate(pkcs8KeySpec);

        return encryptedPrivateKey;

    }


    public static PrivateKey getPrivateKeyFromStringPKCS8(String key) throws IOException, GeneralSecurityException {
        String encrypted = key;

        //Create object from encrypted private key
//                encrypted = encrypted.replace("-----BEGIN ENCRYPTED PRIVATE KEY-----", "");
//                encrypted = encrypted.replace("-----END ENCRYPTED PRIVATE KEY-----", "");

        //EncryptedPrivateKeyInfo pkInfo = new EncryptedPrivateKeyInfo(Base64.getDecoder().decode(encrypted));

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(encrypted));


//                PBEKeySpec keySpec = new PBEKeySpec("paba1503!".toCharArray()); // password
//                SecretKeyFactory pbeKeyFactory = SecretKeyFactory.getInstance(pkInfo.getAlgName());
//                PKCS8EncodedKeySpec encodedKeySpec = pkInfo.getKeySpec(pbeKeyFactory.generateSecret(keySpec));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey encryptedPrivateKey = keyFactory.generatePrivate(keySpec);
        return encryptedPrivateKey;

    }


//
//        public static RSAPrivateKey getPrivateKeyFromString(String key) throws IOException, GeneralSecurityException {
//                String privateKeyPEM = key;
//                privateKeyPEM = privateKeyPEM.replace("-----BEGIN ENCRYPTED PRIVATE KEY-----\n", "");
//                privateKeyPEM = privateKeyPEM.replace("-----END ENCRYPTED PRIVATE KEY-----", "");
//                byte[] encoded = Base64.decodeBase64(privateKeyPEM);
//                KeyFactory kf = KeyFactory.getInstance("RSA");
//                PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
//                RSAPrivateKey privKey = (RSAPrivateKey) kf.generatePrivate(keySpec);
//                return privKey;
//        }

    String signingString =
            "digest: NDn9q5HLdOJbNVkJR6SdHhl2Bw3dll2PI6EoxlrBb8Gp1Yj7am86nR07XAmnThdsZ0U3hHJQz0shNazhmOf8tw8hKLDztxP3f2yOG8m72WVln9uHhVKuSmEvRpAQi7KKYYBCjbzxPT4GVYJR8wI2jdlE7Q7thz24NTb90fI7J04+TsLdsVSlALNXuSof5YGxItQvB+H7b0KFSum34FNA5WuD17Yi2jXDFHmAQlFMU72pc/oHDKS/P4srUMYscvDsHW7XFVAeK6JAo+OtFDidBr+zI1aDSI6a5BPH6E7FvMOQJiUmnr1fFNCcKmnESUoTIpzVfB6DWmtyhe+JQhBsEg==\n" +
                    "x-request-id: 3ee2d65d-6d34-45d6-805e-3e579190759d\n" +
                    "tpp-redirect-uri: http://localhost/tpp-endpoint\n" +
                    "date: Tue, 16 Apr 2019 12:21:43 GMT";

    String getPrivateKeyString1 =
            "MIIFHDBOBgkqhkiG9w0BBQ0wQTApBgkqhkiG9w0BBQwwHAQIpOoZF2+E/XsCAggA\n" +
                    "MAwGCCqGSIb3DQIJBQAwFAYIKoZIhvcNAwcECF/uRYeyjDnXBIIEyPSrgf5LOXTi\n" +
                    "e+iGw9g9XGejDHOsz/DZ7050LoOLYMmg/6r3DlgoGxiW4YQKFmM+3X6UYS8lrSos\n" +
                    "TWhHvoj1Wh7zGD2KACWKZ2G/Hh21JsfPH4jwNR8/sOaVd6YYDpewz6MZDlBtkgGw\n" +
                    "nkiVzeaZIe6Srdqh+eL5E33oaPH5w5NUpO50b5wtjta14vfCaTIOTIeu/3fseBex\n" +
                    "0K8dI4igHTevzRKij7YiSm6OpbBxvGuk4l3CYdkNYQC3uscmi5hhJHSOigME2XpL\n" +
                    "6Xx9wKJpvT/CjUQ/aVIwGw93mhi4F0za1O4pbNy1bMnHS4/5quNWyus8XlHhtXi5\n" +
                    "CSe6Kw1kEEBpR03wZXp9yYR/univofTX8SBrEqVFRRV1lD6mwxfyXOPZYDenKzpg\n" +
                    "Z/xE/4eQRP/X5qN4yiJ5eGlsFnGA772sVoVKh9HMd8QIU88FAs9s/eU+VETy5eUJ\n" +
                    "nYPN5aL54B15qQ8OfKk9volpLHn1+8YMdepsKjdL8OQOYgWW6XIb/6eSm0Jmsvft\n" +
                    "x6jVOfjv7oHnNY2+gLYwnzZmrTyJHLaNbwB64JJRklvy+//7LQB/TF2LPPS4VN0T\n" +
                    "rw0XA0QGGKgf3sP3+NLOEHPgnisK2GBGEDmnJicLpLdOWgCOtGNNL2U8LLIxGOgx\n" +
                    "86C6Sz25xt1xcIvCuEoo7fCMDoQ0fLJC830N9PR3sxwjYI2+zxnP7zQNJO0XzerC\n" +
                    "ceV6cP8HZFWiwSMGGYnhaohGedVvOulVFS2yVJN83VkPbuX+8MJlTiUHQ/OOfNgu\n" +
                    "PhacWwv2Fk/9x5DFjD166WDrNRGgpbBSUaIrHgyie18d9AprC0so6/I2TZAn90Ce\n" +
                    "wAef7ZaHtM2Fq5rNNun7GZ7AeiJ4Kgx4V6trlB2fVrPKZixqPftlHLc14yEbnNBj\n" +
                    "rLYuUpqUyQGGmNwH/WfPUFfM7gttZFh34OgqCiKXPpS3XYm1R6z9MIePNPvY9kpS\n" +
                    "tVyIy1MIa2eK1G59dma7vPURhzYLUe3N+HW0V5Jp10yYUn5xGbTmaQaegruGICO/\n" +
                    "3wohzZI6LIEo/RDc/w8fthL7joAZd6kZWCFVocvc7kYPHxXthWysizAehr6Gs0Uw\n" +
                    "hKG+DBd69j6TQ6yHyG0DhsUnaW1jisB70/hfXXnVuvOyovV4LqwV2W78N3NdVo1w\n" +
                    "0fl+pkr/1rbvjWxG4tA7vR3jsOYH9iuoGP8Q4aozq+YWK6AyOBo/RyQEWyh4hVeq\n" +
                    "W1HwgFeVms5NPxuDXhk85i8bGf4/rPnO4SZQNzeGUEGFTZMNktlus9moOv98kr8j\n" +
                    "hpkf6TNFy6M3BK3KQdM51O5wABYmwVvv4Acrg+ZnY6gnMo+3tPrPKHSXtTGQerq/\n" +
                    "zaD4slZTiylvHz5lW1Z8gfZKwRj+2Q0UAfRc0iGqjlCZMOsRto+RuCNz3I6SpbC7\n" +
                    "3qtSN1qhP4hrDrHMCyEqcOubrlRchVvQz+QDqK/riSXISry6IyADO+0SW13FyNsQ\n" +
                    "x4Dy9SYoNGvTvtcMuESqV2SSmjyRGivA1yxJT+J58/M6OVG73U5DeYsVtfdFqKeL\n" +
                    "C/6BBKXQt9xVSOFtFE4ARwXaOV+HCJIK+nfaOn8PjTHiOddQ+KB937gou2YF+LmN\n" +
                    "TX+ptbB/Y23tUfZIHXZeNA==";

    String privateKeyString =
            "-----BEGIN ENCRYPTED PRIVATE KEY-----\n" +
                    "MIIFHDBOBgkqhkiG9w0BBQ0wQTApBgkqhkiG9w0BBQwwHAQIpOoZF2+E/XsCAggA\n" +
                    "MAwGCCqGSIb3DQIJBQAwFAYIKoZIhvcNAwcECF/uRYeyjDnXBIIEyPSrgf5LOXTi\n" +
                    "e+iGw9g9XGejDHOsz/DZ7050LoOLYMmg/6r3DlgoGxiW4YQKFmM+3X6UYS8lrSos\n" +
                    "TWhHvoj1Wh7zGD2KACWKZ2G/Hh21JsfPH4jwNR8/sOaVd6YYDpewz6MZDlBtkgGw\n" +
                    "nkiVzeaZIe6Srdqh+eL5E33oaPH5w5NUpO50b5wtjta14vfCaTIOTIeu/3fseBex\n" +
                    "0K8dI4igHTevzRKij7YiSm6OpbBxvGuk4l3CYdkNYQC3uscmi5hhJHSOigME2XpL\n" +
                    "6Xx9wKJpvT/CjUQ/aVIwGw93mhi4F0za1O4pbNy1bMnHS4/5quNWyus8XlHhtXi5\n" +
                    "CSe6Kw1kEEBpR03wZXp9yYR/univofTX8SBrEqVFRRV1lD6mwxfyXOPZYDenKzpg\n" +
                    "Z/xE/4eQRP/X5qN4yiJ5eGlsFnGA772sVoVKh9HMd8QIU88FAs9s/eU+VETy5eUJ\n" +
                    "nYPN5aL54B15qQ8OfKk9volpLHn1+8YMdepsKjdL8OQOYgWW6XIb/6eSm0Jmsvft\n" +
                    "x6jVOfjv7oHnNY2+gLYwnzZmrTyJHLaNbwB64JJRklvy+//7LQB/TF2LPPS4VN0T\n" +
                    "rw0XA0QGGKgf3sP3+NLOEHPgnisK2GBGEDmnJicLpLdOWgCOtGNNL2U8LLIxGOgx\n" +
                    "86C6Sz25xt1xcIvCuEoo7fCMDoQ0fLJC830N9PR3sxwjYI2+zxnP7zQNJO0XzerC\n" +
                    "ceV6cP8HZFWiwSMGGYnhaohGedVvOulVFS2yVJN83VkPbuX+8MJlTiUHQ/OOfNgu\n" +
                    "PhacWwv2Fk/9x5DFjD166WDrNRGgpbBSUaIrHgyie18d9AprC0so6/I2TZAn90Ce\n" +
                    "wAef7ZaHtM2Fq5rNNun7GZ7AeiJ4Kgx4V6trlB2fVrPKZixqPftlHLc14yEbnNBj\n" +
                    "rLYuUpqUyQGGmNwH/WfPUFfM7gttZFh34OgqCiKXPpS3XYm1R6z9MIePNPvY9kpS\n" +
                    "tVyIy1MIa2eK1G59dma7vPURhzYLUe3N+HW0V5Jp10yYUn5xGbTmaQaegruGICO/\n" +
                    "3wohzZI6LIEo/RDc/w8fthL7joAZd6kZWCFVocvc7kYPHxXthWysizAehr6Gs0Uw\n" +
                    "hKG+DBd69j6TQ6yHyG0DhsUnaW1jisB70/hfXXnVuvOyovV4LqwV2W78N3NdVo1w\n" +
                    "0fl+pkr/1rbvjWxG4tA7vR3jsOYH9iuoGP8Q4aozq+YWK6AyOBo/RyQEWyh4hVeq\n" +
                    "W1HwgFeVms5NPxuDXhk85i8bGf4/rPnO4SZQNzeGUEGFTZMNktlus9moOv98kr8j\n" +
                    "hpkf6TNFy6M3BK3KQdM51O5wABYmwVvv4Acrg+ZnY6gnMo+3tPrPKHSXtTGQerq/\n" +
                    "zaD4slZTiylvHz5lW1Z8gfZKwRj+2Q0UAfRc0iGqjlCZMOsRto+RuCNz3I6SpbC7\n" +
                    "3qtSN1qhP4hrDrHMCyEqcOubrlRchVvQz+QDqK/riSXISry6IyADO+0SW13FyNsQ\n" +
                    "x4Dy9SYoNGvTvtcMuESqV2SSmjyRGivA1yxJT+J58/M6OVG73U5DeYsVtfdFqKeL\n" +
                    "C/6BBKXQt9xVSOFtFE4ARwXaOV+HCJIK+nfaOn8PjTHiOddQ+KB937gou2YF+LmN\n" +
                    "TX+ptbB/Y23tUfZIHXZeNA==\n" +
                    "-----END ENCRYPTED PRIVATE KEY-----";


    String test = "Bag Attributes\n" +
            "    friendlyName: te-616d9275-9bda-49df-bd96-a3a38e026cce\n" +
            "    localKeyID: F4 CC 1C 61 28 C8 D0 57 D4 C3 6E 8A 00 57 13 13 FC D9 25 DB \n" +
            "Key Attributes: <No Attributes>\n" +
            "-----BEGIN ENCRYPTED PRIVATE KEY-----\n" +
            "MIIFDjBABgkqhkiG9w0BBQ0wMzAbBgkqhkiG9w0BBQwwDgQIv3DEeDCZ/zMCAggA\n" +
            "MBQGCCqGSIb3DQMHBAgvsIfIJcq4KQSCBMhUtenYJnyDRRh4h6Sw4RlITP0WW77i\n" +
            "Ln5N+rWEVcOnhy2blXI0mnLs8f/O5b+AoHGTM9vl8QLyENkb1ESrw07EtYd8L9T+\n" +
            "2wJ6muY5FO0HyqBt7ZdAgZTxIPJTI4q3xEX/w3MbEQaUufYB0ZpVI2vQ7JFW7JDw\n" +
            "gN4x1gw32lRDik5xSsI9s0waaqVCAvousjibNInLySGXUS78NoDFKIxXXiz9dGq2\n" +
            "g18scKWc0wyg64IGJJAEK5ZtUnDJwIaVmyxqAyr/5/vNK828EX8Zz8aU+S3SAhZ5\n" +
            "/4oaWojLon9anpuwF9hQRxVhgSwm0TZD27xvVZXJKMrnWEE1/rJsMvY4Niahr0rj\n" +
            "Dwp04wtmVCUZXIfRzYH87EeWMYkIz5+RgebRhDJViGclAw1Ni8FEd+nGcLo99cR5\n" +
            "PXilxQp8cGs2B4mihkuGBAYdUpQnULRVQgWUQozLVMUAt6u1BMjvCQyT5aC83CQF\n" +
            "NS+MK7zbYUas4Wt4s+vWnvTm7Enpo98bagkI9wW35IWG3IoUFRN40fH8UhStAxXu\n" +
            "UDH+rkzJSfAWb0RCArCrcatE2FIsUe7377ytSqJKDU0UIAX+y1NnyOMODdYEVSfm\n" +
            "sLo29m8zH7iH+Jw5Tc+xbLb+LgCnm666MTKUkkFqtND2Leg0qfu+Fr773srnvtR2\n" +
            "X2QSM6rw4CjvFV7vFSHTtCzTbhHhxDAuhoeXzF+xmoz/03kxpGFdC5qAvt5WkHm/\n" +
            "wUd+9iIO2yEjqsTnJZ+/wLuRh3sFy4KO57rqbTYp+130/BwW3U58asD4fnfzvz5Y\n" +
            "YQ6sD2FlEGPgIVvBJzWzhHYgPbBIZsE/L41tRzvUqJXw4rVS2MRnh07melR7Ic/n\n" +
            "f/8/9gbbALHumKwnUU1B0kJvx2Ac059doj2SFvVPv3tFAVVBqojki8roJqbT9G9Y\n" +
            "stSFEK+JeCRHE2fUIMzb6UWxGKPwSvGpTHcgJ4c6ov3EacOKOhRr7JXYUF6BZh+0\n" +
            "QAFNfmM2g0fKwrauHWp7GxX2xH2NrFC4WAu2p20R8mf17l3yxwF01PS9WlerX3yO\n" +
            "/aN5xi6hTtKPtlp9KgG8GDFtD7EbudD3PlSe6GsKKPvzTu55/fDyf6LPq3+vaohD\n" +
            "UU07QNeZbO3vo5UfK8oDqvqxBnWbxRw9tkO9qAgQ9pOSqDRIq5StaCx1bSzAvBxV\n" +
            "2traUjOnV1aPzpsjyN4+QKJ98+WuQ4pdqTFlPFDQ9rNbnRZ5pWpJjr3sWvjVsxtO\n" +
            "GjLq76LHXBqnTJA1feCoph5OomIbURb/cPMCU93wLyro6ZpezmDrk4yAZRv06wvx\n" +
            "aA/MCZWNfBMqg9B0aNKvOl8iaWufoAeqPuvKrKyAOCfA2gCSTQrfPARrDt6+DTzC\n" +
            "paYdoxzaz9pyW6HWT/jAK1ouwSDjcUbrgooWT1gxK2GQEgUE+yL33Wwt73exDazk\n" +
            "tvXbeIZpbJuK8L58e8QyQ7ASps51I2lOCZ9X/IsJsHTMyV/gYMYAjz07VvRvnOsF\n" +
            "+Ap+QWML5VMm7UlRsWDeuLSP1F2T3AS8LdAcZ+wtwIM+iqprN0z7Q2gsKbpxmN8J\n" +
            "hf+HteJU2v/MW2rhEVo1TnKl/cjQx2+aLsZ+rnplSBt6KPGuupk6CgJDpTxZWEpt\n" +
            "7bM=\n" +
            "-----END ENCRYPTED PRIVATE KEY-----\n" +
            "Bag Attributes\n" +
            "    friendlyName: Fina Demo CA 2014 - Financijska agencija\n" +
            "subject=/C=HR/O=Financijska agencija/CN=Fina Demo CA 2014\n" +
            "issuer=/C=HR/O=Financijska agencija/CN=Fina Demo Root CA\n" +
            "-----BEGIN CERTIFICATE-----\n" +
            "MIIG9zCCBN+gAwIBAgINAOceyVkAAAAAUygd5DANBgkqhkiG9w0BAQsFADBIMQsw\n" +
            "CQYDVQQGEwJIUjEdMBsGA1UEChMURmluYW5jaWpza2EgYWdlbmNpamExGjAYBgNV\n" +
            "BAMTEUZpbmEgRGVtbyBSb290IENBMB4XDTE0MDMyNTA0NDU0N1oXDTI0MDMyNTA1\n" +
            "MTU0N1owSDELMAkGA1UEBhMCSFIxHTAbBgNVBAoTFEZpbmFuY2lqc2thIGFnZW5j\n" +
            "aWphMRowGAYDVQQDExFGaW5hIERlbW8gQ0EgMjAxNDCCAiIwDQYJKoZIhvcNAQEB\n" +
            "BQADggIPADCCAgoCggIBAMBi387JXbuIneYvs/NdvheVKb7RGrX5vuIrxaHw1iZN\n" +
            "Gru0T0pHwr/LE6BcFSLCJ5yc1Pr+rAjcTM9tQJAubZbLYx+OmZOJhHfCGbYtm35q\n" +
            "psVwF/hhCdNf5xfxLD2EdpSuM49xRy1/5C06qcA8o0GSyVBUUSaM6P4mI5t6zcUY\n" +
            "1B1CW8aslv0AWIduvwMLeRXGEfO0jikiWF+t1NcWI9toF+O+UoO7V+9893O+1PSa\n" +
            "Kuid7ZIA/OaBXsaIIpAHMsECIQ7ZZTpvexd/JSF6z62fRhBvWKUsHWCOckjp40nq\n" +
            "tAgPqN8rVz0zzD+CJB44P5h/NgvaeP5HdAEnrcA8p5MxLaqZ+MZQAkLHNKNxsHuH\n" +
            "NwETBDM/Oq9eDCdG0UZW6YXm2DRzTagVaDduNKvqXooQej8QEycoONo5c9atFUZC\n" +
            "TiBFS8wf6Hjp/pl0UlfiHdUxayuhV2VDagQHlzMgnZFs3fww50r+cYEJXasBKoOV\n" +
            "ybT1ZDLz66eKOxfsyCHJfZhtkgM38TIEtsdfjcERjexWrYviAHj5Qz1dByacZGiD\n" +
            "iB93dIVsX5jCnaRXZYdwbtD7EJGjkONntDO8EqIa+NwpH2DJyYz9FGtAR/5PhCus\n" +
            "vbCu1jSJ31zpZTcX9doTqj9EcBNQOtwuTZEPakn0COGod9uW/9ah6UozKMvnJRU9\n" +
            "AgMBAAGjggHeMIIB2jAOBgNVHQ8BAf8EBAMCAQYwEgYDVR0TAQH/BAgwBgEB/wIB\n" +
            "ADBRBgNVHSAESjBIMEYGByt8iFAFAQEwOzA5BggrBgEFBQcCARYtaHR0cDovL2Rl\n" +
            "bW8tcGtpLmZpbmEuaHIvY3BzL2NwZGVtb3Jvb3QxLTAucGRmMH4GCCsGAQUFBwEB\n" +
            "BHIwcDAoBggrBgEFBQcwAYYcaHR0cDovL2RlbW8yMDE0LW9jc3AuZmluYS5ocjBE\n" +
            "BggrBgEFBQcwAoY4aHR0cDovL2RlbW8tcGtpLmZpbmEuaHIvY2VydGlmaWthdGkv\n" +
            "ZGVtbzIwMTRfcm9vdF9jYS5jZXIwgaAGA1UdHwSBmDCBlTAyoDCgLoYsaHR0cDov\n" +
            "L2RlbW8tcGtpLmZpbmEuaHIvY3JsL0RlbW9Sb290MjAxNC5jcmwwX6BdoFukWTBX\n" +
            "MQswCQYDVQQGEwJIUjEdMBsGA1UEChMURmluYW5jaWpza2EgYWdlbmNpamExGjAY\n" +
            "BgNVBAMTEUZpbmEgRGVtbyBSb290IENBMQ0wCwYDVQQDEwRDUkwxMB8GA1UdIwQY\n" +
            "MBaAFF9vWznJf0Hm5pEV+qG2tbLnglXVMB0GA1UdDgQWBBQ7hFoU9cU84Ug7XdEn\n" +
            "NXvVZbwOKjANBgkqhkiG9w0BAQsFAAOCAgEANVRLUsyhXTHuaMYDRbyqIb2hLwm2\n" +
            "VZzEyMgUYWNc+6WGhYKCcR0fADJco4/eAQ7rTMM5aSR/iAfkdC/DPpIWQt3IcnvB\n" +
            "JxBZWRzYZ2DN7jHOj1GatAY+bE8ELGaZ/TiWcmePZEazemJ1oCMwpejqIQcnEpJ/\n" +
            "51YGbMVCwLuEJ7I5+MVKbI6199ebSuLkxEt3wU1CUbEhScP0AICbxMEOnNYL37AX\n" +
            "vhP3LSPJIc0xVM9xG26qMYDdtwsRjb5sxuca6Nso8newK9t3PU2khF/Qcfu2OY1j\n" +
            "PLdCVO8uNFvVCWpWb7fVPd3/TaM3rfaoDbFg/FBJGUZjEo0nwmblUDYMH5VAWcFm\n" +
            "G/sOhkoSEtMyWMR7UL5PQEeT9Uxy708tsVgYZaG+G4bo6ZCGrEY/rDXoMneVAOrH\n" +
            "IfBmyNLmfPqJLdrln+u5Nt+n1y52nfWbeuxLHNghdFA7g//lJI2Wpk9Uc+p5g+7X\n" +
            "jTT6llUMZBBFiuuFTZtdtyF33JabUyr2QMCY6zkA1yxx8M4aoXavVlIPOegkFsgw\n" +
            "9ohfHJlV5xB870JVkXd3RCVn/YPfA9GATfY1eF0hm6cCOMn16yq/SW/Mjk0MA++x\n" +
            "iRkcUtyDQQZ7LZod/XBIC5fTde+OWUDNQpJ+KMDZpZWpDiQTZMl3xVlAQ+oDGvzu\n" +
            "zoLcNykYzj5wLFQ=\n" +
            "-----END CERTIFICATE-----\n" +
            "Bag Attributes\n" +
            "    friendlyName: Fina Demo Root CA - Financijska agencija\n" +
            "subject=/C=HR/O=Financijska agencija/CN=Fina Demo Root CA\n" +
            "issuer=/C=HR/O=Financijska agencija/CN=Fina Demo Root CA\n" +
            "-----BEGIN CERTIFICATE-----\n" +
            "MIIFejCCA2KgAwIBAgINANtffOEAAAAAUygcnzANBgkqhkiG9w0BAQsFADBIMQsw\n" +
            "CQYDVQQGEwJIUjEdMBsGA1UEChMURmluYW5jaWpza2EgYWdlbmNpamExGjAYBgNV\n" +
            "BAMTEUZpbmEgRGVtbyBSb290IENBMB4XDTE0MDMxODA5NDUwMFoXDTM0MDMxODEw\n" +
            "MTUwMFowSDELMAkGA1UEBhMCSFIxHTAbBgNVBAoTFEZpbmFuY2lqc2thIGFnZW5j\n" +
            "aWphMRowGAYDVQQDExFGaW5hIERlbW8gUm9vdCBDQTCCAiIwDQYJKoZIhvcNAQEB\n" +
            "BQADggIPADCCAgoCggIBAJfL3DlwWc7i4sNbDhuF1FhxptBX3JtkXil04uXgzRmf\n" +
            "qukZ4jSCTpd9Vdc1xizes0Yxt9oxtaxIDKWLtnQ7lp6tuyon7+5h5Z4/UZ6d3CTK\n" +
            "ps/2F9UgYAzG1KXYIiUrGDhvJJEHhRG3wHcaagyR4YGv0kMmlzw/Emp3tNZVmHe4\n" +
            "NEN0XCzCQp6Zn8DGofo3KuhfvEi5OkSGhR0kPSs7aEX654SQgp/+ruzmjtItowGb\n" +
            "jEM/1Q1pD8GDe53VSpMrkRm/tYzwGLwkAKwGJ/hynrhBxv6BmENFebhE0q72++Pk\n" +
            "T4FsN/5wMPFaClrzUj6wBoToruIeIMnut5xd/VnkkN5wN8o4tzHTku3ODotFF8Kb\n" +
            "//kwmsRqxEvwi3Dz4G70xYA8+eysb0LsuO1MNmrxz2oYOd2lG1iXEQeEV1GRFM9I\n" +
            "H25w7/iMBOIDX46HhrfCjqhVuWIALBodnIu9eaid4PAfHCTxOyXb6n5kE5e0K87c\n" +
            "d9RVRZ7KglHyfTqLSbF9Jd7BqNy8bzBOc8hppVCbkW0C+ucqUI6T2QsbyW81I1sO\n" +
            "C5IpN6U3ADEWgG12w4pdgBEoJXbrIjMlM01STfGe3cp1KjJqkx0dpPGYoObZ/vw2\n" +
            "3q2O7OGuNsLJuaAS3TN4UMSgC6GxOmElotlFmchKlIE4FclrcWXOqUWkbRY5Sp0f\n" +
            "AgMBAAGjYzBhMA4GA1UdDwEB/wQEAwIBBjAPBgNVHRMBAf8EBTADAQH/MB8GA1Ud\n" +
            "IwQYMBaAFF9vWznJf0Hm5pEV+qG2tbLnglXVMB0GA1UdDgQWBBRfb1s5yX9B5uaR\n" +
            "FfqhtrWy54JV1TANBgkqhkiG9w0BAQsFAAOCAgEAkiUD6hj4ZnPEhkNt2gnj4zlx\n" +
            "ed5DR+WOWTW3oen41rebQvbtH8lRsS2IYsLKR97TNzFIo5Aanrk9AA468L/5q4lv\n" +
            "ebXeCpKLbrX5mKyT3k+mWzyNFR/uMmDT7mFOP7iBMvIt3UsXdoZuTLwWpGzbfieB\n" +
            "lQHk/87XA2bcaKWKwMXefPqt0g96QB59b3r1CsKasWuGzEqoK9BKuMhFElC0XzK1\n" +
            "9HdFmV6AUt1aEqw5bRduoMjpEg37p7ZjQl6VCnMWwTjy1Ttw+1H9f30dDRO/Sgji\n" +
            "bblOKrCpayxHnyULe2mteZAjX5OMaAEBtIYtWS/Xt4lcqsRkA/erQsTFw2ED7fj7\n" +
            "Ftbi8k7W73RTqcM+PuN1H6o0XmNU7TkgH9fgPnlw9E80EFQWVoVFOm0eMVfhuc24\n" +
            "XHYwI5aixsZj0mU4MMmbbeSW3n5CzL0uWiaOcVCWgEoO8nkWhXnxkT2ASIxXnNTh\n" +
            "hY/THPBaZDSS9T3puuqTm6jNXH/L8ZyEN8mR7iYKtPhLbIjo2SUFkgNT4TYi8i7b\n" +
            "H3YkEC013EE4SBPO/rl9ZPAUjn7TPlPr7Kf15AdQZmyGD9itpBVutnKj799daeP2\n" +
            "PeGG9J7Rz+o+Z/jpkOdd6wFuP1XlTAR3h7luhzlvQT2Chrujth0zDlUR/qQVkKTO\n" +
            "Eark0byDjwMgKuvuQRk=\n" +
            "-----END CERTIFICATE-----\n" +
            "Bag Attributes\n" +
            "    friendlyName: te-616d9275-9bda-49df-bd96-a3a38e026cce\n" +
            "    localKeyID: F4 CC 1C 61 28 C8 D0 57 D4 C3 6E 8A 00 57 13 13 FC D9 25 DB \n" +
            "subject=/C=HR/O=PARTNER BANKA D.D./2.5.4.97=PSDHR-CNB-2408002/L=ZAGREB/CN=PABA/serialNumber=P1.39\n" +
            "issuer=/C=HR/O=Financijska agencija/CN=Fina Demo CA 2014\n" +
            "-----BEGIN CERTIFICATE-----\n" +
            "MIIH8DCCBdigAwIBAgIQMJtMwHL0twMAAAAAUywoeTANBgkqhkiG9w0BAQsFADBI\n" +
            "MQswCQYDVQQGEwJIUjEdMBsGA1UEChMURmluYW5jaWpza2EgYWdlbmNpamExGjAY\n" +
            "BgNVBAMTEUZpbmEgRGVtbyBDQSAyMDE0MB4XDTE5MDIwNzA5MjczN1oXDTIxMDIw\n" +
            "NzA5MjczN1owdjELMAkGA1UEBhMCSFIxGzAZBgNVBAoTElBBUlRORVIgQkFOS0Eg\n" +
            "RC5ELjEaMBgGA1UEYRMRUFNESFItQ05CLTI0MDgwMDIxDzANBgNVBAcTBlpBR1JF\n" +
            "QjENMAsGA1UEAxMEUEFCQTEOMAwGA1UEBRMFUDEuMzkwggEiMA0GCSqGSIb3DQEB\n" +
            "AQUAA4IBDwAwggEKAoIBAQDI8G8ckAmNbb3Oq1HRHoMEbVsZbWZI32BM/Y6BllGd\n" +
            "dgWqlTKwjaSwosiMLfGLiyYGJq2v7rQcERD+zL/pxpg+I400d1neXRXU8n0Q1tVO\n" +
            "3N8J5tojk8PWf4avaX4yCqhF6byzh8pjB7AFwUdaGIlk2/K+ofGoLGH2jBWI8SsL\n" +
            "HKoJIGWSdlRP+ofa3khlka6/Yn0EGXpqf410a9pEb8Ql8sYC+gkWf/gNVC8ZZ37K\n" +
            "QEhytarThOsbzJY990Vj7IWzD4yJJP8bHeB0rfFnPidCkDfd0VLKh8+M5XsRVQD3\n" +
            "qiDNoVPUjtsbHjKOzbnnfiDYeAMOYybd5tWGCNSzb/bZAgMBAAGjggOmMIIDojAO\n" +
            "BgNVHQ8BAf8EBAMCB4AwgasGA1UdIASBozCBoDCBkgYJK3yIUAUgDQEBMIGEMEAG\n" +
            "CCsGAQUFBwIBFjRodHRwOi8vZGVtby1wa2kuZmluYS5oci9jcHMvY3BzcWNkZW1v\n" +
            "MjAxNHYyLTAtaHIucGRmMEAGCCsGAQUFBwIBFjRodHRwOi8vZGVtby1wa2kuZmlu\n" +
            "YS5oci9jcHMvY3BzcWNkZW1vMjAxNHYyLTAtZW4ucGRmMAkGBwQAi+xAAQEwfQYI\n" +
            "KwYBBQUHAQEEcTBvMCgGCCsGAQUFBzABhhxodHRwOi8vZGVtbzIwMTQtb2NzcC5m\n" +
            "aW5hLmhyMEMGCCsGAQUFBzAChjdodHRwOi8vZGVtby1wa2kuZmluYS5oci9jZXJ0\n" +
            "aWZpa2F0aS9kZW1vMjAxNF9zdWJfY2EuY2VyMIH7BggrBgEFBQcBAwSB7jCB6zAV\n" +
            "BggrBgEFBQcLAjAJBgcEAIvsSQECMAgGBgQAjkYBATByBgYEAI5GAQUwaDAyFixo\n" +
            "dHRwczovL2RlbW8tcGtpLmZpbmEuaHIvcGRzL1BEU1FDMS0wLWVuLnBkZhMCZW4w\n" +
            "MhYsaHR0cHM6Ly9kZW1vLXBraS5maW5hLmhyL3Bkcy9QRFNRQzEtMC1oci5wZGYT\n" +
            "AmhyMBMGBgQAjkYBBjAJBgcEAI5GAQYCMD8GBgQAgZgnAjA1MBMwEQYHBACBmCcB\n" +
            "AQwGUFNQX0FTDBZDcm9hdGlhbiBOYXRpb25hbCBCYW5rDAZIUi1DTkIwggEYBgNV\n" +
            "HR8EggEPMIIBCzCBpqCBo6CBoIYoaHR0cDovL2RlbW8tcGtpLmZpbmEuaHIvY3Js\n" +
            "L2RlbW8yMDE0LmNybIZ0bGRhcDovL2RlbW8tbGRhcC5maW5hLmhyL2NuPUZpbmEl\n" +
            "MjBEZW1vJTIwQ0ElMjAyMDE0LG89RmluYW5jaWpza2ElMjBhZ2VuY2lqYSxjPUhS\n" +
            "P2NlcnRpZmljYXRlUmV2b2NhdGlvbkxpc3QlM0JiaW5hcnkwYKBeoFykWjBYMQsw\n" +
            "CQYDVQQGEwJIUjEdMBsGA1UEChMURmluYW5jaWpza2EgYWdlbmNpamExGjAYBgNV\n" +
            "BAMTEUZpbmEgRGVtbyBDQSAyMDE0MQ4wDAYDVQQDEwVDUkwxMzAfBgNVHSMEGDAW\n" +
            "gBQ7hFoU9cU84Ug7XdEnNXvVZbwOKjAdBgNVHQ4EFgQUsmUKFftloHQStOTg9Cvb\n" +
            "ijhOtzYwCQYDVR0TBAIwADANBgkqhkiG9w0BAQsFAAOCAgEAiOmTDms9WsqyZDeq\n" +
            "G7UHu4duB1n4PI7dIBHQwAQv8wNpQElyWWVsAah3/dMLt/VlE8aJ9dEjQpmDgx8x\n" +
            "dvqL899p86O5bR0MENQt5vfiIWuYQbnc+q0Js7z9z0gnBOYqFXh3qCVp41ZcD86c\n" +
            "aqjGumCjHR0FOfRc1WDF3qp0hMLdHJWRFNU/5auMbt6LJsqRlyKHaXhaJbD5+60x\n" +
            "/fjtQ1k07to+IM3VD2C7mI2seCDAf+w2HFyxfeZ7KaXDTvGN8bt2dbLMO0y+7mMz\n" +
            "hnyi4xjfvS1OWcO6HjJWO+mhkzB5mV5zWCNK+/05TcWF76rjGAGldMVgu/JV+zIh\n" +
            "x/xuwNuC8dPfZza2NUF1pyPU1WG+RT3xy6A2+HJbFCjRMTR7coXZ4hnctz23npsV\n" +
            "4HV/o+g1Go7CyIMv5saNbOTp8U+IJ5AAeJ1MG2Q9eRfa1llGTKExZUrIou4bKnoH\n" +
            "mOrLWIomOt7WQjdPiX9UA5U+s/ImVrmx9ruMSn5kUqt8/4luvU8X4CRjrCmIEfTT\n" +
            "Y0A1JZlqaw9nRdabvWeUSqsVsrU1mhRQP8KTXOUG2y1TBKXgf6P2Q9UD06OtAqO5\n" +
            "rCQSoXSBMYvPxLZcGz98VYB6+zzSZNp5yOp7b1cKe4e7pvwNGb8MU4oNwBmi08v4\n" +
            "mbf64qYUOyQledAgDVuAd1+pta8=\n" +
            "-----END CERTIFICATE-----";
}
