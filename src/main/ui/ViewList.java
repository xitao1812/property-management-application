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
// Represents a View Property List GUI
public class ViewList extends JFrame implements ActionListener {
    private PropertyList propertyList;
    private MyTableModel model;

    // EFFECTS: constructs the view list
    public ViewList(PropertyList propertyList) {

        super("View List");
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

        JLabel labelInfo = new JLabel("Property List");
        labelInfo.setBounds(50, 10, 400, 30);

        add(labelInfo);

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
        for (Property property : propertyList.getPropertyList()) {
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
        JButton btnAdd = new JButton("Add a new property");
        btnAdd.setActionCommand("addProperty");
        btnAdd.addActionListener(this); // Sets "this" object as an action listener for btn
        add(btnAdd);
        btnAdd.setBounds(20, 400, 200, 30);

        JButton btnRemove = new JButton("Remove a property");
        btnRemove.setActionCommand("removeProperty");
        btnRemove.addActionListener(this); // Sets "this" object as an action listener for btn
        add(btnRemove);
        btnRemove.setBounds(20, 450, 200, 30);

        JButton btnMark = new JButton("Mark a property as sold");
        btnMark.setActionCommand("mark");
        btnMark.addActionListener(this); // Sets "this" object as an action listener for btn
        add(btnMark);
        btnMark.setBounds(230, 400, 280, 30);

        JButton btnFilter = new JButton("Filter based on price and location");
        btnFilter.setActionCommand("filter");
        btnFilter.addActionListener(this); // Sets "this" object as an action listener for btn
        add(btnFilter);
        btnFilter.setBounds(230, 450, 280, 30);

    }

    // EFFECTS: calls corresponding methods when the JButton btn is clicked
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("addProperty")) {
            new AddProperty(this, propertyList);
        } else if (e.getActionCommand().equals("removeProperty")) {
            new RemoveProperty(this, propertyList);
        } else if (e.getActionCommand().equals("mark")) {
            new MarkSold(this, propertyList);
        } else if (e.getActionCommand().equals("filter")) {
            new Filter(this, propertyList);
        }
    }


}

