package day05;

import org.junit.jupiter.api.* ;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.MethodOrderer.* ;

// by default the test is running on alphabetical order or the test method name

// these are all available option for ordering your tests
// @TestMethodOrder(OrderAnnotation.class)
// @TestMethodOrder(Random.class)
// @TestMethodOrder(MethodName.class)       // default options
   @TestMethodOrder(OrderAnnotation.class)
public class TestingOrderingJunit5 {

    @Order(3)
    @DisplayName("3. Test A method")
    @Test
    public void testA(){
        System.out.println("running test A");
    }

    @Order(1)
    @DisplayName("1. Test C method")
    @Test
    public void testC(){
        System.out.println("running test C");
    }

    @Order(4)
    @DisplayName("4. Test D method")
    @Test
    public void testD(){
        System.out.println("running test D");
    }

    @Order(2)
    @DisplayName("2. Test B method")
    @Test
    public void testB(){
        System.out.println("running test B");
    }
}



