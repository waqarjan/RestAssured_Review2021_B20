package day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class SpartanTest {

    //  http://35.170.61.191:8000/api/spartans
    //  http://35.170.61.191:8000 ---> this is the base part of the url that takes us to the application
    //  /api                      ---> this is our entry point for our API end point
    //  /spartans                 --->  the actual end point that get us the resource
    //RestAssured gives us a static field so that we can save all the above parts separately and globally as we did in Postman.

    //So, we put these in @BeforeAll and @AfterAll in the beginning so that it will run once before and after each test and every test.
    @BeforeAll
    public static void setUp(){
        //RestAssured.baseURI = "http://35.170.61.191:8000";
        //RestAssured.basePath= "/api";

        baseURI = "http://35.170.61.191:8000";
        basePath= "/api";
    }

    @AfterAll
    public static void tearDown(){
        //resetting the value of baseURI, basePath to original
        //RestAssured.reset();
        reset();
    }

    @DisplayName("Testing /api/spartans endpoint")
    @Test
    public void testGetAllSpartans() {

        //send a get request to above endpoint, save the response, printout the result,
        // and try to assert the status code, content type header
        Response response = get("/spartans");
        response.prettyPrint();
        assertThat(response.getStatusCode(), is(200));
        assertThat(response.contentType(), is(ContentType.JSON.toString()));
    }

    @DisplayName("Testing /api/spartans endpoint XML Response")
    @Test
    public void testGetAllSpartansXML() {
        //CHAINING
        //Given
        /*----------- request specification
                           used to provide additional information about the request
                           base url base path
                           header, query param, path variable, payload, authentication
                            authorization, logging, cooke
         */
        //When
        /*----------- This is where you actually send the request with http method
                            like GET, POST, PUT, DELETE, .... with the URL
                            - We get Response Object after send the request
         */
        //Then
        /*----------- Validatable Response
                            - validate status code, header, payload, cookie, responseTime
                            - content type
         */
/*
        given()
                .header("accept","application/xml").
        when()
                .get("http://35.170.61.191:8000/api/spartans").
        then()
                .assertThat()
                .statusCode(200).and().header("Content-Type", "application/xml")
        ;
*/
        given()
                .accept(ContentType.XML).
        when()
                .get("/spartans").
        then()
                .assertThat().statusCode(is(200)).and().contentType(ContentType.XML);
    }
}
