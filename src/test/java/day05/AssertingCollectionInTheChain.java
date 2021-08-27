package day05;
import Utility.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class AssertingCollectionInTheChain {

    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath = "/api";
    }

    @AfterAll
    public static void tearDown() {
        setUp();
    }


    @DisplayName("Testing GET /api/spartans/search with Basic auth")
    @Test
    public void testSearchAndExtractData() {

        // search for nameContains : a , gender Female
        // verify status code is 200
        // check the size of result is Some hardcoded number
        // verify all names from the result contains 'a'
        // verify all gender is Female only
        // do it in the chain

                given()
                        .log().all()
                        .contentType(ContentType.JSON)
                        .queryParam("nameContains", "a")
                        .queryParam("gender", "Female").
                when()
                        .get("/spartans/search").
                then()
                        .log().all()
                        .assertThat()
                        .statusCode(is(200))
                        .body("totalElement", equalTo(41))
                        .body("content", hasSize(41))
                        .body("content.name", everyItem(containsStringIgnoringCase("a")))
                        .body("content.gender", everyItem(is("Female")))
                        ;
    }
}