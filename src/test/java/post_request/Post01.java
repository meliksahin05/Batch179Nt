package post_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post01 extends JsonPlaceHolderBaseUrl {

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
    public void post(){


//        i) Set the Url
         spec.pathParam("first","todos");
//        ii) Set the Expected Data
        String payLoad = "{\n" +
                "                 \"userId\": 55,\n" +
                "                 \"title\": \"Tidy your room\",\n" +
                "                 \"completed\": false\n" +
                "              }";

//        iii) Send Request And Get Response
        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();
//        iv)  Do Assertions
        JsonPath json = response.jsonPath();
        assertEquals(201,response.statusCode());
        assertEquals(55,json.getInt("userId"));
        assertEquals("Tidy your room",json.getString("title"));
        assertEquals(false,json.getBoolean("completed"));
        assertEquals(201,json.getInt("id")); //you can not check the id because the system assign the id so you can not never know the id

    }


    @Test
    public void postMap(){


//        i) Set the Url
        spec.pathParam("first","todos");
//        ii) Set the Expected Data
        Map<String,Object> payLoad = new HashMap<>();

        payLoad.put("userId",55);
        payLoad.put("title","Tidy your room");
        payLoad.put("completed",false);

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
