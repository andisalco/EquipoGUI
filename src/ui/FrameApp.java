package ui;

import code.Entrenador;
import db.DatabaseEquipo;
import db.DatabaseCiudad;
import db.DatabasePersona;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FrameApp extends JFrame implements ActionListener {
    private JPanel uiFrameApp;
    private JPanel uiPanelTitle;
    private JPanel uiPanelBotton;
    private JLabel uiLabelAutor;
    private JLabel uiLabelTitle;
    private JButton equiposButton;
    private JButton jugadoresButton;
    private JButton entrenadorButton;
    private JButton ciudadesButton;


    DatabaseCiudad dbase = new DatabaseCiudad();
    DatabasePersona dbasePersona = new DatabasePersona();
    DatabaseEquipo dbaseEquipo = new DatabaseEquipo();

    PanelInicio panelInicio;
    PanelCiudad panelCiudad;
    PanelEntrenador panelEntrenador;
    PanelEquipo panelEquipo;
    PanelJugador panelJugadores;

    public FrameApp(){

        // Se crea el panel ciudad
        panelCiudad =  new PanelCiudad(dbase);

        // Se crea el panel de bienvenida
        panelInicio =  new PanelInicio();

        // Se crea el panel de entrenadores
        panelEntrenador =  new PanelEntrenador(dbasePersona,dbase);

        // Se crea el panel de equipos
        panelEquipo =  new PanelEquipo(dbaseEquipo, dbase,dbasePersona);

        // Se crea el panel de jugdores
        panelJugadores =  new PanelJugador(dbasePersona, dbaseEquipo, dbase);


        // Caracteristicas para JFrame
        setSize(650,920);
        setMinimumSize(new Dimension(650,920));
        setLocationRelativeTo(null);
        setTitle("Equipos App");

        // Icono Aplicación
        // Clase Toolkit utizada para incluir imagen para el ícono de la aplicación.
        Toolkit interfaceApp = Toolkit.getDefaultToolkit();
        URL ruta_imagen = FrameApp.class.getClassLoader().getResource("ui/iconApp.png");

        Image iconApp = interfaceApp.getImage(ruta_imagen);
        setIconImage(iconApp);

        // Finaliza la aplicación cuando se cierra la ventana.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Visualizamos el panel Principal del frame.
        setContentPane(uiFrameApp);

        // adicionar paneles
        add(panelCiudad,BorderLayout.CENTER);
        add(panelEntrenador,BorderLayout.CENTER);
        add(panelEquipo,BorderLayout.CENTER);
        add(panelJugadores,BorderLayout.CENTER);

        // Panel inicio
        add(panelInicio,BorderLayout.CENTER);

        // Visibilidad del frame
        setVisible(true);
        ciudadesButton.addActionListener(this);
        equiposButton.addActionListener(this);
        entrenadorButton.addActionListener(this);
        jugadoresButton.addActionListener(this);

        cargarDatosIniciales();

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Ciudades")){

            // Panel ciudad
            panelCiudad =  new PanelCiudad(dbase);
            add(panelCiudad,BorderLayout.CENTER);
            panelCiudad.setVisible(true);
            panelInicio.setVisible(false);
            panelJugadores.setVisible(false);
            panelEquipo.setVisible(false);
            panelEntrenador.setVisible(false);

        }

        if (e.getActionCommand().equals("Equipos")){

            // Panel equipo
            panelEquipo =  new PanelEquipo(dbaseEquipo, dbase, dbasePersona);
            add(panelEquipo,BorderLayout.CENTER);
            panelEquipo.setVisible(true);
            panelCiudad.setVisible(false);
            panelInicio.setVisible(false);
            panelJugadores.setVisible(false);
            panelEntrenador.setVisible(false);

        }

        if (e.getActionCommand().equals("Jugadores")){

            // Panel jugadores
            panelJugadores =  new PanelJugador(dbasePersona, dbaseEquipo, dbase);
            add(panelJugadores,BorderLayout.CENTER);
            panelJugadores.setVisible(true);
            panelCiudad.setVisible(false);
            panelInicio.setVisible(false);
            panelEquipo.setVisible(false);
            panelEntrenador.setVisible(false);

        }

        if (e.getActionCommand().equals("Entrenador")){

            // Panel Entrenadores
            panelEntrenador =  new PanelEntrenador(dbasePersona, dbase);
            add(panelEntrenador,BorderLayout.CENTER);
            panelEntrenador.setVisible(true);
            panelJugadores.setVisible(false);
            panelCiudad.setVisible(false);
            panelInicio.setVisible(false);
            panelEquipo.setVisible(false);

        }

    }

    public void cargarDatosIniciales() {

        // Cargar datos de ciudades
        dbase.agregarCiudad(1,"Jamundi");
        dbase.agregarCiudad(2,"Bogota");
        dbase.agregarCiudad(3,"Medellin");
        dbase.agregarCiudad(4,"Barranquilla");
        dbase.agregarCiudad(5,"Neiva");
        dbase.agregarCiudad(6,"Manizales");
        dbase.agregarCiudad(7,"Cucuta");
        dbase.agregarCiudad(8,"Armenia");
        dbase.agregarCiudad(9,"Mosquera");
        dbase.agregarCiudad(10,"Palmira");

        // Cargar datos entrenador
        dbasePersona.agregarPersona(1, 1234567L,"Andrea Salazar",dbase.returnCiudad(1),
                4,"Informatica");
        dbasePersona.agregarPersona(2,551234567L,"Alex Arango",dbase.returnCiudad(2),
                4,"Ing. Deportes");

        // Cargar datos equipo
        dbaseEquipo.agregarEquipo(1,"Deportivo Unir",5, parseDate("2015-02-20"),dbase.returnCiudad(1), (Entrenador) dbasePersona.returnPersona(1));
        dbaseEquipo.agregarEquipo(2,"Star team",4, parseDate("2018-09-10"),dbase.returnCiudad(2), (Entrenador)dbasePersona.returnPersona(2));
        dbaseEquipo.agregarEquipo(3,"Equipo Sur",8, parseDate("2015-01-22"),dbase.returnCiudad(5), (Entrenador)dbasePersona.returnPersona(2));
        dbaseEquipo.agregarEquipo(4,"Deportes UnoA",1, parseDate("2020-12-22"),dbase.returnCiudad(3), (Entrenador)dbasePersona.returnPersona(1));

        // Cargar datos Jugadores

        dbasePersona.agregarPersona(3,335673434L,"Pedro Perez",dbase.returnCiudad(4),
               parseDate("2015-02-20"),"Central",3,dbaseEquipo.returnEquipo(1));
        dbasePersona.agregarPersona(4,122233567L,"Juan Jota",dbase.returnCiudad(3),
                parseDate("2015-02-20"),"Central",3,dbaseEquipo.returnEquipo(2));

    }

    // Formato fecha
    public static Date parseDate(String date){
        try{
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e1){
            return null;
        }
    }
}
