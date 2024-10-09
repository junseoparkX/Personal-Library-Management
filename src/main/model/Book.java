package model;

public class Book {  
    private String title;
    private String genre; 
    private String tag; 
    private String author;
    private Float rating; 
    private Boolean readingStatus; 

    // Constructor
    // EFFECTS: Initializes a new Book with the provided title, author, genre, tag, and rating.
    //          Sets readingStatus to false by default.
    public Book(String title, String author, String genre, String tags, Float rating) {
        this.title = title; 
        this.genre = genre; 
        this.tag = tags; 
        this.rating = rating; 
        this.author = author; 
        this.readingStatus = false; 
    }

    // EFFECTS: Returns the title.
    public String getTitle() {
        return title;
    }

    // EFFECTS: Returns the author.
    public String getAuthor() {
        return author;
    }

    // EFFECTS: Returns the genre.
    public String getGenre() {
        return genre;
    }

    // EFFECTS: Returns the tag.
    public String getTag() {
        return tag;
    }

    // EFFECTS: Returns the rating.
    public Float getRating() {
        return rating;
    }

    // EFFECTS: Returns true if the book is being read
    public Boolean getReadingStatus() {
        return readingStatus;
    }

    // MODIFIES:this
    // EFFECTS: Updates the readingStatus to the provided value.
    public void setReadingStatus(Boolean readingStatus) {
        this.readingStatus = readingStatus;
    }

    // MODIFIES:this
    // EFFECTS: Updates the rating to the provided value.
    public void setRating(Float rating) {
        this.rating = rating;
    }

    // MODIFIES: this
    // EFFECTS: Sets readingStatus to the provided status and print a status
    public void updateReadingStatus(Boolean status) {
        this.readingStatus = status; 
        if (!status) {
            System.out.println("\"" + title + "\" is now marked as \"Reading\"");
        } else {
            System.out.println("\"" + title + "\" is now marked as \"Not reading\"");
        }

    }

}
