package day05;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class HamcrestCollectionSupport {

    @Test
    public void testList() {

        List<Integer> numList = Arrays.asList(4, 6, 7, 9, 5, 88, 90);

        // use hamcrest matcher to test the size of this list
        assertThat(numList, hasSize(7));

        // assert that this list contains 9
        assertThat(numList, contains(9));

        // assert that this list contains 9 , 88
        assertThat(numList, contains(9,90));

        // assert that everyItems in the list are more than 3
        assertThat(numList, everyItem(is(greaterThan(3))));

        List<String> allNames  = Arrays.asList("Rory Hogan", "Mariana","Olivia","Gulbadan","Ayxamgul","Kareem","Virginia","Tahir Zohra") ;

        // use hamcrest matcher to test the size of this list
        assertThat(allNames, hasSize(8));

        // check every item has names Virginia, Ayxamgul, Rory Hogan
        assertThat(allNames, hasItems("Virginia", "Ayxamgul", "Rory Hogan") );

        // check every item has letter a
        assertThat(allNames,everyItem(containsString("a")));

        // check every items has letter a in case insensitive manner
        assertThat(allNames, hasItem(containsStringIgnoringCase("a")));

        // AND OR in hamcrest syntax
        // allOf --> and logic , all of the matchers should match or it fails
        assertThat("Murat Degirmenci", allOf(startsWith("Mu"), containsString("men"))) ;

        //  anyOf -->> or logic  as long as one matcher match it will pass
        assertThat("Ramazan Alic" , anyOf(  is("Ramazan") ,  endsWith("ic") )   ) ;


    }
}
