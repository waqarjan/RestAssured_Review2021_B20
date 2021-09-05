package day06;

import Utility.ConfigurationReader;
import Utility.SpartanUtil;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.Spartan;

import java.util.Map;

import static org.hamcrest.Matchers.*;

public class JsonToJavaObject {

    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath = "/api";
    }

    @AfterAll
    public static void tearDown() {
        reset();
    }


    @DisplayName("Get 1 Data and Save Response Json As Java Map Object")
    @Test
    public void getOneSpartanAndSaveResponseJsonAsMap() {

    Response response= given()
                                .auth().basic("admin", "admin")
                                .log().all()
                                .pathParam("id",5).
                        when()
                                .get("/spartans/{id}")
                                .prettyPeek()
                                ;

    JsonPath jp = response.jsonPath();
    //jp.getMap("");

    Map <String, Object> responseMap = jp.getMap("" );
        System.out.println("responseMap = " + responseMap);
    }


}
