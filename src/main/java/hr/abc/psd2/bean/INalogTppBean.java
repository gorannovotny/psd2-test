package hr.abc.psd2.bean;

import hr.abc.psd2.exception.AbitRuleException;
import hr.abc.psd2.model.dto.pis.PaymentCancellationResponseDto;
import hr.abc.psd2.model.dto.pis.SinglePaymentDto;
import hr.abc.psd2.model.dto.pis.SinglePaymentResponseDto;
import hr.abc.psd2.model.dto.pis.TppNalogPlatniDto;
import hr.abc.psd2.model.dto.pis.TransactionStatusResponseDto;

public interface INalogTppBean {

    SinglePaymentResponseDto resolveDatoteka(String file, SinglePaymentDto header) throws AbitRuleException;

    PaymentCancellationResponseDto cancelBulkPayment(String paymentService, String paymentProduct, String tppId, Integer paymentId);

    SinglePaymentDto getBulkPaymentInformation(String paymentProduct, String tppId, Integer paymentId);

    TransactionStatusResponseDto getBulkPaymentInitiationStatus(String paymentProduct, String tppId, Integer paymentId);

    SinglePaymentResponseDto create(SinglePaymentDto tppNalog, String originalPaymentOrder) throws AbitRuleException;

    PaymentCancellationResponseDto cancelPayment(String paymentService, String paymentProduct, String tppId, String paymentId, String tppRedirectUriCancellation);

    SinglePaymentDto getPaymentInformation(String paymentService, String paymentProduct, String tppId, String paymentId);

    TransactionStatusResponseDto getPaymentInitiationStatus(String paymentService, String paymentProduct, String tppId, String paymentId);

    TppNalogPlatniDto getTppNalogBySifra(Integer paymentId);

}
