
package apitests;

import base.BaseAPI;
import endpoints.PostEndpoints;
import payloads.PostPayload;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class PostDataDrivenTest extends BaseAPI {

    @BeforeClass
    public void setAPI() {
        setupAPI();
    }

    @DataProvider(name = "postData")
    public Object[][] getPostData() {

        return new Object[][] {
                {"Java Testing", "Java API Body", 1},
                {"Selenium Testing", "Selenium API Body", 2},
                {"REST Assured", "REST API Body", 3}
        };
    }

    @Test(dataProvider = "postData")
    public void testCreatePostWithMultipleData(
            String title, String body, int userId) {

        Map<String, Object> payload = PostPayload.createPost(title, body, userId);

        Response response = PostEndpoints.createPost(payload);
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("title"), title);
        System.out.println("Test passed: " + title);
    }
}
