package model;


public class Property {

    private String address;
    private String city;
    private int price;
    private String ownerName;
    private static boolean isSold;

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

    public String getOwnerName() {
        return ownerName;
    }

    public boolean getSoldStatus() {
        return Property.isSold;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setOwnerName(String owner) {
        this.ownerName = owner;
    }

    public void setAsSold() {
        this.isSold = true;
    }


}
