
package endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostEndpoints {

    public static Response createPost(
            Map<String, Object> payload) {

        return given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post("/posts");
    }

    public static Response getAllPosts() {

        return given()
                .when()
                .get("/posts");
    }

    public static Response getPost(int id) {

        return given()
                .pathParam("id", id)
                .when()
                .get("/posts/{id}");
    }

    public static Response updatePost(
            int id, Map<String, Object> payload) {

        return given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(payload)
                .when()
                .put("/posts/{id}");
    }

    public static Response patchPost(
            int id, Map<String, Object> payload) {

        return given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .body(payload)
                .when()
                .patch("/posts/{id}");
    }

    public static Response deletePost(int id) {

        return given()
                .pathParam("id", id)
                .when()
                .delete("/posts/{id}");
    }
}
