package code;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;

public class ModelTableEquipo extends AbstractTableModel {
    private ArrayList<Equipo> equipoList;
    private String[] columnNames;
    private TableModelListener tableModelListener;

    public ModelTableEquipo(ArrayList<Equipo> equipoList) {
        this.equipoList = equipoList;
        columnNames = new String[] {"Nombre", "Titulos", "Fundacion", "Ciudad","Entrenador"};
    }

    @Override
    public int getRowCount() {
        return equipoList.size();
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
            case 1:
                return columnNames[1];
            case 2:
                return columnNames[2];
            case 3:
                return columnNames[3];
            case 4:
                return columnNames[4];

        }
        // Para que siempre retorne un valor, aunque no llegaría aquí.
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex){
            case 0:
                return String.class;

            case 1:
                return int.class;

            case 2:
                return Date.class;

            case 3:
                return Ciudad.class;

            case 4:
                return Entrenador.class;

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
                return equipoList.get(rowIndex).getNombre();
            case 1:
                return equipoList.get(rowIndex).getTitulos();
            case 2:
                return equipoList.get(rowIndex).getFundacion();
            case 3:
                return equipoList.get(rowIndex).getCiudad().getNombre();
            case 4:
                return equipoList.get(rowIndex).getEntrenador().getNombre();

        }
        // Para que siempre retorne un valor, aunque no llegaría aquí.
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Equipo equipo = this.equipoList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                equipo.setNombre((String)aValue);
            case 1:
                equipo.setTitulos((int)aValue);
            case 2:
                equipo.setFundacion((Date)aValue);
            case 3:
                equipo.setCiudad((Ciudad)aValue);
            case 4:
                equipo.setEntrenador((Entrenador)aValue);
        }

        this.equipoList.set(rowIndex,equipo);
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
        return equipoList.get(rowIndex).getIdEquipo();
    }

}
