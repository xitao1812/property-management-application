package ui;


import javax.swing.table.DefaultTableModel;

//Code influenced by the Oracle https://docs.oracle.com/javase/tutorial/uiswing/examples/components/
//                              TableRenderDemoProject/src/components/TableRenderDemo.java
//Represents a table model for property list
public class MyTableModel extends DefaultTableModel {
    String[] columnNames = {"Index",
            "Address",
            "City",
            "Price",
            "Owner Name",
            "Sold status"};

    public final Object[] longValues = {"0", "612 - 3888 FOSTER STREET", "RICHMOND",
            1400000, "Peter", Boolean.TRUE};


    // EFFECTS: returns the number of columns
    public int getColumnCount() {
        return columnNames.length;
    }

    // EFFECTS: returns the column name given the column number
    public String getColumnName(int col) {
        return columnNames[col];
    }

}



