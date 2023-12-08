package gmibank_api;

import base_urls.GMIBankBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.gmibank_pojos.CountryPojo;
import pojos.gmibank_pojos.StatePojo;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class CreateCountryPojo extends GMIBankBaseUrl {

        /*
    Given
        https://gmibank.com/api/tp-countries
    And
        {
            "id": null,
            "name": "Banana Republic",
            "states": [
                {
                    "id": 0,
                    "name": "Apple",
                    "tpcountry": null
                },
                {
                    "id": 1,
                    "name": "Orange",
                    "tpcountry": null
                },
                {
                    "id": 2,
                    "name": "Peach",
                    "tpcountry": null
                }
            ]
        }
    When
        Send post request
    Then
        Status code is 201
    And
        Response body is like:
        {
            "id": 189865,
            "name": "Banana Republic",
            "states": [
                {
                    "id": 0,
                    "name": "Apple",
                    "tpcountry": null
                },
                {
                    "id": 1,
                    "name": "Orange",
                    "tpcountry": null
                },
                {
                    "id": 2,
                    "name": "Peach",
                    "tpcountry": null
                }
            ]
        }
     */

    @Test
    public void createCountry(){

        //Set the Url
        spec.pathParams("first","api"
                ,"second","tp-countries");

        //Expected Data
        StatePojo statePojo1 = new StatePojo(0,"Apple",null);
        StatePojo statePojo2 = new StatePojo(1,"Orange",null);
        StatePojo statePojo3 = new StatePojo(2,"Peach",null);

        List<StatePojo> statePojos = new ArrayList<>();
        statePojos.add(statePojo1);
        statePojos.add(statePojo2);
        statePojos.add(statePojo3);
        System.out.println("states = " + statePojos);

//        states.get(0).getName(); //Yukaridaki list yapmamizin sebebi hem payLoad a list eklicez parametere kismina
                               // hemde istedigmiz element ulasabiliyoruz boyle kolaylikla

        CountryPojo payLoad = new CountryPojo(null,"Banana Republic", statePojos);
        System.out.println("payLoad = " + payLoad);


        //Send the request and get the response
        Response response = given(spec).body(payLoad).when().post("{first}/{second}");
        response.prettyPrint();


        //Do Assertions
       CountryPojo actualData = convertJsonToJava(response.asString(), CountryPojo.class);
       response.then().statusCode(201);

       assertEquals(201, response.statusCode());
       assertEquals(payLoad.getName(), actualData.getName());
       assertEquals(statePojo1.getId(), actualData.getStates().get(0).getId());
       assertEquals(statePojo1.getName(), actualData.getStates().get(0).getName());
       assertEquals(statePojo1.getTpcountry(), actualData.getStates().get(0).getTpcountry());

        assertEquals(statePojo2.getId(), actualData.getStates().get(1).getId());
        assertEquals(statePojo2.getName(), actualData.getStates().get(1).getName());
        assertEquals(statePojo2.getTpcountry(), actualData.getStates().get(1).getTpcountry());

        assertEquals(statePojo3.getId(), actualData.getStates().get(2).getId());
        assertEquals(statePojo3.getName(), actualData.getStates().get(2).getName());
        assertEquals(statePojo3.getTpcountry(), actualData.getStates().get(2).getTpcountry());


        //1st Validation: response.then() method


        //2nd Validation: jsonPath()


        //3rd Validation: as() method


        //4th Validation as() method (By converting response to Map)


        //5th Validation-->  Map + Object Mapper


        //6th Validation--> Best Practice: Pojo Class + Object Mapper



    }
}
