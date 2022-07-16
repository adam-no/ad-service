package pl.adamnowicki.ad.primaryadapter.restapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GenericControlAdvice {

  @ExceptionHandler(IllegalStateException.class)
  ResponseEntity<?> invalidCommand(IllegalStateException e) {
    log.error("Bad request", e);
    return ResponseEntity.badRequest()
        .build();
  }
}
