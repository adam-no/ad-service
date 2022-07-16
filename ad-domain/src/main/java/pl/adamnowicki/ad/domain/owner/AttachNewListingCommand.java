package pl.adamnowicki.ad.domain.owner;

import lombok.Builder;
import lombok.Value;
import pl.adamnowicki.ad.domain.listing.ListingId;

@Value
@Builder
public class AttachNewListingCommand {

  ListingId listingId;
  String ownerName;
}
