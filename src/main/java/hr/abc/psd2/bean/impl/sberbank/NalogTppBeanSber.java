package hr.abc.psd2.bean.impl.sberbank;

import static hr.abc.psd2.bean.impl.ServiceConstants.SERVICE_SBER;
import static hr.abc.psd2.bean.impl.ServiceConstants.TRUE;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import hr.abc.psd2.bean.INalogTppBean;
import hr.abc.psd2.bean.impl.AbstractNalogTppBean;
import hr.abc.psd2.model.dto.core.NalogDevizniDto;
import hr.abc.psd2.model.dto.core.NalogPlatniDto;
import hr.abc.psd2.model.dto.core.NalogSepaDto;
import hr.abc.psd2.model.dto.pis.TppNalogPlatniDto;
import hr.abc.psd2.security.Globals;
import hr.sberbank.resources.PaymentRestSrvc;
import io.quarkus.arc.properties.IfBuildProperty;

@Dependent
@IfBuildProperty(name = SERVICE_SBER, stringValue = TRUE)
public class NalogTppBeanSber extends AbstractNalogTppBean implements INalogTppBean, Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    @RestClient
    PaymentRestSrvc paymentRestSrvc;


    @Override
    protected void createNalogFromChangedTppNalog(TppNalogPlatniDto tppNalogPlatniDto) {

    }

    @Override
    protected NalogDevizniDto validateAndInitializeKupoprodaja(NalogDevizniDto devizniNalogDto, Globals globals) {
        return null;
    }

    @Override
    public TppNalogPlatniDto getTppNalogBySifra(Integer paymentId) {
        return null;
    }

    @Override
    protected List<NalogPlatniDto> resolvePainDatoteka(byte[] podaciDatoteke) {
        return null;
    }

    @Override
    protected List<NalogSepaDto> pripremaNaloga(List<NalogSepaDto> nalogSepaDtos, Globals globals) {
        return null;
    }
}
