package hr.abc.psd2.util;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.X509Certificate;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;

import org.apache.commons.lang3.StringUtils;

import hr.abc.psd2.model.dto.ProcessRequestDto;
import hr.abc.psd2.model.dto.error.ErrorCode;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Tomica on 10-Apr-19.
 */
@Slf4j
public class SignatureUtil {
    /**
     * Verify Signature
     *
     * @param signatureAlgorithm - "SHA512withRSA", "SHA256withRSA" https://docs.oracle.com/javase/7/docs/technotes/guides/security/StandardNames.html
     * @param publicKey
     * @param signingString
     * @param signature
     */
    public static ProcessRequestDto verifySignature(String signatureAlgorithm, PublicKey publicKey, byte[] signingString, byte[] signature) {
        ProcessRequestDto processRequestDto = new ProcessRequestDto();

        Boolean isValid = false;
        //create a Signature object
        Signature sig = null;
        try {
            //Creating a Signature object
            sig = Signature.getInstance(signatureAlgorithm);
            //Initialize Signature object with the public key
            sig.initVerify(publicKey);
            //Update Signature object
            sig.update(signingString);
            //Verify the Signature
            isValid = sig.verify(signature);
            if (isValid) {
                processRequestDto.setIsValid(true);
            } else {
                processRequestDto.setIsValid(false);
                processRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.SIGNATURE_INVALID.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_signature_invalid", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
            }
        } catch (NoSuchAlgorithmException e) {
            processRequestDto.setIsValid(false);
            processRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.SIGNATURE_INVALID.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_signature_err_no_such_alg", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
            log.error("Greška provjere potpisa:", e);
        } catch (InvalidKeyException e) {
            processRequestDto.setIsValid(false);
            processRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.SIGNATURE_INVALID.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_signature_err_invalid_key", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
            log.error("Greška provjere potpisa:", e);
        } catch (SignatureException e) {
            processRequestDto.setIsValid(false);
            if (StringUtils.isNotBlank(e.getMessage())) {
                processRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.SIGNATURE_INVALID.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_signature_err", GenericBassxConstants.Psd2.MESSAGE_BUNDLE) + " " + e.getMessage()))));
            } else {
                processRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.SIGNATURE_INVALID.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_signature_err", GenericBassxConstants.Psd2.MESSAGE_BUNDLE)))));
            }

            log.error("Greška provjere potpisa:", e);
        }
        return processRequestDto;
    }

    /**
     * Generate Signature
     *
     * @param qSealCertificate
     * @param privateKey
     * @param signingString
     * @param
     */
    public static byte[] signMessage(X509Certificate qSealCertificate, PrivateKey privateKey, byte[] signingString) {
        Signature sig = null;
        byte[] realSignature = null;
        byte[] encodedSignature = null;
        try {
            //Creating a Signature object
            sig = Signature.getInstance(qSealCertificate.getSigAlgName());
            //Initialize Signature object with the public key
            sig.initSign(privateKey);
            //Update data
            sig.update(signingString);
            //Sign
            realSignature = sig.sign();
            encodedSignature = Base64.getEncoder().encode(realSignature);
//            System.out.println("Text [Byte Format] signature: " + realSignature.toString());
//            System.out.println("Text [Byte Format] encoded signature: " + encodedSignature.toString());
//            String digitalSignatureString = new String(realSignature);
//            System.out.println("Text signature : " + digitalSignatureString);

//            String digitalSignatureEncodedString = new String(encodedSignature);
            ///          System.out.println("Text encoded signature : " + digitalSignatureEncodedString);
        } catch (NoSuchAlgorithmException e) {
            log.error("Greška provjere potpisa:", e);
        } catch (InvalidKeyException e) {
            log.error("Greška provjere potpisa:", e);
        } catch (SignatureException e) {
            log.error("Greška provjere potpisa:", e);
        }
        return encodedSignature;
    }

    public static PrivateKey getPrivateKey() {
        PrivateKey privateKey = null;
        try {
            KeyFactory fac = KeyFactory.getInstance("RSA");
            EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(SignatureContants.QSEAL_PRIVATE_KEY.getBytes()));
            privateKey = fac.generatePrivate(privKeySpec);
        } catch (NoSuchAlgorithmException nsae) {
            log.error("Greška kod dohvata privatnog ključa:", nsae);
        } catch (InvalidKeySpecException ikse) {
            log.error("Greška kod dohvata privatnog ključa:", ikse);
        }
        return privateKey;
    }

}