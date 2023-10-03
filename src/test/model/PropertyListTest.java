package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class PropertyListTest {
    PropertyList propertyList;
    ArrayList<Property> propertyListInCity;
    ArrayList<Property> propertyListInPriceRange;
    private Property property1;
    private Property property2;
    private Property property3;

    @BeforeEach
    void runBefore() {
        propertyList = new PropertyList();
        property1 = new Property("2686 18TH AVE E", "VANCOUVER",
                1400000, "Peter", false);
        property2 = new Property("6688 EDINBURGH STREET", "BURNABY",
                17000000, "Sally", false);
        property3 = new Property("4406 DAWSON STREET", "BURNABY",
                900000, "Linda", false);
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
    }

    @Test
    void testGetPropertyListInCity () {
        propertyList.addProperty(property1);
        propertyList.addProperty(property2);
        propertyListInCity = propertyList.getPropertyListInCity("BURNABY");
        assertFalse(propertyListInCity.contains(property1));
        assertTrue(propertyListInCity.contains(property2));
    }

    @Test
    void testGetPropertyListInPriceRange () {
        propertyList.addProperty(property1);
        propertyList.addProperty(property2);
        propertyList.addProperty(property3);
        propertyListInPriceRange = propertyList.getPropertyListInPriceRange(1300000, 1600000);
        assertEquals(1, propertyListInPriceRange.size());
        assertFalse(propertyListInPriceRange.contains(property2));
        assertFalse(propertyListInPriceRange.contains(property3));
        assertTrue(propertyListInPriceRange.contains(property1));
    }

}
