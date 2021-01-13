package hr.abc.psd2.mapper;

import hr.abc.psd2.model.dto.ais.AccountReferenceDto;
import hr.abc.psd2.model.dto.ais.Amount;
import hr.abc.psd2.model.dto.pis.*;
import hr.sberbank.model.payment.PaymentOrderInformationResponse;
import hr.sberbank.model.payment.PaymentOrderRequestJson;
import hr.sberbank.model.payment.PaymentOrderStatusResponseJson;

import java.io.Serializable;

import javax.enterprise.context.Dependent;

@Dependent
public class PaymentMapper implements Serializable {

	private static final long serialVersionUID = 1L;

	public SinglePaymentDto mapSinglePayment(PaymentOrderInformationResponse orderInformationResponse) {
		return SinglePaymentDto.builder()
				.endToEndIdentification(orderInformationResponse.getEndToEndIdentification())
				.debtorAccount(new AccountReferenceDto(orderInformationResponse.getDebtorAccount().getIban(),orderInformationResponse.getDebtorAccount().getCurrency()))
				.ultimateDebtor(orderInformationResponse.getUltimateDebtor())
				.instructedAmount(new Amount(orderInformationResponse.getInstructedAmount().getCurrency(),orderInformationResponse.getInstructedAmount().getAmount()))
				.creditorAccount(new AccountReferenceDto(orderInformationResponse.getCreditorAccount().getIban(),orderInformationResponse.getCreditorAccount().getCurrency()))
				.creditorAgentName(orderInformationResponse.getCreditorAgentName())
				.creditorAgent(orderInformationResponse.getCreditorAgent())
				.creditorName(orderInformationResponse.getCreditorName())
				.creditorAddress(orderInformationResponse.getCreditorAddress() !=null?new AddressDto(orderInformationResponse.getCreditorAddress().getStreet(),orderInformationResponse.getCreditorAddress().getBuildingNumber(),orderInformationResponse.getCreditorAddress().getCity(),orderInformationResponse.getCreditorAddress().getPostalCode(),orderInformationResponse.getCreditorAddress().getCountry()):null)
				.ultimateCreditor(orderInformationResponse.getUltimateCreditor())
				.purposeCode(orderInformationResponse.getPurposeCode())
				.chargeBearer(orderInformationResponse.getChargeBearer() != null? orderInformationResponse.getChargeBearer().name():null)
				.remittanceInformationUnstructured(orderInformationResponse.getRemittanceInformationUnstructured())
				.remittanceInformationStructured(orderInformationResponse.getRemittanceInformationStructured() != null ?new RemittanceDto(orderInformationResponse.getRemittanceInformationStructured().getReference(),orderInformationResponse.getRemittanceInformationStructured().getReferenceType(),orderInformationResponse.getRemittanceInformationStructured().getReferenceIssuer()):null)
				.requestedExecutionDate(orderInformationResponse.getRequestedExecutionDate().toString())
				.transactionStatus(orderInformationResponse.getTransactionStatus())
				.paymentProduct(orderInformationResponse.getPaymentProduct().toString())
				.paymentService("payments")
				.build();
	}

	public TransactionStatusResponseDto mapTransactionStatusResponse(PaymentOrderStatusResponseJson statusResponseJson) {
		return new TransactionStatusResponseDto(statusResponseJson.getTransactionStatus().name() , true);
	}
}
