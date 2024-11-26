package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Represents a log that tracks all recorded events.
 * Implements the Singleton Design Pattern to ensure a single global instance.
 */
public class EventLog implements Iterable<Event> {
    /** Singleton instance of the EventLog */
    private static EventLog theLog;
    private Collection<Event> events;

    /**
     * Private constructor to enforce Singleton Design Pattern.
     * 
     * Effects: Initializes an empty collection of events.
     */
    private EventLog() {
        events = new ArrayList<>();
    }

    /**
     * Returns the single instance of EventLog, creating it if necessary.
     * 
     * Effects: Creates the EventLog instance if it does not already exist,
     *          then returns the singleton instance.
     *
     * @return the singleton instance of EventLog
     */
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }
        return theLog;
    }

    /**
     * Records an event in the event log.
     * 
     * Modifies: this
     * Effects: Adds the given event to the collection of logged events.
     *
     * @param e the event to be logged
     */
    public void logEvent(Event e) {
        events.add(e);
    }

    /**
     * Clears the event log and adds a "log cleared" event.
     * 
     * Modifies: this
     * Effects: Empties the collection of logged events and records
     *          a "log cleared" event.
     */
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    /**
     * Returns an iterator to traverse the logged events.
     *
     * Effects: Returns an iterator over the events in the log.
     *
     * @return an iterator for the event collection
     */
    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}
