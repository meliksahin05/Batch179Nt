package post_request;

import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class Post07ObjectMapperPojo extends JsonPlaceHolderBaseUrl {

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

        //Set the url
        spec.pathParam("first","todos");

        //Expected Data
        JsonPlaceHolderPojo payLoad = new JsonPlaceHolderPojo(55,"Tidy your room",false);

        //Send the request and get the response
        Response response = given(spec).body(payLoad).when().post("{first}");
//        response.prettyPrint();

        // Do Assertions
        assertEquals(201,response.statusCode());
        JsonPlaceHolderPojo actualData = new ObjectMapper().readValue(response.asString(), JsonPlaceHolderPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(payLoad.getUserId(),actualData.getUserId());
        assertEquals(payLoad.getTitle(),actualData.getTitle());
        assertEquals(payLoad.getCompleted(),actualData.getCompleted());

        JsonPlaceHolderPojo actualData2 = convertJsonToJava(response.asString(), JsonPlaceHolderPojo.class);
        assertEquals(payLoad.getUserId(),actualData2.getUserId());
        assertEquals(payLoad.getTitle(),actualData2.getTitle());
        assertEquals(payLoad.getCompleted(),actualData2.getCompleted());

    }
}
