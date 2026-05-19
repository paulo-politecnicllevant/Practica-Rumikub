import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=========== RUMMY CLÁSICO ===========");
        System.out.print("Número de jugadores: ");

        int n = sc.nextInt();

        Juego juego = new Juego(n);
        juego.iniciar();
    }
}