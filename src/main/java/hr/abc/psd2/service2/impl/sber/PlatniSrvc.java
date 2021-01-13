package hr.abc.psd2.service2.impl.sber;

import hr.abc.psd2.service2.IPlatniSrvc;
import hr.abc.psd2.model.dto.core.NalogDevizniDto;
import hr.abc.psd2.model.dto.core.NalogSepaDto;
import hr.abc.psd2.model.dto.core.SifraNamjeneDto;
import hr.abc.psd2.security.Globals;

import javax.enterprise.context.Dependent;
import java.io.Serializable;

@Dependent
public class PlatniSrvc implements IPlatniSrvc, Serializable {

    private static final long serialVersionUID = 1L;

    @Override
    public SifraNamjeneDto getSifraNamjeneDtoBySifraNamjene(String sifraNamjene) {
        return null;
    }

    @Override
    public NalogDevizniDto validateAndInitializeKupoprodaja(NalogDevizniDto devizniNalogDto, Globals globals) {
        return null;
    }

    @Override
    public NalogDevizniDto createKupoprodaja(NalogDevizniDto devizniNalogDto, Globals globals) {
        return null;
    }

    @Override
    public NalogSepaDto forwardToInstaPay(NalogSepaDto sepaDto, Globals globals) {
        return null;
    }
}
