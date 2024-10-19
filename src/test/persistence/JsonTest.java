package persistence;

import model.Book;

import static org.junit.Assert.assertEquals;

public class JsonTest {
    protected void checkBook(String title, String author, String genre, String tag,
                             float rating, boolean readingStatus, Book book) {
        assertEquals(title, book.getTitle());
        assertEquals(author, book.getAuthor());
        assertEquals(genre, book.getGenre());
        assertEquals(tag, book.getTag());
        assertEquals(rating, book.getRating(), 0.001);
        assertEquals(readingStatus, book.getReadingStatus());
    }
}

