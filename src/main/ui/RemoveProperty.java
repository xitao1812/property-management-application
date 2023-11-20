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
// Represents a remove property GUI
public class RemoveProperty extends JFrame implements ActionListener {
    private static PropertyList propertyList;
    private JTextField fieldIndex1;
    private static ViewList viewList;

    // EFFECTS: constructs the remove property page
    public RemoveProperty(ViewList viewList, PropertyList propertyList) {
        super("Remove a property");
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
        JLabel labelInfo = new JLabel("Please enter property index that you want to remove");
        labelInfo.setBounds(50, 40, 400, 30);

        add(labelInfo);

    }

    // EFFECTS: constructs the input fields
    public void setField() {
        fieldIndex1 = new JTextField(30);
        fieldIndex1.setBounds(50, 70, 200, 30);
        add(fieldIndex1);

    }

    // EFFECTS: constructs the buttons
    public void setButton() {
        JButton btnConfirm = new JButton("Confirm");
        btnConfirm.setActionCommand("confirm");
        btnConfirm.addActionListener(this);
        add(btnConfirm);
        btnConfirm.setBounds(50, 180, 200, 30);


    }

    // EFFECTS: calls corresponding methods when the JButton btn is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("confirm")) {
            int index = Integer.parseInt(fieldIndex1.getText());
            propertyList.removeProperty(index);
            this.dispose();
            viewList.dispose();
            new ViewList(propertyList);

        }
    }



    public static void main(String[] args) {
        new RemoveProperty(viewList, propertyList);
    }
}

