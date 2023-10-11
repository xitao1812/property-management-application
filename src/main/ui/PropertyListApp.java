package ui;

import model.Property;
import model.PropertyList;

import java.util.List;
import java.util.Scanner;

// Property list application
public class PropertyListApp {
    private final PropertyList propertyList;
    private Scanner input;

    public PropertyListApp()  {
        propertyList = new PropertyList();
        runPropertyList();
    }

    // Referenced the Teller App runTeller code
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
        } else {
            System.out.println("Selection not valid");
        }
    }

    private void init() {
        input = new Scanner(System.in);
    }

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
        System.out.println("\tq -> quit");
    }

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

        propertyList.addProperty(new Property(address, city, price, ownerName));

    }

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












