public abstract class Usuario {
    private String nombre;
    private String email;

    public Usuario (String nombre, String email){
        this.nombre = nombre;
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMail() {
        return email;
    }

    public String verResultadoSubasta(){
        return "";
    }
}



