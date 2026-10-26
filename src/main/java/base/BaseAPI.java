
package base;

import io.restassured.RestAssured;
import utils.ConfigReader;

public class BaseAPI {

    protected void setupAPI() {

        RestAssured.baseURI = ConfigReader.getProperty("baseUrl");
    }
}

