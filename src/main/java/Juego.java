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

    private void descartar(Jugador jugador, ArrayList<Carta> descarte) {
        jugador.mostrarMano();
        System.out.print("Elige la carta que quieres descartar: ");

        int index = scanner.nextInt();
        scanner.nextLine();

        Carta carta = jugador.getMano().get(index);
        jugador.eliminarCarta(carta);
        descarte.add(carta);

        System.out.println(jugador.getNombre() + " ha descartado: " + carta);
    }


    public void iniciar() {
        int turno = 0;
        ArrayList<Carta> descarte = new ArrayList<>();

        //Descartar primera carta
        descarte.add(mazo.robar());

        boolean fin = false;

        while (!fin) {

            Jugador jugador = jugadores.get(turno);

            System.out.println("==============================");
            System.out.println("TURNO DE " + jugador.getNombre());
            System.out.println("==============================");

            jugador.mostrarMano();
            mesa.mostrar();
            System.out.println("Carta en descarte: " + descarte.get(descarte.size() - 1));

            //Robar carta
            System.out.println("1. Robar del mazo");
            System.out.println("2. Robar del descarte");
            System.out.print("Elige opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            Carta robada;
            if (opcion == 1) {
                robada = mazo.robar();
                System.out.println(jugador.getNombre() + " roba del mazo: " + robada);
            } else {
                robada = descarte.remove(descarte.size() - 1);
                System.out.println(jugador.getNombre() + " roba del descarte: " + robada);
            }

            jugador.anyadirCarta(robada);

            boolean turnoTerminado = false;

            while (!turnoTerminado) {
                System.out.println("============= ACCIONES =============");
                System.out.println("1. Ver mano");
                System.out.println("2. Jugar combinación");
                System.out.println("3. Añadir carta a combinación existente");
                System.out.println("4. Descartar y terminar turno");
                System.out.print("Elige opción: ");

                int accion = scanner.nextInt();
                scanner.nextLine();

                switch (accion) {
                    case 1:
                        jugador.mostrarMano();
                        break;

                    case 2:
                        jugarCombinacion(jugador);
                        break;

                    case 3:
                        anadirAMesa(jugador);
                        break;

                    case 4:
                        turnoTerminado = true;
                        descartar(jugador, descarte);
                        break;

                    default:
                        System.out.println("Opcion invalida");
                }
            }

            if (jugador.gano()) {
                System.out.println(jugador.getNombre() + " ha ganado la pratida");
                fin = true;
            }

            turno = (turno + 1) % jugadores.size();
        }
    }

}