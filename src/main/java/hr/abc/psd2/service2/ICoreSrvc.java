package hr.abc.psd2.service2;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import hr.abc.psd2.model.dto.ais.PrometStanjePartijeDto;
import hr.abc.psd2.model.dto.core.NalogDto;
import hr.abc.psd2.model.dto.core.NalogKnjizniDto;
import hr.abc.psd2.model.dto.core.NalogPlatniDto;
import hr.abc.psd2.model.dto.core.PartijaDto;
import hr.abc.psd2.model.dto.core.ValutaDto;
import hr.abc.psd2.model.dto.pis.SinglePaymentDto;
import hr.abc.psd2.model.dto.pis.TppNalogPlatniDto;
import hr.abc.psd2.model.dto.pis.TransactionStatusResponseDto;

import javax.ws.rs.core.Response;

public interface ICoreSrvc {

    PartijaDto getPartijaDtoByBrojPartije(String iban);

    String getCustomerIdByIban(String iban);

    String getVrijednostParametraBySifra(String sifraParametra);


    //List<ValutaDto> valutaDtoList = LookupHelper.getICoreDaoProxyRemote().getValutaDtoList(); // stari
    List<ValutaDto> getValutaDtoList();

    List<PrometStanjePartijeDto> getPrometiKontraStrana(PrometStanjePartijeDto filterDto, String reportType, boolean isPravna);

    void createNalogFromChangedTppNalog (TppNalogPlatniDto tppNalogPlatniDto);

    NalogKnjizniDto getNalogKnjizniDto(Integer sifraKnjiznogNaloga);

    List<NalogPlatniDto> adaptAndInsertListPlatniNalog(List<Object> inputSource, boolean hasSepaNalog);

    List<NalogKnjizniDto> fetchListKnjizniNalogNaknadeByPrethodnik(List<NalogDto> listSifreKnjiznihNalogaPrethodnika, List<String> listGrupaKlaseNaloga, boolean ukljuciPrethodnike);

    Response initiatePayment(String paymentOrder, String paymentProduct, String requestUUID) throws JsonProcessingException;

    SinglePaymentDto getPaymentRequest(String paymentService, String paymentProduct, String tppId, String paymentId);

    TransactionStatusResponseDto getPaymentRequestStatus(String paymentService, String paymentProduct, String tppId, String paymentId);

    Response cancelPayment(String paymentService, String paymentProduct, String tppId, String paymentId);

}
