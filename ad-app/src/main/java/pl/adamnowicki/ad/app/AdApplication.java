package pl.adamnowicki.ad.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.adamnowicki.ad.primaryadapter.restapi.RestApiWebUiConfiguration;
import pl.adamnowicki.ad.app.secondaryadapter.inmemorydb.InMemoryDbConfiguration;
import pl.adamnowicki.ad.app.domain.DomainConfiguration;

@SpringBootApplication(scanBasePackageClasses = {
    DomainConfiguration.class,
    RestApiWebUiConfiguration.class,
    InMemoryDbConfiguration.class
})
public class AdApplication {

  public static void main(String[] args) {
    SpringApplication.run(AdApplication.class, args);
  }
}
