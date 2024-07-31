package employees;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmployeesRepository implements PanacheRepositoryBase<Employee, Long> {
}
