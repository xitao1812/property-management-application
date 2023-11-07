package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class CongratulationGUI extends JPanel {

    private MarkSold markSold;
    private static final int VGAP = 15;
    private JPanel congratPanel;
    private ImageIcon congratImage;
    private JLabel imageAsLabel;


    public CongratulationGUI(MarkSold markSold) {

        this.markSold = markSold;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalStrut(VGAP));
        congratPanel = new JPanel();
        congratPanel.setPreferredSize(new Dimension(300,100));
        add(congratPanel);
        //congratPanel.setBounds(20,60,300,100);

        loadImage();
        setImage();

    }

    private void loadImage() {
        String sep = System.getProperty("file.separator");
        congratImage = new ImageIcon(System.getProperty("user.dir") + sep
                + "images" + sep + "congrat.png");
    }

    private void setImage() {
        imageAsLabel = new JLabel(congratImage);
        congratPanel.add(imageAsLabel);
    }


}
