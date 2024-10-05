package model;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private ArrayList<Book> books; 

    public Library(){
        books = new ArrayList<>();
    }

    public void addBook(Book book){
        books.add(book);
        System.out.println("Book was added to the libaray: "+ book.getTitle());
    }

    public boolean removeBook(String title){
        for(int i=0; i < books.size(); i++){
            Book book = books.get(i);
            if(book.getTitle().equals(title)){
                books.remove(i);
                System.out.println("Book removed: " + title);
                return true; 
            }
        }
        System.out.println("Book \""+ title+"\" was not found");
        return false; 
    }

    public List<Book> searchBook(String keyword){
        List<Book> bookList = new ArrayList<>();
        for (Book book : books){
            if(book.getAuthor().equals(keyword) ||
               book.getTitle().equals(keyword) ||
               book.getTag().equals(keyword)){
                bookList.add(book);
               }
        }
        return bookList; 
    }



    
}
