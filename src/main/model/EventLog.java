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
     */
    private EventLog() {
        events = new ArrayList<>();
    }

    /**
     * Returns the single instance of EventLog, creating it if necessary.
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
     * @param e the event to be logged
     */
    public void logEvent(Event e) {
        events.add(e);
    }

    /**
     * Clears the event log and adds a "log cleared" event.
     */
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    /**
     * Returns an iterator to traverse the logged events.
     * @return an iterator for the event collection
     */
    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}


