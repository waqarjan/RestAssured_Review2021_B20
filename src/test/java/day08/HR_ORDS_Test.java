package day08;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import pojo.Region;
import java.util.List;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class HR_ORDS_Test {

    //http://35.170.61.191:1000/ords/hr/countries/AR

    @BeforeAll
    public static void setUp(){
        baseURI = "http://35.170.61.191:1000";
        basePath = "/ords/hr" ;
    }

    @AfterAll
    public static void tearDown(){
        reset();
    }


}
