import java.io.Serializable;
import java.util.ArrayList;

public class JuegoRummyArgentino extends JuegoBase implements Serializable {

    public JuegoRummyArgentino(int n) {
        super(n);
        this.mazo = new Mazo();
        this.descarte = new ArrayList<>();
        repartir();
    }

    private void repartir() {
        for (int i = 0; i < 9; i++) {
            for (Jugador j : jugadores) {
                j.anyadirCarta(mazo.robar());
            }
        }
        descarte.add(mazo.robar());
    }

    private void bajarCombinacion(Jugador jugador) {
        System.out.println("¿Cuantas cartas tendra la combinacion?");
        int n = scanner.nextInt();
        scanner.nextLine();

        ArrayList<Carta> jugada = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            jugador.mostrarMano();
            System.out.print("Elige indice de carta: ");

            int indice = scanner.nextInt();
            scanner.nextLine();
            jugada.add(jugador.getMano().get(indice));
        }

        int comodines = 0;
        for (Carta c : jugada){
            if (c.esComodin()){
                comodines++;
            }
        }

        if (comodines > 1) {
            System.out.println("No puedes usar mas de un comodin en la misma jugada");
            return;
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

    private void descartar(Jugador jugador) {
        jugador.mostrarMano();
        System.out.print("Elige una carta para descartar: ");

        int indice = scanner.nextInt();
        scanner.nextLine();

        Carta carta = jugador.getMano().get(indice);
        jugador.eliminarCarta(carta);
        descarte.add(carta);

        System.out.println("Has descartado: " + carta);
    }

    private void anadirAMesa(Jugador jugador) {
        mesa.mostrar();

        System.out.print("Elige el numero de la jugada: ");
        int jugadaIndice = scanner.nextInt();
        scanner.nextLine();

        jugador.mostrarMano();
        System.out.print("Elige una carta para añadir: ");
        int cartaIndice = scanner.nextInt();
        scanner.nextLine();

        Carta carta = jugador.getMano().get(cartaIndice);
        ArrayList<Carta> jugada = mesa.getJugadas().get(jugadaIndice);

        jugada.add(carta);

        if (Combinacion.esGrupo(jugada) || Combinacion.esEscalera(jugada)) {
            System.out.println("Carta añadida correctamente");
            jugador.eliminarCarta(carta);
        } else {
            System.out.println("Esa carta rompe la jugada");
            jugada.remove(carta);
        }
    }

    @Override
    public void iniciar() {

        turnoActual = 0;
        boolean fin = false;

        while (!fin) {

            Jugador jugador = jugadores.get(turnoActual);

            System.out.println("==============================");
            System.out.println("TURNO DE " + jugador.getNombre());
            System.out.println("==============================");

            jugador.mostrarMano();
            System.out.println("Carta en descarte: " + descarte.get(descarte.size() - 1));

            System.out.println("1. Robar del mazo");
            System.out.println("2. Robar del descarte");
            System.out.print("Elige una opcion: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            Carta robada;

            if (opcion == 1) {
                robada = mazo.robar();
                System.out.println("Has robado del mazo: " + robada);
            } else {
                robada = descarte.remove(descarte.size() - 1);
                System.out.println("Has robado del descarte: " + robada);
            }

            jugador.anyadirCarta(robada);

            boolean turnoTerminado = false;

            while (!turnoTerminado) {

                System.out.println("============ ACCIONES ==============");
                System.out.println("1. Ver mano");
                System.out.println("2. Bajar combinacion");
                System.out.println("3. Añadir a la mesa");
                System.out.println("4. Descartar y terminar turno");
                System.out.print("Elige una opción: ");

                int accion = scanner.nextInt();
                scanner.nextLine();

                switch (accion) {
                    case 1:
                        jugador.mostrarMano();
                        break;

                    case 2:
                        bajarCombinacion(jugador);
                        break;

                    case 3:
                        anadirAMesa(jugador);
                        break;

                    case 4:
                        descartar(jugador);
                        turnoTerminado = true;
                        break;

                    default:
                        System.out.println("Opcion invalida");
                }
            }

            //Si el jugador se quedó sin cartas, gana
            if (jugador.getMano().isEmpty()) {
                System.out.println(jugador.getNombre() + " ha ganado la ronda");
                fin = true;
            }

            turnoActual = (turnoActual + 1) % jugadores.size();
        }
    }
}