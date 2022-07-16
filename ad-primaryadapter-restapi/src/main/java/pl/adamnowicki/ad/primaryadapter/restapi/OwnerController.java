package pl.adamnowicki.ad.primaryadapter.restapi;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.adamnowicki.ad.domain.owner.CreateOwnerCommand;
import pl.adamnowicki.ad.domain.owner.CreateOwnerCommandHandler;
import pl.adamnowicki.ad.domain.owner.InvalidCreateOwnerCommandException;
import pl.adamnowicki.ad.domain.owner.OwnerQuery;
import pl.adamnowicki.ad.primaryadapter.restapi.OwnersDto.OwnerDto;

import static pl.adamnowicki.ad.primaryadapter.restapi.RestApiWebUiConfiguration.ROOT_V1;

@RestController
@RequestMapping(ROOT_V1 + "/owners")
@RequiredArgsConstructor
public class OwnerController {

  private final OwnerQuery ownerQuery;
  private final CreateOwnerCommandHandler createOwnerCommandHandler;

  @GetMapping()
  OwnersDto listOwners() {
    var ownerDtos = ownerQuery.listAllOwners().stream()
        .map(owner -> OwnerDto.builder()
            .name(owner.getName().unwrap())
            .build())
        .toList();
    return OwnersDto.builder()
        .owners(ownerDtos)
        .build();
  }

  @PostMapping()
  void createOwner(@RequestBody CreateOwnerCommand createOwnerCommand) {
    createOwnerCommandHandler.handle(createOwnerCommand);
  }

  @ExceptionHandler(InvalidCreateOwnerCommandException.class)
  ResponseEntity<?> invalidCommand() {
    return ResponseEntity.badRequest()
        .build();
  }
}
