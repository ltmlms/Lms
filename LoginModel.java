import java.util.HashMap;
import java.util.Map;

public class LoginModel {
    private Map<String, String> userCredentials = new HashMap<>();

    public LoginModel() {
        // Example credentials
        userCredentials.put("Admin", "Pass@123");
    }

    public boolean authenticate(String username, String password) {
        return userCredentials.containsKey(username) && userCredentials.get(username).equals(password);
    }
}
