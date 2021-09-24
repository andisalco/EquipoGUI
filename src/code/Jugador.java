package code;

import java.util.Date;

public class Jugador extends Persona {

    private Date fechaNacimiento;
    private String posicion;
    private int acciones;
    private Equipo equipo;

    public Jugador(int idPersona, long cedula, String nombre, Ciudad ciudadNacimiento, Date fechaNacimiento, String posicion, int acciones, Equipo equipo) {
        super(idPersona, cedula, nombre, ciudadNacimiento);
        this.fechaNacimiento = fechaNacimiento;
        this.posicion = posicion;
        this.acciones = acciones;
        this.equipo = equipo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public int getAcciones() {
        return acciones;
    }

    public void setAcciones(int acciones) {
        this.acciones = acciones;
    }

    public Equipo getEquipo() {return equipo;}

    public void setEquipo(Equipo equipo) {this.equipo = equipo;}
}
