package pl.adamnowicki.ad.app.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.adamnowicki.ad.domain.CreateOwnerCommandHandler;
import pl.adamnowicki.ad.domain.ForManipulatingOwner;
import pl.adamnowicki.ad.domain.OwnerQuery;

@Configuration
public class DomainConfiguration {

  @Bean
  OwnerQuery ownerQuery(ForManipulatingOwner forManipulatingOwner) {
    return new OwnerQuery(forManipulatingOwner);
  }

  @Bean
  CreateOwnerCommandHandler createOwnerCommandHandler(ForManipulatingOwner forManipulatingOwner) {
    return new CreateOwnerCommandHandler(forManipulatingOwner);
  }
}
