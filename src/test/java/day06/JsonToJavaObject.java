package day06;

import Utility.ConfigurationReader;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;
import pojo.SpartanRead;

import java.util.List;
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

        Response response = given()
                                .auth().basic("admin", "admin")
                                .log().all()
                                .pathParam("id", 5).
                            when()
                                .get("/spartans/{id}")
                                .prettyPeek();

        JsonPath jp = response.jsonPath();
        //jp.getMap("");

        Map<String, Object> responseMap = jp.getMap("");
        System.out.println("responseMap = " + responseMap);

        SpartanRead sp = jp.getObject("", SpartanRead.class);
        System.out.println("sp = " + sp);
    }


    @DisplayName("Get All Data and Save Response JsonArray As Java Object")
    @Test
    public void getOneSpartanAndSaveResponseJsonAsJavaObject() {

        Response response =
            given()
                    .auth().basic("admin","admin").
            when()
                    .get("/spartans");

        JsonPath jp = response.jsonPath();

        List<SpartanRead> allSpartanPOJO = jp.getList("", SpartanRead.class);

      //  System.out.println(allSpartanPOJO);

        allSpartanPOJO.forEach(System.out::println);

    }
}
