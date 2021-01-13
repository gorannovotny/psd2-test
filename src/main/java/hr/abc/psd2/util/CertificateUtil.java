package hr.abc.psd2.util;

import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.TPP_SIGNATURE_CERTIFICATE;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.security.KeyStore;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.net.ssl.SSLPeerUnverifiedException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import io.vertx.core.http.HttpServerRequest;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERUTF8String;
import org.bouncycastle.asn1.x509.qualified.QCStatement;

import hr.abc.psd2.model.dto.CertificateInfoDto;
import hr.abc.psd2.model.dto.CertificateSignInfoDto;


public class CertificateUtil {
    public static void getCertificateDetails(String jksPath, String jksPassword) {

        CertificateDetails certDetails = null;

        try {
            KeyStore p12 = KeyStore.getInstance("pkcs12");
            p12.load(new FileInputStream(jksPath), jksPassword.toCharArray());
            Enumeration e = p12.aliases();
            while (e.hasMoreElements()) {
                String alias = (String) e.nextElement();
                X509Certificate c = (X509Certificate) p12.getCertificate(alias);
                // CertificateInfoDto certificateInfoDto = getCertificateDetails(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CertificateInfoDto getCertificateDetails(X509Certificate certificate) {
        CertificateInfoDto certificateInfoDto = new CertificateInfoDto();
        certificateInfoDto.setCertificate(certificate);
        certificateInfoDto.setIsValid(true);
        List<String> tppRoles = new ArrayList<>();
        try {
            //valid from
            if (certificate.getNotBefore() != null) {
                Instant instant = Instant.ofEpochMilli(certificate.getNotBefore().getTime());
                certificateInfoDto.setValidFrom(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()));
            }
            //valid to
            if (certificate.getNotAfter() != null) {
                Instant instant = Instant.ofEpochMilli(certificate.getNotAfter().getTime());
                certificateInfoDto.setValidTo(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()));
            }
            Principal subject = certificate.getSubjectDN();
            String subjectArray[] = subject.toString().split(",");
            for (String sub : subjectArray) {
                String s = StringUtils.trim(sub);
                // TPP ID
                if (StringUtils.startsWith(s, Psd2Constants.ORGANIZATION_ID_OID)) {
                    certificateInfoDto.setOrganizationID(StringUtils.substringAfter(s, Psd2Constants.ORGANIZATION_ID_OID));
                }
                // TPP NAME
                if (StringUtils.startsWith(s, Psd2Constants.ORGANIZATION_NAME_OID)) {
                    certificateInfoDto.setOrganizationName(StringUtils.substringAfter(s, Psd2Constants.ORGANIZATION_NAME_OID));
                }
            }
            //QC STATEMENT
            final byte[] qcStatement = certificate.getExtensionValue(Psd2Constants.QC_STATEMENTS_CERTIFICATE_EXTENSION_OID);
            if (qcStatement != null) {
                final ASN1Sequence seq = getAsn1SequenceFromDerOctetString(qcStatement);
                QCStatement statement = null;
                Boolean oneQCStatement = false;
                for (int ii = 0; ii < seq.size(); ii++) {
                    try {
                        statement = QCStatement.getInstance(seq.getObjectAt(ii));
                    } catch (Exception ae) {
                        try {
                            statement = QCStatement.getInstance(seq);
                            oneQCStatement = true;
                        } catch (Exception e) {
                            certificateInfoDto.setIsValid(false);
                            certificateInfoDto.setErrorList(new ArrayList<>(Arrays.asList("Greška kod čitanja podataka iz certifikata - QCStatement!")));
                        }
                    }
                    //PSD2 QC STATEMENT
                    if (StringUtils.equals(statement.getStatementId().toString(), Psd2Constants.ID_ETSI_PSD2_QC_STATEMENT)) {
                        Enumeration psd2Statement = ((DERSequence) ((QCStatement) statement).getStatementInfo()).getObjects();
                        for (Object psd2Elem : Collections.list(psd2Statement)) {
                            //PSD2 ROLA
                            if (psd2Elem instanceof DERSequence) {
                                Enumeration psd2Roles = ((DERSequence) psd2Elem).getObjects();
                                for (Object psd2Role : Collections.list(psd2Roles)) {
                                    tppRoles.add(((DERSequence) psd2Role).getObjectAt(1).toString());
                                }
                            }
                            //COMPETENT AUTHORITY ID, NAME
                            if (psd2Elem instanceof DERUTF8String) {
                                if (StringUtils.isBlank(certificateInfoDto.getCompetentAuthorityName())) {
                                    certificateInfoDto.setCompetentAuthorityName(psd2Elem.toString());
                                } else {
                                    certificateInfoDto.setCompetentAuthorityID(psd2Elem.toString());
                                }
                            }
                        }
                        if (tppRoles != null && !tppRoles.isEmpty()) {
                            certificateInfoDto.setTppRoles(tppRoles);
                        }
                    }
                    if (oneQCStatement) {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            certificateInfoDto.setIsValid(false);
            certificateInfoDto.setErrorList(new ArrayList<>(Arrays.asList("Greška kod čitanja podataka iz certifikata!")));
        }
        return certificateInfoDto;
    }

    public static CertificateInfoDto validateCertificateDetails(CertificateInfoDto certificateInfoDto) {
        List<String> errorList = new ArrayList<>();
        try {
            if (certificateInfoDto.getIsValid()) {
                if (certificateInfoDto.getValidFrom() == null || certificateInfoDto.getValidTo() == null) {
                    certificateInfoDto.setIsValid(false);
                    errorList.add("Certifikat nije valjan - nedostaje Valid from ili Valid to parametar!");
                }
                if (LocalDateTime.now().isBefore(certificateInfoDto.getValidFrom()) || LocalDateTime.now().isAfter(certificateInfoDto.getValidTo())) {
                    certificateInfoDto.setIsValid(false);
                    errorList.add("Certifikat nije valjan - istekao je ili još nije počeo vrijediti!");
                }
                if (StringUtils.isBlank(certificateInfoDto.getOrganizationID())) {
                    certificateInfoDto.setIsValid(false);
                    errorList.add("Certifikat nije valjan - nedostaje parametar TPP ID!");
                }
                if (StringUtils.isBlank(certificateInfoDto.getOrganizationName())) {
                    certificateInfoDto.setIsValid(false);
                    errorList.add("Certifikat nije valjan - nedostaje parametar TPP Name!");
                }
                if (certificateInfoDto.getTppRoles() == null || certificateInfoDto.getTppRoles().size() < 1) {
                    certificateInfoDto.setIsValid(false);
                    errorList.add("Certifikat nije valjan - nedostaje parametar Uloga TPP-a!");
                }
                if (StringUtils.isBlank(certificateInfoDto.getCompetentAuthorityID())) {
                    certificateInfoDto.setIsValid(false);
                    errorList.add("Certifikat nije valjan - nedostaje parametar ID nadležnog tijela!");
                }
                if (StringUtils.isBlank(certificateInfoDto.getCompetentAuthorityName())) {
                    certificateInfoDto.setIsValid(false);
                    errorList.add("Certifikat nije valjan - nedostaje parametar Naziv nadležnog tijela!");
                }
                if (errorList != null && errorList.size() > 0) {
                    certificateInfoDto.setErrorList(errorList);
                }
            }
        } catch (Exception e) {
            certificateInfoDto.setIsValid(false);
            certificateInfoDto.setErrorList(new ArrayList<>(Arrays.asList("Greška kod validacije certifikata!")));
        }
        return certificateInfoDto;
    }

    public static CertificateInfoDto getCertificateDetails(X509Certificate certificate, String certificateType) {
        CertificateInfoDto certificateInfoDto = new CertificateInfoDto();
        if (StringUtils.equals(Psd2Constants.CERTIFICATE_TYPE_QWAC, certificateType)) {
            certificateInfoDto.setCertificate(certificate);
        } else {
            certificateInfoDto.setCertificate(certificate);
        }
        certificateInfoDto.setIsValid(true);
        List<String> tppRoles = new ArrayList<>();
        try {
            //valid from
            if (certificate.getNotBefore() != null) {
                Instant instant = Instant.ofEpochMilli(certificate.getNotBefore().getTime());
                certificateInfoDto.setValidFrom(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()));
            }
            //valid to
            if (certificate.getNotAfter() != null) {
                Instant instant = Instant.ofEpochMilli(certificate.getNotAfter().getTime());
                certificateInfoDto.setValidTo(LocalDateTime.ofInstant(instant, ZoneId.systemDefault()));
            }
            Principal subject = certificate.getSubjectDN();
            String subjectArray[] = subject.toString().split(",");
            for (String sub : subjectArray) {
                String s = StringUtils.trim(sub);
                // TPP ID
                if (StringUtils.startsWith(s, Psd2Constants.ORGANIZATION_ID_OID)) {
                    certificateInfoDto.setOrganizationID(StringUtils.substringAfter(s, Psd2Constants.ORGANIZATION_ID_OID));
                }
                // TPP NAME
                if (StringUtils.startsWith(s, Psd2Constants.ORGANIZATION_NAME_OID)) {
                    certificateInfoDto.setOrganizationName(StringUtils.substringAfter(s, Psd2Constants.ORGANIZATION_NAME_OID));
                }
            }
            //QC STATEMENT
            final byte[] qcStatement = certificate.getExtensionValue(Psd2Constants.QC_STATEMENTS_CERTIFICATE_EXTENSION_OID);
            if (qcStatement != null) {
                final ASN1Sequence seq = getAsn1SequenceFromDerOctetString(qcStatement);
                QCStatement statement = null;
                Boolean oneQCStatement = false;
                for (int ii = 0; ii < seq.size(); ii++) {
                    try {
                        statement = QCStatement.getInstance(seq.getObjectAt(ii));
                    } catch (Exception ae) {
                        try {
                            statement = QCStatement.getInstance(seq);
                            oneQCStatement = true;
                        } catch (Exception e) {
                            certificateInfoDto.setIsValid(false);
                            certificateInfoDto.setErrorList(new ArrayList<>(Arrays.asList("Greška kod čitanja podataka iz certifikata - QCStatement!")));
                        }
                    }
                    //PSD2 QC STATEMENT
                    if (StringUtils.equals(statement.getStatementId().toString(), Psd2Constants.ID_ETSI_PSD2_QC_STATEMENT)) {
                        Enumeration psd2Statement = ((DERSequence) ((QCStatement) statement).getStatementInfo()).getObjects();
                        for (Object psd2Elem : Collections.list(psd2Statement)) {
                            //PSD2 ROLA
                            if (psd2Elem instanceof DERSequence) {
                                Enumeration psd2Roles = ((DERSequence) psd2Elem).getObjects();
                                for (Object psd2Role : Collections.list(psd2Roles)) {
                                    tppRoles.add(((DERSequence) psd2Role).getObjectAt(1).toString());
                                }
                            }
                            //COMPETENT AUTHORITY ID, NAME
                            if (psd2Elem instanceof DERUTF8String) {
                                if (StringUtils.isBlank(certificateInfoDto.getCompetentAuthorityName())) {
                                    certificateInfoDto.setCompetentAuthorityName(psd2Elem.toString());
                                } else {
                                    certificateInfoDto.setCompetentAuthorityID(psd2Elem.toString());
                                }
                            }
                        }
                        if (tppRoles != null && !tppRoles.isEmpty()) {
                            certificateInfoDto.setTppRoles(tppRoles);
                        }
                    }
                    if (oneQCStatement) {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            certificateInfoDto.setIsValid(false);
            certificateInfoDto.setErrorList(new ArrayList<>(Arrays.asList("Greška kod čitanja podataka iz certifikata!")));
        }
        return certificateInfoDto;
    }

    public static CertificateInfoDto validateCertificateDetails(CertificateInfoDto certificateInfoDto, String certificateName) {
        List<String> errorList = new ArrayList<>();
        try {
            if (certificateInfoDto.getIsValid()) {
                if (certificateInfoDto.getValidFrom() == null || certificateInfoDto.getValidTo() == null) {
                    certificateInfoDto.setIsValid(false);
                    errorList.add("Certifikat " + certificateName + " nije valjan - nedostaje Valid from ili Valid to parametar!");
                }
                if (LocalDateTime.now().isBefore(certificateInfoDto.getValidFrom()) || LocalDateTime.now().isAfter(certificateInfoDto.getValidTo())) {
                    certificateInfoDto.setIsValid(false);
                    errorList.add("Certifikat " + certificateName + " nije valjan - istekao je ili još nije počeo vrijediti!");
                }
                if (StringUtils.isBlank(certificateInfoDto.getOrganizationID())) {
                    certificateInfoDto.setIsValid(false);
                    errorList.add("Certifikat " + certificateName + " nije valjan - nedostaje parametar TPP ID!");
                }
                if (StringUtils.isBlank(certificateInfoDto.getOrganizationName())) {
                    certificateInfoDto.setIsValid(false);
                    errorList.add("Certifikat " + certificateName + " nije valjan - nedostaje parametar TPP Name!");
                }
                if (certificateInfoDto.getTppRoles() == null || certificateInfoDto.getTppRoles().size() < 1) {
                    certificateInfoDto.setIsValid(false);
                    errorList.add("Certifikat " + certificateName + " nije valjan - nedostaje parametar Uloga TPP-a!");
                }
                if (StringUtils.isBlank(certificateInfoDto.getCompetentAuthorityID())) {
                    certificateInfoDto.setIsValid(false);
                    errorList.add("Certifikat " + certificateName + " nije valjan - nedostaje parametar ID nadležnog tijela!");
                }
                if (StringUtils.isBlank(certificateInfoDto.getCompetentAuthorityName())) {
                    certificateInfoDto.setIsValid(false);
                    errorList.add("Certifikat " + certificateName + " nije valjan - nedostaje parametar Naziv nadležnog tijela!");
                }
                if (errorList != null && errorList.size() > 0) {
                    certificateInfoDto.setErrorList(errorList);
                }
            }
        } catch (Exception e) {
            certificateInfoDto.setIsValid(false);
            certificateInfoDto.setErrorList(new ArrayList<>(Arrays.asList("Greška kod validacije certifikata!")));
        }
        return certificateInfoDto;
    }

    public static ASN1Sequence getAsn1SequenceFromDerOctetString(byte[] bytes) {
        ASN1Sequence resultSeq = null;
        try {
            ASN1InputStream input = new ASN1InputStream(bytes);
            final DEROctetString s = (DEROctetString) input.readObject();
            final byte[] content = s.getOctets();
            input.close();
            input = new ASN1InputStream(content);
            resultSeq = (ASN1Sequence) input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultSeq;
    }

    public static ASN1Sequence getAsn1SequenceFromDerOctetString1(byte[] bytes) {
        return getASN1Sequence(getDEROctetStringContent(bytes));
    }

    private static byte[] getDEROctetStringContent(byte[] bytes) {
        try (ASN1InputStream input = new ASN1InputStream(bytes)) {
            final DEROctetString s = (DEROctetString) input.readObject();
            return s.getOctets();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ASN1Sequence getASN1Sequence(byte[] bytes) {
        try (ASN1InputStream input = new ASN1InputStream(bytes)) {
            return (ASN1Sequence) input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get the list of all QCStatement Ids that are present in the certificate.
     * (As per ETSI EN 319 412-5 V2.1.1)
     *
     * @param certificate
     * @return
     */
    public static List<String> getQCStatementsIdList(X509Certificate certificate) {
        final List<String> extensionIdList = new ArrayList<String>();
        //final byte[] qcStatement = certificate.getExtensionValue(Extension.qCStatements.getId());
        final byte[] qcStatement = certificate.getExtensionValue(Psd2Constants.QC_STATEMENTS_CERTIFICATE_EXTENSION_OID);
        if (qcStatement != null) {
            final ASN1Sequence seq = getAsn1SequenceFromDerOctetString(qcStatement);
            // Sequence of QCStatement
            for (int ii = 0; ii < seq.size(); ii++) {
                final QCStatement statement = QCStatement.getInstance(seq.getObjectAt(ii));
                extensionIdList.add(statement.getStatementId().getId());
            }
        }
        return extensionIdList;
    }


    public static CertificateInfoDto getQwacCertificate(HttpServerRequest request) throws SSLPeerUnverifiedException {
        CertificateInfoDto certificateInfoDto = new CertificateInfoDto();
        X509Certificate qWacCertificate = null;
        String certificates = request.getHeader("ssl_client_cert");
        if (StringUtils.isNotBlank(certificates) && certificates.indexOf("-----BEGIN CERTIFICATE-----") > -1) {
            try {
                qWacCertificate = parseCertificate(certificates);
                certificateInfoDto.setCertificate(qWacCertificate);
                certificateInfoDto.setIsValid(true);
            } catch (CertificateException e) {
                certificateInfoDto.setIsValid(false);
                certificateInfoDto.getErrorList().add("Greška kod čitanja QWAC certifikata!");
                return certificateInfoDto;
            }
        } else {
            //TODO
            X509Certificate[] certs = null;// request.peerCertificateChain();
            if (certs == null) {
                certificateInfoDto.setIsValid(false);
                certificateInfoDto.getErrorList().add("Nedostaje QWAC certifikat!");
                return certificateInfoDto;
            }
            qWacCertificate = certs[0];
            certificateInfoDto.setCertificate(qWacCertificate);
            certificateInfoDto.setIsValid(true);
        }
        return certificateInfoDto;
    }

    public static CertificateInfoDto getQSealCertificate(HttpServerRequest request) {
        CertificateInfoDto certificateInfoDto = new CertificateInfoDto();
        X509Certificate qSealCertificate = null;
        String certificates = request.getHeader(TPP_SIGNATURE_CERTIFICATE.getValue());
        if (StringUtils.isNotBlank(certificates) && certificates.indexOf("-----BEGIN CERTIFICATE-----") > -1) {
            try {
                qSealCertificate = parseCertificate(certificates);
                certificateInfoDto.setCertificate(qSealCertificate);
                certificateInfoDto.setIsValid(true);
            } catch (CertificateException e) {
                certificateInfoDto.setIsValid(false);
                certificateInfoDto.getErrorList().add("Greška kod čitanja QSeal certifikata!");
                return certificateInfoDto;
            }
        } else {
            certificateInfoDto.setIsValid(false);
            certificateInfoDto.getErrorList().add("Nedostaje QSeal certifikat!");
            return certificateInfoDto;
        }
        return certificateInfoDto;
    }

    public static X509Certificate parseCertificate(String cert) throws CertificateException {

        //before decoding we need to get rid off the prefix and suffix
        byte[] decoded = Base64.getDecoder().decode(cert.replaceAll("-----BEGIN CERTIFICATE-----", "").replaceAll("-----END CERTIFICATE-----", ""));

        return (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(decoded));
    }

    public static CertificateInfoDto getQwacCertificateTest(String qwac) {
        CertificateInfoDto certificateInfoDto = new CertificateInfoDto();
        X509Certificate qWacCertificate = null;
        if (StringUtils.isNotBlank(qwac) && qwac.indexOf("-----BEGIN CERTIFICATE-----") > -1) {
            try {
                qWacCertificate = parseCertificate(qwac);
                certificateInfoDto.setCertificate(qWacCertificate);
                certificateInfoDto.setIsValid(true);
            } catch (CertificateException e) {
                certificateInfoDto.setIsValid(false);
                certificateInfoDto.getErrorList().add("Greška kod čitanja QWAC certifikata!");
            }
        } else {
//            X509Certificate[] certs = (X509Certificate[]) request.getAttribute("javax.servlet.request.X509Certificate");
//            if(certs == null) {
//                certificateInfoDto.setIsValid(false);
//                certificateInfoDto.getErrorList().add("Nedostaje QWAC certifikat! Kontaktirajte administratora!");
//            }
//            qWacCertificate = certs[0];
//            certificateInfoDto.setQWacCertificate(qWacCertificate);
//            certificateInfoDto.setIsValid(true);
        }
        return certificateInfoDto;
    }

    public static CertificateSignInfoDto getCertificateSignInfoFromKeyStore(InputStream store, String keyStorePass, String alias) throws Exception {
        CertificateSignInfoDto certificateSignInfoDto = new CertificateSignInfoDto();
        X509Certificate x509Certificate = null;
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(store, keyStorePass.toCharArray());
        //certificate
        x509Certificate = (X509Certificate) keyStore.getCertificate(alias);
        certificateSignInfoDto.setCertificate(x509Certificate);
        //privateKey
        PrivateKey privateKey = (PrivateKey) keyStore.getKey(alias, keyStorePass.toCharArray());
        certificateSignInfoDto.setPrivateKey(privateKey);
        //pem format
        certificateSignInfoDto.setCertificatePem(certToString(x509Certificate));
        return certificateSignInfoDto;
    }

    private static String certToString(X509Certificate cert) throws Exception {
        StringWriter sw = new StringWriter();

        sw.write("-----BEGIN CERTIFICATE-----\n");
        sw.write(DatatypeConverter.printBase64Binary(cert.getEncoded()).replaceAll("(.{64})", "$1\n"));
        sw.write("\n-----END CERTIFICATE-----\n");

        return sw.toString();
    }

}

