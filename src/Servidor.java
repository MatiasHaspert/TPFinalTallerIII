import java.io.IOException;
import java.net.ServerSocket;

public class Servidor {
    private GestorDeSubasta gestor;

    public static void main(String[] args) {
        int puerto = 5555;

        try {
            ServerSocket socketServidor = new ServerSocket(puerto);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
}
