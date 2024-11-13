package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import model.Book;
import model.Library;

/**
 * UpdateReadingStatus provides a user interface panel for updating the reading status of a book.
 * Users can search for a book by title, view its details, and toggle its reading status.
 */
public class UpdateReadingStatus extends JPanel {
    private Library library;
    private JTextField searchField;
    private JTextArea resultArea;
    private LibraryAppUI parentFrame;
    private ViewBookList viewBookList; // Reference to ViewBookList to refresh display

    /**
     * Constructs a new UpdateReadingStatus panel to search for books by title and update their reading status.
     *
     * Requires: library, parentFrame, and viewBookList must be non-null.
     * Modifies: this
     * Effects: Initializes the UI elements, sets up layout and action listeners.
     */
    public UpdateReadingStatus(Library library, LibraryAppUI parentFrame, ViewBookList viewBookList) {
        this.library = library;
        this.parentFrame = parentFrame;
        this.viewBookList = viewBookList;
        setLayout(new BorderLayout());

        // Title label for the view
        JLabel titleLabel = new JLabel("Update Reading Status", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Search bar at the top
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchField = new JTextField();
        searchField.setFont(new Font("Arial", Font.PLAIN, 16));
        searchField.setBorder(BorderFactory.createTitledBorder("Enter Book Title to Update Status"));
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
     * the searchAndToggleReadingStatus method is called with the input title.
     */
    private class SearchActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String title = searchField.getText().trim();
            searchAndToggleReadingStatus(title);
        }
    }

    /**
     * Searches for a book by title in the library and displays its details in the result area.
     * If the book is found, prompts the user to toggle the reading status.
     * 
     * Requires: title is a non-null string.
     * Modifies: library, resultArea, viewBookList
     * Effects: Clears the previous search result, searches for the book in the library, and
     *          displays its details in the result area. If the book is found, prompts the user
     *          for confirmation to toggle its reading status. If confirmed, updates the reading
     *          status and refreshes the book list in ViewBookList. If the book is not found,
     *          displays a "no book found" message.
     */
    private void searchAndToggleReadingStatus(String title) {
        resultArea.setText(""); // Clear previous result
        List<Book> books = library.getBooks();

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                displayBookDetailsForToggle(book);
                confirmAndToggleReadingStatus(book);
                return;
            }
        }

        resultArea.append("No book found with the title \"" + title + "\"");
    }

    /**
     * Displays the details of the given book in the result area, including the current reading status.
     * 
     * Requires: book is non-null.
     * Modifies: resultArea
     * Effects: Shows the details of the book in the result area.
     */
    private void displayBookDetailsForToggle(Book book) {
        resultArea.append("Title: " + book.getTitle() + "\n");
        resultArea.append("Author: " + book.getAuthor() + "\n");
        resultArea.append("Genre: " + book.getGenre() + "\n");
        resultArea.append("Tag: " + book.getTag() + "\n");
        resultArea.append("Rating: " + book.getRating() + "\n");
        resultArea.append("Current Reading Status: " + (book.getReadingStatus() ? "Reading" : "Not Reading") + "\n\n");
    }

    /**
     * Prompts the user to confirm toggling the reading status and updates it if confirmed.
     * 
     * Requires: book is non-null.
     * Modifies: book, viewBookList
     * Effects: If the user confirms, toggles the reading status of the book, refreshes ViewBookList,
     *          and shows a success message. If not confirmed, shows a cancellation message.
     */
    private void confirmAndToggleReadingStatus(Book book) {
        int choice = JOptionPane.showConfirmDialog(
                this,
                "Would you like to change the reading status?",
                "Confirm Status Change",
                JOptionPane.YES_NO_OPTION
        );

        if (choice == JOptionPane.YES_OPTION) {
            book.setReadingStatus(!book.getReadingStatus()); // Toggle the reading status
            JOptionPane.showMessageDialog(this, "Reading status updated successfully.");
            viewBookList.updateBookList(); // Refresh ViewBookList to reflect the update
        } else {
            JOptionPane.showMessageDialog(this, "Reading status not changed.");
        }
    }
}


