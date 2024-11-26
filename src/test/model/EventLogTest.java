package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertSame(eventLog, anotherInstance); // Ensure only one instance exists (Singleton)
    }

    @Test
    void testLogEvent() {
        Event event1 = new Event("First Event");
        Event event2 = new Event("Second Event");

        eventLog.logEvent(event1);
        eventLog.logEvent(event2);

        Iterator<Event> iterator = eventLog.iterator();
        assertEquals(event1, iterator.next()); // First event is logged
        assertEquals(event2, iterator.next()); // Second event is logged
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
    void testIterator() {
        Event event1 = new Event("Event 1");
        Event event2 = new Event("Event 2");
        eventLog.logEvent(event1);
        eventLog.logEvent(event2);

        Iterator<Event> iterator = eventLog.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(event1, iterator.next()); // First event
        assertEquals(event2, iterator.next()); // Second event
        assertFalse(iterator.hasNext()); // No more events
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
}
