package hr.abc.psd2.microprofile;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.health.Liveness;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

@Liveness
@ApplicationScoped
public class MemoryCheck implements HealthCheck {
	
	@ConfigProperty(name = "service.name")
	String serviceName;

	@Override
	public HealthCheckResponse call() {
		MemoryMXBean memBean = ManagementFactory.getMemoryMXBean();
	    long memUsed = memBean.getHeapMemoryUsage().getUsed();
	    long memMax = memBean.getHeapMemoryUsage().getMax();

	    return HealthCheckResponse.named(serviceName + " System Liveness Check Ivana")
	                              .withData("memory used", memUsed)
	                              .withData("memory max", memMax)
	                              .state(memUsed < memMax * 0.9).build();
	}
	
	

}
