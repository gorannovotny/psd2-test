package hr.abc.psd2.service2;

import java.util.Date;
import java.util.List;

import hr.abc.psd2.model.dto.core.Psd2WebPregledRacunaDto;
import hr.abc.psd2.model.sepa.camt.Document;

public interface IInternetSrvc {

    List<Psd2WebPregledRacunaDto> getPopisRacunaFromIb (List<Integer> listSifraParOvlastenja);

    Document getIzvodiCAMT(String sifraPartije, String patriPar, Date datumOd, Date datumDo, String sifraValute, List<String> listaValuta, Boolean isPravna);
}
