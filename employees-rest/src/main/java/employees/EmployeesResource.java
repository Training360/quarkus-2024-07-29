package employees;

import io.quarkus.logging.Log;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import training.api.EmployeesApi;
import training.dto.EmployeeDto;

import java.net.URI;
import java.util.List;
import java.util.Optional;

public class EmployeesResource implements EmployeesApi {

    private EmployeesService employeesService;

    private UriBuilder uriBuilder;

    public EmployeesResource(EmployeesService employeesService, UriBuilder uriBuilder) {
        this.employeesService = employeesService;
        this.uriBuilder = uriBuilder;
    }

    @Override
    public Response findEmployeeById(Long id) {
        return Response.ok(employeesService.findEmployeeById(id)).build();
    }

    @Override
    public Response listEmployees(String namePrefix, String requestId) {
        Log.infof("Parameters: %s, %s", namePrefix, requestId);
        return Response.ok(employeesService.getEmployees(Optional.ofNullable(namePrefix))).build();
    }

    @Override
    public Response createEmployee(EmployeeDto employeeDto) {
        var employee = employeesService.create(employeeDto);
        return Response
                .created(uriBuilder.path("/api/employees/{employeeId}").build(employee.getId()))
                .entity(employee)
                .build();
    }

    @Override
    public Response updateEmployee(Long id, EmployeeDto employeeDto) {
        if (!id.equals(employeeDto.getId())) {
            throw new IllegalArgumentException("Employee id (%d) must be equal to id (%d)".formatted(id, employeeDto.getId()));
        }
        return Response.ok(employeesService.updateEmployee(employeeDto)).build();
    }
}
