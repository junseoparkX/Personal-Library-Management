package ui;

import java.awt.*;
import java.util.List;
import javax.swing.*;
import model.Library;

/**
 * ViewBookList provides a user interface panel for displaying the list of books in the library.
 * It shows book details in a scrollable text area and allows users to return to the main menu.
 */
public class ViewBookList extends JPanel {
    private Library library;
    private JTextArea bookListArea;
    private LibraryAppUI parentFrame;

    /**
     * Constructs a new ViewBookList panel to display all books in the library.
     *
     * Requires: library and parentFrame must be non-null.
     * Modifies: this
     * Effects: Initializes the UI elements, sets up layout and action listeners.
     */
    public ViewBookList(Library library, LibraryAppUI parentFrame) {
        this.library = library;
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());

        // Title label for the view
        JLabel titleLabel = new JLabel("Library Book List", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Text area to display the list of books
        bookListArea = new JTextArea();
        bookListArea.setEditable(false);
        bookListArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(bookListArea);
        add(scrollPane, BorderLayout.CENTER);

        // Back button to return to the main menu
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> parentFrame.showPanel("MenuPanel"));
        add(backButton, BorderLayout.SOUTH);

        // Initial display of books
        updateBookList();
    }

    /**
     * Updates the display with the current list of books in the library.
     *
     * Requires: library must be initialized and contain the books to display.
     * Modifies: bookListArea
     * Effects: Clears the text area and populates it with a list of all books in the library,
     *          where each book is shown on a new line.
     */
    public void updateBookList() {
        List<String> bookList = library.displayAllBooks(); // Call Library's displayAllBooks()
        bookListArea.setText(""); 
        for (String bookInfo : bookList) {
            bookListArea.append(bookInfo + "\n"); 
        }
    }
}
