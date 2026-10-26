
package payloads;

import java.util.HashMap;
import java.util.Map;

public class UserPayload {

    public static Map<String, Object> createUser(
            String username,
            String password,
            String firstName) {

        Map<String, Object> user = new HashMap<>();

        user.put("id", System.currentTimeMillis());
        user.put("username", username);
        user.put("password", password);
        user.put("firstName", firstName);
        user.put("lastName", "Kumar");
        user.put("email", username + "@example.com");
        user.put("phone", "1234567890");
        user.put("userStatus", 1);

        return user;
    }
}
