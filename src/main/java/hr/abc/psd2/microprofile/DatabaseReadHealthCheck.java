package hr.abc.psd2.microprofile;

//import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

import io.agroal.api.AgroalDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Readiness
@ApplicationScoped
public class DatabaseReadHealthCheck implements HealthCheck {
	
	
	@Inject
	AgroalDataSource defaultDataSource;

	@Override
	public HealthCheckResponse call() {
		
		HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("Database read health check ");
		
		try {
            simulateDatabaseConnectionVerification();
            responseBuilder.up();
        } catch (IllegalStateException e) { 
            // cannot access the database
            responseBuilder.down();
        }

		return responseBuilder.build();
	}
	
	private void simulateDatabaseConnectionVerification() {
    
		try(Statement st = defaultDataSource.getConnection().createStatement();) {
           try(ResultSet res = st.executeQuery("SELECT 1 as igor")) {
               if(res.next() && res.getInt(1) != 1)
                   throw  new SQLException();
           }
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalStateException("Cannot contact database");
		}
        
    }

}
