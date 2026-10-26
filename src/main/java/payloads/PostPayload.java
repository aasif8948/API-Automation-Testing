
package payloads;

import java.util.HashMap;
import java.util.Map;

public class PostPayload {

    public static Map<String, Object> createPost(
            String title, String body, int userId) {

        Map<String, Object> payload = new HashMap<>();

        payload.put("title", title);
        payload.put("body", body);
        payload.put("userId", userId);

        return payload;
    }
}
