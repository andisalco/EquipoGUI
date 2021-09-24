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

public class PanelEntrenador extends JPanel implements ActionListener {
    private JPanel uiPanelEntrenador;
    private JButton eliminarButton;
    private JScrollPane uiScrollPane;
    private JTable showTableEntrenador;
    private JTextField textFieldNombre;
    private JTextField textFieldExperiencia;
    private JComboBox comboBoxCiudad;
    private JButton agregarButton;
    private JTextField textFieldCedula;
    private JTextField textFieldEstudio;
    private ModelTableEntrenador tmodel;
    private DatabaseEquipo dbaseEquipo;
    private DatabaseCiudad dbaseCiudad;
    private DatabasePersona dbasePersona;
    private MaskFormatter maskDate;
    private Ciudad ciudadAdd;
    private Persona entrenadorAdd;
    int comboIdCiudad;
    int comboIdPersona;


    public PanelEntrenador(DatabasePersona dbasePersona, DatabaseCiudad dbaseCiudad){

        this.dbaseCiudad = dbaseCiudad;
        this.dbasePersona = dbasePersona;
        tmodel = new ModelTableEntrenador(dbasePersona.getListDatabaseEntrenador());
        showTableEntrenador.setModel(tmodel);
        createTableEntrenador(showTableEntrenador);
        createComboBoxCiudad();
        validateFields();

        add(uiPanelEntrenador);

    }


    public void createComboBoxCiudad(){
        ArrayList<Ciudad> ciudadList  = dbaseCiudad.getListDatabaseCiudad();

        for (int i=0; i< ciudadList.size(); i++){
            Ciudad ciudad = (Ciudad)ciudadList.get(i);
            String nameCiudad= ciudad.getNombre();

            comboBoxCiudad.addItem(nameCiudad);
        }
    }




    public void createTableEntrenador(JTable showTableEntrenador){

        // Modificar encabezado
        showTableEntrenador.getTableHeader().setReorderingAllowed(false);
        showTableEntrenador.getTableHeader().setBackground(new Color(30, 42, 72));
        showTableEntrenador.getTableHeader().setForeground(new Color(255, 255, 255));

        showTableEntrenador.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 14));

        showTableEntrenador.setSelectionBackground(new Color(86, 116, 153));

        showTableEntrenador.setRowHeight(20);


        // Alinear a la Izquierda columna precios e inventario

        DefaultTableCellRenderer alignRight = new DefaultTableCellRenderer();
        alignRight.setHorizontalAlignment(SwingConstants.RIGHT);


        // Edición de celdas

        uiScrollPane.setPreferredSize(new Dimension(450, 300));
        uiScrollPane.setViewportView(showTableEntrenador);

        agregarButton.addActionListener(this);
        eliminarButton.addActionListener(this);
        comboBoxCiudad.addActionListener(this);

    }

    public  void validateFields(){
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
        // Evento que permite solo digitar en el campo experiencia, números
        textFieldExperiencia.addKeyListener(new KeyAdapter() {

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


    @Override
    public void actionPerformed(ActionEvent e) {

        URL ruta_imagen = PanelEquipo.class.getClassLoader().getResource("images/flaticon.png");
        ImageIcon iconFlat = new ImageIcon(ruta_imagen);

        if (e.getSource() == comboBoxCiudad){
            String seleccion = (String) comboBoxCiudad.getSelectedItem();
            int comboIdCiudad = dbaseCiudad.returnIndexCiudad(seleccion);
            ciudadAdd = dbaseCiudad.returnCiudad(comboIdCiudad);
        }

        if (e.getActionCommand().equals("Agregar")){

            // Validar productos obligatorios
            if ((textFieldCedula.getText().isEmpty()) ||
                    (textFieldNombre.getText().isEmpty()) ||
                    (textFieldExperiencia.getText().isEmpty()) ||
                    (textFieldEstudio.getText().isEmpty()) ) {
                JOptionPane.showMessageDialog(this, "Todos los campos son oblitatorios",
                        "Error!", JOptionPane.WARNING_MESSAGE);

                // Agregar Entrenador
            } else{
                int codAdd = dbasePersona.getIndexPersona()+1;
                // Se eliminan espacios en blanco en los extremos del String
                Long cedulaAdd = Long.parseLong(textFieldCedula.getText().trim());
                String nameAdd = textFieldNombre.getText().trim();
                int experienciaAdd = Integer.parseInt(textFieldExperiencia.getText().trim());
                String estudioAdd = textFieldEstudio.getText().trim();

                dbasePersona.agregarPersona(codAdd,cedulaAdd,nameAdd,ciudadAdd,experienciaAdd,estudioAdd);

                String nameAddProd = dbasePersona.returnPersona(codAdd).getNombre();// Entrenador
                showTableEntrenador.setModel(new ModelTableEntrenador(dbasePersona.getListDatabaseEntrenador()));

                // Limpiar valores
                textFieldCedula.setText("");
                textFieldNombre.setText("");
                textFieldExperiencia.setText("");
                textFieldEstudio.setText("");

                JOptionPane.showMessageDialog(this, "Entrenador \""+nameAddProd+"\"  agregado exitosamente","Información",JOptionPane.INFORMATION_MESSAGE,iconFlat);

            }

        }

        // Modelo con los objetos de la tabla
        ModelTableEntrenador modelEntrenador;

        if (e.getActionCommand().equals("Eliminar")){

            // Valida si no fue seleccionado el producto a borrar
            if (showTableEntrenador.getSelectedRows().length < 1 ) {
                JOptionPane.showMessageDialog(this, "Seleccione el Entrenador a eliminar.",
                        "Error!", JOptionPane.WARNING_MESSAGE);

                // Valida si existen múltiples productos a borrar
            } else if (showTableEntrenador.getSelectedRows().length > 1 ){
                JOptionPane.showMessageDialog(this, "Seleccione solamente un Entrenador.",
                        "Error!", JOptionPane.WARNING_MESSAGE);
            }
            // Procede a borrar si se selecciona un solo producto.
            else {

                int codRow = showTableEntrenador.getSelectedRow(); // fila seleccionada
                tmodel = (ModelTableEntrenador) showTableEntrenador.getModel(); // Modelo del Jtable
                // Código del equipo seleccionado
                int codeDel = tmodel.getValueCodAt(codRow);
                String nameDel = dbasePersona.returnPersona(codeDel).getNombre();
                dbasePersona.eliminarPersona(codeDel);

                // Actualizar modelo
                showTableEntrenador.setModel(new ModelTableEntrenador(dbasePersona.getListDatabaseEntrenador()));
                JOptionPane.showMessageDialog(this, "El Entrenador \""+nameDel+"\" fue eliminado exitosamente.","Información",JOptionPane.INFORMATION_MESSAGE,iconFlat);

            }
        }

    }


}
