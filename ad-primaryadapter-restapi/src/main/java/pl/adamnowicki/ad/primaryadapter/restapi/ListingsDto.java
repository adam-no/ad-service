package pl.adamnowicki.ad.primaryadapter.restapi;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ListingsDto {

  @Builder.Default
  List<ListingDto> listings = List.of();

  @Data
  @Builder
  public static class ListingDto {

    private String id;
    private String content;
  }
}
