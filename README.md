# My Personal Project

## Introduction


- **What will the application do?**

    It is a real estate management application to keep track of the properties for sale.
 
- **Who will use it?**

    The user is the real estate agent to help seller and buyer in real estate transactions.

- **Why is this project of interest to you?**
    
    The real estate market is an important market in Vancouver. I am interested in helping seller agent
    to keep track of the properties for sale and provide better experience for their clients.

## User Stories

- *As a user, I want to be able to add a property to my list of property.*
- *As a user, I want to be able to mark a property as sold.*
- *As a user, I want to be able to remove a property.*
- *As a user, I want to be able to view the list of property.*
- *As a user, I want to be able to filter the list of property and view a list of properties
  in the specific city and price range.*
- *As a user, I want to be able to save the property list.*
- *As a user, I want to be able to reload the property list.*

## Instructions for Grader

- You can generate the first required action related to adding properties to property list by clicking 
the "Add property" button on the main menu page or the ViewList page
- You can generate the second required action related to changing the sold status and new owner name 
by clicking the "Mark a property as sold" button on the ViewList page
- You can locate my visual component by clicking the "load" button and "save" button 
- You can save the state of my application by clicking the "save" button
- You can reload the state of my application by clicking the "load" button



## Phase 4: Task 2
- If a property at 3361 Euclid Ave, Vancouver is added to the property list, 
 the event is "Property: [3361 Euclid Ave, Vancouver] added to the property list"
- If a property is removed from the list, the event is "A property removed from the property list"
- If a property at 3361 Euclid Ave, Vancouver is mark as sold and updated with new owner name to Cathy, the event is 
"Marked Property: [3361 Euclid Ave,Vancouver] as sold" and "Updated the owner name of 
Property: [3361 Euclid Ave, Vancouver] to Cathy"


## Phase 4: Task 3

- In ViewList's addPropertyToTable method, it uses propertyList.getPropertyList() to get a list of property 
in the for each loop. 
- This can be refactored that propertyList implements Iterable<Property>. So that propertyList is iterable and can
  be used directly in the for each loop.