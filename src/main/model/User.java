package model;

/**
 * Represents a user with a username, password, and a counter for login attempts.
 * Provides methods to validate the username and password length, track and reset login attempts,
 * check if the user is locked out due to too many failed login attempts, and change the password
 * if certain conditions are met.
 */
public class User {
    private String username;
    private String password;
    private int loginAttempts;  

    // EFFECTS: initializes a new User with the provided username and password
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.loginAttempts = 0;  // Initialize login attempts to 0
    }

    // EFFECTS: returns the username of the user
    public String getUsername() {
        return username;
    }

    // EFFECTS: returns the password of the user
    public String getPassword() {
        return password;
    }

    // EFFECTS: returns true if the password length is less than or equal to 10
    public boolean isPasswordValid() {
        return password.length() <= 10;
    }

    // EFFECTS: returns true if the username length is less than or equal to 10
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
        return loginAttempts >= 3;  // User is locked out 
    }

    // MODIFIES: this
    // EFFECTS: Changes the password if the old password matches and the new password is valid
    public boolean changePassword(String oldPassword, String newPassword) {
        if (this.password.equals(oldPassword) && newPassword.length() <= 10) {
            this.password = newPassword;
            return true;
        } else {
            return false;  // Password change failed
        }
    }
}
