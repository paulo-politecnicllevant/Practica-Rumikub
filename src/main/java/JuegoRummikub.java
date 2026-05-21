public class JuegoRummikub extends JuegoBase {
    private Mazo bolsa;

    public JuegoRummikub(int n) {
        super(n);
        bolsa = new Mazo();
        repartir();
    }

    private void repartir() {
        for (int i = 0; i < 14; i++) {
            for (Jugador j : jugadores) {
                j.anyadirCarta(bolsa.robar());
            }
        }
    }

    @Override
    public void iniciar() {

    }
}
