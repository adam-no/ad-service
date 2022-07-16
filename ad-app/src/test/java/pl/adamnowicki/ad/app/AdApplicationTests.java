package pl.adamnowicki.ad.app;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.minidev.json.JSONObject;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import pl.adamnowicki.ad.domain.listing.ForManipulatingListing;
import pl.adamnowicki.ad.domain.owner.ForManipulatingOwner;
import pl.adamnowicki.ad.primaryadapter.restapi.ListingsDto;
import pl.adamnowicki.ad.primaryadapter.restapi.OwnersDto;
import pl.adamnowicki.ad.secondaryadapter.inmemorydb.ForManipulatingListingAdapter;
import pl.adamnowicki.ad.secondaryadapter.inmemorydb.ForManipulatingOwnerAdapter;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static pl.adamnowicki.ad.primaryadapter.restapi.RestApiWebUiConfiguration.ROOT_V1;

@SpringBootTest(
    classes = ContextTestConfiguration.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AdApplicationTests {

  @Autowired
  ForManipulatingOwner forManipulatingOwner;
  @Autowired
  ForManipulatingListing forManipulatingListing;

  private static final String OWNER_NAME = "John Doe";
  private static final String LISTING_CONTENT = "some content";

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
    JSONObject newOwnerRequest = new JSONObject();
    newOwnerRequest.put("name", OWNER_NAME);

    given()
        .contentType(ContentType.JSON)
        .body(newOwnerRequest.toString())
        .when()
        .post(ROOT_V1 + "/owners")
        .then()
        .statusCode(HttpStatus.SC_OK);

    OwnersDto response = when()
        .get(ROOT_V1 + "/owners")
        .as(OwnersDto.class);

    assertThat(response.getOwners()).hasSize(1);
    var ownerDto = response.getOwners().get(0);

    assertThat(ownerDto.getName()).isEqualTo(OWNER_NAME);
  }

  @Test
  void shouldCreateListing() {
    JSONObject newOwnerRequest = new JSONObject();
    newOwnerRequest.put("name", OWNER_NAME);

    given()
        .contentType(ContentType.JSON)
        .body(newOwnerRequest.toString())
        .when()
        .post(ROOT_V1 + "/owners")
        .then()
        .statusCode(HttpStatus.SC_OK);

    JSONObject newListingRequest = new JSONObject();
    newListingRequest.put("ownerName", OWNER_NAME);
    newListingRequest.put("content", LISTING_CONTENT);

    given()
        .contentType(ContentType.JSON)
        .body(newListingRequest.toString())
        .when()
        .post(ROOT_V1 + "/listings")
        .then()
        .statusCode(HttpStatus.SC_OK);

    ListingsDto response = when()
        .get(ROOT_V1 + "/listings")
        .as(ListingsDto.class);

    assertThat(response.getListings()).hasSize(1);
    var listingDto = response.getListings().get(0);

    assertThat(listingDto.getContent()).isEqualTo(LISTING_CONTENT);
  }
}