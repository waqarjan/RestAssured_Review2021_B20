package day05;
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
        baseURI = "http://35.170.61.191:8000";
        basePath = "/api";
    }

    @AfterAll
    public static void tearDown() {
        setUp();
    }


    @DisplayName("Testing GET /api/spartans/search with Basic auth")
    @Test
    public void testSearchAndExtractData() {

        //STEPS WE WANT TO PERFORM
        // search for nameContains : a , gender Female
        // verify status code is 200

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
                        .contentType(ContentType.JSON)
                        ;

    }
}