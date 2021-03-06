package pl.adamnowicki.ad.domain.listing;

import lombok.Builder;
import lombok.Value;
import pl.adamnowicki.ad.domain.listing.Listing.PublicationStatus;

@Value
@Builder
public class ModifyPublicationStatusCommand {

  ListingId listingId;
  PublicationStatus publicationStatus;
}
