import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Combinacion implements Serializable {

    //Compruebo si una lista de cartas forma un GRUPO
    public static boolean esGrupo(ArrayList<Carta> cartas) {
        if (cartas.size() < 3 || cartas.size() > 4) return false;

        int valor = -1;

        for (Carta c : cartas) {
            if (c.esJoker()) {
                continue;
            }

            if (valor == -1) {
                //Primera carta no comodín define el valor del grupo
                valor = c.getValor();
            } else if (valor != c.getValor()) {
                // Si alguna carta no coincide, no es un grupo
                return false;
            }
        }
        return true;
    }

    // Compruebo si una lista de cartas forma un GRUPO
    public static boolean esEscalera(ArrayList<Carta> cartas) {
        if (cartas.size() < 4) {
            return false;
        }

        //Copia para no modificar la original
        ArrayList<Carta> copia = new ArrayList<>(cartas);

        //Ordenar por valor
        copia.sort(Comparator.comparingInt(Carta::getValor));

        String palo = null;

        //Comprobar que todas las cartas que no son comodín tienen el mismo palo
        for (Carta c : copia) {
            if (!c.esJoker()) {
                if (palo == null) {
                    palo = c.getPalo();
                } else if (!palo.equals(c.getPalo())) {
                    return false;
                }
            }
        }

        //Contar comodines disponibles
        int jokers = (int) copia.stream().filter(Carta::esJoker).count();

        // Comprobar si son consecutivas
        for (int i = 1; i < copia.size(); i++) {
            Carta previa = copia.get(i - 1);
            Carta actual = copia.get(i);

            if (previa.esJoker() || actual.esJoker()) continue;

            //Si no son consecutivas
            if (actual.getValor() != previa.getValor() + 1) {
                //Mirar si puedo usar un comodín para rellenar el hueco
                if (jokers > 0){
                    jokers--;
                } else{
                    return false;
                }
            }
        }
        return true;
    }

    //Calcula los puntos totales de una combinación
    public static int puntos(ArrayList<Carta> cartas) {
        int suma = 0;

        for (Carta c : cartas) {
            suma += c.getPuntos();
        }

        return suma;
    }
}
