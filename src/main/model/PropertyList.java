package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a property list
public class PropertyList implements Writable {
    private String listName;
    private List<Property> propertyList;
    private List<Property> propertyListInCity;
    private List<Property> propertyListInRange;
    private List<Property> propertyListCityAndPriceRange;


    // EFFECTS: Construct a property list with a list name and empty list of property
    public PropertyList(String listName) {
        this.listName = listName;
        this.propertyList = new ArrayList<>();
    }

    // EFFECTS: return the property list's name
    public String getListName() {
        return listName;
    }

    // EFFECTS: return the property list
    public List<Property> getPropertyList() {
        return propertyList;
    }

    // MODIFIES: this
    // EFFECTS: add a property to the property list, and add this Event to EventLog
    public void addProperty(Property property) {
        propertyList.add(property);
        EventLog.getInstance().logEvent(new Event("Property: [" + property.getAddress() + ", "
                + property.getCity()
                + "] added to the property list"));

    }

    // REQUIRES: propertyIndex >= 0 and propertyIndex < size of propertyList
    // MODIFIES: this
    // EFFECTS: remove a property form the list, and add this Event to EventLog
    public void removeProperty(int propertyIndex) {
        propertyList.remove(propertyIndex);
        EventLog.getInstance().logEvent(new Event("A property removed from the property list"));
    }

    // REQUIRES: propertyIndex >= 0 and propertyIndex < size of propertyList
    // MODIFIES: this
    // EFFECTS: mark a property as sold
    public void markPropertyAsSold(int propertyIndex) {
        Property property = propertyList.get(propertyIndex);
        property.setAsSold();

    }

    // EFFECTS: return a list of property in the specified city
    public List<Property> getPropertyListInCity(String city) {
        propertyListInCity = new ArrayList<>();
        for (Property property : propertyList) {
            if (property.getCity().equalsIgnoreCase(city)) {
                propertyListInCity.add(property);
            }
        }
        return propertyListInCity;
    }

    // REQUIRES: minPrice >= 0, maxPrice >= 0, minPrice < maxPrice
    // EFFECTS: return a list of property in the specified price range (has a price more than the minPrice
    // and less than maxPrice)
    public List<Property> getPropertyListInPriceRange(int minPrice, int maxPrice) {
        propertyListInRange = new ArrayList<>();
        for (Property property : propertyList) {
            if (property.getPrice() >= minPrice && property.getPrice() <= maxPrice) {
                propertyListInRange.add(property);
            }
        }
        return propertyListInRange;
    }

    // REQUIRES: minPrice >= 0, maxPrice >= 0, minPrice < maxPrice
    // EFFECTS: return a list of property in the specified city and price range (has a price more than the minPrice
    // and less than maxPrice)
    public List<Property> getPropertyListCityAndPrice(String city, int minPrice, int maxPrice) {
        propertyListCityAndPriceRange = new ArrayList<>();
        for (Property property : propertyList) {
            if (property.getPrice() >= minPrice && property.getPrice() <= maxPrice
                    && property.getCity().equalsIgnoreCase(city)) {
                propertyListCityAndPriceRange.add(property);
            }
        }
        return propertyListCityAndPriceRange;

    }

    // EFFECTS: return true if the property list contains the property, otherwise return false
    public boolean contains(Property property) {
        return propertyList.contains(property);
    }

    // EFFECTS: return the number of property in the property list
    public int size() {
        return propertyList.size();
    }

    // REQUIRES: propertyIndex >= 0 and propertyIndex < size of propertyList
    // EFFECTS: return the property in the property list based on propertyIndex
    public Property get(int propertyIndex) {
        return propertyList.get(propertyIndex);
    }


    // EFFECTS:  returns a property list as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", listName);
        json.put("properties", listToJson());
        return json;
    }

    // EFFECTS: returns properties in this property list as a JSON array
    private JSONArray listToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Property p : propertyList) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }


}
