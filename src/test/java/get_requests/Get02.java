package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class Get02 {

//    Given
//    https://restful-booker.herokuapp.com/booking/0
//    When
//    User send a GET Request to the url
//    Then
//    HTTP Status code should be 404
//    And
//    Status Line should be HTTP/1.1 404 Not Found
//    And
//    Response body contains "Not Found"
//    And
//    Response body does not contain "TechProEd"
//    And
//    Server is "Cowboy"

    @Test
    public void get(){
// i) Set the Url
        String url = "https://restful-booker.herokuapp.com/booking/0";

        // ii) Set the Expected Data
        // iii) Send Request And Get Response
        Response response = given().get(url);
        response.prettyPrint();

        // iv)  Do Assertions
        response.
                then().
                statusCode(404).//HTTP Status code should be 404
                statusLine("HTTP/1.1 404 Not Found").
                header("server","Cowboy");// Status Line should be HTTP/1.1 404 Not Found

        // Response body contains "Not Found"
        assertTrue("Response body does not contain Not Found",response.asString().contains("Not Found"));

        // Response body does not contain "TechProEd"
        assertFalse("Response body does not contain TechProEd",response.asString().contains("TechProEd"));

        // Server is "Cowboy"
        assertEquals("Cowboy",response.header("server"));
    }
}
