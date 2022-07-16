package pl.adamnowicki.ad.primaryadapter.restapi;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.adamnowicki.ad.domain.listing.*;
import pl.adamnowicki.ad.domain.owner.*;
import pl.adamnowicki.ad.primaryadapter.restapi.ListingsDto.ListingDto;

import java.util.UUID;

import static pl.adamnowicki.ad.domain.listing.Listing.PublicationStatus.INACTIVE;
import static pl.adamnowicki.ad.domain.listing.ListingId.of;
import static pl.adamnowicki.ad.primaryadapter.restapi.RestApiWebUiConfiguration.ROOT_V1;

@RestController
@RequestMapping(ROOT_V1 + "/listings")
@RequiredArgsConstructor
class ListingController {

  private final ListingQuery listingQuery;
  private final CreateListingCommandHandler createListingCommandHandler;
  private final UpdateListingCommandHandler updateListingCommandHandler;
  private final ModifyPublicationStatusCommandHandler modifyPublicationStatusCommandHandler;
  private final PublishNewListingForOwnerCommandHandler publishNewListingForOwnerCommandHandler;
  private final AttachNewListingCommandHandler attachNewListingCommandHandler;

  @GetMapping()
  ListingsDto listListings() {
    var listingDtos = listingQuery.listAllListings().stream()
        .map(listing -> ListingDto.builder()
            .id(listing.getId().unwrap())
            .title(listing.getTitle())
            .description(listing.getDescription())
            .price(listing.getPrice())
            .publicationStatus(listing.getStatus())
            .build())
        .toList();
    return ListingsDto.builder()
        .listings(listingDtos)
        .build();
  }

  @PostMapping()
  void createListing(@RequestBody CreateListingRequest createListingRequest) {
    CreateListingCommand createListingCommand = CreateListingCommand.builder()
        .title(createListingRequest.getTitle())
        .description(createListingRequest.getDescription())
        .price(createListingRequest.getPrice())
        .build();
    ListingId handle = createListingCommandHandler.handle(createListingCommand);

    AttachNewListingCommand attachNewListingCommand = AttachNewListingCommand.builder()
        .listingId(handle)
        .ownerName(OwnerName.of(createListingRequest.getOwnerName()))
        .build();
    attachNewListingCommandHandler.handle(attachNewListingCommand);
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
      UpdateListingRequest updateListingRequest) {

    UpdateListingCommand updateListingCommand = UpdateListingCommand.builder()
        .listingId(of(listingId))
        .title(updateListingRequest.getTitle())
        .description(updateListingRequest.getDescription())
        .price(updateListingRequest.getPrice())
        .build();

    updateListingCommandHandler.handle(updateListingCommand);
  }

  @PostMapping("/{id}/publish")
  ResponseEntity<?> publishSingleListing(
      @PathVariable("id") UUID listingId) {

    PublishNewListingForOwnerCommand publishNewListingForOwnerCommand = PublishNewListingForOwnerCommand.builder()
        .listingId(of(listingId))
        .build();

    return publishNewListingForOwnerCommandHandler.handle(publishNewListingForOwnerCommand)
        ? ResponseEntity.ok().build()
        : ResponseEntity.badRequest().build();
  }

  @PostMapping("/{id}/unpublish")
  void unpublishSingleListing(
      @PathVariable("id") UUID listingId) {

    ModifyPublicationStatusCommand modifyPublicationStatusCommand = ModifyPublicationStatusCommand.builder()
        .publicationStatus(INACTIVE)
        .listingId(of(listingId))
        .build();
    modifyPublicationStatusCommandHandler.handle(modifyPublicationStatusCommand);
  }
}
