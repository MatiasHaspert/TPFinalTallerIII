public abstract class Usuario {
    private String nombre;
    private String email;
    private Rol r;

    public Usuario (String nombre, String email, Rol r){
        this.nombre = nombre;
        this.email = email;
        this.r = r;
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

    public Rol getRol() {
        return r;
    }

    public abstract void
}



