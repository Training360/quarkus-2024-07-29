package employees;

import io.quarkus.logging.Log;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@QuarkusMain
public class EmployeeClientApplication implements QuarkusApplication {

    private final EmployeesResource employeesResource;

    public EmployeeClientApplication(@RestClient EmployeesResource employeesResource) {
        this.employeesResource = employeesResource;
    }

    @Override
    public int run(String... args) throws Exception {
        Log.info("Hello command line client");

        var employees = employeesResource.findAll();
        Log.infof("Employees: %s", employees.toString());

        return 0;
    }
}
