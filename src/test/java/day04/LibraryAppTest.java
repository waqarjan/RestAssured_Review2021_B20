package day04;

import org.junit.jupiter.api.*;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LibraryAppTest {

    @BeforeAll
    public static void setUp() {
        baseURI = "https://www.library1.cybertekschool.com";
        basePath = "/rest/v1";
    }

    @AfterAll
    public static void tearDown() {
        reset();
    }


}


