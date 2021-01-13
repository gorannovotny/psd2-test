package hr.abc.psd2.resources.ws.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import hr.abc.psd2.bean.IAuthorizationBean;
import hr.abc.psd2.bean.INalogTppBean;
import hr.abc.psd2.model.dto.authorization.AuthorizationListResponseDto;
import hr.abc.psd2.model.dto.authorization.CreateAuthorizationRequestDto;
import hr.abc.psd2.model.dto.authorization.ScaStatusResponseDto;
import hr.abc.psd2.model.dto.authorization.StartAuthorizationResponseDto;
import hr.abc.psd2.model.dto.error.ErrorCode;
import hr.abc.psd2.model.dto.pis.*;
import hr.abc.psd2.resources.ws.IPaymentsServiceResource;
import hr.abc.psd2.util.GenericBassxConstants;
import hr.abc.psd2.util.InternationalizationUtil;
import hr.abc.psd2.util.Psd2Constants;
import hr.abc.psd2.util.Psd2Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.*;

/**
 * Web service resource for Payment Information Service - Single Payment.
 * <p>
 * Created by tkorpar on 19.09.2018..
 */

@Slf4j
public class PaymentsServiceResource implements IPaymentsServiceResource {
  
    @Inject
    private INalogTppBean nalogTppBean;
    
    @Inject
    private IAuthorizationBean authorizationBean;

    /**
     * summary: Payment initiation request
     * description: This method is used to initiate a payment at the ASPSP.
     * This method to initiate a payment initiation at the ASPSP can be sent with
     * either a JSON body or an pain.001 body depending on the payment product in the path.
     **/
    public Response initiatePayment(String psd2Nalog, String paymentProduct, HttpHeaders headers) {
        Response response = null;
        try {
            MultivaluedMap<String, String> requestHeaders = headers.getRequestHeaders();
            String xRequestId = requestHeaders.getFirst(Psd2Constants.RequestHeaderAttributes.X_REQUEST_ID.getValue());
            String contentType = requestHeaders.getFirst(Psd2Constants.RequestHeaderAttributes.CONTENT_TYPE.getValue());
            //validate payment product
            if (StringUtils.isBlank(paymentProduct)) {
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            }
            if (!PaymentProduct.getListPaymentProduct().contains(paymentProduct)) {
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.PRODUCT_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_wrong_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            }
            if (StringUtils.equals(paymentProduct, PaymentProduct.INSTANT_SEPA_CREDIT_TANSFER.getCode()) || StringUtils.equals(paymentProduct, PaymentProduct.PAIN_INSTANT_SEPA_CREDIT_TANSFER.getCode())
//                    || StringUtils.equals(paymentProduct, PaymentProduct.HR_INSTANT_DOMESTIC_PAYMENTS.getCode()) || StringUtils.equals(paymentProduct, PaymentProduct.PAIN_HR_INSTANT_DOMESTIC_PAYMENTS.getCode())
            ) {
                response = Response.status(Response.Status.NOT_FOUND).entity(Psd2Util.setErrorInformationDto(ErrorCode.PRODUCT_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_not_supp_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            }
            if (StringUtils.isBlank(psd2Nalog)) {
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_request_body", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            }
            SinglePaymentDto psd2RequestNalog = null;
            if (StringUtils.startsWith(contentType, MediaType.APPLICATION_XML) || paymentProduct.startsWith(PaymentProduct.PAIN_SEPA_CREDIT_TANSFER.getCode().substring(0, 3))) {
                response = Response.status(Response.Status.NOT_FOUND).entity(Psd2Util.setErrorInformationDto(ErrorCode.PRODUCT_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_not_supp_xml", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;

                //TODO ako bude potrebno zasad ne treba Lana
            } else if (StringUtils.contains(contentType, MediaType.APPLICATION_JSON)) {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    psd2RequestNalog = mapper.readValue(psd2Nalog, SinglePaymentDto.class);
                } catch (JsonParseException jpe) {
                    log.error("Exception is:", jpe);
                    response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList("Neispravan body requesta (podaci o nalogu u JSON formatu)")))).build();
                    return response;
                } catch (JsonMappingException jme) {
                    log.error("Exception is:", jme);
                    response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList("Neispravan body requesta (podaci o nalogu u JSON formatu)")))).build();
                    return response;
                }
            }
            psd2RequestNalog.setPaymentService(PaymentServiceType.SINGLE.getValue());
            psd2RequestNalog.setPaymentProduct(paymentProduct);
            psd2RequestNalog.setxRequestId(xRequestId);
            psd2RequestNalog.setTppId(requestHeaders.getFirst(TPP_ID.getValue()));
            psd2RequestNalog.setContentType(contentType);
            psd2RequestNalog.setPsuIpAddress(requestHeaders.getFirst(PSU_IP_ADDRESS.getValue()));
            psd2RequestNalog.setTppRedirectUri(requestHeaders.getFirst(TPP_REDIRECT_URI.getValue()));
            //poziv core WS-a
            // SinglePaymentResponseDto singlePaymentResponseDto = nalogTppBean.create(psd2RequestNalog); // stari
            SinglePaymentResponseDto singlePaymentResponseDto = nalogTppBean.create(psd2RequestNalog,psd2Nalog); // prazni
            if (singlePaymentResponseDto.getIsValid()) {
                singlePaymentResponseDto.setIsValid(null);
                response = Response.created(new URI(singlePaymentResponseDto.get_links().getSelf().getHref())).header("ASPSP-SCA-Approach", "REDIRECT").entity(singlePaymentResponseDto).build();
                return response;
            } else {
                response = Response.status(Response.Status.BAD_REQUEST).entity(singlePaymentResponseDto.getErrorInformationDto()).build();
                return response;
            }
        } catch (UnrecognizedPropertyException upe) {
            log.error("Exception is:", upe);
            response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.BAD_REQUEST.getValue(), new ArrayList<>(Arrays.asList(upe.getMessage())))).build();
            return response;
//        } catch (AbitRuleException ex) {
//            log.error("Exception is:", ex);
//            if (ex.getMessage().equals(InternationalizationUtil.getLocalMessage("psd2_requestedExecutionDateError", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))) {
//                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.EXECUTION_DATE_INVALID.getValue(), new ArrayList<>(Arrays.asList(ex.getMessage())))).build();
//            } else {
//                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(ex.getMessage())))).build();
//            }
//            return response;
        } catch (Exception e) {
            log.error("Exception is:", e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto(ErrorCode.INTERNAL_SERVER_ERROR.getValue(), new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();
            return response;
        }
    }


    /**
     * summary: Payment Cancellation Request
     * description: This method initiates the cancellation of a payment.
     * Depending on the payment-service, the payment-product and the ASPSP's implementation,
     * this TPP call might be sufficient to cancel a payment.
     */
    public Response cancelPayment(@PathParam("payment-product") String paymentProduct, @PathParam("paymentId") String paymentId, HttpHeaders headers) {
        Response response = null;
        try {
            MultivaluedMap<String, String> requestHeaders = headers.getRequestHeaders();
            //validate payment product
            if (StringUtils.isBlank(paymentProduct)) {
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            }
            if (!PaymentProduct.getListPaymentProduct().contains(paymentProduct)) {
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.PRODUCT_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_wrong_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            }
            if (StringUtils.equals(paymentProduct, PaymentProduct.INSTANT_SEPA_CREDIT_TANSFER.getCode()) || StringUtils.equals(paymentProduct, PaymentProduct.PAIN_INSTANT_SEPA_CREDIT_TANSFER.getCode())
//                    || StringUtils.equals(paymentProduct, PaymentProduct.HR_INSTANT_DOMESTIC_PAYMENTS.getCode()) || StringUtils.equals(paymentProduct, PaymentProduct.PAIN_HR_INSTANT_DOMESTIC_PAYMENTS.getCode())
            ) {
                response = Response.status(Response.Status.NOT_FOUND).entity(Psd2Util.setErrorInformationDto(ErrorCode.PRODUCT_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_not_supp_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            }
            //validate payment id
            if (StringUtils.isBlank(paymentId)) {
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_payment_id", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            } else {
                Integer paymentIdInt = null;
                String tppId = requestHeaders.getFirst(TPP_ID.getValue());
                String tppRedirectUriCancelation=requestHeaders.getFirst(TPP_REDIRECT_URI.getValue());
                /*try {
                    paymentIdInt = Integer.valueOf(paymentId);
                } catch (NumberFormatException nfe) {
                    response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_payment", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                    return response;
                }*/
                // PaymentCancellationResponseDto responseDto = nalogTppBean.cancelPayment(PaymentServiceType.SINGLE.getValue(), paymentProduct, tppId, paymentIdInt,tppRedirectUriCancelation); // stari
                PaymentCancellationResponseDto responseDto = nalogTppBean.cancelPayment(PaymentServiceType.SINGLE.getValue(), paymentProduct, tppId, paymentId,tppRedirectUriCancelation); // prazni
                if (responseDto.getIsValid()) {
                    responseDto.setIsValid(null);
                    response = Response.status(Response.Status.NO_CONTENT).entity(responseDto).build();
                    return response;
                } else {
                    if (responseDto.getErrorInformationDto().getTppMessages() != null && !responseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            responseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.CANCELLATION_INVALID.getValue())) {
                        response = Response.status(Response.Status.METHOD_NOT_ALLOWED).entity(responseDto.getErrorInformationDto()).build();
                    } else if (responseDto.getErrorInformationDto().getTppMessages() != null && !responseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            responseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.RESOURCE_UNKNOWN.getValue())) {
                        response = Response.status(Response.Status.NOT_ACCEPTABLE).entity(responseDto.getErrorInformationDto()).build();
                    } else {
                        response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseDto.getErrorInformationDto()).build();
                    }
                }
            }
        } catch (Exception e) {
            log.error("Exception is:", e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto(ErrorCode.INTERNAL_SERVER_ERROR.getValue(), new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();
            return response;
        }
        return response;
    }

    /**
     * summary: Get Payment Information
     * description: Returns the content of a payment object
     */
    public Response getPaymentInformation(@PathParam("payment-product") String paymentProduct, @PathParam("paymentId") String paymentId, HttpHeaders headers) {
        Response response = null;
        try {
            MultivaluedMap<String, String> requestHeaders = headers.getRequestHeaders();
            //validate payment product
            if (StringUtils.isBlank(paymentProduct)) {
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            }
            if (!PaymentProduct.getListPaymentProduct().contains(paymentProduct)) {
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.PRODUCT_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_wrong_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            }
            if (StringUtils.equals(paymentProduct, PaymentProduct.INSTANT_SEPA_CREDIT_TANSFER.getCode()) || StringUtils.equals(paymentProduct, PaymentProduct.PAIN_INSTANT_SEPA_CREDIT_TANSFER.getCode())
//                    || StringUtils.equals(paymentProduct, PaymentProduct.HR_INSTANT_DOMESTIC_PAYMENTS.getCode()) || StringUtils.equals(paymentProduct, PaymentProduct.PAIN_HR_INSTANT_DOMESTIC_PAYMENTS.getCode())
            ) {
                response = Response.status(Response.Status.NOT_FOUND).entity(Psd2Util.setErrorInformationDto(ErrorCode.PRODUCT_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_not_supp_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            }
            //validate payment id
            if (StringUtils.isBlank(paymentId)) {
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_payment_id", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            } else {
                String tppId = requestHeaders.getFirst(TPP_ID.getValue());
                /*try {
                    paymentIdInt = Integer.valueOf(paymentId);
                } catch (NumberFormatException nfe) {
                    response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_payment", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                    return response;
                }*/
                // SinglePaymentDto singlePaymentDto = nalogTppBean.getPaymentInformation(PaymentServiceType.SINGLE.getValue(), paymentProduct, tppId, paymentIdInt); // stari
                SinglePaymentDto singlePaymentDto = nalogTppBean.getPaymentInformation(PaymentServiceType.SINGLE.getValue(), paymentProduct, tppId, paymentId); // prazni
                if (singlePaymentDto.getIsValid()) {
                    //form psd2 nalog
                    singlePaymentDto.setIsValid(null);
                    response = Response.status(Response.Status.OK).entity(singlePaymentDto).build();
                    return response;
                    //if (singlePaymentDto.getContentType().equals(MediaType.APPLICATION_XML)) {
                    //FOR XML TEST
//            Document document = new Document();
//            document.setCstmrCdtTrfInitn(new CustomerCreditTransferInitiationV03());
//            document.getCstmrCdtTrfInitn().setGrpHdr(new GroupHeader32());
//            document.getCstmrCdtTrfInitn().getGrpHdr().setMsgId("test");
//            document.getCstmrCdtTrfInitn().getPmtInf().add(new PaymentInstructionInformation3());
//            document.getCstmrCdtTrfInitn().getPmtInf().set(0, new PaymentInstructionInformation3());
//            document.getCstmrCdtTrfInitn().getPmtInf().get(0).setNbOfTxs("45");
//
//            response = Response.status(Response.Status.OK).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML).entity(document).build();
                } else {
                    if (singlePaymentDto.getErrorInformationDto().getTppMessages() != null && !singlePaymentDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            singlePaymentDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.FORMAT_ERROR.getValue())) {
                        response = Response.status(Response.Status.BAD_REQUEST).entity(singlePaymentDto.getErrorInformationDto()).build();

                    } else if (singlePaymentDto.getErrorInformationDto().getTppMessages() != null && !singlePaymentDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            singlePaymentDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.RESOURCE_UNKNOWN.getValue())) {
                        response = Response.status(Response.Status.NOT_FOUND).entity(singlePaymentDto.getErrorInformationDto()).build();
                    } else {
                        response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(singlePaymentDto.getErrorInformationDto()).build();
                    }
                }
            }
        } catch (Exception e) {
            log.error("Exception is:", e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto(ErrorCode.INTERNAL_SERVER_ERROR.getValue(), new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();
            return response;
        }
        return response;
    }

    /**
     * summary: Payment initiation status request
     * description: Check the transaction status of a payment initiation.
     * operationId: getPaymentInitiationStatus
     */
    public Response getPaymentInitiationStatus(@PathParam("payment-product") String paymentProduct, @PathParam("paymentId") String paymentId, @Context HttpHeaders headers) {
        Response response = null;
        try {
            MultivaluedMap<String, String> requestHeaders = headers.getRequestHeaders();
            String xRequestId = requestHeaders.getFirst(X_REQUEST_ID.getValue());
            //validate payment product
            if (StringUtils.isBlank(paymentProduct)) {
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            }
            if (!PaymentProduct.getListPaymentProduct().contains(paymentProduct)) {
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.PRODUCT_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_wrong_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            }
            if (StringUtils.equals(paymentProduct, PaymentProduct.INSTANT_SEPA_CREDIT_TANSFER.getCode()) || StringUtils.equals(paymentProduct, PaymentProduct.PAIN_INSTANT_SEPA_CREDIT_TANSFER.getCode())
//                    || StringUtils.equals(paymentProduct, PaymentProduct.HR_INSTANT_DOMESTIC_PAYMENTS.getCode()) || StringUtils.equals(paymentProduct, PaymentProduct.PAIN_HR_INSTANT_DOMESTIC_PAYMENTS.getCode())
            ) {
                response = Response.status(Response.Status.NOT_FOUND).entity(Psd2Util.setErrorInformationDto(ErrorCode.PRODUCT_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_not_supp_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            }
            //validate payment id
            if (StringUtils.isBlank(paymentId)) {
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_payment_id", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            } else {
                Integer paymentIdInt = null;
                String tppId = requestHeaders.getFirst(TPP_ID.getValue());
                /*try {
                    paymentIdInt = Integer.valueOf(paymentId);
                } catch (NumberFormatException nfe) {
                    response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_payment", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                    return response;
                }*/
                // TransactionStatusResponseDto transactionStatusResponseDto = nalogTppBean.getPaymentInitiationStatus(PaymentServiceType.SINGLE.getValue(), paymentProduct, tppId, paymentIdInt); // stari
                TransactionStatusResponseDto transactionStatusResponseDto = nalogTppBean.getPaymentInitiationStatus(PaymentServiceType.SINGLE.getValue(), paymentProduct, tppId, paymentId); // prazni
                if (transactionStatusResponseDto.getIsValid()) {
                    transactionStatusResponseDto.setIsValid(null);
                    response = Response.status(Response.Status.OK).entity(transactionStatusResponseDto).build();
                    return response;
                } else {
                    if (transactionStatusResponseDto.getErrorInformationDto().getTppMessages() != null && !transactionStatusResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            transactionStatusResponseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.FORMAT_ERROR.getValue())) {
                        response = Response.status(Response.Status.BAD_REQUEST).entity(transactionStatusResponseDto.getErrorInformationDto()).build();
                    } else if (transactionStatusResponseDto.getErrorInformationDto().getTppMessages() != null && !transactionStatusResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            transactionStatusResponseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.RESOURCE_UNKNOWN.getValue())) {
                        response = Response.status(Response.Status.NOT_FOUND).entity(transactionStatusResponseDto.getErrorInformationDto()).build();
                    } else {
                        response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(transactionStatusResponseDto.getErrorInformationDto()).build();
                    }
                }
            }
        } catch (Exception e) {
            log.error("Exception is:", e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto(ErrorCode.INTERNAL_SERVER_ERROR.getValue(), new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();
            return response;
        }
        return response;
    }


    /**
     * summary: Start the authorisation process for a payment initiation
     */
    public Response startPaymentAuthorisation(@PathParam("payment-product") String paymentProduct, @PathParam("paymentId") String paymentId, HttpHeaders httpHeaders) {
        Response response = null;
        try {
            MultivaluedMap<String, String> requestHeaders = httpHeaders.getRequestHeaders();

            String xRequestId = requestHeaders.getFirst(X_REQUEST_ID.getValue());
            //validate payment product
            if (StringUtils.isBlank(paymentProduct)) {
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            }
            if (!PaymentProduct.getListPaymentProduct().contains(paymentProduct)) {

                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.PRODUCT_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_wrong_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            }
            if (StringUtils.equals(paymentProduct, PaymentProduct.INSTANT_SEPA_CREDIT_TANSFER.getCode()) || StringUtils.equals(paymentProduct, PaymentProduct.PAIN_INSTANT_SEPA_CREDIT_TANSFER.getCode())) {

                response = Response.status(Response.Status.NOT_FOUND).entity(Psd2Util.setErrorInformationDto(ErrorCode.PRODUCT_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_not_supp_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            }
            //validate payment id
            if (StringUtils.isBlank(paymentId)) {

                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_payment_id", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            } else {
                String tppId = requestHeaders.getFirst(TPP_ID.getValue());
                /*Integer paymentIdInt = null;
                try {
                    paymentIdInt = Integer.valueOf(paymentId);
                } catch (NumberFormatException nfe) {
                    response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_payment", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                    return response;
                }*/
                // StartAuthorizationResponseDto startAuthorizationResponseDto = authorizationBean.startAuthorisation(new CreateAuthorizationRequestDto(PaymentServiceType.SINGLE.getValue(), paymentProduct, Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), paymentIdInt, Psd2Constants.AuthorizationType.CREATED.getValue(), xRequestId, tppId, null)); // stari
                StartAuthorizationResponseDto startAuthorizationResponseDto = authorizationBean.startAuthorisation(new CreateAuthorizationRequestDto(PaymentServiceType.SINGLE.getValue(), paymentProduct, Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), paymentId, Psd2Constants.AuthorizationType.CREATED.getValue(), xRequestId, tppId, null));
                if (startAuthorizationResponseDto.getValid()) {
                    startAuthorizationResponseDto.setValid(null);
                    String location = startAuthorizationResponseDto.get_links().getSelf().getHref();
                    startAuthorizationResponseDto.get_links().setSelf(null);
                    response = Response.created(new URI(location)).entity(startAuthorizationResponseDto).build();
                    return response;
                } else {
                    if (startAuthorizationResponseDto.getErrorInformationDto().getTppMessages() != null && !startAuthorizationResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            startAuthorizationResponseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.STATUS_INVALID.getValue())) {
                        response = Response.status(Response.Status.CONFLICT).entity(startAuthorizationResponseDto.getErrorInformationDto()).build();
                        return response;
                    } else if (startAuthorizationResponseDto.getErrorInformationDto().getTppMessages() != null && !startAuthorizationResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            startAuthorizationResponseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.RESOURCE_UNKNOWN.getValue())) {
                        response = Response.status(Response.Status.FORBIDDEN).entity(startAuthorizationResponseDto.getErrorInformationDto()).build();
                        return response;
                    }else if (startAuthorizationResponseDto.getErrorInformationDto().getTppMessages() != null && !startAuthorizationResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            startAuthorizationResponseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.STATUS_INVALID.getValue())) {
                        response = Response.status(Response.Status.CONFLICT).entity(startAuthorizationResponseDto.getErrorInformationDto()).build();
                        return response;
                    }
                    else {
                        response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(startAuthorizationResponseDto.getErrorInformationDto()).build();
                        return response;
                    }
                }
            }
        } catch (Exception e) {
            log.error("Exception is:", e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto(ErrorCode.INTERNAL_SERVER_ERROR.getValue(), new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();
            return response;
        }
    }

    /**
     * summary: Get Payment Initiation Authorisation Sub-Resources Request
     * description: |
     * Read a list of all authorisation subresources IDs which have been created.
     * This function returns an array of hyperlinks to all generated authorisation sub-resources.
     **/
    public Response getPaymentInitiationAuthorisation(@PathParam("payment-product") String paymentProduct,
                                                      @PathParam("paymentId") String paymentId, HttpHeaders httpHeaders) {
        Response response = null;
        AuthorizationListResponseDto authorizationListResponseResultDto = new AuthorizationListResponseDto();
        authorizationListResponseResultDto.setValid(true);
        try {
            MultivaluedMap<String, String> requestHeaders = httpHeaders.getRequestHeaders();
            String tppId = requestHeaders.getFirst(TPP_ID.getValue());
            //validate payment product
            if (StringUtils.isNotBlank(paymentProduct)) {
                if (PaymentProduct.getListPaymentProduct().contains(paymentProduct)) {
                    if (!StringUtils.equals(paymentProduct, PaymentProduct.INSTANT_SEPA_CREDIT_TANSFER.getCode()) && !StringUtils.equals(paymentProduct, PaymentProduct.PAIN_INSTANT_SEPA_CREDIT_TANSFER.getCode())) {
                        //validate path parameter
                        if (StringUtils.isNotBlank(paymentId)) {
                           /* Integer paymentIdInt = null;
                            try {
                                paymentIdInt = Integer.valueOf(paymentId);
                            } catch (NumberFormatException nfe) {
                                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_payment", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                                return response;
                            }*/
                            // AuthorizationListResponseDto authorizationListResponseBackWsDto = authorizationBean.getAuthorizationSubResources(paymentIdInt, Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), PaymentServiceType.SINGLE.getValue(), paymentProduct, tppId, Psd2Constants.AuthorizationType.CREATED.getValue()); // stari
                            AuthorizationListResponseDto authorizationListResponseBackWsDto = authorizationBean.getAuthorizationSubResources(paymentId, Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), PaymentServiceType.SINGLE.getValue(), paymentProduct, tppId, Psd2Constants.AuthorizationType.CREATED.getValue()); // prazni
                            //validate response from back end ws
                            if (authorizationListResponseBackWsDto.getValid()) {
                                if (authorizationListResponseBackWsDto.getAuthorisationIds() != null && !authorizationListResponseBackWsDto.getAuthorisationIds().isEmpty()) {
                                    authorizationListResponseResultDto.setValid(null);
                                    authorizationListResponseResultDto.setAuthorisationIds(authorizationListResponseBackWsDto.getAuthorisationIds());
                                    response = Response.status(Response.Status.OK).entity(authorizationListResponseResultDto).build();
                                } else {
                                    response = Response.status(Response.Status.NOT_FOUND).entity(authorizationListResponseResultDto.getErrorInformationDto()).build();
                                }
                            } else {
                                if (authorizationListResponseBackWsDto.getErrorInformationDto().getTppMessages() != null && !authorizationListResponseBackWsDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                                        authorizationListResponseBackWsDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.RESOURCE_UNKNOWN.getValue())) {
                                    response = Response.status(Response.Status.NOT_FOUND).entity(authorizationListResponseBackWsDto.getErrorInformationDto()).build();
                                } else {
                                    response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(authorizationListResponseBackWsDto.getErrorInformationDto()).build();
                                }
                            }
                        } else {
                            response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_payment_id", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                        }
                    } else {
                        response = Response.status(Response.Status.NOT_FOUND).entity(Psd2Util.setErrorInformationDto(ErrorCode.PRODUCT_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_not_supp_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                    }
                } else {
                    response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.PRODUCT_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_wrong_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                }
            } else {
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR .getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
            }
        } catch (Exception e) {
            log.error("Exception is :", e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto("INTERNAL_SERVER_ERROR", new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();
        }

        return response;
    }

    /**
     * summary: Read the SCA Status of the payment authorisation
     * description: |
     * This method returns the SCA status of a payment initiation's authorisation sub-resource.
     */
    public Response getPaymentInitiationScaStatus(@PathParam("payment-product") String paymentProduct, @PathParam("paymentId") String paymentId, @PathParam("authorisationId") String authorisationId,
                                                  HttpHeaders httpHeaders) {
        Response response = null;
        //response object for body
        ScaStatusResponseDto scaStatusResponseDtoResult = new ScaStatusResponseDto();
        scaStatusResponseDtoResult.setValid(true);
        try {
            MultivaluedMap<String, String> requestHeaders = httpHeaders.getRequestHeaders();
            String tppId = requestHeaders.getFirst(TPP_ID.getValue());
            //validate path parameter
            if (StringUtils.isNotBlank((paymentId)) && StringUtils.isNotBlank(authorisationId) && StringUtils.isNotBlank(paymentProduct)) {
                if (PaymentProduct.getListPaymentProduct().contains(paymentProduct)) {
                    if (!StringUtils.equals(paymentProduct, PaymentProduct.INSTANT_SEPA_CREDIT_TANSFER.getCode()) && !StringUtils.equals(paymentProduct, PaymentProduct.PAIN_INSTANT_SEPA_CREDIT_TANSFER.getCode())) {
                        /*String jsonResponse = null;
                        Integer paymentIdInt = null;
                        Integer authIdInt = null;
                        try {
                            paymentIdInt = Integer.valueOf(paymentId);
                            authIdInt = Integer.parseInt(authorisationId);
                        } catch (NumberFormatException nfe) {
                            response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_payment", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                            return response;
                        }*/
                        // ScaStatusResponseDto scaStatusResponseBackWsDto = authorizationBean.getScaStatus(paymentIdInt, Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), authIdInt, PaymentServiceType.SINGLE.getValue(), paymentProduct, tppId, Psd2Constants.AuthorizationType.CREATED.getValue()); // stari
                        ScaStatusResponseDto scaStatusResponseBackWsDto = authorizationBean.getScaStatus(paymentId, Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), authorisationId, PaymentServiceType.SINGLE.getValue(), paymentProduct, tppId, Psd2Constants.AuthorizationType.CREATED.getValue()); // prazni
                        //validate response from backend ws
                        if (scaStatusResponseBackWsDto.getValid()) {
                            if (scaStatusResponseBackWsDto.getScaStatus() != null) {
                                scaStatusResponseDtoResult.setValid(null);
                                scaStatusResponseDtoResult.setScaStatus(scaStatusResponseBackWsDto.getScaStatus());
                                response = Response.status(Response.Status.OK).entity(scaStatusResponseDtoResult).build();
                            } else {
                                response = Response.status(Response.Status.NOT_FOUND).entity(scaStatusResponseBackWsDto.getErrorInformationDto()).build();
                            }
                        } else {
                            if (scaStatusResponseBackWsDto.getErrorInformationDto().getTppMessages() != null && !scaStatusResponseBackWsDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                                    scaStatusResponseBackWsDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.BAD_REQUEST.getValue())) {
                                response = Response.status(Response.Status.BAD_REQUEST).entity(scaStatusResponseBackWsDto).build();
                            } else if (scaStatusResponseBackWsDto.getErrorInformationDto().getTppMessages() != null && !scaStatusResponseBackWsDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                                    scaStatusResponseBackWsDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals("NOT_FOUND")) {
                                response = Response.status(Response.Status.NOT_FOUND).entity(scaStatusResponseBackWsDto.getErrorInformationDto()).build();

                            } else {
                                response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(scaStatusResponseBackWsDto.getErrorInformationDto()).build();
                            }
                        }
                    } else {
                        response = Response.status(Response.Status.NOT_FOUND).entity(Psd2Util.setErrorInformationDto(ErrorCode.PRODUCT_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_not_supp_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                    }
                } else {
                    response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.PRODUCT_UNKNOWN .getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_wrong_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                }
            } else {
                List<String> errorList = new ArrayList<>();
                if (!StringUtils.isNotBlank((paymentId))) {
                    errorList.add(InternationalizationUtil.getLocalMessage("psd2_no_payment_id", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
                }
                if (!StringUtils.isNotBlank(authorisationId)) {
                    errorList.add(InternationalizationUtil.getLocalMessage("psd2_no_auth_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
                }
                if (!StringUtils.isNotBlank(paymentProduct)) {
                    errorList.add(InternationalizationUtil.getLocalMessage("psd2_no_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
                }
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), errorList)).build();
            }
        } catch (Exception e) {
            log.error("Exceprion is:", e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto(ErrorCode.INTERNAL_SERVER_ERROR.getValue(), new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();
        }
        return response;
    }


    /**
     * summary: Start the authorisation process for the cancellation of the addressed payment
     * description: |
     * Creates an authorisation sub-resource and start the authorisation process of the cancellation of the addressed payment.
     * The message might in addition transmit authentication and authorisation related data.
     */
    public Response startPaymentInitiationCancellationAuthorisation(@PathParam("payment-product") String paymentProduct, @PathParam("paymentId") String paymentId, HttpHeaders httpHeaders) {
        Response response = null;
        String xRequestId = null;
        try {
            MultivaluedMap<String, String> requestHeaders = httpHeaders.getRequestHeaders();
            xRequestId = requestHeaders.getFirst(X_REQUEST_ID.getValue());
            //validate payment product
            if (StringUtils.isBlank(paymentProduct)) {
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR .getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            }
            if (!PaymentProduct.getListPaymentProduct().contains(paymentProduct)) {
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.PRODUCT_UNKNOWN .getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_wrong_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            }
            if (StringUtils.equals(paymentProduct, PaymentProduct.INSTANT_SEPA_CREDIT_TANSFER.getCode()) || StringUtils.equals(paymentProduct, PaymentProduct.PAIN_INSTANT_SEPA_CREDIT_TANSFER.getCode())) {
                response = Response.status(Response.Status.NOT_FOUND).entity(Psd2Util.setErrorInformationDto(ErrorCode.PRODUCT_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_not_supp_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            }
            //validate payment id
            if (StringUtils.isBlank(paymentId)) {
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR .getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_payment_id", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            } else {
                String tppId = requestHeaders.getFirst(Psd2Constants.RequestHeaderAttributes.TPP_ID.getValue());
                /*Integer paymentIdInt = null;
                try {
                    paymentIdInt = Integer.valueOf(paymentId);
                } catch (NumberFormatException nfe) {
                    response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_payment", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                    return response;
                }*/
                // StartAuthorizationResponseDto startAuthorizationResponseDto = authorizationBean.startAuthorisation(new CreateAuthorizationRequestDto(PaymentServiceType.SINGLE.getValue(), paymentProduct, Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), paymentIdInt, Psd2Constants.AuthorizationType.CANCELLED.getValue(), xRequestId, tppId, null)); // stari
                StartAuthorizationResponseDto startAuthorizationResponseDto = authorizationBean.startAuthorisation(new CreateAuthorizationRequestDto(PaymentServiceType.SINGLE.getValue(), paymentProduct, Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), paymentId, Psd2Constants.AuthorizationType.CANCELLED.getValue(), xRequestId, tppId, null)); // prazni
                if (startAuthorizationResponseDto.getValid()) {
                    startAuthorizationResponseDto.setValid(null);
                    String location = startAuthorizationResponseDto.get_links().getSelf().getHref();
                    startAuthorizationResponseDto.get_links().setSelf(null);
                    response = Response.created(new URI(location)).entity(startAuthorizationResponseDto).build();
                    return response;
                } else {
                    if (startAuthorizationResponseDto.getErrorInformationDto().getTppMessages() != null && !startAuthorizationResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            startAuthorizationResponseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.STATUS_INVALID.getValue())) {
                        response = Response.status(Response.Status.CONFLICT).entity(startAuthorizationResponseDto.getErrorInformationDto()).build();
                        return response;
                    } else if (startAuthorizationResponseDto.getErrorInformationDto().getTppMessages() != null && !startAuthorizationResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            startAuthorizationResponseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.CANCELLATION_INVALID.getValue())) {
                        response = Response.status(Response.Status.METHOD_NOT_ALLOWED).entity(startAuthorizationResponseDto.getErrorInformationDto()).build();
                        return response;
                    }else if (startAuthorizationResponseDto.getErrorInformationDto().getTppMessages() != null && !startAuthorizationResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            startAuthorizationResponseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.RESOURCE_UNKNOWN.getValue())) {
                        response = Response.status(Response.Status.FORBIDDEN).entity(startAuthorizationResponseDto.getErrorInformationDto()).build();
                        return response;
                    } else {
                        response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(startAuthorizationResponseDto.getErrorInformationDto()).build();
                        return response;
                    }
                }
            }
        } catch (Exception e) {
            log.error("Exception is:", e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto(ErrorCode.INTERNAL_SERVER_ERROR.getValue(), new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();
            return response;
        }
    }

    /**
     * summary: Will deliver an array of resource identifications to all generated cancellation authorisation sub-resources.
     * description: |
     * Retrieve a list of all created cancellation authorisation sub-resources.
     */
    public Response getPaymentInitiationCancellationAuthorisationInformation(@PathParam("payment-product") String paymentProduct,
                                                                             @PathParam("paymentId") String paymentId, HttpHeaders httpHeaders ) {
        Response response = null;
        AuthorizationListResponseDto authorizationListResponseResultDto = new AuthorizationListResponseDto();
        authorizationListResponseResultDto.setValid(true);
        try {
            MultivaluedMap<String, String> requestHeaders = httpHeaders.getRequestHeaders();
            String tppId = requestHeaders.getFirst(TPP_ID.getValue());
            //validate payment product
            if (StringUtils.isNotBlank(paymentProduct)) {
                if (PaymentProduct.getListPaymentProduct().contains(paymentProduct)) {
                    if (!StringUtils.equals(paymentProduct, PaymentProduct.INSTANT_SEPA_CREDIT_TANSFER.getCode()) && !StringUtils.equals(paymentProduct, PaymentProduct.PAIN_INSTANT_SEPA_CREDIT_TANSFER.getCode())) {
                        //validate path parameter
                        if (StringUtils.isNotBlank(paymentId)) {
                            Integer paymentIdInt = null;
                            /*try {
                                paymentIdInt = Integer.valueOf(paymentId);
                            } catch (NumberFormatException nfe) {
                                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_payment", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                            }*/
                            // AuthorizationListResponseDto authorizationListResponseBackWsDto = authorizationBean.getAuthorizationSubResources(paymentIdInt, Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), PaymentServiceType.SINGLE.getValue(), paymentProduct, tppId, Psd2Constants.AuthorizationType.CANCELLED.getValue()); // stari
                            AuthorizationListResponseDto authorizationListResponseBackWsDto = authorizationBean.getAuthorizationSubResources(paymentId, Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), PaymentServiceType.SINGLE.getValue(), paymentProduct, tppId, Psd2Constants.AuthorizationType.CANCELLED.getValue()); // prazni
                            //validate response from back end ws
                            if (authorizationListResponseBackWsDto.getValid()) {
                                if (authorizationListResponseBackWsDto.getAuthorisationIds() != null && !authorizationListResponseBackWsDto.getAuthorisationIds().isEmpty()) {
                                    authorizationListResponseResultDto.setValid(null);
                                    authorizationListResponseResultDto.setAuthorisationIds(authorizationListResponseBackWsDto.getAuthorisationIds());
                                    response = Response.status(Response.Status.OK).entity(authorizationListResponseResultDto).build();
                                } else {
                                    response = Response.status(Response.Status.NOT_FOUND).entity(authorizationListResponseResultDto.getErrorInformationDto()).build();
                                }
                            } else {
                                if (authorizationListResponseBackWsDto.getErrorInformationDto().getTppMessages() != null && !authorizationListResponseBackWsDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                                        authorizationListResponseBackWsDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.BAD_REQUEST.getValue())) {
                                    response = Response.status(Response.Status.BAD_REQUEST).entity(authorizationListResponseBackWsDto.getErrorInformationDto()).build();
                                } else if (authorizationListResponseBackWsDto.getErrorInformationDto().getTppMessages() != null && !authorizationListResponseBackWsDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                                        authorizationListResponseBackWsDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.RESOURCE_UNKNOWN .getValue())) {
                                    response = Response.status(Response.Status.NOT_FOUND).entity(authorizationListResponseBackWsDto.getErrorInformationDto()).build();
                                } else {
                                    response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(authorizationListResponseBackWsDto.getErrorInformationDto()).build();
                                }
                            }
                        } else {
                            response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.BAD_REQUEST.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_payment_id", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                        }
                    } else {
                        response = Response.status(Response.Status.NOT_FOUND).entity(Psd2Util.setErrorInformationDto(ErrorCode.PRODUCT_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_not_supp_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                    }
                } else {
                    response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.PRODUCT_UNKNOWN .getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_wrong_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                }
            } else {
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR .getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
            }
        } catch (Exception e) {
            log.error("Exception is :", e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto(ErrorCode.INTERNAL_SERVER_ERROR.getValue(), new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();
        }
        return response;
    }

    /**
     * summary: Read the SCA status of the payment cancellation's authorisation.
     * description: |
     * This method returns the SCA status of a payment initiation's authorisation sub-resource.
     */
    public Response getPaymentCancellationScaStatus(@PathParam("payment-product") String paymentProduct, @PathParam("paymentId") String paymentId, @PathParam("cancellationId") String cancellationAuthorisationId,
                                                    HttpHeaders httpHeaders) {
        Response response = null;
        //response object for body
        ScaStatusResponseDto scaStatusResponseDtoResult = new ScaStatusResponseDto();
        scaStatusResponseDtoResult.setValid(true);
        try {
            MultivaluedMap<String, String> requestHeaders = httpHeaders.getRequestHeaders();
            String tppId = requestHeaders.getFirst(TPP_ID.getValue());
            //validate path parameter
            if (StringUtils.isNotBlank((paymentId)) && StringUtils.isNotBlank(cancellationAuthorisationId) && StringUtils.isNotBlank(paymentProduct)) {
                if (PaymentProduct.getListPaymentProduct().contains(paymentProduct)) {
                    if (!StringUtils.equals(paymentProduct, PaymentProduct.INSTANT_SEPA_CREDIT_TANSFER.getCode()) && !StringUtils.equals(paymentProduct, PaymentProduct.PAIN_INSTANT_SEPA_CREDIT_TANSFER.getCode())) {
                        /*String jsonResponse = null;
                        Integer paymentIdInt = null;
                        Integer authIdInt = null;
                        try {
                            paymentIdInt = Integer.valueOf(paymentId);
                            authIdInt = Integer.parseInt(cancellationAuthorisationId);
                        } catch (NumberFormatException nfe) {
                            response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_payment", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                            return response;
                        }*/
                        // ScaStatusResponseDto scaStatusResponseBackWsDto = authorizationBean.getScaStatus(paymentIdInt, Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), authIdInt, PaymentServiceType.SINGLE.getValue(), paymentProduct, tppId, Psd2Constants.AuthorizationType.CANCELLED.getValue()); // stari
                        ScaStatusResponseDto scaStatusResponseBackWsDto = authorizationBean.getScaStatus(paymentId, Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), cancellationAuthorisationId, PaymentServiceType.SINGLE.getValue(), paymentProduct, tppId, Psd2Constants.AuthorizationType.CANCELLED.getValue()); // prazni
                        //validate response from backend ws
                        if (scaStatusResponseBackWsDto.getValid()) {
                            if (scaStatusResponseBackWsDto.getScaStatus() != null) {
                                scaStatusResponseDtoResult.setValid(null);
                                scaStatusResponseDtoResult.setScaStatus(scaStatusResponseBackWsDto.getScaStatus());
                                response = Response.status(Response.Status.OK).entity(scaStatusResponseDtoResult).build();
                            } else {
                                response = Response.status(Response.Status.NOT_FOUND).entity(scaStatusResponseBackWsDto.getErrorInformationDto()).build();
                            }
                        } else {
                            if (scaStatusResponseBackWsDto.getErrorInformationDto().getTppMessages() != null && !scaStatusResponseBackWsDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                                    scaStatusResponseBackWsDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.RESOURCE_UNKNOWN.getValue())) {
                                response = Response.status(Response.Status.NOT_FOUND).entity(scaStatusResponseBackWsDto).build();
                            }else {
                                response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(scaStatusResponseBackWsDto.getErrorInformationDto()).build();
                            }
                        }
                    } else {
                        response = Response.status(Response.Status.NOT_FOUND).entity(Psd2Util.setErrorInformationDto(ErrorCode.PRODUCT_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_not_supp_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                    }
                } else {
                    response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.PRODUCT_UNKNOWN .getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_wrong_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                }
            } else {
                List<String> errorList = new ArrayList<>();
                if (!StringUtils.isNotBlank((paymentId))) {
                    errorList.add(InternationalizationUtil.getLocalMessage("psd2_no_payment_id", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
                }
                if (!StringUtils.isNotBlank(cancellationAuthorisationId)) {
                    errorList.add(InternationalizationUtil.getLocalMessage("psd2_no_auth_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
                }
                if (!StringUtils.isNotBlank(paymentProduct)) {
                    errorList.add(InternationalizationUtil.getLocalMessage("psd2_no_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE));
                }
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), errorList)).build();
            }
        } catch (Exception e) {
            log.error("Exceprion is:", e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto(ErrorCode.INTERNAL_SERVER_ERROR.getValue(), new ArrayList<>(Arrays.asList(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase())))).build();
        }
        return response;
    }
}
