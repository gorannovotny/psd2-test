package hr.abc.psd2.model.dto;

import java.io.Serializable;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

public class CertificateSignInfoDto implements Serializable {

    private static final long serialVersionUID = 1L;

    PrivateKey privateKey = null;
    X509Certificate certificate = null;
    String certificatePem = null;

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public X509Certificate getCertificate() {
        return certificate;
    }

    public void setCertificate(X509Certificate certificate) {
        this.certificate = certificate;
    }

    public String getCertificatePem() {
        return certificatePem;
    }

    public void setCertificatePem(String certificatePem) {
        this.certificatePem = certificatePem;
    }
}
