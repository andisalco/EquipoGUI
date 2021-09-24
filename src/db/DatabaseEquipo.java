package db;

import code.Ciudad;
import code.Entrenador;
import code.Equipo;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class DatabaseEquipo {
    private HashMap<Integer, Equipo> hashMapDatabaseEquipo;
    private HashMap<String, Integer> hashMapNombreEquipo;
    private int indexEquipo = 4;
    private DatabaseCiudad dbCiudad;
    private DatabasePersona dbEntrenador;
    private Entrenador entrenador;

    public DatabaseEquipo() {

        hashMapDatabaseEquipo = new HashMap<>();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        dbCiudad = new DatabaseCiudad();
        dbEntrenador = new DatabasePersona();

    }


    public ArrayList<Equipo> getListDatabaseEquipo(){
        return new ArrayList<>(hashMapDatabaseEquipo.values());
    }


    public boolean verificarExistencia(int idEquipo){
        return hashMapDatabaseEquipo.containsKey(idEquipo);
    }


    public void agregarEquipo(int idEquipo, String nombre, int titulos, Date fundacion, Ciudad ciudad, Entrenador entrenador){
        hashMapDatabaseEquipo.put(idEquipo,new Equipo(idEquipo,nombre,titulos,fundacion,ciudad,entrenador));
        setIndexEquipo(indexEquipo+1);
    }


    public void eliminarEquipo(int idEquipo){

        hashMapDatabaseEquipo.remove(idEquipo);
    }

    public void actualizarEquipo(int idEquipo, String nombre, int titulos, Date fundacion, Ciudad ciudad,Entrenador entrenador){
        Equipo equipo = hashMapDatabaseEquipo.get(idEquipo);
        equipo.setNombre(nombre);
        equipo.setTitulos(titulos);
        equipo.setFundacion(fundacion);
        equipo.setCiudad(ciudad);
        equipo.setEntrenador(entrenador);

    }

    public int returnIndexEquipo(String nombreEquipo){

        ArrayList equipoLista = getListDatabaseEquipo();
        hashMapNombreEquipo = new HashMap<>();

        for (int i=0; i<equipoLista.size();i++){
            Equipo equipo = (Equipo) equipoLista.get(i);
            hashMapNombreEquipo.put(equipo.getNombre(),equipo.getIdEquipo());
        }
        return hashMapNombreEquipo.get(nombreEquipo);
    }

    public Equipo returnEquipo(int idEquipo){
        return hashMapDatabaseEquipo.get(idEquipo);
    }

    public int getIndexEquipo() {
        return indexEquipo;
    }

    public void setIndexEquipo(int indexEquipo) {
        this.indexEquipo = indexEquipo;
    }



}
