package get_requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get01 {

    /*
            1) We will use Postman for Manuel Tests
            2) We will use Rest Assures for API automation testing
            3) To type automation scripts follow the following steps:
                a) Understand the requirements
                b) We will use "Gherkin Language" to type test scripts:
                   There are four keywords in  "Gherkin Language":
                   i) Given : used for pre conditions ( url, body, accept Type, content type, ....)
                   ii) When: used for action (get, post, put, ....)
                   iii) Then: used for assertions
                   iv) And: used for multiple usage of Keywords.
               c) Start to type Automation Script :
                    i) Set the Url
                    ii) Set the Expected Data
                    iii) Send Request And Get Response
                    iv)  Do Assertions
         */

/*
          Given
              https://restful-booker.herokuapp.com/booking/10
          When
              User sends a GET Request to the url
          Then
              HTTP Status Code should be 200
          And
              Content Type should be application/json
          And
              Status Line should be HTTP/1.1 200 OK
 */


    @Test
    public void get(){
//        i) Set the Url
                String url = "https://restful-booker.herokuapp.com/booking/10"; // base url den sonra gelen
//        slashtan sonrasi path parameterdir. Yani bu url de 2 tane path parameters vardir. First booking second 10

//        ii) Set the Expected Data  ----->>>>>>> We will do it later
//        iii) Send Request And Get Response
          Response response = given().when().get(url);
          response.prettyPrint();
//        iv)  Do Assertions
        response.then().  //after "then()" assertion starts...
                statusCode(200).    //HTTP Status Code should be 200
                contentType("application/json").  //Content Type should be application/json
                statusLine("HTTP/1.1 200 OK");    //Status Line should be HTTP/1.1 200 OK

        //Yukardaki assertions coming from rest assured assertions but you can also use assertions from jUnit

//        assertEquals(200,response.statusCode());  //assertions by jUnit


    }


    @Test
    public void get02(){
        String url = "https://restful-booker.herokuapp.com/booking/10";
        given().
                when().
                get(url).
                then().
                statusCode(200).
                statusLine("application/json").
                contentType(ContentType.JSON);

    }
}
