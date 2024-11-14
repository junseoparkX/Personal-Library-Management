package ui;

import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import model.Library;
import persistence.JsonReader;
import persistence.JsonWriter;

/**
 * LibraryAppUI represents the main user interface for the PersonalLibraryApp. 
 * It initializes the main menu with options to view and manage the library, 
 * including adding, removing, updating, and searching for books, as well as 
 * saving and loading the library data.
 */
public class LibraryAppUI extends JFrame {
    private static final String JSON_STORE = "./data/library.json"; // Path to the JSON file
    private JPanel mainPanel;
    private BufferedImage backgroundImage;
    private CardLayout cardLayout;
    private Library library;
    private ViewBookList viewBookListPanel;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

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

        initializeLibraryComponents();
        loadBackgroundImage();
        setupMainPanel();
        
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }

    /**
     * Initializes the library and JSON components.
     * 
     * Modifies: this
     * Effects: Instantiates the Library object and JSON reader/writer for data storage.
     */
    private void initializeLibraryComponents() {
        library = new Library();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    /**
     * Loads the background image from the specified path.
     * 
     * Modifies: this
     * Effects: Sets the background image for the main menu. If loading fails, prints the stack trace.
     */
    private void loadBackgroundImage() {
        try {
            backgroundImage = ImageIO.read(new File("src/main/image/library.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets up the main panel with CardLayout and adds all UI panels.
     * 
     * Modifies: this
     * Effects: Configures the main panel with CardLayout, adds the menu panel, 
     *          and includes other UI components for various functionalities.
     */
    private void setupMainPanel() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create and add the main menu panel
        JPanel menuPanel = createMenuPanel();
        mainPanel.add(menuPanel, "MenuPanel");

        initializeUIPanels();

        add(mainPanel);
    }

    /**
     * Initializes and adds various UI panels for different functionalities.
     * 
     * Modifies: this
     * Effects: Adds multiple panels to the main panel for viewing, adding, removing,
     *          searching, and updating books in the library.
     */
    private void initializeUIPanels() {
        viewBookListPanel = new ViewBookList(library, this);
        mainPanel.add(viewBookListPanel, "ViewBookListUI");
        mainPanel.add(new AddBookUI(library, this, viewBookListPanel), "AddBookUI");

        RemoveBook removeBookPanel = new RemoveBook(library, this, viewBookListPanel);
        mainPanel.add(removeBookPanel, "RemoveBook");

        SearchBook searchBookPanel = new SearchBook(library, this);
        mainPanel.add(searchBookPanel, "SearchBook");

        UpdateReadingStatus updateReadingStatusPanel = new UpdateReadingStatus(library, this, viewBookListPanel);
        mainPanel.add(updateReadingStatusPanel, "UpdateReadingStatus");

        // Inside LibraryAppUI constructor or setup method
        TrendingBooks trendingBooksPanel = new TrendingBooks(this);
        mainPanel.add(trendingBooksPanel, "TrendingBooksUI");
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
        JPanel menuPanel = createPanelWithBackgroundAndTitle();

        // Content panel to hold all buttons, positioned at the bottom
        JPanel contentPanel = createButtonPanel();

        // Anchor the entire content panel at the bottom of the main menu
        menuPanel.add(contentPanel, BorderLayout.SOUTH);

        return menuPanel;
    }

    /**
     * Creates the main panel with background and title.
     * 
     * Effects: Returns a JPanel with the specified background image and title.
     */
    private JPanel createPanelWithBackgroundAndTitle() {
        JPanel menuPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };

        JLabel titleLabel = new JLabel("PersonalLibraryApp", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        menuPanel.add(titleLabel, BorderLayout.NORTH);

        return menuPanel;
    }

        /**
     * Creates the button panel with all menu options.
     * 
     * Modifies: this
     * Effects: Returns a JPanel containing buttons for all main menu options.
     */
    private JPanel createButtonPanel() {
        JPanel contentPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        contentPanel.setOpaque(false);

        // Add the wide buttons and bottom buttons to the content panel
        contentPanel.add(createWideButtonsPanel());
        contentPanel.add(createBottomButtonPanel());

        return contentPanel;
    }

    /**
     * Creates the wide buttons panel with "View Book List" and "Trending Books".
     * 
     * Effects: Returns a JPanel containing the wide buttons.
     */
    private JPanel createWideButtonsPanel() {
        JPanel wideButtonsPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        wideButtonsPanel.setOpaque(false);
        wideButtonsPanel.add(createButton("View Book List", "ViewBookListUI"));
        wideButtonsPanel.add(createButton("Trending Books", "TrendingBooksUI"));
        return wideButtonsPanel;
    }

    /**
     * Creates the bottom button panel with additional options and actions.
     * 
     * Modifies: this
     * Effects: Returns a JPanel containing the bottom buttons for the application.
     */
    private JPanel createBottomButtonPanel() {
        JPanel bottomButtonPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        bottomButtonPanel.setOpaque(false);

        bottomButtonPanel.add(createButton("Add Book", "AddBookUI"));
        bottomButtonPanel.add(createButton("Remove Book", "RemoveBook"));
        bottomButtonPanel.add(createButton("Update Reading Status", "UpdateReadingStatus"));
        bottomButtonPanel.add(createButton("Search Books", "SearchBook"));

        JButton loadButton = new JButton("Load Library");
        loadButton.addActionListener(e -> loadLibrary());
        bottomButtonPanel.add(loadButton);

        JButton saveButton = new JButton("Save Library");
        saveButton.addActionListener(e -> saveLibrary());
        bottomButtonPanel.add(saveButton);

        return bottomButtonPanel;
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
     * Saves the current library data to a JSON file.
     * 
     * Requires: JSON_STORE must be a valid file path and jsonWriter must be initialized.
     * Modifies: JSON_STORE file
     * Effects: Writes the library data to JSON_STORE; displays success or error message.
     */
    private void saveLibrary() {
        try {
            jsonWriter.open();
            jsonWriter.write(library);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Library saved successfully to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Error: Unable to write to file: " + JSON_STORE, 
                                                "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Loads the library data from a JSON file.
     * 
     * Requires: JSON_STORE must be a valid file path and jsonReader must be initialized.
     * Modifies: this, library, viewBookListPanel
     * Effects: Reads library data from JSON_STORE; displays success or error message,
     *          and refreshes the ViewBookList panel to show loaded data.
     */
    private void loadLibrary() {
        try {
            Library loadedLibrary = jsonReader.read();
            library.getBooks().clear(); // Clear current library
            library.getBooks().addAll(loadedLibrary.getBooks()); // Add loaded books to existing library

            viewBookListPanel.updateBookList(); // Refresh the display
            JOptionPane.showMessageDialog(this, "Library loaded successfully from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error: Unable to read from file: " + JSON_STORE,
                                             "Load Error", JOptionPane.ERROR_MESSAGE);
        }
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
