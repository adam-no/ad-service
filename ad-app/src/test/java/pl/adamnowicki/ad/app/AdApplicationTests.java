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
import pl.adamnowicki.ad.domain.ForManipulatingOwner;
import pl.adamnowicki.ad.primaryadapter.restapi.OwnersDto;
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

  private static final String OWNER_NAME = "John Doe";

  @LocalServerPort
  int port;

  @BeforeEach
  public void setUp() {
    ((ForManipulatingOwnerAdapter) forManipulatingOwner).cleanAll();
    RestAssured.port = port;
  }

  @Test
  void contextLoads() {
  }

  @Test
  void shouldCreateOwner() {
    JSONObject jsonObj = new JSONObject();
    jsonObj.put("name", OWNER_NAME);

    given()
        .contentType(ContentType.JSON)
        .body(jsonObj.toString())
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
}
