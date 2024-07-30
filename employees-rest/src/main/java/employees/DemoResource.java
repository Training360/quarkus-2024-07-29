package employees;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import training.dto.EmployeeDto;

import java.net.URI;

@Path("/demo/employees")
public class DemoResource {

    @POST
    public Response createEmployeeDemo(EmployeeDto employeeDto) {
        return
                Response.created(URI.create("/api/employees/3"))
                        .entity(
                                new EmployeeDto().id(1L).name("John Wick"))
                        .build();
    }
}
