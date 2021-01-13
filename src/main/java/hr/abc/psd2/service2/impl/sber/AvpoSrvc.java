package hr.abc.psd2.service2.impl.sber;

import hr.abc.psd2.service2.IAvpoSrvc;
import hr.abc.psd2.model.dto.core.NalogPlatniDto;
import hr.abc.psd2.model.dto.core.NalogSepaDto;
import hr.abc.psd2.security.Globals;

import javax.enterprise.context.Dependent;
import java.io.Serializable;
import java.util.List;

@Dependent
public class AvpoSrvc implements IAvpoSrvc, Serializable {

    private static final long serialVersionUID = 1L;


    @Override
    public List<NalogSepaDto> pripremaNaloga(List<NalogSepaDto> nalogSepaDtos, Globals globals) {
        return null;
    }

    @Override
    public List<NalogPlatniDto> resolvePainDatoteka(byte[] podaciDatoteke) {
        return null;
    }
}
