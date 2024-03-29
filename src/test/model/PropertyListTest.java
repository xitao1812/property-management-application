package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Test methods for property list
public class PropertyListTest {
    PropertyList propertyList;
    List<Property> propertyListInCity;
    List<Property> propertyListInPriceRange;
    List<Property> propertyListInCityAndRange;
    private Property property1;
    private Property property2;
    private Property property3;
    private Property property4;
    private Property property5;
    private Property property6;

    @BeforeEach
    void runBefore() {
        propertyList = new PropertyList("Realtor' property list");
        property1 = new Property("3888 FOSTER STREET", "RICHMOND",
                1400000, "Peter", false);
        property2 = new Property("6688 EDINBURGH STREET", "BURNABY",
                17000000, "Sally", false);
        property3 = new Property("4406 DAWSON STREET", "BURNABY",
                1000000, "Linda", false);
        property4 = new Property("6038 FRASER STREET", "VANCOUVER",
                1300000, "Jerry", false);
        property5 = new Property("6038 FLEMING STREET", "BURNABY",
                1200000, "David", false);
        property6 = new Property("3038 MANITOBA STREET", "BURNABY",
                900000, "Jason", false);
    }

    @Test
    void testAddProperty() {
        assertEquals(0, propertyList.size());
        propertyList.addProperty(property1);
        assertEquals(1, propertyList.size());
        assertTrue(propertyList.contains(property1));
    }


    @Test
    void testRemoveProperty () {
        propertyList.addProperty(property1);
        propertyList.addProperty(property2);
        assertEquals(2, propertyList.size());
        propertyList.removeProperty(0);
        assertEquals(1, propertyList.size());
        assertFalse(propertyList.contains(property1));
        assertTrue(propertyList.contains(property2));
    }

    @Test
    void testMarkPropertyAsSold () {
        propertyList.addProperty(property1);
        propertyList.addProperty(property2);
        assertFalse(property1.getSoldStatus());
        propertyList.markPropertyAsSold(0);
        assertTrue(property1.getSoldStatus());
        property1.setOwnerName("Sophie");
        Property soldProperty = propertyList.get(0);
        assertEquals("Sophie", soldProperty.getOwnerName());
        propertyList.markPropertyAsSold(1);
        assertTrue(property2.getSoldStatus());
        property2.setOwnerName("Tim");
        Property soldProperty2 = propertyList.get(1);
        assertEquals("Tim", soldProperty2.getOwnerName());

    }

    @Test
    void testGetPropertyList () {
        propertyList.addProperty(property1);
        propertyList.addProperty(property2);
        propertyList.addProperty(property3);
        propertyListInCity = propertyList.getPropertyList();
        assertEquals(3, propertyList.size());
        assertTrue(propertyList.contains(property1));
        assertTrue(propertyList.contains(property2));
        assertTrue(propertyList.contains(property3));

    }

    @Test
    void testGetPropertyListInCity () {
        propertyList.addProperty(property1);
        propertyList.addProperty(property2);
        propertyList.addProperty(property3);
        propertyListInCity = propertyList.getPropertyListInCity("BURNABY");
        assertFalse(propertyListInCity.contains(property1));
        assertTrue(propertyListInCity.contains(property2));
        assertTrue(propertyListInCity.contains(property3));

        propertyList.addProperty(property5);
        propertyListInCity = propertyList.getPropertyListInCity("BURNABY");
        assertTrue(propertyListInCity.contains(property2));
        assertTrue(propertyListInCity.contains(property3));
        assertTrue(propertyListInCity.contains(property5));
        assertFalse(propertyListInCity.contains(property1));
    }

    @Test
    void testGetPropertyListInPriceRange () {
        propertyList.addProperty(property1);
        propertyList.addProperty(property2);
        propertyList.addProperty(property3);
        propertyListInPriceRange = propertyList.getPropertyListInPriceRange(1300000, 1600000);
        assertEquals(1, propertyListInPriceRange.size());
        assertTrue(propertyListInPriceRange.contains(property1));
        assertFalse(propertyListInPriceRange.contains(property2));
        assertFalse(propertyListInPriceRange.contains(property3));
        assertFalse(propertyListInPriceRange.contains(property4));

        propertyList.addProperty(property4);
        propertyListInPriceRange = propertyList.getPropertyListInPriceRange(1300000, 1600000);
        assertEquals(2, propertyListInPriceRange.size());
        assertTrue(propertyListInPriceRange.contains(property1));
        assertTrue(propertyListInPriceRange.contains(property4));
        assertFalse(propertyListInPriceRange.contains(property2));
        assertFalse(propertyListInPriceRange.contains(property3));

        property2.setPrice(1600000);
        propertyListInPriceRange = propertyList.getPropertyListInPriceRange(1300000, 1600000);
        assertEquals(3, propertyListInPriceRange.size());
        assertTrue(propertyListInPriceRange.contains(property1));
        assertTrue(propertyListInPriceRange.contains(property4));
        assertTrue(propertyListInPriceRange.contains(property2));
        assertFalse(propertyListInPriceRange.contains(property3));
    }


    @Test
    void testGetPropertyListInCityAndPrice () {
        propertyList.addProperty(property1);
        propertyList.addProperty(property2);
        propertyList.addProperty(property3);
        propertyListInCityAndRange = propertyList.getPropertyListCityAndPrice("BURNABY",
                1000000, 1400000);
        assertEquals(1, propertyListInCityAndRange.size());
        assertFalse(propertyListInCityAndRange.contains(property1));
        assertFalse(propertyListInCityAndRange.contains(property2));
        assertTrue(propertyListInCityAndRange.contains(property3));

        propertyList.addProperty(property5);
        propertyList.addProperty(property6);
        propertyListInCityAndRange = propertyList.getPropertyListCityAndPrice("BURNABY",
                1000000, 1400000);
        assertEquals(2, propertyListInCityAndRange.size());
        assertFalse(propertyListInCityAndRange.contains(property1));
        assertFalse(propertyListInCityAndRange.contains(property2));
        assertTrue(propertyListInCityAndRange.contains(property3));
        assertTrue(propertyListInCityAndRange.contains(property5));
    }

}
