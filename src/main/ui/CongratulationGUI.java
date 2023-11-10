package ui;

import model.PropertyList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

// Represents a Congratulation GUI
public class CongratulationGUI extends JPanel {
    private JFrame frame;
    private JLabel displayField;
    private ImageIcon image;

    // EFFECTS: constructs the Congratulation picture
    public CongratulationGUI() {

        frame = new JFrame();
        image = new ImageIcon(getClass().getResource("congratulation.png"));
        displayField = new JLabel(image);
        frame.add(displayField);
        frame.setSize(1200, 1000);
        frame.setVisible(true);

    }


}
