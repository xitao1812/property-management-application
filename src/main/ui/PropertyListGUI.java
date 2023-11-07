package ui;

import model.Property;
import model.PropertyList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import persistence.JsonReader;
import persistence.JsonWriter;

// Represents a Property List GUI

public class PropertyListGUI extends JFrame implements ActionListener {
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/propertyList.json";
    private JLabel label;
    private JTextField field;
    private JTextField field2;
    private PropertyList propertyList = new PropertyList("Realtor' property list");


    public PropertyListGUI() {
        super("Property List");

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);

        // so that when the btn is clicked,
        // this.actionPerformed(ActionEvent e) will be called.
        // You could also set a different object, if you wanted
        // a different object to respond to the button click
        label = new JLabel("Property");
        JLabel labelOptions = new JLabel("Please select from the following options");
        labelOptions.setBounds(50, 10, 400, 30);


        add(labelOptions);
        this.setButton();



        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }




    public void setButton() {
        JButton btnAdd = new JButton("Add property");
        btnAdd.setActionCommand("addProperty");
        btnAdd.addActionListener(this); // Sets "this" object as an action listener for btn
        add(btnAdd);
        btnAdd.setBounds(50, 30, 150, 30);

        JButton btnView = new JButton("View List");
        btnView.setActionCommand("viewList");
        btnView.addActionListener(this); // Sets "this" object as an action listener for btn
        add(btnView);
        btnView.setBounds(200, 30, 150, 30);

        JButton btnSave = new JButton("Save");
        btnSave.setActionCommand("save");
        btnSave.addActionListener(this); // Sets "this" object as an action listener for btn

        add(btnSave);
        btnSave.setBounds(350, 30, 150, 30);

        JButton btnLoad = new JButton("Load");
        btnLoad.setActionCommand("load");
        btnLoad.addActionListener(this); // Sets "this" object as an action listener for btn

        add(btnLoad);
        btnLoad.setBounds(500, 30, 150, 30);
    }

    //This is the method that is called when the JButton btn is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("addProperty")) {
            new AddProperty(new ViewList(propertyList), propertyList);
        } else if (e.getActionCommand().equals("viewList")) {
            new ViewList(propertyList);
        } else if (e.getActionCommand().equals("save")) {
            savePropertyList();
            //PropertyListApp.savePropertyList();
        } else if (e.getActionCommand().equals("load")) {
            loadPropertyList();
        }
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

    public static void main(String[] args) {
        new PropertyListGUI();
    }
}