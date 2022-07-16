package pl.adamnowicki.ad.domain.listing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ModifyPublicationStatusCommandHandler {

  private final ForManipulatingListing forManipulatingListing;

  public void handle(ModifyPublicationStatusCommand modifyPublicationStatusCommand) {
    log.debug("ModifyPublicationStatusCommand started, modifyPublicationStatusCommand={}",
        modifyPublicationStatusCommand);

    ListingId listingId = modifyPublicationStatusCommand.getListingId();
    Listing updatedListing = forManipulatingListing.getById(listingId)
        .orElseThrow(InvalidUpdateListingCommandException::new)
        .asBuilder()
        .status(modifyPublicationStatusCommand.getPublicationStatus())
        .build();

    forManipulatingListing.storeListing(updatedListing);

    log.info("ModifyPublicationStatusCommand completed, modifyPublicationStatusCommand={}",
        modifyPublicationStatusCommand);
  }
}
