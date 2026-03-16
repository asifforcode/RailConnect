import java.util.*;

public class UserService {
    private final Map<String, User> userMap = new HashMap<>();
    private User currentUser = null;

    public boolean registerUser(String username, String password, String fullname, String contact) {
        String normalizedUsername = normalize(username);
        if (normalizedUsername.isEmpty() || password == null || password.isBlank()) {
            System.out.println("Username and password are required.");
            return false;
        }

        if (userMap.containsKey(normalizedUsername)) {
            System.out.println("Username already taken. Please choose another username.");
            return false;
        }

        User user = new User(username.trim(), password, safeTrim(fullname), safeTrim(contact));
        userMap.put(normalizedUsername, user);
        System.out.println("Registration successful!");
        return true;
    }

    public boolean loginUser(String username, String password) {
        String normalizedUsername = normalize(username);
        if (!userMap.containsKey(normalizedUsername)) {
            System.out.println("User not found with this username.");
            return false;
        }

        User user = userMap.get(normalizedUsername);
        if (!user.getPassword().equals(password)) {
            System.out.println("Incorrect password.");
            return false;
        }

        currentUser = user;
        System.out.println("Welcome to the platform, " + currentUser.getFullname() + "!");
        return true;
    }

    public void logOutUser() {
        if (currentUser != null) {
            System.out.println("Logged out: " + currentUser.getFullname());
        }
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public boolean isLoggedIN() {
        return isLoggedIn();
    }

    private String normalize(String value) {
        return safeTrim(value).toLowerCase();
    }

    private String safeTrim(String value) {
        return value == null ? "" : value.trim();
    }
}
