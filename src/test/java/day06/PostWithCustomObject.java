package day06;

import Utility.ConfigurationReader;
import Utility.SpartanUtil;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.Spartan;

import java.util.Map;

import static org.hamcrest.Matchers.*;

public class PostWithCustomObject {

    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath = "/api";
    }

    @AfterAll
    public static void tearDown() {
        reset();
    }

    //Serialization: Java object to JSON
    //De-serialization: JSON to Java object
    //POJO (Plain Old java Object) class  is used to create object that represent Data
    /*
        A POJO Class must have:
            1.	encapsulated fields (getter & setter),
            2.	public no argument constructor,
            3.	everything else is optional
     */


    @DisplayName("Add 1 Data with POJO as body")
    @Test
    public void testAddDataWithPojo(){

        Spartan sp1 = new Spartan("B20 user", "Male", 1234567890l);
        System.out.println("sp1 = " + sp1);

        given()
                .auth().basic("admin", "admin")
                .log().all()
                .contentType(ContentType.JSON)
                .body( sp1 ).
        when()
                .post("/spartans").
        then()
                .log().all()
                .assertThat()
                .statusCode(201)
                ;
    }






}
