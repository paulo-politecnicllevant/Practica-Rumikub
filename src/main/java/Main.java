import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("============ ELIGE EL JUEGO =============");
        System.out.println("1. Rummi");
        System.out.println("2. Rummikub");
        System.out.println("3. Gin Rummi");
        System.out.print("Opcion: ");

        int opcion = sc.nextInt();

        System.out.print("Elige el numero de jugadores: ");
        int n = sc.nextInt();

        JuegoBase juego;

        switch (opcion) {
            case 1:
                juego = new JuegoRummi(n);
                break;

            case 2:
                juego = new JuegoRummikub(n);
                break;

            case 3:
                juego = new JuegoGinRummi(n);
                break;
            default:
                System.out.println("Opcion invalida");
                return;
        }

        juego.iniciar();
    }
}