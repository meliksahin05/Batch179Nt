package post_request;

import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.JsonPlaceHolderTestData.jsonPlaceHolderMapper;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class Post06ObjectMapperMap extends JsonPlaceHolderBaseUrl {

      /*
   Given
     1) https://jsonplaceholder.typicode.com/todos
     2) {
         "userId": 55,
         "title": "Tidy your room",
         "completed": false
         }


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
    public void post() throws JsonProcessingException {

        //Set the Url
        spec.pathParam("first","todos");

        //Expected Data
        Map<String , Object> payLoad = jsonPlaceHolderMapper(55,"Tidy your room",false);


        //Send the request and get the response
        Response response = given(spec).body(payLoad).when().post("{first}");
//        response.prettyPrint();

        //Do Assertions
        // Map<String,Object> actualData = response.as(HashMap.class);
        Map<String,Object> actualData = new ObjectMapper().readValue(response.asString(), HashMap.class);
        System.out.println("actualData = " + actualData);
        assertEquals(payLoad.get("userId"),actualData.get("userId"));
        assertEquals(payLoad.get("title"),actualData.get("title"));
        assertEquals(payLoad.get("completed"),actualData.get("completed"));


        Map<String,Object> actualData2 = convertJsonToJava(response.asString(), HashMap.class);
        assertEquals(payLoad.get("userId"),actualData2.get("userId"));
        assertEquals(payLoad.get("title"),actualData2.get("title"));
        assertEquals(payLoad.get("completed"),actualData2.get("completed"));



    }
}
