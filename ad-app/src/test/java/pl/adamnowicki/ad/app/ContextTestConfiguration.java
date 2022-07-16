package pl.adamnowicki.ad.app;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import pl.adamnowicki.ad.app.domain.DomainConfiguration;
import pl.adamnowicki.ad.app.secondaryadapter.inmemorydb.InMemoryDbConfiguration;
import pl.adamnowicki.ad.primaryadapter.restapi.RestApiWebUiConfiguration;

@EnableAutoConfiguration
@ComponentScan(basePackageClasses = {
    DomainConfiguration.class,
    RestApiWebUiConfiguration.class,
    InMemoryDbConfiguration.class
})
public class ContextTestConfiguration {
}
