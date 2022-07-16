package pl.adamnowicki.ad.domain.owner;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.Objects;

@EqualsAndHashCode
public class OwnerName {

  @NonNull
  String name;

  private OwnerName(String name) {
    this.name = name;
  }

  public static OwnerName of(String name) {
    if (Objects.isNull(name) || name.isBlank()) {
      throw new InvalidOwnerNameException();
    }

    return new OwnerName(name);
  }

  public String unwrap() {
    return name;
  }

  @Override
  public String toString() {
    return name;
  }
}
