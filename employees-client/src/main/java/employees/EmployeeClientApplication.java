package employees;

import io.quarkus.logging.Log;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

@QuarkusMain
public class EmployeeClientApplication implements QuarkusApplication {

    @Override
    public int run(String... args) throws Exception {
        Log.info("Hello command line client");

        return 0;
    }
}
