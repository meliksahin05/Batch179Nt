package get_requests;

import base_urls.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class Get12 extends GoRestBaseUrl {

        /*
        Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 10
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 10
        And
            We have at least one "active" status
        And
            "The Hon. Sarisha Gandhi", "Anasuya Khatri", "Chakravartee Gandhi MD" are among the users
        And
            The female users are less than or equals to male users
    */

    @Test
    public void get(){

        //Set the Url
        spec.pathParam("first","users");

        //Expected Data

        //Send the Request and get the response
        Response response = given(spec).when().get("{first}");
//        response.prettyPrint();

        //Do Assertions
        JsonPath json = response.jsonPath();

        // The value of "pagination limit" is 10

        int limit = json.getInt("meta.pagination.limit");

        assertEquals(10,limit);

        // The "current link" should be "https://gorest.co.in/public/v1/users?page=1"

        String expectedCurrentLink = "https://gorest.co.in/public/v1/users?page=1";
        String actualCurrentLink = json.getString("meta.pagination.links.current");

        assertEquals(expectedCurrentLink,actualCurrentLink);

//        The number of users should  be 10
        int usersNum = 10;
        int numOfUsers = json.getList("data.name").size();

        assertEquals(usersNum,numOfUsers);

//        We have at least one "active" status
        response.then().body("data.status",hasItem("active"));

        Boolean isActiveExist = json.getList("data.status").contains("active");

        assertTrue(isActiveExist);

//        "The Hon. Sarisha Gandhi", "Anasuya Khatri", "Chakravartee Gandhi MD" are among the users

        //1st way
        response.then()
                .body("data.name",hasItems("Govinda Talwar", "Prema Iyer Esq.", "Gopaal Mukhopadhyay"));

        //2nd way
        Boolean GovindaTalwarExist = json.getList("data.name").contains("Govinda Talwar");
        assertTrue(GovindaTalwarExist);

        Boolean premaExist = json.getList("data.name").contains("Prema Iyer Esq.");
        assertTrue(premaExist);

        Boolean gopaalExist = json.getList("data.name").contains("Gopaal Mukhopadhyay");
        assertTrue(gopaalExist);

        //3rd way
        List<String> nameList = json.getList("data.name");
        assertTrue(nameList.contains("Govinda Talwar"));
        assertTrue(nameList.contains("Prema Iyer Esq."));
        assertTrue(nameList.contains("Gopaal Mukhopadhyay"));

//        The male users are less than or equals to female users

        int femaleUsers = json.getList("data.findAll{it.gender =='female'}").size();
        System.out.println("femaleUsers = " + femaleUsers);

        int numOfMaleUsers = json.getList("data.findAll{it.gender == 'male'}").size();
        System.out.println("numOfMaleUsers = " + numOfMaleUsers);

        assertTrue(numOfMaleUsers <= femaleUsers);




    }

}
