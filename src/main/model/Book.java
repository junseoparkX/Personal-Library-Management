package model;

public class Book {  
    private String title;
    private String genre; 
    private String tags; 
    private Float rating; 
    private Boolean readingStatus;
    private Boolean wishToRead; 

    // Constructor
    public Book(String title, String genre, String tags, Float rating){
        this.title = title; 
        this.genre = genre; 
        this.tags = tags; 
        this.rating = rating; 
        this.readingStatus = false; 
        this.wishToRead = false;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getTags() {
        return tags;
    }

    public Float getRating() {
        return rating;
    }

    public Boolean getReadingStatus() {
        return readingStatus;
    }
    
    public Boolean getWishToRead() {
        return wishToRead;
    }

    public void setReadingStatus(Boolean readingStatus) {
        this.readingStatus = readingStatus;
    }

    public void setWishToRead(Boolean wishToRead) {
        this.wishToRead = wishToRead;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }
    
    //toString method to print book details
    @Override
    public String toString() {
        return "Title: " + title + ", Genre: " + genre + ", Rating: " + rating + ", Reading Status: " + readingStatus + ", Wishlist: " + wishToRead;
    }
}
