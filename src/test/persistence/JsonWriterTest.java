package persistence;

import model.Book;
import model.Library;
import org.junit.Test;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    public void testWriterInvalidFile() {
        try {
            Library library = new Library();
            JsonWriter writer = new JsonWriter("./data/\0illegal:fileName.json");
            writer.open();
            fail("FileNotFoundException was expected");
        } catch (FileNotFoundException e) {
            // Expected exception caught, test passes
        }
    }

    @Test
    public void testWriterEmptyLibrary() {
        try {
            Library library = new Library();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyLibrary.json");
            writer.open();
            writer.write(library);
            writer.close();

            // Read back the file and check contents
            JsonReader reader = new JsonReader("./data/testWriterEmptyLibrary.json");
            library = reader.read();
            assertEquals(0, library.getBooks().size());
        } catch (IOException e) {
            fail("IOException should not have been thrown");
        }
    }

    @Test
    public void testWriterGeneralLibrary() {
        try {
            Library library = new Library();
            library.addBook(new Book("Book1", "Author1", "Genre1", "Tag1", 4.5f));
            library.addBook(new Book("Book2", "Author2", "Genre2", "Tag2", 3.8f));

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralLibrary.json");
            writer.open();
            writer.write(library);
            writer.close();

            // Read back the file and check contents
            JsonReader reader = new JsonReader("./data/testWriterGeneralLibrary.json");
            library = reader.read();
            assertEquals(2, library.getBooks().size());
            checkBook("Book1", "Author1", "Genre1", "Tag1", 4.5f, false, library.getBooks().get(0));
            checkBook("Book2", "Author2", "Genre2", "Tag2", 3.8f, false, library.getBooks().get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}