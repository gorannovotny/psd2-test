package hr.abc.psd2.resources.ws.filter;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertPath;
import java.security.cert.CertPathBuilder;
import java.security.cert.CertPathBuilderResult;
import java.security.cert.CertPathValidator;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.PKIXBuilderParameters;
import java.security.cert.PKIXRevocationChecker;
import java.security.cert.X509CertSelector;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.abc.psd2.model.dto.CertificateInfoDto;
import hr.abc.psd2.util.CertificateUtil;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChainOfTrust {

    final static Logger LOGGER = LoggerFactory.getLogger(ChainOfTrust.class);


    public static CertificateInfoDto getCertificateInfo(String requestParam, InputStream keyStorePath, String keyStorePass) {
        CertificateInfoDto info = new CertificateInfoDto();
        try {

            CertPath certPath = ChainOfTrust.populateMissingCerts(requestParam, keyStorePath, keyStorePass);

            //user certificate
            X509Certificate cert = (X509Certificate) certPath.getCertificates().get(0);
            //get certificate details
            info = CertificateUtil.getCertificateDetails(cert);
            //validate validity (date from, date to,..)
            info = CertificateUtil.validateCertificateDetails(info);
            //key usage digitalSignature
            if (cert.getKeyUsage()[0] && !cert.getKeyUsage()[5]) info.setIsValid(true);
            else {
                info.setIsValid(false);
                info.getErrorList().add("Krivi key usage certifikata");
            }
        } catch (Exception e) {
            info.getErrorList().add(e.getMessage());
            info.setIsValid(false);

            log.warn(e.getMessage());
            log.debug(e.getMessage(), e);
        }
        return info;
        //TODO  getCertificateDetails napraviti tu
        //TODO  keyUsage lepše napisati
        //

    }

    private static CertPath populateMissingCerts(String requestParam, InputStream store, String keyStorePass) throws Exception {
        CertPathBuilder cpb = null;
        CertPathBuilderResult result = null;
        CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
        //byte[] decoded = org.apache.axis.encoding.Base64.decode(requestParam.replaceAll("-----BEGIN CERTIFICATE-----", "").replaceAll("-----END CERTIFICATE-----", ""));
        /**
         * TODO Ivana
         */
        byte[] decoded = Base64.getDecoder().decode(requestParam.replaceAll("-----BEGIN CERTIFICATE-----", "").replaceAll("-----END CERTIFICATE-----", ""));

        InputStream inStream = new ByteArrayInputStream(decoded);
        Collection<? extends Certificate> collection = certFactory.generateCertificates(inStream);
        List<Certificate> certList = new ArrayList<>(collection);

        // pali traženje issuera prek interneta
        System.setProperty("com.sun.security.enableAIAcaIssuers", "true");
        cpb = CertPathBuilder.getInstance("PKIX");

        // napuni CA
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(store, keyStorePass.toCharArray());

        X509CertSelector selector = new X509CertSelector();
        selector.setCertificate((X509Certificate) certList.get(0));
        PKIXBuilderParameters params = new PKIXBuilderParameters(keyStore, selector);

        CertPathValidator validator = CertPathValidator.getInstance("PKIX");
        //validate path (with revoke OSCP)
        PKIXRevocationChecker checker = (PKIXRevocationChecker) validator.getRevocationChecker();
        params.setRevocationEnabled(true);
        params.addCertPathChecker(checker);

        result = cpb.build(params);
        return result.getCertPath();
    }

}
