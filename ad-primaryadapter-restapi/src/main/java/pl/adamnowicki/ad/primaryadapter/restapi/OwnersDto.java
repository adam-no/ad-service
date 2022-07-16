package pl.adamnowicki.ad.primaryadapter.restapi;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class OwnersDto {

  @Builder.Default
  List<OwnerDto> owners = List.of();

  @Value
  @Builder
  public static class OwnerDto {

    String name;
  }
}
