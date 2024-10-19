package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private ArrayList<Book> books; 

    // EFFECTS: initializes the books list
    public Library() {
        books = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a book to the library and prints a confirmation message
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book was added to the library: " + book.getTitle());
    }

    // MODIFIES: this
    // EFFECTS: removes a book with the specified title from the library
    public boolean removeBook(String title) {
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (book.getTitle().equals(title)) {
                books.remove(i);
                System.out.println("Book removed: " + title);
                return true; 
            }
        }
        System.out.println("Book \"" + title + "\" was not found");
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
