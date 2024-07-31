package employees;

import com.tietoevry.quarkus.resteasy.problem.HttpProblem;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

import java.net.URI;
import java.util.UUID;

public class GlobalExceptionHandler {

    @ServerExceptionMapper
    public Response handle(NotFoundException e) {
        return Response
                .status(Response.Status.NOT_FOUND)
                .entity(HttpProblem.builder()
                        .withTitle("Not found")
                        .withDetail(e.getMessage())
                        .withStatus(Response.Status.NOT_FOUND)
                        .withType(URI.create("http://training360.com/errors/not-found"))
                        .with("error-id", UUID.randomUUID().toString())
                        .build()
                )
                .header("Content-Type", "application/problem+json")
                .build();
    }
}
