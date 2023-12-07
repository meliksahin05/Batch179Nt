package herokuapp_smoketest;


import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.HerokuPojo;

import static herokuapp_smoketest.C01CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C03UpdateBooking extends HerOkuAppBaseUrl {
 /*
    Given
        https://restful-booker.herokuapp.com/booking/{{bookingId}}
    And
        {
            "firstname" : "Tommy",
            "lastname" : "Hanks",
            "totalprice" : 113,
            "depositpaid" : false,
            "bookingdates" : {
                "checkin" : "2018-01-01",
                "checkout" : "2019-01-01"
            },
            "additionalneeds" : "Lunch"
            }
    When
        sent put request to the url
    Then
        status code is 200
    And
        body: {
                "firstname": "Tommy",
                "lastname": "Hanks",
                "totalprice": 113,
                "depositpaid": false,
                "bookingdates": {
                    "checkin": "2018-01-01",
                    "checkout": "2019-01-01"
                },
                "additionalneeds": "Lunch"
                   }

     */

    @Test
    public void put(){

        //Set the Url
        spec.pathParams("first","booking"
                ,"second",bookingId);

        //Expected Data
        BookingDatesPojo bookingDates = new BookingDatesPojo("2018-01-01","2019-01-01");
        HerokuPojo payLoad = new HerokuPojo("Tommy","Hanks",113,false,bookingDates,"Lunch");
        System.out.println(payLoad);

        //Send the request and get the response
        Response response = given(spec).body(payLoad).when().put("{first}/{second}");
        response.prettyPrint();

        //Do assertions
        HerokuPojo actualData = response.as(HerokuPojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(payLoad.getFirstname(),actualData.getFirstname());
        assertEquals(payLoad.getLastname(),actualData.getLastname());
        assertEquals(payLoad.getTotalprice(),actualData.getTotalprice());
        assertEquals(payLoad.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(bookingDates.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(payLoad.getAdditionalneeds(),actualData.getAdditionalneeds());






    }
}