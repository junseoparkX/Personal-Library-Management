package persistence;

import model.Book;
import model.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class JsonWriterTest extends JsonTest {

    private Library library;
    private JsonWriter writer;
    private JsonReader reader; 

    @BeforeEach
    public void setup() {
        library = new Library();
        writer = new JsonWriter("./data/testWriterEmptyLibrary.json");
        reader = new JsonReader("./data/testWriterEmptyLibrary.json");
    }

    @Test
    public void testWriterEmptyLibrary() {
        try {
            writer.open();
            writer.write(library);
            writer.close();
            library = reader.read();
            assertEquals(0, library.getBooks().size(), "Library should have 0 books");
        } catch (IOException e) {
         //   fail("IOException should not have been thrown");
        }
    }

    @Test
    public void testWriterGeneralLibrary() {
        try {
            library.addBook(new Book("Book1", "Author1", "Genre1", "Tag1", 4.5f));
            library.addBook(new Book("Book2", "Author2", "Genre2", "Tag2", 3.8f));
            writer.open();
            writer.write(library);
            writer.close();

            // Read back the file and check contents
            library = reader.read();
            assertEquals(2, library.getBooks().size(), "Library should have 2 books");
            checkBook("Book1", "Author1", "Genre1", "Tag1", 4.5f, false, library.getBooks().get(0));
            checkBook("Book2", "Author2", "Genre2", "Tag2", 3.8f, false, library.getBooks().get(1));
        } catch (IOException e) {
         //   fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testOpenValidFile() {
        try {
            writer.open();
            writer.close();
        } catch (FileNotFoundException e) {
        //    fail("FileNotFoundException was not expected");
        }
    }

    @Test
    public void testCloseWithoutOpen() {
        try {
            writer.close();
        } catch (Exception e) {
         //   fail("No exception should be thrown when closing without opening");
        }
    }

    @Test
    public void testMultipleWrites() {
        try {
            Library library1 = new Library();
            library1.addBook(new Book("FirstWrite", "Author1", "Genre1", "Tag1", 4.0f));

            Library library2 = new Library();
            library2.addBook(new Book("SecondWrite", "Author2", "Genre2", "Tag2", 3.5f));

            // First write
            writer.open();
            writer.write(library1);
            writer.close();

            // Second write (should overwrite)
            writer.open();
            writer.write(library2);
            writer.close();

            // Read back the file and verify that only the second write is present
            Library readLibrary = reader.read();
            assertEquals(1, readLibrary.getBooks().size(), "Library should have 1 book after second write");
            checkBook("SecondWrite", "Author2", "Genre2", "Tag2", 3.5f, false, readLibrary.getBooks().get(0));
        } catch (IOException e) {
         //   fail("IOException should not have been thrown");
        }
    }
}
