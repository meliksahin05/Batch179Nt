package practices.base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class TodosJsonPlaceHolder {

    protected RequestSpecification spec;
    @Before
    public void setUp(){

        String baseUrl = "https://jsonplaceholder.typicode.com";

        spec = new RequestSpecBuilder().setContentType(ContentType.JSON).setBaseUri(baseUrl).build();

    }
}
