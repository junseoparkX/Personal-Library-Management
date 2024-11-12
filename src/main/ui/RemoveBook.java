package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import model.Book;
import model.Library;

/**
 * RemoveBook provides a user interface panel for searching and removing books in the library.
 * Users can search for a book by title, view its details, and confirm its removal.
 */
public class RemoveBook extends JPanel {
    private Library library;
    private JTextField searchField;
    private JTextArea resultArea;
    private LibraryAppUI parentFrame;
    private ViewBookList viewBookList; // Reference to ViewBookList to refresh display

    /**
     * Constructs a new RemoveBook panel to search and remove books by title.
     *
     * Requires: library, parentFrame, and viewBookList must be non-null.
     * Modifies: this
     * Effects: Initializes the UI elements, sets up layout and action listeners.
     */
    public RemoveBook(Library library, LibraryAppUI parentFrame, ViewBookList viewBookList) {
        this.library = library;
        this.parentFrame = parentFrame;
        this.viewBookList = viewBookList;
        setLayout(new BorderLayout());

        // Title label for the view
        JLabel titleLabel = new JLabel("Remove a Book", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Search bar at the top
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchField = new JTextField();
        searchField.setFont(new Font("Arial", Font.PLAIN, 16));
        searchField.setBorder(BorderFactory.createTitledBorder("Enter Book Title to Remove"));
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
     * the searchAndDisplayBook method is called with the input title.
     */
    private class SearchActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String title = searchField.getText().trim();
            searchAndDisplayBook(title);
        }
    }

    /**
     * Searches for a book by title in the library and displays its details in the result area.
     * If the book is found, prompts the user to confirm its removal.
     * 
     * @param title the title of the book to search for
     * 
     * Requires: title is a non-null string.
     * Modifies: library, resultArea, viewBookList
     * Effects: Clears the previous search result, searches for the book in the library, and
     *          displays its details in the result area. If the book is found, prompts the user
     *          for confirmation to remove it. If the user confirms, removes the book from the
     *          library, updates the book list in ViewBookList, and shows a success message.
     *          If the book is not found, displays a "no book found" message.
     */
    private void searchAndDisplayBook(String title) {
        resultArea.setText(""); // Clear previous result
        List<Book> books = library.getBooks();

        boolean found = false;
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                // Display the book details
                resultArea.append("Title: " + book.getTitle() + "\n");
                resultArea.append("Author: " + book.getAuthor() + "\n");
                resultArea.append("Genre: " + book.getGenre() + "\n");
                resultArea.append("Tag: " + book.getTag() + "\n");
                resultArea.append("Rating: " + book.getRating() + "\n");
                resultArea.append("Reading Status: " + (book.getReadingStatus() ? "Reading" : "Not Reading") + "\n\n");

                // Ask for confirmation to remove the book
                int choice = JOptionPane.showConfirmDialog(
                        this,
                        "Are you going to remove this book?",
                        "Confirm Removal",
                        JOptionPane.YES_NO_OPTION
                );

                if (choice == JOptionPane.YES_OPTION) {
                    library.removeBook(title); // Remove the book from the library
                    JOptionPane.showMessageDialog(this, "Book removed successfully.");
                    resultArea.setText(""); // Clear the display after removal
                    viewBookList.updateBookList(); // Refresh ViewBookList to reflect the deletion
                } else {
                    JOptionPane.showMessageDialog(this, "Book not removed.");
                }
                found = true;
                break;
            }
        }

        if (!found) {
            resultArea.append("No book found with the title \"" + title + "\"");
        }
    }
}


