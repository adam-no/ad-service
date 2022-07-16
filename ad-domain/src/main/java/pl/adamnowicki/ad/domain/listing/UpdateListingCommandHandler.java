package pl.adamnowicki.ad.domain.listing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class UpdateListingCommandHandler {

  private final ForManipulatingListing forManipulatingListing;

  public void handle(UpdateListingCommand updateListingCommand) {
    log.debug("UpdateListingCommandHandler started, updateListingCommand={}", updateListingCommand);

    ListingId listingId = updateListingCommand.getListingId();
    Listing updatedListing = forManipulatingListing.getById(listingId)
        .orElseThrow(InvalidUpdateListingCommandException::new)
        .asBuilder()
        .content(updateListingCommand.getContent())
        .build();

    forManipulatingListing.storeListing(updatedListing);

    log.info("UpdateListingCommandHandler completed, updateListingCommand={}", updateListingCommand);
  }
}
