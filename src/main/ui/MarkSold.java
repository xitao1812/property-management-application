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
// Represents a mark as sold GUI
public class MarkSold extends JFrame implements ActionListener {
    private JFrame frame;
    private JLabel displayField;
    private PropertyList propertyList;
    private JTextField fieldIndex1;
    private JTextField fieldIndex2;
    private ViewList viewList;

    // EFFECTS: constructs the mark as sold
    public MarkSold(ViewList viewList, PropertyList propertyList) {
        super("Mark a property as sold");
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
        JLabel labelInfo = new JLabel("Please enter property index that you want to mark as sold");
        labelInfo.setBounds(50, 40, 400, 30);

        add(labelInfo);

        JLabel labelOwner = new JLabel("What is the new owner name?");
        labelOwner.setBounds(50, 100, 400, 30);

        add(labelOwner);

    }

    // EFFECTS: constructs the input fields
    public void setField() {
        fieldIndex1 = new JTextField(30);
        fieldIndex1.setBounds(50, 70, 200, 30);
        add(fieldIndex1);

        fieldIndex2 = new JTextField(30);
        fieldIndex2.setBounds(50, 130, 200, 30);
        add(fieldIndex2);


    }

    // EFFECTS: constructs the buttons
    public void setButton() {
        JButton btnConfirm = new JButton("Confirm");
        btnConfirm.setActionCommand("confirm");
        btnConfirm.addActionListener(this);
        add(btnConfirm);
        btnConfirm.setBounds(50, 180, 200, 30);

        JButton btnView = new JButton("View List");
        btnView.setActionCommand("viewList");
        btnView.addActionListener(this);
        add(btnView);
        btnView.setBounds(300, 180, 200, 30);

    }

    // EFFECTS: calls corresponding methods when the JButton btn is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("confirm")) {
            int index = Integer.parseInt(fieldIndex1.getText());
            propertyList.markPropertyAsSold(index);
            String newOwnerName = fieldIndex2.getText();
            Property soldProperty = propertyList.get(index);
            soldProperty.setOwnerName(newOwnerName);
            this.dispose();
            viewList.dispose();
            new ViewList(propertyList);

        } else if (e.getActionCommand().equals("viewList")) {
            new ViewList(propertyList);
        }
    }


}
