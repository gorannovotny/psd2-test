package hr.abc.psd2.config;

import io.quarkus.arc.config.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import java.util.Map;

@ConfigProperties(prefix = "app.psd2")
public interface AppConfig {

    @ConfigProperty(name = "currency-map")
    Map<String, String> currencyMap();

    @ConfigProperty(name = "tpp.frequency-per-day")
    Integer tppFrequencyPerday();

    @ConfigProperty(name = "tpp.redirect-link")
    String psd2RedirectLink();

    @ConfigProperty(name = "vbdi")
    String vbdi();

    @ConfigProperty(name = "qwac.use")
    String qwacUse();

    @ConfigProperty(name = "qseal.use")
    String qwacSeal();

    @ConfigProperty(name = "x-req-id.use")
    String xReqIdUse();

    @ConfigProperty(name = "sign-response")
    String signResponse();
}
