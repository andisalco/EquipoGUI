package db;

import code.*;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class DatabasePersona {
    private HashMap<Integer, Persona> hashMapDatabasePersona;
    private HashMap<String, Integer> hashMapNombrePersona;
    private int indexPersona = 4;
    private DatabaseCiudad dbCiudad;
    private DatabaseEquipo dbEquipo;


    public DatabasePersona() {

        hashMapDatabasePersona = new HashMap<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        dbCiudad = new DatabaseCiudad();
        //dbEquipo = new DatabaseEquipo();
        crearDatosIniciales();

    }

    public ArrayList<Persona> getListDatabasePersona(){
        return new ArrayList<>(hashMapDatabasePersona.values());
    }

    public ArrayList<Entrenador> getListDatabaseEntrenador(){
        ArrayList<Entrenador> entrenadorList = new ArrayList<>();
        ArrayList<Persona> personaLista = getListDatabasePersona();

        for (Object persona:personaLista){

            if (persona instanceof  Entrenador) {
                entrenadorList.add((Entrenador) persona);
            }
        }
        return entrenadorList;
    }

    public ArrayList<Jugador> getListDatabaseJugador(){
        ArrayList<Jugador> jugadorList = new ArrayList<>();
        ArrayList<Persona> personaLista = getListDatabasePersona();

        for (Object persona:personaLista){

            if (persona instanceof  Jugador) {
                jugadorList.add((Jugador) persona);
            }
        }
        return jugadorList;
    }


    public boolean verificarExistencia(int idPersona){
        return hashMapDatabasePersona.containsKey(idPersona);
    }

    // Jugador
    public void agregarPersona(int idPersona, Long cedula, String nombre, Ciudad ciudad, Date fechaNacimiento, String posicion, int acciones, Equipo equipo){
        hashMapDatabasePersona.put(idPersona,new Jugador(idPersona,cedula,nombre,ciudad, fechaNacimiento,posicion,acciones,equipo));
        setIndexPersona(indexPersona+1);
    }

    // Entrenador
    public void agregarPersona(int idPersona, Long cedula, String nombre, Ciudad ciudad, int experiencia, String estudio){
        hashMapDatabasePersona.put(idPersona,new Entrenador(idPersona,cedula,nombre,ciudad, experiencia, estudio));
        setIndexPersona(indexPersona+1);
    }


    public void eliminarPersona(int idPersona){
        hashMapDatabasePersona.remove(idPersona);
    }

    // Jugador
    public void actualizarPersona(int idPersona, Long cedula, String nombre, Ciudad ciudad, Date fechaNacimiento, String posicion, int acciones, Equipo equipo){
        Persona persona = hashMapDatabasePersona.get(idPersona);
        persona.setCedula(cedula);
        persona.setNombre(nombre);
        persona.setCiudadNacimiento(ciudad);
        ((Jugador)persona).setFechaNacimiento(fechaNacimiento);
        ((Jugador)persona).setPosicion(posicion);
        ((Jugador)persona).setAcciones(acciones);
        ((Jugador)persona).setEquipo(equipo);

    }

    // Entrenador
    public void actualizarPersona(int idPersona, Long cedula, String nombre, Ciudad ciudad, int experiencia, String estudio){
        Persona persona = hashMapDatabasePersona.get(idPersona);
        persona.setCedula(cedula);
        persona.setNombre(nombre);
        persona.setCiudadNacimiento(ciudad);
        ((Entrenador)persona).setExperiencia(experiencia);
        ((Entrenador)persona).setEstudio(estudio);
    }

    public Persona returnPersona(int idPersona){
        return hashMapDatabasePersona.get(idPersona);
    }

    public int returnIndexPersona(String nombrePersona){

        ArrayList personaLista = getListDatabasePersona();
        hashMapNombrePersona = new HashMap<>();

        for (int i=0; i<personaLista.size();i++){
            Persona persona = (Persona) personaLista.get(i);
            hashMapNombrePersona.put(persona.getNombre(),persona.getIdPersona());
        }
        return hashMapNombrePersona.get(nombrePersona);
    }

    public int getIndexPersona() {
        return indexPersona;
    }

    public void setIndexPersona(int indexPersona) {
        this.indexPersona = indexPersona;
    }

    // Formato fecha
    public static Date parseDate(String date){
        try{
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e1){
            return null;
        }
    }

    public void crearDatosIniciales(){
/*        hashMapDatabasePersona.put(1,new Entrenador(1,1234567,"Andrea Salazar",dbCiudad.returnCiudad(1),
                4,"Informatica"));
       // hashMapDatabasePersona.put(2,new Jugador(2,33567,"Pedro Perez",dbCiudad.returnCiudad(1),
      //          parseDate("2015-02-20"),"Central",3,dbEquipo.returnEquipo(1)));
     //   hashMapDatabasePersona.put(3,new Jugador(3,122233567,"Juan Jota",dbCiudad.returnCiudad(3),
    //            parseDate("2015-02-20"),"Central",3,dbEquipo.returnEquipo(2)));
        hashMapDatabasePersona.put(4,new Entrenador(4,551234567,"Alex Arango",dbCiudad.returnCiudad(2),
                4,"Ing. Deportes"));*/
    }


}
