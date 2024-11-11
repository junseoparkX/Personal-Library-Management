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

    /**
     * Constructs a new AddBookUI panel with fields to input book details and buttons 
     * to save the book or return to the main menu.
     *
     * Requires: library and parent must be non-null.
     * Modifies: this
     * Effects: Initializes the UI elements, sets up layout and action listeners.
     */
    public AddBookUI(Library library, LibraryAppUI parent) {
        this.library = library;
        setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("Add New Book", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Form panel for book details
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

        // Button panel for save and back actions
        JPanel buttonPanel = new JPanel();
        saveButton = new JButton("Save");
        backButton = new JButton("Back");

        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action listener for save button
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBookToLibrary();
            }
        });

        // Action listener for back button to return to the main menu
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
     * Requires: All input fields should contain valid data for adding a book.
     * Modifies: library
     * Effects: Creates a new Book instance and adds it to the library if input is valid.
     *          Displays a success or error message accordingly, and clears input fields if successful.
     */
    private void addBookToLibrary() {
        String title = titleField.getText().trim();
        String author = authorField.getText().trim();
        String genre = genreField.getText().trim();
        String tag = tagField.getText().trim();
        float rating;

        // Validate rating input
        try {
            rating = Float.parseFloat(ratingField.getText().trim());
            if (rating < 0 || rating > 5) {
                throw new NumberFormatException("Rating out of bounds.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid rating (0-5).", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check for empty fields
        if (title.isEmpty() || author.isEmpty() || genre.isEmpty() || tag.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Incomplete Data", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Create the Book object and add it to the library
        Book book = new Book(title, author, genre, tag, rating);
        library.addBook(book);

        // Confirmation message
        JOptionPane.showMessageDialog(this, "Book added to the library: " + book.getTitle(), "Success", JOptionPane.INFORMATION_MESSAGE);

        // Clear the fields after adding the book
        titleField.setText("");
        authorField.setText("");
        genreField.setText("");
        tagField.setText("");
        ratingField.setText("");
    }
}


