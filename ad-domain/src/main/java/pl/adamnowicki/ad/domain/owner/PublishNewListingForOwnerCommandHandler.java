package pl.adamnowicki.ad.domain.owner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.adamnowicki.ad.domain.listing.*;

import java.util.Optional;

import static pl.adamnowicki.ad.domain.listing.Listing.PublicationStatus.ACTIVE;

@Slf4j
@RequiredArgsConstructor
public class PublishNewListingForOwnerCommandHandler {

  private final int maxActiveListingsPerOwner;
  private final ForManipulatingOwner forManipulatingOwner;

  private final ModifyPublicationStatusCommandHandler modifyPublicationStatusCommandHandler;
  private final ListingQuery listingQuery;

  public boolean handle(PublishNewListingForOwnerCommand publishNewListingForOwnerCommand) {
    log.debug("PublishNewListingForOwnerCommandHandler started, publishNewListingForOwnerCommand={}",
        publishNewListingForOwnerCommand);

    ListingId listingId = publishNewListingForOwnerCommand.getListingId();
    Owner owner = forManipulatingOwner.getByListingId(listingId)
        .orElseThrow(() -> new PublishNewListingForOwnerCommandException(
            "Listing is not linked to a known owner, listingId=" + listingId));

    if (getActiveListingCountForOwner(owner) >= maxActiveListingsPerOwner) {
      return fail(publishNewListingForOwnerCommand);
    }

    ModifyPublicationStatusCommand modifyPublicationStatusCommand = ModifyPublicationStatusCommand.builder()
        .publicationStatus(ACTIVE)
        .listingId(listingId)
        .build();
    modifyPublicationStatusCommandHandler.handle(modifyPublicationStatusCommand);

    if (getActiveListingCountForOwner(owner) > maxActiveListingsPerOwner) {
      ModifyPublicationStatusCommand revertPublicationStatusCommand = ModifyPublicationStatusCommand.builder()
          .publicationStatus(Listing.PublicationStatus.INACTIVE)
          .listingId(listingId)
          .build();
      modifyPublicationStatusCommandHandler.handle(revertPublicationStatusCommand);

      return fail(publishNewListingForOwnerCommand);
    }

    log.info("PublishNewListingForOwnerCommandHandler completed, publishNewListingForOwnerCommand={}",
        publishNewListingForOwnerCommand);
    return true;
  }

  private boolean fail(PublishNewListingForOwnerCommand publishNewListingForOwnerCommand) {
    log.info("PublishNewListingForOwnerCommandHandler could not be completed because the limit of active listings " +
            "has been reached, attachNewListingCommand={}, maxActiveListingsPerOwner={}",
        publishNewListingForOwnerCommand, maxActiveListingsPerOwner);
    return false;
  }
  private long getActiveListingCountForOwner(Owner owner) {
    return owner.getListings().stream()
        .map(listingQuery::getById)
        .flatMap(Optional::stream)
        .filter(listing -> ACTIVE.equals(listing.getStatus()))
        .count();
  }
}
