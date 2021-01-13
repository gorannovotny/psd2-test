package hr.abc.psd2.resources.ws.impl;

import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.CONTENT_TYPE;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.PSU_IP_ADDRESS;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.TPP_ID;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.TPP_REDIRECT_URI;
import static hr.abc.psd2.util.Psd2Constants.RequestHeaderAttributes.X_REQUEST_ID;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import hr.abc.psd2.bean.IAuthorizationBean;
import hr.abc.psd2.bean.INalogTppBean;
import hr.abc.psd2.model.dto.authorization.AuthorizationListResponseDto;
import hr.abc.psd2.model.dto.authorization.CreateAuthorizationRequestDto;
import hr.abc.psd2.model.dto.authorization.ScaStatusResponseDto;
import hr.abc.psd2.model.dto.authorization.StartAuthorizationResponseDto;
import hr.abc.psd2.model.dto.error.ErrorCode;
import hr.abc.psd2.model.dto.pis.PaymentCancellationResponseDto;
import hr.abc.psd2.model.dto.pis.PaymentProduct;
import hr.abc.psd2.model.dto.pis.PaymentServiceType;
import hr.abc.psd2.model.dto.pis.SinglePaymentDto;
import hr.abc.psd2.model.dto.pis.SinglePaymentResponseDto;
import hr.abc.psd2.model.dto.pis.TransactionStatusResponseDto;
import hr.abc.psd2.resources.ws.IBulkPaymentsServiceResource;
import hr.abc.psd2.util.GenericBassxConstants;
import hr.abc.psd2.util.InternationalizationUtil;
import hr.abc.psd2.util.Psd2Constants;
import hr.abc.psd2.util.Psd2Util;
import lombok.extern.slf4j.Slf4j;

/**
 * Web service resource for Payment Information Service - Single Payment.
 * <p>
 * Created by tkorpar on 19.09.2018..
 */
@Slf4j
public class BulkPaymentsServiceResource implements IBulkPaymentsServiceResource {

  
    @EJB
    private INalogTppBean nalogTppBean; 
    
    @EJB
    private IAuthorizationBean authorizationBean;

    /**
     * summary: Payment initiation request
     * description: This method is used to initiate a payment at the ASPSP.
     * This method to initiate a payment initiation at the ASPSP can be sent with
     * either a JSON body or an pain.001 body depending on the payment product in the path.
     **/
    public Response initiateBulkPayment(String psd2Nalog, String paymentProduct, ContainerRequestContext ctx) {
        Response response = null;
        try {
            MultivaluedMap<String, String> requestHeaders = ctx.getHeaders();
            String xRequestId = requestHeaders.getFirst(X_REQUEST_ID.getValue());
            String contentType = requestHeaders.getFirst(CONTENT_TYPE.getValue());
            //validate payment product
            if (StringUtils.isBlank(paymentProduct)) {
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            }
            if (!PaymentProduct.getListPaymentProduct().contains(paymentProduct)) {
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.PRODUCT_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_wrong_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            }
//            if (StringUtils.equals(paymentProduct, PaymentProduct.INSTANT_SEPA_CREDIT_TANSFER.getCode()) || StringUtils.equals(paymentProduct, PaymentProduct.PAIN_INSTANT_SEPA_CREDIT_TANSFER.getCode())
//                    || StringUtils.equals(paymentProduct, PaymentProduct.HR_INSTANT_DOMESTIC_PAYMENTS.getCode()) || StringUtils.equals(paymentProduct, PaymentProduct.PAIN_HR_INSTANT_DOMESTIC_PAYMENTS.getCode())
//                    || !PaymentProduct.getListBulkPaymentProductHr().contains(paymentProduct)) {
//                response = Response.status(Response.Status.NOT_FOUND).entity(Psd2Util.setErrorInformationDto(ErrorCode.PRODUCT_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_not_supp_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
//                return response;
//            }
            if (StringUtils.isBlank(psd2Nalog)) {
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_request_body", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            }
            if (!StringUtils.startsWith(contentType, MediaType.APPLICATION_XML) || !paymentProduct.startsWith(PaymentProduct.PAIN_SEPA_CREDIT_TANSFER.getCode().substring(0, 3))) {
                response = Response.status(Response.Status.NOT_FOUND).entity(Psd2Util.setErrorInformationDto(ErrorCode.PRODUCT_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_not_supp_json", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            } else {
                //Napunimo SimplePaymentDto sa podacima iz headera jer su nam potrebni za spremanje naloga
                SinglePaymentDto psd2RequestHeader = SinglePaymentDto.builder().build();
                psd2RequestHeader.setPaymentService(PaymentServiceType.BULK.getValue());
                psd2RequestHeader.setPaymentProduct(paymentProduct);
                psd2RequestHeader.setxRequestId(xRequestId);
                psd2RequestHeader.setTppId(requestHeaders.getFirst(TPP_ID.getValue()));
                psd2RequestHeader.setContentType(contentType);
                psd2RequestHeader.setPsuIpAddress(requestHeaders.getFirst(PSU_IP_ADDRESS.getValue()));
                psd2RequestHeader.setTppRedirectUri(requestHeaders.getFirst(TPP_REDIRECT_URI.getValue()));
                SinglePaymentResponseDto singlePaymentResponseDto = null;

                // singlePaymentResponseDto = nalogTppBean.resolveDatoteka(psd2Nalog, psd2RequestHeader); //stari
                singlePaymentResponseDto = nalogTppBean.resolveDatoteka(psd2Nalog, psd2RequestHeader); // prazni
                if (singlePaymentResponseDto.getIsValid()) {
                    singlePaymentResponseDto.setIsValid(null);
                    response = Response.created(new URI(singlePaymentResponseDto.get_links().getSelf().getHref())).header("X-Request-ID", xRequestId).header("ASPSP-SCA-Approach", "REDIRECT").entity(singlePaymentResponseDto).build();
                    return response;
                } else {
                    response = Response.status(Response.Status.BAD_REQUEST).entity(singlePaymentResponseDto.getErrorInformationDto()).build();
                    return response;
                }
            }
//        } catch (AbitRuleException are) {
//            response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(are.getMessage())))).build();
//            return response;

        } catch (Exception e) {
            log.error("Exception is:", e);
            response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(Psd2Util.setErrorInformationDto(ErrorCode.INTERNAL_SERVER_ERROR.getValue(), new ArrayList<>(Arrays.asList(e.getMessage())))).build();
            return response;
        }
    }


    /**
     * summary: Payment Cancellation Request
     * description: This method initiates the cancellation of a payment.
     * Depending on the payment-service, the payment-product and the ASPSP's implementation,
     * this TPP call might be sufficient to cancel a payment.
     */
    public Response cancelBulkPayment(String paymentProduct, String paymentId, ContainerRequestContext ctx) {
        Response response = null;
        try {
            MultivaluedMap<String, String> requestHeaders = ctx.getHeaders();
            //validate payment product
            if (StringUtils.isBlank(paymentProduct)) {
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            }
            if (!PaymentProduct.getListPaymentProduct().contains(paymentProduct)) {
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.PRODUCT_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_wrong_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            }
//            if (StringUtils.equals(paymentProduct, PaymentProduct.INSTANT_SEPA_CREDIT_TANSFER.getCode()) || StringUtils.equals(paymentProduct, PaymentProduct.PAIN_INSTANT_SEPA_CREDIT_TANSFER.getCode())
//                    || StringUtils.equals(paymentProduct, PaymentProduct.HR_INSTANT_DOMESTIC_PAYMENTS.getCode()) || StringUtils.equals(paymentProduct, PaymentProduct.PAIN_HR_INSTANT_DOMESTIC_PAYMENTS.getCode())
//                    || !PaymentProduct.getListBulkPaymentProductHr().contains(paymentProduct)) {
//                response = Response.status(Response.Status.NOT_FOUND).entity(Psd2Util.setErrorInformationDto(ErrorCode.PRODUCT_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_not_supp_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
//                return response;
//            }
            //validate payment id
            if (StringUtils.isBlank(paymentId)) {
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_payment_id", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            } else {
                Integer paymentIdInt = null;
                String tppId = requestHeaders.getFirst(TPP_ID.getValue());
                try {
                    paymentIdInt = Integer.valueOf(paymentId);
                } catch (NumberFormatException nfe) {
                    response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_payment", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                    return response;
                }
                // PaymentCancellationResponseDto responseDto = nalogTppBean.cancelBulkPayment(PaymentServiceType.BULK.getValue(), paymentProduct, tppId, paymentIdInt); //stari
                PaymentCancellationResponseDto responseDto = nalogTppBean.cancelBulkPayment(PaymentServiceType.BULK.getValue(), paymentProduct, tppId, paymentIdInt); // prazni
                if (responseDto.getIsValid()) {
                    responseDto.setIsValid(null);
                    response = Response.status(Response.Status.ACCEPTED).entity(responseDto).build();
                    return response;
                } else {
                    if (responseDto.getErrorInformationDto().getTppMessages() != null && !responseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            responseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.CANCELLATION_INVALID.getValue())) {
                        response = Response.status(Response.Status.METHOD_NOT_ALLOWED).entity(responseDto.getErrorInformationDto()).build();
                    } else if (responseDto.getErrorInformationDto().getTppMessages() != null && !responseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            responseDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.RESOURCE_UNKNOWN.getValue())) {
                        response = Response.status(Response.Status.NOT_FOUND).entity(responseDto.getErrorInformationDto()).build();
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
     * summary: Get Bulk Payment Information
     * description: Returns the content of a payment object
     */
    public Response getBulkPaymentInformation(String paymentProduct, String paymentId, ContainerRequestContext ctx) {
        Response response = null;
        try {
            MultivaluedMap<String, String> requestHeaders = ctx.getHeaders();
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
                    || !PaymentProduct.getListBulkPaymentProductHr().contains(paymentProduct)) {
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
                try {
                    paymentIdInt = Integer.valueOf(paymentId);
                } catch (NumberFormatException nfe) {
                    response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_payment", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                    return response;
                }
                ////platni/pain001/createSepaPain001.xhtml
                // SinglePaymentDto singlePaymentDto = nalogTppBean.getBulkPaymentInformation(paymentProduct, tppId, paymentIdInt); // stari
                SinglePaymentDto singlePaymentDto = nalogTppBean.getBulkPaymentInformation(paymentProduct, tppId, paymentIdInt); // prazni
                if (singlePaymentDto.getIsValid()) {
                    response = Response.status(Response.Status.OK).type(MediaType.APPLICATION_XML).entity(singlePaymentDto.getPainXml()).build();
                    return response;
                } else {
                    if (singlePaymentDto.getErrorInformationDto().getTppMessages() != null && !singlePaymentDto.getErrorInformationDto().getTppMessages().isEmpty() &&
                            singlePaymentDto.getErrorInformationDto().getTppMessages().get(0).getCode().equals(ErrorCode.BAD_REQUEST.getValue())) {
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
     *
     * @param paymentId - datotekaID
     */

    public Response getBulkPaymentInitiationStatus(String paymentProduct, String paymentId, ContainerRequestContext ctx) {
        Response response = null;
        try {
            MultivaluedMap<String, String> requestHeaders = ctx.getHeaders();
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
                    || !PaymentProduct.getListBulkPaymentProductHr().contains(paymentProduct)){
                response = Response.status(Response.Status.NOT_FOUND).entity(Psd2Util.setErrorInformationDto(ErrorCode.PRODUCT_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_not_supp_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            }
            //validate payment id
            if (StringUtils.isBlank(paymentId)) {
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_bulk_payment_id", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            } else {
                Integer paymentIdInt = null;
                String tppId = requestHeaders.getFirst(TPP_ID.getValue());
                try {
                    paymentIdInt = Integer.valueOf(paymentId);
                } catch (NumberFormatException nfe) {
                    response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_bulk_file", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                    return response;
                }
                // TransactionStatusResponseDto transactionStatusResponseDto = nalogTppBean.getBulkPaymentInitiationStatus(paymentProduct, tppId, paymentIdInt); // stari
                TransactionStatusResponseDto transactionStatusResponseDto = nalogTppBean.getBulkPaymentInitiationStatus(paymentProduct, tppId, paymentIdInt); // prazni
                if (transactionStatusResponseDto.getIsValid()) {
                    response = Response.status(Response.Status.OK).type(MediaType.APPLICATION_XML).entity(transactionStatusResponseDto.getPain002()).build();
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
    public Response startBulkPaymentAuthorisation(String paymentProduct, String paymentId, ContainerRequestContext ctx) {
        Response response = null;
        try {
            MultivaluedMap<String, String> requestHeaders = ctx.getHeaders();

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
                Integer paymentIdInt = null;
                try {
                    paymentIdInt = Integer.valueOf(paymentId);
                } catch (NumberFormatException nfe) {
                    response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_payment", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                    return response;
                }
                // StartAuthorizationResponseDto startAuthorizationResponseDto = authorizationBean.startAuthorisation(new CreateAuthorizationRequestDto(PaymentServiceType.BULK.getValue(), paymentProduct, Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), paymentIdInt, Psd2Constants.AuthorizationType.CREATED.getValue(), xRequestId, tppId, null)); // stari
                StartAuthorizationResponseDto startAuthorizationResponseDto = authorizationBean.startAuthorisation(new CreateAuthorizationRequestDto(PaymentServiceType.BULK.getValue(), paymentProduct, Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), paymentIdInt.toString(), Psd2Constants.AuthorizationType.CREATED.getValue(), xRequestId, tppId, null)); // prazni
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
                        response = Response.status(Response.Status.NOT_FOUND).entity(startAuthorizationResponseDto.getErrorInformationDto()).build();
                        return response;
                    }
                    else if (startAuthorizationResponseDto.getErrorInformationDto().getTppMessages() != null && !startAuthorizationResponseDto.getErrorInformationDto().getTppMessages().isEmpty() &&
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
    public Response getBulkPaymentInitiationAuthorisation(String paymentProduct, String paymentId, ContainerRequestContext ctx) {
        Response response = null;
        AuthorizationListResponseDto authorizationListResponseResultDto = new AuthorizationListResponseDto();
        authorizationListResponseResultDto.setValid(true);
        try {
            MultivaluedMap<String, String> requestHeaders = ctx.getHeaders();
            String tppId = requestHeaders.getFirst(TPP_ID.getValue());
            //validate payment product
            if (StringUtils.isNotBlank(paymentProduct)) {
                if (PaymentProduct.getListPaymentProduct().contains(paymentProduct)) {
                    if (!StringUtils.equals(paymentProduct, PaymentProduct.INSTANT_SEPA_CREDIT_TANSFER.getCode()) && !StringUtils.equals(paymentProduct, PaymentProduct.PAIN_INSTANT_SEPA_CREDIT_TANSFER.getCode())) {
                        //validate path parameter
                        if (StringUtils.isNotBlank(paymentId)) {
                            Integer paymentIdInt = null;
                            try {
                                paymentIdInt = Integer.valueOf(paymentId);
                            } catch (NumberFormatException nfe) {
                                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_payment", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                                return response;
                            }
                            // AuthorizationListResponseDto authorizationListResponseBackWsDto = authorizationBean.getBulkAuthorizationSubResources(paymentIdInt, paymentProduct, tppId, Psd2Constants.AuthorizationType.CREATED.getValue()); // stari
                            AuthorizationListResponseDto authorizationListResponseBackWsDto = authorizationBean.getBulkAuthorizationSubResources(paymentIdInt, paymentProduct, tppId, Psd2Constants.AuthorizationType.CREATED.getValue()); // prazni
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
                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_payment_pr", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
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
    public Response getBulkPaymentInitiationScaStatus(String paymentProduct, String paymentId, String authorisationId, ContainerRequestContext ctx) {
        Response response = null;
        //response object for body
        ScaStatusResponseDto scaStatusResponseDtoResult = new ScaStatusResponseDto();
        scaStatusResponseDtoResult.setValid(true);
        try {
            MultivaluedMap<String, String> requestHeaders = ctx.getHeaders();
            String tppId = requestHeaders.getFirst(TPP_ID.getValue());
            //validate path parameter
            if (StringUtils.isNotBlank((paymentId)) && StringUtils.isNotBlank(authorisationId) && StringUtils.isNotBlank(paymentProduct)) {
                if (PaymentProduct.getListPaymentProduct().contains(paymentProduct)) {
                    if (!StringUtils.equals(paymentProduct, PaymentProduct.INSTANT_SEPA_CREDIT_TANSFER.getCode()) && !StringUtils.equals(paymentProduct, PaymentProduct.PAIN_INSTANT_SEPA_CREDIT_TANSFER.getCode())) {
                        String jsonResponse = null;
                        Integer paymentIdInt = null;
                        Integer authIdInt = null;
                        try {
                            paymentIdInt = Integer.valueOf(paymentId);
                            authIdInt = Integer.parseInt(authorisationId);
                        } catch (NumberFormatException nfe) {
                            response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_payment", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                            return response;
                        }
                        // ScaStatusResponseDto scaStatusResponseBackWsDto = authorizationBean.getScaStatus(paymentIdInt, Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), authIdInt, PaymentServiceType.BULK.getValue(), paymentProduct, tppId, Psd2Constants.AuthorizationType.CREATED.getValue()); // stari
                        ScaStatusResponseDto scaStatusResponseBackWsDto = authorizationBean.getScaStatus(paymentIdInt.toString(), Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), authIdInt.toString(), PaymentServiceType.BULK.getValue(), paymentProduct, tppId, Psd2Constants.AuthorizationType.CREATED.getValue()); // prazni
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
     * summary: Start the authorisation cancellation process for a payment initiation
     */
    public Response startBulkPaymentInitiationCancellationAuthorisation(String paymentProduct, String paymentId, ContainerRequestContext ctx) {
        Response response = null;
        try {
            MultivaluedMap<String, String> requestHeaders = ctx.getHeaders();

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

                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.FORMAT_ERROR .getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_no_payment_id", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                return response;
            } else {
                String tppId = requestHeaders.getFirst(TPP_ID.getValue());
                Integer paymentIdInt = null;
                try {
                    paymentIdInt = Integer.valueOf(paymentId);
                } catch (NumberFormatException nfe) {
                    response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_payment", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                    return response;
                }
                // StartAuthorizationResponseDto startAuthorizationResponseDto = authorizationBean.startAuthorisation(new CreateAuthorizationRequestDto(PaymentServiceType.BULK.getValue(), paymentProduct, Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), paymentIdInt, Psd2Constants.AuthorizationType.CANCELLED.getValue(), xRequestId, tppId, null)); // stari
                StartAuthorizationResponseDto startAuthorizationResponseDto = authorizationBean.startAuthorisation(new CreateAuthorizationRequestDto(PaymentServiceType.BULK.getValue(), paymentProduct, Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), paymentIdInt.toString(), Psd2Constants.AuthorizationType.CANCELLED.getValue(), xRequestId, tppId, null)); // prazni
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
    public Response getBulkPaymentInitiationCancellationAuthorisationInformation(String paymentProduct, String paymentId, ContainerRequestContext ctx) {
        Response response = null;
        AuthorizationListResponseDto authorizationListResponseResultDto = new AuthorizationListResponseDto();
        authorizationListResponseResultDto.setValid(true);
        try {
            MultivaluedMap<String, String> requestHeaders = ctx.getHeaders();
            String tppId = requestHeaders.getFirst(TPP_ID.getValue());
            //validate payment product
            if (StringUtils.isNotBlank(paymentProduct)) {
                if (PaymentProduct.getListPaymentProduct().contains(paymentProduct)) {
                    if (!StringUtils.equals(paymentProduct, PaymentProduct.INSTANT_SEPA_CREDIT_TANSFER.getCode()) && !StringUtils.equals(paymentProduct, PaymentProduct.PAIN_INSTANT_SEPA_CREDIT_TANSFER.getCode())) {
                        //validate path parameter
                        if (StringUtils.isNotBlank(paymentId)) {
                            Integer paymentIdInt = null;
                            try {
                                paymentIdInt = Integer.valueOf(paymentId);
                            } catch (NumberFormatException nfe) {
                                response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_payment", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                            }
                            // AuthorizationListResponseDto authorizationListResponseBackWsDto = authorizationBean.getBulkAuthorizationSubResources(paymentIdInt, paymentProduct, tppId, Psd2Constants.AuthorizationType.CANCELLED.getValue()); // stari
                            AuthorizationListResponseDto authorizationListResponseBackWsDto = authorizationBean.getBulkAuthorizationSubResources(paymentIdInt, paymentProduct, tppId, Psd2Constants.AuthorizationType.CANCELLED.getValue()); // prazni
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
                                }else {
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
    public Response getBulkPaymentCancellationScaStatus(String paymentProduct, String paymentId, String cancellationAuthorisationId, ContainerRequestContext ctx) {
        Response response = null;
        //response object for body
        ScaStatusResponseDto scaStatusResponseDtoResult = new ScaStatusResponseDto();
        scaStatusResponseDtoResult.setValid(true);
        try {
            MultivaluedMap<String, String> requestHeaders = ctx.getHeaders();
            String tppId = requestHeaders.getFirst(TPP_ID.getValue());
            //validate path parameter
            if (StringUtils.isNotBlank((paymentId)) && StringUtils.isNotBlank(cancellationAuthorisationId) && StringUtils.isNotBlank(paymentProduct)) {
                if (PaymentProduct.getListPaymentProduct().contains(paymentProduct)) {
                    if (!StringUtils.equals(paymentProduct, PaymentProduct.INSTANT_SEPA_CREDIT_TANSFER.getCode()) && !StringUtils.equals(paymentProduct, PaymentProduct.PAIN_INSTANT_SEPA_CREDIT_TANSFER.getCode())) {
                        String jsonResponse = null;
                        Integer paymentIdInt = null;
                        Integer authIdInt = null;
                        try {
                            paymentIdInt = Integer.valueOf(paymentId);
                            authIdInt = Integer.parseInt(cancellationAuthorisationId);
                        } catch (NumberFormatException nfe) {
                            response = Response.status(Response.Status.BAD_REQUEST).entity(Psd2Util.setErrorInformationDto(ErrorCode.RESOURCE_UNKNOWN.getValue(), new ArrayList<>(Arrays.asList(InternationalizationUtil.getLocalMessage("psd2_unkonown_payment", GenericBassxConstants.Psd2.MESSAGE_BUNDLE))))).build();
                            return response;
                        }
                        // ScaStatusResponseDto scaStatusResponseBackWsDto = authorizationBean.getScaStatus(paymentIdInt, Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), authIdInt, PaymentServiceType.BULK.getValue(), paymentProduct, tppId, Psd2Constants.AuthorizationType.CANCELLED.getValue()); // stari
                        ScaStatusResponseDto scaStatusResponseBackWsDto = authorizationBean.getScaStatus(paymentIdInt.toString(), Psd2Constants.AuthorizationObjectType.NALOG_TPP_AUTHORIZATION.getValue(), authIdInt.toString(), PaymentServiceType.BULK.getValue(), paymentProduct, tppId, Psd2Constants.AuthorizationType.CANCELLED.getValue()); // prazni
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

