import java.util.ArrayList;

public class JuegoRummikub extends JuegoBase {
    private MazoRummikub bolsa;
    private boolean[] jugadorHaHecho30; //Para comprobar la primera jugada

    public JuegoRummikub(int n) {
        super(n);
        bolsa = new MazoRummikub();
        jugadorHaHecho30 = new boolean[n];
        repartir();
    }

    private void repartir() {
        for (int i = 0; i < 14; i++) {
            for (Jugador j : jugadores) {
                j.anyadirCarta(bolsa.robar());
            }
        }
    }

    private void jugarCombinacion(Jugador jugador, int turno) {
        System.out.println("¿Cuantas fichas tendra la combinacion?");
        int n = scanner.nextInt();
        scanner.nextLine();

        ArrayList<Carta> jugada = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            jugador.mostrarMano();
            System.out.print("Elige el indice de las ficha: ");

            int indice = scanner.nextInt();
            scanner.nextLine();

            jugada.add(jugador.getMano().get(indice));
        }

        int puntos = Combinacion.puntos(jugada);

        if (!jugadorHaHecho30[turno]) {

            if (puntos < 30) {
                System.out.println("Necesitas al menos 30 puntos en tu primera jugada.");
                return;
            } else {
                jugadorHaHecho30[turno] = true;
                System.out.println("Primera jugada valida ya que supera los 30 punos");
            }
        }

        if (Combinacion.esGrupo(jugada) || Combinacion.esEscalera(jugada)) {
            System.out.println("Combinacian valida: " + jugada);
            mesa.agregar(jugada);

            for (Carta c : jugada){
                jugador.eliminarCarta(c);
            }

        } else {
            System.out.println("Combinacion invalida");
        }
    }

    @Override
    public void iniciar() {

    }
}
