package sv.edu.udb.entidades;

import java.time.LocalTime;

public class Dvd extends Material {
    private String director;
    private LocalTime duracion;
    private String genero;
    private int stock;

    public Dvd () {

    }

    public Dvd(String codigoId, String titulo, String tipo, String director, LocalTime duracion, String genero, int stock) {
        super(codigoId, titulo, tipo);
        this.director = director;
        this.duracion = duracion;
        this.genero = genero;
        this.stock = stock;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public LocalTime getDuracion() {
        return duracion;
    }

    public void setDuracion(LocalTime duracion) {
        this.duracion = duracion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
