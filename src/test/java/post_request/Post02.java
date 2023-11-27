package post_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.JsonPlaceHolderTestData.jsonPlaceHolderMapper;

public class Post02 extends JsonPlaceHolderBaseUrl {

              /*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2)  {
                 "userId": 55,
                 "title": "Tidy your room",
                 "completed": false
              }
        When
            I send POST Request to the Url

        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void postMap(){


//        i) Set the Url
        spec.pathParam("first","todos");
//        ii) Set the Expected Data
        Map<String,Object> payLoad = jsonPlaceHolderMapper(55,"Tidy your room",false);



        System.out.println("payLoad = " + payLoad);
//        iii) Send Request And Get Response

        Response response = given(spec).body(payLoad).when().post("{first}"); // At this stage Serialization is taking place
        response.prettyPrint(); // Serialization : process of converting Java Object to Json Object

//        iv)  Do Assertions
//        JsonPath json = response.jsonPath();
//        assertEquals(payLoad.get("title"),json.getString("title"));

        Map<String, Object> actualData = response.as(HashMap.class); // De-serialization is taking place: process of converting Json Object to Java Object.
        assertEquals(payLoad.get("title"),actualData.get("title"));
        assertEquals(payLoad.get("userId"),actualData.get("userId"));
        assertEquals(payLoad.get("completed"),actualData.get("completed"));




    }
}
