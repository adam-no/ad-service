package pl.adamnowicki.ad.secondaryadapter.inmemorydb;

import lombok.extern.slf4j.Slf4j;
import pl.adamnowicki.ad.domain.owner.ForManipulatingOwner;
import pl.adamnowicki.ad.domain.owner.Owner;
import pl.adamnowicki.ad.domain.owner.OwnerName;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Optional.ofNullable;

@Slf4j
public class ForManipulatingOwnerAdapter implements ForManipulatingOwner {

  private final ConcurrentHashMap<OwnerName, Owner> ownerRepository = new ConcurrentHashMap<>();


  @Override
  public void storeOwner(Owner newOwner) {
    OwnerName ownerName = newOwner.getName();

    Owner owner = Owner.builder()
        .name(ownerName)
        .listings(new ArrayList<>(newOwner.getListings()))
        .build();

    ownerRepository.put(ownerName, owner);
    log.trace("New owner stored, owner={}", owner);
  }

  @Override
  public List<Owner> getAllOwners() {
    log.trace("Fetching all owners requested");
    return ownerRepository.values().stream()
        .map(owner -> Owner.builder()
            .name(owner.getName())
            .listings(new ArrayList<>(owner.getListings()))
            .build())
        .toList();
  }

  @Override
  public Optional<Owner> getByName(OwnerName name) {
    return ofNullable(ownerRepository.get(name))
        .map(owner -> Owner.builder()
            .name(owner.getName())
            .listings(new ArrayList<>(owner.getListings()))
            .build());
  }

  public void cleanAll() {
    ownerRepository.clear();
  }
}
