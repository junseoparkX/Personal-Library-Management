package ui;

import java.util.ArrayList;
import java.util.Scanner;
import model.User;

/**
 * Manages user accounts within the Personal Library Management System.
 * Supports user registration, login with password validation and lockout after
 * multiple failed attempts, and password change functionality.
 * Ensures unique usernames and enforces password and username length constraints.
 * Uses a list to store user accounts and a scanner for console input during password change operations.
 */
public class UserManager {
    private ArrayList<User> userAccounts;
    private Scanner input;

    // EFFECTS: initializes user accounts
    public UserManager() {
        userAccounts = new ArrayList<>();
        input = new Scanner(System.in);  // Initialize scanner for input
        // Add a default user
        userAccounts.add(new User("junseo", "1234"));
    }

    // MODIFIES: this
    // EFFECTS: registers a new user
    public boolean registerNewUser(String username, String password) {
        User user = new User(username, password);

        // check the password is valid
        if (!user.isPasswordValid()) {
            System.out.println("Password must be less than 10 characters.");
            return false;
        }

        if (!user.isUserNameValid()) {
            System.out.println("Username must be less than 10 characters.");
            return false;
        }

        // Check if username already exists
        for (User existingUser : userAccounts) {
            if (existingUser.getUsername().equals(username)) {
                System.out.println("Username already exists.");
                return false;
            }
        }

        // Add the new user to the list
        userAccounts.add(user);
        return true;  
    }

    // EFFECTS: tracks failed login attempts and handle user login
    public boolean login(String username, String password) {
        // Search for the user by username
        for (User user : userAccounts) {
            if (user.getUsername().equals(username)) {
                // Check whether the user is locked out
                if (user.isLockedOut()) {
                    System.out.println("Account is locked due to too many failed login attempts.");
                    return false;
                }

                // Check if the password matches
                if (user.getPassword().equals(password)) {
                    user.resetLoginAttempts();  // Reset login attempts on successful login
                    return true;  // return true if the login was succesful
                } else {
                    user.incrementLoginAttempts();  // Increasefailed login attempts
                    System.out.println("Incorrect password. You have " 
                            + (3 - user.getLoginAttempts()) + " attempts left.");
                    return false;
                }
            }
        }
        System.out.println("Username or password is incorrect.");
        return false;  // Login failed
    }

    // MODIFIES: this
    // EFFECTS: Changes the password for the user if the old password matches and the new password is valid
    public boolean changePassword(String username) {
        // Search for the user by username
        for (User user : userAccounts) {
            if (user.getUsername().equals(username)) {
                // Ask for the old password
                System.out.print("Enter your current password: ");
                String currentPassword = input.next();

                // Check if the current password is correct
                if (!user.getPassword().equals(currentPassword)) {
                    System.out.println("Incorrect current password.");
                    return false;
                }

                // Ask for the new password
                System.out.print("Enter your new password (must be less than 10 characters): ");
                String newPassword = input.next();

                // Validate the new password
                if (!newPassword.equals(currentPassword) && newPassword.length() <= 10) {
                    user.changePassword(currentPassword, newPassword);  // Update the password
                    System.out.println("Password changed successfully.");
                    return true;
                } else {
                    System.out.println("New password is invalid or the same as the current password.");
                    return false;
                }
            }
        }

        System.out.println("User not found.");
        return false;
    }
}
