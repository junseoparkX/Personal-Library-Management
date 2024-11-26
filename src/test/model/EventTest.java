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
    void testEqualsDifferentTimestamp() {
        Event laterEvent = new Event("Test event");
        assertNotEquals(event, laterEvent); // Events with the same description but different timestamps should not be equal        
    }

    @Test
    void testGetDate() {
        assertNotNull(event.getDate()); // Ensure date is not null
        Date now = new Date();
        assertTrue(event.getDate().compareTo(now) <= 0); // Ensure the date is before or equal to 'now'
    }
    @Test
    void testGetDescription() {
        assertEquals(description, event.getDescription()); // Ensure description matches
    }

    @Test
    void testEqualsSameEvent() {
        assertTrue(event.equals(event)); // An object must be equal to itself
    }
    
    @Test
    void testEqualsSameFields() {
        Event sameEvent = new Event(description);
        assertNotEquals(event, sameEvent); // Same description but different timestamps are not equal
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
    void testEqualsDifferentClass() {
        assertFalse(event.equals("Not an Event")); // Ensure not equal to a different class
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
    void testToString() {
        String result = event.toString();
        assertTrue(result.contains(description)); // Ensure the description is in the string
        assertTrue(result.contains(event.getDate().toString())); // Ensure the date is in the string
    }
}
