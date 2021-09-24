package code;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Date;

public class ModelTableJugador extends AbstractTableModel {
    private ArrayList<Jugador> jugadorList;
    private String[] columnNames;
    private TableModelListener tableModelListener;

    public ModelTableJugador(ArrayList<Jugador> jugadorList) {
        this.jugadorList = jugadorList;
        columnNames = new String[] {"Cédula", "Nombre", "Ciudad", "FechaNac.", "Posicion", "Acciones", "Equipo"};
    }

    @Override
    public int getRowCount() {
        return jugadorList.size();
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
            case 5:
                return columnNames[5];
            case 6:
                return columnNames[6];

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
                return Date.class;

            case 4:
                return String.class;

            case 5:
                return Integer.class;

            case 6:
                return Equipo.class;


        }
        return null;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Jugador jugador = (Jugador) this.jugadorList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return jugador.getCedula();
            case 1:
                return jugador.getNombre();
            case 2:
                return jugador.getCiudadNacimiento().getNombre();
            case 3:
                return  jugador.getFechaNacimiento();
            case 4:
                return jugador.getPosicion();
            case 5:
                return jugador.getAcciones();
            case 6:
                return jugador.getEquipo().getNombre();
        }
        // Para que siempre retorne un valor, aunque no llegaría aquí.
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Jugador jugador = this.jugadorList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                jugador.setCedula((Long)aValue);
            case 1:
                jugador.setNombre((String)aValue);
            case 2:
                jugador.setCiudadNacimiento((Ciudad)aValue);
            case 3:
                jugador.setFechaNacimiento((Date)aValue);
            case 4:
                jugador.setPosicion((String)aValue);
            case 5:
                jugador.setAcciones((int)aValue);
            case 6:
                jugador.setEquipo((Equipo)aValue);
        }

        this.jugadorList.set(rowIndex,jugador);
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
        return jugadorList.get(rowIndex).getIdPersona();
    }

}
