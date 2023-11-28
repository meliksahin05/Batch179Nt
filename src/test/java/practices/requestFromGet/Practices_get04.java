package practices.requestFromGet;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import practices.base_urls.TodosJsonPlaceHolder;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class Practices_get04 extends TodosJsonPlaceHolder {

        /*
        Given
            https://jsonplaceholder.typicode.com/todos
        And
            Accept type is “application/json”
        When
           I send a GET request to the Url
        Then
            HTTP Status Code should be 200
        And
            Response format should be "application/json"
        And
            There should be 200 todos
        And
            "quis eius est sint explicabo" should be one of the todos title
        And
            2, 7, and 9 should be among the userIds
     */

    @Test
    public void get(){

//        i) Set the Url
        spec.when().pathParam("first","todos");
//        ii) Set the Expected Data
//        iii) Send Request And Get Response
        Response response = given(spec).when().get("{first}");
        response.prettyPrint();
//        iv)  Do Assertions
        response.then().statusCode(200).
                contentType(ContentType.JSON).
                body("title", hasSize(200),
                "title", hasItem("quis eius est sint explicabo"),
                "userId", hasItems(2,7,9));


    }
}

