package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {
    private Event event;
    private String description;

    @BeforeEach
    void setup() {
        description = "Test event";
        event = new Event(description);
    }

    @Test
    void testConstructor() {
        assertEquals(description, event.getDescription());
        assertNotNull(event.getDate()); // Ensure the date is set
    }

    @Test
    void testGetDescription() {
        assertEquals("Test event", event.getDescription());
    }

    @Test
    void testGetDate() {
        Date now = new Date();
        assertTrue(event.getDate().compareTo(now) <= 0); // Ensure the event date is before or equal to 'now'
    }

    @Test
    void testHashCodeConsistency() {
        int hash1 = event.hashCode();
        int hash2 = event.hashCode();
        assertEquals(hash1, hash2); // Same object should produce consistent hash codes
    }

    @Test
    void testToString() {
        String toString = event.toString();
        assertTrue(toString.contains("Test event"));
        assertTrue(toString.contains(event.getDate().toString()));
    }
    
    @Test
    void testEqualsDifferentObject() {
        Event differentEvent = new Event("Different description");
        assertFalse(event.equals(differentEvent)); // Different descriptions mean not equal
    }
    
    @Test
    void testEqualsNull() {
        assertFalse(event.equals(null)); // Ensure it is not equal to null
    }

    @Test
    void testHashCode() {
        int hash1 = event.hashCode();
        int hash2 = event.hashCode();
        assertEquals(hash1, hash2); // Hash code must remain consistent
    }
    
    @Test
    void testHashCodeDifferentEvents() {
        Event differentEvent = new Event("Different description");
        assertNotEquals(event.hashCode(), differentEvent.hashCode()); // Different events have different hash codes
    }
    
    
    @Test
    void testEqualsSameFields() {
        // Create two events with the same description and manually set the same date
        Date sameDate = new Date();
        Event event1 = new Event("Test Event");
        Event event2 = new Event("Test Event");
        
        // Simulate same timestamp
        event1.getDate().setTime(sameDate.getTime());
        event2.getDate().setTime(sameDate.getTime());
        
        assertTrue(event1.equals(event2)); // Events with the same description and timestamp should be equal
    }

    @Test
    void testEqualsSameObject() {
        assertTrue(event.equals(event)); // An object must always be equal to itself
    }

    @Test
    void testEqualsDifferentClass() {
        assertFalse(event.equals("Not an Event")); // An event must not be equal to an object of a different class
    }
    
    
    @Test
    void testEqualsDifferentDescriptions() {
        // Create two events with different descriptions
        Event event1 = new Event("Description 1");
        Event event2 = new Event("Description 2");
    
        assertFalse(event1.equals(event2)); // Events with different descriptions are not equal
    }

    @Test
    void testEqualsNullAndClassCheck() {
        // Ensure null comparison returns false
        assertFalse(event.equals(null));
    
        // Ensure different class comparison returns false
        assertFalse(event.equals("This is not an Event"));
    }   
}
