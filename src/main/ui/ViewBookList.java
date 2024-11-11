package ui;

import javax.swing.*;
import model.Library;

/**
 * ViewBookList provides a user interface panel for displaying the list of books in the library.
 * It shows book details in a scrollable text area and allows users to return to the main menu.
 */
public class ViewBookList extends JPanel {

    /**
     * Constructs a new ViewBookList panel to display all books in the library.
     *
     * Requires: library and parentFrame must be non-null.
     * Modifies: this
     * Effects: Initializes the UI elements, sets up layout and action listeners.
     */
    public ViewBookList(Library library, LibraryAppUI parentFrame) {

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

    }
}
