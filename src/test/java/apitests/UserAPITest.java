package apitests;


import base.BaseAPI;
//package apitests;
import endpoints.UserEndpoints;
import payloads.UserPayload;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

public class UserAPITest extends BaseAPI {



    @BeforeClass
    public void setAPI() {
        setupAPI();
    }



    @Test
    public void testUserWorkflow() {

        // Unique username for every execution
        String username = "test" + System.currentTimeMillis();
        String password = "Test@123";

        Map<String, Object> payload = UserPayload.createUser(username, password, "Rahul");

        boolean created = false;

        try {


            Response create = UserEndpoints.createUser(payload);

            System.out.println("===== CREATE USER RESPONSE =====");

            System.out.println("Status Code: " + create.getStatusCode());

            System.out.println("Response Headers: " + create.getHeaders());

            System.out.println("Response Body: " + create.asString());

            Assert.assertEquals(create.getStatusCode(), 200, "Create User API failed");
            created = true;

            // TEST 2: Get User



            Response get = UserEndpoints.getUser(username);
            get.prettyPrint();
            Assert.assertEquals(get.statusCode(), 200);
            Assert.assertEquals(get.jsonPath().getString("username"), username);

            // TEST 3: Login User



            Response login = UserEndpoints.loginUser(username, password);
            login.prettyPrint();
            Assert.assertEquals(login.statusCode(), 200);

            // TEST 4: Update User


            payload.put("firstName", "Amit");
            Response update = UserEndpoints.updateUser(username, payload);
            update.prettyPrint();
            Assert.assertEquals(update.statusCode(), 200);

            // TEST 5: Verify Update


            Response verify = UserEndpoints.getUser(username);
            Assert.assertEquals(verify.statusCode(), 200);
            Assert.assertEquals(verify.jsonPath().getString("firstName"), "Amit");

            // TEST 6: Logout User
            Response logout = UserEndpoints.logoutUser();
            Assert.assertEquals(logout.statusCode(), 200);

        } finally {

            // TEST 7: Delete User / Cleanup
            if (created) {
                Response delete = UserEndpoints.deleteUser(username);
                System.out.println("Cleanup status: " + delete.statusCode());
                Assert.assertEquals(delete.statusCode(), 200);
            }
        }
    }
}
