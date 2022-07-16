package pl.adamnowicki.ad.domain;

import java.util.List;

public interface ForManipulatingOwner {

  void storeOwner(Owner owner);
  List<Owner> getAllOwners();
}
