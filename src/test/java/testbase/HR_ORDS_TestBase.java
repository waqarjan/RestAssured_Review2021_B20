package testbase;

import Utility.ConfigurationReader;
import Utility.DB_Utility;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.util.Locale;

import static io.restassured.RestAssured.*;

public class HR_ORDS_TestBase {  //this class will set up baseURI, basePath and make a connection between configuration.properties file and our test class.


    @BeforeAll
    public static void setUp(){
        baseURI = ConfigurationReader.getProperty("ords.baseURL");
        basePath = ConfigurationReader.getProperty("ords.basePath");

        //Create Database connection here

        DB_Utility.createConnection(
                ConfigurationReader.getProperty("ords.baseURL"),
                ConfigurationReader.getProperty("hr.database.username"),
                ConfigurationReader.getProperty("ords.database.password")
        );

        DB_Utility.createConnection();
        DB_Utility.createConnection();
    }

    @AfterAll
    public static void tearDown(){
        reset();
        //destroy DB connection here
        DB_Utility.destroy();
    }


}
