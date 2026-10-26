
package endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserEndpoints {

    // POST - Create User

    public static Response createUser(
            Map<String, Object> payload) {

        return given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.CREATE_USER)
                .then()
                .log().all()
                .extract()
                .response();
    }


    // GET - Login User
    public static Response loginUser(
            String username, String password) {

        return given().queryParam("username", username).queryParam("password", password).when().get(Routes.LOGIN_USER);
    }

    // GET - Get User Details
    public static Response getUser(String username) {

        return given().pathParam("username", username).when().get(Routes.GET_USER);
    }

    // PUT - Update User
    public static Response updateUser(
            String username,
            Map<String, Object> payload) {

        return given().contentType(ContentType.JSON).pathParam("username", username).body(payload).when().put(Routes.UPDATE_USER);
    }

    // DELETE - Delete User
    public static Response deleteUser(String username) {

        return given().pathParam("username", username).when().delete(Routes.DELETE_USER);
    }

    // GET - Logout User
    public static Response logoutUser() {

        return given().when().get(Routes.LOGOUT_USER);
    }
}
