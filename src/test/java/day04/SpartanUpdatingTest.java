package day04;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;


public class SpartanUpdatingTest {

    @BeforeAll
    public static void setUp() {
        baseURI = "http://35.170.61.191:8000";
        basePath = "/api";
    }

    @AfterAll
    public static void tearDown() {
        setUp();
    }

    @DisplayName("Testing PUT /api/spartans{ID}")
    @Test
    public void testUpdatingSingleSpartanWithStringBody() {

        String updateStrPayload = "    {\n" +
                "        \"name\": \"B20 Vola\",\n" +
                "        \"gender\": \"Male\",\n" +
                "        \"phone\": 9876543210\n" +
                "    }";

        given()
                .log().all()
                .auth().basic("admin", "admin")
                .contentType(ContentType.JSON)
                .pathParam("id", 1)
                .body(updateStrPayload).
        when()
                .put("/spartans/{id}").
        then()
                .log().all()
                .assertThat()
                .statusCode(204)
                .header("Date", is(notNullValue()))
                .body(emptyString())
        ;
    }

    @DisplayName("Testing PATCH /api/spartans/{id} with String body")
    @Test
    public void testPartialUpdatingSingleSpartanWithStringBody() {



    }






}
