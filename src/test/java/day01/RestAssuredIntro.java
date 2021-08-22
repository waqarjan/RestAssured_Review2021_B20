package day01;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class RestAssuredIntro {

    @DisplayName("Spartan GET /api/hello Endpoint Test")
    @Test
    public void TestHello() {
        //http://35.170.61.191:8000/api/hello

        Response response = get("http://35.170.61.191:8000/api/hello");

        //get status code
        System.out.println("status code is = " + response.statusCode());
        //assert status code is 200
        assertThat(response.statusCode(), is(equalTo(200)));

        //how to pretty print the entire response body
        //prettyPrint() -- prints and returns the PayLoad as String.
        String payLoad = response.prettyPrint();
        //assert that the body is Hello from Sparta
        assertThat(payLoad, is(equalTo("Hello from Sparta")));

        //get the header called ContentType from the response
        System.out.println( response.getHeader("Content-Type") );
        System.out.println( response.getContentType() );
        System.out.println( response.contentType() );
        //assert that Contenty-Type is text/plain;charset=UTF-8
        assertThat(response.contentType(),is("text/plain;charset=UTF-8"));
        assertThat(response.contentType(), containsStringIgnoringCase("text/plain"));
        //assert that Contenty-Type startsWith text
        assertThat(response.contentType(), startsWith("text"));

        //Easy way to work with Content-type without typing much
        assertThat(response.contentType(),startsWith(ContentType.TEXT.toString()));
        //assertThat(response.contentType(),is(not(ContentType.JSON)));



    }

}
