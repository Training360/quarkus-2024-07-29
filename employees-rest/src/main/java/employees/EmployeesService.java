package employees;

import jakarta.enterprise.context.ApplicationScoped;
import training.dto.EmployeeDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class EmployeesService {

    private AtomicLong sequence = new AtomicLong();

    private List<Employee> employees = Collections.synchronizedList(
            new ArrayList<>(
            List.of(
                    new Employee(sequence.incrementAndGet(), "Jack Doe"),
                    new Employee(sequence.incrementAndGet(), "Jane Doe")
            )
            )
    );

    private static EmployeeDto toDto(Employee entity) {
        return new EmployeeDto().id(entity.getId()).name(entity.getName());
    }

    public List<EmployeeDto> getEmployees(Optional<String> prefix) {
        return employees
                .stream()
                .filter(employee ->prefix.isEmpty() || employee.getName().startsWith(prefix.get()))
                .map(EmployeesService::toDto)
                .toList()
                ;
    }

    public EmployeeDto findEmployeeById(Long id) {
        return employees
                .stream()
                .filter(employeeDto -> employeeDto.getId().equals(id))
                .findAny()
                .map(EmployeesService::toDto)
                .orElseThrow(() -> new NotFoundException("Employee with id %d not found".formatted(id)));
    }

    public EmployeeDto create(EmployeeDto employee) {
        var entity = new Employee(sequence.incrementAndGet(), employee.getName());
        employees.add(entity);
        return toDto(entity);
    }
}
