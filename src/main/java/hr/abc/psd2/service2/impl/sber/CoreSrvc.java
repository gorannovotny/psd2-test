package hr.abc.psd2.service2.impl.sber;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import hr.abc.psd2.mapper.PaymentMapper;
import hr.abc.psd2.model.dto.ais.PrometStanjePartijeDto;
import hr.abc.psd2.model.dto.core.NalogDto;
import hr.abc.psd2.model.dto.core.NalogKnjizniDto;
import hr.abc.psd2.model.dto.core.NalogPlatniDto;
import hr.abc.psd2.model.dto.core.PartijaDto;
import hr.abc.psd2.model.dto.core.ValutaDto;
import hr.abc.psd2.model.dto.pis.SinglePaymentDto;
import hr.abc.psd2.model.dto.pis.TppNalogPlatniDto;
import hr.abc.psd2.model.dto.pis.TransactionStatusResponseDto;
import hr.abc.psd2.service2.ICoreSrvc;
import hr.abc.psd2.util.Bassx2Constants;
import hr.sberbank.model.account.AccountDetails;
import hr.sberbank.model.account.AccountStatus;
import hr.sberbank.model.payment.PaymentOrderInformationResponse;
import hr.sberbank.model.payment.PaymentOrderRequestJson;
import hr.sberbank.model.payment.PaymentOrderStatusResponseJson;
import hr.sberbank.resources.AccountRestSrvc;
import hr.sberbank.resources.PaymentRestSrvc;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@Dependent
public class CoreSrvc implements ICoreSrvc, Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    @RestClient
    AccountRestSrvc accountRestSrvc;

    @Inject
    @RestClient
    PaymentRestSrvc paymentRestSrvc;
    
    @Inject
    PaymentMapper paymentMapper;

    @Override
    public PartijaDto getPartijaDtoByBrojPartije(String brojPartije) {
        PartijaDto partijaDto=new PartijaDto();
        AccountDetails accountDetails=accountRestSrvc.accountsGet(brojPartije,null).getAccounts().stream().findFirst().get();
        if(accountDetails != null && accountDetails.getStatus().equals(AccountStatus.ACTIVE)) {
            partijaDto.setStatus(Bassx2Constants.Core.PARTIJA_STATUPAR_OTVORENA);
        }
        return partijaDto;
    }

    @Override
    public String getCustomerIdByIban(String iban) {
        return accountRestSrvc.accountsGet(iban,null).getAccounts().stream().findFirst().get().getCustomerId();
    }

    @Override
    public String getVrijednostParametraBySifra(String sifraParametra) {
        return null;
    }

    @Override
    public List<ValutaDto> getValutaDtoList() {
        return Arrays.asList(new ValutaDto(ValutaDto.VALUTA_SIFRAVAL_HOME_CURRENCY,ValutaDto.VALUTA_OZNKAVAL_HOME_CURRENCY));
    }

    @Override
    public List<PrometStanjePartijeDto> getPrometiKontraStrana(PrometStanjePartijeDto filterDto, String reportType, boolean isPravna) {
        return null;
    }

    @Override
    public void createNalogFromChangedTppNalog(TppNalogPlatniDto tppNalogPlatniDto) {

    }

    @Override
    public NalogKnjizniDto getNalogKnjizniDto(Integer sifraKnjiznogNaloga) {
        return null;
    }

    @Override
    public List<NalogPlatniDto> adaptAndInsertListPlatniNalog(List<Object> inputSource, boolean hasSepaNalog) {
        return null;
    }

    @Override
    public List<NalogKnjizniDto> fetchListKnjizniNalogNaknadeByPrethodnik(List<NalogDto> listSifreKnjiznihNalogaPrethodnika, List<String> listGrupaKlaseNaloga, boolean ukljuciPrethodnike) {
        return null;
    }

    @Override
    public Response initiatePayment(String paymentoOrder, String paymentProduct, String requestUUID) throws JsonProcessingException {
        ObjectMapper objectMapper= new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        PaymentOrderRequestJson json=objectMapper.readValue(paymentoOrder, PaymentOrderRequestJson.class);
        return paymentRestSrvc.paymentsPaymentProductPost(json, paymentProduct, UUID.randomUUID());
    }

    @Override
    public SinglePaymentDto getPaymentRequest(String paymentService, String paymentProduct, String tppId, String paymentId) {
    	PaymentOrderInformationResponse paymentInfRes = paymentRestSrvc.paymentsPaymentProductPaymentRequestIdGet(paymentProduct,paymentId,UUID.randomUUID());
        return paymentMapper.mapSinglePayment(paymentInfRes);
    }

    @Override
    public TransactionStatusResponseDto getPaymentRequestStatus(String paymentService, String paymentProduct, String tppId, String paymentId) {
    	PaymentOrderStatusResponseJson paymentOrderStatResp = paymentRestSrvc.paymentsPaymentProductPaymentRequestIdStatusGet(paymentProduct,paymentId,UUID.randomUUID());
        return paymentMapper.mapTransactionStatusResponse(paymentOrderStatResp);
    }

    @Override
    public Response cancelPayment(String paymentService, String paymentProduct, String tppId, String paymentId) {
        return paymentRestSrvc.paymentsBulkCancelPaymentRequestIdDelete(paymentId, UUID.randomUUID(),null);
    }
}
