package practices.requestFromGet;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Practices_get03 {

//    Given
//    https://jsonplaceholder.typicode.com/todos/23
//    When
//    User send GET Request to the URL
//            Then
//    HTTP Status Code should be 200
//    And
//    Response format should be “application/json”
//    And
//    “title” is “et itaque necessitatibus maxime molestiae qui quas velit”,
//    And
//    “completed” is false
//    And
//    “userId” is 2

    @Test
    public void get(){

//        i) Set the Url
        String url = "https://jsonplaceholder.typicode.com/todos/23";
//        ii) Set the Expected Data
//        iii) Send Request And Get Response
        Response response = given().when().get(url);
        response.prettyPrint();
//        iv)  Do Assertions
        //Alttaki yaptigim assertion bir soft assertiondir. Hard assertion yapmak istiyorsan her karsilastiracagin sey icin body eklemen lazim

        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                "completed",equalTo(false),"userId",equalTo(2));

//        Hard Assertion
        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed" , equalTo(false)).
                body("userId" ,equalTo(2));
    }
}
