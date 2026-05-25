import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Mazo implements Serializable {
    private ArrayList<Carta> cartas;

    //Constructor
    public Mazo(){
        cartas = new ArrayList<>();
        String[] palos = {"Corazon", "Diamante", "Pica", "Trebol"};

        for (int i = 0; i < 2; i++){
            for (String palo : palos){
                for (int j = 1; j <= 13; j++){
                    cartas.add(new Carta(palo, j, false));
                }
            }
        }
        //Cartas joker
        cartas.add(new Carta("", 0 , true));
        cartas.add(new Carta("", 0 , true));

        Collections.shuffle(cartas);
    }

    //Método para robar una ficha del mazo
    public Carta robar(){
        return cartas.removeFirst();
    }

    //Indica si ya no quedan fichas en la bolsa
    public boolean estaVacio(){
        return cartas.isEmpty();
    }
}
