package day01;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.* ;


public class HamcrestMatchersTest {

    @DisplayName("Test 1+3 is 4")
    @Test
    public void test1() {

        assertThat(1 + 3, is(4));
        assertThat(1 + 3, equalTo(4));
        assertThat("wrong result", 1 + 4, equalTo(5));
        //assertThat(1+3, not(equalTo(5)));
        assertThat(1 + 4, not(5));
        assertThat(1 + 4, is(not(5)));
        assertThat(1 + 4, is(not(equalTo(5))));
    }
    @DisplayName("1+3 is less than 5")
    @Test
    public void test2(){
        assertThat(1+3, is(lessThan(5)));
    }

    @DisplayName(("1+3 is more than 2"))
    @Test
    public void test3(){
        assertThat(1+2, is(not(lessThan(2))));
        assertThat(1+2, is(greaterThan(2)));
    }

    @DisplayName("Common Matcher for Strings")
    @Test
    public void testString(){
        String str = "Rest Assured is cool so far";

        //assert the str is "Rest Assured is cool so far"
        assertThat(str, is("Rest Assured is cool so far"));

        //assert the str starts "Rest Assured IS COOL so far" in case insensitive manner
        assertThat(str, containsStringIgnoringCase("Rest Assured IS COOL so far"));

        //assert the str starts "Rest"
        assertThat(str, startsWith("Rest"));

        //assert the str starts "is ool"
        assertThat(str, containsString("is cool"));

        //assert the str starts "IS COOL" case insentive manner
        assertThat(str, containsStringIgnoringCase("IS COOL"));

        //assert the str ends with "so far"
        assertThat(str, endsWithIgnoringCase("so far"));


    }


}
