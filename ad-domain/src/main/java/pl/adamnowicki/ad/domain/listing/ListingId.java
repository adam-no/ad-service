package pl.adamnowicki.ad.domain.listing;

import lombok.EqualsAndHashCode;

import java.util.UUID;

import static java.lang.String.valueOf;

@EqualsAndHashCode
public class ListingId {

  private final UUID id;

  private ListingId(UUID id) {
    this.id = id;
  }

  public static ListingId of(UUID id) {
    return new ListingId(id);
  }

  public static ListingId generate() {
    return new ListingId(UUID.randomUUID());
  }

  public String unwrap() {
    return id.toString();
  }

  @Override
  public String toString() {
    return valueOf(id);
  }
}
