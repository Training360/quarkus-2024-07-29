package employees;

import io.quarkus.vertx.http.runtime.HttpConfiguration;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;
import java.net.URISyntaxException;

public class UriBuilderFactory {

    private final URI rootUri;

    public UriBuilderFactory(HttpConfiguration httpConf) {
        try {
            if (httpConf.ssl.certificate.files.isPresent()) {
                rootUri = new URI("https://" + httpConf.host +
                        (httpConf.sslPort == 443 ? "" : ":" + httpConf.sslPort)
                        + "/");
            } else {
                rootUri = new URI("http://" + httpConf.host +
                        (httpConf.sslPort == 80 ? "" : ":" + httpConf.port)
                        + "/");
            }
        } catch (URISyntaxException e) {
            throw new RuntimeException("Can not create root uri", e);
        }
    }

    @Produces
    public UriBuilder getUriBuilder() {
        return UriBuilder.fromUri(rootUri);
    }
}
