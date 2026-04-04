import java.util.HashMap;
import java.util.Map;

// this class takes care of register, login, and logout stuff
public class UserService {

    // storing users with username as key so we can find them quickly
    private final Map<String, User> usersByUsername = new HashMap<>();
    private User currentUser = null;

    public boolean registerUser(String username, String password, String fullName, String contact) {
        // trim and lowercase so "Alice" and "alice" are treated the same
        String normalizedUsername = normalize(username);

        if (normalizedUsername.isEmpty() || password == null || password.isBlank()) {
            System.out.println("username or password is missing");
            return false;
        }

        // check if someone already has this username
        if (usersByUsername.containsKey(normalizedUsername)) {
            System.out.println("that username is already taken, try a different one");
            return false;
        }

        User user = new User(username.trim(), password, safeTrim(fullName), safeTrim(contact));
        usersByUsername.put(normalizedUsername, user);
        System.out.println("account created successfully!");
        return true;
    }

    public boolean loginUser(String username, String password) {
        String normalizedUsername = normalize(username);

        if (!usersByUsername.containsKey(normalizedUsername)) {
            System.out.println("no account found with this username");
            return false;
        }

        User user = usersByUsername.get(normalizedUsername);

        // check if password matches
        if (password == null || !user.getPassword().equals(password)) {
            System.out.println("wrong password, please try again");
            return false;
        }

        currentUser = user;
        System.out.println("hey " + currentUser.getFullName() + ", you're logged in!");
        return true;
    }

    public void logOutUser() {
        if (currentUser != null) {
            System.out.println(currentUser.getFullName() + " logged out");
        }
        currentUser = null; // clear the session
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    // lowercases and trims the username before storing or searching
    private String normalize(String value) {
        return safeTrim(value).toLowerCase();
    }

    private String safeTrim(String value) {
        return value == null ? "" : value.trim();
    }
}
