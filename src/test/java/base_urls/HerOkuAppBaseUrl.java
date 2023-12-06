package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

import static utils.HerokuAppAuthentication.generateToken;

public class HerOkuAppBaseUrl {

    protected RequestSpecification spec;
    @Before  // "Before" makes the method to be executed befare every @Test method
    public void setUp(){
        String baseUrl = "https://restful-booker.herokuapp.com";

        spec = new RequestSpecBuilder().
                addHeader("Cookie","token="+generateToken()).
                setContentType(ContentType.JSON).
                setBaseUri(baseUrl).
                build();
    }
}
