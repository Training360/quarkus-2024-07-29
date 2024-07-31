package employees;

import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/api/hello")
public class HelloResource {

    @GET
    @Blocking
    public Uni<String> hello() {
        return Uni.createFrom().item("Hello")
                .log()
                .map(String::toUpperCase);
    }
}
