package pl.adamnowicki.ad.primaryadapter.restapi;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
class CreateListingRequest {

  String title;
  String description;
  BigDecimal price;
  String ownerName;
}
