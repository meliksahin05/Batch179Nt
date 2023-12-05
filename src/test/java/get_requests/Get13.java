package get_requests;

import base_urls.SpacexDataBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get13 extends SpacexDataBaseUrl {

                /*
    Given
        https://api.spacexdata.com/v3/launches
    When
        User sends Get Request to the Url
    Then
        Status code is 200
    And
        There are  111 launches
    And
        "Falcon 1" and "Falcon 9" are among the rocket names
    And
        25 launches are fired on 2020
    And
        "Trailblazer" is one of the  failed mission name



     */

    @Test
    public void get(){

        // Set Url:
        spec.pathParam("first","launches");

        // Set Expected Data:

        // Sent Request and Get Response:
        Response response =  given(spec).when().get("{first}");
//        response.prettyPrint();

        //Do assertions
        JsonPath json = response.jsonPath();

//        Status code is 200
        response.then().statusCode(200);

//        There are  111 launches
        int numberOfLaunches = json.getList("flight_number").size();
        assertEquals(111,numberOfLaunches);

//        "Falcon 1" and "Falcon 9" are among the rocket names
        response.then().body("rocket.rocket_name", hasItems("Falcon 1","Falcon 9"));

        List<String> rocketNamesList = json.getList("rocket.rocket_name");
        assertTrue(rocketNamesList.contains("Falcon 1"));
        assertTrue(rocketNamesList.contains("Falcon 9"));

//        25 launches are fired on 2020
        int firedLaunchesIn2020 = json.getList("findAll{it.launch_year == '2020'}").size();
        System.out.println("firedLaunchesIn2020 = " + firedLaunchesIn2020);
        assertEquals(25, firedLaunchesIn2020);


    }
}
