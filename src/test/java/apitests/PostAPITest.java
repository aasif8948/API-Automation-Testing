
package apitests;

import base.BaseAPI;
import endpoints.PostEndpoints;
import payloads.PostPayload;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class PostAPITest extends BaseAPI {

    @BeforeClass
    public void setAPI() {
        setupAPI();
    }

    // 1. POST API
    @Test
    public void testCreatePost() {

        Map<String, Object> payload =
                PostPayload.createPost(
                        "API Automation",
                        "Learning REST Assured", 1);

        Response response = PostEndpoints.createPost(payload);
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("title"), "API Automation");
        System.out.println("POST API PASSED");
    }

    // 2. GET All Posts
    @Test
    public void testGetAllPosts() {

        Response response = PostEndpoints.getAllPosts();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertTrue(response.jsonPath().getList("$").size() > 0);
        System.out.println("GET ALL API PASSED");
    }

    // 3. GET Single Post
    @Test
    public void testGetSinglePost() {

        Response response = PostEndpoints.getPost(1);
        response.prettyPrint();
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), 1);
        System.out.println("GET SINGLE API PASSED");
    }

    // 4. PUT API
    @Test
    public void testUpdatePost() {

        Map<String, Object> payload = PostPayload.createPost("Updated Title", "Updated Body", 1);
        Response response = PostEndpoints.updatePost(1, payload);
        response.prettyPrint();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("title"), "Updated Title");
        System.out.println("PUT API PASSED");
    }

    // 5. PATCH API
    @Test
    public void testPatchPost() {

        Map<String, Object> payload = Map.of("title", "Partially Updated");

        Response response = PostEndpoints.patchPost(1, payload);
        response.prettyPrint();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("title"), "Partially Updated");
        System.out.println("PATCH API PASSED");
    }

    // 6. DELETE API
    @Test
    public void testDeletePost() {

        Response response = PostEndpoints.deletePost(1);
        Assert.assertEquals(response.statusCode(), 200);
        System.out.println("DELETE API PASSED");
    }

    // 7. Negative Testing
    @Test
    public void testInvalidPostId() {

        Response response = PostEndpoints.getPost(999999);
        Assert.assertEquals(response.statusCode(), 404);
        System.out.println("NEGATIVE API PASSED");
    }
}
