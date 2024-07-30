package employees;

import jakarta.enterprise.context.ApplicationScoped;
import training.dto.EmployeeDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class EmployeesService {

    private List<EmployeeDto> employees = Collections.synchronizedList(
            new ArrayList<>(
            List.of(
                    new EmployeeDto().id(1L).name("John Doe"),
                    new EmployeeDto().id(2L).name("Jane Doe")
            )
            )
    );

    public List<EmployeeDto> getEmployees(Optional<String> prefix) {
        return employees
                .stream()
                .filter(employee ->prefix.isEmpty() || employee.getName().startsWith(prefix.get()))
                .toList()
                ;
    }
}
