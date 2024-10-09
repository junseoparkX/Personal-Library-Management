package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
    
    private Book book;

    @BeforeEach
    void runBefore() {
        book = new Book("The Selfish Gene", "Richard Dawkins", "Science", "Evolution", 4.9f);
    }

    @Test
    void testConsturctor() {
        assertEquals("The Selfish Gene", book.getTitle());
        assertEquals("Richard Dawkins", book.getAuthor());
        assertEquals("Science", book.getGenre());
        assertEquals("Evolution", book.getTag());
        assertEquals(4.9f, book.getRating());
        assertFalse(book.getReadingStatus()); 
    }

    @Test
    void testUpdateReadingStatus() {
        assertFalse(book.getReadingStatus()); 

        // update from false to true
        book.updateReadingStatus(true);
        assertTrue(book.getReadingStatus()); 

        // update from true to false
        book.updateReadingStatus(false);
        assertFalse(book.getReadingStatus()); 
    }

    @Test
    void testSetReadingStatus() {
        assertFalse(book.getReadingStatus()); 

        // update reading status to true
        book.setReadingStatus(true);
        assertTrue(book.getReadingStatus()); 

        // update reading status to false
        book.setReadingStatus(false);
        assertFalse(book.getReadingStatus()); 
    }

    @Test
    void testSetRating(){
        assertEquals(4.9f, book.getRating());

        // update the book rating from 4.9 to 4.7
        book.setRating(4.7f);
        assertEquals(4.7f, book.getRating());
    }
}
