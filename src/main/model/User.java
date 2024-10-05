package model;

public class User {
    private String username;
    private String password;
    private int loginAttempts;  // Keeps track of failed login attempts

    // Constructs a user with the given username and password
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.loginAttempts = 0;  // Initialize login attempts to 0
    }

    // Returns the username
    public String getUsername() {
        return username;
    }

    // Returns the password
    public String getPassword() {
        return password;
    }

    // Checks if the password is valid 
    public boolean isPasswordValid() {
        return password.length() <= 10;
    }

    // Checks if the username is valid 
    public boolean isUserNameValid() {
        return username.length() <= 10;
    }

    // EFFECTS: Increments the login attempt counter
    public void incrementLoginAttempts() {
        loginAttempts++;
    }

    // EFFECTS: Resets the login attempt counter
    public void resetLoginAttempts() {
        loginAttempts = 0;
    }

    // EFFECTS: Gets the current number of login attempts (required for login logic)
    public int getLoginAttempts() {
        return loginAttempts;
    }

    // EFFECTS: Checks if the user is locked out after too many failed login attempts
    public boolean isLockedOut() {
        return loginAttempts >= 3;  // User is locked out after 3 failed attempts
    }

    // EFFECTS: Changes the password 
    public boolean changePassword(String oldPassword, String newPassword) {
        if (this.password.equals(oldPassword) && newPassword.length() <= 10) {
            this.password = newPassword;
            return true;
        } else {
            return false;  // Password change failed
        }
    }
}
