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

public class PanelEquipo extends JPanel implements ActionListener {
    private JPanel uiPanelEquipo;
    private JButton eliminarButton;
    private JScrollPane uiScrollPane;
    private JTable showTableEquipo;
    private JTextField textFieldNombre;
    private JButton agregarButton;
    private JTextField textFieldTitulos;
    private JFormattedTextField fTextFieldDate;
    private JComboBox comboBoxCiudad;
    private JTextField textFieldDate;
    private JButton jugadoresButton;
    private JComboBox comboBoxEntrenador;
    private ModelTableEquipo tmodel;
    private DatabaseEquipo dbaseEquipo;
    private DatabaseCiudad dbaseCiudad;
    private DatabasePersona dbasePersona;
    private MaskFormatter maskDate;
    private Ciudad ciudadAdd;
    private Persona entrenadorAdd;
    int comboIdCiudad;
    int comboIdPersona;


    public PanelEquipo(DatabaseEquipo dbaseEquipo,  DatabaseCiudad dbaseCiudad, DatabasePersona dbasePersona){

        this.dbaseEquipo = dbaseEquipo;
        this.dbaseCiudad = dbaseCiudad;
        this.dbasePersona = dbasePersona;
        tmodel = new ModelTableEquipo(dbaseEquipo.getListDatabaseEquipo());
        showTableEquipo.setModel(tmodel);
        createTableEquipo(showTableEquipo);
        createComboBoxCiudad();
        createComboBoxEntrenador();
        validateFields();

        add(uiPanelEquipo);

    }


    public void createComboBoxCiudad(){
        ArrayList<Ciudad> ciudadList  = dbaseCiudad.getListDatabaseCiudad();

        for (int i=0; i< ciudadList.size(); i++){
            Ciudad ciudad = (Ciudad)ciudadList.get(i);
            String nameCiudad= ciudad.getNombre();

            comboBoxCiudad.addItem(nameCiudad);
        }
    }


    public void createComboBoxEntrenador(){
        ArrayList<Entrenador> entrenadorList  = dbasePersona.getListDatabaseEntrenador();

        for (int i=0; i< entrenadorList.size(); i++){
            Entrenador entrenador = (Entrenador)entrenadorList.get(i);
            String nameEntrenador = entrenador.getNombre();

            comboBoxEntrenador.addItem(nameEntrenador);
        }
    }

    public void createTableEquipo(JTable showTableEquipo){

        // Modificar encabezado
        showTableEquipo.getTableHeader().setReorderingAllowed(false);
        showTableEquipo.getTableHeader().setBackground(new Color(30, 42, 72));
        showTableEquipo.getTableHeader().setForeground(new Color(255, 255, 255));

        showTableEquipo.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 14));

        showTableEquipo.setSelectionBackground(new Color(86, 116, 153));

        showTableEquipo.setRowHeight(20);


        // Alinear a la Izquierda columna precios e inventario

        DefaultTableCellRenderer alignRight = new DefaultTableCellRenderer();
        alignRight.setHorizontalAlignment(SwingConstants.RIGHT);


        // Edición de celdas

        uiScrollPane.setPreferredSize(new Dimension(450, 300));
        uiScrollPane.setViewportView(showTableEquipo);

        agregarButton.addActionListener(this);
        eliminarButton.addActionListener(this);
        comboBoxCiudad.addActionListener(this);
        comboBoxEntrenador.addActionListener(this);
        jugadoresButton.addActionListener(this);
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

        // Evento que permite solo digitar en el campo Titulos, números
        textFieldTitulos.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                //super.keyTyped(e);
                char keyChar = e.getKeyChar();
                // Rechazar caracteres no numéricos y diferentes a punto
                if (!Character.isDigit(keyChar) ){
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

        if (e.getSource() == comboBoxCiudad) {
            String selCiudad = (String) comboBoxCiudad.getSelectedItem();
            int comboIdCiudad = dbaseCiudad.returnIndexCiudad(selCiudad);
            ciudadAdd = dbaseCiudad.returnCiudad(comboIdCiudad);
        }

        if (e.getSource() == comboBoxEntrenador) {
            String selEntrenador = (String) comboBoxEntrenador.getSelectedItem();
            int comboIdEntrenador = dbasePersona.returnIndexPersona(selEntrenador);
            entrenadorAdd = dbasePersona.returnPersona(comboIdEntrenador);
        }


        if (e.getActionCommand().equals("Agregar")) {

            // Validar productos obligatorios
            if ((textFieldNombre.getText().isEmpty()) ||
                    (textFieldTitulos.getText().isEmpty()) ||
                    (textFieldDate.getText().isEmpty())) {
                JOptionPane.showMessageDialog(this, "Todos los campos son oblitatorios",
                        "Error!", JOptionPane.WARNING_MESSAGE);

                // Agregar equipo
            } else {
                int codAdd = dbaseEquipo.getIndexEquipo() + 1;
                // Se eliminan espacios en blanco en los extremos del String
                String nameAdd = textFieldNombre.getText().trim();
                int titulosAdd = Integer.parseInt(textFieldTitulos.getText().trim());
                Date dateAdd = parseDate(textFieldDate.getText().trim());

                dbaseEquipo.agregarEquipo(codAdd, nameAdd, titulosAdd, dateAdd, ciudadAdd, (Entrenador) entrenadorAdd);

                String nameAddProd = dbaseEquipo.returnEquipo(codAdd).getNombre();// Equipo
                showTableEquipo.setModel(new ModelTableEquipo(dbaseEquipo.getListDatabaseEquipo()));

                // Limpiar valores
                textFieldNombre.setText("");
                textFieldTitulos.setText("");
                textFieldDate.setText("");

                JOptionPane.showMessageDialog(this, "Equipo \"" + nameAddProd + "\"  agregado exitosamente", "Información", JOptionPane.INFORMATION_MESSAGE, iconFlat);

            }

        }

        // Modelo con los objetos de la tabla
        ModelTableEquipo modelEquipo;

        if (e.getActionCommand().equals("Eliminar")) {

            // Valida si no fue seleccionado el producto a borrar
            if (showTableEquipo.getSelectedRows().length < 1) {
                JOptionPane.showMessageDialog(this, "Seleccione el equipo a eliminar.",
                        "Error!", JOptionPane.WARNING_MESSAGE);

                // Valida si existen múltiples productos a borrar
            } else if (showTableEquipo.getSelectedRows().length > 1) {
                JOptionPane.showMessageDialog(this, "Seleccione solamente un equipo.",
                        "Error!", JOptionPane.WARNING_MESSAGE);
            }
            // Procede a borrar si se selecciona un solo producto.
            else {

                int codRow = showTableEquipo.getSelectedRow(); // fila seleccionada
                modelEquipo = (ModelTableEquipo) showTableEquipo.getModel(); // Modelo del Jtable
                // Código del equipo seleccionado
                int codeDel = modelEquipo.getValueCodAt(codRow);
                String nameDel = dbaseEquipo.returnEquipo(codeDel).getNombre();
                dbaseEquipo.eliminarEquipo(codeDel);

                // Actualizar modelo
                showTableEquipo.setModel(new ModelTableEquipo(dbaseEquipo.getListDatabaseEquipo()));
                JOptionPane.showMessageDialog(this, "El equipo \"" + nameDel + "\" fue eliminado exitosamente.", "Información", JOptionPane.INFORMATION_MESSAGE, iconFlat);

            }
        }


        if (e.getActionCommand().equals("Jugadores")) {

            // Valida si no fue seleccionado el equipo
            if (showTableEquipo.getSelectedRows().length < 1) {
                JOptionPane.showMessageDialog(this, "Seleccione el equipo.",
                        "Error!", JOptionPane.WARNING_MESSAGE);

                // Valida si se seleccionaron múltiples equipo
            } else if (showTableEquipo.getSelectedRows().length > 1) {
                JOptionPane.showMessageDialog(this, "Seleccione solamente un equipo.",
                        "Error!", JOptionPane.WARNING_MESSAGE);
            }
            // Procede a desplegar los jugadores del equipo.
            else {

                ArrayList<Jugador> jugadorLista = dbasePersona.getListDatabaseJugador();
                ArrayList<String> jugadorEquipo = new ArrayList();
                int codRow = showTableEquipo.getSelectedRow(); // fila seleccionada
                modelEquipo = (ModelTableEquipo) showTableEquipo.getModel(); // Modelo del Jtable
                // Código del equipo seleccionado
                int codeDel = modelEquipo.getValueCodAt(codRow);
                String nameDel = dbaseEquipo.returnEquipo(codeDel).getNombre();
                int totalJugadores = 0;

                for (int i = 0; i < jugadorLista.size(); i++) {
                    Jugador jugador = (Jugador) jugadorLista.get(i);
                    if (jugador.getEquipo().getNombre() == nameDel) {
                        totalJugadores += 1;
                        jugadorEquipo.add(jugador.getNombre());
                    }

                }
                JOptionPane.showMessageDialog(this, "El equipo "+nameDel+ " cuenta con " +totalJugadores+ " jugador(es):" +
                        "\n" + jugadorEquipo, "Información", JOptionPane.INFORMATION_MESSAGE, iconFlat);

            }

        }


    }

}
