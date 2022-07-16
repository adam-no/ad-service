package pl.adamnowicki.ad.domain.owner;

import java.util.List;
import java.util.Optional;

public interface ForManipulatingOwner {

  void storeOwner(Owner owner);
  List<Owner> getAllOwners();
  Optional<Owner> getByName(OwnerName name);
}
