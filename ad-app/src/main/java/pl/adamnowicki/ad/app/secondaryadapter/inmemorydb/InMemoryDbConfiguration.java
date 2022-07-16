package pl.adamnowicki.ad.app.secondaryadapter.inmemorydb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.adamnowicki.ad.domain.listing.ForManipulatingListing;
import pl.adamnowicki.ad.domain.owner.ForManipulatingOwner;
import pl.adamnowicki.ad.secondaryadapter.inmemorydb.ForManipulatingListingAdapter;
import pl.adamnowicki.ad.secondaryadapter.inmemorydb.ForManipulatingOwnerAdapter;

@Configuration
public class InMemoryDbConfiguration {

  @Bean
  ForManipulatingOwner forManipulatingOwner() {
    return new ForManipulatingOwnerAdapter();
  }

  @Bean
  ForManipulatingListing forManipulatingListing() {
    return new ForManipulatingListingAdapter();
  }
}
