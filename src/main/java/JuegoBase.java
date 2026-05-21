import java.util.ArrayList;
import java.util.Scanner;

public abstract class JuegoBase {
    protected ArrayList<Jugador> jugadores;
    protected Mesa mesa;
    protected Scanner scanner;

    public JuegoBase(int n) {
        jugadores = new ArrayList<>();
        mesa = new Mesa();
        scanner = new Scanner(System.in);

        for (int i = 1; i <= n; i++) {
            jugadores.add(new Jugador("Jugador " + i));
        }
    }

    public abstract void iniciar();
}
