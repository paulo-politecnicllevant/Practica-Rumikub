import java.util.ArrayList;

public class JuegoRummyArgentino extends JuegoBase {

    private Mazo mazo;
    private ArrayList<Carta> descarte;

    public JuegoRummyArgentino(int n) {
        super(n);
        mazo = new Mazo();
        descarte = new ArrayList<>();
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

    }
}