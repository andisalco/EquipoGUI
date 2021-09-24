package code;

public class Persona {
    private int idPersona;
    private long cedula;
    private String nombre;
    private Ciudad ciudadNacimiento;

    public Persona(int idPersona, long cedula, String nombre, Ciudad ciudadNacimiento) {
        this.idPersona = idPersona;
        this.cedula = cedula;
        this.nombre = nombre;
        this.ciudadNacimiento = ciudadNacimiento;
    }

    public int getIdPersona() {return idPersona;}

    public void setIdPersona(int idPersona) {this.idPersona = idPersona; }

    public long getCedula() {
        return cedula;
    }

    public void setCedula(long cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Ciudad getCiudadNacimiento() {
        return ciudadNacimiento;
    }

    public void setCiudadNacimiento(Ciudad ciudadNacimiento) {
        this.ciudadNacimiento = ciudadNacimiento;
    }



}
