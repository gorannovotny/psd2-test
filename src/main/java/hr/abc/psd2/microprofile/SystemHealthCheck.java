package hr.abc.psd2.microprofile;

import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

@Readiness
@ApplicationScoped
public class SystemHealthCheck implements HealthCheck {

	@ConfigProperty(name = "service.name")
	String serviceName;
	
//	@Inject
//	InventoryConfig config;
	
	public boolean isHealthy() {
//	    if (config.isInMaintenance()) {
//	      return false;
//	    }
//	    try {
//	      String url = InventoryUtils.buildUrl("http", "localhost", config.getPortNumber(),
//	          "/system/properties");
//	      Client client = ClientBuilder.newClient();
//	      Response response = client.target(url).request(MediaType.APPLICATION_JSON).get();
//	      if (response.getStatus() != 200) {
//	        return false;
//	      }
//	      return true;
//	    } catch (Exception e) {
//	      return false;
//	    }
	    return true;
	  }
	
	@Override
	public HealthCheckResponse call() {
		if (!isHealthy()) {
		      return HealthCheckResponse.named(serviceName + " Inventory Readiness Check Ivana")
		          .down()
		          .build();
		} else {
		    return HealthCheckResponse.named(serviceName + " Inventory Readiness Check Ivana")
		    		.up()
		    		.build();
		}
	}

}
