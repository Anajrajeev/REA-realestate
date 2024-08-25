package realestate;

public class User {
    private int id;
    private String username;
    private String password;
    private boolean isLoggedIn;

    // Constructor
    public User() {
    }

    public User(int id, String username, String password, boolean isLoggedIn) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isLoggedIn = isLoggedIn;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}
