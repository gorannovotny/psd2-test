package hr.abc.psd2.service2.impl.sber;

import hr.abc.psd2.service2.IInternetSrvc;
import hr.abc.psd2.model.dto.core.Psd2WebPregledRacunaDto;
import hr.abc.psd2.model.sepa.camt.Document;

import javax.enterprise.context.Dependent;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Dependent
public class InternetSrvc implements IInternetSrvc, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public List<Psd2WebPregledRacunaDto> getPopisRacunaFromIb(List<Integer> listSifraParOvlastenja) {
        return null;
    }

    @Override
    public Document getIzvodiCAMT(String sifraPartije, String patriPar, Date datumOd, Date datumDo, String sifraValute, List<String> listaValuta, Boolean isPravna) {
        return null;
    }
}
