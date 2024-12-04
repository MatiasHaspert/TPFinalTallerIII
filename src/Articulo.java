public class Articulo {
    private String nombre;
    private String descripcion;
    private float precioBase;
    private float precioFinal;
    private Subastador propietario;

    public Articulo(String nombre, String descripcion, float precioBase, Subastador propietario){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioBase = precioBase;
        this.precioFinal = precioBase;
        this.propietario = propietario;
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

    public Subastador getPropietario() {
        return propietario;
    }
}
