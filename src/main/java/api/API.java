package api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class API {

    public String getSearch(String request) {
    return given()
                .get("https://desearch.com/api/search/v2?request="+ request)
                .then()
                .log().body()
                .assertThat().statusCode(200).body("data",is(not(empty())))
                .extract().body().asString();
    }



}
