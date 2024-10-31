package sv.edu.udb.entidades;

import java.time.LocalTime;

public class CdAudio extends Material {
    private String artista;
    private String genero;
    private LocalTime duracion;
    private int canciones;
    private int stock;

    public CdAudio () {

    }

    public CdAudio(String titulo,  String artista, String genero, LocalTime duracion, int canciones, int stock) {
        super( titulo);
        this.artista = artista;
        this.genero = genero;
        this.duracion = duracion;
        this.canciones = canciones;
        this.stock = stock;
    }



    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalTime getDuracion() {
        return duracion;
    }

    public void setDuracion(LocalTime duracion) {
        this.duracion = duracion;
    }

    public int getCanciones() {
        return canciones;
    }

    public void setCanciones(int canciones) {
        this.canciones = canciones;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
