import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class MazoRummikub implements Serializable {
    private ArrayList<Carta> fichas;

    //Constructor
    public MazoRummikub() {
        fichas = new ArrayList<>();

        String[] colores = {"Rojo", "Azul", "Amarillo", "Negro"};

        for (int i = 0; i < 2; i++) {
            for (String color : colores) {
                for (int valor = 1; valor <= 13; valor++) {
                    fichas.add(new Carta(color, valor, false));
                }
            }
        }

        //Cartas joker
        fichas.add(new Carta("", 0, true));
        fichas.add(new Carta("", 0, true));

        Collections.shuffle(fichas);
    }

    //Método para robar una ficha del mazo
    public Carta robar() {
        return fichas.removeFirst();
    }

    //Indica si ya no quedan fichas en la bolsa
    public boolean estaVacio() {
        return fichas.isEmpty();
    }
}
