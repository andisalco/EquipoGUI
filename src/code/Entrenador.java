package code;

public class Entrenador extends Persona {
    private int experiencia;
    private String estudio;

    public Entrenador(int idPersona, long cedula, String nombre, Ciudad ciudadNacimiento, int experiencia, String estudio) {
        super(idPersona, cedula, nombre, ciudadNacimiento);
        this.experiencia = experiencia;
        this.estudio = estudio;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    public String getEstudio() {
        return estudio;
    }

    public void setEstudio(String estudio) {
        this.estudio = estudio;
    }
}
