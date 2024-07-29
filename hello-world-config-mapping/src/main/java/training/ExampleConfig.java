package training;

import io.smallrye.common.constraint.NotNull;
import io.smallrye.config.ConfigMapping;
//import io.smallrye.config.WithDefault;
//import io.smallrye.config.WithName;

@ConfigMapping(prefix = "example")
public interface ExampleConfig {

//    @WithName("welcome")
//    @WithDefault("Hello default")
    String message();
}
