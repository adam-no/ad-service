package pl.adamnowicki.ad.domain.owner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class OwnerQuery {

  private final ForManipulatingOwner forManipulatingOwner;

  public List<Owner> listAllOwners() {
    List<Owner> owners = forManipulatingOwner.getAllOwners();
    log.debug("Listing all owners requested, size={}", owners.size());
    return owners;
  }
}
