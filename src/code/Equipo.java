package code;


import java.util.Date;

public class Equipo {
    private int idEquipo;
    private String nombre;
    private int titulos;
    private Date fundacion;
    private Ciudad ciudad;
    private Entrenador entrenador;

    public Equipo(int idEquipo, String nombre, int titulos, Date fundacion, Ciudad ciudad, Entrenador entrenador) {
        this.idEquipo = idEquipo;
        this.nombre = nombre;
        this.titulos = titulos;
        this.fundacion = fundacion;
        this.ciudad = ciudad;
        this.entrenador = entrenador;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTitulos() {
        return titulos;
    }

    public void setTitulos(int titulos) {
        this.titulos = titulos;
    }

    public Date getFundacion() {
        return fundacion;
    }

    public void setFundacion(Date fundacion) {
        this.fundacion = fundacion;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Entrenador getEntrenador() {return entrenador;}

    public void setEntrenador(Entrenador entrenador) {this.entrenador = entrenador;}
}
