package pl.adamnowicki.ad.domain.owner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CreateOwnerCommandHandler {

  private final ForManipulatingOwner forManipulatingOwner;

  public void handle(CreateOwnerCommand createOwnerCommand) {
    log.debug("CreateOwnerCommandHandler started, createOwnerCommand={}", createOwnerCommand);
    OwnerName name = OwnerName.of(createOwnerCommand.getName());

    Owner newOwner = Owner.builder()
        .name(name)
        .build();
    forManipulatingOwner.storeOwner(newOwner);
    log.info("CreateOwnerCommandHandler completed, createOwnerCommand={}", createOwnerCommand);
  }
}
