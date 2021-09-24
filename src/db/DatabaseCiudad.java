package db;

import code.Ciudad;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Andrea Salazar
 * db.DatabaseCiudad.java
 *
 * Contiene el HashMap utilizado como base de datos
 */
public class DatabaseCiudad {
    private HashMap<Integer, Ciudad> hashMapDatabaseCiudad;
    private HashMap<String, Integer> hashMapNombreCiudad;
    private int indexCiudad = 10;
    public DatabaseCiudad() {
        hashMapDatabaseCiudad = new HashMap<>();
    }

    public ArrayList<Ciudad> getListDatabaseCiudad(){
        return new ArrayList<>(hashMapDatabaseCiudad.values());
    }

    public void agregarCiudad(int idCiudad, String nombre){
        hashMapDatabaseCiudad.put(idCiudad,new Ciudad(idCiudad,nombre));
        setIndexCiudad(indexCiudad+1);
    }

    public void eliminarCiudad(int idCiudad){
        hashMapDatabaseCiudad.remove(idCiudad);
    }

    public Ciudad returnCiudad(int idCiudad){
        return hashMapDatabaseCiudad.get(idCiudad);
    }

    public int returnIndexCiudad(String nombreCiudad){
        ArrayList<Ciudad> ciudadLista = getListDatabaseCiudad();
        hashMapNombreCiudad = new HashMap<>();
        for (Ciudad ciudad : ciudadLista) {
            hashMapNombreCiudad.put(ciudad.getNombre(), ciudad.getIdCiudad());
        }
        return hashMapNombreCiudad.get(nombreCiudad);
    }

    public int getIndexCiudad() {
        return indexCiudad;
    }

    public void setIndexCiudad(int indexCiudad) {
        this.indexCiudad = indexCiudad;
    }

}

