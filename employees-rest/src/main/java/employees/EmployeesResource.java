package employees;

import io.quarkus.logging.Log;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import training.api.EmployeesApi;
import training.dto.EmployeeDto;

import java.net.URI;
import java.util.List;
import java.util.Optional;

public class EmployeesResource implements EmployeesApi {

    private EmployeesService employeesService;

    public EmployeesResource(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @Override
    public EmployeeDto findEmployeeById(Long id) {
        return employeesService.findEmployeeById(id);
    }

    @Override
    public List<EmployeeDto> listEmployees(String namePrefix, String requestId) {
        Log.infof("Parameters: %s, %s", namePrefix, requestId);
        return employeesService.getEmployees(Optional.ofNullable(namePrefix));
    }

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        return employeesService.create(employeeDto);
    }

    @Override
    public EmployeeDto updateEmployee(Long id, EmployeeDto employeeDto) {
        return null;
    }
}
