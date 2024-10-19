package persistence;

import model.Library;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class JsonWriter {
    private String destination;
    private PrintWriter writer;

    // EFFECTS: constructs a writer to write to the specified destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens the writer; throws FileNotFoundException if the file cannot be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    // MODIFIES: this
    // EFFECTS: writes the JSON representation of the library to the file
    public void write(Library library) {
        JSONObject json = library.toJson();
        saveToFile(json.toString(4)); // Indent with 4 spaces for readability
    }

    // MODIFIES: this
    // EFFECTS: closes the writer
    public void close() {
        if (writer != null) {
            writer.close();
        }
    }
    // MODIFIES: this
    // EFFECTS: writes a string to the file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
