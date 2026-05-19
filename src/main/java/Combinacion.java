import java.util.ArrayList;

public class Combinacion {

    public static boolean esGrupo(ArrayList<Carta> cartas) {
        if (cartas.size() < 3 || cartas.size() > 4) return false;

        int valor = -1;

        for (Carta c : cartas) {
            if (c.esJoker()){
                continue;
            }

            if (valor == -1) {
                valor = c.getValor();
            }else if (valor != c.getValor()) {
                return false;
            }
        }
        return true;
    }
}
