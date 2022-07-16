package pl.adamnowicki.ad.domain.listing;

import java.util.List;
import java.util.Optional;

public interface ForManipulatingListing {

  void storeListing(Listing listing);
  List<Listing> getAllListings();

  Optional<Listing> getById(ListingId listingId);
}
