package pl.adamnowicki.ad.primaryadapter.restapi;

import lombok.Builder;
import lombok.Value;
import pl.adamnowicki.ad.domain.listing.Listing;

import java.math.BigDecimal;
import java.util.List;

@Value
@Builder
public class ListingsDto {

  @Builder.Default
  List<ListingDto> listings = List.of();

  @Value
  @Builder
  public static class ListingDto {

    String id;
    String title;
    String description;
    BigDecimal price;
    Listing.PublicationStatus publicationStatus;
  }
}
