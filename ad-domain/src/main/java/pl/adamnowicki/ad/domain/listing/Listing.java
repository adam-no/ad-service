package pl.adamnowicki.ad.domain.listing;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

import static pl.adamnowicki.ad.domain.listing.Listing.PublicationStatus.INACTIVE;

@Value
@Builder
public class Listing {

  ListingId id;
  String title;
  String description;
  BigDecimal price;
  @Builder.Default
  PublicationStatus status = INACTIVE;

  public enum PublicationStatus {
    ACTIVE,
    INACTIVE
  }

  public ListingBuilder asBuilder() {
    return Listing.builder()
        .id(this.id)
        .title(this.title)
        .description(this.description)
        .price(this.price)
        .status(this.status);
  }
}

