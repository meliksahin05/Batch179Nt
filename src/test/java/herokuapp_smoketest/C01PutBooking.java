package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.HerokuPojo;
import pojos.HerokuResponsePojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class C01PutBooking extends HerOkuAppBaseUrl {


    public static int bookingId;

    /*
    Given
         https://restful-booker.herokuapp.com/booking

    And
        {
         "firstname" : "Tom ",
         "lastname" : "Hanks",
          "totalprice" : 111,
          "depositpaid" : true,
           "bookingdates" : {
                "checkin" : "2018-01-01",
               "checkout" : "2019-01-01"
            },
           "additionalneeds" : "Breakfast"
           }
    When
        User sends post request
    Then
        Statuscode is 200
    And
        body: {
                "bookingid": 2146,
                "booking": {
                    "firstname": "Tom",
                    "lastname": "Hanks",
                    "totalprice": 111,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2018-01-01",
                        "checkout": "2019-01-01"
                    },
                    "additionalneeds": "Breakfast"
                }
            }

     */

    @Test
    public void post(){

        //Set the Url
        spec.pathParam("first","booking");

        //Expected Data

        BookingDatesPojo booking = new BookingDatesPojo("2018-01-01","2019-01-01");
        HerokuPojo payLoad = new HerokuPojo("Tom","Hanks",
                111,true,booking,"Breakfast");
        System.out.println("payLoad = " + payLoad);

        //Send the request and get the response

        Response response = given(spec).body(payLoad).when().post("{first}");
        response.prettyPrint();

        //Do Assertions
        HerokuResponsePojo actualData = convertJsonToJava(response.asString(), HerokuResponsePojo.class);
        response.then().statusCode(200);

        assertEquals(payLoad.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(payLoad.getLastname(),actualData.getBooking().getLastname());
        assertEquals(payLoad.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(payLoad.getDepositpaid(),actualData.getBooking().getDepositpaid());
        assertEquals(booking.getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(booking.getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(payLoad.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());


        bookingId = response.jsonPath().getInt("bookingid");
    }


}
