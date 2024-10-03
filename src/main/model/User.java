package model;

public class User {
    private String username;
    private String password;

    // Constructs a user with the given username and password
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Returns the username
    public String getUsername() {
        return username;
    }

    // Returns the password
    public String getPassword() {
        return password;
    }

    public boolean isPasswordValid(){
       return password.length() <= 10;
    }

    public boolean isUserNameValid(){
       return username.length() <= 10;
    }
}
