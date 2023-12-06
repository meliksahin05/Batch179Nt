package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.HerokuPojo;

import static herokuapp_smoketest.C01PutBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class C02GetBooking extends HerOkuAppBaseUrl {

    @Test
    public void get(){

        //Set The Url
        spec.pathParams("first","booking","second",bookingId);

        //Expected Data
        BookingDatesPojo booking = new BookingDatesPojo("2018-01-01","2019-01-01");
        HerokuPojo expectedData = new HerokuPojo("Tom","Hanks",
                111 ,true,booking,"Breakfast");
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        //Do Assertions
//        HerokuPojo actualData = response.as(HerokuPojo.class);
        HerokuPojo actualData = convertJsonToJava(response.asString(), HerokuPojo.class);
        assertEquals(expectedData.getFirstname(),actualData.getFirstname());
        assertEquals(expectedData.getLastname(),actualData.getLastname());
        assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
        assertEquals(booking.getCheckin(),actualData.getBookingdates().getCheckin());
        assertEquals(booking.getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds()  ,actualData.getAdditionalneeds());





    }
}
