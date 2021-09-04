package day01;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Day 1 Hello Test")
public class HelloTest {

    //JUnit5 Annotations @BeforeAll @AfterAll @BeforeEach @AfterEach
    //@DisplayName @Disable

    @BeforeAll
    public static void setUp(){
        System.out.println("@BeforAll is started");
    }

    @AfterAll
    public static void tearDown(){
        System.out.println("@AfterAll tear down");
    }

    @BeforeEach
    public void setUpTest(){
        System.out.println("BeforeEach is running");
    }

    @AfterEach
    public void tearDownTest(){
        System.out.println("AfterEach is running");
    }

    @DisplayName("Test 1+3=4")
    @Test
    public void test(){
        System.out.println("test 1 is running");
        assertEquals(4,1+3);
    }

    @Disabled
    @DisplayName("Test 3*4=12")
    @Test
    public void test2(){
        System.out.println("test 2 is running");
        assertEquals(12, 3*4);
    }

}
