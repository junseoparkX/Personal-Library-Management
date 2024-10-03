package ui;

import java.util.Scanner;

public class PersonalLibraryApp {

    private UserManager userManager;  // Delegate user management to UserManager
    private Scanner input;

    // EFFECTS: runs the application
    public PersonalLibraryApp() {
        init();
        loginSystem();
    }

    // MODIFIES: this
    // EFFECTS: initializes user accounts and scanner
    private void init() {
        userManager = new UserManager();  // Initialize the UserManager
        input = new Scanner(System.in);   // Initialize Scanner for user input
    }

    // MODIFIES: this
    // EFFECTS: processes user input for signingIn or registration
    private void loginSystem() {
        boolean running = true;

        while (running) {
            System.out.println("\nWelcome to Personal Library Management System!");
            System.out.println("If you are a new user, press 'n'.");
            System.out.println("If you are an existing user, press 'u'.");
            System.out.println("To exit, press 'e'.");

            System.out.print("Please enter your choice: ");
            String command = input.next();  // Using next() for input

            if (command.equals("u")) {
                if (signingIn()) {
                    System.out.println("Login successful! Proceeding to the next step...");
                    running = false; // Exit the loop after successful signingIn
                } else {
                    System.out.println(" Please try again. Login failed.");
                }
            } else if (command.equals("n")) {
                registeration();
            } else if (command.equals("e")) {
                System.out.println("Exiting the application. Goodbye!");
                running = false; // Exit the loop to end the application
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: registers a new user
    private void registeration() {
        System.out.print("Create a username (less than 10 characters): ");
        String username = input.next();

        System.out.print("Create a password (less than 10 characters): ");
        String password = input.next();

        // Call UserManager to register the new user
        if (userManager.registerNewUser(username, password)) {
            System.out.println("You can now log in with your new account.");
        } else {
            System.out.println("Registration failed. Please try again.");
            registeration();
            return; 
        }
    }

    // EFFECTS: handles user signingIn
    private boolean signingIn() {
        System.out.print("Enter username: ");
        String username = input.next();

        System.out.print("Enter password: ");
        String password = input.next();

        // Delegate the signingIn process to UserManager
        return userManager.login(username, password);
    }
    
}
