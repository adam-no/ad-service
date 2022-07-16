package pl.adamnowicki.ad.domain.listing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.adamnowicki.ad.domain.owner.AttachNewListingCommand;
import pl.adamnowicki.ad.domain.owner.AttachNewListingCommandHandler;

@Slf4j
@RequiredArgsConstructor
public class CreateListingCommandHandler {

  private final AttachNewListingCommandHandler attachNewListingCommandHandler;

  private final ForManipulatingListing forManipulatingListing;

  public void handle(CreateListingCommand createListingCommand) {
    log.debug("CreateListingCommandHandler started, createListingCommand={}", createListingCommand);
    ListingId listingId = ListingId.generate();

    Listing newListing = Listing.builder()
        .id(listingId)
        .content(createListingCommand.getContent())
        .build();

    AttachNewListingCommand attachNewListingCommand = AttachNewListingCommand.builder()
        .listingId(listingId)
        .ownerName(createListingCommand.getOwnerName())
        .build();
    attachNewListingCommandHandler.handle(attachNewListingCommand);

    forManipulatingListing.storeListing(newListing);
    log.info("CreateListingCommandHandler completed, createListingCommand={}", createListingCommand);
  }
}
