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

    @Override
    public void iniciar() {

    }
}