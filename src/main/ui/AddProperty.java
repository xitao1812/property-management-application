package ui;

import model.Property;
import model.PropertyList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Code influenced by stackoverflow example https://stackoverflow.com/questions/6578205/
//                                         swing-jlabel-text-change-on-the-running-application
// Represents an Add Property List GUI
public class AddProperty extends JFrame implements ActionListener {
    private PropertyList propertyList;
    private JLabel labelAddress;
    private JLabel labelCity;
    private JLabel labelOwner;
    private JLabel labelPrice;
    private JTextField fieldAddress;
    private JTextField fieldCity;
    private JTextField fieldOwner;
    private JTextField fieldPrice;
    private ViewList viewList;


    // EFFECTS: constructs the add property labels and buttons
    public AddProperty(ViewList viewList, PropertyList propertyList) {
        super("Add a property");
        this.propertyList = propertyList;
        this.viewList = viewList;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 300));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);

        JLabel labelInfo = new JLabel("Please enter property information");
        labelInfo.setBounds(50, 10, 400, 30);


        add(labelInfo);
        this.setLabel();
        this.setField();
        this.setButton();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: constructs the labels for address, city, price, owner name, and price
    public void setLabel() {
        labelAddress = new JLabel("Address:");
        add(labelAddress);
        labelAddress.setBounds(50, 30, 200, 30);

        labelCity = new JLabel("City:");
        add(labelCity);
        labelCity.setBounds(50, 70, 200, 30);

        labelOwner = new JLabel("Owner name:");
        add(labelOwner);
        labelOwner.setBounds(50, 110, 200, 30);

        labelPrice = new JLabel("Price:");
        add(labelPrice);
        labelPrice.setBounds(50, 150, 200, 30);

    }

    // EFFECTS: constructs the input fields
    public void setField() {
        fieldAddress = new JTextField(30);
        fieldAddress.setBounds(50, 50, 200, 30);
        add(fieldAddress);

        fieldCity = new JTextField(30);
        fieldCity.setBounds(50, 90, 200, 30);
        add(fieldCity);

        fieldOwner = new JTextField(30);
        fieldOwner.setBounds(50, 130, 200, 30);
        add(fieldOwner);

        fieldPrice = new JTextField(30);
        fieldPrice.setBounds(50, 170, 200, 30);
        add(fieldPrice);
    }

    // EFFECTS: constructs the buttons
    public void setButton() {
        JButton btnAdd = new JButton("Finish");
        btnAdd.setActionCommand("addProperty");
        btnAdd.addActionListener(this); // Sets "this" object as an action listener for btn
        add(btnAdd);
        btnAdd.setBounds(50, 210, 200, 30);

        JButton btnView = new JButton("View List");
        btnView.setActionCommand("viewList");
        btnView.addActionListener(this); // Sets "this" object as an action listener for btn
        add(btnView);
        btnView.setBounds(300, 210, 200, 30);



    }

    // EFFECTS: calls corresponding methods when the JButton btn is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("addProperty")) {
            String address = fieldAddress.getText();
            String city = fieldCity.getText();
            String ownerName = fieldOwner.getText();
            int price = Integer.parseInt(fieldPrice.getText());
            propertyList.addProperty(new Property(address, city, price, ownerName, false));
            viewList.dispose();
            new ViewList(propertyList);
            dispose();
        } else if (e.getActionCommand().equals("viewList")) {
            new ViewList(propertyList);
        }
    }


}