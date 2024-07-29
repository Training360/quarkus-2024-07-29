package training;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;

@ApplicationScoped
public class HelloEventHandler {

    public void handleEvent(@Observes HelloEvent event) {
        Log.info("Received HelloEvent: " + event);
    }
}
