package persistence;

import org.json.JSONObject;

/**
 * An interface representing a writable object that can be converted to a JSON object.
 * Classes implementing this interface must provide a `toJson` method that returns
 * a JSON representation.
 */
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}