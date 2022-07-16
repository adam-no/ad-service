package pl.adamnowicki.ad.primaryadapter.restapi;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
class CreateListingRequest {

  String content;
  String ownerName;
}
