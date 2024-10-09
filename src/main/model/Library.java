package model;

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
    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("There are no books in the library");
        }
        System.out.println("Books in the library:");
        for (Book book : books) {
                System.out.println("Title: " + book.getTitle() +
                               ", Author: " + book.getAuthor() +
                               ", Genre: " + book.getGenre() +
                               ", Tag: " + book.getTag() +
                               ", Rating: " + book.getRating() +
                               ", Reading Status: " + (book.getReadingStatus() ? "Reading" : "Not Reading"));
        }
    }
}
