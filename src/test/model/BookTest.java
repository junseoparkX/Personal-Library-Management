package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.json.JSONObject;

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

 @Test
    void testToJson() {
        JSONObject json = book.toJson();

        assertEquals("The Selfish Gene", json.getString("title"));
        assertEquals("Richard Dawkins", json.getString("author"));
        assertEquals("Science", json.getString("genre"));
        assertEquals("Evolution", json.getString("tag"));
        assertEquals(4.9f, (float) json.getDouble("rating"), 0.001);
        assertFalse(json.getBoolean("readingStatus"));

        // Update reading status and check JSON again
        book.updateReadingStatus(true);
        json = book.toJson();
        assertTrue(json.getBoolean("readingStatus"));
    }

    @Test
    void testSetTitle() {
        assertEquals("The Selfish Gene", book.getTitle());

        // Update the title
        book.setTitle("The Extended Phenotype");
        assertEquals("The Extended Phenotype", book.getTitle());

        // Update the title to an empty string
        book.setTitle("");
        assertEquals("", book.getTitle());

        // Update the title to null
        book.setTitle(null);
        assertNull(book.getTitle());
    }

}
