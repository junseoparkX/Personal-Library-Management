# Personal Library Management 

## An Application to Track and Organize Your Personal Book Collection

**What will the application do?**
Personal Library Management project enables users to manage their personal book collections efficiently. Users can add new books with adding deltailed information such as genres, tags, authors and can track book loan and reading progress. This application also provide function of searching books and author, filter based on different author, genre and tags of books that user want to read and safe for later.   

**Who will use it?**
- It would be usefule for book collectors who wants to make their own personal libraries to collect their favourite book collection. 

- Readers who want to monitor their reading habits and keep wish list. 

- People who want to track their reading status of books and find their favourite books easily in their personal library. 

**Why is this project of interest to you?**

This project interests me because through the opportunity for making a personal book collection system, I can combines my passion for reading habits and my creative skills for software development.It would be a great change to enhance my java programming skills as well as design and developing user interface.  I also like reading books and want to record and save them. This project is important for me to apply what I have learned from the class and make a tool that I personally find it is useful and needed.


**Key Features:**
- There is a registeration and login system which user can make account with username and password and change username. If the user fail to login with 3 attemps, the account blocks. 
- Add New Books to a collection: Add detailed information like title, author, genre, and reading status.
- View Personal Book Collection: Display a list of all books in the library.
- Filter and Search Books: Find books by title, author, genre, or tags.
- Recommendation for book - Show trending books thesedays with the tag "Trending". (This is already contained in book collection.) 
- Track Reading Progress: Update the reading status of each book.
- Remove a book from user's library: Remove books that user mistakely added or want to exclude from their wishlist.  

# Instructions for End User

- **You can generate the first required action related to the user story "adding multiple Books to a Library" by** clicking the **"Add Book"** button in the main menu, entering the book’s details (title, author, genre, tag, and rating) in the **Add New Book** panel, and clicking **"Save"** to add it to the library.

- **You can generate the second required action related to the user story "viewing the list of books" by** clicking the **"View Book List"** button in the main menu to display a list of all books currently in the library, including details such as title, author, genre, tag, rating, and reading status.

- **You can update the reading status of a book by** clicking the **"Update Reading Status"** button in the main menu, entering the title of the book in the search bar, pressing **Enter**, and confirming the prompt to toggle the reading status between "Reading" and "Not Reading".

- **You can search for a book in the library by** clicking the **"Search Books"** button in the main menu, entering a search term (title, author, genre, or tag) in the search bar, and pressing **Enter** to display matching books in the search results panel.

- **You can remove a book from the library by** clicking the **"Remove Book"** button in the main menu, entering the title of the book in the search bar, pressing **Enter**, and selecting **Yes** in the prompt to confirm removal of the book from the library.

- **You can locate my visual component by** navigating to the **"Trending Books"** section from the main menu by clicking the **"Trending Books"** button, which displays images of popular book covers alongside descriptions of each book.

- **You can save the state of my application by** clicking the **"Save Library"** button in the main menu, which saves the current library data to a JSON file to preserve all added books.

- **You can reload the state of my application by** clicking the **"Load Library"** button in the main menu to load the saved data from the JSON file and update the library with previously saved books.


## User Stories
- As a user, I want to make my private library where I can access it by username and password. 
- As a user, I want to change my password from my account. 
- As a user, I want to be able to add a book to my library.
- As a user, I want to be able to add book in the library with adding deail information: tags, authors and genres. 
- As a user, I want to be able to view the list of books in the library.
- As a user, I want to be able to update my reading status. 
- As a user, I want to be able to search for books by title, author and genres. 
- As a user, I want to be able to remove books from the library. 
- As a user, I want to have the option to save my personal library to a file so that I can preserve my data for future use.
- As a user, I want to have the option to load my personal library from a file so that I can resume my work from where I left off.

## Phase 4: Task 2

### Event Logging

The event logging mechanism is implemented using the `Event` and `EventLog` classes, both integrated within the `model` package. Logging is restricted exclusively to the model to ensure separation of concerns and maintain clean code architecture.

### Implementation Details

#### **Logged Events in the `Library` Class**
The following actions in the `Library` class trigger event logging:

- **Adding a Book**  
  `EventLog.getInstance().logEvent(new Event("Added book: " + book.getTitle() + " by " + book.getAuthor()));`

- **Removing a Book**  
  `EventLog.getInstance().logEvent(new Event("Removed book: " + title));`

- **Searching for Books**  
  `EventLog.getInstance().logEvent(new Event("Searched for books with keyword: " + keyword));`

- **Displaying All Books**  
  `EventLog.getInstance().logEvent(new Event("Displayed all books in the library"));`

- **Attempting to Remove a Non-Existent Book**  
  `EventLog.getInstance().logEvent(new Event("Attempted to remove book: " + title + " but it was not found"));`

#### **Logged Events in the `Book` Class**
The following action in the `Book` class triggers event logging:

- **Updating Reading Status**  
  `EventLog.getInstance().logEvent(new Event("Updated reading status for book: " + title + " to " + statusMessage));`

#### **Logging Scope**
- Logging is **restricted to the `model` package** to ensure that the `ui` package focuses solely on the user interface.  
- **No logging occurs directly in the `ui` package.**

#### **Printing Logged Events**
When the application exits, all logged events are printed to the console to provide a summary of key operations performed during the session.


### Phase 4: Task 3

Reflecting on the design presented in the UML class diagram, there are several ways the Library Management System could be improved. One important area is separating concerns within the `Library` class. Currently, it manages multiple responsibilities, such as adding/removing books, searching, displaying information, and logging events, which violates the Single Responsibility Principle. Refactoring this class by introducing helper classes, such as a `LibraryManager` for book operations and a `DisplayService` for formatting and displaying book details, would make the design cleaner and more focused. This separation would also make each component easier to test and maintain independently.

Another area for improvement is reducing the direct interaction between the UI and model classes. Currently, UI components like `LibraryAppUI` directly manipulate instances of `Library` and `Book`, creating tight coupling and making future modifications or testing more challenging. Adding a `Controller` layer, such as a `LibraryController`, would manage tasks like adding or removing books, searching, and updating reading statuses. It would act as a middle layer and ensures that the UI does not directly manipulate the `Library` or `Book` classes. Moreover , the `Book` class could be extended by introducing specific subclasses, such as `FictionBook` and `NonFictionBook`, to better represent different types of books while keeping shared fields and methods in the parent `Book` class. These changes would improve the overall structure of the system, making it more adaptable and robust for future updates.

