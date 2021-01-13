package hr.abc.psd2;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ApplicationScoped
public class AppLifecycleBean {

	@ConfigProperty(name = "quarkus.datasource.jdbc.url")
	String dbUrl;

	void onStart(@Observes StartupEvent ev) {
		log.info("The application is starting with profile {}", ProfileManager.getActiveProfile());
		log.info("Connectiong to database URL={}", dbUrl);
	}

	void onStop(@Observes ShutdownEvent ev) {
		log.info("The application is stopping with profile {}", ProfileManager.getActiveProfile());
	}
}
