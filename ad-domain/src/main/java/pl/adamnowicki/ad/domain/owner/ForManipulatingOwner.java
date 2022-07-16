package pl.adamnowicki.ad.domain.owner;

import pl.adamnowicki.ad.domain.listing.ListingId;

import java.util.List;
import java.util.Optional;

public interface ForManipulatingOwner {

  void storeOwner(Owner owner);
  List<Owner> getAllOwners();
  Optional<Owner> getByName(OwnerName name);

  Optional<Owner> getByListingId(ListingId listingId);
}
