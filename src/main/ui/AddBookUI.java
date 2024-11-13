package ui;

import model.Book;
import model.Library;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * AddBookUI provides a user interface panel for adding new books to the library.
 * Users can enter details such as the book's title, author, genre, tag, and rating.
 */
public class AddBookUI extends JPanel {
    private JTextField titleField;
    private JTextField authorField;
    private JTextField genreField;
    private JTextField tagField;
    private JTextField ratingField;
    private JButton saveButton;
    private JButton backButton;
    private Library library; // Reference to the Library object
    private ViewBookList viewBookList; // Reference to ViewBookList for refreshing display

    /**
     * Constructs a new AddBookUI panel with fields to input book details and buttons 
     * to save the book or return to the main menu.
     *
     * Requires: library, parent, and viewBookList must be non-null.
     * Modifies: this
     * Effects: Initializes the UI elements, sets up layout and action listeners.
     */
    public AddBookUI(Library library, LibraryAppUI parent, ViewBookList viewBookList) {
        this.library = library;
        this.viewBookList = viewBookList;
        setLayout(new BorderLayout());
    
        setupTitleLabel();
        setupFormPanel();
        setupButtonPanel(parent);
    }
        
            /**
     * Sets up the title label at the top of the panel.
     * 
     * Modifies: this
     * Effects: Adds a title label to the top of the panel with specific styling.
     */
    private void setupTitleLabel() {
        JLabel titleLabel = new JLabel("Add New Book", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);
    }

    /**
     * Sets up the form panel with fields for book details.
     * 
     * Modifies: this
     * Effects: Adds a form panel with text fields for book details (title, author, genre, tag, and rating).
     */
    private void setupFormPanel() {
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        formPanel.add(new JLabel("Title:"));
        titleField = new JTextField();
        formPanel.add(titleField);

        formPanel.add(new JLabel("Author:"));
        authorField = new JTextField();
        formPanel.add(authorField);

        formPanel.add(new JLabel("Genre:"));
        genreField = new JTextField();
        formPanel.add(genreField);

        formPanel.add(new JLabel("Tag:"));
        tagField = new JTextField();
        formPanel.add(tagField);

        formPanel.add(new JLabel("Rating (out of 5):"));
        ratingField = new JTextField();
        formPanel.add(ratingField);

        add(formPanel, BorderLayout.CENTER);
    }

    /**
     * Sets up the button panel with save and back buttons.
     * 
     * Modifies: this
     * Effects: Adds a panel with "Save" and "Back" buttons to the bottom of the UI.
     */
    private void setupButtonPanel(LibraryAppUI parent) {
        JPanel buttonPanel = new JPanel();
        saveButton = new JButton("Save");
        backButton = new JButton("Back");

        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        setupButtonListeners(parent);
    }

    /**
     * Sets up action listeners for the save and back buttons.
     * 
     * Modifies: this
     * Effects: Adds action listeners to the "Save" button (to add a book to the library) 
     *          and the "Back" button (to return to the main menu).
     */
    private void setupButtonListeners(LibraryAppUI parent) {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBookToLibrary();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.showPanel("MenuPanel"); // Go back to the main menu
            }
        });
    }

    
    /**
     * Gathers user input, validates it, and adds the new book to the library if all data is valid.
     * 
     * Modifies: library
     * Effects: If input is valid, creates a new Book instance, adds it to the library,
     *          updates the book list view, shows a success message, and clears input fields.
     *          Shows an error message if the input is incomplete or invalid.
     */
    private void addBookToLibrary() {
        Book book = validateAndParseInput();
        if (book != null) {
            processNewBook(book);
        }
    }

    /**
     * Validates user input, parses it, and creates a Book instance if all input is valid.
     * 
     * Effects: Returns a new Book instance if input is valid; otherwise, shows an error
     *          message and returns null.
     */
    private Book validateAndParseInput() {
        String title = titleField.getText().trim();
        String author = authorField.getText().trim();
        String genre = genreField.getText().trim();
        String tag = tagField.getText().trim();
        float rating;

        try {
            rating = Float.parseFloat(ratingField.getText().trim());
            if (rating < 0 || rating > 5) {
                throw new NumberFormatException("Rating out of bounds.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid rating (0-5).",
                                        "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        if (title.isEmpty() || author.isEmpty() || genre.isEmpty() || tag.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.",
                                        "Incomplete Data", JOptionPane.WARNING_MESSAGE);
            return null;
        }

        return new Book(title, author, genre, tag, rating);
    }

    /**
     * Processes the new book by adding it to the library, updating the view, 
     * and clearing the input fields.
     * 
     * Modifies: library, viewBookList
     * Effects: Adds the book to the library, refreshes the book list view,
     *          displays a success message, and clears the input fields.
     */
    private void processNewBook(Book book) {
        library.addBook(book);
        viewBookList.updateBookList();

        JOptionPane.showMessageDialog(this, "Book added to the library: " + book.getTitle(),
                                    "Success", JOptionPane.INFORMATION_MESSAGE);

        titleField.setText("");
        authorField.setText("");
        genreField.setText("");
        tagField.setText("");
        ratingField.setText("");
    }
}
