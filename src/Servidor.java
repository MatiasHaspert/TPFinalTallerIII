import java.io.IOException;
import java.net.ServerSocket;

public class Servidor {
    GestorDeSubasta gestor;
    ServerSocket socketServidor;

    public Servidor() {
        try {
            this.socketServidor = new ServerSocket(5555);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        this.gestor = new GestorDeSubasta();
    }

}
