package ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * Represents the TrendingBooks panel, displaying a list of popular books with images and descriptions.
 */
public class TrendingBooks extends JPanel {
    private LibraryAppUI parentFrame;

    /**
     * Constructs a new TrendingBooks panel within the application.
     * 
     * Modifies: this
     * Effects: Initializes the layout, adds a title label, sets up book panels with images and descriptions,
     *          and adds a back button to navigate back to the main menu.
     */
    public TrendingBooks(LibraryAppUI parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());

        // Title label for the view
        JLabel titleLabel = new JLabel("Trending Books", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // Panel to hold book images and descriptions
        JPanel booksPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        booksPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add each book with its image and description
        booksPanel.add(createBookPanel("Harry Potter and the Philosopher's Stone", "J.K. Rowling", 
                "src/main/image/HarryPotter.png",
                 "The first book in the Harry Potter series where Harry discovers he's a wizard."));
        booksPanel.add(createBookPanel("The Maze Runner", "James Dashner", 
                "src/main/image/MazeRunner.png", 
                "A thrilling story of teens trying to survive in a deadly maze."));
        booksPanel.add(createBookPanel("The Great Gatsby", "F. Scott Fitzgerald", 
                "src/main/image/TheGreatGatsby.png", 
                "A classic tale of wealth, love, and the American Dream in the 1920s."));
        booksPanel.add(createBookPanel("To Kill a Mockingbird", "Harper Lee", 
                "src/main/image/ToKillAMockingbird.png", 
                "A profound story about racial injustice in the American South."));

        // Add the books panel to the center
        add(booksPanel, BorderLayout.CENTER);

        // Back button to return to the main menu
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> parentFrame.showPanel("MenuPanel"));
        add(backButton, BorderLayout.SOUTH);
    }

    /**
     * Creates a panel for a single book with its image and description.
     * 
     * Requires: title, author, imagePath, and description are non-null strings.
     * Modifies: this
     * Effects: Creates and returns a JPanel containing the book's title, author, image, and description.
     *          If the image cannot be loaded, displays a placeholder text instead.
     * 
     */
    private JPanel createBookPanel(String title, String author, String imagePath, String description) {
        JPanel bookPanel = new JPanel(new BorderLayout());
        bookPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Load and display the book image, resizing it to a smaller width
        try {
            BufferedImage bookImage = ImageIO.read(new File(imagePath));
            Image scaledImage = bookImage.getScaledInstance(100, 150, Image.SCALE_SMOOTH); // Resize image to fit nicely
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            bookPanel.add(imageLabel, BorderLayout.WEST);
        } catch (IOException e) {
            e.printStackTrace();
            JLabel errorLabel = new JLabel("Image not found");
            bookPanel.add(errorLabel, BorderLayout.WEST);
        }

        // Book description
        JTextArea descriptionArea = new JTextArea(
                "Title: " + title + "\n" 
                + "Author: " + author + "\n\n" 
                + description
        );
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 14));
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        bookPanel.add(descriptionArea, BorderLayout.CENTER);

        return bookPanel;
    }
}


