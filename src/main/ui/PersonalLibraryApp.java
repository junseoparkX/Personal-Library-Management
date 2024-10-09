package ui;

import java.util.List;
import java.util.Scanner;
import model.Book;
import model.Library;

public class PersonalLibraryApp {

    private UserManager userManager; 
    private Scanner input;
    private Library library; 

    // EFFECTS: runs the application
    public PersonalLibraryApp() {
        init();
        registerationMenu();
    }

    // MODIFIES: this
    // EFFECTS: initializes user accounts and scanner
    private void init() {
        // Initalie the UserManager, Scanner and library
        userManager = new UserManager();  // Initialize the UserManager
        input = new Scanner(System.in);   // Initialize Scanner for user input
        library = new Library(); 

        // Adding recommendation books 
        library.addBook(new Book("Harry Potter", "J.K. Rowling", "Adventure", "Trending", 4.8f));
        library.addBook(new Book("Maze Runner", "James Dashner", "Dystopian", "Adventure", 4.2f));
        library.addBook(new Book("qwer", "qwer", "qwer", "Trending", 3.0f));
    }

    // MODIFIES: this
    // EFFECTS: processes user input: signing in, password change, registeration, exiting
    private void registerationMenu() {
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
                loginSystem(command, running);
            }
        }
    } 
    

    private void loginSystem(String command, Boolean running) {
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
            return; 
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
    // EFFECTS: provide user input for library options
    private void runLibrary() {
        boolean running = true;

        while (running) {
            libraryMenu();
            String command = input.next();
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
            } else if (command.equals("e")) {
                System.out.println("Exiting the application. Goodbye!");
                running = false; // Exit the loop to end the application
            } else {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    // EFFECTS: displays the library menu options to the user
    private void libraryMenu() {
        System.out.println("\nLibrary Menu:");
        System.out.println("1. Add books to library");
        System.out.println("2. Remove a book from library");
        System.out.println("3. View a3ll books in library");
        System.out.println("4. Search for books");
        System.out.println("5. Update reading status");
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

        System.out.println("The book was added to the Library.");
    }

    // MODIFIES:this
    // EFFECTS: it allows user to remove the book from the library 
    private void removeBook() {
        input.nextLine();
        System.out.println("Enter the title of the book to remove:");
        String bookTitle = input.nextLine();

        boolean removedBook = library.removeBook(bookTitle); 
        if (!removedBook) {
            System.out.println("Could not remove the book. Check the title of the book again");
        }
    }

    // EFFECTS: displays all books that are currently in the library
    private void viewAllBooks() {
        library.displayAllBooks();
    }

    private void updateReadingStatus() {
        input.nextLine();
        System.out.println("Choose and enter a search term from this option: (title, author, tag): ");
        String searchOption = input.nextLine();
    
        List<Book> foundBooks = library.searchBook(searchOption); 
        if (foundBooks.isEmpty()) {
            System.out.println("No books were found");
            return; 
        } 
        
        Book bookToUpdate = foundBooks.get(0); 
        System.out.println("Enter 'true' to mark as 'Reading' or enter 'false' to mark as 'Not Reading'");
        String statusReading = input.nextLine();
    
        // Convert string to Boolean
        Boolean status; 
        if (statusReading.equals("true")) {
            status = true; 
        } else if (statusReading.equals("false")) {
            status = false; 
        } else {
            System.out.println("Invalid input. Please enter again");
            return;
        }
        bookToUpdate.updateReadingStatus(status);
    }
    

    // EFFECTS: allows user to search for books based on title, genre, or author
    private void searchBooks() {
        input.nextLine(); 
        System.out.println("Enter anything related from this option: (title, author, tag): ");
        String searchOption = input.nextLine(); 

        List<Book> foundBooks = library.searchBook(searchOption); 
        if (foundBooks.isEmpty()) {
            System.out.println("No books were found");
        } else {
            System.out.println("\n Found books: ");
            for (Book book: foundBooks) {
                System.out.println("Title: " + book.getTitle() +
                               ", Author: " + book.getAuthor() +
                               ", Genre: " + book.getGenre() +
                               ", Tag: " + book.getTag() +
                               ", Rating: " + book.getRating() +
                               ", Reading Status: " + (book.getReadingStatus() ? "Reading" : "Not Reading"));
        }
        }
    }
}

