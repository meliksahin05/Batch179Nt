package put_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put01 extends JsonPlaceHolderBaseUrl {


         /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "userId": 21,
                 "title": "Wash the dishes",
                 "completed": false
               }
        When
	 		I send PUT Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 21,
									    "title": "Wash the dishes",
									    "completed": false
									   }
     */

    @Test
    public void put(){

        //Set the Url
        spec.pathParams("first", "todos", "second",198);

//      ii) Set the Expected Data
        Map<String, Object> payLoad = JsonPlaceHolderTestData.jsonPlaceHolderMapper(21,"Wash the dishes",false);

        //Send a request and get the response
        Response response = given(spec).body(payLoad).when().put("{first}/{second}");
        response.prettyPrint();

        //Do Assertions

//      i)convert the json format to map format
        Map<String, Object> actualData = response.as(HashMap.class);
//      ii)assert expected data with actualData

        assertEquals(200,response.statusCode());
        assertEquals(payLoad.get("userId"),actualData.get("userId"));
        assertEquals(payLoad.get("title"),actualData.get("title"));
        assertEquals(payLoad.get("completed"),actualData.get("completed"));

    }

}
