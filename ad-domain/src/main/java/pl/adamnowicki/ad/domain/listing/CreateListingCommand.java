package pl.adamnowicki.ad.domain.listing;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
public class CreateListingCommand {

  String title;
  String description;
  BigDecimal price;
}
