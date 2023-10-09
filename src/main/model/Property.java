package model;

// Represents a property having an address, city, price, owner name and sold status
public class Property {

    private final String address;
    private final String city;
    private int price;
    private String ownerName;
    private boolean isSold;

    // REQUIRES: price > 0; address, city, and owner name have a non-zero length
    // MODIFIES: this
    // EFFECTS: construct a property with given address, city, price(in $), owner name
    public Property(String address, String city, int price, String ownerName) {
        this.address = address;
        this.city = city;
        this.price = price;
        this.ownerName = ownerName;
        this.isSold = false;
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
    public double getPrice() {
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
    }

    // MODIFIES: this
    // EFFECTS: mark the property as sold by changing the property's isSold status to true
    public void setAsSold() {
        this.isSold = true;
    }


}
