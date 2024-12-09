import java.util.Scanner;

public class Subastador extends Usuario {

    public Subastador(String nombre, String email) {
        super(nombre, email);
    }

    public Articulo cargarArticulo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del artículo: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese la descripción del artículo: ");
        String descripcion = scanner.nextLine();

        float precioBase = 0;
        while (true) {
            try {
                System.out.print("Ingrese el precio base del artículo: ");
                precioBase = Float.parseFloat(scanner.nextLine());
                if (precioBase > 0) {
                    break;
                } else {
                    System.out.println("El precio base debe ser mayor a 0. Intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido. Intente nuevamente.");
            }
        }

        return new Articulo(nombre, descripcion, precioBase);
    }

    public long setTiempo() {
        Scanner scanner = new Scanner(System.in);
        long tiempoEnSegundos = 0;
        while (true) {
            try {
                System.out.print("Ingrese el tiempo para la subasta (en segundos, entre 30 y 3600): ");
                tiempoEnSegundos = Long.parseLong(scanner.nextLine());
                if (tiempoEnSegundos >= 30 && tiempoEnSegundos <= 3600) {
                    break;
                } else {
                    System.out.println("El tiempo debe estar entre 30 y 3600 segundos. Intente nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número válido. Intente nuevamente.");
            }
        }

        return tiempoEnSegundos * 1000; // Convertir a milisegundos.
    }
}
