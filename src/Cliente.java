import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private Usuario cliente;

    public Cliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public static void main(String[] args) {
        Cliente clienteApp = new Cliente(null);
        clienteApp.definirRolCliente();

        String servidor = "localhost"; // Dirección del servidor
        int puerto = 5555;             // Puerto del servidor

        try (Socket socket = new Socket(servidor, puerto)) {
            System.out.println("Conectado al servidor.");

            // Flujos de entrada y salida
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            // Leer desde la consola para enviar al servidor
            BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));

            if (clienteApp.getCliente() instanceof Subastador) {
                System.out.println("Eres un Subastador. Inicia una subasta:");
                Subastador subastador = (Subastador) clienteApp.getCliente();
                //subastador.cargarArticulo();

                // Ejemplo: Subastador envía mensajes al servidor
                while (true) {
                    System.out.print("Mensaje al servidor (subasta): ");
                    Participante participante = (Participante) clienteApp.getCliente();
                    //participante.ingresarOferta();
                    String mensaje = consola.readLine();
                    salida.println("Subastador: " + mensaje);
                    if (mensaje.equalsIgnoreCase("salir")) break;
                }
            } else if (clienteApp.getCliente() instanceof Participante) {
                System.out.println("Eres un Participante. Esperando mensajes:");

                // Ejemplo: Participante recibe mensajes del servidor
                while (true) {
                    String mensajeServidor = entrada.readLine();
                    if (mensajeServidor == null || mensajeServidor.equalsIgnoreCase("salir")) break;

                    System.out.println("Mensaje del servidor: " + mensajeServidor);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void definirRolCliente() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("¿Desea ser Subastador o Participante? (S/P): ");
        String tipo = scanner.nextLine().toUpperCase();

        System.out.println("Ingrese su nombre: ");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese su email: ");
        String email = scanner.nextLine();

        switch (tipo) {
            case "S":
                new Cliente(new Subastador(nombre, email));
                break;
            case "P":
                new Cliente(new Participante(nombre, email));
                break;
            default:
                System.out.println("Opción no válida. Saliendo...");
                System.exit(0);
        }
    }

    public Usuario getCliente() {
        return cliente;
    }

}
