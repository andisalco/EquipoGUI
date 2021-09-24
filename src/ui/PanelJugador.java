package ui;

import code.*;
import db.DatabaseCiudad;
import db.DatabaseEquipo;
import db.DatabasePersona;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PanelJugador extends JPanel implements ActionListener {
    private JPanel uiPanelJugador;
    private JButton eliminarButton;
    private JScrollPane uiScrollPane;
    private JTable showTableJugador;
    private JTextField textFieldNombre;
    private JComboBox comboBoxCiudad;
    private JTextField textFieldDate;
    private JButton agregarButton;
    private JTextField textFieldPosicion;
    private JComboBox comboBoxEquipo;
    private JTextField textFieldCedula;
    private JTextField textFieldAcciones;
    private ModelTableJugador tmodel;

    //  private ModelTableJugador tmodel;
    private DatabaseEquipo dbaseEquipo;
    private DatabaseCiudad dbaseCiudad;
    private DatabasePersona dbasePersona;
    private MaskFormatter maskDate;
    private Ciudad ciudadAdd;
    private Equipo equipoAdd;
    int comboIdCiudad;


    public PanelJugador(DatabasePersona dbasePersona, DatabaseEquipo dbaseEquipo, DatabaseCiudad dbaseCiudad){

        this.dbaseEquipo = dbaseEquipo;
        this.dbaseCiudad = dbaseCiudad;
        this.dbasePersona = dbasePersona;
        tmodel = new ModelTableJugador(dbasePersona.getListDatabaseJugador());
        showTableJugador.setModel(tmodel);
        createTableJugador(showTableJugador);
        createComboBoxCiudad();
        createComboBoxEquipo();
        validateFields();

        add(uiPanelJugador);

    }


    public void createComboBoxCiudad(){
        ArrayList<Ciudad> ciudadList  = dbaseCiudad.getListDatabaseCiudad();

        for (int i=0; i< ciudadList.size(); i++){
            Ciudad ciudad = (Ciudad)ciudadList.get(i);
            String nameCiudad= ciudad.getNombre();

            comboBoxCiudad.addItem(nameCiudad);
        }
    }

    public void createComboBoxEquipo(){
        ArrayList<Equipo> equipoList  = dbaseEquipo.getListDatabaseEquipo();

        for (int i=0; i< equipoList.size(); i++){
            Equipo equipo = (Equipo)equipoList.get(i);
            String nameEquipo= equipo.getNombre();

            comboBoxEquipo.addItem(nameEquipo);
        }
    }


    public void createTableJugador(JTable showTableEquipo){

        // Modificar encabezado
        showTableJugador.getTableHeader().setReorderingAllowed(false);
        showTableJugador.getTableHeader().setBackground(new Color(30, 42, 72));
        showTableJugador.getTableHeader().setForeground(new Color(255, 255, 255));

        showTableJugador.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 12));

        showTableJugador.setSelectionBackground(new Color(86, 116, 153));

        showTableJugador.setRowHeight(20);


        // Alinear a la Izquierda columna precios e inventario

        DefaultTableCellRenderer alignRight = new DefaultTableCellRenderer();
        alignRight.setHorizontalAlignment(SwingConstants.RIGHT);


        // Edición de celdas

        uiScrollPane.setPreferredSize(new Dimension(480, 300));
        uiScrollPane.setViewportView(showTableEquipo);

        agregarButton.addActionListener(this);
        eliminarButton.addActionListener(this);
        comboBoxCiudad.addActionListener(this);
        comboBoxEquipo.addActionListener(this);
    }

    public  void validateFields(){
        // Evento que permite solo digitar en el campo Fundación, números y el guion
        textFieldDate.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                //super.keyTyped(e);
                char keyChar = e.getKeyChar();
                // Rechazar caracteres no numéricos y diferentes a punto
                if (!Character.isDigit(keyChar) && keyChar != '-'){
                    e.consume();

                }
            }
        });

        // Evento que permite solo digitar en el campo cedula, números
        textFieldCedula.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                //super.keyTyped(e);
                char keyChar = e.getKeyChar();
                // Rechazar caracteres no numéricos
                if (!Character.isDigit(keyChar)){
                    e.consume();
                }
            }
        });


        // Evento que permite solo digitar en el campo acciones, números
        textFieldAcciones.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                //super.keyTyped(e);
                char keyChar = e.getKeyChar();
                // Rechazar caracteres no numéricos
                if (!Character.isDigit(keyChar)){
                    e.consume();
                }
            }
        });


    }

    // Formato fecha
    public static Date parseDate(String date){
        try{
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e1){
            return null;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        URL ruta_imagen = PanelEquipo.class.getClassLoader().getResource("images/flaticon.png");
        ImageIcon iconFlat = new ImageIcon(ruta_imagen);

        if (e.getSource() == comboBoxCiudad){
            String selCiudad = (String) comboBoxCiudad.getSelectedItem();
            int comboIdCiudad = dbaseCiudad.returnIndexCiudad(selCiudad);
            ciudadAdd = dbaseCiudad.returnCiudad(comboIdCiudad);
        }

        if (e.getSource() == comboBoxEquipo){
            String selEquipo = (String) comboBoxEquipo.getSelectedItem();
            int comboIdEquipo = dbaseEquipo.returnIndexEquipo(selEquipo);
            equipoAdd = dbaseEquipo.returnEquipo(comboIdEquipo);
        }

        if (e.getActionCommand().equals("Agregar")){

            // Validar productos obligatorios
            if (    (textFieldCedula.getText().isEmpty()) ||
                    (textFieldNombre.getText().isEmpty()) ||
                    (textFieldDate.getText().isEmpty()) ||
                    (textFieldAcciones.getText().isEmpty()) ||
                    (textFieldPosicion.getText().isEmpty()) ) {
                JOptionPane.showMessageDialog(this, "Todos los campos son oblitatorios",
                        "Error!", JOptionPane.WARNING_MESSAGE);

                // Agregar Jugador
            } else{
                int codAdd = dbasePersona.getIndexPersona()+1;
                // Se eliminan espacios en blanco en los extremos del String
                Long cedulaAdd = Long.parseLong(textFieldCedula.getText().trim());
                String nameAdd = textFieldNombre.getText().trim();
                Date nacimientoAdd = parseDate(textFieldDate.getText().trim());
                int accionesAdd = Integer.parseInt(textFieldAcciones.getText().trim());
                String posicionAdd = textFieldPosicion.getText().trim();

                dbasePersona.agregarPersona(codAdd,cedulaAdd,nameAdd,ciudadAdd,nacimientoAdd,posicionAdd,accionesAdd, equipoAdd);
                String nameAddProd = dbasePersona.returnPersona(codAdd).getNombre();// Jugador
                showTableJugador.setModel(new ModelTableJugador(dbasePersona.getListDatabaseJugador()));

                // Limpiar valores
                textFieldCedula.setText("");
                textFieldNombre.setText("");
                textFieldDate.setText("");
                textFieldPosicion.setText("");
                textFieldAcciones.setText("");

                JOptionPane.showMessageDialog(this, "Jugador \""+nameAddProd+"\"  agregado exitosamente","Información",JOptionPane.INFORMATION_MESSAGE,iconFlat);

            }

        }

        // Modelo con los objetos de la tabla
        ModelTableJugador modelJugador;

        if (e.getActionCommand().equals("Eliminar")){

            // Valida si no fue seleccionado el producto a borrar
            if (showTableJugador.getSelectedRows().length < 1 ) {
                JOptionPane.showMessageDialog(this, "Seleccione el Jugador a eliminar.",
                        "Error!", JOptionPane.WARNING_MESSAGE);

                // Valida si existen múltiples productos a borrar
            } else if (showTableJugador.getSelectedRows().length > 1 ){
                JOptionPane.showMessageDialog(this, "Seleccione solamente un Jugador.",
                        "Error!", JOptionPane.WARNING_MESSAGE);
            }
            // Procede a borrar si se selecciona un solo producto.
            else {

                int codRow = showTableJugador.getSelectedRow(); // fila seleccionada
                tmodel = (ModelTableJugador) showTableJugador.getModel(); // Modelo del Jtable
                // Código del equipo seleccionado
                int codeDel = tmodel.getValueCodAt(codRow);
                String nameDel = dbasePersona.returnPersona(codeDel).getNombre();
                dbasePersona.eliminarPersona(codeDel);

                // Actualizar modelo
                showTableJugador.setModel(new ModelTableJugador(dbasePersona.getListDatabaseJugador()));
                JOptionPane.showMessageDialog(this, "El Jugador \""+nameDel+"\" fue eliminado exitosamente.","Información",JOptionPane.INFORMATION_MESSAGE,iconFlat);

            }
        }

    }

}
