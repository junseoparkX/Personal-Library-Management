package persistence;

import model.Book;
import model.Library;
import org.junit.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class JsonReaderTest extends JsonTest {

    @Test
    public void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Library library = reader.read();
            fail("IOException was expected");
        } catch (IOException e) {
            // Expected exception caught, test passes
        }
    }

    @Test
    public void testReaderEmptyLibrary() {
        try {
            // First, write an empty library to a file
            Library library = new Library();
            JsonWriter writer = new JsonWriter("./data/testReaderEmptyLibrary.json");
            writer.open();
            writer.write(library);
            writer.close();

            // Now, read the library back
            JsonReader reader = new JsonReader("./data/testReaderEmptyLibrary.json");
            library = reader.read();
            assertEquals(0, library.getBooks().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    public void testReaderGeneralLibrary() {
        try {
            // First, write a library with books to a file
            Library library = new Library();
            Book book1 = new Book("Book1", "Author1", "Genre1", "Tag1", 4.5f);
            book1.setReadingStatus(true);
            Book book2 = new Book("Book2", "Author2", "Genre2", "Tag2", 3.8f);
            library.addBook(book1);
            library.addBook(book2);

            JsonWriter writer = new JsonWriter("./data/testReaderGeneralLibrary.json");
            writer.open();
            writer.write(library);
            writer.close();

            // Now, read the library back
            JsonReader reader = new JsonReader("./data/testReaderGeneralLibrary.json");
            library = reader.read();
            assertEquals(2, library.getBooks().size());
            checkBook("Book1", "Author1", "Genre1", "Tag1", 4.5f, true, library.getBooks().get(0));
            checkBook("Book2", "Author2", "Genre2", "Tag2", 3.8f, false, library.getBooks().get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}

