package pl.adamnowicki.ad.domain.listing;

import lombok.Builder;
import lombok.Value;

import static pl.adamnowicki.ad.domain.listing.Listing.ListingStatus.INACTIVE;

@Value
@Builder
public class Listing {

  ListingId id;
  String content;
  @Builder.Default
  ListingStatus status = INACTIVE;

  public enum ListingStatus {
    ACTIVE,
    INACTIVE
  }

  public ListingBuilder asBuilder() {
    return Listing.builder()
        .id(this.id)
        .content(this.content)
        .status(this.status);
  }
}

