package day04;

import org.junit.jupiter.api.*;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LibraryAppTest {

    @BeforeAll
    public static void setUp() {
        baseURI = "http://library1.cybertekschool.com";
        basePath = "/rest/v1";
    }

    @AfterAll
    public static void tearDown() {
        reset();
    }


    private static String myToken="";


    @DisplayName("Testing POST /login Endpoint")
    @Test
    public void testLogin() {

    myToken =
        given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("email", "librarian69@library")
                .formParam("password", "KNPXrm3S").
        when()
                .post("/login").
        then()
                .log().all()
                .assertThat()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                .body("token", is(not(emptyString())))
            .extract()
                .jsonPath()
                .getString("token");
        System.out.println("myToken = " + myToken);
    }



    @DisplayName("Testing GET /dashboard_stats Endpoint")
    @Test
    public void testzDashboard_stats() {

        // this is how we provide header .header("headerName", "headerValue")
        given()
                .log().all()
                .header("x-library-token", myToken).
        when()
                .get("/dashboard_stats").
        then()
                .log().all()
                .assertThat()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
        ;
    }
    // create a utility class LibraryUtility
    // create a static method called
    //getToken(environment, username, password)


}


