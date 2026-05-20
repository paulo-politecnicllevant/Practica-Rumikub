import java.util.ArrayList;
import java.util.Scanner;

public class Juego {
    private ArrayList<Jugador> jugadores;
    private Mazo mazo;
    private Mesa mesa;
    private Scanner scanner;

    public Juego(int n) {
        jugadores = new ArrayList<>();
        mazo = new Mazo();
        mesa = new Mesa();
        scanner = new Scanner(System.in);

        for (int i = 1; i <= n; i++) {
            jugadores.add(new Jugador("Jugador " + i));
        }

        repartir();
    }

    private void repartir() {
        for (int i = 0; i < 14; i++) {
            for (Jugador j : jugadores) {
                j.anyadirCarta(mazo.robar());
            }
        }
    }

    private void jugarCombinacion(Jugador jugador) {
        System.out.println("¿Cuantas cartas tendra la combinacion?");
        int n = scanner.nextInt();
        scanner.nextLine();

        ArrayList<Carta> jugada = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            jugador.mostrarMano();
            System.out.print("Elige el indice de la carta: ");
            int index = scanner.nextInt();
            scanner.nextLine();

            jugada.add(jugador.getMano().get(index));
        }

        if (Combinacion.esGrupo(jugada) || Combinacion.esEscalera(jugada)) {
            System.out.println("Combinacion valida: " + jugada);
            mesa.agregar(jugada);

            for (Carta c : jugada){
                jugador.eliminarCarta(c);
            }

        } else {
            System.out.println("Combinacion invalida");
        }
    }

    private void anadirAMesa(Jugador jugador) {
        mesa.mostrar();
        System.out.print("Elige el numero de la jugada: ");

        int jugadaIndex = scanner.nextInt();
        scanner.nextLine();

        jugador.mostrarMano();
        System.out.print("Elige una carta para añadir: ");

        int cartaIndex = scanner.nextInt();
        scanner.nextLine();

        Carta carta = jugador.getMano().get(cartaIndex);
        ArrayList<Carta> jugada = mesa.getJugadas().get(jugadaIndex);

        jugada.add(carta);

        if (Combinacion.esGrupo(jugada) || Combinacion.esEscalera(jugada)) {
            System.out.println("Carta añadida correctamente");
            jugador.eliminarCarta(carta);
        } else {
            System.out.println("No se puede añadir esa carta");
            jugada.remove(carta);
        }
    }

    public void iniciar() {

    }
}