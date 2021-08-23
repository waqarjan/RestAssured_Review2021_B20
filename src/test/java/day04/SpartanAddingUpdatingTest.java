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
    public void testAllSpartanWithBasicAuth() {
        given()
                .log().all()
                .auth().basic("admin", "admin").
        when()
                .get("/spartans")
        .then()
                .log().all()
                .statusCode(200);
    }


    @DisplayName("Add 1 Data with Raw Json String POST/api/spartans")
    @Test
    public void testAddOneData() {

        String newSpartanStr = "   {\n" +
                "        \"name\": \"Assyle Khan\",\n" +
                "        \"gender\": \"Male\",\n" +
                "        \"phone\": 1231031231\n" +
                "    }";
        System.out.println("newSpartanStr = " + newSpartanStr);

        given()
                .log().all()
                .auth().basic("admin", "admin")
                .contentType(ContentType.JSON)      //tell the server what we are sending
                .body(newSpartanStr).
        when()
                .post("/spartans").
        then()
                .log().all()
                .assertThat()
                .statusCode(is(201))
                .contentType(ContentType.JSON)
                .body("success", is("A Spartan is Born!"))
                .body("data.name", is("Assyle Khan"))
                .body("data.gender", is("Male"))
        ;
        }

        @DisplayName("Add 1 Data with Map Object POST /api/spartans")
        @Test
        public void testAddOneDataWithMapAsBody() {

            Map<String, Object> payloadMap = new LinkedHashMap<>();
                payloadMap.put("name" ,   "Tucky");
                payloadMap.put("gender" , "Male");
                payloadMap.put("phone" ,  9876543210L);

            System.out.println("payloadMap = " + payloadMap);

            given()
                    .log().all()
                    .auth().basic("admin", "admin")
                    .contentType(ContentType.JSON)
                    .body(payloadMap).
            when()
                    .post("/spartans").
            then()
                    .log().all()
                    .assertThat()
                    .statusCode(is(201))
                    .contentType(ContentType.JSON)
                    .body("success", is("A Spartan is Born!"))
                    .body("data.name", is("Tucky"))
                    .body("data.gender", is("Male"))
                    .body("data.phone", is(9876543210L))
            ;
        }

    @DisplayName("Add 1 Data with Map Object POST /api/spartans")
    @Test
    public void testAddOneDataWithJSONFileAsBody(){
    //create a file called single spartan.json right under root directory with below content
        /*
            {
            "name": "Olivia",
            "gender": "Female",
            "phone": 9877543210L
            }
            add below code to point File object to this singleSpartan.json
         */

        File externalJson = new File("singleSpartan.json");

        given()
                .log().all()
                .auth().basic("admin", "admin")
                .contentType(ContentType.JSON)
            .body(externalJson).
        when()
                .post("/spartans").
        then()
                .log().all()
                .assertThat()
                .statusCode(is(201))
                .contentType(ContentType.JSON)
                .body("success", is("A Spartan is Born!"))
                .body("data.name", is("Olivia"))
                .body("data.gender", is("Female"))
                .body("data.phone", is(9877543210L));
    }

}
