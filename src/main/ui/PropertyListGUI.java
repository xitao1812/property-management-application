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

//Code influenced by stackoverflow example https://stackoverflow.com/questions/6578205/
//                                         swing-jlabel-text-change-on-the-running-application
// Represents a Property List GUI
public class PropertyListGUI extends JFrame implements ActionListener {
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/propertyList.json";

    private JLabel displayField1;
    private ImageIcon image1;
    private JLabel displayField2;
    private ImageIcon image2;
    private PropertyList propertyList = new PropertyList("Realtor' property list");

    // EFFECTS: constructs the property list and runs application
    public PropertyListGUI() {
        super("Property List");

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(900, 600));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);

        JLabel label = new JLabel("Property");
        JLabel labelOptions = new JLabel("Please select from the following options");
        labelOptions.setBounds(50, 10, 400, 30);

        add(labelOptions);
        this.setButton();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }



    // EFFECTS: constructs the buttons in the application
    public void setButton() {
        JButton btnAdd = new JButton("Add property");
        btnAdd.setActionCommand("addProperty");
        btnAdd.addActionListener(this); // Sets "this" object as an action listener for btn
        add(btnAdd);
        btnAdd.setBounds(50, 50, 150, 30);

        JButton btnView = new JButton("View List");
        btnView.setActionCommand("viewList");
        btnView.addActionListener(this); // Sets "this" object as an action listener for btn
        add(btnView);
        btnView.setBounds(200, 50, 150, 30);

        JButton btnSave = new JButton("Save");
        btnSave.setActionCommand("save");
        btnSave.addActionListener(this); // Sets "this" object as an action listener for btn

        add(btnSave);
        btnSave.setBounds(350, 50, 150, 30);

        JButton btnLoad = new JButton("Load");
        btnLoad.setActionCommand("load");
        btnLoad.addActionListener(this); // Sets "this" object as an action listener for btn

        add(btnLoad);
        btnLoad.setBounds(500, 50, 150, 30);
    }

    // EFFECTS: constructs the saved picture
    public void displaySavePicture() {
        if (displayField2 != null) {
            displayField2.setVisible(false);
        }
        image1 = new ImageIcon(getClass().getResource("saved.png"));
        displayField1 = new JLabel(image1);
        this.add(displayField1);
        displayField1.setBounds(50, 50, 50, 30);
        displayField1.setSize(800, 480);
        displayField1.setVisible(true);

    }

    // EFFECTS: constructs the loaded picture
    public void displayLoadPicture() {
        if (displayField1 != null) {
            displayField1.setVisible(false);
        }
        image2 = new ImageIcon(getClass().getResource("loaded.png"));
        displayField2 = new JLabel(image2);
        this.add(displayField2);
        displayField2.setBounds(50, 50, 50, 30);
        displayField2.setSize(800, 480);
        displayField2.setVisible(true);

    }

    // EFFECTS: calls corresponding methods when the JButton btn is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("addProperty")) {
            new AddProperty(new ViewList(propertyList), propertyList);
        } else if (e.getActionCommand().equals("viewList")) {
            new ViewList(propertyList);
        } else if (e.getActionCommand().equals("save")) {
            savePropertyList();
            displaySavePicture();
        } else if (e.getActionCommand().equals("load")) {
            loadPropertyList();
            displayLoadPicture();
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