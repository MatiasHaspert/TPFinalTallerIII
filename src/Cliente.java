import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    private Usuario cliente;

    public Cliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public static void main(String[] args) {
        Cliente clienteApp = definirRolCliente();

        DataInputStream dataIn;
        DataOutputStream dataOut;
        ObjectInputStream objectIn;
        ObjectOutputStream objectOut;

        String servidor = "localhost"; // Dirección del servidor
        int puerto = 5555;             // Puerto del servidor

        try (Socket socket = new Socket(servidor, puerto)) {
            System.out.println("Conectado al servidor.");

            dataIn = new DataInputStream(socket.getInputStream());
            dataOut = new DataOutputStream(socket.getOutputStream());
            objectIn = new ObjectInputStream(socket.getInputStream());
            objectOut = new ObjectOutputStream(socket.getOutputStream());

            if (clienteApp.getCliente() instanceof Subastador) {
                System.out.println("Eres un Subastador. Inicia una subasta:");
                Subastador subastador = (Subastador) clienteApp.getCliente();
                Subasta nuevaSubasta = new Subasta(subastador.cargarArticulo(), subastador.setTiempo(), subastador);
                objectOut.writeObject(nuevaSubasta); //Envio el objeto
                objectOut.flush();

                //Luego leer la respuesta del servidor
                boolean subastaIniciada = dataIn.readBoolean();
                String mensaje = subastaIniciada ? "Subasta Iniciada" : "Error al iniciar la subasta";
                System.out.println(mensaje);

            } else if (clienteApp.getCliente() instanceof Participante) {
                System.out.println("Eres un Participante. Esperando mensajes:");
                Participante participante = (Participante) clienteApp.getCliente();
                objectOut.writeObject(participante);
                objectOut.flush();

                boolean esperaSubasta = dataIn.readBoolean();
                if (!esperaSubasta) {
                    System.out.println("Esperando por un subastador...");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Cliente definirRolCliente() {
        Scanner scanner = new Scanner(System.in);
        Cliente c = null;
        String tipo = "";
        while (!tipo.equals("S") && !tipo.equals("P")) {
            System.out.println("¿Desea ser Subastador o Participante? (S/P): ");
            tipo = scanner.nextLine().toUpperCase();
            if (!tipo.equals("S") && !tipo.equals("P")) {
                System.out.println("Opción no válida. Por favor ingrese 'S' para Subastador o 'P' para Participante.");
            }
        }

        System.out.println("Ingrese su nombre: ");
        String nombre = scanner.nextLine();

        String email = "";
        while (true) {
            System.out.println("Ingrese su email: ");
            email = scanner.nextLine();
            if (email.contains("@")) {
                break; // Email válido
            } else {
                System.out.println("Email no válido. Asegúrese de que el email contenga '@'.");
            }
        }

        switch (tipo) {
            case "S":
                // Crear un subastador y asignarlo al cliente
                c = new Cliente(new Subastador(nombre, email));
                System.out.println("¡Bienvenido, Subastador " + nombre + "!");
                break;
            case "P":
                // Crear un participante y asignarlo al cliente
                c = new Cliente(new Participante(nombre, email));
                System.out.println("¡Bienvenido, Participante " + nombre + "!");
                break;
            default:
                System.out.println("Opción no válida. Saliendo...");
                System.exit(0); // Terminar el programa si la opción es inválida
        }
        return c;
    }

    public Usuario getCliente() {
        return cliente;
    }

}
