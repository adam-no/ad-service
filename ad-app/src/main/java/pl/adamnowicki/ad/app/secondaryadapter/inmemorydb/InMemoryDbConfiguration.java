package pl.adamnowicki.ad.app.secondaryadapter.inmemorydb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.adamnowicki.ad.domain.ForManipulatingOwner;
import pl.adamnowicki.ad.secondaryadapter.inmemorydb.ForManipulatingOwnerAdapter;

@Configuration
public class InMemoryDbConfiguration {

  @Bean
  ForManipulatingOwner forManipulatingOwner() {
    return new ForManipulatingOwnerAdapter();
  }
}
