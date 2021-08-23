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
    public void testUpdatingSingleSpartan() {

    }





}
