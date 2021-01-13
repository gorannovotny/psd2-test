package hr.abc.psd2.service2.impl.sber;

import hr.abc.psd2.service2.IVerifikacijaSrvc;
import hr.abc.psd2.model.dto.core.NalogDto;
import hr.abc.psd2.security.Globals;

import javax.enterprise.context.Dependent;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Dependent
public class VerifikacijaSrvc implements IVerifikacijaSrvc, Serializable {

    private static final long serialVersionUID = 1L;


    @Override
    public NalogDto inkrementStatusa(NalogDto nalogDto, Globals globals) {
        return null;
    }

    @Override
    public void povuciNalog(NalogDto nalogDto, Integer sifraVerifikatora) {

    }

    @Override
    public Map<Integer, List<String>> povuciNalogList(List<NalogDto> listNalogDto, Integer sifraVerifikatora) {
        return null;
    }
}
