import java.io.Serializable;

public class Subasta implements Serializable {
    private Articulo articulo;
    private long tiempoLimite; // Tiempo en milisegundos.
    private Subastador subastador;
    private boolean finalizo;

    public Subasta(Articulo articulo, long tiempoLimite, Subastador subastador) {
        this.articulo = articulo;
        this.tiempoLimite = tiempoLimite;
        this.subastador = subastador;
        this.finalizo = false;
    }

    public Articulo getArticulo() { return articulo; }
    public long getTiempoLimite() { return tiempoLimite; }
    public Subastador getSubastador() { return subastador; }
}
