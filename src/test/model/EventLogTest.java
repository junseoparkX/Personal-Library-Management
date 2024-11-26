package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class EventLogTest {
    private EventLog eventLog;

    @BeforeEach
    void setup() {
        eventLog = EventLog.getInstance(); // Get the Singleton instance
        eventLog.clear(); // Clear the log before each test
    }

    @Test
    void testSingletonInstance() {
        EventLog anotherInstance = EventLog.getInstance();
        assertEquals(eventLog, anotherInstance); // Ensure only one instance exists (Singleton)
    }

    @Test
    void testLogEvent() {
        // Clear the EventLog to ensure no residual events from previous tests
        eventLog.clear();
    
        // Create two events
        Event event1 = new Event("First Event");
        Event event2 = new Event("Second Event");
    
        // Log the events
        eventLog.logEvent(event1);
        eventLog.logEvent(event2);
    
        // Get an iterator and verify the order of logged events
        Iterator<Event> iterator = eventLog.iterator();
    
        // Skip the "Event log cleared." event
        Event clearedEvent = iterator.next();
        assertEquals("Event log cleared.", clearedEvent.getDescription());
    
        // Verify the first logged event
        assertTrue(iterator.hasNext());
        assertEquals(event1, iterator.next());
    
        // Verify the second logged event
        assertTrue(iterator.hasNext());
        assertEquals(event2, iterator.next());
    
        // Ensure no more events are present
        assertFalse(iterator.hasNext());
    }
    

    @Test
    void testClearLog() {
        eventLog.logEvent(new Event("Test Event"));
        assertTrue(eventLog.iterator().hasNext()); // Ensure the log is not empty

        eventLog.clear();
        Iterator<Event> iterator = eventLog.iterator();
        assertTrue(iterator.hasNext()); // The "Event log cleared." event should be present
        assertEquals("Event log cleared.", iterator.next().getDescription());
    }

    @Test
    void testLogEventAfterClear() {
        Event event1 = new Event("Event before clear");
        eventLog.logEvent(event1);

        eventLog.clear(); // Clear the log
        Event event2 = new Event("Event after clear");
        eventLog.logEvent(event2);

        Iterator<Event> iterator = eventLog.iterator();
        assertTrue(iterator.hasNext());
        Event clearedEvent = iterator.next();
        assertEquals("Event log cleared.", clearedEvent.getDescription()); // "Cleared" event is logged
        assertEquals(event2, iterator.next()); // Event after clear is logged
    }

    @Test
    void testEqualsSameObject() {
        Event event = new Event("Test Event");
        assertTrue(event.equals(event)); // An object must always be equal to itself
    }
 

    @Test
    void testEqualsDifferentDescriptions() {
        Event event1 = new Event("Event 1");
        Event event2 = new Event("Event 2");
        assertFalse(event1.equals(event2)); // Events with different descriptions are not equal
    }
    
    @Test
    void testEqualsNull() {
        Event event = new Event("Test Event");
        assertFalse(event.equals(null)); // An event must not be equal to null
    }
    
    @Test
    void testEqualsDifferentClass() {
        Event event = new Event("Test Event");
        assertFalse(event.equals("Not an Event")); // Events must not be equal to objects of a different class
    }


    @Test
    void testEqualsDateLoggedMatch() {
        Event event1 = new Event("Test Event");
        Event event2 = new Event("Test Event");
    
        // Ensure both events have the same dateLogged by copying the date from event1
        event2.getDate().setTime(event1.getDate().getTime());
    
        assertTrue(event1.equals(event2));
    }
    
    @Test
    void testEqualsDateLoggedMismatch() {
        Event event1 = new Event("Test Event");
    
        // Introduce a delay to ensure different timestamps
        try {
            Thread.sleep(10); // 10 ms delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    
        Event event2 = new Event("Test Event");
    
        assertFalse(event1.equals(event2));
    }
    
}
