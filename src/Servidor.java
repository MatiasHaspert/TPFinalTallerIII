import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class Servidor {
    private static final int PUERTO = 5555;
    private static ServerSocket serverSocket;
    private static ExecutorService pool;
    private static Subasta subastaActual = null;

    public static void main(String[] args) {
        try {
            // Crear un ServerSocket para esperar conexiones
            serverSocket = new ServerSocket(PUERTO);
            pool = Executors.newCachedThreadPool();  // Usamos un pool de hilos para manejar cada cliente

            System.out.println("Servidor iniciado en el puerto " + PUERTO);

            while (true) {
                // Aceptar una nueva conexión de cliente
                Socket socket = serverSocket.accept();
                System.out.println("Nuevo cliente conectado: " + socket.getInetAddress());

                // Crear un nuevo hilo para manejar el cliente
                pool.execute(new ClienteHandler(socket));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Clase interna para manejar la comunicación con cada cliente
    private static class ClienteHandler implements Runnable {
        private Socket socket;
        private DataInputStream dataIn;
        private DataOutputStream dataOut;
        private ObjectInputStream objectIn;
        private ObjectOutputStream objectOut;

        public ClienteHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                // Establecer los streams de comunicación
                dataIn = new DataInputStream(socket.getInputStream());
                dataOut = new DataOutputStream(socket.getOutputStream());
                objectIn = new ObjectInputStream(socket.getInputStream());
                objectOut = new ObjectOutputStream(socket.getOutputStream());

                // Recibir y procesar los mensajes de los clientes
                Object mensaje = objectIn.readObject();
                if (mensaje instanceof Subasta) {
                    if(subastaActual == null) {
                        Subasta subasta = (Subasta) mensaje;
                        dataOut.writeBoolean(true);
                        // Aquí puedes manejar la lógica de la subasta, como enviar actualizaciones a los clientes
                    }
                } else if (mensaje instanceof Participante) {
                    // Si es un participante, procesarlo
                    Participante participante = (Participante) mensaje;
                    System.out.println("Participante conectado: " + participante);
                    // Enviar el estado de la subasta al participante, si es necesario
                    if(subastaActual == null){
                        dataOut.writeBoolean(false);
                    }
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    // Cerrar los streams y el socket cuando se termine
                    if (socket != null) {
                        socket.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
