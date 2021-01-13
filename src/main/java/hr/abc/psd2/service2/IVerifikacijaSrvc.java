package hr.abc.psd2.service2;

import java.util.List;
import java.util.Map;

import hr.abc.psd2.model.dto.core.NalogDto;
import hr.abc.psd2.security.Globals;

public interface IVerifikacijaSrvc {

    NalogDto inkrementStatusa(NalogDto nalogDto, Globals globals);

    void povuciNalog(NalogDto nalogDto, Integer sifraVerifikatora);

    Map<Integer, List<String>> povuciNalogList(List<NalogDto> listNalogDto, Integer sifraVerifikatora);
}
