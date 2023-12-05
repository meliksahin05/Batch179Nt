package delete_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;
import utils.ObjectMapperUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static test_data.JsonPlaceHolderTestData.jsonPlaceHolderMapper;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class Delete01 extends JsonPlaceHolderBaseUrl {

        /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */

    @Test
    public void delete(){

        //Set the url
        spec.pathParams("first","todos","second",198);

        //Expected Data
        Map<String , Object> expectedData = jsonPlaceHolderMapper(null,null,null);
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).when().delete("{first}/{second}");
        response.prettyPrint();

        //Do Assertions
       Map<String, Object> actualData = convertJsonToJava(response.asString(), HashMap.class);
        System.out.println("actualData = " + actualData);

       assertEquals(200, response.statusCode());

       //First Way
        assertEquals(expectedData,actualData);

        //Second Way
        assertTrue(actualData.isEmpty());

        //Third Way
        System.out.println(actualData.get(0));
        assertEquals(null,actualData.get(0));











    }
}
