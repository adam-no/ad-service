package pl.adamnowicki.ad.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateOwnerCommand {

  String name;
}
