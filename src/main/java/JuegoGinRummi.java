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

    private void descartar(Jugador jugador) {
        jugador.mostrarMano();
        System.out.print("Elige la carta que quieres descartar: ");

        int index = scanner.nextInt();
        scanner.nextLine();

        Carta carta = jugador.getMano().get(index);
        jugador.eliminarCarta(carta);
        cartaDescarte.add(carta);

        System.out.println(jugador.getNombre() + " ha descartado: " + carta);
    }

    @Override
    public void iniciar() {

    }
}
