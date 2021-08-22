package day04;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static org.hamcrest.Matchers.*;


public class OpenMovieDB_Test {

    @BeforeAll
    public static void setUp() {
        baseURI = "http://www.omdbapi.com/";
    }

    @AfterAll
    public static void tearDown() {
        setUp();
    }


}