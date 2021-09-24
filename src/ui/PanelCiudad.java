package ui;

import code.ModelTableCiudad;
import db.DatabaseCiudad;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

public class PanelCiudad extends JPanel implements ActionListener {
    private JPanel uiPanelCiudad;
    private JButton eliminarButton;
    private JTable showTableCiudad;
    private JScrollPane uiScrollPane;
    private JTextField textFieldCiudad;
    private JButton agregarButton;
    private ModelTableCiudad tmodel;
    private DatabaseCiudad dbase;

    public PanelCiudad(DatabaseCiudad dbase){

        this.dbase = dbase;
        tmodel = new ModelTableCiudad(dbase.getListDatabaseCiudad());
        showTableCiudad.setModel(tmodel);
        createTableCiudad(showTableCiudad);

        add(uiPanelCiudad);

    }

    public void createTableCiudad(JTable showTableCiudad){

        // Modificar encabezado
        showTableCiudad.getTableHeader().setReorderingAllowed(false);
        showTableCiudad.getTableHeader().setBackground(new Color(30, 42, 72));
        showTableCiudad.getTableHeader().setForeground(new Color(255, 255, 255));

        showTableCiudad.getTableHeader().setFont(new Font("Arial", Font.PLAIN, 14));

        showTableCiudad.setSelectionBackground(new Color(86, 116, 153));

        showTableCiudad.setRowHeight(20);


        // Alinear a la Izquierda columna precios e inventario

        DefaultTableCellRenderer alignRight = new DefaultTableCellRenderer();
        alignRight.setHorizontalAlignment(SwingConstants.RIGHT);


        // Edición de celdas

        uiScrollPane.setPreferredSize(new Dimension(250, 350));
        uiScrollPane.setViewportView(showTableCiudad);

        agregarButton.addActionListener(this);
        eliminarButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        URL ruta_imagen = PanelEquipo.class.getClassLoader().getResource("images/flaticon.png");
        ImageIcon iconFlat = new ImageIcon(ruta_imagen);

        if (e.getActionCommand().equals("Agregar")){

            // Validar productos obligatorios
            if ((textFieldCiudad.getText().isEmpty())) {
                JOptionPane.showMessageDialog(this, "Todos los campos son oblitatorios",
                        "Error!", JOptionPane.WARNING_MESSAGE);

                // Agregar ciudad
            } else{
                int codAdd = dbase.getIndexCiudad()+1;
                // Se eliminan espacios en blanco en los extremos del String
                String nameAdd = textFieldCiudad.getText().trim();

                dbase.agregarCiudad(codAdd,nameAdd);
                String nameAddProd = dbase.returnCiudad(codAdd).getNombre();// Nombre
                showTableCiudad.setModel(new ModelTableCiudad(dbase.getListDatabaseCiudad()));

                JOptionPane.showMessageDialog(this, "ciudad.Ciudad \""+nameAddProd+"\"  agregada exitosamente","Información",JOptionPane.INFORMATION_MESSAGE,iconFlat);

                // Limpiar valores
                textFieldCiudad.setText("");
            }

        }
        // Modelo con los objetos de la tabla
        ModelTableCiudad modelCiudad;

        if (e.getActionCommand().equals("Eliminar")){

            // Valida si no fue seleccionado el producto a borrar
            if (showTableCiudad.getSelectedRows().length < 1 ) {
                JOptionPane.showMessageDialog(this, "Seleccione la ciudad a eliminar.",
                        "Error!", JOptionPane.WARNING_MESSAGE);

                // Valida si existen múltiples productos a borrar
            } else if (showTableCiudad.getSelectedRows().length > 1 ){
                JOptionPane.showMessageDialog(this, "Seleccione solamente una ciudad.",
                        "Error!", JOptionPane.WARNING_MESSAGE);
            }
            // Procede a borrar si se selecciona un solo producto.
            else {

                int codRow = showTableCiudad.getSelectedRow(); // fila seleccionada
                modelCiudad = (ModelTableCiudad) showTableCiudad.getModel(); // Modelo del Jtable
                // Código del producto seleccionado
                int codeDel = modelCiudad.getValueCodAt(codRow);
                String nameDel = dbase.returnCiudad(codeDel).getNombre();
                dbase.eliminarCiudad(codeDel);

                // Actualizar modelo
                showTableCiudad.setModel(new ModelTableCiudad(dbase.getListDatabaseCiudad()));
                JOptionPane.showMessageDialog(this, "La ciudad \""+nameDel+"\" fue eliminada exitosamente.","Información",JOptionPane.INFORMATION_MESSAGE,iconFlat);

            }
        }

    }

}
