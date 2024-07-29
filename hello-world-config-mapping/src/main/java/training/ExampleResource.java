package training;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/hello")
public class ExampleResource {

    private String welcomeMessage;

    public ExampleResource(@ConfigProperty(name = "example.message") String welcomeMessage) {
        this.welcomeMessage = welcomeMessage;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return welcomeMessage;
    }
}
