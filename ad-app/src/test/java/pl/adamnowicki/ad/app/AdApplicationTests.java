package pl.adamnowicki.ad.app;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.minidev.json.JSONObject;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import pl.adamnowicki.ad.domain.listing.ForManipulatingListing;
import pl.adamnowicki.ad.domain.listing.Listing;
import pl.adamnowicki.ad.domain.owner.ForManipulatingOwner;
import pl.adamnowicki.ad.primaryadapter.restapi.ListingsDto;
import pl.adamnowicki.ad.primaryadapter.restapi.OwnersDto;
import pl.adamnowicki.ad.secondaryadapter.inmemorydb.ForManipulatingListingAdapter;
import pl.adamnowicki.ad.secondaryadapter.inmemorydb.ForManipulatingOwnerAdapter;

import java.math.BigDecimal;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.adamnowicki.ad.primaryadapter.restapi.RestApiWebUiConfiguration.ROOT_V1;

@SpringBootTest(
    classes = ContextTestConfiguration.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdApplicationTests {

  private static final String OWNER_NAME = "John Doe";
  private static final String LISTING_TITLE = "some title";
  private static final String LISTING_DESCRIPTION = "some content";
  private static final BigDecimal LISTING_PRICE = BigDecimal.TEN;

  @Autowired
  ForManipulatingOwner forManipulatingOwner;
  @Autowired
  ForManipulatingListing forManipulatingListing;

  @Value("${ad.owner.max-active-listings}")
  int maxActiveListingsPerOwner;

  @LocalServerPort
  int port;

  @BeforeEach
  public void setUp() {
    ((ForManipulatingOwnerAdapter) forManipulatingOwner).cleanAll();
    ((ForManipulatingListingAdapter) forManipulatingListing).cleanAll();
    RestAssured.port = port;
  }

  @Test
  void contextLoads() {
  }

  @Test
  void shouldCreateOwner() {
    OwnersDto response = createOwner();

    assertThat(response.getOwners()).hasSize(1);
    var ownerDto = response.getOwners().get(0);

    assertThat(ownerDto.getName()).isEqualTo(OWNER_NAME);
  }

  @Test
  void shouldCreateListing() {
    createOwner();
    ListingsDto response = createListing(1);

    assertThat(response.getListings()).hasSize(1);
    var listingDto = response.getListings().get(0);

    assertThat(listingDto.getId()).isNotBlank();
    assertThat(listingDto.getTitle()).isEqualTo(LISTING_TITLE);
    assertThat(listingDto.getDescription()).isEqualTo(LISTING_DESCRIPTION);
    assertThat(listingDto.getPrice()).isEqualTo(LISTING_PRICE);
    assertThat(listingDto.getPublicationStatus()).isEqualTo(Listing.PublicationStatus.INACTIVE);
  }

  @Test
  void shouldLimitNumberOfActiveListings() {
    createOwner();
    ListingsDto response = createListing(maxActiveListingsPerOwner + 1);

    List<Integer> statusCodes = response.getListings().stream()
        .map(
            listing -> when()
                .post(ROOT_V1 + "/listings/{id}/publish", listing.getId())
                .getStatusCode())
        .toList();

    assertThat(statusCodes)
        .contains(HttpStatus.SC_OK, HttpStatus.SC_BAD_REQUEST)
        .containsOnlyOnce(HttpStatus.SC_BAD_REQUEST);
  }

  private OwnersDto createOwner() {
    JSONObject newOwnerRequest = new JSONObject();
    newOwnerRequest.put("name", OWNER_NAME);

    given()
        .contentType(ContentType.JSON)
        .body(newOwnerRequest.toString())
        .when()
        .post(ROOT_V1 + "/owners")
        .then()
        .statusCode(HttpStatus.SC_OK);

    return when()
        .get(ROOT_V1 + "/owners")
        .as(OwnersDto.class);
  }

  private ListingsDto createListing(int listingCount) {
    for (int i = 0; i < listingCount; i++) {
      JSONObject newListingRequest = new JSONObject();
      newListingRequest.put("ownerName", OWNER_NAME);
      newListingRequest.put("title", LISTING_TITLE);
      newListingRequest.put("description", LISTING_DESCRIPTION);
      newListingRequest.put("price", LISTING_PRICE);

      given()
          .contentType(ContentType.JSON)
          .body(newListingRequest.toString())
          .when()
          .post(ROOT_V1 + "/listings")
          .then()
          .statusCode(HttpStatus.SC_OK);
    }

    return when()
        .get(ROOT_V1 + "/listings")
        .as(ListingsDto.class);
  }
}