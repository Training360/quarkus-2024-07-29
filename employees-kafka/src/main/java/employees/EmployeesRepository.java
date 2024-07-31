package employees;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import training.dto.EmployeeDto;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class EmployeesRepository implements PanacheRepositoryBase<Employee, Long> {

    public List<EmployeeDto> findAllDtoByPrefix(Optional<String> prefix) {
        return getEntityManager()
                .createQuery("select new EmployeeDto(e.id, e.name) from Employee e where e.name like :prefix", EmployeeDto.class)
                .setParameter("prefix", prefix.orElse("") + "%")
                .getResultList();
    }
}
