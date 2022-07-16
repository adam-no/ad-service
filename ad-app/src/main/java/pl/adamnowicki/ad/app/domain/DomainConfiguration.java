package pl.adamnowicki.ad.app.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.adamnowicki.ad.domain.listing.CreateListingCommandHandler;
import pl.adamnowicki.ad.domain.listing.ForManipulatingListing;
import pl.adamnowicki.ad.domain.listing.ListingQuery;
import pl.adamnowicki.ad.domain.listing.UpdateListingCommandHandler;
import pl.adamnowicki.ad.domain.owner.AttachNewListingCommandHandler;
import pl.adamnowicki.ad.domain.owner.CreateOwnerCommandHandler;
import pl.adamnowicki.ad.domain.owner.ForManipulatingOwner;
import pl.adamnowicki.ad.domain.owner.OwnerQuery;

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

  @Bean
  ListingQuery listingQuery(ForManipulatingListing forManipulatingListing) {
    return new ListingQuery(forManipulatingListing);
  }

  @Bean
  CreateListingCommandHandler createListingCommandHandler(
      AttachNewListingCommandHandler attachNewListingCommandHandler,
      ForManipulatingListing forManipulatingListing) {

    return new CreateListingCommandHandler(
        attachNewListingCommandHandler,
        forManipulatingListing);
  }

  @Bean
  AttachNewListingCommandHandler attachNewListingCommandHandler(ForManipulatingOwner forManipulatingOwner) {
    return new AttachNewListingCommandHandler(forManipulatingOwner);
  }

  @Bean
  UpdateListingCommandHandler updateListingCommandHandler(ForManipulatingListing forManipulatingListing) {
    return new UpdateListingCommandHandler(forManipulatingListing);
  }
}
