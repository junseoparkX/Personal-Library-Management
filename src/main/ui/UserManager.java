package ui;

import java.util.ArrayList;
import model.User;

public class UserManager {
    private ArrayList<User> userAccounts;

    // EFFECTS: initializes user accounts
    public UserManager() {
        userAccounts = new ArrayList<>();
        // Add a default user
        userAccounts.add(new User("junseo", "1234"));
    }

    // MODIFIES: this
    // EFFECTS: registers a new user
    public boolean registerNewUser(String username, String password) {
        User user = new User(username, password);

        // Validation logic
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
        return true;  // Registration successful
    }

    // EFFECTS: handles user login
    public boolean login(String username, String password) {
        // Search for the user by username
        for (User user : userAccounts) {
            if (user.getUsername().equals(username)) {
                // Check if the password matches
                if (user.getPassword().equals(password)) {
                    return true;  // Login successful
                }
            }
        }
        System.out.println("Username or password is incorrect.");
        return false;  // Login failed
    }
}
