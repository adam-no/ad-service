package pl.adamnowicki.ad.domain.owner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.adamnowicki.ad.domain.listing.ListingId;

import static pl.adamnowicki.ad.domain.owner.OwnerName.of;

@Slf4j
@RequiredArgsConstructor
public class AttachNewListingCommandHandler {

  private final ForManipulatingOwner forManipulatingOwner;

  public void handle(AttachNewListingCommand attachNewListingCommand) {
    log.debug("AttachNewListingCommand started, attachNewListingCommand={}", attachNewListingCommand);
    OwnerName ownerName = of(attachNewListingCommand.getOwnerName());
    Owner owner = forManipulatingOwner.getByName(ownerName)
        .orElseThrow(() -> new InvalidAttachNewListingCommandException(
            "Specified owner name does not exists, ownerName=" + ownerName));

    ListingId listingId = attachNewListingCommand.getListingId();
    owner.getListings().add(listingId);

    forManipulatingOwner.storeOwner(owner);
    log.info("AttachNewListingCommand completed, attachNewListingCommand={}", attachNewListingCommand);
  }
}
