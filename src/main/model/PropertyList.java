package model;

import java.util.ArrayList;

public class PropertyList {
    private ArrayList<Property> propertyList;
    private ArrayList<Property> propertyListInCity;
    private ArrayList<Property> propertyListInRange;

    // REQUIRES:
    // EFFECTS: Construct a property list
    public PropertyList() {
        this.propertyList = new ArrayList<>();
    }

    public ArrayList<Property> getPropertyList() {
        return propertyList;
    }

    // REQUIRES: propertyIndex >= 0 and propertyIndex < size of propertyList
    // MODIFIES: this
    // EFFECTS: add a property to the property list
    public void addProperty(Property property) {
        propertyList.add(property);
    }

    // REQUIRES: propertyIndex >= 0 and propertyIndex < size of propertyList
    // MODIFIES: this
    // EFFECTS: remove a property form the list
    public void removeProperty(int propertyIndex) {
        propertyList.remove(propertyIndex);
    }

    // REQUIRES: propertyIndex >= 0 and propertyIndex < size of propertyList
    // MODIFIES: this
    // EFFECTS: mark a property as sold
    public void markPropertyAsSold(int propertyIndex) {
        Property property = propertyList.get(propertyIndex);
        property.setAsSold();
    }

    // EFFECTS: return a list of property in the specified city
    public ArrayList<Property> getPropertyListInCity(String city) {
        propertyListInCity = new ArrayList<>();
        for (Property property : propertyList) {
            if (property.getCity().equalsIgnoreCase(city)) {
                propertyListInCity.add(property);
            }
        }
        return propertyListInCity;
    }

    // REQUIRES: minPrice >= 0, maxPrice >= 0, minPrice < maxPrice
    // EFFECTS: return a list of property that has a price more than the minPrice and less than
    // maxPrice
    public ArrayList<Property> getPropertyListInPriceRange(int minPrice, int maxPrice) {
        propertyListInRange = new ArrayList<>();
        for (Property property : propertyList) {
            if (property.getPrice() > minPrice && property.getPrice() < maxPrice) {
                propertyListInRange.add(property);
            }
        }
        return propertyListInRange;
    }


    public boolean contains(Property property) {
        return propertyList.contains(property);
    }

    public int size() {
        return propertyList.size();
    }

}
