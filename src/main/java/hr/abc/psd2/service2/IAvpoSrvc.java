package hr.abc.psd2.service2;

import java.util.List;

import hr.abc.psd2.model.dto.core.NalogPlatniDto;
import hr.abc.psd2.model.dto.core.NalogSepaDto;
import hr.abc.psd2.security.Globals;

public interface IAvpoSrvc {

    List<NalogSepaDto> pripremaNaloga(List<NalogSepaDto> nalogSepaDtos, Globals globals);

    List<NalogPlatniDto> resolvePainDatoteka(byte[] podaciDatoteke);
}
