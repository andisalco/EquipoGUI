package code;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;

public class ModelTableEntrenador extends AbstractTableModel {
    private ArrayList<Entrenador> entrenadorList;
    private String[] columnNames;
    private TableModelListener tableModelListener;

    public ModelTableEntrenador(ArrayList<Entrenador> entrenadorList) {
        this.entrenadorList = entrenadorList;
        columnNames = new String[] {"Cédula", "Nombre", "Ciudad", "Exp.", "Estudio"};
    }

    @Override
    public int getRowCount() {
        return entrenadorList.size();
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
                return Long.class;

            case 1:
                return String.class;

            case 2:
                return Ciudad.class;

            case 3:
                return int.class;

            case 4:
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
        Entrenador entrenador = (Entrenador) this.entrenadorList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return entrenador.getCedula();
            case 1:
                return entrenador.getNombre();
            case 2:
                return entrenador.getCiudadNacimiento().getNombre();
            case 3:
                return  entrenador.getExperiencia();
            case 4:
                return entrenador.getEstudio();

        }
        // Para que siempre retorne un valor, aunque no llegaría aquí.
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Entrenador entrenador = this.entrenadorList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                entrenador.setCedula((Long)aValue);
            case 1:
                entrenador.setNombre((String)aValue);
            case 2:
                entrenador.setCiudadNacimiento((Ciudad)aValue);
            case 3:
                entrenador.setExperiencia((int)aValue);
            case 4:
                entrenador.setEstudio((String)aValue);
        }

        this.entrenadorList.set(rowIndex,entrenador);
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
        return entrenadorList.get(rowIndex).getIdPersona();
    }

}
