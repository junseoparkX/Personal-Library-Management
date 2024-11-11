package ui;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import model.Library;

/**
 * LibraryAppUI represents the main user interface for the PersonalLibraryApp. 
 * It initializes the main menu with options to view and manage the library, 
 * including adding, removing, updating, and searching for books, as well as 
 * saving and loading the library data.
 */
public class LibraryAppUI extends JFrame {
    private JPanel mainPanel;
    private BufferedImage backgroundImage;
    private CardLayout cardLayout;
    private Library library;
    private ViewBookList viewBookListPanel; 

    /**
     * Constructs a new LibraryAppUI, setting up the main window and loading
     * initial components such as the menu panel and background image.
     * 
     * Modifies: this
     * Effects: Initializes the JFrame, sets up the menu panel and background image,
     *          centers the window, and makes it visible.
     */
    public LibraryAppUI() {
        super("Library Application");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);

        library = new Library();

        // Load the background image from src/main/image/library.png
        try {
            backgroundImage = ImageIO.read(new File("src/main/image/library.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set up the main panel with CardLayout for switching screens
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create and add the main menu panel
        JPanel menuPanel = createMenuPanel();
        mainPanel.add(menuPanel, "MenuPanel");

        // In LibraryAppUI, when initializing AddBookUI:
        viewBookListPanel = new ViewBookList(library, this);
        mainPanel.add(viewBookListPanel, "ViewBookListUI");
        mainPanel.add(new AddBookUI(library, this, viewBookListPanel), "AddBookUI");


        // Add the main panel to the frame
        add(mainPanel);

        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    /**
     * Creates the main menu panel with buttons for the application actions.
     * Each button is linked to a different action within the application.
     * 
     * Requires: Background image file must exist at the specified path.
     * Modifies: this
     * Effects: Returns a JPanel containing the menu layout, including background image and buttons.
     */
    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        // Title at the top of the menu panel
        JLabel titleLabel = new JLabel("PersonalLibraryApp", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        menuPanel.add(titleLabel, BorderLayout.NORTH);

        // Content panel to hold all buttons, positioned at the bottom
        JPanel contentPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        contentPanel.setOpaque(false);

        // Wide buttons panel with "View Book List" and "Trending Books" stacked vertically
        JPanel wideButtonsPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        wideButtonsPanel.setOpaque(false);
        wideButtonsPanel.add(createButton("View Book List", "ViewBookListUI"));
        wideButtonsPanel.add(createButton("Trending Books", "TrendingBooksUI"));
        contentPanel.add(wideButtonsPanel);

        // Bottom section with six smaller buttons in a 2x3 grid
        JPanel bottomButtonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        bottomButtonPanel.setOpaque(false);

        bottomButtonPanel.add(createButton("Add Book", "AddBookUI"));
        bottomButtonPanel.add(createButton("Remove Book", "RemoveBookUI"));
        bottomButtonPanel.add(createButton("Update Reading Status", "UpdateReadingStatusUI"));
        bottomButtonPanel.add(createButton("Search Books", "SearchBooksUI"));
        bottomButtonPanel.add(createButton("Load Library", "LoadLibraryUI"));
        bottomButtonPanel.add(createButton("Save Library", "SaveLibraryUI"));

        // Add bottom button panel below the wide buttons
        contentPanel.add(bottomButtonPanel);

        // Anchor the entire content panel at the bottom of the main menu
        menuPanel.add(contentPanel, BorderLayout.SOUTH);

        return menuPanel;
    }

    /**
     * Helper method to create buttons with action listeners for switching panels.
     * 
     * Requires: text and panelName must be non-null.
     * Modifies: this
     * Effects: Returns a JButton with the specified text and associated action listener
     *          for switching to the corresponding panel.
     */
    private JButton createButton(String text, String panelName) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 50)); // Set a consistent height for all buttons
        button.addActionListener(e -> showPanel(panelName));
        return button;
    }

    /**
     * Method to switch panels in the main window.
     * 
     * Requires: panelName must be non-null and a valid panel name within the CardLayout.
     * Modifies: this
     * Effects: Changes the visible panel in the main window to the specified panel.
     */
    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LibraryAppUI::new);
    }
}
