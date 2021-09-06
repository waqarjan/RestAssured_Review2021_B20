package day07;

import Utility.ConfigurationReader;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class PatchOneSpartanTest {

    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("spartan.base_url");
        basePath = "/api";
    }

    @AfterAll
    public static void tearDown() {
        reset();
    }


    @DisplayName("Patching 1 data with Java Object")
    @Test
    public void testPath1DataPartialUpdate(){

        //we just want to update the name and phone number

        Map <String, Object> patchBodyMap = new LinkedHashMap<>();
        patchBodyMap.put("name", "Naghmma");
        patchBodyMap.put("phone", 1231231231L);


        given()
                .auth().basic("admin", "admin")
                .log().all()
                .pathParam("id",100)
                .contentType(ContentType.JSON)
                .body( patchBodyMap ).
        when()
                .patch("/spartans/{id}").
        then()
                .log().all()
                .statusCode(204)
                ;
    }


    @DisplayName("Patching 1 data with Java Object")
    @Test
    public void testPath1DataPartialUpdateWithPOJO(){

        //we just want to update the name and phone number

        Spartan sp = new Spartan(); //"Nagina", "", 3213213213L);
        sp.setName("Nagi");
        sp.setPhone(3334445551l);

        // MAP IS A BETTER OPTION WITH MINIMAL EFFORT
        // POJO class need some handling to ignore empty field values
        // when being serialized

        given()
                .auth().basic("admin", "admin")
                .log().all()
                .pathParam("id",100)
                .contentType(ContentType.JSON)
                .body( sp ).
        when()
                .patch("/spartans/{id}").
        then()
                .log().all()
                .statusCode(204)
        ;
    }
}
