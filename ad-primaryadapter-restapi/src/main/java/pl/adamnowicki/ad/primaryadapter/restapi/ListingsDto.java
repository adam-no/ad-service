package pl.adamnowicki.ad.primaryadapter.restapi;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class ListingsDto {

  @Builder.Default
  List<ListingDto> listings = List.of();

  @Value
  @Builder
  public static class ListingDto {

    String id;
    String content;
  }
}
