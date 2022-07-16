package pl.adamnowicki.ad.domain.listing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CreateListingCommandHandler {

  private final ForManipulatingListing forManipulatingListing;

  public ListingId handle(CreateListingCommand createListingCommand) {
    log.debug("CreateListingCommandHandler started, createListingCommand={}", createListingCommand);
    ListingId listingId = ListingId.generate();

    Listing newListing = Listing.builder()
        .id(listingId)
        .content(createListingCommand.getContent())
        .build();

    forManipulatingListing.storeListing(newListing);
    log.info("CreateListingCommandHandler completed, createListingCommand={}, listingId={}",
        createListingCommand, listingId);

    return listingId;
  }
}
