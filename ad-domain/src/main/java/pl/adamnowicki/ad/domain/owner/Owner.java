package pl.adamnowicki.ad.domain.owner;

import lombok.Builder;
import lombok.Value;
import pl.adamnowicki.ad.domain.listing.ListingId;

import java.util.List;

@Value
@Builder
public class Owner {

  OwnerName name;
  @Builder.Default
  List<ListingId> listings = List.of();
}
