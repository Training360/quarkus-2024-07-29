package employees;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(configKey = "employees-api")
@Path("/api/employees")
public interface EmployeesResource {

    @GET
    List<Employee> findAll();
}
