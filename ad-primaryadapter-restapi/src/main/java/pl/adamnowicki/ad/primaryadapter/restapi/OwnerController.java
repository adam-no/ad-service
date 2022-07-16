package pl.adamnowicki.ad.primaryadapter.restapi;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.adamnowicki.ad.domain.CreateOwnerCommand;
import pl.adamnowicki.ad.domain.CreateOwnerCommandHandler;
import pl.adamnowicki.ad.domain.OwnerQuery;
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
            .name(owner.getName())
            .build())
        .toList();
    return OwnersDto.builder()
        .owners(ownerDtos)
        .build();
  }

  @PostMapping()
  void createOwner(@RequestBody OwnerDto ownerDto) {
    CreateOwnerCommand createOwnerCommand = CreateOwnerCommand.builder()
        .name(ownerDto.getName())
        .build();
    createOwnerCommandHandler.handle(createOwnerCommand);
  }
}
