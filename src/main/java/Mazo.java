import enums.ColorFicha;
import enums.Palo;
import enums.TipoMazo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Mazo implements Serializable {
    private ArrayList<Carta> cartas;

    //Constructor
    public Mazo(TipoMazo tipo){
        cartas = new ArrayList<>();

        if (tipo == TipoMazo.CARTAS){
            crearMazoCartas();
        }

        if (tipo == TipoMazo.FICHAS){
            crearMazoFichas();
        }

        Collections.shuffle(cartas);
    }

    private void crearMazoCartas(){
        for (int i = 0; i < 2; i++){
            for (Palo palo : Palo.values()){
                for (int valor = 1; valor <= 13; valor++){
                    cartas.add(new Carta(palo, valor, false));
                }
            }
        }

        cartas.add(new Carta(null, 0, true));
        cartas.add(new Carta(null, 0, true));
    }

    private void crearMazoFichas(){
        for (int i = 0; i < 2; i++){
            for (ColorFicha color : ColorFicha.values()){
                for (int valor = 1; valor <= 13; valor++){
                    cartas.add(new Carta(color, valor, false));
                }
            }
        }

        cartas.add(new Carta(null, 0, true));
        cartas.add(new Carta(null, 0, true)
        );
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
