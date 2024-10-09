package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class LibraryTest {

    private Library library;
    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    public void setUp() {
        // Initialize the LibraryTest before each test
        library = new Library();

        // Create sample Book objects
        book1 = new Book("Harry Potter", "J.K. Rowling", "Fantasy", "Magic", 4.9f);
        book2 = new Book("The Selfish Gene", "Richard Dawkins", "Science", "Evolution", 4.7f);
        book3 = new Book("The Tyranny of Merit", "Michael Sandel", "Philosophy", "Society", 4.5f);
    }


    @Test
    public void testAddBook() {
        library.addBook(book3);
        assertEquals(1, library.getBooks().size());
        assertTrue(library.getBooks().contains(book3));
    }

    @Test
    public void testRemoveBook() {
        library.addBook(book1);
        library.addBook(book2);
        boolean removed = library.removeBook("Harry Potter");
        assertTrue(removed);
        assertEquals(1, library.getBooks().size(), "Library should contain one book after removal.");
        assertFalse(library.getBooks().contains(book1), "Library should no longer contain the removed book.");
    }

    @Test
    public void testFailToRemoveBook() {
        library.addBook(book2);
        boolean removed = library.removeBook("NOT EXISTING BOOK");
        assertFalse(removed);
        assertEquals(1, library.getBooks().size());
    }

    @Test
    public void testSearchBook() {
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        // Searching with author
        List<Book> bookList = library.searchBook("Richard Dawkins");
        assertEquals(1, bookList.size());
        assertTrue(bookList.contains(book2));

        // Searching with title
        bookList = library.searchBook("Harry Potter");
        assertEquals(1, bookList.size());
        assertTrue(bookList.contains(book1));

        // Searching with tag
        bookList = library.searchBook("Society");
        assertEquals(1, bookList.size());
        assertTrue(bookList.contains(book3));
    }

    @Test
    public void testFailToSearchBook() {
        library.addBook(book1);
        library.addBook(book3);
        List<Book> bookList = library.searchBook("Non-Existent Keyword");
        assertTrue(bookList.isEmpty());
    }


    @Test
    public void testRemoveAllBooks() {
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        boolean removed1 = library.removeBook("Harry Potter");
        boolean removed2 = library.removeBook("The Selfish Gene");
        boolean removed3 = library.removeBook("The Tyranny of Merit");
        assertTrue(removed1);
        assertTrue(removed2);
        assertTrue(removed3);
        assertTrue(library.getBooks().isEmpty());
    }

    @Test
    public void testSearchBookMultipleMatches() {
        Book book4 = new Book("Harry Potter and the Chamber of Secrets", "J.K. Rowling", "Fantasy", "Magic", 4.8f);
        library.addBook(book1);
        library.addBook(book4);
        library.addBook(book2);

        List<Book> result = library.searchBook("Magic");
        assertEquals(2, result.size());
        assertTrue(result.contains(book1));
        assertTrue(result.contains(book4));
    }
    
    @Test
    public void testDisplayAllBooksWithBooks() {
        library.addBook(book1);
        library.addBook(book2);
        library.displayAllBooks();
        List<Book> expected = Arrays.asList(book1, book2);
        assertEquals(expected, library.getBooks());
    }

    @Test
    public void testAddBooks() {
        Book mazeRunner = new Book("Maze Runner", "James Dashner", "Dystopian", "Adventure", 4.2f);
        Book demian = new Book("Demian", "Hermann Hesse", "Philosophical", "Classic", 4.0f);
        
        library.addBook(mazeRunner);
        library.addBook(demian);

        List<Book> books = library.getBooks();

        assertEquals(2, books.size());

        assertTrue(books.contains(mazeRunner));
        assertTrue(books.contains(demian));
    }

    @Test
    public void testDisplayAllBooksWithOneBook() {
        Book mazeRunner = new Book("Maze Runner", "James Dashner", "Dystopian", "Adventure", 4.2f);
        library.addBook(mazeRunner);

        List<String> expectedList = List.of(
                "Books in the library:",
                "Title: Maze Runner, Author: James Dashner, Genre: Dystopian, Tag: Adventure, Rating: 4.2, Reading Status: Not Reading"
        );

        List<String> actualList = library.displayAllBooks();
        assertEquals(expectedList, actualList);
    }


    @Test
    public void testDisplayAllBooksWithMultipleBooks() {
        Book mazeRunner = new Book("Maze Runner", "James Dashner", "Dystopian", "Adventure", 4.2f);
        Book demian = new Book("Demian", "Hermann Hesse", "Philosophical", "Classic", 4.0f);
        demian.setReadingStatus(true);
        library.addBook(mazeRunner);
        library.addBook(demian);

        List<String> expectedList = List.of(
                "Books in the library:",
                "Title: Maze Runner, Author: James Dashner, Genre: Dystopian, Tag: Adventure, Rating: 4.2, Reading Status: Not Reading",
                "Title: Demian, Author: Hermann Hesse, Genre: Philosophical, Tag: Classic, Rating: 4.0, Reading Status: Reading"
        );

        // Call the modified displayAllBooks method and compare the output
        List<String> actualList = library.displayAllBooks();
        assertEquals(expectedList, actualList);
    }

    @Test
    public void testDisplayAllBooks_EmptyLibrary() {
        List<String> expectedList = List.of("There are no books in the library");
        List<String> actualList = library.displayAllBooks();
        assertEquals(expectedList, actualList);
    }
    
}
