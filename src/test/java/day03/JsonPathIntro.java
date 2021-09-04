package day03;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.List;

import static org.hamcrest.Matchers.*;

public class JsonPathIntro {

    @BeforeAll
    public static void setUp() {
        baseURI = "http://35.170.61.191:8000";
        basePath = "/api";
    }

    @AfterAll
    public static void tearDown() {
        setUp();
    }


    @DisplayName("Extracting data out of Json object")
    @Test
    public void test1SpartanPayload() {
        //send a request to get 1 spartan by providing path param with valid id
        //save it into Response object
        //NEW: create an object with type JsonPath by calling the method jsonPath() on
        //response object extract id, name, gender, phone, and save it into variable of correct type

        Response response = given()
                                .accept(ContentType.JSON)
                                .pathParam("id", 34).
                            when()
                                .get("spartans/{id}")
                                .prettyPeek();

        //response.prettyPrint();

        JsonPath jp = response.jsonPath();
        //JsonPath navigates through the json payload and extracts the values
        //according to the valid "jsonpath" provided

        int myID = jp.getInt("id");
        String myName = jp.getString("name");
        String myGender = jp.getString("gender");
        long myPhone = jp.getLong("phone");

        System.out.println("myID = " + myID);
        System.out.println("myName = " + myName);
        System.out.println("myGender = " + myGender);
        System.out.println("myPhone = " + myPhone);
    }

    @DisplayName("Extracting data from Json Array Response")
    @Test
    public void getAllSpartanExtractData(){

       // Response response = get("/spartan");
       // JsonPath jp = response.jsonPath();

        JsonPath jp = get("/spartans").jsonPath(); //this is get all spartans

        System.out.println("jp.getString(\"name[33]\") = " +
                jp.getString("name[33]"));

        System.out.println("jp.getLong(\"phone[33]\") = " +
                jp.getLong("phone[33]"));

        System.out.println("jp.get(\"gender[33]\") = " +
                jp.get("gender[33]"));

        List<String> allNames= jp.getList("name");
        System.out.println("allNames = " + allNames);

        List<Long> allPhones = jp.getList ("phone");
        System.out.println("allPhones = " + allPhones);
    }


    // send request to this request url
    // http://35.170.61.191:8000/api/spartans/search?nameContains=des&gender=Male
    //get the name of first guy in the result
    //get the phone of 2nd guy in the result
    //get all names, all phones save it as list
    //print it out

    @DisplayName("Testing /spartans/search and extracting data")
    @Test
    public void testSearch(){

    JsonPath jp= given()
                .queryParam("nameContains", "des")
                .queryParam("gender", "Male").

        when()
                .get("spartans/search")
                .jsonPath();

        System.out.println("first guy name is "+ jp.getString("content[0].name"));
        System.out.println("first guy name is "+ jp.getString(" content[1].phone"));

        System.out.println("names are "+ jp.getList(" content.name"));
        System.out.println("phone no's are "+ jp.getList(" content.phone"));
    }

}
