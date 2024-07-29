package training;

import io.quarkus.logging.Log;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;
//import jakarta.inject.Singleton;

@ApplicationScoped // proxyzott és lazy, ez a javasolt
//@Singleton // nem proxyzott és eager
public class ExampleService {

    private final ExampleConfig exampleConfig;

    @PostConstruct
    public void logBeanCreation() {
        Log.infof("Bean creation: %s", LocalDateTime.now().toString());
    }

    public ExampleService(ExampleConfig exampleConfig) {
        this.exampleConfig = exampleConfig;
    }

    public String hello() {
        return exampleConfig.message();
    }
}
