package day05;

import io.restassured.http.ContentType;
import Utility.ConfigurationReader;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.DisplayName;
import Utility.SpartanUtil;

import java.util.Map;


@TestMethodOrder(MethodOrderer.DisplayName.class)
public class Spartan_EndToEnd_HappyPath {

    private static Map<String, Object> payloadMap;
    private static int newID;


    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath = "/api";
        payloadMap = SpartanUtil.getRandomSpartanRequestPayload();
    }

    @AfterAll
    public static void tearDown() {
        reset();
    }


    /*
    STEPS:
        1-Create a top-level variable so each and every class can access.
            private static Map<String,Object> payloadMap ;
        2-Set the value of the payloadMap using the SpartanUtil.
            payloadMap =  spartanUtil.getRandomSpartanRequestPayload() ;
        3-Validate
     */


    @DisplayName("1. Testing POST /api/spartans Endpoint")
    @Test
    public void testAddData() {
        newID =
        given()
                .auth().basic("admin","admin")
                .contentType(ContentType.JSON)
                .body(  payloadMap  )
                .log().all().
        when()
                .post("/spartans").
        then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .contentType(ContentType.JSON)
                // assert the response body name , gender , phone
                // is same as what faker generated
                .body("data.name" , is( payloadMap.get("name") )  )
                .body("data.gender" , is( payloadMap.get("gender") )  )
                .body("data.phone" , equalTo( payloadMap.get("phone") )  )
             .extract()
                .jsonPath()
                .getInt("data.id")
        ;
            System.out.println("newID = " + newID);

    }

    @DisplayName("2. Testing GET /api/spartans/{id} Endpoint")
    @Test
    public void testGet1SpartanData() {

        given()
                .auth().basic("admin","admin")
                .pathParam("id" , newID)
                .log().all().
        when()
                .get("/spartans/{id}").
                then()
                .log().all()
            .assertThat()
                .statusCode( is (200) )
                .contentType(ContentType.JSON)
                .body("id" , is(newID) )
                .body("name" , is( payloadMap.get("name")  ) )
                .body("gender" , is( payloadMap.get("gender") ) )
                .body("phone" , is( payloadMap.get("phone") ) )
        ;
    }


    @DisplayName("3. Testing PUT /api/spartans/{id} Endpoint")
    @Test
    public void testUpdate1SpartanData() {

        payloadMap = SpartanUtil.getRandomSpartanRequestPayload();

        given()
                .auth().basic("admin","admin")
                .pathParam("id",newID)
                .contentType(ContentType.JSON)
                .body(payloadMap)
                .log().all().
        when()
                .put("/spartans/{id}").
        then()
                .log().all()
            .assertThat()
                .statusCode(204)
                .body(emptyString())
        ;


        given()
                .auth().basic("admin","admin")
                .pathParam("id" , newID)
                .log().all().
        when()
                .get("/spartans/{id}").
        then()
                .log().all()
                .body("gender" , is( payloadMap.get("gender") ) )
             .assertThat()
                .statusCode( is (200) )
                .contentType(ContentType.JSON)
                .body("id" , is(newID) )
                .body("name" , is( payloadMap.get("name")  ) )
                .body("phone" , is( payloadMap.get("phone") ) )
        ;
    }

    @DisplayName("4. Testing DELETE /api/spartans/{id} Endpoint")
    @Test
    public void testDelete1SpartanData() {

        given()
                .auth().basic("admin","admin")
                .pathParam("id",newID)
                .log().all().
        when()
                .delete("/spartans/{id}").
        then()
                .log().all()
                .assertThat()
                .statusCode(204)
                .body(emptyString())
        ;
    }
}