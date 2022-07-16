package pl.adamnowicki.ad.primaryadapter.restapi;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.adamnowicki.ad.domain.listing.*;
import pl.adamnowicki.ad.primaryadapter.restapi.ListingsDto.ListingDto;

import java.util.UUID;

import static pl.adamnowicki.ad.domain.listing.ListingId.of;
import static pl.adamnowicki.ad.primaryadapter.restapi.RestApiWebUiConfiguration.ROOT_V1;

@RestController
@RequestMapping(ROOT_V1 + "/listings")
@RequiredArgsConstructor
public class ListingController {

  private final ListingQuery listingQuery;
  private final CreateListingCommandHandler createListingCommandHandler;

  private final UpdateListingCommandHandler updateListingCommandHandler;

  @GetMapping()
  ListingsDto listListings() {
    var listingDtos = listingQuery.listAllListings().stream()
        .map(listing -> ListingDto.builder()
            .id(listing.getId().unwrap())
            .content(listing.getContent())
            .build())
        .toList();
    return ListingsDto.builder()
        .listings(listingDtos)
        .build();
  }

  @PostMapping()
  void createListing(@RequestBody CreateListingCommand createListingCommand) {
    createListingCommandHandler.handle(createListingCommand);
  }

  @GetMapping("/{id}")
  ResponseEntity<?> getSingleListing(@PathVariable("id") UUID listingId) {
    return listingQuery.getById(of(listingId))
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/{id}")
  void updateSingleListing(
      @PathVariable("id") UUID listingId,
      UpdateListingCommand updateListingCommand) {

    updateListingCommandHandler.handle(of(listingId), updateListingCommand);
  }
}
