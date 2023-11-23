package get_requests;

import base_urls.PetStoreBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get07 extends PetStoreBaseUrl {


        /*
    Given
        https://petstore.swagger.io/v2/pet/235
    When
         User send a GET request to the URL
    Then
        HTTP Status Code should be 200
    And
        Response content type is "application/json"
    And
        Response body should be like;
           {
            "id": 235,
            "category": {
                "id": 0,
                "name": "Aldo"
            },
            "name": "Dog",
            "photoUrls": [
                "string"
            ],
            "tags": [
                {
                    "id": 0,
                    "name": "Seven"
                },
                {
                    "id": 0,
                    "name": "Eight"
                }
            ],
            "status": "pending"
           }

     */


    @Test
    public void get(){


//        i) Set the Url
        spec.pathParams("first","pet",
                "second",235);
//        ii) Set the Expected Data
//        iii) Send Request And Get Response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

//        iv)  Do Assertions

        JsonPath json = response.jsonPath();
        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertEquals("application/json",response.contentType());
        assertEquals("Aldo",json.getString("category.name"));
        assertEquals("Dog",json.getString("name"));
        assertEquals("Seven",json.getString("tags[0].name"));
        assertEquals("string",json.getString("photoUrls[0]"));
    }



}
