package ui;


import javax.swing.table.DefaultTableModel;

//Code influenced by the Oracle https://docs.oracle.com/javase/tutorial/uiswing/examples/components/
//                              TableRenderDemoProject/src/components/TableRenderDemo.java
//
public class MyTableModel extends DefaultTableModel {
    String[] columnNames = {"Index",
            "Address",
            "City",
            "Price",
            "Owner Name",
            "Sold status"};
    private Object[][] data = {

    };

    public final Object[] longValues = {"0", "3888 FOSTER STREET", "RICHMOND",
            1400000, "Peter", Boolean.TRUE};

    public int getColumnCount() {
        return columnNames.length;
    }


//    public int getRowCount() {
//        return data.length;
//    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

//    public Object getValueAt(int row, int col) {
//        return data[row][col];
//    }
}

