package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.HerokuPojo;

import java.util.HashMap;
import java.util.Map;

import static herokuapp_smoketest.C01CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static test_data.HerokuAppTestData.bookingDatesMapper;
import static test_data.HerokuAppTestData.herokuAppMapper;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class C04PartiallyUpdateBooking extends HerOkuAppBaseUrl {

 /*
    Given
        https://restful-booker.herokuapp.com/booking/{{bookingId}}
    And
        {
            "firstname" : "Brad",
            "lastname" : "Pit",
            "additionalneeds": "Lemonate"
        }
    When
        sent patch request to the url
    Then
        status code is 200
    And
        body: {
                "firstname": "Brad",
                "lastname": "Pit",
                "totalprice": 113,
                "depositpaid": false,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Lemonate"
               }
     */

    @Test
    public void patch(){

        //Set the url
        spec.pathParams("first","booking","second",bookingId);

        //Expected Data
        Map<String ,Object> payload = herokuAppMapper("Brad","Pitt",
                                                      null,null,
                                                      null,"Lemonate");

        Map<String,String> bookingDates = bookingDatesMapper("2018-01-01", "2019-01-01");
        Map<String, Object> expectedData = herokuAppMapper("Brad","Pitt",
                113,false,bookingDates,"Lemonate");

        //2nd way of creating expected data
        BookingDatesPojo bookingDates2 = new BookingDatesPojo("2018-01-01","2019-01-01");
        HerokuPojo expectedData2 = new HerokuPojo("Brad","Pitt",113,false, bookingDates2,"Lemonate");

        //Send the Request and get the response
        Response response = given(spec).body(payload).when().patch("{first}/{second}");
        response.prettyPrint();

        //Do Assertions
        response.then().statusCode(200);


        //1st way of assertion with Map
        Map<String, Object> actualData = convertJsonToJava(response.asString(), HashMap.class);
        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        assertEquals(payload.get("checkin"),actualData.get("bookingdates.checkin"));
        assertEquals(payload.get("checkout"),actualData.get("bookingdates.checkout"));
        assertEquals(expectedData.get("additionalneeds"),actualData.get("additionalneeds"));

        //2nd way of assertion with Pojo
        HerokuPojo actualData2 = response.as(HerokuPojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData2.getFirstname(), actualData2.getFirstname());
        assertEquals(expectedData2.getLastname(), actualData2.getLastname());
        assertEquals(expectedData2.getTotalprice(), actualData2.getTotalprice());
        assertEquals(expectedData2.getDepositpaid(), actualData2.getDepositpaid());
        assertEquals(bookingDates2.getCheckin(), actualData2.getBookingdates().getCheckin());
        assertEquals(bookingDates2.getCheckout(), actualData2.getBookingdates().getCheckout());
        assertEquals(expectedData2.getAdditionalneeds(), actualData2.getAdditionalneeds());










    }
}
