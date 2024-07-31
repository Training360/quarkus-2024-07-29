package employees;

import io.quarkus.logging.Log;
import jakarta.ws.rs.container.ContainerRequestContext;
import org.jboss.resteasy.reactive.server.ServerRequestFilter;

import java.io.ByteArrayOutputStream;

public class Filters {

    @ServerRequestFilter
    public void logRequestId(ContainerRequestContext requestContext){
        Log.debugf("RequestId: %s", requestContext.getHeaderString("RequestId"));

//        try  {
//            var output = new ByteArrayOutputStream();
//            requestContext.getEntityStream().transferTo(output);
//            Log.debugf("Response: %s", output.toString());
//        }
//        catch (Exception e){
//            throw new RuntimeException("Can not log", e);
//        }
    }
}
