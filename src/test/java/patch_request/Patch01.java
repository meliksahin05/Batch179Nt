package patch_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.JsonPlaceHolderTestData.jsonPlaceHolderMapper;

public class Patch01 extends JsonPlaceHolderBaseUrl {

        /*
     Given
         1) https://jsonplaceholder.typicode.com/todos/198
         2) {
              "title": "Read the books"
            }
     When
          I send PATCH Request to the Url
     Then
           Status code is 200
           And response body is like   {
                                         "userId": 10,
                                         "title": "Read the books",
                                         "completed": true,
                                         "id": 198
                                        }
  */

    @Test
    public void patch01(){

        //Set the Url;
        spec.pathParams("first","todos","second",198);

        //Set expected Data
        Map<String, Object> payLoad = jsonPlaceHolderMapper(null,"Read the books",null);
        System.out.println("payLoad = " + payLoad);

        //Send a request and get the response
        Response response = given(spec).body(payLoad).when().patch("{first}/{second}"); //burda map oldugundan ve dependecy de bizim json-datablind oldugundan seriazaliton yapiyor
        response.prettyPrint();
        payLoad.put("userId",10);
        payLoad.put("completed",true);

        //Do Assertions
//        JsonPath json = response.jsonPath();
//        assertEquals(payLoad.get("title") ,json.getString("title")); //Assert ederken farkli iki data turu kullandin

        Map<String ,Object> actualData = response.as(HashMap.class); // De-Seriazalitation process happening here
        assertEquals(200,response.statusCode());
        assertEquals(payLoad.get("userId"),actualData.get("userId"));
        assertEquals(payLoad.get("title"),actualData.get("title"));
        assertEquals(payLoad.get("completed"),actualData.get("completed"));





    }
}
