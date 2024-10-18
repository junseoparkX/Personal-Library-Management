package persistence;

import model.Library;
import model.Book;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Tests for JsonReader
public class JsonReaderTest extends JsonTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Library library = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // Pass
        }
    }

    @Test
    public void testReaderEmptyLibrary() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyLibrary.json");
        try {
            Library library = reader.read();
            assertEquals(0, library.getBooks().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralLibrary() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralLibrary.json");
        try {
            Library library = reader.read();
            List<Book> books = library.getBooks();
            assertEquals(2, books.size());
            checkBook("Harry Potter", "J.K. Rowling", "Adventure", "Trending", 4.8f,
                      false, books.get(0));
            checkBook("Maze Runner", "James Dashner", "Dystopian", "Adventure", 4.2f,
                      true, books.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
