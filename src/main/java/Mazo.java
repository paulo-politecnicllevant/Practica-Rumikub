import java.util.ArrayList;
import java.util.Collections;

public class Mazo {
    private ArrayList<Carta> cartas;

    public Mazo(){
        cartas = new ArrayList<>();
        String[] palos = {"Corazon", "Diamante", "Pica", "Trebol"};

        for (int i = 0; i < 2; i++){
            for (String palo : palos){
                for (int j = 0; j < 13; j++){
                    cartas.add(new Carta(palo, j, false));
                }
            }
        }
        //Cartas joker
        cartas.add(new Carta("", 0 , true));
        cartas.add(new Carta("", 0 , true));

        Collections.shuffle(cartas);
    }

    public Carta robar(){
        return cartas.removeFirst();
    }

    public boolean estaVacio(){
        return cartas.isEmpty();
    }
}
