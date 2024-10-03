package ui;

import java.util.ArrayList;
import java.util.Scanner;
import model.Book;

public class PersonalLibraryApp {

    private UserManager userManager;  // Delegate user management to UserManager
    private Scanner input;
    private ArrayList<Book> books;

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
        books = new ArrayList<>();        // Initialize the books list
        books.add(new Book("Harry Potter", "J.K. Rowling", "Adventure", "Magic", 4.8f));
        books.add(new Book("qwer", "qwer", "qwer", "qwer", 3.0f));
    }

    // MODIFIES: this
    // EFFECTS: processes user input for signing in or registration
    private void loginSystem() {
        boolean running = true;

        while (running) {
            System.out.println("\nWelcome to Personal Library Management System!");
            System.out.println("1. New user registration");
            System.out.println("2. Existing user login");
            System.out.println("3. Change password");
            System.out.println("e. Exit");

            System.out.print("Please enter your choice: ");
            String command = input.next();

            if (command.equals("1")) {
                registration();
            } else if (command.equals("2")) {
                if (signingIn()) {
                    System.out.println("Login successful! Proceeding to the library...");
                    running = false; // Exit the loop after successful sign-in
                    runLibrary();    // Proceed to library options
                } else {
                    System.out.println("Please try again. Login failed.");
                }
            } else if (command.equals("3")) {  // Option to change password
                System.out.print("Enter your username: ");
                String username = input.next();
                boolean passwordChanged = userManager.changePassword(username);
                if (passwordChanged) {
                    System.out.println("Password changed successfully.");
                } else {
                    System.out.println("Failed to change password.");
                }
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
    private void registration() {
        System.out.print("Create a username (less than 10 characters): ");
        String username = input.next();

        System.out.print("Create a password (less than 10 characters): ");
        String password = input.next();

        // Call UserManager to register the new user
        if (userManager.registerNewUser(username, password)) {
            System.out.println("Registration successful. You can now log in with your new account.");
        } else {
            System.out.println("Registration failed. Please try again.");
            registration();
            return; 
        }
    }

    // EFFECTS: handles user sign-in
    private boolean signingIn() {
        System.out.print("Enter username: ");
        String username = input.next();

        System.out.print("Enter password: ");
        String password = input.next();

        // Delegate the sign-in process to UserManager
        return userManager.login(username, password);
    }

    // MODIFIES: this
    // EFFECTS: processes user input for library options
    private void runLibrary() {
        boolean running = true;

        while (running) {
            System.out.println("\nLibrary Menu:");
            System.out.println("1. Add books to library");
            System.out.println("2. Add books to wishlist");
            System.out.println("3. See your wishlist");
            System.out.println("4. Search for books");
            System.out.println("e. Exit");

            System.out.print("Please enter your choice: ");
            String command = input.next();

            if (command.equals("1")) {
                addBooks(); 
            } else if (command.equals("2")) {
                // TODO: Implement wishlist functionality
            } else if (command.equals("3")) {
                // TODO: Implement wishlist display functionality
            } else if (command.equals("4")) {
                searchBooks();
            } else if (command.equals("e")) {
                System.out.println("Exiting the application. Goodbye!");
                running = false; // Exit the loop to end the application
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: allows user to add books to the library
    private void addBooks() {
        input.nextLine(); // Consume leftover newline
        System.out.print("What is the title of the book?: ");
        String title = input.nextLine();

        System.out.print("What is the author of the book?: ");
        String author = input.nextLine();

        System.out.print("What is the genre of the book?: ");
        String genre = input.nextLine();

        System.out.print("Name a tag for this book: ");
        String tag = input.nextLine();

        System.out.print("What rating do you give this book out of 5?: ");
        float rating = input.nextFloat();

        Book book = new Book(title, author, genre, tag, rating);
        books.add(book); 

        System.out.println("The book was added to the Library.");
        // No need to call runLibrary() here since we're in a loop
    }

    // EFFECTS: allows user to search for books based on title, genre, or author
    private void searchBooks() {
        // First, print all the books in the library
        System.out.println("\nBooks in the library:");
        for (Book book : books) {
            System.out.println("- " + book.getTitle());
        }

        // Ask the user which field to search by
        System.out.println("\nSearch by:");
        System.out.println("1. Title");
        System.out.println("2. Tag");
        System.out.println("3. Author");
        System.out.print("Please enter your choice (1-3): ");
        int choice = input.nextInt();
        input.nextLine(); // Consume leftover newline

        // Get the search value from the user
        String searchValue = "";
        switch (choice) {
            case 1:
                System.out.print("Enter the title to search: ");
                searchValue = input.nextLine();
                break;
            case 2:
                System.out.print("Enter the tag to search: ");
                searchValue = input.nextLine();
                break;
            case 3:
                System.out.print("Enter the author to search: ");
                searchValue = input.nextLine();
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        // Search through the books
        boolean found = false;
        for (Book book : books) {
            boolean matches = false;
            switch (choice) {
                case 1:
                    if (book.getTitle().equalsIgnoreCase(searchValue)) {
                        matches = true;
                    }
                    break;
                case 2:
                    if (book.getTag().equalsIgnoreCase(searchValue)) {
                        matches = true;
                    }
                    break;
                case 3:
                    if (book.getAuthor().equalsIgnoreCase(searchValue)) {
                        matches = true;
                    }
                    break;
            }
            if (matches) {
                System.out.println("\nFound book:");
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("Genre: " + book.getGenre());
                System.out.println("Tag: " + book.getTag());
                System.out.println("Rating: " + book.getRating());
                found = true;
                break; // Assuming we want to find the first match
            }
        }
        if (!found) {
            System.out.println("No books found matching your search.");
        }
    }
}
