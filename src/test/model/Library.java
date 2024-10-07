package model;

import org.junit.jupiter.api.BeforeEach;

public class Library.Test {
    private Library library;
    private Book book1; 
    private Book book2; 
    private Book book3;

    @BeforeEach
    public void setUp() {
        // Initialize the Library before each test
        library = new Library();

        // Create sample Book objects
        book1 = new Book("Harry Potter", "J.K. Rowling", "Fantasy", "Magic", 4.9f);
        book2 = new Book("The Selfish Gene", "Richard Dawkins", "Science", "Evolution", 4.7f);
        book3 = new Book("The Tyranny of Merit", "Michael Sandel", "Philosophy", "Society", 4.5f);
    }

    @Test
    public testAddBook(){
        library.addBook(book2);
        asserEquals(1, library.size());
    }
}
