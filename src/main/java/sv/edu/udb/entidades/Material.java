package sv.edu.udb.entidades;

public class Material {
    private String codigoId;
    private String titulo;
    private String tipo;

    public Material () {

    }

    public Material(String codigoId, String titulo, String tipo) {
        this.codigoId = codigoId;
        this.titulo = titulo;
        this.tipo = tipo;
    }

    public String getCodigoId() {
        return codigoId;
    }

    public void setCodigoId(String codigoId) {
        this.codigoId = codigoId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
