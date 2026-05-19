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

        String palo = null;

        for (Carta c : cartas) {
            if (c.esJoker()) {
                continue;
            }

            if (palo == null) {
                palo = c.getPalo();
            } else if (!palo.equals(c.getPalo())) {
                return false;
            }

            Collections.sort(cartas, Comparator.comparingInt(Carta::getValor));

            for (int i = 1; i < cartas.size(); i++) {
                if (!cartas.get(i).esJoker() && !cartas.get(i - 1).esJoker()) {
                    if (cartas.get(i).getValor() != cartas.get(i - 1).getValor() + 1)
                        return false;
                }
            }

            return true;
        }
        return false;
    }

    public static int puntos(ArrayList<Carta> cartas) {
        int suma = 0;

        for (Carta c : cartas) {
            suma += c.getPuntos();
        }

        return suma;
    }
}
