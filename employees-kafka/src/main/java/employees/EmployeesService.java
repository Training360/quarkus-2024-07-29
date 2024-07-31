package employees;

import io.smallrye.reactive.messaging.MutinyEmitter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import training.dto.EmployeeDto;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

@ApplicationScoped
public class EmployeesService {

    private final EmployeesRepository employeesRepository;

    @Channel("employees-event")
    private MutinyEmitter<EmployeeHasBeenCreatedEvent> eventEmitter;

    public EmployeesService(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    private static EmployeeDto toDto(Employee entity) {
        return new EmployeeDto().id(entity.getId()).name(entity.getName());
    }

    public List<EmployeeDto> getEmployees(Optional<String> prefix) {
        // TODO Prefix
        return employeesRepository.findAll()
                .stream()
                .map(EmployeesService::toDto)
                .toList();
    }

    public EmployeeDto findEmployeeById(Long id) {
        return employeesRepository.findByIdOptional(id)
                .map(EmployeesService::toDto)
                .orElseThrow(notFound(id));
    }

    public Supplier<NotFoundException> notFound(long id) {
        return () -> new NotFoundException("Employee with id %d not found".formatted(id));
    }

    @Transactional
    public EmployeeDto create(EmployeeDto employee) {
        var entity = new Employee(employee.getName());
        employeesRepository.persist(entity);

        eventEmitter.sendAndAwait(new EmployeeHasBeenCreatedEvent(entity.getId(), employee.getName()));

        return toDto(entity);
    }

    @Transactional
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        // Különösen akkor okozhat problémát, ha sokáig tart???
        var entity = employeesRepository.findByIdOptional(employeeDto.getId())
                .orElseThrow(notFound(employeeDto.getId()));
        entity.setName(employeeDto.getName());

        /// ??? nyitott tranzakción belül kerül-e meghívásra...
        return toDto(entity);
    }

    public void deleteEmployee(Long id) {
        employeesRepository.deleteById(id);
    }
}
