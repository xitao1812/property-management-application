package persistence;

import model.PropertyList;
import model.Property;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkProperty(String address, String city, int price, String ownerName, Property property) {
        assertEquals(address, property.getAddress());
        assertEquals(city, property.getCity());
        assertEquals(price, property.getPrice());
        assertEquals(ownerName, property.getOwnerName());
    }
}