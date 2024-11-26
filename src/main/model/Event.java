package model;

import java.util.Calendar;
import java.util.Date;

/**
 * Represents an event with a timestamp and description.
 */
public class Event {
    private static final int HASH_CONSTANT = 13; // Constant for generating hash codes
    private Date dateLogged; // The date and time when the event was logged
    private String description; // A brief description of the event

    /**
     * Creates an event with the given description and a timestamp.
     * 
     * Modifies: this
     * Effects: Initializes the event with the given description and sets the
     *          timestamp to the current date and time.
     *
     * @param description the description of the event
     */
    public Event(String description) {
        dateLogged = Calendar.getInstance().getTime();
        this.description = description;
    }

    /**
     * Returns the date and time of the event.
     *
     * @return the date of the event
     */
    public Date getDate() {
        return dateLogged;
    }

    /**
     * Returns the description of the event.
     *
     * @return the event description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks equality between this event and another object.
     * 
     * Effects: Returns true if the given object is an Event and has the same
     *          dateLogged and description as this event; false otherwise.
     *
     * @param other the object to compare with
     * @return true if both events have the same date and description, false otherwise
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || other.getClass() != this.getClass()) {
            return false;
        }
        Event otherEvent = (Event) other;
        return this.dateLogged.equals(otherEvent.dateLogged) 
            && this.description.equals(otherEvent.description);
    }

    /**
     * Generates a hash code for the event based on its fields.
     *
     * @return the hash code for this event
     */
    @Override
    public int hashCode() {
        return (HASH_CONSTANT * dateLogged.hashCode() + description.hashCode());
    }

    /**
     * Converts the event to a string representation.
     *
     * @return a string containing the event's date and description
     */
    @Override
    public String toString() {
        return dateLogged.toString() + "\n" + description;
    }
}
