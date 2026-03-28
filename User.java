// Stores basic user details.
public class User {
    private final String username;
    private final String password;
    private final String fullName;
    private final String contact;

    public User(String username, String password, String fullName, String contact) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }

        this.username = username;
        this.contact = contact;
        this.fullName = fullName;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getContact() {
        return contact;
    }

    public String getFullName() {
        return fullName;
    }

    // Old getter name kept so existing code still works.
    public String getFullname() {
        return getFullName();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return fullName + " (" + username + ")";
    }
}
