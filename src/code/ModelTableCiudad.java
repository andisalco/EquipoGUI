package code;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ModelTableCiudad extends AbstractTableModel {
    private ArrayList<Ciudad> ciudadList;
    private String[] columnNames;
    private TableModelListener tableModelListener;

    public ModelTableCiudad(ArrayList<Ciudad> ciudadList) {
        this.ciudadList = ciudadList;
        columnNames = new String[] {"Nombre"};
    }

    @Override
    public int getRowCount() {
        return ciudadList.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return columnNames[0];

        }
        // Para que siempre retorne un valor, aunque no llegaría aquí.
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return String.class;

        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {
            case 0:
                return ciudadList.get(rowIndex).getNombre();

        }
        // Para que siempre retorne un valor, aunque no llegaría aquí.
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Ciudad ciudad = this.ciudadList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                ciudad.setNombre((String)aValue);
        }

        this.ciudadList.set(rowIndex,ciudad);
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        this.tableModelListener = l;

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        this.tableModelListener = null;

    }
    public int getValueCodAt(int rowIndex) {
        return ciudadList.get(rowIndex).getIdCiudad();
    }

}

