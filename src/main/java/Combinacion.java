import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Combinacion {

    public static boolean esGrupo(ArrayList<Carta> cartas) {
        if (cartas.size() < 3 || cartas.size() > 4) return false;

        int valor = -1;

        for (Carta c : cartas) {
            if (c.esJoker()) {
                continue;
            }

            if (valor == -1) {
                valor = c.getValor();
            } else if (valor != c.getValor()) {
                return false;
            }
        }
        return true;
    }

    public static boolean esEscalera(ArrayList<Carta> cartas) {
        if (cartas.size() < 4) {
            return false;
        }

        //Copia para no modificar la original
        ArrayList<Carta> copia = new ArrayList<>(cartas);

        //Ordenar por valor
        copia.sort(Comparator.comparingInt(Carta::getValor));

        String palo = null;

        for (Carta c : copia) {
            if (!c.esJoker()) {
                if (palo == null) {
                    palo = c.getPalo();
                } else if (!palo.equals(c.getPalo())) {
                    return false;
                }
            }
        }

        int jokers = (int) copia.stream().filter(Carta::esJoker).count();

        for (int i = 1; i < copia.size(); i++) {
            Carta previa = copia.get(i - 1);
            Carta actual = copia.get(i);

            if (previa.esJoker() || actual.esJoker()) continue;

            if (actual.getValor() != previa.getValor() + 1) {
                if (jokers > 0){
                    jokers--;
                } else{
                    return false;
                }
            }
        }
        return true;
    }

    public static int puntos(ArrayList<Carta> cartas) {
        int suma = 0;

        for (Carta c : cartas) {
            suma += c.getPuntos();
        }

        return suma;
    }
}
