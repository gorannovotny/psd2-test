package hr.abc.psd2.service2;

import hr.abc.psd2.model.dto.core.NalogDevizniDto;
import hr.abc.psd2.model.dto.core.NalogSepaDto;
import hr.abc.psd2.model.dto.core.SifraNamjeneDto;
import hr.abc.psd2.security.Globals;

public interface IPlatniSrvc {

    SifraNamjeneDto getSifraNamjeneDtoBySifraNamjene(String sifraNamjene);

    NalogDevizniDto validateAndInitializeKupoprodaja(NalogDevizniDto devizniNalogDto, Globals globals);

    NalogDevizniDto createKupoprodaja(NalogDevizniDto devizniNalogDto, Globals globals);

    NalogSepaDto forwardToInstaPay(NalogSepaDto sepaDto, Globals globals);

}
