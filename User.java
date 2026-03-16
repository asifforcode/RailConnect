public class User {
    private final String username;
    private final String password;
    private final String fullname;
    private final String contact;

    public User(String username, String password, String fullname, String contact) {
        this.username = username;
        this.contact = contact;
        this.fullname = fullname;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getContact() {
        return contact;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return fullname + " (" + username + ")";

    }
}
