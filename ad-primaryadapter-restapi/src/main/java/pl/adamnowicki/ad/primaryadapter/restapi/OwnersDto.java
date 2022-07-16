package pl.adamnowicki.ad.primaryadapter.restapi;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OwnersDto {

  @Builder.Default
  List<OwnerDto> owners = List.of();

  @Data
  @Builder
  public static class OwnerDto {

    private String name;
  }
}
