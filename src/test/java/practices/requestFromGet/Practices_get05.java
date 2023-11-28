package practices.requestFromGet;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import practices.base_urls.Restful_booker;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class Practices_get05 extends Restful_booker {

            /*
       Given
           https://restful-booker.herokuapp.com/booking
       When
           User sends get request to the URL
       Then
           Status code is 200
         And
           Among the data there should be someone whose firstname is "John" and lastname is "Smith"
    */

    @Test
    public void get(){

//        i) Set the Url
        spec.pathParam("first", "booking").
                queryParams("firstname","John","second","Smith");
//        ii) Set the Expected Data
//        iii) Send Request And Get Response
        Response response = given(spec).when().get("{first}");
        response.prettyPrint(); //Now i have john Smith data

//        iv)  Do Assertions
        //3 yol var John Smithin olup olmadigini kontrol etmek icin
        response.then().
                statusCode(200).
                body("bookingid",hasSize(greaterThan(0)));

        response.then().
                statusCode(200).
                body(containsString("bookingid"));

        assertTrue(response.asString().contains("bookingid"));



    }

}
