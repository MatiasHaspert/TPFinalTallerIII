public class Articulo {
    private String nombre;
    private String descripcion;
    private float precioBase;
    private float precioFinal;

    public Articulo(String nombre, String descripcion, float precioBase){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioBase = precioBase;
        this.precioFinal = precioBase;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getPrecioBase() {
        return precioBase;
    }

    public float getPrecioFinal() {
        return precioFinal;
    }

}
