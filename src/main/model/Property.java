package model;

// Represents a property having an address, city, price, owner name and sold status
public class Property {

    private final String address;
    private final String city;
    private int price;
    private String ownerName;
    private boolean isSold;

    // EFFECTS: construct a property with given address, city, price, owner and status
    public Property(String address, String city, int price, String ownerName, boolean isSold) {
        this.address = address;
        this.city = city;
        this.price = price;
        this.ownerName = ownerName;
        this.isSold = isSold;
    }

    public String getAddress() {
        return address;
    }


    public String getCity() {
        return city;
    }

    public double getPrice() {
        return price;
    }

    public boolean getSoldStatus() {
        return isSold;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String owner) {
        this.ownerName = owner;
    }

    public void setAsSold() {
        this.isSold = true;
    }


}
