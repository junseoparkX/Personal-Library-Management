package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import model.Book;
import model.Library;
import persistence.JsonReader;
import persistence.JsonWriter;

/**
 * The main user interface for the Personal Library Management System.
 * Provides menu options for user registration, login, password management, and library operations.
 * Library operations include adding, removing, viewing, and searching books, as well as updating
 * reading status and saving/loading the library data to/from a JSON file.
 * Uses `UserManager` for user account management and `JsonReader`/`JsonWriter` for file handling.
 */
public class PersonalLibraryApp {

    private static final String JSON_STORE = "./data/library.json";
    private UserManager userManager;
    private Scanner input;
    private Library library;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the application
    public PersonalLibraryApp() {
        init();
        registrationMenu();
    }

    // MODIFIES: this
    // EFFECTS: initializes user accounts, scanner, and JSON reader/writer
    private void init() {
        // Initialize the UserManager, Scanner, and library
        userManager = new UserManager();  // Initialize the UserManager
        input = new Scanner(System.in);   // Initialize Scanner for user input
        library = new Library();

        // Initialize JSON reader and writer
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        // Adding recommendation books
        library.addBook(new Book("Harry Potter", "J.K. Rowling", "Adventure", "Trending", 4.8f));
        library.addBook(new Book("Maze Runner", "James Dashner", "Dystopian", "Adventure", 4.2f));
        library.addBook(new Book("qwer", "qwer", "qwer", "Trending", 3.0f));
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", "Fiction", "Trending", 4.4f));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "Fiction", "Classic", 4.8f));   
    }

    // MODIFIES: this
    // EFFECTS: show the registeration to user and if the user pressed e, exit the menu
    private void registrationMenu() {
        boolean running = true;

        while (running) {
            System.out.println("\nWelcome to Personal Library Management System!");
            System.out.println("1. New user registration");
            System.out.println("2. Existing user login");
            System.out.println("3. Change password");
            System.out.println("e. Exit");
            System.out.print("Please enter your choice: ");
            String command = input.next();

            if (command.equals("e")) {
                System.out.println("Exiting the application. Goodbye!");
                running = false; // Exit the loop
            } else {
                loginSystem(command);
            }
        }
    }


    // MODIFIES: this
    // EFFECTS: processes user input: signing in, password change, registration, exiting
    private void loginSystem(String command) {
        if (command.equals("1")) {
            registration();
        } else if (command.equals("2")) {
            if (signingIn()) {
                System.out.println("Login successful! Proceeding to the library...");
                runLibrary();    // Proceed to library options
            } else {
                System.out.println("Please try again. Login failed.");
            }
        } else if (command.equals("3")) {  // Option to change user password
            System.out.print("Enter your username: ");
            String username = input.next();
            boolean passwordChanged = userManager.changePassword(username);
            if (passwordChanged) {
                System.out.println("Password changed successfully.");
            } else {
                System.out.println("Failed to change password.");
            }
        } else {
            System.out.println("Invalid input. Please try again.");
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
            System.out.println("Registration was successful. You can now log in with your new account.");
        } else {
            System.out.println("Registration failed. Please try again.");
            registration();
        }
    }

    // EFFECTS: handles user sign-in
    private boolean signingIn() {
        System.out.print("Enter username: ");
        String username = input.next();

        System.out.print("Enter password: ");
        String password = input.next();

        // The sign-in process to UserManager
        return userManager.login(username, password);
    }
    
    // MODIFIES: this
    // EFFECTS: provides user input for library options
    private void runLibrary() {
        boolean running = true;
        while (running) {
            libraryMenu();
            String command = input.next();
            running = processLibraryCommand(command, running);
        }
    }

    // MODIFIES: this
    // EFFECTS: processes the user's library command and returns the updated running state
    private boolean processLibraryCommand(String command, boolean running) {
        if (command.equals("1")) {
            addBooks();
        } else if (command.equals("2")) {
            removeBook();
        } else if (command.equals("3")) {
            viewAllBooks();
        } else if (command.equals("4")) {
            searchBooks();
        } else if (command.equals("5")) {
            updateReadingStatus();
        } else if (command.equals("6")) {
            saveLibrary();
        } else if (command.equals("7")) {
            loadLibrary();
        } else if (command.equals("e")) {
            System.out.println("Exiting the application. Goodbye!");
            running = false; // Exit the loop to end the application
        } else {
            System.out.println("Invalid input. Please try again.");
        }
        return running;
    }

    // EFFECTS: displays the library menu options to the user
    private void libraryMenu() {
        System.out.println("\nLibrary Menu:");
        System.out.println("1. Add books to library");
        System.out.println("2. Remove a book from library");
        System.out.println("3. View all books in library");
        System.out.println("4. Search for books");
        System.out.println("5. Update reading status");
        System.out.println("6. Save library to file");
        System.out.println("7. Load library from file");
        System.out.println("e. Exit");
        System.out.print("Please enter your choice: ");
    }

    // MODIFIES: this
    // EFFECTS: allows user to add books to the library
    private void addBooks() {
        input.nextLine();
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
        library.addBook(book);
        System.out.println("Book was added to the library: " + book.getTitle());

        System.out.println("The book was added to the Library.");
    }

    // MODIFIES: this
    // EFFECTS: allows user to remove a book from the library
    private void removeBook() {
        input.nextLine();
        System.out.print("Enter the title of the book to remove: ");
        String bookTitle = input.nextLine();

        boolean removedBook = library.removeBook(bookTitle);
        if (!removedBook) {
            System.out.println("Book \"" + bookTitle + "\" was not found");
            System.out.println("Could not remove the book. Check the title of the book again.");
        } else  {
            System.out.println("Book removed: " + bookTitle);
        }
    }

    // EFFECTS: displays all books that are currently in the library
    private void viewAllBooks() {
        List<String> bookList = library.displayAllBooks();
        for (String bookInfo : bookList) {
            System.out.println(bookInfo);
        }
    }

    // MODIFIES: this
    // EFFECTS: updates the reading status of a book
    private void updateReadingStatus() {
        input.nextLine();
        System.out.print("Enter a search term (title, author, tag): ");
        String searchOption = input.nextLine();

        List<Book> foundBooks = library.searchBook(searchOption);
        if (foundBooks.isEmpty()) {
            System.out.println("No books were found.");
            return;
        }

        Book bookToUpdate = foundBooks.get(0);
        System.out.print("Enter 'true' to mark as 'Reading' or 'false' to mark as 'Not Reading': ");
        String statusReading = input.nextLine();

        // Convert string to Boolean
        Boolean status;
        if (statusReading.equalsIgnoreCase("true")) {
            status = true;
            System.out.println("It is now marked as \"Reading\"");
        } else if (statusReading.equalsIgnoreCase("false")) {
            status = false;
            System.out.println("It is now marked as \"Not reading\"");
        } else {
            System.out.println("Invalid input. Please enter 'true' or 'false'.");
            return;
        }
        bookToUpdate.updateReadingStatus(status);
    }

    // EFFECTS: allows user to search for books based on title, genre, or author
    private void searchBooks() {
        input.nextLine();
        System.out.print("Enter a search term (title, author, tag): ");
        String searchOption = input.nextLine();

        List<Book> foundBooks = library.searchBook(searchOption);
        if (foundBooks.isEmpty()) {
            System.out.println("No books were found.");
        } else {
            System.out.println("\nFound books:");
            for (Book book : foundBooks) {
                System.out.println("Title: " + book.getTitle()
                        + ", Author: " + book.getAuthor()
                        + ", Genre: " + book.getGenre()
                        + ", Tag: " + book.getTag()
                        + ", Rating: " + book.getRating()
                        + ", Reading Status: " + (book.getReadingStatus() ? "Reading" : "Not Reading"));
            }
        }
    }

    // EFFECTS: saves the library to a file
    private void saveLibrary() {
        try {
            jsonWriter.open();
            jsonWriter.write(library);
            jsonWriter.close();
            System.out.println("Library saved successfully to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Error: Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the library from a file
    private void loadLibrary() {
        try {
            library = jsonReader.read();
            System.out.println("Library loaded successfully from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Error: Unable to read from file: " + JSON_STORE);
        }
    }
}
