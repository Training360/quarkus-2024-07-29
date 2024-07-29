package employees;

import training.api.EmployeesApi;
import training.dto.EmployeeDto;

import java.util.List;

public class EmployeesResource implements EmployeesApi {

    @Override
    public List<EmployeeDto> listEmployees() {
        return List.of(
                new EmployeeDto().id(1L).name("John Doe"),
                new EmployeeDto().id(2L).name("Jane Doe")
        );
    }
}
