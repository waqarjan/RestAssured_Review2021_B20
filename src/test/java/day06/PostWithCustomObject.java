package day06;

import Utility.ConfigurationReader;
import Utility.SpartanUtil;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.Spartan;
import pojo.SpartanRead;
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

//SERIALIZATION
    @DisplayName("Add 1 Data with POJO as body")
    @Test
    public void testAddDataWithPojo(){

        Spartan sp1 = new Spartan("B20 user", "Male", 1234567890L);
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


    @DisplayName("Add 1 Data with POJO as body")
    @Test
    public void addOneDataWithPojo() {

        System.out.println("--------------------------------------------------------");

        Spartan sp2 = new Spartan("B20 user", "Male", 1234567890L);
        System.out.println("sp2 = " + sp2);

        System.out.println("--------------------------------------------------------");

        Response response =
                given()
                        .auth().basic("admin", "admin")
                        .log().all()
                        .contentType(ContentType.JSON)
                        .body( sp2 ).
                when()
                        .post("/spartans").prettyPeek()
                ;
        System.out.println("--------------------------------------------------------");

        JsonPath jp = response.jsonPath();
        SpartanRead sp = jp.getObject("data",SpartanRead.class);
        System.out.println("sp = " + sp);

        System.out.println("--------------------------------------------------------");
    }
}
