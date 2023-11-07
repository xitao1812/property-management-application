package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Test methods for property
public class PropertyTest {
    private Property property1;
    private Property property2;

    @BeforeEach
    void runBefore() {
        property1 = new Property("2686 18TH AVE E", "VANCOUVER",
                1400000, "Peter", false);
        property2 = new Property("6688 EDINBURGH STREET", "BURNABY",
                1700000, "Sally", false);
    }

    @Test
    void testConstructor() {
        assertEquals("2686 18TH AVE E", property1.getAddress());
        assertEquals("VANCOUVER", property1.getCity());
        assertEquals(1400000, property1.getPrice());
        assertEquals("Peter", property1.getOwnerName());
        assertFalse(property1.getSoldStatus());

        assertEquals("6688 EDINBURGH STREET", property2.getAddress());
        assertEquals("BURNABY", property2.getCity());
        assertEquals(1700000, property2.getPrice());
        assertEquals("Sally", property2.getOwnerName());
        assertFalse(property2.getSoldStatus());
    }

}
