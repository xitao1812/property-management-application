package ui;

import model.Property;
import model.PropertyList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Represents a mark as sold GUI
public class MarkSold extends JFrame implements ActionListener {
    private JFrame frame;
    private JLabel displayField;
    private PropertyList propertyList;
    private JTextField fieldIndex;
    private CongratulationGUI photoArea;
    private ImageIcon image;
    private ViewList viewList;

    // EFFECTS: constructs the mark as sold
    public MarkSold(ViewList viewList, PropertyList propertyList) {
        super("Mark a property as sold");
        this.propertyList = propertyList;
        this.viewList = viewList;






        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 500));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());


        JLabel labelInfo = new JLabel("Please enter property index that you want to mark as sold");
        labelInfo.setBounds(50, 10, 400, 30);

        add(labelInfo);
        this.setField();
        this.setButton();


        //createCongratulation();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: constructs the input fields
    public void setField() {
        fieldIndex = new JTextField(30);
        fieldIndex.setBounds(50, 50, 200, 30);
        add(fieldIndex);


    }

    // EFFECTS: constructs the buttons
    public void setButton() {
        JButton btnAdd = new JButton("Confirm");
        btnAdd.setActionCommand("confirm");
        btnAdd.addActionListener(this);
        add(btnAdd);
        btnAdd.setBounds(50, 110, 200, 30);

        JButton btnView = new JButton("View List");
        btnView.setActionCommand("viewList");
        btnView.addActionListener(this);
        add(btnView);
        btnView.setBounds(300, 110, 200, 30);

    }

    // EFFECTS: calls corresponding methods when the JButton btn is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("confirm")) {
            int index = Integer.parseInt(fieldIndex.getText());
            propertyList.markPropertyAsSold(index);
            viewList.dispose();
            new ViewList(propertyList);
            new CongratulationGUI();

        } else if (e.getActionCommand().equals("viewList")) {
            new ViewList(propertyList);
        }
    }



    public static void main(String[] args) {
        new PropertyListGUI();
    }
}
