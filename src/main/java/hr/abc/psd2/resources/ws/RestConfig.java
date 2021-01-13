package hr.abc.psd2.resources.ws;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@OpenAPIDefinition(
	    tags = {
	            @Tag(name="DEV", description="Operations in develpoment"),
	            @Tag(name="TEST", description="Operations ready for test"),
	            @Tag(name="PROD", description="Operations ready for production")
	    },
	    info = @Info(
	        title="XS2A-PSD2 API",
	        version = "1.0.0",
	        contact = @Contact(
	            name = "API Support",
	            url = "https://www.sberbank.hr/kontakt/",
	            email = "info@sberbank.hr"),
	        license = @License(
	            name = "Creative Commons Attribution 4.0 International Public License",
	            url = "https://creativecommons.org/licenses/by/4.0/"))
	)
@ApplicationPath("/resources/psd2")
public class RestConfig extends Application {

}
