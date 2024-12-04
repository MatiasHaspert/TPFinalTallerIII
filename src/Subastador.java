public class Subastador extends Usuario{
    public Subastador(String nombre, String email){
        super(nombre, email, Rol.SUBASTADOR);
    }

    public Articulo cargarArticulo(String nombre, String descripcion, float precioBase, Subastador propietario){
        return new Articulo(nombre, descripcion, precioBase, propietario);
    }
}
