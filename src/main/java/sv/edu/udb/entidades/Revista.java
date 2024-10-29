package sv.edu.udb.entidades;

import java.util.Date;

public class Revista extends Material {
    private String editorial;
    private String periodicidad;
    private Date fechaPublicacion;
    private int stock;

    public Revista () {

    }

    public Revista(String titulo, String editorial, String periodicidad, Date fechaPublicacion, int stock) {
        super(titulo);
        this.editorial = editorial;
        this.periodicidad = periodicidad;
        this.fechaPublicacion = fechaPublicacion;
        this.stock = stock;
    }

    public Revista(String editorial, String periodicidad, Date fechaPublicacion, int stock) {
        this.editorial = editorial;
        this.periodicidad = periodicidad;
        this.fechaPublicacion = fechaPublicacion;
        this.stock = stock;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(String periodicidad) {
        this.periodicidad = periodicidad;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
