package pl.adamnowicki.ad.domain.listing;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UpdateListingCommand {

  String content;
}
