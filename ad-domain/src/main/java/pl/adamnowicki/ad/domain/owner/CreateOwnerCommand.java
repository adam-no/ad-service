package pl.adamnowicki.ad.domain.owner;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateOwnerCommand {

  String name;
}
