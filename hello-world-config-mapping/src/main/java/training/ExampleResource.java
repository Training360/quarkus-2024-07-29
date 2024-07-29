package training;

import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class ExampleResource {

    private final ExampleService exampleService;

    private final Event<HelloEvent> event;

    // Konstruktor injection
    // 1. ekkor van kódgenerálás, @Inject esetén reflection van (Quarkus nem szeret)
    //   ez az aspektus csak a private fieldre vonatkozik
    // 2. Így final lehet a mező
    // 3. unit teszteléskor keretrendszer nélkül tesztelhető
    //  var resource = new ExampleResource(mock(ExampleService.class));
    // 4. kicsit jobban fáj a fejlesztőnek sok (5-nél több) függőséget felvennie
    public ExampleResource(ExampleService exampleService, Event<HelloEvent> event) {
        this.exampleService = exampleService;
        this.event = event;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        event.fire(new HelloEvent("Hello World"));
        return exampleService.hello();
    }
}
