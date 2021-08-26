package day05;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.* ;

public class ExtractPractice {

    /*
     extract() method of RestAssured enables us to
     extract data after validation in the
     section of the method chaining
     */

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
        // extract jsonPath object after validation
        // use that jsonPath object to get the list of all results
        // and get the numberOfElements field value
        // compare those 2

        JsonPath jp =
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
                        .extract()
                        .jsonPath();


      List<String> listOfNames = jp.getList("content.name");

      int numOfElements = jp.getInt("totalElement");
      System.out.println("numOfElements = " + numOfElements);

       assertThat(listOfNames.size(), equalTo(numOfElements));

      assertThat(listOfNames, hasSize(numOfElements));
    }



}
