import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password; // In a real application, this should be stored as a hash.

    public User(String username, String password) {
        this.username = username;
        this.password = password; // Store hashed password in practice.
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password; // This method should not exist in a real application. Passwords should be compared using hashes.
    }
}
