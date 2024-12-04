import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private Usuario cliente;

    public Cliente(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("¿Desea ser Subastador o Participante? (S/P): ");
        String tipo = scanner.nextLine().toUpperCase();

        System.out.println("Ingrese su nombre: ");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese su email: ");
        String email = scanner.nextLine();

        switch (tipo) {
            case "S":
                this.cliente = new Subastador(nombre, email);
                break;
            case "P":
                this.cliente = new Participante(nombre, email);
                break;
            default:
                System.out.println("Opción no válida. Saliendo...");
        }
    }
    public static void main(String[] args) {
        Cliente c = new Cliente();

        String servidor = "localhost"; // Dirección del servidor
        int puerto = 5555;            // Puerto del servidor

        try (Socket socket = new Socket(servidor, puerto)) {
            System.out.println("Conectado al servidor.");

            // Flujos de entrada y salida
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            // Leer desde la consola para enviar al servidor
            BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
