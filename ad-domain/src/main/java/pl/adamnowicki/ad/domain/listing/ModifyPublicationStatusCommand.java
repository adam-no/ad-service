package pl.adamnowicki.ad.domain.listing;

import lombok.Builder;
import lombok.Value;
import pl.adamnowicki.ad.domain.listing.Listing.ListingStatus;

@Value
@Builder
public class ModifyPublicationStatusCommand {

  ListingId listingId;
  ListingStatus listingStatus;
}
