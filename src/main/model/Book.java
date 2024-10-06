package model;

public class Book {  
    private String title;
    private String genre; 
    private String tag; 
    private String author;
    private Float rating; 
    private Boolean readingStatus;
    private Boolean wishToRead; 

    // Constructor
    public Book(String title, String author, String genre, String tags, Float rating){
        this.title = title; 
        this.genre = genre; 
        this.tag = tags; 
        this.rating = rating; 
        this.author = author; 
        this.readingStatus = false; 
        this.wishToRead = false;
    }

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

    public void updateReadingStatus(Boolean status){
        this.readingStatus = status; 
        if (!status){
            System.out.println("\"" + title + "\" is now marked as \"Reading\"");
        } else{
            System.out.println("\"" + title + "\" is now marked as \"Not reading\"" );
        }

    }
    
    //toString method to print book details
    @Override
    public String toString() {
        return "Title: " + title + ", Genre: " + genre + ", Rating: " + rating + ", Reading Status: " + readingStatus + ", Wishlist: " + wishToRead;
    }
}
