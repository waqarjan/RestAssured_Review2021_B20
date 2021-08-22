package day02;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SingleSpartanTest {

    @BeforeAll
    public static void setUp() {
        baseURI = "http://35.170.61.191:8000";
        basePath = "/api";
    }

    @AfterAll
    public static void tearDown() {
        setUp();
    }


    @DisplayName("Option 1- Testing GET /spartans/{id} endpoint")
    @Test
    public void test1Spartan() {
        given()
                .accept(ContentType.JSON).
        when()
                .get("/spartans/100").
        then()
                .assertThat()
                .statusCode(is(200)).and()
                .contentType(ContentType.JSON)
        ;
    }


    @DisplayName("Option 2- Testing GET /spartans/{id} endpoint")
    @Test
    public void test2Spartan() {
        given()
                .accept(ContentType.JSON)
                .pathParam("id", 100).
        when()
                .get("/spartans/{id}").
        then()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
        ;
    }


    @DisplayName("Option 3- Testing GET /spartans/{id} endpoint")
    @Test
    public void test3Spartans() {
        given()
                .accept(ContentType.JSON).
        when()
                .get("/spartans/{id}", 100).
        then()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
        ;
    }


    @DisplayName("Testing GET /spartans/{id} endpoint Payload")
    @Test
    public void testSpartansBody(){
        given()
                .accept(ContentType.JSON).
        when()
                .get("/spartans/{id}",100).
        then()
                .assertThat()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                .body("id", is(100))
                .body("name", is("Terence"))
                .body("gender", is("Male"))
                .body("phone", is(1311814806))
        ;
    }
}
