package pl.adamnowicki.ad.primaryadapter.restapi;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Value
@Builder
class UpdateListingRequest {

  String title;
  String description;
  BigDecimal price;
}
