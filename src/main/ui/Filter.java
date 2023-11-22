package ui;

import model.Property;
import model.PropertyList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


//Code influenced by stackoverflow example https://stackoverflow.com/questions/6578205/
//                                         swing-jlabel-text-change-on-the-running-application
// Represents a filter based on price and location GUI
public class Filter extends JFrame implements ActionListener {
    private PropertyList propertyList;
    private JTextField fieldIndex1;
    private JTextField fieldIndex2;
    private JTextField fieldIndex3;
    private ViewList viewList;

    // EFFECTS: constructs the mark as sold interface to display labels, buttons and input fields
    public Filter(ViewList viewList, PropertyList propertyList) {
        super("Filtered List");
        this.propertyList = propertyList;
        this.viewList = viewList;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);

        this.setLabel();
        this.setField();
        this.setButton();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: constructs the labels
    public void setLabel() {
        JLabel labelCity = new JLabel("Which city you want to view a list of properties in?");
        labelCity.setBounds(50, 40, 500, 30);

        add(labelCity);

        JLabel labelMin = new JLabel("What is the minimum price you want to view a list of properties in?");
        labelMin.setBounds(50, 100, 500, 30);

        add(labelMin);

        JLabel labelMax = new JLabel("What is the maximum price you want to view a list of properties in?");
        labelMax.setBounds(50, 160, 500, 30);

        add(labelMax);

    }

    // EFFECTS: constructs the input fields
    public void setField() {
        fieldIndex1 = new JTextField(30);
        fieldIndex1.setBounds(50, 70, 200, 30);
        add(fieldIndex1);

        fieldIndex2 = new JTextField(30);
        fieldIndex2.setBounds(50, 130, 200, 30);
        add(fieldIndex2);

        fieldIndex3 = new JTextField(30);
        fieldIndex3.setBounds(50, 190, 200, 30);
        add(fieldIndex3);


    }

    // EFFECTS: constructs the buttons
    public void setButton() {
        JButton btnAdd = new JButton("Confirm");
        btnAdd.setActionCommand("confirm");
        btnAdd.addActionListener(this);
        add(btnAdd);
        btnAdd.setBounds(50, 220, 200, 30);



    }

    // EFFECTS: calls corresponding methods when the JButton btn is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("confirm")) {
            String city = fieldIndex1.getText();
            int minPrice = Integer.parseInt(fieldIndex2.getText());
            int maxPrice = Integer.parseInt(fieldIndex3.getText());
            List<Property> filtered = propertyList.getPropertyListCityAndPrice(city, minPrice, maxPrice);
            this.dispose();
            viewList.dispose();
            new FilteredList(filtered, propertyList);

        }
    }


}
