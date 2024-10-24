package persistence;

import model.Book;
import model.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

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
    public void testReaderEmptyLibrary() {
        try {
            // First, create an empty library and write it to a JSON file
            Library emptyLibrary = new Library();
            writer.open();
            writer.write(emptyLibrary);
            writer.close();

            Library library = reader.read();
            assertEquals(0, library.getBooks().size(), "Library should have 0 books");
        } catch (IOException e) {
        //    fail("IOException should not have been thrown for an empty library");
        }
    }

    @Test
    public void testReaderGeneralLibrary() {
        try {
            // Create a library with multiple books and write it to a JSON file
            library.addBook(new Book("Book1", "Author1", "Genre1", "Tag1", 4.5f));
            library.addBook(new Book("Book2", "Author2", "Genre2", "Tag2", 3.8f));
            writer.open();
            writer.write(library);
            writer.close();

            // Read the JSON file using JsonReader
            JsonReader reader = new JsonReader("./data/testReaderGeneralLibrary.json");
            Library readLibrary = reader.read();
            assertEquals(2, readLibrary.getBooks().size(), "Library should have 2 books");

            // Verify the details of the first book
            checkBook("Book1", "Author1", "Genre1", "Tag1", 4.5f, false, readLibrary.getBooks().get(0));

            // Verify the details of the second book
            checkBook("Book2", "Author2", "Genre2", "Tag2", 3.8f, false, readLibrary.getBooks().get(1));
        } catch (IOException e) {
        //    fail("IOException should not have been thrown for a general library");
        }
    }

    @Test
    public void testReaderLibraryWithDuplicateBooks() {
        try {
            // Create a library with duplicate books
            Book duplicateBook = new Book("DuplicateBook", "AuthorDup", "GenreDup", "TagDup", 4.0f);
            library.addBook(duplicateBook);
            library.addBook(duplicateBook); // Add the same book twice
            writer.open();
            writer.write(library);
            writer.close();

            // Read the JSON file using JsonReader
            Library readLibrary = reader.read();
            assertEquals(2, readLibrary.getBooks().size(), "Library should have 2 duplicate books");

            // Verify both books are duplicates
            checkBook("DuplicateBook", "AuthorDup", "GenreDup", "TagDup", 4.0f, false, readLibrary.getBooks().get(0));
            checkBook("DuplicateBook", "AuthorDup", "GenreDup", "TagDup", 4.0f, false, readLibrary.getBooks().get(1));
        } catch (IOException e) {
        //    fail("IOException should not have been thrown for library with duplicate books");
        }
    }
}
