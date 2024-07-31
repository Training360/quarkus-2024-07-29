package employees;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@ApplicationScoped
public class EmployeeesEventHandler {

    @Incoming("incoming-employees-event")
    public void handle(EmployeeHasBeenCreatedEvent event) {
        Log.infof("Event received: %s", event.toString());
    }
}
