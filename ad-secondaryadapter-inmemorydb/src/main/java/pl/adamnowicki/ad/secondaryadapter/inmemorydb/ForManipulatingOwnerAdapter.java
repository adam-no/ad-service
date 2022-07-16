package pl.adamnowicki.ad.secondaryadapter.inmemorydb;

import lombok.extern.slf4j.Slf4j;
import pl.adamnowicki.ad.domain.ForManipulatingOwner;
import pl.adamnowicki.ad.domain.Owner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class ForManipulatingOwnerAdapter implements ForManipulatingOwner {

  private final ConcurrentHashMap<Long, Owner> ownerRepository = new ConcurrentHashMap<>();
  private final AtomicLong ownerIdSequence = new AtomicLong(0);


  @Override
  public void storeOwner(Owner owner) {
    ownerRepository.put(ownerIdSequence.getAndIncrement(), owner);
    log.trace("New owner stored, owner={}", owner);
  }

  @Override
  public List<Owner> getAllOwners() {
    log.trace("Fetching all owners requested");
    return new ArrayList<>(ownerRepository.values());
  }

  public void cleanAll() {
    ownerIdSequence.set(0);
    ownerRepository.clear();
  }
}
