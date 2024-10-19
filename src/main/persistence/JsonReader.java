package persistence;

import model.Book;
import model.Library;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonReader {
    private String source;

    // EFFECTS: constructs a reader to read from the specified source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads the library from the file and returns it;
    // throws IOException if an error occurs reading data from the file
    public Library read() throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(source)));
        JSONObject jsonObject = new JSONObject(content);
        return parseLibrary(jsonObject);
    }

    // EFFECTS: parses the library from a JSON object and returns it
    private Library parseLibrary(JSONObject jsonObject) {
        Library library = new Library();
        addBooks(library, jsonObject);
        return library;
    }

    // MODIFIES: library
    // EFFECTS: parses books from the JSON object and adds them to the library
    private void addBooks(Library library, JSONObject jsonObject) {
        JSONArray booksArray = jsonObject.getJSONArray("books");
        for (Object obj : booksArray) {
            JSONObject bookJson = (JSONObject) obj;
            Book book = parseBook(bookJson);
            library.addBook(book);
        }
    }

    // EFFECTS: parses a book from a JSON object and returns it
    private Book parseBook(JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        String author = jsonObject.getString("author");
        String genre = jsonObject.getString("genre");
        String tag = jsonObject.getString("tag");
        float rating = (float) jsonObject.getDouble("rating");
        boolean readingStatus = jsonObject.getBoolean("readingStatus");

        Book book = new Book(title, author, genre, tag, rating);
        book.setReadingStatus(readingStatus);
        return book;
    }
}
