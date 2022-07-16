package pl.adamnowicki.ad.domain.listing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class ListingQuery {

  private final ForManipulatingListing forManipulatingListing;

  public List<Listing> listAllListings() {
    List<Listing> listings = forManipulatingListing.getAllListings();
    log.debug("Listing all listings requested, size={}", listings.size());
    return listings;
  }

  public Optional<Listing> getById(ListingId listingId) {
    Optional<Listing> listing = forManipulatingListing.getById(listingId);
    log.debug("Fetching Listing by id, id={}, found={}", listingId, listing.isPresent());
    return listing;
  }
}
