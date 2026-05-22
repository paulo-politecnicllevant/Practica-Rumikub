import java.util.ArrayList;

public class JuegoGinRummi extends JuegoBase{
    private Mazo mazo;
    private ArrayList<Carta> cartaDescarte;

    public JuegoGinRummi(int n) {
        super(n);
        mazo = new Mazo();
        cartaDescarte = new ArrayList<>();
        repartir();
    }

    public void repartir(){
        for (int i = 0; i < 10; i++){
            for (Jugador j: jugadores){
                j.anyadirCarta(mazo.robar());
            }
        }
    }

    @Override
    public void iniciar() {

    }
}
