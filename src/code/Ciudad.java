package code;

public class Ciudad {
    private int idCiudad;
    private String nombre;

    public Ciudad(int id, String nombre) {
        this.idCiudad = id;
        this.nombre = nombre;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setId_ciudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
