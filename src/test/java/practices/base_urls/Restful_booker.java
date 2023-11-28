package practices.base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class Restful_booker {

    protected RequestSpecification spec;
    @Before
    public void setUp(){

        String baseUrl = "https://restful-booker.herokuapp.com";

        spec = new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri(baseUrl).build();
    }
}
