import java.util.ArrayList;
import java.util.Collections;

public class MazoRummikub {
    private ArrayList<Carta> fichas;

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

    public Carta robar() {
        return fichas.removeFirst();
    }

    public boolean estaVacio() {
        return fichas.isEmpty();
    }
}
