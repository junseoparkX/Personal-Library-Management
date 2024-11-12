package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import model.Book;
import model.Library;

/**
 * SearchBook provides a user interface panel for searching books in the library.
 * Users can search by title, author, genre, or tag, and view matching results.
 */
public class SearchBook extends JPanel {
    private Library library;
    private JTextField searchField;
    private JTextArea resultArea;
    private LibraryAppUI parentFrame;

    /**
     * Constructs a new SearchBook panel with a search field and results display.
     *
     * Requires: library and parentFrame must be non-null.
     * Modifies: this
     * Effects: Initializes the UI elements, sets up layout and action listeners.
     */
    public SearchBook(Library library, LibraryAppUI parentFrame) {
        this.library = library;
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());

        // Title label for the view
        JLabel titleLabel = new JLabel("Search for a Book", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Search bar at the top
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchField = new JTextField();
        searchField.setFont(new Font("Arial", Font.PLAIN, 16));
        searchField.setBorder(BorderFactory.createTitledBorder("Enter Book Title, Author, Genre, or Tag"));
        searchField.addActionListener(new SearchActionListener());
        searchPanel.add(searchField, BorderLayout.CENTER);

        add(searchPanel, BorderLayout.NORTH);

        // Text area to display the search result
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 14));
        resultArea.setBorder(BorderFactory.createTitledBorder("Search Result"));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.CENTER);

        // Back button to return to the main menu
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> parentFrame.showPanel("MenuPanel"));
        add(backButton, BorderLayout.SOUTH);
    }

    /**
     * Action listener for the search field. When the user presses Enter,
     * the searchForBook method is called with the input keyword.
     */
    private class SearchActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String keyword = searchField.getText().trim();
            searchForBook(keyword);
        }
    }

    /**
     * Searches for books by title, author, genre, or tag in the library and displays matching results.
     * 
     * Requires: keyword is a non-null string.
     * Modifies: resultArea
     * Effects: Clears the previous search results, searches for books in the library matching the keyword,
     *          and displays book details in the result area. If no match is found, displays a "no book found" message.
     */
    private void searchForBook(String keyword) {
        resultArea.setText(""); // Clear previous result
        List<Book> books = library.getBooks();

        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(keyword) ||
                book.getAuthor().equalsIgnoreCase(keyword) ||
                book.getGenre().equalsIgnoreCase(keyword) ||
                book.getTag().equalsIgnoreCase(keyword)) {

                resultArea.append("Title: " + book.getTitle() + "\n");
                resultArea.append("Author: " + book.getAuthor() + "\n");
                resultArea.append("Genre: " + book.getGenre() + "\n");
                resultArea.append("Tag: " + book.getTag() + "\n");
                resultArea.append("Rating: " + book.getRating() + "\n");
                resultArea.append("Reading Status: " + (book.getReadingStatus() ? "Reading" : "Not Reading") + "\n");
                resultArea.append("\n------------------------------------\n");
                found = true;
            }
        }

        if (!found) {
            resultArea.append("No book found matching \"" + keyword + "\"");
        }
    }
}


