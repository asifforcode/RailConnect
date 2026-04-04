// class to store user info like name, username and password
public class User {
    private final String username;
    private final String password;
    private final String fullName;
    private final String contact;

    public User(String username, String password, String fullName, String contact) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("username can't be blank");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("password can't be blank");
        }

        this.username = username;
        this.contact = contact;
        this.fullName = fullName;
        this.password = password;
    }

    // getter method for username
    public String getUsername() {
        return username;
    }

    // getter method for contact number
    public String getContact() {
        return contact;
    }

    // getter method for full name
    public String getFullName() {
        return fullName;
    }

    // getter method for password
    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return fullName + " (" + username + ")";
    }
}
