package training;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class ExampleResource {

    private ExampleConfig exampleConfig;

    public ExampleResource(ExampleConfig exampleConfig) {
        this.exampleConfig = exampleConfig;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return exampleConfig.message();
    }
}
