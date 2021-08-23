package day04;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static org.hamcrest.Matchers.*;

public class SpartanAddingUpdatingTest {

    @BeforeAll
    public static void setUp() {
        baseURI = "http://35.170.61.191:8000";
        basePath = "/api";
    }

    @AfterAll
    public static void tearDown() {
        setUp();
    }

    @DisplayName("Testing GET /api/spartans with Basic auth ")
    @Test
    public void testAllSpartanWithBasicAuth(){
        given()
                .log().all()
                .auth().basic("admin","admin").
        when()
                .get("/spartans")
        .then()
                .log().all()
                .statusCode(200);
    }





}
