package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

import static utils.GMIBankAuthentication.generateToken;

public class GMIBankBaseUrl {

    protected RequestSpecification spec;
    @Before  // "Before" makes the method to be executed befare every @Test method
    public void setUp(){
        String baseUrl = "https://gmibank.com";
        spec = new RequestSpecBuilder().
                addHeader("Authorization","Bearer " +generateToken()).
                setContentType(ContentType.JSON).setBaseUri(baseUrl).build();
    }
}
