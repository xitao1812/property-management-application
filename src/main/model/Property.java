package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a property having an address, city, price, owner name and sold status
public class Property implements Writable {

    private final String address;
    private final String city;
    private int price;
    private String ownerName;
    private boolean isSold;

    // REQUIRES: price > 0; address, city, and owner name have a non-zero length
    // MODIFIES: this
    // EFFECTS: construct a property with given address, city, price(in $), owner name
    public Property(String address, String city, int price, String ownerName, boolean isSold) {
        this.address = address;
        this.city = city;
        this.price = price;
        this.ownerName = ownerName;
        this.isSold = isSold;
    }

    // EFFECTS: return the address of the property
    public String getAddress() {
        return address;
    }

    // EFFECTS: return the city of the property
    public String getCity() {
        return city;
    }

    // EFFECTS: return the price of the property
    public int getPrice() {
        return price;
    }

    // EFFECTS: return the sold status of the property
    public boolean getSoldStatus() {
        return isSold;
    }

    // REQUIRES: price > 0
    // MODIFIES: this
    // EFFECTS: change the property's price to new price
    public void setPrice(int price) {
        this.price = price;
    }

    // EFFECTS: return the owner name of the property
    public String getOwnerName() {
        return ownerName;
    }


    // MODIFIES: this
    // EFFECTS: change the property's owner name to new owner name
    public void setOwnerName(String owner) {
        this.ownerName = owner;
        EventLog.getInstance().logEvent(new Event("Updated the owner name of Property: [" + getAddress()
                + ", " + getCity() + "] to " + owner));
    }

    // MODIFIES: this
    // EFFECTS: mark the property as sold by changing the property's isSold status to true
    public void setAsSold() {
        this.isSold = true;
        EventLog.getInstance().logEvent(new Event("Marked Property: [" + getAddress() + "," + getCity()
                + "] as sold"));
    }

    // EFFECTS: returns a property as JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("address", address);
        json.put("city", city);
        json.put("price", price);
        json.put("ownerName", ownerName);
        json.put("isSold", isSold);
        return json;
    }


}
