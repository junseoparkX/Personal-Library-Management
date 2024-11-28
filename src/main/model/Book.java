package model;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

/**
 * Represents a book with attributes such as title, author, genre, tag, and rating.
 * It also tracks the reading status of the book, allowing users to mark it as 
 * "Reading" or "Not reading". Provides methods for accessing and updating these 
 * attributes and for converting the book's information into JSON format.
 */
public class Book implements Writable {  
    private String title;
    private String genre; 
    private String tag; 
    private String author;
    private float rating; 
    private boolean readingStatus; 

    // Constructor
    // EFFECTS: Initializes a new Book with the provided title, author, genre, tag, and rating.
    //          Sets readingStatus to false by default.
    public Book(String title, String author, String genre, String tag, float rating) {
        this.title = title; 
        this.genre = genre; 
        this.tag = tag; 
        this.rating = rating; 
        this.author = author; 
        this.readingStatus = false; 
    }

    // Getters
    public String getTitle() { 
        return title; 
    }

    public String getAuthor() { 
        return author; 
    }

    public String getGenre() { 
        return genre; 
    }

    public String getTag() { 
        return tag;
    }

    public Float getRating() { 
        return rating; 
    }

    public Boolean getReadingStatus() { 
        return readingStatus; 
    }

    // Setters
    public void setTitle(String title) { 
        this.title = title; 
    }

    public void setReadingStatus(boolean readingStatus) { 
        String statusMessage = "Not Reading";
        if (readingStatus) {
            statusMessage = "Reading";
        }
        EventLog.getInstance().logEvent(new Event("Updated reading status for book: " 
                        + title + " to " + statusMessage));
        this.readingStatus = readingStatus; 
    }

    public void setRating(float rating) { 
        this.rating = rating; 
    }

    // MODIFIES: this
    // EFFECTS: Sets readingStatus to the provided status 
    public void updateReadingStatus(boolean status) {
        this.readingStatus = status; 
    }

    // EFFECTS: returns this book as a JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("author", author);
        json.put("genre", genre);
        json.put("tag", tag);
        json.put("rating", rating);
        json.put("readingStatus", readingStatus);
        return json;
    }
}
