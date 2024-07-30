package employees;

import io.quarkus.logging.Log;
import training.api.EmployeesApi;
import training.dto.EmployeeDto;

import java.util.List;
import java.util.Optional;

public class EmployeesResource implements EmployeesApi {

    private EmployeesService employeesService;

    public EmployeesResource(EmployeesService employeesService) {
        this.employeesService = employeesService;
    }

    @Override
    public List<EmployeeDto> listEmployees(String namePrefix, String requestId) {
        Log.infof("Parameters: %s, %s", namePrefix, requestId);
        return employeesService.getEmployees(Optional.ofNullable(namePrefix));
    }
}
