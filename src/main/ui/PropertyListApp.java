package ui;

import model.Property;
import model.PropertyList;

import java.util.List;
import java.util.Scanner;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;

//Code influenced by the teller app https://github.students.cs.ubc.ca/CPSC210/TellerApp
// Represents a Property List application
public class PropertyListApp {
    private static final String JSON_STORE = "./data/propertyList.json";
    private PropertyList propertyList;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs the property list and runs application
    public PropertyListApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        propertyList = new PropertyList("Realtor's property list");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runPropertyList();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runPropertyList() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye!");
    }


    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("1")) {
            createProperty();
        } else if (command.equals("2")) {
            markPropertyAsSold();
        } else if (command.equals("3")) {
            removeProperty();
        } else if (command.equals("4")) {
            viewPropertyList();
        } else if (command.equals("5")) {
            viewPropertyListInCity();
        } else if (command.equals("6")) {
            viewPropertyListInPriceRange();
        } else if (command.equals("7")) {
            viewPropertyListCityAndPrice();
        } else if (command.equals("s")) {
            savePropertyList();
        } else if (command.equals("l")) {
            loadPropertyList();
        } else {
            System.out.println("Selection not valid");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes property list
    private void init() {
        input = new Scanner(System.in);
    }


    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nProperty List Application");
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> add a new property");
        System.out.println("\t2 -> mark a property as sold");
        System.out.println("\t3 -> remove a property");
        System.out.println("\t4 -> view all properties in the list");
        System.out.println("\t5 -> get a list of properties in the specified city");
        System.out.println("\t6 -> get a list of properties in the specified price range");
        System.out.println("\t7 -> get a list of properties in the specified city and price range");
        System.out.println("\ts -> save property list to file");
        System.out.println("\tl -> load property list from file");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: saves the property list to file
    private void savePropertyList() {
        try {
            jsonWriter.open();
            jsonWriter.write(propertyList);
            jsonWriter.close();
            System.out.println("Saved " + propertyList.getListName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the property list from file
    private void loadPropertyList() {
        try {
            propertyList = jsonReader.read();
            System.out.println("Loaded " + propertyList.getListName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: prompt user for property address, city, price, ownerName, and add the property to the property list
    private void createProperty() {
        System.out.print("Enter property address:");
        input.nextLine();
        String address = input.nextLine();
        System.out.print("Enter property city:");
        String city = input.next();
        System.out.print("Enter property's owner name:");
        String ownerName = input.next();
        System.out.print("Enter property price : $");
        int price = input.nextInt();

        propertyList.addProperty(new Property(address, city, price, ownerName, false));

    }

    // REQUIRES: propertyIndex >= 0 and propertyIndex < size of propertyList
    // MODIFIES: this
    // EFFECTS: mark the property with the given propertyIndex as sold and update the owner name to new owner
    private void markPropertyAsSold() {
        System.out.print("Which property would you like to mark as Sold? Please enter Property Index:");
        int propertyIndex = input.nextInt();
        if (propertyIndex < propertyList.size()) {
            propertyList.markPropertyAsSold(propertyIndex);
            System.out.print("Marked as Sold successfully! ");
            System.out.println("What is the new owner name:");
            input.nextLine();
            String newOwnerName = input.nextLine();
            Property soldProperty = propertyList.get(propertyIndex);
            soldProperty.setOwnerName(newOwnerName);
        } else {
            System.out.print("Index not valid");
        }

    }

    // REQUIRES: propertyIndex >= 0 and propertyIndex < size of propertyList
    // MODIFIES: this
    // EFFECTS: remove the property with the given propertyIndex form the list
    private void removeProperty() {
        System.out.print("Which property would you like to remove? Please enter Property Index:");
        int propertyIndex = input.nextInt();
        if (propertyIndex < propertyList.size()) {
            propertyList.removeProperty(propertyIndex);
            System.out.print("Property has been removed from the list");
        } else {
            System.out.print("Index not valid");
        }

    }

    // EFFECTS: prints the entire property list
    private void viewPropertyList() {
        int propertyIndex = 0;
        List<Property> propertyList = this.propertyList.getPropertyList();
        System.out.println("Property List");
        System.out.println("--------------------");
        for (Property property : propertyList) {
            System.out.println("Property Index " + propertyIndex);
            printPropertyInfo(property);
            System.out.println("--------------------");
            propertyIndex++;
        }
    }

    // EFFECTS: print a list of property in the given city
    private void viewPropertyListInCity() {
        System.out.print("Which city you want to view a list of properties in:");
        String city = input.next();
        List<Property> propertyList = this.propertyList.getPropertyListInCity(city);
        System.out.println("Property List in " + city);
        System.out.println("--------------------");
        for (Property property : propertyList) {
            printPropertyInfo(property);
            System.out.println("--------------------");
        }
    }


    // REQUIRES: minPrice >= 0, maxPrice >= 0, minPrice < maxPrice
    // EFFECTS: print a list of property in the given price range (has a price more than the minPrice
    // and less than maxPrice)
    private void viewPropertyListInPriceRange() {
        System.out.print("What is the minimum price you want to view a list of properties in");
        int minPrice = input.nextInt();
        System.out.print("What is the maximum price you want to view a list of properties in");
        int maxPrice = input.nextInt();
        List<Property> propertyList = this.propertyList.getPropertyListInPriceRange(minPrice, maxPrice);
        System.out.println("Property List in Price Range [" + minPrice + "," + maxPrice + "]");
        System.out.println("--------------------");
        for (Property property : propertyList) {
            printPropertyInfo(property);
            System.out.println("--------------------");
        }
    }

    // REQUIRES: minPrice >= 0, maxPrice >= 0, minPrice < maxPrice
    // EFFECTS: print a list of property in the given city and price range (has a price more than the minPrice
    // and less than maxPrice)
    private void viewPropertyListCityAndPrice() {
        System.out.print("Which city you want to view a list of properties in:");
        String city = input.next();
        System.out.print("What is the minimum price you want to view a list of properties in");
        int minPrice = input.nextInt();
        System.out.print("What is the maximum price you want to view a list of properties in");
        int maxPrice = input.nextInt();
        List<Property> propertyList = this.propertyList.getPropertyListCityAndPrice(city, minPrice, maxPrice);
        System.out.println("Property List in " + city + " & Price Range [" + minPrice + "," + maxPrice + "]");
        System.out.println("--------------------");
        for (Property property : propertyList) {
            printPropertyInfo(property);
            System.out.println("--------------------");
        }
    }


    // EFFECTS: prints a property's address, city, price, owner name, and sold status
    private void printPropertyInfo(Property property) {
        String address = property.getAddress();
        String city = property.getCity();
        int price = property.getPrice();
        String owner = property.getOwnerName();
        Boolean isSold = property.getSoldStatus();

        System.out.println("Address:" + address);
        System.out.println("City:" + city);
        System.out.println("Price: $" + price);
        System.out.println("Owner Name:" + owner);
        if (isSold) {
            System.out.println("Sold Status:" + "Sold");
        } else {
            System.out.println("Sold Status:" + "Available for Sale");
        }

    }

}











