package post_request;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.HerokuPojo;
import pojos.HerokuResponsePojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class
Post05 extends HerOkuAppBaseUrl {

            /*
   Given
    1)  https://restful-booker.herokuapp.com/booking
    2)   {
          "firstname": "John",
          "lastname": "Doe",
          "totalprice": 999,
          "depositpaid": true,
          "bookingdates": {
              "checkin": "2021-09-21",
              "checkout": "2021-12-21"
           },
           "additionalneeds": "Breakfast"
          }
  When
       I send POST Request to the URL
   Then
       Status code is 200
   And
       Response body is like {
                               "bookingid": 4319,
                               "booking" :{
                                  "firstname": "John",
                                  "lastname": "Doe",
                                  "totalprice": 999,
                                  "depositpaid": true,
                                  "bookingdates": {
                                      "checkin": "2021-09-21",
                                      "checkout": "2021-12-21"
                                  },
                                  "additionalneeds": "Breakfast"
                               }
                            }
*/


    @Test
    public void post() {

        //Set the Url
        spec.pathParam("first", "booking");

        //Expected Data
        BookingDatesPojo bookingDates = new BookingDatesPojo("2021-09-21","2021-12-21");
        HerokuPojo payLoad = new HerokuPojo("John","Doe",999,true,bookingDates,"Breakfast");


        //Send the Request and get the response
        Response response = given(spec).body(payLoad).when().post("{first}");
//        response.prettyPrint();


        //Do Assertions
        JsonPath json = response.jsonPath();
//        assertEquals(payLoad.getFirstname(),json.getString("booking.firstname")); // bu assert pass oluyor

       HerokuResponsePojo actualData = response.as(HerokuResponsePojo.class); //De-serilization

        assertEquals(payLoad.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(payLoad.getLastname(),actualData.getBooking().getLastname());
        assertEquals(payLoad.getTotalprice(),actualData.getBooking().getTotalprice());
        assertEquals(payLoad.getDepositpaid(),actualData.getBooking().getDepositpaid());

        //1st wat
        assertEquals(payLoad.getBookingdates().getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(payLoad.getBookingdates().getCheckout(),actualData.getBooking().getBookingdates().getCheckout());

        //2nd way
        assertEquals(bookingDates.getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingDates.getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(payLoad.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());






    }
}
