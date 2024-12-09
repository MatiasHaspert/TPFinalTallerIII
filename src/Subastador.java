public class Subastador extends Usuario{
    public Subastador(String nombre, String email){
        super(nombre, email);
    }

    public Articulo cargarArticulo(String nombre, String descripcion, float precioBase, Subastador propietario){
        return new Articulo(nombre, descripcion, precioBase, propietario);
    }
}
