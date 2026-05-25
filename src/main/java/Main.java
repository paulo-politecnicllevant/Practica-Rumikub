import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("============ MENU PRINCIPAL ============");
        System.out.println("1. Nueva partida");
        System.out.println("2. Cargar partida");
        System.out.println("3. Salir");
        System.out.print("Opción: ");

        int opcion = sc.nextInt();
        sc.nextLine();

        JuegoBase juego = null;

        switch (opcion) {
            case 1:
                juego = crearNuevaPartida(sc);
                break;

            case 2:
                System.out.print("Nombre del archivo a cargar: ");
                String archivo = sc.nextLine();
                juego = JuegoBase.cargarPartida(archivo);

                if (juego == null) {
                    System.out.println("No se pudo cargar la partida");
                    return;
                }
                break;

            case 3:
                return;

            default:
                System.out.println("Opcion invalida");
                return;
        }

        juego.iniciar();
    }

    private static JuegoBase crearNuevaPartida(Scanner sc) {
        System.out.println("============ ELIGE EL JUEGO =============");
        System.out.println("1. Rummi");
        System.out.println("2. Rummikub");
        System.out.println("3. Gin Rummi");
        System.out.println("4. Rummi Argentino");
        System.out.print("Opción: ");

        int opcion = sc.nextInt();
        sc.nextLine();

        System.out.print("Número de jugadores: ");
        int n = sc.nextInt();
        sc.nextLine();

        switch (opcion) {
            case 1: return new JuegoRummi(n);
            case 2: return new JuegoRummikub(n);
            case 3: return new JuegoGinRummi(n);
            case 4: return new JuegoRummyArgentino(n);
            default:
                System.out.println("Opción inválida");
                return null;
        }
    }
}
