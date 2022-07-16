package pl.adamnowicki.ad.secondaryadapter.inmemorydb;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.adamnowicki.ad.domain.listing.ForManipulatingListing;
import pl.adamnowicki.ad.domain.listing.Listing;
import pl.adamnowicki.ad.domain.listing.ListingId;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RequiredArgsConstructor
public class ForManipulatingListingAdapter implements ForManipulatingListing {

  private final ConcurrentHashMap<ListingId, Listing> repository = new ConcurrentHashMap<>();

  @Override
  public void storeListing(Listing newListing) {
    ListingId id = Optional.ofNullable(newListing.getId())
        .orElse(ListingId.generate());

    Listing listing = Listing.builder()
        .id(id)
        .content(newListing.getContent())
        .build();

    repository.put(id, listing);
    log.trace("New listing stored, listing={}", listing);
  }

  @Override
  public List<Listing> getAllListings() {
    log.trace("Fetching all listing requested");
    return repository.values().stream()
        .map(listing -> Listing.builder()
            .id(listing.getId())
            .content(listing.getContent())
            .build())
        .toList();
  }

  @Override
  public Optional<Listing> getById(ListingId listingId) {
    return Optional.ofNullable(repository.get(listingId))
        .map(listing -> Listing.builder()
            .id(listing.getId())
            .content(listing.getContent())
            .build());
  }

  public void cleanAll() {
    repository.clear();
  }
}
