package pl.adamnowicki.ad.app.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.adamnowicki.ad.domain.listing.*;
import pl.adamnowicki.ad.domain.owner.*;

@Configuration
public class DomainConfiguration {

  @Value("${ad.owner.max-active-listings}")
  int maxActiveListingsPerOwner;

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
  CreateListingCommandHandler createListingCommandHandler(ForManipulatingListing forManipulatingListing) {
    return new CreateListingCommandHandler(forManipulatingListing);
  }

  @Bean
  AttachNewListingCommandHandler attachNewListingCommandHandler(ForManipulatingOwner forManipulatingOwner) {
    return new AttachNewListingCommandHandler(forManipulatingOwner);
  }

  @Bean
  UpdateListingCommandHandler updateListingCommandHandler(ForManipulatingListing forManipulatingListing) {
    return new UpdateListingCommandHandler(forManipulatingListing);
  }

  @Bean
  ModifyPublicationStatusCommandHandler modifyPublicationStatusCommandHandler(
      ForManipulatingListing forManipulatingListing) {

    return new ModifyPublicationStatusCommandHandler(forManipulatingListing);
  }

  @Bean
  PublishNewListingForOwnerCommandHandler publishNewListingForOwnerCommandHandler(
      ForManipulatingOwner forManipulatingOwner,
      ModifyPublicationStatusCommandHandler modifyPublicationStatusCommandHandler,
      ListingQuery listingQuery) {

    return new PublishNewListingForOwnerCommandHandler(
        maxActiveListingsPerOwner,
        forManipulatingOwner,
        modifyPublicationStatusCommandHandler,
        listingQuery);
  }
}
