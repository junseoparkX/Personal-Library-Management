package model;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a library that manages a collection of books. 
 * Provides methods to add, remove, search, and display books in the library.
 * Additionally, it supports converting the library's book collection to a JSON format.
 * Each book in the library is represented by the Book class.
 */
public class Library implements Writable {
    private ArrayList<Book> books; 

    // EFFECTS: initializes the books list
    public Library() {
        books = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a book to the library
    public void addBook(Book book) {
        EventLog.getInstance().logEvent(new Event("Added book: " + book.getTitle() + " by " + book.getAuthor()));
        books.add(book);
    }

    // MODIFIES: this
    // EFFECTS: removes a book with the specified title from the library
    public boolean removeBook(String title) {
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getTitle().equals(title)) {
                books.remove(i);
                EventLog.getInstance().logEvent(new Event("Removed book: " + title));
                return true; 
            }
        }
        EventLog.getInstance().logEvent(new Event("Attempted to remove book: " + title + " but it was not found"));
        return false; 
    }

    // EFFECTS: searches for books matching the keyword in author, title, or tag
    public List<Book> searchBook(String keyword) {
        List<Book> bookList = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equals(keyword) 
                    || book.getTitle().equals(keyword) 
                    || book.getTag().equals(keyword)) {
                bookList.add(book);
            }
        }
        EventLog.getInstance().logEvent(new Event("Searched for books with keyword: " + keyword));
        return bookList; 
    }

    // EFFECTS: returns the list of books in the library
    public List<Book> getBooks() {
        return books;
    }

    // EFFECTS: displays all books in the library or a message if the library is empty
    public List<String> displayAllBooks() {
        List<String> output = new ArrayList<>();
    
        if (books.isEmpty()) {
            output.add("There are no books in the library");
        } else {
            output.add("Books in the library:");
            for (Book book : books) {
                output.add("Title: " + book.getTitle() 
                        + ", Author: " + book.getAuthor() 
                        + ", Genre: " + book.getGenre() 
                        + ", Tag: " + book.getTag() 
                        + ", Rating: " + book.getRating() 
                        + ", Reading Status: " + (book.getReadingStatus() ? "Reading" : "Not Reading"));
            }
            EventLog.getInstance().logEvent(new Event("Displayed all books in the library"));
        }
        return output;
    }   

    // EFFECTS: returns this library as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("books", booksToJson());
        return json;
    }

    // EFFECTS: returns books in this library as a JSON array
    private JSONArray booksToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Book book : books) {
            jsonArray.put(book.toJson());
        }

        return jsonArray;
    }
}
