package pl.adamnowicki.ad.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CreateOwnerCommandHandler {

  private final ForManipulatingOwner forManipulatingOwner;

  public void handle(CreateOwnerCommand createOwnerCommand) {
    log.debug("CreateOwnerCommandHandler started, createOwnerCommand={}", createOwnerCommand);
    Owner newOwner = Owner.builder()
        .name(createOwnerCommand.getName())
        .build();
    forManipulatingOwner.storeOwner(newOwner);
    log.info("CreateOwnerCommandHandler completed, createOwnerCommand={}", createOwnerCommand);
  }
}
