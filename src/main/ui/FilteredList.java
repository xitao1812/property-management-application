package ui;

import model.Property;
import model.PropertyList;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.Component;
import java.awt.Dimension;


//Code influenced by the Oracle https://docs.oracle.com/javase/tutorial/uiswing/examples/components/
//                              TableRenderDemoProject/src/components/TableRenderDemo.java
//Code influenced by stackoverflow example https://stackoverflow.com/questions/6578205/
//                                         swing-jlabel-text-change-on-the-running-application
// Represents a Filtered Property List GUI
public class FilteredList extends JFrame implements ActionListener {
    private List<Property> filteredList;
    private PropertyList propertyList;
    private MyTableModel model;

    // EFFECTS: constructs the filtered list interface
    public FilteredList(List<Property> filteredList, PropertyList propertyList) {
        super("Filtered List");
        this.filteredList = filteredList;
        this.propertyList = propertyList;
        model = new MyTableModel();
        JTable table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 300));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        initColumnSizes(table);
        add(scrollPane);
        scrollPane.setBounds(50, 50, 700, 300);
        this.addPropertyToTable();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(null);

        this.setButton();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    // EFFECTS: constructs a table with columns index, address, city, price, owner name, price, and sold status
    private void initColumnSizes(JTable table) {
        MyTableModel model = (MyTableModel)table.getModel();
        TableColumn column = null;
        Component comp = null;
        int headerWidth = 0;
        int cellWidth = 0;
        Object[] longValues = model.longValues;
        TableCellRenderer headerRenderer =
                table.getTableHeader().getDefaultRenderer();

        for (int i = 0; i < 4; i++) {
            column = table.getColumnModel().getColumn(i);

            comp = headerRenderer.getTableCellRendererComponent(
                    null, column.getHeaderValue(),
                    false, false, 0, 0);
            headerWidth = comp.getPreferredSize().width;

            comp = table.getDefaultRenderer(model.getColumnClass(i)).getTableCellRendererComponent(
                    table, longValues[i],
                    false, false, 0, i);
            cellWidth = comp.getPreferredSize().width;


            column.setPreferredWidth(Math.max(headerWidth, cellWidth));
        }
    }


    // EFFECTS: prints properties' index, address, city, price, owner name, and sold status to table
    public void addPropertyToTable() {
        int i = 0;
        for (Property property : filteredList) {
            Object[] tableRow = new Object[] {
                    i,
                    property.getAddress(),
                    property.getCity(),
                    property.getPrice(),
                    property.getOwnerName(),
                    property.getSoldStatus(),

            };
            i++;
            model.addRow(tableRow);
        }
    }



    // EFFECTS: constructs the buttons in the application
    public void setButton() {
        JButton btnAdd = new JButton("Return to full list");
        btnAdd.setActionCommand("return");
        btnAdd.addActionListener(this); // Sets "this" object as an action listener for btn
        add(btnAdd);
        btnAdd.setBounds(20, 400, 200, 30);


    }

    // EFFECTS: calls corresponding methods when the JButton btn is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("return")) {
            this.dispose();
            new ViewList(propertyList);
        }
    }


}


