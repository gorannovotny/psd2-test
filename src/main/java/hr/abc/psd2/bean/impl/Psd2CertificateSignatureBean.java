package hr.abc.psd2.bean.impl;

import static hr.abc.psd2.bean.impl.ServiceConstants.SERVICE_PABA;
import static hr.abc.psd2.bean.impl.ServiceConstants.TRUE;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.DIGEST;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.SIGNATURE;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.TPP_SIGNATURE_CERTIFICATE;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.context.Dependent;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Providers;
import javax.xml.bind.DatatypeConverter;

import io.vertx.core.http.HttpServerRequest;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import hr.abc.psd2.model.dto.CertificateInfoDto;
import hr.abc.psd2.model.dto.CertificateSignInfoDto;
import hr.abc.psd2.model.dto.ProcessRequestDto;
import hr.abc.psd2.model.dto.RequestHeaderDto;
import hr.abc.psd2.model.dto.error.ErrorCode;
import hr.abc.psd2.util.CertificateUtil;
import hr.abc.psd2.util.Psd2Constants;
import hr.abc.psd2.util.Psd2Constants.ResponsetHeaderAttributes;
import hr.abc.psd2.util.Psd2Util;
import hr.abc.psd2.util.SignatureContants;
import hr.abc.psd2.util.SignatureUtil;
import hr.abc.psd2.util.StringFunctions;
import io.quarkus.arc.properties.IfBuildProperty;
import lombok.extern.slf4j.Slf4j;
/**
 * Created by Tomica on 19-Feb-19.
 */
@Slf4j
@Dependent
@Transactional
public class Psd2CertificateSignatureBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Context
	private Providers providers;

	public CertificateInfoDto checkQSealCertificate(HttpServerRequest httpServletRequest) {
		CertificateInfoDto certificateInfoDto = new CertificateInfoDto();
		certificateInfoDto.setIsValid(true);
		X509Certificate certificate = null;
		try {
			String certificateHeader = httpServletRequest.getHeader(TPP_SIGNATURE_CERTIFICATE.getValue());
			if (certificateHeader == null) {
				certificateInfoDto.setIsValid(false);
				certificateInfoDto.setErrorList(new ArrayList<>(Arrays.asList("Nedostaje QSeal certifikat!")));
				return certificateInfoDto;
			}
			certificateInfoDto = CertificateUtil.getQSealCertificate(httpServletRequest);
			if (certificateInfoDto.getIsValid()) {
				certificateInfoDto = CertificateUtil.getCertificateDetails(certificateInfoDto.getCertificate(), Psd2Constants.CERTIFICATE_TYPE_QSEAL);
				if (certificateInfoDto.getIsValid()) {
					certificateInfoDto = CertificateUtil.validateCertificateDetails(certificateInfoDto, "QSeal");
				}
			}
		} catch (Exception e) {
			certificateInfoDto.setIsValid(false);
			certificateInfoDto.setErrorList(new ArrayList<>(Arrays.asList("Greška kod obrade QSeal certifikata!")));
			return certificateInfoDto;
		}
		return certificateInfoDto;
	}

	public CertificateInfoDto checkQWacCertificate(HttpServerRequest httpServletRequest) {
		CertificateInfoDto certificateInfoDto = new CertificateInfoDto();
		try {
			certificateInfoDto = CertificateUtil.getQwacCertificate(httpServletRequest);
			if (certificateInfoDto.getIsValid()) {
				//get certificate details
				certificateInfoDto = CertificateUtil.getCertificateDetails(certificateInfoDto.getCertificate(), Psd2Constants.CERTIFICATE_TYPE_QWAC);
				if (certificateInfoDto.getIsValid()) {
					//validate certificate
					certificateInfoDto = CertificateUtil.validateCertificateDetails(certificateInfoDto, "QWAC");
				}
			}
		} catch (Exception e) {
			certificateInfoDto.setIsValid(false);
			certificateInfoDto.setErrorList(new ArrayList<>(Arrays.asList("Greška kod obrade QWAC certifikata!")));
			return certificateInfoDto;
		}

		return certificateInfoDto;
	}

	public void validateSignature(HttpServletRequest httpServletRequest) {
		//validate request header - DIGEST, SIGNATURE, CERTIFICATE
		RequestHeaderDto requestHeaderDto = null;//Psd2Util.validateMandatoryAndGetAllHeaderAttributes(httpServletRequest, new ArrayList<String>(Arrays.asList(DIGEST.getValue(),
				//SIGNATURE.getValue(), TPP_SIGNATURE_CERTIFICATE.getValue())));
		if (!requestHeaderDto.getIsValid()) {
			//return TODO
		}

		X509Certificate certificate = null;
		X509Certificate[] certs = (X509Certificate[]) httpServletRequest.getAttribute("javax.servlet.request.X509Certificate");

		if (certs == null) {
			//TODO throw new AuthenticationException(InternationalizationUtil.getLocalMessage("authNoCertificate", GenericBassxConstants.Authentication.MESSAGE_BUNDLE));
		}
		certificate = certs[0];

		//certificate.checkValidity();  validate certificate
		certificate.getPublicKey(); //public key
		//certificate.

		//

	}

	/**
	 * Form sigining string for digital signature
	 *
	 * @param httpServletRequest - https://www.ietf.org/archive/id/draft-cavage-http-signatures-10.txt
	 * @return
	 */
	private RequestHeaderDto formSigningString(HttpServerRequest httpServletRequest) {
		RequestHeaderDto requestHeaderDto = null;
		String signingString = null;
		String signatureHeaders = httpServletRequest.getHeader(SIGNATURE.getValue());
		signatureHeaders = StringUtils.substringAfter(signatureHeaders, "headers=");
		signatureHeaders = StringUtils.substringBefore(signatureHeaders, ",");
		signatureHeaders = StringUtils.replace(signatureHeaders, "\"", "");
		List<String> listHeaders = StringFunctions.getListFromDelimitedText(signatureHeaders, " ");
		requestHeaderDto = null;//Psd2Util.validateMandatoryAndGetAllHeaderAttributes(httpServletRequest, listHeaders);
		if (requestHeaderDto.getIsValid()) {
			signingString = "";
			for (String header : listHeaders) {
				signingString = signingString + StringUtils.lowerCase(header) + ": " + StringUtils.strip(requestHeaderDto.getHeaderAttributesMap().get(header.toLowerCase())) + System.lineSeparator();
			}
			if (StringUtils.endsWith(signingString, System.lineSeparator())) {
				signingString = StringUtils.substring(signingString, 0, StringUtils.lastIndexOf(signingString, System.lineSeparator()));
			}
			requestHeaderDto.setSigningString(signingString);
		}
		return requestHeaderDto;
	}

	private String getSignitureFromHeader(HttpServerRequest httpServletRequest) {
		String signature = null;
		String signatureHeaders = httpServletRequest.getHeader(SIGNATURE.getValue());
		signature = StringUtils.substringAfter(signatureHeaders, "signature=");
		signature = StringUtils.replace(signature, "\"", "");
		return signature;
	}

	private String getAlgorithmFromHeader(HttpServerRequest httpServletRequest) {
		String algorithm = null;
		String signatureHeaders = httpServletRequest.getHeader(SIGNATURE.getValue());
		algorithm = StringUtils.substringAfter(signatureHeaders, "algorithm=");
		algorithm = StringUtils.substringBefore(algorithm, ",");
		algorithm = StringUtils.replace(algorithm, "\"", "");
		return algorithm;
	}

	public ProcessRequestDto validateRequestSignature(X509Certificate qSealCertificate, HttpServerRequest httpServletRequest, ContainerRequestContext ctx) {
		ProcessRequestDto processRequestDto = null;
		processRequestDto = new ProcessRequestDto();
		//verify digest
		String signatureAlgorithm = getAlgorithmFromHeader(httpServletRequest);
		processRequestDto = validateDigest(httpServletRequest, ctx, signatureAlgorithm);
		if (!processRequestDto.getIsValid()) {
			return processRequestDto;
		}
		//verify signature
		String signatureS = null;
		RequestHeaderDto requestHeaderDto = formSigningString(httpServletRequest);
		if (!requestHeaderDto.getIsValid()) {
			processRequestDto.setIsValid(false);
			processRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), requestHeaderDto.getErrorList()));
			return processRequestDto;
		}
		PublicKey publicKey = qSealCertificate.getPublicKey();
		signatureS = requestHeaderDto.getSigningString();
		signatureAlgorithm = qSealCertificate.getSigAlgName();
		byte[] signingStringByteArray = signatureS.getBytes(StandardCharsets.UTF_8);
		String signatureFromHeader = getSignitureFromHeader(httpServletRequest);
		byte[] signatureFromHeaderByte = signatureFromHeader.getBytes(StandardCharsets.UTF_8);
		byte[] signatureDecodec = null;
		try {
			signatureDecodec = Base64.getDecoder().decode(signatureFromHeaderByte);
		} catch (IllegalArgumentException iae) {
			processRequestDto.setIsValid(false);
			processRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.SIGNATURE_INVALID.getValue(), new ArrayList<>(Arrays.asList("Neispravan potpis"))));
			return processRequestDto;
		}
		//byte[] signatureByteArray = getSignitureFromHeader(httpServletRequest).getBytes(StandardCharsets.UTF_8);
		processRequestDto = SignatureUtil.verifySignature(signatureAlgorithm, publicKey, signingStringByteArray, signatureDecodec);
		return processRequestDto;
	}

	public ProcessRequestDto validateDigest(HttpServerRequest httpServletRequest, ContainerRequestContext ctx, String digestAlgorithm) {
		ProcessRequestDto processRequestDto = null;
		processRequestDto = new ProcessRequestDto();
		processRequestDto.setIsValid(true);
		MessageDigest md = null;
		byte[] messageDigest = null;

		try {
			if (!StringUtils.equalsIgnoreCase(digestAlgorithm, SignatureContants.DIGEST_ALGORITHM_SHA_256) && !StringUtils.equalsIgnoreCase(digestAlgorithm, SignatureContants.DIGEST_ALGORITHM_SHA_512)) {
				processRequestDto.setIsValid(false);
				processRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.SIGNATURE_INVALID.getValue(), new ArrayList<>(Arrays.asList("Neispravan hash algoritam"))));
				return processRequestDto;
			}
			md = MessageDigest.getInstance(digestAlgorithm);
			if (ctx.hasEntity()) {
				byte[] entityBytes = IOUtils.toByteArray(ctx.getEntityStream());
				byte[] entityBytes1 = entityBytes;
				InputStream in = new ByteArrayInputStream(entityBytes1);
				ctx.setEntityStream(in);
				messageDigest = md.digest(entityBytes);
			} else {
				messageDigest = md.digest("".getBytes());
			}
			String shaStringHex = DatatypeConverter.printHexBinary(messageDigest);
			shaStringHex = shaStringHex.toLowerCase();
			String base64ShaString = new String(Base64.getEncoder().encode(shaStringHex.getBytes()));
			if (StringUtils.equalsIgnoreCase(base64ShaString, httpServletRequest.getHeader(DIGEST.getValue()))) {
				processRequestDto.setIsValid(true);
			} else {
				processRequestDto.setIsValid(false);
				processRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.SIGNATURE_INVALID.getValue(), new ArrayList<>(Arrays.asList("Netočan hash body-a request-a"))));
				return processRequestDto;
			}
		} catch (NoSuchAlgorithmException nsae) {
			processRequestDto.setIsValid(false);
			processRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.SIGNATURE_INVALID.getValue(), new ArrayList<>(Arrays.asList("Neispravan hash algoritam"))));
			return processRequestDto;
		} catch (Exception e) {
			processRequestDto.setIsValid(false);
			processRequestDto.setErrorInformationDto(Psd2Util.setErrorInformationDto(ErrorCode.SIGNATURE_INVALID.getValue(), new ArrayList<>(Arrays.asList("Greška kod provjere hash-a body-a request-a"))));
			return processRequestDto;
		}
		return processRequestDto;
	}

	/**
	 * Sign http response
	 *
	 * @param responseContext - https://www.ietf.org/archive/id/draft-cavage-http-signatures-10.txt
	 * @return
	 */

	public void signResponse(ContainerResponseContext responseContext, CertificateSignInfoDto certificateSignInfoDto) throws Exception {

		//get certificate
		InputStream keyStore = new FileInputStream(SignatureContants.PSD2_QSEAL_KEYSTORE_FILE);
		//digest header - hash of message body
		String digestAlgorithm = null;
		if (StringUtils.containsIgnoreCase(certificateSignInfoDto.getCertificate().getSigAlgName(), "256")) {
			digestAlgorithm = SignatureContants.DIGEST_ALGORITHM_SHA_256;
		} else if (StringUtils.containsIgnoreCase(certificateSignInfoDto.getCertificate().getSigAlgName(), "512")) {
			digestAlgorithm = SignatureContants.DIGEST_ALGORITHM_SHA_512;
		}
		String bodyDigestBase64String = getResponseBodyDigest(responseContext, digestAlgorithm);
		//set digest header
		responseContext.getHeaders().add(ResponsetHeaderAttributes.DIGEST.getValue(), bodyDigestBase64String);
		//signature header
		String signatureHeader = formSignatureHeader(responseContext, certificateSignInfoDto.getCertificate(), certificateSignInfoDto.getPrivateKey());
		//set signature header
		responseContext.getHeaders().add(ResponsetHeaderAttributes.SIGNATURE.getValue(), signatureHeader);
		//set TPP-Signature-Certificate
		responseContext.getHeaders().add(ResponsetHeaderAttributes.TPP_SIGNATURE_CERTIFICATE.getValue(), certificateSignInfoDto.getCertificatePem());
	}

	private String getResponseBodyDigest(ContainerResponseContext responseContext, String digestAlgorithm) {
		String bodyDigestBase64String = null;
		MessageDigest md = null;
		byte[] messageDigest = null;
		try {
			md = MessageDigest.getInstance(digestAlgorithm);
			if (responseContext.getEntity() != null) {
				String responseBody = payloadMessage(responseContext);
				messageDigest = md.digest(responseBody.getBytes());
			} else {
				messageDigest = md.digest("".getBytes());
			}
			String shaStringHex = DatatypeConverter.printHexBinary(messageDigest);
			shaStringHex = shaStringHex.toLowerCase();
			bodyDigestBase64String = new String(Base64.getEncoder().encode(shaStringHex.getBytes()));
		} catch (NoSuchAlgorithmException nsae) {
			log.error("Greška kod potpisa poruke (response-a):", nsae);
		} catch (Exception e) {
			log.error("Greška kod potpisa poruke (response-a):", e);
		}
		return bodyDigestBase64String;
	}

	private String formSignatureHeader(ContainerResponseContext responseContext, X509Certificate pabaCert, PrivateKey privateKey) {
		String signatureHeader = null;
		//keyId, algorithm
		signatureHeader = "keyId=\"" + pabaCert.getSerialNumber().toString(16) + ",CA=" + pabaCert.getIssuerDN().getName() + "\",algorithm=\"" + pabaCert.getSigAlgName() + "\",headers=" + SignatureContants.SIGNATURE_HEADERS;
		String signingString = formSigningString(responseContext);
		//sign
		byte[] encodedSignature = SignatureUtil.signMessage(pabaCert, privateKey, signingString.getBytes());
		String digitalSignatureEncodedString = new String(encodedSignature);
		//add signature string
		signatureHeader = signatureHeader + ",signature=\"" + digitalSignatureEncodedString + "\"";
		return signatureHeader;
	}

	/**
	 * Form sigining string for digital signature
	 *
	 * @param responseContext - https://www.ietf.org/archive/id/draft-cavage-http-signatures-10.txt
	 * @return
	 */
	private String formSigningString(ContainerResponseContext responseContext) {
		String signingString = "";
		MultivaluedMap<String, Object> requestHeaders = responseContext.getHeaders();
		for (String header : SignatureContants.SIGNATURE_HEADER_LIST) {
			signingString = signingString + StringUtils.lowerCase(header) + ": " + StringUtils.strip((String) requestHeaders.getFirst(header.toLowerCase())) + System.lineSeparator();
		}
		if (StringUtils.endsWith(signingString, System.lineSeparator())) {
			signingString = StringUtils.substring(signingString, 0, StringUtils.lastIndexOf(signingString, System.lineSeparator()));
		}
		return signingString;
	}

	public String payloadMessage(ContainerResponseContext responseContext) throws IOException {
		String message = new String();
		if (responseContext.hasEntity()) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Class<?> entityClass = responseContext.getEntityClass();
			Type entityType = responseContext.getEntityType();
			Annotation[] entityAnnotations = responseContext.getEntityAnnotations();
			MediaType mediaType = responseContext.getMediaType();
			@SuppressWarnings("unchecked")
			MessageBodyWriter<Object> bodyWriter = (MessageBodyWriter<Object>) providers.getMessageBodyWriter(entityClass,
					entityType,
					entityAnnotations,
					mediaType); // I retrieve the bodywriter
			bodyWriter.writeTo(responseContext.getEntity(),
					entityClass,
					entityType,
					entityAnnotations,
					mediaType,
					responseContext.getHeaders(),
					baos); // I use the bodywriter to write to an accessible outputStream
			message = message.concat(new String(baos.toByteArray())); // I convert the stream to a String
		}
		return message;
	}
	//    public CertificateInfoDto checkQSealCertificate(HttpServletRequest httpServletRequest) {
	//        CertificateInfoDto certificateInfoDto = new CertificateInfoDto();
	//        certificateInfoDto.setIsValid(true);
	//        X509Certificate certificate = null;
	//        try {getSignitureFromHeader(httpServletRequest)
	//            String certificateHeader = httpServletRequest.getHeader(TPP_SIGNATURE_CERTIFICATE.getValue());
	//            if (certificateHeader == null) {
	//                certificateInfoDto.setIsValid(false);
	//                certificateInfoDto.setErrorList(new ArrayList<>(Arrays.asList("Nedostaje QSeal certifikat!")));
	//                return certificateInfoDto;
	//            }
	//            certificateInfoDto = CertificateUtil.getQSealCertificate(httpServletRequest);
	//            //InputStream inputStream = IOUtils.toInputStream(certificateHeader, "UTF-8");
	////            InputStream inputStream = IOUtils.toInputStream("certString", "UTF-8");
	////            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
	////            certificate = (X509Certificate) certFactory.generateCertificate(inputStream);
	//            //get certificate details
	//            certificateInfoDto = CertificateUtil.getCertificateDetails(certificateInfoDto.getQSealCertificate());
	//            //validate certificate
	//            certificateInfoDto = CertificateUtil.validateCertificateDetails(certificateInfoDto, "QSeal");
	////        } catch (IOException io) {
	////            certificateInfoDto.setIsValid(false);
	////            certificateInfoDto.setErrorList(new ArrayList<>(Arrays.asList("Pogre�an certificate header parametar!")));
	////            return certificateInfoDto;
	//
	//        } catch (Exception e) {
	//            certificateInfoDto.setIsValid(false);
	//            certificateInfoDto.setErrorList(new ArrayList<>(Arrays.asList("Greška kod obrade QSeal certifikata!")));
	//            return certificateInfoDto;
	//        }
	//        return certificateInfoDto;
	//    }

}

//"Digest X-Request-ID PSU-IDTPP-Redirect-URI Date";